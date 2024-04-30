# [447. 回旋镖的数量](https://leetcode.cn/problems/number-of-boomerangs)

[English Version](/solution/0400-0499/0447.Number%20of%20Boomerangs/README_EN.md)

<!-- tags:数组,哈希表,数学 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定平面上<em>&nbsp;</em><code>n</code><em> </em>对 <strong>互不相同</strong> 的点&nbsp;<code>points</code> ，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 。<strong>回旋镖</strong> 是由点&nbsp;<code>(i, j, k)</code> 表示的元组 ，其中&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;之间的欧式距离和&nbsp;<code>i</code>&nbsp;和&nbsp;<code>k</code>&nbsp;之间的欧式距离相等（<strong>需要考虑元组的顺序</strong>）。</p>

<p>返回平面上所有回旋镖的数量。</p>
&nbsp;

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

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n ==&nbsp;points.length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>所有点都 <strong>互不相同</strong></li>
</ul>

## 解法

### 方法一：枚举 + 计数

我们可以枚举 `points` 中的每个点作为回旋镖的点 $i$，然后用一个哈希表 $cnt$ 记录其他点到 $i$ 的距离出现的次数。

如果有 $x$ 个点到 $i$ 的距离相等，那么我们可以任选其中 $2$ 个点作为回旋镖的 $j$ 和 $k$，方案数为 $A_x^2 = x \times (x - 1)$。因此，我们对哈希表中的每个值 $x$，都计算并累加 $A_x^2$，就可以得到满足题目要求的回旋镖数量之和。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `points` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def numberOfBoomerangs(self, points: List[List[int]]) -> int:
        ans = 0
        for p1 in points:
            cnt = Counter()
            for p2 in points:
                d = dist(p1, p2)
                ans += cnt[d]
                cnt[d] += 1
        return ans << 1
```

```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p1 : points) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int[] p2 : points) {
                int d = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
                ans += cnt.getOrDefault(d, 0);
                cnt.merge(d, 1, Integer::sum);
            }
        }
        return ans << 1;
    }
}
```

```cpp
class Solution {
public:
    int numberOfBoomerangs(vector<vector<int>>& points) {
        int ans = 0;
        for (auto& p1 : points) {
            unordered_map<int, int> cnt;
            for (auto& p2 : points) {
                int d = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
                ans += cnt[d];
                cnt[d]++;
            }
        }
        return ans << 1;
    }
};
```

```go
func numberOfBoomerangs(points [][]int) (ans int) {
	for _, p1 := range points {
		cnt := map[int]int{}
		for _, p2 := range points {
			d := (p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1])
			ans += cnt[d]
			cnt[d]++
		}
	}
	ans <<= 1
	return
}
```

```ts
function numberOfBoomerangs(points: number[][]): number {
    let ans = 0;
    for (const [x1, y1] of points) {
        const cnt: Map<number, number> = new Map();
        for (const [x2, y2] of points) {
            const d = (x1 - x2) ** 2 + (y1 - y2) ** 2;
            ans += cnt.get(d) || 0;
            cnt.set(d, (cnt.get(d) || 0) + 1);
        }
    }
    return ans << 1;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def numberOfBoomerangs(self, points: List[List[int]]) -> int:
        ans = 0
        for p1 in points:
            cnt = Counter()
            for p2 in points:
                d = dist(p1, p2)
                cnt[d] += 1
            ans += sum(x * (x - 1) for x in cnt.values())
        return ans
```

```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p1 : points) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int[] p2 : points) {
                int d = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
                cnt.merge(d, 1, Integer::sum);
            }
            for (int x : cnt.values()) {
                ans += x * (x - 1);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfBoomerangs(vector<vector<int>>& points) {
        int ans = 0;
        for (auto& p1 : points) {
            unordered_map<int, int> cnt;
            for (auto& p2 : points) {
                int d = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
                cnt[d]++;
            }
            for (auto& [_, x] : cnt) {
                ans += x * (x - 1);
            }
        }
        return ans;
    }
};
```

```go
func numberOfBoomerangs(points [][]int) (ans int) {
	for _, p1 := range points {
		cnt := map[int]int{}
		for _, p2 := range points {
			d := (p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1])
			cnt[d]++
		}
		for _, x := range cnt {
			ans += x * (x - 1)
		}
	}
	return
}
```

```ts
function numberOfBoomerangs(points: number[][]): number {
    let ans = 0;
    for (const [x1, y1] of points) {
        const cnt: Map<number, number> = new Map();
        for (const [x2, y2] of points) {
            const d = (x1 - x2) ** 2 + (y1 - y2) ** 2;
            cnt.set(d, (cnt.get(d) || 0) + 1);
        }
        for (const [_, x] of cnt) {
            ans += x * (x - 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
