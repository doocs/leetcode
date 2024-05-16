---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1072.Flip%20Columns%20For%20Maximum%20Number%20of%20Equal%20Rows/README_EN.md
rating: 1797
source: Weekly Contest 139 Q2
tags:
    - Array
    - Hash Table
    - Matrix
---

# [1072. Flip Columns For Maximum Number of Equal Rows](https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows)

[中文文档](/solution/1000-1099/1072.Flip%20Columns%20For%20Maximum%20Number%20of%20Equal%20Rows/README.md)

## Description

<p>You are given an <code>m x n</code> binary matrix <code>matrix</code>.</p>

<p>You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from <code>0</code> to <code>1</code> or vice versa).</p>

<p>Return <em>the maximum number of rows that have all values equal after some number of flips</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[0,1],[1,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> After flipping no values, 1 row has all values equal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[0,1],[1,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> After flipping values in the first column, both rows have equal values.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[0,0,0],[0,0,1],[1,1,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> After flipping values in the first two columns, the last two rows have equal values.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>matrix[i][j]</code> is either&nbsp;<code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def maxEqualRowsAfterFlips(self, matrix: List[List[int]]) -> int:
        cnt = Counter()
        for row in matrix:
            t = tuple(row) if row[0] == 0 else tuple(x ^ 1 for x in row)
            cnt[t] += 1
        return max(cnt.values())
```

```java
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> cnt = new HashMap<>();
        int ans = 0, n = matrix[0].length;
        for (var row : matrix) {
            char[] cs = new char[n];
            for (int i = 0; i < n; ++i) {
                cs[i] = (char) (row[0] ^ row[i]);
            }
            ans = Math.max(ans, cnt.merge(String.valueOf(cs), 1, Integer::sum));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxEqualRowsAfterFlips(vector<vector<int>>& matrix) {
        unordered_map<string, int> cnt;
        int ans = 0;
        for (auto& row : matrix) {
            string s;
            for (int x : row) {
                s.push_back('0' + (row[0] == 0 ? x : x ^ 1));
            }
            ans = max(ans, ++cnt[s]);
        }
        return ans;
    }
};
```

```go
func maxEqualRowsAfterFlips(matrix [][]int) (ans int) {
	cnt := map[string]int{}
	for _, row := range matrix {
		s := []byte{}
		for _, x := range row {
			if row[0] == 1 {
				x ^= 1
			}
			s = append(s, byte(x)+'0')
		}
		t := string(s)
		cnt[t]++
		ans = max(ans, cnt[t])
	}
	return
}
```

```ts
function maxEqualRowsAfterFlips(matrix: number[][]): number {
    const cnt = new Map<string, number>();
    let ans = 0;
    for (const row of matrix) {
        if (row[0] === 1) {
            for (let i = 0; i < row.length; i++) {
                row[i] ^= 1;
            }
        }
        const s = row.join('');
        cnt.set(s, (cnt.get(s) || 0) + 1);
        ans = Math.max(ans, cnt.get(s)!);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
