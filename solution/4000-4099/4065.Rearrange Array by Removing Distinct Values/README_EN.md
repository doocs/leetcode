---
comments: true
difficulty: Easy
rating: 1172
source: Weekly Contest 521 Q1
tags:
    - Array
    - Hash Table
    - Counting
    - Ordered Set
    - Sorting
    - Simulation
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [4065. Rearrange Array by Removing Distinct Values](https://leetcode.com/problems/rearrange-array-by-removing-distinct-values)

[中文文档](/solution/4000-4099/4065.Rearrange%20Array%20by%20Removing%20Distinct%20Values/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You start with an <strong>empty</strong> array <code>ans</code>. Repeat the following operation until <code>nums</code> is <strong>empty</strong>:</p>

<ul>
	<li>Identify <strong>all</strong> <strong>distinct</strong> values currently present in <code>nums</code>.</li>
	<li>Remove <strong>one</strong> occurrence of every <strong>distinct</strong> value currently in <code>nums</code>, and append those values to <code>ans</code> in <strong>ascending</strong> order.</li>
</ul>

<p>Return the array <code>ans</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,3,2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,1,3,3]</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">Operation</th>
			<th scope="col" style="text-align:center;">Appended to <code>ans</code></th>
			<th scope="col" style="text-align:center;"><code>nums</code> after</th>
			<th scope="col" style="text-align:center;"><code>ans</code> after</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">1, 2, 3</td>
			<td style="text-align:center;"><code>[3, 1, 3]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">1, 3</td>
			<td style="text-align:center;"><code>[3]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3, 1, 3]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;"><code>[]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3, 1, 3, 3]</code></td>
		</tr>
	</tbody>
</table>

<p><code>nums</code> is now empty, so the answer is <code>[1, 2, 3, 1, 3, 3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,7,4,4,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">[4,7,4,7,4]</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">Operation</th>
			<th scope="col" style="text-align:center;">Appended to <code>ans</code></th>
			<th scope="col" style="text-align:center;"><code>nums</code> after</th>
			<th scope="col" style="text-align:center;"><code>ans</code> after</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">4, 7</td>
			<td style="text-align:center;"><code>[7, 4, 4]</code></td>
			<td style="text-align:center;"><code>[4, 7]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">4, 7</td>
			<td style="text-align:center;"><code>[4]</code></td>
			<td style="text-align:center;"><code>[4, 7, 4, 7]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;"><code>[]</code></td>
			<td style="text-align:center;"><code>[4, 7, 4, 7, 4]</code></td>
		</tr>
	</tbody>
</table>

<p><code>nums</code> is now empty, so the answer is <code>[4, 7, 4, 7, 4]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

<!-- thinking:start -->

> **Thinking**
>
> Both $n$ and every value are at most $100$, so taking one copy of each remaining value per round fits the limits. Each round has to collect the values still present and delete one of each in ascending order. Searching and deleting inside the original array keeps moving the indices.
>
> A value is emitted once for every time it occurs, and the order inside a round depends only on the value, not on its original index.
>
> Count by value. The range is $[1,m]$. Scan from small to large, and while a count is still positive, append that value and decrease the count. Repeat until the answer has length $n$. Each scan is one operation.

<!-- thinking:end -->

Let $m=\max(\textit{nums})$ and let $\textit{cnt}[x]$ be the number of times $x$ occurs. Round $k$, starting from $0$, appends every value that still remains, in ascending order. Those are exactly the values whose original frequency is greater than $k$.

Store the frequencies in an array of length $m+1$. While the answer has fewer than $n$ elements, scan $x$ from $1$ to $m$. If $\textit{cnt}[x]>0$, append $x$ and decrease the count. One scan is one operation, and the appended values are already sorted.

The time complexity is $O(nm)$ and the space complexity is $O(m)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeArray(self, nums: list[int]) -> list[int]:
        mx = max(nums)
        cnt = [0] * (mx + 1)
        for x in nums:
            cnt[x] += 1

        ans = []
        while len(ans) < len(nums):
            for x in range(1, mx + 1):
                if cnt[x]:
                    ans.append(x)
                    cnt[x] -= 1
        return ans
```

#### Java

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : nums) {
            cnt[x]++;
        }

        int[] ans = new int[nums.length];
        int idx = 0;
        while (idx < nums.length) {
            for (int x = 1; x <= mx; x++) {
                if (cnt[x] > 0) {
                    ans[idx++] = x;
                    cnt[x]--;
                }
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
    vector<int> rearrangeArray(vector<int>& nums) {
        int mx = ranges::max(nums);
        vector<int> cnt(mx + 1);
        for (int x : nums) {
            cnt[x]++;
        }

        vector<int> ans;
        while (ans.size() < nums.size()) {
            for (int x = 1; x <= mx; x++) {
                if (cnt[x]) {
                    ans.push_back(x);
                    cnt[x]--;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func rearrangeArray(nums []int) []int {
	mx := slices.Max(nums)

	cnt := make([]int, mx+1)
	for _, x := range nums {
		cnt[x]++
	}

	ans := make([]int, 0, len(nums))
	for len(ans) < len(nums) {
		for x := 1; x <= mx; x++ {
			if cnt[x] > 0 {
				ans = append(ans, x)
				cnt[x]--
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function rearrangeArray(nums: number[]): number[] {
    const mx = Math.max(...nums);
    const cnt = new Array(mx + 1).fill(0);

    for (const x of nums) {
        cnt[x]++;
    }

    const ans: number[] = [];
    while (ans.length < nums.length) {
        for (let x = 1; x <= mx; x++) {
            if (cnt[x]) {
                ans.push(x);
                cnt[x]--;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
