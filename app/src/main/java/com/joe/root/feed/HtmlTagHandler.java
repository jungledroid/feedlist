package com.joe.root.feed;

import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

public class HtmlTagHandler implements Html.TagHandler {
    private boolean first = true;
    private String parent = null;
    private int index = 1;

    @Override
    public void handleTag(boolean isOpeningTag, String tag, Editable output, XMLReader xmlReader) {
        //http://stackoverflow.com/questions/3150400/html-list-tag-not-working-in-android-textview-what-can-i-do
        if (tag.equals("ul")) {
            parent = "ul";
            index = 1;
        } else if (tag.equals("ol")) {
            parent = "ol";
            index = 1;
        }
        if (tag.equals("li")) {
            char lastChar = 0;
            if (output.length() > 0) {
                lastChar = output.charAt(output.length() - 1);
            }
            if (parent.equals("ul")) {
                if (first) {
                    if (lastChar == '\n') {
                        output.append("\t? ");
                    } else {
                        output.append("\n\t? ");
                    }
                    first = false;
                } else {
                    first = true;
                }
            } else {
                if (first) {
                    if (lastChar == '\n') {
                        output.append(String.format("\t%s. ", index));
                    } else {
                        output.append(String.format("\n\t%s. ", index));
                    }
                    first = false;
                    index++;
                } else {
                    first = true;
                }
            }
        }
    }
}
