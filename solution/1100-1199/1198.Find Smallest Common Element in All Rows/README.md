# [1198. 找出所有行中最小公共元素](https://leetcode-cn.com/problems/find-smallest-common-element-in-all-rows)

[English Version](/solution/1100-1199/1198.Find%20Smallest%20Common%20Element%20in%20All%20Rows/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个矩阵 <code>mat</code>，其中每一行的元素都已经按 <strong>严格递增</strong> 顺序排好了。请你帮忙找出在所有这些行中 <strong>最小的公共元素</strong>。</p>

<p>如果矩阵中没有这样的公共元素，就请返回 <code>-1</code>。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
<strong>输出：</strong>5
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= mat.length, mat[i].length <= 500</code></li>
	<li><code>1 <= mat[i][j] <= 10^4</code></li>
	<li><code>mat[i]</code> 已按严格递增顺序排列。</li>
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
