---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1840.Maximum%20Building%20Height/README_EN.md
rating: 2374
source: Weekly Contest 238 Q4
tags:
    - Array
    - Math
    - Sorting
---

<!-- problem:start -->

# [1840. Maximum Building Height](https://leetcode.com/problems/maximum-building-height)

[中文文档](/solution/1800-1899/1840.Maximum%20Building%20Height/README.md)

## Description

<!-- description:start -->

<p>You want to build <code>n</code> new buildings in a city. The new buildings will be built in a line and are labeled from <code>1</code> to <code>n</code>.</p>

<p>However, there are city restrictions on the heights of the new buildings:</p>

<ul>
	<li>The height of each building must be a non-negative integer.</li>
	<li>The height of the first building <strong>must</strong> be <code>0</code>.</li>
	<li>The height difference between any two adjacent buildings <strong>cannot exceed</strong> <code>1</code>.</li>
</ul>

<p>Additionally, there are city restrictions on the maximum height of specific buildings. These restrictions are given as a 2D integer array <code>restrictions</code> where <code>restrictions[i] = [id<sub>i</sub>, maxHeight<sub>i</sub>]</code> indicates that building <code>id<sub>i</sub></code> must have a height <strong>less than or equal to</strong> <code>maxHeight<sub>i</sub></code>.</p>

<p>It is guaranteed that each building will appear <strong>at most once</strong> in <code>restrictions</code>, and building <code>1</code> will <strong>not</strong> be in <code>restrictions</code>.</p>

<p>Return <em>the <strong>maximum possible height</strong> of the <strong>tallest</strong> building</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1840.Maximum%20Building%20Height/images/ic236-q4-ex1-1.png" style="width: 400px; height: 253px;" />
<pre>
<strong>Input:</strong> n = 5, restrictions = [[2,1],[4,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,1,2], and the tallest building has a height of 2.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1840.Maximum%20Building%20Height/images/ic236-q4-ex2.png" style="width: 500px; height: 269px;" />
<pre>
<strong>Input:</strong> n = 6, restrictions = []
<strong>Output:</strong> 5
<strong>Explanation:</strong> The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,4,5], and the tallest building has a height of 5.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1840.Maximum%20Building%20Height/images/ic236-q4-ex3.png" style="width: 500px; height: 187px;" />
<pre>
<strong>Input:</strong> n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,3,4,4,5,4,3], and the tallest building has a height of 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= restrictions.length &lt;= min(n - 1, 10<sup>5</sup>)</code></li>
	<li><code>2 &lt;= id<sub>i</sub> &lt;= n</code></li>
	<li><code>id<sub>i</sub></code>&nbsp;is <strong>unique</strong>.</li>
	<li><code>0 &lt;= maxHeight<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Mathematics

First, we sort all the constraints by the building number in ascending order.

Then we traverse all the constraints from left to right. For each constraint, we can get an upper bound on the maximum height, i.e., $r_i[1] = \min(r_i[1], r_{i-1}[1] + r_i[0] - r_{i-1}[0])$, where $r_i$ represents the $i$-th constraint, and $r_i[0]$ and $r_i[1]$ represent the building number and the upper bound on the maximum height of the building, respectively.

Next, we traverse all the constraints from right to left. For each constraint, we can get an upper bound on the maximum height, i.e., $r_i[1] = \min(r_i[1], r_{i+1}[1] + r_{i+1}[0] - r_i[0])$.

In this way, we obtain the upper bound on the maximum height for each constrained building.

The problem asks for the height of the tallest building. We can enumerate the buildings between two adjacent constraints $i$ and $i+1$. To maximize the height, the height should first increase and then decrease. Suppose the maximum height is $t$, then $t - r_i[1] + t - r_{i+1}[1] \leq r_{i+1}[0] - r_i[0]$, i.e., $t \leq \frac{r_i[1] + r_{i+1}[1] + r_{i+1}[0] - r_{i}[0]}{2}$. We take the maximum value of all such $t$.

