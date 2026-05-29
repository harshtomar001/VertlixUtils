package com.harsh.pdfstudio.model;

public class RecentFileItem {
    String pdfName;
    String pdfSize;
    String pdfDate;
    int pdfPages;
    String pdfUri;
    public  RecentFileItem(String pdfName, String pdfSize, String pdfDate, int pdfPages, String pdfUri){
        this.pdfName=pdfName;
        this.pdfSize=pdfSize;
        this.pdfDate=pdfDate;
        this.pdfPages=pdfPages;
        this.pdfUri=pdfUri;
    }

    public String getPdfName() {
        return pdfName;
    }
    public String getPdfSize() {
        return pdfSize;
    }
    public String getPdfDate() {
        return pdfDate;
    }
    public int getPdfPages() {
        return pdfPages;
    }
    public String getPdfUri() {
        return pdfUri;
    }
}