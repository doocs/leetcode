import requests
from requests import Response
from retrying import retry

from config import Req, retry_max_number, retry_min_random_wait, retry_max_random_wait, fetch_timeout


def need_retry(exception):
    result = isinstance(exception, (requests.ConnectionError,
                                    requests.ReadTimeout,
                                    requests.exceptions.ConnectTimeout,
                                    requests.exceptions.ReadTimeout,
                                    requests.exceptions.Timeout))
    if result:
        print('Exception:{} occurred, retrying...'.format(type(exception)))
    return result


def fetch(url, method=Req.GET, **kwargs):
    @retry(stop_max_attempt_number=retry_max_number, wait_random_min=retry_min_random_wait,
           wait_random_max=retry_max_random_wait, retry_on_exception=need_retry)
    def _fetch(url, **kwargs) -> Response:
        # kwargs.update({'verify': False})
        kwargs.update({'timeout': fetch_timeout})
        response = requests.post(url, **kwargs) if method == Req.POST else requests.get(url, **kwargs)
        if response.status_code != 200:
            raise requests.ConnectionError('Expected status code 200, but got {}'.format(response.status_code))
        return response

    try:
        resp = _fetch(url, **kwargs)
        return resp
    except Exception as e:
        print('Something got wrong, error msg:{}'.format(e))
        return None
