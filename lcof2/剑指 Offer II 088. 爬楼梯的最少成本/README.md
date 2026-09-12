---
comments: true
---

<!-- problem:start -->

# [剑指 Offer II 088. 爬楼梯的最少成本](https://leetcode.cn/problems/GzCJIP)

## 题目描述

<!-- description:start -->

<p>数组的每个下标作为一个阶梯，第 <code>i</code> 个阶梯对应着一个非负数的体力花费值&nbsp;<code>cost[i]</code>（下标从 <code>0</code> 开始）。</p>

<p>每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。</p>

<p>请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>cost = [10, 15, 20]
<strong>输出：</strong>15
<strong>解释：</strong>最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
</pre>

<p><strong>&nbsp;示例 2：</strong></p>

<pre>
<strong>输入：</strong>cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
<strong>输出：</strong>6
<strong>解释：</strong>最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= cost.length &lt;= 1000</code></li>
	<li><code>0 &lt;= cost[i] &lt;= 999</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 746&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/min-cost-climbing-stairs/">https://leetcode.cn/problems/min-cost-climbing-stairs/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 可以从下标 $0$ 或 $1$ 起步，每步付当前台阶费用并跨一或两级。到达顶部的方式互有重叠。
>
> 令 $dp[i]$ 为到达第 $i$ 级（顶为 $n$）的最小花费，则它只由 $i-1$、$i-2$ 转移而来。按升序填表即可。

<!-- thinking:end -->

定义 `dp[i]` 表示到达第 `i` 个台阶的最小花费。可以得到状态转移方程：

$$
dp[i] = \min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
$$

最终结果为 `dp[n]`。其中 $n$ 表示 `cost` 数组的长度。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
        dp = [0] * (n + 1)
        for i in range(2, n + 1):
            dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
        return dp[-1]
```

#### Java

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; ++i) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int n = cost.size();
        vector<int> dp(n + 1);
        for (int i = 2; i <= n; ++i) {
            dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
};
```

#### Go

```go
func minCostClimbingStairs(cost []int) int {
	n := len(cost)
	dp := make([]int, n+1)
	for i := 2; i <= n; i++ {
		dp[i] = min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2])
	}
	return dp[n]
}
```

#### TypeScript

```ts
function minCostClimbingStairs(cost: number[]): number {
    const n = cost.length;
    const dp = new Array(n + 1).fill(0);
    for (let i = 2; i <= n; ++i) {
        dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
    }
    return dp[n];
}
```

#### Swift

```swift
class Solution {
    func minCostClimbingStairs(_ cost: [Int]) -> Int {
        let n = cost.count
        var dp = Array(repeating: 0, count: n + 1)
        for i in 2...n {
            dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
        }
        return dp[n]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：动态规划（空间优化）

<!-- thinking:start -->

> **思考**
>
> 方法一用了长度为 $n+1$ 的数组。转移只依赖前两项，两个变量滚动即可把空间降为常数。

<!-- thinking:end -->

`dp[i]` 只跟前两项有关，用两个变量滚动即可，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        a = b = 0
        for i in range(1, len(cost)):
            a, b = b, min(a + cost[i - 1], b + cost[i])
        return b
```

#### Java

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int a = 0, b = 0;
        for (int i = 1; i < cost.length; ++i) {
            int c = Math.min(a + cost[i - 1], b + cost[i]);
            a = b;
            b = c;
        }
        return b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int a = 0, b = 0;
        for (int i = 1; i < cost.size(); ++i) {
            int c = min(a + cost[i - 1], b + cost[i]);
            a = b;
            b = c;
        }
        return b;
    }
};
```

#### Go

```go
func minCostClimbingStairs(cost []int) int {
	a, b := 0, 0
	for i := 1; i < len(cost); i++ {
		a, b = b, min(a+cost[i-1], b+cost[i])
	}
	return b
}
```

#### TypeScript

```ts
function minCostClimbingStairs(cost: number[]): number {
    let a = 0,
        b = 0;
    for (let i = 1; i < cost.length; ++i) {
        [a, b] = [b, Math.min(a + cost[i - 1], b + cost[i])];
    }
    return b;
}
```

#### Swift

```swift
class Solution {
    func minCostClimbingStairs(_ cost: [Int]) -> Int {
        var a = 0
        var b = 0
        for i in 1..<cost.count {
            let c = min(a + cost[i - 1], b + cost[i])
            a = b
            b = c
        }
        return b
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
