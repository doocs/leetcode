# [1049. 最后一块石头的重量 II](https://leetcode-cn.com/problems/last-stone-weight-ii)

[English Version](/solution/1000-1099/1049.Last%20Stone%20Weight%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一堆石头，每块石头的重量都是正整数。</p>

<p>每一回合，从中选出<strong>任意两块石头</strong>，然后将它们一起粉碎。假设石头的重量分别为&nbsp;<code>x</code> 和&nbsp;<code>y</code>，且&nbsp;<code>x &lt;= y</code>。那么粉碎的可能结果如下：</p>

<ul>
	<li>如果&nbsp;<code>x == y</code>，那么两块石头都会被完全粉碎；</li>
	<li>如果&nbsp;<code>x != y</code>，那么重量为&nbsp;<code>x</code>&nbsp;的石头将会完全粉碎，而重量为&nbsp;<code>y</code>&nbsp;的石头新重量为&nbsp;<code>y-x</code>。</li>
</ul>

<p>最后，最多只会剩下一块石头。返回此石头<strong>最小的可能重量</strong>。如果没有石头剩下，就返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[2,7,4,1,8,1]
<strong>输出：</strong>1
<strong>解释：</strong>
组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= stones.length &lt;= 30</code></li>
	<li><code>1 &lt;= stones[i] &lt;= 1000</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**两个**石头的重量越接近，粉碎后的新重量就越小。同样的，**两堆**石头的重量越接近，它们粉碎后的新重量也越小。

所以本题可以转换为，计算容量为 `sum / 2` 的背包最多能装多少石头。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lastStoneWeightII(self, stones: List[int]) -> int:
        s = sum(stones)
        n = s // 2
        dp = [False for i in range(n + 1)]
        dp[0] = True
        for stone in stones:
            for j in range(n, stone - 1, -1):
                dp[j] = dp[j] or dp[j - stone]
        for j in range(n, -1, -1):
            if dp[j]:
                return s - j - j
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int n = sum / 2;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int stone : stones) {
            for (int j = n; j >= stone; j--) {
                dp[j] = dp[j] || dp[j - stone];
            }
        }
        for (int j = n; ; j--) {
            if (dp[j]) {
                return sum - j - j;
            }
        }
    }
}
```

### **Go**

```go
func lastStoneWeightII(stones []int) int {
	sum := 0
	for _, stone := range stones {
		sum += stone
	}
	n := sum / 2
	dp := make([]bool, n+1)
	dp[0] = true
	for _, stone := range stones {
		for j := n; j >= stone; j-- {
			dp[j] = dp[j] || dp[j-stone]
		}
	}
	for j := n; ; j-- {
		if dp[j] {
			return sum - j - j
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
