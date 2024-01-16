# [2806. Account Balance After Rounded Purchase](https://leetcode.com/problems/account-balance-after-rounded-purchase)

[中文文档](/solution/2800-2899/2806.Account%20Balance%20After%20Rounded%20Purchase/README.md)

## Description

<p>Initially, you have a bank account balance of <code>100</code> dollars.</p>

<p>You are given an integer <code>purchaseAmount</code> representing the amount you will spend on a purchase in dollars.</p>

<p>At the store where you will make the purchase, the purchase amount is rounded to the <strong>nearest multiple</strong> of <code>10</code>. In other words, you pay a <strong>non-negative</strong> amount, <code>roundedAmount</code>, such that <code>roundedAmount</code> is a multiple of <code>10</code> and <code>abs(roundedAmount - purchaseAmount)</code> is <strong>minimized</strong>.</p>

<p>If there is more than one nearest multiple of <code>10</code>, the <strong>largest multiple</strong> is chosen.</p>

<p>Return <em>an integer denoting your account balance after making a purchase worth </em><code>purchaseAmount</code><em> dollars from the store.</em></p>

<p><strong>Note:</strong> <code>0</code> is considered to be a multiple of <code>10</code> in this problem.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> purchaseAmount = 9
<strong>Output:</strong> 90
<strong>Explanation:</strong> In this example, the nearest multiple of 10 to 9 is 10. Hence, your account balance becomes 100 - 10 = 90.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> purchaseAmount = 15
<strong>Output:</strong> 80
<strong>Explanation:</strong> In this example, there are two nearest multiples of 10 to 15: 10 and 20. So, the larger multiple, 20, is chosen.
Hence, your account balance becomes 100 - 20 = 80.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= purchaseAmount &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Enumeration + Simulation

We enumerate all multiples of 10 within the range $[0, 100]$, and find the one that is closest to `purchaseAmount`, denoted as $x$. The answer is $100 - x$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

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
