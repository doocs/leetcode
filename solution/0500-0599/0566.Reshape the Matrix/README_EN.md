# [566. Reshape the Matrix](https://leetcode.com/problems/reshape-the-matrix)

[中文文档](/solution/0500-0599/0566.Reshape%20the%20Matrix/README.md)

## Description

<p>In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.

</p>



<p>

You're given a matrix represented by a two-dimensional array, and two <b>positive</b> integers <b>r</b> and <b>c</b> representing the <b>row</b> number and <b>column</b> number of the wanted reshaped matrix, respectively.</p>



 <p>The reshaped matrix need to be filled with all the elements of the original matrix in the same <b>row-traversing</b> order as they were.

</p>



<p>

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.

</p>



<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> 

nums = 

[[1,2],

 [3,4]]

r = 1, c = 4

<b>Output:</b> 

[[1,2,3,4]]

<b>Explanation:</b><br>The <b>row-traversing</b> of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.

</pre>

</p>



<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> 

nums = 

[[1,2],

 [3,4]]

r = 2, c = 4

<b>Output:</b> 

[[1,2],

 [3,4]]

<b>Explanation:</b><br>There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.

</pre>

</p>



<p><b>Note:</b><br>

<ol>

<li>The height and width of the given matrix is in range [1, 100].</li>

<li>The given r and c are all positive.</li>

</ol>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def matrixReshape(self, nums: List[List[int]], r: int, c: int) -> List[List[int]]:
        m, n = len(nums), len(nums[0])
        if m * n != r * c:
            return nums
        res = [[0] * c for _ in range(r)]
        for x in range(m * n):
            res[x // c][x % c] = nums[x // n][x % n]
        return res
```

### **Java**

```java
class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) return nums;
        int[][] res = new int[r][c];
        for (int i = 0; i < m * n; ++i) {
            res[i / c][i % c] = nums[i / n][i % n];
        }
        return res;
    }
}
```

### **TypeScrpt**

```ts
function matrixReshape(mat: number[][], r: number, c: number): number[][] {
    let m = mat.length, n = mat[0].length;
    if (m * n != r * c) return mat;
    let ans = Array.from({length: r}, v => new Array(c).fill(0));
    let k = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans[Math.floor(k / c)][k % c] = mat[i][j];
            ++k;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
