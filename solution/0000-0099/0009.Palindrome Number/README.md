# [9. 回文数](https://leetcode.cn/problems/palindrome-number)

[English Version](/solution/0000-0099/0009.Palindrome%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>x</code> ，如果 <code>x</code> 是一个回文整数，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。</p>

<ul>
	<li>例如，<code>121</code> 是回文，而 <code>123</code> 不是。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 121
<strong>输出：</strong>true
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>x = -121
<strong>输出：</strong>false
<strong>解释：</strong>从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 10
<strong>输出：</strong>false
<strong>解释：</strong>从右向左读, 为 01 。因此它不是一个回文数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= x &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能不将整数转为字符串来解决这个问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：反转一半数字**

我们先判断特殊情况：

-   如果 $x \lt 0$，那么 $x$ 不是回文数，直接返回 `false`；
-   如果 $x \gt 0$ 且 $x$ 的个位数是 $0$，那么 $x$ 不是回文数，直接返回 `false`；
-   如果 $x$ 的个位数不是 $0$，那么 $x$ 可能是回文数，继续执行下面的步骤。

我们将 $x$ 的后半部分反转，与前半部分进行比较，如果相等，那么 $x$ 是回文数，否则 $x$ 不是回文数。

举个例子，例如 $x = 1221$，我们可以将数字后半部分从 “21” 反转为 “12”，并将其与前半部分 “12” 进行比较，因为二者相等，我们得知数字 $x$ 是回文。

让我们看看如何将后半部分反转。

对于数字 $1221$，如果执行 $1221 \bmod 10$，我们将得到最后一位数字 $1$，要得到倒数第二位数字，我们可以先通过除以 $10$ 将最后一位数字从 $1221$ 中移除，$1221 / 10 = 122$，再求出上一步结果除以 $10$ 的余数，$122 \bmod 10 = 2$，就可以得到倒数第二位数字。

如果继续这个过程，我们将得到更多位数的反转数字。

通过将最后一位数字不断地累乘到取出数字的变量 $y$ 上，我们可以得到以相反顺序的数字。

在代码实现上，我们可以反复“取出” $x$ 的最后一位数字，并将其“添加”到 $y$ 的后面，循环直到 $y \ge x$，如果此时 $x = y$，或者 $x = y / 10$，那么 $x$ 就是回文数。

时间复杂度 $O(\log_{10}(n))$，其中 $n$ 是 $x$。对于每次迭代，我们会将输入除以 $10$，因此时间复杂度为 $O(\log_{10}(n))$。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0 or (x and x % 10 == 0):
            return False
        y = 0
        while y < x:
            y = y * 10 + x % 10
            x //= 10
        return x in (y, y // 10)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x > 0 && x % 10 == 0)) {
            return false;
        }
        int y = 0;
        for (; y < x; x /= 10) {
            y = y * 10 + x % 10;
        }
        return x == y || x == y / 10;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPalindrome(int x) {
        if (x < 0 || (x && x % 10 == 0)) {
            return false;
        }
        int y = 0;
        for (; y < x; x /= 10) {
            y = y * 10 + x % 10;
        }
        return x == y || x == y / 10;
    }
};
```

### **Go**

```go
func isPalindrome(x int) bool {
	if x < 0 || (x > 0 && x%10 == 0) {
		return false
	}
	y := 0
	for ; y < x; x /= 10 {
		y = y*10 + x%10
	}
	return x == y || x == y/10
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @return {boolean}
 */
var isPalindrome = function (x) {
    if (x < 0 || (x > 0 && x % 10 === 0)) {
        return false;
    }
    let y = 0;
    for (; y < x; x = ~~(x / 10)) {
        y = y * 10 + (x % 10);
    }
    return x === y || x === ~~(y / 10);
};
```

### **TypeScript**

```ts
function isPalindrome(x: number): boolean {
    if (x < 0 || (x > 0 && x % 10 === 0)) {
        return false;
    }
    let y = 0;
    for (; y < x; x = ~~(x / 10)) {
        y = y * 10 + (x % 10);
    }
    return x === y || x === ~~(y / 10);
}
```

### **Rust**

```rust
impl Solution {
    pub fn is_palindrome(x: i32) -> bool {
        if x < 0 {
            return false;
        }
        let s = x.to_string();
        let bs = s.as_bytes();
        let n = bs.len();
        let mut l = 0;
        let mut r = n - 1;
        while l < r {
            if bs[l] != bs[r] {
                return false;
            }
            l += 1;
            r -= 1;
        }
        true
    }
}
```

```rust
impl Solution {
    pub fn is_palindrome(mut x: i32) -> bool {
        if x < 0 || (x % 10 == 0 && x != 0) {
            return false;
        }
        let mut y = 0;
        while x > y {
            y *= 10;
            y += x % 10;
            x /= 10;
        }
        x == y || x == y / 10
    }
}
```

### **...**

```

```

<!-- tabs:end -->
