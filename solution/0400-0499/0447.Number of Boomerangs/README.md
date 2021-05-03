# [447. 回旋镖的数量](https://leetcode-cn.com/problems/number-of-boomerangs)

[English Version](/solution/0400-0499/0447.Number%20of%20Boomerangs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定平面上<em> </em><code>n</code><em> </em>对 <strong>互不相同</strong> 的点 <code>points</code> ，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 。<strong>回旋镖</strong> 是由点 <code>(i, j, k)</code> 表示的元组 ，其中 <code>i</code> 和 <code>j</code> 之间的距离和 <code>i</code> 和 <code>k</code> 之间的距离相等（<strong>需要考虑元组的顺序</strong>）。</p>

<p>返回平面上所有回旋镖的数量。</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>points = [[0,0],[1,0],[2,0]]
<strong>输出：</strong>2
<strong>解释：</strong>两个回旋镖为 <strong>[[1,0],[0,0],[2,0]]</strong> 和 <strong>[[1,0],[2,0],[0,0]]</strong>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>points = [[1,1],[2,2],[3,3]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>points = [[1,1]]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == points.length</code></li>
	<li><code>1 <= n <= 500</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10<sup>4</sup> <= x<sub>i</sub>, y<sub>i</sub> <= 10<sup>4</sup></code></li>
	<li>所有点都 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

计数器实现。

对于每个点，计算其他点到该点的距离，然后按照距离进行分组计数。对每个组中的点进行两两排列组合（A n 取 2，即 `n * (n - 1))`）计数即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfBoomerangs(self, points: List[List[int]]) -> int:
        n = len(points)
        if len(points) < 3:
            return 0
        number = 0
        for i in range(n):
            distance_counter = collections.Counter()
            for j in range(n):
                if i == j:
                    continue
                x1, y1 = points[i][0], points[i][1]
                x2, y2 = points[j][0], points[j][1]
                distance = (x1 - x2) ** 2 + (y1 - y2) ** 2
                distance_counter[distance] += 1
            number += sum([val * (val - 1) for val in distance_counter.values()])
        return number
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        if (n < 3) {
            return 0;
        }
        int number = 0;
        for (int i = 0; i < n; ++i) {
            Map<Integer, Integer> distanceCounter = new HashMap<>();
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                int distance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                distanceCounter.put(distance, distanceCounter.getOrDefault(distance, 0) + 1);
            }
            for (int val : distanceCounter.values()) {
                number += val * (val - 1);
            }
        }
        return number;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
