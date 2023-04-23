# [2624. 蜗牛排序](https://leetcode.cn/problems/snail-traversal)

[English Version](/solution/2600-2699/2624.Snail%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一段代码为所有数组实现&nbsp;&nbsp;<code>snail(rowsCount，colsCount)</code> 方法，该方法将 1D 数组转换为以蜗牛排序的模式的 2D 数组。无效的输入值应该输出一个空数组。当 <code>rowsCount * colsCount&nbsp;!==</code><code>nums.length</code>&nbsp;时。这个输入被认为是无效的。</p>

<p>蜗牛排序从左上角的单元格开始，从当前数组的第一个值开始。然后，它从上到下遍历第一列，接着移动到右边的下一列，并从下到上遍历它。将这种模式持续下去，每列交替变换遍历方向，直到覆盖整个数组。例如，当给定输入数组&nbsp;&nbsp;<code>[19, 10, 3, 7, 9, 8, 5, 2, 1, 17, 16, 14, 12, 18, 6, 13, 11, 20, 4, 15]</code> ，当 <code>rowsCount = 5</code>&nbsp;且&nbsp;<code>colsCount = 4</code> 时，需要输出矩阵如下图所示。注意，矩阵沿箭头方向对应于原数组中数字的顺序</p>

<p>&nbsp;</p>

<p><img alt="Traversal Diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2624.Snail%20Traversal/images/screen-shot-2023-04-10-at-100006-pm.png" style="width: 275px; height: 343px;" /></p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>
nums = [19, 10, 3, 7, 9, 8, 5, 2, 1, 17, 16, 14, 12, 18, 6, 13, 11, 20, 4, 15]
rowsCount = 5
colsCount = 4
<b>输出：</b>
[
 [19,17,16,15],
&nbsp;[10,1,14,4],
&nbsp;[3,2,12,20],
&nbsp;[7,5,18,11],
&nbsp;[9,8,6,13]
]
</pre>

<p><b>示例 2：</b></p>

<pre>
<b>输入：</b>
nums = [1,2,3,4]
rowsCount = 1
colsCount = 4
<b>输出：</b>[[1, 2, 3, 4]]
</pre>

<p><b>示例 3：</b></p>

<pre>
<b>输入：</b>
nums = [1,3]
rowsCount = 2
colsCount = 2
<b>输出：</b>[]
<strong>Explanation:</strong> 2 * 2 = 4, 且原数组 [1,3] 的长度为 2; 所以，输入是无效的。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 250</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= rowsCount &lt;= 250</code></li>
	<li><code>1 &lt;= colsCount &lt;= 250</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们首先判断数组的长度是否等于行数与列数的乘积，如果不等，说明输入是无效的，返回空数组。

接下来，我们可以模拟蜗牛排序的过程，从左上角开始，遍历数组，按照蜗牛排序的顺序，将遍历到的元素依次放入结果数组中。

时间复杂度 $(n)$，其中 $n$ 为数组的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
declare global {
    interface Array<T> {
        snail(rowsCount: number, colsCount: number): number[][];
    }
}

Array.prototype.snail = function (
    rowsCount: number,
    colsCount: number,
): number[][] {
    if (rowsCount * colsCount !== this.length) {
        return [];
    }
    const ans: number[][] = Array.from({ length: rowsCount }, () =>
        Array(colsCount),
    );
    for (let h = 0, i = 0, j = 0, k = 1; h < this.length; ++h) {
        ans[i][j] = this[h];
        i += k;
        if (i === rowsCount || i === -1) {
            i -= k;
            k = -k;
            ++j;
        }
    }
    return ans;
};

/**
 * const arr = [1,2,3,4];
 * arr.snail(1,4); // [[1,2,3,4]]
 */
```

### **...**

```

```

<!-- tabs:end -->
