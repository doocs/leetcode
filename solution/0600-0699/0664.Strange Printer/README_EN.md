# [664. Strange Printer](https://leetcode.com/problems/strange-printer)

[中文文档](/solution/0600-0699/0664.Strange%20Printer/README.md)

## Description

<p>There is a strange printer with the following two special properties:</p>

<ul>
	<li>The printer can only print a sequence of <strong>the same character</strong> each time.</li>
	<li>At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.</li>
</ul>

<p>Given a string <code>s</code>, return <em>the minimum number of turns the printer needed to print it</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabbb&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Print &quot;aaa&quot; first and then print &quot;bbb&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aba&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Print &quot;aaa&quot; first and then print &quot;b&quot; from the second place of the string, which will cover the existing character &#39;a&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the minimum operations to print $s[i..j]$, with the initial value $f[i][j]=\infty$, and the answer is $f[0][n-1]$, where $n$ is the length of string $s$.

Consider $f[i][j]$, if $s[i] = s[j]$, we can print $s[j]$ when print $s[i]$, so we can ignore $s[j]$ and continue to print $s[i+1..j-1]$. If $s[i] \neq s[j]$, we need to print the substring separately, i.e. $s[i..k]$ and $s[k+1..j]$, where $k \in [i,j)$. So we can have the following transition equation:

$$
f[i][j]=
\begin{cases}
1, & \text{if } i=j \\
f[i][j-1], & \text{if } s[i]=s[j] \\
\min_{i \leq k < j} \{f[i][k]+f[k+1][j]\}, & \text{otherwise}
\end{cases}
$$

We can enumerate $i$ from large to small and $j$ from small to large, so that we can ensure that $f[i][j-1]$, $f[i][k]$ and $f[k+1][j]$ have been calculated when we calculate $f[i][j]$.

The time complexity is $O(n^3)$ and the space complexity is $O(n^2)$. Where $n$ is the length of string $s$.

<!-- tabs:start -->

```python
class Solution:
    def strangePrinter(self, s: str) -> int:
        n = len(s)
        f = [[inf] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            f[i][i] = 1
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    f[i][j] = f[i][j - 1]
                else:
                    for k in range(i, j):
                        f[i][j] = min(f[i][j], f[i][k] + f[k + 1][j])
        return f[0][-1]
```

```java
class Solution {
    public int strangePrinter(String s) {
        final int inf = 1 << 30;
        int n = s.length();
        int[][] f = new int[n][n];
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        for (int i = n - 1; i >= 0; --i) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i][j - 1];
                } else {
                    for (int k = i; k < j; ++k) {
                        f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j]);
                    }
                }
            }
        }
        return f[0][n - 1];
    }
}
```

```cpp
class Solution {
public:
    int strangePrinter(string s) {
        int n = s.size();
        int f[n][n];
        memset(f, 0x3f, sizeof(f));
        for (int i = n - 1; ~i; --i) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i][j - 1];
                } else {
                    for (int k = i; k < j; ++k) {
                        f[i][j] = min(f[i][j], f[i][k] + f[k + 1][j]);
                    }
                }
            }
        }
        return f[0][n - 1];
    }
};
```

```go
func strangePrinter(s string) int {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = 1 << 30
		}
	}
	for i := n - 1; i >= 0; i-- {
		f[i][i] = 1
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				f[i][j] = f[i][j-1]
			} else {
				for k := i; k < j; k++ {
					f[i][j] = min(f[i][j], f[i][k]+f[k+1][j])
				}
			}
		}
	}
	return f[0][n-1]
}
```

```ts
function strangePrinter(s: string): number {
    const n = s.length;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(1 << 30));
    for (let i = n - 1; i >= 0; --i) {
        f[i][i] = 1;
        for (let j = i + 1; j < n; ++j) {
            if (s[i] === s[j]) {
                f[i][j] = f[i][j - 1];
            } else {
                for (let k = i; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j]);
                }
            }
        }
    }
    return f[0][n - 1];
}
```

<!-- tabs:end -->

<!-- end -->
