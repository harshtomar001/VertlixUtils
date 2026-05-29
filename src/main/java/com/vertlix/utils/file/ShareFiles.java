package com.vertlix.utils.file;

import android.content.Intent;

public class ShareFiles {

    public static  Intent  sharePDF(){
        Intent intent=new Intent(Intent.ACTION_SEND);

        intent.setType("application/pdf");

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


        return intent;


    }

    public  static  Intent shareImage(){
        Intent intent=new Intent(Intent.ACTION_SEND_MULTIPLE);

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.setType("image/*");
        return  intent;
    }

}
