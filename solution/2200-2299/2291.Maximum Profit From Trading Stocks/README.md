---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2291.Maximum%20Profit%20From%20Trading%20Stocks/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [2291. 最大股票收益 🔒](https://leetcode.cn/problems/maximum-profit-from-trading-stocks)

[English Version](/solution/2200-2299/2291.Maximum%20Profit%20From%20Trading%20Stocks/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的数组 <code>present</code> 和 <code>future</code> ，<code>present[i]</code> 和 <code>future[i]</code> 分别代表第 <code>i</code> 支股票现在和将来的价格。每支股票你最多购买 <strong>一次</strong> ，你的预算为 <code>budget</code> 。</p>

<p>求最大的收益。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>present = [5,4,6,2,3], future = [8,5,4,3,5], budget = 10
<strong>输出：</strong>6
<strong>解释：</strong>你可以选择购买第 0,3,4 支股票获得最大收益：6 。总开销为：5 + 2 + 3 = 10 , 总收益是: 8 + 3 + 5 - 10 = 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>present = [2,2,5], future = [3,4,10], budget = 6
<strong>输出：</strong>5
<strong>解释：</strong>你可以选择购买第 2 支股票获得最大收益：5 。总开销为：5 , 总收益是: 10 - 5 = 5 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>present = [3,3,12], future = [0,3,15], budget = 10
<strong>输出：</strong>0
<strong>解释：</strong>你无法购买唯一一支正收益股票 2 ，因此你的收益是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == present.length == future.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= present[i], future[i] &lt;= 100</code></li>
	<li><code>0 &lt;= budget &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 每支股票至多买一次，花费为现价，收益为未来价减现价，预算有限。$n$ 与预算均为 $10^3$，是典型的 $0$-$1$ 背包：物品体积为现价，价值为利润（仅当利润为正时才值得买）。
>
> 令 $f[i][j]$ 为前 $i$ 支股票、预算 $j$ 的最大利润。不买则继承上一行；买则要求 $j\ge present[i]$ 且未来价更高。答案为 $f[n][\textit{budget}]$。

<!-- thinking:end -->

我们定义 $f[i][j]$ 表示前 $i$ 支股票，预算为 $j$ 时的最大收益。那么答案就是 $f[n][\textit{budget}]$。

对于第 $i$ 支股票，我们有两种选择：

- 不购买，那么 $f[i][j] = f[i - 1][j]$；
- 购买，那么 $f[i][j] = f[i - 1][j - \textit{present}[i]] + \textit{future}[i] - \textit{present}[i]$。

最后返回 $f[n][\textit{budget}]$ 即可。

时间复杂度 $O(n \times \textit{budget})$，空间复杂度 $O(n \times \textit{budget})$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumProfit(self, present: List[int], future: List[int], budget: int) -> int:
        f = [[0] * (budget + 1) for _ in range(len(present) + 1)]
        for i, w in enumerate(present, 1):
            for j in range(budget + 1):
                f[i][j] = f[i - 1][j]
                if j >= w and future[i - 1] > w:
                    f[i][j] = max(f[i][j], f[i - 1][j - w] + future[i - 1] - w)
        return f[-1][-1]
```

#### Java

```java
class Solution {
    public int maximumProfit(int[] present, int[] future, int budget) {
        int n = present.length;
        int[][] f = new int[n + 1][budget + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= budget; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= present[i - 1]) {
                    f[i][j] = Math.max(
                        f[i][j], f[i - 1][j - present[i - 1]] + future[i - 1] - present[i - 1]);
                }
            }
        }
        return f[n][budget];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumProfit(vector<int>& present, vector<int>& future, int budget) {
        int n = present.size();
        int f[n + 1][budget + 1];
        memset(f, 0, sizeof f);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= budget; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= present[i - 1]) {
                    f[i][j] = max(f[i][j], f[i - 1][j - present[i - 1]] + future[i - 1] - present[i - 1]);
                }
            }
        }
        return f[n][budget];
    }
};
```

#### Go

```go
func maximumProfit(present []int, future []int, budget int) int {
	n := len(present)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, budget+1)
	}
	for i := 1; i <= n; i++ {
		for j := 0; j <= budget; j++ {
			f[i][j] = f[i-1][j]
			if j >= present[i-1] {
				f[i][j] = max(f[i][j], f[i-1][j-present[i-1]]+future[i-1]-present[i-1])
			}
		}
	}
	return f[n][budget]
}
```

#### TypeScript

```ts
function maximumProfit(present: number[], future: number[], budget: number): number {
    const f = new Array(budget + 1).fill(0);
    for (let i = 0; i < present.length; ++i) {
        const [a, b] = [present[i], future[i]];
        for (let j = budget; j >= a; --j) {
            f[j] = Math.max(f[j], f[j - a] + b - a);
        }
    }
    return f[budget];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划（空间优化）

<!-- thinking:start -->

> **思考**
>
> 方法一的转移只读上一行，二维表可以压成一维。为避免同一支股票被买两次，预算 $j$ 必须从大到小枚举。时间不变，空间降为 $O(\textit{budget})$。

<!-- thinking:end -->

我们可以发现，对于每一行，我们只需要用到上一行的值，因此可以将空间复杂度优化到 $O(\text{budget})$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumProfit(self, present: List[int], future: List[int], budget: int) -> int:
        f = [0] * (budget + 1)
        for a, b in zip(present, future):
            for j in range(budget, a - 1, -1):
                f[j] = max(f[j], f[j - a] + b - a)
        return f[-1]
```

#### Java

```java
class Solution {
    public int maximumProfit(int[] present, int[] future, int budget) {
        int n = present.length;
        int[] f = new int[budget + 1];
        for (int i = 0; i < n; ++i) {
            int a = present[i], b = future[i];
            for (int j = budget; j >= a; --j) {
                f[j] = Math.max(f[j], f[j - a] + b - a);
            }
        }
        return f[budget];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumProfit(vector<int>& present, vector<int>& future, int budget) {
        int n = present.size();
        int f[budget + 1];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) {
            int a = present[i], b = future[i];
            for (int j = budget; j >= a; --j) {
                f[j] = max(f[j], f[j - a] + b - a);
            }
        }
        return f[budget];
    }
};
```

#### Go

```go
func maximumProfit(present []int, future []int, budget int) int {
	f := make([]int, budget+1)
	for i, a := range present {
		for j := budget; j >= a; j-- {
			f[j] = max(f[j], f[j-a]+future[i]-a)
		}
	}
	return f[budget]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
