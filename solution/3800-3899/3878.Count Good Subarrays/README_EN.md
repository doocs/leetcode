---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3878.Count%20Good%20Subarrays/README_EN.md
---

<!-- problem:start -->

# [3878. Count Good Subarrays](https://leetcode.com/problems/count-good-subarrays)

[中文文档](/solution/3800-3899/3878.Count%20Good%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> is called <strong>good</strong> if the <strong>bitwise OR</strong> of all its elements is equal to <strong>at least one</strong> element present in that subarray.</p>

<p>Return the number of good subarrays in <code>nums</code>.</p>

<p>Here, the bitwise OR of two integers <code>a</code> and <code>b</code> is denoted by <code>a | b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarrays of <code>nums</code> are:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Subarray</th>
			<th style="border: 1px solid black;">Bitwise OR</th>
			<th style="border: 1px solid black;">Present in Subarray</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[4]</code></td>
			<td style="border: 1px solid black;"><code>4 = 4</code></td>
			<td style="border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2]</code></td>
			<td style="border: 1px solid black;"><code>2 = 2</code></td>
			<td style="border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[3]</code></td>
			<td style="border: 1px solid black;"><code>3 = 3</code></td>
			<td style="border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[4, 2]</code></td>
			<td style="border: 1px solid black;"><code>4 | 2 = 6</code></td>
			<td style="border: 1px solid black;">No</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[2, 3]</code></td>
			<td style="border: 1px solid black;"><code>2 | 3 = 3</code></td>
			<td style="border: 1px solid black;">Yes</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;"><code>[4, 2, 3]</code></td>
			<td style="border: 1px solid black;"><code>4 | 2 | 3 = 7</code></td>
			<td style="border: 1px solid black;">No</td>
		</tr>
	</tbody>
</table>

<p>Thus, the good subarrays of <code>nums</code> are <code>[4]</code>, <code>[2]</code>, <code>[3]</code> and <code>[2, 3]</code>. Thus, the answer is 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>Any subarray of <code>nums</code> containing 3 has bitwise OR equal to 3, and subarrays containing only 1 have bitwise OR equal to 1.</p>

<p>In both cases, the result is present in the subarray, so all subarrays are good, and the answer is 6.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Monotonic Stack + Contribution Enumeration

We can enumerate each element $\textit{nums}[i]$ as the bitwise OR result of a subarray, and count how many subarrays have a bitwise OR exactly equal to $\textit{nums}[i]$.

If the bitwise OR of a subarray is $\textit{nums}[i]$, then every element in the subarray must satisfy:

$$
\textit{nums}[k] \mid \textit{nums}[i] = \textit{nums}[i]
$$

That is, every element in the subarray must be a subset of $\textit{nums}[i]$ (in terms of bits). We can use a monotonic stack to find the left boundary $l[i]$ and right boundary $r[i]$ for each element $\textit{nums}[i]$, such that all elements in the interval $(l[i], r[i])$ satisfy the above condition, while $\textit{nums}[l[i]]$ and $\textit{nums}[r[i]]$ do not. The number of subarrays with $\textit{nums}[i]$ as the bitwise OR result is then $(i - l[i]) \cdot (r[i] - i)$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodSubarrays(self, nums: list[int]) -> int:
        n = len(nums)
        l = [-1] * n
        stk = []
        for i, x in enumerate(nums):
            while stk and nums[stk[-1]] < x and (nums[stk[-1]] | x) == x:
                stk.pop()
            l[i] = stk[-1] if stk else -1
            stk.append(i)
        r = [n] * n
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and (nums[stk[-1]] | nums[i]) == nums[i]:
                stk.pop()
            r[i] = stk[-1] if stk else n
            stk.append(i)
        return sum((i - l[i]) * (r[i] - i) for i in range(n))
```

#### Java

```java
class Solution {
    public long countGoodSubarrays(int[] nums) {
        int n = nums.length;

        int[] l = new int[n];
        Arrays.fill(l, -1);
        Deque<Integer> stk = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] < x && (nums[stk.peek()] | x) == x) {
                stk.pop();
            }
            l[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(i);
        }

        int[] r = new int[n];
        Arrays.fill(r, n);
        stk.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.isEmpty() && (nums[stk.peek()] | nums[i]) == nums[i]) {
                stk.pop();
            }
            r[i] = stk.isEmpty() ? n : stk.peek();
            stk.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) (i - l[i]) * (r[i] - i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countGoodSubarrays(vector<int>& nums) {
        int n = nums.size();

        vector<int> l(n, -1);
        vector<int> stk;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stk.empty() && nums[stk.back()] < x && (nums[stk.back()] | x) == x) {
                stk.pop_back();
            }
            l[i] = stk.empty() ? -1 : stk.back();
            stk.push_back(i);
        }

        vector<int> r(n, n);
        stk.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.empty() && (nums[stk.back()] | nums[i]) == nums[i]) {
                stk.pop_back();
            }
            r[i] = stk.empty() ? n : stk.back();
            stk.push_back(i);
        }

        long long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += 1LL * (i - l[i]) * (r[i] - i);
        }
        return ans;
    }
};
```

#### Go

```go
func countGoodSubarrays(nums []int) int64 {
	n := len(nums)

	l := make([]int, n)
	for i := range l {
		l[i] = -1
	}
	stk := []int{}

	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] < x &&
			(nums[stk[len(stk)-1]]|x) == x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			l[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}

	r := make([]int, n)
	for i := range r {
		r[i] = n
	}
	stk = stk[:0]

	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 &&
			(nums[stk[len(stk)-1]]|nums[i]) == nums[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			r[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}

	var ans int64
	for i := 0; i < n; i++ {
		ans += int64(i-l[i]) * int64(r[i]-i)
	}
	return ans
}
```

#### TypeScript

```ts
function countGoodSubarrays(nums: number[]): number {
    const n = nums.length;

    const l = new Array(n).fill(-1);
    const stk: number[] = [];

    for (let i = 0; i < n; i++) {
        const x = nums[i];
        while (
            stk.length &&
            nums[stk[stk.length - 1]] < x &&
            (nums[stk[stk.length - 1]] | x) === x
        ) {
            stk.pop();
        }
        l[i] = stk.length ? stk[stk.length - 1] : -1;
        stk.push(i);
    }

    const r = new Array(n).fill(n);
    stk.length = 0;

    for (let i = n - 1; i >= 0; i--) {
        while (stk.length && (nums[stk[stk.length - 1]] | nums[i]) === nums[i]) {
            stk.pop();
        }
        r[i] = stk.length ? stk[stk.length - 1] : n;
        stk.push(i);
    }

    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans += (i - l[i]) * (r[i] - i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
