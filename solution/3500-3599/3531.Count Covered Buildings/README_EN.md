---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3531.Count%20Covered%20Buildings/README_EN.md
tags:
    - Array
    - Hash Table
    - Sorting
---

<!-- problem:start -->

# [3531. Count Covered Buildings](https://leetcode.com/problems/count-covered-buildings)

[中文文档](/solution/3500-3599/3531.Count%20Covered%20Buildings/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code>, representing an <code>n x n</code> city. You are also given a 2D grid <code>buildings</code>, where <code>buildings[i] = [x, y]</code> denotes a <strong>unique</strong> building located at coordinates <code>[x, y]</code>.</p>

<p>A building is <strong>covered</strong> if there is at least one building in all <strong>four</strong> directions: left, right, above, and below.</p>

<p>Return the number of <strong>covered</strong> buildings.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3531.Count%20Covered%20Buildings/images/telegram-cloud-photo-size-5-6212982906394101085-m.jpg" style="width: 200px; height: 204px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Only building <code>[2,2]</code> is covered as it has at least one building:

    <ul>
    	<li>above (<code>[1,2]</code>)</li>
    	<li>below (<code>[3,2]</code>)</li>
    	<li>left (<code>[2,1]</code>)</li>
    	<li>right (<code>[2,3]</code>)</li>
    </ul>
    </li>
    <li>Thus, the count of covered buildings is 1.</li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3531.Count%20Covered%20Buildings/images/telegram-cloud-photo-size-5-6212982906394101086-m.jpg" style="width: 200px; height: 204px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>No building has at least one building in all four directions.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3531.Count%20Covered%20Buildings/images/telegram-cloud-photo-size-5-6248862251436067566-x.jpg" style="width: 202px; height: 205px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Only building <code>[3,3]</code> is covered as it has at least one building:

    <ul>
    	<li>above (<code>[1,3]</code>)</li>
    	<li>below (<code>[5,3]</code>)</li>
    	<li>left (<code>[3,2]</code>)</li>
    	<li>right (<code>[3,5]</code>)</li>
    </ul>
    </li>
    <li>Thus, the count of covered buildings is 1.</li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= buildings.length &lt;= 10<sup>5</sup> </code></li>
	<li><code>buildings[i] = [x, y]</code></li>
	<li><code>1 &lt;= x, y &lt;= n</code></li>
	<li>All coordinates of <code>buildings</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Sorting

We can group the buildings by their x-coordinates and y-coordinates, storing them in hash tables $\text{g1}$ and $\text{g2}$, respectively. Here, $\text{g1[x]}$ represents all y-coordinates for buildings with x-coordinate $x$, and $\text{g2[y]}$ represents all x-coordinates for buildings with y-coordinate $y$. Then, we sort these lists.

Next, we iterate through all buildings. For the current building $(x, y)$, we retrieve the corresponding y-coordinate list $l_1$ from $\text{g1}$ and the x-coordinate list $l_2$ from $\text{g2}$. We check the conditions to determine whether the building is covered. A building is covered if $l_2[0] < x < l_2[-1]$ and $l_1[0] < y < l_1[-1]$. If so, we increment the answer by one.

After finishing the iteration, we return the final answer.

The complexity is $O(n \times \log n)$, and the space complexity is $O(n)$, where $n$ is the number of buildings.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countCoveredBuildings(self, n: int, buildings: List[List[int]]) -> int:
        g1 = defaultdict(list)
        g2 = defaultdict(list)
        for x, y in buildings:
            g1[x].append(y)
            g2[y].append(x)
        for x in g1:
            g1[x].sort()
        for y in g2:
            g2[y].sort()
        ans = 0
        for x, y in buildings:
            l1 = g1[x]
            l2 = g2[y]
            if l2[0] < x < l2[-1] and l1[0] < y < l1[-1]:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, List<Integer>> g1 = new HashMap<>();
        Map<Integer, List<Integer>> g2 = new HashMap<>();

        for (int[] building : buildings) {
            int x = building[0], y = building[1];
            g1.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            g2.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        for (var e : g1.entrySet()) {
            Collections.sort(e.getValue());
        }
        for (var e : g2.entrySet()) {
            Collections.sort(e.getValue());
        }

        int ans = 0;

        for (int[] building : buildings) {
            int x = building[0], y = building[1];
            List<Integer> l1 = g1.get(x);
            List<Integer> l2 = g2.get(y);

            if (l2.get(0) < x && x < l2.get(l2.size() - 1) && l1.get(0) < y
                && y < l1.get(l1.size() - 1)) {
                ans++;
            }
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countCoveredBuildings(int n, vector<vector<int>>& buildings) {
        unordered_map<int, vector<int>> g1;
        unordered_map<int, vector<int>> g2;

        for (const auto& building : buildings) {
            int x = building[0], y = building[1];
            g1[x].push_back(y);
            g2[y].push_back(x);
        }

        for (auto& e : g1) {
            sort(e.second.begin(), e.second.end());
        }
        for (auto& e : g2) {
            sort(e.second.begin(), e.second.end());
        }

        int ans = 0;

        for (const auto& building : buildings) {
            int x = building[0], y = building[1];
            const vector<int>& l1 = g1[x];
            const vector<int>& l2 = g2[y];

            if (l2[0] < x && x < l2[l2.size() - 1] && l1[0] < y && y < l1[l1.size() - 1]) {
                ans++;
            }
        }

        return ans;
    }
};
```

#### Go

```go
func countCoveredBuildings(n int, buildings [][]int) (ans int) {
	g1 := make(map[int][]int)
	g2 := make(map[int][]int)

	for _, building := range buildings {
		x, y := building[0], building[1]
		g1[x] = append(g1[x], y)
		g2[y] = append(g2[y], x)
	}

	for _, list := range g1 {
		sort.Ints(list)
	}
	for _, list := range g2 {
		sort.Ints(list)
	}

	for _, building := range buildings {
		x, y := building[0], building[1]
		l1 := g1[x]
		l2 := g2[y]

		if l2[0] < x && x < l2[len(l2)-1] && l1[0] < y && y < l1[len(l1)-1] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countCoveredBuildings(n: number, buildings: number[][]): number {
    const g1: Map<number, number[]> = new Map();
    const g2: Map<number, number[]> = new Map();

    for (const [x, y] of buildings) {
        if (!g1.has(x)) g1.set(x, []);
        g1.get(x)?.push(y);

        if (!g2.has(y)) g2.set(y, []);
        g2.get(y)?.push(x);
    }

    for (const list of g1.values()) {
        list.sort((a, b) => a - b);
    }
    for (const list of g2.values()) {
        list.sort((a, b) => a - b);
    }

    let ans = 0;

    for (const [x, y] of buildings) {
        const l1 = g1.get(x)!;
        const l2 = g2.get(y)!;

        if (l2[0] < x && x < l2[l2.length - 1] && l1[0] < y && y < l1[l1.length - 1]) {
            ans++;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
