---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3641.Longest%20Semi-Repeating%20Subarray/README.md
---

<!-- problem:start -->

# [3641. 最长半重复子数组 🔒](https://leetcode.cn/problems/longest-semi-repeating-subarray)

[English Version](/solution/3600-3699/3641.Longest%20Semi-Repeating%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为  <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数 <code>k</code>。</p>

<p><strong>半重复&nbsp;</strong>子数组是指最多有 <code>k</code> 个元素重复（即出现超过一次）的连续子数组。</p>

<p>返回&nbsp;<code>nums</code>&nbsp;中最长 <strong>半重复</strong>&nbsp;子数组的长度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,1,2,3,4], k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>最长的半重复子数组是&nbsp;<code>[2, 3, 1, 2, 3, 4]</code>，其中有 2 个重复元素（2 和 3）。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,1,1,1,1], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong></p>

<p>最长的半重复子数组是&nbsp;<code>[1, 1, 1, 1, 1]</code>，其中只有 1 个重复元素（1）。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,1,1,1,1], k = 0</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>最长的半重复子数组是&nbsp;<code>[1]</code>，其中没有重复元素。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

我们使用双指针 $l$ 和 $r$ 维护一个滑动窗口，右指针不断向右移动，并使用哈希表 $\textit{cnt}$ 记录每个元素在当前窗口内出现的次数。

当某个元素的出现次数从 $1$ 变为 $2$ 时，表示当前有一个新的重复元素，我们将重复元素的计数器 $\textit{cur}$ 加 $1$。当重复元素的计数器大于 $k$ 时，说明当前窗口不满足条件，我们需要移动左指针，直到重复元素的计数器不大于 $k$ 为止。在移动左指针的过程中，如果某个元素的出现次数从 $2$ 变为 $1$，表示当前少了一个重复元素，我们将重复元素的计数器减 $1$。然后，我们更新答案，即 $\textit{ans} = \max(\textit{ans}, r - l + 1)$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: List[int], k: int) -> int:
        cnt = defaultdict(int)
        ans = cur = l = 0
        for r, x in enumerate(nums):
            cnt[x] += 1
            cur += cnt[x] == 2
            while cur > k:
                cnt[nums[l]] -= 1
                cur -= cnt[nums[l]] == 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0, cur = 0, l = 0;
        for (int r = 0; r < nums.length; ++r) {
            if (cnt.merge(nums[r], 1, Integer::sum) == 2) {
                ++cur;
            }
            while (cur > k) {
                if (cnt.merge(nums[l++], -1, Integer::sum) == 1) {
                    --cur;
                }
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
    int longestSubarray(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int ans = 0, cur = 0, l = 0;
        for (int r = 0; r < nums.size(); ++r) {
            if (++cnt[nums[r]] == 2) {
                ++cur;
            }
            while (cur > k) {
                if (--cnt[nums[l++]] == 1) {
                    --cur;
                }
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubarray(nums []int, k int) (ans int) {
	cnt := make(map[int]int)
	cur, l := 0, 0
	for r := 0; r < len(nums); r++ {
		if cnt[nums[r]]++; cnt[nums[r]] == 2 {
			cur++
		}
		for cur > k {
			if cnt[nums[l]]--; cnt[nums[l]] == 1 {
				cur--
			}
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let [ans, cur, l] = [0, 0, 0];
    for (let r = 0; r < nums.length; r++) {
        cnt.set(nums[r], (cnt.get(nums[r]) || 0) + 1);
        if (cnt.get(nums[r]) === 2) {
            cur++;
        }

        while (cur > k) {
            cnt.set(nums[l], cnt.get(nums[l])! - 1);
            if (cnt.get(nums[l]) === 1) {
                cur--;
            }
            l++;
        }

        ans = Math.max(ans, r - l + 1);
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn longest_subarray(nums: Vec<i32>, k: i32) -> i32 {
        let mut cnt = HashMap::new();
        let mut ans = 0;
        let mut cur = 0;
        let mut l = 0;

        for r in 0..nums.len() {
            let entry = cnt.entry(nums[r]).or_insert(0);
            *entry += 1;
            if *entry == 2 {
                cur += 1;
            }

            while cur > k {
                let entry = cnt.entry(nums[l]).or_insert(0);
                *entry -= 1;
                if *entry == 1 {
                    cur -= 1;
                }
                l += 1;
            }

            ans = ans.max(r - l + 1);
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
