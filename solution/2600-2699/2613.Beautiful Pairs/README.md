# [2613. 美数对 🔒](https://leetcode.cn/problems/beautiful-pairs)

[English Version](/solution/2600-2699/2613.Beautiful%20Pairs/README_EN.md)

<!-- tags:几何,数组,数学,分治,有序集合,排序 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个长度相同的 <strong>下标从 0 开始</strong> 的整数数组 <code>nums1</code> 和 <code>nums2</code>&nbsp;，如果 <code>|nums1[i] - nums1[j]| + |nums2[i] - nums2[j]|</code> 在所有可能的下标对中是最小的，其中 <code>i &lt; j</code> ，则称下标对 <code>(i,j)</code> 为 <strong>美</strong> 数对，</p>

<p>返回美数对。如果有多个美数对，则返回字典序最小的美数对。</p>

<p>注意：</p>

<ul>
	<li><code>|x|</code> 表示 <code>x</code> 的绝对值。</li>
	<li>一对索引 <code>(i1, j1)</code> 在字典序意义下小于 <code>(i2, j2)</code> ，当且仅当 <code>i1 &lt; i2</code> 或 <code>i1 == i2</code> 且 <code>j1 &lt; j2</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,3,2,4], nums2 = [2,3,1,2,3]
<b>输出：</b>[0,3]
<b>解释：</b>取下标为 0 和下标为 3 的数对，计算出 |nums1[0]-nums1[3]| + |nums2[0]-nums2[3]| 的值为 1 ，这是我们能够得到的最小值。
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,4,3,2,5], nums2 = [1,4,2,3,5,1]
<b>输出：</b>[1,4]
<b>解释：</b>取下标为 1 和下标为 4 的数对，计算出 |nums1[1]-nums1[4]| + |nums2[1]-nums2[4]| 的值为 1，这是我们可以达到的最小值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1.length == nums2.length</code></li>
	<li><code>0 &lt;= nums1<sub>i</sub><sub>&nbsp;</sub>&lt;= nums1.length</code></li>
	<li><code>0 &lt;= nums2<sub>i</sub>&nbsp;&lt;= nums2.length</code></li>
</ul>

## 解法

### 方法一：排序 + 分治

本题相当于找出平面中两个点，使得它们的曼哈顿距离最小，如果有多个点满足条件，则返回下标字典序最小的点。

我们先处理重复点的情况，找出每个点对应的下标列表，如果某个点的下标列表长度大于 $1$，那么它的前两个下标可作为候选答案，我们找出最小的下标对即可。

如果没有重复点，我们将所有点按照 $x$ 坐标排序，然后分治求解。

对于每个区间 $[l, r]$，我们先求出 $x$ 坐标的中位数 $m$，然后递归求解左右两个区间，分别得到 $d_1, (pi_1, pj_1)$ 和 $d_2, (pi_2, pj_2)$，其中 $d_1$ 和 $d_2$ 分别表示左右两个区间的最小曼哈顿距离，而 $(pi_1, pj_1)$ 和 $(pi_2, pj_2)$ 分别表示左右两个区间的最小曼哈顿距离的两个点的下标。我们取 $d_1$ 和 $d_2$ 中较小的一个，如果 $d_1 = d_2$，则取下标字典序较小的一个，将其作为当前区间的最小曼哈顿距离，同时将对应的两个点的下标作为答案。

以上考虑的是两个点位于同一侧的情况，如果两个点位于不同侧，那么我们以中间点，即下标为 $m = \lfloor (l + r) / 2 \rfloor$ 的点为标准，划分出一个新的区域，区域的范围为中间点向左右两侧分别扩展 $d_1$ 的范围。然后我们将这些范围内的点按照 $y$ 坐标排序，然后遍历排序后的每个点对，如果两个点的 $y$ 坐标之差大于当前的最小曼哈顿距离，那么后面的点对都不用考虑了，因为它们的 $y$ 坐标之差更大，所以曼哈顿距离更大，不会比当前的最小曼哈顿距离更小。否则，我们更新最小曼哈顿距离，同时更新答案。

最后，我们返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

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
