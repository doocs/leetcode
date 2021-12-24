# [566. 重塑矩阵](https://leetcode-cn.com/problems/reshape-the-matrix)

[English Version](/solution/0500-0599/0566.Reshape%20the%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在MATLAB中，有一个非常有用的函数 <code>reshape</code>，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。</p>

<p>给出一个由二维数组表示的矩阵，以及两个正整数<code>r</code>和<code>c</code>，分别表示想要的重构的矩阵的行数和列数。</p>

<p>重构后的矩阵需要将原始矩阵的所有元素以相同的<strong>行遍历顺序</strong>填充。</p>

<p>如果具有给定参数的<code>reshape</code>操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
nums = 
[[1,2],
 [3,4]]
r = 1, c = 4
<strong>输出:</strong> 
[[1,2,3,4]]
<strong>解释:</strong>
行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> 
nums = 
[[1,2],
 [3,4]]
r = 2, c = 4
<strong>输出:</strong> 
[[1,2],
 [3,4]]
<strong>解释:</strong>
没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
</pre>

<p><strong>注意：</strong></p>

<ol>
	<li>给定矩阵的宽和高范围在 [1, 100]。</li>
	<li>给定的 r 和 c 都是正数。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **TypeScript**

```ts
function matrixReshape(mat: number[][], r: number, c: number): number[][] {
    let m = mat.length,
        n = mat[0].length;
    if (m * n != r * c) return mat;
    let ans = Array.from({ length: r }, v => new Array(c).fill(0));
    let k = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans[Math.floor(k / c)][k % c] = mat[i][j];
            ++k;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
