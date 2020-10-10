import os
import time
import random
from eval import *
from flask import Flask, jsonify, request, abort
from miscc.config import cfg


app = Flask(__name__)


@app.route('/generateImages', methods=['POST'])
def create_bird():
    if not request.json or not 'caption' in request.json:
        abort(400)
    caption = request.json['caption']

    t0 = time.time()
    urls = generate(caption, wordtoix, ixtoword, text_encoder, netG, blob_service)
    t1 = time.time()

    response = {
        'small': urls[0],
        'medium': urls[1],
        'large': urls[2],
        'map1': urls[3],
        'map2': urls[4],
        'caption': caption,
        'elapsed': t1 - t0
    }
    return jsonify({'bird': response}), 201

@app.route('/generateMultipleImages', methods=['POST'])
def create_birds():
    if not request.json or not 'caption' in request.json:
        abort(400)

    caption = request.json['caption']

    t0 = time.time()
    urls = generate(caption, wordtoix, ixtoword, text_encoder, netG, blob_service, copies=6)
    t1 = time.time()

    response = {
        'img1' : { 'small': urls[0], 'medium': urls[1], 'large': urls[2] },
        'img2' : { 'small': urls[3], 'medium': urls[4], 'large': urls[5] },
        'img3' : { 'small': urls[6], 'medium': urls[7], 'large': urls[8] },
        'img4' : { 'small': urls[9], 'medium': urls[10], 'large': urls[11] },
        'img5' : { 'small': urls[12], 'medium': urls[13], 'large': urls[14] },
        'img6' : { 'small': urls[15], 'medium': urls[16], 'large': urls[17] },
        'caption': caption,
        'elapsed': t1 - t0
    }
    return jsonify({'bird': response}), 201

@app.route('/', methods=['GET'])
def get_bird():
    return 'Version 1'

if __name__ == '__main__':
    t0 = time.time()
    
    
    
    cfg.CUDA = False#os.environ["GPU"].lower() == 'true'
    


    wordtoix, ixtoword = word_index()
    text_encoder, netG = models(len(wordtoix))
    blob_service = BlockBlobService(account_name='hacks2020', account_key=os.environ['KEY'])

    seed = 100
    random.seed(seed)
    np.random.seed(seed)
    torch.manual_seed(seed)
    if cfg.CUDA:
        torch.cuda.manual_seed_all(seed)


    t1 = time.time()
    app.run(host='0.0.0.0',port = 5000)
