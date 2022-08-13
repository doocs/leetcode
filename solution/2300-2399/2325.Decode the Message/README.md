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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def decodeMessage(self, key: str, message: str) -> str:
        d = {" ": " "}
        i = 0
        for c in key:
            if c in d:
                continue
            d[c] = ascii_lowercase[i]
            i += 1
        return "".join(d[c] for c in message)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String decodeMessage(String key, String message) {
        Map<Character, Character> d = new HashMap<>();
        String lowcase = "abcdefghijklmnopqrstuvwxyz";
        d.put(' ', ' ');
        int i = 0;
        for (char c : key.toCharArray()) {
            if (d.containsKey(c)) {
                continue;
            }
            d.put(c, lowcase.charAt(i++));
        }
        StringBuilder ans = new StringBuilder();
        for (char c : message.toCharArray()) {
            ans.append(d.get(c));
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string decodeMessage(string key, string message) {
        unordered_map<char, char> d;
        d[' '] = ' ';
        int i = 0;
        string lowcase = "abcdefghijklmnopqrstuvwxyz";
        for (char c : key) {
            if (d.count(c)) continue;
            d[c] = lowcase[i]++;
        }
        string ans;
        for (char c : message) ans.push_back(d[c]);
        return ans;
    }
};
```

### **Go**

```go
func decodeMessage(key string, message string) string {
	d := map[rune]byte{}
	d[' '] = ' '
	i := 0
	lowcase := "abcdefghijklmnopqrstuvwxyz"
	for _, c := range key {
		if _, ok := d[c]; ok {
			continue
		}
		d[c] = lowcase[i]
		i++
	}
	var ans []byte
	for _, c := range message {
		ans = append(ans, d[c])
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function decodeMessage(key: string, message: string): string {
    let decodeMap = new Map();
    const m = key.length,
        n = 26;
    for (let i = 0, j = 0; i < m; i++) {
        let char = key.charAt(i);
        if (char != ' ' && !decodeMap.has(char)) {
            decodeMap.set(char, String.fromCharCode(j + 97));
            j++;
        }
    }
    let ans = [];
    for (let char of message) {
        ans.push(char == ' ' ? ' ' : decodeMap.get(char));
    }
    return ans.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
