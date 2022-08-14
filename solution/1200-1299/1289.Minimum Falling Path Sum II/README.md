# [1289. 下降路径最小和 II](https://leetcode.cn/problems/minimum-falling-path-sum-ii)

[English Version](/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>n x n</code> 整数矩阵&nbsp;<code>arr</code>&nbsp;，请你返回 <strong>非零偏移下降路径</strong> 数字和的最小值。</p>

<p><strong>非零偏移下降路径</strong> 定义为：从&nbsp;<code>arr</code> 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/images/falling-grid.jpg" style="width: 244px; height: 245px;" /></p>

<pre>
<strong>输入：</strong>arr = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>13
<strong>解释：</strong>
所有非零偏移下降路径包括：
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
下降路径中数字和最小的是&nbsp;[1,5,7] ，所以答案是&nbsp;13 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[7]]
<strong>输出：</strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>-99 &lt;= grid[i][j] &lt;= 99</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
