---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0474.Ones%20and%20Zeroes/README.md
tags:
    - 数组
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [474. 一和零](https://leetcode.cn/problems/ones-and-zeroes)

[English Version](/solution/0400-0499/0474.Ones%20and%20Zeroes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制字符串数组 <code>strs</code> 和两个整数 <code>m</code> 和 <code>n</code> 。</p>

<div class="MachineTrans-Lines">
<p class="MachineTrans-lang-zh-CN">请你找出并返回 <code>strs</code> 的最大子集的长度，该子集中 <strong>最多</strong> 有 <code>m</code> 个 <code>0</code> 和 <code>n</code> 个 <code>1</code> 。</p>

<p class="MachineTrans-lang-zh-CN">如果 <code>x</code> 的所有元素也是 <code>y</code> 的元素，集合 <code>x</code> 是集合 <code>y</code> 的 <strong>子集</strong> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
<strong>输出：</strong>4
<strong>解释：</strong>最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["10", "0", "1"], m = 1, n = 1
<strong>输出：</strong>2
<strong>解释：</strong>最大的子集是 {"0", "1"} ，所以答案是 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 600</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code>&nbsp;仅由&nbsp;<code>'0'</code> 和&nbsp;<code>'1'</code> 组成</li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j][k]$ 表示在前 $i$ 个字符串中，使用 $j$ 个 0 和 $k$ 个 1 的情况下最多可以得到的字符串数量。初始时 $f[i][j][k]=0$，答案为 $f[sz][m][n]$，其中 $sz$ 是数组 $strs$ 的长度。

对于 $f[i][j][k]$，我们有两种决策：

-   不选第 $i$ 个字符串，此时 $f[i][j][k]=f[i-1][j][k]$；
-   选第 $i$ 个字符串，此时 $f[i][j][k]=f[i-1][j-a][k-b]+1$，其中 $a$ 和 $b$ 分别是第 $i$ 个字符串中 $0$ 和 $1$ 的数量。

我们取两种决策中的最大值，即可得到 $f[i][j][k]$ 的值。

最终的答案即为 $f[sz][m][n]$。

时间复杂度 $O(sz \times m \times n)$，空间复杂度 $O(sz \times m \times n)$。其中 $sz$ 是数组 $strs$ 的长度；而 $m$ 和 $n$ 分别是字符串中 $0$ 和 $1$ 的数量上限。

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

### 方法二：动态规划（空间优化）

我们注意到 $f[i][j][k]$ 的计算只和 $f[i-1][j][k]$ 以及 $f[i-1][j-a][k-b]$ 有关，因此我们可以去掉第一维，将空间复杂度优化到 $O(m \times n)$。

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
