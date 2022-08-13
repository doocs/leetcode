# [7. Reverse Integer](https://leetcode.com/problems/reverse-integer)

[中文文档](/solution/0000-0099/0007.Reverse%20Integer/README.md)

## Description

<p>Given a signed 32-bit integer <code>x</code>, return <code>x</code><em> with its digits reversed</em>. If reversing <code>x</code> causes the value to go outside the signed 32-bit integer range <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>, then return <code>0</code>.</p>

<p><strong>Assume the environment does not allow you to store 64-bit integers (signed or unsigned).</strong></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 123
<strong>Output:</strong> 321
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = -123
<strong>Output:</strong> -321
</pre>

<p><strong>Example 3:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverse(self, x: int) -> int:
        y = int(str(abs(x))[::-1])
        res = -y if x < 0 else y
        return 0 if res < -(2**31) or res > 2**31 - 1 else res
```

### **Java**

```java
class Solution {
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + (x % 10);
            x /= 10;
        }
        return res < Integer.MIN_VALUE || res > Integer.MAX_VALUE ? 0 : (int) res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int reverse(int x) {
        long long ans = 0;
        while (x) {
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return ans < INT_MIN || ans > INT_MAX ? 0 : ans;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @return {number}
 */
var reverse = function (x) {
    let res = 0;
    while (x) {
        res = res * 10 + (x % 10);
        x = ~~(x / 10);
    }
    return res < Math.pow(-2, 31) || res > Math.pow(2, 31) - 1 ? 0 : res;
};
```

### **C**

```c
int reverse(int x) {
    int res = 0;
    while (x != 0) {
        if (res > INT_MAX / 10 || res < INT_MIN / 10) {
            return 0;
        }
        res = res * 10 + x % 10;
        x /= 10;
    }
    return res;
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

### **Go**

```go
func reverse(x int) int {
	slot := make([]int, 11)
	count := 0
	for x != 0 {
		n := x % 10
		slot[count] = n
		count++
		x /= 10
	}
	result := 0
	flag := true
	for i := 0; i < count; i++ {
		if flag && slot[i] == 0 {
			continue
		}
		flag = false
		result = 10*result + slot[i]
	}
	if result > math.MaxInt32 || result < math.MinInt32 {
		return 0
	}
	return result
}
```

### **C#**

```cs
public class Solution {
    public int Reverse(int x) {
        var negative = x < 0;
        if (negative) x = -x;
        long result = 0;
        while (x > 0)
        {
            result = (result * 10) + x % 10;
            x /= 10;
        }
        if (negative) result = -result;
        if (result > int.MaxValue || result < int.MinValue) result = 0;
        return (int) result;
    }
}
```

### **Ruby**

```rb
# @param {Integer} x
# @return {Integer}
def reverse(x)
  neg = x < 0

  x = x.abs
  s = ''

  x /= 10 while x > 0 && (x % 10).zero?

  while x > 0
    s += (x % 10).to_s
    x /= 10
  end

  s = neg ? '-' + s : s

  # have to explicitly constraint the int boundary as per the dummy test case
  res = s.to_i
  res <= 214_748_364_7 && res >= -214_748_364_8 ? res : 0
end
```

### **...**

```

```

<!-- tabs:end -->
