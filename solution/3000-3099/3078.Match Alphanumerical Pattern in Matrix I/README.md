# [3078. 矩阵中的字母数字模式匹配 I](https://leetcode.cn/problems/match-alphanumerical-pattern-in-matrix-i)

[English Version](/solution/3000-3099/3078.Match%20Alphanumerical%20Pattern%20in%20Matrix%20I/README_EN.md)

<!-- tags:数组,哈希表,字符串,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二维整数矩阵&nbsp;<code>board</code>&nbsp;和一个二维字符矩阵&nbsp;<code>pattern</code>。其中&nbsp;<code>0 &lt;= board[r][c] &lt;= 9</code>&nbsp;并且&nbsp;<code>pattern</code>&nbsp;的每个元素是一个数字或一个小写英文字母。</p>

<p>你的任务是找到 <strong>匹配</strong>&nbsp;<code>board</code>&nbsp;的子矩阵&nbsp;<code>pattern</code>。</p>

<p>如果我们能用一些数字（每个 <strong>不同</strong> 的字母对应 <strong>不同</strong> 的数字）替换&nbsp;<code>pattern</code>&nbsp;中包含的字母使得结果矩阵与整数矩阵&nbsp;<code>part</code>&nbsp;相同，我们称整数矩阵&nbsp;<code>part</code>&nbsp;与&nbsp;<code>pattern</code>&nbsp;匹配。换句话说，</p>

<ul>
	<li>这两个矩阵具有相同的维数。</li>
	<li>如果&nbsp;<code>pattern[r][c]</code>&nbsp;是一个数字，那么&nbsp;<code>part[r][c]</code>&nbsp;必须是&nbsp;<strong>相同的</strong> 数字。</li>
	<li>如果&nbsp;<code>pattern[r][c]</code>&nbsp;是一个字母&nbsp;<code>x</code>：
	<ul>
		<li>对于每个&nbsp;<code>pattern[i][j] == x</code>，<code>part[i][j]</code>&nbsp;一定与 <code>part[r][c]</code>&nbsp;<strong>相同</strong>。</li>
		<li>对于每个&nbsp;<code>pattern[i][j] != x</code>，<code>part[i][j]</code>&nbsp;一定与 <code>part[r][c]</code>&nbsp;<strong>不同</strong>。<span style="display: none;"> </span></li>
	</ul>
	</li>
</ul>

<p>返回一个长度为<em>&nbsp;</em><code>2</code>&nbsp;的数组，包含匹配&nbsp;<code>pattern</code>&nbsp;的&nbsp;<code>board</code>&nbsp;的子矩阵左上角的行号和列号。如果有多个这样的子矩阵，返回行号更小的子矩阵。如果依然有多个，则返回列号更小的子矩阵。如果没有符合的答案，返回&nbsp;<code>[-1, -1]</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

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
<p><strong>输入：</strong><span class="example-io">board = [[1,2,2],[2,2,3],[2,3,3]], pattern = ["ab","bb"]</span></p>

<p><strong>输出：</strong><span class="example-io">[0,0]</span></p>

<p><strong>解释：</strong>如果我们考虑这个映射：<code>"a" -&gt; 1</code> 并且&nbsp;<code>"b" -&gt; 2</code>；左上角为&nbsp;<code>(0,0)</code>&nbsp;的子矩阵与上面的矩阵中加粗的相匹配。</p>

<p>注意左上角为&nbsp;<code>(1,1)</code>&nbsp;的子矩阵同样匹配，但它在另一个之后出现，所以我们返回&nbsp;<code>[0,0]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

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
<p><strong>输入：</strong><span class="example-io">board = [[1,1,2],[3,3,4],[6,6,6]], pattern = ["ab","66"]</span></p>

<p><strong>输出：</strong><span class="example-io">[1,1]</span></p>

<p><strong>解释：</strong>如果我们考虑这个映射：<code>"a" -&gt; 3</code>&nbsp;并且&nbsp;<code>"b" -&gt; 4</code>；左上角为&nbsp;<code>(1,1)</code>&nbsp;的子矩阵与上面的矩阵中加粗的匹配。</p>

<p>注意&nbsp;<code>"a"</code>&nbsp;和&nbsp;<code>"b"</code>&nbsp;对应的值必须不同，左上角为 <code>(1,0)</code>&nbsp;的子矩阵不匹配。因此，我们返回&nbsp;<code>[1,1]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

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
<p><strong>输入：</strong><span class="example-io">board = [[1,2],[2,1]], pattern = ["xx"]</span></p>

<p><b>输出：</b><span class="example-io">[-1,-1]</span></p>

<p><strong>解释：</strong>&nbsp;由于匹配子矩阵的值必须相同，因此不存在匹配。因此，我们返回&nbsp;<code>[-1,-1]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= board.length &lt;= 50</code></li>
	<li><code>1 &lt;= board[i].length &lt;= 50</code></li>
	<li><code>0 &lt;= board[i][j] &lt;= 9</code></li>
	<li><code>1 &lt;= pattern.length &lt;= 50</code></li>
	<li><code>1 &lt;= pattern[i].length &lt;= 50</code></li>
	<li><code>pattern[i][j]</code> 表示为一个数字的字符串或一个小写英文字母。</li>
</ul>

## 解法

### 方法一：枚举

我们不妨记 $m$ 和 $n$ 分别为矩阵 `board` 的行数和列数，记 $r$ 和 $c$ 分别为矩阵 `pattern` 的行数和列数。

我们可以从小到大枚举矩阵 `board` 中的每一个可能的子矩阵的左上角位置 $(i, j)$，然后判断以 $(i, j)$ 为左上角的 $r \times c$ 的子矩阵是否与 `pattern` 匹配。如果找到了一个匹配的子矩阵，我们就返回 $(i, j)$。否则，我们返回 $(-1, -1)$。

时间复杂度 $O(m \times n \times r \times c)$，其中 $m$ 和 $n$ 分别是矩阵 `board` 的行数和列数，而 $r$ 和 $c$ 分别是矩阵 `pattern` 的行数和列数。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集，本题中 $\Sigma$ 包括数字和小写字母，因此 $|\Sigma| \leq 36$。

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
