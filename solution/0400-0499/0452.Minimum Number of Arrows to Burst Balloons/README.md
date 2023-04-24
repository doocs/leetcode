# [452. 用最少数量的箭引爆气球](https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons)

[English Version](/solution/0400-0499/0452.Minimum%20Number%20of%20Arrows%20to%20Burst%20Balloons/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组&nbsp;<code>points</code>&nbsp;，其中<code>points[i] = [x<sub>start</sub>, x<sub>end</sub>]</code>&nbsp;表示水平直径在&nbsp;<code>x<sub>start</sub></code>&nbsp;和&nbsp;<code>x<sub>end</sub></code>之间的气球。你不知道气球的确切 y 坐标。</p>

<p>一支弓箭可以沿着 x 轴从不同点 <strong>完全垂直</strong> 地射出。在坐标 <code>x</code> 处射出一支箭，若有一个气球的直径的开始和结束坐标为 <code>x</code><sub><code>start</code>，</sub><code>x</code><sub><code>end</code>，</sub> 且满足 &nbsp;<code>x<sub>start</sub>&nbsp;≤ x ≤ x</code><sub><code>end</code>，</sub>则该气球会被 <strong>引爆</strong>&nbsp;<sub>。</sub>可以射出的弓箭的数量 <strong>没有限制</strong> 。 弓箭一旦被射出之后，可以无限地前进。</p>

<p>给你一个数组 <code>points</code> ，<em>返回引爆所有气球所必须射出的 <strong>最小</strong> 弓箭数&nbsp;</em>。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>points = [[10,16],[2,8],[1,6],[7,12]]
<strong>输出：</strong>2
<strong>解释：</strong>气球可以用2支箭来爆破:
-在x = 6处射出箭，击破气球[2,8]和[1,6]。
-在x = 11处发射箭，击破气球[10,16]和[7,12]。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>points = [[1,2],[3,4],[5,6],[7,8]]
<strong>输出：</strong>4
<strong>解释：</strong>每个气球需要射出一支箭，总共需要4支箭。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>points = [[1,2],[2,3],[3,4],[4,5]]
<strong>输出：</strong>2
解释：气球可以用2支箭来爆破:
- 在x = 2处发射箭，击破气球[1,2]和[2,3]。
- 在x = 4处射出箭，击破气球[3,4]和[4,5]。</pre>

<p>&nbsp;</p>

<p><meta charset="UTF-8" /></p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= x<sub>start</sub>&nbsp;&lt; x<sub>end</sub>&nbsp;&lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

我们可以将所有气球按照右端点升序排序，然后从左到右遍历气球，维护当前的箭所能覆盖的最右端点 $last$，如果当前气球的左端点 $a$ 大于 $last$，说明当前箭无法覆盖当前气球，需要额外射出一支箭，那么答案加一，同时更新 $last$ 为当前气球的右端点 $b$。

遍历结束后，即可得到答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为气球的数量。

相似题目：[757. 设置交集大小至少为 2](/solution/0700-0799/0757.Set%20Intersection%20Size%20At%20Least%20Two/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        ans, last = 0, -inf
        for a, b in sorted(points, key=lambda x: x[1]):
            if a > last:
                ans += 1
                last = b
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        // 直接 a[1] - b[1] 可能会溢出
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int ans = 0;
        long last = -(1L << 60);
        for (var p : points) {
            int a = p[0], b = p[1];
            if (a > last) {
                ++ans;
                last = b;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        sort(points.begin(), points.end(), [](vector<int>& a, vector<int>& b) {
            return a[1] < b[1];
        });
        int ans = 0;
        long long last = -(1LL << 60);
        for (auto& p : points) {
            int a = p[0], b = p[1];
            if (a > last) {
                ++ans;
                last = b;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findMinArrowShots(points [][]int) (ans int) {
	sort.Slice(points, func(i, j int) bool { return points[i][1] < points[j][1] })
	last := -(1 << 60)
	for _, p := range points {
		a, b := p[0], p[1]
		if a > last {
			ans++
			last = b
		}
	}
	return
}
```

### **TypeScript**

```ts
function findMinArrowShots(points: number[][]): number {
    points.sort((a, b) => a[1] - b[1]);
    let ans = 0;
    let last = -Infinity;
    for (const [a, b] of points) {
        if (last < a) {
            ans++;
            last = b;
        }
    }
    return ans;
}
```

### **C#**

```cs
public class Solution {
    public int FindMinArrowShots(int[][] points) {
        Array.Sort(points, (a, b) => a[1] < b[1] ? -1 : a[1] > b[1] ? 1 : 0);
        int ans = 0;
        long last = long.MinValue;
        foreach (var point in points) {
            if (point[0] > last) {
                ++ans;
                last = point[1];
            }
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
