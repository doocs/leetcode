# [535. TinyURL 的加密与解密](https://leetcode.cn/problems/encode-and-decode-tinyurl)

[English Version](/solution/0500-0599/0535.Encode%20and%20Decode%20TinyURL/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>TinyURL 是一种 URL 简化服务， 比如：当你输入一个 URL&nbsp;<code>https://leetcode.com/problems/design-tinyurl</code>&nbsp;时，它将返回一个简化的URL&nbsp;<code>http://tinyurl.com/4e9iAk</code> 。请你设计一个类来加密与解密 TinyURL 。</p>

<p>加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。</p>

<p>实现 <code>Solution</code> 类：</p>

<div class="original__bRMd">
<div>
<ul>
	<li><code>Solution()</code> 初始化 TinyURL 系统对象。</li>
	<li><code>String encode(String longUrl)</code> 返回 <code>longUrl</code> 对应的 TinyURL 。</li>
	<li><code>String decode(String shortUrl)</code> 返回 <code>shortUrl</code> 原本的 URL 。题目数据保证给定的 <code>shortUrl</code> 是由同一个系统对象加密的。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>url = "https://leetcode.com/problems/design-tinyurl"
<strong>输出：</strong>"https://leetcode.com/problems/design-tinyurl"

<strong>解释：</strong>
Solution obj = new Solution();
string tiny = obj.encode(url); // 返回加密后得到的 TinyURL 。
string ans = obj.decode(tiny); // 返回解密后得到的原本的 URL 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= url.length &lt;= 10<sup>4</sup></code></li>
	<li>题目数据保证 <code>url</code> 是一个有效的 URL</li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Codec:
    def __init__(self):
        self.m = defaultdict()
        self.idx = 0
        self.domain = 'https://tinyurl.com/'

    def encode(self, longUrl: str) -> str:
        """Encodes a URL to a shortened URL."""
        self.idx += 1
        self.m[str(self.idx)] = longUrl
        return f'{self.domain}{self.idx}'

    def decode(self, shortUrl: str) -> str:
        """Decodes a shortened URL to its original URL."""
        idx = shortUrl.split('/')[-1]
        return self.m[idx]


# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.decode(codec.encode(url))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
public class Codec {
    private Map<String, String> m = new HashMap<>();
    private int idx = 0;
    private String domain = "https://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String v = String.valueOf(++idx);
        m.put(v, longUrl);
        return domain + v;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int i = shortUrl.lastIndexOf('/') + 1;
        return m.get(shortUrl.substring(i));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
```

### **C++**

```cpp
class Solution {
public:
    // Encodes a URL to a shortened URL.
    string encode(string longUrl) {
        string v = to_string(++idx);
        m[v] = longUrl;
        return domain + v;
    }

    // Decodes a shortened URL to its original URL.
    string decode(string shortUrl) {
        int i = shortUrl.rfind('/') + 1;
        return m[shortUrl.substr(i, shortUrl.size() - i)];
    }

private:
    unordered_map<string, string> m;
    int idx = 0;
    string domain = "https://tinyurl.com/";
};

// Your Solution object will be instantiated and called as such:
// Solution solution;
// solution.decode(solution.encode(url));
```

### **Go**

```go
type Codec struct {
	m   map[int]string
	idx int
}

func Constructor() Codec {
	m := map[int]string{}
	return Codec{m, 0}
}

// Encodes a URL to a shortened URL.
func (this *Codec) encode(longUrl string) string {
	this.idx++
	this.m[this.idx] = longUrl
	return "https://tinyurl.com/" + strconv.Itoa(this.idx)
}

// Decodes a shortened URL to its original URL.
func (this *Codec) decode(shortUrl string) string {
	i := strings.LastIndexByte(shortUrl, '/')
	v, _ := strconv.Atoi(shortUrl[i+1:])
	return this.m[v]
}

/**
 * Your Codec object will be instantiated and called as such:
 * obj := Constructor();
 * url := obj.encode(longUrl);
 * ans := obj.decode(url);
 */
```

### **...**

```

```

<!-- tabs:end -->
