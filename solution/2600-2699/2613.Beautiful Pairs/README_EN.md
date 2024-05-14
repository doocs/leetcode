# [2613. Beautiful Pairs 🔒](https://leetcode.com/problems/beautiful-pairs)

[中文文档](/solution/2600-2699/2613.Beautiful%20Pairs/README.md)

<!-- tags:Geometry,Array,Math,Divide and Conquer,Ordered Set,Sorting -->

<!-- difficulty:Hard -->

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code> of the same length. A pair of indices <code>(i,j)</code> is called <strong>beautiful</strong> if<code>|nums1[i] - nums1[j]| + |nums2[i] - nums2[j]|</code> is the smallest amongst all possible indices pairs where <code>i &lt; j</code>.</p>

<p>Return <em>the beautiful pair. In the case that there are multiple beautiful pairs, return the lexicographically smallest pair.</em></p>

<p>Note that</p>

<ul>
	<li><code>|x|</code> denotes the absolute value of <code>x</code>.</li>
	<li>A pair of indices <code>(i<sub>1</sub>, j<sub>1</sub>)</code> is lexicographically smaller than <code>(i<sub>2</sub>, j<sub>2</sub>)</code> if <code>i<sub>1</sub> &lt; i<sub>2</sub></code> or <code>i<sub>1</sub> == i<sub>2</sub></code> and <code>j<sub>1</sub> &lt; j<sub>2</sub></code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,2,4], nums2 = [2,3,1,2,3]
<strong>Output:</strong> [0,3]
<strong>Explanation:</strong> Consider index 0 and index 3. The value of |nums1[i]-nums1[j]| + |nums2[i]-nums2[j]| is 1, which is the smallest value we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,4,3,2,5], nums2 = [1,4,2,3,5,1]
<strong>Output:</strong> [1,4]
<strong>Explanation:</strong> Consider index 1 and index 4. The value of |nums1[i]-nums1[j]| + |nums2[i]-nums2[j]| is 1, which is the smallest value we can achieve.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1.length == nums2.length</code></li>
	<li><code>0 &lt;= nums1<sub>i</sub><sub>&nbsp;</sub>&lt;= nums1.length</code></li>
	<li><code>0 &lt;= nums2<sub>i</sub>&nbsp;&lt;= nums2.length</code></li>
</ul>

## Solutions

### Solution 1: Sorting + Divide and Conquer

This problem is equivalent to finding two points in the plane, such that the Manhattan distance between them is the smallest. If there are multiple points satisfying the condition, return the one with the smallest index.

First, we handle the case where there are duplicate points. For each point, we record the corresponding indices in a list. If the length of the index list is greater than $1$, then the first two indices in the index list can be used as candidates, and we find the smallest index pair.

If there are no duplicate points, we sort all the points by $x$ coordinates, and then use the divide and conquer to solve the problem.

For each interval $[l, r]$, we first calculate the median of the $x$ coordinates $m$, and then recursively solve the left and right intervals, and get $d_1, (pi_1, pj_1)$ and $d_2, (pi_2, pj_2)$ respectively, where $d_1$ and $d_2$ are the minimum Manhattan distances of the left and right intervals respectively, and $(pi_1, pj_1)$ and $(pi_2, pj_2)$ are the index pairs of the two points of the minimum Manhattan distance of the left and right intervals respectively. We take the smaller one of $d_1$ and $d_2$ as the minimum Manhattan distance of the current interval, and if $d_1 = d_2$, we take the one with the smaller index as the answer. The corresponding two points of the index are taken as the answer.

The above considers the case where the two points are on the same side. If the two points are on different sides, we take the middle point, i.e. the point with the index of $m = \lfloor (l + r) / 2 \rfloor$ as the standard, and divide a new region. The range of this region is to expand the range of $d_1$ from the middle point to the left and right sides respectively. Then we sort these points in the range by $y$ coordinates, and then traverse each point pair in the sorted order. If the difference of the $y$ coordinates of the two points is greater than the current minimum Manhattan distance, then the following point pairs do not need to be considered, because their $y$ coordinate differences are larger, so the Manhattan distance is larger, and it will not be smaller than the current minimum Manhattan distance. Otherwise, we update the minimum Manhattan distance, and update the answer.

