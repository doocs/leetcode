---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0271.Encode%20and%20Decode%20Strings/README.md
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

<pre>string encode(vector&lt;string&gt; strs) {
  // ... your code
  return encoded_string;
}</pre>

<p>2 号机（接收方）有如下函数：</p>

<pre>vector&lt;string&gt; decode(string s) {
  //... your code
  return strs;
}
</pre>

<p>1 号机（发送方）执行：</p>

<pre>string encoded_string = encode(strs);
</pre>

<p>2 号机（接收方）执行：</p>

<pre>vector&lt;string&gt; strs2 = decode(encoded_string);
</pre>

<p>此时，2 号机（接收方）的 <code>strs2</code>&nbsp;需要和 1 号机（发送方）的 <code>strs</code> 相同。</p>

<p>请你来实现这个&nbsp;<code>encode</code> 和&nbsp;<code>decode</code> 方法。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>因为字符串可能会包含 256 个合法&nbsp;ascii 字符中的任何字符，所以您的算法必须要能够处理任何可能会出现的字符。</li>
	<li>请勿使用 &ldquo;类成员&rdquo;、&ldquo;全局变量&rdquo; 或 &ldquo;静态变量&rdquo; 来存储这些状态，您的编码和解码算法应该是非状态依赖的。</li>
	<li>请不要依赖任何方法库，例如 <code>eval</code>&nbsp;又或者是&nbsp;<code>serialize</code>&nbsp;之类的方法。本题的宗旨是需要您自己实现 &ldquo;编码&rdquo; 和 &ldquo;解码&rdquo; 算法。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：编码字符串长度

编码时，将字符串的长度转成固定 $4$ 位的字符串，加上字符串本身，依次拼接到结果字符串。

解码时，先取前四位字符串，得到长度，再通过长度截取后面的字符串。依次截取，最终得到字符串列表。

时间复杂度 $O(n)$。

<!-- tabs:start -->

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
