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

我们不妨记 $mi$ 和 $mx$ 分别为 $-2^{31}$ 和 $2^{31} - 1$，则 $x$ 的反转结果 $ans$ 需要满足 $mi \le ans \le mx$。

我们可以通过不断地对 $x$ 取余来获取 $x$ 的最后一位数字 $y$，并将 $y$ 添加到 $ans$ 的末尾。在添加 $y$ 之前，我们需要判断 $ans$ 是否溢出。即判断 $ans \times 10 + y$ 是否在 $[mi, mx]$ 的范围内。

若 $x \gt 0$，那么需要满足 $ans \times 10 + y \leq mx$，即 $ans \times 10 + y \leq \left \lfloor \frac{mx}{10} \right \rfloor \times 10 + 7$。整理得 $(ans - \left \lfloor \frac{mx}{10} \right \rfloor) \times 10 \leq 7 - y$。

下面我们讨论上述不等式成立的条件：

-   当 $ans \lt \left \lfloor \frac{mx}{10} \right \rfloor$ 时，不等式显然成立；
-   当 $ans = \left \lfloor \frac{mx}{10} \right \rfloor$ 时，不等式成立的充要条件是 $y \leq 7$。如果 $ans = \left \lfloor \frac{mx}{10} \right \rfloor$ 并且还能继续添加数字，说明此时数字是最高位，即此时 $y$ 一定不超过 $2$，因此，不等式一定成立；
-   当 $ans \gt \left \lfloor \frac{mx}{10} \right \rfloor$ 时，不等式显然不成立。

综上，当 $x \gt 0$ 时，不等式成立的充要条件是 $ans \leq \left \lfloor \frac{mx}{10} \right \rfloor$。

同理，当 $x \lt 0$ 时，不等式成立的充要条件是 $ans \geq \left \lfloor \frac{mi}{10} \right \rfloor$。

因此，我们可以通过判断 $ans$ 是否在 $[\left \lfloor \frac{mi}{10} \right \rfloor, \left \lfloor \frac{mx}{10} \right \rfloor]$ 的范围内来判断 $ans$ 是否溢出。若溢出，则返回 $0$。否则，将 $y$ 添加到 $ans$ 的末尾，然后将 $x$ 的最后一位数字去除，即 $x \gets \left \lfloor \frac{x}{10} \right \rfloor$。

时间复杂度 $O(\log |x|)$，其中 $|x|$ 为 $x$ 的绝对值。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
