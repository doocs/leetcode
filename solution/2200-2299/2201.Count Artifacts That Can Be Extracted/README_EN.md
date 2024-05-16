---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/README_EN.md
rating: 1525
source: Weekly Contest 284 Q2
tags:
    - Array
    - Hash Table
    - Simulation
---

# [2201. Count Artifacts That Can Be Extracted](https://leetcode.com/problems/count-artifacts-that-can-be-extracted)

[中文文档](/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/README.md)

## Description

<p>There is an <code>n x n</code> <strong>0-indexed</strong> grid with some artifacts buried in it. You are given the integer <code>n</code> and a <strong>0-indexed </strong>2D integer array <code>artifacts</code> describing the positions of the rectangular artifacts where <code>artifacts[i] = [r1<sub>i</sub>, c1<sub>i</sub>, r2<sub>i</sub>, c2<sub>i</sub>]</code> denotes that the <code>i<sup>th</sup></code> artifact is buried in the subgrid where:</p>

<ul>
	<li><code>(r1<sub>i</sub>, c1<sub>i</sub>)</code> is the coordinate of the <strong>top-left</strong> cell of the <code>i<sup>th</sup></code> artifact and</li>
	<li><code>(r2<sub>i</sub>, c2<sub>i</sub>)</code> is the coordinate of the <strong>bottom-right</strong> cell of the <code>i<sup>th</sup></code> artifact.</li>
</ul>

<p>You will excavate some cells of the grid and remove all the mud from them. If the cell has a part of an artifact buried underneath, it will be uncovered. If all the parts of an artifact are uncovered, you can extract it.</p>

