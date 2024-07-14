---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2806.Account%20Balance%20After%20Rounded%20Purchase/README_EN.md
rating: 1214
source: Biweekly Contest 110 Q1
tags:
    - Math
---

<!-- problem:start -->

# [2806. Account Balance After Rounded Purchase](https://leetcode.com/problems/account-balance-after-rounded-purchase)

[中文文档](/solution/2800-2899/2806.Account%20Balance%20After%20Rounded%20Purchase/README.md)

## Description

<!-- description:start -->

<p>Initially, you have a bank account balance of <strong>100</strong> dollars.</p>

<p>You are given an integer <code>purchaseAmount</code> representing the amount you will spend on a purchase in dollars, in other words, its price.</p>

<p>When making the purchase, first the <code>purchaseAmount</code> <strong>is rounded to the nearest multiple of 10</strong>. Let us call this value <code>roundedAmount</code>. Then, <code>roundedAmount</code> dollars are removed from your bank account.</p>

<p>Return an integer denoting your final bank account balance after this purchase.</p>

<p><strong>Notes:</strong></p>

<ul>
	<li>0 is considered to be a multiple of 10 in this problem.</li>
	<li>When rounding, 5 is rounded upward (5 is rounded to 10, 15 is rounded to 20, 25 to 30, and so on).</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">purchaseAmount = 9</span></p>

<p><strong>Output:</strong> <span class="example-io">90</span></p>

<p><strong>Explanation:</strong></p>

<p>The nearest multiple of 10 to 9 is 10. So your account balance becomes 100 - 10 = 90.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">purchaseAmount = 15</span></p>

<p><strong>Output:</strong> <span class="example-io">80</span></p>

<p><strong>Explanation:</strong></p>

<p>The nearest multiple of 10 to 15 is 20. So your account balance becomes 100 - 20 = 80.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">purchaseAmount = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">90</span></p>

<p><strong>Explanation:</strong></p>

<p>10 is a multiple of 10 itself. So your account balance becomes 100 - 10 = 90.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= purchaseAmount &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Simulation

We enumerate all multiples of 10 within the range $[0, 100]$, and find the one that is closest to `purchaseAmount`, denoted as $x$. The answer is $100 - x$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
