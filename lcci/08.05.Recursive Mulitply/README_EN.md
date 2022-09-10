# [08.05. Recursive Mulitply](https://leetcode.cn/problems/recursive-mulitply-lcci)

[中文文档](/lcci/08.05.Recursive%20Mulitply/README.md)

## Description

<p>Write a recursive function to multiply two positive integers without using the * operator. You can use addition, subtraction, and bit shifting, but you should minimize the number of those operations.</p>
<p><strong>Example 1:</strong></p>
<pre>

<strong> Input</strong>: A = 1, B = 10

<strong> Output</strong>: 10

</pre>
<p><strong>Example 2:</strong></p>
<pre>

<strong> Input</strong>: A = 3, B = 4

<strong> Output</strong>: 12

</pre>
<p><strong>Note:</strong></p>
<ol>
	<li>The result will not overflow.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

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
