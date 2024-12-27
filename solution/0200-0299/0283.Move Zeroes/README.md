---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0283.Move%20Zeroes/README.md
tags:
    - 数组
    - 双指针
---

<!-- problem:start -->

# [283. 移动零](https://leetcode.cn/problems/move-zeroes)

[English Version](/solution/0200-0299/0283.Move%20Zeroes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个数组 <code>nums</code>，编写一个函数将所有 <code>0</code> 移动到数组的末尾，同时保持非零元素的相对顺序。</p>

<p><strong>请注意</strong>&nbsp;，必须在不复制数组的情况下原地对数组进行操作。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = <code>[0,1,0,3,12]</code>
<strong>输出:</strong> <code>[1,3,12,0,0]</code>
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = <code>[0]</code>
<strong>输出:</strong> <code>[0]</code></pre>

<p>&nbsp;</p>

<p><strong>提示</strong>:</p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= nums[i] &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

<p>&nbsp;</p>

<p><b>进阶：</b>你能尽量减少完成的操作次数吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们用一个指针 $k$ 记录当前待插入的位置，初始时 $k = 0$。

然后我们遍历数组 $\textit{nums}$，每次遇到一个非零数，就将其与 $\textit{nums}[k]$ 交换，同时将 $k$ 的值加 $1$。

这样我们就可以保证 $\textit{nums}$ 的前 $k$ 个元素都是非零的，且它们的相对顺序与原数组一致。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        k = 0
        for i, x in enumerate(nums):
            if x:
                nums[k], nums[i] = nums[i], nums[k]
                k += 1
```

#### Java

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int k = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] != 0) {
                int t = nums[i];
                nums[i] = nums[k];
                nums[k++] = t;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int k = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            if (nums[i]) {
                swap(nums[i], nums[k++]);
            }
        }
    }
};
```

#### Go

```go
func moveZeroes(nums []int) {
	k := 0
	for i, x := range nums {
		if x != 0 {
			nums[i], nums[k] = nums[k], nums[i]
			k++
		}
	}
}
```

#### TypeScript

```ts
/**
 Do not return anything, modify nums in-place instead.
 */
function moveZeroes(nums: number[]): void {
    let k = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i]) {
            [nums[i], nums[k]] = [nums[k], nums[i]];
            ++k;
        }
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn move_zeroes(nums: &mut Vec<i32>) {
        let mut k = 0;
        let n = nums.len();
        for i in 0..n {
            if nums[i] != 0 {
                nums.swap(i, k);
                k += 1;
            }
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    let k = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i]) {
            [nums[i], nums[k]] = [nums[k], nums[i]];
            ++k;
        }
    }
};
```

#### C

```c
void moveZeroes(int* nums, int numsSize) {
    int k = 0;
    for (int i = 0; i < numsSize; ++i) {
        if (nums[i] != 0) {
            int t = nums[i];
            nums[i] = nums[k];
            nums[k++] = t;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
