---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3027.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20II/README_EN.md
rating: 2020
source: Biweekly Contest 123 Q4
tags:
    - Geometry
    - Array
    - Math
    - Enumeration
    - Sorting
---

<!-- problem:start -->

# [3027. Find the Number of Ways to Place People II](https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii)

[中文文档](/solution/3000-3099/3027.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D array <code>points</code> of size <code>n x 2</code> representing integer coordinates of some points on a 2D-plane, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>.</p>

<p>We define the <strong>right</strong> direction as positive x-axis (<strong>increasing x-coordinate</strong>) and the <strong>left</strong> direction as negative x-axis (<strong>decreasing x-coordinate</strong>). Similarly, we define the <strong>up</strong> direction as positive y-axis (<strong>increasing y-coordinate</strong>) and the <strong>down</strong> direction as negative y-axis (<strong>decreasing y-coordinate</strong>)</p>

<p>You have to place <code>n</code> people, including Alice and Bob, at these points such that there is <strong>exactly one</strong> person at every point. Alice wants to be alone with Bob, so Alice will build a rectangular fence with Alice&#39;s position as the <strong>upper left corner</strong> and Bob&#39;s position as the <strong>lower right corner</strong> of the fence (<strong>Note</strong> that the fence <strong>might not</strong> enclose any area, i.e. it can be a line). If any person other than Alice and Bob is either <strong>inside</strong> the fence or <strong>on</strong> the fence, Alice will be sad.</p>

<p>Return <em>the number of <strong>pairs of points</strong> where you can place Alice and Bob, such that Alice <strong>does not</strong> become sad on building the fence</em>.</p>

<p><strong>Note</strong> that Alice can only build a fence with Alice&#39;s position as the upper left corner, and Bob&#39;s position as the lower right corner. For example, Alice cannot build either of the fences in the picture below with four corners <code>(1, 1)</code>, <code>(1, 3)</code>, <code>(3, 1)</code>, and <code>(3, 3)</code>, because:</p>

<ul>
	<li>With Alice at <code>(3, 3)</code> and Bob at <code>(1, 1)</code>, Alice&#39;s position is not the upper left corner and Bob&#39;s position is not the lower right corner of the fence.</li>
	<li>With Alice at <code>(1, 3)</code> and Bob at <code>(1, 1)</code>, Bob&#39;s position is not the lower right corner of the fence.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3027.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20II/images/example0alicebob-1.png" style="width: 750px; height: 308px;padding: 10px; background: #fff; border-radius: .5rem;" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3027.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20II/images/example1alicebob.png" style="width: 376px; height: 308px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<pre>
<strong>Input:</strong> points = [[1,1],[2,2],[3,3]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no way to place Alice and Bob such that Alice can build a fence with Alice&#39;s position as the upper left corner and Bob&#39;s position as the lower right corner. Hence we return 0. 
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3027.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20II/images/example2alicebob.png" style="width: 1321px; height: 363px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<pre>
<strong>Input:</strong> points = [[6,2],[4,4],[2,6]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two ways to place Alice and Bob such that Alice will not be sad:
- Place Alice at (4, 4) and Bob at (6, 2).
- Place Alice at (2, 6) and Bob at (4, 4).
You cannot place Alice at (2, 6) and Bob at (6, 2) because the person at (4, 4) will be inside the fence.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3027.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20II/images/example4alicebob.png" style="width: 1123px; height: 308px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<pre>
<strong>Input:</strong> points = [[3,1],[1,3],[1,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two ways to place Alice and Bob such that Alice will not be sad:
- Place Alice at (1, 1) and Bob at (3, 1).
- Place Alice at (1, 3) and Bob at (1, 1).
You cannot place Alice at (1, 3) and Bob at (3, 1) because the person at (1, 1) will be on the fence.
Note that it does not matter if the fence encloses any area, the first and second fences in the image are valid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10<sup>9</sup> &lt;= points[i][0], points[i][1] &lt;= 10<sup>9</sup></code></li>
	<li>All <code>points[i]</code> are distinct.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting and Classification

First, we sort the array. Then, we can classify the results based on the properties of a triangle.

-   If the sum of the two smaller numbers is less than or equal to the largest number, it cannot form a triangle. Return "Invalid".
-   If the three numbers are equal, it is an equilateral triangle. Return "Equilateral".
-   If two numbers are equal, it is an isosceles triangle. Return "Isosceles".
-   If none of the above conditions are met, it is a scalene triangle. Return "Scalene".

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def numberOfPairs(self, points: List[List[int]]) -> int:
        points.sort(key=lambda x: (x[0], -x[1]))
        ans = 0
        for i, (_, y1) in enumerate(points):
            max_y = -inf
            for _, y2 in points[i + 1 :]:
                if max_y < y2 <= y1:
                    max_y = y2
                    ans += 1
        return ans
```

```java
class Solution {
    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int n = points.length;
        final int inf = 1 << 30;
        for (int i = 0; i < n; ++i) {
            int y1 = points[i][1];
            int maxY = -inf;
            for (int j = i + 1; j < n; ++j) {
                int y2 = points[j][1];
                if (maxY < y2 && y2 <= y1) {
                    maxY = y2;
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfPairs(vector<vector<int>>& points) {
        sort(points.begin(), points.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] < b[0] || (a[0] == b[0] && b[1] < a[1]);
        });
        int n = points.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int y1 = points[i][1];
            int maxY = INT_MIN;
            for (int j = i + 1; j < n; ++j) {
                int y2 = points[j][1];
                if (maxY < y2 && y2 <= y1) {
                    maxY = y2;
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

```go
func numberOfPairs(points [][]int) (ans int) {
	sort.Slice(points, func(i, j int) bool {
		return points[i][0] < points[j][0] || points[i][0] == points[j][0] && points[j][1] < points[i][1]
	})
	for i, p1 := range points {
		y1 := p1[1]
		maxY := math.MinInt32
		for _, p2 := range points[i+1:] {
			y2 := p2[1]
			if maxY < y2 && y2 <= y1 {
				maxY = y2
				ans++
			}
		}
	}
	return
}
```

```ts
function numberOfPairs(points: number[][]): number {
    points.sort((a, b) => (a[0] === b[0] ? b[1] - a[1] : a[0] - b[0]));
    const n = points.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const [_, y1] = points[i];
        let maxY = -Infinity;
        for (let j = i + 1; j < n; ++j) {
            const [_, y2] = points[j];
            if (maxY < y2 && y2 <= y1) {
                maxY = y2;
                ++ans;
            }
        }
    }
    return ans;
}
```

```cs
public class Solution {
    public int NumberOfPairs(int[][] points) {
        Array.Sort(points, (a, b) => a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int n = points.Length;
        int inf = 1 << 30;
        for (int i = 0; i < n; ++i) {
            int y1 = points[i][1];
            int maxY = -inf;
            for (int j = i + 1; j < n; ++j) {
                int y2 = points[j][1];
                if (maxY < y2 && y2 <= y1) {
                    maxY = y2;
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
