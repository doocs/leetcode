# [533. Lonely Pixel II](https://leetcode.com/problems/lonely-pixel-ii)

[中文文档](/solution/0500-0599/0533.Lonely%20Pixel%20II/README.md)

## Description

<p>Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels located at some specific row <b>R</b> and column <b>C</b> that align with all the following rules:</p>

<ol>

<li> Row R and column C both contain exactly N black pixels.</li>

<li> For all rows that have a black pixel at column C, they should be exactly the same as row R</li>

</ol>

<p>The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively. </p>

<p><b>Example:</b><br />

<pre>

<b>Input:</b>                                            

[['W', 'B', 'W', 'B', 'B', 'W'],    

 ['W', 'B', 'W', 'B', 'B', 'W'],    

 ['W', 'B', 'W', 'B', 'B', 'W'],    

 ['W', 'W', 'B', 'W', 'B', 'W']] 



N = 3

<b>Output:</b> 6

<b>Explanation:</b> All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).

        0    1    2    3    4    5         column index                                            

0    [['W', <b>'B'</b>, 'W', <b>'B'</b>, 'B', 'W'],    

1     ['W', <b>'B'</b>, 'W', <b>'B'</b>, 'B', 'W'],    

2     ['W', <b>'B'</b>, 'W', <b>'B'</b>, 'B', 'W'],    

3     ['W', 'W', 'B', 'W', 'B', 'W']]    

row index



Take 'B' at row R = 0 and column C = 1 as an example:

Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 

Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.



</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>The range of width and height of the input 2D array is [1,200].</li>

</ol>

</p>

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
