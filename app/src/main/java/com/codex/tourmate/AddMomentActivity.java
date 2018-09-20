package com.codex.tourmate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddMomentActivity extends AppCompatActivity {

    private EditText momentDetailsEt;
    private ImageView momentImageView;
    private Button loadImageButton, saveMomentButton;
    private Bitmap bitmap;
    private static final int LOAD_IMAGE_REQUEST =7;
    private String imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_moment);
        momentDetailsEt = findViewById(R.id.moment_details);
        momentImageView = findViewById(R.id.moment_image_view);
        loadImageButton = findViewById(R.id.load_image_button);
        saveMomentButton = findViewById(R.id.save_moment_button);

        loadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, LOAD_IMAGE_REQUEST);
            }
        });

        saveMomentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String details = momentDetailsEt.getText().toString();
                if (!TextUtils.isEmpty(details) && !imageData.isEmpty()){
                    //Do firebase code here




                }else {
                    Toast.makeText(AddMomentActivity.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();

        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
            imageData = encodeImage(bitmap);
            momentImageView.setImageBitmap(decodeImage(imageData));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String encodeImage(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    public Bitmap decodeImage(String imageString){
        byte[] bytes = Base64.decode(imageString,0);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

}
