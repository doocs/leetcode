# [533. 孤独像素 II](https://leetcode-cn.com/problems/lonely-pixel-ii)

[English Version](/solution/0500-0599/0533.Lonely%20Pixel%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一幅由黑色像素和白色像素组成的图像，&nbsp;与一个正整数N, 找到位于某行&nbsp;<strong>R</strong>&nbsp;和某列&nbsp;<strong>C</strong>&nbsp;中且符合下列规则的黑色像素的数量:</p>

<ol>
	<li>行R 和列C都恰好包括N个黑色像素。</li>
	<li>列C中所有黑色像素所在的行必须和行R完全相同。</li>
</ol>

<p>图像由一个由&lsquo;B&rsquo;和&lsquo;W&rsquo;组成二维字符数组表示, &lsquo;B&rsquo;和&lsquo;W&rsquo;分别代表黑色像素和白色像素。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>                                            
[[&#39;W&#39;, &#39;B&#39;, &#39;W&#39;, &#39;B&#39;, &#39;B&#39;, &#39;W&#39;],    
 [&#39;W&#39;, &#39;B&#39;, &#39;W&#39;, &#39;B&#39;, &#39;B&#39;, &#39;W&#39;],    
 [&#39;W&#39;, &#39;B&#39;, &#39;W&#39;, &#39;B&#39;, &#39;B&#39;, &#39;W&#39;],    
 [&#39;W&#39;, &#39;W&#39;, &#39;B&#39;, &#39;W&#39;, &#39;B&#39;, &#39;W&#39;]] 

N = 3
<strong>输出:</strong> 6
<strong>解析:</strong> 所有粗体的&#39;B&#39;都是我们所求的像素(第1列和第3列的所有&#39;B&#39;).
        0    1    2    3    4    5         列号                                          
0    [[&#39;W&#39;, <strong>&#39;B&#39;</strong>, &#39;W&#39;, <strong>&#39;B&#39;</strong>, &#39;B&#39;, &#39;W&#39;],    
1     [&#39;W&#39;, <strong>&#39;B&#39;</strong>, &#39;W&#39;, <strong>&#39;B&#39;</strong>, &#39;B&#39;, &#39;W&#39;],    
2     [&#39;W&#39;, <strong>&#39;B&#39;</strong>, &#39;W&#39;, <strong>&#39;B&#39;</strong>, &#39;B&#39;, &#39;W&#39;],    
3     [&#39;W&#39;, &#39;W&#39;, &#39;B&#39;, &#39;W&#39;, &#39;B&#39;, &#39;W&#39;]]    
行号

以R = 0行和C = 1列的&#39;B&#39;为例:
规则 1，R = 0行和C = 1列都恰好有N = 3个黑色像素. 
规则 2，在C = 1列的黑色像素分别位于0，1和2行。它们都和R = 0行完全相同。

</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ol>
	<li>输入二维数组行和列的范围是 [1,200]。</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (picture[i][j] == 'B')
                {
                    ++rows[i];
                    cols[j].push_back(i);
                }
            }
        }
        vector<vector<bool>> t(m, vector<bool>(m, false));
        for (int i = 0; i < m; ++i)
        {
            for (int k = i; k < m; ++k)
            {
                t[i][k] = i == k || all(picture[i], picture[k]);
                t[k][i] = t[i][k];
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i)
        {
            if (rows[i] == target)
            {
                for (int j = 0; j < n; ++j)
                {
                    if (cols[j].size() == target)
                    {
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
