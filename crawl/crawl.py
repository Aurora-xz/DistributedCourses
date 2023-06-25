import threading
from queue import Queue

import requests as requests
from bs4 import BeautifulSoup

MAX_PAGES = 1000  # 最大爬取数量
NUM_THREAD = 10   # 线程数量

visited_urls = set()  # 存储已经访问的url
url_queue = Queue()   # url队列

url_queue.put("https://www.baidu.com")

# 互斥锁 保护visited_urls的线程安全
lock = threading.Lock()


def crawl():
    while not url_queue.empty() and len(visited_urls) < MAX_PAGES:
        # 从url队列获取url
        url = url_queue.get()
        # 发送HTTP请求获取网页内容
        response = requests.get(url)
        context = response.text

        # 获取网页大小
        page_size = len(response.content)
        # 输出网页大小
        print(f"URL: {url}, Size: {page_size}")

        # 解析网页内容
        soup = BeautifulSoup(context, 'html.parser')
        # 提取所有<a>标签中的url
        for link in soup.find_all('a'):
            href = link.get('href')
            if href and href.startswith('http'):
                # 将新的url添加到url队列并查重
                with lock:
                    if href not in visited_urls:
                        url_queue.put(href)

        # 将已经访问的url添加到visited_url
        with lock:
            visited_urls.add(url)


# 创建并启动多个线程
threads = []
for _ in range(NUM_THREAD):
    t = threading.Thread(target=crawl)
    t.start()
    threads.append(t)

# 等待所有线程完成
for t in threads:
    t.join()


