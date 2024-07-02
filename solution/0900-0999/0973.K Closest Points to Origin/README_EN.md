---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0973.K%20Closest%20Points%20to%20Origin/README_EN.md
tags:
    - Geometry
    - Array
    - Math
    - Divide and Conquer
    - Quickselect
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin)

[中文文档](/solution/0900-0999/0973.K%20Closest%20Points%20to%20Origin/README.md)

## Description

<!-- description:start -->

<p>Given an array of <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents a point on the <strong>X-Y</strong> plane and an integer <code>k</code>, return the <code>k</code> closest points to the origin <code>(0, 0)</code>.</p>

<p>The distance between two points on the <strong>X-Y</strong> plane is the Euclidean distance (i.e., <code>&radic;(x<sub>1</sub> - x<sub>2</sub>)<sup>2</sup> + (y<sub>1</sub> - y<sub>2</sub>)<sup>2</sup></code>).</p>

<p>You may return the answer in <strong>any order</strong>. The answer is <strong>guaranteed</strong> to be <strong>unique</strong> (except for the order that it is in).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0973.K%20Closest%20Points%20to%20Origin/images/closestplane1.jpg" style="width: 400px; height: 400px;" />
<pre>
<strong>Input:</strong> points = [[1,3],[-2,2]], k = 1
<strong>Output:</strong> [[-2,2]]
<strong>Explanation:</strong>
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) &lt; sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[3,3],[5,-1],[-2,4]], k = 2
<strong>Output:</strong> [[3,3],[-2,4]]
<strong>Explanation:</strong> The answer [[-2,4],[3,3]] would also be accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= points.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Custom Sorting

We sort all points by their distance from the origin in ascending order, and then take the first $k$ points.

The time complexity is $O(n \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $\text{points}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        points.sort(key=lambda p: hypot(p[0], p[1]))
        return points[:k]
```

#### Java

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (p1, p2) -> Math.hypot(p1[0], p1[1]) - Math.hypot(p2[0], p2[1]) > 0 ? 1 : -1);
        return Arrays.copyOfRange(points, 0, k);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        sort(points.begin(), points.end(), [](const vector<int>& p1, const vector<int>& p2) {
            return hypot(p1[0], p1[1]) < hypot(p2[0], p2[1]);
        });
        return vector<vector<int>>(points.begin(), points.begin() + k);
    }
};
```

#### Go

```go
func kClosest(points [][]int, k int) [][]int {
	sort.Slice(points, func(i, j int) bool {
		return math.Hypot(float64(points[i][0]), float64(points[i][1])) < math.Hypot(float64(points[j][0]), float64(points[j][1]))
	})
	return points[:k]
}
```

#### TypeScript

```ts
function kClosest(points: number[][], k: number): number[][] {
    points.sort((a, b) => Math.hypot(a[0], a[1]) - Math.hypot(b[0], b[1]));
    return points.slice(0, k);
}
```

#### Rust

```rust
impl Solution {
    pub fn k_closest(mut points: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        points.sort_by(|a, b| {
            let dist_a = f64::hypot(a[0] as f64, a[1] as f64);
            let dist_b = f64::hypot(b[0] as f64, b[1] as f64);
            dist_a.partial_cmp(&dist_b).unwrap()
        });
        points.into_iter().take(k as usize).collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Priority Queue (Max Heap)

We can use a priority queue (max heap) to maintain the $k$ closest points to the origin.

The time complexity is $O(n \times \log k)$, and the space complexity is $O(k)$. Here, $n$ is the length of the array $\text{points}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        max_q = []
        for i, (x, y) in enumerate(points):
            dist = math.hypot(x, y)
            heappush(max_q, (-dist, i))
            if len(max_q) > k:
                heappop(max_q)
        return [points[i] for _, i in max_q]
```

#### Java

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxQ = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < points.length; ++i) {
            int x = points[i][0], y = points[i][1];
            maxQ.offer(new int[] {x * x + y * y, i});
            if (maxQ.size() > k) {
                maxQ.poll();
            }
        }
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; ++i) {
            ans[i] = points[maxQ.poll()[1]];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        priority_queue<pair<double, int>> pq;
        for (int i = 0, n = points.size(); i < n; ++i) {
            double dist = hypot(points[i][0], points[i][1]);
            pq.push({dist, i});
            if (pq.size() > k) {
                pq.pop();
            }
        }
        vector<vector<int>> ans;
        while (!pq.empty()) {
            ans.push_back(points[pq.top().second]);
            pq.pop();
        }
        return ans;
    }
};
```

#### Go

```go
func kClosest(points [][]int, k int) [][]int {
	maxQ := hp{}
	for i, p := range points {
		dist := math.Hypot(float64(p[0]), float64(p[1]))
		heap.Push(&maxQ, pair{dist, i})
		if len(maxQ) > k {
			heap.Pop(&maxQ)
		}
	}
	ans := make([][]int, k)
	for i, p := range maxQ {
		ans[i] = points[p.i]
	}
	return ans
}

