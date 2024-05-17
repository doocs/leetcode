---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1847.Closest%20Room/README.md
rating: 2081
source: 第 51 场双周赛 Q4
tags:
    - 数组
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [1847. 最近的房间](https://leetcode.cn/problems/closest-room)

[English Version](/solution/1800-1899/1847.Closest%20Room/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个酒店里有 <code>n</code> 个房间，这些房间用二维整数数组 <code>rooms</code> 表示，其中 <code>rooms[i] = [roomId<sub>i</sub>, size<sub>i</sub>]</code> 表示有一个房间号为 <code>roomId<sub>i</sub></code> 的房间且它的面积为 <code>size<sub>i</sub></code> 。每一个房间号 <code>roomId<sub>i</sub></code> 保证是 <strong>独一无二</strong> 的。</p>

<p>同时给你 <code>k</code> 个查询，用二维数组 <code>queries</code> 表示，其中 <code>queries[j] = [preferred<sub>j</sub>, minSize<sub>j</sub>]</code> 。第 <code>j</code> 个查询的答案是满足如下条件的房间 <code>id</code> ：</p>

<ul>
	<li>房间的面积 <b>至少</b> 为 <code>minSize<sub>j</sub></code> ，且</li>
	<li><code>abs(id - preferred<sub>j</sub>)</code> 的值 <strong>最小</strong> ，其中 <code>abs(x)</code> 是 <code>x</code> 的绝对值。</li>
</ul>

<p>如果差的绝对值有 <strong>相等</strong> 的，选择 <strong>最小</strong> 的 <code>id</code> 。如果 <strong>没有满足条件的房间</strong> ，答案为 <code>-1</code> 。</p>

<p>请你返回长度为 <code>k</code> 的数组 <code>answer</code> ，其中<em> </em><code>answer[j]</code> 为第 <code>j</code> 个查询的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
<b>输出：</b>[3,-1,3]
<strong>解释：</strong>查询的答案如下：
查询 [3,1] ：房间 3 的面积为 2 ，大于等于 1 ，且号码是最接近 3 的，为 abs(3 - 3) = 0 ，所以答案为 3 。
查询 [3,3] ：没有房间的面积至少为 3 ，所以答案为 -1 。
查询 [5,2] ：房间 3 的面积为 2 ，大于等于 2 ，且号码是最接近 5 的，为 abs(3 - 5) = 2 ，所以答案为 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
<b>输出：</b>[2,1,3]
<strong>解释：</strong>查询的答案如下：
查询 [2,3] ：房间 2 的面积为 3 ，大于等于 3 ，且号码是最接近的，为 abs(2 - 2) = 0 ，所以答案为 2 。
查询 [2,4] ：房间 1 和 3 的面积都至少为 4 ，答案为 1 因为它房间编号更小。
查询 [2,5] ：房间 3 是唯一面积大于等于 5 的，所以答案为 3 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == rooms.length</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>k == queries.length</code></li>
	<li><code>1 <= k <= 10<sup>4</sup></code></li>
	<li><code>1 <= roomId<sub>i</sub>, preferred<sub>j</sub> <= 10<sup>7</sup></code></li>
	<li><code>1 <= size<sub>i</sub>, minSize<sub>j</sub> <= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：离线查询 + 有序集合 + 二分查找

我们注意到，查询的顺序并不影响答案，而且题目中涉及到房间面积的大小关系，因此，我们可以将查询按照最小面积从小到大排序，这样我们就可以从小到大依次处理每个查询。另外，我们也将房间按照面积从小到大排序。

接下来，我们创建一个有序列表，将所有房间的编号加入有序列表中。

然后，我们从小到大依次处理每个查询。对于每个查询，我们首先将所有面积小于等于当前查询的最小面积的房间从有序列表中移除。然后，我们在剩余的房间中，使用二分查找找到最接近当前查询的房间编号。如果不存在这样的房间，那么我们就返回 $-1$。

时间复杂度 $O(n \times \log n + k \times \log k)$，空间复杂度 $O(n + k)$。其中 $n$ 和 $k$ 分别是房间数和查询数。

<!-- tabs:start -->

```python
from sortedcontainers import SortedList


class Solution:
    def closestRoom(
        self, rooms: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        rooms.sort(key=lambda x: x[1])
        k = len(queries)
        idx = sorted(range(k), key=lambda i: queries[i][1])
        ans = [-1] * k
        i, n = 0, len(rooms)
        sl = SortedList(x[0] for x in rooms)
        for j in idx:
            prefer, minSize = queries[j]
            while i < n and rooms[i][1] < minSize:
                sl.remove(rooms[i][0])
                i += 1
            if i == n:
                break
            p = sl.bisect_left(prefer)
            if p < len(sl):
                ans[j] = sl[p]
            if p and (ans[j] == -1 or ans[j] - prefer >= prefer - sl[p - 1]):
                ans[j] = sl[p - 1]
        return ans
```

```java
class Solution {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length;
        int k = queries.length;
        Arrays.sort(rooms, (a, b) -> a[1] - b[1]);
        Integer[] idx = new Integer[k];
        for (int i = 0; i < k; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[i][1] - queries[j][1]);
        int i = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int[] room : rooms) {
            tm.merge(room[0], 1, Integer::sum);
        }
        int[] ans = new int[k];
        Arrays.fill(ans, -1);
        for (int j : idx) {
            int prefer = queries[j][0], minSize = queries[j][1];
            while (i < n && rooms[i][1] < minSize) {
                if (tm.merge(rooms[i][0], -1, Integer::sum) == 0) {
                    tm.remove(rooms[i][0]);
                }
                ++i;
            }
            if (i == n) {
                break;
            }
            Integer p = tm.ceilingKey(prefer);
            if (p != null) {
                ans[j] = p;
            }
            p = tm.floorKey(prefer);
            if (p != null && (ans[j] == -1 || ans[j] - prefer >= prefer - p)) {
                ans[j] = p;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> closestRoom(vector<vector<int>>& rooms, vector<vector<int>>& queries) {
        int n = rooms.size();
        int k = queries.size();
        sort(rooms.begin(), rooms.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        vector<int> idx(k);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return queries[i][1] < queries[j][1];
        });
        vector<int> ans(k, -1);
        int i = 0;
        multiset<int> s;
        for (auto& room : rooms) {
            s.insert(room[0]);
        }
        for (int j : idx) {
            int prefer = queries[j][0], minSize = queries[j][1];
            while (i < n && rooms[i][1] < minSize) {
                s.erase(s.find(rooms[i][0]));
                ++i;
            }
            if (i == n) {
                break;
            }
            auto it = s.lower_bound(prefer);
            if (it != s.end()) {
                ans[j] = *it;
            }
            if (it != s.begin()) {
                --it;
                if (ans[j] == -1 || abs(*it - prefer) <= abs(ans[j] - prefer)) {
                    ans[j] = *it;
                }
            }
        }
        return ans;
    }
};
```

```go
func closestRoom(rooms [][]int, queries [][]int) []int {
	n, k := len(rooms), len(queries)
	sort.Slice(rooms, func(i, j int) bool { return rooms[i][1] < rooms[j][1] })
	idx := make([]int, k)
	ans := make([]int, k)
	for i := range idx {
		idx[i] = i
		ans[i] = -1
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[i]][1] < queries[idx[j]][1] })
	rbt := redblacktree.NewWithIntComparator()
	merge := func(rbt *redblacktree.Tree, key, value int) {
		if v, ok := rbt.Get(key); ok {
			nxt := v.(int) + value
			if nxt == 0 {
				rbt.Remove(key)
			} else {
				rbt.Put(key, nxt)
			}
		} else {
			rbt.Put(key, value)
		}
	}
	for _, room := range rooms {
		merge(rbt, room[0], 1)
	}
	i := 0

	for _, j := range idx {
		prefer, minSize := queries[j][0], queries[j][1]
		for i < n && rooms[i][1] < minSize {
			merge(rbt, rooms[i][0], -1)
			i++
		}
		if i == n {
			break
		}
		c, _ := rbt.Ceiling(prefer)
		f, _ := rbt.Floor(prefer)
		if c != nil {
			ans[j] = c.Key.(int)
		}
		if f != nil && (ans[j] == -1 || ans[j]-prefer >= prefer-f.Key.(int)) {
			ans[j] = f.Key.(int)
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
