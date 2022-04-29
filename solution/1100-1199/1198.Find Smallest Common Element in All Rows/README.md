# [1198. 找出所有行中最小公共元素](https://leetcode.cn/problems/find-smallest-common-element-in-all-rows)

[English Version](/solution/1100-1199/1198.Find%20Smallest%20Common%20Element%20in%20All%20Rows/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的矩阵&nbsp;<code>mat</code>，其中每一行的元素均符合&nbsp;<strong>严格递增</strong> 。请返回 <em>所有行中的&nbsp;<strong>最小公共元素</strong>&nbsp;</em>。</p>

<p>如果矩阵中没有这样的公共元素，就请返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
<strong>输出：</strong>5
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>mat = [[1,2,3],[2,3,4],[2,3,5]]
<strong>输出：</strong> 2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 10<sup>4</sup></code></li>
	<li><code>mat[i]</code>&nbsp;已按严格递增顺序排列。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“计数器”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestCommonElement(self, mat: List[List[int]]) -> int:
        counter = Counter()
        for row in mat:
            for num in row:
                counter[num] += 1
                if counter[num] == len(mat):
                    return num
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int smallestCommonElement(int[][] mat) {
        int[] counter = new int[10001];
        for (int[] row : mat) {
            for (int num : row) {
                ++counter[num];
                if (counter[num] == mat.length) {
                    return num;
                }
            }
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
