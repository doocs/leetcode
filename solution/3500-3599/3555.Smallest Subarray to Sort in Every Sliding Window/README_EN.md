---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3555.Smallest%20Subarray%20to%20Sort%20in%20Every%20Sliding%20Window/README_EN.md
tags:
    - Stack
    - Greedy
    - Array
    - Two Pointers
    - Sorting
    - Monotonic Stack
---

<!-- problem:start -->

# [3555. Smallest Subarray to Sort in Every Sliding Window 🔒](https://leetcode.com/problems/smallest-subarray-to-sort-in-every-sliding-window)

[中文文档](/solution/3500-3599/3555.Smallest%20Subarray%20to%20Sort%20in%20Every%20Sliding%20Window/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>For each contiguous <span data-keyword="subarray">subarray</span> of length <code>k</code>, determine the <strong>minimum</strong> length of a continuous segment that must be sorted so that the entire window becomes <strong>non‑decreasing</strong>; if the window is already sorted, its required length is zero.</p>

<p>Return an array of length <code>n &minus; k + 1</code> where each element corresponds to the answer for its window.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2,4,5], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0...2] = [1, 3, 2]</code>. Sort <code>[3, 2]</code> to get <code>[1, 2, 3]</code>, the answer is 2.</li>
	<li><code>nums[1...3] = [3, 2, 4]</code>. Sort <code>[3, 2]</code> to get <code>[2, 3, 4]</code>, the answer is 2.</li>
	<li><code>nums[2...4] = [2, 4, 5]</code> is already sorted, so the answer is 0.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,4,3,2,1], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0...3] = [5, 4, 3, 2]</code>. The whole subarray must be sorted, so the answer is 4.</li>
	<li><code>nums[1...4] = [4, 3, 2, 1]</code>. The whole subarray must be sorted, so the answer is 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Maintaining Left Maximum and Right Minimum

We can enumerate every subarray of length $k$. For each subarray $nums[i...i + k - 1]$, we need to find the smallest continuous segment such that, after sorting it, the entire subarray becomes non-decreasing.

For the subarray $nums[i...i + k - 1]$, we can traverse from left to right, maintaining a maximum value $mx$. If the current value is less than $mx$, it means the current value is not in the correct position, so we update the right boundary $r$ to the current position. Similarly, we can traverse from right to left, maintaining a minimum value $mi$. If the current value is greater than $mi$, it means the current value is not in the correct position, so we update the left boundary $l$ to the current position. Initially, both $l$ and $r$ are set to $-1$. If neither $l$ nor $r$ is updated, it means the subarray is already sorted, so we return $0$; otherwise, we return $r - l + 1$.

The time complexity is $O(n \times k)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSubarraySort(self, nums: List[int], k: int) -> List[int]:
        def f(i: int, j: int) -> int:
            mi, mx = inf, -inf
            l = r = -1
            for k in range(i, j + 1):
                if mx > nums[k]:
                    r = k
                else:
                    mx = nums[k]
                p = j - k + i
                if mi < nums[p]:
                    l = p
                else:
                    mi = nums[p]
            return 0 if r == -1 else r - l + 1

        n = len(nums)
        return [f(i, i + k - 1) for i in range(n - k + 1)]
```

#### Java

```java
class Solution {
    private int[] nums;
    private final int inf = 1 << 30;

    public int[] minSubarraySort(int[] nums, int k) {
        this.nums = nums;
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; ++i) {
            ans[i] = f(i, i + k - 1);
        }
        return ans;
    }

    private int f(int i, int j) {
        int mi = inf, mx = -inf;
        int l = -1, r = -1;
        for (int k = i; k <= j; ++k) {
            if (nums[k] < mx) {
                r = k;
            } else {
                mx = nums[k];
            }
            int p = j - k + i;
            if (nums[p] > mi) {
                l = p;
            } else {
                mi = nums[p];
            }
        }
        return r == -1 ? 0 : r - l + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minSubarraySort(vector<int>& nums, int k) {
        const int inf = 1 << 30;
        int n = nums.size();
        auto f = [&](int i, int j) -> int {
            int mi = inf, mx = -inf;
            int l = -1, r = -1;
            for (int k = i; k <= j; ++k) {
                if (nums[k] < mx) {
                    r = k;
                } else {
                    mx = nums[k];
                }
                int p = j - k + i;
                if (nums[p] > mi) {
                    l = p;
                } else {
                    mi = nums[p];
                }
            }
            return r == -1 ? 0 : r - l + 1;
        };
        vector<int> ans;
        for (int i = 0; i < n - k + 1; ++i) {
            ans.push_back(f(i, i + k - 1));
        }
        return ans;
    }
};
```

#### Go

```go
func minSubarraySort(nums []int, k int) []int {
	const inf = 1 << 30
	n := len(nums)
	f := func(i, j int) int {
		mi := inf
		mx := -inf
		l, r := -1, -1
		for p := i; p <= j; p++ {
			if nums[p] < mx {
				r = p
			} else {
				mx = nums[p]
			}
			q := j - p + i
			if nums[q] > mi {
				l = q
			} else {
				mi = nums[q]
			}
		}
		if r == -1 {
			return 0
		}
		return r - l + 1
	}

	ans := make([]int, 0, n-k+1)
	for i := 0; i <= n-k; i++ {
		ans = append(ans, f(i, i+k-1))
	}
	return ans
}
```

#### TypeScript

```ts
function minSubarraySort(nums: number[], k: number): number[] {
    const inf = Infinity;
    const n = nums.length;
    const f = (i: number, j: number): number => {
        let mi = inf;
        let mx = -inf;
        let l = -1,
            r = -1;
        for (let p = i; p <= j; ++p) {
            if (nums[p] < mx) {
                r = p;
            } else {
                mx = nums[p];
            }
            const q = j - p + i;
            if (nums[q] > mi) {
                l = q;
            } else {
                mi = nums[q];
            }
        }
        return r === -1 ? 0 : r - l + 1;
    };

    const ans: number[] = [];
    for (let i = 0; i <= n - k; ++i) {
        ans.push(f(i, i + k - 1));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
