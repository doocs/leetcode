# [1337. The K Weakest Rows in a Matrix](https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix)

[中文文档](/solution/1300-1399/1337.The%20K%20Weakest%20Rows%20in%20a%20Matrix/README.md)

## Description

<p>You are given an <code>m x n</code> binary matrix <code>mat</code> of <code>1</code>&#39;s (representing soldiers) and <code>0</code>&#39;s (representing civilians). The soldiers are positioned <strong>in front</strong> of the civilians. That is, all the <code>1</code>&#39;s will appear to the <strong>left</strong> of all the <code>0</code>&#39;s in each row.</p>

<p>A row <code>i</code> is <strong>weaker</strong> than a row <code>j</code> if one of the following is true:</p>

<ul>
	<li>The number of soldiers in row <code>i</code> is less than the number of soldiers in row <code>j</code>.</li>
	<li>Both rows have the same number of soldiers and <code>i &lt; j</code>.</li>
</ul>

<p>Return <em>the indices of the </em><code>k</code><em> <strong>weakest</strong> rows in the matrix ordered from weakest to strongest</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> mat = 
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]], 
k = 3
<strong>Output:</strong> [2,0,3]
<strong>Explanation:</strong> 
The number of soldiers in each row is: 
- Row 0: 2 
- Row 1: 4 
- Row 2: 1 
- Row 3: 2 
- Row 4: 5 
The rows ordered from weakest to strongest are [2,0,3,1,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = 
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]], 
k = 2
<strong>Output:</strong> [0,2]
<strong>Explanation:</strong> 
The number of soldiers in each row is: 
- Row 0: 1 
- Row 1: 4 
- Row 2: 1 
- Row 3: 1 
The rows ordered from weakest to strongest are [0,2,3,1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>2 &lt;= n, m &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= m</code></li>
	<li><code>matrix[i][j]</code> is either 0 or 1.</li>
</ul>

## Solutions

Binary search & sort.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kWeakestRows(self, mat: List[List[int]], k: int) -> List[int]:
        m, n = len(mat), len(mat[0])
        ans = [n - bisect_right(row[::-1], 0) for row in mat]
        idx = list(range(m))
        idx.sort(key=lambda i: ans[i])
        return idx[:k]
```

### **Java**

```java
class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m];
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            idx.add(i);
            int[] row = mat[i];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (row[mid] == 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            res[i] = left;
        }
        idx.sort(Comparator.comparingInt(a -> res[a]));
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = idx.get(i);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function kWeakestRows(mat: number[][], k: number): number[] {
    let n = mat.length;
    let sumMap = mat.map((d, i) => [d.reduce((a, c) => a + c, 0), i]);
    let ans = [];
    // 冒泡排序
    for (let i = 0; i < k; i++) {
        for (let j = i; j < n; j++) {
            if (
                sumMap[j][0] < sumMap[i][0] ||
                (sumMap[j][0] == sumMap[i][0] && sumMap[i][1] > sumMap[j][1])
            ) {
                [sumMap[i], sumMap[j]] = [sumMap[j], sumMap[i]];
            }
        }
        ans.push(sumMap[i][1]);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int search(vector<int>& m) {
        int l = 0;
        int h = m.size() - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (m[mid] == 0)
                h = mid - 1;
            else
                l = mid + 1;
        }
        return l;
    }

    vector<int> kWeakestRows(vector<vector<int>>& mat, int k) {
        vector<pair<int, int>> p;
        vector<int> res;
        for (int i = 0; i < mat.size(); i++) {
            int count = search(mat[i]);
            p.push_back({count, i});
        }
        sort(p.begin(), p.end());
        for (int i = 0; i < k; i++) {
            res.push_back(p[i].second);
        }
        return res;
    }
};
```

### **Go**

```go
func kWeakestRows(mat [][]int, k int) []int {
	m, n := len(mat), len(mat[0])
	res := make([]int, m)
	var idx []int
	for i, row := range mat {
		idx = append(idx, i)
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if row[mid] == 0 {
				right = mid
			} else {
				left = mid + 1
			}
		}
		res[i] = left
	}
	sort.Slice(idx, func(i, j int) bool {
		return res[idx[i]] < res[idx[j]] || (res[idx[i]] == res[idx[j]] && idx[i] < idx[j])
	})
	return idx[:k]
}
```

### **...**

```

```

<!-- tabs:end -->
