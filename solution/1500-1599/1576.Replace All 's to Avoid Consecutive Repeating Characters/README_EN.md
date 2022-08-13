# [1576. Replace All 's to Avoid Consecutive Repeating Characters](https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters)

[中文文档](/solution/1500-1599/1576.Replace%20All%20%27s%20to%20Avoid%20Consecutive%20Repeating%20Characters/README.md)

## Description

<p>Given a string <code>s</code> containing only lowercase English letters and the <code>&#39;?&#39;</code> character, convert <strong>all </strong>the <code>&#39;?&#39;</code> characters into lowercase letters such that the final string does not contain any <strong>consecutive repeating </strong>characters. You <strong>cannot </strong>modify the non <code>&#39;?&#39;</code> characters.</p>

<p>It is <strong>guaranteed </strong>that there are no consecutive repeating characters in the given string <strong>except </strong>for <code>&#39;?&#39;</code>.</p>

<p>Return <em>the final string after all the conversions (possibly zero) have been made</em>. If there is more than one solution, return <strong>any of them</strong>. It can be shown that an answer is always possible with the given constraints.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;?zs&quot;
<strong>Output:</strong> &quot;azs&quot;
<strong>Explanation:</strong> There are 25 solutions for this problem. From &quot;azs&quot; to &quot;yzs&quot;, all are valid. Only &quot;z&quot; is an invalid modification as the string will consist of consecutive repeating characters in &quot;zzs&quot;.
</pre>

<p><strong>Example 2:</strong></p>

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
        ans = list(s)
        for i, c in enumerate(ans):
            if c == '?':
                for cc in 'abc':
                    if i > 0 and ans[i - 1] == cc:
                        continue
                    if i < len(s) - 1 and ans[i + 1] == cc:
                        continue
                    ans[i] = cc
                    break
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            if (c == '?') {
                for (char cc = 'a'; cc <= 'c'; ++cc) {
                    if (i > 0 && chars[i - 1] == cc) {
                        continue;
                    }
                    if (i < chars.length - 1 && chars[i + 1] == cc) {
                        continue;
                    }
                    chars[i] = cc;
                    break;
                }
            }
        }
        return String.valueOf(chars);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string modifyString(string s) {
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == '?') {
                for (char cc : "abc") {
                    if (i > 0 && s[i - 1] == cc) continue;
                    if (i < s.size() - 1 && s[i + 1] == cc) continue;
                    s[i] = cc;
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
	ans := []byte(s)
	for i, c := range ans {
		if c == '?' {
			for cc := byte('a'); cc <= 'c'; cc++ {
				if i > 0 && ans[i-1] == cc {
					continue
				}
				if i < len(s)-1 && ans[i+1] == cc {
					continue
				}
				ans[i] = cc
				break
			}
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function modifyString(s: string): string {
    const strArr = s.split('');
    const n = s.length;
    for (let i = 0; i < n; i++) {
        if (strArr[i] === '?') {
            const before = strArr[i - 1];
            const after = strArr[i + 1];

            if (after !== 'a' && before !== 'a') {
                strArr[i] = 'a';
            } else if (after !== 'b' && before !== 'b') {
                strArr[i] = 'b';
            } else {
                strArr[i] = 'c';
            }
        }
    }
    return strArr.join('');
}
```

<!-- tabs:end -->
