# [2747. 统计没有收到请求的服务器数目](https://leetcode.cn/problems/count-zero-request-servers)

[English Version](/solution/2700-2799/2747.Count%20Zero%20Request%20Servers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，表示服务器的总数目，再给你一个下标从 <strong>0</strong>&nbsp;开始的 <strong>二维</strong>&nbsp;整数数组&nbsp;<code>logs</code>&nbsp;，其中&nbsp;<code>logs[i] = [server_id, time]</code>&nbsp;表示 id 为&nbsp;<code>server_id</code>&nbsp;的服务器在&nbsp;<code>time</code>&nbsp;时收到了一个请求。</p>

<p>同时给你一个整数&nbsp;<code>x</code>&nbsp;和一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>queries</code>&nbsp; 。</p>

<p>请你返回一个长度等于&nbsp;<code>queries.length</code>&nbsp;的数组&nbsp;<code>arr</code>&nbsp;，其中&nbsp;<code>arr[i]</code>&nbsp;表示在时间区间&nbsp;<code>[queries[i] - x, queries[i]]</code>&nbsp;内没有收到请求的服务器数目。</p>

<p>注意时间区间是个闭区间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]
<b>输出：</b>[1,2]
<b>解释：</b>
对于 queries[0]：id 为 1 和 2 的服务器在区间 [5, 10] 内收到了请求，所以只有服务器 3 没有收到请求。
对于 queries[1]：id 为 2 的服务器在区间 [6,11] 内收到了请求，所以 id 为 1 和 3 的服务器在这个时间段内没有收到请求。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 3, logs = [[2,4],[2,1],[1,2],[3,1]], x = 2, queries = [3,4]
<b>输出：</b>[0,1]
<b>解释：</b>
对于 queries[0]：区间 [1, 3] 内所有服务器都收到了请求。
对于 queries[1]：只有 id 为 3 的服务器在区间 [2,4] 内没有收到请求。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= logs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>logs[i].length == 2</code></li>
	<li><code>1 &lt;= logs[i][0] &lt;= n</code></li>
	<li><code>1 &lt;= logs[i][1] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>5</sup></code></li>
	<li><code>x &lt;&nbsp;queries[i]&nbsp;&lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：离线查询 + 排序 + 双指针**

我们可以将所有的查询按照时间从小到大排序，然后按照时间顺序依次处理每个查询。

对于每个查询 $q = (r, i)$，其窗口左边界为 $l = r - x$，我们需要统计在窗口 $[l, r]$ 内有多少个服务器收到了请求。我们用双指针 $j$ 和 $k$ 分别维护窗口的左右边界，初始时 $j = k = 0$。每一次，如果 $k$ 指向的日志的时间小于等于 $r$，我们就将其加入到窗口中，然后将 $k$ 向右移动一位。如果 $j$ 指向的日志的时间小于 $l$，我们就将其从窗口中移除，然后将 $j$ 向右移动一位。在移动的过程中，我们需要统计窗口中有多少个不同的服务器，这可以使用哈希表来实现。移动结束后，当前时间区间中没有收到请求的服务器数目就是 $n$ 减去哈希表中不同的服务器数目。

