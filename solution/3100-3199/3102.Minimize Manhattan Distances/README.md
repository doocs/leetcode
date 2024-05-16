---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3102.Minimize%20Manhattan%20Distances/README.md
rating: 2215
source: 第 391 场周赛 Q4
tags:
    - 几何
    - 数组
    - 数学
    - 有序集合
    - 排序
---

# [3102. 最小化曼哈顿距离](https://leetcode.cn/problems/minimize-manhattan-distances)

[English Version](/solution/3100-3199/3102.Minimize%20Manhattan%20Distances/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>points</code> ，它表示二维平面上一些点的整数坐标，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 。</p>

<p>两点之间的距离定义为它们的<span data-keyword="manhattan-distance">曼哈顿距离</span>。</p>

<p>请你恰好移除一个点，返回移除后任意两点之间的 <strong>最大 </strong>距离可能的 <strong>最小 </strong>值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>points = [[3,10],[5,15],[10,2],[4,4]]
<strong>输出：</strong>12
<strong>解释：</strong>移除每个点后的最大距离如下所示：
- 移除第 0 个点后，最大距离在点 (5, 15) 和 (10, 2) 之间，为 |5 - 10| + |15 - 2| = 18 。
- 移除第 1 个点后，最大距离在点 (3, 10) 和 (10, 2) 之间，为 |3 - 10| + |10 - 2| = 15 。
- 移除第 2 个点后，最大距离在点 (5, 15) 和 (4, 4) 之间，为 |5 - 4| + |15 - 4| = 12 。
- 移除第 3 个点后，最大距离在点 (5, 15) 和 (10, 2) 之间的，为 |5 - 10| + |15 - 2| = 18 。
在恰好移除一个点后，任意两点之间的最大距离可能的最小值是 12 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>points = [[1,1],[1,1],[1,1]]
<strong>输出：</strong>0
<strong>解释：</strong>移除任一点后，任意两点之间的最大距离都是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>1 &lt;= points[i][0], points[i][1] &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

### 方法一：有序集合

对于两个点 $(x_1, y_1)$ 和 $(x_2, y_2)$，它们的曼哈顿距离为 $|x_1 - x_2| + |y_1 - y_2|$。我们可以将其转化为 $\max(x_1 - x_2, x_2 - x_1) + \max(y_1 - y_2, y_2 - y_1)$，即：

$$
|x_1 - x_2| + |y_1 - y_2| = \max \begin{cases}
x_1 - x_2 + y_1 - y_2 \\
x_2 - x_1 + y_2 - y_1 \\
x_1 - x_2 + y_2 - y_1 \\
x_2 - x_1 + y_1 - y_2
\end{cases}
$$

整理可得：

$$
|x_1 - x_2| + |y_1 - y_2| = \max \begin{cases}
(x_1 + y_1) - (x_2 + y_2) \\
(x_2 + y_2) - (x_1 + y_1) \\
(x_1 - y_1) - (x_2 - y_2) \\
(x_2 - y_2) - (x_1 - y_1)
\end{cases}
$$

其中，前两项可以表示为 $\max(\max(x_1 + y_1, x_2 + y_2) - \min(x_1 + y_1, x_2 + y_2))$，后两项可以表示为 $\max(\max(x_1 - y_1, x_2 - y_2) - \min(x_1 - y_1, x_2 - y_2))$。

因此，我们可以将所有点按照 $x + y$ 和 $x - y$ 的值分别存入两个有序集合中，然后枚举每个点，移除该点后，更新有序集合中的值，计算最大值和最小值的差值，取最小值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为点的个数。

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
