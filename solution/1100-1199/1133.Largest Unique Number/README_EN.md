# [1133. Largest Unique Number](https://leetcode.com/problems/largest-unique-number)

[中文文档](/solution/1100-1199/1133.Largest%20Unique%20Number/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the largest integer that only occurs once</em>. If no integer occurs once, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,7,3,9,4,9,8,3,1]
<strong>Output:</strong> 8
<strong>Explanation:</strong> The maximum integer in the array is 9 but it is repeated. The number 8 occurs only once, so it is the answer.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,9,8,8]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no number that occurs only once.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

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