Finally, we return the answer.

Time complexity: $O(n \times \log n)$, where $n$ is the length of the array.

Space complexity: $O(n)$.

<!-- tabs:start -->

```python
class Solution:
    def beautifulPair(self, nums1: List[int], nums2: List[int]) -> List[int]:
        def dist(x1: int, y1: int, x2: int, y2: int) -> int:
            return abs(x1 - x2) + abs(y1 - y2)

        def dfs(l: int, r: int):
            if l >= r:
                return inf, -1, -1
            m = (l + r) >> 1
            x = points[m][0]
            d1, pi1, pj1 = dfs(l, m)
            d2, pi2, pj2 = dfs(m + 1, r)
            if d1 > d2 or (d1 == d2 and (pi1 > pi2 or (pi1 == pi2 and pj1 > pj2))):
                d1, pi1, pj1 = d2, pi2, pj2
            t = [p for p in points[l : r + 1] if abs(p[0] - x) <= d1]
            t.sort(key=lambda x: x[1])
            for i in range(len(t)):
                for j in range(i + 1, len(t)):
                    if t[j][1] - t[i][1] > d1:
                        break
                    pi, pj = sorted([t[i][2], t[j][2]])
                    d = dist(t[i][0], t[i][1], t[j][0], t[j][1])
                    if d < d1 or (d == d1 and (pi < pi1 or (pi == pi1 and pj < pj1))):
                        d1, pi1, pj1 = d, pi, pj
            return d1, pi1, pj1

        pl = defaultdict(list)
        for i, (x, y) in enumerate(zip(nums1, nums2)):
            pl[(x, y)].append(i)
        points = []
        for i, (x, y) in enumerate(zip(nums1, nums2)):
            if len(pl[(x, y)]) > 1:
                return [i, pl[(x, y)][1]]
            points.append((x, y, i))
        points.sort()
        _, pi, pj = dfs(0, len(points) - 1)
        return [pi, pj]
```

```java
class Solution {
    private List<int[]> points = new ArrayList<>();

    public int[] beautifulPair(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Long, List<Integer>> pl = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            long z = f(nums1[i], nums2[i]);
            pl.computeIfAbsent(z, k -> new ArrayList<>()).add(i);
        }
        for (int i = 0; i < n; ++i) {
            long z = f(nums1[i], nums2[i]);
            if (pl.get(z).size() > 1) {
                return new int[] {i, pl.get(z).get(1)};
            }
            points.add(new int[] {nums1[i], nums2[i], i});
        }
        points.sort((a, b) -> a[0] - b[0]);
        int[] ans = dfs(0, points.size() - 1);
        return new int[] {ans[1], ans[2]};
    }

    private long f(int x, int y) {
        return x * 100000L + y;
    }

    private int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private int[] dfs(int l, int r) {
        if (l >= r) {
            return new int[] {1 << 30, -1, -1};
        }
        int m = (l + r) >> 1;
        int x = points.get(m)[0];
        int[] t1 = dfs(l, m);
        int[] t2 = dfs(m + 1, r);
        if (t1[0] > t2[0]
            || (t1[0] == t2[0] && (t1[1] > t2[1] || (t1[1] == t2[1] && t1[2] > t2[2])))) {
            t1 = t2;
        }
        List<int[]> t = new ArrayList<>();
        for (int i = l; i <= r; ++i) {
            if (Math.abs(points.get(i)[0] - x) <= t1[0]) {
                t.add(points.get(i));
            }
        }
        t.sort((a, b) -> a[1] - b[1]);
        for (int i = 0; i < t.size(); ++i) {
            for (int j = i + 1; j < t.size(); ++j) {
                if (t.get(j)[1] - t.get(i)[1] > t1[0]) {
                    break;
                }
                int pi = Math.min(t.get(i)[2], t.get(j)[2]);
                int pj = Math.max(t.get(i)[2], t.get(j)[2]);
                int d = dist(t.get(i)[0], t.get(i)[1], t.get(j)[0], t.get(j)[1]);
                if (d < t1[0] || (d == t1[0] && (pi < t1[1] || (pi == t1[1] && pj < t1[2])))) {
                    t1 = new int[] {d, pi, pj};
                }
            }
        }
        return t1;
    }
}
```

