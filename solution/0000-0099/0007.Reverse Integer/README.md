# [7. 整数反转](https://leetcode-cn.com/problems/reverse-integer)

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

转字符串，进行翻转。

```python
class Solution:
    def reverse(self, x: int) -> int:
        y = int(str(abs(x))[::-1])
        res = -y if x < 0 else y
        return 0 if res < -2**31 or res > 2**31 -1 else res
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
