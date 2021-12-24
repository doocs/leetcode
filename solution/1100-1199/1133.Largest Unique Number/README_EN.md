# [1133. Largest Unique Number](https://leetcode.com/problems/largest-unique-number)

[中文文档](/solution/1100-1199/1133.Largest%20Unique%20Number/README.md)

## Description

<p>Given an array of integers <code>A</code>, return the largest integer that only occurs once.</p>

<p>If no integer occurs once, return -1.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[5,7,3,9,4,9,8,3,1]</span>

<strong>Output: </strong><span id="example-output-1">8</span>

<strong>Explanation: </strong>

The maximum integer in the array is 9 but it is repeated. The number 8 occurs only once, so it&#39;s the answer.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[9,9,8,8]</span>

<strong>Output: </strong><span id="example-output-1">-1</span>

<strong>Explanation: </strong>

There is no number that occurs only once.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 2000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 1000</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestUniqueNumber(self, A: List[int]) -> int:
        counter = Counter(A)
        for i in range(1000, -1, -1):
            if counter[i] == 1:
                return i
        return -1
```

### **Java**

```java
class Solution {
    public int largestUniqueNumber(int[] A) {
        int[] counter = new int[1001];
        for (int a : A) {
            ++counter[a];
        }
        for (int i = 1000; i >= 0; --i) {
            if (counter[i] == 1) {
                return i;
            }
        }
        return -1;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} A
 * @return {number}
 */
var largestUniqueNumber = function (A) {
    let counter = {};
    for (const a of A) {
        counter[a] = (counter[a] || 0) + 1;
    }
    for (let i = 1000; i >= 0; --i) {
        if (counter[i] == 1) {
            return i;
        }
    }
    return -1;
};
```

### **...**

```

```

<!-- tabs:end -->
