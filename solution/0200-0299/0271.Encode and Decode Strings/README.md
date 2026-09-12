---
comments: true
difficulty: 中等
tags:
    - 设计
    - 数组
    - 字符串
---

<!-- problem:start -->

# [271. 字符串的编码与解码 🔒](https://leetcode.cn/problems/encode-and-decode-strings)

[English Version](/solution/0200-0299/0271.Encode%20and%20Decode%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你设计一个算法，可以将一个&nbsp;<strong>字符串列表&nbsp;</strong>编码成为一个&nbsp;<strong>字符串</strong>。这个编码后的字符串是可以通过网络进行高效传送的，并且可以在接收端被解码回原来的字符串列表。</p>

<p>1 号机（发送方）有如下函数：</p>

<pre>
string encode(vector&lt;string&gt; strs) {
  // ... your code
  return encoded_string;
}</pre>

<p>2 号机（接收方）有如下函数：</p>

<pre>
vector&lt;string&gt; decode(string s) {
  //... your code
  return strs;
}
</pre>

<p>1 号机（发送方）执行：</p>

<pre>
string encoded_string = encode(strs);
</pre>

<p>2 号机（接收方）执行：</p>

<pre>
vector&lt;string&gt; strs2 = decode(encoded_string);
</pre>

<p>此时，2 号机（接收方）的 <code>strs2</code>&nbsp;需要和 1 号机（发送方）的 <code>strs</code> 相同。</p>

<p>请你来实现这个&nbsp;<code>encode</code> 和&nbsp;<code>decode</code> 方法。</p>

<p>不允许使用任何序列化方法解决这个问题（例如 <code>eval</code>）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>dummy_input = ["Hello","World"]
<b>输出：</b>["Hello","World"]
<strong>解释：</strong>
1 号机：
Codec encoder = new Codec();
String msg = encoder.encode(strs);
Machine 1 ---msg---&gt; Machine 2

2 号机：
Codec decoder = new Codec();
String[] strs = decoder.decode(msg);
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>dummy_input = [""]
<b>输出：</b>[""]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 200</code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 200</code></li>
	<li><code>strs[i]</code>&nbsp;包含 256 个有效 ASCII 字符中的任何可能字符。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能编写一个通用算法来处理任何可能的字符集吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：编码字符串长度

<!-- thinking:start -->

> **思考**
>
> 直接用分隔符拼接会与串内相同字符冲突。先写入定长的长度信息，再跟原文，即可无歧义切开。
>
> 编码时把长度格式化为 $4$ 位再拼接；解码时读 $4$ 位得到长度并截取。

<!-- thinking:end -->

编码时，将字符串的长度转成固定 $4$ 位的字符串，加上字符串本身，依次拼接到结果字符串。

解码时，先取前四位字符串，得到长度，再通过长度截取后面的字符串。依次截取，最终得到字符串列表。

时间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Codec:
    def encode(self, strs: List[str]) -> str:
        """Encodes a list of strings to a single string."""
        ans = []
        for s in strs:
            ans.append('{:4}'.format(len(s)) + s)
        return ''.join(ans)

    def decode(self, s: str) -> List[str]:
        """Decodes a single string to a list of strings."""
        ans = []
        i, n = 0, len(s)
        while i < n:
            size = int(s[i : i + 4])
            i += 4
            ans.append(s[i : i + size])
            i += size
        return ans


# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.decode(codec.encode(strs))
```

#### Java

```java
public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder ans = new StringBuilder();
        for (String s : strs) {
            ans.append((char) s.length()).append(s);
        }
        return ans.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ans = new ArrayList<>();
        int i = 0, n = s.length();
        while (i < n) {
            int size = s.charAt(i++);
            ans.add(s.substring(i, i + size));
            i += size;
        }
        return ans;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
```

#### C++

```cpp
class Codec {
public:
    // Encodes a list of strings to a single string.
    string encode(vector<string>& strs) {
        string ans;
        for (string s : strs) {
            int size = s.size();
            ans += string((const char*) &size, sizeof(size));
            ans += s;
        }
        return ans;
    }

    // Decodes a single string to a list of strings.
    vector<string> decode(string s) {
        vector<string> ans;
        int i = 0, n = s.size();
        int size = 0;
        while (i < n) {
            memcpy(&size, s.data() + i, sizeof(size));
            i += sizeof(size);
            ans.push_back(s.substr(i, size));
            i += size;
        }
        return ans;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.decode(codec.encode(strs));
```

#### Go

```go
type Codec struct {
}

// Encodes a list of strings to a single string.
func (codec *Codec) Encode(strs []string) string {
	ans := &bytes.Buffer{}
	for _, s := range strs {
		t := fmt.Sprintf("%04d", len(s))
		ans.WriteString(t)
		ans.WriteString(s)
	}
	return ans.String()
}

// Decodes a single string to a list of strings.
func (codec *Codec) Decode(strs string) []string {
	ans := []string{}
	i, n := 0, len(strs)
	for i < n {
		t := strs[i : i+4]
		i += 4
		size, _ := strconv.Atoi(t)
		ans = append(ans, strs[i:i+size])
		i += size
	}
	return ans
}

// Your Codec object will be instantiated and called as such:
// var codec Codec
// codec.Decode(codec.Encode(strs));
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
