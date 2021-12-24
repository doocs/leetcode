# [311. 稀疏矩阵的乘法](https://leetcode-cn.com/problems/sparse-matrix-multiplication)

[English Version](/solution/0300-0399/0311.Sparse%20Matrix%20Multiplication/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个&nbsp;<a href="https://baike.baidu.com/item/%E7%A8%80%E7%96%8F%E7%9F%A9%E9%98%B5" target="_blank">稀疏矩阵</a>&nbsp;<strong>A</strong>&nbsp;和&nbsp;<strong>B</strong>，请你返回&nbsp;<strong>AB</strong> 的结果。你可以默认&nbsp;<strong>A&nbsp;</strong>的列数等于&nbsp;<strong>B&nbsp;</strong>的行数。</p>

<p>请仔细阅读下面的示例。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：

A</strong> = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

<strong>B</strong> = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

<strong>输出：</strong>

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
<strong>AB</strong> = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

直接模拟。

```python
class Solution:
    def multiply(self, mat1: List[List[int]], mat2: List[List[int]]) -> List[List[int]]:
        r1, c1, c2 = len(mat1), len(mat1[0]), len(mat2[0])
        res = [[0] * c2 for _ in range(r1)]
        for i in range(r1):
            for j in range(c2):
                for k in range(c1):
                    res[i][j] += mat1[i][k] * mat2[k][j]
        return res
```

用哈希表记录稀疏矩阵 mat1 中的非 0 值。

```python
class Solution:
    def multiply(self, mat1: List[List[int]], mat2: List[List[int]]) -> List[List[int]]:
        r1, c1, c2 = len(mat1), len(mat1[0]), len(mat2[0])
        res = [[0] * c2 for _ in range(r1)]
        mp = defaultdict(list)
        for i in range(r1):
            for j in range(c1):
                if mat1[i][j] != 0:
                    mp[i].append(j)
        for i in range(r1):
            for j in range(c2):
                for k in mp[i]:
                    res[i][j] += mat1[i][k] * mat2[k][j]
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int r1 = mat1.length, c1 = mat1[0].length, c2 = mat2[0].length;
        int[][] res = new int[r1][c2];
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < r1; ++i) {
            for (int j = 0; j < c1; ++j) {
                if (mat1[i][j] != 0) {
                    mp.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        for (int i = 0; i < r1; ++i) {
            for (int j = 0; j < c2; ++j) {
                if (mp.containsKey(i)) {
                    for (int k : mp.get(i)) {
                        res[i][j] += mat1[i][k] * mat2[k][j];
                    }
                }
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> multiply(vector<vector<int>>& mat1, vector<vector<int>>& mat2) {
        int r1 = mat1.size(), c1 = mat1[0].size(), c2 = mat2[0].size();
        vector<vector<int>> res(r1, vector<int>(c2));
        unordered_map<int, vector<int>> mp;
        for (int i = 0; i < r1; ++i)
        {
            for (int j = 0; j < c1; ++j)
            {
                if (mat1[i][j] != 0) mp[i].push_back(j);
            }
        }
        for (int i = 0; i < r1; ++i)
        {
            for (int j = 0; j < c2; ++j)
            {
                for (int k : mp[i]) res[i][j] += mat1[i][k] * mat2[k][j];
            }
        }
        return res;
    }
};
```

### **Go**

```go
func multiply(mat1 [][]int, mat2 [][]int) [][]int {
	r1, c1, c2 := len(mat1), len(mat1[0]), len(mat2[0])
	res := make([][]int, r1)
	for i := range res {
		res[i] = make([]int, c2)
	}
	mp := make(map[int][]int)
	for i := 0; i < r1; i++ {
		for j := 0; j < c1; j++ {
			if mat1[i][j] != 0 {
				mp[i] = append(mp[i], j)
			}
		}
	}
	for i := 0; i < r1; i++ {
		for j := 0; j < c2; j++ {
			for _, k := range mp[i] {
				res[i][j] += mat1[i][k] * mat2[k][j]
			}
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
