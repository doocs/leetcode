# [1513. Number of Substrings With Only 1s](https://leetcode.com/problems/number-of-substrings-with-only-1s)

[中文文档](/solution/1500-1599/1513.Number%20of%20Substrings%20With%20Only%201s/README.md)

## Description

<p>Given a binary string <code>s</code>, return <em>the number of substrings with all characters</em> <code>1</code><em>&#39;s</em>. Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0110111&quot;
<strong>Output:</strong> 9
<strong>Explanation:</strong> There are 9 substring in total with only 1&#39;s characters.
&quot;1&quot; -&gt; 5 times.
&quot;11&quot; -&gt; 3 times.
&quot;111&quot; -&gt; 1 time.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;101&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Substring &quot;1&quot; is shown 2 times in s.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;111111&quot;
<strong>Output:</strong> 21
<strong>Explanation:</strong> Each substring contains only 1&#39;s characters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numSub(self, s: str) -> int:
        ans = cnt = 0
        for c in s:
            if c == "1":
                cnt += 1
            else:
                cnt = 0
            ans += cnt
        return ans % (10**9 + 7)
```

### **Java**

```java
class Solution {
    public int numSub(String s) {
        final int mod = (int) 1e9 + 7;
        int ans = 0, cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            cnt = s.charAt(i) == '1' ? cnt + 1 : 0;
            ans = (ans + cnt) % mod;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSub(string s) {
        int ans = 0, cnt = 0;
        const int mod = 1e9 + 7;
        for (char& c : s) {
            cnt = c == '1' ? cnt + 1 : 0;
            ans = (ans + cnt) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func numSub(s string) (ans int) {
	const mod = 1e9 + 7
	cnt := 0
	for _, c := range s {
		if c == '1' {
			cnt++
		} else {
			cnt = 0
		}
		ans = (ans + cnt) % mod
	}
	return
}
```

### **TypeScript**

```ts
function numSub(s: string): number {
    const mod = 10 ** 9 + 7;
    let ans = 0;
    let cnt = 0;
    for (const c of s) {
        cnt = c == '1' ? cnt + 1 : 0;
        ans = (ans + cnt) % mod;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
