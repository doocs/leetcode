---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3991.Sort%20Array%20Using%20Prefix%20Reversals/README_EN.md
---

<!-- problem:start -->

# [3991. Sort Array Using Prefix Reversals 🔒](https://leetcode.com/problems/sort-array-using-prefix-reversals)

[中文文档](/solution/3900-3999/3991.Sort%20Array%20Using%20Prefix%20Reversals/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>, where <code>nums</code> is a <span data-keyword="permutation-array">permutation</span> of the integers in the range <code>[0, n - 1]</code>.</p>

<p>You are also given an integer array <code>pre</code>, where each <code>pre[i]</code> is a valid <span data-keyword="array-prefix">prefix</span> length.</p>

<p>In one operation, you may choose any length <code>x</code> from <code>pre</code> and reverse the first <code>x</code> elements of <code>nums</code>.</p>

<p>For example, applying a prefix reversal of length <code>3</code> on <code>[4, 1, 2, 3]</code> results in <code>[2, 1, 4, 3]</code>.</p>

<p>Return the minimum number of operations required to sort <code>nums</code> in ascending order. If it is impossible to sort <code>nums</code>, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,0,1], pre = [2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Reverse <code>pre[1] = 3</code> elements to get <code>nums = [1, 0, 2]</code>.</li>
	<li>Then reverse <code>pre[0] = 2</code> elements to get <code>nums = [0, 1, 2]</code>.</li>
	<li>Thus, the minimum number of prefix reversal required is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,2], pre = [1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to sort the array using the given prefix lengths, so the answer is -1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1], pre = [2]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>nums</code> is already sorted, no prefix reversals are needed. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 8</code></li>
	<li><code>0 &lt;= nums[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= pre.length &lt;= n</code></li>
	<li><code>1 &lt;= pre[i] &lt;= n</code></li>
	<li><code>​​​​​​​nums</code> is a permutation of integers from 0 to <code>n - 1</code>.</li>
	<li><code>pre</code> consists of <strong>unique</strong> integers.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

Since $n \le 8$, the number of permutations is at most $8! = 40320$, so we can use BFS to find the minimum number of operations.

Treat the current array as a state, and the target state is $[0, 1, \ldots, n - 1]$. If the initial state is already the target, return $0$. Otherwise, start BFS from the initial state: each time take a state from the queue, enumerate every prefix length $x$ in $\textit{pre}$, and reverse the first $x$ elements to obtain a new state. If the new state equals the target, return the current number of steps; otherwise, if it has not been visited, enqueue it. If the search finishes without reaching the target, return $-1$.

For convenience of deduplication, encode each permutation as an integer in base $8$ (every element lies in $[0, 7]$).

The time complexity is $O(n! \cdot m \cdot n)$, and the space complexity is $O(n! \cdot n)$. Here, $n$ is the length of the array, and $m$ is the length of $\textit{pre}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortArray(self, nums: List[int], pre: List[int]) -> int:
        n = len(nums)
        target = tuple(range(n))
        start = tuple(nums)

        if start == target:
            return 0

        vis = {start}
        q = deque([(start, 0)])

        while q:
            state, dist = q.popleft()
            nd = dist + 1
            for x in pre:
                nxt = state[:x][::-1] + state[x:]
                if nxt == target:
                    return nd
                if nxt not in vis:
                    vis.add(nxt)
                    q.append((nxt, nd))
        return -1
```

#### Java

```java
class Solution {
    public int sortArray(int[] nums, int[] pre) {
        int n = nums.length;

        int target = 0;
        for (int i = 0; i < n; i++) {
            target = target * 8 + i;
        }

        int start = 0;
        for (int x : nums) {
            start = start * 8 + x;
        }

        if (start == target) {
            return 0;
        }

        Set<Integer> vis = new HashSet<>();
        vis.add(start);

        Deque<int[]> q = new ArrayDeque<>();
        Deque<Integer> dist = new ArrayDeque<>();
        q.offer(nums.clone());
        dist.offer(0);

        while (!q.isEmpty()) {
            int[] state = q.poll();
            int d = dist.poll();
            int nd = d + 1;

            for (int x : pre) {
                int[] nxt = state.clone();

                int l = 0, r = x - 1;
                while (l < r) {
                    int t = nxt[l];
                    nxt[l] = nxt[r];
                    nxt[r] = t;
                    l++;
                    r--;
                }

                int key = 0;
                for (int v : nxt) {
                    key = key * 8 + v;
                }

                if (key == target) {
                    return nd;
                }

                if (vis.add(key)) {
                    q.offer(nxt);
                    dist.offer(nd);
                }
            }
        }

        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int sortArray(vector<int>& nums, vector<int>& pre) {
        int n = nums.size();

        int target = 0;
        for (int i = 0; i < n; i++) {
            target = target * 8 + i;
        }

        int start = 0;
        for (int x : nums) {
            start = start * 8 + x;
        }

        if (start == target) {
            return 0;
        }

        unordered_set<int> vis;
        vis.insert(start);

        queue<pair<vector<int>, int>> q;
        q.emplace(nums, 0);

        while (!q.empty()) {
            auto [state, dist] = q.front();
            q.pop();

            int nd = dist + 1;

            for (int x : pre) {
                vector<int> nxt = state;
                reverse(nxt.begin(), nxt.begin() + x);

                int key = 0;
                for (int v : nxt) {
                    key = key * 8 + v;
                }

                if (key == target) {
                    return nd;
                }

                if (vis.insert(key).second) {
                    q.emplace(move(nxt), nd);
                }
            }
        }

        return -1;
    }
};
```

#### Go

```go
func sortArray(nums []int, pre []int) int {
	n := len(nums)

	target := 0
	for i := 0; i < n; i++ {
		target = target*8 + i
	}

	start := 0
	for _, x := range nums {
		start = start*8 + x
	}

	if start == target {
		return 0
	}

	vis := map[int]bool{start: true}

	type pair struct {
		state []int
		dist  int
	}
	q := []pair{{append([]int(nil), nums...), 0}}

	for len(q) > 0 {
		p := q[0]
		q = q[1:]

		state := p.state
		dist := p.dist
		nd := dist + 1

		for _, x := range pre {
			nxt := append([]int(nil), state...)

			for l, r := 0, x-1; l < r; l, r = l+1, r-1 {
				nxt[l], nxt[r] = nxt[r], nxt[l]
			}

			key := 0
			for _, v := range nxt {
				key = key*8 + v
			}

			if key == target {
				return nd
			}

			if !vis[key] {
				vis[key] = true
				q = append(q, pair{nxt, nd})
			}
		}
	}

	return -1
}
```

#### TypeScript

```ts
function sortArray(nums: number[], pre: number[]): number {
    const n = nums.length;

    let target = 0;
    for (let i = 0; i < n; i++) {
        target = target * 8 + i;
    }

    let start = 0;
    for (const x of nums) {
        start = start * 8 + x;
    }

    if (start === target) {
        return 0;
    }

    const vis = new Set<number>();
    vis.add(start);

    const q: [number[], number][] = [[nums.slice(), 0]];

    while (q.length) {
        const [state, dist] = q.shift()!;
        const nd = dist + 1;

        for (const x of pre) {
            const nxt = state.slice();

            for (let l = 0, r = x - 1; l < r; l++, r--) {
                [nxt[l], nxt[r]] = [nxt[r], nxt[l]];
            }

            let key = 0;
            for (const v of nxt) {
                key = key * 8 + v;
            }

            if (key === target) {
                return nd;
            }

            if (!vis.has(key)) {
                vis.add(key);
                q.push([nxt, nd]);
            }
        }
    }

    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
