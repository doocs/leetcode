# [535. Encode and Decode TinyURL](https://leetcode.com/problems/encode-and-decode-tinyurl)

[中文文档](/solution/0500-0599/0535.Encode%20and%20Decode%20TinyURL/README.md)

## Description

<blockquote>Note: This is a companion problem to the <a href="https://leetcode.com/discuss/interview-question/system-design/" target="_blank">System Design</a> problem: <a href="https://leetcode.com/discuss/interview-question/124658/Design-a-URL-Shortener-(-TinyURL-)-System/" target="_blank">Design TinyURL</a>.</blockquote>

<p>TinyURL is a URL shortening service where you enter a URL such as <code>https://leetcode.com/problems/design-tinyurl</code> and it returns a short URL such as <code>http://tinyurl.com/4e9iAk</code>.</p>

<p>Design the <code>encode</code> and <code>decode</code> methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
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
```

### **Java**

```java
public class Codec {
    private Map<String, String> code2Url = new HashMap<>();
    private int count = 0;
    private static final String prefixUrl = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String code = Integer.toHexString(++count);
        code2Url.put(code, longUrl);
        return prefixUrl + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String code = shortUrl.replace(prefixUrl, "");
        return code2Url.get(code);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
```

### **...**

```

```

<!-- tabs:end -->
