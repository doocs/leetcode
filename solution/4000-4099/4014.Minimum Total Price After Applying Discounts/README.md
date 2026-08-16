---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4014.Minimum%20Total%20Price%20After%20Applying%20Discounts/README.md
rating: 1192
source: 第 514 场周赛 Q1
---

<!-- problem:start -->

# [4014. 应用折扣后的最低总价](https://leetcode.cn/problems/minimum-total-price-after-applying-discounts)

[English Version](/solution/4000-4099/4014.Minimum%20Total%20Price%20After%20Applying%20Discounts/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数数组 <code>prices</code> 和 <code>discounts</code>。</p>

<p><code>prices[i]</code> 表示第 <code>i<sup>th</sup></code> 件商品的价格，<code>discounts[j]</code> 表示一个折扣百分比。</p>

<p>你可以按照以下规则使用折扣：</p>

<ul>
	<li>每个折扣&nbsp;<strong>最多&nbsp;</strong>只能用于一件商品。</li>
	<li>每件商品<strong>&nbsp;最多</strong>&nbsp;只能使用一个折扣。</li>
	<li>商品也可以不使用任何折扣。</li>
</ul>

<p>如果将 <code>d</code>% 的折扣应用于价格为 <code>p</code> 的商品，则其最终价格为 <code>(p * (100 - d)) / 100</code>。最终价格<strong>&nbsp;不进行四舍五入&nbsp;</strong>。</p>

<p>请以最优方式分配折扣，并返回所有商品最终价格之和的&nbsp;<strong>最小值&nbsp;</strong>。与实际答案的误差在 <code>10<sup>-5</sup></code> 以内的结果都将被接受。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [10,30,21], discounts = [50,60]</span></p>

<p><strong>输出：</strong> <span class="example-io">32.50000</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>discounts[1] = 60</code> 应用于 <code>prices[1] = 30</code>，则最终价格为 <code>30 * (100 - 60) / 100 = 12</code>。</li>
	<li>将 <code>discounts[0] = 50</code> 应用于 <code>prices[2] = 21</code>，则最终价格为 <code>21 * (100 - 50) / 100 = 10.5</code>。</li>
	<li><code>prices[0] = 10</code> 不使用折扣，因此价格仍为 10。</li>
</ul>

<p>总价为 <code>12 + 10.5 + 10 = 32.50000</code>，这是可能得到的最小值。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [100,70], discounts = [10,40,50]</span></p>

<p><strong>输出：</strong> <span class="example-io">92.00000</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>discounts[2] = 50</code> 应用于 <code>prices[0] = 100</code>，则最终价格为 <code>100 * (100 - 50) / 100 = 50</code>。</li>
	<li>将 <code>discounts[1] = 40</code> 应用于 <code>prices[1] = 70</code>，则最终价格为 <code>70 * (100 - 40) / 100 = 42</code>。</li>
</ul>

<p>总价为 <code>50 + 42 = 92.00000</code>，这是可能得到的最小值。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">prices = [7,3,9], discounts = [100,100]</span></p>

<p><strong>输出：</strong> <span class="example-io">3.00000</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>discounts[0] = 100</code> 应用于 <code>prices[2] = 9</code>，则最终价格为 <code>9 * (100 - 100) / 100 = 0</code>。</li>
	<li>将 <code>discounts[1] = 100</code> 应用于 <code>prices[0] = 7</code>，则最终价格为 <code>7 * (100 - 100) / 100 = 0</code>。</li>
	<li><code>prices[1] = 3</code> 不使用折扣，因此价格仍为 3。</li>
</ul>

<p>总价为 <code>0 + 0 + 3 = 3.00000</code>，这是可能得到的最小值。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length, discounts.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= discounts[j] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

为了最小化总价，我们需要最大化折扣节省的总金额。若把折扣 $d$ 应用于价格为 $p$ 的商品，节省的金额为 $p \times d / 100$。根据排序不等式，把较大的折扣用在价格较高的商品上，可以使节省的总金额最大。

因此，我们将 $\textit{prices}$ 和 $\textit{discounts}$ 都按升序排序，然后用双指针从两个数组的末尾开始，依次把当前最大的折扣应用到当前最贵的商品上，并累加折后价格。当折扣用完后，剩余的商品按原价累加即可。

时间复杂度 $O(n \times \log n + m \times \log m)$，空间复杂度 $O(\log n + \log m)$。其中 $n$ 和 $m$ 分别是数组 $\textit{prices}$ 和 $\textit{discounts}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minPrice(self, prices: list[int], discounts: list[int]) -> float:
        prices.sort()
        discounts.sort()
        i, j = len(prices) - 1, len(discounts) - 1
        ans = 0
        while i >= 0 and j >= 0:
            ans += prices[i] * (100 - discounts[j]) / 100
            i -= 1
            j -= 1
        while i >= 0:
            ans += prices[i]
            i -= 1
        return ans
```

#### Java

```java
class Solution {
    public double minPrice(int[] prices, int[] discounts) {
        Arrays.sort(prices);
        Arrays.sort(discounts);

        int i = prices.length - 1;
        int j = discounts.length - 1;

        double ans = 0;

        while (i >= 0 && j >= 0) {
            ans += prices[i] * (100 - discounts[j]) / 100.0;
            i--;
            j--;
        }

        while (i >= 0) {
            ans += prices[i];
            i--;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double minPrice(vector<int>& prices, vector<int>& discounts) {
        sort(prices.begin(), prices.end());
        sort(discounts.begin(), discounts.end());

        int i = prices.size() - 1;
        int j = discounts.size() - 1;

        double ans = 0;

        while (i >= 0 && j >= 0) {
            ans += prices[i] * (100 - discounts[j]) / 100.0;
            i--;
            j--;
        }

        while (i >= 0) {
            ans += prices[i];
            i--;
        }

        return ans;
    }
};
```

#### Go

```go
func minPrice(prices []int, discounts []int) float64 {
	sort.Ints(prices)
	sort.Ints(discounts)

	i := len(prices) - 1
	j := len(discounts) - 1

	var ans float64

	for i >= 0 && j >= 0 {
		ans += float64(prices[i]) * float64(100-discounts[j]) / 100.0
		i--
		j--
	}

	for i >= 0 {
		ans += float64(prices[i])
		i--
	}

	return ans
}
```

#### TypeScript

```ts
function minPrice(prices: number[], discounts: number[]): number {
    prices.sort((a, b) => a - b);
    discounts.sort((a, b) => a - b);

    let i = prices.length - 1;
    let j = discounts.length - 1;

    let ans = 0;

    while (i >= 0 && j >= 0) {
        ans += (prices[i] * (100 - discounts[j])) / 100;
        i--;
        j--;
    }

    while (i >= 0) {
        ans += prices[i];
        i--;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
