# [504. Base 7](https://leetcode.com/problems/base-7)

[中文文档](/solution/0500-0599/0504.Base%207/README.md)

## Description

<p>Given an integer, return its base 7 string representation.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> 100

<b>Output:</b> "202"

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> -7

<b>Output:</b> "-10"

</pre>

</p>

<p><b>Note:</b>

The input will be in range of [-1e7, 1e7].

</p>

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
function convertToBase7(num: number): string {
    if (num == 0) {
        return '0';
    }
    let res = '';
    const isMinus = num < 0;
    if (isMinus) {
        num = -num;
    }
    while (num != 0) {
        const r = num % 7;
        res = r + res;
        num = (num - r) / 7;
    }
    return isMinus ? '-' + res : res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn convert_to_base7(mut num: i32) -> String {
        if num == 0 {
            return String::from("0");
        }
        let mut res = String::new();
        let is_minus = num < 0;
        if is_minus {
            num = -num;
        }
        while num != 0 {
            res.push_str((num % 7).to_string().as_str());
            num /= 7;
        }
        if is_minus {
            res.push('-');
        }
        res.chars().rev().collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
