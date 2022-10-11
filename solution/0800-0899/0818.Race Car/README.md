# [818. 赛车](https://leetcode.cn/problems/race-car)

[English Version](/solution/0800-0899/0818.Race%20Car/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

你的赛车可以从位置 <code>0</code> 开始，并且速度为 <code>+1</code> ，在一条无限长的数轴上行驶。赛车也可以向负方向行驶。赛车可以按照由加速指令 <code>'A'</code> 和倒车指令 <code>'R'</code> 组成的指令序列自动行驶。

<ul>
	<li>当收到指令 <code>'A'</code> 时，赛车这样行驶：
	<ul>
		<li><code>position += speed</code></li>
		<li><code>speed *= 2</code></li>
	</ul>
	</li>
	<li>当收到指令 <code>'R'</code> 时，赛车这样行驶：
	<ul>
		<li>如果速度为正数，那么<code>speed = -1</code></li>
		<li>否则 <code>speed = 1</code></li>
	</ul>
	当前所处位置不变。</li>
</ul>

<p>例如，在执行指令 <code>"AAR"</code> 后，赛车位置变化为 <code>0 --&gt; 1 --&gt; 3 --&gt; 3</code> ，速度变化为 <code>1 --&gt; 2 --&gt; 4 --&gt; -1</code> 。</p>

<p>给你一个目标位置 <code>target</code> ，返回能到达目标位置的最短指令序列的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = 3
<strong>输出：</strong>2
<strong>解释：</strong>
最短指令序列是 "AA" 。
位置变化 0 --&gt; 1 --&gt; 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = 6
<strong>输出：</strong>5
<strong>解释：</strong>
最短指令序列是 "AAARA" 。
位置变化 0 --&gt; 1 --&gt; 3 --&gt; 7 --&gt; 7 --&gt; 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

设 $dp[i]$ 表示到达位置 $i$ 的最短指令序列的长度。答案为 $dp[target]$。

对于任意位置 $i$，都有 $2^{k-1} \leq i \lt 2^k$，并且我们可以有三种方式到达位置 $i$：

-   如果 $i$ 等于 $2^k-1$，那么我们可以直接执行 $k$ 个 `A` 指令到达位置 $i$，此时 $dp[i] = k$；
-   否则，我们可以先执行 $k$ 个 `A` 指令到达位置 $2^k-1$，然后执行 `R` 指令，剩余距离为 $2^k-1-i$，此时 $dp[i] = dp[2^k-1-i] + k + 1$；我们也可以先执行 $k-1$ 个 `A` 指令到达位置 $2^{k-1}-1$，然后执行 `R` 指令，接着执行 $j$（其中 $0 \le j \lt k$） 个 `A`，再执行 `R`，剩余距离为 $i - 2^{k-1} + 2^j$，此时 $dp[i] = dp[i - 2^{k-1} + 2^j] + k - 1 + j + 2$。求出 $dp[i]$ 的最小值即可。

时间复杂度 $O(n \log n)$，其中 $n$ 为 $target$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def racecar(self, target: int) -> int:
        dp = [0] * (target + 1)
        for i in range(1, target + 1):
            k = i.bit_length()
            if i == 2**k - 1:
                dp[i] = k
                continue
            dp[i] = dp[2**k - 1 - i] + k + 1
            for j in range(k - 1):
                dp[i] = min(dp[i], dp[i - (2 ** (k - 1) - 2**j)] + k - 1 + j + 2)
        return dp[target]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int racecar(int target) {
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; ++i) {
            int k = 32 - Integer.numberOfLeadingZeros(i);
            if (i == (1 << k) - 1) {
                dp[i] = k;
                continue;
            }
            dp[i] = dp[(1 << k) - 1 - i] + k + 1;
            for (int j = 0; j < k; ++j) {
                dp[i] = Math.min(dp[i], dp[i - (1 << (k - 1)) + (1 << j)] + k - 1 + j + 2);
            }
        }
        return dp[target];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int racecar(int target) {
        vector<int> dp(target + 1);
        for (int i = 1; i <= target; ++i) {
            int k = 32 - __builtin_clz(i);
            if (i == (1 << k) - 1) {
                dp[i] = k;
                continue;
            }
            dp[i] = dp[(1 << k) - 1 - i] + k + 1;
            for (int j = 0; j < k; ++j) {
                dp[i] = min(dp[i], dp[i - (1 << (k - 1)) + (1 << j)] + k - 1 + j + 2);
            }
        }
        return dp[target];
    }
};
```

### **Go**

```go
func racecar(target int) int {
	dp := make([]int, target+1)
	for i := 1; i <= target; i++ {
		k := bits.Len(uint(i))
		if i == (1<<k)-1 {
			dp[i] = k
			continue
		}
		dp[i] = dp[(1<<k)-1-i] + k + 1
		for j := 0; j < k; j++ {
			dp[i] = min(dp[i], dp[i-(1<<(k-1))+(1<<j)]+k-1+j+2)
		}
	}
	return dp[target]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
