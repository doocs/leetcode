# [1359. Count All Valid Pickup and Delivery Options](https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options)

[中文文档](/solution/1300-1399/1359.Count%20All%20Valid%20Pickup%20and%20Delivery%20Options/README.md)

## Description

<p>Given <code>n</code> orders, each order consists of a pickup and a delivery service.</p>

<p>Count all valid pickup/delivery possible sequences such that delivery(i) is always after of&nbsp;pickup(i).&nbsp;</p>

<p>Since the answer&nbsp;may be too large,&nbsp;return it modulo&nbsp;10^9 + 7.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> All possible orders: 
(P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 90
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i]$ as the number of all valid pickup/delivery sequences for $i$ orders. Initially, $f[1] = 1$.

We can choose any of these $i$ orders as the last delivery order $D_i$, then its pickup order $P_i$ can be at any position in the previous $2 \times i - 1$, and the number of pickup/delivery sequences for the remaining $i - 1$ orders is $f[i - 1]$, so $f[i]$ can be expressed as:

$$
f[i] = i \times (2 \times i - 1) \times f[i - 1]
$$

The final answer is $f[n]$.

We notice that the value of $f[i]$ is only related to $f[i - 1]$, so we can use a variable instead of an array to reduce the space complexity.

The time complexity is $O(n)$, where $n$ is the number of orders. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def countOrders(self, n: int) -> int:
        mod = 10**9 + 7
        f = 1
        for i in range(2, n + 1):
            f = (f * i * (2 * i - 1)) % mod
        return f
```

```java
class Solution {
    public int countOrders(int n) {
        final int mod = (int) 1e9 + 7;
        long f = 1;
        for (int i = 2; i <= n; ++i) {
            f = f * i * (2 * i - 1) % mod;
        }
        return (int) f;
    }
}
```

```cpp
class Solution {
public:
    int countOrders(int n) {
        const int mod = 1e9 + 7;
        long long f = 1;
        for (int i = 2; i <= n; ++i) {
            f = f * i * (2 * i - 1) % mod;
        }
        return f;
    }
};
```

```go
func countOrders(n int) int {
	const mod = 1e9 + 7
	f := 1
	for i := 2; i <= n; i++ {
		f = f * i * (2*i - 1) % mod
	}
	return f
}
```

```rust
const MOD: i64 = (1e9 as i64) + 7;

impl Solution {
    #[allow(dead_code)]
    pub fn count_orders(n: i32) -> i32 {
        let mut f = 1;
        for i in 2..=n as i64 {
            f = (i * (2 * i - 1) * f) % MOD;
        }
        f as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
