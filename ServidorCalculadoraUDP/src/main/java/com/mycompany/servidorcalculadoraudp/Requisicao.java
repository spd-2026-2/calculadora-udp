/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidorcalculadoraudp;


import com.google.gson.Gson;
/*
Para esse import, adicionar no pom.xml 
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.13.1</version>
</dependency>

Clicar no botão direito sobre o nome do projeto e clicar em "Clean and Build"
*/

public class Requisicao {

    private double num1;
    private double num2;

    public Requisicao(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    public String paraLinha() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Requisicao fromLinha(String linha) {
        Gson gson = new Gson();
        return gson.fromJson(linha, Requisicao.class);
    }
}