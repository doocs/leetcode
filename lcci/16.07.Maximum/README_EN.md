# [16.07. Maximum](https://leetcode.cn/problems/maximum-lcci)

[中文文档](/lcci/16.07.Maximum/README.md)

## Description

<p>Write a method that finds the maximum of two numbers. You should not use if-else or any other comparison operator.</p>
<p><strong>Example: </strong></p>
<pre>

<strong>Input: </strong> a = 1, b = 2

<strong>Output: </strong> 2

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximum(self, a: int, b: int) -> int:
        k = (int(((a - b) & 0xFFFFFFFFFFFFFFFF) >> 63)) & 1
        return a * (k ^ 1) + b * k
```

### **Java**

```java
class Solution {
    public int maximum(int a, int b) {
        int k = (int) (((long) a - (long) b) >> 63) & 1;
        return a * (k ^ 1) + b * k;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximum(int a, int b) {
        int k = ((static_cast<long long>(a) - static_cast<long long>(b)) >> 63) & 1;
        return a * (k ^ 1) + b * k;
    }
};
```

### **Go**

```go
func maximum(a int, b int) int {
	k := (a - b) >> 63 & 1
	return a*(k^1) + b*k
}
```

### **TypeScript**

```ts
function maximum(a: number, b: number): number {
    const k: number = Number(((BigInt(a) - BigInt(b)) >> BigInt(63)) & BigInt(1));
    return a * (k ^ 1) + b * k;
}
```

### **...**

```

```

<!-- tabs:end -->