```cpp
class Solution {
public:
    vector<int> beautifulPair(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        unordered_map<long long, vector<int>> pl;
        for (int i = 0; i < n; ++i) {
            pl[f(nums1[i], nums2[i])].push_back(i);
        }
        vector<tuple<int, int, int>> points;
        for (int i = 0; i < n; ++i) {
            long long z = f(nums1[i], nums2[i]);
            if (pl[z].size() > 1) {
                return {i, pl[z][1]};
            }
            points.emplace_back(nums1[i], nums2[i], i);
        }

        function<tuple<int, int, int>(int, int)> dfs = [&](int l, int r) -> tuple<int, int, int> {
            if (l >= r) {
                return {1 << 30, -1, -1};
            }
            int m = (l + r) >> 1;
            int x = get<0>(points[m]);
            auto t1 = dfs(l, m);
            auto t2 = dfs(m + 1, r);
            if (get<0>(t1) > get<0>(t2) || (get<0>(t1) == get<0>(t2) && (get<1>(t1) > get<1>(t2) || (get<1>(t1) == get<1>(t2) && get<2>(t1) > get<2>(t2))))) {
                swap(t1, t2);
            }
            vector<tuple<int, int, int>> t;
            for (int i = l; i <= r; ++i) {
                if (abs(get<0>(points[i]) - x) <= get<0>(t1)) {
                    t.emplace_back(points[i]);
                }
            }
            sort(t.begin(), t.end(), [](const tuple<int, int, int>& a, const tuple<int, int, int>& b) {
                return get<1>(a) < get<1>(b);
            });
            for (int i = 0; i < t.size(); ++i) {
                for (int j = i + 1; j < t.size(); ++j) {
                    if (get<1>(t[j]) - get<1>(t[i]) > get<0>(t1)) {
                        break;
                    }
                    int pi = min(get<2>(t[i]), get<2>(t[j]));
                    int pj = max(get<2>(t[i]), get<2>(t[j]));
                    int d = dist(get<0>(t[i]), get<1>(t[i]), get<0>(t[j]), get<1>(t[j]));
                    if (d < get<0>(t1) || (d == get<0>(t1) && (pi < get<1>(t1) || (pi == get<1>(t1) && pj < get<2>(t1))))) {
                        t1 = {d, pi, pj};
                    }
                }
            }
            return t1;
        };

        sort(points.begin(), points.end());
        auto [_, pi, pj] = dfs(0, points.size() - 1);
        return {pi, pj};
    }

    long long f(int x, int y) {
        return x * 100000LL + y;
    }

    int dist(int x1, int y1, int x2, int y2) {
        return abs(x1 - x2) + abs(y1 - y2);
    }
};
```

