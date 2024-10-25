package mavem.ti2cc.service;

import mavem.ti2cc.DAO.*;
import mavem.ti2cc.Classes.*;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import okhttp3.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import spark.Request;
import spark.Response;

public class PersonagemService {
    PersonagemDAO PersonagemDAO = new PersonagemDAO();
    MagiasDAO MagiasDAO;
    CookieRequest cookies;
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_DETAIL = 2;
    private final int FORM_UPDATE = 3;
    private final int FORM_ORDERBY_ID = 1;
    private final int FORM_ORDERBY_DESCRICAO = 2;
    private final int FORM_ORDERBY_NAME = 2;

    public PersonagemService() {
        this.cookies = new CookieRequest();
        this.PersonagemDAO = new PersonagemDAO();
        this.MagiasDAO = new MagiasDAO();
        makeForm();
    }

    public void makeForm() {
        makeForm(FORM_INSERT, new Personagem(), FORM_ORDERBY_DESCRICAO);
    }

    public void makeForm(int orderBy) {
        makeForm(FORM_INSERT, new Personagem(), orderBy);
    }

    public void makeForm(int tipo, Personagem personagem, int orderBy) {
        String nomeArquivo = "index.html";
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

        String umPersonagem = "";
        if (tipo != FORM_INSERT) {
            // Botão ou link para adicionar novo personagem
            umPersonagem += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umPersonagem += "\t\t<tr>";
            umPersonagem += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/personagem/list/1\">Novo Personagem</a></b></font></td>";
            umPersonagem += "\t\t</tr>";
            umPersonagem += "\t</table>";
            umPersonagem += "\t<br>";
        }

        // Formulário para inserção ou atualização do personagem
        if (tipo == FORM_INSERT || tipo == FORM_UPDATE) {
            String action = "/personagem/";
            String name, descricao, buttonLabel;
            if (tipo == FORM_INSERT) {
                action += "insert";
                name = "Inserir Personagem";
                descricao = "Nome, Religião, Descrição, História, Tendência, Classe, Raça, Atributo";
                buttonLabel = "Inserir";
            } else {
                action += "update/" + personagem.getNome();
                name = "Atualizar Personagem (ID " + personagem.getNome() + ")";
                descricao = personagem.getNome();
                buttonLabel = "Atualizar";
            }
            umPersonagem += "\t<form class=\"form--register\" action=\"" + action
                    + "\" method=\"post\" id=\"form-add\">";
            umPersonagem += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umPersonagem += "\t\t<tr>";
            umPersonagem += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name
                    + "</b></font></td>";
            umPersonagem += "\t\t</tr>";
            umPersonagem += "\t\t<tr>";
            umPersonagem += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
            umPersonagem += "\t\t</tr>";
            umPersonagem += "\t\t<tr>";
            // Campos do formulário (nome, religião, descrição, etc.)
            umPersonagem += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""
                    + descricao + "\"></td>";
            // Adicione os campos restantes conforme necessário
            umPersonagem += "\t\t</tr>";
            umPersonagem += "\t\t<tr>";
            umPersonagem += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\"" + buttonLabel
                    + "\" class=\"input--main__style input--button\"></td>";
            umPersonagem += "\t\t</tr>";
            umPersonagem += "\t</table>";
            umPersonagem += "\t</form>";
        }
        // Detalhes do personagem
        else if (tipo == FORM_DETAIL) {
            umPersonagem += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
            umPersonagem += "\t\t<tr>";
            umPersonagem += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Personagem (ID "
                    + personagem.getNome() + ")</b></font></td>";
            umPersonagem += "\t\t</tr>";
            umPersonagem += "\t\t<tr>";
            // Detalhes do personagem (nome, religião, descrição, etc.)
            umPersonagem += "\t\t\t<td>&nbsp;Nome: " + personagem.getNome() + "</td>";
            // Adicione os detalhes restantes conforme necessário
            umPersonagem += "\t\t</tr>";
            umPersonagem += "\t\t<tr>";
            umPersonagem += "\t\t\t<td>&nbsp;</td>";
            umPersonagem += "\t\t</tr>";
            umPersonagem += "\t</table>";
        } else {
            System.out.println("ERRO! Tipo não identificado " + tipo);
        }

        // Substitui a marcação no arquivo HTML pelo formulário criado
        form = form.replaceFirst("<UM-PERSONAGEM>", umPersonagem);

        // Lógica para criar a lista de personagens
        String listaPersonagens = "<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">";
        // Implemente aqui a lógica para criar a lista de personagens
        List<Personagem> personagens = PersonagemDAO.get(orderBy);
        for (Personagem p : personagens) {
            listaPersonagens += "<tr>";
            listaPersonagens += "<td>" + p.getNome() + "</td>";
            // Adicione mais detalhes do personagem conforme necessário
            listaPersonagens += "<td><a href=\"/personagem/detail/" + p.getNome() + "\">Detalhar</a></td>";
            listaPersonagens += "<td><a href=\"/personagem/update/" + p.getNome() + "\">Atualizar</a></td>";
            listaPersonagens += "<td><a href=\"/personagem/delete/" + p.getNome() + "\">Excluir</a></td>";
            listaPersonagens += "</tr>";
        }
        listaPersonagens += "</table>";

        // Substitui a marcação no arquivo HTML pela lista de personagens
        form = form.replaceFirst("<LISTA-PERSONAGENS>", listaPersonagens);
    }

    public Object insertPersonagem(Request request, Response response) {
        Gson gson = new Gson();
        Personagem registro = gson.fromJson(request.body(), Personagem.class);

        String name_classe = request.params("name_classe");
        String name_raca = request.params("name_raca");
        int carisma = Integer.parseInt(request.params("carisma"));
        int inteligencia = Integer.parseInt(request.params("inteligencia"));
        int constituicao = Integer.parseInt(request.params("constituicao"));
        int destreza = Integer.parseInt(request.params("destreza"));
        int forca = Integer.parseInt(request.params("forca"));
        int sabedoria = Integer.parseInt(request.params("sabedoria"));

        registro.setClasse(name_classe);
        registro.setRaca(name_raca);
        registro.setAtributos(carisma, inteligencia, constituicao, destreza, forca, sabedoria);

        int userId = cookies.getUserId(request);
        String resp = "";
        if (userId != -1) {
            if (PersonagemDAO.insert(registro, userId) == true) {
                resp = ("Personagem inserido com sucesso");
                response.status(202);
            } else {
                resp = "Não inserido";
                response.status(404);
            }

        }
        makeForm();
        return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
    }
    
    public Object getPersonagemByName(Request request, Response response) {
    	String nome = request.params("nome");
    	Personagem personagem = new Personagem();
    	Gson gson = new Gson();
    	try {
    		personagem = PersonagemDAO.getPersonbyName(nome);
    		response.status(200);
    		System.out.println("Sucesso");
    	}catch (Exception e) {
    		response.status(500);
    		System.out.println("Erro ao selecionar");
    	}
    	String json = gson.toJson(personagem);
    	System.out.println(json);
    	return json;
    }

    public Object getPersonagem(Request request, Response response) {

        List<Personagem> personagens = new ArrayList<Personagem>();
        List<String> jsonPersonagens = new ArrayList<>();
        Gson gson = new Gson();

        try {
            CookieRequest cookie = new CookieRequest();
            int id = cookie.getUserId(request);

            personagens = PersonagemDAO.get(id);

            response.status(200);
            System.out.println("Sucesso");
        } catch (Exception e) {
            response.status(500);
            System.out.println("erro na responsta");
        }

        // Converter cada personagem em JSON e adicionar à lista
        for (Personagem personagem : personagens) {
            String json = gson.toJson(personagem);
            jsonPersonagens.add(json);
        }
        System.out.println(jsonPersonagens);
        return jsonPersonagens;
    }
    public Object getPersonagemWithUserID(Request request, Response response) {

        List<Personagem> personagens = new ArrayList<Personagem>();
        List<String> jsonPersonagens = new ArrayList<>();
        int id = Integer.parseInt(request.params(":id"));
        Gson gson = new Gson();

        try {
            personagens = PersonagemDAO.get(id);

            response.status(200);
            System.out.println("Sucesso");
        } catch (Exception e) {
            response.status(500);
            System.out.println("erro na responsta");
        }

        // Converter cada personagem em JSON e adicionar à lista
        for (Personagem personagem : personagens) {
            String json = gson.toJson(personagem);
            jsonPersonagens.add(json);
        }
        System.out.println(jsonPersonagens);
        return jsonPersonagens;
    }

    public Object set_image(Request request, Response response) {
        boolean status = false;
        CookieRequest cookie = new CookieRequest();
        Random random = new Random();
        int cod = random.nextInt(1000000);
        int id = cookie.getUserId(request);
        String nome = request.params(":nomePersonagem");
        try {
            String connectStr = "";
            String containerName = "fotos";
            String blobName = "perfil" + cod + ".png";

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
            status = PersonagemDAO.insertImage(cod, nome);
        } catch (Exception e) {
            response.status(500);
            response.body("Erro ao enviar");
        }

        return status;
    }

    public Object getPersonimage(Request request, Response response) {
        CookieRequest cookies = new CookieRequest();
        String image;
        String nome = request.params(":nomePersonagem");
        int id = cookies.getUserId(request);
        try {
            image = PersonagemDAO.getImageById(nome);
            response.status(200);
        } catch (Exception e) {
            response.status(500);
            System.out.println(e.getMessage());
            return "Erro ao recuperar a imagem";
        }
        return image;
    }

    public Object deletePerson(Request request, Response response) {
        boolean status = false;
        try {
            status = PersonagemDAO.delete(request.body());
            response.status(200);
        } catch (Exception e) {
            response.status(500);
            System.out.println(e.getMessage());
        }
        return status;
    }

    public Object getRecommendMage(Request request, Response response) {   
    	Personagem personagem = null;
    	try {
            personagem = PersonagemDAO.getPersonbyName(request.body());
            response.status(200);
    	}catch(Exception e) {
    		response.status(500);
    		System.out.println(e.getMessage());
    	}
        Gson gson = new Gson();
        String jsonPersonagem = gson.toJson(personagem);
     
        return jsonPersonagem;
    }
    
    public Object setMagia(Request request, Response response) {
    	boolean status = false;
    	String nome = request.params(":nome");
    	String descricao = request.params(":descricao");
    	String nome_Personagem = request.params(":nome_personagem");
    	int nivel = Integer.parseInt(request.params("nivel"));
    	
    	System.out.println(descricao);
    	try {
    		status = MagiasDAO.setMagia(nome, descricao, nome_Personagem, nivel);
    		response.status(200);
    	}catch(Exception e) {
    		response.status(500);
    		System.out.println(e.getMessage());
    		return status;
    	}
    	return status;
    }
    
    public Object getAllMages(Request request, Response response) {
    	ArrayList<Magia> arr = new ArrayList<Magia>();
    	ArrayList<String> jsonMagias = new ArrayList<String>();
    	
    	String nome_Personagem = request.params(":nome");
    	
    	try {
    		arr = MagiasDAO.getAllMagias(nome_Personagem);
    		response.status(200);
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		response.status(500);
    	}
    	
    	Gson gson = new Gson();
    	
        for (Magia magia : arr) {
            String json = gson.toJson(magia);
            jsonMagias.add(json);
        }
    	
    	return jsonMagias;
    }
    
}


