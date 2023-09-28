# [2251. 花期内花的数目](https://leetcode.cn/problems/number-of-flowers-in-full-bloom)

[English Version](/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>flowers</code>&nbsp;，其中&nbsp;<code>flowers[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;朵花的 <strong>花期</strong>&nbsp;从&nbsp;<code>start<sub>i</sub></code>&nbsp;到&nbsp;<code>end<sub>i</sub></code>&nbsp;（都 <strong>包含</strong>）。同时给你一个下标从 <strong>0</strong>&nbsp;开始大小为 <code>n</code>&nbsp;的整数数组&nbsp;<code>people</code> ，<code>people[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个人来看花的时间。</p>

<p>请你返回一个大小为 <code>n</code>&nbsp;的整数数组<em>&nbsp;</em><code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>是第&nbsp;<code>i</code>&nbsp;个人到达时在花期内花的&nbsp;<strong>数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/images/ex1new.jpg" style="width: 550px; height: 216px;" /></p>

<pre>
<b>输入：</b>flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
<b>输出：</b>[1,2,2,2]
<strong>解释：</strong>上图展示了每朵花的花期时间，和每个人的到达时间。
对每个人，我们返回他们到达时在花期内花的数目。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/images/ex2new.jpg" style="width: 450px; height: 195px;" /></p>

<pre>
<b>输入：</b>flowers = [[1,10],[3,3]], people = [3,3,2]
<b>输出：</b>[2,2,1]
<b>解释：</b>上图展示了每朵花的花期时间，和每个人的到达时间。
对每个人，我们返回他们到达时在花期内花的数目。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= flowers.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>flowers[i].length == 2</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= people.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= people[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 二分查找**

我们将花按照开始时间和结束时间分别排序，然后对于每个人，我们可以使用二分查找来找到他们到达时在花期内花的数目。就是说，找出在每个人到达时，已经开花的花的数目，减去在每个人到达时，已经凋谢的花的数目，即可得到答案。

时间复杂度 $O((m + n) \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 $flowers$ 和 $people$ 的长度。

**方法二：差分 + 排序 + 离线查询**

我们可以利用差分来维护每个时间点的花的数目。接下来，我们将 $people$ 按照到达时间从小到大排序，在每个人到达时，我们对差分数组进行前缀和运算，就可以得到答案。

时间复杂度 $O(m \times \log m + n \times \log n)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是数组 $flowers$ 和 $people$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fullBloomFlowers(
        self, flowers: List[List[int]], people: List[int]
    ) -> List[int]:
        start, end = sorted(a for a, _ in flowers), sorted(b for _, b in flowers)
        return [bisect_right(start, p) - bisect_left(end, p) for p in people]
```

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
