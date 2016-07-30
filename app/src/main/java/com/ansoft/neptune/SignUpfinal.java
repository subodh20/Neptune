package com.ansoft.neptune;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ansoft.neptune.Constants.PC;
import com.ansoft.neptune.Data.Ourdata;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class SignUpfinal extends AppCompatActivity {

    Button finishButton;
    private ImageView imgPhoto;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri photoURI = null;
    String selectedImagePath;
    String filemanagerstring;
    int column_index;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upfinal);
        //imgPhoto = (ImageView) findViewById(R.id.profilePicView);
        imgPhoto = (ImageView) findViewById(R.id.profilePicView);
        imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        finishButton = (Button) findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(SignUpfinal.this);
                pd.setTitle("Please wait");
                pd.setMessage("Loging in.....");
                pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));
                pd.setIndeterminate(false);

                // Finally, show the progress dialog
                pd.show();
                String firstName = Ourdata.getcFn();
                String lastName = Ourdata.getcLn();
                String phoneNumber = Ourdata.getcPh();
                String password = Ourdata.getcPw();
                String email = Ourdata.getcEmail();
                final ParseUser user = new ParseUser();
                user.setEmail(email);
                user.setUsername(email);
                user.setPassword(password);
                user.put(PC.KEY_FULLNAME, firstName + " " + lastName);
                user.put(PC.KEY_PHONE_NUMBER, phoneNumber);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                                // Convert it to byte
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                // Compress image to lower quality scale 1 - 100
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] image = stream.toByteArray();
                                final ParseFile file = new ParseFile("photo.png", image);
                                file.saveInBackground();

                                user.put(PC.KEY_USER_PROFILEPHOTO, file);
                                user.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {

                                        pd.dismiss();
                                        if (e==null){
                                            Intent intent = new Intent(SignUpfinal.this, HomeActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        }else{
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpfinal.this);
                                            builder.setTitle("Signup error occured");
                                            builder.setMessage(e.getMessage());
                                            builder.setPositiveButton("ok", null);
                                            AlertDialog dialog = builder.create();
                                            dialog.show();
                                        }

                                    }
                                });
                            } catch (Exception ce) {

                            }

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpfinal.this);
                            builder.setTitle("Signup error occured");
                            builder.setMessage(e.getMessage());
                            builder.setPositiveButton("ok", null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });

            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            photoURI = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                imgPhoto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*  private void loadImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==SELECT_PICTURE)
            {
                Uri selectedImageUri = data.getData();
                filemanagerstring = selectedImageUri.getPath();
                selectedImagePath = getPath(selectedImageUri);
                imgPhoto.setImageURI(selectedImageUri);
                Bitmap bm = BitmapFactory.decodeFile(imagePath);
            }
        }
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        imagePath = cursor.getString(column_index);

        return cursor.getString(column_index);
    }
    */
}


