# [535. TinyURL 的加密与解密](https://leetcode-cn.com/problems/encode-and-decode-tinyurl)

[English Version](/solution/0500-0599/0535.Encode%20and%20Decode%20TinyURL/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>TinyURL是一种URL简化服务， 比如：当你输入一个URL&nbsp;<code>https://leetcode.com/problems/design-tinyurl</code>&nbsp;时，它将返回一个简化的URL&nbsp;<code>http://tinyurl.com/4e9iAk</code>.</p>

<p>要求：设计一个 TinyURL 的加密&nbsp;<code>encode</code>&nbsp;和解密&nbsp;<code>decode</code>&nbsp;的方法。你的加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，并且这个TinyURL可以用解密方法恢复成原本的URL。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

哈希表实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
