# [447. 回旋镖的数量](https://leetcode.cn/problems/number-of-boomerangs)

[English Version](/solution/0400-0499/0447.Number%20of%20Boomerangs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定平面上<em>&nbsp;</em><code>n</code><em> </em>对 <strong>互不相同</strong> 的点&nbsp;<code>points</code> ，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 。<strong>回旋镖</strong> 是由点&nbsp;<code>(i, j, k)</code> 表示的元组 ，其中&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;之间的距离和&nbsp;<code>i</code>&nbsp;和&nbsp;<code>k</code>&nbsp;之间的欧式距离相等（<strong>需要考虑元组的顺序</strong>）。</p>

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

<!-- 这里可写通用的实现逻辑 -->

计数器实现。

对于每个点，计算其他点到该点的距离，然后按照距离进行分组计数。对每个组中的点进行两两排列组合（A n 取 2，即 `n * (n - 1))`）计数即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfBoomerangs(self, points: List[List[int]]) -> int:
        ans = 0
        for p in points:
            counter = Counter()
            for q in points:
                distance = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1])
                counter[distance] += 1
            ans += sum([val * (val - 1) for val in counter.values()])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            Map<Integer, Integer> counter = new HashMap<>();
            for (int[] q : points) {
                int distance = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                counter.put(distance, counter.getOrDefault(distance, 0) + 1);
            }
            for (int val : counter.values()) {
                ans += val * (val - 1);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function numberOfBoomerangs(points: number[][]): number {
    let ans = 0;
    for (let p1 of points) {
        let hashMap: Map<number, number> = new Map();
        for (let p2 of points) {
            const distance = (p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2;
            hashMap.set(distance, (hashMap.get(distance) || 0) + 1);
        }
        for (let [, v] of [...hashMap]) {
            ans += v * (v - 1);
        }
    }
    return ans;
}
```

### **Go**

```go
func numberOfBoomerangs(points [][]int) int {
	ans := 0
	for _, p := range points {
		cnt := make(map[int]int)
		for _, q := range points {
			cnt[(p[0]-q[0])*(p[0]-q[0])+(p[1]-q[1])*(p[1]-q[1])]++
		}
		for _, v := range cnt {
			ans += v * (v - 1)
		}
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfBoomerangs(vector<vector<int>>& points) {
        int ans = 0;
        for (const auto& p : points) {
            unordered_map<int, int> cnt;
            for (const auto& q : points) {
                ++cnt[(p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1])];
            }
            for (const auto& [_, v] : cnt) {
                ans += v * (v - 1);
            }
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
