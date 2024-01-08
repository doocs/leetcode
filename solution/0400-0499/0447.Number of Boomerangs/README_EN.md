# [447. Number of Boomerangs](https://leetcode.com/problems/number-of-boomerangs)

[中文文档](/solution/0400-0499/0447.Number%20of%20Boomerangs/README.md)

## Description

<p>You are given <code>n</code> <code>points</code> in the plane that are all <strong>distinct</strong>, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>. A <strong>boomerang</strong> is a tuple of points <code>(i, j, k)</code> such that the distance between <code>i</code> and <code>j</code> equals the distance between <code>i</code> and <code>k</code> <strong>(the order of the tuple matters)</strong>.</p>

<p>Return <em>the number of boomerangs</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> points = [[0,0],[1,0],[2,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[1,1],[2,2],[3,3]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> points = [[1,1]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == points.length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>All the points are <strong>unique</strong>.</li>
</ul>

## Solutions

**Solution 1: Enumeration + Counting**

We can enumerate each point in `points` as the boomerang's point $i$, and then use a hash table $cnt$ to record the number of times the distance from other points to $i$ appears.

If there are $x$ points with equal distance to $i$, then we can arbitrarily select two of them as the boomerang's $j$ and $k$. The number of schemes is $A_x^2 = x \times (x - 1)$. Therefore, for each value $x$ in the hash table, we calculate and accumulate $A_x^2$, which gives us the total number of boomerangs that meet the problem's requirements.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the length of the array `points`.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
