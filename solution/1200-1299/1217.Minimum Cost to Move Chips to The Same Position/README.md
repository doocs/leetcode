# [1217. 玩筹码](https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position)

[English Version](/solution/1200-1299/1217.Minimum%20Cost%20to%20Move%20Chips%20to%20The%20Same%20Position/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有&nbsp;<code>n</code>&nbsp;个筹码。第 <code>i</code> 个筹码的位置是<meta charset="UTF-8" />&nbsp;<code>position[i]</code>&nbsp;。</p>

<p>我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 <code>i</code> 个筹码的位置从&nbsp;<code>position[i]</code>&nbsp;改变为:</p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>position[i] + 2</code>&nbsp;或&nbsp;<code>position[i] - 2</code>&nbsp;，此时&nbsp;<code>cost = 0</code></li>
	<li><code>position[i] + 1</code>&nbsp;或&nbsp;<code>position[i] - 1</code>&nbsp;，此时&nbsp;<code>cost = 1</code></li>
</ul>

<p>返回将所有筹码移动到同一位置上所需要的 <em>最小代价</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1217.Minimum%20Cost%20to%20Move%20Chips%20to%20The%20Same%20Position/images/chips_e1.jpg" style="height: 217px; width: 750px;" /></p>

<pre>
<strong>输入：</strong>position = [1,2,3]
<strong>输出：</strong>1
<strong>解释：</strong>第一步:将位置3的筹码移动到位置1，成本为0。
第二步:将位置2的筹码移动到位置1，成本= 1。
总成本是1。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1217.Minimum%20Cost%20to%20Move%20Chips%20to%20The%20Same%20Position/images/chip_e2.jpg" style="height: 306px; width: 750px;" /></p>

<pre>
<strong>输入：</strong>position = [2,2,2,3,3]
<strong>输出：</strong>2
<strong>解释：</strong>我们可以把位置3的两个筹码移到位置2。每一步的成本为1。总成本= 2。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong>position = [1,1000000000]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= position.length &lt;= 100</code></li>
	<li><code>1 &lt;= position[i] &lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

将所有偶数下标的芯片移动到 0 号位置，所有奇数下标的芯片移动到 1 号位置，所有的代价为 0，接下来只需要在 0/1 号位置中选择其中一个较小数量的芯片，移动到另一个位置。所需的最小代价就是那个较小的数量。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为芯片的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCostToMoveChips(self, position: List[int]) -> int:
        a = sum(p % 2 for p in position)
        b = len(position) - a
        return min(a, b)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minCostToMoveChips(int[] position) {
        int a = 0;
        for (int p : position) {
            a += p % 2;
        }
        int b = position.length - a;
        return Math.min(a, b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCostToMoveChips(vector<int>& position) {
        int a = 0;
        for (auto& p : position) a += p & 1;
        int b = position.size() - a;
        return min(a, b);
    }
};
```

### **Go**

```go
func minCostToMoveChips(position []int) int {
	a := 0
	for _, p := range position {
		a += p & 1
	}
	b := len(position) - a
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} position
 * @return {number}
 */
var minCostToMoveChips = function (position) {
    let a = 0;
    for (let v of position) {
        a += v % 2;
    }
    let b = position.length - a;
    return Math.min(a, b);
};
```

### **...**

```

```

<!-- tabs:end -->
