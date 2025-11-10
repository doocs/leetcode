---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0474.Ones%20and%20Zeroes/README_EN.md
tags:
    - Array
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [474. Ones and Zeroes](https://leetcode.com/problems/ones-and-zeroes)

[中文文档](/solution/0400-0499/0474.Ones%20and%20Zeroes/README.md)

## Description

<!-- description:start -->

<p>You are given an array of binary strings <code>strs</code> and two integers <code>m</code> and <code>n</code>.</p>

<p>Return <em>the size of the largest subset of <code>strs</code> such that there are <strong>at most</strong> </em><code>m</code><em> </em><code>0</code><em>&#39;s and </em><code>n</code><em> </em><code>1</code><em>&#39;s in the subset</em>.</p>

<p>A set <code>x</code> is a <strong>subset</strong> of a set <code>y</code> if all elements of <code>x</code> are also elements of <code>y</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;10&quot;,&quot;0001&quot;,&quot;111001&quot;,&quot;1&quot;,&quot;0&quot;], m = 5, n = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The largest subset with at most 5 0&#39;s and 3 1&#39;s is {&quot;10&quot;, &quot;0001&quot;, &quot;1&quot;, &quot;0&quot;}, so the answer is 4.
Other valid but smaller subsets include {&quot;0001&quot;, &quot;1&quot;} and {&quot;10&quot;, &quot;1&quot;, &quot;0&quot;}.
{&quot;111001&quot;} is an invalid subset because it contains 4 1&#39;s, greater than the maximum of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;10&quot;,&quot;0&quot;,&quot;1&quot;], m = 1, n = 1
<strong>Output:</strong> 2
<b>Explanation:</b> The largest subset is {&quot;0&quot;, &quot;1&quot;}, so the answer is 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 600</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code> consists only of digits <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j][k]$ as the maximum number of strings that can be obtained from the first $i$ strings using $j$ zeros and $k$ ones. Initially, $f[i][j][k]=0$, and the answer is $f[sz][m][n]$, where $sz$ is the length of the array $strs$.

For $f[i][j][k]$, we have two choices:

-   Do not select the $i$-th string, in which case $f[i][j][k]=f[i-1][j][k]$;
-   Select the $i$-th string, in which case $f[i][j][k]=f[i-1][j-a][k-b]+1$, where $a$ and $b$ are the number of zeros and ones in the $i$-th string, respectively.

We take the maximum of these two choices to obtain the value of $f[i][j][k]$.

The final answer is $f[sz][m][n]$.

The time complexity is $O(sz \times m \times n)$, and the space complexity is $O(sz \times m \times n)$, where $sz$ is the length of the array $strs$, and $m$ and $n$ are the upper limits on the number of zeros and ones, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:
        sz = len(strs)
        f = [[[0] * (n + 1) for _ in range(m + 1)] for _ in range(sz + 1)]
        for i, s in enumerate(strs, 1):
            a, b = s.count("0"), s.count("1")
            for j in range(m + 1):
                for k in range(n + 1):
                    f[i][j][k] = f[i - 1][j][k]
                    if j >= a and k >= b:
                        f[i][j][k] = max(f[i][j][k], f[i - 1][j - a][k - b] + 1)
        return f[sz][m][n]
```

#### Java

```java
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int sz = strs.length;
        int[][][] f = new int[sz + 1][m + 1][n + 1];
        for (int i = 1; i <= sz; ++i) {
            int[] cnt = count(strs[i - 1]);
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= n; ++k) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= cnt[0] && k >= cnt[1]) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j - cnt[0]][k - cnt[1]] + 1);
                    }
                }
            }
        }
        return f[sz][m][n];
    }

    private int[] count(String s) {
        int[] cnt = new int[2];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - '0'];
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        int sz = strs.size();
        int f[sz + 1][m + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= sz; ++i) {
            auto [a, b] = count(strs[i - 1]);
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= n; ++k) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= a && k >= b) {
                        f[i][j][k] = max(f[i][j][k], f[i - 1][j - a][k - b] + 1);
                    }
                }
            }
        }
        return f[sz][m][n];
    }

    pair<int, int> count(string& s) {
        int a = count_if(s.begin(), s.end(), [](char c) { return c == '0'; });
        return {a, s.size() - a};
    }
};
```

#### Go

```go
func findMaxForm(strs []string, m int, n int) int {
	sz := len(strs)
	f := make([][][]int, sz+1)
	for i := range f {
		f[i] = make([][]int, m+1)
		for j := range f[i] {
			f[i][j] = make([]int, n+1)
		}
	}
	for i := 1; i <= sz; i++ {
		a, b := count(strs[i-1])
		for j := 0; j <= m; j++ {
			for k := 0; k <= n; k++ {
				f[i][j][k] = f[i-1][j][k]
				if j >= a && k >= b {
					f[i][j][k] = max(f[i][j][k], f[i-1][j-a][k-b]+1)
				}
			}
		}
	}
	return f[sz][m][n]
}

