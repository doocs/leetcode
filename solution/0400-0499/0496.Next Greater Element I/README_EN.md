# [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i)

[中文文档](/solution/0400-0499/0496.Next%20Greater%20Element%20I/README.md)

## Description

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> both of <strong>unique</strong> elements, where <code>nums1</code> is a subset of <code>nums2</code>.</p>

<p>Find all the next greater numbers for <code>nums1</code>&#39;s elements in the corresponding places of <code>nums2</code>.</p>

<p>The Next Greater Number of a number <code>x</code> in <code>nums1</code> is the first greater number to its right in <code>nums2</code>. If it does not exist, return <code>-1</code> for this number.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,1,2], nums2 = [1,3,4,2]
<strong>Output:</strong> [-1,3,-1]
<strong>Explanation:
</strong>For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
For number 1 in the first array, the next greater number for it in the second array is 3.
For number 2 in the first array, there is no next greater number for it in the second array, so output -1.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,4], nums2 = [1,2,3,4]
<strong>Output:</strong> [3,-1]
<strong>Explanation:</strong>
For number 2 in the first array, the next greater number for it in the second array is 3.
For number 4 in the first array, there is no next greater number for it in the second array, so output -1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
	<li>All integers in <code>nums1</code> and <code>nums2</code> are <strong>unique</strong>.</li>
	<li>All the integers of <code>nums1</code> also appear in <code>nums2</code>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you find an <code>O(nums1.length + nums2.length)</code> solution?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        stack = []
        mapper = {}
        for num in nums2:
            while stack and stack[-1] < num:
                mapper[stack.pop()] = num
            stack.append(num)
        return [mapper.get(num, -1) for num in nums1]
```

### **Java**

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        int n = nums1.length;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
