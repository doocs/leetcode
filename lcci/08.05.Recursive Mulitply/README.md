# [面试题 08.05. 递归乘法](https://leetcode-cn.com/problems/recursive-mulitply-lcci)

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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts
function multiply(A: number, B: number): number {
    if (A === 0 || B === 0) {
        return 0;
    }
    const [max, min] = [Math.max(A, B), Math.min(A, B)];
    return max + multiply(max, min - 1);
}
```

```ts
function multiply(A: number, B: number): number {
    const max = Math.max(A, B);
    const min = Math.min(A, B);
    const helper = (a: number, b: number) =>
        (b & 1 ? a : 0) + (b > 1 ? helper(a + a, b >> 1) : 0);
    return helper(max, min);
}
```

### **Rust**

```rust
impl Solution {
    pub fn multiply(a: i32, b: i32) -> i32 {
        if a == 0 || b == 0 {
            return 0;
        }
        a.max(b) + Self::multiply(a.max(b), a.min(b) - 1)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
