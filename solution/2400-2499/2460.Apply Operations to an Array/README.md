---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2460.Apply%20Operations%20to%20an%20Array/README.md
rating: 1223
source: 第 318 场周赛 Q1
tags:
    - 数组
    - 双指针
    - 模拟
---

<!-- problem:start -->

# [2460. 对数组执行操作](https://leetcode.cn/problems/apply-operations-to-an-array)

[English Version](/solution/2400-2499/2460.Apply%20Operations%20to%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> ，数组大小为 <code>n</code> ，且由 <strong>非负</strong> 整数组成。</p>

<p>你需要对数组执行 <code>n - 1</code> 步操作，其中第 <code>i</code> 步操作（从 <strong>0</strong> 开始计数）要求对 <code>nums</code> 中第 <code>i</code> 个元素执行下述指令：</p>

<ul>
	<li>如果 <code>nums[i] == nums[i + 1]</code> ，则 <code>nums[i]</code> 的值变成原来的 <code>2</code> 倍，<code>nums[i + 1]</code> 的值变成 <code>0</code> 。否则，跳过这步操作。</li>
</ul>

<p>在执行完 <strong>全部</strong> 操作后，将所有 <code>0</code> <strong>移动</strong> 到数组的 <strong>末尾</strong> 。</p>

<ul>
	<li>例如，数组 <code>[1,0,2,0,0,1]</code> 将所有 <code>0</code> 移动到末尾后变为 <code>[1,2,1,0,0,0]</code> 。</li>
</ul>

<p>返回结果数组。</p>

<p><strong>注意</strong> 操作应当 <strong>依次有序</strong> 执行，而不是一次性全部执行。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,1,1,0]
<strong>输出：</strong>[1,4,2,0,0,0]
<strong>解释：</strong>执行以下操作：
- i = 0: nums[0] 和 nums[1] 不相等，跳过这步操作。
- i = 1: nums[1] 和 nums[2] 相等，nums[1] 的值变成原来的 2 倍，nums[2] 的值变成 0 。数组变成 [1,<em><strong>4</strong></em>,<em><strong>0</strong></em>,1,1,0] 。
- i = 2: nums[2] 和 nums[3] 不相等，所以跳过这步操作。
- i = 3: nums[3] 和 nums[4] 相等，nums[3] 的值变成原来的 2 倍，nums[4] 的值变成 0 。数组变成 [1,4,0,<em><strong>2</strong></em>,<em><strong>0</strong></em>,0] 。
- i = 4: nums[4] 和 nums[5] 相等，nums[4] 的值变成原来的 2 倍，nums[5] 的值变成 0 。数组变成 [1,4,0,2,<em><strong>0</strong></em>,<em><strong>0</strong></em>] 。
执行完所有操作后，将 0 全部移动到数组末尾，得到结果数组 [1,4,2,0,0,0] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1]
<strong>输出：</strong>[1,0]
<strong>解释：</strong>无法执行任何操作，只需要将 0 移动到末尾。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们直接根据题意模拟即可。

首先，我们遍历数组 $nums$，对于任意相邻的两个元素 $nums[i]$ 和 $nums[i+1]$，如果 $nums[i]=nums[i+1]$，那么我们将 $nums[i]$ 的值变为原来的 $2$ 倍，同时将 $nums[i+1]$ 的值变为 $0$。

然后，我们创建一个长度为 $n$ 的答案数组 $ans$，并将 $nums$ 中所有非零元素按顺序放入 $ans$ 中。

最后，返回答案数组 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def applyOperations(self, nums: List[int]) -> List[int]:
        n = len(nums)
        for i in range(n - 1):
            if nums[i] == nums[i + 1]:
                nums[i] <<= 1
                nums[i + 1] = 0
        ans = [0] * n
        i = 0
        for x in nums:
            if x:
                ans[i] = x
                i += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                nums[i] <<= 1;
                nums[i + 1] = 0;
            }
        }
        int[] ans = new int[n];
        int i = 0;
        for (int x : nums) {
            if (x > 0) {
                ans[i++] = x;
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
    vector<int> applyOperations(vector<int>& nums) {
        int n = nums.size();
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                nums[i] <<= 1;
                nums[i + 1] = 0;
            }
        }
        vector<int> ans(n);
        int i = 0;
        for (int& x : nums) {
            if (x) {
                ans[i++] = x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func applyOperations(nums []int) []int {
	n := len(nums)
	for i := 0; i < n-1; i++ {
		if nums[i] == nums[i+1] {
			nums[i] <<= 1
			nums[i+1] = 0
		}
	}
	ans := make([]int, n)
	i := 0
	for _, x := range nums {
		if x > 0 {
			ans[i] = x
			i++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function applyOperations(nums: number[]): number[] {
    const n = nums.length;
    for (let i = 0; i < n - 1; ++i) {
        if (nums[i] === nums[i + 1]) {
            nums[i] <<= 1;
            nums[i + 1] = 0;
        }
    }
    const ans: number[] = Array(n).fill(0);
    let i = 0;
    for (const x of nums) {
        if (x !== 0) {
            ans[i++] = x;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn apply_operations(nums: Vec<i32>) -> Vec<i32> {
        let mut nums = nums;

        for i in 0..nums.len() - 1 {
            if nums[i] == nums[i + 1] {
                nums[i] <<= 1;
                nums[i + 1] = 0;
            }
        }

        let mut cur = 0;
        for i in 0..nums.len() {
            if nums[i] != 0 {
                nums.swap(i, cur);
                cur += 1;
            }
        }

        nums
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
