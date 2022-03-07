# [504. 七进制数](https://leetcode-cn.com/problems/base-7)

[English Version](/solution/0500-0599/0504.Base%207/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数，将其转化为7进制，并以字符串形式输出。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 100
<strong>输出:</strong> &quot;202&quot;
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> -7
<strong>输出:</strong> &quot;-10&quot;
</pre>

<p><strong>注意:</strong> 输入范围是&nbsp;[-1e7, 1e7] 。</p>

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
