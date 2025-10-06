---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3688.Bitwise%20OR%20of%20Even%20Numbers%20in%20an%20Array/README.md
rating: 1204
source: 第 468 场周赛 Q1
tags:
    - 位运算
    - 数组
    - 模拟
---

<!-- problem:start -->

# [3688. 偶数的按位或运算](https://leetcode.cn/problems/bitwise-or-of-even-numbers-in-an-array)

[English Version](/solution/3600-3699/3688.Bitwise%20OR%20of%20Even%20Numbers%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>返回数组中所有&nbsp;<strong>偶数&nbsp;</strong>的按位 <strong>或</strong> 运算结果。</p>

<p>如果 <code>nums</code> 中没有偶数，返回 0。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,4,5,6]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>偶数为 2、4 和 6。它们的按位或运算结果是 6。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,9,11]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>数组中没有偶数，因此结果为 0。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,8,16]</span></p>

<p><strong>输出：</strong> <span class="example-io">24</span></p>

<p><strong>解释：</strong></p>

<p>偶数为 8 和 16。它们的按位或运算结果是 24。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们定义一个答案变量 $\textit{ans}$，初始值为 0。然后我们遍历数组 $\textit{nums}$ 中的每个元素 $x$，如果 $x$ 是偶数，则将 $\textit{ans}$ 更新为 $\textit{ans}$ 与 $x$ 的按位或运算结果。

最后返回 $\textit{ans}$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def evenNumberBitwiseORs(self, nums: List[int]) -> int:
        return reduce(or_, (x for x in nums if x % 2 == 0), 0)
```

#### Java

```java
class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                ans |= x;
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
    int evenNumberBitwiseORs(vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                ans |= x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func evenNumberBitwiseORs(nums []int) (ans int) {
	for _, x := range nums {
		if x%2 == 0 {
			ans |= x
		}
	}
	return
}
```

#### TypeScript

```ts
function evenNumberBitwiseORs(nums: number[]): number {
    return nums.reduce((ans, x) => (x % 2 === 0 ? ans | x : ans), 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
