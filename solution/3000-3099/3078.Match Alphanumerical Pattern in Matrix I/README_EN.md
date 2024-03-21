# [3078. Match Alphanumerical Pattern in Matrix I](https://leetcode.com/problems/match-alphanumerical-pattern-in-matrix-i)

[中文文档](/solution/3000-3099/3078.Match%20Alphanumerical%20Pattern%20in%20Matrix%20I/README.md)

<!-- tags:Array,Hash Table,String,Matrix -->

## Description

<p>You are given a 2D integer matrix <code>board</code> and a 2D character matrix <code>pattern</code>. Where <code>0 &lt;= board[r][c] &lt;= 9</code> and each element of <code>pattern</code> is either a digit or a lowercase English letter.</p>

<p>Your task is to find a <span data-keyword="submatrix">submatrix</span> of <code>board</code> that <strong>matches</strong> <code>pattern</code>.</p>

<p>An integer matrix <code>part</code> matches <code>pattern</code> if we can replace cells containing letters in <code>pattern</code> with some digits (each <strong>distinct</strong> letter with a <strong>unique</strong> digit) in such a way that the resulting matrix becomes identical to the integer matrix <code>part</code>. In other words,</p>

<ul>
	<li>The matrices have identical dimensions.</li>
	<li>If <code>pattern[r][c]</code> is a digit, then <code>part[r][c]</code> must be the <strong>same</strong> digit.</li>
	<li>If <code>pattern[r][c]</code> is a letter <code>x</code>:
	<ul>
		<li>For every <code>pattern[i][j] == x</code>, <code>part[i][j]</code> must be the <strong>same</strong> as <code>part[r][c]</code>.</li>
		<li>For every <code>pattern[i][j] != x</code>, <code>part[i][j]</code> must be <strong>different</strong> than <code>part[r][c]</code>.<span style="display: none;"> </span></li>
	</ul>
	</li>
</ul>

