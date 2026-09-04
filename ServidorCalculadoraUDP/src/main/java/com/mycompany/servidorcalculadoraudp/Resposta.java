package com.mycompany.servidorcalculadoraudp;

import com.google.gson.Gson;

public class Resposta {

    private String status;
    private String timestamp;
    private String dados;

    public Resposta(String status, String timestamp, String dados) {
        this.status = status;
        this.timestamp = timestamp;
        this.dados = dados;
    }

    public String getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDados() {
        return dados;
    }

    public String paraLinha() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Resposta fromLinha(String linha) {
        Gson gson = new Gson();
        return gson.fromJson(linha, Resposta.class);
    }
}