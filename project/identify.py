#百度api
import base64
import urllib
import requests
# coding=utf-8
from sys import argv
import io
import sys

API_KEY = "1cx3HWc83NwB1P715ECuAHNn"
SECRET_KEY = "DCEmVQoAOpBU5EWz0iKG8G3iXAGL6zXi"

def main(filepath):
        
    url = "https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice?access_token=" + get_access_token()
    
    # image 可以通过 get_file_content_as_base64("C:\fakepath\invoice.png",True) 方法获取
    with open(filepath, 'rb') as f1:
        base64_str = base64.b64encode(f1.read())  # base64类型
        #  b'JVBERi0xLjUNCiXi48
        src = base64_str.decode('utf-8')  # str
        # JVBERi0xLjUNCiXi48/
        #print(src)

    file_base64=src
    #print(file_base64)
    payload={'image':file_base64}
    #payload=payload.urlencode()
    #print(type(payload))
    headers = {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
    }
    
    response = requests.request("POST", url, headers=headers, data=payload)
    
    print(response.text)
    #print(type(response.text))

def get_file_content_as_base64(path, urlencoded=False):
    """
    获取文件base64编码
    :param path: 文件路径
    :param urlencoded: 是否对结果进行urlencoded 
    :return: base64编码信息
    """
    with open(path, "rb") as f:
        content = base64.b64encode(f.read()).decode("utf8")
        if urlencoded:
            content = urllib.parse.quote_plus(content)
    return content

def get_access_token():
    """
    使用 AK，SK 生成鉴权签名（Access Token）
    :return: access_token，或是None(如果错误)
    """
    url = "https://aip.baidubce.com/oauth/2.0/token"
    params = {"grant_type": "client_credentials", "client_id": API_KEY, "client_secret": SECRET_KEY}
    return str(requests.post(url, params=params).json().get("access_token"))

if __name__ == '__main__':
    #filepath=r'C:\Users\ASUS\Desktop\invoice.png'
    #print(argv[1])
    filepath=argv[1]
    main(filepath)
