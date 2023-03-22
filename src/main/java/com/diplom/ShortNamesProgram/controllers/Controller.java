package com.diplom.ShortNamesProgram.controllers;

import com.diplom.ShortNamesProgram.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Controller  {

    @FXML
    private Button addText;

    @FXML
    private TextField inputString_first;

    @FXML
    private TextField inputString_second;

    @FXML
    private Label strSaveResult;

    @FXML
    private Label totalMessage;


    private DB db = new DB();
    String str_first = "";
    String str_second = "";
    String resulText = "";


    @FXML
    void addText() throws ClassNotFoundException {

        setValues();
        try {
            db.isConnected();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(str_first.length()==0 || str_second.length()==0){
                noText();
            }
            else if(db.getShort(str_second)){
                clearTextField();
                negative();
            } else{
                addToBase();
                resulText += "Сокращение - " + str_second + " ";
                strSaveResult.setText(resulText);
                positive();
                clearTextField();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void initialize() {
        try {
            db.isConnected();
            ResultSet res = db.getShorts();
            while (res.next()){
                resulText += "Сокращение - " + res.getString("short_name") +"\n";
                System.out.println(res.getString("short_name"));
                strSaveResult.setText(resulText);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        addText.setOnAction(event -> {
            try {
                addText();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }



    void setValues() {

        str_first = inputString_first.getCharacters().toString();
        str_second = inputString_second.getCharacters().toString();

    }

    void addToBase(){
        try {
            db.addToBase(str_second, str_first);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    void clearTextField(){
        inputString_first.setText("");
        inputString_second.setText("");
    }


    void positive(){
        totalMessage.setText("Добавлено");
    }



    void negative(){
        totalMessage.setText("Укажите другое сокращение");
    }

    void noText(){
        totalMessage.setText("Введите текст");
    }



}

