package com.example.tva_projekt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tva_projekt.databinding.ActivityHribiIzbiraBinding;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class SpletneKamere extends AppCompatActivity {

    ImageView imageView;
    CallbackManager callbackManager;
    ShareButton sbLink;
    ShareButton sbPhoto;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spletne_kamere);
        imageView=findViewById(R.id.imageView);
        sbLink=findViewById(R.id.btnShareLink);
        sbPhoto=findViewById(R.id.btnSharePhoto);
        loginButton=findViewById(R.id.btnFBlog);

        imageView.setImageResource(R.drawable.img);

        callbackManager=CallbackManager.Factory.create();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        ShareLinkContent shareLinkContent=new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=GxrxV37a9YE&ab_channel=WittCode"))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#Planinec").build())
                .build();

        sbLink.setShareContent(shareLinkContent);

        BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        SharePhoto sharePhoto=new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();

        SharePhotoContent sharePhotoContent=new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();

        sbPhoto.setShareContent(sharePhotoContent);
    }

}