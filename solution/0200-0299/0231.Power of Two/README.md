# [231. 2 的幂](https://leetcode-cn.com/problems/power-of-two)

[English Version](/solution/0200-0299/0231.Power%20of%20Two/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数，编写一个函数来判断它是否是 2 的幂次方。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> 1
<strong>输出:</strong> true
<strong>解释: </strong>2<sup>0</sup>&nbsp;= 1</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> 16
<strong>输出:</strong> true
<strong>解释: </strong>2<sup>4</sup>&nbsp;= 16</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> 218
<strong>输出:</strong> false</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPowerOfTwo(self, n: int) -> bool:
        return n > 0 and (n & (n - 1)) == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isPowerOfTwo = function(n) {
    return n > 0 && (n & (n - 1)) == 0;
};
```

### **Go**

```go
func isPowerOfTwo(n int) bool {
    return n > 0 && (n & (n - 1)) == 0
}
```

### **TypeScript**

```ts
function isPowerOfTwo(n: number): boolean {
  return n > 0 && (n & (n - 1)) == 0;
};
```

### **...**

```

```

<!-- tabs:end -->
