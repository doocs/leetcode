# [05.07. Exchange](https://leetcode.cn/problems/exchange-lcci)

[中文文档](/lcci/05.07.Exchange/README.md)

## Description

<p>Write a program to swap odd and even bits in an integer with as few instructions as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: num = 2（0b10）

<strong> Output</strong> 1 (0b01)

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: num = 3

<strong> Output</strong>: 3

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= num &lt;=</code>&nbsp;2^30 - 1</li>
	<li>The result integer fits into 32-bit integer.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exchangeBits(self, num: int) -> int:
        return ((num & 0x55555555) << 1) | ((num & 0xAAAAAAAA) >> 1)
```

### **Java**

```java
class Solution {
    public int exchangeBits(int num) {
        return ((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa)) >> 1;
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn exchange_bits(mut num: i32) -> i32 {
        let mut res = 0;
        let mut i = 0;
        while num != 0 {
            let a = num & 1;
            num >>= 1;
            let b = num & 1;
            num >>= 1;
            res |= a << i + 1;
            res |= b << i;
            i += 2;
        }
        res
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int exchangeBits(int num) {
        return ((num & 0x55555555) << 1) | ((num & 0xaaaaaaaa)) >> 1;
    }
};
```

### **Go**

```go
func exchangeBits(num int) int {
	return ((num & 0x55555555) << 1) | (num&0xaaaaaaaa)>>1
}
```

### **...**

```

```

<!-- tabs:end -->
