# [1901. 寻找峰值 II](https://leetcode.cn/problems/find-a-peak-element-ii)

[English Version](/solution/1900-1999/1901.Find%20a%20Peak%20Element%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个 2D 网格中的 <strong>峰值</strong><strong> </strong>是指那些 <strong>严格大于 </strong>其相邻格子(上、下、左、右)的元素。</p>

<p>给你一个<strong> 从 0 开始编号 </strong>的 <code>m x n</code> 矩阵 <code>mat</code> ，其中任意两个相邻格子的值都<strong> 不相同</strong> 。找出 <strong>任意一个 峰值</strong> <code>mat[i][j]</code> 并 <strong>返回其位置 </strong><code>[i,j]</code> 。</p>

<p>你可以假设整个矩阵周边环绕着一圈值为 <code>-1</code> 的格子。</p>

<p>要求必须写出时间复杂度为 <code>O(m log(n))</code> 或 <code>O(n log(m))</code> 的算法</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1901.Find%20a%20Peak%20Element%20II/images/1.png" style="width: 206px; height: 209px;" /></p>

<pre>
<strong>输入:</strong> mat = [[1,4],[3,2]]
<strong>输出:</strong> [0,1]
<strong>解释:</strong>&nbsp;3 和 4 都是峰值，所以[1,0]和[0,1]都是可接受的答案。
</pre>

<p><strong>示例 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1901.Find%20a%20Peak%20Element%20II/images/3.png" style="width: 254px; height: 257px;" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[10,20,15],[21,30,14],[7,16,32]]
<strong>输出:</strong> [1,1]
<strong>解释:</strong>&nbsp;30 和 32 都是峰值，所以[1,1]和[2,2]都是可接受的答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 10<sup>5</sup></code></li>
	<li>任意两个相邻元素均不相等.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

记 $m$ 和 $n$ 分别为矩阵的行数和列数。

题目要求我们寻找峰值，并且时间复杂度为 $O(m \times \log n)$ 或 $O(n \times \log m)$，那么我们可以考虑使用二分查找。

我们考虑第 $i$ 行的最大值，不妨将其下标记为 $j$。

如果 $mat[i][j] \gt mat[i + 1][j]$，那么第 $[0,..i]$ 行中必然存在一个峰值，我们只需要在第 $[0,..i]$ 行中找到最大值即可。同理，如果 $mat[i][j] \lt mat[i + 1][j]$，那么第 $[i + 1,..m - 1]$ 行中必然存在一个峰值，我们只需要在第 $[i + 1,..m - 1]$ 行中找到最大值即可。

为什么上述做法是对的？我们不妨用反证法来证明。

如果 $mat[i][j] \gt mat[i + 1][j]$，假设第 $[0,..i]$ 行中不存在峰值，那么 $mat[i][j]$ 不是峰值，而由于 $mat[i][j]$ 是第 $i$ 行的最大值，并且 $mat[i][j] \gt mat[i + 1][j]$，那么 $mat[i][j] \lt mat[i - 1][j]$。我们继续从第 $i - 1$ 行往上考虑，每一行的最大值都小于上一行的最大值。那么当遍历到 $i = 0$ 时，由于矩阵中的元素都是正整数，并且矩阵周边一圈的格子的值都为 $-1$。因此，在第 $0$ 行时，其最大值大于其所有相邻元素，那么第 $0$ 行的最大值就是峰值，与假设矛盾。因此，第 $[0,..i]$ 行中必然存在一个峰值。

对于 $mat[i][j] \lt mat[i + 1][j]$ 的情况，我们可以用类似的方法证明第 $[i + 1,..m - 1]$ 行中必然存在一个峰值。

因此，我们可以使用二分查找来寻找峰值。

我们二分查找矩阵的行，初始时查找的边界为 $l = 0$, $r = m - 1$。每一次，我们找到当前的中间行 $mid$，并找到该行的最大值下标 $j$。如果 $mat[mid][j] \gt mat[mid + 1][j]$，那么我们就在第 $[0,..mid]$ 行中寻找峰值，即更新 $r = mid$。否则，我们就在第 $[mid + 1,..m - 1]$ 行中寻找峰值，即更新 $l = mid + 1$。当 $l = r$ 时，我们就找到了峰值所在的位置 $[l, j_l]$。其中 $j_l$ 是第 $l$ 行的最大值下标。

时间复杂度 $O(n \times \log m)$，其中 $m$ 和 $n$ 分别为矩阵的行数和列数。二分查找的时间复杂度为 $O(\log m)$，每次二分查找时，我们需要遍历第 $mid$ 行的所有元素，时间复杂度为 $O(n)$。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findPeakGrid(self, mat: List[List[int]]) -> List[int]:
        l, r = 0, len(mat) - 1
        while l < r:
            mid = (l + r) >> 1
            j = mat[mid].index(max(mat[mid]))
            if mat[mid][j] > mat[mid + 1][j]:
                r = mid
            else:
                l = mid + 1
        return [l, mat[l].index(max(mat[l]))]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int l = 0, r = mat.length - 1;
        int n = mat[0].length;
        while (l < r) {
            int mid = (l + r) >> 1;
            int j = maxPos(mat[mid]);
            if (mat[mid][j] > mat[mid + 1][j]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return new int[] {l, maxPos(mat[l])};
    }

    private int maxPos(int[] arr) {
        int j = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[j] < arr[i]) {
                j = i;
            }
        }
        return j;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findPeakGrid(vector<vector<int>>& mat) {
        int l = 0, r = mat.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            int j = distance(mat[mid].begin(), max_element(mat[mid].begin(), mat[mid].end()));
            if (mat[mid][j] > mat[mid + 1][j]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int j = distance(mat[l].begin(), max_element(mat[l].begin(), mat[l].end()));
        return {l, j};
    }
};
```

### **Go**

```go
func findPeakGrid(mat [][]int) []int {
	maxPos := func(arr []int) int {
		j := 0
		for i := 1; i < len(arr); i++ {
			if arr[i] > arr[j] {
				j = i
			}
		}
		return j
	}
	l, r := 0, len(mat)-1
	for l < r {
		mid := (l + r) >> 1
		j := maxPos(mat[mid])
		if mat[mid][j] > mat[mid+1][j] {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return []int{l, maxPos(mat[l])}
}
```

### **TypeScript**

```ts
function findPeakGrid(mat: number[][]): number[] {
    let [l, r] = [0, mat.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        const j = mat[mid].indexOf(Math.max(...mat[mid]));
        if (mat[mid][j] > mat[mid + 1][j]) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return [l, mat[l].indexOf(Math.max(...mat[l]))];
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_peak_grid(mat: Vec<Vec<i32>>) -> Vec<i32> {
        let mut l: usize = 0;
        let mut r: usize = mat.len() - 1;
        while l < r {
            let mid: usize = (l + r) >> 1;
            let j: usize = mat[mid]
                .iter()
                .position(|&x| x == *mat[mid].iter().max().unwrap())
                .unwrap();
            if mat[mid][j] > mat[mid + 1][j] {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        let j: usize = mat[l]
            .iter()
            .position(|&x| x == *mat[l].iter().max().unwrap())
            .unwrap();
        vec![l as i32, j as i32]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
