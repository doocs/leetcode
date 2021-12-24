# [面试题 66. 构建乘积数组](https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

给定一个数组 `A[0,1,…,n-1]`，请构建一个数组 `B[0,1,…,n-1]`，其中 B 中的元素 `B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]`。不能使用除法。

**示例:**

```
输入: [1,2,3,4,5]
输出: [120,60,40,30,24]
```

**提示：**

- 所有元素乘积之和不会溢出 32 位整数
- `a.length <= 100000`

## 解法

<!-- 这里可写通用的实现逻辑 -->

`B[i] = (A[0] * A[1] * ... * A[i-1]) * (A[i+1] * ... * A[n-1])`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def constructArr(self, a: List[int]) -> List[int]:
        n = len(a)
        output = [1] * n
        left = right = 1
        for i in range(n):
            output[i] = left
            left *= a[i]
        for i in range(n - 1, -1, -1):
            output[i] *= right
            right *= a[i]
        return output
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] output = new int[n];
        for (int i = 0, left = 1; i < n; ++i) {
            output[i] = left;
            left *= a[i];
        }
        for (int i = n - 1, right = 1; i >= 0; --i) {
            output[i] *= right;
            right *= a[i];
        }
        return output;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} a
 * @return {number[]}
 */
var constructArr = function (a) {
    const n = a.length;
    let output = new Array(n);
    for (let i = 0, left = 1; i < n; ++i) {
        output[i] = left;
        left *= a[i];
    }
    for (let i = n - 1, right = 1; i >= 0; --i) {
        output[i] *= right;
        right *= a[i];
    }
    return output;
};
```

### **...**

```

```

<!-- tabs:end -->
