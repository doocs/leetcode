# [1253. 重构 2 行二进制矩阵](https://leetcode.cn/problems/reconstruct-a-2-row-binary-matrix)

[English Version](/solution/1200-1299/1253.Reconstruct%20a%202-Row%20Binary%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>2</code>&nbsp;行 <code>n</code> 列的二进制数组：</p>

<ul>
	<li>矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code>。</li>
	<li>第 <code>0</code> 行的元素之和为&nbsp;<code>upper</code>。</li>
	<li>第 <code>1</code> 行的元素之和为 <code>lower</code>。</li>
	<li>第 <code>i</code> 列（从 <code>0</code> 开始编号）的元素之和为&nbsp;<code>colsum[i]</code>，<code>colsum</code>&nbsp;是一个长度为&nbsp;<code>n</code>&nbsp;的整数数组。</li>
</ul>

<p>你需要利用&nbsp;<code>upper</code>，<code>lower</code>&nbsp;和&nbsp;<code>colsum</code>&nbsp;来重构这个矩阵，并以二维整数数组的形式返回它。</p>

<p>如果有多个不同的答案，那么任意一个都可以通过本题。</p>

<p>如果不存在符合要求的答案，就请返回一个空的二维数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>upper = 2, lower = 1, colsum = [1,1,1]
<strong>输出：</strong>[[1,1,0],[0,0,1]]
<strong>解释：</strong>[[1,0,1],[0,1,0]] 和 [[0,1,1],[1,0,0]] 也是正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>upper = 2, lower = 3, colsum = [2,2,1,1]
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
<strong>输出：</strong>[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= colsum.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= upper, lower &lt;= colsum.length</code></li>
	<li><code>0 &lt;= colsum[i] &lt;= 2</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们先创建一个答案数组 `ans`，其中 `ans[0]` 和 `ans[1]` 分别表示第 $0$ 行和第 $1$ 行的元素。

对于 `colsum` 中的每个元素 $v$：

-   如果 $v = 2$，那么我们将 `ans[0][j]` 和 `ans[1][j]` 都置为 $1$，其中 $j$ 是当前元素的下标。此时 `upper` 和 `lower` 都减去 $1$。
-   如果 $v = 1$，那么我们将 `ans[0][j]` 或 `ans[1][j]` 置为 $1$，其中 $j$ 是当前元素的下标。如果 `upper` 大于 `lower`，那么我们优先将 `ans[0][j]` 置为 $1$，否则我们优先将 `ans[1][j]` 置为 $1$。此时 `upper` 或 `lower` 减去 $1$。
-   如果 $v = 0$，那么我们将 `ans[0][j]` 和 `ans[1][j]` 都置为 $0$，其中 $j$ 是当前元素的下标。
-   如果 `upper` 或 `lower` 小于 $0$，那么我们直接返回一个空数组。

遍历完 `colsum` 后，如果 `upper` 和 `lower` 都为 $0$，那么我们返回 `ans`，否则我们返回一个空数组。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是 `colsum` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def reconstructMatrix(self, upper: int, lower: int, colsum: List[int]) -> List[List[int]]:
        n = len(colsum)
        ans = [[0] * n for _ in range(2)]
        for j, v in enumerate(colsum):
            if v == 2:
                ans[0][j] = ans[1][j] = 1
                upper, lower = upper - 1, lower - 1
            if v == 1:
                if upper > lower:
                    upper -= 1
                    ans[0][j] = 1
                else:
                    lower -= 1
                    ans[1][j] = 1
            if upper < 0 or lower < 0:
                return []
        return ans if lower == upper == 0 else []
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            if (colsum[j] == 2) {
                first.add(1);
                second.add(1);
                upper--;
                lower--;
            } else if (colsum[j] == 1) {
                if (upper > lower) {
                    upper--;
                    first.add(1);
                    second.add(0);
                } else {
                    lower--;
                    first.add(0);
                    second.add(1);
                }
            } else {
                first.add(0);
                second.add(0);
            }
            if (upper < 0 || lower < 0) {
                return ans;
            }
        }
        if (upper != 0 || lower != 0) {
            return ans;
        }
        ans.add(first);
        ans.add(second);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> reconstructMatrix(int upper, int lower, vector<int>& colsum) {
        int n = colsum.size();
        vector<vector<int>> ans(2, vector<int>(n));
        for (int j = 0; j < n; ++j) {
            if (colsum[j] == 2) {
                ans[0][j] = ans[1][j] = 1;
                upper--;
                lower--;
            }
            if (colsum[j] == 1) {
                if (upper > lower) {
                    upper--;
                    ans[0][j] = 1;
                } else {
                    lower--;
                    ans[1][j] = 1;
                }
            }
            if (upper < 0 || lower < 0) {
                return {};
            }
        }
        return upper != 0 || lower != 0 ? vector<vector<int>>{} : ans;
    }
};
```

### **Go**

```go
func reconstructMatrix(upper int, lower int, colsum []int) [][]int {
	n := len(colsum)
	ans := make([][]int, 2)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for j, v := range colsum {
		if v == 2 {
			ans[0][j], ans[1][j] = 1, 1
			upper--
			lower--
		}
		if v == 1 {
			if upper > lower {
				upper--
				ans[0][j] = 1
			} else {
				lower--
				ans[1][j] = 1
			}
		}
		if upper < 0 || lower < 0 {
			return [][]int{}
		}
	}
	if upper != 0 || lower != 0 {
		return [][]int{}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
