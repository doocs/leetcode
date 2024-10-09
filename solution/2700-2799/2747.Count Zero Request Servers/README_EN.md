---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2747.Count%20Zero%20Request%20Servers/README_EN.md
rating: 2405
source: Biweekly Contest 107 Q4
tags:
    - Array
    - Hash Table
    - Sorting
    - Sliding Window
---

<!-- problem:start -->

# [2747. Count Zero Request Servers](https://leetcode.com/problems/count-zero-request-servers)

[中文文档](/solution/2700-2799/2747.Count%20Zero%20Request%20Servers/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> denoting the total number of servers and a <strong>2D</strong> <strong>0-indexed </strong>integer array <code>logs</code>, where <code>logs[i] = [server_id, time]</code> denotes that the server with id <code>server_id</code> received a request at time <code>time</code>.</p>

<p>You are also given an integer <code>x</code> and a <strong>0-indexed</strong> integer array <code>queries</code>.</p>

<p>Return <em>a <strong>0-indexed</strong> integer array</em> <code>arr</code> <em>of length</em> <code>queries.length</code> <em>where</em> <code>arr[i]</code> <em>represents the number of servers that <strong>did not receive</strong> any requests during the time interval</em> <code>[queries[i] - x, queries[i]]</code>.</p>

<p>Note that the time intervals are inclusive.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> 
For queries[0]: The servers with ids 1 and 2 get requests in the duration of [5, 10]. Hence, only server 3 gets zero requests.
For queries[1]: Only the server with id 2 gets a request in duration of [6,11]. Hence, the servers with ids 1 and 3 are the only servers that do not receive any requests during that time period.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, logs = [[2,4],[2,1],[1,2],[3,1]], x = 2, queries = [3,4]
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> 
For queries[0]: All servers get at least one request in the duration of [1, 3].
For queries[1]: Only server with id 3 gets no request in the duration [2,4].

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= logs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code><font face="monospace">logs[i].length == 2</font></code></li>
	<li><code>1 &lt;= logs[i][0] &lt;= n</code></li>
	<li><code>1 &lt;= logs[i][1] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>5</sup></code></li>
	<li><code>x &lt;&nbsp;queries[i]&nbsp;&lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Offline Queries + Sorting + Two Pointers

We can sort all the queries by time from smallest to largest, and then process each query in chronological order.

For each query $q = (r, i)$, its window left boundary is $l = r - x$, and we need to count how many servers received requests within the window $[l, r]$. We use two pointers $j$ and $k$ to maintain the left and right boundaries of the window, initially $j = k = 0$. Each time, if the log time pointed by $k$ is less than or equal to $r$, we add it to the window, and then move $k$ to the right by one. If the log time pointed by $j$ is less than $l$, we remove it from the window, and then move $j$ to the right by one. During the movement, we need to count how many different servers are in the window, which can be implemented using a hash table. After the movement, the number of servers that did not receive requests in the current time interval is $n$ minus the number of different servers in the hash table.

The time complexity is $O(l \times \log l + m \times \log m + n)$, and the space complexity is $O(l + m)$. Here, $l$ and $n$ are the lengths of the arrays $\textit{logs}$ and the number of servers, respectively, while $m$ is the length of the array $\textit{queries}$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
