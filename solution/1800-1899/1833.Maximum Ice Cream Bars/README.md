# [1833. 雪糕的最大数量](https://leetcode.cn/problems/maximum-ice-cream-bars)

[English Version](/solution/1800-1899/1833.Maximum%20Ice%20Cream%20Bars/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。</p>

<p>商店中新到 <code>n</code> 支雪糕，用长度为 <code>n</code> 的数组 <code>costs</code> 表示雪糕的定价，其中 <code>costs[i]</code> 表示第 <code>i</code> 支雪糕的现金价格。Tony 一共有 <code>coins</code> 现金可以用于消费，他想要买尽可能多的雪糕。</p>

<p><strong>注意：</strong>Tony 可以按任意顺序购买雪糕。</p>

<p>给你价格数组 <code>costs</code> 和现金量 <code>coins</code> ，请你计算并返回 Tony 用 <code>coins</code> 现金能够买到的雪糕的 <strong>最大数量</strong> 。</p>

<p>你必须使用计数排序解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>costs = [1,3,2,4,1], coins = 7
<strong>输出：</strong>4
<strong>解释：</strong>Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>costs = [10,6,8,7,7,8], coins = 5
<strong>输出：</strong>0
<strong>解释：</strong>Tony 没有足够的钱买任何一支雪糕。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>costs = [1,6,3,1,2,5], coins = 20
<strong>输出：</strong>6
<strong>解释：</strong>Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>costs.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coins &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

要买尽可能多的雪糕，且可以按任意顺序购买，因此，我们应该优先选择定价小的雪糕。

对数组 `costs` 进行排序，然后从定价最小的雪糕开始，依次购买，直到不能购买为止，返回能购买的雪糕数量。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 `costs` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        costs.sort()
        for i, c in enumerate(costs):
            if coins < c:
                return i
            coins -= c
        return len(costs)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int n = costs.length;
        for (int i = 0; i < n; ++i) {
            if (coins < costs[i]) {
                return i;
            }
            coins -= costs[i];
        }
        return n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxIceCream(vector<int>& costs, int coins) {
        sort(costs.begin(), costs.end());
        int n = costs.size();
        for (int i = 0; i < n; ++i) {
            if (coins < costs[i]) return i;
            coins -= costs[i];
        }
        return n;
    }
};
```

### **Go**

```go
func maxIceCream(costs []int, coins int) int {
	sort.Ints(costs)
	for i, c := range costs {
		if coins < c {
			return i
		}
		coins -= c
	}
	return len(costs)
}
```

### **JavaScript**

```js
/**
 * @param {number[]} costs
 * @param {number} coins
 * @return {number}
 */
var maxIceCream = function (costs, coins) {
    costs.sort((a, b) => a - b);
    const n = costs.length;
    for (let i = 0; i < n; ++i) {
        if (coins < costs[i]) {
            return i;
        }
        coins -= costs[i];
    }
    return n;
};
```

### **...**

```

```

<!-- tabs:end -->
