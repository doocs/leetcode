# [1872. 石子游戏 VIII](https://leetcode.cn/problems/stone-game-viii)

[English Version](/solution/1800-1899/1872.Stone%20Game%20VIII/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 玩一个游戏，两人轮流操作， <strong>Alice 先手</strong> 。</p>

<p>总共有 <code>n</code> 个石子排成一行。轮到某个玩家的回合时，如果石子的数目 <strong>大于 1</strong> ，他将执行以下操作：</p>

<ol>
	<li>选择一个整数 <code>x &gt; 1</code> ，并且 <strong>移除</strong> 最左边的 <code>x</code> 个石子。</li>
	<li>将<strong> 移除</strong> 的石子价值之 <strong>和</strong> 累加到该玩家的分数中。</li>
	<li>将一个 <strong>新的石子</strong> 放在最左边，且新石子的值为被移除石子值之和。</li>
</ol>

<p>当只剩下 <strong>一个</strong> 石子时，游戏结束。</p>

<p>Alice 和 Bob 的 <strong>分数之差</strong> 为 <code>(Alice 的分数 - Bob 的分数)</code> 。 Alice 的目标是<strong> 最大化</strong> 分数差，Bob 的目标是 <strong>最小化</strong> 分数差。</p>

<p>给你一个长度为 <code>n</code> 的整数数组 <code>stones</code> ，其中 <code>stones[i]</code> 是 <strong>从左边起</strong> 第 <code>i</code> 个石子的价值。请你返回在双方都采用 <strong>最优</strong> 策略的情况下，Alice 和 Bob 的 <strong>分数之差</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>stones = [-1,2,-3,4,-5]
<b>输出：</b>5
<strong>解释：</strong>
- Alice 移除最左边的 4 个石子，得分增加 (-1) + 2 + (-3) + 4 = 2 ，并且将一个价值为 2 的石子放在最左边。stones = [2,-5] 。
- Bob 移除最左边的 2 个石子，得分增加 2 + (-5) = -3 ，并且将一个价值为 -3 的石子放在最左边。stones = [-3] 。
两者分数之差为 2 - (-3) = 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>stones = [7,-6,5,10,5,-2,-6]
<b>输出：</b>13
<b>解释：</b>
- Alice 移除所有石子，得分增加 7 + (-6) + 5 + 10 + 5 + (-2) + (-6) = 13 ，并且将一个价值为 13 的石子放在最左边。stones = [13] 。
两者分数之差为 13 - 0 = 13 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>stones = [-10,-12]
<b>输出：</b>-22
<strong>解释：</strong>
- Alice 只有一种操作，就是移除所有石子。得分增加 (-10) + (-12) = -22 ，并且将一个价值为 -22 的石子放在最左边。stones = [-22] 。
两者分数之差为 (-22) - 0 = -22 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == stones.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= stones[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“前缀和 + 动态规划”实现。

每次取走最左边的 x 个石子，把它们的和放回最左边，前缀和 `preSum[x]` 不变。

假设 `dp[i]` 表示当 Alice 选择 `[i, n)` 范围内的某个下标时，Alice 与 Bob 分数的最大差值。

-   若 Alice 选择 i，她获得的分数是 `preSum[i]`，此时 Bob 会在 `[i+1, n]` 范围内选择，并且 Bob 也会采取最优策略，此时最大差值为 `dp[i+1]`。状态转移方程：`dp[i] = preSum[i] - dp[i+1]`。
-   若 Alice 没选择 i，那么她需要在 `[i+1, n)` 范围内找，状态转移方程为 `dp[i] = dp[i+1]`。

最优策略下，`dp[i] = max(dp[i+1], preSum[i] - dp[i+1])`。这里 $O(n)$ 的空间复杂度可以优化为一个变量 f。

x 必须大于 1，所以题目即是求 `dp[1]`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def stoneGameVIII(self, stones: List[int]) -> int:
        pre_sum = list(accumulate(stones))
        f = pre_sum[len(stones) - 1]
        for i in range(len(stones) - 2, 0, -1):
            f = max(f, pre_sum[i] - f)
        return f
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for (int i = 1; i < n; ++i) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        int f = preSum[n - 1];
        for (int i = n - 2; i > 0; --i) {
            f = Math.max(f, preSum[i] - f);
        }
        return f;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
