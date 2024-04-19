# [533. 孤独像素 II 🔒](https://leetcode.cn/problems/lonely-pixel-ii)

[English Version](/solution/0500-0599/0533.Lonely%20Pixel%20II/README_EN.md)

<!-- tags:数组,哈希表,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的二维字符数组 <code>picture</code> ，表示一张黑白图像，数组中的 <code>'B'</code> 表示黑色像素，<code>'W'</code> 表示白色像素。另给你一个整数 <code>target</code> ，请你找出并返回符合规则的 <strong>黑色</strong> 孤独像素的数量。</p>

<p>黑色孤独像素是指位于某一特定位置 <code>(r, c)</code> 的字符 <code>'B'</code> ，其中：</p>

<ul>
	<li>行 <code>r</code> 和列 <code>c</code> 中的黑色像素恰好有 <code>target</code> 个。</li>
	<li>列 <code>c</code> 中所有黑色像素所在的行必须和行 <code>r</code> 完全相同。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0533.Lonely%20Pixel%20II/images/1694957797-UWXAxl-image.png" style="width: 493px; height: 333px;" />
<pre>
<strong>输入：</strong>picture = [["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","W","B","W","B","W"]], target = 3
<strong>输出：</strong>6
<strong>解释：</strong>所有绿色的 'B' 都是我们所求的像素(第 1 列和第 3 列的所有 'B' )
以行 r = 0 和列 c = 1 的 'B' 为例：
- 规则 1 ，行 r = 0 和列 c = 1 都恰好有 target = 3 个黑色像素 
- 规则 2 ，列 c = 1 的黑色像素分别位于行 0，行 1 和行 2。和行 r = 0 完全相同。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0533.Lonely%20Pixel%20II/images/1694957806-FyCCMF-image.png" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>picture = [["W","W","B"],["W","W","B"],["W","W","B"]], target = 1
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m ==&nbsp;picture.length</code></li>
	<li><code>n ==&nbsp;picture[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>picture[i][j]</code> 为 <code>'W'</code> 或 <code>'B'</code></li>
	<li><code>1 &lt;= target &lt;= min(m, n)</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def findBlackPixel(self, picture: List[List[str]], target: int) -> int:
        m, n = len(picture), len(picture[0])
        rows = [0] * m
        cols = defaultdict(list)
        for i in range(m):
            for j in range(n):
                if picture[i][j] == 'B':
                    rows[i] += 1
                    cols[j].append(i)
        t = [[False] * m for _ in range(m)]
        for i in range(m):
            for k in range(i, m):
                if i == k:
                    t[i][k] = True
                else:
                    t[i][k] = all([picture[i][j] == picture[k][j] for j in range(n)])
                t[k][i] = t[i][k]
        res = 0
        for i in range(m):
            if rows[i] == target:
                for j in range(n):
                    if len(cols[j]) == target and all([t[i][k] for k in cols[j]]):
                        res += 1
        return res
```

```java
class Solution {
    public int findBlackPixel(char[][] picture, int target) {
        int m = picture.length, n = picture[0].length;
        int[] rows = new int[m];
        Map<Integer, List<Integer>> cols = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    cols.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
                }
            }
        }
        boolean[][] t = new boolean[m][m];
        for (int i = 0; i < m; ++i) {
            for (int k = i; k < m; ++k) {
                t[i][k] = i == k || all(picture[i], picture[k]);
                t[k][i] = t[i][k];
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            if (rows[i] == target) {
                for (int j = 0; j < n; ++j) {
                    List<Integer> col = cols.get(j);
                    if (col != null && col.size() == target) {
                        boolean check = true;
                        for (int k : col) {
                            check = check && t[i][k];
                        }
                        if (check) {
                            ++res;
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean all(char[] row1, char[] row2) {
        int n = row1.length;
        for (int j = 0; j < n; ++j) {
            if (row1[j] != row2[j]) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    int findBlackPixel(vector<vector<char>>& picture, int target) {
        int m = picture.size(), n = picture[0].size();
        vector<int> rows(m);
        unordered_map<int, vector<int>> cols;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    cols[j].push_back(i);
                }
            }
        }
        vector<vector<bool>> t(m, vector<bool>(m, false));
        for (int i = 0; i < m; ++i) {
            for (int k = i; k < m; ++k) {
                t[i][k] = i == k || all(picture[i], picture[k]);
                t[k][i] = t[i][k];
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            if (rows[i] == target) {
                for (int j = 0; j < n; ++j) {
                    if (cols[j].size() == target) {
                        bool check = true;
                        for (int k : cols[j]) check = check && t[i][k];
                        if (check) ++res;
                    }
                }
            }
        }
        return res;
    }

    bool all(vector<char>& row1, vector<char>& row2) {
        int n = row1.size();
        for (int j = 0; j < n; ++j)
            if (row1[j] != row2[j]) return false;
        return true;
    }
};
```

```go
func findBlackPixel(picture [][]byte, target int) int {
	m, n := len(picture), len(picture[0])
	rows := make([]int, m)
	cols := make(map[int][]int)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if picture[i][j] == 'B' {
				rows[i]++
				cols[j] = append(cols[j], i)
			}
		}
	}
	t := make([][]bool, m)
	for i := 0; i < m; i++ {
		t[i] = make([]bool, m)
	}
	for i := 0; i < m; i++ {
		for k := i; k < m; k++ {
			if i == k {
				t[i][k] = true
			} else {
				t[i][k] = all(picture[i], picture[k])
			}
			t[k][i] = t[i][k]
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		if rows[i] == target {
			for j := 0; j < n; j++ {
				col, ok := cols[j]
				if ok && len(col) == target {
					check := true
					for _, k := range col {
						check = check && t[i][k]
					}
					if check {
						res++
					}
				}
			}
		}
	}
	return res
}

func all(row1, row2 []byte) bool {
	n := len(row1)
	for i := 0; i < n; i++ {
		if row1[i] != row2[i] {
			return false
		}
	}
	return true
}
```

<!-- tabs:end -->

<!-- end -->
