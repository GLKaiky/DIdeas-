package mavem.ti2cc.service;

import java.io.*;

public class StartAI {
    public void init() {
        try {
            String separator = File.separator;

            // Caminho relativo ao diretório base do projeto Java
            String baseDir = "src" + separator + "main" + separator + "python" + separator + "Sistema_Inteligente";

            // Caminho relativo ao arquivo main.py dentro do diretório do projeto
            String pythonScriptPath = baseDir + separator + "main.py";

            String os = System.getProperty("os.name").toLowerCase();
            String[] command;

            if(os.contains("win")){
                command = new String[]{"python", pythonScriptPath};
            }else{
                command = new String[]{"python3", pythonScriptPath};
            }
            // Comando para executar o script Python

            // Inicia o processo
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true); // Redireciona a saída de erro para a saída padrão
            Process process = processBuilder.start();

            // Lê a saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Aguarda o término do processo
            int exitCode = process.waitFor();
            System.out.println("Script Python finalizado com código de saída: " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StartAI startAI = new StartAI();
        startAI.init();
    }
}
