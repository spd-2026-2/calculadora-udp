package com.mycompany.servidorcalculadoraudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServidorCalculadoraUDP {

    public static void main(String[] args) {
        final int PORTA = 9898;

        try (DatagramSocket socket = new DatagramSocket(PORTA)) {
            System.out.println("Servidor UDP iniciado na porta " + PORTA);
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket pacoteRecebido = new DatagramPacket(buffer, buffer.length);
                // Aguarda a requisição
                socket.receive(pacoteRecebido);

                // Converte bytes para String
                String linha = new String(pacoteRecebido.getData(), 0, pacoteRecebido.getLength());

                System.out.println("Recebido: " + linha);

                // Reconstrói a requisição
                Requisicao requisicao = Requisicao.fromLinha(linha);

                double num1 = requisicao.getNum1();
                double num2 = requisicao.getNum2();

                // Calcula as quatro operações
                double soma = num1 + num2;
                double sub = num1 - num2;
                double mul = num1 * num2;

                String dados = "SOMA = " + soma +
                              "\nSUB = " + sub +
                              "\nMUL = " + mul;

                if (num2 != 0) {
                    double div = num1 / num2;
                    dados += "\nDIV = " + div;
                } else {
                    dados += "\nDIV = erro: divisão por zero";
                }

                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                Resposta resposta = new Resposta("OK",timestamp,dados);

                String linhaResposta = resposta.paraLinha();

                byte[] dadosResposta = linhaResposta.getBytes();

                InetAddress enderecoCliente = pacoteRecebido.getAddress();

                int portaCliente = pacoteRecebido.getPort();

                DatagramPacket pacoteResposta = new DatagramPacket(dadosResposta, dadosResposta.length,
                                                          enderecoCliente, portaCliente);

                socket.send(pacoteResposta);

                System.out.println("Resposta enviada:");
                System.out.println(linhaResposta);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}