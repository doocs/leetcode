---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3048.Earliest%20Second%20to%20Mark%20Indices%20I/README_EN.md
rating: 2262
tags:
    - Array
    - Binary Search
---

# [3048. Earliest Second to Mark Indices I](https://leetcode.com/problems/earliest-second-to-mark-indices-i)

[中文文档](/solution/3000-3099/3048.Earliest%20Second%20to%20Mark%20Indices%20I/README.md)

## Description

<p>You are given two <strong>1-indexed</strong> integer arrays, <code>nums</code> and, <code>changeIndices</code>, having lengths <code>n</code> and <code>m</code>, respectively.</p>

<p>Initially, all indices in <code>nums</code> are unmarked. Your task is to mark <strong>all</strong> indices in <code>nums</code>.</p>

<p>In each second, <code>s</code>, in order from <code>1</code> to <code>m</code> (<strong>inclusive</strong>), you can perform <strong>one</strong> of the following operations:</p>

<ul>
	<li>Choose an index <code>i</code> in the range <code>[1, n]</code> and <strong>decrement</strong> <code>nums[i]</code> by <code>1</code>.</li>
	<li>If <code>nums[changeIndices[s]]</code> is <strong>equal</strong> to <code>0</code>, <strong>mark</strong> the index <code>changeIndices[s]</code>.</li>
	<li>Do nothing.</li>
</ul>

<p>Return <em>an integer denoting the <strong>earliest second</strong> in the range </em><code>[1, m]</code><em> when <strong>all</strong> indices in </em><code>nums</code><em> can be marked by choosing operations optimally, or </em><code>-1</code><em> if it is impossible.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,0], changeIndices = [2,2,2,2,3,2,2,1]
<strong>Output:</strong> 8
<strong>Explanation:</strong> In this example, we have 8 seconds. The following operations can be performed to mark all indices:
Second 1: Choose index 1 and decrement nums[1] by one. nums becomes [1,2,0].
Second 2: Choose index 1 and decrement nums[1] by one. nums becomes [0,2,0].
Second 3: Choose index 2 and decrement nums[2] by one. nums becomes [0,1,0].
Second 4: Choose index 2 and decrement nums[2] by one. nums becomes [0,0,0].
Second 5: Mark the index changeIndices[5], which is marking index 3, since nums[3] is equal to 0.
Second 6: Mark the index changeIndices[6], which is marking index 2, since nums[2] is equal to 0.
Second 7: Do nothing.
Second 8: Mark the index changeIndices[8], which is marking index 1, since nums[1] is equal to 0.
Now all indices have been marked.
It can be shown that it is not possible to mark all indices earlier than the 8th second.
Hence, the answer is 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3], changeIndices = [1,1,1,2,1,1,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> In this example, we have 7 seconds. The following operations can be performed to mark all indices:
Second 1: Choose index 2 and decrement nums[2] by one. nums becomes [1,2].
Second 2: Choose index 2 and decrement nums[2] by one. nums becomes [1,1].
Second 3: Choose index 2 and decrement nums[2] by one. nums becomes [1,0].
Second 4: Mark the index changeIndices[4], which is marking index 2, since nums[2] is equal to 0.
Second 5: Choose index 1 and decrement nums[1] by one. nums becomes [0,0].
Second 6: Mark the index changeIndices[6], which is marking index 1, since nums[1] is equal to 0.
Now all indices have been marked.
It can be shown that it is not possible to mark all indices earlier than the 6th second.
Hence, the answer is 6.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1], changeIndices = [2,2,2]
<strong>Output:</strong> -1
<strong>Explanation:</strong> In this example, it is impossible to mark all indices because index 1 isn&#39;t in changeIndices.
Hence, the answer is -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == changeIndices.length &lt;= 2000</code></li>
	<li><code>1 &lt;= changeIndices[i] &lt;= n</code></li>
</ul>

## Solutions

### Solution 1: Binary Search

We notice that if we can mark all indices within $t$ seconds, then we can also mark all indices within $t' \geq t$ seconds. Therefore, we can use binary search to find the earliest seconds.

We define the left and right boundaries of binary search as $l = 1$ and $r = m + 1$, where $m$ is the length of the array `changeIndices`. For each $t = \frac{l + r}{2}$, we check whether we can mark all indices within $t$ seconds. If we can, we move the right boundary to $t$, otherwise we move the left boundary to $t + 1$. Finally, we judge whether the left boundary is greater than $m$, if it is, return $-1$, otherwise return the left boundary.

