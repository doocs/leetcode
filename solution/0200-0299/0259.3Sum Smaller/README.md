---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0259.3Sum%20Smaller/README.md
tags:
    - 数组
    - 双指针
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [259. 较小的三数之和 🔒](https://leetcode.cn/problems/3sum-smaller)

[English Version](/solution/0200-0299/0259.3Sum%20Smaller/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的整数数组和一个目标值 <code>target</code>&nbsp;，寻找能够使条件&nbsp;<code>nums[i] + nums[j] + nums[k] &lt; target</code>&nbsp;成立的三元组&nbsp; <code>i, j, k</code>&nbsp;个数（<code>0 &lt;= i &lt; j &lt; k &lt; n</code>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[-2,0,1,3]</code>, <em>target</em> = 2
<strong>输出: </strong>2 
<strong>解释: </strong>因为一共有两个三元组满足累加和小于 2:
&nbsp;    [-2,0,1]
     [-2,0,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[]</code>, <em>target</em> = 0
<strong>输出: </strong>0 </pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[0]</code>, <em>target</em> = 0
<strong>输出: </strong>0 </pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>0 &lt;= n &lt;= 3500</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>-100 &lt;= target &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def threeSumSmaller(self, nums: List[int], target: int) -> int:
        nums.sort()
        ans, n = 0, len(nums)
        for i in range(n):
            j, k = i + 1, n - 1
            while j < k:
                s = nums[i] + nums[j] + nums[k]
                if s >= target:
                    k -= 1
                else:
                    ans += k - j
                    j += 1
        return ans
```

```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0, n = nums.length; i < n; ++i) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int s = nums[i] + nums[j] + nums[k];
                if (s >= target) {
                    --k;
                } else {
                    ans += k - j;
                    ++j;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int threeSumSmaller(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int i = 0, n = nums.size(); i < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int s = nums[i] + nums[j] + nums[k];
                if (s >= target)
                    --k;
                else {
                    ans += k - j;
                    ++j;
                }
            }
        }
        return ans;
    }
};
```

```go
func threeSumSmaller(nums []int, target int) int {
	sort.Ints(nums)
	ans := 0
	for i, n := 0, len(nums); i < n; i++ {
		j, k := i+1, n-1
		for j < k {
			s := nums[i] + nums[j] + nums[k]
			if s >= target {
				k--
			} else {
				ans += k - j
				j++
			}
		}
	}
	return ans
}
```

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumSmaller = function (nums, target) {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0, n = nums.length; i < n; ++i) {
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            s = nums[i] + nums[j] + nums[k];
            if (s >= target) {
                --k;
            } else {
                ans += k - j;
                ++j;
            }
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
