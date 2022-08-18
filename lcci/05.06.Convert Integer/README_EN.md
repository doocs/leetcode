# [05.06. Convert Integer](https://leetcode.cn/problems/convert-integer-lcci)

[中文文档](/lcci/05.06.Convert%20Integer/README.md)

## Description

<p>Write a function to determine the number of bits you would need to flip to convert integer A to integer B.</p>

<p><strong>Example1:</strong></p>

<pre>



<strong> Input</strong>: A = 29 (0b11101), B = 15 (0b01111)



<strong> Output</strong>: 2



</pre>

<p><strong>Example2:</strong></p>

<pre>



<strong> Input</strong>: A = 1，B = 2



<strong> Output</strong>: 2



</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>-2147483648 &lt;= A, B &lt;= 2147483647</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python


```

### **Java**

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
