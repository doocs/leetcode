# [3102. Minimize Manhattan Distances](https://leetcode.com/problems/minimize-manhattan-distances)

[中文文档](/solution/3100-3199/3102.Minimize%20Manhattan%20Distances/README.md)

<!-- tags: -->

## Description

<p>You are given a <strong>0-indexed</strong> array <code>points</code> representing integer coordinates of some points on a 2D plane, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>.</p>

<p>The distance between two points is defined as their <span data-keyword="manhattan-distance">Manhattan distance</span>.</p>

<p>Return <em>the <strong>minimum</strong> possible value for <strong>maximum</strong> distance between any two points by removing exactly one point</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> points = [[3,10],[5,15],[10,2],[4,4]]
<strong>Output:</strong> 12
<strong>Explanation:</strong> The maximum distance after removing each point is the following:
- After removing the 0<sup>th</sup> point the maximum distance is between points (5, 15) and (10, 2), which is |5 - 10| + |15 - 2| = 18.
- After removing the 1<sup>st</sup> point the maximum distance is between points (3, 10) and (10, 2), which is |3 - 10| + |10 - 2| = 15.
- After removing the 2<sup>nd</sup> point the maximum distance is between points (5, 15) and (4, 4), which is |5 - 4| + |15 - 4| = 12.
- After removing the 3<sup>rd</sup> point the maximum distance is between points (5, 15) and (10, 2), which is |5 - 10| + |15 - 2| = 18.
It can be seen that 12 is the minimum possible maximum distance between any two points after removing exactly one point.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[1,1],[1,1],[1,1]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> It can be seen that removing any of the points results in the maximum distance between any two points of 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>1 &lt;= points[i][0], points[i][1] &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
from sortedcontainers import SortedList


class Solution:
    def minimumDistance(self, points: List[List[int]]) -> int:
        sl1 = SortedList()
        sl2 = SortedList()
        for x, y in points:
            sl1.add(x + y)
            sl2.add(x - y)
        ans = inf
        for x, y in points:
            sl1.remove(x + y)
            sl2.remove(x - y)
            ans = min(ans, max(sl1[-1] - sl1[0], sl2[-1] - sl2[0]))
            sl1.add(x + y)
            sl2.add(x - y)
        return ans
```

```java
class Solution {
    public int minimumDistance(int[][] points) {
        TreeMap<Integer, Integer> tm1 = new TreeMap<>();
        TreeMap<Integer, Integer> tm2 = new TreeMap<>();
        for (int[] p : points) {
            int x = p[0], y = p[1];
            tm1.merge(x + y, 1, Integer::sum);
            tm2.merge(x - y, 1, Integer::sum);
        }
        int ans = Integer.MAX_VALUE;
        for (int[] p : points) {
            int x = p[0], y = p[1];
            if (tm1.merge(x + y, -1, Integer::sum) == 0) {
                tm1.remove(x + y);
            }
            if (tm2.merge(x - y, -1, Integer::sum) == 0) {
                tm2.remove(x - y);
            }
            ans = Math.min(
                ans, Math.max(tm1.lastKey() - tm1.firstKey(), tm2.lastKey() - tm2.firstKey()));
            tm1.merge(x + y, 1, Integer::sum);
            tm2.merge(x - y, 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumDistance(vector<vector<int>>& points) {
        multiset<int> st1;
        multiset<int> st2;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            st1.insert(x + y);
            st2.insert(x - y);
        }
        int ans = INT_MAX;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            st1.erase(st1.find(x + y));
            st2.erase(st2.find(x - y));
            ans = min(ans, max(*st1.rbegin() - *st1.begin(), *st2.rbegin() - *st2.begin()));
            st1.insert(x + y);
            st2.insert(x - y);
        }
        return ans;
    }
};
```

```go
func minimumDistance(points [][]int) int {
	st1 := redblacktree.New[int, int]()
	st2 := redblacktree.New[int, int]()
	merge := func(st *redblacktree.Tree[int, int], x, v int) {
		c, _ := st.Get(x)
		if c+v == 0 {
			st.Remove(x)
		} else {
			st.Put(x, c+v)
		}
	}
	for _, p := range points {
		x, y := p[0], p[1]
		merge(st1, x+y, 1)
		merge(st2, x-y, 1)
	}
	ans := math.MaxInt
	for _, p := range points {
		x, y := p[0], p[1]
		merge(st1, x+y, -1)
		merge(st2, x-y, -1)
		ans = min(ans, max(st1.Right().Key-st1.Left().Key, st2.Right().Key-st2.Left().Key))
		merge(st1, x+y, 1)
		merge(st2, x-y, 1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
