# [2272. Substring With Largest Variance](https://leetcode.com/problems/substring-with-largest-variance)

[中文文档](/solution/2200-2299/2272.Substring%20With%20Largest%20Variance/README.md)

## Description

<p>The <strong>variance</strong> of a string is defined as the largest difference between the number of occurrences of <strong>any</strong> <code>2</code> characters present in the string. Note the two characters may or may not be the same.</p>

<p>Given a string <code>s</code> consisting of lowercase English letters only, return <em>the <strong>largest variance</strong> possible among all <strong>substrings</strong> of</em> <code>s</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aababbb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong>
All possible variances along with their respective substrings are listed below:
- Variance 0 for substrings &quot;a&quot;, &quot;aa&quot;, &quot;ab&quot;, &quot;abab&quot;, &quot;aababb&quot;, &quot;ba&quot;, &quot;b&quot;, &quot;bb&quot;, and &quot;bbb&quot;.
- Variance 1 for substrings &quot;aab&quot;, &quot;aba&quot;, &quot;abb&quot;, &quot;aabab&quot;, &quot;ababb&quot;, &quot;aababbb&quot;, and &quot;bab&quot;.
- Variance 2 for substrings &quot;aaba&quot;, &quot;ababbb&quot;, &quot;abbb&quot;, and &quot;babb&quot;.
- Variance 3 for substring &quot;babbb&quot;.
Since the largest possible variance is 3, we return it.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcde&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong>
No letter occurs more than once in s, so the variance of every substring is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestVariance(self, s: str) -> int:
        ans = 0
        for a, b in permutations(ascii_lowercase, 2):
            if a == b:
                continue
            f = [0, -inf]
            for c in s:
                if c == a:
                    f[0], f[1] = f[0] + 1, f[1] + 1
                elif c == b:
                    f[1] = max(f[1] - 1, f[0] - 1)
                    f[0] = 0
                if ans < f[1]:
                    ans = f[1]
        return ans
```

### **Java**

```java
class Solution {
    public int largestVariance(String s) {
        int n = s.length();
        int ans = 0;
        for (char a = 'a'; a <= 'z'; ++a) {
            for (char b = 'a'; b <= 'z'; ++b) {
                if (a == b) {
                    continue;
                }
                int[] f = new int[] {0, -n};
                for (int i = 0; i < n; ++i) {
                    if (s.charAt(i) == a) {
                        f[0]++;
                        f[1]++;
                    } else if (s.charAt(i) == b) {
                        f[1] = Math.max(f[0] - 1, f[1] - 1);
                        f[0] = 0;
                    }
                    ans = Math.max(ans, f[1]);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestVariance(string s) {
        int n = s.size();
        int ans = 0;
        for (char a = 'a'; a <= 'z'; ++a) {
            for (char b = 'a'; b <= 'z'; ++b) {
                if (a == b) continue;
                int f[2] = {0, -n};
                for (char c : s) {
                    if (c == a) {
                        f[0]++;
                        f[1]++;
                    } else if (c == b) {
                        f[1] = max(f[1] - 1, f[0] - 1);
                        f[0] = 0;
                    }
                    ans = max(ans, f[1]);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func largestVariance(s string) int {
	ans, n := 0, len(s)
	for a := 'a'; a <= 'z'; a++ {
		for b := 'a'; b <= 'z'; b++ {
			if a == b {
				continue
			}
			f := [2]int{0, -n}
			for _, c := range s {
				if c == a {
					f[0]++
					f[1]++
				} else if c == b {
					f[1] = max(f[1]-1, f[0]-1)
					f[0] = 0
				}
				ans = max(ans, f[1])
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
