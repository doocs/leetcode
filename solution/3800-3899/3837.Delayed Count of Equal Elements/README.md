---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3837.Delayed%20Count%20of%20Equal%20Elements/README.md
---

<!-- problem:start -->

# [3837. 相等元素的延迟计数 🔒](https://leetcode.cn/problems/delayed-count-of-equal-elements)

[English Version](/solution/3800-3899/3837.Delayed%20Count%20of%20Equal%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为&nbsp;<code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p>对于每个下标&nbsp;<code>i</code>，将 <strong>延迟计数</strong> 定义为满足以下条件的索引 <code>j</code> 的数量：</p>

<ul>
	<li><code>i + k &lt; j &lt;= n - 1</code>，且</li>
	<li><code>nums[j] == nums[i]</code></li>
</ul>

<p>返回一个数组&nbsp;<code>ans</code>，其中&nbsp;<code>ans[i]</code>&nbsp;是下标&nbsp;<code>i</code>&nbsp;的 <strong>延迟计数</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,1,1], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>[2,0,0,0]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>nums[i]</code></th>
			<th style="border: 1px solid black;">可能的&nbsp;<code>j</code></th>
			<th style="border: 1px solid black;"><code>nums[j]</code></th>
			<th style="border: 1px solid black;">满足<br />
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

<p>因此，<code>ans = [2, 0, 0, 0]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,1,3,1], k = 0</span></p>

<p><span class="example-io"><b>输出：</b>[1,1,0,0]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>nums[i]</code></th>
			<th style="border: 1px solid black;">可能的&nbsp;<code>j</code></th>
			<th style="border: 1px solid black;"><code>nums[j]</code></th>
			<th style="border: 1px solid black;">满足<br />
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

<p>因此，<code>ans = [1, 1, 0, 0]</code>​​​​​​​.</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 倒序枚举

我们可以使用一个哈希表 $\textit{cnt}$ 来记录每个数字在索引范围 $(i + k, n - 1]$ 内出现的次数。我们从下标 $n - k - 2$ 开始倒序枚举索引 $i$，在枚举的过程中先将索引 $i + k + 1$ 处的数字加入哈希表 $\textit{cnt}$ 中，然后将 $\textit{cnt}[nums[i]]$ 的值赋给答案数组 $\textit{ans}[i]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

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
