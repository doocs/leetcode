---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1475.Final%20Prices%20With%20a%20Special%20Discount%20in%20a%20Shop/README.md
rating: 1212
source: 第 28 场双周赛 Q1
tags:
    - 栈
    - 数组
    - 单调栈
---

<!-- problem:start -->

# [1475. 商品折扣后的最终价格](https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop)

[English Version](/solution/1400-1499/1475.Final%20Prices%20With%20a%20Special%20Discount%20in%20a%20Shop/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组&nbsp;<code>prices</code>&nbsp;，其中&nbsp;<code>prices[i]</code>&nbsp;是商店里第&nbsp;<code>i</code>&nbsp;件商品的价格。</p>

<p>商店里正在进行促销活动，如果你要买第&nbsp;<code>i</code>&nbsp;件商品，那么你可以得到与 <code>prices[j]</code> 相等的折扣，其中&nbsp;<code>j</code>&nbsp;是满足&nbsp;<code>j &gt; i</code>&nbsp;且&nbsp;<code>prices[j] &lt;= prices[i]</code>&nbsp;的&nbsp;<strong>最小下标</strong>&nbsp;，如果没有满足条件的&nbsp;<code>j</code>&nbsp;，你将没有任何折扣。</p>

<p>请你返回一个数组，数组中第&nbsp;<code>i</code>&nbsp;个元素是折扣后你购买商品 <code>i</code>&nbsp;最终需要支付的价格。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>prices = [8,4,6,2,3]
<strong>输出：</strong>[4,2,4,2,3]
<strong>解释：</strong>
商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
商品 3 和 4 都没有折扣。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>prices = [1,2,3,4,5]
<strong>输出：</strong>[1,2,3,4,5]
<strong>解释：</strong>在这个例子中，所有商品都没有折扣。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>prices = [10,1,1,6]
<strong>输出：</strong>[9,0,1,6]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10^3</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

题目实际上是求每个元素右侧第一个比它小的元素，可以使用单调栈来解决。

我们逆序遍历数组 $\textit{prices}$，利用单调栈找出左侧最近一个比当前元素小的元素，然后计算折扣。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{prices}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        stk = []
        for i in reversed(range(len(prices))):
            x = prices[i]
            while stk and x < stk[-1]:
                stk.pop()
            if stk:
                prices[i] -= stk[-1]
            stk.append(x)
        return prices
```

#### Java

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; --i) {
            int x = prices[i];
            while (!stk.isEmpty() && stk.peek() > x) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                prices[i] -= stk.peek();
            }
            stk.push(x);
        }
        return prices;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> finalPrices(vector<int>& prices) {
        stack<int> stk;
        for (int i = prices.size() - 1; ~i; --i) {
            int x = prices[i];
            while (!stk.empty() && stk.top() > x) {
                stk.pop();
            }
            if (!stk.empty()) {
                prices[i] -= stk.top();
            }
            stk.push(x);
        }
        return prices;
    }
};
```

#### Go

```go
func finalPrices(prices []int) []int {
	stk := []int{}
	for i := len(prices) - 1; i >= 0; i-- {
		x := prices[i]
		for len(stk) > 0 && stk[len(stk)-1] > x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			prices[i] -= stk[len(stk)-1]
		}
		stk = append(stk, x)
	}
	return prices
}
```

#### TypeScript

```ts
function finalPrices(prices: number[]): number[] {
    const stk: number[] = [];
    for (let i = prices.length - 1; ~i; --i) {
        const x = prices[i];
        while (stk.length && stk.at(-1)! > x) {
            stk.pop();
        }
        prices[i] -= stk.at(-1) || 0;
        stk.push(x);
    }
    return prices;
}
```

#### Rust

```rust
impl Solution {
    pub fn final_prices(mut prices: Vec<i32>) -> Vec<i32> {
        let mut stk: Vec<i32> = Vec::new();
        for i in (0..prices.len()).rev() {
            let x = prices[i];
            while !stk.is_empty() && x < *stk.last().unwrap() {
                stk.pop();
            }
            if let Some(&top) = stk.last() {
                prices[i] -= top;
            }
            stk.push(x);
        }
        prices
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} prices
 * @return {number[]}
 */
var finalPrices = function (prices) {
    const stk = [];
    for (let i = prices.length - 1; ~i; --i) {
        const x = prices[i];
        while (stk.length && stk.at(-1) > x) {
            stk.pop();
        }
        prices[i] -= stk.at(-1) || 0;
        stk.push(x);
    }
    return prices;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $prices
     * @return Integer[]
     */
    function finalPrices($prices) {
        $stk = [];
        $n = count($prices);

        for ($i = $n - 1; $i >= 0; $i--) {
            $x = $prices[$i];
            while (!empty($stk) && $x < end($stk)) {
                array_pop($stk);
            }
            if (!empty($stk)) {
                $prices[$i] -= end($stk);
            }
            $stk[] = $x;
        }

        return $prices;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
