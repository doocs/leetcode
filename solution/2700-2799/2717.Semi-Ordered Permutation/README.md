---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2717.Semi-Ordered%20Permutation/README.md
rating: 1295
source: 第 348 场周赛 Q2
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [2717. 半有序排列](https://leetcode.cn/problems/semi-ordered-permutation)

[English Version](/solution/2700-2799/2717.Semi-Ordered%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的整数排列 <code>nums</code> 。</p>

<p>如果排列的第一个数字等于 <code>1</code> 且最后一个数字等于 <code>n</code> ，则称其为 <strong>半有序排列</strong> 。你可以执行多次下述操作，直到将 <code>nums</code> 变成一个 <strong>半有序排列</strong> ：</p>

<ul>
	<li>选择 <code>nums</code> 中相邻的两个元素，然后交换它们。</li>
</ul>

<p>返回使 <code>nums</code> 变成 <strong>半有序排列</strong> 所需的最小操作次数。</p>

<p><strong>排列</strong> 是一个长度为 <code>n</code> 的整数序列，其中包含从 <code>1</code> 到 <code>n</code> 的每个数字恰好一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,4,3]
<strong>输出：</strong>2
<strong>解释：</strong>可以依次执行下述操作得到半有序排列：
1 - 交换下标 0 和下标 1 对应元素。排列变为 [1,2,4,3] 。
2 - 交换下标 2 和下标 3 对应元素。排列变为 [1,2,3,4] 。
可以证明，要让 nums 成为半有序排列，不存在执行操作少于 2 次的方案。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4,1,3]
<strong>输出：</strong>3
<strong>解释：
</strong>可以依次执行下述操作得到半有序排列：
1 - 交换下标 1 和下标 2 对应元素。排列变为 [2,1,4,3] 。
2 - 交换下标 0 和下标 1 对应元素。排列变为 [1,2,4,3] 。
3 - 交换下标 2 和下标 3 对应元素。排列变为 [1,2,3,4] 。
可以证明，要让 nums 成为半有序排列，不存在执行操作少于 3 次的方案。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,4,2,5]
<strong>输出：</strong>0
<strong>解释：</strong>这个排列已经是一个半有序排列，无需执行任何操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length == n &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i]&nbsp;&lt;= 50</code></li>
	<li><code>nums</code> 是一个 <strong>排列</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：寻找 1 和 n 的位置

我们可以先找到 $1$ 和 $n$ 的下标 $i$ 和 $j$，然后根据 $i$ 和 $j$ 的相对位置，判断需要交换的次数。

如果 $i \lt j$，那么需要交换的次数为 $i + n - j - 1$；如果 $i \gt j$，那么需要交换的次数为 $i + n - j - 2$。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def semiOrderedPermutation(self, nums: List[int]) -> int:
        n = len(nums)
        i = nums.index(1)
        j = nums.index(n)
        k = 1 if i < j else 2
        return i + n - j - k
```

#### Java

```java
class Solution {
    public int semiOrderedPermutation(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        for (int k = 0; k < n; ++k) {
            if (nums[k] == 1) {
                i = k;
            }
            if (nums[k] == n) {
                j = k;
            }
        }
        int k = i < j ? 1 : 2;
        return i + n - j - k;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int semiOrderedPermutation(vector<int>& nums) {
        int n = nums.size();
        int i = find(nums.begin(), nums.end(), 1) - nums.begin();
        int j = find(nums.begin(), nums.end(), n) - nums.begin();
        int k = i < j ? 1 : 2;
        return i + n - j - k;
    }
};
```

#### Go

```go
func semiOrderedPermutation(nums []int) int {
	n := len(nums)
	var i, j int
	for k, x := range nums {
		if x == 1 {
			i = k
		}
		if x == n {
			j = k
		}
	}
	k := 1
	if i > j {
		k = 2
	}
	return i + n - j - k
}
```

#### TypeScript

```ts
function semiOrderedPermutation(nums: number[]): number {
    const n = nums.length;
    const i = nums.indexOf(1);
    const j = nums.indexOf(n);
    const k = i < j ? 1 : 2;
    return i + n - j - k;
}
```

#### Rust

```rust
impl Solution {
    pub fn semi_ordered_permutation(nums: Vec<i32>) -> i32 {
        let mut i = 0;
        let mut j = 0;
        let mut n = nums.len();

        for idx in 0..n {
            if nums[idx] == 1 {
                i = idx;
            }
            if nums[idx] == (n as i32) {
                j = idx;
            }
        }

        let mut ans = i - 1 + n - j;
        if i > j {
            ans = i - 1 + n - j - 1;
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Rust

```rust
impl Solution {
    pub fn semi_ordered_permutation(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let i = nums
            .iter()
            .enumerate()
            .find(|&(_, &v)| v == 1)
            .map(|(i, _)| i)
            .unwrap();
        let j = nums
            .iter()
            .enumerate()
            .find(|&(_, &v)| v == (n as i32))
            .map(|(i, _)| i)
            .unwrap();

        let mut ans = i - 1 + n - j;
        if i > j {
            ans = i - 1 + n - j - 1;
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
