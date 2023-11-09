# [7. Reverse Integer](https://leetcode.com/problems/reverse-integer)

[中文文档](/solution/0000-0099/0007.Reverse%20Integer/README.md)

## Description

<p>Given a signed 32-bit integer <code>x</code>, return <code>x</code><em> with its digits reversed</em>. If reversing <code>x</code> causes the value to go outside the signed 32-bit integer range <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>, then return <code>0</code>.</p>

<p><strong>Assume the environment does not allow you to store 64-bit integers (signed or unsigned).</strong></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 123
<strong>Output:</strong> 321
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = -123
<strong>Output:</strong> -321
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> x = 120
<strong>Output:</strong> 21
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= x &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

**Solution 1: Mathematical**

Let $mi$ and $mx$ be $-2^{31}$ and $2^{31} - 1$ respectively. The reversed result $ans$ needs to satisfy $mi \le ans \le mx$.

We can get the last digit $y$ of $x$ by repeatedly taking the remainder of $x$ by $10$, and then add $y$ to the end of $ans$. Before adding $y$, we need to determine whether $ans$ will overflow. That is, whether $ans \times 10 + y$ is within the range $[mi, mx]$.

If $x \gt 0$, then $ans \times 10 + y \leq mx$ should be satisfied. That is, $ans \times 10 + y \leq \left \lfloor \frac{mx}{10} \right \rfloor \times 10 + 7$. Rearrange to get $(ans - \left \lfloor \frac{mx}{10} \right \rfloor) \times 10 \leq 7 - y$.

The following conditions need to be satisfied for the above inequality to hold:

-   When $ans \lt \left \lfloor \frac{mx}{10} \right \rfloor$, the inequality obviously holds;
-   When $ans = \left \lfloor \frac{mx}{10} \right \rfloor$, the necessary and sufficient conditions for the above inequality to hold are $y \leq 7$. If $ans = \left \lfloor \frac{mx}{10} \right \rfloor$ and can still continue to add digits, it means that the current digit is the most significant digit. Therefore, $y$ must be less than or equal to $2$, so the inequality must hold;
-   When $ans \gt \left \lfloor \frac{mx}{10} \right \rfloor$, the inequality obviously does not hold.

Therefore, when $x \gt 0$, the necessary and sufficient conditions for the inequality to hold are $ans \leq \left \lfloor \frac{mx}{10} \right \rfloor$.

Similarly, when $x \lt 0$, the necessary and sufficient conditions for the inequality to hold are $ans \geq \left \lfloor \frac{mi}{10} \right \rfloor$.

Therefore, we can determine whether $ans$ will overflow by determining whether $ans$ is within the range $[\left \lfloor \frac{mi}{10} \right \rfloor, \left \lfloor \frac{mx}{10} \right \rfloor]$. If so, return $0$. Otherwise, add $y$ to the end of $ans$, and then remove the last digit of $x$, that is, $x \gets \left \lfloor \frac{x}{10} \right \rfloor$.

Time complexity $O(\log |x|)$, where $|x|$ is the absolute value of $x$. Space complexity $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverse(self, x: int) -> int:
        ans = 0
        mi, mx = -(2**31), 2**31 - 1
        while x:
            if ans < mi // 10 + 1 or ans > mx // 10:
                return 0
            y = x % 10
            if x < 0 and y > 0:
                y -= 10
            ans = ans * 10 + y
            x = (x - y) // 10
        return ans
```

### **Java**

```java
class Solution {
    public int reverse(int x) {
        int ans = 0;
        for (; x != 0; x /= 10) {
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int reverse(int x) {
        int ans = 0;
        for (; x; x /= 10) {
            if (ans < INT_MIN / 10 || ans > INT_MAX / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }
};
```

### **Go**

```go
func reverse(x int) (ans int) {
	for ; x != 0; x /= 10 {
		if ans < math.MinInt32/10 || ans > math.MaxInt32/10 {
			return 0
		}
		ans = ans*10 + x%10
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @return {number}
 */
var reverse = function (x) {
    const mi = -(2 ** 31);
    const mx = 2 ** 31 - 1;
    let ans = 0;
    for (; x != 0; x = ~~(x / 10)) {
        if (ans < ~~(mi / 10) || ans > ~~(mx / 10)) {
            return 0;
        }
        ans = ans * 10 + (x % 10);
    }
    return ans;
};
```

### **C**

```c
int reverse(int x) {
    int ans = 0;
    for (; x != 0; x /= 10) {
        if (ans > INT_MAX / 10 || ans < INT_MIN / 10) {
            return 0;
        }
        ans = ans * 10 + x % 10;
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn reverse(mut x: i32) -> i32 {
        let is_minus = x < 0;
        match x.abs().to_string().chars().rev().collect::<String>().parse::<i32>() {
            Ok(x) => x * (if is_minus { -1 } else { 1 }),
            Err(_) => 0,
        }
    }
}
```

### **C#**

```cs
public class Solution {
    public int Reverse(int x) {
        int ans = 0;
        for (; x != 0; x /= 10) {
            if (ans < int.MinValue / 10 || ans > int.MaxValue / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
