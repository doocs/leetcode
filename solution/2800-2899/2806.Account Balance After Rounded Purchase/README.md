---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2806.Account%20Balance%20After%20Rounded%20Purchase/README.md
rating: 1214
source: 第 110 场双周赛 Q1
tags:
    - 数学
---

# [2806. 取整购买后的账户余额](https://leetcode.cn/problems/account-balance-after-rounded-purchase)

[English Version](/solution/2800-2899/2806.Account%20Balance%20After%20Rounded%20Purchase/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一开始，你的银行账户里有&nbsp;<code>100</code>&nbsp;块钱。</p>

<p>给你一个整数<code>purchaseAmount</code>&nbsp;，它表示你在一次购买中愿意支出的金额。</p>

<p>在一个商店里，你进行一次购买，实际支出的金额会向 <strong>最近</strong>&nbsp;的&nbsp;<code>10</code>&nbsp;的 <strong>倍数</strong>&nbsp;取整。换句话说，你实际会支付一个&nbsp;<strong>非负</strong>&nbsp;金额&nbsp;<code>roundedAmount</code>&nbsp;，满足&nbsp;<code>roundedAmount</code>&nbsp;是&nbsp;<code>10</code>&nbsp;的倍数且&nbsp;<code>abs(roundedAmount - purchaseAmount)</code>&nbsp;的值 <strong>最小</strong>&nbsp;。</p>

<p>如果存在多于一个最接近的 <code>10</code>&nbsp;的倍数，<strong>较大的倍数</strong>&nbsp;是你的实际支出金额。</p>

<p>请你返回一个整数，表示你在愿意支出金额为<em>&nbsp;</em><code>purchaseAmount</code><em>&nbsp;</em>块钱的前提下，购买之后剩下的余额。</p>

<p><strong>注意：</strong> <code>0</code>&nbsp;也是&nbsp;<code>10</code>&nbsp;的倍数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>purchaseAmount = 9
<b>输出：</b>90
<b>解释：</b>这个例子中，最接近 9 的 10 的倍数是 10 。所以你的账户余额为 100 - 10 = 90 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>purchaseAmount = 15
<b>输出：</b>80
<b>解释：</b>这个例子中，有 2 个最接近 15 的 10 的倍数：10 和 20，较大的数 20 是你的实际开销。
所以你的账户余额为 100 - 20 = 80 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= purchaseAmount &lt;= 100</code></li>
</ul>

## 解法

### 方法一：枚举 + 模拟

我们在 $[0, 100]$ 的范围内枚举所有的 $10$ 的倍数，然后找到与 `purchaseAmount` 最接近的那个数，记为 $x$，那么答案就是 $100 - x$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def accountBalanceAfterPurchase(self, purchaseAmount: int) -> int:
        diff, x = 100, 0
        for y in range(100, -1, -10):
            if (t := abs(y - purchaseAmount)) < diff:
                diff = t
                x = y
        return 100 - x
```

```java
class Solution {
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int diff = 100, x = 0;
        for (int y = 100; y >= 0; y -= 10) {
            int t = Math.abs(y - purchaseAmount);
            if (t < diff) {
                diff = t;
                x = y;
            }
        }
        return 100 - x;
    }
}
```

```cpp
class Solution {
public:
    int accountBalanceAfterPurchase(int purchaseAmount) {
        int diff = 100, x = 0;
        for (int y = 100; y >= 0; y -= 10) {
            int t = abs(y - purchaseAmount);
            if (t < diff) {
                diff = t;
                x = y;
            }
        }
        return 100 - x;
    }
};
```

```go
func accountBalanceAfterPurchase(purchaseAmount int) int {
	diff, x := 100, 0
	for y := 100; y >= 0; y -= 10 {
		t := abs(y - purchaseAmount)
		if t < diff {
			diff = t
			x = y
		}
	}
	return 100 - x
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function accountBalanceAfterPurchase(purchaseAmount: number): number {
    let [diff, x] = [100, 0];
    for (let y = 100; y >= 0; y -= 10) {
        const t = Math.abs(y - purchaseAmount);
        if (t < diff) {
            diff = t;
            x = y;
        }
    }
    return 100 - x;
}
```

<!-- tabs:end -->

<!-- end -->
