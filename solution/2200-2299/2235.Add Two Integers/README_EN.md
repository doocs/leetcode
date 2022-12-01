# [2235. Add Two Integers](https://leetcode.com/problems/add-two-integers)

[中文文档](/solution/2200-2299/2235.Add%20Two%20Integers/README.md)

## Description

Given two integers <code>num1</code> and <code>num2</code>, return <em>the <strong>sum</strong> of the two integers</em>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = 12, num2 = 5
<strong>Output:</strong> 17
<strong>Explanation:</strong> num1 is 12, num2 is 5, and their sum is 12 + 5 = 17, so 17 is returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = -10, num2 = 4
<strong>Output:</strong> -6
<strong>Explanation:</strong> num1 + num2 = -6, so -6 is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-100 &lt;= num1, num2 &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sum(self, num1: int, num2: int) -> int:
        return num1 + num2
```

### **Java**

```java
class Solution {
    public int sum(int num1, int num2) {
        return num1 + num2;
    }
}
```

### **TypeScript**

```ts
function sum(num1: number, num2: number): number {
    return num1 + num2;
}
```

### **C++**

```cpp
class Solution {
public:
    int sum(int num1, int num2) {
        return num1 + num2;
    }
};
```

### **Go**

```go
func sum(num1 int, num2 int) int {
	return num1 + num2
}
```

### **TypeScript**

```ts
function sum(num1: number, num2: number): number {
    return num1 + num2;
}
```

### **Rust**

```rust
impl Solution {
    pub fn sum(num1: i32, num2: i32) -> i32 {
        num1 + num2
    }
}
```

### **C**

```c
int sum(int num1, int num2) {
    return num1 + num2;
}
```

### **...**

```

```

<!-- tabs:end -->
