# [899. Orderly Queue](https://leetcode.com/problems/orderly-queue)

[中文文档](/solution/0800-0899/0899.Orderly%20Queue/README.md)

## Description

<p>You are given a string <code>s</code> and an integer <code>k</code>. You can choose one of the first <code>k</code> letters of <code>s</code> and append it at the end of the string..</p>

<p>Return <em>the lexicographically smallest string you could have after applying the mentioned step any number of moves</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cba&quot;, k = 1
<strong>Output:</strong> &quot;acb&quot;
<strong>Explanation:</strong> 
In the first move, we move the 1<sup>st</sup> character &#39;c&#39; to the end, obtaining the string &quot;bac&quot;.
In the second move, we move the 1<sup>st</sup> character &#39;b&#39; to the end, obtaining the final result &quot;acb&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;baaca&quot;, k = 3
<strong>Output:</strong> &quot;aaabc&quot;
<strong>Explanation:</strong> 
In the first move, we move the 1<sup>st</sup> character &#39;b&#39; to the end, obtaining the string &quot;aacab&quot;.
In the second move, we move the 3<sup>rd</sup> character &#39;c&#39; to the end, obtaining the final result &quot;aaabc&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def orderlyQueue(self, s: str, k: int) -> str:
        if k == 1:
            ans = s
            for _ in range(len(s) - 1):
                s = s[1:] + s[0]
                ans = min(ans, s)
            return ans
        return "".join(sorted(s))
```

### **Java**

```java
class Solution {
    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            String ans = s;
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < s.length() - 1; ++i) {
                sb.append(sb.charAt(0)).deleteCharAt(0);
                if (sb.toString().compareTo(ans) < 0) {
                    ans = sb.toString();
                }
            }
            return ans;
        }
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        return String.valueOf(cs);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string orderlyQueue(string s, int k) {
        if (k == 1) {
            string ans = s;
            for (int i = 0; i < s.size() - 1; ++i) {
                s = s.substr(1) + s[0];
                if (s < ans) ans = s;
            }
            return ans;
        }
        sort(s.begin(), s.end());
        return s;
    }
};
```

### **Go**

```go
func orderlyQueue(s string, k int) string {
	if k == 1 {
		ans := s
		for i := 0; i < len(s)-1; i++ {
			s = s[1:] + s[:1]
			if s < ans {
				ans = s
			}
		}
		return ans
	}
	t := []byte(s)
	sort.Slice(t, func(i, j int) bool { return t[i] < t[j] })
	return string(t)
}
```

### **TypeScript**

```ts
function orderlyQueue(s: string, k: number): string {
    if (k > 1) {
        return [...s].sort().join('');
    }
    const n = s.length;
    let min = s;
    for (let i = 1; i < n; i++) {
        const t = s.slice(i) + s.slice(0, i);
        if (t < min) {
            min = t;
        }
    }
    return min;
}
```

### **...**

```

```

<!-- tabs:end -->
