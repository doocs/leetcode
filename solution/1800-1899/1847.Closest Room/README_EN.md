# [1847. Closest Room](https://leetcode.com/problems/closest-room)

[中文文档](/solution/1800-1899/1847.Closest%20Room/README.md)

<!-- tags:Array,Binary Search,Sorting -->

<!-- difficulty:Hard -->

## Description

<p>There is a hotel with <code>n</code> rooms. The rooms are represented by a 2D integer array <code>rooms</code> where <code>rooms[i] = [roomId<sub>i</sub>, size<sub>i</sub>]</code> denotes that there is a room with room number <code>roomId<sub>i</sub></code> and size equal to <code>size<sub>i</sub></code>. Each <code>roomId<sub>i</sub></code> is guaranteed to be <strong>unique</strong>.</p>

<p>You are also given <code>k</code> queries in a 2D array <code>queries</code> where <code>queries[j] = [preferred<sub>j</sub>, minSize<sub>j</sub>]</code>. The answer to the <code>j<sup>th</sup></code> query is the room number <code>id</code> of a room such that:</p>

<ul>
	<li>The room has a size of <strong>at least</strong> <code>minSize<sub>j</sub></code>, and</li>
	<li><code>abs(id - preferred<sub>j</sub>)</code> is <strong>minimized</strong>, where <code>abs(x)</code> is the absolute value of <code>x</code>.</li>
</ul>

<p>If there is a <strong>tie</strong> in the absolute difference, then use the room with the <strong>smallest</strong> such <code>id</code>. If there is <strong>no such room</strong>, the answer is <code>-1</code>.</p>

<p>Return <em>an array </em><code>answer</code><em> of length </em><code>k</code><em> where </em><code>answer[j]</code><em> contains the answer to the </em><code>j<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
<strong>Output:</strong> [3,-1,3]
<strong>Explanation: </strong>The answers to the queries are as follows:
Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size of 2 is at least 1. The answer is 3.
Query = [3,3]: There are no rooms with a size of at least 3, so the answer is -1.
Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size of 2 is at least 2. The answer is 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
<strong>Output:</strong> [2,1,3]
<strong>Explanation: </strong>The answers to the queries are as follows:
Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size of 3 is at least 3. The answer is 2.
Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The answer is 1 since it is smaller.
Query = [2,5]: Room number 3 is the only room with a size of at least 5. The answer is 3.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == rooms.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>k == queries.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= roomId<sub>i</sub>, preferred<sub>j</sub> &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= size<sub>i</sub>, minSize<sub>j</sub> &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

### Solution 1: Offline Query + Ordered Set + Binary Search

We notice that the order of queries does not affect the answer, and the problem involves the size relationship of room areas. Therefore, we can sort the queries in ascending order of minimum area, so that we can process each query from small to large. Also, we sort the rooms in ascending order of area.

Next, we create an ordered list and add all room numbers to the ordered list.

Then, we process each query from small to large. For each query, we first remove all rooms with an area less than or equal to the current query's minimum area from the ordered list. Then, in the remaining rooms, we use binary search to find the room number closest to the current query. If there is no such room, we return $-1$.

The time complexity is $O(n \times \log n + k \times \log k)$, and the space complexity is $O(n + k)$. Where $n$ and $k$ are the number of rooms and queries, respectively.

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

<!-- end -->
