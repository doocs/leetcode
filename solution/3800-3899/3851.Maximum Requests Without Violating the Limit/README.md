---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3851.Maximum%20Requests%20Without%20Violating%20the%20Limit/README.md
---

<!-- problem:start -->

# [3851. 不违反限制的最大请求数 🔒](https://leetcode.cn/problems/maximum-requests-without-violating-the-limit)

[English Version](/solution/3800-3899/3851.Maximum%20Requests%20Without%20Violating%20the%20Limit/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二维整数数组&nbsp;<code>requests</code>，其中&nbsp;<code>requests[i] = [user<sub>i</sub>, time<sub>i</sub>]</code>&nbsp;表示&nbsp;<code>user<sub>i</sub></code>&nbsp;在 <code>time<sub>i</sub></code>&nbsp;进行了一次请求。</p>

<p>同时给定两个整数&nbsp;<code>k</code> 和&nbsp;<code>window</code>。</p>

<p>如果存在一个整数 <code>t</code>，使得用户在闭区间 <code>[t, t + window]</code> 内的请求次数严格大于&nbsp;<code>k</code>，则用户违反了限制。</p>

<p>可以发送任意数量的请求。</p>

<p>返回一个整数，表示没有用户违反限制的可 <strong>保留</strong> 的&nbsp;<strong>最大</strong> 请求数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>requests = [[1,1],[2,1],[1,7],[2,8]], k = 1, window = 4</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><b>解释：</b></p>

<ul>
	<li>对于用户 1，请求时间是&nbsp;<code>[1, 7]</code>。它们的差是 6，这大于 <code>window = 4</code>。</li>
	<li>对于用户 2，请求时间是&nbsp;<code>[1, 8]</code>。它们的差是 7，这同样大于&nbsp;<code>window = 4</code>。</li>
	<li>任何&nbsp;<code>window</code> 长度的闭区间内，用户发出的请求数不超过 <code>k = 1</code>，因此所有 4 个请求都可以保留。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>requests = [[1,2],[1,5],[1,2],[1,6]], k = 2, window = 5</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<ul>
	<li>对于用户 1，请求时间是&nbsp;<code>[2, 2, 5, 6]</code>。长度为&nbsp;<code>window = 5</code>&nbsp;的闭区间&nbsp;<code>[2, 7]</code>&nbsp;包含所有 4 个请求。</li>
	<li>由于 4 严格大于&nbsp;<code>k = 2</code>，必须至少移除 2 个请求。</li>
	<li>在移除任意 2 个请求后，长度为&nbsp;<code>window</code> 的每个闭区间包含最多 <code>k = 2</code>&nbsp;个请求。</li>
	<li>因此，最多可以保留的请求数是 2。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>requests = [[1,1],[2,5],[1,2],[3,9]], k = 1, window = 1</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>对于用户 1，请求时间是&nbsp;<code>[1, 2]</code>。差值为 1，这等于&nbsp;<code>window = 1</code>。</li>
	<li>闭区间&nbsp;<code>[1, 2]</code>&nbsp;同时包含这两个请求，所以计数为 2，超过了&nbsp;<code>k = 1</code>。必须移除一个请求。</li>
	<li>用户 2 和用户 3 各自只有一条请求，且均未超出限制。因此，最多可以保留的请求数是 3。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= requests.length &lt;= 10<sup>5</sup></code></li>
	<li><code>requests[i] = [user<sub>i</sub>, time<sub>i</sub>]</code></li>
	<li><code>1 &lt;= k &lt;= requests.length</code></li>
	<li><code>1 &lt;= user<sub>i</sub>, time<sub>i</sub>, window &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口 + 双端队列

我们可以将请求按照用户进行分组，放在哈希表 $g$ 中，其中 $g[u]$ 是用户 $u$ 的请求时间列表。对于每个用户，我们需要从请求时间列表中删除一些请求，使得在任意长度为 $window$ 的区间内，剩余的请求数不超过 $k$。

我们初始化答案 $\textit{ans}$ 为请求的总数。

对于用户 $u$ 的请求时间列表 $g[u]$，我们首先对其进行排序。然后，我们使用一个双端队列 $kept$ 来维护当前保留的请求时间。我们遍历请求时间列表中的每个请求时间 $t$，对于每个请求时间，我们需要将 $kept$ 中所有与 $t$ 的差值大于 $window$ 的请求时间删除掉。然后，如果 $kept$ 中剩余的请求数小于 $k$，我们就将 $t$ 添加到 $kept$ 中，否则我们需要删除 $t$，并将答案减 1。

最后返回答案 $\textit{ans}$。

时间复杂度 $O(n \log n)$，空间复杂度 $O(n)$，其中 $n$ 是请求的数量。每个请求被访问一次，排序需要 $O(n \log n)$ 的时间，哈希表和双端队列的操作需要 $O(n)$ 的时间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxRequests(self, requests: list[list[int]], k: int, window: int) -> int:
        g = defaultdict(list)
        for u, t in requests:
            g[u].append(t)
        ans = len(requests)
        for ts in g.values():
            ts.sort()
            kept = deque()
            for t in ts:
                while kept and t - kept[0] > window:
                    kept.popleft()
                if len(kept) < k:
                    kept.append(t)
                else:
                    ans -= 1
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

        int ans = requests.length;
        ArrayDeque<Integer> kept = new ArrayDeque<>();

        for (List<Integer> ts : g.values()) {
            Collections.sort(ts);
            kept.clear();
            for (int t : ts) {
                while (!kept.isEmpty() && t - kept.peekFirst() > window) {
                    kept.pollFirst();
                }
                if (kept.size() < k) {
                    kept.addLast(t);
                } else {
                    --ans;
                }
            }
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
        for (auto& r : requests) {
            g[r[0]].push_back(r[1]);
        }

        int ans = requests.size();
        for (auto& [_, ts] : g) {
            sort(ts.begin(), ts.end());
            queue<int> kept;
            int deletions = 0;

            for (int t : ts) {
                while (!kept.empty() && t - kept.front() > window) {
                    kept.pop();
                }
                if (kept.size() < k) {
                    kept.push(t);
                } else {
                    ans--;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxRequests(requests [][]int, k int, window int) int {
	g := make(map[int][]int)
	for _, r := range requests {
		u, t := r[0], r[1]
		g[u] = append(g[u], t)
	}
	ans := len(requests)
	for _, ts := range g {
		sort.Ints(ts)
		kept := make([]int, 0)
		for _, t := range ts {
			for len(kept) > 0 && t-kept[0] > window {
				kept = kept[1:]
			}
			if len(kept) < k {
				kept = append(kept, t)
			} else {
				ans--
			}
		}
	}
	return ans
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
    let ans = requests.length;
    for (const ts of g.values()) {
        ts.sort((a, b) => a - b);
        const kept: number[] = [];
        let head = 0;
        for (const t of ts) {
            while (head < kept.length && t - kept[head] > window) {
                head++;
            }
            if (kept.length - head < k) {
                kept.push(t);
            } else {
                --ans;
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::{HashMap, VecDeque};

impl Solution {
    pub fn max_requests(requests: Vec<Vec<i32>>, k: i32, window: i32) -> i32 {
        let mut g: HashMap<i32, Vec<i32>> = HashMap::new();
        for r in &requests {
            let u: i32 = r[0];
            let t: i32 = r[1];
            g.entry(u).or_insert_with(Vec::new).push(t);
        }

        let mut ans: i32 = requests.len() as i32;
        let mut kept: VecDeque<i32> = VecDeque::new();

        for ts in g.values_mut() {
            ts.sort();
            kept.clear();

            for &t in ts.iter() {
                while let Some(&front) = kept.front() {
                    if t - front > window {
                        kept.pop_front();
                    } else {
                        break;
                    }
                }

                if kept.len() < k as usize {
                    kept.push_back(t);
                } else {
                    ans -= 1;
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
