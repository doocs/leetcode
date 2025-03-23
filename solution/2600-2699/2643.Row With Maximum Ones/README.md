---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2643.Row%20With%20Maximum%20Ones/README.md
rating: 1174
source: 第 341 场周赛 Q1
tags:
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [2643. 一最多的行](https://leetcode.cn/problems/row-with-maximum-ones)

[English Version](/solution/2600-2699/2643.Row%20With%20Maximum%20Ones/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>m x n</code> 的二进制矩阵 <code>mat</code> ，请你找出包含最多 <strong>1</strong> 的行的下标（从 <strong>0</strong> 开始）以及这一行中 <strong>1</strong> 的数目。</p>

<p>如果有多行包含最多的 1 ，只需要选择 <strong>行下标最小</strong> 的那一行。</p>

<p>返回一个由行下标和该行中 1 的数量组成的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>mat = [[0,1],[1,0]]
<strong>输出：</strong>[0,1]
<strong>解释：</strong>两行中 1 的数量相同。所以返回下标最小的行，下标为 0 。该行 1 的数量为 1 。所以，答案为 [0,1] 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[0,0,0],[0,1,1]]
<strong>输出：</strong>[1,2]
<strong>解释：</strong>下标为 1 的行中 1 的数量最多<code>。</code>该行 1 的数量<code>为 2 。所以，答案为</code> [1,2] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>mat = [[0,0],[1,1],[0,0]]
<strong>输出：</strong>[1,2]
<strong>解释：</strong>下标为 1 的行中 1 的数量最多。该行 1 的数量<code>为 2 。所以，答案为</code> [1,2] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code>&nbsp;</li>
	<li><code>n == mat[i].length</code>&nbsp;</li>
	<li><code>1 &lt;= m, n &lt;= 100</code>&nbsp;</li>
	<li><code>mat[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们初始化一个数组 $\textit{ans} = [0, 0]$，用于记录最多 $1$ 的行的下标和 $1$ 的数量。

然后遍历矩阵的每一行，对于每一行：

-   计算该行 $1$ 的数量 $\textit{cnt}$（由于矩阵中只包含 $0$ 和 $1$，我们可以直接对该行求和）；
-   如果 $\textit{ans}[1] < \textit{cnt}$，则更新 $\textit{ans} = [i, \textit{cnt}]$。

遍历结束后，返回 $\textit{ans}$。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rowAndMaximumOnes(self, mat: List[List[int]]) -> List[int]:
        ans = [0, 0]
        for i, row in enumerate(mat):
            cnt = sum(row)
            if ans[1] < cnt:
                ans = [i, cnt]
        return ans
```

#### Java

```java
class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int[] ans = new int[2];
        for (int i = 0; i < mat.length; ++i) {
            int cnt = 0;
            for (int x : mat[i]) {
                cnt += x;
            }
            if (ans[1] < cnt) {
                ans[0] = i;
                ans[1] = cnt;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> rowAndMaximumOnes(vector<vector<int>>& mat) {
        vector<int> ans(2);
        for (int i = 0; i < mat.size(); ++i) {
            int cnt = accumulate(mat[i].begin(), mat[i].end(), 0);
            if (ans[1] < cnt) {
                ans = {i, cnt};
            }
        }
        return ans;
    }
};
```

#### Go

```go
func rowAndMaximumOnes(mat [][]int) []int {
	ans := []int{0, 0}
	for i, row := range mat {
		cnt := 0
		for _, x := range row {
			cnt += x
		}
		if ans[1] < cnt {
			ans = []int{i, cnt}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function rowAndMaximumOnes(mat: number[][]): number[] {
    const ans: number[] = [0, 0];
    for (let i = 0; i < mat.length; i++) {
        const cnt = mat[i].reduce((sum, num) => sum + num, 0);
        if (ans[1] < cnt) {
            ans[0] = i;
            ans[1] = cnt;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn row_and_maximum_ones(mat: Vec<Vec<i32>>) -> Vec<i32> {
        let mut ans = vec![0, 0];
        for (i, row) in mat.iter().enumerate() {
            let cnt = row.iter().sum();
            if ans[1] < cnt {
                ans = vec![i as i32, cnt];
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int[] RowAndMaximumOnes(int[][] mat) {
        int[] ans = new int[2];
        for (int i = 0; i < mat.Length; i++) {
            int cnt = mat[i].Sum();
            if (ans[1] < cnt) {
                ans = new int[] { i, cnt };
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
