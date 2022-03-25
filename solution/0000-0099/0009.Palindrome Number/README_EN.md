# [9. Palindrome Number](https://leetcode.com/problems/palindrome-number)

[中文文档](/solution/0000-0099/0009.Palindrome%20Number/README.md)

## Description

<p>Given an integer <code>x</code>, return <code>true</code> if <code>x</code> is palindrome integer.</p>

<p>An integer is a <strong>palindrome</strong> when it reads the same backward as forward.</p>

<ul>
	<li>For example, <code>121</code> is a palindrome while <code>123</code> is not.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 121
<strong>Output:</strong> true
<strong>Explanation:</strong> 121 reads as 121 from left to right and from right to left.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = -121
<strong>Output:</strong> false
<strong>Explanation:</strong> From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
</pre>

<p><strong>Example 3:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0:
            return False
        y, t = 0, x
        while t:
            y = y * 10 + t % 10
            t //= 10
        return x == y
```

### **Java**

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int y = 0, t = x;
        while (t != 0) {
            y = y * 10 + t % 10;
            t /= 10;
        }
        return x == y;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} x
 * @return {boolean}
 */
var isPalindrome = function (x) {
    let str = x + '';
    let left = 0,
        right = str.length - 1;
    while (left < right) {
        if (str[left] != str[right]) return false;
        left++;
        right--;
    }
    return true;
};
```

### **Go**

```go
func isPalindrome(x int) bool {
	if x < 0 {
		return false
	}
	result := 0
	y := x
	for y != 0 {
		result = result * 10 + y%10
		y /= 10
	}
	return result == x
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
