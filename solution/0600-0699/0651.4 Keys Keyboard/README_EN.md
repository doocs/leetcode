# [651. 4 Keys Keyboard](https://leetcode.com/problems/4-keys-keyboard)

[中文文档](/solution/0600-0699/0651.4%20Keys%20Keyboard/README.md)

<!-- tags:Math,Dynamic Programming -->

## Description

<p>Imagine you have a special keyboard with the following keys:</p>

<ul>
	<li>A: Print one <code>&#39;A&#39;</code> on the screen.</li>
	<li>Ctrl-A: Select the whole screen.</li>
	<li>Ctrl-C: Copy selection to buffer.</li>
	<li>Ctrl-V: Print buffer on screen appending it after what has already been printed.</li>
</ul>

<p>Given an integer n, return <em>the maximum number of </em><code>&#39;A&#39;</code><em> you can print on the screen with <strong>at most</strong> </em><code>n</code><em> presses on the keys</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can at most get 3 A&#39;s on screen by pressing the following key sequence:
A, A, A
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> 9
<strong>Explanation:</strong> We can at most get 9 A&#39;s on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def maxA(self, n: int) -> int:
        dp = list(range(n + 1))
        for i in range(3, n + 1):
            for j in range(2, i - 1):
                dp[i] = max(dp[i], dp[j - 1] * (i - j))
        return dp[-1]
```

```java
class Solution {
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            dp[i] = i;
        }
        for (int i = 3; i < n + 1; ++i) {
            for (int j = 2; j < i - 1; ++j) {
                dp[i] = Math.max(dp[i], dp[j - 1] * (i - j));
            }
        }
        return dp[n];
    }
}
```

```cpp
class Solution {
public:
    int maxA(int n) {
        vector<int> dp(n + 1);
        iota(dp.begin(), dp.end(), 0);
        for (int i = 3; i < n + 1; ++i) {
            for (int j = 2; j < i - 1; ++j) {
                dp[i] = max(dp[i], dp[j - 1] * (i - j));
            }
        }
        return dp[n];
    }
};
```

```go
func maxA(n int) int {
	dp := make([]int, n+1)
	for i := range dp {
		dp[i] = i
	}
	for i := 3; i < n+1; i++ {
		for j := 2; j < i-1; j++ {
			dp[i] = max(dp[i], dp[j-1]*(i-j))
		}
	}
	return dp[n]
}
```

<!-- tabs:end -->

<!-- end -->
