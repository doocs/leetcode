# [191. 位 1 的个数](https://leetcode.cn/problems/number-of-1-bits)

[English Version](/solution/0100-0199/0191.Number%20of%201%20Bits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为<a href="https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E9%87%8D%E9%87%8F" target="_blank">汉明重量</a>）。</p>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。</li>
	<li>在 Java 中，编译器使用<a href="https://baike.baidu.com/item/二进制补码/5295284" target="_blank">二进制补码</a>记法来表示有符号整数。因此，在上面的 <strong>示例 3</strong> 中，输入表示有符号整数 <code>-3</code>。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>00000000000000000000000000001011
<strong>输出：</strong>3
<strong>解释：</strong>输入的二进制串 <code><strong>00000000000000000000000000001011</strong> 中，共有三位为 '1'。</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>00000000000000000000000010000000
<strong>输出：</strong>1
<strong>解释：</strong>输入的二进制串 <strong>00000000000000000000000010000000</strong> 中，共有一位为 '1'。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>11111111111111111111111111111101
<strong>输出：</strong>31
<strong>解释：</strong>输入的二进制串 <strong>11111111111111111111111111111101</strong> 中，共有 31 位为 '1'。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>输入必须是长度为 <code>32</code> 的 <strong>二进制串</strong> 。</li>
</ul>

<ul>
</ul>

<p> </p>

<p><strong>进阶</strong>：</p>

<ul>
	<li>如果多次调用这个函数，你将如何优化你的算法？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

利用 `n & (n - 1)` 消除 `n` 中最后一位 1 这一特点，优化过程：

```txt
HAMMING-WEIGHT(n)
    r = 0
    while n != 0
        n &= n - 1
        r += 1
    r
```

以 5 为例，演示推演过程：

```txt
[0, 1, 0, 1] // 5
[0, 1, 0, 0] // 5 - 1 = 4
[0, 1, 0, 0] // 5 & 4 = 4

[0, 1, 0, 0] // 4
[0, 0, 1, 1] // 4 - 1 = 3
[0, 0, 0, 0] // 4 & 3 = 0
```

**方法二：lowbit**

`x -= (x & -x)` 可以消除二进制形式的最后一位 1。

同 [剑指 Offer 15. 二进制中 1 的个数](/lcof/面试题15.%20二进制中1的个数/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        ans = 0
        while n:
            n &= n - 1
            ans += 1
        return ans
```

```python
class Solution:
    def hammingWeight(self, n: int) -> int:
        ans = 0
        while n:
            n -= (n & -n)
            ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= n - 1;
            ++ans;
        }
        return ans;
    }
}
```

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n -= (n & -n);
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int hammingWeight(uint32_t n) {
        int ans = 0;
        while (n) {
            n &= n - 1;
            ++ans;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int hammingWeight(uint32_t n) {
        int ans = 0;
        while (n) {
            n -= (n & -n);
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func hammingWeight(num uint32) int {
	ans := 0
	for num != 0 {
		num &= num - 1
		ans++
	}
	return ans
}
```

```go
func hammingWeight(num uint32) int {
	ans := 0
	for num != 0 {
		num -= (num & -num)
		ans++
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number} n - a positive integer
 * @return {number}
 */
var hammingWeight = function (n) {
    let ans = 0;
    while (n) {
        n &= n - 1;
        ++ans;
    }
    return ans;
};
```

### **Rust**

```rust
impl Solution {
    pub fn hammingWeight(n: u32) -> i32 {
        n.count_ones() as i32
    }
}
```

```rust
impl Solution {
    pub fn hammingWeight(mut n: u32) -> i32 {
        let mut res = 0;
        while n != 0 {
            res += n & 1;
            n >>= 1;
        }
        res as i32
    }
}
```

```rust
impl Solution {
    pub fn hammingWeight(mut n: u32) -> i32 {
        let mut res = 0;
        while n != 0 {
            n &= (n - 1);
            res += 1;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