时间复杂度 $O(l \times \log l + m \times \log m + n)$，空间复杂度 $O(l + m)$。其中 $l$ 和 $n$ 分别是数组 $logs$ 的长度和服务器的数量，而 $m$ 是数组 $queries$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countServers(
        self, n: int, logs: List[List[int]], x: int, queries: List[int]
    ) -> List[int]:
        cnt = Counter()
        logs.sort(key=lambda x: x[1])
        ans = [0] * len(queries)
        j = k = 0
        for r, i in sorted(zip(queries, count())):
            l = r - x
            while k < len(logs) and logs[k][1] <= r:
                cnt[logs[k][0]] += 1
                k += 1
            while j < len(logs) and logs[j][1] < l:
                cnt[logs[j][0]] -= 1
                if cnt[logs[j][0]] == 0:
                    cnt.pop(logs[j][0])
                j += 1
            ans[i] = n - len(cnt)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        int m = queries.length;
        int[][] qs = new int[m][0];
        for (int i = 0; i < m; ++i) {
            qs[i] = new int[] {queries[i], i};
        }
        Arrays.sort(qs, (a, b) -> a[0] - b[0]);
        Map<Integer, Integer> cnt = new HashMap<>();
        int[] ans = new int[m];
        int j = 0, k = 0;
        for (var q : qs) {
            int r = q[0], i = q[1];
            int l = r - x;
            while (k < logs.length && logs[k][1] <= r) {
                cnt.merge(logs[k++][0], 1, Integer::sum);
            }
            while (j < logs.length && logs[j][1] < l) {
                if (cnt.merge(logs[j][0], -1, Integer::sum) == 0) {
                    cnt.remove(logs[j][0]);
                }
                j++;
            }
            ans[i] = n - cnt.size();
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> countServers(int n, vector<vector<int>>& logs, int x, vector<int>& queries) {
        sort(logs.begin(), logs.end(), [](const auto& a, const auto& b) {
            return a[1] < b[1];
        });
        int m = queries.size();
        vector<pair<int, int>> qs(m);
        for (int i = 0; i < m; ++i) {
            qs[i] = {queries[i], i};
        }
        sort(qs.begin(), qs.end());
        unordered_map<int, int> cnt;
        vector<int> ans(m);
        int j = 0, k = 0;
        for (auto& [r, i] : qs) {
            int l = r - x;
            while (k < logs.size() && logs[k][1] <= r) {
                ++cnt[logs[k++][0]];
            }
            while (j < logs.size() && logs[j][1] < l) {
                if (--cnt[logs[j][0]] == 0) {
                    cnt.erase(logs[j][0]);
                }
                ++j;
            }
            ans[i] = n - cnt.size();
        }
        return ans;
    }
};
```

### **Go**

```go
func countServers(n int, logs [][]int, x int, queries []int) []int {
	sort.Slice(logs, func(i, j int) bool { return logs[i][1] < logs[j][1] })
	m := len(queries)
	qs := make([][2]int, m)
	for i, q := range queries {
		qs[i] = [2]int{q, i}
	}
	sort.Slice(qs, func(i, j int) bool { return qs[i][0] < qs[j][0] })
	cnt := map[int]int{}
	ans := make([]int, m)
	j, k := 0, 0
	for _, q := range qs {
		r, i := q[0], q[1]
		l := r - x
		for k < len(logs) && logs[k][1] <= r {
			cnt[logs[k][0]]++
			k++
		}
		for j < len(logs) && logs[j][1] < l {
			cnt[logs[j][0]]--
			if cnt[logs[j][0]] == 0 {
				delete(cnt, logs[j][0])
			}
			j++
		}
		ans[i] = n - len(cnt)
	}
	return ans
}
```

### **TypeScript**

```ts
function countServers(n: number, logs: number[][], x: number, queries: number[]): number[] {
    logs.sort((a, b) => a[1] - b[1]);
    const m = queries.length;
    const qs: number[][] = [];
    for (let i = 0; i < m; ++i) {
        qs.push([queries[i], i]);
    }
    qs.sort((a, b) => a[0] - b[0]);
    const cnt: Map<number, number> = new Map();
    const ans: number[] = new Array(m);
    let j = 0;
    let k = 0;
    for (const [r, i] of qs) {
        const l = r - x;
        while (k < logs.length && logs[k][1] <= r) {
            cnt.set(logs[k][0], (cnt.get(logs[k][0]) || 0) + 1);
            ++k;
        }
        while (j < logs.length && logs[j][1] < l) {
            cnt.set(logs[j][0], (cnt.get(logs[j][0]) || 0) - 1);
            if (cnt.get(logs[j][0]) === 0) {
                cnt.delete(logs[j][0]);
            }
            ++j;
        }
        ans[i] = n - cnt.size;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
