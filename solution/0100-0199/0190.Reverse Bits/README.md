# [190. 颠倒二进制位](https://leetcode.cn/problems/reverse-bits)

[English Version](/solution/0100-0199/0190.Reverse%20Bits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>颠倒给定的 32 位无符号整数的二进制位。</p>

<p><strong>提示：</strong></p>

<ul>
	<li>请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。</li>
	<li>在 Java 中，编译器使用<a href="https://baike.baidu.com/item/二进制补码/5295284" target="_blank">二进制补码</a>记法来表示有符号整数。因此，在 <strong>示例 2</strong>&nbsp;中，输入表示有符号整数 <code>-3</code>，输出表示有符号整数 <code>-1073741825</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 00000010100101000001111010011100
<strong>输出：</strong>964176192 (00111001011110000010100101000000)
<strong>解释：</strong>输入的二进制串 <strong>00000010100101000001111010011100 </strong>表示无符号整数<strong> 43261596</strong><strong>，
    </strong> 因此返回 964176192，其二进制表示形式为 <strong>00111001011110000010100101000000</strong>。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 11111111111111111111111111111101
<strong>输出：</strong>3221225471 (10111111111111111111111111111111)
<strong>解释：</strong>输入的二进制串 <strong>11111111111111111111111111111101</strong> 表示无符号整数 4294967293，
   &nbsp; 因此返回 3221225471 其二进制表示形式为 <strong>10111111111111111111111111111111 。</strong></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>输入是一个长度为 <code>32</code> 的二进制字符串</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶</strong>: 如果多次调用这个函数，你将如何优化你的算法？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reverseBits(self, n: int) -> int:
        res = 0
        for i in range(32):
            res |= (n & 1) << (31 - i)
            n >>= 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            res |= ((n & 1) << (31 - i));
            n >>>= 1;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        uint32_t res = 0;
        for (int i = 0; i < 32; ++i) {
            res |= ((n & 1) << (31 - i));
            n >>= 1;
        }
        return res;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number} n - a positive integer
 * @return {number} - a positive integer
 */
var reverseBits = function (n) {
    let res = 0;
    for (let i = 0; i < 32 && n > 0; ++i) {
        res |= (n & 1) << (31 - i);
        n >>>= 1;
    }
    // 无符号右移
    return res >>> 0;
};
```

### **Rust**

```rust
impl Solution {
    pub fn reverse_bits(mut x: u32) -> u32 {
        let mut res = 0;
        for _ in 0..32 {
            res = (res << 1) | (x & 1);
            x >>= 1;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
