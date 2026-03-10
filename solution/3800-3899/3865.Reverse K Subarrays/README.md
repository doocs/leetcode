---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3865.Reverse%20K%20Subarrays/README.md
---

<!-- problem:start -->

# [3865. 反转 K 个子数组 🔒](https://leetcode.cn/problems/reverse-k-subarrays)

[English Version](/solution/3800-3899/3865.Reverse%20K%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p>你必须将数组 <strong>划分</strong> 为 <code>k</code> 个长度 <strong>相等</strong> 的连续子数组，并 <strong>反转</strong> 每个子数组。</p>

<p>保证&nbsp;<code>n</code>&nbsp;能被&nbsp;<code>k</code>&nbsp;整除。</p>

<p>返回上述操作后的结果数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,4,3,5,6], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>[2,1,3,4,6,5]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>数组被划分为&nbsp;<code>k = 3</code>&nbsp;个子数组：<code>[1, 2]</code>，<code>[4, 3]</code>，和&nbsp;<code>[5, 6]</code>。</li>
	<li>反转每个子数组后：<code>[2, 1]</code>，<code>[3, 4]</code>，和&nbsp;<code>[6, 5]</code>。</li>
	<li>组合它们得到最终数组&nbsp;<code>[2, 1, 3, 4, 6, 5]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,4,4,2], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>[2,4,4,5]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>数组被划分为&nbsp;<code>k = 1</code>&nbsp;个子数组：<code>[5, 4, 4, 2]</code>。</li>
	<li>反转它得到&nbsp;<code>[2, 4, 4, 5]</code>，即最终数组。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>n</code>&nbsp;能被&nbsp;<code>k</code>&nbsp;整除。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

由于需要将数组分成 $k$ 个长度相等的子数组，因此每个子数组的长度为 $m = \frac{n}{k}$。我们可以使用一个循环，按照步长 $m$ 遍历数组，并在每次迭代中将当前子数组进行反转。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$，我们只使用了常数级别的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseSubarrays(self, nums: list[int], k: int) -> list[int]:
        n = len(nums)
        m = n // k
        for i in range(0, n, m):
            nums[i : i + m] = nums[i : i + m][::-1]
        return nums
```

#### Java

```java
class Solution {
    public int[] reverseSubarrays(int[] nums, int k) {
        int n = nums.length;
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            int l = i, r = i + m - 1;
            while (l < r) {
                int t = nums[l];
                nums[l++] = nums[r];
                nums[r--] = t;
            }
        }
        return nums;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> reverseSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            int l = i, r = i + m - 1;
            while (l < r) {
                swap(nums[l++], nums[r--]);
            }
        }
        return nums;
    }
};
```

#### Go

```go
func reverseSubarrays(nums []int, k int) []int {
	n := len(nums)
	m := n / k
	for i := 0; i < n; i += m {
		l, r := i, i+m-1
		for l < r {
			nums[l], nums[r] = nums[r], nums[l]
			l++
			r--
		}
	}
	return nums
}
```

#### TypeScript

```ts
function reverseSubarrays(nums: number[], k: number): number[] {
    const n = nums.length;
    const m = Math.floor(n / k);
    for (let i = 0; i < n; i += m) {
        let l = i,
            r = i + m - 1;
        while (l < r) {
            const t = nums[l];
            nums[l++] = nums[r];
            nums[r--] = t;
        }
    }
    return nums;
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_subarrays(mut nums: Vec<i32>, k: i32) -> Vec<i32> {
        let n = nums.len();
        let m = n / k as usize;

        for i in (0..n).step_by(m) {
            let mut l = i;
            let mut r = i + m - 1;
            while l < r {
                nums.swap(l, r);
                l += 1;
                r -= 1;
            }
        }

        nums
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
