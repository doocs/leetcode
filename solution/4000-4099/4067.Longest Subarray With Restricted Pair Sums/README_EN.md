---
comments: true
difficulty: Medium
rating: 1917
source: Weekly Contest 521 Q3
tags:
    - Array
    - Hash Table
    - Sliding Window
---

<!-- problem:start -->

# [4067. Longest Subarray With Restricted Pair Sums](https://leetcode.com/problems/longest-subarray-with-restricted-pair-sums)

[中文文档](/solution/4000-4099/4067.Longest%20Subarray%20With%20Restricted%20Pair%20Sums/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> <code>nums[l..r]</code> is valid if there are no three <strong>distinct</strong> indices <code>i</code>, <code>j</code>, and <code>k</code> such that <code>l &lt;= i, j, k &lt;= r</code> and:</p>

<ul>
	<li><code>nums[i] + nums[j] == nums[k]</code></li>
</ul>

<p>Return the <strong>maximum</strong> length of a valid subarray of <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5,3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Consider the subarray <code>[3, 5, 3]</code>. The pairs of elements at distinct indices have the following sums:</p>

<ul>
	<li><code>3 + 5 = 8</code></li>
	<li><code>3 + 3 = 6</code>, using the two different occurrences of 3</li>
	<li><code>5 + 3 = 8</code></li>
</ul>

<p>None of these sums is an element at the remaining index, so the subarray is valid.</p>

<p>Every subarray of length 4 contains 2, 3, and 5 at distinct indices, where <code>2 + 3 = 5</code>. Therefore, no longer valid subarray exists, and the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,5,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The sums obtained from every pair of elements at distinct indices are 7, 8, 9, 9, 10, and 11. None of these values appears at the remaining index, so the entire array is valid.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 500</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

<!-- thinking:start -->

> **Thinking**
>
> $n\le 1000$. Checking three indices inside every subarray adds another quadratic factor on top of the two endpoints and does not finish in time.
>
> Once $a+b=c$ occurs, every longer interval that contains it is also invalid, so the left end only moves right as the right end moves right. A newly added $x$ breaks the window only when it equals the sum of two values already inside, or the difference of two such values.
>
> Keep the number of pair sums and absolute differences in the window. If $x$ hits either count, delete elements from the left and remove the pairs they belong to. Each pair is inserted once and deleted once.

<!-- thinking:end -->

The window $\textit{nums}[l..r]$ is valid exactly when no three distinct indices have two elements summing to the third. An invalid segment stays invalid in every longer interval that contains it, so the left end only increases as the right end grows.

Let $m=\max(\textit{nums})$. $\textit{cntS}[s]$ is the number of pairs in the current window whose values sum to $s$, and $\textit{cntD}[d]$ is the number of pairs whose absolute difference is $d$. Sums are at most $2m$ and differences are at most $m$.

The value at the right end is $x$, and the window before it is added is $[l,r)$. Adding $x$ makes the window invalid exactly when some pair already sums to $x$, or some pair already differs by $x$. The first case means $x$ is the sum. The second means $x$ is an addend and the other two elements are still in the window. Every value is positive, so the absolute difference is enough.

While that happens, remove the left element $y$ and subtract the sums and differences of $y$ with each element that remains in $[l,r)$. After the window is valid again, add the sums and differences of $x$ with each element of $[l,r)$, and update the answer with $r-l+1$.

Each pair is inserted when the later end enters and deleted when the earlier end leaves. The time complexity is $O(n^2)$ and the space complexity is $O(m)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubarray(self, nums: List[int]) -> int:
        mx = max(nums)
        cnt_s = [0] * (mx << 1 | 1)
        cnt_d = [0] * (mx + 1)
        ans = l = 0

        for r, x in enumerate(nums):
            while cnt_s[x] > 0 or cnt_d[x] > 0:
                y = nums[l]
                l += 1
                for z in nums[l:r]:
                    cnt_s[y + z] -= 1
                    cnt_d[abs(y - z)] -= 1

            for y in nums[l:r]:
                cnt_s[x + y] += 1
                cnt_d[abs(x - y)] += 1

            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int maxSubarray(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        int[] cntS = new int[(mx << 1) | 1];
        int[] cntD = new int[mx + 1];
        int ans = 0;
        int l = 0;

        for (int r = 0; r < nums.length; r++) {
            int x = nums[r];
            while (cntS[x] > 0 || cntD[x] > 0) {
                int y = nums[l++];
                for (int i = l; i < r; i++) {
                    int z = nums[i];
                    cntS[y + z]--;
                    cntD[Math.abs(y - z)]--;
                }
            }

            for (int i = l; i < r; i++) {
                int y = nums[i];
                cntS[x + y]++;
                cntD[Math.abs(x - y)]++;
            }

            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSubarray(vector<int>& nums) {
        int mx = ranges::max(nums);

        vector<int> cntS((mx << 1) | 1);
        vector<int> cntD(mx + 1);

        int ans = 0;
        int l = 0;

        for (int r = 0; r < nums.size(); r++) {
            int x = nums[r];

            while (cntS[x] > 0 || cntD[x] > 0) {
                int y = nums[l++];

                for (int i = l; i < r; i++) {
                    int z = nums[i];
                    cntS[y + z]--;
                    cntD[abs(y - z)]--;
                }
            }

            for (int i = l; i < r; i++) {
                int y = nums[i];
                cntS[x + y]++;
                cntD[abs(x - y)]++;
            }

            ans = max(ans, r - l + 1);
        }

        return ans;
    }
};
```

#### Go

```go
func maxSubarray(nums []int) int {
	mx := slices.Max(nums)

	cntS := make([]int, (mx<<1)|1)
	cntD := make([]int, mx+1)

	ans := 0
	l := 0

	for r, x := range nums {
		for cntS[x] > 0 || cntD[x] > 0 {
			y := nums[l]
			l++

			for i := l; i < r; i++ {
				z := nums[i]
				cntS[y+z]--

				d := y - z
				if d < 0 {
					d = -d
				}
				cntD[d]--
			}
		}

		for i := l; i < r; i++ {
			y := nums[i]
			cntS[x+y]++

			d := x - y
			if d < 0 {
				d = -d
			}
			cntD[d]++
		}

		ans = max(ans, r-l+1)
	}

	return ans
}
```

#### TypeScript

```ts
function maxSubarray(nums: number[]): number {
    let mx = 0;
    for (const x of nums) {
        mx = Math.max(mx, x);
    }

    const cntS = new Array((mx << 1) | 1).fill(0);
    const cntD = new Array(mx + 1).fill(0);

    let ans = 0;
    let l = 0;

    for (let r = 0; r < nums.length; r++) {
        const x = nums[r];

        while (cntS[x] > 0 || cntD[x] > 0) {
            const y = nums[l++];
            for (let i = l; i < r; i++) {
                const z = nums[i];
                cntS[y + z]--;
                cntD[Math.abs(y - z)]--;
            }
        }

        for (let i = l; i < r; i++) {
            const y = nums[i];
            cntS[x + y]++;
            cntD[Math.abs(x - y)]++;
        }

        ans = Math.max(ans, r - l + 1);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
