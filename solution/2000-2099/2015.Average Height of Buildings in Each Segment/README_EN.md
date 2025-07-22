---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/README_EN.md
tags:
    - Greedy
    - Array
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2015. Average Height of Buildings in Each Segment 🔒](https://leetcode.com/problems/average-height-of-buildings-in-each-segment)

[中文文档](/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/README.md)

## Description

<!-- description:start -->

<p>A perfectly straight street is represented by a number line. The street has building(s) on it and is represented by a 2D integer array <code>buildings</code>, where <code>buildings[i] = [start<sub>i</sub>, end<sub>i</sub>, height<sub>i</sub>]</code>. This means that there is a building with <code>height<sub>i</sub></code> in the <strong>half-closed segment</strong> <code>[start<sub>i</sub>, end<sub>i</sub>)</code>.</p>

<p>You want to <strong>describe</strong> the heights of the buildings on the street with the <strong>minimum</strong> number of non-overlapping <strong>segments</strong>. The street can be represented by the 2D integer array <code>street</code> where <code>street[j] = [left<sub>j</sub>, right<sub>j</sub>, average<sub>j</sub>]</code> describes a <strong>half-closed segment</strong> <code>[left<sub>j</sub>, right<sub>j</sub>)</code> of the road where the <strong>average</strong> heights of the buildings in the<strong> segment</strong> is <code>average<sub>j</sub></code>.</p>

<ul>
	<li>For example, if <code>buildings = [[1,5,2],[3,10,4]],</code> the street could be represented by <code>street = [[1,3,2],[3,5,3],[5,10,4]]</code> because:

    <ul>
    	<li>From 1 to 3, there is only the first building with an average height of <code>2 / 1 = 2</code>.</li>
    	<li>From 3 to 5, both the first and the second building are there with an average height of <code>(2+4) / 2 = 3</code>.</li>
    	<li>From 5 to 10, there is only the second building with an average height of <code>4 / 1 = 4</code>.</li>
    </ul>
    </li>

</ul>

<p>Given <code>buildings</code>, return <em>the 2D integer array </em><code>street</code><em> as described above (<strong>excluding</strong> any areas of the street where there are no buldings). You may return the array in <strong>any order</strong></em>.</p>

<p>The <strong>average</strong> of <code>n</code> elements is the <strong>sum</strong> of the <code>n</code> elements divided (<strong>integer division</strong>) by <code>n</code>.</p>

<p>A <strong>half-closed segment</strong> <code>[a, b)</code> is the section of the number line between points <code>a</code> and <code>b</code> <strong>including</strong> point <code>a</code> and <strong>not including</strong> point <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/images/image-20210921224001-2.png" style="width: 500px; height: 349px;" />
<pre>
<strong>Input:</strong> buildings = [[1,4,2],[3,9,4]]
<strong>Output:</strong> [[1,3,2],[3,4,3],[4,9,4]]
<strong>Explanation:</strong>
From 1 to 3, there is only the first building with an average height of 2 / 1 = 2.
From 3 to 4, both the first and the second building are there with an average height of (2+4) / 2 = 3.
From 4 to 9, there is only the second building with an average height of 4 / 1 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> buildings = [[1,3,2],[2,5,3],[2,8,3]]
<strong>Output:</strong> [[1,3,2],[3,8,3]]
<strong>Explanation:</strong>
From 1 to 2, there is only the first building with an average height of 2 / 1 = 2.
From 2 to 3, all three buildings are there with an average height of (2+3+3) / 3 = 2.
From 3 to 5, both the second and the third building are there with an average height of (3+3) / 2 = 3.
From 5 to 8, there is only the last building with an average height of 3 / 1 = 3.
The average height from 1 to 3 is the same so we can group them into one segment.
The average height from 3 to 8 is the same so we can group them into one segment.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> buildings = [[1,2,1],[5,6,1]]
<strong>Output:</strong> [[1,2,1],[5,6,1]]
<strong>Explanation:</strong>
From 1 to 2, there is only the first building with an average height of 1 / 1 = 1.
From 2 to 5, there are no buildings, so it is not included in the output.
From 5 to 6, there is only the second building with an average height of 1 / 1 = 1.
We cannot group the segments together because an empty space with no buildings seperates the segments.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= buildings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>buildings[i].length == 3</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= height<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array + Hash Table

We can use the difference array concept, utilizing a hash table $\textit{cnt}$ to record the change in the number of buildings at each position, and another hash table $\textit{d}$ to record the change in height at each position.

Next, we sort the hash table $\textit{d}$ by its keys, use a variable $\textit{s}$ to record the current total height, and a variable $\textit{m}$ to record the current number of buildings.

Then, we traverse the hash table $\textit{d}$. For each position, if $\textit{m}$ is not 0, it means there are buildings at the previous positions. We calculate the average height. If the average height of the buildings at the current position is the same as that of the previous buildings, we merge them; otherwise, we add the current position to the result set.

Finally, we return the result set.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of buildings.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def averageHeightOfBuildings(self, buildings: List[List[int]]) -> List[List[int]]:
        cnt = defaultdict(int)
        d = defaultdict(int)
        for start, end, height in buildings:
            cnt[start] += 1
            cnt[end] -= 1
            d[start] += height
            d[end] -= height
        s = m = 0
        last = -1
        ans = []
        for k, v in sorted(d.items()):
            if m:
                avg = s // m
                if ans and ans[-1][2] == avg and ans[-1][1] == last:
                    ans[-1][1] = k
                else:
                    ans.append([last, k, avg])
            s += v
            m += cnt[k]
            last = k
        return ans
```

#### Java

```java
class Solution {
    public int[][] averageHeightOfBuildings(int[][] buildings) {
        Map<Integer, Integer> cnt = new HashMap<>();
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (var e : buildings) {
            int start = e[0], end = e[1], height = e[2];
            cnt.merge(start, 1, Integer::sum);
            cnt.merge(end, -1, Integer::sum);
            d.merge(start, height, Integer::sum);
            d.merge(end, -height, Integer::sum);
        }
        int s = 0, m = 0;
        int last = -1;
        List<int[]> ans = new ArrayList<>();
        for (var e : d.entrySet()) {
            int k = e.getKey(), v = e.getValue();
            if (m > 0) {
                int avg = s / m;
                if (!ans.isEmpty() && ans.get(ans.size() - 1)[2] == avg
                    && ans.get(ans.size() - 1)[1] == last) {
                    ans.get(ans.size() - 1)[1] = k;
                } else {
                    ans.add(new int[] {last, k, avg});
                }
            }
            s += v;
            m += cnt.get(k);
            last = k;
        }
        return ans.toArray(new int[0][]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> averageHeightOfBuildings(vector<vector<int>>& buildings) {
        unordered_map<int, int> cnt;
        map<int, int> d;

        for (const auto& e : buildings) {
            int start = e[0], end = e[1], height = e[2];
            cnt[start]++;
            cnt[end]--;
            d[start] += height;
            d[end] -= height;
        }

        int s = 0, m = 0;
        int last = -1;
        vector<vector<int>> ans;

        for (const auto& [k, v] : d) {
            if (m > 0) {
                int avg = s / m;
                if (!ans.empty() && ans.back()[2] == avg && ans.back()[1] == last) {
                    ans.back()[1] = k;
                } else {
                    ans.push_back({last, k, avg});
                }
            }
            s += v;
            m += cnt[k];
            last = k;
        }

        return ans;
    }
};
```

#### Go

```go
func averageHeightOfBuildings(buildings [][]int) [][]int {
	cnt := make(map[int]int)
	d := make(map[int]int)

	for _, e := range buildings {
		start, end, height := e[0], e[1], e[2]
		cnt[start]++
		cnt[end]--
		d[start] += height
		d[end] -= height
	}

	s, m := 0, 0
	last := -1
	var ans [][]int

	keys := make([]int, 0, len(d))
	for k := range d {
		keys = append(keys, k)
	}
	sort.Ints(keys)

	for _, k := range keys {
		v := d[k]
		if m > 0 {
			avg := s / m
			if len(ans) > 0 && ans[len(ans)-1][2] == avg && ans[len(ans)-1][1] == last {
				ans[len(ans)-1][1] = k
			} else {
				ans = append(ans, []int{last, k, avg})
			}
		}
		s += v
		m += cnt[k]
		last = k
	}

	return ans
}
```

#### TypeScript

```ts
function averageHeightOfBuildings(buildings: number[][]): number[][] {
    const cnt = new Map<number, number>();
    const d = new Map<number, number>();
    for (const [start, end, height] of buildings) {
        cnt.set(start, (cnt.get(start) || 0) + 1);
        cnt.set(end, (cnt.get(end) || 0) - 1);
        d.set(start, (d.get(start) || 0) + height);
        d.set(end, (d.get(end) || 0) - height);
    }
    let [s, m] = [0, 0];
    let last = -1;
    const ans: number[][] = [];
    const sortedKeys = Array.from(d.keys()).sort((a, b) => a - b);
    for (const k of sortedKeys) {
        const v = d.get(k)!;
        if (m > 0) {
            const avg = Math.floor(s / m);
            if (ans.length > 0 && ans.at(-1)![2] === avg && ans.at(-1)![1] === last) {
                ans[ans.length - 1][1] = k;
            } else {
                ans.push([last, k, avg]);
            }
        }
        s += v;
        m += cnt.get(k)!;
        last = k;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
