---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3666.Minimum%20Operations%20to%20Equalize%20Binary%20String/README_EN.md
rating: 2476
source: Biweekly Contest 164 Q4
tags:
    - Breadth-First Search
    - Union Find
    - Math
    - String
    - Ordered Set
---

<!-- problem:start -->

# [3666. Minimum Operations to Equalize Binary String](https://leetcode.com/problems/minimum-operations-to-equalize-binary-string)

[中文文档](/solution/3600-3699/3666.Minimum%20Operations%20to%20Equalize%20Binary%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a binary string <code>s</code>, and an integer <code>k</code>.</p>

<p>In one operation, you must choose <strong>exactly</strong> <code>k</code> <strong>different</strong> indices and <strong>flip</strong> each <code>&#39;0&#39;</code> to <code>&#39;1&#39;</code> and each <code>&#39;1&#39;</code> to <code>&#39;0&#39;</code>.</p>

<p>Return the <strong>minimum</strong> number of operations required to make all characters in the string equal to <code>&#39;1&#39;</code>. If it is not possible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;110&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There is one <code>&#39;0&#39;</code> in <code>s</code>.</li>
	<li>Since <code>k = 1</code>, we can flip it directly in one operation.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0101&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal set of operations choosing <code>k = 3</code> indices in each operation is:</p>

<ul>
	<li><strong>Operation 1</strong>: Flip indices <code>[0, 1, 3]</code>. <code>s</code> changes from <code>&quot;0101&quot;</code> to <code>&quot;1000&quot;</code>.</li>
	<li><strong>Operation 2</strong>: Flip indices <code>[1, 2, 3]</code>. <code>s</code> changes from <code>&quot;1000&quot;</code> to <code>&quot;1111&quot;</code>.</li>
</ul>

<p>Thus, the minimum number of operations is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;101&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>k = 2</code> and <code>s</code> has only one <code>&#39;0&#39;</code>, it is impossible to flip exactly <code>k</code> indices to make all <code>&#39;1&#39;</code>. Hence, the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>​​​​​​​5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS + Ordered Set

We denote the length of string $s$ as $n$, and the current number of '0's in the string as $\textit{cur}$. In each operation, we select $k$ indices to flip, where $x$ indices flip from '0' to '1', and $k-x$ indices flip from '1' to '0'. Then the number of '0's in the string after flipping is $\textit{cur} + k - 2x$.

The value of $x$ needs to satisfy the following conditions:

1. At most $\min(\textit{cur}, k)$ '0's can be taken, because we cannot flip more than $\textit{cur}$ '0's, so $0 \leq x \leq \min(\textit{cur}, k)$.
2. At most $n - \textit{cur}$ '1's can be taken, because we cannot flip more than $n - \textit{cur}$ '1's, so $k - x \leq n - \textit{cur}$, i.e., $x \geq k - n + \textit{cur}$.

Therefore, the range of $x$ is $[\max(k - n + \textit{cur}, 0), \min(\textit{cur}, k)]$, and the range of the number of '0's in the string after flipping is $[\textit{cur} + k - 2 \cdot \min(\textit{cur}, k), \textit{cur} + k - 2 \cdot \max(k - n + \textit{cur}, 0)]$.

We notice that the parity of the number of '0's in the string after flipping is the same as the parity of the number of '0's in the string before flipping. Therefore, we can use two ordered sets to store states where the number of '0's is even and odd, respectively.

We use BFS to search the state transition graph, where the initial state is the number of '0's in the string, and the target state is 0. Each time we dequeue a state $\textit{cur}$, we calculate the range $[l, r]$ of the number of '0's in the string after flipping, find all states in the range $[l, r]$ in the ordered set, add them to the queue, and remove them from the ordered set.

If we visit state 0 during the BFS process, we return the current number of operations; if state 0 is not visited after BFS ends, we return -1.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$, where $O(n)$ is the number of states that may be visited during the BFS process, and $O(\log n)$ is the time complexity of inserting and deleting elements in the ordered set.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, s: str, k: int) -> int:
        n = len(s)
        ts = [SortedSet() for _ in range(2)]
        for i in range(n + 1):
            ts[i % 2].add(i)
        cnt0 = s.count('0')
        ts[cnt0 % 2].remove(cnt0)
        q = deque([cnt0])
        ans = 0
        while q:
            for _ in range(len(q)):
                cur = q.popleft()
                if cur == 0:
                    return ans
                l = cur + k - 2 * min(cur, k)
                r = cur + k - 2 * max(k - n + cur, 0)
                t = ts[l % 2]
                j = t.bisect_left(l)
                while j < len(t) and t[j] <= r:
                    q.append(t[j])
                    t.remove(t[j])
            ans += 1
        return -1
