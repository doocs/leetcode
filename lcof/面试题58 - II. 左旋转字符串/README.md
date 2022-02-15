# [面试题 58 - II. 左旋转字符串](https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/)

## 题目描述

<p>字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串&quot;abcdefg&quot;和数字2，该函数将返回左旋转两位得到的结果&quot;cdefgab&quot;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> s = &quot;abcdefg&quot;, k = 2
<strong>输出:&nbsp;</strong>&quot;cdefgab&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> s = &quot;lrloseumgh&quot;, k = 6
<strong>输出:&nbsp;</strong>&quot;umghlrlose&quot;
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt; s.length &lt;= 10000</code></li>
</ul>

## 解法

需要注意，此题是旋转，而非位移。当 `n >= s.length` 时，字符串不变。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverseLeftWords(self, s: str, n: int) -> str:
        return s[n:] + s[:n]
```

### **Java**

```java
class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @param {number} n
 * @return {string}
 */
var reverseLeftWords = function (s, n) {
  return s.substring(n) + s.substr(0, n);
};
```

### **Go**

```go
func reverseLeftWords(s string, n int) string {
	return s[n:] + s[:n]
}
```

### **Rust**

```rust
impl Solution {
    pub fn reverse_left_words(s: String, n: i32) -> String {
        let len = s.len() as i32;
        if n >= len {
            return s;
        }
        String::from(&s[n as usize..]) + &s[..n as usize]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
