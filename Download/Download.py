import pandas as pd
import requests

class Downloading:
    nameList = [];
    urlList = [];

    def __init__(self):
        path = r'D:\1-Project\Py Projects\downloading\pictures.xlsx'
        xlsx = pd.read_excel(path)
        data = pd.DataFrame(xlsx)
        self.nameList = data['ImageNumber']
        self.urlList = data['Url']
        self.download(self.nameList.__len__())

    def download(self, len):
        for i in range(0, len):
            name = str(self.nameList[i]) + ".png"
            url = self.urlList[i]
            r = requests.get(url, stream=False)
            print(r.status_code)
            with open("./Images/" + name, 'wb') as file:
                chunk_size = 1024 * 4  # The chunk size = 8K
                for chunk in r.iter_content(chunk_size):
                    file.write(chunk)
                    file.flush()
