# [2351. First Letter to Appear Twice](https://leetcode.com/problems/first-letter-to-appear-twice)

[中文文档](/solution/2300-2399/2351.First%20Letter%20to%20Appear%20Twice/README.md)

## Description

<p>Given a string <code>s</code> consisting of lowercase English letters, return <em>the first letter to appear <strong>twice</strong></em>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>A letter <code>a</code> appears twice before another letter <code>b</code> if the <strong>second</strong> occurrence of <code>a</code> is before the <strong>second</strong> occurrence of <code>b</code>.</li>
	<li><code>s</code> will contain at least one letter that appears twice.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abccbaacz&quot;
<strong>Output:</strong> &quot;c&quot;
<strong>Explanation:</strong>
The letter &#39;a&#39; appears on the indexes 0, 5 and 6.
The letter &#39;b&#39; appears on the indexes 1 and 4.
The letter &#39;c&#39; appears on the indexes 2, 3 and 7.
The letter &#39;z&#39; appears on the index 8.
The letter &#39;c&#39; is the first letter to appear twice, because out of all the letters the index of its second occurrence is the smallest.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcdd&quot;
<strong>Output:</strong> &quot;d&quot;
<strong>Explanation:</strong>
The only letter that appears twice is &#39;d&#39; so we return &#39;d&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>s</code> has at least one repeated letter.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def repeatedCharacter(self, s: str) -> str:
        cnt = Counter()
        for v in s:
            cnt[v] += 1
            if cnt[v] == 2:
                return v
```

### **Java**

```java
class Solution {
    public char repeatedCharacter(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            if (++cnt[c - 'a'] == 2) {
                return c;
            }
        }
        return '.';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    char repeatedCharacter(string s) {
        vector<int> cnt(26);
        for (char c : s)
            if (++cnt[c - 'a'] == 2) return c;
        return '.';
    }
};
```

### **Go**

```go
func repeatedCharacter(s string) byte {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
		if cnt[c-'a'] == 2 {
			return byte(c)
		}
	}
	return '.'
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
