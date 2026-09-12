---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0334.Increasing%20Triplet%20Subsequence/README.md
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [334. 递增的三元子序列](https://leetcode.cn/problems/increasing-triplet-subsequence)

[English Version](/solution/0300-0399/0334.Increasing%20Triplet%20Subsequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code> ，判断这个数组中是否存在长度为 <code>3</code> 的递增子序列。</p>

<p>如果存在这样的三元组下标 <code>(i, j, k)</code>&nbsp;且满足 <code>i &lt; j &lt; k</code> ，使得&nbsp;<code>nums[i] &lt; nums[j] &lt; nums[k]</code> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4,5]
<strong>输出：</strong>true
<strong>解释：</strong>任何 i &lt; j &lt; k 的三元组都满足题意
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,3,2,1]
<strong>输出：</strong>false
<strong>解释：</strong>不存在满足题意的三元组</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,5,0,4,6]
<strong>输出：</strong>true
<strong>解释：</strong>其中一个满足题意的三元组是 (1, 4, 5)，因为 nums[1] == 1 &lt; nums[4] == 4 &lt; nums[5] == 6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能实现时间复杂度为 <code>O(n)</code> ，空间复杂度为 <code>O(1)</code> 的解决方案吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 判断是否存在下标递增且值严格递增的三元组。枚举中间点为 $O(n^2)$。只需知道左侧更小值与当前可接的第二小值。
>
> 维护 $mi<mid$，扫描时：大于 $mid$ 则已构成三元；否则若不大于 $mi$ 则更新 $mi$，不然更新 $mid$。$mid$ 的原下标不必保存，因其一定出现在更小的 $mi$ 之后。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def increasingTriplet(self, nums: List[int]) -> bool:
        mi, mid = inf, inf
        for num in nums:
            if num > mid:
                return True
            if num <= mi:
                mi = num
            else:
                mid = num
        return False
```

#### Java

```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > mid) {
                return true;
            }
            if (num <= min) {
                min = num;
            } else {
                mid = num;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool increasingTriplet(vector<int>& nums) {
        int mi = INT_MAX, mid = INT_MAX;
        for (int num : nums) {
            if (num > mid) return true;
            if (num <= mi)
                mi = num;
            else
                mid = num;
        }
        return false;
    }
};
```

#### Go

```go
func increasingTriplet(nums []int) bool {
	min, mid := math.MaxInt32, math.MaxInt32
	for _, num := range nums {
		if num > mid {
			return true
		}
		if num <= min {
			min = num
		} else {
			mid = num
		}
	}
	return false
}
```

#### TypeScript

```ts
function increasingTriplet(nums: number[]): boolean {
    let n = nums.length;
    if (n < 3) return false;
    let min = nums[0],
        mid = Number.MAX_SAFE_INTEGER;
    for (let num of nums) {
        if (num <= min) {
            min = num;
        } else if (num <= mid) {
            mid = num;
        } else if (num > mid) {
            return true;
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    pub fn increasing_triplet(nums: Vec<i32>) -> bool {
        let n = nums.len();
        if n < 3 {
            return false;
        }
        let mut min = i32::MAX;
        let mut mid = i32::MAX;
        for num in nums.into_iter() {
            if num <= min {
                min = num;
            } else if num <= mid {
                mid = num;
            } else {
                return true;
            }
        }
        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
