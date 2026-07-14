---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3991.Sort%20Array%20Using%20Prefix%20Reversals/README.md
---

<!-- problem:start -->

# [3991. 使用前缀反转对数组进行排序 🔒](https://leetcode.cn/problems/sort-array-using-prefix-reversals)

[English Version](/solution/3900-3999/3991.Sort%20Array%20Using%20Prefix%20Reversals/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，其中 <code>nums</code> 是区间 <code>[0, n - 1]</code> 内整数的一个&nbsp;<span data-keyword="permutation-array">排列</span>。</p>

<p>另给你一个整数数组 <code>pre</code>，其中每个 <code>pre[i]</code> 都是一个合法的&nbsp;<span data-keyword="array-prefix">前缀&nbsp;</span>长度。</p>

<p>一次操作中，你可以从 <code>pre</code> 中选择任意一个长度 <code>x</code>，并将 <code>nums</code> 的前 <code>x</code> 个元素翻转。</p>

<p>例如，对数组 <code>[4, 1, 2, 3]</code> 执行一次长度为 <code>3</code> 的前缀翻转后，结果为 <code>[2, 1, 4, 3]</code>。</p>

<p>返回将 <code>nums</code> 按升序排序所需的最少操作次数。如果无法完成排序，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,0,1], pre = [2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>先翻转前 <code>pre[1] = 3</code> 个元素，得到 <code>nums = [1, 0, 2]</code>。</li>
	<li>然后翻转前 <code>pre[0] = 2</code> 个元素，得到 <code>nums = [0, 1, 2]</code>。</li>
	<li>因此，将数组排序所需的最少前缀翻转次数为 2。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,0,2], pre = [1,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>无法仅使用给定的前缀长度对数组进行排序，因此答案为 <code>-1</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1], pre = [2]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>由于 <code>nums</code> 已经按升序排列，因此无需进行任何前缀翻转操作，答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>约束条件：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 8</code></li>
	<li><code>0 &lt;= nums[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= pre.length &lt;= n</code></li>
	<li><code>1 &lt;= pre[i] &lt;= n</code></li>
	<li><code>nums</code> 是由 <code>0</code> 到 <code>n - 1</code> 所有整数组成的一个排列。</li>
	<li><code>pre</code> 中的所有整数&nbsp;<strong>互不相同</strong>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

由于 $n \le 8$，排列的状态总数不超过 $8! = 40320$，可以用广度优先搜索求最少操作次数。

将当前数组视为一个状态，目标状态为 $[0, 1, \ldots, n - 1]$。若初始状态已是目标状态，直接返回 $0$。否则从初始状态出发进行 BFS：每次取出一个状态，枚举 $\textit{pre}$ 中的每个前缀长度 $x$，翻转该状态的前 $x$ 个元素得到新状态。若新状态等于目标状态，返回当前步数；否则若尚未访问过，则加入队列继续搜索。若搜索结束仍未到达目标状态，返回 $-1$。

为方便判重，可将排列编码为以 $8$ 为进制的整数（每个元素均落在 $[0, 7]$ 内）。

时间复杂度 $O(n! \cdot m \cdot n)$，空间复杂度 $O(n! \cdot n)$。其中 $n$ 是数组长度，$m$ 是 $\textit{pre}$ 的长度。

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
