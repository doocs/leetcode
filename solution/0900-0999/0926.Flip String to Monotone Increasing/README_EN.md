# [926. Flip String to Monotone Increasing](https://leetcode.com/problems/flip-string-to-monotone-increasing)

[中文文档](/solution/0900-0999/0926.Flip%20String%20to%20Monotone%20Increasing/README.md)

## Description

<p>A string of <code>&#39;0&#39;</code>s and <code>&#39;1&#39;</code>s is <em>monotone increasing</em> if it consists of some number of <code>&#39;0&#39;</code>s (possibly 0), followed by some number of <code>&#39;1&#39;</code>s (also possibly 0.)</p>

<p>We are given a string <code>S</code> of <code>&#39;0&#39;</code>s and <code>&#39;1&#39;</code>s, and we may flip any <code>&#39;0&#39;</code> to a <code>&#39;1&#39;</code> or a <code>&#39;1&#39;</code> to a <code>&#39;0&#39;</code>.</p>

<p>Return the minimum number of flips to make <code>S</code>&nbsp;monotone increasing.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;00110&quot;</span>

<strong>Output: </strong><span id="example-output-1">1</span>

<strong>Explanation: </strong>We flip the last digit to get 00111.

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">&quot;010110&quot;</span>

<strong>Output: </strong><span id="example-output-2">2</span>

<strong>Explanation: </strong>We flip to get 011111, or alternatively 000111.

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">&quot;00011000&quot;</span>

<strong>Output: </strong><span id="example-output-3">2</span>

<strong>Explanation: </strong>We flip to get 00000000.

</pre>

<p>&nbsp;</p>

<p><strong><span>Note:</span></strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 20000</code></li>
	<li><code>S</code> only consists of <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code> characters.</li>
</ol>

</div>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        n = len(s)
        left, right = [0] * (n + 1), [0] * (n + 1)
        ans = 0x3f3f3f3f
        for i in range(1, n + 1):
            left[i] = left[i - 1] + (1 if s[i - 1] == '1' else 0)
        for i in range(n - 1, -1, -1):
            right[i] = right[i + 1] + (1 if s[i] == '0' else 0)
        for i in range(0, n + 1):
            ans = min(ans, left[i] + right[i])
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

### **...**

```

```

<!-- tabs:end -->
