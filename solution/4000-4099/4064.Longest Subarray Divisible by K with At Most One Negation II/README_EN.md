---
comments: true
difficulty: Hard
rating: 2284
source: Biweekly Contest 192 Q4
tags:
    - Array
    - Hash Table
    - Binary Search
    - Prefix Sum
---

<!-- problem:start -->

# [4064. Longest Subarray Divisible by K with At Most One Negation II](https://leetcode.com/problems/longest-subarray-divisible-by-k-with-at-most-one-negation-ii)

[中文文档](/solution/4000-4099/4064.Longest%20Subarray%20Divisible%20by%20K%20with%20At%20Most%20One%20Negation%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>A <span data-keyword="subarray-nonempty">subarray</span> is <strong>valid</strong> if its sum is divisible by <code>k</code>, or can become divisible by <code>k</code> by <strong>negating one element within that subarray</strong>.</p>

<p>Negating an element means replacing its value <code>x</code> with <code>-x</code>.</p>

<p>Return the <strong>length of the longest valid subarray</strong>. If no valid subarray exists, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,1,2], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The sum of the entire array is 7, and <code>7 % 3 = 1</code>, so it is not divisible by <code>k = 3</code>.</li>
	<li>Negating <code>nums[2] = 2</code> changes the sum to <code>4 + 1 &minus; 2 = 3</code>, which is divisible by <code>k</code>.</li>
	<li>Therefore, the entire array is a valid subarray, giving a length of 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,3,4], k = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The sum of the entire array is 12, and negating any one of its elements does not make its sum divisible by 7.</li>
	<li>However, the subarray <code>[3, 4]</code> has a sum of 7, which is divisible by <code>k = 7</code> without any negation.</li>
	<li>Therefore, the longest valid subarray has a length of 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,5], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The sum of the entire array is 9, and negating any one of its elements does not make its sum divisible by 6.</li>
	<li>The subarray <code>[2, 2]</code> has a sum of 4. Negating either element changes it to <code>[-2, 2]</code> or <code>[2, -2]</code>, both of which have a sum of 0.</li>
	<li>Therefore, the longest valid subarray has a length of 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup>​​​​​​​</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 3000</code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sums

<!-- thinking:start -->

> **Thinking**
>
> $n$ can be $10^5$. Enumerating the negated index and scanning prefix sums for each choice, as in the previous problem, takes $O(n^2)$ and does not finish in time. Here $k\le 3000$, so there are only that many residues.
>
> A subarray sum modulo $k$ is the right prefix minus the left prefix. Negating an element $x$ decreases that difference by $2x$, so the right residue equals the left residue plus $2(x\bmod k)$. Each left residue needs only its earliest prefix; a later index produces a shorter subarray.
>
> Those earliest indices are handled in the order they appear. While scanning the right end, the current element supplies the residue used for negation, and every left residue that can already cover it and has not been recorded for this residue is written onto the target residue. Each pair of residues is written once, and the smallest left end stored for the right residue is the longest valid subarray ending there.

<!-- thinking:end -->

Let $p[0]=0$ and $p[i+1]=(p[i]+\textit{nums}[i])\bmod k$. The sum of $\textit{nums}[L..R]$ is $p[R+1]-p[L]$ modulo $k$. Negating $\textit{nums}[t]$ inside it, with $a=\textit{nums}[t]\bmod k$, decreases the sum by $2\textit{nums}[t]$. The subarray is valid when some $t\in[L,R]$ satisfies

$$
p[R+1]\equiv p[L]+2a\pmod{k},
$$

and it is also valid with no negation when $p[R+1]\equiv p[L]$. The length is $(R+1)-L$, so a fixed right end wants the smallest left end.

$\textit{first}[q]$ is the first prefix index whose residue is $q$. Sort the residues that occur by $\textit{first}$ into $\textit{order}$. $\textit{best}[s]$ stores the smallest left end currently known for a right prefix of residue $s$. It starts as $\textit{first}[s]$, or as a sentinel when that residue has not occurred. The initial values cover the case with no negation.

Scan index $i$ from left to right and let $a=\textit{nums}[i]\bmod k$. A pointer remembers how far each residue $a$ has walked through $\textit{order}$. Every residue $q$ with $\textit{first}[q]\le i$ can include position $i$, so set

$$
t=(q+2a)\bmod k,\qquad \textit{best}[t]=\min(\textit{best}[t],\textit{first}[q]).
$$

After negating index $i$, a subarray that starts at $\textit{first}[q]$ and ends at any later right end of residue $t$ is valid. The pointer only moves forward, so each pair $(a,q)$ is handled once. Later right ends still contain $i$, and $\textit{best}$ keeps the smallest left end.

Let $s=p[i+1]$. When $\textit{best}[s]$ is not the sentinel, update the answer with $i+1-\textit{best}[s]$. Negative values are reduced into $[0,k)$.

