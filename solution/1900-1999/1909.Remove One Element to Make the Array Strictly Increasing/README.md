---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1909.Remove%20One%20Element%20to%20Make%20the%20Array%20Strictly%20Increasing/README.md
rating: 1461
source: 第 55 场双周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [1909. 删除一个元素使数组严格递增](https://leetcode.cn/problems/remove-one-element-to-make-the-array-strictly-increasing)

[English Version](/solution/1900-1999/1909.Remove%20One%20Element%20to%20Make%20the%20Array%20Strictly%20Increasing/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，如果 <strong>恰好</strong> 删除 <strong>一个</strong> 元素后，数组 <strong>严格递增</strong> ，那么请你返回 <code>true</code> ，否则返回 <code>false</code> 。如果数组本身已经是严格递增的，请你也返回 <code>true</code> 。</p>

<p>数组 <code>nums</code> 是 <strong>严格递增</strong> 的定义为：对于任意下标的 <code>1 &lt;= i &lt; nums.length</code> 都满足 <code>nums[i - 1] &lt; nums[i]</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,<strong>10</strong>,5,7]
<b>输出：</b>true
<b>解释：</b>从 nums 中删除下标 2 处的 10 ，得到 [1,2,5,7] 。
[1,2,5,7] 是严格递增的，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,3,1,2]
<b>输出：</b>false
<b>解释：</b>
[3,1,2] 是删除下标 0 处元素后得到的结果。
[2,1,2] 是删除下标 1 处元素后得到的结果。
[2,3,2] 是删除下标 2 处元素后得到的结果。
[2,3,1] 是删除下标 3 处元素后得到的结果。
没有任何结果数组是严格递增的，所以返回 false 。</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [1,1,1]
<b>输出：</b>false
<b>解释：</b>删除任意元素后的结果都是 [1,1] 。
[1,1] 不是严格递增的，所以返回 false 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>nums = [1,2,3]
<b>输出：</b>true
<b>解释：</b>[1,2,3] 已经是严格递增的，所以返回 true 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们可以遍历数组，找到第一个不满足 $\textit{nums}[i] < \textit{nums}[i+1]$ 的位置 $i$，然后检查删除 $i$ 或者 $i+1$ 后的数组是否严格递增，如果是则返回 $\textit{true}$，否则返回 $\textit{false}$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canBeIncreasing(self, nums: List[int]) -> bool:
        def check(k: int) -> bool:
            pre = -inf
            for i, x in enumerate(nums):
                if i == k:
                    continue
                if pre >= x:
                    return False
                pre = x
            return True

        i = 0
        while i + 1 < len(nums) and nums[i] < nums[i + 1]:
            i += 1
        return check(i) or check(i + 1)
```

#### Java

```java
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        int i = 0;
        while (i + 1 < nums.length && nums[i] < nums[i + 1]) {
            ++i;
        }
        return check(nums, i) || check(nums, i + 1);
    }

    private boolean check(int[] nums, int k) {
        int pre = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i == k) {
                continue;
            }
            if (pre >= nums[i]) {
                return false;
            }
            pre = nums[i];
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canBeIncreasing(vector<int>& nums) {
        int n = nums.size();
        auto check = [&](int k) -> bool {
            int pre = 0;
            for (int i = 0; i < n; ++i) {
                if (i == k) {
                    continue;
                }
                if (pre >= nums[i]) {
                    return false;
                }
                pre = nums[i];
            }
            return true;
        };
        int i = 0;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        return check(i) || check(i + 1);
    }
};
```

#### Go

```go
func canBeIncreasing(nums []int) bool {
	check := func(k int) bool {
		pre := 0
		for i, x := range nums {
			if i == k {
				continue
			}
			if pre >= x {
				return false
			}
			pre = x
		}
		return true
	}
	i := 0
	for i+1 < len(nums) && nums[i] < nums[i+1] {
		i++
	}
	return check(i) || check(i+1)
}
```

#### TypeScript

```ts
function canBeIncreasing(nums: number[]): boolean {
    const n = nums.length;
    const check = (k: number): boolean => {
        let pre = 0;
        for (let i = 0; i < n; ++i) {
            if (i === k) {
                continue;
            }
            if (pre >= nums[i]) {
                return false;
            }
            pre = nums[i];
        }
        return true;
    };
    let i = 0;
    while (i + 1 < n && nums[i] < nums[i + 1]) {
        ++i;
    }
    return check(i) || check(i + 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn can_be_increasing(nums: Vec<i32>) -> bool {
        let check = |k: usize| -> bool {
            let mut pre = 0;
            for (i, &x) in nums.iter().enumerate() {
                if i == k {
                    continue;
                }
                if pre >= x {
                    return false;
                }
                pre = x;
            }
            true
        };

        let mut i = 0;
        while i + 1 < nums.len() && nums[i] < nums[i + 1] {
            i += 1;
        }
        check(i) || check(i + 1)
    }
}
```

#### C#

```cs
public class Solution {
    public bool CanBeIncreasing(int[] nums) {
        int n = nums.Length;
        bool check(int k) {
            int pre = 0;
            for (int i = 0; i < n; ++i) {
                if (i == k) {
                    continue;
                }
                if (pre >= nums[i]) {
                    return false;
                }
                pre = nums[i];
            }
            return true;
        }
        int i = 0;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        return check(i) || check(i + 1);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
