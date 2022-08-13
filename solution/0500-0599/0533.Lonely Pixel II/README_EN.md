# [533. Lonely Pixel II](https://leetcode.com/problems/lonely-pixel-ii)

[中文文档](/solution/0500-0599/0533.Lonely%20Pixel%20II/README.md)

## Description

<p>Given an <code>m x n</code> <code>picture</code> consisting of black <code>&#39;B&#39;</code> and white <code>&#39;W&#39;</code> pixels and an integer target, return <em>the number of <b>black</b> lonely pixels</em>.</p>

<p>A black lonely pixel is a character <code>&#39;B&#39;</code> that located at a specific position <code>(r, c)</code> where:</p>

<ul>
	<li>Row <code>r</code> and column <code>c</code> both contain exactly <code>target</code> black pixels.</li>
	<li>For all rows that have a black pixel at column <code>c</code>, they should be exactly the same as row <code>r</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0533.Lonely%20Pixel%20II/images/pixel2-1-grid.jpg" style="width: 493px; height: 333px;" />
<pre>
<strong>Input:</strong> picture = [[&quot;W&quot;,&quot;B&quot;,&quot;W&quot;,&quot;B&quot;,&quot;B&quot;,&quot;W&quot;],[&quot;W&quot;,&quot;B&quot;,&quot;W&quot;,&quot;B&quot;,&quot;B&quot;,&quot;W&quot;],[&quot;W&quot;,&quot;B&quot;,&quot;W&quot;,&quot;B&quot;,&quot;B&quot;,&quot;W&quot;],[&quot;W&quot;,&quot;W&quot;,&quot;B&quot;,&quot;W&quot;,&quot;B&quot;,&quot;W&quot;]], target = 3
<strong>Output:</strong> 6
<strong>Explanation:</strong> All the green &#39;B&#39; are the black pixels we need (all &#39;B&#39;s at column 1 and 3).
Take &#39;B&#39; at row r = 0 and column c = 1 as an example:
 - Rule 1, row r = 0 and column c = 1 both have exactly target = 3 black pixels. 
 - Rule 2, the rows have black pixel at column c = 1 are row 0, row 1 and row 2. They are exactly the same as row r = 0.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0533.Lonely%20Pixel%20II/images/pixel2-2-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>Input:</strong> picture = [[&quot;W&quot;,&quot;W&quot;,&quot;B&quot;],[&quot;W&quot;,&quot;W&quot;,&quot;B&quot;],[&quot;W&quot;,&quot;W&quot;,&quot;B&quot;]], target = 1
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;picture.length</code></li>
	<li><code>n ==&nbsp;picture[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>picture[i][j]</code> is <code>&#39;W&#39;</code> or <code>&#39;B&#39;</code>.</li>
	<li><code>1 &lt;= target &lt;= min(m, n)</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
