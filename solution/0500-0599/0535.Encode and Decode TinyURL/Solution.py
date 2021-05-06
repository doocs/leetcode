class Codec:
    def __init__(self):
        self.code_url = {}
        self.count = 0
        self.prefix_url = 'http://tinyurl.com/'

    def encode(self, longUrl: str) -> str:
        """Encodes a URL to a shortened URL.
        """
        self.count += 1
        code = str(hex(self.count))[2:]
        self.code_url[code] = longUrl
        return self.prefix_url + code

    def decode(self, shortUrl: str) -> str:
        """Decodes a shortened URL to its original URL.
        """
        code = shortUrl.replace(self.prefix_url, '')
        return self.code_url[code]

# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.decode(codec.encode(url))