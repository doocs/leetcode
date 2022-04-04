# [1281. Subtract the Product and Sum of Digits of an Integer](https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer)

[中文文档](/solution/1200-1299/1281.Subtract%20the%20Product%20and%20Sum%20of%20Digits%20of%20an%20Integer/README.md)

## Description

Given an integer number <code>n</code>, return the difference between the product of its digits and the sum of its digits.

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 234
<strong>Output:</strong> 15 
<b>Explanation:</b> 
Product of digits = 2 * 3 * 4 = 24 
Sum of digits = 2 + 3 + 4 = 9 
Result = 24 - 9 = 15
</pre>

<p><strong>Example 2:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        s, p = 0, 1
        while n:
            t = n % 10
            n //= 10
            s += t
            p *= t
        return p - s
```

### **Java**

```java
class Solution {
    public int subtractProductAndSum(int n) {
        int s = 0, p = 1;
        while (n != 0) {
            int t = n % 10;
            n /= 10;
            s += t;
            p *= t;
        }
        return p - s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subtractProductAndSum(int n) {
        int s = 0, p = 1;
        while (n) {
            int t = n % 10;
            n /= 10;
            s += t;
            p *= t;
        }
        return p - s;
    }
};
```

### **Go**

```go
func subtractProductAndSum(n int) int {
	s, p := 0, 1
	for n != 0 {
		t := n % 10
		n /= 10
		s += t
		p *= t
	}
	return p - s
}
```

### **Rust**

```rust
impl Solution {
    pub fn subtract_product_and_sum(mut n: i32) -> i32 {
        let mut mul = 1;
        let mut sum = 0;
        while n != 0 {
            let num = n % 10;
            n /= 10;
            mul *= num;
            sum += num;
        }
        mul - sum
    }
}
```

### **...**

```

```

<!-- tabs:end -->
