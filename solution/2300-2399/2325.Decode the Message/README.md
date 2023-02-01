# [2325. 解密消息](https://leetcode.cn/problems/decode-the-message)

[English Version](/solution/2300-2399/2325.Decode%20the%20Message/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你字符串 <code>key</code> 和 <code>message</code> ，分别表示一个加密密钥和一段加密消息。解密 <code>message</code> 的步骤如下：</p>

<ol>
	<li>使用 <code>key</code> 中 26 个英文小写字母第一次出现的顺序作为替换表中的字母 <strong>顺序</strong> 。</li>
	<li>将替换表与普通英文字母表对齐，形成对照表。</li>
	<li>按照对照表 <strong>替换</strong> <code>message</code> 中的每个字母。</li>
	<li>空格 <code>' '</code> 保持不变。</li>
</ol>

<ul>
	<li>例如，<code>key = "<em><strong>hap</strong></em>p<em><strong>y</strong></em> <em><strong>bo</strong></em>y"</code>（实际的加密密钥会包含字母表中每个字母 <strong>至少一次</strong>），据此，可以得到部分对照表（<code>'h' -&gt; 'a'</code>、<code>'a' -&gt; 'b'</code>、<code>'p' -&gt; 'c'</code>、<code>'y' -&gt; 'd'</code>、<code>'b' -&gt; 'e'</code>、<code>'o' -&gt; 'f'</code>）。</li>
</ul>

<p>返回解密后的消息。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2325.Decode%20the%20Message/images/ex1new4.jpg" style="width: 752px; height: 150px;" /></p>

<pre>
<strong>输入：</strong>key = "the quick brown fox jumps over the lazy dog", message = "vkbs bs t suepuv"
<strong>输出：</strong>"this is a secret"
<strong>解释：</strong>对照表如上图所示。
提取 "<em><strong>the</strong></em> <em><strong>quick</strong></em> <em><strong>brown</strong></em> <em><strong>f</strong></em>o<em><strong>x</strong></em> <em><strong>j</strong></em>u<em><strong>mps</strong></em> o<em><strong>v</strong></em>er the <em><strong>lazy</strong></em> <em><strong>d</strong></em>o<em><strong>g</strong></em>" 中每个字母的首次出现可以得到替换表。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2325.Decode%20the%20Message/images/ex2new.jpg" style="width: 754px; height: 150px;" /></p>

<pre>
<strong>输入：</strong>key = "eljuxhpwnyrdgtqkviszcfmabo", message = "zwx hnfx lqantp mnoeius ycgk vcnjrdb"
<strong>输出：</strong>"the five boxing wizards jump quickly"
<strong>解释：</strong>对照表如上图所示。
提取 "<em><strong>eljuxhpwnyrdgtqkviszcfmabo</strong></em>" 中每个字母的首次出现可以得到替换表。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>26 &lt;= key.length &lt;= 2000</code></li>
	<li><code>key</code> 由小写英文字母及 <code>' '</code> 组成</li>
	<li><code>key</code> 包含英文字母表中每个字符（<code>'a'</code> 到 <code>'z'</code>）<strong>至少一次</strong></li>
	<li><code>1 &lt;= message.length &lt;= 2000</code></li>
	<li><code>message</code> 由小写英文字母和 <code>' '</code> 组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表**

我们可以使用数组或哈希表 $d$ 存储对照表，然后遍历 `message` 中的每个字符，将其替换为对应的字符即可。

时间复杂度 $O(m + n)$，空间复杂度 $O(C)$。其中 $m$ 和 $n$ 分别为 `key` 和 `message` 的长度；而 $C$ 为字符集大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def decodeMessage(self, key: str, message: str) -> str:
        d = {" ": " "}
        i = 0
        for c in key:
            if c not in d:
                d[c] = ascii_lowercase[i]
                i += 1
        return "".join(d[c] for c in message)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String decodeMessage(String key, String message) {
        char[] d = new char[128];
        d[' '] = ' ';
        for (int i = 0, j = 0; i < key.length(); ++i) {
            char c = key.charAt(i);
            if (d[c] == 0) {
                d[c] = (char) ('a' + j++);
            }
        }
        char[] ans = message.toCharArray();
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = d[ans[i]];
        }
        return String.valueOf(ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string decodeMessage(string key, string message) {
        char d[128]{};
        d[' '] = ' ';
        char i = 'a';
        for (char& c : key) {
            if (!d[c]) {
                d[c] = i++;
            }
        }
        for (char& c : message) {
            c = d[c];
        }
        return message;
    }
};
```

### **Go**

```go
func decodeMessage(key string, message string) string {
	d := [128]byte{}
	d[' '] = ' '
	for i, j := 0, 0; i < len(key); i++ {
		if d[key[i]] == 0 {
			d[key[i]] = byte('a' + j)
			j++
		}
	}
	ans := []byte(message)
	for i, c := range ans {
		ans[i] = d[c]
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function decodeMessage(key: string, message: string): string {
    const d = new Map<string, string>();
    for (const c of key) {
        if (c === ' ' || d.has(c)) {
            continue;
        }
        d.set(c, String.fromCharCode('a'.charCodeAt(0) + d.size));
    }
    d.set(' ', ' ');
    return [...message].map(v => d.get(v)).join('');
}
```

### **Rust**

```rust
use std::collections::HashMap;
impl Solution {
    pub fn decode_message(key: String, message: String) -> String {
        let mut d = HashMap::new();
        for c in key.as_bytes() {
            if *c == b' ' || d.contains_key(c) {
                continue;
            }
            d.insert(c, char::from((97 + d.len()) as u8));
        }
        message
            .as_bytes()
            .iter()
            .map(|c| d.get(c).unwrap_or(&' '))
            .collect()
    }
}
```

### **C**

```c
char *decodeMessage(char *key, char *message) {
    int m = strlen(key);
    int n = strlen(message);
    char d[26];
    memset(d, ' ', 26);
    for (int i = 0, j = 0; i < m; i++) {
        if (key[i] == ' ' || d[key[i] - 'a'] != ' ') {
            continue;
        }
        d[key[i] - 'a'] = 'a' + j++;
    }
    char *ans = malloc(n + 1);
    for (int i = 0; i < n; i++) {
        ans[i] = message[i] == ' ' ? ' ' : d[message[i] - 'a'];
    }
    ans[n] = '\0';
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
