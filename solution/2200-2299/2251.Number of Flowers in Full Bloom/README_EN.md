---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/README_EN.md
rating: 2022
source: Weekly Contest 290 Q4
tags:
    - Array
    - Hash Table
    - Binary Search
    - Ordered Set
    - Prefix Sum
    - Sorting
---

# [2251. Number of Flowers in Full Bloom](https://leetcode.com/problems/number-of-flowers-in-full-bloom)

[中文文档](/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>flowers</code>, where <code>flowers[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> means the <code>i<sup>th</sup></code> flower will be in <strong>full bloom</strong> from <code>start<sub>i</sub></code> to <code>end<sub>i</sub></code> (<strong>inclusive</strong>). You are also given a <strong>0-indexed</strong> integer array <code>people</code> of size <code>n</code>, where <code>people[i]</code> is the time that the <code>i<sup>th</sup></code> person will arrive to see the flowers.</p>

<p>Return <em>an integer array </em><code>answer</code><em> of size </em><code>n</code><em>, where </em><code>answer[i]</code><em> is the <strong>number</strong> of flowers that are in full bloom when the </em><code>i<sup>th</sup></code><em> person arrives.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/images/ex1new.jpg" style="width: 550px; height: 216px;" />
<pre>
<strong>Input:</strong> flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
<strong>Output:</strong> [1,2,2,2]
<strong>Explanation: </strong>The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/images/ex2new.jpg" style="width: 450px; height: 195px;" />
<pre>
<strong>Input:</strong> flowers = [[1,10],[3,3]], people = [3,3,2]
<strong>Output:</strong> [2,2,1]
<strong>Explanation:</strong> The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= flowers.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>flowers[i].length == 2</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= people.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= people[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def fullBloomFlowers(
        self, flowers: List[List[int]], people: List[int]
    ) -> List[int]:
        start, end = sorted(a for a, _ in flowers), sorted(b for _, b in flowers)
        return [bisect_right(start, p) - bisect_left(end, p) for p in people]
```

```java
class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; ++i) {
            start[i] = flowers[i][0];
            end[i] = flowers[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int m = people.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            ans[i] = search(start, people[i] + 1) - search(end, people[i]);
        }
        return ans;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    vector<int> fullBloomFlowers(vector<vector<int>>& flowers, vector<int>& people) {
        int n = flowers.size();
        vector<int> start;
        vector<int> end;
        for (auto& f : flowers) {
            start.push_back(f[0]);
            end.push_back(f[1]);
        }
        sort(start.begin(), start.end());
        sort(end.begin(), end.end());
        vector<int> ans;
        for (auto& p : people) {
            auto r = upper_bound(start.begin(), start.end(), p) - start.begin();
            auto l = lower_bound(end.begin(), end.end(), p) - end.begin();
            ans.push_back(r - l);
        }
        return ans;
    }
};
```

```go
func fullBloomFlowers(flowers [][]int, people []int) (ans []int) {
	n := len(flowers)
	start := make([]int, n)
	end := make([]int, n)
	for i, f := range flowers {
		start[i] = f[0]
		end[i] = f[1]
	}
	sort.Ints(start)
	sort.Ints(end)
	for _, p := range people {
		r := sort.SearchInts(start, p+1)
		l := sort.SearchInts(end, p)
		ans = append(ans, r-l)
	}
	return
}
```

```ts
function fullBloomFlowers(flowers: number[][], people: number[]): number[] {
    const n = flowers.length;
    const start = new Array(n).fill(0);
    const end = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        start[i] = flowers[i][0];
        end[i] = flowers[i][1];
    }
    start.sort((a, b) => a - b);
    end.sort((a, b) => a - b);
    const ans: number[] = [];
    for (const p of people) {
        const r = search(start, p + 1);
        const l = search(end, p);
        ans.push(r - l);
    }
    return ans;
}

function search(nums: number[], x: number): number {
    let l = 0;
    let r = nums.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

```rust
use std::collections::BTreeMap;

impl Solution {
    #[allow(dead_code)]
    pub fn full_bloom_flowers(flowers: Vec<Vec<i32>>, people: Vec<i32>) -> Vec<i32> {
        let n = people.len();

        // First sort the people vector based on the first item
        let mut people: Vec<(usize, i32)> = people
            .into_iter()
            .enumerate()
            .map(|x| x)
            .collect();

        people.sort_by(|lhs, rhs| { lhs.1.cmp(&rhs.1) });

        // Initialize the difference vector
        let mut diff = BTreeMap::new();
        let mut ret = vec![0; n];

        for f in flowers {
            let (left, right) = (f[0], f[1]);
            diff.entry(left)
                .and_modify(|x| {
                    *x += 1;
                })
                .or_insert(1);

            diff.entry(right + 1)
                .and_modify(|x| {
                    *x -= 1;
                })
                .or_insert(-1);
        }

        let mut sum = 0;
        let mut i = 0;
        for (k, v) in diff {
            while i < n && people[i].1 < k {
                ret[people[i].0] += sum;
                i += 1;
            }
            sum += v;
        }

        ret
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def fullBloomFlowers(
        self, flowers: List[List[int]], people: List[int]
    ) -> List[int]:
        d = defaultdict(int)
        for st, ed in flowers:
            d[st] += 1
            d[ed + 1] -= 1
        ts = sorted(d)
        s = i = 0
        m = len(people)
        ans = [0] * m
        for t, j in sorted(zip(people, range(m))):
            while i < len(ts) and ts[i] <= t:
                s += d[ts[i]]
                i += 1
            ans[j] = s
        return ans
```

```java
class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (int[] f : flowers) {
            d.merge(f[0], 1, Integer::sum);
            d.merge(f[1] + 1, -1, Integer::sum);
        }
        int s = 0;
        int m = people.length;
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, Comparator.comparingInt(i -> people[i]));
        int[] ans = new int[m];
        for (int i : idx) {
            int t = people[i];
            while (!d.isEmpty() && d.firstKey() <= t) {
                s += d.pollFirstEntry().getValue();
            }
            ans[i] = s;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> fullBloomFlowers(vector<vector<int>>& flowers, vector<int>& people) {
        map<int, int> d;
        for (auto& f : flowers) {
            d[f[0]]++;
            d[f[1] + 1]--;
        }
        int m = people.size();
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return people[i] < people[j];
        });
        vector<int> ans(m);
        int s = 0;
        for (int i : idx) {
            int t = people[i];
            while (!d.empty() && d.begin()->first <= t) {
                s += d.begin()->second;
                d.erase(d.begin());
            }
            ans[i] = s;
        }
        return ans;
    }
};
```

```go
func fullBloomFlowers(flowers [][]int, people []int) []int {
	d := map[int]int{}
	for _, f := range flowers {
		d[f[0]]++
		d[f[1]+1]--
	}
	ts := []int{}
	for t := range d {
		ts = append(ts, t)
	}
	sort.Ints(ts)
	m := len(people)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return people[idx[i]] < people[idx[j]] })
	ans := make([]int, m)
	s, i := 0, 0
	for _, j := range idx {
		t := people[j]
		for i < len(ts) && ts[i] <= t {
			s += d[ts[i]]
			i++
		}
		ans[j] = s
	}
	return ans
}
```

```ts
function fullBloomFlowers(flowers: number[][], people: number[]): number[] {
    const d: Map<number, number> = new Map();
    for (const [st, ed] of flowers) {
        d.set(st, (d.get(st) || 0) + 1);
        d.set(ed + 1, (d.get(ed + 1) || 0) - 1);
    }
    const ts = [...d.keys()].sort((a, b) => a - b);
    let s = 0;
    let i = 0;
    const m = people.length;
    const idx: number[] = [...Array(m)].map((_, i) => i).sort((a, b) => people[a] - people[b]);
    const ans = Array(m).fill(0);
    for (const j of idx) {
        const t = people[j];
        while (i < ts.length && ts[i] <= t) {
            s += d.get(ts[i])!;
            ++i;
        }
        ans[j] = s;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
