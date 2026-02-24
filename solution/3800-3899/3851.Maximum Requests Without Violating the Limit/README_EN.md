---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3851.Maximum%20Requests%20Without%20Violating%20the%20Limit/README_EN.md
---

<!-- problem:start -->

# [3851. Maximum Requests Without Violating the Limit 🔒](https://leetcode.com/problems/maximum-requests-without-violating-the-limit)

[中文文档](/solution/3800-3899/3851.Maximum%20Requests%20Without%20Violating%20the%20Limit/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>requests</code>, where <code>requests[i] = [user<sub>i</sub>, time<sub>i</sub>]</code> indicates that <code>user<sub>i</sub></code> made a request at <code>time<sub>i</sub></code>.</p>

<p>You are also given two integers <code>k</code> and <code>window</code>.</p>

<p>A user violates the limit if there exists an integer <code>t</code> such that the user makes strictly more than <code>k</code> requests in the inclusive interval <code>[t, t + window]</code>.</p>

<p>You may drop any number of requests.</p>

<p>Return an integer denoting the <strong>maximum</strong>​​​​​​​ number of requests that can <strong>remain</strong> such that no user violates the limit.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">requests = [[1,1],[2,1],[1,7],[2,8]], k = 1, window = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>For user 1, the request times are <code>[1, 7]</code>. The difference between them is 6, which is greater than <code>window = 4</code>.</li>
	<li>For user 2, the request times are <code>[1, 8]</code>. The difference is 7, which is also greater than <code>window = 4</code>.</li>
	<li>No user makes more than <code>k = 1</code> request within any inclusive interval of length <code>window</code>. Therefore, all 4 requests can remain.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">requests = [[1,2],[1,5],[1,2],[1,6]], k = 2, window = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>For user 1, the request times are <code>[2, 2, 5, 6]</code>. The inclusive interval <code>[2, 7]</code> of length <code>window = 5</code> contains all 4 requests.</li>
	<li>Since 4 is strictly greater than <code>k = 2</code>, at least 2 requests must be removed.</li>
	<li>After removing any 2 requests, every inclusive interval of length <code>window</code> contains at most <code>k = 2</code> requests.</li>
	<li>Therefore, the maximum number of requests that can remain is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">requests = [[1,1],[2,5],[1,2],[3,9]], k = 1, window = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For user 1, the request times are <code>[1, 2]</code>. The difference is 1, which is equal to <code>window = 1</code>.</li>
	<li>The inclusive interval <code>[1, 2]</code> contains both requests, so the count is 2, which exceeds <code>k = 1</code>. One request must be removed.</li>
	<li>Users 2 and 3 each have only one request and do not violate the limit. Therefore, the maximum number of requests that can remain is 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= requests.length &lt;= 10<sup>5</sup></code></li>
	<li><code>requests[i] = [user<sub>i</sub>, time<sub>i</sub>]</code></li>
	<li><code>1 &lt;= k &lt;= requests.length</code></li>
	<li><code>1 &lt;= user<sub>i</sub>, time<sub>i</sub>, window &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window + Deque

We can group the requests by user and store them in a hash table $g$, where $g[u]$ is the list of request times for user $u$. For each user, we need to remove some requests from the request time list so that within any interval of length $window$, the number of remaining requests does not exceed $k$.

For the request time list $g[u]$ of user $u$, we first sort it. Then, we use a deque $kept$ to maintain the currently kept request times. We iterate through each request time $t$ in the request time list. For each request time, we need to remove all request times from $kept$ whose difference from $t$ is greater than $window$. Then, we add $t$ to $kept$. If at this point the number of requests in $kept$ exceeds $k$, we need to remove the last request time from $kept$ and increase the count of deleted requests. Finally, we subtract the number of deleted requests from the total number of requests for user $u$ and add it to the answer.

The time complexity is $O(n \log n)$ and the space complexity is $O(n)$, where $n$ is the number of requests. Each request is visited once, sorting takes $O(n \log n)$ time, and the operations on the hash table and deque take $O(n)$ time.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxRequests(self, requests: list[list[int]], k: int, window: int) -> int:
        g = defaultdict(list)
        for u, t in requests:
            g[u].append(t)
        ans = 0
        for ts in g.values():
            ts.sort()
            kept = deque()
            deletions = 0
            for t in ts:
                while kept and t - kept[0] > window:
                    kept.popleft()
                kept.append(t)
                if len(kept) > k:
                    kept.pop()
                    deletions += 1
            ans += len(ts) - deletions
        return ans
```

#### Java

```java
class Solution {
    public int maxRequests(int[][] requests, int k, int window) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] r : requests) {
            int u = r[0], t = r[1];
            g.computeIfAbsent(u, x -> new ArrayList<>()).add(t);
        }

        int ans = 0;
        ArrayDeque<Integer> kept = new ArrayDeque<>();

        for (List<Integer> ts : g.values()) {
            Collections.sort(ts);
            kept.clear();
            int deletions = 0;

            for (int t : ts) {
                while (!kept.isEmpty() && t - kept.peekFirst() > window) {
                    kept.pollFirst();
                }
                kept.addLast(t);
                if (kept.size() > k) {
                    kept.pollLast();
                    deletions++;
                }
            }
            ans += ts.size() - deletions;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxRequests(vector<vector<int>>& requests, int k, int window) {
        unordered_map<int, vector<int>> g;
        g.reserve(requests.size() * 2);

        for (auto& r : requests) {
            g[r[0]].push_back(r[1]);
        }

        int ans = 0;
        deque<int> kept;

        for (auto& [_, ts] : g) {
            sort(ts.begin(), ts.end());
            kept.clear();
            int deletions = 0;

            for (int t : ts) {
                while (!kept.empty() && t - kept.front() > window) {
                    kept.pop_front();
                }
                kept.push_back(t);
                if (kept.size() > k) {
                    kept.pop_back();
                    deletions++;
                }
            }
            ans += ts.size() - deletions;
        }
        return ans;
    }
};
```

#### Go

```go
func maxRequests(requests [][]int, k int, window int) (ans int) {
	g := make(map[int][]int)
	for _, r := range requests {
		u, t := r[0], r[1]
		g[u] = append(g[u], t)
	}
	for _, ts := range g {
		sort.Ints(ts)

		kept := make([]int, 0)
		head := 0
		deletions := 0

		for _, t := range ts {
			for head < len(kept) && t-kept[head] > window {
				head++
			}
			kept = append(kept, t)
			if len(kept)-head > k {
				kept = kept[:len(kept)-1]
				deletions++
			}
		}

		ans += len(ts) - deletions
	}
	return
}
```

#### TypeScript

```ts
function maxRequests(requests: number[][], k: number, window: number): number {
    const g = new Map<number, number[]>();
    for (const [u, t] of requests) {
        if (!g.has(u)) g.set(u, []);
        g.get(u)!.push(t);
    }

    let ans = 0;

    for (const ts of g.values()) {
        ts.sort((a, b) => a - b);

        const kept: number[] = [];
        let head = 0;
        let deletions = 0;

        for (const t of ts) {
            while (head < kept.length && t - kept[head] > window) head++;
            kept.push(t);
            if (kept.length - head > k) {
                kept.pop();
                deletions++;
            }
        }

        ans += ts.length - deletions;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
