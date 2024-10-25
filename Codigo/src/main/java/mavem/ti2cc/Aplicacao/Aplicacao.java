package mavem.ti2cc.Aplicacao;

import mavem.ti2cc.service.PersonagemService;
import	mavem.ti2cc.service.UsuarioService;
import mavem.ti2cc.service.StartAI;

import static spark.Spark.*;
public class Aplicacao {
	private static UsuarioService usuarioservice = new UsuarioService();
	private static PersonagemService personagemservice = new PersonagemService();
	private static StartAI start = new StartAI();
		
	public static void main(String[]args) {
		port(8080);
		Thread flaskThread = new Thread(() -> start.init());
		flaskThread.start();
		
		staticFiles.location("/public");

		//Usuarios	
		//Cadastro de Usuario
		post("/produto/insert", (request, response) -> usuarioservice.insert(request, response));
		
		//Get de usuario
		get("/produto/get/:username/:senha", (request, response) -> usuarioservice.get(request, response));

		//Inserir Imagens
		post("/produto/set_image", (request, response) -> usuarioservice.set_image(request, response));

		//Requisitar imagens
		get("/produto/get_image", (request, response) -> usuarioservice.get_image(request, response));

		//Deletar usuarios
		post("/produto/deleteUser", (request, response) -> usuarioservice.delete_user(request, response));

		//Editar informações
		post("/produto/modifyUser/:idrequisicao", (request, response) -> usuarioservice.modifyUser(request, response));

		//Personagem
		//Cadastro
		post("/produto/insertPersonagem/:name_classe/:name_raca/:carisma/:inteligencia/:constituicao/:destreza/:forca/:sabedoria", (request, response) -> personagemservice.insertPersonagem(request, response));	
	
		get("/produto/getPersonagem", (request, response) -> personagemservice.getPersonagem(request, response));

		get("/produto/getComId/:id", (request, response) -> personagemservice.getPersonagemWithUserID(request, response));
		
		//Inserir imagem de personagem
		post("/produto/setPersonImage/:nomePersonagem", (request, response) -> personagemservice.set_image(request, response));
		
		get("/produto/getPersonimage/:nomePersonagem", (request, response) -> personagemservice.getPersonimage(request, response));

		//Deletar personagem
		post("/produto/deletePerson", (request, response) -> personagemservice.deletePerson(request, response));
		
		//Get Personagem pelo nome
		get("/produto/getPersonbyName/:nome", (request, response) -> personagemservice.getPersonagemByName(request, response));
		
		//setMagias
		post("/produto/setMagias/:nome/:descricao/:nome_personagem/:nivel", (request, response) -> personagemservice.setMagia(request, response));
		
		//getAllMagias
		get("/produto/getAllMages/:nome", (request, response) -> personagemservice.getAllMages(request, response));
		
	}
}
	

