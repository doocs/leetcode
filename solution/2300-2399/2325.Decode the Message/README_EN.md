# [2325. Decode the Message](https://leetcode.com/problems/decode-the-message)

[中文文档](/solution/2300-2399/2325.Decode%20the%20Message/README.md)

## Description

<p>You are given the strings <code>key</code> and <code>message</code>, which represent a cipher key and a secret message, respectively. The steps to decode <code>message</code> are as follows:</p>

<ol>
	<li>Use the <strong>first</strong> appearance of all 26 lowercase English letters in <code>key</code> as the <strong>order</strong> of the substitution table.</li>
	<li>Align the substitution table with the regular English alphabet.</li>
	<li>Each letter in <code>message</code> is then <strong>substituted</strong> using the table.</li>
	<li>Spaces <code>&#39; &#39;</code> are transformed to themselves.</li>
</ol>

<ul>
	<li>For example, given <code>key = &quot;<u><strong>hap</strong></u>p<u><strong>y</strong></u> <u><strong>bo</strong></u>y&quot;</code> (actual key would have <strong>at least one</strong> instance of each letter in the alphabet), we have the partial substitution table of (<code>&#39;h&#39; -&gt; &#39;a&#39;</code>, <code>&#39;a&#39; -&gt; &#39;b&#39;</code>, <code>&#39;p&#39; -&gt; &#39;c&#39;</code>, <code>&#39;y&#39; -&gt; &#39;d&#39;</code>, <code>&#39;b&#39; -&gt; &#39;e&#39;</code>, <code>&#39;o&#39; -&gt; &#39;f&#39;</code>).</li>
</ul>

<p>Return <em>the decoded message</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2325.Decode%20the%20Message/images/ex1new4.jpg" style="width: 752px; height: 150px;" />
<pre>
<strong>Input:</strong> key = &quot;the quick brown fox jumps over the lazy dog&quot;, message = &quot;vkbs bs t suepuv&quot;
<strong>Output:</strong> &quot;this is a secret&quot;
<strong>Explanation:</strong> The diagram above shows the substitution table.
It is obtained by taking the first appearance of each letter in &quot;<u><strong>the</strong></u> <u><strong>quick</strong></u> <u><strong>brown</strong></u> <u><strong>f</strong></u>o<u><strong>x</strong></u> <u><strong>j</strong></u>u<u><strong>mps</strong></u> o<u><strong>v</strong></u>er the <u><strong>lazy</strong></u> <u><strong>d</strong></u>o<u><strong>g</strong></u>&quot;.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2325.Decode%20the%20Message/images/ex2new.jpg" style="width: 754px; height: 150px;" />
<pre>
<strong>Input:</strong> key = &quot;eljuxhpwnyrdgtqkviszcfmabo&quot;, message = &quot;zwx hnfx lqantp mnoeius ycgk vcnjrdb&quot;
<strong>Output:</strong> &quot;the five boxing wizards jump quickly&quot;
<strong>Explanation:</strong> The diagram above shows the substitution table.
It is obtained by taking the first appearance of each letter in &quot;<u><strong>eljuxhpwnyrdgtqkviszcfmabo</strong></u>&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>26 &lt;= key.length &lt;= 2000</code></li>
	<li><code>key</code> consists of lowercase English letters and <code>&#39; &#39;</code>.</li>
	<li><code>key</code> contains every letter in the English alphabet (<code>&#39;a&#39;</code> to <code>&#39;z&#39;</code>) <strong>at least once</strong>.</li>
	<li><code>1 &lt;= message.length &lt;= 2000</code></li>
	<li><code>message</code> consists of lowercase English letters and <code>&#39; &#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
