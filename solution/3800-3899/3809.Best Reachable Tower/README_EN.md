---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3809.Best%20Reachable%20Tower/README_EN.md
rating: 1358
source: Biweekly Contest 174 Q1
---

<!-- problem:start -->

# [3809. Best Reachable Tower](https://leetcode.com/problems/best-reachable-tower)

[中文文档](/solution/3800-3899/3809.Best%20Reachable%20Tower/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>towers</code>, where <code>towers[i] = [x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub>]</code> represents the coordinates <code>(x<sub>i</sub>, y<sub>i</sub>)</code> and quality factor <code>q<sub>i</sub></code> of the <code>i<sup>th</sup></code> tower.</p>

<p>You are also given an integer array <code>center = [cx, cy​​​​​​​]</code> representing your location, and an integer <code>radius</code>.</p>

<p>A tower is <strong>reachable</strong> if its <strong>Manhattan distance</strong> from <code>center</code> is <strong>less than or equal</strong> to <code>radius</code>.</p>

<p>Among all reachable towers:</p>

<ul>
	<li>Return the coordinates of the tower with the <strong>maximum</strong> quality factor.</li>
	<li>If there is a tie, return the tower with the <strong>lexicographically smallest</strong> coordinate. If no tower is reachable, return <code>[-1, -1]</code>.</li>
</ul>
The <strong>Manhattan Distance</strong> between two cells <code>(x<sub>i</sub>, y<sub>i</sub>)</code> and <code>(x<sub>j</sub>, y<sub>j</sub>)</code> is <code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>.

<p>A coordinate <code>[x<sub>i</sub>, y<sub>i</sub>]</code> is <strong>lexicographically smaller</strong> than <code>[x<sub>j</sub>, y<sub>j</sub>]</code> if <code>x<sub>i</sub> &lt; x<sub>j</sub></code>, or <code>x<sub>i</sub> == x<sub>j</sub></code> and <code>y<sub>i</sub> &lt; y<sub>j</sub></code>.</p>

<p><code>|x|</code> denotes the <strong>absolute</strong> <strong>value</strong> of <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">towers = [[1,2,5], [2,1,7], [3,1,9]], center = [1,1], radius = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Tower <code>[1, 2, 5]</code>: Manhattan distance = <code>|1 - 1| + |2 - 1| = 1</code>, reachable.</li>
	<li>Tower <code>[2, 1, 7]</code>: Manhattan distance = <code>|2 - 1| + |1 - 1| = 1</code>, reachable.</li>
	<li>Tower <code>[3, 1, 9]</code>: Manhattan distance = <code>|3 - 1| + |1 - 1| = 2</code>, reachable.</li>
</ul>

<p>All towers are reachable. The maximum quality factor is 9, which corresponds to tower <code>[3, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">towers = [[1,3,4], [2,2,4], [4,4,7]], center = [0,0], radius = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Tower <code>[1, 3, 4]</code>: Manhattan distance = <code>|1 - 0| + |3 - 0| = 4</code>, reachable.</li>
	<li>Tower <code>[2, 2, 4]</code>: Manhattan distance = <code>|2 - 0| + |2 - 0| = 4</code>, reachable.</li>
	<li>Tower <code>[4, 4, 7]</code>: Manhattan distance = <code>|4 - 0| + |4 - 0| = 8</code>, not reachable.</li>
</ul>

<p>Among the reachable towers, the maximum quality factor is 4. Both <code>[1, 3]</code> and <code>[2, 2]</code> have the same quality, so the lexicographically smaller coordinate is <code>[1, 3]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">towers = [[5,6,8], [0,3,5]], center = [1,2], radius = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Tower <code>[5, 6, 8]</code>: Manhattan distance = <code>|5 - 1| + |6 - 2| = 8</code>, not reachable.</li>
	<li>Tower <code>[0, 3, 5]</code>: Manhattan distance = <code>|0 - 1| + |3 - 2| = 2</code>, not reachable.</li>
</ul>

<p>No tower is reachable within the given radius, so <code>[-1, -1]</code> is returned.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= towers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>towers[i] = [x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub>]</code></li>
	<li><code>center = [cx, cy]</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub>, cx, cy &lt;= 10<sup>5</sup></code>​​​​​​​</li>
	<li><code>0 &lt;= radius &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: One-Pass Traversal

We define a variable $\textit{idx}$ to record the index of the current best tower, initially $\textit{idx} = -1$. Then, we traverse each tower and calculate the Manhattan distance $\textit{dist}$ between it and $\textit{center}$:

$$
\textit{dist} = |x_i - cx| + |y_i - cy|
$$

If $\textit{dist} > \textit{radius}$, the tower is unreachable, so we skip it. Otherwise, we compare the quality factor $q$ of the current tower with that of the best tower:

- If $\textit{idx} = -1$, it means no reachable tower has been found yet, so we update $\textit{idx}$ to the current tower's index.
- If the current tower's quality factor $q_i$ is greater than the best tower's quality factor $q_{\textit{idx}}$, we update $\textit{idx}$ to the current tower's index.
- If the current tower's quality factor $q_i$ is equal to the best tower's quality factor $q_{\textit{idx}}$, we compare the coordinates of the two towers and choose the one with the smaller lexicographical order.

After the traversal ends, if $\textit{idx} = -1$, it means there are no reachable towers, so we return $[-1, -1]$. Otherwise, we return the coordinates of the best tower.

The time complexity is $O(n)$, where $n$ is the number of towers. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bestTower(
        self, towers: List[List[int]], center: List[int], radius: int
    ) -> List[int]:
        cx, cy = center
        idx = -1
        for i, (x, y, q) in enumerate(towers):
            dist = abs(x - cx) + abs(y - cy)
            if dist > radius:
                continue
            if (
                idx == -1
                or towers[idx][2] < q
                or (towers[idx][2] == q and towers[i][:2] < towers[idx][:2])
            ):
                idx = i
        return [-1, -1] if idx == -1 else towers[idx][:2]
```

#### Java

```java
class Solution {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int cx = center[0], cy = center[1];
        int idx = -1;
        for (int i = 0; i < towers.length; i++) {
            int x = towers[i][0], y = towers[i][1], q = towers[i][2];
            int dist = Math.abs(x - cx) + Math.abs(y - cy);
            if (dist > radius) {
                continue;
            }
            if (idx == -1 || towers[idx][2] < q
                || (towers[idx][2] == q
                    && (x < towers[idx][0] || (x == towers[idx][0] && y < towers[idx][1])))) {
                idx = i;
            }
        }
        return idx == -1 ? new int[] {-1, -1} : new int[] {towers[idx][0], towers[idx][1]};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> bestTower(vector<vector<int>>& towers, vector<int>& center, int radius) {
        int cx = center[0], cy = center[1];
        int idx = -1;
        for (int i = 0; i < towers.size(); ++i) {
            int x = towers[i][0], y = towers[i][1], q = towers[i][2];
            int dist = abs(x - cx) + abs(y - cy);
            if (dist > radius) {
                continue;
            }
            if (
                idx == -1
                || towers[idx][2] < q
                || (towers[idx][2] == q && (x < towers[idx][0] || (x == towers[idx][0] && y < towers[idx][1])))) {
                idx = i;
            }
        }
        if (idx == -1) {
            return {-1, -1};
        }
        return {towers[idx][0], towers[idx][1]};
    }
};
```

#### Go

```go
func bestTower(towers [][]int, center []int, radius int) []int {
	cx, cy := center[0], center[1]
	idx := -1
	for i, a := range towers {
		x, y, q := a[0], a[1], a[2]
		dist := abs(x-cx) + abs(y-cy)
		if dist > radius {
			continue
		}
		if idx == -1 ||
			towers[idx][2] < q ||
			(towers[idx][2] == q &&
				(x < towers[idx][0] ||
					(x == towers[idx][0] && y < towers[idx][1]))) {
			idx = i
		}
	}
	if idx == -1 {
		return []int{-1, -1}
	}
	return []int{towers[idx][0], towers[idx][1]}
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
function bestTower(towers: number[][], center: number[], radius: number): number[] {
    const [cx, cy] = center;
    let idx = -1;
    for (let i = 0; i < towers.length; i++) {
        const [x, y, q] = towers[i];
        const dist = Math.abs(x - cx) + Math.abs(y - cy);
        if (dist > radius) {
            continue;
        }
        if (
            idx === -1 ||
            towers[idx][2] < q ||
            (towers[idx][2] === q &&
                (x < towers[idx][0] || (x === towers[idx][0] && y < towers[idx][1])))
        ) {
            idx = i;
        }
    }
    return idx === -1 ? [-1, -1] : [towers[idx][0], towers[idx][1]];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
