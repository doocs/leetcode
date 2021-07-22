# [面试题 64. 求 1+2+…+n](https://leetcode-cn.com/problems/qiu-12n-lcof/)

## 题目描述

求 `1+2+...+n`，要求不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句（A?B:C）。

**示例 1：**

```
输入: n = 3
输出: 6
```

**示例 2：**

```
输入: n = 9
输出: 45
```

**限制：**

- `1 <= n <= 10000`

## 解法

递归，结合**逻辑与**短路运算符求解。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumNums(self, n: int) -> int:
        return n and (n + self.sumNums(n - 1))
```

### **Java**

```java
class Solution {
    public int sumNums(int n) {
        int s = n;
        boolean t = n > 0 && (s += sumNums(n - 1)) > 0;
        return s;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var sumNums = function (n) {
  return (n ** 2 + n) >> 1;
};
```

### **Go**

```go
func sumNums(n int) int {
	s := 0
	var sum func(int) bool
	sum = func(n int) bool {
		s += n
		return n > 0 && sum(n-1)
	}
	sum(n)
	return s
}
```

### **...**

```

```

<!-- tabs:end -->
