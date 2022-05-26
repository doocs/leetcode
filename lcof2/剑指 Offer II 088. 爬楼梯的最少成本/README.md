# [剑指 Offer II 088. 爬楼梯的最少成本](https://leetcode.cn/problems/GzCJIP)

## 题目描述

<!-- 这里写题目描述 -->

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
