# [7. 整数反转](https://leetcode.cn/problems/reverse-integer)

[English Version](/solution/0000-0099/0007.Reverse%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 32 位的有符号整数 <code>x</code> ，返回将 <code>x</code> 中的数字部分反转后的结果。</p>

<p>如果反转后整数超过 32 位的有符号整数的范围 <code>[−2<sup>31</sup>,  2<sup>31 </sup>− 1]</code> ，就返回 0。</p>
<strong>假设环境不允许存储 64 位整数（有符号或无符号）。</strong>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 123
<strong>输出：</strong>321
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = -123
<strong>输出：</strong>-321
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 120
<strong>输出：</strong>21
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>x = 0
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> <= x <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

设返回结果初始值 $ans = 0$，每次循环对于给定的数 $x$，若 $x \ne 0$，求出个位数字 $digit = x \ \% \ 10$，根据题意当 $ans \times 10 + digit > INT32\\_MAX$ 或者 $ans \times 10 + digit < INT32\\_MIN$ 时返回 $0$，否则我们令 $ans = ans \times 10 + digit$，然后结束本次循环，同时更新 $x = x/10$。

但题目要求不允许存储 64 位整数，故其中的判断条件需要修改为当 $ans > (INT32\\_MAX-digit)/10$ 或者 $ans < (INT32\\_MIN-digit)/10$ 时返回 $0$。

更进一步来看，在 $x > 0$ 时，若 $x$ 的位数小于 $INT32\\_MAX$ 的位数则一定可以反转数字；若 $x$ 的位数等于 $INT32\\_MAX$ 的位数，能否反转则取决于 $x$ 的最高位数字 $digit$，而 $x \leq INT32\\_MAX$，故最高位数字 $digit \leq 2 < INT32\\_MAX \\% 10$，所以判断条件可以简化为 $ans > INT32\\_MAX/10$。同理，当 $x < 0$ 时，判断条件可以简化为 $ans < INT32\\_MIN/10$。

时间复杂度 $O(log|x|)$，空间复杂度 $O(1)$，其中 $log|x|$ 表示有符号整数 $x$ 的位数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

转字符串，进行翻转。

```python
class Solution:
    def reverse(self, x: int) -> int:
        y = int(str(abs(x))[::-1])
        res = -y if x < 0 else y
        return 0 if res < -(2**31) or res > 2**31 - 1 else res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int reverse(int x) {
        long res = 0;
        // 考虑负数情况，所以这里条件为: x != 0
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
        int ans = 0;
        for (; x != 0; x /= 10) {
            if (ans > INT32_MAX / 10 || ans < INT32_MIN / 10)
                return 0;
            ans = ans * 10 + x % 10;
        }
        return ans;
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
	ans, INT32_MAX, INT32_MIN := 0, math.MaxInt32, math.MinInt32
	for ; x != 0; x /= 10 {
		if ans > INT32_MAX/10 || ans < INT32_MIN/10 {
			return 0
		}
		ans = ans*10 + x % 10
	}
	return ans
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
