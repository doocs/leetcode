---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0573.Squirrel%20Simulation/README_EN.md
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [573. Squirrel Simulation 🔒](https://leetcode.com/problems/squirrel-simulation)

[中文文档](/solution/0500-0599/0573.Squirrel%20Simulation/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>height</code> and <code>width</code> representing a garden of size <code>height x width</code>. You are also given:</p>

<ul>
	<li>an array <code>tree</code> where <code>tree = [tree<sub>r</sub>, tree<sub>c</sub>]</code> is the position of the tree in the garden,</li>
	<li>an array <code>squirrel</code> where <code>squirrel = [squirrel<sub>r</sub>, squirrel<sub>c</sub>]</code> is the position of the squirrel in the garden,</li>
	<li>and an array <code>nuts</code> where <code>nuts[i] = [nut<sub>i<sub>r</sub></sub>, nut<sub>i<sub>c</sub></sub>]</code> is the position of the <code>i<sup>th</sup></code> nut in the garden.</li>
</ul>

<p>The squirrel can only take at most one nut at one time and can move in four directions: up, down, left, and right, to the adjacent cell.</p>

<p>Return <em>the <strong>minimal distance</strong> for the squirrel to collect all the nuts and put them under the tree one by one</em>.</p>

<p>The <strong>distance</strong> is the number of moves.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0573.Squirrel%20Simulation/images/squirrel1-grid.jpg" style="width: 573px; height: 413px;" />
<pre>
<strong>Input:</strong> height = 5, width = 7, tree = [2,2], squirrel = [4,4], nuts = [[3,0], [2,5]]
<strong>Output:</strong> 12
<strong>Explanation:</strong> The squirrel should go to the nut at [2, 5] first to achieve a minimal distance.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0573.Squirrel%20Simulation/images/squirrel2-grid.jpg" style="width: 253px; height: 93px;" />
<pre>
<strong>Input:</strong> height = 1, width = 3, tree = [0,1], squirrel = [0,0], nuts = [[0,2]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= height, width &lt;= 100</code></li>
	<li><code>tree.length == 2</code></li>
	<li><code>squirrel.length == 2</code></li>
	<li><code>1 &lt;= nuts.length &lt;= 5000</code></li>
	<li><code>nuts[i].length == 2</code></li>
	<li><code>0 &lt;= tree<sub>r</sub>, squirrel<sub>r</sub>, nut<sub>i<sub>r</sub></sub> &lt;= height</code></li>
	<li><code>0 &lt;= tree<sub>c</sub>, squirrel<sub>c</sub>, nut<sub>i<sub>c</sub></sub> &lt;= width</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

Observing the squirrel's movement path, we can see that the squirrel will first move to the position of a nut, then move to the position of the tree. After that, the total movement path of the squirrel is equal to "the sum of the distances from the remaining nuts to the tree" multiplied by $2$.

Therefore, we only need to select a nut as the squirrel's first target, such that the sum of its distance to the tree is minimized, to obtain the shortest path.

The time complexity is $O(n)$, where $n$ is the number of nuts. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDistance(
        self,
        height: int,
        width: int,
        tree: List[int],
        squirrel: List[int],
        nuts: List[List[int]],
    ) -> int:
        tr, tc = tree
        sr, sc = squirrel
        s = sum(abs(r - tr) + abs(c - tc) for r, c in nuts) * 2
        ans = inf
        for r, c in nuts:
            a = abs(r - tr) + abs(c - tc)
            b = abs(r - sr) + abs(c - sc)
            ans = min(ans, s - a + b)
        return ans
```

#### Java

```java
import static java.lang.Math.*;

class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int tr = tree[0], tc = tree[1];
        int sr = squirrel[0], sc = squirrel[1];
        int s = 0;
        for (var e : nuts) {
            s += abs(e[0] - tr) + abs(e[1] - tc);
        }
        s <<= 1;
        int ans = Integer.MAX_VALUE;
        for (var e : nuts) {
            int a = abs(e[0] - tr) + abs(e[1] - tc);
            int b = abs(e[0] - sr) + abs(e[1] - sc);
            ans = min(ans, s - a + b);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDistance(int height, int width, vector<int>& tree, vector<int>& squirrel, vector<vector<int>>& nuts) {
        int tr = tree[0], tc = tree[1];
        int sr = squirrel[0], sc = squirrel[1];
        int s = 0;
        for (const auto& e : nuts) {
            s += abs(e[0] - tr) + abs(e[1] - tc);
        }
        s <<= 1;
        int ans = INT_MAX;
        for (const auto& e : nuts) {
            int a = abs(e[0] - tr) + abs(e[1] - tc);
            int b = abs(e[0] - sr) + abs(e[1] - sc);
            ans = min(ans, s - a + b);
        }
        return ans;
    }
};
```

#### Go

```go
func minDistance(height int, width int, tree []int, squirrel []int, nuts [][]int) int {
	tr, tc := tree[0], tree[1]
	sr, sc := squirrel[0], squirrel[1]
	s := 0
	for _, e := range nuts {
		s += abs(e[0]-tr) + abs(e[1]-tc)
	}
	s <<= 1
	ans := math.MaxInt32
	for _, e := range nuts {
		a := abs(e[0]-tr) + abs(e[1]-tc)
		b := abs(e[0]-sr) + abs(e[1]-sc)
		ans = min(ans, s-a+b)
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minDistance(
    height: number,
    width: number,
    tree: number[],
    squirrel: number[],
    nuts: number[][],
): number {
    const [tr, tc] = tree;
    const [sr, sc] = squirrel;
    const s = nuts.reduce((acc, [r, c]) => acc + (Math.abs(tr - r) + Math.abs(tc - c)) * 2, 0);
    let ans = Infinity;
    for (const [r, c] of nuts) {
        const a = Math.abs(tr - r) + Math.abs(tc - c);
        const b = Math.abs(sr - r) + Math.abs(sc - c);
        ans = Math.min(ans, s - a + b);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_distance(
        height: i32,
        width: i32,
        tree: Vec<i32>,
        squirrel: Vec<i32>,
        nuts: Vec<Vec<i32>>,
    ) -> i32 {
        let (tr, tc) = (tree[0], tree[1]);
        let (sr, sc) = (squirrel[0], squirrel[1]);
        let s: i32 = nuts
            .iter()
            .map(|nut| (nut[0] - tr).abs() + (nut[1] - tc).abs())
            .sum::<i32>()
            * 2;

        let mut ans = i32::MAX;
        for nut in &nuts {
            let a = (nut[0] - tr).abs() + (nut[1] - tc).abs();
            let b = (nut[0] - sr).abs() + (nut[1] - sc).abs();
            ans = ans.min(s - a + b);
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int MinDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int tr = tree[0], tc = tree[1];
        int sr = squirrel[0], sc = squirrel[1];
        int s = 0;

        foreach (var e in nuts) {
            s += Math.Abs(e[0] - tr) + Math.Abs(e[1] - tc);
        }
        s <<= 1;

        int ans = int.MaxValue;
        foreach (var e in nuts) {
            int a = Math.Abs(e[0] - tr) + Math.Abs(e[1] - tc);
            int b = Math.Abs(e[0] - sr) + Math.Abs(e[1] - sc);
            ans = Math.Min(ans, s - a + b);
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
