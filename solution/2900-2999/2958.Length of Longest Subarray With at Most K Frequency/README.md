---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2958.Length%20of%20Longest%20Subarray%20With%20at%20Most%20K%20Frequency/README.md
rating: 1535
source: 第 119 场双周赛 Q3
tags:
    - 数组
    - 哈希表
    - 滑动窗口
---

<!-- problem:start -->

# [2958. 最多 K 个重复元素的最长子数组](https://leetcode.cn/problems/length-of-longest-subarray-with-at-most-k-frequency)

[English Version](/solution/2900-2999/2958.Length%20of%20Longest%20Subarray%20With%20at%20Most%20K%20Frequency/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>一个元素 <code>x</code>&nbsp;在数组中的 <strong>频率</strong>&nbsp;指的是它在数组中的出现次数。</p>

<p>如果一个数组中所有元素的频率都 <strong>小于等于&nbsp;</strong><code>k</code>&nbsp;，那么我们称这个数组是 <strong>好</strong>&nbsp;数组。</p>

<p>请你返回 <code>nums</code>&nbsp;中 <strong>最长好</strong>&nbsp;子数组的长度。</p>

<p><strong>子数组</strong> 指的是一个数组中一段连续非空的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,1,2,3,1,2], k = 2
<b>输出：</b>6
<strong>解释：</strong>最长好子数组是 [1,2,3,1,2,3] ，值 1 ，2 和 3 在子数组中的频率都没有超过 k = 2 。[2,3,1,2,3,1] 和 [3,1,2,3,1,2] 也是好子数组。
最长好子数组的长度为 6 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1,2,1,2], k = 1
<b>输出：</b>2
<b>解释：</b>最长好子数组是 [1,2] ，值 1 和 2 在子数组中的频率都没有超过 k = 1 。[2,1] 也是好子数组。
最长好子数组的长度为 2 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [5,5,5,5,5,5,5], k = 4
<b>输出：</b>4
<b>解释：</b>最长好子数组是 [5,5,5,5] ，值 5 在子数组中的频率没有超过 k = 4 。
最长好子数组的长度为 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们可以用两个指针 $l$ 和 $r$ 分别表示子数组的左右端点，初始时两个指针都指向数组的第一个元素。

接下来，我们遍历数组 $nums$ 中的每个元素 $x$，对于每个元素 $x$，我们将 $x$ 的出现次数加一，然后判断当前子数组是否满足要求。如果当前子数组不满足要求，我们就将指针 $l$ 右移一位，并将 $nums[l]$ 的出现次数减一，直到当前子数组满足要求为止。然后我们更新答案 $ans = \max(ans, r - l + 1)$。继续遍历，直到 $r$ 到达数组的末尾。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubarrayLength(self, nums: List[int], k: int) -> int:
        ans = l = 0
        cnt = defaultdict(int)
        for r, x in enumerate(nums):
            cnt[x] += 1
            while cnt[x] > k:
                cnt[nums[l]] -= 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int l = 0, r = 0; r < nums.length; ++r) {
            cnt.merge(nums[r], 1, Integer::sum);
            while (cnt.get(nums[r]) > k) {
                cnt.merge(nums[l++], -1, Integer::sum);
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
    int maxSubarrayLength(vector<int>& nums, int k) {
        int ans = 0;
        unordered_map<int, int> cnt;
        for (int l = 0, r = 0; r < nums.size(); ++r) {
            ++cnt[nums[r]];
            while (cnt[nums[r]] > k) {
                --cnt[nums[l++]];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func maxSubarrayLength(nums []int, k int) (ans int) {
	cnt := make(map[int]int)
	for l, r := 0, 0; r < len(nums); r++ {
		cnt[nums[r]]++
		for cnt[nums[r]] > k {
			cnt[nums[l]]--
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
```

#### TypeScript

```ts
function maxSubarrayLength(nums: number[], k: number): number {
    let ans = 0;
    const cnt = new Map<number, number>();
    for (let l = 0, r = 0; r < nums.length; ++r) {
        cnt.set(nums[r], (cnt.get(nums[r]) ?? 0) + 1);
        while (cnt.get(nums[r])! > k) {
            cnt.set(nums[l], cnt.get(nums[l])! - 1);
            ++l;
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_subarray_length(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        let mut cnt = std::collections::HashMap::new();

        let mut l = 0;
        for r in 0..nums.len() {
            *cnt.entry(nums[r]).or_insert(0) += 1;

            while cnt[&nums[r]] > k {
                *cnt.get_mut(&nums[l]).unwrap() -= 1;
                l += 1;
            }

            ans = ans.max((r - l + 1) as i32);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
