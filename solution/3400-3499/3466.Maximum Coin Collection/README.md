---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3466.Maximum%20Coin%20Collection/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3466. 最大硬币收集量 🔒](https://leetcode.cn/problems/maximum-coin-collection)

[English Version](/solution/3400-3499/3466.Maximum%20Coin%20Collection/README_EN.md)

## 题目描述

<!-- description:start -->

<p>马里奥在双车道高速公路上行驶，每英里都有硬币。给定两个整数数组，<code>lane1</code> 和&nbsp;<code>lane2</code>，其中第&nbsp;<code>i</code>&nbsp;个下标的值表示他在车道上处于第&nbsp;<code>i</code>&nbsp;英里时获得或失去的硬币数量。</p>

<ul>
	<li>如果马里奥在车道 1 上处于&nbsp;<code>i</code> 英里处，并且&nbsp;<code>lane1[i] &gt; 0</code>，马里奥获得&nbsp;<code>lane1[i]</code> 硬币。</li>
	<li>如果马里奥在车道 1 上处于&nbsp;<code>i</code>&nbsp;英里处，并且&nbsp;<code>lane1[i] &lt; 0</code>，马里奥支付通行费并失去&nbsp;<code>abs(lane1[i])</code>&nbsp;个硬币。</li>
	<li>规则同样对&nbsp;<code>lane2</code>&nbsp;适用。</li>
</ul>

<p>马里奥可以在任何地方进入高速公路，并在行驶 <strong>至少</strong> 一英里后随时退出。马里奥总是从 1 号车道进入高速公路，但 <strong>最多</strong> 可以换道 2 次。</p>

<p><strong>换道</strong>&nbsp;是指马里奥从车道 1 换到车道 2，反之亦然。</p>

<p>返回马里奥在进行&nbsp;<strong>最多 2 次换道</strong>&nbsp;后&nbsp;<strong>最多</strong>&nbsp;可以获得的硬币数。</p>

<p><strong>注意：</strong>马里奥可以在进入高速公路或退出高速公路之前立即切换车道。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>lane1 = [1,-2,-10,3], lane2 = [-5,10,0,1]</span></p>

<p><span class="example-io"><b>输出：</b>14</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>马里奥在车道 1 上行驶了第 1 英里。</li>
	<li>接着，他切换到车道 2 并继续行驶 2 英里。</li>
	<li>最后 1 英里他切换回了车道 1。</li>
</ul>

<p>马里奥收集了&nbsp;<code>1 + 10 + 0 + 3 = 14</code> 硬币。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>lane1 = [1,-1,-1,-1], lane2 = [0,3,4,-5]</span></p>

<p><span class="example-io"><b>输出：</b>8</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>马里奥从 0 英里处进入车道 1 并行驶了 1 英里。</li>
	<li>接着，他切换到车道 2 并继续行驶了 2 英里。他在 3 英里处离开高速公路。</li>
</ul>

<p>他总共收集了&nbsp;<code>1 + 3 + 4 = 8</code> 硬币。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>lane1 = [-5,-4,-3], lane2 = [-1,2,3]</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>马里奥从 1 英里处进入并立即切换到车道 2。他全程保持在这根车道上。</li>
</ul>

<p>他总共收集了&nbsp;<code>2 + 3 = 5</code>&nbsp;硬币。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>lane1 = [-3,-3,-3], lane2 = [9,-2,4]</span></p>

<p><b>输出：</b>11</p>

<p><strong>解释：</strong></p>

<ul>
	<li>马里奥从高速公路的开头进入并立即切换到车道 2。他全程保持在这根车道上。</li>
</ul>

<p>他总共获得了&nbsp;<code>9 + (-2) + 4 = 11</code> 硬币。</p>
</div>

<p><strong class="example">示例 5：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>lane1 = [-10], lane2 = [-2]</span></p>

<p><span class="example-io"><b>输出：</b>-2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>由于马里奥必须在高速公路上行驶至少 1 英里，他只在车道 2 上行驶了 1 英里。</li>
</ul>

<p>他总共获得了 -2 硬币。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= lane1.length == lane2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= lane1[i], lane2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $\textit{dfs}(i, j, k)$，表示 Mario 从第 $i$ 个位置开始，当前在第 $j$ 条车道上，还可以换道 $k$ 次的情况下，最多可以获得的硬币数。那么答案就是对于所有的 $i$，取 $\textit{dfs}(i, 0, 2)$ 的最大值。

函数 $\textit{dfs}(i, j, k)$ 的计算方式如下：

- 如果 $i \geq n$，表示已经走到了终点，返回 0；
- 如果不变道，当前可以行驶 1 英里，然后驶出，或者继续行驶，取两者中的最大值，即 $\max(x, \textit{dfs}(i + 1, j, k) + x)$；
- 如果可以变道，有两种选择，一种是行驶 1 英里，然后变道，另一种是直接变道，取这两种情况的最大值，即 $\max(\textit{dfs}(i + 1, j \oplus 1, k - 1) + x, \textit{dfs}(i, j \oplus 1, k - 1))$。
- 其中 $x$ 表示当前位置的硬币数。

为了避免重复计算，我们使用记忆化搜索的方法，将已经计算过的结果保存下来。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 表示车道的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCoins(self, lane1: List[int], lane2: List[int]) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i >= n:
                return 0
            x = lane1[i] if j == 0 else lane2[i]
            ans = max(x, dfs(i + 1, j, k) + x)
            if k > 0:
                ans = max(ans, dfs(i + 1, j ^ 1, k - 1) + x)
                ans = max(ans, dfs(i, j ^ 1, k - 1))
            return ans

        n = len(lane1)
        ans = -inf
        for i in range(n):
            ans = max(ans, dfs(i, 0, 2))
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private int[] lane1;
    private int[] lane2;
    private Long[][][] f;

    public long maxCoins(int[] lane1, int[] lane2) {
        n = lane1.length;
        this.lane1 = lane1;
        this.lane2 = lane2;
        f = new Long[n][2][3];
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dfs(i, 0, 2));
        }
        return ans;
    }

    private long dfs(int i, int j, int k) {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int x = j == 0 ? lane1[i] : lane2[i];
        long ans = Math.max(x, dfs(i + 1, j, k) + x);
        if (k > 0) {
            ans = Math.max(ans, dfs(i + 1, j ^ 1, k - 1) + x);
            ans = Math.max(ans, dfs(i, j ^ 1, k - 1));
        }
        return f[i][j][k] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxCoins(vector<int>& lane1, vector<int>& lane2) {
        int n = lane1.size();
        long long ans = -1e18;
        vector<vector<vector<long long>>> f(n, vector<vector<long long>>(2, vector<long long>(3, -1e18)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> long long {
            if (i >= n) {
                return 0LL;
            }
            if (f[i][j][k] != -1e18) {
                return f[i][j][k];
            }
            int x = j == 0 ? lane1[i] : lane2[i];
            long long ans = max((long long) x, dfs(i + 1, j, k) + x);
            if (k > 0) {
                ans = max(ans, dfs(i + 1, j ^ 1, k - 1) + x);
                ans = max(ans, dfs(i, j ^ 1, k - 1));
            }
            return f[i][j][k] = ans;
        };
        for (int i = 0; i < n; ++i) {
            ans = max(ans, dfs(i, 0, 2));
        }
        return ans;
    }
};
```

#### Go

```go
func maxCoins(lane1 []int, lane2 []int) int64 {
	n := len(lane1)
	f := make([][2][3]int64, n)
	for i := range f {
		for j := range f[i] {
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var dfs func(int, int, int) int64
	dfs = func(i, j, k int) int64 {
		if i >= n {
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		x := int64(lane1[i])
		if j == 1 {
			x = int64(lane2[i])
		}
		ans := max(x, dfs(i+1, j, k)+x)
		if k > 0 {
			ans = max(ans, dfs(i+1, j^1, k-1)+x)
			ans = max(ans, dfs(i, j^1, k-1))
		}
		f[i][j][k] = ans
		return ans
	}
	ans := int64(-1e18)
	for i := range lane1 {
		ans = max(ans, dfs(i, 0, 2))
	}
	return ans
}
```

#### TypeScript

```ts
function maxCoins(lane1: number[], lane2: number[]): number {
    const n = lane1.length;
    const NEG_INF = -1e18;
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: 2 }, () => Array(3).fill(NEG_INF)),
    );
    const dfs = (dfs: Function, i: number, j: number, k: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] !== NEG_INF) {
            return f[i][j][k];
        }
        const x = j === 0 ? lane1[i] : lane2[i];
        let ans = Math.max(x, dfs(dfs, i + 1, j, k) + x);
        if (k > 0) {
            ans = Math.max(ans, dfs(dfs, i + 1, j ^ 1, k - 1) + x);
            ans = Math.max(ans, dfs(dfs, i, j ^ 1, k - 1));
        }
        f[i][j][k] = ans;
        return ans;
    };
    let ans = NEG_INF;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, dfs(dfs, i, 0, 2));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
