---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3457.Eat%20Pizzas%21/README_EN.md
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [3457. Eat Pizzas!](https://leetcode.com/problems/eat-pizzas)

[中文文档](/solution/3400-3499/3457.Eat%20Pizzas%21/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>pizzas</code> of size <code>n</code>, where <code>pizzas[i]</code> represents the weight of the <code>i<sup>th</sup></code> pizza. Every day, you eat <strong>exactly</strong> 4 pizzas. Due to your incredible metabolism, when you eat pizzas of weights <code>W</code>, <code>X</code>, <code>Y</code>, and <code>Z</code>, where <code>W &lt;= X &lt;= Y &lt;= Z</code>, you gain the weight of only 1 pizza!</p>

<ul>
	<li>On <strong><span style="box-sizing: border-box; margin: 0px; padding: 0px;">odd-numbered</span></strong> days <strong>(1-indexed)</strong>, you gain a weight of <code>Z</code>.</li>
	<li>On <strong>even-numbered</strong> days, you gain a weight of <code>Y</code>.</li>
</ul>

<p>Find the <strong>maximum</strong> total weight you can gain by eating <strong>all</strong> pizzas optimally.</p>

<p><strong>Note</strong>: It is guaranteed that <code>n</code> is a multiple of 4, and each pizza can be eaten only once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">pizzas = [1,2,3,4,5,6,7,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>On day 1, you eat pizzas at indices <code>[1, 2, 4, 7] = [2, 3, 5, 8]</code>. You gain a weight of 8.</li>
	<li>On day 2, you eat pizzas at indices <code>[0, 3, 5, 6] = [1, 4, 6, 7]</code>. You gain a weight of 6.</li>
</ul>

<p>The total weight gained after eating all the pizzas is <code>8 + 6 = 14</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">pizzas = [2,1,1,1,1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>On day 1, you eat pizzas at indices <code>[4, 5, 6, 0] = [1, 1, 1, 2]</code>. You gain a weight of 2.</li>
	<li>On day 2, you eat pizzas at indices <code>[1, 2, 3, 7] = [1, 1, 1, 1]</code>. You gain a weight of 1.</li>
</ul>

<p>The total weight gained after eating all the pizzas is <code>2 + 1 = 3.</code></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= n == pizzas.length &lt;= 2 * 10<sup><span style="font-size: 10.8333px;">5</span></sup></code></li>
	<li><code>1 &lt;= pizzas[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> is a multiple of 4.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

According to the problem description, we can eat $4$ pizzas each day. On odd days, we get the maximum value among these $4$ pizzas, and on even days, we get the second largest value among these $4$ pizzas.

Therefore, we can sort the pizzas by weight in ascending order. We can eat for $\textit{days} = n / 4$ days, with $\textit{odd} = (\textit{days} + 1) / 2$ days being odd days and $\textit{even} = \textit{days} - \textit{odd}$ days being even days.

For odd days, we can choose the largest $\textit{odd}$ pizzas and the smallest $\textit{odd} \times 3$ pizzas, with the increased weight being $\sum_{i = n - \textit{odd}}^{n - 1} \textit{pizzas}[i]$.

For even days, among the remaining pizzas, we greedily choose the largest two pizzas and the smallest two pizzas each time, with the increased weight being $\sum_{i = n - \textit{odd} - 2}^{n - \textit{odd} - 2 \times \textit{even}} \textit{pizzas}[i]$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $\textit{pizzas}$.

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
