# [693. 交替位二进制数](https://leetcode.cn/problems/binary-number-with-alternating-bits)

[English Version](/solution/0600-0699/0693.Binary%20Number%20with%20Alternating%20Bits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>true
<strong>解释：</strong>5 的二进制表示是：101
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 7
<strong>输出：</strong>false
<strong>解释：</strong>7 的二进制表示是：111.</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 11
<strong>输出：</strong>false
<strong>解释：</strong>11 的二进制表示是：1011.</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

n 循环右移直至为 0，依次检测 n 的二进制位是否交替出现。若循环过程中发现 0、1 没有交替出现，直接返回 false。否则循环结束返回 true。

**方法二：位运算**

假设 01 交替出现，那么我们可以通过错位异或将尾部全部转为 1，加 1 可以得到 2 的幂次的一个数 n（n 中只有一个位是 1），接着利用 `n & (n + 1)` 可以消除最后一位的 1。

此时判断是否为 0，若是，说明假设成立，是 01 交替串。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        prev = -1
        while n:
            curr = n & 1
            if prev == curr:
                return False
            prev = curr
            n >>= 1
        return True
```

```python
class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        n ^= (n >> 1)
        return (n & (n + 1)) == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean hasAlternatingBits(int n) {
        int prev = -1;
        while (n != 0) {
            int curr = n & 1;
            if (prev == curr) {
                return false;
            }
            prev = curr;
            n >>= 1;
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean hasAlternatingBits(int n) {
        n ^= (n >> 1);
        return (n & (n + 1)) == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool hasAlternatingBits(int n) {
        int prev = -1;
        while (n) {
            int curr = n & 1;
            if (prev == curr) return false;
            prev = curr;
            n >>= 1;
        }
        return true;
    }
};
```

```cpp
class Solution {
public:
    bool hasAlternatingBits(int n) {
        n ^= (n >> 1);
        return (n & ((long) n + 1)) == 0;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn has_alternating_bits(mut n: i32) -> bool {
        let u = n & 3;
        if u != 1 && u != 2 {
            return false;
        }
        while n != 0 {
            if (n & 3) != u {
                return false
            }
            n >>= 2;
        }
        true
    }
}
```

```rust
impl Solution {
    pub fn has_alternating_bits(n: i32) -> bool {
        let t = n ^ (n >> 1);
        (t & (t + 1)) == 0
    }
}
```

### **Go**

```go
func hasAlternatingBits(n int) bool {
	prev := -1
	for n != 0 {
		curr := n & 1
		if prev == curr {
			return false
		}
		prev = curr
		n >>= 1
	}
	return true
}
```

```go
func hasAlternatingBits(n int) bool {
	n ^= (n >> 1)
	return (n & (n + 1)) == 0
}
```

### **...**

```

```

<!-- tabs:end -->
