# [1049. Last Stone Weight II](https://leetcode.com/problems/last-stone-weight-ii)

[中文文档](/solution/1000-1099/1049.Last%20Stone%20Weight%20II/README.md)

## Description

<p>You are given an array of integers <code>stones</code> where <code>stones[i]</code> is the weight of the <code>i<sup>th</sup></code> stone.</p>

<p>We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights <code>x</code> and <code>y</code> with <code>x &lt;= y</code>. The result of this smash is:</p>

<ul>
	<li>If <code>x == y</code>, both stones are destroyed, and</li>
	<li>If <code>x != y</code>, the stone of weight <code>x</code> is destroyed, and the stone of weight <code>y</code> has new weight <code>y - x</code>.</li>
</ul>

<p>At the end of the game, there is <strong>at most one</strong> stone left.</p>

<p>Return <em>the smallest possible weight of the left stone</em>. If there are no stones left, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [2,7,4,1,8,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
we can combine 1 and 1 to get 0, so the array converts to [1], then that&#39;s the optimal value.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [31,26,33,21,40]
<strong>Output:</strong> 5
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> stones = [1,2]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stones.length &lt;= 30</code></li>
	<li><code>1 &lt;= stones[i] &lt;= 100</code></li>
</ul>


## Solutions

This question can be converted to calculate how many stones a backpack with a capacity of `sum / 2` can hold.

<!-- tabs:start -->

### **Python3**

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
