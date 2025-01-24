---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2412.Minimum%20Money%20Required%20Before%20Transactions/README.md
rating: 2092
source: 第 87 场双周赛 Q4
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [2412. 完成所有交易的初始最少钱数](https://leetcode.cn/problems/minimum-money-required-before-transactions)

[English Version](/solution/2400-2499/2412.Minimum%20Money%20Required%20Before%20Transactions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code><font face="monospace">transactions</font></code>，其中<code>transactions[i] = [cost<sub>i</sub>, cashback<sub>i</sub>]</code>&nbsp;。</p>

<p>数组描述了若干笔交易。其中每笔交易必须以 <strong>某种顺序</strong> 恰好完成一次。在任意一个时刻，你有一定数目的钱&nbsp;<code>money</code>&nbsp;，为了完成交易&nbsp;<code>i</code>&nbsp;，<code>money &gt;= cost<sub>i</sub></code>&nbsp;这个条件必须为真。执行交易后，你的钱数&nbsp;<code>money</code> 变成&nbsp;<code>money - cost<sub>i</sub> + cashback<sub>i</sub></code><sub>&nbsp;</sub>。</p>

<p>请你返回 <strong>任意一种</strong> 交易顺序下，你都能完成所有交易的最少钱数<em>&nbsp;</em><code>money</code>&nbsp;是多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>transactions = [[2,1],[5,0],[4,2]]
<b>输出：</b>10
<strong>解释：
</strong>刚开始 money = 10 ，交易可以以任意顺序进行。
可以证明如果 money &lt; 10 ，那么某些交易无法进行。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>transactions = [[3,0],[0,3]]
<b>输出：</b>3
<strong>解释：</strong>
- 如果交易执行的顺序是 [[3,0],[0,3]] ，完成所有交易需要的最少钱数是 3 。
- 如果交易执行的顺序是 [[0,3],[3,0]] ，完成所有交易需要的最少钱数是 0 。
所以，刚开始钱数为 3 ，任意顺序下交易都可以全部完成。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= transactions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>transactions[i].length == 2</code></li>
	<li><code>0 &lt;= cost<sub>i</sub>, cashback<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们先累计所有负收益，记为 $s$。然后枚举每个交易 $\text{transactions}[i] = [a, b]$ 作为最后一个交易，如果 $a > b$，说明当前的交易是亏钱的，而这个交易在此前我们累计负收益的时候，已经被计算，因此取 $s + b$ 更新答案；否则，取 $s + a$ 更新答案。

时间复杂度 $O(n)$，其中 $n$ 为交易数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumMoney(self, transactions: List[List[int]]) -> int:
        s = sum(max(0, a - b) for a, b in transactions)
        ans = 0
        for a, b in transactions:
            if a > b:
                ans = max(ans, s + b)
            else:
                ans = max(ans, s + a)
        return ans
```

#### Java

```java
class Solution {
    public long minimumMoney(int[][] transactions) {
        long s = 0;
        for (var e : transactions) {
            s += Math.max(0, e[0] - e[1]);
        }
        long ans = 0;
        for (var e : transactions) {
            if (e[0] > e[1]) {
                ans = Math.max(ans, s + e[1]);
            } else {
                ans = Math.max(ans, s + e[0]);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumMoney(vector<vector<int>>& transactions) {
        long long s = 0, ans = 0;
        for (auto& e : transactions) {
            s += max(0, e[0] - e[1]);
        }
        for (auto& e : transactions) {
            if (e[0] > e[1]) {
                ans = max(ans, s + e[1]);
            } else {
                ans = max(ans, s + e[0]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumMoney(transactions [][]int) int64 {
	s, ans := 0, 0
	for _, e := range transactions {
		s += max(0, e[0]-e[1])
	}
	for _, e := range transactions {
		if e[0] > e[1] {
			ans = max(ans, s+e[1])
		} else {
			ans = max(ans, s+e[0])
		}
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function minimumMoney(transactions: number[][]): number {
    const s = transactions.reduce((acc, [a, b]) => acc + Math.max(0, a - b), 0);
    let ans = 0;
    for (const [a, b] of transactions) {
        if (a > b) {
            ans = Math.max(ans, s + b);
        } else {
            ans = Math.max(ans, s + a);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_money(transactions: Vec<Vec<i32>>) -> i64 {
        let mut s: i64 = 0;
        for transaction in &transactions {
            let (a, b) = (transaction[0], transaction[1]);
            s += (a - b).max(0) as i64;
        }
        let mut ans = 0;
        for transaction in &transactions {
            let (a, b) = (transaction[0], transaction[1]);
            if a > b {
                ans = ans.max(s + b as i64);
            } else {
                ans = ans.max(s + a as i64);
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} transactions
 * @return {number}
 */
var minimumMoney = function (transactions) {
    const s = transactions.reduce((acc, [a, b]) => acc + Math.max(0, a - b), 0);
    let ans = 0;
    for (const [a, b] of transactions) {
        if (a > b) {
            ans = Math.max(ans, s + b);
        } else {
            ans = Math.max(ans, s + a);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
