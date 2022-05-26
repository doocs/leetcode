# [423. Reconstruct Original Digits from English](https://leetcode.com/problems/reconstruct-original-digits-from-english)

[中文文档](/solution/0400-0499/0423.Reconstruct%20Original%20Digits%20from%20English/README.md)

## Description

<p>Given a string <code>s</code> containing an out-of-order English representation of digits <code>0-9</code>, return <em>the digits in <strong>ascending</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "owoztneoer"
<strong>Output:</strong> "012"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "fviefuro"
<strong>Output:</strong> "45"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is one of the characters <code>[&quot;e&quot;,&quot;g&quot;,&quot;f&quot;,&quot;i&quot;,&quot;h&quot;,&quot;o&quot;,&quot;n&quot;,&quot;s&quot;,&quot;r&quot;,&quot;u&quot;,&quot;t&quot;,&quot;w&quot;,&quot;v&quot;,&quot;x&quot;,&quot;z&quot;]</code>.</li>
	<li><code>s</code> is <strong>guaranteed</strong> to be valid.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def originalDigits(self, s: str) -> str:
        counter = Counter(s)
        cnt = [0] * 10

        cnt[0] = counter['z']
        cnt[2] = counter['w']
        cnt[4] = counter['u']
        cnt[6] = counter['x']
        cnt[8] = counter['g']

        cnt[3] = counter['h'] - cnt[8]
        cnt[5] = counter['f'] - cnt[4]
        cnt[7] = counter['s'] - cnt[6]

        cnt[1] = counter['o'] - cnt[0] - cnt[2] - cnt[4]
        cnt[9] = counter['i'] - cnt[5] - cnt[6] - cnt[8]

        return ''.join(cnt[i] * str(i) for i in range(10))
```

### **Java**

```java
class Solution {
    public String originalDigits(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int[] cnt = new int[10];
        cnt[0] = counter['z' - 'a'];
        cnt[2] = counter['w' - 'a'];
        cnt[4] = counter['u' - 'a'];
        cnt[6] = counter['x' - 'a'];
        cnt[8] = counter['g' - 'a'];

        cnt[3] = counter['h' - 'a'] - cnt[8];
        cnt[5] = counter['f' - 'a'] - cnt[4];
        cnt[7] = counter['s' - 'a'] - cnt[6];

        cnt[1] = counter['o' - 'a'] - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = counter['i' - 'a'] - cnt[5] - cnt[6] - cnt[8];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string originalDigits(string s) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        vector<int> cnt(10);
        cnt[0] = counter['z' - 'a'];
        cnt[2] = counter['w' - 'a'];
        cnt[4] = counter['u' - 'a'];
        cnt[6] = counter['x' - 'a'];
        cnt[8] = counter['g' - 'a'];

        cnt[3] = counter['h' - 'a'] - cnt[8];
        cnt[5] = counter['f' - 'a'] - cnt[4];
        cnt[7] = counter['s' - 'a'] - cnt[6];

        cnt[1] = counter['o' - 'a'] - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = counter['i' - 'a'] - cnt[5] - cnt[6] - cnt[8];

        string ans;
        for (int i = 0; i < 10; ++i)
            for (int j = 0; j < cnt[i]; ++j)
                ans += char(i + '0');
        return ans;
    }
};
```

### **Go**

```go
func originalDigits(s string) string {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	cnt := make([]int, 10)
	cnt[0] = counter['z'-'a']
	cnt[2] = counter['w'-'a']
	cnt[4] = counter['u'-'a']
	cnt[6] = counter['x'-'a']
	cnt[8] = counter['g'-'a']

	cnt[3] = counter['h'-'a'] - cnt[8]
	cnt[5] = counter['f'-'a'] - cnt[4]
	cnt[7] = counter['s'-'a'] - cnt[6]

	cnt[1] = counter['o'-'a'] - cnt[0] - cnt[2] - cnt[4]
	cnt[9] = counter['i'-'a'] - cnt[5] - cnt[6] - cnt[8]

	ans := []byte{}
	for i, c := range cnt {
		ans = append(ans, bytes.Repeat([]byte{byte('0' + i)}, c)...)
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
