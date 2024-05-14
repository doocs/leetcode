---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2643.Row%20With%20Maximum%20Ones/README_EN.md
rating: 1174
tags:
    - Array
    - Matrix
---

# [2643. Row With Maximum Ones](https://leetcode.com/problems/row-with-maximum-ones)

[中文文档](/solution/2600-2699/2643.Row%20With%20Maximum%20Ones/README.md)

## Description

<p>Given a <code>m x n</code> binary matrix <code>mat</code>, find the <strong>0-indexed</strong> position of the row that contains the <strong>maximum</strong> count of <strong>ones,</strong> and the number of ones in that row.</p>

<p>In case there are multiple rows that have the maximum count of ones, the row with the <strong>smallest row number</strong> should be selected.</p>

<p>Return<em> an array containing the index of the row, and the number of ones in it.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> mat = [[0,1],[1,0]]
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> Both rows have the same number of 1&#39;s. So we return the index of the smaller row, 0, and the maximum count of ones (1<code>)</code>. So, the answer is [0,1]. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[0,0,0],[0,1,1]]
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> The row indexed 1 has the maximum count of ones <code>(2)</code>. So we return its index, <code>1</code>, and the count. So, the answer is [1,2].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> mat = [[0,0],[1,1],[0,0]]
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> The row indexed 1 has the maximum count of ones (2). So the answer is [1,2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code>&nbsp;</li>
	<li><code>n == mat[i].length</code>&nbsp;</li>
	<li><code>1 &lt;= m, n &lt;= 100</code>&nbsp;</li>
	<li><code>mat[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

### Solution 1: Simulation

We directly traverse the matrix, count the number of $1$s in each row, and update the maximum value and the corresponding row index. Note that if the number of $1$s in the current row is equal to the maximum value, we need to choose the row with the smaller index.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def rowAndMaximumOnes(self, mat: List[List[int]]) -> List[int]:
        ans = [0, 0]
        for i, row in enumerate(mat):
            cnt = row.count(1)
            if ans[1] < cnt:
                ans = [i, cnt]
        return ans
```

```java
class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int[] ans = new int[2];
        for (int i = 0; i < mat.length; ++i) {
            int cnt = 0;
            for (int x : mat[i]) {
                if (x == 1) {
                    ++cnt;
                }
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

```cpp
class Solution {
public:
    vector<int> rowAndMaximumOnes(vector<vector<int>>& mat) {
        vector<int> ans(2);
        for (int i = 0; i < mat.size(); ++i) {
            int cnt = 0;
            for (auto& x : mat[i]) {
                cnt += x == 1;
            }
            if (ans[1] < cnt) {
                ans[0] = i;
                ans[1] = cnt;
            }
        }
        return ans;
    }
};
```

```go
func rowAndMaximumOnes(mat [][]int) []int {
	ans := make([]int, 2)
	for i, row := range mat {
		cnt := 0
		for _, x := range row {
			if x == 1 {
				cnt++
			}
		}
		if ans[1] < cnt {
			ans[0], ans[1] = i, cnt
		}
	}
	return ans
}
```

```ts
function rowAndMaximumOnes(mat: number[][]): number[] {
    const ans: number[] = [0, 0];
    for (let i = 0; i < mat.length; ++i) {
        const cnt = mat[i].reduce((a, b) => a + b);
        if (ans[1] < cnt) {
            ans[0] = i;
            ans[1] = cnt;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn row_and_maximum_ones(mat: Vec<Vec<i32>>) -> Vec<i32> {
        let mut ans = vec![0, 0];

        for (i, row) in mat.iter().enumerate() {
            let cnt = row
                .iter()
                .filter(|&v| *v == 1)
                .count() as i32;
            if ans[1] < cnt {
                ans[0] = i as i32;
                ans[1] = cnt;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
