---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3457.Eat%20Pizzas%21/README.md
rating: 1704
source: 第 437 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [3457. 吃披萨](https://leetcode.cn/problems/eat-pizzas)

[English Version](/solution/3400-3499/3457.Eat%20Pizzas%21/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组 <code>pizzas</code>，其中 <code>pizzas[i]</code> 表示第 <code>i</code>&nbsp;个披萨的重量。每天你会吃&nbsp;<strong>恰好</strong> 4 个披萨。由于你的新陈代谢能力惊人，当你吃重量为 <code>W</code>、<code>X</code>、<code>Y</code> 和 <code>Z</code> 的披萨（其中 <code>W &lt;= X &lt;= Y &lt;= Z</code>）时，你只会增加 1 个披萨的重量！体重增加规则如下：</p>

<ul>
	<li>在&nbsp;<strong><span style="box-sizing: border-box; margin: 0px; padding: 0px;">奇数天</span></strong>（按 <strong>1 开始计数</strong>）你会增加 <code>Z</code> 的重量。</li>
	<li>在&nbsp;<strong>偶数天</strong>，你会增加 <code>Y</code> 的重量。</li>
</ul>

<p>请你设计吃掉&nbsp;<strong>所有&nbsp;</strong>披萨的最优方案，并计算你可以增加的&nbsp;<strong>最大&nbsp;</strong>总重量。</p>

<p><strong>注意：</strong>保证 <code>n</code> 是 4 的倍数，并且每个披萨只吃一次。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">pizzas = [1,2,3,4,5,6,7,8]</span></p>

<p><strong>输出：</strong> <span class="example-io">14</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 1 天，你吃掉下标为 <code>[1, 2, 4, 7] = [2, 3, 5, 8]</code> 的披萨。你增加的重量为 8。</li>
	<li>第 2 天，你吃掉下标为 <code>[0, 3, 5, 6] = [1, 4, 6, 7]</code> 的披萨。你增加的重量为 6。</li>
</ul>

<p>吃掉所有披萨后，你增加的总重量为 <code>8 + 6 = 14</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">pizzas = [2,1,1,1,1,1,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第 1 天，你吃掉下标为 <code>[4, 5, 6, 0] = [1, 1, 1, 2]</code> 的披萨。你增加的重量为 2。</li>
	<li>第 2 天，你吃掉下标为 <code>[1, 2, 3, 7] = [1, 1, 1, 1]</code> 的披萨。你增加的重量为 1。</li>
</ul>

<p>吃掉所有披萨后，你增加的总重量为 <code>2 + 1 = 3</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>4 &lt;= n == pizzas.length &lt;= 2 * 10<sup><span style="font-size: 10.8333px;">5</span></sup></code></li>
	<li><code>1 &lt;= pizzas[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> 是 4 的倍数。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

根据题目描述，我们每天可以吃 $4$ 个披萨。在奇数天，我们可以得到这 $4$ 个披萨中的最大值，而在偶数天，我们可以得到这 $4$ 个披萨中的第二大值。

因此，我们可以将披萨按重量从小到大排序，一共能吃 $\textit{days} = n / 4$ 天，那么一共有 $\textit{odd} = (\textit{days} + 1) / 2$ 天是奇数天，一共有 $\textit{even} = \textit{days} - \textit{odd}$ 天是偶数天。

考虑奇数天，我们可以选择最大的 $\textit{odd}$ 个披萨，以及最小的 $\textit{odd} \times 3$ 个披萨，增加的重量为 $\sum_{i = n - \textit{odd}}^{n - 1} \textit{pizzas}[i]$。

考虑偶数天，我们在剩余的披萨中，每次贪心地选择最大的两个披萨，以及最小的两个披萨，增加的重量为 $\sum_{i = n - \textit{odd} - 2}^{n - \textit{odd} - 2 \times \textit{even}} \textit{pizzas}[i]$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{pizzas}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxWeight(self, pizzas: List[int]) -> int:
        days = len(pizzas) // 4
        pizzas.sort()
        odd = (days + 1) // 2
        even = days - odd
        ans = sum(pizzas[-odd:])
        i = len(pizzas) - odd - 2
        for _ in range(even):
            ans += pizzas[i]
            i -= 2
        return ans
```

#### Java

```java
class Solution {
    public long maxWeight(int[] pizzas) {
        int n = pizzas.length;
        int days = n / 4;
        Arrays.sort(pizzas);
        int odd = (days + 1) / 2;
        int even = days / 2;
        long ans = 0;
        for (int i = n - odd; i < n; ++i) {
            ans += pizzas[i];
        }
        for (int i = n - odd - 2; even > 0; --even) {
            ans += pizzas[i];
            i -= 2;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxWeight(vector<int>& pizzas) {
        int n = pizzas.size();
        int days = pizzas.size() / 4;
        ranges::sort(pizzas);
        int odd = (days + 1) / 2;
        int even = days - odd;
        long long ans = accumulate(pizzas.begin() + n - odd, pizzas.end(), 0LL);
        for (int i = n - odd - 2; even; --even) {
            ans += pizzas[i];
            i -= 2;
        }
        return ans;
    }
};
```

#### Go

```go
func maxWeight(pizzas []int) (ans int64) {
	n := len(pizzas)
	days := n / 4
	sort.Ints(pizzas)
	odd := (days + 1) / 2
	even := days - odd
	for i := n - odd; i < n; i++ {
		ans += int64(pizzas[i])
	}
	for i := n - odd - 2; even > 0; even-- {
		ans += int64(pizzas[i])
		i -= 2
	}
	return
}
```

#### TypeScript

```ts
function maxWeight(pizzas: number[]): number {
    const n = pizzas.length;
    const days = n >> 2;
    pizzas.sort((a, b) => a - b);
    const odd = (days + 1) >> 1;
    let even = days - odd;
    let ans = 0;
    for (let i = n - odd; i < n; ++i) {
        ans += pizzas[i];
    }
    for (let i = n - odd - 2; even; --even) {
        ans += pizzas[i];
        i -= 2;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
