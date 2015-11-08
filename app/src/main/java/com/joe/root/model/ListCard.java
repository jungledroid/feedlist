package com.joe.root.model;


import com.joe.root.feed.FeedPartDefine;

import java.util.List;

/**
 * Created by dell on 2015/10/8.
 */
public class ListCard implements Card {
    private String type;
    private String title;
    private String editorial_response;
    private List<Answer> answers;

    public ListCard(){

    }

    public static String getTypeCode(){
        return "list";
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getEditorialResponse() {
        return editorial_response;
    }

    public String getEditorial_response() {
        return editorial_response;
    }

    public void setEditorial_response(String editorial_response) {
        this.editorial_response = editorial_response;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public class Answer{
        private Long id;
        private String title;
        private Long percentage_selected;
        private boolean isChecked;

        public Answer(){

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getPercentage_selected() {
            return percentage_selected;
        }

        public void setPercentage_selected(Long percentage_selected) {
            this.percentage_selected = percentage_selected;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }
    }
}
