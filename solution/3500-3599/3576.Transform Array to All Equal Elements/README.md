---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3576.Transform%20Array%20to%20All%20Equal%20Elements/README.md
rating: 1489
source: 第 453 场周赛 Q1
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [3576. 数组元素相等转换](https://leetcode.cn/problems/transform-array-to-all-equal-elements)

[English Version](/solution/3500-3599/3576.Transform%20Array%20to%20All%20Equal%20Elements/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>n</code> 的整数数组 <code>nums</code>，其中只包含 <code>1</code> 和 <code>-1</code>，以及一个整数 <code>k</code>。</p>

<p>你可以最多进行 <code>k</code> 次以下操作：</p>

<ul>
	<li>
	<p>选择一个下标&nbsp;<code>i</code>（<code>0 &lt;= i &lt; n - 1</code>），然后将 <code>nums[i]</code> 和 <code>nums[i + 1]</code> 同时&nbsp;<strong>乘以</strong>&nbsp;<code>-1</code>。</p>
	</li>
</ul>

<p><strong>注意：</strong>你可以在&nbsp;<strong>不同&nbsp;</strong>的操作中多次选择相同的下标&nbsp;<code>i</code>。</p>

<p>如果在最多 <code>k</code> 次操作后可以使数组的所有元素相等，则返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-1,1,-1,1], k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>我们可以通过以下两次操作使数组的所有元素相等：</p>

<ul>
	<li>选择下标&nbsp;<code>i = 1</code>，将 <code>nums[1]</code> 和 <code>nums[2]</code> 同时乘以 -1。此时 <code>nums = [1,1,-1,-1,1]</code>。</li>
	<li>选择下标&nbsp;<code>i = 2</code>，将 <code>nums[2]</code> 和 <code>nums[3]</code> 同时乘以 -1。此时 <code>nums = [1,1,1,1,1]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [-1,-1,-1,1,1,1], k = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>在最多 5 次操作内，无法使数组的所有元素相等。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 的值为 <code>-1</code> 或 <code>1</code>。</li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历计数

根据题目描述，要使得数组的所有元素相等，要么所有元素为 $\textit{nums}[0]$，要么所有元素为 $-\textit{nums}[0]$。因此，我们设计一个函数 $\textit{check}$，用于判断在最多 $k$ 次操作后，数组能否变成所有元素为 $\textit{target}$ 的形式。

该函数的思路是遍历数组，记录需要进行操作的次数。一个元素要么修改一次，要么不修改。如果当前元素与目标值相等，则不需要修改，继续遍历下一个元素；如果当前元素与目标值不相等，则需要修改，计数器加一，并将符号切换为负数，表示后续元素需要进行相反的操作。

如果遍历结束后，计数器小于等于 $k$ 且最后一个元素的符号与目标值相同，则返回 $\textit{true}$，否则返回 $\textit{false}$。

最终答案是 $\textit{check}(\textit{nums}[0], k)$ 或 $\textit{check}(-\textit{nums}[0], k)$ 的结果。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canMakeEqual(self, nums: List[int], k: int) -> bool:
        def check(target: int, k: int) -> bool:
            cnt, sign = 0, 1
            for i in range(len(nums) - 1):
                x = nums[i] * sign
                if x == target:
                    sign = 1
                else:
                    sign = -1
                    cnt += 1
            return cnt <= k and nums[-1] * sign == target

        return check(nums[0], k) or check(-nums[0], k)
```

#### Java

```java
class Solution {
    public boolean canMakeEqual(int[] nums, int k) {
        return check(nums, nums[0], k) || check(nums, -nums[0], k);
    }

    private boolean check(int[] nums, int target, int k) {
        int cnt = 0, sign = 1;
        for (int i = 0; i < nums.length - 1; ++i) {
            int x = nums[i] * sign;
            if (x == target) {
                sign = 1;
            } else {
                sign = -1;
                ++cnt;
            }
        }
        return cnt <= k && nums[nums.length - 1] * sign == target;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canMakeEqual(vector<int>& nums, int k) {
        auto check = [&](int target, int k) -> bool {
            int n = nums.size();
            int cnt = 0, sign = 1;
            for (int i = 0; i < n - 1; ++i) {
                int x = nums[i] * sign;
                if (x == target) {
                    sign = 1;
                } else {
                    sign = -1;
                    ++cnt;
                }
            }
            return cnt <= k && nums[n - 1] * sign == target;
        };
        return check(nums[0], k) || check(-nums[0], k);
    }
};
```

#### Go

```go
func canMakeEqual(nums []int, k int) bool {
	check := func(target, k int) bool {
		cnt, sign := 0, 1
		for i := 0; i < len(nums)-1; i++ {
			x := nums[i] * sign
			if x == target {
				sign = 1
			} else {
				sign = -1
				cnt++
			}
		}
		return cnt <= k && nums[len(nums)-1]*sign == target
	}
	return check(nums[0], k) || check(-nums[0], k)
}
```

#### TypeScript

```ts
function canMakeEqual(nums: number[], k: number): boolean {
    function check(target: number, k: number): boolean {
        let [cnt, sign] = [0, 1];
        for (let i = 0; i < nums.length - 1; i++) {
            const x = nums[i] * sign;
            if (x === target) {
                sign = 1;
            } else {
                sign = -1;
                cnt++;
            }
        }
        return cnt <= k && nums[nums.length - 1] * sign === target;
    }

    return check(nums[0], k) || check(-nums[0], k);
}
```

#### Rust

```rust
impl Solution {
    pub fn can_make_equal(nums: Vec<i32>, k: i32) -> bool {
        fn check(target: i32, k: i32, nums: &Vec<i32>) -> bool {
            let mut cnt = 0;
            let mut sign = 1;
            for i in 0..nums.len() - 1 {
                let x = nums[i] * sign;
                if x == target {
                    sign = 1;
                } else {
                    sign = -1;
                    cnt += 1;
                }
            }
            cnt <= k && nums[nums.len() - 1] * sign == target
        }

        check(nums[0], k, &nums) || check(-nums[0], k, &nums)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
