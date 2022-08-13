# [2144. 打折购买糖果的最小开销](https://leetcode.cn/problems/minimum-cost-of-buying-candies-with-discount)

[English Version](/solution/2100-2199/2144.Minimum%20Cost%20of%20Buying%20Candies%20With%20Discount/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一家商店正在打折销售糖果。每购买 <strong>两个</strong>&nbsp;糖果，商店会 <strong>免费</strong>&nbsp;送一个糖果。</p>

<p>免费送的糖果唯一的限制是：它的价格需要小于等于购买的两个糖果价格的 <strong>较小值</strong>&nbsp;。</p>

<ul>
	<li>比方说，总共有 <code>4</code>&nbsp;个糖果，价格分别为&nbsp;<code>1</code>&nbsp;，<code>2</code>&nbsp;，<code>3</code>&nbsp;和&nbsp;<code>4</code>&nbsp;，一位顾客买了价格为&nbsp;<code>2</code> 和&nbsp;<code>3</code>&nbsp;的糖果，那么他可以免费获得价格为 <code>1</code>&nbsp;的糖果，但不能获得价格为&nbsp;<code>4</code>&nbsp;的糖果。</li>
</ul>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>cost</code>&nbsp;，其中&nbsp;<code>cost[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个糖果的价格，请你返回获得 <strong>所有</strong>&nbsp;糖果的 <strong>最小</strong>&nbsp;总开销。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>cost = [1,2,3]
<b>输出：</b>5
<b>解释：</b>我们购买价格为 2 和 3 的糖果，然后免费获得价格为 1 的糖果。
总开销为 2 + 3 = 5 。这是开销最小的 <strong>唯一</strong>&nbsp;方案。
注意，我们不能购买价格为 1 和 3 的糖果，并免费获得价格为 2 的糖果。
这是因为免费糖果的价格必须小于等于购买的 2 个糖果价格的较小值。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>cost = [6,5,7,9,2,2]
<b>输出：</b>23
<b>解释：</b>最小总开销购买糖果方案为：
- 购买价格为 9 和 7 的糖果
- 免费获得价格为 6 的糖果
- 购买价格为 5 和 2 的糖果
- 免费获得价格为 2 的最后一个糖果
因此，最小总开销为 9 + 7 + 5 + 2 = 23 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>cost = [5,5]
<b>输出：</b>10
<b>解释：</b>由于只有 2 个糖果，我们需要将它们都购买，而且没有免费糖果。
所以总最小开销为 5 + 5 = 10 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= cost.length &lt;= 100</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumCost(self, cost: List[int]) -> int:
        cost.sort()
        ans, n = 0, len(cost)
        for i in range(n - 1, -1, -3):
            ans += cost[i]
            if i >= 1:
                ans += cost[i - 1]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int ans = 0, n = cost.length;
        for (int i = n - 1; i >= 0; i -= 3) {
            ans += cost[i];
            if (i >= 1) {
                ans += cost[i - 1];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumCost(vector<int>& cost) {
        sort(cost.begin(), cost.end());
        int ans = 0, n = cost.size();
        for (int i = n - 1; i >= 0; i -= 3) {
            ans += cost[i];
            if (i >= 1) ans += cost[i - 1];
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumCost(cost []int) int {
	sort.Ints(cost)
	ans, n := 0, len(cost)
	for i := n - 1; i >= 0; i -= 3 {
		ans += cost[i]
		if i >= 1 {
			ans += cost[i-1]
		}
	}
	return ans
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
