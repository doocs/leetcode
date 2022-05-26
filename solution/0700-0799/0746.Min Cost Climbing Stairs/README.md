# [746. 使用最小花费爬楼梯](https://leetcode.cn/problems/min-cost-climbing-stairs)

[English Version](/solution/0700-0799/0746.Min%20Cost%20Climbing%20Stairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>cost</code> ，其中 <code>cost[i]</code> 是从楼梯第 <code>i</code> 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。</p>

<p>你可以选择从下标为 <code>0</code> 或下标为 <code>1</code> 的台阶开始爬楼梯。</p>

<p>请你计算并返回达到楼梯顶部的最低花费。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>cost = [10,<em><strong>15</strong></em>,20]
<strong>输出：</strong>15
<strong>解释：</strong>你将从下标为 1 的台阶开始。
- 支付 15 ，向上爬两个台阶，到达楼梯顶部。
总花费为 15 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>cost = [<em><strong>1</strong></em>,100,<em><strong>1</strong></em>,1,<em><strong>1</strong></em>,100,<em><strong>1</strong></em>,<em><strong>1</strong></em>,100,<em><strong>1</strong></em>]
<strong>输出：</strong>6
<strong>解释：</strong>你将从下标为 0 的台阶开始。
- 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
- 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
- 支付 1 ，向上爬一个台阶，到达楼梯顶部。
总花费为 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= cost.length &lt;= 1000</code></li>
	<li><code>0 &lt;= cost[i] &lt;= 999</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        a = b = 0
        for i in range(1, len(cost)):
            a, b = b, min(a + cost[i - 1], b + cost[i])
        return b
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **TypeScript**

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

### **C++**

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

### **Go**

```go
func minCostClimbingStairs(cost []int) int {
	a, b := 0, 0
	for i := 1; i < len(cost); i++ {
		a, b = b, min(a+cost[i-1], b+cost[i])
	}
	return b
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
