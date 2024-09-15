---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3290.Maximum%20Multiplication%20Score/README.md
---

<!-- problem:start -->

# [3290. 最高乘法得分](https://leetcode.cn/problems/maximum-multiplication-score)

[English Version](/solution/3200-3299/3290.Maximum%20Multiplication%20Score/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 4 的整数数组 <code>a</code> 和一个大小 <strong>至少</strong>为 4 的整数数组 <code>b</code>。</p>

<p>你需要从数组 <code>b</code> 中选择四个下标 <code>i<sub>0</sub></code>, <code>i<sub>1</sub></code>, <code>i<sub>2</sub></code>, 和 <code>i<sub>3</sub></code>，并满足 <code>i<sub>0</sub> &lt; i<sub>1</sub> &lt; i<sub>2</sub> &lt; i<sub>3</sub></code>。你的得分将是 <code>a[0] * b[i<sub>0</sub>] + a[1] * b[i<sub>1</sub>] + a[2] * b[i<sub>2</sub>] + a[3] * b[i<sub>3</sub>]</code> 的值。</p>

<p>返回你能够获得的 <strong>最大 </strong>得分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = [3,2,5,6], b = [2,-6,4,-5,-3,2,-7]</span></p>

<p><strong>输出：</strong> <span class="example-io">26</span></p>

<p><strong>解释：</strong><br />
选择下标 0, 1, 2 和 5。得分为 <code>3 * 2 + 2 * (-6) + 5 * 4 + 6 * 2 = 26</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">a = [-1,4,5,-2], b = [-5,-1,-3,-2,-4]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong><br />
选择下标 0, 1, 3 和 4。得分为 <code>(-1) * (-5) + 4 * (-1) + 5 * (-2) + (-2) * (-4) = -1</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>a.length == 4</code></li>
	<li><code>4 &lt;= b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= a[i], b[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $\textit{dfs}(i, j)$，表示从数组 $a$ 的第 $i$ 个元素开始，从数组 $b$ 的第 $j$ 个元素开始，能够获得的最大得分。那么答案就是 $\textit{dfs}(0, 0)$。

函数 $\textit{dfs}(i, j)$ 的计算方式如下：

-   如果 $j \geq \text{len}(b)$，表示数组 $b$ 已经遍历完了，此时如果数组 $a$ 也遍历完了，返回 $0$，否则返回负无穷；
-   如果 $i \geq \text{len}(a)$，表示数组 $a$ 已经遍历完了，返回 $0$；
-   否则，我们可以不选择数组 $b$ 的第 $j$ 个元素，直接跳到下一个元素，即 $\textit{dfs}(i, j + 1)$；也可以选择数组 $b$ 的第 $j$ 个元素，此时得分为 $a[i] \times b[j]$，再加上 $\textit{dfs}(i + 1, j + 1)$。我们取这两者的最大值作为 $\textit{dfs}(i, j)$ 的返回值。

我们可以使用记忆化搜索的方式，避免重复计算。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为数组 $a$ 和 $b$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, a: List[int], b: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if j >= len(b):
                return 0 if i >= len(a) else -inf
            if i >= len(a):
                return 0
            return max(dfs(i, j + 1), a[i] * b[j] + dfs(i + 1, j + 1))

        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Long[][] f;
    private int[] a;
    private int[] b;

    public long maxScore(int[] a, int[] b) {
        f = new Long[a.length][b.length];
        this.a = a;
        this.b = b;
        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        if (j >= b.length) {
            return i >= a.length ? 0 : Long.MIN_VALUE / 2;
        }
        if (i >= a.length) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        return f[i][j] = Math.max(dfs(i, j + 1), 1L * a[i] * b[j] + dfs(i + 1, j + 1));
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxScore(vector<int>& a, vector<int>& b) {
        int m = a.size(), n = b.size();
        long long f[m][n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int j) -> long long {
            if (j >= n) {
                return i >= m ? 0 : LLONG_MIN / 2;
            }
            if (i >= m) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            return f[i][j] = max(dfs(dfs, i, j + 1), 1LL * a[i] * b[j] + dfs(dfs, i + 1, j + 1));
        };
        return dfs(dfs, 0, 0);
    }
};
```

#### Go

```go
func maxScore(a []int, b []int) int64 {
	m, n := len(a), len(b)
	f := make([][]int64, m)
	for i := range f {
		f[i] = make([]int64, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int64
	dfs = func(i, j int) int64 {
		if j >= n {
			if i >= m {
				return 0
			}
			return math.MinInt64 / 2
		}
		if i >= m {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		f[i][j] = max(dfs(i, j+1), int64(a[i])*int64(b[j])+dfs(i+1, j+1))
		return f[i][j]
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function maxScore(a: number[], b: number[]): number {
    const [m, n] = [a.length, b.length];
    const f: number[][] = Array.from({ length: m }, () => Array(n).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (j >= n) {
            return i >= m ? 0 : -Infinity;
        }
        if (i >= m) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        return (f[i][j] = Math.max(dfs(i, j + 1), a[i] * b[j] + dfs(i + 1, j + 1)));
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