```go
func beautifulPair(nums1 []int, nums2 []int) []int {
	n := len(nums1)
	pl := map[[2]int][]int{}
	for i := 0; i < n; i++ {
		k := [2]int{nums1[i], nums2[i]}
		pl[k] = append(pl[k], i)
	}
	points := [][3]int{}
	for i := 0; i < n; i++ {
		k := [2]int{nums2[i], nums1[i]}
		if len(pl[k]) > 1 {
			return []int{pl[k][0], pl[k][1]}
		}
		points = append(points, [3]int{nums1[i], nums2[i], i})
	}
	sort.Slice(points, func(i, j int) bool { return points[i][0] < points[j][0] })

	var dfs func(l, r int) [3]int
	dfs = func(l, r int) [3]int {
		if l >= r {
			return [3]int{1 << 30, -1, -1}
		}
		m := (l + r) >> 1
		x := points[m][0]
		t1 := dfs(l, m)
		t2 := dfs(m+1, r)
		if t1[0] > t2[0] || (t1[0] == t2[0] && (t1[1] > t2[1] || (t1[1] == t2[1] && t1[2] > t2[2]))) {
			t1 = t2
		}
		t := [][3]int{}
		for i := l; i <= r; i++ {
			if abs(points[i][0]-x) <= t1[0] {
				t = append(t, points[i])
			}
		}
		sort.Slice(t, func(i, j int) bool { return t[i][1] < t[j][1] })
		for i := 0; i < len(t); i++ {
			for j := i + 1; j < len(t); j++ {
				if t[j][1]-t[i][1] > t1[0] {
					break
				}
				pi := min(t[i][2], t[j][2])
				pj := max(t[i][2], t[j][2])
				d := dist(t[i][0], t[i][1], t[j][0], t[j][1])
				if d < t1[0] || (d == t1[0] && (pi < t1[1] || (pi == t1[1] && pj < t1[2]))) {
					t1 = [3]int{d, pi, pj}
				}
			}
		}
		return t1
	}
	ans := dfs(0, n-1)
	return []int{ans[1], ans[2]}
}

func dist(x1, y1, x2, y2 int) int {
	return abs(x1-x2) + abs(y1-y2)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function beautifulPair(nums1: number[], nums2: number[]): number[] {
    const pl: Map<number, number[]> = new Map();
    const n = nums1.length;
    for (let i = 0; i < n; ++i) {
        const z = f(nums1[i], nums2[i]);
        if (!pl.has(z)) {
            pl.set(z, []);
        }
        pl.get(z)!.push(i);
    }
    const points: number[][] = [];
    for (let i = 0; i < n; ++i) {
        const z = f(nums1[i], nums2[i]);
        if (pl.get(z)!.length > 1) {
            return [i, pl.get(z)![1]];
        }
        points.push([nums1[i], nums2[i], i]);
    }
    points.sort((a, b) => a[0] - b[0]);

    const dfs = (l: number, r: number): number[] => {
        if (l >= r) {
            return [1 << 30, -1, -1];
        }
        const m = (l + r) >> 1;
        const x = points[m][0];
        let t1 = dfs(l, m);
        let t2 = dfs(m + 1, r);
        if (
            t1[0] > t2[0] ||
            (t1[0] == t2[0] && (t1[1] > t2[1] || (t1[1] == t2[1] && t1[2] > t2[2])))
        ) {
            t1 = t2;
        }
        const t: number[][] = [];
        for (let i = l; i <= r; ++i) {
            if (Math.abs(points[i][0] - x) <= t1[0]) {
                t.push(points[i]);
            }
        }
        t.sort((a, b) => a[1] - b[1]);
        for (let i = 0; i < t.length; ++i) {
            for (let j = i + 1; j < t.length; ++j) {
                if (t[j][1] - t[i][1] > t1[0]) {
                    break;
                }
                const pi = Math.min(t[i][2], t[j][2]);
                const pj = Math.max(t[i][2], t[j][2]);
                const d = dist(t[i][0], t[i][1], t[j][0], t[j][1]);
                if (d < t1[0] || (d == t1[0] && (pi < t1[1] || (pi == t1[1] && pj < t1[2])))) {
                    t1 = [d, pi, pj];
                }
            }
        }
        return t1;
    };
    return dfs(0, n - 1).slice(1);
}

function dist(x1: number, y1: number, x2: number, y2: number): number {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
}

function f(x: number, y: number): number {
    return x * 100000 + y;
}
```

<!-- tabs:end -->

<!-- end -->
