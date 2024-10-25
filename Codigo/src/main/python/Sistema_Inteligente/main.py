# Importar a função do módulo magias
from IA import obter_magias_por_nivel_e_classe
from flask import Flask, request, jsonify
from flask_cors import CORS
import requests
import joblib
import base64
import os

app = Flask(__name__)
CORS(app)

# Obtenha o diretório atual do arquivo main.py
current_directory = os.path.dirname(os.path.abspath(__file__))

# Caminho para a pasta Models (dentro do diretório atual)
model_path = os.path.join(current_directory, "Models")

# Carregue as colunas de características usando o caminho dinâmico
feature_columns = joblib.load(os.path.join(model_path, "feature_columns.pkl"))


@app.route('/recomendar', methods = ['POST'])
def recomendar():
    data = request.get_json()
    classe = data['nome_classe']
    nivel = data.get('nivel') 
    recomendacoes = obter_magias_por_nivel_e_classe(nivel, classe, feature_columns, model_path)

    return jsonify({'recomendacoes': recomendacoes})

@app.route('/analisar', methods = ['POST'])
def analisar():

    data = request.get_json()
    image_data = data['image']
    image_bytes = base64.b64decode(image_data)

    # URL do endpoint de análise de imagem do Azure Computer Vision
    url = "https://iadideas.cognitiveservices.azure.com/vision/v3.0/analyze"

    # Chave de assinatura do Azure Computer Vision
    subscription_key = ""

    # Parâmetros da solicitação
    params = {
        'visualFeatures': 'Categories,Description,Color',
        'language': 'pt'
    }

    # Headers da solicitação com a chave de assinatura
    headers = {
        'Content-Type': 'application/octet-stream',
        'Ocp-Apim-Subscription-Key': subscription_key,
    }

    # Enviar a solicitação POST com a imagem para o serviço do Azure Computer Vision
    response = requests.post(url, headers=headers, params=params, data=image_bytes)
    print("sucesso")

    return response.json()

if __name__ == '__main__':
    app.run(debug=True, port=9090)

