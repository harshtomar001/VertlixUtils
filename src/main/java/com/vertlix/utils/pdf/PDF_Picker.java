package com.vertlix.utils.pdf;

import android.content.Intent;

public class PDF_Picker {

    public static Intent pick_pdf(){

        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);

        intent.setType("application/pdf");

        return intent;
    }
}
