# [2022. 将一维数组转变成二维数组](https://leetcode.cn/problems/convert-1d-array-into-2d-array)

[English Version](/solution/2000-2099/2022.Convert%201D%20Array%20Into%202D%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的一维整数数组&nbsp;<code>original</code>&nbsp;和两个整数&nbsp;<code>m</code>&nbsp;和&nbsp;&nbsp;<code>n</code>&nbsp;。你需要使用&nbsp;<code>original</code>&nbsp;中&nbsp;<strong>所有</strong>&nbsp;元素创建一个&nbsp;<code>m</code>&nbsp;行&nbsp;<code>n</code>&nbsp;列的二维数组。</p>

<p><code>original</code>&nbsp;中下标从 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;（都 <strong>包含</strong> ）的元素构成二维数组的第一行，下标从 <code>n</code>&nbsp;到 <code>2 * n - 1</code>&nbsp;（都 <strong>包含</strong>&nbsp;）的元素构成二维数组的第二行，依此类推。</p>

<p>请你根据上述过程返回一个<em>&nbsp;</em><code>m x n</code>&nbsp;的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2022.Convert%201D%20Array%20Into%202D%20Array/images/image-20210826114243-1.png" style="width: 500px; height: 174px;">
<pre><b>输入：</b>original = [1,2,3,4], m = 2, n = 2
<b>输出：</b>[[1,2],[3,4]]
<strong>解释：
</strong>构造出的二维数组应该包含 2 行 2 列。
original 中第一个 n=2 的部分为 [1,2] ，构成二维数组的第一行。
original 中第二个 n=2 的部分为 [3,4] ，构成二维数组的第二行。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>original = [1,2,3], m = 1, n = 3
<b>输出：</b>[[1,2,3]]
<b>解释：</b>
构造出的二维数组应该包含 1 行 3 列。
将 original 中所有三个元素放入第一行中，构成要求的二维数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>original = [1,2], m = 1, n = 1
<b>输出：</b>[]
<strong>解释：
</strong>original 中有 2 个元素。
无法将 2 个元素放入到一个 1x1 的二维数组中，所以返回一个空的二维数组。
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>original = [3], m = 1, n = 2
<b>输出：</b>[]
<strong>解释：</strong>
original 中只有 1 个元素。
无法将 1 个元素放满一个 1x2 的二维数组，所以返回一个空的二维数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= original.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= original[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m, n &lt;= 4 * 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题目描述，我们知道，要想构造出一个 $m$ 行 $n$ 列的二维数组，需要满足 $m \times n$ 等于原数组的长度。如果不满足，直接返回空数组即可。

如果满足，我们可以按照题目描述的过程，将原数组中的元素依次放入二维数组中即可。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别为二维数组的行数和列数。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def construct2DArray(self, original: List[int], m: int, n: int) -> List[List[int]]:
        if m * n != len(original):
            return []
        return [original[i : i + n] for i in range(0, m * n, n)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (m * n != original.length) {
            return new int[0][0];
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = original[i * n + j];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> construct2DArray(vector<int>& original, int m, int n) {
        if (m * n != original.size()) {
            return {};
        }
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = original[i * n + j];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func construct2DArray(original []int, m int, n int) (ans [][]int) {
	if m*n != len(original) {
		return [][]int{}
	}
	for i := 0; i < m*n; i += n {
		ans = append(ans, original[i:i+n])
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} original
 * @param {number} m
 * @param {number} n
 * @return {number[][]}
 */
var construct2DArray = function (original, m, n) {
    if (m * n != original.length) {
        return [];
    }
    const ans = [];
    for (let i = 0; i < m * n; i += n) {
        ans.push(original.slice(i, i + n));
    }
    return ans;
};
```

### **TypeScript**

```ts
function construct2DArray(
    original: number[],
    m: number,
    n: number,
): number[][] {
    if (m * n != original.length) {
        return [];
    }
    const ans: number[][] = [];
    for (let i = 0; i < m * n; i += n) {
        ans.push(original.slice(i, i + n));
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
