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

~~**最佳方案：**~~
~~直接返回 `A * B`~~
正常递归，叠加总和

```txt
MULTIPLY(A, B)
    if A == 0 || B == 0
        return 0
    A + multiply(A, B - 1)
```

优化 1：
由数值较小的数字决定递归层次

```txt
MULTIPLY(A, B)
    if A == 0 || B == 0
        return 0
    return max(A, B) + multiply(max(A, B), min(A, B) - 1)
```

优化 2：
使用位移减少递归层次

```txt
MULTIPLY(A, B)
    return (B % 1 == 1 ? A : 0) + (B > 1 ? MULTIPLY(A + A, B >> 1) : 0)
```

可进一步，转换为循环，虽然并不符合递归主题。

> A 与 B 皆为**正整数**，初始值不会为 0，所以终止条件是 `B != 1`

```txt
MULTIPLY(A, B)
    T = min(A, B)
    A = max(A, B)
    B = T
    r = 0
    while B != 1 {
        if B % 2 == 1 {
            r = r + A
        }
        A = A + A
        B = B >> 1
    }
    return res + A
```

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
