package com.example.notepadbasic;

public class Notes {
    String title;
    String text;

    //Controller som skickar till adaptern som sen kan skicka till listviewn



    //Settar och gettar

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    //Konstruktor
    public Notes(String title, String text) {
        this.title = title;
        this.text = text;
    }

}
