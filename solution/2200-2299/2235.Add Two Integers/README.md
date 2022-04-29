# [2235. 两整数相加](https://leetcode.cn/problems/add-two-integers)

[English Version](/solution/2200-2299/2235.Add%20Two%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你两个整数&nbsp;<code>num1</code> 和 <code>num2</code>，返回这两个整数的和。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num1 = 12, num2 = 5
<strong>输出：</strong>17
<strong>解释：</strong>num1 是 12，num2 是 5 ，它们的和是 12 + 5 = 17 ，因此返回 17 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num1 = -10, num2 = 4
<strong>输出：</strong>-6
<strong>解释：</strong>num1 + num2 = -6 ，因此返回 -6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-100 &lt;= num1, num2 &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sum(self, num1: int, num2: int) -> int:
        return num1 + num2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **...**

```

```

<!-- tabs:end -->
