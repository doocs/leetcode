---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2240.Number%20of%20Ways%20to%20Buy%20Pens%20and%20Pencils/README.md
rating: 1399
source: 第 76 场双周赛 Q2
tags:
    - 数学
    - 枚举
---

<!-- problem:start -->

# [2240. 买钢笔和铅笔的方案数](https://leetcode.cn/problems/number-of-ways-to-buy-pens-and-pencils)

[English Version](/solution/2200-2299/2240.Number%20of%20Ways%20to%20Buy%20Pens%20and%20Pencils/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>total</code>&nbsp;，表示你拥有的总钱数。同时给你两个整数&nbsp;<code>cost1</code> 和&nbsp;<code>cost2</code>&nbsp;，分别表示一支钢笔和一支铅笔的价格。你可以花费你部分或者全部的钱，去买任意数目的两种笔。</p>

<p>请你返回购买钢笔和铅笔的&nbsp;<strong>不同方案数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>total = 20, cost1 = 10, cost2 = 5
<b>输出：</b>9
<b>解释：</b>一支钢笔的价格为 10 ，一支铅笔的价格为 5 。
- 如果你买 0 支钢笔，那么你可以买 0 ，1 ，2 ，3 或者 4 支铅笔。
- 如果你买 1 支钢笔，那么你可以买 0 ，1 或者 2 支铅笔。
- 如果你买 2 支钢笔，那么你没法买任何铅笔。
所以买钢笔和铅笔的总方案数为 5 + 3 + 1 = 9 种。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>total = 5, cost1 = 10, cost2 = 10
<b>输出：</b>1
<b>解释：</b>钢笔和铅笔的价格都为 10 ，都比拥有的钱数多，所以你没法购买任何文具。所以只有 1 种方案：买 0 支钢笔和 0 支铅笔。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= total, cost1, cost2 &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举购买钢笔的数量 $x$，对于每个 $x$，我们最多可以购买铅笔的数量为 $\frac{\textit{total} - x \times \textit{cost1}}{\textit{cost2}}$，那么数量加 $1$ 即为 $x$ 的方案数。我们累加所有的 $x$ 的方案数，即为答案。

时间复杂度 $O(\frac{total}{cost1})$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def waysToBuyPensPencils(self, total: int, cost1: int, cost2: int) -> int:
        ans = 0
        for x in range(total // cost1 + 1):
            y = (total - (x * cost1)) // cost2 + 1
            ans += y
        return ans
```

#### Java

```java
class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ans = 0;
        for (int x = 0; x <= total / cost1; ++x) {
            int y = (total - x * cost1) / cost2 + 1;
            ans += y;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long long ans = 0;
        for (int x = 0; x <= total / cost1; ++x) {
            int y = (total - x * cost1) / cost2 + 1;
            ans += y;
        }
        return ans;
    }
};
```

#### Go

```go
func waysToBuyPensPencils(total int, cost1 int, cost2 int) (ans int64) {
	for x := 0; x <= total/cost1; x++ {
		y := (total-x*cost1)/cost2 + 1
		ans += int64(y)
	}
	return
}
```

#### TypeScript

```ts
function waysToBuyPensPencils(total: number, cost1: number, cost2: number): number {
    let ans = 0;
    for (let x = 0; x <= Math.floor(total / cost1); ++x) {
        const y = Math.floor((total - x * cost1) / cost2) + 1;
        ans += y;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn ways_to_buy_pens_pencils(total: i32, cost1: i32, cost2: i32) -> i64 {
        let mut ans: i64 = 0;
        for pen in 0..=total / cost1 {
            ans += (((total - pen * cost1) / cost2) as i64) + 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
