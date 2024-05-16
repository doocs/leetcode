---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2647.Color%20the%20Triangle%20Red/README_EN.md
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [2647. Color the Triangle Red 🔒](https://leetcode.com/problems/color-the-triangle-red)

[中文文档](/solution/2600-2699/2647.Color%20the%20Triangle%20Red/README.md)

## Description

<p>You are given an integer <code>n</code>. Consider an equilateral triangle of side length <code>n</code>, broken up into <code>n<sup>2</sup></code> unit equilateral triangles. The triangle has <code>n</code> <strong>1-indexed</strong> rows where the <code>i<sup>th</sup></code> row has <code>2i - 1</code> unit equilateral triangles.</p>

<p>The triangles in the <code>i<sup>th</sup></code> row are also <strong>1-indexed</strong> with coordinates from <code>(i, 1)</code> to <code>(i, 2i - 1)</code>. The following image shows a triangle of side length <code>4</code> with the indexing of its triangle.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2647.Color%20the%20Triangle%20Red/images/triangle4.jpg" style="width: 402px; height: 242px;" />
<p>Two triangles are <strong>neighbors</strong> if they <strong>share a side</strong>. For example:</p>

<ul>
	<li>Triangles <code>(1,1)</code> and <code>(2,2)</code> are neighbors</li>
	<li>Triangles <code>(3,2)</code> and <code>(3,3)</code> are neighbors.</li>
	<li>Triangles <code>(2,2)</code> and <code>(3,3)</code> are not neighbors because they do not share any side.</li>
</ul>

<p>Initially, all the unit triangles are <strong>white</strong>. You want to choose <code>k</code> triangles and color them <strong>red</strong>. We will then run the following algorithm:</p>

<ol>
	<li>Choose a white triangle that has <strong>at least two</strong> red neighbors.

    <ul>
    	<li>If there is no such triangle, stop the algorithm.</li>
    </ul>
    </li>
    <li>Color that triangle <strong>red</strong>.</li>
    <li>Go to step 1.</li>

</ol>

<p>Choose the minimum <code>k</code> possible and set <code>k</code> triangles red before running this algorithm such that after the algorithm stops, all unit triangles are colored red.</p>

<p>Return <em>a 2D list of the coordinates of the triangles that you will color red initially</em>. The answer has to be of the smallest size possible. If there are multiple valid solutions, return any.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2647.Color%20the%20Triangle%20Red/images/example1.jpg" style="width: 500px; height: 263px;" />
<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> [[1,1],[2,1],[2,3],[3,1],[3,5]]
<strong>Explanation:</strong> Initially, we choose the shown 5 triangles to be red. Then, we run the algorithm:
- Choose (2,2) that has three red neighbors and color it red.
- Choose (3,2) that has two red neighbors and color it red.
- Choose (3,4) that has three red neighbors and color it red.
- Choose (3,3) that has three red neighbors and color it red.
It can be shown that choosing any 4 triangles and running the algorithm will not make all triangles red.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2647.Color%20the%20Triangle%20Red/images/example2.jpg" style="width: 300px; height: 101px;" />
<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> [[1,1],[2,1],[2,3]]
<strong>Explanation:</strong> Initially, we choose the shown 3 triangles to be red. Then, we run the algorithm:
- Choose (2,2) that has three red neighbors and color it red.
It can be shown that choosing any 2 triangles and running the algorithm will not make all triangles red.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Find the Pattern

We draw a graph to observe, and we can find that the first row only has one triangle and must be colored, and from the last row to the second row, the coloring scheme of every four rows is the same:

1. The last row is colored at $(n, 1)$, $(n, 3)$, ..., $(n, 2n - 1)$.
1. The $n - 1$ row is colored at $(n - 1, 2)$.
1. The $n - 2$ row is colored at $(n - 2, 3)$, $(n - 2, 5)$, ..., $(n - 2, 2n - 5)$.
1. The $n - 3$ row is colored at $(n - 3, 1)$.

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2647.Color%20the%20Triangle%20Red/images/demo3.png" style="width: 50%">

Therefore, we can color the first row according to the above rules, and then start from the last row, and color every four rows once until the second row ends.

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2647.Color%20the%20Triangle%20Red/images/demo2.png" style="width: 80%">

The time complexity is $(n^2)$, where $n$ is the parameter given in the problem. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def colorRed(self, n: int) -> List[List[int]]:
        ans = [[1, 1]]
        k = 0
        for i in range(n, 1, -1):
            if k == 0:
                for j in range(1, i << 1, 2):
                    ans.append([i, j])
            elif k == 1:
                ans.append([i, 2])
            elif k == 2:
                for j in range(3, i << 1, 2):
                    ans.append([i, j])
            else:
                ans.append([i, 1])
            k = (k + 1) % 4
        return ans
```

```java
class Solution {
    public int[][] colorRed(int n) {
        List<int[]> ans = new ArrayList<>();
        ans.add(new int[] {1, 1});
        for (int i = n, k = 0; i > 1; --i, k = (k + 1) % 4) {
            if (k == 0) {
                for (int j = 1; j < i << 1; j += 2) {
                    ans.add(new int[] {i, j});
                }
            } else if (k == 1) {
                ans.add(new int[] {i, 2});
            } else if (k == 2) {
                for (int j = 3; j < i << 1; j += 2) {
                    ans.add(new int[] {i, j});
                }
            } else {
                ans.add(new int[] {i, 1});
            }
        }
        return ans.toArray(new int[0][]);
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> colorRed(int n) {
        vector<vector<int>> ans;
        ans.push_back({1, 1});
        for (int i = n, k = 0; i > 1; --i, k = (k + 1) % 4) {
            if (k == 0) {
                for (int j = 1; j < i << 1; j += 2) {
                    ans.push_back({i, j});
                }
            } else if (k == 1) {
                ans.push_back({i, 2});
            } else if (k == 2) {
                for (int j = 3; j < i << 1; j += 2) {
                    ans.push_back({i, j});
                }
            } else {
                ans.push_back({i, 1});
            }
        }
        return ans;
    }
};
```

```go
func colorRed(n int) (ans [][]int) {
	ans = append(ans, []int{1, 1})
	for i, k := n, 0; i > 1; i, k = i-1, (k+1)%4 {
		if k == 0 {
			for j := 1; j < i<<1; j += 2 {
				ans = append(ans, []int{i, j})
			}
		} else if k == 1 {
			ans = append(ans, []int{i, 2})
		} else if k == 2 {
			for j := 3; j < i<<1; j += 2 {
				ans = append(ans, []int{i, j})
			}
		} else {
			ans = append(ans, []int{i, 1})
		}
	}
	return
}
```

```ts
function colorRed(n: number): number[][] {
    const ans: number[][] = [[1, 1]];
    for (let i = n, k = 0; i > 1; --i, k = (k + 1) % 4) {
        if (k === 0) {
            for (let j = 1; j < i << 1; j += 2) {
                ans.push([i, j]);
            }
        } else if (k === 1) {
            ans.push([i, 2]);
        } else if (k === 2) {
            for (let j = 3; j < i << 1; j += 2) {
                ans.push([i, j]);
            }
        } else {
            ans.push([i, 1]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
