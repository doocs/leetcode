# [2291. 最大股票收益 🔒](https://leetcode.cn/problems/maximum-profit-from-trading-stocks)

[English Version](/solution/2200-2299/2291.Maximum%20Profit%20From%20Trading%20Stocks/README_EN.md)

<!-- tags:数组,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示前 $i$ 支股票，预算为 $j$ 时的最大收益。那么答案就是 $f[n][budget]$。

对于第 $i$ 支股票，我们有两种选择：

-   不购买，那么 $f[i][j] = f[i - 1][j]$；
-   购买，那么 $f[i][j] = f[i - 1][j - present[i]] + future[i] - present[i]$。

最后返回 $f[n][budget]$ 即可。

时间复杂度 $O(n \times budget)$，空间复杂度 $O(n \times budget)$。其中 $n$ 为数组长度。

我们可以发现，对于每一行，我们只需要用到上一行的值，因此可以将空间复杂度优化到 $O(budget)$。

<!-- tabs:start -->

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

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def maximumProfit(self, present: List[int], future: List[int], budget: int) -> int:
        f = [0] * (budget + 1)
        for a, b in zip(present, future):
            for j in range(budget, a - 1, -1):
                f[j] = max(f[j], f[j - a] + b - a)
        return f[-1]
```

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

<!-- end -->
