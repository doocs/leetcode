# [903. DI 序列的有效排列](https://leetcode.cn/problems/valid-permutations-for-di-sequence)

[English Version](/solution/0900-0999/0903.Valid%20Permutations%20for%20DI%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为 <code>n</code> 的字符串 <code>s</code> ，其中 <code>s[i]</code> 是:</p>

<ul>
	<li><code>“D”</code> 意味着减少，或者</li>
	<li><code>“I”</code> 意味着增加</li>
</ul>

<p><strong>有效排列</strong>&nbsp;是对有&nbsp;<code>n + 1</code>&nbsp;个在&nbsp;<code>[0, n]</code>&nbsp; 范围内的整数的一个排列&nbsp;<code>perm</code>&nbsp;，使得对所有的&nbsp;<code>i</code>：</p>

<ul>
	<li>如果 <code>s[i] == 'D'</code>，那么&nbsp;<code>perm[i] &gt; perm[i+1]</code>，以及；</li>
	<li>如果 <code>s[i] == 'I'</code>，那么 <code>perm[i] &lt; perm[i+1]</code>。</li>
</ul>

<p>返回 <em><strong>有效排列 </strong>&nbsp;</em><code>perm</code><em>的数量 </em>。因为答案可能很大，所以请<strong>返回你的答案对</strong>&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code><strong>&nbsp;取余</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "DID"
<strong>输出：</strong>5
<strong>解释：</strong>
(0, 1, 2, 3) 的五个有效排列是：
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "D"
<strong>输出:</strong> 1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>s[i]</code>&nbsp;不是&nbsp;<code>'I'</code>&nbsp;就是&nbsp;<code>'D'</code></li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示字符串的前 $i$ 个字符中，以数字 $j$ 结尾的满足题目要求的排列的数量。初始时 $f[0][0]=1$，其余 $f[0][j]=0$。答案为 $\sum_{j=0}^n f[n][j]$。

考虑 $f[i][j]$，其中 $j \in [0, i]$。

如果第 $i$ 个字符 $s[i-1]$ 是 `'D'`，那么 $f[i][j]$ 可以从 $f[i-1][k]$ 转移而来，其中 $k \in [j+1, i]$，而由于 $k-1$ 最大只能为 $i-1$，我们将 $k$ 向左移动一位，那么 $k \in [j, i-1]$，因此有 $f[i][j] = \sum_{k=j}^{i-1} f[i-1][k]$。

如果第 $i$ 个字符 $s[i-1]$ 是 `'I'`，那么 $f[i][j]$ 可以从 $f[i-1][k]$ 转移而来，其中 $k \in [0, j-1]$，因此有 $f[i][j] = \sum_{k=0}^{j-1} f[i-1][k]$。

最终的答案即为 $\sum_{j=0}^n f[n][j]$。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 是字符串的长度。

我们可以用前缀和优化时间复杂度，使得时间复杂度降低到 $O(n^2)$。另外，我们也可以用滚动数组优化空间复杂度，使得空间复杂度降低到 $O(n)$。

<!-- tabs:start -->

```python
class Solution:
    def numPermsDISequence(self, s: str) -> int:
        mod = 10**9 + 7
        n = len(s)
        f = [[0] * (n + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i, c in enumerate(s, 1):
            if c == "D":
                for j in range(i + 1):
                    for k in range(j, i):
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod
            else:
                for j in range(i + 1):
                    for k in range(j):
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod
        return sum(f[n][j] for j in range(n + 1)) % mod
```

```java
class Solution {
    public int numPermsDISequence(String s) {
        final int mod = (int) 1e9 + 7;
        int n = s.length();
        int[][] f = new int[n + 1][n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) == 'D') {
                for (int j = 0; j <= i; ++j) {
                    for (int k = j; k < i; ++k) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                    }
                }
            } else {
                for (int j = 0; j <= i; ++j) {
                    for (int k = 0; k < j; ++k) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= n; ++j) {
            ans = (ans + f[n][j]) % mod;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numPermsDISequence(string s) {
        const int mod = 1e9 + 7;
        int n = s.size();
        int f[n + 1][n + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] == 'D') {
                for (int j = 0; j <= i; ++j) {
                    for (int k = j; k < i; ++k) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                    }
                }
            } else {
                for (int j = 0; j <= i; ++j) {
                    for (int k = 0; k < j; ++k) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= n; ++j) {
            ans = (ans + f[n][j]) % mod;
        }
        return ans;
    }
};
```

```go
func numPermsDISequence(s string) (ans int) {
	const mod = 1e9 + 7
	n := len(s)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		if s[i-1] == 'D' {
			for j := 0; j <= i; j++ {
				for k := j; k < i; k++ {
					f[i][j] = (f[i][j] + f[i-1][k]) % mod
				}
			}
		} else {
			for j := 0; j <= i; j++ {
				for k := 0; k < j; k++ {
					f[i][j] = (f[i][j] + f[i-1][k]) % mod
				}
			}
		}
	}
	for j := 0; j <= n; j++ {
		ans = (ans + f[n][j]) % mod
	}
	return
}
```

```ts
function numPermsDISequence(s: string): number {
    const n = s.length;
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    f[0][0] = 1;
    const mod = 10 ** 9 + 7;
    for (let i = 1; i <= n; ++i) {
        if (s[i - 1] === 'D') {
            for (let j = 0; j <= i; ++j) {
                for (let k = j; k < i; ++k) {
                    f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                }
            }
        } else {
            for (let j = 0; j <= i; ++j) {
                for (let k = 0; k < j; ++k) {
                    f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                }
            }
        }
    }
    let ans = 0;
    for (let j = 0; j <= n; ++j) {
        ans = (ans + f[n][j]) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def numPermsDISequence(self, s: str) -> int:
        mod = 10**9 + 7
        n = len(s)
        f = [[0] * (n + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i, c in enumerate(s, 1):
            pre = 0
            if c == "D":
                for j in range(i, -1, -1):
                    pre = (pre + f[i - 1][j]) % mod
                    f[i][j] = pre
            else:
                for j in range(i + 1):
                    f[i][j] = pre
                    pre = (pre + f[i - 1][j]) % mod
        return sum(f[n][j] for j in range(n + 1)) % mod
```

```java
class Solution {
    public int numPermsDISequence(String s) {
        final int mod = (int) 1e9 + 7;
        int n = s.length();
        int[][] f = new int[n + 1][n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int pre = 0;
            if (s.charAt(i - 1) == 'D') {
                for (int j = i; j >= 0; --j) {
                    pre = (pre + f[i - 1][j]) % mod;
                    f[i][j] = pre;
                }
            } else {
                for (int j = 0; j <= i; ++j) {
                    f[i][j] = pre;
                    pre = (pre + f[i - 1][j]) % mod;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= n; ++j) {
            ans = (ans + f[n][j]) % mod;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numPermsDISequence(string s) {
        const int mod = 1e9 + 7;
        int n = s.size();
        int f[n + 1][n + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            int pre = 0;
            if (s[i - 1] == 'D') {
                for (int j = i; j >= 0; --j) {
                    pre = (pre + f[i - 1][j]) % mod;
                    f[i][j] = pre;
                }
            } else {
                for (int j = 0; j <= i; ++j) {
                    f[i][j] = pre;
                    pre = (pre + f[i - 1][j]) % mod;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= n; ++j) {
            ans = (ans + f[n][j]) % mod;
        }
        return ans;
    }
};
```

```go
func numPermsDISequence(s string) (ans int) {
	const mod = 1e9 + 7
	n := len(s)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		pre := 0
		if s[i-1] == 'D' {
			for j := i; j >= 0; j-- {
				pre = (pre + f[i-1][j]) % mod
				f[i][j] = pre
			}
		} else {
			for j := 0; j <= i; j++ {
				f[i][j] = pre
				pre = (pre + f[i-1][j]) % mod
			}
		}
	}
	for j := 0; j <= n; j++ {
		ans = (ans + f[n][j]) % mod
	}
	return
}
```

```ts
function numPermsDISequence(s: string): number {
    const n = s.length;
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    f[0][0] = 1;
    const mod = 10 ** 9 + 7;
    for (let i = 1; i <= n; ++i) {
        let pre = 0;
        if (s[i - 1] === 'D') {
            for (let j = i; j >= 0; --j) {
                pre = (pre + f[i - 1][j]) % mod;
                f[i][j] = pre;
            }
        } else {
            for (let j = 0; j <= i; ++j) {
                f[i][j] = pre;
                pre = (pre + f[i - 1][j]) % mod;
            }
        }
    }
    let ans = 0;
    for (let j = 0; j <= n; ++j) {
        ans = (ans + f[n][j]) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法三

<!-- tabs:start -->

```python
class Solution:
    def numPermsDISequence(self, s: str) -> int:
        mod = 10**9 + 7
        n = len(s)
        f = [1] + [0] * n
        for i, c in enumerate(s, 1):
            pre = 0
            g = [0] * (n + 1)
            if c == "D":
                for j in range(i, -1, -1):
                    pre = (pre + f[j]) % mod
                    g[j] = pre
            else:
                for j in range(i + 1):
                    g[j] = pre
                    pre = (pre + f[j]) % mod
            f = g
        return sum(f) % mod
```

```java
class Solution {
    public int numPermsDISequence(String s) {
        final int mod = (int) 1e9 + 7;
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int pre = 0;
            int[] g = new int[n + 1];
            if (s.charAt(i - 1) == 'D') {
                for (int j = i; j >= 0; --j) {
                    pre = (pre + f[j]) % mod;
                    g[j] = pre;
                }
            } else {
                for (int j = 0; j <= i; ++j) {
                    g[j] = pre;
                    pre = (pre + f[j]) % mod;
                }
            }
            f = g;
        }
        int ans = 0;
        for (int j = 0; j <= n; ++j) {
            ans = (ans + f[j]) % mod;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numPermsDISequence(string s) {
        const int mod = 1e9 + 7;
        int n = s.size();
        vector<int> f(n + 1);
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int pre = 0;
            vector<int> g(n + 1);
            if (s[i - 1] == 'D') {
                for (int j = i; j >= 0; --j) {
                    pre = (pre + f[j]) % mod;
                    g[j] = pre;
                }
            } else {
                for (int j = 0; j <= i; ++j) {
                    g[j] = pre;
                    pre = (pre + f[j]) % mod;
                }
            }
            f = move(g);
        }
        int ans = 0;
        for (int j = 0; j <= n; ++j) {
            ans = (ans + f[j]) % mod;
        }
        return ans;
    }
};
```

```go
func numPermsDISequence(s string) (ans int) {
	const mod = 1e9 + 7
	n := len(s)
	f := make([]int, n+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		pre := 0
		g := make([]int, n+1)
		if s[i-1] == 'D' {
			for j := i; j >= 0; j-- {
				pre = (pre + f[j]) % mod
				g[j] = pre
			}
		} else {
			for j := 0; j <= i; j++ {
				g[j] = pre
				pre = (pre + f[j]) % mod
			}
		}
		f = g
	}
	for j := 0; j <= n; j++ {
		ans = (ans + f[j]) % mod
	}
	return
}
```

```ts
function numPermsDISequence(s: string): number {
    const n = s.length;
    let f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    const mod = 10 ** 9 + 7;
    for (let i = 1; i <= n; ++i) {
        let pre = 0;
        const g: number[] = Array(n + 1).fill(0);
        if (s[i - 1] === 'D') {
            for (let j = i; j >= 0; --j) {
                pre = (pre + f[j]) % mod;
                g[j] = pre;
            }
        } else {
            for (let j = 0; j <= i; ++j) {
                g[j] = pre;
                pre = (pre + f[j]) % mod;
            }
        }
        f = g;
    }
    let ans = 0;
    for (let j = 0; j <= n; ++j) {
        ans = (ans + f[j]) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
