# [2718. Sum of Matrix After Queries](https://leetcode.com/problems/sum-of-matrix-after-queries)

[中文文档](/solution/2700-2799/2718.Sum%20of%20Matrix%20After%20Queries/README.md)

<!-- tags:Array,Hash Table -->

## Description

<p>You are given an integer <code>n</code> and a <strong>0-indexed</strong>&nbsp;<strong>2D array</strong> <code>queries</code> where <code>queries[i] = [type<sub>i</sub>, index<sub>i</sub>, val<sub>i</sub>]</code>.</p>

<p>Initially, there is a <strong>0-indexed</strong> <code>n x n</code> matrix filled with <code>0</code>&#39;s. For each query, you must apply one of the following changes:</p>

<ul>
	<li>if <code>type<sub>i</sub> == 0</code>, set the values in the row with <code>index<sub>i</sub></code> to <code>val<sub>i</sub></code>, overwriting any previous values.</li>
	<li>if <code>type<sub>i</sub> == 1</code>, set the values in the column with <code>index<sub>i</sub></code> to <code>val<sub>i</sub></code>, overwriting any previous values.</li>
</ul>

<p>Return <em>the sum of integers in the matrix after all queries are applied</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2718.Sum%20of%20Matrix%20After%20Queries/images/exm1.png" style="width: 681px; height: 161px;" />
<pre>
<strong>Input:</strong> n = 3, queries = [[0,0,1],[1,2,2],[0,2,3],[1,0,4]]
<strong>Output:</strong> 23
<strong>Explanation:</strong> The image above describes the matrix after each query. The sum of the matrix after all queries are applied is 23. 
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2718.Sum%20of%20Matrix%20After%20Queries/images/exm2.png" style="width: 681px; height: 331px;" />
<pre>
<strong>Input:</strong> n = 3, queries = [[0,0,4],[0,1,2],[1,0,1],[0,2,3],[1,2,1]]
<strong>Output:</strong> 17
<strong>Explanation:</strong> The image above describes the matrix after each query. The sum of the matrix after all queries are applied is 17.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>queries[i].length == 3</code></li>
	<li><code>0 &lt;= type<sub>i</sub> &lt;= 1</code></li>
	<li><code>0 &lt;= index<sub>i</sub>&nbsp;&lt; n</code></li>
	<li><code>0 &lt;= val<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table

Since the value of each row and column depends on the last modification, we can traverse all queries in reverse order and use hash tables $row$ and $col$ to record which rows and columns have been modified.

For each query $(t, i, v)$:

-   If $t = 0$, we check whether the $i$th row has been modified. If not, we add $v \times (n - |col|)$ to the answer, where $|col|$ represents the size of $col$, and then add $i$ to $row$.
-   If $t = 1$, we check whether the $i$th column has been modified. If not, we add $v \times (n - |row|)$ to the answer, where $|row|$ represents the size of $row$, and then add $i$ to $col$.

Finally, return the answer.

The time complexity is $O(m)$, and the space complexity is $O(n)$. Here, $m$ represents the number of queries.

<!-- tabs:start -->

```python
class Solution:
    def matrixSumQueries(self, n: int, queries: List[List[int]]) -> int:
        row = set()
        col = set()
        ans = 0
        for t, i, v in queries[::-1]:
            if t == 0:
                if i not in row:
                    ans += v * (n - len(col))
                    row.add(i)
            else:
                if i not in col:
                    ans += v * (n - len(row))
                    col.add(i)
        return ans
```

```java
class Solution {
    public long matrixSumQueries(int n, int[][] queries) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        int m = queries.length;
        long ans = 0;
        for (int k = m - 1; k >= 0; --k) {
            var q = queries[k];
            int t = q[0], i = q[1], v = q[2];
            if (t == 0) {
                if (row.add(i)) {
                    ans += 1L * (n - col.size()) * v;
                }
            } else {
                if (col.add(i)) {
                    ans += 1L * (n - row.size()) * v;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long matrixSumQueries(int n, vector<vector<int>>& queries) {
        unordered_set<int> row, col;
        reverse(queries.begin(), queries.end());
        long long ans = 0;
        for (auto& q : queries) {
            int t = q[0], i = q[1], v = q[2];
            if (t == 0) {
                if (!row.count(i)) {
                    ans += 1LL * (n - col.size()) * v;
                    row.insert(i);
                }
            } else {
                if (!col.count(i)) {
                    ans += 1LL * (n - row.size()) * v;
                    col.insert(i);
                }
            }
        }
        return ans;
    }
};
```

```go
func matrixSumQueries(n int, queries [][]int) (ans int64) {
	row, col := map[int]bool{}, map[int]bool{}
	m := len(queries)
	for k := m - 1; k >= 0; k-- {
		t, i, v := queries[k][0], queries[k][1], queries[k][2]
		if t == 0 {
			if !row[i] {
				ans += int64(v * (n - len(col)))
				row[i] = true
			}
		} else {
			if !col[i] {
				ans += int64(v * (n - len(row)))
				col[i] = true
			}
		}
	}
	return
}
```

```ts
function matrixSumQueries(n: number, queries: number[][]): number {
    const row: Set<number> = new Set();
    const col: Set<number> = new Set();
    let ans = 0;
    queries.reverse();
    for (const [t, i, v] of queries) {
        if (t === 0) {
            if (!row.has(i)) {
                ans += v * (n - col.size);
                row.add(i);
            }
        } else {
            if (!col.has(i)) {
                ans += v * (n - row.size);
                col.add(i);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
