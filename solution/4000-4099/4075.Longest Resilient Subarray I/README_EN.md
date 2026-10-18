---
comments: true
difficulty: Medium
rating: 1567
source: Biweekly Contest 193 Q2
---

<!-- problem:start -->

# [4075. Longest Resilient Subarray I](https://leetcode.com/problems/longest-resilient-subarray-i)

[中文文档](/solution/4000-4099/4075.Longest%20Resilient%20Subarray%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>A <span data-keyword="subarray-nonempty">subarray</span> is <strong>resilient</strong> if, for <strong>every</strong> position in it, deleting the element at that position leaves the <strong>remaining</strong> elements with a <strong>sum divisible</strong> by <code>k</code>.</p>

<p>For a subarray of length 1, the remaining sum is 0, which is divisible by <code>k</code>.</p>

<p>Return the length of the <strong>longest resilient subarray</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,6,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[2, 4, 6]</code> sums to 12. Deleting 2, 4, or 6 leaves a sum of 10, 8, or 6, respectively, each of which is divisible by 2. Therefore, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,7,1], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[1, 4, 7, 1]</code> sums to 13. Deleting the first 1, the 4, the 7, or the last 1 leaves a sum of 12, 9, 6, or 12, respectively, each of which is divisible by 3. Therefore, the answer is 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,4,8], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[5, 5]</code> is not resilient, since deleting either 5 leaves a sum of 5, which is not divisible by 4.</p>

<p>The subarray <code>[4, 8]</code> sums to 12. Deleting 4 or 8 leaves a sum of 8 or 4, respectively, each of which is divisible by 4. Therefore, the answer is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Congruent Runs

<!-- thinking:start -->

> **Thinking**
>
> A subarray can be as long as $2500$. Enumerating every interval and then checking the sum after each deletion is cubic, which is too much for this limit.
>
> Requiring $\textit{sum}-a_i$ to be divisible by $k$ at every index means every $a_i$ is congruent to $\textit{sum}$ modulo $k$. All elements in the subarray therefore share one residue $r$, and the length $L$ must also satisfy $(L-1)r\equiv 0\pmod{k}$.
>
> Equal residues form contiguous runs. Inside a run the length grows by $1$, and the condition depends only on that length and $r$, so one pass over each run records every feasible length.

<!-- thinking:end -->

Reduce every element modulo $k$. Equal residues form contiguous runs. Let $r$ be the residue of the current run and $\textit{cnt}$ its current length. A run of length $1$ is always resilient, because the remaining sum is $0$. Whenever

$$
r\cdot(\textit{cnt}-1)\equiv 0\pmod{k},
$$

the prefix of length $\textit{cnt}$ is resilient, and the answer is updated with $\textit{cnt}$. A change of residue starts a new run.

The time complexity is $O(n)$ and the space complexity is $O(1)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def resilientSubarray(self, nums: list[int], k: int) -> int:
        ans = cnt = 0
        for i, x in enumerate(nums):
            x %= k
            if i and nums[i - 1] % k == x:
                cnt += 1
            else:
                cnt = 1
            if x * (cnt - 1) % k == 0:
                ans = max(ans, cnt)
        return ans
```

#### Java

```java
class Solution {
    public int resilientSubarray(int[] nums, int k) {
        int ans = 0, cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i] % k;
            if (i > 0 && nums[i - 1] % k == x) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (x * (cnt - 1) % k == 0) {
                ans = Math.max(ans, cnt);
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
    int resilientSubarray(vector<int>& nums, int k) {
        int ans = 0, cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            int x = nums[i] % k;
            if (i > 0 && nums[i - 1] % k == x) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (x * (cnt - 1) % k == 0) {
                ans = max(ans, cnt);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func resilientSubarray(nums []int, k int) int {
	ans, cnt := 0, 0
	for i, v := range nums {
		x := v % k
		if i > 0 && nums[i-1]%k == x {
			cnt++
		} else {
			cnt = 1
		}
		if x*(cnt-1)%k == 0 {
			ans = max(ans, cnt)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function resilientSubarray(nums: number[], k: number): number {
    let ans = 0,
        cnt = 0;
    for (let i = 0; i < nums.length; i++) {
        const x = nums[i] % k;
        if (i > 0 && nums[i - 1] % k === x) {
            cnt++;
        } else {
            cnt = 1;
        }
        if ((x * (cnt - 1)) % k === 0) {
            ans = Math.max(ans, cnt);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
