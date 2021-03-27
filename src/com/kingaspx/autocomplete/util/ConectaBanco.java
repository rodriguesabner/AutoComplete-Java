package com.kingaspx.autocomplete.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConectaBanco {

    public Statement stm;// responsável por preparar e realizar pesquisas no banco de dados

    public ResultSet rs;//  responsável por armazenar o resultado de uma pesquisa passada para o Statement
    private final String driver = "org.postgresql.Driver";// responsável por identificar o serviço de banco de dados
    private final String caminho = "jdbc:postgresql://localhost:5432/imperium";// responsável por setar o local do banco de dados
    private final String usuario = "user";
    private final String senha = "password";

    public Connection conn; // responsável por realizar a conexão com o banco de dados

    public void conexao() { //Metodo Responsável por realizar a conexão com o banco
        try {// tentativa inicial
            System.setProperty("jdbc.Driver", driver); // seta a propriedade do driver de conexão
            conn = DriverManager.getConnection(caminho, usuario, senha); // realiza a conexão com o banco de dados
            //JOptionPane.showMessageDialog (null, "Conectado com Sucesso ao Banco de Dados!"); // imprime uma caixa de mensagens
            //System.out.println("Conectado"); // imprime uma caixa de mensagens
        } catch (SQLException ex) { // excessão
            JOptionPane.showMessageDialog(null, "Erro de Conexão!\n Erro:" + ex.getMessage());
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void executaSQL(String sql) {
        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog (null, "Erro de ExecutaSQL!\n Erro:" + ex.getMessage());
        }

    }

    public void desconecta() {//metodo para fechar a conexão com o banco de dados
        try {
            conn.close(); //fecha a conexão
            //JOptionPane.showMessageDialog (null, "Desconectado com Sucesso!"); // imprime uma caixa de mensagens
            //System.out.println("Desconectado");
            //System.out.println();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão!\n Erro:" + ex.getMessage());
        }

    }

}
