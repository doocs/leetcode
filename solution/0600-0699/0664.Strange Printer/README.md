# [664. 奇怪的打印机](https://leetcode.cn/problems/strange-printer)

[English Version](/solution/0600-0699/0664.Strange%20Printer/README_EN.md)

<!-- tags:字符串,动态规划 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>有台奇怪的打印机有以下两个特殊要求：</p>

<ul>
	<li>打印机每次只能打印由 <strong>同一个字符</strong> 组成的序列。</li>
	<li>每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。</li>
</ul>

<p>给你一个字符串 <code>s</code> ，你的任务是计算这个打印机打印它需要的最少打印次数。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabbb"
<strong>输出：</strong>2
<strong>解释：</strong>首先打印 "aaa" 然后打印 "bbb"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aba"
<strong>输出：</strong>2
<strong>解释：</strong>首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示打印完成区间 $s[i..j]$ 的最少操作数，初始时 $f[i][j]=\infty$，答案为 $f[0][n-1]$，其中 $n$ 是字符串 $s$ 的长度。

考虑 $f[i][j]$，如果 $s[i] = s[j]$，那么我们在打印 $s[i]$ 时可以顺便打印 $s[j]$，这样我们即可忽略字符 $s[j]$，在区间 $s[i+1..j-1]$ 内继续进行打印。如果 $s[i] \neq s[j]$，那么我们需要分别完成该区间的打印，即使用 $s[i..k]$ 和 $s[k+1..j]$，其中 $k \in [i,j)$。于是我们可以列出如下的转移方程：

$$
f[i][j]=
\begin{cases}
1, & \text{if } i=j \\
f[i][j-1], & \text{if } s[i]=s[j] \\
\min_{i \leq k < j} \{f[i][k]+f[k+1][j]\}, & \text{otherwise}
\end{cases}
$$

在枚举时，我们可以从大到小枚举 $i$，从小到大枚举 $j$，这样可以保证在计算 $f[i][j]$ 时，状态 $f[i][j-1]$ 和 $f[i][k]$ 以及 $f[k+1][j]$ 都已经被计算过。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 是字符串 $s$ 的长度。

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
