---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3218.Minimum%20Cost%20for%20Cutting%20Cake%20I/README_EN.md
rating: 1654
source: Weekly Contest 406 Q3
tags:
    - Greedy
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [3218. Minimum Cost for Cutting Cake I](https://leetcode.com/problems/minimum-cost-for-cutting-cake-i)

[中文文档](/solution/3200-3299/3218.Minimum%20Cost%20for%20Cutting%20Cake%20I/README.md)

## Description

<!-- description:start -->

<p>There is an <code>m x n</code> cake that needs to be cut into <code>1 x 1</code> pieces.</p>

<p>You are given integers <code>m</code>, <code>n</code>, and two arrays:</p>

<ul>
	<li><code>horizontalCut</code> of size <code>m - 1</code>, where <code>horizontalCut[i]</code> represents the cost to cut along the horizontal line <code>i</code>.</li>
	<li><code>verticalCut</code> of size <code>n - 1</code>, where <code>verticalCut[j]</code> represents the cost to cut along the vertical line <code>j</code>.</li>
</ul>

<p>In one operation, you can choose any piece of cake that is not yet a <code>1 x 1</code> square and perform one of the following cuts:</p>

<ol>
	<li>Cut along a horizontal line <code>i</code> at a cost of <code>horizontalCut[i]</code>.</li>
	<li>Cut along a vertical line <code>j</code> at a cost of <code>verticalCut[j]</code>.</li>
</ol>

<p>After the cut, the piece of cake is divided into two distinct pieces.</p>

<p>The cost of a cut depends only on the initial cost of the line and does not change.</p>

<p>Return the <strong>minimum</strong> total cost to cut the entire cake into <code>1 x 1</code> pieces.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 3, n = 2, horizontalCut = [1,3], verticalCut = [5]</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3218.Minimum%20Cost%20for%20Cutting%20Cake%20I/images/ezgifcom-animated-gif-maker-1.gif" style="width: 280px; height: 320px;" /></p>

<ul>
	<li>Perform a cut on the vertical line 0 with cost 5, current total cost is 5.</li>
	<li>Perform a cut on the horizontal line 0 on <code>3 x 1</code> subgrid with cost 1.</li>
	<li>Perform a cut on the horizontal line 0 on <code>3 x 1</code> subgrid with cost 1.</li>
	<li>Perform a cut on the horizontal line 1 on <code>2 x 1</code> subgrid with cost 3.</li>
	<li>Perform a cut on the horizontal line 1 on <code>2 x 1</code> subgrid with cost 3.</li>
</ul>

<p>The total cost is <code>5 + 1 + 1 + 3 + 3 = 13</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 2, horizontalCut = [7], verticalCut = [4]</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Perform a cut on the horizontal line 0 with cost 7.</li>
	<li>Perform a cut on the vertical line 0 on <code>1 x 2</code> subgrid with cost 4.</li>
	<li>Perform a cut on the vertical line 0 on <code>1 x 2</code> subgrid with cost 4.</li>
</ul>

<p>The total cost is <code>7 + 4 + 4 = 15</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 20</code></li>
	<li><code>horizontalCut.length == m - 1</code></li>
	<li><code>verticalCut.length == n - 1</code></li>
	<li><code>1 &lt;= horizontalCut[i], verticalCut[i] &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Two Pointers

For a given position, the earlier you cut, the fewer cuts are needed, so it is clear that positions with higher costs should be cut earlier.

Therefore, we can sort the arrays $\textit{horizontalCut}$ and $\textit{verticalCut}$ in descending order, and then use two pointers $i$ and $j$ to point to the costs in $\textit{horizontalCut}$ and $\textit{verticalCut}$, respectively. Each time, we choose the position with the larger cost to cut, while updating the corresponding number of rows and columns.

Each time a horizontal cut is made, if the number of columns before the cut was $v$, then the cost of this cut is $\textit{horizontalCut}[i] \times v$, and then the number of rows $h$ is incremented by one; similarly, each time a vertical cut is made, if the number of rows before the cut was $h$, then the cost of this cut is $\textit{verticalCut}[j] \times h$, and then the number of columns $v$ is incremented by one.

Finally, when both $i$ and $j$ reach the end, return the total cost.

The time complexity is $O(m \times \log m + n \times \log n)$, and the space complexity is $O(\log m + \log n)$. Here, $m$ and $n$ are the lengths of $\textit{horizontalCut}$ and $\textit{verticalCut}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCost(
        self, m: int, n: int, horizontalCut: List[int], verticalCut: List[int]
    ) -> int:
        horizontalCut.sort(reverse=True)
        verticalCut.sort(reverse=True)
        ans = i = j = 0
        h = v = 1
        while i < m - 1 or j < n - 1:
            if j == n - 1 or (i < m - 1 and horizontalCut[i] > verticalCut[j]):
                ans += horizontalCut[i] * v
                h, i = h + 1, i + 1
            else:
                ans += verticalCut[j] * h
                v, j = v + 1, j + 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        int ans = 0;
        int i = m - 2, j = n - 2;
        int h = 1, v = 1;
        while (i >= 0 || j >= 0) {
            if (j < 0 || (i >= 0 && horizontalCut[i] > verticalCut[j])) {
                ans += horizontalCut[i--] * v;
                ++h;
            } else {
                ans += verticalCut[j--] * h;
                ++v;
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
    int minimumCost(int m, int n, vector<int>& horizontalCut, vector<int>& verticalCut) {
        sort(horizontalCut.rbegin(), horizontalCut.rend());
        sort(verticalCut.rbegin(), verticalCut.rend());
        int ans = 0;
        int i = 0, j = 0;
        int h = 1, v = 1;
        while (i < m - 1 || j < n - 1) {
            if (j == n - 1 || (i < m - 1 && horizontalCut[i] > verticalCut[j])) {
                ans += horizontalCut[i++] * v;
                h++;
            } else {
                ans += verticalCut[j++] * h;
                v++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumCost(m int, n int, horizontalCut []int, verticalCut []int) (ans int) {
	sort.Sort(sort.Reverse(sort.IntSlice(horizontalCut)))
	sort.Sort(sort.Reverse(sort.IntSlice(verticalCut)))
	i, j := 0, 0
	h, v := 1, 1
	for i < m-1 || j < n-1 {
		if j == n-1 || (i < m-1 && horizontalCut[i] > verticalCut[j]) {
			ans += horizontalCut[i] * v
			h++
			i++
		} else {
			ans += verticalCut[j] * h
			v++
			j++
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumCost(m: number, n: number, horizontalCut: number[], verticalCut: number[]): number {
    horizontalCut.sort((a, b) => b - a);
    verticalCut.sort((a, b) => b - a);
    let ans = 0;
    let [i, j] = [0, 0];
    let [h, v] = [1, 1];
    while (i < m - 1 || j < n - 1) {
        if (j === n - 1 || (i < m - 1 && horizontalCut[i] > verticalCut[j])) {
            ans += horizontalCut[i] * v;
            h++;
            i++;
        } else {
            ans += verticalCut[j] * h;
            v++;
            j++;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_cost(m: i32, n: i32, mut horizontal_cut: Vec<i32>, mut vertical_cut: Vec<i32>) -> i32 {
        horizontal_cut.sort();
        vertical_cut.sort();
        let (mut i, mut j) = ((m - 2) as isize, (n - 2) as isize);
        let (mut h, mut v) = (1, 1);
        let mut ans = 0;

        while i >= 0 || j >= 0 {
            if j < 0 || (i >= 0 && horizontal_cut[i as usize] > vertical_cut[j as usize]) {
                ans += horizontal_cut[i as usize] * v;
                i -= 1;
                h += 1;
            } else {
                ans += vertical_cut[j as usize] * h;
                j -= 1;
                v += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
