# [189. Rotate Array](https://leetcode.com/problems/rotate-array)

[中文文档](/solution/0100-0199/0189.Rotate%20Array/README.md)

## Description

<p>Given an array, rotate the array to the right by <em>k</em> steps, where&nbsp;<em>k</em>&nbsp;is non-negative.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> <code>[1,2,3,4,5,6,7]</code> and <em>k</em> = 3

<strong>Output:</strong> <code>[5,6,7,1,2,3,4]</code>

<strong>Explanation:</strong>

rotate 1 steps to the right: <code>[7,1,2,3,4,5,6]</code>

rotate 2 steps to the right: <code>[6,7,1,2,3,4,5]

</code>rotate 3 steps to the right: <code>[5,6,7,1,2,3,4]</code>

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> <code>[-1,-100,3,99]</code> and <em>k</em> = 2

<strong>Output:</strong> [3,99,-1,-100]

<strong>Explanation:</strong> 

rotate 1 steps to the right: [99,-1,-100,3]

rotate 2 steps to the right: [3,99,-1,-100]

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li>Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.</li>
	<li>Could you do it in-place with O(1) extra space?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        n = len(nums)
        k %= n
        if n < 2 or k == 0:
            return
        nums[:] = nums[::-1]
        nums[:k] = nums[:k][::-1]
        nums[k:] = nums[k:][::-1]
```

### **Java**

```java
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null) {
            return;
        }
        int n = nums.length;
        k %= n;
        if (n < 2 || k == 0) {
            return;
        }

        rotate(nums, 0, n - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, n - 1);
    }

    private void rotate(int[] nums, int i, int j) {
        while (i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            ++i;
            --j;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
