---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3837.Delayed%20Count%20of%20Equal%20Elements/README_EN.md
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [3837. Delayed Count of Equal Elements 🔒](https://leetcode.com/problems/delayed-count-of-equal-elements)

[中文文档](/solution/3800-3899/3837.Delayed%20Count%20of%20Equal%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>For each index <code>i</code>, define the <strong>delayed count</strong> as the number of indices <code>j</code> such that:</p>

<ul>
	<li><code>i + k &lt; j &lt;= n - 1</code>, and</li>
	<li><code>nums[j] == nums[i]</code></li>
</ul>

<p>Return an array <code>ans</code> where <code>ans[i]</code> is the <strong>delayed count</strong> of index <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,0,0,0]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>nums[i]</code></th>
			<th style="border: 1px solid black;">possible <code>j</code></th>
			<th style="border: 1px solid black;"><code>nums[j]</code></th>
			<th style="border: 1px solid black;">satisfying<br />
			<code>nums[j] == nums[i]</code></th>
			<th style="border: 1px solid black;"><code>ans[i]</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[2, 3]</td>
			<td style="border: 1px solid black;">[1, 1]</td>
			<td style="border: 1px solid black;">[2, 3]</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">[3]</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
	</tbody>
</table>

<p>Thus, <code>ans = [2, 0, 0, 0]</code>​​​​​​​.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,3,1], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,0,0]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>nums[i]</code></th>
			<th style="border: 1px solid black;">possible <code>j</code></th>
			<th style="border: 1px solid black;"><code>nums[j]</code></th>
			<th style="border: 1px solid black;">satisfying<br />
			<code>nums[j] == nums[i]</code></th>
			<th style="border: 1px solid black;"><code>ans[i]</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">[1, 2, 3]</td>
			<td style="border: 1px solid black;">[1, 3, 1]</td>
			<td style="border: 1px solid black;">[2]</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[2, 3]</td>
			<td style="border: 1px solid black;">[3, 1]</td>
			<td style="border: 1px solid black;">[3]</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">[3]</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
	</tbody>
</table>

<p>Thus, <code>ans = [1, 1, 0, 0]</code>​​​​​​​.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Reverse Enumeration

We can use a hash table $\textit{cnt}$ to record the number of occurrences of each number within the index range $(i + k, n - 1]$. We enumerate index $i$ in reverse order starting from index $n - k - 2$. During the enumeration, we first add the number at index $i + k + 1$ to the hash table $\textit{cnt}$, then assign the value of $\textit{cnt}[nums[i]]$ to the answer array $\textit{ans}[i]$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def delayedCount(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        cnt = Counter()
        ans = [0] * n
        for i in range(n - k - 2, -1, -1):
            cnt[nums[i + k + 1]] += 1
            ans[i] = cnt[nums[i]]
        return ans
```

#### Java

```java
class Solution {
    public int[] delayedCount(int[] nums, int k) {
        int n = nums.length;
        var cnt = new HashMap<Integer, Integer>();
        int[] ans = new int[n];
        for (int i = n - k - 2; i >= 0; --i) {
            cnt.merge(nums[i + k + 1], 1, Integer::sum);
            ans[i] = cnt.getOrDefault(nums[i], 0);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> delayedCount(vector<int>& nums, int k) {
        int n = nums.size();
        unordered_map<int, int> cnt;
        vector<int> ans(n);
        for (int i = n - k - 2; i >= 0; --i) {
            ++cnt[nums[i + k + 1]];
            ans[i] = cnt[nums[i]];
        }
        return ans;
    }
};
```

#### Go

```go
func delayedCount(nums []int, k int) []int {
	n := len(nums)
	cnt := map[int]int{}
	ans := make([]int, n)
	for i := n - k - 2; i >= 0; i-- {
		cnt[nums[i+k+1]]++
		ans[i] = cnt[nums[i]]
	}
	return ans
}
```

#### TypeScript

```ts
function delayedCount(nums: number[], k: number): number[] {
    const n = nums.length;
    const cnt = new Map<number, number>();
    const ans = Array(n).fill(0);
    for (let i = n - k - 2; i >= 0; i--) {
        cnt.set(nums[i + k + 1], (cnt.get(nums[i + k + 1]) ?? 0) + 1);
        ans[i] = cnt.get(nums[i]) ?? 0;
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn delayed_count(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let n = nums.len();
        let mut cnt: HashMap<i32, i32> = HashMap::new();
        let mut ans = vec![0; n];
        let k = k as usize;
        let mut i = n as i32 - k as i32 - 2;
        while i >= 0 {
            let idx = i as usize;
            *cnt.entry(nums[idx + k + 1]).or_insert(0) += 1;
            ans[idx] = *cnt.get(&nums[idx]).unwrap_or(&0);
            i -= 1;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
