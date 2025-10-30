---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3724.Minimum%20Operations%20to%20Transform%20Array/README_EN.md
rating: 1789
source: Biweekly Contest 168 Q3
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [3724. Minimum Operations to Transform Array](https://leetcode.com/problems/minimum-operations-to-transform-array)

[中文文档](/solution/3700-3799/3724.Minimum%20Operations%20to%20Transform%20Array/README.md)

## Description

<!-- description:start -->

<p data-end="180" data-start="93">You are given two integer arrays <code>nums1</code> of length <code>n</code> and <code>nums2</code> of length <code>n + 1</code>.</p>

<p>You want to transform <code>nums1</code> into <code>nums2</code> using the <strong>minimum</strong> number of operations.</p>

<p>You may perform the following operations <strong>any</strong> number of times, each time choosing an index <code>i</code>:</p>

<ul>
	<li><strong>Increase</strong> <code>nums1[i]</code> by 1.</li>
	<li><strong>Decrease</strong> <code>nums1[i]</code> by 1.</li>
	<li><strong>Append</strong> <code>nums1[i]</code> to the <strong>end</strong> of the array.</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to transform <code>nums1</code> into <code>nums2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [2,8], nums2 = [1,7,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;">Step</th>
			<th align="center" style="border: 1px solid black;"><code>i</code></th>
			<th align="center" style="border: 1px solid black;">Operation</th>
			<th align="center" style="border: 1px solid black;"><code>nums1[i]</code></th>
			<th align="center" style="border: 1px solid black;">Updated <code>nums1</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">Append</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">[2, 8, 2]</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">Decrement</td>
			<td align="center" style="border: 1px solid black;">Decreases to 1</td>
			<td align="center" style="border: 1px solid black;">[1, 8, 2]</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">Decrement</td>
			<td align="center" style="border: 1px solid black;">Decreases to 7</td>
			<td align="center" style="border: 1px solid black;">[1, 7, 2]</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">4</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">Increment</td>
			<td align="center" style="border: 1px solid black;">Increases to 3</td>
			<td align="center" style="border: 1px solid black;">[1, 7, 3]</td>
		</tr>
	</tbody>
</table>

<p>Thus, after 4 operations <code>nums1</code> is transformed into <code>nums2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [1,3,6], nums2 = [2,4,5,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;">Step</th>
			<th align="center" style="border: 1px solid black;"><code>i</code></th>
			<th align="center" style="border: 1px solid black;">Operation</th>
			<th align="center" style="border: 1px solid black;"><code>nums1[i]</code></th>
			<th align="center" style="border: 1px solid black;">Updated <code>nums1</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">Append</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">[1, 3, 6, 3]</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">Increment</td>
			<td align="center" style="border: 1px solid black;">Increases to 2</td>
			<td align="center" style="border: 1px solid black;">[2, 3, 6, 3]</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">Increment</td>
			<td align="center" style="border: 1px solid black;">Increases to 4</td>
			<td align="center" style="border: 1px solid black;">[2, 4, 6, 3]</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">4</td>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">Decrement</td>
			<td align="center" style="border: 1px solid black;">Decreases to 5</td>
			<td align="center" style="border: 1px solid black;">[2, 4, 5, 3]</td>
		</tr>
	</tbody>
</table>

<p>Thus, after 4 operations <code>nums1</code> is transformed into <code>nums2</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [2], nums2 = [3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;">Step</th>
			<th align="center" style="border: 1px solid black;"><code>i</code></th>
			<th align="center" style="border: 1px solid black;">Operation</th>
			<th align="center" style="border: 1px solid black;"><code>nums1[i]</code></th>
			<th align="center" style="border: 1px solid black;">Updated <code>nums1</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">Increment</td>
			<td align="center" style="border: 1px solid black;">Increases to 3</td>
			<td align="center" style="border: 1px solid black;">[3]</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="center" style="border: 1px solid black;">0</td>
			<td align="center" style="border: 1px solid black;">Append</td>
			<td align="center" style="border: 1px solid black;">-</td>
			<td align="center" style="border: 1px solid black;">[3, 3]</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="center" style="border: 1px solid black;">Increment</td>
			<td align="center" style="border: 1px solid black;">Increases to 4</td>
			<td align="center" style="border: 1px solid black;">[3, 4]</td>
		</tr>
	</tbody>
</table>

<p>Thus, after 3 operations <code>nums1</code> is transformed into <code>nums2</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2.length == n + 1</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We define an answer variable $\text{ans}$ to record the minimum number of operations, with an initial value of $1$, representing the operation needed to append the last element to the end of the array.

Then we iterate through the first $n$ elements of the array. For each pair of corresponding elements $(\text{nums1}[i], \text{nums2}[i])$, we calculate their difference and add it to $\text{ans}$.

During the iteration, we also need to check whether $\min(\text{nums1}[i], \text{nums2}[i]) \leq \text{nums2}[n] \leq \max(\text{nums1}[i], \text{nums2}[i])$ holds. If it does, it means we can directly adjust $\text{nums1}[i]$ to reach $\text{nums2}[n]$. Otherwise, we need to record a minimum difference $d$, which represents the minimum number of operations required to adjust some element to $\text{nums2}[n]$.

Finally, if no element satisfying the condition is found after the iteration, we need to add $d$ to $\text{ans}$, indicating that we need extra operations to adjust some element to $\text{nums2}[n]$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        ans = 1
        ok = False
        d = inf
        for x, y in zip(nums1, nums2):
            if x < y:
                x, y = y, x
            ans += x - y
            d = min(d, abs(x - nums2[-1]), abs(y - nums2[-1]))
            ok = ok or y <= nums2[-1] <= x
        if not ok:
            ans += d
        return ans
```

#### Java

```java
class Solution {
    public long minOperations(int[] nums1, int[] nums2) {
        long ans = 1;
        int n = nums1.length;
        boolean ok = false;
        int d = 1 << 30;
        for (int i = 0; i < n; ++i) {
            int x = Math.max(nums1[i], nums2[i]);
            int y = Math.min(nums1[i], nums2[i]);
            ans += x - y;
            d = Math.min(d, Math.min(Math.abs(x - nums2[n]), Math.abs(y - nums2[n])));
            ok = ok || (nums2[n] >= y && nums2[n] <= x);
        }
        if (!ok) {
            ans += d;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minOperations(vector<int>& nums1, vector<int>& nums2) {
        long long ans = 1;
        int n = nums1.size();
        bool ok = false;
        int d = 1 << 30;
        for (int i = 0; i < n; ++i) {
            int x = max(nums1[i], nums2[i]);
            int y = min(nums1[i], nums2[i]);
            ans += x - y;
            d = min(d, min(abs(x - nums2[n]), abs(y - nums2[n])));
            ok = ok || (nums2[n] >= y && nums2[n] <= x);
        }
        if (!ok) {
            ans += d;
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums1 []int, nums2 []int) int64 {
	var ans int64 = 1
	n := len(nums1)
	ok := false
	d := 1 << 30
	for i := 0; i < n; i++ {
		x := max(nums1[i], nums2[i])
		y := min(nums1[i], nums2[i])
		ans += int64(x - y)
		d = min(d, min(abs(x-nums2[n]), abs(y-nums2[n])))
		if nums2[n] >= y && nums2[n] <= x {
			ok = true
		}
	}
	if !ok {
		ans += int64(d)
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minOperations(nums1: number[], nums2: number[]): number {
    let ans = 1;
    const n = nums1.length;
    let ok = false;
    let d = 1 << 30;
    for (let i = 0; i < n; ++i) {
        const x = Math.max(nums1[i], nums2[i]);
        const y = Math.min(nums1[i], nums2[i]);
        ans += x - y;
        d = Math.min(d, Math.abs(x - nums2[n]), Math.abs(y - nums2[n]));
        if (nums2[n] >= y && nums2[n] <= x) {
            ok = true;
        }
    }
    if (!ok) {
        ans += d;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