```

#### Java

```java
class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();

        TreeSet<Integer>[] ts = new TreeSet[2];
        Arrays.setAll(ts, i -> new TreeSet<>());

        for (int i = 0; i <= n; i++) {
            ts[i % 2].add(i);
        }

        int cnt0 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                cnt0++;
            }
        }

        ts[cnt0 % 2].remove(cnt0);

        Deque<Integer> q = new ArrayDeque<>();
        q.offer(cnt0);

        int ans = 0;
        while (!q.isEmpty()) {
            for (int size = q.size(); size > 0; --size) {
                int cur = q.poll();
                if (cur == 0) {
                    return ans;
                }

                int l = cur + k - 2 * Math.min(cur, k);
                int r = cur + k - 2 * Math.max(k - n + cur, 0);

                TreeSet<Integer> t = ts[l % 2];

                Integer next = t.ceiling(l);
                while (next != null && next <= r) {
                    q.offer(next);
                    t.remove(next);
                    next = t.ceiling(l);
                }
            }
            ans++;
        }

        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(string s, int k) {
        int n = s.size();

        set<int> ts[2];
        for (int i = 0; i <= n; i++) {
            ts[i % 2].insert(i);
        }

        int cnt0 = count(s.begin(), s.end(), '0');
        ts[cnt0 % 2].erase(cnt0);

        queue<int> q;
        q.push(cnt0);

        int ans = 0;

        while (!q.empty()) {
            for (int size = q.size(); size > 0; --size) {
                int cur = q.front();
                q.pop();
                if (cur == 0) {
                    return ans;
                }

                int l = cur + k - 2 * min(cur, k);
                int r = cur + k - 2 * max(k - n + cur, 0);

                auto& t = ts[l % 2];
                auto it = t.lower_bound(l);

                while (it != t.end() && *it <= r) {
                    q.push(*it);
                    it = t.erase(it);
                }
            }
            ans++;
        }

        return -1;
    }
};
```

#### Go

```go
func minOperations(s string, k int) int {
	n := len(s)

	ts := [2]*redblacktree.Tree{
		redblacktree.NewWithIntComparator(),
		redblacktree.NewWithIntComparator(),
	}

	for i := 0; i <= n; i++ {
		ts[i%2].Put(i, struct{}{})
	}

	cnt0 := strings.Count(s, "0")
	ts[cnt0%2].Remove(cnt0)

	q := []int{cnt0}
	ans := 0

	for len(q) > 0 {
		nq := []int{}

		for _, cur := range q {
			if cur == 0 {
				return ans
			}

			l := cur + k - 2*min(cur, k)
			r := cur + k - 2*max(k-n+cur, 0)
			t := ts[l%2]

			node, found := t.Ceiling(l)
			for found && node.Key.(int) <= r {
				val := node.Key.(int)
				nq = append(nq, val)
				t.Remove(val)
				node, found = t.Ceiling(l)
			}
		}

		q = nq
		ans++
	}

	return -1
}
```

#### TypeScript

```ts
import { AvlTree } from '@datastructures-js/binary-search-tree';

function minOperations(s: string, k: number): number {
    const n: number = s.length;

    const ts = [new AvlTree<number>(), new AvlTree<number>()];

    for (let i = 0; i <= n; i++) {
        ts[i % 2].insert(i);
    }

    let cnt0 = 0;
    for (const c of s) {
        if (c === '0') cnt0++;
    }

    ts[cnt0 % 2].remove(cnt0);

    let q: number[] = [cnt0];
    let ans = 0;

    while (q.length > 0) {
        const nq: number[] = [];

        for (const cur of q) {
            if (cur === 0) {
                return ans;
            }

            const l = cur + k - 2 * Math.min(cur, k);
            const r = cur + k - 2 * Math.max(k - n + cur, 0);

            const t = ts[l % 2];
            let node = t.upperBound(l, true);
            while (node && node.getValue() <= r) {
                const val = node.getValue();
                nq.push(val);
                t.remove(val);
                node = t.upperBound(l, false);
            }
        }

        q = nq;
        ans++;
    }

    return -1;
}
```

#### Rust

```rust
use std::collections::{BTreeSet, VecDeque};

impl Solution {
    pub fn min_operations(s: String, k: i32) -> i32 {
        let n: i32 = s.len() as i32;
        let k: i32 = k;

        let mut ts: [BTreeSet<i32>; 2] = [BTreeSet::new(), BTreeSet::new()];
        for i in 0..=n {
            ts[(i % 2) as usize].insert(i);
        }

        let cnt0: i32 = s.bytes().filter(|&c| c == b'0').count() as i32;
        ts[(cnt0 % 2) as usize].remove(&cnt0);

        let mut q: VecDeque<i32> = VecDeque::new();
        q.push_back(cnt0);

        let mut ans: i32 = 0;

        while !q.is_empty() {
            let size = q.len();
            for _ in 0..size {
                let cur = q.pop_front().unwrap();
                if cur == 0 {
                    return ans;
                }

                let l = cur + k - 2 * cur.min(k);
                let r = cur + k - 2 * (k - n + cur).max(0);

                let parity = (l % 2) as usize;

                let vals: Vec<i32> = ts[parity]
                    .range(l..=r)
                    .cloned()
                    .collect();

                for v in vals {
                    q.push_back(v);
                    ts[parity].remove(&v);
                }
            }
            ans += 1;
        }

        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
