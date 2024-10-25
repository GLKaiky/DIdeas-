package mavem.ti2cc.service;

import mavem.ti2cc.DAO.*;
import mavem.ti2cc.Classes.*;

import spark.Route;
import spark.Spark;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.BlobServiceClientBuilder;
import java.io.*;
import java.util.Scanner;

import java.util.Base64;
import com.google.gson.Gson;
import spark.Request;
import java.util.Random;
import spark.Response;

public class UsuarioService {
    UsuarioDAO UsuarioDAO;
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_DETAIL = 2;
    private final int FORM_UPDATE = 3;
    private final int FORM_ORDERBY_ID = 1;
    private final int FORM_ORDERBY_DESCRICAO = 2;
    private final int FORM_ORDERBY_NAME = 2;

    public UsuarioService() {
        this.UsuarioDAO = new UsuarioDAO();
        makeForm();
    }

    public void makeForm() {
        makeForm(FORM_INSERT, new Usuario(), FORM_ORDERBY_DESCRICAO);
    }

    public void makeForm(int orderBy) {
        makeForm(FORM_INSERT, new Usuario(), orderBy);
    }

    public void makeForm(int tipo, Usuario usuario, int orderBy) {
        String nomeArquivo = "perfil.html";
        form = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String umUsuario = "";
        if (tipo != FORM_INSERT) {
            umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/usuario/list/1\">Novo Usuário</a></b></font></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t</table>";
            umUsuario += "\t<br>";
        }

        if (tipo == FORM_INSERT || tipo == FORM_UPDATE) {
            String action = "/usuario/";
            String name, descricao, buttonLabel;
            if (tipo == FORM_INSERT) {
                action += "insert";
                name = "Inserir Usuário";
                descricao = "Name, Nickname, Email, Password, Data de Nascimento";
                buttonLabel = "Inserir";
            } else {
                action += "update/" + usuario.getId();
                name = "Atualizar Usuário (ID " + usuario.getId() + ")";
                descricao = usuario.getName();
                buttonLabel = "Atualizar";
            }
            umUsuario += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
            umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name
                    + "</b></font></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""
                    + descricao + "\"></td>";
            umUsuario += "\t\t\t<td>Nickname: <input class=\"input--register\" type=\"text\" name=\"nickname\" value=\""
                    + usuario.getNickName() + "\"></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td>&nbsp;Email: <input class=\"input--register\" type=\"text\" name=\"email\" value=\""
                    + usuario.getEmail() + "\"></td>";
            umUsuario += "\t\t\t<td>Password: <input class=\"input--register\" type=\"text\" name=\"password\" value=\""
                    + usuario.getPassword() + "\"></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\"" + buttonLabel
                    + "\" class=\"input--main__style input--button\"></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t</table>";
            umUsuario += "\t</form>";
        } else if (tipo == FORM_DETAIL) {
            umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Usuário (ID "
                    + usuario.getId() + ")</b></font></td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td>&nbsp;Nome: " + usuario.getName() + "</td>";
            umUsuario += "\t\t\t<td>Nickname: " + usuario.getNickName() + "</td>";
            umUsuario += "\t\t\t<td>Email: " + usuario.getEmail() + "</td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td>&nbsp;</td>";
            umUsuario += "\t\t</tr>";
            umUsuario += "\t</table>";
        } else {
            System.out.println("ERRO! Tipo não identificado " + tipo);
        }
        form = form.replaceFirst("<UM-USUARIO>", umUsuario);

        String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
        list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Usuários</b></font></td></tr>\n"
                +
                "\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
                "\n<tr>\n" +
                "\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
                "\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_NAME + "\"><b>Nome</b></a></td>\n" +
                "\t<td><b>Nickname</b></td>\n" +
                "\t<td><b>Email</b></td>\n" +
                "\t<td><b>Data de Nascimento</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
                "\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
                "</tr>\n";
    }

    public Object insert(Request request, Response response) throws Exception{
        Gson gson = new Gson();
        Usuario registro = gson.fromJson(request.body(), Usuario.class);

        System.out.println(registro.getName());
        Random random = new Random();
        int cod = random.nextInt(1000000);

        registro.setId(cod);
        response.cookie("/", "user_id", String.valueOf(cod), 24 * 60 * 60, false, true);
        String resp = "";
        if (UsuarioDAO.insert(registro) == true) {
            resp = "Usuario Registrado";
            response.status(201);
        } else {
            resp = "Não inserido";
            response.status(404);
        }
        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");

    }

    public Object get(Request request, Response response) throws Exception {
        String Username = request.params(":username");
        String Senha = request.params(":senha");
        Usuario usuario = UsuarioDAO.get(Username, Senha);

        response.cookie("/", "user_id", String.valueOf(usuario.getId()), 24 * 60 * 60, false, true);
        Gson gson = new Gson();

        String json = "";

        try {
            json = gson.toJson(usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        response.type("application/json");
        return json;
    }

    public Object set_image(Request request, Response response) {
    	boolean status = false;
        CookieRequest cookie = new CookieRequest();
        int id = cookie.getUserId(request);
        try {
            String connectStr = "";
            String containerName = "blob";
            String blobName = "perfil" + id + ".png";

            // Obtendo o conteúdo do arquivo do corpo da solicitação
            String imagem = request.body();
            String base64Data = imagem.substring(imagem.indexOf(",") + 1);
            byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
             
            ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);
            

            // Obtendo o tamanho do arquivo, se disponível

            BlobServiceClientBuilder clientBuilder = new BlobServiceClientBuilder().connectionString(connectStr);

            BlobClient blobClient = new BlobClientBuilder().connectionString(connectStr)
                    .containerName(containerName)
                    .blobName(blobName)
                    .buildClient();
            // Fazendo upload do conteúdo do arquivo
            blobClient.upload(inputStream, inputStream.available(), true);
            
            response.status(200);
            response.body("Imagem enviada com sucesso");
            status = UsuarioDAO.insertImage(id);
        } catch (Exception e) {
            response.status(500);
            response.body("Erro ao enviar");
        }
        
        
        return status;
    }



    public Object get_image(Request request, Response response) {
        CookieRequest cookies = new CookieRequest();
        String image;
        int id = cookies.getUserId(request);
       try {
           image = UsuarioDAO.getImageById(id);
           response.status(200);
       }catch(Exception e) {
    	   response.status(500);
    	   System.out.println(e.getMessage());
    	   return "Erro ao recuperar a imagem";
       }
       return image;
    }

    public Object delete_user(Request request, Response response) {
    	CookieRequest cookies = new CookieRequest();
    	int id = cookies.getUserId(request);
    	boolean teste = false;
    	try {
    		teste = UsuarioDAO.delete(id);
    		response.status(200);
    		return teste;
    	}catch(Exception e) {
    		response.status(500);
    		System.out.println("Impossivel deletar usuario" + e.getMessage());
    		return teste;
    	}
    }
    
    public Object modifyUser(Request request, Response response) {
    	CookieRequest cookies = new CookieRequest();
        int tipo = Integer.parseInt(request.params(":idrequisicao"));
        
    	int id = cookies.getUserId(request);
    	boolean teste = false;
    	try {
    		teste = UsuarioDAO.modify(request.body(), tipo, id);
    		response.status(200);
    		return teste;
    	}catch(Exception e) {
    		response.status(500);
    		System.out.println("Impossivel editar" + e.getMessage());
    		return teste;
    	}
    }
}
