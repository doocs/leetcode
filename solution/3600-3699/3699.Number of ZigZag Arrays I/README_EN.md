---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3699.Number%20of%20ZigZag%20Arrays%20I/README_EN.md
rating: 2123
source: Weekly Contest 469 Q3
---

<!-- problem:start -->

# [3699. Number of ZigZag Arrays I](https://leetcode.com/problems/number-of-zigzag-arrays-i)

[中文文档](/solution/3600-3699/3699.Number%20of%20ZigZag%20Arrays%20I/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>l</code>, and <code>r</code>.</p>

<p>A <strong>ZigZag</strong> array of length <code>n</code> is defined as follows:</p>

<ul>
	<li>Each element lies in the range <code>[l, r]</code>.</li>
	<li>No <strong>two</strong> adjacent elements are equal.</li>
	<li>No <strong>three</strong> consecutive elements form a <strong>strictly increasing</strong> or <strong>strictly decreasing</strong> sequence.</li>
</ul>

<p>Return the total number of valid <strong>ZigZag</strong> arrays.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>sequence</strong> is said to be <strong>strictly increasing</strong> if each element is strictly greater than its previous one (if exists).</p>

<p>A <strong>sequence</strong> is said to be <strong>strictly decreasing</strong> if each element is strictly smaller than its previous one (if exists).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, l = 4, r = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>There are only 2 valid ZigZag arrays of length <code>n = 3</code> using values in the range <code>[4, 5]</code>:</p>

<ul>
	<li><code>[4, 5, 4]</code></li>
	<li><code>[5, 4, 5]</code>​​​​​​​</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, l = 1, r = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 10 valid ZigZag arrays of length <code>n = 3</code> using values in the range <code>[1, 3]</code>:</p>

<ul>
	<li><code>[1, 2, 1]</code>, <code>[1, 3, 1]</code>, <code>[1, 3, 2]</code></li>
	<li><code>[2, 1, 2]</code>, <code>[2, 1, 3]</code>, <code>[2, 3, 1]</code>, <code>[2, 3, 2]</code></li>
	<li><code>[3, 1, 2]</code>, <code>[3, 1, 3]</code>, <code>[3, 2, 3]</code></li>
</ul>

<p>All arrays meet the ZigZag conditions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 2000</code></li>
	<li><code>1 &lt;= l &lt; r &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

Let $m = r - l + 1$ and map the range $[l, r]$ to $[0, m - 1]$.

Let $up[i]$ be the number of arrays of the current length that end with $i$ whose last step is an increase, and $down[i]$ the number whose last step is a decrease. For length $1$ there is no direction, so initialize $up[i] = down[i] = 1$.

Transitions:

- If the array ends at $i$ with a decrease, the previous value must be greater than $i$ and the previous step must be an increase: $down'[i] = \sum_{j > i} up[j]$;
- If the last step is an increase: $up'[i] = \sum_{j < i} down[j]$.

Prefix and suffix sums make each transition $O(m)$. Repeat $n - 1$ times. The answer is the sum of all $up[i] + down[i]$.

The time complexity is $O(n \times m)$, and the space complexity is $O(m)$, where $n$ is the array length and $m$ is the size of the value range.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        mod = 10**9 + 7
        m = r - l + 1
        up = [1] * m
        down = [1] * m
        for _ in range(n - 1):
            pre = [0] * (m + 1)
            suf = [0] * (m + 1)
            for i in range(m):
                pre[i + 1] = (pre[i] + down[i]) % mod
            for i in range(m - 1, -1, -1):
                suf[i] = (suf[i + 1] + up[i]) % mod
            up = pre[:m]
            down = suf[1:]
        return sum(up + down) % mod
```

#### Java

```java
class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final int mod = (int) 1e9 + 7;
        int m = r - l + 1;
        long[] up = new long[m];
        long[] down = new long[m];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);
        for (int k = 1; k < n; ++k) {
            long[] pre = new long[m + 1];
            long[] suf = new long[m + 1];
            for (int i = 0; i < m; ++i) {
                pre[i + 1] = (pre[i] + down[i]) % mod;
            }
            for (int i = m - 1; i >= 0; --i) {
                suf[i] = (suf[i + 1] + up[i]) % mod;
            }
            for (int i = 0; i < m; ++i) {
                up[i] = pre[i];
                down[i] = suf[i + 1];
            }
        }
        long ans = 0;
        for (int i = 0; i < m; ++i) {
            ans = (ans + up[i] + down[i]) % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int zigZagArrays(int n, int l, int r) {
        const int mod = 1e9 + 7;
        int m = r - l + 1;
        vector<int> up(m, 1), down(m, 1);
        for (int k = 1; k < n; ++k) {
            vector<int> pre(m + 1), suf(m + 1);
            for (int i = 0; i < m; ++i) {
                pre[i + 1] = (pre[i] + down[i]) % mod;
            }
            for (int i = m - 1; i >= 0; --i) {
                suf[i] = (suf[i + 1] + up[i]) % mod;
            }
            for (int i = 0; i < m; ++i) {
                up[i] = pre[i];
                down[i] = suf[i + 1];
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            ans = (ans + up[i] + down[i]) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func zigZagArrays(n int, l int, r int) (ans int) {
	const mod = int(1e9 + 7)
	m := r - l + 1
	up := make([]int, m)
	down := make([]int, m)
	for i := range up {
		up[i], down[i] = 1, 1
	}
	for k := 1; k < n; k++ {
		pre := make([]int, m+1)
		suf := make([]int, m+1)
		for i := 0; i < m; i++ {
			pre[i+1] = (pre[i] + down[i]) % mod
		}
		for i := m - 1; i >= 0; i-- {
			suf[i] = (suf[i+1] + up[i]) % mod
		}
		for i := 0; i < m; i++ {
			up[i] = pre[i]
			down[i] = suf[i+1]
		}
	}
	for i := 0; i < m; i++ {
		ans = (ans + up[i] + down[i]) % mod
	}
	return
}
```

#### C

```c
int zigZagArrays(int n, int l, int r) {
    int mod = 1e9 + 7;
    int m = r - l + 1;
    int up[m], down[m];
    for (int i = 0; i < m; ++i) {
        up[i] = down[i] = 1;
    }
    for (int k = 1; k < n; ++k) {
        int pre[m + 1], suf[m + 1];
        memset(pre, 0, sizeof(pre));
        memset(suf, 0, sizeof(suf));
        for (int i = 0; i < m; ++i) {
            pre[i + 1] = (pre[i] + down[i]) % mod;
        }
        for (int i = m - 1; i >= 0; --i) {
            suf[i] = (suf[i + 1] + up[i]) % mod;
        }
        for (int i = 0; i < m; ++i) {
            up[i] = pre[i];
            down[i] = suf[i + 1];
        }
    }
    int ans = 0;
    for (int i = 0; i < m; ++i) {
        ans = (ans + up[i] + down[i]) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
