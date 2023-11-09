# [面试题 08.05. 递归乘法](https://leetcode.cn/problems/recursive-mulitply-lcci)

[English Version](/lcci/08.05.Recursive%20Mulitply/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。</p>
<p> <strong>示例1:</strong></p>
<pre>
<strong> 输入</strong>：A = 1, B = 10
<strong> 输出</strong>：10
</pre>
<p> <strong>示例2:</strong></p>
<pre>
<strong> 输入</strong>：A = 3, B = 4
<strong> 输出</strong>：12
</pre>
<p> <strong>提示:</strong></p>
<ol>
<li>保证乘法范围不会溢出</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归 + 位运算**

我们先判断 $B$ 是否为 $1$，如果是，那么直接返回 $A$。

否则，我们判断 $B$ 是否为奇数，如果是，那么我们可以将 $B$ 右移一位，然后递归调用函数，最后将结果左移一位，再加上 $A$。否则，我们可以将 $B$ 右移一位，然后递归调用函数，最后将结果左移一位。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是 $B$ 的大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def multiply(self, A: int, B: int) -> int:
        if B == 1:
            return A
        if B & 1:
            return (self.multiply(A, B >> 1) << 1) + A
        return self.multiply(A, B >> 1) << 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int multiply(int A, int B) {
        if (B == 1) {
            return A;
        }
        if ((B & 1) == 1) {
            return (multiply(A, B >> 1) << 1) + A;
        }
        return multiply(A, B >> 1) << 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int multiply(int A, int B) {
        if (B == 1) {
            return A;
        }
        if ((B & 1) == 1) {
            return (multiply(A, B >> 1) << 1) + A;
        }
        return multiply(A, B >> 1) << 1;
    }
};
```

### **Go**

```go
func multiply(A int, B int) int {
	if B == 1 {
		return A
	}
	if B&1 == 1 {
		return (multiply(A, B>>1) << 1) + A
	}
	return multiply(A, B>>1) << 1
}
```

### **TypeScript**

```ts
function multiply(A: number, B: number): number {
    if (B === 1) {
        return A;
    }
    if ((B & 1) === 1) {
        return (multiply(A, B >> 1) << 1) + A;
    }
    return multiply(A, B >> 1) << 1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn multiply(a: i32, b: i32) -> i32 {
        if b == 1 {
            return a;
        }
        if (b & 1) == 1 {
            return (Self::multiply(a, b >> 1) << 1) + a;
        }
        Self::multiply(a, b >> 1) << 1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
