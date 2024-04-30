# [903. Valid Permutations for DI Sequence](https://leetcode.com/problems/valid-permutations-for-di-sequence)

[中文文档](/solution/0900-0999/0903.Valid%20Permutations%20for%20DI%20Sequence/README.md)

<!-- tags:String,Dynamic Programming,Prefix Sum -->

## Description

<p>You are given a string <code>s</code> of length <code>n</code> where <code>s[i]</code> is either:</p>

<ul>
	<li><code>&#39;D&#39;</code> means decreasing, or</li>
	<li><code>&#39;I&#39;</code> means increasing.</li>
</ul>

<p>A permutation <code>perm</code> of <code>n + 1</code> integers of all the integers in the range <code>[0, n]</code> is called a <strong>valid permutation</strong> if for all valid <code>i</code>:</p>

<ul>
	<li>If <code>s[i] == &#39;D&#39;</code>, then <code>perm[i] &gt; perm[i + 1]</code>, and</li>
	<li>If <code>s[i] == &#39;I&#39;</code>, then <code>perm[i] &lt; perm[i + 1]</code>.</li>
</ul>

<p>Return <em>the number of <strong>valid permutations</strong> </em><code>perm</code>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;DID&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The 5 valid permutations of (0, 1, 2, 3) are:
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;D&quot;
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>s[i]</code> is either <code>&#39;I&#39;</code> or <code>&#39;D&#39;</code>.</li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the number of permutations that satisfy the problem's requirements with the first $i$ characters of the string ending with the number $j$. Initially, $f[0][0]=1$, and the rest $f[0][j]=0$. The answer is $\sum_{j=0}^n f[n][j]$.

Consider $f[i][j]$, where $j \in [0, i]$.

If the $i$th character $s[i-1]$ is `'D'`, then $f[i][j]$ can be transferred from $f[i-1][k]$, where $k \in [j+1, i]$. Since $k-1$ can only be up to $i-1$, we move $k$ one place to the left, so $k \in [j, i-1]$. Therefore, we have $f[i][j] = \sum_{k=j}^{i-1} f[i-1][k]$.

If the $i$th character $s[i-1]$ is `'I'`, then $f[i][j]$ can be transferred from $f[i-1][k]$, where $k \in [0, j-1]$. Therefore, we have $f[i][j] = \sum_{k=0}^{j-1} f[i-1][k]$.

The final answer is $\sum_{j=0}^n f[n][j]$.

The time complexity is $O(n^3)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the string.

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

We can optimize the time complexity to $O(n^2)$ using prefix sums.

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

Additionally, we can optimize the space complexity to $O(n)$ using a rolling array.

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
