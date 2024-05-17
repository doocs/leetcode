---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1545.Find%20Kth%20Bit%20in%20Nth%20Binary%20String/README_EN.md
rating: 1479
source: Weekly Contest 201 Q2
tags:
    - Recursion
    - String
    - Simulation
---

<!-- problem:start -->

# [1545. Find Kth Bit in Nth Binary String](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string)

[中文文档](/solution/1500-1599/1545.Find%20Kth%20Bit%20in%20Nth%20Binary%20String/README.md)

## Description

<!-- description:start -->

<p>Given two positive integers <code>n</code> and <code>k</code>, the binary string <code>S<sub>n</sub></code> is formed as follows:</p>

<ul>
	<li><code>S<sub>1</sub> = &quot;0&quot;</code></li>
	<li><code>S<sub>i</sub> = S<sub>i - 1</sub> + &quot;1&quot; + reverse(invert(S<sub>i - 1</sub>))</code> for <code>i &gt; 1</code></li>
</ul>

<p>Where <code>+</code> denotes the concatenation operation, <code>reverse(x)</code> returns the reversed string <code>x</code>, and <code>invert(x)</code> inverts all the bits in <code>x</code> (<code>0</code> changes to <code>1</code> and <code>1</code> changes to <code>0</code>).</p>

<p>For example, the first four strings in the above sequence are:</p>

<ul>
	<li><code>S<sub>1 </sub>= &quot;0&quot;</code></li>
	<li><code>S<sub>2 </sub>= &quot;0<strong>1</strong>1&quot;</code></li>
	<li><code>S<sub>3 </sub>= &quot;011<strong>1</strong>001&quot;</code></li>
	<li><code>S<sub>4</sub> = &quot;0111001<strong>1</strong>0110001&quot;</code></li>
</ul>

<p>Return <em>the</em> <code>k<sup>th</sup></code> <em>bit</em> <em>in</em> <code>S<sub>n</sub></code>. It is guaranteed that <code>k</code> is valid for the given <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 1
<strong>Output:</strong> &quot;0&quot;
<strong>Explanation:</strong> S<sub>3</sub> is &quot;<strong><u>0</u></strong>111001&quot;.
The 1<sup>st</sup> bit is &quot;0&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, k = 11
<strong>Output:</strong> &quot;1&quot;
<strong>Explanation:</strong> S<sub>4</sub> is &quot;0111001101<strong><u>1</u></strong>0001&quot;.
The 11<sup>th</sup> bit is &quot;1&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= k &lt;= 2<sup>n</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Analysis + Recursion

We can observe that for $S_n$, the first half is the same as $S_{n-1}$, and the second half is the reverse and negation of $S_{n-1}$. Therefore, we can design a function $dfs(n, k)$, which represents the $k$-th character of the $n$-th string. The answer is $dfs(n, k)$.

The calculation process of the function $dfs(n, k)$ is as follows:

-   If $k = 1$, then the answer is $0$;
-   If $k$ is a power of $2$, then the answer is $1$;
-   If $k \times 2 < 2^n - 1$, it means that $k$ is in the first half, and the answer is $dfs(n - 1, k)$;
-   Otherwise, the answer is $dfs(n - 1, 2^n - k) \oplus 1$, where $\oplus$ represents the XOR operation.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the given $n$ in the problem.

<!-- tabs:start -->

```python
class Solution:
    def findKthBit(self, n: int, k: int) -> str:
        def dfs(n: int, k: int) -> int:
            if k == 1:
                return 0
            if (k & (k - 1)) == 0:
                return 1
            m = 1 << n
            if k * 2 < m - 1:
                return dfs(n - 1, k)
            return dfs(n - 1, m - k) ^ 1

        return str(dfs(n, k))
```

```java
class Solution {
    public char findKthBit(int n, int k) {
        return (char) ('0' + dfs(n, k));
    }

    private int dfs(int n, int k) {
        if (k == 1) {
            return 0;
        }
        if ((k & (k - 1)) == 0) {
            return 1;
        }
        int m = 1 << n;
        if (k * 2 < m - 1) {
            return dfs(n - 1, k);
        }
        return dfs(n - 1, m - k) ^ 1;
    }
}
```

```cpp
class Solution {
public:
    char findKthBit(int n, int k) {
        function<int(int, int)> dfs = [&](int n, int k) {
            if (k == 1) {
                return 0;
            }
            if ((k & (k - 1)) == 0) {
                return 1;
            }
            int m = 1 << n;
            if (k * 2 < m - 1) {
                return dfs(n - 1, k);
            }
            return dfs(n - 1, m - k) ^ 1;
        };
        return '0' + dfs(n, k);
    }
};
```

```go
func findKthBit(n int, k int) byte {
	var dfs func(n, k int) int
	dfs = func(n, k int) int {
		if k == 1 {
			return 0
		}
		if k&(k-1) == 0 {
			return 1
		}
		m := 1 << n
		if k*2 < m-1 {
			return dfs(n-1, k)
		}
		return dfs(n-1, m-k) ^ 1
	}
	return byte('0' + dfs(n, k))
}
```

```ts
function findKthBit(n: number, k: number): string {
    const dfs = (n: number, k: number): number => {
        if (k === 1) {
            return 0;
        }
        if ((k & (k - 1)) === 0) {
            return 1;
        }
        const m = 1 << n;
        if (k * 2 < m - 1) {
            return dfs(n - 1, k);
        }
        return dfs(n - 1, m - k) ^ 1;
    };
    return dfs(n, k).toString();
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
