# [926. Flip String to Monotone Increasing](https://leetcode.com/problems/flip-string-to-monotone-increasing)

[中文文档](/solution/0900-0999/0926.Flip%20String%20to%20Monotone%20Increasing/README.md)

## Description

<p>A binary string is monotone increasing if it consists of some number of <code>0</code>&#39;s (possibly none), followed by some number of <code>1</code>&#39;s (also possibly none).</p>

<p>You are given a binary string <code>s</code>. You can flip <code>s[i]</code> changing it from <code>0</code> to <code>1</code> or from <code>1</code> to <code>0</code>.</p>

<p>Return <em>the minimum number of flips to make </em><code>s</code><em> monotone increasing</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00110&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We flip the last digit to get 00111.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;010110&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We flip to get 011111, or alternatively 000111.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00011000&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We flip to get 00000000.
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
    def minFlipsMonoIncr(self, s: str) -> int:
        n = len(s)
        left, right = [0] * (n + 1), [0] * (n + 1)
        ans = 0x3F3F3F3F
        for i in range(1, n + 1):
            left[i] = left[i - 1] + (1 if s[i - 1] == '1' else 0)
        for i in range(n - 1, -1, -1):
            right[i] = right[i + 1] + (1 if s[i] == '0' else 0)
        for i in range(0, n + 1):
            ans = min(ans, left[i] + right[i])
        return ans
```

```python
class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        n = len(s)
        presum = [0] * (n + 1)
        for i, c in enumerate(s):
            presum[i + 1] = presum[i] + int(c)
        ans = presum[-1]
        for i in range(n):
            ans = min(ans, presum[i] + n - i - (presum[-1] - presum[i]))
        return ans
```

### **Java**

```java
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            left[i] = left[i - 1] + (s.charAt(i - 1) == '1' ? 1 : 0);
        }
        for (int i = n - 1; i >= 0; i--) {
            right[i] = right[i + 1] + (s.charAt(i) == '0' ? 1 : 0);
        }
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, left[i] + right[i]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + (s.charAt(i) - '0');
        }
        int ans = presum[n];
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, presum[i] + n - i - (presum[n] - presum[i]));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minFlipsMonoIncr(string s) {
        int n = s.size();
        vector<int> left(n + 1, 0), right(n + 1, 0);
        int ans = INT_MAX;
        for (int i = 1; i <= n; ++i) {
            left[i] = left[i - 1] + (s[i - 1] == '1');
        }
        for (int i = n - 1; i >= 0; --i) {
            right[i] = right[i + 1] + (s[i] == '0');
        }
        for (int i = 0; i <= n; i++) {
            ans = min(ans, left[i] + right[i]);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int minFlipsMonoIncr(string s) {
        int n = s.size();
        vector<int> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + (s[i] == '1');
        int ans = presum[n];
        for (int i = 0; i < n; ++i) ans = min(ans, presum[i] + n - i - (presum[n] - presum[i]));
        return ans;
    }
};
```

### **Go**

```go
func minFlipsMonoIncr(s string) int {
	n := len(s)
	left, right := make([]int, n+1), make([]int, n+1)
	ans := math.MaxInt32
	for i := 1; i <= n; i++ {
		left[i] = left[i-1]
		if s[i-1] == '1' {
			left[i]++
		}
	}
	for i := n - 1; i >= 0; i-- {
		right[i] = right[i+1]
		if s[i] == '0' {
			right[i]++
		}
	}
	for i := 0; i <= n; i++ {
		ans = min(ans, left[i]+right[i])
	}
	return ans
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

```go
func minFlipsMonoIncr(s string) int {
	n := len(s)
	presum := make([]int, n+1)
	for i, c := range s {
		presum[i+1] = presum[i] + int(c-'0')
	}
	ans := presum[n]
	for i := range s {
		ans = min(ans, presum[i]+n-i-(presum[n]-presum[i]))
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var minFlipsMonoIncr = function (s) {
    const n = s.length;
    let presum = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        presum[i + 1] = presum[i] + (s[i] == '1');
    }
    let ans = presum[n];
    for (let i = 0; i < n; ++i) {
        ans = Math.min(ans, presum[i] + n - i - (presum[n] - presum[i]));
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
