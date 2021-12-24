# [7. Reverse Integer](https://leetcode.com/problems/reverse-integer)

[中文文档](/solution/0000-0099/0007.Reverse%20Integer/README.md)

## Description

<p>Given a signed 32-bit integer <code>x</code>, return <code>x</code><em> with its digits reversed</em>. If reversing <code>x</code> causes the value to go outside the signed 32-bit integer range <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>, then return <code>0</code>.</p>

<p><strong>Assume the environment does not allow you to store 64-bit integers (signed or unsigned).</strong></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> x = 123
<strong>Output:</strong> 321
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> x = -123
<strong>Output:</strong> -321
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> x = 120
<strong>Output:</strong> 21
</pre><p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong> x = 0
<strong>Output:</strong> 0
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
        return 0 if res < -2**31 or res > 2**31 -1 else res
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

### **...**

```

```

<!-- tabs:end -->
