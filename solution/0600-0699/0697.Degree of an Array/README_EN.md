# [697. Degree of an Array](https://leetcode.com/problems/degree-of-an-array)

[中文文档](/solution/0600-0699/0697.Degree%20of%20an%20Array/README.md)

## Description

<p>Given a non-empty array of non-negative integers <code>nums</code>, the <b>degree</b> of this array is defined as the maximum frequency of any one of its elements.</p>

<p>Your task is to find the smallest possible length of a (contiguous) subarray of <code>nums</code>, that has the same degree as <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,3,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,3,1,4,2]
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
The degree is 3 because the element 2 is repeated 3 times.
So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length</code> will be between 1 and 50,000.</li>
	<li><code>nums[i]</code> will be an integer between 0 and 49,999.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findShortestSubArray(self, nums: List[int]) -> int:
        mapper = {}
        for i, v in enumerate(nums):
            if v in mapper:
                arr = mapper[v]
                arr[0] += 1
                arr[2] = i
            else:
                arr = [1, i, i]
                mapper[v] = arr
        max_degree = min_len = 0
        for arr in mapper.values():
            if max_degree < arr[0]:
                max_degree = arr[0]
                min_len = arr[2] - arr[1] + 1
            elif max_degree == arr[0]:
                min_len = min(min_len, arr[2] - arr[1] + 1)
        return min_len
```

### **Java**

```java
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> mapper = new HashMap<>();
        for (int i = 0, n = nums.length; i < n; ++i) {
            if (mapper.containsKey(nums[i])) {
                int[] arr = mapper.get(nums[i]);
                ++arr[0];
                arr[2] = i;
            } else {
                int[] arr = new int[]{1, i, i};
                mapper.put(nums[i], arr);
            }
        }

        int maxDegree = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : mapper.entrySet()) {
            int[] arr = entry.getValue();
            if (maxDegree < arr[0]) {
                maxDegree = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxDegree == arr[0]) {
                minLen = Math.min(minLen, arr[2] - arr[1] + 1);
            }
        }
        return minLen;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
