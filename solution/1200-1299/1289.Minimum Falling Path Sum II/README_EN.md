# [1289. Minimum Falling Path Sum II](https://leetcode.com/problems/minimum-falling-path-sum-ii)

[中文文档](/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/README.md)

## Description

<p>Given an <code>n x n</code> integer matrix <code>grid</code>, return <em>the minimum sum of a <strong>falling path with non-zero shifts</strong></em>.</p>

<p>A <strong>falling path with non-zero shifts</strong> is a choice of exactly one element from each row of <code>grid</code> such that no two elements chosen in adjacent rows are in the same column.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/images/falling-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> arr = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> 13
<strong>Explanation:</strong> 
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is&nbsp;[1,5,7], so the answer is&nbsp;13.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[7]]
<strong>Output:</strong> 7
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>-99 &lt;= grid[i][j] &lt;= 99</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int[] res = new int[nums.length];
        int leftInit = 0;
        if (nums.length < k) {
            k = k % nums.length;
        }
        for (int i = nums.length - k; i < nums.length; i++) {
            res[leftInit] = nums[i];
            leftInit++;
        }
        int rightInit = 0;
        for (int i = k; i < nums.length; i++) {
            res[i] = nums[rightInit];
            rightInit++;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }


    }
}
```

### **...**

```

```

<!-- tabs:end -->
