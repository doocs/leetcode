# [面试题 05.06. 整数转换](https://leetcode.cn/problems/convert-integer-lcci)

[English Version](/lcci/05.06.Convert%20Integer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：A = 29 （或者0b11101）, B = 15（或者0b01111）
<strong> 输出</strong>：2
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：A = 1，B = 2
<strong> 输出</strong>：2
</pre>

<p> <strong>提示:</strong></p>

<ol>
<li>A，B范围在[-2147483648, 2147483647]之间</li>
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
class Solution {
    public int convertInteger(int A, int B) {
        return Integer.bitCount(A ^ B);
    }
}
```

### **TypeScript**

```ts
function convertInteger(A: number, B: number): number {
    let res = 0;
    while (A !== 0 || B !== 0) {
        if ((A & 1) !== (B & 1)) {
            res++;
        }
        A >>>= 1;
        B >>>= 1;
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn convert_integer(a: i32, b: i32) -> i32 {
        (a ^ b).count_ones() as i32
    }
}
```

### **...**

```


```

<!-- tabs:end -->