<p>Given a <strong>0-indexed</strong> 2D integer array <code>dig</code> where <code>dig[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> indicates that you will excavate the cell <code>(r<sub>i</sub>, c<sub>i</sub>)</code>, return <em>the number of artifacts that you can extract</em>.</p>

<p>The test cases are generated such that:</p>

<ul>
	<li>No two artifacts overlap.</li>
	<li>Each artifact only covers at most <code>4</code> cells.</li>
	<li>The entries of <code>dig</code> are unique.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/images/untitled-diagram.jpg" style="width: 216px; height: 216px;" />
<pre>
<strong>Input:</strong> n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
The different colors represent different artifacts. Excavated cells are labeled with a &#39;D&#39; in the grid.
There is 1 artifact that can be extracted, namely the red artifact.
The blue artifact has one part in cell (1,1) which remains uncovered, so we cannot extract it.
Thus, we return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2201.Count%20Artifacts%20That%20Can%20Be%20Extracted/images/untitled-diagram-1.jpg" style="width: 216px; height: 216px;" />
<pre>
<strong>Input:</strong> n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1],[1,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Both the red and blue artifacts have all parts uncovered (labeled with a &#39;D&#39;) and can be extracted, so we return 2. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= artifacts.length, dig.length &lt;= min(n<sup>2</sup>, 10<sup>5</sup>)</code></li>
	<li><code>artifacts[i].length == 4</code></li>
	<li><code>dig[i].length == 2</code></li>
	<li><code>0 &lt;= r1<sub>i</sub>, c1<sub>i</sub>, r2<sub>i</sub>, c2<sub>i</sub>, r<sub>i</sub>, c<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>r1<sub>i</sub> &lt;= r2<sub>i</sub></code></li>
	<li><code>c1<sub>i</sub> &lt;= c2<sub>i</sub></code></li>
	<li>No two artifacts will overlap.</li>
	<li>The number of cells covered by an artifact is <strong>at most</strong> <code>4</code>.</li>
	<li>The entries of <code>dig</code> are unique.</li>
</ul>

## Solutions

### Solution 1: Hash Table

We can use a hash table $s$ to record all the excavated cells, then traverse all the workpieces, and check whether all parts of the workpiece are in the hash table. If so, we can extract the workpiece, and the answer is increased by one.

The time complexity is $O(m + k)$, and the space complexity is $O(k)$. Here, $m$ is the number of workpieces, and $k$ is the number of excavated cells.

<!-- tabs:start -->

```python
class Solution:
    def digArtifacts(
        self, n: int, artifacts: List[List[int]], dig: List[List[int]]
    ) -> int:
        def check(a: List[int]) -> bool:
            x1, y1, x2, y2 = a
            return all(
                (x, y) in s for x in range(x1, x2 + 1) for y in range(y1, y2 + 1)
            )

        s = {(i, j) for i, j in dig}
        return sum(check(a) for a in artifacts)
```

```java
class Solution {
    private Set<Integer> s = new HashSet<>();
    private int n;

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        this.n = n;
        for (var p : dig) {
            s.add(p[0] * n + p[1]);
        }
        int ans = 0;
        for (var a : artifacts) {
            ans += check(a);
        }
        return ans;
    }

    private int check(int[] a) {
        int x1 = a[0], y1 = a[1], x2 = a[2], y2 = a[3];
        for (int x = x1; x <= x2; ++x) {
            for (int y = y1; y <= y2; ++y) {
                if (!s.contains(x * n + y)) {
                    return 0;
                }
            }
        }
        return 1;
    }
}
```

```cpp
class Solution {
public:
    int digArtifacts(int n, vector<vector<int>>& artifacts, vector<vector<int>>& dig) {
        unordered_set<int> s;
        for (auto& p : dig) {
            s.insert(p[0] * n + p[1]);
        }
        auto check = [&](vector<int>& a) {
            int x1 = a[0], y1 = a[1], x2 = a[2], y2 = a[3];
            for (int x = x1; x <= x2; ++x) {
                for (int y = y1; y <= y2; ++y) {
                    if (!s.count(x * n + y)) {
                        return 0;
                    }
                }
            }
            return 1;
        };
        int ans = 0;
        for (auto& a : artifacts) {
            ans += check(a);
        }
        return ans;
    }
};
```

```go
func digArtifacts(n int, artifacts [][]int, dig [][]int) (ans int) {
	s := map[int]bool{}
	for _, p := range dig {
		s[p[0]*n+p[1]] = true
	}
	check := func(a []int) int {
		x1, y1, x2, y2 := a[0], a[1], a[2], a[3]
		for x := x1; x <= x2; x++ {
			for y := y1; y <= y2; y++ {
				if !s[x*n+y] {
					return 0
				}
			}
		}
		return 1
	}
	for _, a := range artifacts {
		ans += check(a)
	}
	return
}
```

```ts
function digArtifacts(n: number, artifacts: number[][], dig: number[][]): number {
    const s: Set<number> = new Set();
    for (const [x, y] of dig) {
        s.add(x * n + y);
    }
    let ans = 0;
    const check = (a: number[]): number => {
        const [x1, y1, x2, y2] = a;
        for (let x = x1; x <= x2; ++x) {
            for (let y = y1; y <= y2; ++y) {
                if (!s.has(x * n + y)) {
                    return 0;
                }
            }
        }
        return 1;
    };
    for (const a of artifacts) {
        ans += check(a);
    }
    return ans;
}
```

```rust
use std::collections::HashSet;

impl Solution {
    pub fn dig_artifacts(n: i32, artifacts: Vec<Vec<i32>>, dig: Vec<Vec<i32>>) -> i32 {
        let mut s: HashSet<i32> = HashSet::new();
        for p in dig {
            s.insert(p[0] * n + p[1]);
        }
        let check = |a: &[i32]| -> i32 {
            let x1 = a[0];
            let y1 = a[1];
            let x2 = a[2];
            let y2 = a[3];
            for x in x1..=x2 {
                for y in y1..=y2 {
                    if !s.contains(&(x * n + y)) {
                        return 0;
                    }
                }
            }
            1
        };
        let mut ans = 0;
        for a in artifacts {
            ans += check(&a);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
