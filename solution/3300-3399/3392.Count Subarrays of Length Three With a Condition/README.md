---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3392.Count%20Subarrays%20of%20Length%20Three%20With%20a%20Condition/README.md
rating: 1200
source: 第 146 场双周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3392. 统计符合条件长度为 3 的子数组数目](https://leetcode.cn/problems/count-subarrays-of-length-three-with-a-condition)

[English Version](/solution/3300-3399/3392.Count%20Subarrays%20of%20Length%20Three%20With%20a%20Condition/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，请你返回长度为 3 的 <span data-keyword="subarray-nonempty">子数组</span>，满足第一个数和第三个数的和恰好为第二个数的一半。</p>

<p><strong>子数组</strong>&nbsp;指的是一个数组中连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,1,4,1]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p>只有子数组&nbsp;<code>[1,4,1]</code>&nbsp;包含 3 个元素且第一个和第三个数字之和是中间数字的一半。number.</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,1,1]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><b>解释：</b></p>

<p><code>[1,1,1]</code>&nbsp;是唯一长度为 3 的子数组，但第一个数和第三个数的和不是第二个数的一半。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code><font face="monospace">-100 &lt;= nums[i] &lt;= 100</font></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们遍历数组 $\textit{nums}$ 每个长度为 $3$ 的子数组，判断第一个数和第三个数的和乘以 $2$ 是否等于第二个数，若是，答案加 $1$。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubarrays(self, nums: List[int]) -> int:
        return sum(
            (nums[i - 1] + nums[i + 1]) * 2 == nums[i] for i in range(1, len(nums) - 1)
        )
```

#### Java

```java
class Solution {
    public int countSubarrays(int[] nums) {
        int ans = 0;
        for (int i = 1; i + 1 < nums.length; ++i) {
            if ((nums[i - 1] + nums[i + 1]) * 2 == nums[i]) {
                ++ans;
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
    int countSubarrays(vector<int>& nums) {
        int ans = 0;
        for (int i = 1; i + 1 < nums.size(); ++i) {
            if ((nums[i - 1] + nums[i + 1]) * 2 == nums[i]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSubarrays(nums []int) (ans int) {
	for i := 1; i+1 < len(nums); i++ {
		if (nums[i-1]+nums[i+1])*2 == nums[i] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countSubarrays(nums: number[]): number {
    let ans: number = 0;
    for (let i = 1; i + 1 < nums.length; ++i) {
        if ((nums[i - 1] + nums[i + 1]) * 2 === nums[i]) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
