---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0533.Lonely%20Pixel%20II/README_EN.md
tags:
    - Array
    - Hash Table
    - Matrix
---

<!-- problem:start -->

# [533. Lonely Pixel II 🔒](https://leetcode.com/problems/lonely-pixel-ii)

[中文文档](/solution/0500-0599/0533.Lonely%20Pixel%20II/README.md)

## Description

<!-- description:start -->

<p>Given an <code>m x n</code> <code>picture</code> consisting of black <code>&#39;B&#39;</code> and white <code>&#39;W&#39;</code> pixels and an integer target, return <em>the number of <b>black</b> lonely pixels</em>.</p>

<p>A black lonely pixel is a character <code>&#39;B&#39;</code> that located at a specific position <code>(r, c)</code> where:</p>

<ul>
	<li>Row <code>r</code> and column <code>c</code> both contain exactly <code>target</code> black pixels.</li>
	<li>For all rows that have a black pixel at column <code>c</code>, they should be exactly the same as row <code>r</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0533.Lonely%20Pixel%20II/images/pixel2-1-grid.jpg" style="width: 493px; height: 333px;" />
<pre>
<strong>Input:</strong> picture = [[&quot;W&quot;,&quot;B&quot;,&quot;W&quot;,&quot;B&quot;,&quot;B&quot;,&quot;W&quot;],[&quot;W&quot;,&quot;B&quot;,&quot;W&quot;,&quot;B&quot;,&quot;B&quot;,&quot;W&quot;],[&quot;W&quot;,&quot;B&quot;,&quot;W&quot;,&quot;B&quot;,&quot;B&quot;,&quot;W&quot;],[&quot;W&quot;,&quot;W&quot;,&quot;B&quot;,&quot;W&quot;,&quot;B&quot;,&quot;W&quot;]], target = 3
<strong>Output:</strong> 6
<strong>Explanation:</strong> All the green &#39;B&#39; are the black pixels we need (all &#39;B&#39;s at column 1 and 3).
Take &#39;B&#39; at row r = 0 and column c = 1 as an example:
 - Rule 1, row r = 0 and column c = 1 both have exactly target = 3 black pixels. 
 - Rule 2, the rows have black pixel at column c = 1 are row 0, row 1 and row 2. They are exactly the same as row r = 0.
</pre>

<p><strong class="example">Example 2:</strong></p>
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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

The second condition in the problem is equivalent to requiring that for each column containing black pixels, these rows are exactly the same.

Therefore, we can use an adjacency list $g$ to store all the rows containing black pixels in each column, i.e., $g[j]$ represents the set of all rows containing black pixels in the $j$-th column. In addition, we use an array $rows$ to store the number of black pixels in each row.

Next, we traverse each column. For each column, we find the first row $i_1$ containing black pixels. If the number of black pixels in this row is not equal to $target$, then this column cannot contain lonely pixels, and we skip it directly. Otherwise, we check whether all the rows containing black pixels in this column are exactly the same as the $i_1$-th row. If so, all the black pixels in this column are lonely pixels, and we add $target$ to the answer.

After the traversal, we return the answer.

The time complexity is $O(m \times n^2)$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns in the matrix respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findBlackPixel(self, picture: List[List[str]], target: int) -> int:
        rows = [0] * len(picture)
        g = defaultdict(list)
        for i, row in enumerate(picture):
            for j, x in enumerate(row):
                if x == "B":
                    rows[i] += 1
                    g[j].append(i)
        ans = 0
        for j in g:
            i1 = g[j][0]
            if rows[i1] != target:
                continue
            if len(g[j]) == rows[i1] and all(picture[i2] == picture[i1] for i2 in g[j]):
                ans += target
        return ans
```

#### Java

```java
class Solution {
    public int findBlackPixel(char[][] picture, int target) {
        int m = picture.length;
        int n = picture[0].length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int[] rows = new int[m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    g[j].add(i);
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            if (g[j].isEmpty() || (rows[g[j].get(0)] != target)) {
                continue;
            }
            int i1 = g[j].get(0);
            int ok = 0;
            if (g[j].size() == rows[i1]) {
                ok = target;
                for (int i2 : g[j]) {
                    if (!Arrays.equals(picture[i1], picture[i2])) {
                        ok = 0;
                        break;
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findBlackPixel(vector<vector<char>>& picture, int target) {
        int m = picture.size();
        int n = picture[0].size();
        vector<int> g[n];
        vector<int> rows(m);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    ++rows[i];
                    g[j].push_back(i);
                }
            }
        }

        int ans = 0;
        for (int j = 0; j < n; ++j) {
            if (g[j].empty() || (rows[g[j][0]] != target)) {
                continue;
            }
            int i1 = g[j][0];
            int ok = 0;
            if (g[j].size() == rows[i1]) {
                ok = target;
                for (int i2 : g[j]) {
                    if (picture[i1] != picture[i2]) {
                        ok = 0;
                        break;
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
};
```

#### Go

```go
func findBlackPixel(picture [][]byte, target int) (ans int) {
	m := len(picture)
	n := len(picture[0])
	g := make([][]int, n)
	rows := make([]int, m)
	for i, row := range picture {
		for j, x := range row {
			if x == 'B' {
				rows[i]++
				g[j] = append(g[j], i)
			}
		}
	}
	for j := 0; j < n; j++ {
		if len(g[j]) == 0 || rows[g[j][0]] != target {
			continue
		}
		i1 := g[j][0]
		ok := 0
		if len(g[j]) == rows[i1] {
			ok = target
			for _, i2 := range g[j] {
				if !bytes.Equal(picture[i1], picture[i2]) {
					ok = 0
					break
				}
			}
		}
		ans += ok
	}
	return
}
```

#### TypeScript

```ts
function findBlackPixel(picture: string[][], target: number): number {
    const m: number = picture.length;
    const n: number = picture[0].length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const rows: number[] = Array(m).fill(0);

    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (picture[i][j] === 'B') {
                ++rows[i];
                g[j].push(i);
            }
        }
    }

    let ans: number = 0;
    for (let j = 0; j < n; ++j) {
        if (g[j].length === 0 || rows[g[j][0]] !== target) {
            continue;
        }
        const i1: number = g[j][0];
        let ok: number = 0;
        if (g[j].length === rows[i1]) {
            ok = target;
            for (const i2 of g[j]) {
                if (picture[i1].join('') !== picture[i2].join('')) {
                    ok = 0;
                    break;
                }
            }
        }
        ans += ok;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
