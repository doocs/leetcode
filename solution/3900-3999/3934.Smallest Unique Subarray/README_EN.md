---
comments: true
difficulty: Hard
rating: 2162
source: Weekly Contest 502 Q4
tags:
    - Array
    - Hash Table
    - Binary Search
    - Suffix Array
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [3934. Smallest Unique Subarray](https://leetcode.com/problems/smallest-unique-subarray)

[中文文档](/solution/3900-3999/3934.Smallest%20Unique%20Subarray/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Find the <strong>minimum </strong>length of a <span data-keyword="subarray">subarray</span> that is <strong>not</strong> <strong>identical</strong> to any other <strong>subarray</strong> in <code>nums</code>.</p>

<p>Return an integer denoting the <strong>minimum possible length</strong> of such a <strong>subarray</strong>.</p>

<p>Two <strong>subarrays</strong> are considered identical if they have the same length and the same elements in corresponding positions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Subarrays of length 1: <code>[3]</code> &rarr; appears 3 times</li>
	<li>Subarrays of length 2: <code>[3, 3]</code> &rarr; appears 2 times</li>
	<li>Subarrays of length 3: <code>[3, 3, 3]</code> &rarr; appears once</li>
</ul>

<p>The subarray <code>[3, 3, 3]</code> is unique, so the smallest unique subarray length is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,2,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays of length 1:</p>

<ul>
	<li><code>[2]</code> &rarr; appears 2 times</li>
	<li><code>[1]</code> &rarr; appears once</li>
	<li><code>[3]</code> &rarr; appears 2 times</li>
</ul>
The subarray <code>[1]</code> is unique, so the smallest unique subarray length is 1.</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Subarrays of length 1:</p>

<ul>
	<li><code>[1]</code> &rarr; appears 3 times</li>
	<li><code>[2]</code> &rarr; appears 2 times</li>
</ul>

<p>Subarrays of length 2:</p>

<ul>
	<li><code>[1, 1]</code> &rarr; appears once</li>
	<li><code>[1, 2]</code> &rarr; appears once</li>
	<li><code>[2, 2]</code> &rarr; appears once</li>
	<li><code>[2, 1]</code> &rarr; appears once</li>
</ul>
There is at least one subarray of length 2 that is unique, so the smallest unique subarray length is 2.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Rolling Hash + Binary Search

<!-- thinking:start -->

> **Thinking**
>
> If some unique subarray of length $L$ exists, a unique subarray of every larger length exists as well (extend that core). The minimal unique length is therefore monotone in $L$ and can be binary-searched.
>
> To test a candidate $\textit{mid}$, slide every window of that length and count hashes. Rolling hashes make each slide $O(1)$, so one test is $O(n)$.
>
> $O(\log n)$ tests give $O(n \log n)$ overall.

<!-- thinking:end -->

At $\textit{mid}_\textit{len} = \frac{\min_\textit{len} + \max_\textit{len}}{2}$, for each candidate
subarray length $\textit{mid}_\textit{len}$, we slide a rolling hash window along all subarrays
of such a length, recording how many times each hash value shows up.

If any hash value appears exactly once, a unique subarray of length $\textit{mid}_\textit{len}$ is found.
We can thereby try to shrink $\max_\textit{len}$ to $\textit{mid}_\textit{len} - 1$.

Otherwise, it means that no unique subarray of that length exists, so we must raise
$\min_\textit{len}$ to $\textit{mid}_\textit{len} + 1$.

This approach works because once a unique subarray exists at length $l$,
any subarray with length $> l$ is also guaranteed to exist.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$, where $n$ is original array length.

We have a total of $O(\log n)$ binary searches, each costing $O(n)$ rolling hash time.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestUniqueSubarray(self, nums: list[int]) -> int:
        self.nums = nums

        self.base = 19
        self.modulo = 10**9 + 7
        self.powers = [1] * (len(nums) + 1)

        self.hash_values: dict[int, int] = dict()

        min_possible_len = len(nums)  # Base case.

        min_len, max_len = 1, len(nums)  # For binary search usage.

        while min_len <= max_len:
            mid_len = (min_len + max_len) // 2

            if self._check_uniqueness(mid_len):
                if mid_len < min_possible_len:
                    min_possible_len = mid_len
                max_len = mid_len - 1

            else:
                min_len = mid_len + 1

        return min_possible_len

    def _check_uniqueness(self, subarray_len: int) -> bool:
        # Only need to reset leftmost power to 1 before usage.
        self.powers[0] = 1  # Because powers are calculated by bottom-up.

        for idx in range(1, len(self.nums) + 1):
            self.powers[idx] = (self.powers[idx - 1] * self.base) % self.modulo

        current_hash = 0
        for idx in range(subarray_len):
            current_hash *= self.base
            current_hash += self.nums[idx]
            current_hash %= self.modulo

        self.hash_values.clear()  # Clear before usage.
        self.hash_values[current_hash] = 1

        for idx in range(1, len(self.nums) - subarray_len + 1):
            # Window shifts: deduct leftmost num's share from hash value.
            current_hash -= self.powers[subarray_len - 1] * self.nums[idx - 1]

            # Integrate newly added num's share into hash value.
            current_hash *= self.base
            current_hash += self.nums[idx + subarray_len - 1]
            current_hash %= self.modulo

            if current_hash not in self.hash_values.keys():
                self.hash_values.update({current_hash: 0})
            self.hash_values[current_hash] += 1

        return 1 in self.hash_values.values()
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Binary Search + Binary Lifting

<!-- thinking:start -->

> **Thinking**
>
> $n$ can be $10^5$, and a rolling hash can collide. The minimal unique length is still monotone, so the search is still over the length, but each test has to compare windows exactly.
>
> A window of length $2^k$ is the pair of two windows of length $2^{k-1}$. Any window of length $L$ is covered by its first $2^k$ elements and its last $2^k$ elements, where $k=\lfloor\log_2 L\rfloor$.
>
> Equal ids on both pieces mean the whole windows are equal. Number the windows from short to long, then count how many times each pair occurs while binary searching the length.

<!-- thinking:end -->

Let $\textit{names}[k][i]$ be the id of the subarray that starts at $i$ and has length $2^k$. Level $0$ is $\textit{nums}$ itself. Level $k+1$ pairs $\textit{names}[k][i]$ with $\textit{names}[k][i+2^k]$ and gives equal pairs the same id. The loop stops once $2\cdot\textit{half}>n$, because a longer block no longer fits.

Binary search the minimal length over $[1, n]$. For a length $L$, let $k=\lfloor\log_2 L\rfloor$. The window starting at $i$ is determined by $\textit{names}[k][i]$ and $\textit{names}[k][i+L-2^k]$: those are its first $2^k$ elements and its last $2^k$ elements, and together they cover the window. Count the pairs. A pair that occurs once means this length works, so the search moves left; otherwise it moves right.

The time complexity is $O(n\log^2 n)$, and the space complexity is $O(n\log n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestUniqueSubarray(self, nums: list[int]) -> int:
        n = len(nums)
        names = [nums]  # names[k][i] same for equal windows nums[i:i + 2**k]
        for k in range(n.bit_length() - 1):  # blocks of 2, 4, 8, ... while they fit
            ids = {}  # pair of halves -> number of the whole window
            names.append(
                [
                    ids.setdefault(pair, len(ids))
                    for pair in zip(names[k], names[k][2**k :])
                ]
            )

        def found(length):  # is some window of this length unique?
            k = (
                length.bit_length() - 1
            )  # its first 2**k elements and its last 2**k cover it
            return 1 in Counter(zip(names[k], names[k][length - 2**k :])).values()

        return bisect_left(range(1, n), True, key=found) + 1
```

#### Java

```java
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        List<int[]> names = new ArrayList<>(); // names.get(k)[i] is equal for equal windows of
                                               // length 2^k starting at i
        names.add(nums);
        for (int half = 1; 2 * half <= n; half *= 2) { // blocks of 2, 4, 8, ... while they fit
            long[] pairs = pairs(names.get(names.size() - 1), half);
            long[] ids = Arrays.stream(pairs)
                             .sorted()
                             .distinct()
                             .toArray(); // a pair's number: its place among the different pairs
            names.add(
                Arrays.stream(pairs).mapToInt(pair -> Arrays.binarySearch(ids, pair)).toArray());
        }

        int lo = 1, hi = n; // the first length that works is in lo..hi
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (found(names, mid))
                hi = mid;
            else
                lo = mid + 1;
        }
        return lo;
    }

    private boolean found(List<int[]> names, int length) { // is some window of this length unique?
        int k = 31
            - Integer.numberOfLeadingZeros(
                length); // its first 2^k elements and its last 2^k cover it
        long[] pairs = pairs(names.get(k), length - (1 << k));
        Arrays.sort(pairs); // equal pairs are neighbors now
        for (int i = 0; i < pairs.length; i++)
            if ((i == 0 || pairs[i - 1] != pairs[i])
                && (i + 1 == pairs.length || pairs[i] != pairs[i + 1]))
                return true;
        return false;
    }

    private long[] pairs(int[] block, int shift) { // every (block[i], block[i + shift]) as one long
        long[] pairs = new long[block.length - shift];
        for (int i = shift; i < block.length; i++)
            pairs[i - shift] = (long) block[i - shift] << 32 | block[i];
        return pairs;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestUniqueSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> names{nums}; // names[k][i] is equal for equal windows of length 2^k starting at i
        for (int half = 1; 2 * half <= n; half *= 2) { // blocks of 2, 4, 8, ... while they fit
            unordered_map<long long, int> ids; // pair of halves -> number of the whole window
            vector<int>& block = names.back();
            vector<int> whole;
            for (int i = half; i < (int) block.size(); ++i) {
                long long key = (long long) block[i - half] << 32 | block[i];
                whole.push_back(ids.try_emplace(key, (int) ids.size()).first->second);
            }
            names.push_back(std::move(whole));
        }

        auto found = [&](int length) { // is some window of this length unique?
            int k = 31 - __builtin_clz(length); // its first 2^k elements and its last 2^k cover it
            vector<int>& block = names[k];
            int shift = length - (1 << k);
            unordered_map<long long, int> count;
            for (int i = shift; i < (int) block.size(); ++i) {
                long long key = (long long) block[i - shift] << 32 | block[i];
                ++count[key];
            }
            for (auto& entry : count) {
                if (entry.second == 1) {
                    return true;
                }
            }
            return false;
        };

        int lo = 1, hi = n; // the first length that works is in lo..hi
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (found(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
};
```

#### Go

```go
func smallestUniqueSubarray(nums []int) int {
	n := len(nums)
	names := [][]int{nums}                  // names[k][i] is equal for equal windows of length 2^k starting at i
	for half := 1; 2*half <= n; half *= 2 { // blocks of 2, 4, 8, ... while they fit
		ids := map[[2]int]int{} // pair of halves -> number of the whole window
		block := names[len(names)-1]
		whole := make([]int, len(block)-half)
		for i := half; i < len(block); i++ {
			pair := [2]int{block[i-half], block[i]}
			if _, seen := ids[pair]; !seen {
				ids[pair] = len(ids)
			}
			whole[i-half] = ids[pair]
		}
		names = append(names, whole)
	}

	found := func(length int) bool { // is some window of this length unique?
		k := bits.Len(uint(length)) - 1 // its first 2^k elements and its last 2^k cover it
		block, shift := names[k], length-(1<<k)
		count := map[[2]int]int{}
		for i := shift; i < len(block); i++ {
			count[[2]int{block[i-shift], block[i]}]++
		}
		for _, c := range count {
			if c == 1 {
				return true
			}
		}
		return false
	}

	return 1 + sort.Search(n-1, func(i int) bool { return found(i + 1) })
}
```

#### JavaScript

```js
var smallestUniqueSubarray = function (nums) {
    const n = nums.length;
    const key = (a, b) => a * 2 ** 17 + b; // a pair of names as one exact number: every name is below 2^17
    const names = [nums]; // names[k][i] is equal for equal windows of length 2^k starting at i
    for (let half = 1; 2 * half <= n; half *= 2) {
        // blocks of 2, 4, 8, ... while they fit
        const ids = new Map(); // pair of halves -> number of the whole window
        const block = names.at(-1),
            whole = [];
        for (let i = half; i < block.length; i++) {
            const pair = key(block[i - half], block[i]);
            if (!ids.has(pair)) ids.set(pair, ids.size);
            whole.push(ids.get(pair));
        }
        names.push(whole);
    }

    const found = length => {
        // is some window of this length unique?
        const k = 31 - Math.clz32(length); // its first 2^k elements and its last 2^k cover it
        const block = names[k],
            shift = length - 2 ** k,
            count = new Map();
        for (let i = shift; i < block.length; i++) {
            const pair = key(block[i - shift], block[i]);
            count.set(pair, (count.get(pair) ?? 0) + 1);
        }
        return [...count.values()].includes(1);
    };

    let lo = 1,
        hi = n; // the first length that works is in lo..hi
    while (lo < hi) {
        const mid = (lo + hi) >> 1;
        if (found(mid)) hi = mid;
        else lo = mid + 1;
    }
    return lo;
};
```

#### TypeScript

```ts
function smallestUniqueSubarray(nums: number[]): number {
    const n = nums.length;
    const key = (a: number, b: number): number => a * 2 ** 17 + b; // a pair of names as one exact number: every name is below 2^17
    const names: number[][] = [nums]; // names[k][i] is equal for equal windows of length 2^k starting at i
    for (let half = 1; 2 * half <= n; half *= 2) {
        // blocks of 2, 4, 8, ... while they fit
        const ids = new Map<number, number>(); // pair of halves -> number of the whole window
        const block = names.at(-1)!,
            whole: number[] = [];
        for (let i = half; i < block.length; i++) {
            const pair = key(block[i - half], block[i]);
            if (!ids.has(pair)) ids.set(pair, ids.size);
            whole.push(ids.get(pair)!);
        }
        names.push(whole);
    }

    const found = (length: number): boolean => {
        // is some window of this length unique?
        const k = 31 - Math.clz32(length); // its first 2^k elements and its last 2^k cover it
        const block = names[k],
            shift = length - 2 ** k,
            count = new Map<number, number>();
        for (let i = shift; i < block.length; i++) {
            const pair = key(block[i - shift], block[i]);
            count.set(pair, (count.get(pair) ?? 0) + 1);
        }
        return [...count.values()].includes(1);
    };

    let lo = 1,
        hi = n; // the first length that works is in lo..hi
    while (lo < hi) {
        const mid = (lo + hi) >> 1;
        if (found(mid)) hi = mid;
        else lo = mid + 1;
    }
    return lo;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
