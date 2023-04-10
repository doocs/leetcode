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

**Approach 1: Pop and Push Digits & Check before Overflow**

Time complexity $O(log|x|)$, Space complexity $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverse(self, x: int) -> int:
        ans = 0
        mi, mx = -2**31, 2**31 - 1
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
        match x
            .abs()
            .to_string()
            .chars()
            .rev()
            .collect::<String>()
            .parse::<i32>()
        {
            Ok(x) => x * if is_minus { -1 } else { 1 },
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