The time complexity is $O(m \times \log m)$, and the space complexity is $O(m)$. Here, $m$ is the number of constraints.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxBuilding(self, n: int, restrictions: List[List[int]]) -> int:
        r = restrictions
        r.append([1, 0])
        r.sort()
        if r[-1][0] != n:
            r.append([n, n - 1])
        m = len(r)
        for i in range(1, m):
            r[i][1] = min(r[i][1], r[i - 1][1] + r[i][0] - r[i - 1][0])
        for i in range(m - 2, 0, -1):
            r[i][1] = min(r[i][1], r[i + 1][1] + r[i + 1][0] - r[i][0])
        ans = 0
        for i in range(m - 1):
            t = (r[i][1] + r[i + 1][1] + r[i + 1][0] - r[i][0]) // 2
            ans = max(ans, t)
        return ans
```

#### Java

```java
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> r = new ArrayList<>();
        r.addAll(Arrays.asList(restrictions));
        r.add(new int[] {1, 0});
        Collections.sort(r, (a, b) -> a[0] - b[0]);
        if (r.get(r.size() - 1)[0] != n) {
            r.add(new int[] {n, n - 1});
        }
        int m = r.size();
        for (int i = 1; i < m; ++i) {
            int[] a = r.get(i - 1), b = r.get(i);
            b[1] = Math.min(b[1], a[1] + b[0] - a[0]);
        }
        for (int i = m - 2; i > 0; --i) {
            int[] a = r.get(i), b = r.get(i + 1);
            a[1] = Math.min(a[1], b[1] + b[0] - a[0]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int[] a = r.get(i), b = r.get(i + 1);
            int t = (a[1] + b[1] + b[0] - a[0]) / 2;
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxBuilding(int n, vector<vector<int>>& restrictions) {
        auto&& r = restrictions;
        r.push_back({1, 0});
        ranges::sort(r);
        if (r[r.size() - 1][0] != n) {
            r.push_back({n, n - 1});
        }
        int m = r.size();
        for (int i = 1; i < m; ++i) {
            r[i][1] = min(r[i][1], r[i - 1][1] + r[i][0] - r[i - 1][0]);
        }
        for (int i = m - 2; i > 0; --i) {
            r[i][1] = min(r[i][1], r[i + 1][1] + r[i + 1][0] - r[i][0]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int t = (r[i][1] + r[i + 1][1] + r[i + 1][0] - r[i][0]) / 2;
            ans = max(ans, t);
        }
        return ans;
    }
};
```

#### Go

```go
func maxBuilding(n int, restrictions [][]int) (ans int) {
	r := restrictions
	r = append(r, []int{1, 0})
	sort.Slice(r, func(i, j int) bool { return r[i][0] < r[j][0] })
	if r[len(r)-1][0] != n {
		r = append(r, []int{n, n - 1})
	}
	m := len(r)
	for i := 1; i < m; i++ {
		r[i][1] = min(r[i][1], r[i-1][1]+r[i][0]-r[i-1][0])
	}
	for i := m - 2; i > 0; i-- {
		r[i][1] = min(r[i][1], r[i+1][1]+r[i+1][0]-r[i][0])
	}
	for i := 0; i < m-1; i++ {
		t := (r[i][1] + r[i+1][1] + r[i+1][0] - r[i][0]) / 2
		ans = max(ans, t)
	}
	return ans
}
```

#### TypeScript

```ts
function maxBuilding(n: number, restrictions: number[][]): number {
    restrictions.push([1, 0]);
    restrictions.sort((a, b) => a[0] - b[0]);
    if (restrictions[restrictions.length - 1][0] !== n) {
        restrictions.push([n, n - 1]);
    }

    const m = restrictions.length;
    for (let i = 1; i < m; ++i) {
        restrictions[i][1] = Math.min(
            restrictions[i][1],
            restrictions[i - 1][1] + restrictions[i][0] - restrictions[i - 1][0],
        );
    }

    for (let i = m - 2; i >= 0; --i) {
        restrictions[i][1] = Math.min(
            restrictions[i][1],
            restrictions[i + 1][1] + restrictions[i + 1][0] - restrictions[i][0],
        );
    }

    let ans = 0;
    for (let i = 0; i < m - 1; ++i) {
        const t = Math.floor(
            (restrictions[i][1] +
                restrictions[i + 1][1] +
                restrictions[i + 1][0] -
                restrictions[i][0]) /
                2,
        );
        ans = Math.max(ans, t);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
