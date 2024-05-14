# [1281. Subtract the Product and Sum of Digits of an Integer](https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer)

[中文文档](/solution/1200-1299/1281.Subtract%20the%20Product%20and%20Sum%20of%20Digits%20of%20an%20Integer/README.md)

<!-- tags:Math -->

<!-- difficulty:Easy -->

## Description

Given an integer number <code>n</code>, return the difference between the product of its digits and the sum of its digits.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 234
<strong>Output:</strong> 15 
<b>Explanation:</b> 
Product of digits = 2 * 3 * 4 = 24 
Sum of digits = 2 + 3 + 4 = 9 
Result = 24 - 9 = 15
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4421
<strong>Output:</strong> 21
<b>Explanation: 
</b>Product of digits = 4 * 4 * 2 * 1 = 32 
Sum of digits = 4 + 4 + 2 + 1 = 11 
Result = 32 - 11 = 21
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
</ul>

## Solutions

### Solution 1: Simulation

We use two variables $x$ and $y$ to record the product of the digits and the sum of the digits respectively. At the beginning, $x=1,y=0$.

When $n \gt 0$, each time we take the $mod$ of $n$ by $10$ to get the current digit $v$, and continue the next loop by dividing $n$ by $10$. In each loop, we update $x = x \times v$, $y = y + v$.

Finally, we return $x - y$.

The time complexity is $O(\log n)$, where $n$ is the given integer. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        x, y = 1, 0
        while n:
            n, v = divmod(n, 10)
            x *= v
            y += v
        return x - y
```

```java
class Solution {
    public int subtractProductAndSum(int n) {
        int x = 1, y = 0;
        for (; n > 0; n /= 10) {
            int v = n % 10;
            x *= v;
            y += v;
        }
        return x - y;
    }
}
```

```cpp
class Solution {
public:
    int subtractProductAndSum(int n) {
        int x = 1, y = 0;
        for (; n; n /= 10) {
            int v = n % 10;
            x *= v;
            y += v;
        }
        return x - y;
    }
};
```

```go
func subtractProductAndSum(n int) int {
	x, y := 1, 0
	for ; n > 0; n /= 10 {
		v := n % 10
		x *= v
		y += v
	}
	return x - y
}
```

```ts
function subtractProductAndSum(n: number): number {
    let [x, y] = [1, 0];
    for (; n > 0; n = Math.floor(n / 10)) {
        const v = n % 10;
        x *= v;
        y += v;
    }
    return x - y;
}
```

```rust
impl Solution {
    pub fn subtract_product_and_sum(mut n: i32) -> i32 {
        let mut x = 1;
        let mut y = 0;
        while n != 0 {
            let v = n % 10;
            n /= 10;
            x *= v;
            y += v;
        }
        x - y
    }
}
```

```cs
public class Solution {
    public int SubtractProductAndSum(int n) {
        int x = 1;
        int y = 0;
        for (; n > 0; n /= 10) {
            int v = n % 10;
            x *= v;
            y += v;
        }
        return x - y;
    }
}
```

```c
int subtractProductAndSum(int n) {
    int x = 1;
    int y = 0;
    for (; n > 0; n /= 10) {
        int v = n % 10;
        x *= v;
        y += v;
    }
    return x - y;
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        nums = list(map(int, str(n)))
        return prod(nums) - sum(nums)
```

<!-- tabs:end -->

<!-- end -->
