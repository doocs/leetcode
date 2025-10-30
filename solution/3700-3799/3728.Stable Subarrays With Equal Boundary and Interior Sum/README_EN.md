---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3728.Stable%20Subarrays%20With%20Equal%20Boundary%20and%20Interior%20Sum/README_EN.md
rating: 1908
source: Weekly Contest 473 Q3
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [3728. Stable Subarrays With Equal Boundary and Interior Sum](https://leetcode.com/problems/stable-subarrays-with-equal-boundary-and-interior-sum)

[中文文档](/solution/3700-3799/3728.Stable%20Subarrays%20With%20Equal%20Boundary%20and%20Interior%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>capacity</code>.</p>

<p>A <span data-keyword="subarray-nonempty">subarray</span> <code>capacity[l..r]</code> is considered <strong>stable</strong> if:</p>

<ul>
	<li>Its length is <strong>at least</strong> 3.</li>
	<li>The <strong>first</strong> and <strong>last</strong> elements are each equal to the <strong>sum</strong> of all elements <strong>strictly between</strong> them (i.e., <code>capacity[l] = capacity[r] = capacity[l + 1] + capacity[l + 2] + ... + capacity[r - 1]</code>).</li>
</ul>

<p>Return an integer denoting the number of <strong>stable subarrays</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">capacity = [9,3,3,3,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>[9,3,3,3,9]</code> is stable because the first and last elements are both 9, and the sum of the elements strictly between them is <code>3 + 3 + 3 = 9</code>.</li>
	<li><code>[3,3,3]</code> is stable because the first and last elements are both 3, and the sum of the elements strictly between them is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">capacity = [1,2,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No subarray of length at least 3 has equal first and last elements, so the answer is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">capacity = [-4,4,0,0,-8,-4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><code>[-4,4,0,0,-8,-4]</code> is stable because the first and last elements are both -4, and the sum of the elements strictly between them is <code>4 + 0 + 0 + (-8) = -4</code></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= capacity.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= capacity[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Hash Table + Enumeration

We define a prefix sum array $\textit{s}$, where $s[i]$ represents the sum of the first $i$ elements in the array $\text{capacity}$, that is, $s[i] = \text{capacity}[0] + \text{capacity}[1] + \ldots + \text{capacity}[i-1]$. Initially, $s[0] = 0$.

According to the problem statement, a subarray $\text{capacity}[l..r]$ is a stable array if:

$$
\text{capacity}[l] = \text{capacity}[r] = \text{capacity}[l + 1] + \text{capacity}[l + 2] + \ldots + \text{capacity}[r - 1]
$$

That is:

$$
\text{capacity}[l] = \text{capacity}[r] = s[r] - s[l + 1]
$$

We can enumerate the right endpoint $r$. For each $r$, we calculate the left endpoint $l = r - 2$, and store the information of the left endpoints that meet the condition in a hash table. Specifically, we use a hash table $\text{cnt}$ to record the number of occurrences of each key-value pair $(\text{capacity}[l], \text{capacity}[l] + s[l + 1])$.

When we enumerate the right endpoint $r$, we can query the hash table $\text{cnt}$ to get the number of left endpoints that meet the condition, that is, the number of occurrences of the key-value pair $(\text{capacity}[r], s[r])$, and add it to the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countStableSubarrays(self, capacity: List[int]) -> int:
        s = list(accumulate(capacity, initial=0))
        n = len(capacity)
        ans = 0
        cnt = defaultdict(int)
        for r in range(2, n):
            l = r - 2
            cnt[(capacity[l], capacity[l] + s[l + 1])] += 1
            ans += cnt[(capacity[r], s[r])]
        return ans
```

#### Java

```java
class Solution {
    public long countStableSubarrays(int[] capacity) {
        int n = capacity.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + capacity[i - 1];
        }
        Map<Pair<Integer, Long>, Integer> cnt = new HashMap<>();
        long ans = 0;
        for (int r = 2; r < n; ++r) {
            int l = r - 2;
            cnt.merge(new Pair<>(capacity[l], capacity[l] + s[l + 1]), 1, Integer::sum);
            ans += cnt.getOrDefault(new Pair<>(capacity[r], s[r]), 0);
        }
        return ans;
    }
}
```

#### C++

```cpp
struct PairHash {
    size_t operator()(const pair<int, long long>& p) const {
        return hash<int>()(p.first) ^ (hash<long long>()(p.second) << 1);
    }
};

class Solution {
public:
    long long countStableSubarrays(vector<int>& capacity) {
        int n = capacity.size();
        vector<long long> s(n + 1, 0);
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + capacity[i - 1];
        }

        unordered_map<pair<int, long long>, int, PairHash> cnt;
        long long ans = 0;

        for (int r = 2; r < n; ++r) {
            int l = r - 2;
            pair<int, long long> keyL = {capacity[l], capacity[l] + s[l + 1]};
            cnt[keyL] += 1;

            pair<int, long long> keyR = {capacity[r], s[r]};
            ans += cnt.count(keyR) ? cnt[keyR] : 0;
        }

        return ans;
    }
};
```

#### Go

```go
func countStableSubarrays(capacity []int) (ans int64) {
	n := len(capacity)
	s := make([]int64, n+1)
	for i := 1; i <= n; i++ {
		s[i] = s[i-1] + int64(capacity[i-1])
	}

	type key struct {
		first  int
		second int64
	}

	cnt := make(map[key]int)

	for r := 2; r < n; r++ {
		l := r - 2
		keyL := key{capacity[l], int64(capacity[l]) + s[l+1]}
		cnt[keyL] += 1

		keyR := key{capacity[r], s[r]}
		ans += int64(cnt[keyR])
	}

	return
}
```

#### TypeScript

```ts
function countStableSubarrays(capacity: number[]): number {
    const n = capacity.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + capacity[i - 1];
    }

    const cnt = new Map<string, number>();
    let ans = 0;

    for (let r = 2; r < n; r++) {
        const l = r - 2;
        const keyL = `${capacity[l]},${capacity[l] + s[l + 1]}`;
        cnt.set(keyL, (cnt.get(keyL) || 0) + 1);

        const keyR = `${capacity[r]},${s[r]}`;
        ans += cnt.get(keyR) || 0;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
