# [561. 数组拆分 I](https://leetcode-cn.com/problems/array-partition-i)

[English Version](/solution/0500-0599/0561.Array%20Partition%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定长度为&nbsp;<strong>2n&nbsp;</strong>的数组, 你的任务是将这些数分成&nbsp;<strong>n </strong>对, 例如 (a<sub>1</sub>, b<sub>1</sub>), (a<sub>2</sub>, b<sub>2</sub>), ..., (a<sub>n</sub>, b<sub>n</sub>) ，使得从1 到&nbsp;n 的 min(a<sub>i</sub>, b<sub>i</sub>) 总和最大。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,4,3,2]

<strong>输出:</strong> 4
<strong>解释:</strong> n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
</pre>

<p><strong>提示:</strong></p>

<ol>
	<li><strong>n</strong>&nbsp;是正整数,范围在 [1, 10000].</li>
	<li>数组中的元素范围在 [-10000, 10000].</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先排序，然后求相邻的两个元素的最小值，得到的总和即为结果。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        return sum(sorted(nums)[::2])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
