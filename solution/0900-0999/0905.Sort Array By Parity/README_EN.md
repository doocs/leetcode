# [905. Sort Array By Parity](https://leetcode.com/problems/sort-array-by-parity)

[中文文档](/solution/0900-0999/0905.Sort%20Array%20By%20Parity/README.md)

## Description

<p>Given an integer array <code>nums</code>, move all the even integers at the beginning of the array followed by all the odd integers.</p>

<p>Return <em><strong>any array</strong> that satisfies this condition</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,2,4]
<strong>Output:</strong> [2,4,3,1]
<strong>Explanation:</strong> The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 5000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
