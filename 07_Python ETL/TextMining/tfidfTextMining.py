# coding:utf-8  
###  ====================== 後端測試區 - TF-IDF =========================
import json
import jieba
import re
import os  
import sys 
import time
from datetime import datetime
from sklearn import feature_extraction  
from sklearn.feature_extraction.text import TfidfTransformer  
from sklearn.feature_extraction.text import CountVectorizer  


allData = []
### 載入要parse的資料
for dataName in range(1,4):    
    with open('../json/{}.json'.format(dataName), 'r',encoding='utf-8') as f:
        data = json.load(f)
        print('Deal with data{}.json'.format(dataName))
        allData += data
### 載入繁體擴充字典
jieba.set_dictionary('dict.txt-v2.big')    

### 載入停用字
print('Input Stop Words')
stopwordset = set()
with open('stopwords.txt','r',encoding='utf-8') as sw:
    for line in sw:
        stopwordset.add(line.strip('\n'))

print('Start Jieba Cut')
totalContent = []
### 從data中挑1000篇文章切詞
for i in range(0,len(allData)):
    article = ''
    content = allData[i]['Content']
    
    ### 斷詞, 產生一個結巴物件
    words = jieba.cut(content, cut_all=False)
    for word in words:   
        m = re.match(r'^[\u4E00-\u9FFFa-zA-Z]+$',word )
        if m is not None:
            if word not in stopwordset:
                article+=word
                article+=' '
    totalContent.append(article)

## 用來裝全部文章的權重值
textWeightList = []

## 用來裝前5權重值
tagList = []
print('Start Tf-Idf')

if __name__ == "__main__":  
    
    ## 該物件會將文本中的詞語轉換為詞頻矩陣，矩陣元素a [i] [j]表示j詞在i類文本下的詞頻 
    vectorizer=CountVectorizer()
    
    ## 該物件會話統計每個詞語的tf-idf權值 
    transformer=TfidfTransformer()
   
    ## 第一個fit_transform是計算tf-idf，第二個fit_transform是將文本轉成詞頻矩陣
    tfidf=transformer.fit_transform(vectorizer.fit_transform(totalContent))  
    
    ## 獲取詞袋模型中的所有詞語 
    word=vectorizer.get_feature_names()
    
    ## 將tf-idf矩陣抽取出來，元素a [i] [j]表示j詞在i類文本中的tf-idf權重 
    weight=tfidf.toarray()
    
    ## 打印每類文本的tf-idf詞語權重，第一個遍遍所有文本，第二個為便利某一類文本下的詞語權重
    for i in range(len(weight)):
        ## 用來裝單篇權重值
        textMining = {}
        ## print (u"-------这里输出第",i,u"类文本的词语tf-idf权重------"  )
        for j in range(len(word)):  
            ## print( word[j],weight[i][j] ) 
            textMining[word[j]] = weight[i][j]
        textWeightList.append(textMining)

print('Sorted the Weight of KeyWords')
### 取出文章，排序權重，取出前5以字典型式存取
for oneArticle in textWeightList:
    dict= sorted(oneArticle.items(), key=lambda d:d[1], reverse = True)

    #tag = {}
    #tag[dict[0][0]]=dict[0][1]
    #tag[dict[1][0]]=dict[1][1]
    #tag[dict[2][0]]=dict[2][1]
    #tag[dict[3][0]]=dict[3][1]
    #tag[dict[4][0]]=dict[4][1]
    #tagList.append(tag)
    
    ### 決定要放幾個tag
    tag = []
    tag.append(dict[0][0])
    tag.append(dict[1][0])
    tag.append(dict[2][0])
    tag.append(dict[3][0])
    tag.append(dict[4][0])
    tag.append(dict[5][0])
    tag.append(dict[6][0])
    tag.append(dict[7][0])
    tag.append(dict[8][0])
    tag.append(dict[9][0])
    tagList.append(tag)
        
### 載入要parse的資料
for i in range(len(tagList)):
    allData[i]['Tag'] =tagList[i]

pat = '[a-zA-Z{" "}{\.}{\,}{\-}a-zA-Z]+'

## 讀取檔案
listName = []

with open('nameList.txt','r',encoding='utf-8') as nl:
    for line in nl:
        name = line.split('\n')[0]
        listName.append(name.lower())

for i in range(0,len(tagList)):
    tagByName = []
    s = allData[i]['Content']
    v=re.findall(pat, s)
    for j in v:
        if j.lower() in listName:
            tagByName.append(j.lower())
    allData[i]['TagByName'] = tagByName
    
	
###  ====================== 後端測試區 - 建立陣列裝要上傳的資料  =========================

### 資料寫進要上傳的陣列

updateList = []


for j in range(len(tagList)):
    updateList.append(data[j])

print('Start Change the data format')    
### 時間格式改為 YYYYMMDD
for i in range(0,len(updateList)): 
    if allData[i]['Link'].startswith('http://www.nownews'):
        newTime = updateList[i]['Date'].split('T')[0].split('-')[0]+updateList[i]['Date'].split('T')[0].split('-')[1]+updateList[i]['Date'].split('T')[0].split('-')[2]
    if allData[i]['Link'].startswith('https://www.sportsv'):
        newTime = updateList[i]['Date'].split('/')[0]+updateList[i]['Date'].split('/')[1]+updateList[i]['Date'].split('/')[2]
    if allData[i]['Link'].startswith('http://sports.ettoday'):
        newTime = updateList[i]['Date'].split(' ')[0].split('-')[0]+updateList[i]['Date'].split(' ')[0].split('-')[1]+updateList[i]['Date'].split(' ')[0].split('-')[2]
    allData[i]['YearDate'] = newTime
	
###  ====================== 後端測試區 - 建立連線上傳資料  =========================

### 將上傳陣列的東西寫進資料庫
### 全部是一次傳
### 2017的版本是要一筆一筆傳

import pymongo
print('Start Insert Data to MongoDB')

client = pymongo.MongoClient("54.249.57.118", 27017)
db = client['spadeAce']
collection = db.allNews
for i in updateList:
    if collection.find_one({'Link':i['Link']}) == None:
        collection.insert(i)
        print(i['Title'])
        print('data assess')
    else:
        print('data duplicated')

client.close()