# [1374. Generate a String With Characters That Have Odd Counts](https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts)

[中文文档](/solution/1300-1399/1374.Generate%20a%20String%20With%20Characters%20That%20Have%20Odd%20Counts/README.md)

## Description

<p>Given an&nbsp;integer <code>n</code>, <em>return a string with <code>n</code>&nbsp;characters such that each character in such string occurs <strong>an odd number of times</strong></em>.</p>

<p>The returned string must contain only lowercase English letters. If there are multiples valid strings, return <strong>any</strong> of them. &nbsp;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> &quot;pppz&quot;
<strong>Explanation:</strong> &quot;pppz&quot; is a valid string since the character &#39;p&#39; occurs three times and the character &#39;z&#39; occurs once. Note that there are many other valid strings such as &quot;ohhh&quot; and &quot;love&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> &quot;xy&quot;
<strong>Explanation:</strong> &quot;xy&quot; is a valid string since the characters &#39;x&#39; and &#39;y&#39; occur once. Note that there are many other valid strings such as &quot;ag&quot; and &quot;ur&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> &quot;holasss&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def generateTheString(self, n: int) -> str:
        return 'a' * n if n & 1 else 'a' * (n - 1) + 'b'
```

### **Java**

```java
class Solution {
    public String generateTheString(int n) {
        return (n % 2 == 1) ? "a".repeat(n) : "a".repeat(n - 1) + "b";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string generateTheString(int n) {
        string ans(n, 'a');
        if (n % 2 == 0) ans[0] = 'b';
        return ans;
    }
};
```

### **Go**

```go
func generateTheString(n int) string {
	ans := strings.Repeat("a", n-1)
	if n%2 == 0 {
		ans += "b"
	} else {
		ans += "a"
	}
	return ans
}
```

### **TypeScript**

```ts
function generateTheString(n: number): string {
    const res = new Array(n).fill('a');
    if (n % 2 === 0) {
        res[n - 1] = 'b';
    }
    return res.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
