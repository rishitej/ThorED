import openai
from gpt import GPT
from gpt import Example
from matplotlib import pyplot as plt
import seaborn as sns
import os
import pandas as pd
import io
from base64 import encodebytes
from PIL import Image
from flask import Flask,request, jsonify, render_template

openai.api_key= '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'

gpt = GPT(engine="davinci", temperature=0.5, max_tokens=100)

df=pd.read_csv('iris.csv')

gpt.add_example(Example('Plot Scatter Plot between Sepal Length & Sepal Width',
                        "plt.scatter(df['sepal_length'], df['sepal_width'])"))
gpt.add_example(Example('Plot Bar Plot of Species',
                        "sns.countplot('species',data=df)"))
gpt.add_example(Example('Plot a Joint Plot between Sepal Length & Petal Length',
                        "sns.jointplot(x='sepal_length',y='petal_length',data=df)"))
gpt.add_example(Example('Show me the histogram of Petal Length',
                        "plt.hist(df['petal_length'])"))
gpt.add_example(Example('Show me the box plot of petal_width',"plt.boxplot(x='petal_width',data=df)"))


app = Flask(__name__)

def get_response_image(image_path):
    pil_img = Image.open(image_path, mode='r') # reads the PIL image
    byte_arr = io.BytesIO()
    pil_img.save(byte_arr, format='PNG') # convert the PIL image to byte array
    encoded_img = encodebytes(byte_arr.getvalue()).decode('ascii') # encode as base64
    return encoded_img

@app.route('/', methods=['POST','GET'])
def find_answer():
    prompt = request.args.get('prompt')
#     prompt = request.files['prompt'].read().decode('utf-8')
    print(prompt)
    response = gpt.get_top_reply(prompt)
    ans=response.split('output:')[1].strip()
    try:
        print('a1')
        eval(ans)
        print('a2')
        plt.savefig('plot.png')
        print('a3')
        resp = jsonify({'code':ans,'image': 'http://137.116.32.204:8888/tree/hackathon/Visualization/plot.png' })
        print('a4')
    except:
        print('here1')
        resp = jsonify({'code':ans,'image':None})
        print(resp.json)
    resp.status_code = 200
    return resp

if __name__=='__main__':
    app.run(host='0.0.0.0',port=7001,debug=True)
