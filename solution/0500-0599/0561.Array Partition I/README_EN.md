# [561. Array Partition I](https://leetcode.com/problems/array-partition-i)

[中文文档](/solution/0500-0599/0561.Array%20Partition%20I/README.md)

## Description

<p>

Given an array of <b>2n</b> integers, your task is to group these integers into <b>n</b> pairs of integer, say (a<sub>1</sub>, b<sub>1</sub>), (a<sub>2</sub>, b<sub>2</sub>), ..., (a<sub>n</sub>, b<sub>n</sub>) which makes sum of min(a<sub>i</sub>, b<sub>i</sub>) for all i from 1 to n as large as possible.

</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> [1,4,3,2]



<b>Output:</b> 4

<b>Explanation:</b> n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li><b>n</b> is a positive integer, which is in the range of [1, 10000].</li>

<li>All the integers in the array will be in the range of [-10000, 10000].</li>

</ol>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        return sum(sorted(nums)[::2])
```

### **Java**

```java
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0, n = nums.length; i < n; i += 2) {
            res += nums[i];
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var arrayPairSum = function (nums) {
  nums.sort((a, b) => a - b);
  let res = 0;
  for (let i = 0, n = nums.length; i < n; i += 2) {
    res += nums[i];
  }
  return res;
};
```

### **...**

```

```

<!-- tabs:end -->
