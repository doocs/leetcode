# [2907. 价格递增的最大利润三元组 I](https://leetcode.cn/problems/maximum-profitable-triplets-with-increasing-prices-i)

[English Version](/solution/2900-2999/2907.Maximum%20Profitable%20Triplets%20With%20Increasing%20Prices%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个长度为 <code>n</code> 的 <b>下标从 0 开始</b>&nbsp;的数组 <code>prices</code> 和 <code>profits</code>。商店里有 <code>n</code> 件商品，其中第 <code>i</code> 件商品的价格为 <code>prices[i]</code>，利润为 <code>profits[i]</code>。</p>

<p>我们必须按照以下条件选择三件商品：</p>

<ul>
	<li><code>prices[i] &lt; prices[j] &lt; prices[k]</code>，其中 <code>i &lt; j &lt; k</code>。</li>
</ul>

<p>如果我们选择满足上述条件的索引 <code>i</code>，<code>j</code> 和 <code>k</code> 的商品，那么利润就是 <code>profits[i] + profits[j] + profits[k]</code>。</p>

<p>返回我们能得到的最大利润，如果无法选择三件满足条件的商品，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b> prices = [10,2,3,4], profits = [100,2,7,10]
<b>输出：</b> 19
<b>解释：</b> 我们不能选择索引为 i=0 的商品，因为不存在索引 j 和 k 满足条件。
所以我们能选择的唯一三元组是索引为 1，2 和 3 的商品，这是一个有效的选择，因为 prices[1] &lt; prices[2] &lt; prices[3]。
答案就是它们的利润之和，即 2 + 7 + 10 = 19。</pre>

<p><b>示例 2:</b></p>

<pre>
<b>输入：</b> prices = [1,2,3,4,5], profits = [1,5,3,4,6]
<b>输出：</b> 15
<b>解释：</b> 我们可以选择任意三件商品，因为对于每个索引三元组 i，j 和 k 满足 i &lt; j &lt; k，条件都成立。
因此我们能得到的最大利润就是三件最赚钱的商品，它们的索引分别是 1，3 和 4。
答案就是它们的利润之和，即 5 + 4 + 6 = 15。</pre>

<p><b>示例 3:</b></p>

<pre>
<b>输入：</b> prices = [4,3,2,1], profits = [33,20,19,87]
<b>输出：</b> -1
<b>解释：</b> 我们不能选择任何满足条件的索引三元组，所以我们返回 -1。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>3 &lt;= prices.length == profits.length &lt;= 2000</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= profits[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举中间元素**

我们可以枚举中间元素 $profits[j]$，然后再枚举左边元素 $profits[i]$ 和右边元素 $profits[k]$。对于每个 $profits[j]$，我们需要找到最大的 $profits[i]$ 和最大的 $profits[k]$，使得 $prices[i] < prices[j] < prices[k]$。我们记 $left$ 为 $profits[j]$ 左边的最大值，而 $right$ 为 $profits[j]$ 右边的最大值。如果存在，那么我们更新答案为 $ans = \max(ans, left + profits[j] + right)$。

时间复杂度 $O(n^2)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxProfit(self, prices: List[int], profits: List[int]) -> int:
        n = len(prices)
        ans = -1
        for j, x in enumerate(profits):
            left = right = 0
            for i in range(j):
                if prices[i] < prices[j] and left < profits[i]:
                    left = profits[i]
            for k in range(j + 1, n):
                if prices[j] < prices[k] and right < profits[k]:
                    right = profits[k]
            if left and right:
                ans = max(ans, left + x + right)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxProfit(int[] prices, int[] profits) {
        int n = prices.length;
        int ans = -1;
        for (int j = 0; j < n; ++j) {
            int left = 0, right = 0;
            for (int i = 0; i < j; ++i) {
                if (prices[i] < prices[j]) {
                    left = Math.max(left, profits[i]);
                }
            }
            for (int k = j + 1; k < n; ++k) {
                if (prices[j] < prices[k]) {
                    right = Math.max(right, profits[k]);
                }
            }
            if (left > 0 && right > 0) {
                ans = Math.max(ans, left + profits[j] + right);
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
    int maxProfit(vector<int>& prices, vector<int>& profits) {
        int n = prices.size();
        int ans = -1;
        for (int j = 0; j < n; ++j) {
            int left = 0, right = 0;
            for (int i = 0; i < j; ++i) {
                if (prices[i] < prices[j]) {
                    left = max(left, profits[i]);
                }
            }
            for (int k = j + 1; k < n; ++k) {
                if (prices[j] < prices[k]) {
                    right = max(right, profits[k]);
                }
            }
            if (left && right) {
                ans = max(ans, left + profits[j] + right);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxProfit(prices []int, profits []int) int {
	n := len(prices)
	ans := -1
	for j, x := range profits {
		left, right := 0, 0
		for i := 0; i < j; i++ {
			if prices[i] < prices[j] {
				left = max(left, profits[i])
			}
		}
		for k := j + 1; k < n; k++ {
			if prices[j] < prices[k] {
				right = max(right, profits[k])
			}
		}
		if left > 0 && right > 0 {
			ans = max(ans, left+x+right)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxProfit(prices: number[], profits: number[]): number {
    const n = prices.length;
    let ans = -1;
    for (let j = 0; j < n; ++j) {
        let [left, right] = [0, 0];
        for (let i = 0; i < j; ++i) {
            if (prices[i] < prices[j]) {
                left = Math.max(left, profits[i]);
            }
        }
        for (let k = j + 1; k < n; ++k) {
            if (prices[j] < prices[k]) {
                right = Math.max(right, profits[k]);
            }
        }
        if (left > 0 && right > 0) {
            ans = Math.max(ans, left + profits[j] + right);
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_profit(prices: Vec<i32>, profits: Vec<i32>) -> i32 {
        let n = prices.len();
        let mut ans = -1;

        for j in 0..n {
            let mut left = 0;
            let mut right = 0;

            for i in 0..j {
                if prices[i] < prices[j] {
                    left = left.max(profits[i]);
                }
            }

            for k in (j + 1)..n {
                if prices[j] < prices[k] {
                    right = right.max(profits[k]);
                }
            }

            if left > 0 && right > 0 {
                ans = ans.max(left + profits[j] + right);
            }
        }

        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