The key to the problem is how to judge whether we can mark all indices within $t$ seconds. We can use an array $last$ to record the latest time each index needs to be marked, use a variable $decrement$ to record the current number of times that can be reduced, and use a variable $marked$ to record the number of indices that have been marked.

We traverse the first $t$ elements of the array `changeIndices`, for each element $i$, if $last[i] = s$, then we need to check whether $decrement$ is greater than or equal to $nums[i - 1]$, if it is, we subtract $nums[i - 1]$ from $decrement$, and add one to $marked$; otherwise, we return `False`. If $last[i] \neq s$, then we can temporarily not mark the index, so we add one to $decrement$. Finally, we check whether $marked$ is equal to $n$, if it is, we return `True`, otherwise return `False`.

The time complexity is $O(m \times \log m)$, and the space complexity is $O(n)$. Where $n$ and $m$ are the lengths of `nums` and `changeIndices` respectively.

<!-- tabs:start -->

```python
class Solution:
    def earliestSecondToMarkIndices(
        self, nums: List[int], changeIndices: List[int]
    ) -> int:
        def check(t: int) -> bool:
            decrement = 0
            marked = 0
            last = {i: s for s, i in enumerate(changeIndices[:t])}
            for s, i in enumerate(changeIndices[:t]):
                if last[i] == s:
                    if decrement < nums[i - 1]:
                        return False
                    decrement -= nums[i - 1]
                    marked += 1
                else:
                    decrement += 1
            return marked == len(nums)

        m = len(changeIndices)
        l = bisect_left(range(1, m + 2), True, key=check) + 1
        return -1 if l > m else l
```

```java
class Solution {
    private int[] nums;
    private int[] changeIndices;

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        this.nums = nums;
        this.changeIndices = changeIndices;
        int m = changeIndices.length;
        int l = 1, r = m + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }

    private boolean check(int t) {
        int[] last = new int[nums.length + 1];
        for (int s = 0; s < t; ++s) {
            last[changeIndices[s]] = s;
        }
        int decrement = 0;
        int marked = 0;
        for (int s = 0; s < t; ++s) {
            int i = changeIndices[s];
            if (last[i] == s) {
                if (decrement < nums[i - 1]) {
                    return false;
                }
                decrement -= nums[i - 1];
                ++marked;
            } else {
                ++decrement;
            }
        }
        return marked == nums.length;
    }
}
```

```cpp
class Solution {
public:
    int earliestSecondToMarkIndices(vector<int>& nums, vector<int>& changeIndices) {
        int n = nums.size();
        int last[n + 1];
        auto check = [&](int t) {
            memset(last, 0, sizeof(last));
            for (int s = 0; s < t; ++s) {
                last[changeIndices[s]] = s;
            }
            int decrement = 0, marked = 0;
            for (int s = 0; s < t; ++s) {
                int i = changeIndices[s];
                if (last[i] == s) {
                    if (decrement < nums[i - 1]) {
                        return false;
                    }
                    decrement -= nums[i - 1];
                    ++marked;
                } else {
                    ++decrement;
                }
            }
            return marked == n;
        };

        int m = changeIndices.size();
        int l = 1, r = m + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }
};
```

```go
func earliestSecondToMarkIndices(nums []int, changeIndices []int) int {
	n, m := len(nums), len(changeIndices)
	l := sort.Search(m+1, func(t int) bool {
		last := make([]int, n+1)
		for s, i := range changeIndices[:t] {
			last[i] = s
		}
		decrement, marked := 0, 0
		for s, i := range changeIndices[:t] {
			if last[i] == s {
				if decrement < nums[i-1] {
					return false
				}
				decrement -= nums[i-1]
				marked++
			} else {
				decrement++
			}
		}
		return marked == n
	})
	if l > m {
		return -1
	}
	return l
}
```

```ts
function earliestSecondToMarkIndices(nums: number[], changeIndices: number[]): number {
    const [n, m] = [nums.length, changeIndices.length];
    let [l, r] = [1, m + 1];
    const check = (t: number): boolean => {
        const last: number[] = Array(n + 1).fill(0);
        for (let s = 0; s < t; ++s) {
            last[changeIndices[s]] = s;
        }
        let [decrement, marked] = [0, 0];
        for (let s = 0; s < t; ++s) {
            const i = changeIndices[s];
            if (last[i] === s) {
                if (decrement < nums[i - 1]) {
                    return false;
                }
                decrement -= nums[i - 1];
                ++marked;
            } else {
                ++decrement;
            }
        }
        return marked === n;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > m ? -1 : l;
}
```

<!-- tabs:end -->

<!-- end -->
