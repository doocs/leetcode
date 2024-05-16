---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3046.Split%20the%20Array/README.md
rating: 1212
source: 第 386 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 计数
---

# [3046. 分割数组](https://leetcode.cn/problems/split-the-array)

[English Version](/solution/3000-3099/3046.Split%20the%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <strong>偶数 </strong>的整数数组 <code>nums</code> 。你需要将这个数组分割成 <code>nums1</code> 和 <code>nums2</code> 两部分，要求：</p>

<ul>
	<li><code>nums1.length == nums2.length == nums.length / 2</code> 。</li>
	<li><code>nums1</code> 应包含 <strong>互不相同</strong><strong> </strong>的元素。</li>
	<li><code>nums2</code>也应包含<strong> 互不相同</strong> 的元素。</li>
</ul>

<p>如果能够分割数组就返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2,2,3,4]
<strong>输出：</strong>true
<strong>解释：</strong>分割 nums 的可行方案之一是 nums1 = [1,2,3] 和 nums2 = [1,2,4] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,1]
<strong>输出：</strong>false
<strong>解释：</strong>分割 nums 的唯一可行方案是 nums1 = [1,1] 和 nums2 = [1,1] 。但 nums1 和 nums2 都不是由互不相同的元素构成。因此，返回 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums.length % 2 == 0</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：计数

根据题意，我们需要将数组分成两部分，每部分的元素都是互不相同的。因此，我们可以统计数组中每个元素的出现次数，如果某个元素出现的次数大于等于 $3$ 次，那么就无法满足题意。否则，我们可以将数组分成两部分。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def isPossibleToSplit(self, nums: List[int]) -> bool:
        return max(Counter(nums).values()) < 3
```

```java
class Solution {
    public boolean isPossibleToSplit(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            if (++cnt[x] >= 3) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool isPossibleToSplit(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            if (++cnt[x] >= 3) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func isPossibleToSplit(nums []int) bool {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
		if cnt[x] >= 3 {
			return false
		}
	}
	return true
}
```

```ts
function isPossibleToSplit(nums: number[]): boolean {
    const cnt: number[] = Array(101).fill(0);
    for (const x of nums) {
        if (++cnt[x] >= 3) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- end -->
