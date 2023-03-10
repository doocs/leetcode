# [1576. Replace All 's to Avoid Consecutive Repeating Characters](https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters)

[中文文档](/solution/1500-1599/1576.Replace%20All%20%27s%20to%20Avoid%20Consecutive%20Repeating%20Characters/README.md)

## Description

<p>Given a string <code>s</code> containing only lowercase English letters and the <code>&#39;?&#39;</code> character, convert <strong>all </strong>the <code>&#39;?&#39;</code> characters into lowercase letters such that the final string does not contain any <strong>consecutive repeating </strong>characters. You <strong>cannot </strong>modify the non <code>&#39;?&#39;</code> characters.</p>

<p>It is <strong>guaranteed </strong>that there are no consecutive repeating characters in the given string <strong>except </strong>for <code>&#39;?&#39;</code>.</p>

<p>Return <em>the final string after all the conversions (possibly zero) have been made</em>. If there is more than one solution, return <strong>any of them</strong>. It can be shown that an answer is always possible with the given constraints.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;?zs&quot;
<strong>Output:</strong> &quot;azs&quot;
<strong>Explanation:</strong> There are 25 solutions for this problem. From &quot;azs&quot; to &quot;yzs&quot;, all are valid. Only &quot;z&quot; is an invalid modification as the string will consist of consecutive repeating characters in &quot;zzs&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ubv?w&quot;
<strong>Output:</strong> &quot;ubvaw&quot;
<strong>Explanation:</strong> There are 24 solutions for this problem. Only &quot;v&quot; and &quot;w&quot; are invalid modifications as the strings will consist of consecutive repeating characters in &quot;ubvvw&quot; and &quot;ubvww&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consist of lowercase English letters and <code>&#39;?&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def modifyString(self, s: str) -> str:
        s = list(s)
        n = len(s)
        for i in range(n):
            if s[i] == "?":
                for c in "abc":
                    if (i and s[i - 1] == c) or (i + 1 < n and s[i + 1] == c):
                        continue
                    s[i] = c
                    break
        return "".join(s)
```

### **Java**

```java
class Solution {
    public String modifyString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; ++i) {
            if (cs[i] == '?') {
                for (char c = 'a'; c <= 'c'; ++c) {
                    if ((i > 0 && cs[i - 1] == c) || (i + 1 < n && cs[i + 1] == c)) {
                        continue;
                    }
                    cs[i] = c;
                    break;
                }
            }
        }
        return String.valueOf(cs);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string modifyString(string s) {
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (s[i] == '?') {
                for (char c : "abc") {
                    if ((i && s[i - 1] == c) || (i + 1 < n && s[i + 1] == c)) {
                        continue;
                    }
                    s[i] = c;
                    break;
                }
            }
        }
        return s;
    }
};
```

### **Go**

```go
func modifyString(s string) string {
	n := len(s)
	cs := []byte(s)
	for i := range s {
		if cs[i] == '?' {
			for c := byte('a'); c <= byte('c'); c++ {
				if (i > 0 && cs[i-1] == c) || (i+1 < n && cs[i+1] == c) {
					continue
				}
				cs[i] = c
				break
			}
		}
	}
	return string(cs)
}
```

### **TypeScript**

```ts
function modifyString(s: string): string {
    const cs = s.split('');
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (cs[i] === '?') {
            for (const c of 'abc') {
                if (
                    (i > 0 && cs[i - 1] === c) ||
                    (i + 1 < n && cs[i + 1] === c)
                ) {
                    continue;
                }
                cs[i] = c;
                break;
            }
        }
    }
    return cs.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
