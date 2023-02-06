# [1691. Maximum Height by Stacking Cuboids](https://leetcode.com/problems/maximum-height-by-stacking-cuboids)

[中文文档](/solution/1600-1699/1691.Maximum%20Height%20by%20Stacking%20Cuboids/README.md)

## Description

<p>Given <code>n</code> <code>cuboids</code> where the dimensions of the <code>i<sup>th</sup></code> cuboid is <code>cuboids[i] = [width<sub>i</sub>, length<sub>i</sub>, height<sub>i</sub>]</code> (<strong>0-indexed</strong>). Choose a <strong>subset</strong> of <code>cuboids</code> and place them on each other.</p>

<p>You can place cuboid <code>i</code> on cuboid <code>j</code> if <code>width<sub>i</sub> &lt;= width<sub>j</sub></code> and <code>length<sub>i</sub> &lt;= length<sub>j</sub></code> and <code>height<sub>i</sub> &lt;= height<sub>j</sub></code>. You can rearrange any cuboid&#39;s dimensions by rotating it to put it on another cuboid.</p>

<p>Return <em>the <strong>maximum height</strong> of the stacked</em> <code>cuboids</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1691.Maximum%20Height%20by%20Stacking%20Cuboids/images/image.jpg" style="width: 420px; height: 299px;" /></strong></p>

<pre>
<strong>Input:</strong> cuboids = [[50,45,20],[95,37,53],[45,23,12]]
<strong>Output:</strong> 190
<strong>Explanation:</strong>
Cuboid 1 is placed on the bottom with the 53x37 side facing down with height 95.
Cuboid 0 is placed next with the 45x20 side facing down with height 50.
Cuboid 2 is placed next with the 23x12 side facing down with height 45.
The total height is 95 + 50 + 45 = 190.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cuboids = [[38,25,45],[76,35,3]]
<strong>Output:</strong> 76
<strong>Explanation:</strong>
You can&#39;t place any of the cuboids on the other.
We choose cuboid 1 and rotate it so that the 35x3 side is facing down and its height is 76.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
<strong>Output:</strong> 102
<strong>Explanation:</strong>
After rearranging the cuboids, you can see that all cuboids have the same dimension.
You can place the 11x7 side down on all cuboids so their heights are 17.
The maximum height of stacked cuboids is 6 * 17 = 102.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == cuboids.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= width<sub>i</sub>, length<sub>i</sub>, height<sub>i</sub> &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxHeight(self, cuboids: List[List[int]]) -> int:
        for c in cuboids:
            c.sort()
        cuboids.sort()
        n = len(cuboids)
        f = [0] * n
        for i in range(n):
            for j in range(i):
                if cuboids[j][1] <= cuboids[i][1] and cuboids[j][2] <= cuboids[i][2]:
                    f[i] = max(f[i], f[j])
            f[i] += cuboids[i][2]
        return max(f)
```

### **Java**

```java
class Solution {
    public int maxHeight(int[][] cuboids) {
        for (var c : cuboids) {
            Arrays.sort(c);
        }
        Arrays.sort(cuboids,
            (a, b) -> a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]);
        int n = cuboids.length;
        int[] f = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            f[i] += cuboids[i][2];
        }
        return Arrays.stream(f).max().getAsInt();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxHeight(vector<vector<int>>& cuboids) {
        for (auto& c : cuboids) sort(c.begin(), c.end());
        sort(cuboids.begin(), cuboids.end());
        int n = cuboids.size();
        vector<int> f(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                    f[i] = max(f[i], f[j]);
                }
            }
            f[i] += cuboids[i][2];
        }
        return *max_element(f.begin(), f.end());
    }
};
```

### **Go**

```go
func maxHeight(cuboids [][]int) (ans int) {
	for _, c := range cuboids {
		sort.Ints(c)
	}
	sort.Slice(cuboids, func(i, j int) bool {
		a, b := cuboids[i], cuboids[j]
		return a[0] < b[0] || a[0] == b[0] && (a[1] < b[1] || a[1] == b[1] && a[2] < b[2])
	})
	n := len(cuboids)
	f := make([]int, n)
	for i := range f {
		for j := 0; j < i; j++ {
			if cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2] {
				f[i] = max(f[i], f[j])
			}
		}
		f[i] += cuboids[i][2]
		ans = max(ans, f[i])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} cuboids
 * @return {number}
 */
var maxHeight = function (cuboids) {
    for (const c of cuboids) {
        c.sort((a, b) => a - b);
    }
    cuboids.sort((a, b) => {
        if (a[0] != b[0]) return a[0] - b[0];
        if (a[1] != b[1]) return a[1] - b[1];
        return a[2] - b[2];
    });
    const n = cuboids.length;
    const f = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            const ok =
                cuboids[j][1] <= cuboids[i][1] &&
                cuboids[j][2] <= cuboids[i][2];
            if (ok) f[i] = Math.max(f[i], f[j]);
        }
        f[i] += cuboids[i][2];
    }
    return Math.max(...f);
};
```

### **...**

```

```

<!-- tabs:end -->
