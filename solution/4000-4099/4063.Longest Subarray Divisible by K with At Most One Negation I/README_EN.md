---
comments: true
difficulty: Medium
rating: 1740
source: Biweekly Contest 192 Q3
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [4063. Longest Subarray Divisible by K with At Most One Negation I](https://leetcode.com/problems/longest-subarray-divisible-by-k-with-at-most-one-negation-i)

[中文文档](/solution/4000-4099/4063.Longest%20Subarray%20Divisible%20by%20K%20with%20At%20Most%20One%20Negation%20I/README.md)

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
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate the Negated Index

<!-- thinking:start -->

> **Thinking**
>
> A subarray sum is divisible by $k$ exactly when the prefix sums at its two ends are congruent modulo $k$. Enumerating every subarray and then every element inside it is about $O(n^3)$. Since $n\le 1000$, the search has to drop an order of magnitude.
>
> Negating $x$ decreases the sum of every subarray that contains it by $2x$, and leaves every other subarray unchanged. There are $n$ choices for the negated index, plus the choice of negating nothing. Each choice only needs the longest subarray whose sum is divisible by $k$.
>
> Each prefix residue modulo $k$ keeps the first index where it appears. Meeting that residue again makes an earlier left end a longer subarray. A subarray that misses the negated index was already counted on the original array.

<!-- thinking:end -->

After $\textit{nums}[i]$ is negated, every subarray that contains index $i$ loses $2\times\textit{nums}[i]$, and every subarray that misses $i$ keeps its sum. Run the divisible-subarray search on the original array and again after negating each index in turn. The answer is the maximum of those lengths.

The scan keeps the prefix sum modulo $k$, with the residue folded into $[0, k)$. Each residue keeps the first index where it appears, and residue $0$ starts at index $-1$. At index $i$, if the current residue was seen at index $j$, then the sum of $\textit{nums}[j+1..i]$ is divisible by $k$ and the length is $i-j$. Python, Java, Go, and TypeScript store those indices in a hash map. C++ uses an array of length $k$, indexed by the residue, and passes the negated index as a parameter.

The time complexity is $O(n^2)$ and the space complexity is $O(n)$. C++ resets that array on every scan, so its time complexity is $O(n(n+k))$ and its space complexity is $O(k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: list[int], k: int) -> int:
        def f(nums: list[int], k: int) -> int:
            d = {0: -1}
            s = res = 0
            for i, x in enumerate(nums):
                s = (s + x) % k
                if s in d:
                    res = max(res, i - d[s])
                else:
                    d[s] = i
            return res

        ans = f(nums, k)
        for i, x in enumerate(nums):
            nums[i] = -x
            ans = max(ans, f(nums, k))
            nums[i] = x
        return ans
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums, int k) {
        int ans = f(nums, k);
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = -nums[i];
            ans = Math.max(ans, f(nums, k));
            nums[i] = -nums[i];
        }
        return ans;
    }

    private int f(int[] nums, int k) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int s = 0, res = 0;
        for (int i = 0; i < nums.length; ++i) {
            s = ((s + nums[i]) % k + k) % k;
            if (d.containsKey(s)) {
                res = Math.max(res, i - d.get(s));
            } else {
                d.put(s, i);
            }
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> d(k, -2);

        auto f = [&](int skip) {
            fill(d.begin(), d.end(), -2);
            d[0] = -1;
            int s = 0, res = 0;
            for (int i = 0; i < n; ++i) {
                int x = i == skip ? -nums[i] : nums[i];
                s = (s + x) % k;
                if (s < 0) {
                    s += k;
                }
                if (d[s] != -2) {
                    res = max(res, i - d[s]);
                } else {
                    d[s] = i;
                }
            }
            return res;
        };

        int ans = f(-1);
        for (int i = 0; i < n; ++i) {
            ans = max(ans, f(i));
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubarray(nums []int, k int) int {
	f := func(nums []int) int {
		d := map[int]int{0: -1}
		s, res := 0, 0
		for i, x := range nums {
			s = (s + x) % k
			if s < 0 {
				s += k
			}
			if j, ok := d[s]; ok {
				res = max(res, i-j)
			} else {
				d[s] = i
			}
		}
		return res
	}

	ans := f(nums)
	for i, x := range nums {
		nums[i] = -x
		ans = max(ans, f(nums))
		nums[i] = x
	}
	return ans
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[], k: number): number {
    const f = (nums: number[]): number => {
        const d = new Map<number, number>([[0, -1]]);
        let s = 0,
            res = 0;
        for (let i = 0; i < nums.length; ++i) {
            s = (s + nums[i]) % k;
            if (s < 0) {
                s += k;
            }
            if (d.has(s)) {
                res = Math.max(res, i - d.get(s)!);
            } else {
                d.set(s, i);
            }
        }
        return res;
    };

    let ans = f(nums);
    for (let i = 0; i < nums.length; ++i) {
        nums[i] = -nums[i];
        ans = Math.max(ans, f(nums));
        nums[i] = -nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