The time complexity is $O(n+k^2)$ and the space complexity is $O(n+k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: list[int], k: int) -> int:
        n = len(nums)
        p = [0] * (n + 1)
        for i in range(n):
            p[i + 1] = (p[i] + nums[i]) % k

        first = [-1] * k
        for i in range(n + 1):
            if first[p[i]] == -1:
                first[p[i]] = i

        order = [q for q in range(k) if first[q] != -1]
        order.sort(key=lambda q: first[q])

        pos = [0] * k
        best = [x if x != -1 else n + 1 for x in first]

        ans = 0
        for i, x in enumerate(nums):
            a = x % k
            while pos[a] < len(order) and first[order[pos[a]]] <= i:
                q = order[pos[a]]
                pos[a] += 1
                t = (q + 2 * a) % k
                best[t] = min(best[t], first[q])

            s = p[i + 1]
            if best[s] != n + 1:
                ans = max(ans, i + 1 - best[s])

        return ans
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        int[] p = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            p[i + 1] = (p[i] + nums[i]) % k;
            if (p[i + 1] < 0) {
                p[i + 1] += k;
            }
        }

        int[] first = new int[k];
        Arrays.fill(first, -1);
        for (int i = 0; i <= n; ++i) {
            if (first[p[i]] == -1) {
                first[p[i]] = i;
            }
        }

        Integer[] order = new Integer[k];
        int m = 0;
        for (int q = 0; q < k; ++q) {
            if (first[q] != -1) {
                order[m++] = q;
            }
        }

        Arrays.sort(order, 0, m, (a, b) -> Integer.compare(first[a], first[b]));

        int[] pos = new int[k];
        int[] best = new int[k];
        Arrays.fill(best, Integer.MAX_VALUE);
        for (int q = 0; q < k; ++q) {
            if (first[q] != -1) {
                best[q] = first[q];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int a = nums[i] % k;
            if (a < 0) {
                a += k;
            }

            while (pos[a] < m && first[order[pos[a]]] <= i) {
                int q = order[pos[a]++];
                int t = (q + 2 * a) % k;
                best[t] = Math.min(best[t], first[q]);
            }

            int s = p[i + 1];
            if (best[s] != Integer.MAX_VALUE) {
                ans = Math.max(ans, i + 1 - best[s]);
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
    int longestSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> p(n + 1);
        for (int i = 0; i < n; ++i) {
            p[i + 1] = (p[i] + nums[i]) % k;
            if (p[i + 1] < 0) {
                p[i + 1] += k;
            }
        }

        vector<int> first(k, -1);
        for (int i = 0; i <= n; ++i) {
            if (first[p[i]] == -1) {
                first[p[i]] = i;
            }
        }

        vector<int> order;
        for (int q = 0; q < k; ++q) {
            if (first[q] != -1) {
                order.push_back(q);
            }
        }

        ranges::sort(order, [&](int a, int b) {
            return first[a] < first[b];
        });

        vector<int> pos(k);
        vector<int> best(k, INT_MAX);
        for (int q = 0; q < k; ++q) {
            best[q] = first[q] == -1 ? INT_MAX : first[q];
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int a = nums[i] % k;
            if (a < 0) {
                a += k;
            }

            while (pos[a] < order.size() && first[order[pos[a]]] <= i) {
                int q = order[pos[a]++];
                int t = (q + 2 * a) % k;
                best[t] = min(best[t], first[q]);
            }

            int s = p[i + 1];
            if (best[s] != INT_MAX) {
                ans = max(ans, i + 1 - best[s]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubarray(nums []int, k int) int {
	n := len(nums)
	p := make([]int, n+1)
	for i := 0; i < n; i++ {
		p[i+1] = (p[i] + nums[i]) % k
		if p[i+1] < 0 {
			p[i+1] += k
		}
	}

	first := make([]int, k)
	for i := range first {
		first[i] = -1
	}
	for i := 0; i <= n; i++ {
		if first[p[i]] == -1 {
			first[p[i]] = i
		}
	}

	order := make([]int, 0, k)
	for q := 0; q < k; q++ {
		if first[q] != -1 {
			order = append(order, q)
		}
	}

	slices.SortFunc(order, func(a, b int) int {
		return first[a] - first[b]
	})

	pos := make([]int, k)
	best := make([]int, k)
	for q := 0; q < k; q++ {
		if first[q] == -1 {
			best[q] = int(^uint(0) >> 1)
		} else {
			best[q] = first[q]
		}
	}

	ans := 0
	for i, x := range nums {
		a := x % k
		if a < 0 {
			a += k
		}

		for pos[a] < len(order) && first[order[pos[a]]] <= i {
			q := order[pos[a]]
			pos[a]++
			t := (q + 2*a) % k
			best[t] = min(best[t], first[q])
		}

		s := p[i+1]
		if best[s] != int(^uint(0)>>1) {
			ans = max(ans, i+1-best[s])
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[], k: number): number {
    const n = nums.length;
    const p = new Array<number>(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        p[i + 1] = (p[i] + nums[i]) % k;
        if (p[i + 1] < 0) {
            p[i + 1] += k;
        }
    }

    const first = new Array<number>(k).fill(-1);
    for (let i = 0; i <= n; ++i) {
        if (first[p[i]] === -1) {
            first[p[i]] = i;
        }
    }

    const order = Array.from({ length: k }, (_, q) => q).filter(q => first[q] !== -1);

    order.sort((a, b) => first[a] - first[b]);

    const pos = new Array<number>(k).fill(0);
    const best = first.map(x => (x === -1 ? n + 1 : x));

    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let a = nums[i] % k;
        if (a < 0) {
            a += k;
        }

        while (pos[a] < order.length && first[order[pos[a]]] <= i) {
            const q = order[pos[a]++];
            const t = (q + 2 * a) % k;
            best[t] = Math.min(best[t], first[q]);
        }

        const s = p[i + 1];
        if (best[s] !== n + 1) {
            ans = Math.max(ans, i + 1 - best[s]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
