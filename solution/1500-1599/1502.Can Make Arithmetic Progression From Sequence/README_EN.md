# [1502. Can Make Arithmetic Progression From Sequence](https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence)

[中文文档](/solution/1500-1599/1502.Can%20Make%20Arithmetic%20Progression%20From%20Sequence/README.md)

## Description

<p>Given an array of numbers <code>arr</code>.&nbsp;A sequence of numbers is called an arithmetic progression&nbsp;if the difference between any two consecutive elements is the same.</p>

<p>Return <code>true</code>&nbsp;if the array can be rearranged to form an arithmetic progression, otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,5,1]
<strong>Output:</strong> true
<strong>Explanation: </strong>We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,4]
<strong>Output:</strong> false
<strong>Explanation: </strong>There is no way to reorder the elements to obtain an arithmetic progression.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>-10^6 &lt;= arr[i] &lt;= 10^6</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canMakeArithmeticProgression(self, arr: List[int]) -> bool:
        arr.sort()
        for i in range(1, len(arr) - 1):
            if (arr[i] << 1) != arr[i - 1] + arr[i + 1]:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length - 1; ++i) {
            if ((arr[i] << 1) != arr[i - 1] + arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} arr
 * @return {boolean}
 */
var canMakeArithmeticProgression = function(arr) {
    arr.sort((a, b) => a - b);
    for (let i = 1; i < arr.length - 1; i++) {
        if ((arr[i] << 1) != (arr[i - 1] + arr[i + 1])) return false;
    }
    return true;
};
```

### **...**

```

```

<!-- tabs:end -->
