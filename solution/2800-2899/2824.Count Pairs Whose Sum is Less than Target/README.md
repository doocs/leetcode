---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2824.Count%20Pairs%20Whose%20Sum%20is%20Less%20than%20Target/README.md
rating: 1165
source: 第 111 场双周赛 Q1
tags:
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

# [2824. 统计和小于目标的下标对数目](https://leetcode.cn/problems/count-pairs-whose-sum-is-less-than-target)

[English Version](/solution/2800-2899/2824.Count%20Pairs%20Whose%20Sum%20is%20Less%20than%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>target</code>&nbsp;，请你返回满足&nbsp;<code>0 &lt;= i &lt; j &lt; n</code> 且 <code>nums[i] + nums[j] &lt; target</code>&nbsp;的下标对&nbsp;<code>(i, j)</code>&nbsp;的数目。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [-1,1,2,3,1], target = 2
<b>输出：</b>3
<b>解释：</b>总共有 3 个下标对满足题目描述：
- (0, 1) ，0 &lt; 1 且 nums[0] + nums[1] = 0 &lt; target
- (0, 2) ，0 &lt; 2 且 nums[0] + nums[2] = 1 &lt; target 
- (0, 4) ，0 &lt; 4 且 nums[0] + nums[4] = 0 &lt; target
注意 (0, 3) 不计入答案因为 nums[0] + nums[3] 不是严格小于 target 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [-6,2,5,-2,-7,-1,3], target = -2
<b>输出：</b>10
<b>解释：</b>总共有 10 个下标对满足题目描述：
- (0, 1) ，0 &lt; 1 且 nums[0] + nums[1] = -4 &lt; target
- (0, 3) ，0 &lt; 3 且 nums[0] + nums[3] = -8 &lt; target
- (0, 4) ，0 &lt; 4 且 nums[0] + nums[4] = -13 &lt; target
- (0, 5) ，0 &lt; 5 且 nums[0] + nums[5] = -7 &lt; target
- (0, 6) ，0 &lt; 6 且 nums[0] + nums[6] = -3 &lt; target
- (1, 4) ，1 &lt; 4 且 nums[1] + nums[4] = -5 &lt; target
- (3, 4) ，3 &lt; 4 且 nums[3] + nums[4] = -9 &lt; target
- (3, 5) ，3 &lt; 5 且 nums[3] + nums[5] = -3 &lt; target
- (4, 5) ，4 &lt; 5 且 nums[4] + nums[5] = -8 &lt; target
- (4, 6) ，4 &lt; 6 且 nums[4] + nums[6] = -4 &lt; target
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == n &lt;= 50</code></li>
	<li><code>-50 &lt;= nums[i], target &lt;= 50</code></li>
</ul>

## 解法

### 方法一：排序 + 二分查找

我们先对数组 $nums$ 进行排序，然后枚举 $j$，在 $[0, j)$ 的范围内使用二分查找第一个大于等于 $target - nums[j]$ 的下标 $i$，那么 $[0, i)$ 的范围内的所有下标 $k$ 都满足条件，因此答案增加 $i$。

遍历结束后，我们返回答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def countPairs(self, nums: List[int], target: int) -> int:
        nums.sort()
        ans = 0
        for j, x in enumerate(nums):
            i = bisect_left(nums, target - x, hi=j)
            ans += i
        return ans
```

```java
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int ans = 0;
        for (int j = 0; j < nums.size(); ++j) {
            int x = nums.get(j);
            int i = search(nums, target - x, j);
            ans += i;
        }
        return ans;
    }

    private int search(List<Integer> nums, int x, int r) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int countPairs(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int j = 0; j < nums.size(); ++j) {
            int i = lower_bound(nums.begin(), nums.begin() + j, target - nums[j]) - nums.begin();
            ans += i;
        }
        return ans;
    }
};
```

```go
func countPairs(nums []int, target int) (ans int) {
	sort.Ints(nums)
	for j, x := range nums {
		i := sort.SearchInts(nums[:j], target-x)
		ans += i
	}
	return
}
```

```ts
function countPairs(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const search = (x: number, r: number): number => {
        let l = 0;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let j = 0; j < nums.length; ++j) {
        const i = search(target - nums[j], j);
        ans += i;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
