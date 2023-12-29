package leitura_arquivo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Programa {
    public static double valorTotalEstoque;

    public static void main(String[] args) {
        String pathReader = "C:\\Users\\IRM\\Desktop\\vendas\\lista.txt";
        File arqReader = new File(pathReader);

        String pathWriter = "C:\\Users\\IRM\\Desktop\\vendas\\out\\summary.csv";
        File arqWriter = new File(pathWriter);

        List<Produto> lista = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(arqReader));
            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(";");
                lista.add(new Produto(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
                line = br.readLine();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(arqWriter));
            String cabecalho = "Nome" + ";" + "Valor" + ";" + "Quantidade";
            bw.write(cabecalho);
            bw.newLine();

            for (int i = 0; i < lista.size(); i++) {
                String linha = lista.get(i).getNome() + ";" + lista.get(i).getValor() + ";" + lista.get(i).getQuantidade();
                bw.write(linha);
                bw.newLine();
            }

            bw.close();
            br.close();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }
    static class Produto {
        private String nome;
        private int valor;
        private int quantidade;

        public Produto(String nome, int valor, int quantidade) {
            this.nome = nome;
            this.valor = valor;
            this.quantidade = quantidade;
        }

        public String getNome() {
            return nome;
        }

        public int getValor() {
            return valor;
        }

        public int getQuantidade() {
            return quantidade;
        }
    }

}