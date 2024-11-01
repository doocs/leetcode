---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3259.Maximum%20Energy%20Boost%20From%20Two%20Drinks/README.md
rating: 1483
source: 第 411 场周赛 Q2
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3259. 超级饮料的最大强化能量](https://leetcode.cn/problems/maximum-energy-boost-from-two-drinks)

[English Version](/solution/3200-3299/3259.Maximum%20Energy%20Boost%20From%20Two%20Drinks/README_EN.md)

## 题目描述

<!-- description:start -->

<p>来自未来的体育科学家给你两个整数数组 <code>energyDrinkA</code> 和 <code>energyDrinkB</code>，数组长度都等于 <code>n</code>。这两个数组分别代表 A、B 两种不同能量饮料每小时所能提供的强化能量。</p>

<p>你需要每小时饮用一种能量饮料来 <strong>最大化 </strong>你的总强化能量。然而，如果从一种能量饮料切换到另一种，你需要等待一小时来梳理身体的能量体系（在那个小时里你将不会获得任何强化能量）。</p>

<p>返回在接下来的 <code>n</code> 小时内你能获得的<strong> 最大 </strong>总强化能量。</p>

<p><strong>注意 </strong>你可以选择从饮用任意一种能量饮料开始。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>energyDrinkA<span class="example-io"> = [1,3,1], </span>energyDrinkB<span class="example-io"> = [3,1,1]</span></p>

<p><strong>输出：</strong><span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>要想获得 5 点强化能量，需要选择只饮用能量饮料 A（或者只饮用 B）。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>energyDrinkA<span class="example-io"> = [4,1,1], </span>energyDrinkB<span class="example-io"> = [1,1,3]</span></p>

<p><strong>输出：</strong><span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一个小时饮用能量饮料 A。</li>
	<li>切换到能量饮料 B ，在第二个小时无法获得强化能量。</li>
	<li>第三个小时饮用能量饮料 B ，并获得强化能量。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == energyDrinkA.length == energyDrinkB.length</code></li>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= energyDrinkA[i], energyDrinkB[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][0]$ 表示在第 $i$ 小时选择能量饮料 A 获得的最大强化能量，定义 $f[i][1]$ 表示在第 $i$ 小时选择能量饮料 B 获得的最大强化能量。初始时 $f[0][0] = \textit{energyDrinkA}[0]$, $f[0][1] = \textit{energyDrinkB}[0]$。答案为 $\max(f[n - 1][0], f[n - 1][1])$。

对于 $i > 0$，我们有以下状态转移方程：

$$
\begin{aligned}
f[i][0] & = \max(f[i - 1][0] + \textit{energyDrinkA}[i], f[i - 1][1]) \\
f[i][1] & = \max(f[i - 1][1] + \textit{energyDrinkB}[i], f[i - 1][0])
\end{aligned}
$$

最后返回 $\max(f[n - 1][0], f[n - 1][1])$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxEnergyBoost(self, energyDrinkA: List[int], energyDrinkB: List[int]) -> int:
        n = len(energyDrinkA)
        f = [[0] * 2 for _ in range(n)]
        f[0][0] = energyDrinkA[0]
        f[0][1] = energyDrinkB[0]
        for i in range(1, n):
            f[i][0] = max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1])
            f[i][1] = max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0])
        return max(f[n - 1])
```

#### Java

```java
class Solution {
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long[][] f = new long[n][2];
        f[0][0] = energyDrinkA[0];
        f[0][1] = energyDrinkB[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1]);
            f[i][1] = Math.max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0]);
        }
        return Math.max(f[n - 1][0], f[n - 1][1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxEnergyBoost(vector<int>& energyDrinkA, vector<int>& energyDrinkB) {
        int n = energyDrinkA.size();
        vector<vector<long long>> f(n, vector<long long>(2));
        f[0][0] = energyDrinkA[0];
        f[0][1] = energyDrinkB[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1]);
            f[i][1] = max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0]);
        }
        return max(f[n - 1][0], f[n - 1][1]);
    }
};
```

#### Go

```go
func maxEnergyBoost(energyDrinkA []int, energyDrinkB []int) int64 {
	n := len(energyDrinkA)
	f := make([][2]int64, n)
	f[0][0] = int64(energyDrinkA[0])
	f[0][1] = int64(energyDrinkB[0])
	for i := 1; i < n; i++ {
		f[i][0] = max(f[i-1][0]+int64(energyDrinkA[i]), f[i-1][1])
		f[i][1] = max(f[i-1][1]+int64(energyDrinkB[i]), f[i-1][0])
	}
	return max(f[n-1][0], f[n-1][1])
}
```

#### TypeScript

```ts
function maxEnergyBoost(energyDrinkA: number[], energyDrinkB: number[]): number {
    const n = energyDrinkA.length;
    const f: number[][] = Array.from({ length: n }, () => [0, 0]);
    f[0][0] = energyDrinkA[0];
    f[0][1] = energyDrinkB[0];
    for (let i = 1; i < n; i++) {
        f[i][0] = Math.max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1]);
        f[i][1] = Math.max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0]);
    }
    return Math.max(...f[n - 1]!);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划（空间优化）

我们注意到，状态 $f[i]$ 至于 $f[i - 1]$ 有关，而与 $f[i - 2]$ 无关。因此我们可以只使用两个变量 $f$ 和 $g$ 来维护状态，从而将空间复杂度优化到 $O(1)$。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxEnergyBoost(self, energyDrinkA: List[int], energyDrinkB: List[int]) -> int:
        f, g = energyDrinkA[0], energyDrinkB[0]
        for a, b in zip(energyDrinkA[1:], energyDrinkB[1:]):
            f, g = max(f + a, g), max(g + b, f)
        return max(f, g)
```

#### Java

```java
class Solution {
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long f = energyDrinkA[0], g = energyDrinkB[0];
        for (int i = 1; i < n; ++i) {
            long ff = Math.max(f + energyDrinkA[i], g);
            g = Math.max(g + energyDrinkB[i], f);
            f = ff;
        }
        return Math.max(f, g);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxEnergyBoost(vector<int>& energyDrinkA, vector<int>& energyDrinkB) {
        int n = energyDrinkA.size();
        long long f = energyDrinkA[0], g = energyDrinkB[0];
        for (int i = 1; i < n; ++i) {
            long long ff = max(f + energyDrinkA[i], g);
            g = max(g + energyDrinkB[i], f);
            f = ff;
        }
        return max(f, g);
    }
};
```

#### Go

```go
func maxEnergyBoost(energyDrinkA []int, energyDrinkB []int) int64 {
	n := len(energyDrinkA)
	f, g := energyDrinkA[0], energyDrinkB[0]
	for i := 1; i < n; i++ {
		f, g = max(f+energyDrinkA[i], g), max(g+energyDrinkB[i], f)
	}
	return int64(max(f, g))
}
```

#### TypeScript

```ts
function maxEnergyBoost(energyDrinkA: number[], energyDrinkB: number[]): number {
    const n = energyDrinkA.length;
    let [f, g] = [energyDrinkA[0], energyDrinkB[0]];
    for (let i = 1; i < n; ++i) {
        [f, g] = [Math.max(f + energyDrinkA[i], g), Math.max(g + energyDrinkB[i], f)];
    }
    return Math.max(f, g);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
