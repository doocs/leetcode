# [面试题66. 构建乘积数组](https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/)

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
        if not a:
            return []
        dp1 = [1 for i in a]
        dp2 = [1 for i in a]
        n = len(a)
        dp1[0], dp2[n - 1] = a[0], a[n - 1]
        for i in range(1, n):
            dp1[i] = dp1[i - 1] * a[i]
        for i in range(n - 2, -1, -1):
            dp2[i] = dp2[i + 1] * a[i]
        return [(1 if i - 1 < 0 else dp1[i - 1]) * (1 if i + 1 >= n else dp2[i + 1]) for i in range(0, n)]
```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        int n = a.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = a[0];
        dp2[n - 1] = a[n - 1];
        for (int i = 1; i < n; ++i) {
            dp1[i] = dp1[i - 1] * a[i];
        }
        for (int i = n - 2; i >= 0; --i) {
            dp2[i] = dp2[i + 1] * a[i];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = (i - 1 < 0 ? 1 : dp1[i - 1]) * (i + 1 >= n ? 1 : dp2[i + 1]);
        }
        return res;
    }
}
```

### **JavaScript**
```js
/**
 * @param {number[]} a
 * @return {number[]}
 */
var constructArr = function(a) {
    let pre = new Array(a.length+1).fill(1)
    pre[0] = 1
    let res = new Array(a.length).fill(1)
    for(let i = 1;i <= a.length;i++) {
        pre[i] = a[i-1] * pre[i-1]
    }
    let cur = 1
    for(let i = a.length - 1;i >= 0;i--) {
        res[i] = pre[i] * cur
        cur *= a[i]
    }
    return res
};
```

### **...**
```

```

<!-- tabs:end -->