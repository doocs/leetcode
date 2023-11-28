# [9. Palindrome Number](https://leetcode.com/problems/palindrome-number)

[中文文档](/solution/0000-0099/0009.Palindrome%20Number/README.md)

## Description

<p>Given an integer <code>x</code>, return <code>true</code><em> if </em><code>x</code><em> is a </em><span data-keyword="palindrome-integer"><em><strong>palindrome</strong></em></span><em>, and </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 121
<strong>Output:</strong> true
<strong>Explanation:</strong> 121 reads as 121 from left to right and from right to left.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = -121
<strong>Output:</strong> false
<strong>Explanation:</strong> From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> x = 10
<strong>Output:</strong> false
<strong>Explanation:</strong> Reads 01 from right to left. Therefore it is not a palindrome.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= x &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you solve it without converting the integer to a string?

## Solutions

**Solution 1: Reverse Half of the Number**

First, we determine special cases:

-   If $x < 0$, then $x$ is not a palindrome, directly return `false`;
-   If $x > 0$ and the last digit of $x$ is $0$, then $x$ is not a palindrome, directly return `false`;
-   If the last digit of $x$ is not $0$, then $x$ might be a palindrome, continue the following steps.

We reverse the second half of $x$ and compare it with the first half. If they are equal, then $x$ is a palindrome, otherwise, $x$ is not a palindrome.

For example, for $x = 1221$, we can reverse the second half from "21" to "12" and compare it with the first half "12". Since they are equal, we know that $x$ is a palindrome.

Let's see how to reverse the second half.

For the number $1221$, if we perform $1221 \bmod 10$, we will get the last digit $1$. To get the second last digit, we can first remove the last digit from $1221$ by dividing by $10$, $1221 / 10 = 122$, then get the remainder of the previous result divided by $10$, $122 \bmod 10 = 2$, to get the second last digit.

If we continue this process, we will get more reversed digits.

By continuously multiplying the last digit to the variable $y$, we can get the number in reverse order.

In the code implementation, we can repeatedly "take out" the last digit of $x$ and "add" it to the end of $y$, loop until $y \ge x$. If at this time $x = y$, or $x = y / 10$, then $x$ is a palindrome.

The time complexity is $O(\log_{10}(n))$, where $n$ is $x$. For each iteration, we will divide the input by $10$, so the time complexity is $O(\log_{10}(n))$. The space complexity is $O(1)$.

<!-- tabs:start -->

### **Python3**

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
