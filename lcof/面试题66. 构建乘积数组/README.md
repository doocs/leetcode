# [面试题 66. 构建乘积数组](https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>A[0,1,…,n-1]</code>，请构建一个数组 <code>B[0,1,…,n-1]</code>，其中 <code>B[i]</code> 的值是数组 <code>A</code> 中除了下标 <code>i</code> 以外的元素的积, 即 <code>B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]</code>。不能使用除法。</p>

<p> </p>

<p><strong>示例:</strong></p>

<pre>
<strong>输入:</strong> [1,2,3,4,5]
<strong>输出:</strong> [120,60,40,30,24]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>所有元素乘积之和不会溢出 32 位整数</li>
	<li><code>a.length <= 100000</code></li>
</ul>

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

### **C#**

```cs
public class Solution {
    public int[] ConstructArr(int[] a) {
        int n = a.Length;
        int[] ans = new int[n];
        int left = 1, right = 1;
        for (int i = 0; i < n; i++) {
            ans[i] = left;
            left *= a[i];
        }
        for (int i = n - 1; i > -1; i--) {
            ans[i] *= right;
            right *= a[i];
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
