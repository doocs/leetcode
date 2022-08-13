# [1833. 雪糕的最大数量](https://leetcode.cn/problems/maximum-ice-cream-bars)

[English Version](/solution/1800-1899/1833.Maximum%20Ice%20Cream%20Bars/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。</p>

<p>商店中新到 <code>n</code> 支雪糕，用长度为 <code>n</code> 的数组 <code>costs</code> 表示雪糕的定价，其中 <code>costs[i]</code> 表示第 <code>i</code> 支雪糕的现金价格。Tony 一共有 <code>coins</code> 现金可以用于消费，他想要买尽可能多的雪糕。</p>

<p>给你价格数组 <code>costs</code> 和现金量 <code>coins</code> ，请你计算并返回 Tony 用 <code>coins</code> 现金能够买到的雪糕的 <strong>最大数量</strong> 。</p>

<p><strong>注意：</strong>Tony 可以按任意顺序购买雪糕。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>costs = [1,3,2,4,1], coins = 7
<strong>输出：</strong>4
<strong>解释：</strong>Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>costs = [10,6,8,7,7,8], coins = 5
<strong>输出：</strong>0
<strong>解释：</strong>Tony 没有足够的钱买任何一支雪糕。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>costs = [1,6,3,1,2,5], coins = 20
<strong>输出：</strong>6
<strong>解释：</strong>Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>costs.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coins &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

注意数据范围，题目很容易误导我们使用 01 背包（会超时），其实这题就是简单贪心，优先选择定价小的雪糕。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        costs.sort()
        ans = 0
        for c in costs:
            if coins < c:
                break
            else:
                ans += 1
                coins -= c
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int ans = 0, n = costs.length;
        for (int i = 0; i < n && coins >= costs[i]; i++) {
            ans++;
            coins -= costs[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxIceCream(vector<int>& costs, int coins) {
        sort(costs.begin(), costs.end());
        int ans = 0;
        for (int i = 0; i < costs.size() && coins >= costs[i]; ++i) {
            ++ans;
            coins -= costs[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func maxIceCream(costs []int, coins int) int {
	sort.Ints(costs)
	n := len(costs)
	ans := 0
	for i := 0; i < n && coins >= costs[i]; i++ {
		ans++
		coins -= costs[i]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
