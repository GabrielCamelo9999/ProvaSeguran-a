package Tentativa;

import java.util.List;
import java.util.Scanner;

import Seguro.SegurancaUtils;
import dao.ExameDAO;
import dao.SenhaDAO;
import dao.UsuarioDAO;
import modelo.Exame;
import modelo.Senha;
import modelo.Usuario;

public class TesteOperacoesBanco {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // Operações para popular o banco de dados
            popularBanco();

            // Operações para verificar dados salvos no banco e realizar decriptografia
            verificarBanco();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void popularBanco() {
        try {
            String login = "Bob";
            String senhaUsuario = "OxWTJHmp4ux";

            UsuarioDAO daoUsuario = new UsuarioDAO();
            daoUsuario.adiciona(login, senhaUsuario);
            System.out.println("Gravação do usuário e senha feita no banco de dados!");

            String senhaCriptografia = "k4BVd1nPVVv";
            SenhaDAO daoSenha = new SenhaDAO();
            daoSenha.adiciona(senhaCriptografia, senhaUsuario);
            System.out.println("Gravação da senha para criptografar feita no banco de dados!");

            ExameDAO daoExame = new ExameDAO();
            daoExame.adiciona("139 mEq/L", 1, 1, senhaCriptografia);
            daoExame.adiciona("144 mEq/L", 2, 1, senhaCriptografia);
            System.out.println("Exame de Sódio feita no banco de dados!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void verificarBanco() {
        try {
            int id = 1;
            String senhaUsuario = "OxWTJHmp4ux";

            UsuarioDAO daoUsuario = new UsuarioDAO();
            Usuario usuario = daoUsuario.getusuariobyId(id);
            System.out.println("Hash da senha do usuário (banco de dados): " + usuario.getSenha());

            SenhaDAO daoSenha = new SenhaDAO();
            Senha senha = daoSenha.getSenhabyId(id);
            System.out.println("Chave criptografada da tabela senha (banco de dados): " + senha.getChaveSecreta());
            String senhaCriptografia = SegurancaUtils.decriptografa(
                    senha.getChaveSecreta(),
                    SegurancaUtils.criarChaveSecreta(senhaUsuario));
            System.out.println("Chave decriptografada: " + senhaCriptografia);

            ExameDAO daoExame = new ExameDAO();
            List<Exame> exames = daoExame.getLista();

            for (Exame exame : exames) {
                System.out.println("----------------------------------------------");
                System.out.println("Resultado do exame de Sódio do paciente: " + exame.getPaciente() +
                        " solicitado pelo médico: " + exame.getMedico());
                System.out.println("Exame criptografado (banco de dados): " + exame.getResultado());
                String nomeDoExame = SegurancaUtils.decriptografa(
                        exame.getResultado(),
                        SegurancaUtils.criarChaveSecreta(senhaCriptografia));
                System.out.println("Exame decriptografado: " + nomeDoExame);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
