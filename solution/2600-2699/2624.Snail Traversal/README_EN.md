# [2624. Snail Traversal](https://leetcode.com/problems/snail-traversal)

[中文文档](/solution/2600-2699/2624.Snail%20Traversal/README.md)

## Description

<p>Write code that enhances all arrays such that you can call the <code>snail(rowsCount, colsCount)</code> method that transforms the 1D&nbsp;array into&nbsp;a 2D array organised in&nbsp;the pattern known as <strong>snail traversal order</strong>. Invalid input values should output an empty array. If&nbsp;<code>rowsCount * colsCount !== nums.length</code>,&nbsp;the input is considered invalid.</p>

<p><strong>Snail traversal order</strong><em>&nbsp;</em>starts at the top left cell with the first value of the current array. It then moves through the entire first column from top to bottom, followed by moving to the next column on the right and traversing it from bottom to top. This pattern continues, alternating the direction of traversal with each column, until the entire current array is covered. For example, when given the input array&nbsp;<code>[19, 10, 3, 7, 9, 8, 5, 2, 1, 17, 16, 14, 12, 18, 6, 13, 11, 20, 4, 15]</code> with <code>rowsCount = 5</code> and <code>colsCount = 4</code>,&nbsp;the desired output matrix is shown below. Note that iterating the matrix following the arrows corresponds to the order of numbers in the original array.</p>

<p>&nbsp;</p>

<p><img alt="Traversal Diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2624.Snail%20Traversal/images/screen-shot-2023-04-10-at-100006-pm.png" style="width: 275px; height: 343px;" /></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
nums = [19, 10, 3, 7, 9, 8, 5, 2, 1, 17, 16, 14, 12, 18, 6, 13, 11, 20, 4, 15]
rowsCount = 5
colsCount = 4
<strong>Output:</strong> 
[
 [19,17,16,15],
&nbsp;[10,1,14,4],
&nbsp;[3,2,12,20],
&nbsp;[7,5,18,11],
&nbsp;[9,8,6,13]
]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
nums = [1,2,3,4]
rowsCount = 1
colsCount = 4
<strong>Output:</strong> [[1, 2, 3, 4]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
nums = [1,3]
rowsCount = 2
colsCount = 2
<strong>Output:</strong> []
<strong>Explanation:</strong> 2 multiplied by 2 is 4, and the original array [1,3] has a length of 2; therefore, the input is invalid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 250</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= rowsCount &lt;= 250</code></li>
	<li><code>1 &lt;= colsCount &lt;= 250</code></li>
</ul>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **TypeScript**

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
