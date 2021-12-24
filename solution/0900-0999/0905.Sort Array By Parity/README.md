# [905. 按奇偶排序数组](https://leetcode-cn.com/problems/sort-array-by-parity)

[English Version](/solution/0900-0999/0905.Sort%20Array%20By%20Parity/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数数组 <code>A</code>，返回一个数组，在该数组中，&nbsp;<code>A</code> 的所有偶数元素之后跟着所有奇数元素。</p>

<p>你可以返回满足此条件的任何数组作为答案。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[3,1,2,4]
<strong>输出：</strong>[2,4,3,1]
输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 5000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 5000</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针原地交换数组元素。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sortArrayByParity(self, A: List[int]) -> List[int]:
        i, j = 0, len(A) - 1
        while i < j:
            if (A[i] & 1) > (A[j] & 1):
                A[i], A[j] = A[j], A[i]
            if A[i] & 1 == 0:
                i += 1
            if A[j] & 1 == 1:
                j -= 1
        return A
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            if ((A[i] & 1) > (A[j] & 1)) {
                int t = A[i];
                A[i] = A[j];
                A[j] = t;
            }
            if ((A[i] & 1) == 0) {
                ++i;
            }
            if ((A[j] & 1) == 1) {
                --j;
            }
        }
        return A;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} A
 * @return {number[]}
 */
var sortArrayByParity = function (A) {
    let i = 0;
    let j = A.length - 1;
    while (i < j) {
        if ((A[i] & 1) > (A[j] & 1)) {
            const t = A[i];
            A[i] = A[j];
            A[j] = t;
        }
        if ((A[i] & 1) == 0) {
            ++i;
        }
        if ((A[j] & 1) == 1) {
            --j;
        }
    }
    return A;
};
```

### **...**

```

```

<!-- tabs:end -->
