# [1781. Sum of Beauty of All Substrings](https://leetcode.com/problems/sum-of-beauty-of-all-substrings)

[中文文档](/solution/1700-1799/1781.Sum%20of%20Beauty%20of%20All%20Substrings/README.md)

## Description

<p>The <strong>beauty</strong> of a string is the difference in frequencies between the most frequent and least frequent characters.</p>

<ul>
	<li>For example, the beauty of <code>&quot;abaacc&quot;</code> is <code>3 - 1 = 2</code>.</li>
</ul>

<p>Given a string <code>s</code>, return <em>the sum of <strong>beauty</strong> of all of its substrings.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabcb&quot;
<strong>Output:</strong> 5
<strong>Explanation: </strong>The substrings with non-zero beauty are [&quot;aab&quot;,&quot;aabc&quot;,&quot;aabcb&quot;,&quot;abcb&quot;,&quot;bcb&quot;], each with beauty equal to 1.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabcbaa&quot;
<strong>Output:</strong> 17
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;=<sup> </sup>500</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def beautySum(self, s: str) -> int:
        ans, n = 0, len(s)
        for i in range(n):
            counter = Counter()
            for j in range(i, n):
                counter[s[j]] += 1
                t = [v for v in counter.values() if v]
                ans += max(t) - min(t)
        return ans
```

### **Java**

```java
class Solution {
    public int beautySum(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int[] counter = new int[26];
            for (int j = i; j < n; ++j) {
                ++counter[s.charAt(j) - 'a'];
                int mi = 1000;
                int mx = 0;
                for (int v : counter) {
                    if (v > 0) {
                        mi = Math.min(mi, v);
                        mx = Math.max(mx, v);
                    }
                }
                ans += mx - mi;
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
    int beautySum(string s) {
        int ans = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            vector<int> counter(26);
            for (int j = i; j < n; ++j) {
                ++counter[s[j] - 'a'];
                int mi = 1000;
                int mx = 0;
                for (int v : counter) {
                    if (v) {
                        mi = min(mi, v);
                        mx = max(mx, v);
                    }
                }
                ans += mx - mi;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func beautySum(s string) int {
	ans, n := 0, len(s)
	for i := 0; i < n; i++ {
		counter := make([]int, 26)
		for j := i; j < n; j++ {
			counter[s[j]-'a']++
			mi, mx := 1000, 0
			for _, v := range counter {
				if v > 0 {
					mi = min(mi, v)
					mx = max(mx, v)
				}
			}
			ans += mx - mi
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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