<p>Return <em>an array of length </em><code>2</code><em> containing the row number and column number of the upper-left corner of a submatrix of </em><code>board</code><em> which matches </em><code>pattern</code><em>. If there is more than one such submatrix, return the coordinates of the submatrix with the lowest row index, and in case there is still a tie, return the coordinates of the submatrix with the lowest column index. If there are no suitable answers, return</em> <code>[-1, -1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div style="display:flex; flex-wrap: wrap; align-items: flex-start; gap: 12px;">
<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid red; --darkreader-inline-border-top: #b30000; --darkreader-inline-border-right: #b30000; --darkreader-inline-border-bottom: #b30000; --darkreader-inline-border-left: #b30000;">1</td>
			<td style="padding: 5px 10px; border: 1px solid red; --darkreader-inline-border-top: #b30000; --darkreader-inline-border-right: #b30000; --darkreader-inline-border-bottom: #b30000; --darkreader-inline-border-left: #b30000;">2</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">2</td>
		</tr>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid red; --darkreader-inline-border-top: #b30000; --darkreader-inline-border-right: #b30000; --darkreader-inline-border-bottom: #b30000; --darkreader-inline-border-left: #b30000;">2</td>
			<td style="padding: 5px 10px; border: 1px solid red; --darkreader-inline-border-top: #b30000; --darkreader-inline-border-right: #b30000; --darkreader-inline-border-bottom: #b30000; --darkreader-inline-border-left: #b30000;">2</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">3</td>
		</tr>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">2</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">3</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">3</td>
		</tr>
	</tbody>
</table>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">a</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">b</td>
		</tr>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">b</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">b</td>
		</tr>
	</tbody>
</table>
</div>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">board = [[1,2,2],[2,2,3],[2,3,3]], pattern = [&quot;ab&quot;,&quot;bb&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0]</span></p>

<p><strong>Explanation:</strong> If we consider this mapping: <code>&quot;a&quot; -&gt; 1</code> and <code>&quot;b&quot; -&gt; 2</code>; the submatrix with the upper-left corner <code>(0,0)</code> is a match as outlined in the matrix above.</p>

<p>Note that the submatrix with the upper-left corner (1,1) is also a match but since it comes after the other one, we return <code>[0,0]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div style="display:flex; flex-wrap: wrap; align-items: flex-start; gap: 12px;">
<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">1</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">1</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">2</td>
		</tr>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">3</td>
			<td style="padding: 5px 10px; border: 1px solid red; --darkreader-inline-border-top: #b30000; --darkreader-inline-border-right: #b30000; --darkreader-inline-border-bottom: #b30000; --darkreader-inline-border-left: #b30000;">3</td>
			<td style="padding: 5px 10px; border: 1px solid red; --darkreader-inline-border-top: #b30000; --darkreader-inline-border-right: #b30000; --darkreader-inline-border-bottom: #b30000; --darkreader-inline-border-left: #b30000;">4</td>
		</tr>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">6</td>
			<td style="padding: 5px 10px; border: 1px solid red; --darkreader-inline-border-top: #b30000; --darkreader-inline-border-right: #b30000; --darkreader-inline-border-bottom: #b30000; --darkreader-inline-border-left: #b30000;">6</td>
			<td style="padding: 5px 10px; border: 1px solid red; --darkreader-inline-border-top: #b30000; --darkreader-inline-border-right: #b30000; --darkreader-inline-border-bottom: #b30000; --darkreader-inline-border-left: #b30000;">6</td>
		</tr>
	</tbody>
</table>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">a</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">b</td>
		</tr>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">6</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">6</td>
		</tr>
	</tbody>
</table>
</div>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">board = [[1,1,2],[3,3,4],[6,6,6]], pattern = [&quot;ab&quot;,&quot;66&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>Explanation:</strong> If we consider this mapping: <code>&quot;a&quot; -&gt; 3</code> and <code>&quot;b&quot; -&gt; 4</code>; the submatrix with the upper-left corner <code>(1,1)</code> is a match as outlined in the matrix above.</p>

<p>Note that since the corresponding values of <code>&quot;a&quot;</code> and <code>&quot;b&quot;</code> must differ, the submatrix with the upper-left corner <code>(1,0)</code> is not a match. Hence, we return <code>[1,1]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div style="display:flex; flex-wrap: wrap; align-items: flex-start; gap: 12px;">
<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">1</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">2</td>
		</tr>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">2</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">1</td>
		</tr>
	</tbody>
</table>

<table border="1" cellspacing="3" style="border-collapse: separate; text-align: center;">
	<tbody>
		<tr>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">x</td>
			<td style="padding: 5px 10px; border: 1px solid black; --darkreader-inline-border-top: #8c8273; --darkreader-inline-border-right: #8c8273; --darkreader-inline-border-bottom: #8c8273; --darkreader-inline-border-left: #8c8273;">x</td>
		</tr>
	</tbody>
</table>
</div>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">board = [[1,2],[2,1]], pattern = [&quot;xx&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>Explanation:</strong> Since the values of the matched submatrix must be the same, there is no match. Hence, we return <code>[-1,-1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= board.length &lt;= 50</code></li>
	<li><code>1 &lt;= board[i].length &lt;= 50</code></li>
	<li><code>0 &lt;= board[i][j] &lt;= 9</code></li>
	<li><code>1 &lt;= pattern.length &lt;= 50</code></li>
	<li><code>1 &lt;= pattern[i].length &lt;= 50</code></li>
	<li><code>pattern[i][j]</code> is either a digit represented as a string or a lowercase English letter.</li>
</ul>

## Solutions

### Solution 1: Enumeration

Let's denote $m$ and $n$ as the number of rows and columns in the matrix `board`, and $r$ and $c$ as the number of rows and columns in the matrix `pattern`.

We can enumerate each possible sub-matrix's top-left position $(i, j)$ in the `board` from small to large, and then determine whether the $r \times c$ sub-matrix with $(i, j)$ as the top-left corner matches `pattern`. If we find a matching sub-matrix, we return $(i, j)$. Otherwise, we return $(-1, -1)$.

The time complexity is $O(m \times n \times r \times c)$, where $m$ and $n$ are the number of rows and columns in the matrix `board`, and $r$ and $c$ are the number of rows and columns in the matrix `pattern`. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the character set. In this problem, $\Sigma$ includes numbers and lowercase letters, so $|\Sigma| \leq 36$.

<!-- tabs:start -->

```python
class Solution:
    def findPattern(self, board: List[List[int]], pattern: List[str]) -> List[int]:
        def check(i: int, j: int) -> bool:
            d1 = {}
            d2 = {}
            for a in range(r):
                for b in range(c):
                    x, y = i + a, j + b
                    if pattern[a][b].isdigit():
                        if int(pattern[a][b]) != board[x][y]:
                            return False
                    else:
                        if pattern[a][b] in d1 and d1[pattern[a][b]] != board[x][y]:
                            return False
                        if board[x][y] in d2 and d2[board[x][y]] != pattern[a][b]:
                            return False
                        d1[pattern[a][b]] = board[x][y]
                        d2[board[x][y]] = pattern[a][b]
            return True

        m, n = len(board), len(board[0])
        r, c = len(pattern), len(pattern[0])
        for i in range(m - r + 1):
            for j in range(n - c + 1):
                if check(i, j):
                    return [i, j]
        return [-1, -1]
```

```java
class Solution {
    public int[] findPattern(int[][] board, String[] pattern) {
        int m = board.length, n = board[0].length;
        int r = pattern.length, c = pattern[0].length();
        for (int i = 0; i < m - r + 1; ++i) {
            for (int j = 0; j < n - c + 1; ++j) {
                if (check(board, pattern, i, j)) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    private boolean check(int[][] board, String[] pattern, int i, int j) {
        int[] d1 = new int[26];
        int[] d2 = new int[10];
        Arrays.fill(d1, -1);
        Arrays.fill(d2, -1);
        for (int a = 0; a < pattern.length; ++a) {
            for (int b = 0; b < pattern[0].length(); ++b) {
                int x = i + a, y = j + b;
                if (Character.isDigit(pattern[a].charAt(b))) {
                    int v = pattern[a].charAt(b) - '0';
                    if (v != board[x][y]) {
                        return false;
                    }
                } else {
                    int v = pattern[a].charAt(b) - 'a';
                    if (d1[v] != -1 && d1[v] != board[x][y]) {
                        return false;
                    }
                    if (d2[board[x][y]] != -1 && d2[board[x][y]] != v) {
                        return false;
                    }
                    d1[v] = board[x][y];
                    d2[board[x][y]] = v;
                }
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findPattern(vector<vector<int>>& board, vector<string>& pattern) {
        int m = board.size(), n = board[0].size();
        int r = pattern.size(), c = pattern[0].size();
        auto check = [&](int i, int j) {
            vector<int> d1(26, -1);
            vector<int> d2(10, -1);
            for (int a = 0; a < r; ++a) {
                for (int b = 0; b < c; ++b) {
                    int x = i + a, y = j + b;
                    if (isdigit(pattern[a][b])) {
                        int v = pattern[a][b] - '0';
                        if (v != board[x][y]) {
                            return false;
                        }
                    } else {
                        int v = pattern[a][b] - 'a';
                        if (d1[v] != -1 && d1[v] != board[x][y]) {
                            return false;
                        }
                        if (d2[board[x][y]] != -1 && d2[board[x][y]] != v) {
                            return false;
                        }
                        d1[v] = board[x][y];
                        d2[board[x][y]] = v;
                    }
                }
            }
            return true;
        };
        for (int i = 0; i < m - r + 1; ++i) {
            for (int j = 0; j < n - c + 1; ++j) {
                if (check(i, j)) {
                    return {i, j};
                }
            }
        }
        return {-1, -1};
    }
};
```

```go
func findPattern(board [][]int, pattern []string) []int {
	m, n := len(board), len(board[0])
	r, c := len(pattern), len(pattern[0])
	check := func(i, j int) bool {
		d1 := [26]int{}
		d2 := [10]int{}
		for a := 0; a < r; a++ {
			for b := 0; b < c; b++ {
				x, y := i+a, j+b
				if pattern[a][b] >= '0' && pattern[a][b] <= '9' {
					v := int(pattern[a][b] - '0')
					if v != board[x][y] {
						return false
					}
				} else {
					v := int(pattern[a][b] - 'a')
					if d1[v] > 0 && d1[v]-1 != board[x][y] {
						return false
					}
					if d2[board[x][y]] > 0 && d2[board[x][y]]-1 != v {
						return false
					}
					d1[v] = board[x][y] + 1
					d2[board[x][y]] = v + 1
				}
			}
		}
		return true
	}
	for i := 0; i < m-r+1; i++ {
		for j := 0; j < n-c+1; j++ {
			if check(i, j) {
				return []int{i, j}
			}
		}
	}
	return []int{-1, -1}
}
```

```ts
function findPattern(board: number[][], pattern: string[]): number[] {
    const m: number = board.length;
    const n: number = board[0].length;
    const r: number = pattern.length;
    const c: number = pattern[0].length;

    const check = (i: number, j: number): boolean => {
        const d1: number[] = Array(26).fill(-1);
        const d2: number[] = Array(10).fill(-1);

        for (let a = 0; a < r; ++a) {
            for (let b = 0; b < c; ++b) {
                const x: number = i + a;
                const y: number = j + b;

                if (!isNaN(Number(pattern[a][b]))) {
                    const v: number = Number(pattern[a][b]);
                    if (v !== board[x][y]) {
                        return false;
                    }
                } else {
                    const v: number = pattern[a].charCodeAt(b) - 'a'.charCodeAt(0);
                    if (d1[v] !== -1 && d1[v] !== board[x][y]) {
                        return false;
                    }
                    if (d2[board[x][y]] !== -1 && d2[board[x][y]] !== v) {
                        return false;
                    }
                    d1[v] = board[x][y];
                    d2[board[x][y]] = v;
                }
            }
        }
        return true;
    };

    for (let i = 0; i < m - r + 1; ++i) {
        for (let j = 0; j < n - c + 1; ++j) {
            if (check(i, j)) {
                return [i, j];
            }
        }
    }
    return [-1, -1];
}
```

<!-- tabs:end -->

<!-- end -->
