# [1883. 准时抵达会议现场的最小跳过休息次数](https://leetcode.cn/problems/minimum-skips-to-arrive-at-meeting-on-time)

[English Version](/solution/1800-1899/1883.Minimum%20Skips%20to%20Arrive%20at%20Meeting%20On%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>hoursBefore</code> ，表示你要前往会议所剩下的可用小时数。要想成功抵达会议现场，你必须途经 <code>n</code> 条道路。道路的长度用一个长度为 <code>n</code> 的整数数组 <code>dist</code> 表示，其中 <code>dist[i]</code> 表示第 <code>i</code> 条道路的长度（单位：<strong>千米</strong>）。另给你一个整数 <code>speed</code> ，表示你在道路上前进的速度（单位：<strong>千米每小时</strong>）。</p>

<p>当你通过第 <code>i</code> 条路之后，就必须休息并等待，直到 <strong>下一个整数小时</strong> 才能开始继续通过下一条道路。注意：你不需要在通过最后一条道路后休息，因为那时你已经抵达会议现场。</p>

<ul>
	<li>例如，如果你通过一条道路用去 <code>1.4</code> 小时，那你必须停下来等待，到 <code>2</code> 小时才可以继续通过下一条道路。如果通过一条道路恰好用去 <code>2</code> 小时，就无需等待，可以直接继续。</li>
</ul>

<p>然而，为了能准时到达，你可以选择 <strong>跳过</strong> 一些路的休息时间，这意味着你不必等待下一个整数小时。注意，这意味着与不跳过任何休息时间相比，你可能在不同时刻到达接下来的道路。</p>

<ul>
	<li>例如，假设通过第 <code>1</code> 条道路用去 <code>1.4</code> 小时，且通过第 <code>2</code> 条道路用去 <code>0.6</code> 小时。跳过第 <code>1</code> 条道路的休息时间意味着你将会在恰好 <code>2</code> 小时完成通过第 <code>2</code> 条道路，且你能够立即开始通过第 <code>3</code> 条道路。</li>
</ul>

<p>返回准时抵达会议现场所需要的 <strong>最小跳过次数</strong> ，如果 <strong>无法准时参会</strong> ，返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dist = [1,3,2], speed = 4, hoursBefore = 2
<strong>输出：</strong>1
<strong>解释：</strong>
不跳过任何休息时间，你将用 (1/4 + 3/4) + (3/4 + 1/4) + (2/4) = 2.5 小时才能抵达会议现场。
可以跳过第 1 次休息时间，共用 ((1/4 + <strong>0</strong>) + (3/4 + 0)) + (2/4) = 1.5 小时抵达会议现场。
注意，第 2 次休息时间缩短为 0 ，由于跳过第 1 次休息时间，你是在整数小时处完成通过第 2 条道路。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dist = [7,3,5,5], speed = 2, hoursBefore = 10
<strong>输出：</strong>2
<strong>解释：</strong>
不跳过任何休息时间，你将用 (7/2 + 1/2) + (3/2 + 1/2) + (5/2 + 1/2) + (5/2) = 11.5 小时才能抵达会议现场。
可以跳过第 1 次和第 3 次休息时间，共用 ((7/2 + <strong>0</strong>) + (3/2 + 0)) + ((5/2 + <strong>0</strong>) + (5/2)) = 10 小时抵达会议现场。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>dist = [7,3,5,5], speed = 1, hoursBefore = 10
<strong>输出：</strong>-1
<strong>解释：</strong>即使跳过所有的休息时间，也无法准时参加会议。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == dist.length</code></li>
	<li><code>1 <= n <= 1000</code></li>
	<li><code>1 <= dist[i] <= 10<sup>5</sup></code></li>
	<li><code>1 <= speed <= 10<sup>6</sup></code></li>
	<li><code>1 <= hoursBefore <= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“动态规划”实现。

定义 `dp[i][j]` 表示前 i 段道路，跳过了 j 次的最短路程（耗时也一样）。

考虑最后一段道路 `dist[i - 1]` 是否跳过：

-   若没有跳过，那么 `dp[i][j] = ⌈dp[i - 1][j] + dist[i - 1] / speed⌉`
-   若跳过，那么 `dp[i][j] = dp[i - 1][j - 1] + dist[i - 1] / speed`

综合两种情况，`dp[i][j] = min{⌈dp[i - 1][j] + dist[i - 1] / speed⌉, dp[i - 1][j - 1] + dist[i - 1] / speed}`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSkips(self, dist: List[int], speed: int, hoursBefore: int) -> int:
        n = len(dist)
        dp = [[inf] * (n + 1) for _ in range(n + 1)]
        dp[0][0] = 0
        for i in range(1, n + 1):
            for j in range(i + 1):
                if i != j:
                    # 没有跳过
                    dp[i][j] = min(
                        dp[i][j],
                        ((dp[i - 1][j] + dist[i - 1] - 1) // speed + 1) * speed,
                    )
                if j > 0:
                    # 跳过
                    dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + dist[i - 1])
        for i in range(n + 1):
            if dp[n][i] <= hoursBefore * speed:
                return i
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (i != j) {
                    // 没有跳过
                    dp[i][j] = Math.min(dp[i][j], ((dp[i - 1][j] + dist[i - 1] - 1) / speed + 1) * speed);
                }
                if (j > 0) {
                    // 跳过
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + dist[i - 1]);
                }
            }
        }
        for (int i = 0; i <= n; ++i) {
            if (dp[n][i] <= hoursBefore * speed) {
                return i;
            }
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
