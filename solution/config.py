# urls
from enum import unique, Enum

cn_graphql_url = 'https://leetcode-cn.com/graphql'
problems_url = 'https://leetcode-cn.com/problems/'
all_problems_url = 'https://leetcode.com/api/problems/all/'
lcof_problems_url = 'https://leetcode-cn.com/api/problems/lcof/'
lcci_problems_url = 'https://leetcode-cn.com/api/problems/lcci/'

# http
fetch_timeout = 5
async_headers = {
    'accept': 'application/json, text/javascript, */*; q=0.01',
    'content-type': 'application/json',
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) '
                  'Chrome/77.0.3865.120 Safari/537.36',
    'x-requested-with': 'XMLHttpRequest'
}

# retrying
retry_max_number = 8
retry_min_random_wait = 50  # ms
retry_max_random_wait = 100  # ms

# maps
difficulties = {
    '1': '简单',
    '2': '中等',
    '3': '困难'
}


# request
@unique
class Req(Enum):
    GET = 0
    POST = 1
