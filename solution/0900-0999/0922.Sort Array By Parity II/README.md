---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0922.Sort%20Array%20By%20Parity%20II/README.md
tags:
    - 数组
    - 双指针
    - 排序
---

<!-- problem:start -->

# [922. 按奇偶排序数组 II](https://leetcode.cn/problems/sort-array-by-parity-ii)

[English Version](/solution/0900-0999/0922.Sort%20Array%20By%20Parity%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个非负整数数组&nbsp;<code>nums</code>，&nbsp;&nbsp;<code>nums</code> 中一半整数是 <strong>奇数</strong> ，一半整数是 <strong>偶数</strong> 。</p>

<p>对数组进行排序，以便当&nbsp;<code>nums[i]</code> 为奇数时，<code>i</code>&nbsp;也是 <strong>奇数</strong> ；当&nbsp;<code>nums[i]</code>&nbsp;为偶数时， <code>i</code> 也是 <strong>偶数</strong> 。</p>

<p>你可以返回 <em>任何满足上述条件的数组作为答案</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,2,5,7]
<strong>输出：</strong>[4,5,2,7]
<strong>解释：</strong>[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,3]
<b>输出：</b>[2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>nums.length</code>&nbsp;是偶数</li>
	<li><code>nums</code>&nbsp;中一半是偶数</li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>可以不使用额外空间解决问题吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们用两个指针 $i$ 和 $j$ 分别指向偶数下标和奇数下标，初始时 $i = 0$, $j = 1$。

当 $i$ 指向偶数下标时，如果 $\textit{nums}[i]$ 是奇数，那么我们需要找到一个奇数下标 $j$，使得 $\textit{nums}[j]$ 是偶数，然后交换 $\textit{nums}[i]$ 和 $\textit{nums}[j]$。继续遍历，直到 $i$ 指向数组末尾。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}[i]$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortArrayByParityII(self, nums: List[int]) -> List[int]:
        n, j = len(nums), 1
        for i in range(0, n, 2):
            if nums[i] % 2:
                while nums[j] % 2:
                    j += 2
                nums[i], nums[j] = nums[j], nums[i]
        return nums
```

#### Java

```java
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        for (int i = 0, j = 1; i < nums.length; i += 2) {
            if (nums[i] % 2 == 1) {
                while (nums[j] % 2 == 1) {
                    j += 2;
                }
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        return nums;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sortArrayByParityII(vector<int>& nums) {
        for (int i = 0, j = 1; i < nums.size(); i += 2) {
            if (nums[i] % 2) {
                while (nums[j] % 2) {
                    j += 2;
                }
                swap(nums[i], nums[j]);
            }
        }
        return nums;
    }
};
```

#### Go

```go
func sortArrayByParityII(nums []int) []int {
	for i, j := 0, 1; i < len(nums); i += 2 {
		if nums[i]%2 == 1 {
			for nums[j]%2 == 1 {
				j += 2
			}
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
	return nums
}
```

#### TypeScript

```ts
function sortArrayByParityII(nums: number[]): number[] {
    for (let i = 0, j = 1; i < nums.length; i += 2) {
        if (nums[i] % 2) {
            while (nums[j] % 2) {
                j += 2;
            }
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }
    return nums;
}
```

#### Rust

```rust
impl Solution {
    pub fn sort_array_by_parity_ii(mut nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut j = 1;
        for i in (0..n).step_by(2) {
            if nums[i] % 2 != 0 {
                while nums[j] % 2 != 0 {
                    j += 2;
                }
                nums.swap(i, j);
            }
        }
        nums
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortArrayByParityII = function (nums) {
    for (let i = 0, j = 1; i < nums.length; i += 2) {
        if (nums[i] % 2) {
            while (nums[j] % 2) {
                j += 2;
            }
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    }
    return nums;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
