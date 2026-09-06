---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4044.Count%20Good%20Cyclic%20Rotations/README_EN.md
---

<!-- problem:start -->

# [4044. Count Good Cyclic Rotations](https://leetcode.com/problems/count-good-cyclic-rotations)

[中文文档](/solution/4000-4099/4044.Count%20Good%20Cyclic%20Rotations/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of even length <code>n</code>.</p>

<p>A <strong>cyclic rotation</strong> of <code>nums</code> is obtained by choosing a <span data-keyword="array-prefix">prefix</span> of <code>nums</code> whose length is between 0 and <code>n - 1</code> (inclusive), and moving it to the end of the array while preserving the order of all elements.</p>

<p>A cyclic rotation is <strong>good</strong> if the sum of its first <code>n / 2</code> elements is <strong>strictly greater</strong> than the sum of its last <code>n / 2</code> elements.</p>

<p>Return the number of cyclic rotations of <code>nums</code> that are good.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The cyclic rotations of <code>nums</code> are:</p>

<table>
	<thead>
		<tr>
			<th style="text-align: center; padding: 6px 12px;">Cyclic rotation</th>
			<th style="text-align: center; padding: 6px 12px;">Sum of first <code>n / 2</code> elements</th>
			<th style="text-align: center; padding: 6px 12px;">Sum of last <code>n / 2</code> elements</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[1, 2, 3, 4, 5, 6]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>1 + 2 + 3 = 6</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>4 + 5 + 6 = 15</code></td>
		</tr>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[2, 3, 4, 5, 6, 1]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>2 + 3 + 4 = 9</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>5 + 6 + 1 = 12</code></td>
		</tr>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[3, 4, 5, 6, 1, 2]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>3 + 4 + 5 = 12</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>6 + 1 + 2 = 9</code></td>
		</tr>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[4, 5, 6, 1, 2, 3]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>4 + 5 + 6 = 15</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>1 + 2 + 3 = 6</code></td>
		</tr>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[5, 6, 1, 2, 3, 4]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>5 + 6 + 1 = 12</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>2 + 3 + 4 = 9</code></td>
		</tr>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[6, 1, 2, 3, 4, 5]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>6 + 1 + 2 = 9</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>3 + 4 + 5 = 12</code></td>
		</tr>
	</tbody>
</table>

<p>The first half has a greater sum than the second half for 3 rotations. Thus, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The cyclic rotations of <code>nums</code> are:</p>

<table>
	<thead>
		<tr>
			<th style="text-align: center; padding: 6px 12px;">Cyclic rotation</th>
			<th style="text-align: center; padding: 6px 12px;">Sum of first <code>n / 2</code> elements</th>
			<th style="text-align: center; padding: 6px 12px;">Sum of last <code>n / 2</code> elements</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[1, 2, 1, 2]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>1 + 2 = 3</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>1 + 2 = 3</code></td>
		</tr>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[2, 1, 2, 1]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>2 + 1 = 3</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>2 + 1 = 3</code></td>
		</tr>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[1, 2, 1, 2]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>1 + 2 = 3</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>1 + 2 = 3</code></td>
		</tr>
		<tr>
			<td style="text-align: center; padding: 6px 12px;"><code>[2, 1, 2, 1]</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>2 + 1 = 3</code></td>
			<td style="text-align: center; padding: 6px 12px;"><code>2 + 1 = 3</code></td>
		</tr>
	</tbody>
</table>

<p>No cyclic rotation is good because the two sums are equal for every rotation. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>n</code> is even.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

Let $n$ be the length of the array and $m = n / 2$. First compute the sum $l$ of the first $m$ elements of the original array and the sum $r$ of the last $m$ elements. If $l > r$, increment the answer by $1$.

Then start from the original array and cyclically shift it left by one position, $n - 1$ times in total. On the $i$-th shift ($i$ starts from $0$), the first half loses $\textit{nums}[i]$ and gains $\textit{nums}[(i + m) \bmod n]$, while the second half does the opposite. Update $l$ and $r$ in $O(1)$ time, and increment the answer whenever $l > r$.

The time complexity is $O(n)$ and the space complexity is $O(1)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodRotations(self, nums: list[int]) -> int:
        n = len(nums)
        m = n // 2
        l = sum(nums[:m])
        r = sum(nums[m:])
        ans = int(l > r)
        for i in range(n - 1):
            l -= nums[i]
            r += nums[i]
            l += nums[(i + m) % n]
            r -= nums[(i + m) % n]
            ans += int(l > r)
        return ans
```

#### Java

```java
class Solution {
    public int countGoodRotations(int[] nums) {
        int n = nums.length;
        int m = n / 2;

        long l = 0, r = 0;
        for (int i = 0; i < m; i++) {
            l += nums[i];
        }
        for (int i = m; i < n; i++) {
            r += nums[i];
        }

        int ans = l > r ? 1 : 0;

        for (int i = 0; i < n - 1; i++) {
            l -= nums[i];
            r += nums[i];
            l += nums[(i + m) % n];
            r -= nums[(i + m) % n];
            ans += l > r ? 1 : 0;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countGoodRotations(vector<int>& nums) {
        int n = nums.size();
        int m = n / 2;

        long long l = 0, r = 0;
        for (int i = 0; i < m; i++) {
            l += nums[i];
        }
        for (int i = m; i < n; i++) {
            r += nums[i];
        }

        int ans = l > r;

        for (int i = 0; i < n - 1; i++) {
            l -= nums[i];
            r += nums[i];
            l += nums[(i + m) % n];
            r -= nums[(i + m) % n];
            ans += l > r;
        }

        return ans;
    }
};
```

#### Go

```go
func countGoodRotations(nums []int) int {
	n := len(nums)
	m := n / 2

	var l, r int64
	for i := 0; i < m; i++ {
		l += int64(nums[i])
	}
	for i := m; i < n; i++ {
		r += int64(nums[i])
	}

	ans := 0
	if l > r {
		ans++
	}

	for i := 0; i < n-1; i++ {
		l -= int64(nums[i])
		r += int64(nums[i])
		l += int64(nums[(i+m)%n])
		r -= int64(nums[(i+m)%n])
		if l > r {
			ans++
		}
	}

	return ans
}
```

#### TypeScript

```ts
function countGoodRotations(nums: number[]): number {
    const n = nums.length;
    const m = Math.floor(n / 2);

    let l = 0,
        r = 0;
    for (let i = 0; i < m; i++) {
        l += nums[i];
    }
    for (let i = m; i < n; i++) {
        r += nums[i];
    }

    let ans = l > r ? 1 : 0;

    for (let i = 0; i < n - 1; i++) {
        l -= nums[i];
        r += nums[i];
        l += nums[(i + m) % n];
        r -= nums[(i + m) % n];
        ans += l > r ? 1 : 0;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
