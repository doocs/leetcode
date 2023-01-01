# [2522. Partition String Into Substrings With Values at Most K](https://leetcode.com/problems/partition-string-into-substrings-with-values-at-most-k)

[中文文档](/solution/2500-2599/2522.Partition%20String%20Into%20Substrings%20With%20Values%20at%20Most%20K/README.md)

## Description

<p>You are given a string <code>s</code> consisting of digits from <code>1</code> to <code>9</code> and an integer <code>k</code>.</p>

<p>A partition of a string <code>s</code> is called <strong>good</strong> if:</p>

<ul>
	<li>Each digit of <code>s</code> is part of <strong>exactly</strong> one substring.</li>
	<li>The value of each substring is less than or equal to <code>k</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of substrings in a <strong>good</strong> partition of</em> <code>s</code>. If no <strong>good</strong> partition of <code>s</code> exists, return <code>-1</code>.</p>

<p><b>Note</b> that:</p>

<ul>
	<li>The <strong>value</strong> of a string is its result when interpreted as an integer. For example, the value of <code>&quot;123&quot;</code> is <code>123</code> and the value of <code>&quot;1&quot;</code> is <code>1</code>.</li>
	<li>A <strong>substring</strong> is a contiguous sequence of characters within a string.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;165462&quot;, k = 60
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can partition the string into substrings &quot;16&quot;, &quot;54&quot;, &quot;6&quot;, and &quot;2&quot;. Each substring has a value less than or equal to k = 60.
It can be shown that we cannot partition the string into less than 4 substrings.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;238182&quot;, k = 5
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no good partition for this string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is a digit from <code>&#39;1&#39;</code> to <code>&#39;9&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<style type="text/css">.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; 
}
.spoiler {overflow:hidden;}
.spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;}
.spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
.spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
</style>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumPartition(self, s: str, k: int) -> int:
        @cache
        def dfs(i):
            if i >= n:
                return 0
            res, v = inf, 0
            for j in range(i, n):
                v = v * 10 + int(s[j])
                if v > k:
                    break
                res = min(res, dfs(j + 1))
            return res + 1

        n = len(s)
        ans = dfs(0)
        return ans if ans < inf else -1
```

### **Java**

```java
class Solution {
    private Integer[] f;
    private int n;
    private String s;
    private int k;
    private int inf = 1 << 30;

    public int minimumPartition(String s, int k) {
        n = s.length();
        f = new Integer[n];
        this.s = s;
        this.k = k;
        int ans = dfs(0);
        return ans < inf ? ans : -1;
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int res = inf;
        long v = 0;
        for (int j = i; j < n; ++j) {
            v = v * 10 + (s.charAt(j) - '0');
            if (v > k) {
                break;
            }
            res = Math.min(res, dfs(j + 1));
        }
        return f[i] = res + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumPartition(string s, int k) {
        int n = s.size();
        int f[n];
        memset(f, 0, sizeof f);
        const int inf = 1 << 30;
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) return 0;
            if (f[i]) return f[i];
            int res = inf;
            long v = 0;
            for (int j = i; j < n; ++j) {
                v = v * 10 + (s[j] - '0');
                if (v > k) break;
                res = min(res, dfs(j + 1));
            }
            return f[i] = res + 1;
        };
        int ans = dfs(0);
        return ans < inf ? ans : -1;
    }
};
```

### **Go**

```go
func minimumPartition(s string, k int) int {
	n := len(s)
	f := make([]int, n)
	const inf int = 1 << 30
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		res, v := inf, 0
		for j := i; j < n; j++ {
			v = v*10 + int(s[j]-'0')
			if v > k {
				break
			}
			res = min(res, dfs(j+1))
		}
		f[i] = res + 1
		return f[i]
	}
	ans := dfs(0)
	if ans < inf {
		return ans
	}
	return -1
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