type pair struct {
	dist float64
	i    int
}

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.dist > b.dist
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function kClosest(points: number[][], k: number): number[][] {
    const maxQ = new MaxPriorityQueue();
    for (const [x, y] of points) {
        const dist = x * x + y * y;
        maxQ.enqueue([x, y], dist);
        if (maxQ.size() > k) {
            maxQ.dequeue();
        }
    }
    return maxQ.toArray().map(item => item.element);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Binary Search

We notice that as the distance increases, the number of points increases as well. There exists a critical value such that the number of points before this value is less than or equal to $k$, and the number of points after this value is greater than $k$.

Therefore, we can use binary search to enumerate the distance. In each binary search iteration, we count the number of points whose distance is less than or equal to the current distance. If the count is greater than or equal to $k$, it indicates that the critical value is on the left side, so we set the right boundary equal to the current distance; otherwise, the critical value is on the right side, so we set the left boundary equal to the current distance plus one.

After the binary search is finished, we just need to return the points whose distance is less than or equal to the left boundary.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\text{points}$, and $M$ is the maximum value of the distance.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        dist = [x * x + y * y for x, y in points]
        l, r = 0, max(dist)
        while l < r:
            mid = (l + r) >> 1
            cnt = sum(d <= mid for d in dist)
            if cnt >= k:
                r = mid
            else:
                l = mid + 1
        return [points[i] for i, d in enumerate(dist) if d <= l]
```

#### Java

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;
        int[] dist = new int[n];
        int r = 0;
        for (int i = 0; i < n; ++i) {
            int x = points[i][0], y = points[i][1];
            dist[i] = x * x + y * y;
            r = Math.max(r, dist[i]);
        }
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int d : dist) {
                if (d <= mid) {
                    ++cnt;
                }
            }
            if (cnt >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int[][] ans = new int[k][0];
        for (int i = 0, j = 0; i < n; ++i) {
            if (dist[i] <= l) {
                ans[j++] = points[i];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        int n = points.size();
        int dist[n];
        int r = 0;
        for (int i = 0; i < n; ++i) {
            int x = points[i][0], y = points[i][1];
            dist[i] = x * x + y * y;
            r = max(r, dist[i]);
        }
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int d : dist) {
                cnt += d <= mid;
            }
            if (cnt >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        vector<vector<int>> ans;
        for (int i = 0; i < n; ++i) {
            if (dist[i] <= l) {
                ans.emplace_back(points[i]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func kClosest(points [][]int, k int) (ans [][]int) {
	n := len(points)
	dist := make([]int, n)
	l, r := 0, 0
	for i, p := range points {
		dist[i] = p[0]*p[0] + p[1]*p[1]
		r = max(r, dist[i])
	}
	for l < r {
		mid := (l + r) >> 1
		cnt := 0
		for _, d := range dist {
			if d <= mid {
				cnt++
			}
		}
		if cnt >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	for i, p := range points {
		if dist[i] <= l {
			ans = append(ans, p)
		}
	}
	return
}
```

#### TypeScript

```ts
function kClosest(points: number[][], k: number): number[][] {
    const dist = points.map(([x, y]) => x * x + y * y);
    let [l, r] = [0, Math.max(...dist)];
    while (l < r) {
        const mid = (l + r) >> 1;
        let cnt = 0;
        for (const d of dist) {
            if (d <= mid) {
                ++cnt;
            }
        }
        if (cnt >= k) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return points.filter((_, i) => dist[i] <= l);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