func count(s string) (int, int) {
	a := strings.Count(s, "0")
	return a, len(s) - a
}
```

#### TypeScript

```ts
function findMaxForm(strs: string[], m: number, n: number): number {
    const sz = strs.length;
    const f = Array.from({ length: sz + 1 }, () =>
        Array.from({ length: m + 1 }, () => Array.from({ length: n + 1 }, () => 0)),
    );
    const count = (s: string): [number, number] => {
        let a = 0;
        for (const c of s) {
            a += c === '0' ? 1 : 0;
        }
        return [a, s.length - a];
    };
    for (let i = 1; i <= sz; ++i) {
        const [a, b] = count(strs[i - 1]);
        for (let j = 0; j <= m; ++j) {
            for (let k = 0; k <= n; ++k) {
                f[i][j][k] = f[i - 1][j][k];
                if (j >= a && k >= b) {
                    f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j - a][k - b] + 1);
                }
            }
        }
    }
    return f[sz][m][n];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_max_form(strs: Vec<String>, m: i32, n: i32) -> i32 {
        let sz = strs.len();
        let m = m as usize;
        let n = n as usize;
        let mut f = vec![vec![vec![0; n + 1]; m + 1]; sz + 1];
        for i in 1..=sz {
            let a = strs[i - 1].chars().filter(|&c| c == '0').count();
            let b = strs[i - 1].len() - a;
            for j in 0..=m {
                for k in 0..=n {
                    f[i][j][k] = f[i - 1][j][k];
                    if j >= a && k >= b {
                        f[i][j][k] = f[i][j][k].max(f[i - 1][j - a][k - b] + 1);
                    }
                }
            }
        }
        f[sz][m][n] as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming (Space Optimization)

We notice that the calculation of $f[i][j][k]$ only depends on $f[i-1][j][k]$ and $f[i-1][j-a][k-b]$. Therefore, we can eliminate the first dimension and optimize the space complexity to $O(m \times n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for s in strs:
            a, b = s.count("0"), s.count("1")
            for i in range(m, a - 1, -1):
                for j in range(n, b - 1, -1):
                    f[i][j] = max(f[i][j], f[i - a][j - b] + 1)
        return f[m][n]
```

#### Java

```java
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] f = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] cnt = count(s);
            for (int i = m; i >= cnt[0]; --i) {
                for (int j = n; j >= cnt[1]; --j) {
                    f[i][j] = Math.max(f[i][j], f[i - cnt[0]][j - cnt[1]] + 1);
                }
            }
        }
        return f[m][n];
    }

    private int[] count(String s) {
        int[] cnt = new int[2];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - '0'];
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        int f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (auto& s : strs) {
            auto [a, b] = count(s);
            for (int i = m; i >= a; --i) {
                for (int j = n; j >= b; --j) {
                    f[i][j] = max(f[i][j], f[i - a][j - b] + 1);
                }
            }
        }
        return f[m][n];
    }

    pair<int, int> count(string& s) {
        int a = count_if(s.begin(), s.end(), [](char c) { return c == '0'; });
        return {a, s.size() - a};
    }
};
```

#### Go

```go
func findMaxForm(strs []string, m int, n int) int {
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for _, s := range strs {
		a, b := count(s)
		for j := m; j >= a; j-- {
			for k := n; k >= b; k-- {
				f[j][k] = max(f[j][k], f[j-a][k-b]+1)
			}
		}
	}
	return f[m][n]
}

func count(s string) (int, int) {
	a := strings.Count(s, "0")
	return a, len(s) - a
}
```

#### TypeScript

```ts
function findMaxForm(strs: string[], m: number, n: number): number {
    const f = Array.from({ length: m + 1 }, () => Array.from({ length: n + 1 }, () => 0));
    const count = (s: string): [number, number] => {
        let a = 0;
        for (const c of s) {
            a += c === '0' ? 1 : 0;
        }
        return [a, s.length - a];
    };
    for (const s of strs) {
        const [a, b] = count(s);
        for (let i = m; i >= a; --i) {
            for (let j = n; j >= b; --j) {
                f[i][j] = Math.max(f[i][j], f[i - a][j - b] + 1);
            }
        }
    }
    return f[m][n];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_max_form(strs: Vec<String>, m: i32, n: i32) -> i32 {
        let m = m as usize;
        let n = n as usize;
        let mut f = vec![vec![0; n + 1]; m + 1];

        for s in strs {
            let a = s.chars().filter(|&c| c == '0').count();
            let b = s.len() - a;
            for i in (a..=m).rev() {
                for j in (b..=n).rev() {
                    f[i][j] = f[i][j].max(f[i - a][j - b] + 1);
                }
            }
        }

        f[m][n]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
