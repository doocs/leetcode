---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/README.md
tags:
    - 贪心
    - 数组
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [2015. 每段建筑物的平均高度 🔒](https://leetcode.cn/problems/average-height-of-buildings-in-each-segment)

[English Version](/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一条完全笔直的街道由一条数字线表示。街道上有建筑物，由二维整数阵列&nbsp;<code>buildings</code> 表示，其中 <code>buildings[i] = [start<sub>i</sub>, end<sub>i</sub>, height<sub>i</sub>]</code>。这意味着在 <strong>半封闭的位置</strong><code>[starti，endi)</code>&nbsp;有一座高度为&nbsp;<code>height<sub>i</sub></code>&nbsp;的建筑。<br />
你想用 <strong>最少</strong> 数量的非重叠 <strong>部分</strong> 来 <strong>描述</strong> 街道上建筑物的高度。街道可以用2D整数数组&nbsp;<code>street</code>&nbsp;来表示，其中&nbsp;<code>street[j] = [left<sub>j</sub>, right<sub>j</sub>, average<sub>j</sub>]</code>&nbsp;描述了道路的 <strong>半封闭区域</strong>&nbsp;<code>[left<sub>j</sub>, right<sub>j</sub>)</code>&nbsp;，该段中建筑物的 <strong>平均</strong> 高度为&nbsp;<code>average<sub>j</sub></code> 。</p>

<ul>
	<li>例如，如果&nbsp;<code>buildings = [[1,5,2],[3,10,4]]</code>&nbsp;，&nbsp;<code>street = [[1,3,2],[3,5,3],[5,10,4]]</code>&nbsp;可以表示街道，因为：

    <ul>
    	<li>从 1 到 3 ，只有第一栋建筑的平均高度为 <code>2 / 1 = 2</code> 。</li>
    	<li>从 3 到 5 ，第一和第二栋建筑的平均高度均为&nbsp;<code>（2+4） / 2 = 3 </code>。</li>
    	<li>从 5 到 10 ，只有第二栋建筑的平均高度为 <code>4 / 1 = 4</code> 。</li>
    </ul>
    </li>

</ul>

<p>给定&nbsp;<code>buildings</code> ，返回如上所述的二维整数矩阵<em>&nbsp;</em><code>street</code><em>&nbsp;</em>（ <strong>不包括</strong> 街道上没有建筑物的任何区域）。您可以按 <strong>任何顺序</strong> 返回数组。<br />
<code>n</code> 个元素的 <strong>平均值</strong> 是 <code>n</code> 个元素除以&nbsp;<code>n</code> 的 <strong>总和</strong> （<strong>整数除法</strong>）。<br />
<strong>半闭合段</strong>&nbsp;<code>[a, b)</code>&nbsp;是点&nbsp;<code>a</code>&nbsp;和 <code>b</code> 之间的数字线的截面，<strong>包括</strong> 点 <code>a</code> ，<strong>不包括&nbsp;</strong>点 <code>b</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2015.Average%20Height%20of%20Buildings%20in%20Each%20Segment/images/image-20210921224001-2.png" />
<pre>
<strong>输入:</strong> buildings = [[1,4,2],[3,9,4]]
<strong>输出:</strong> [[1,3,2],[3,4,3],[4,9,4]]
<strong>解释:</strong>
从 1 到 3 ，只有第一栋建筑的平均高度为 2 / 1 = 2。
从 3 到 4 ，第一和第二栋建筑的平均高度均为（2+4）/ 2 = 3。
从 4 到 9 ，只有第二栋建筑的平均高度为 4 / 1 = 4。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> buildings = [[1,3,2],[2,5,3],[2,8,3]]
<strong>输出:</strong> [[1,3,2],[3,8,3]]
<strong>解释:</strong>
从 1 到 2 ，只有第一栋建筑的平均高度为 2 / 1 = 2。
从 2 到 3 ，这三座建筑的平均高度均为 （2+3+3） / 3 = 2。
从 3 到 5 ，第二和第三栋楼都在那里，平均高度为 （3+3） / 2 = 3。
从 5 到 8 ，只有最后一栋建筑的平均高度为 3 / 1 = 3。
从 1 到 3 的平均高度是相同的，所以我们可以把它们分成一个部分。
从 3 到 8 的平均高度是相同的，所以我们可以把它们分成一个部分。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> buildings = [[1,2,1],[5,6,1]]
<strong>输出:</strong> [[1,2,1],[5,6,1]]
<strong>解释:</strong>
从 1 到 2 ，只有第一栋建筑的平均高度为 1 / 1 = 1。
从 2 到 5 ，没有建筑物，因此不包括在输出中。
从 5 到 6 ，只有第二栋建筑的平均高度为 1 / 1 = 1。
我们无法将这些部分组合在一起，因为没有建筑的空白空间将这些部分隔开。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= buildings.length &lt;= 10<sup>5</sup></code></li>
	<li><code>buildings[i].length == 3</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= height<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分思想 + 哈希表

我们可以利用差分思想，用一个哈希表 $\textit{cnt}$ 记录每个位置的建筑物数量变化，用另一个哈希表 $\textit{d}$ 记录每个位置的高度变化。

接下来，我们对哈希表 $\textit{d}$ 按照键值进行排序，用一个变量 $\textit{s}$ 记录当前位置的高度和，用一个变量 $\textit{m}$ 记录当前位置的建筑物数量。

然后遍历哈希表 $\textit{d}$，对于每个位置，如果 $\textit{m}$ 不为 0，说明此前有建筑物，我们计算出平均高度，如果当前位置的建筑物与上个建筑物的平均高度相同，则合并，否则加入结果集。

最后返回结果集即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为建筑物数量。

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
