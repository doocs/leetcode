---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2221.Find%20Triangular%20Sum%20of%20an%20Array/README.md
rating: 1317
source: 第 75 场双周赛 Q2
tags:
    - 数组
    - 数学
    - 组合数学
    - 模拟
---

<!-- problem:start -->

# [2221. 数组的三角和](https://leetcode.cn/problems/find-triangular-sum-of-an-array)

[English Version](/solution/2200-2299/2221.Find%20Triangular%20Sum%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，其中&nbsp;<code>nums[i]</code>&nbsp;是 <code>0</code>&nbsp;到 <code>9</code>&nbsp;之间（两者都包含）的一个数字。</p>

<p><code>nums</code>&nbsp;的 <strong>三角和</strong>&nbsp;是执行以下操作以后最后剩下元素的值：</p>

<ol>
	<li><code>nums</code>&nbsp;初始包含&nbsp;<code>n</code>&nbsp;个元素。如果&nbsp;<code>n == 1</code>&nbsp;，<strong>终止</strong>&nbsp;操作。否则，<strong>创建</strong>&nbsp;一个新的下标从&nbsp;<strong>0</strong>&nbsp;开始的长度为 <code>n - 1</code>&nbsp;的整数数组&nbsp;<code>newNums</code>&nbsp;。</li>
	<li>对于满足&nbsp;<code>0 &lt;= i &lt;&nbsp;n - 1</code>&nbsp;的下标&nbsp;<code>i</code>&nbsp;，<code>newNums[i]</code> <strong>赋值</strong>&nbsp;为&nbsp;<code>(nums[i] + nums[i+1]) % 10</code>&nbsp;，<code>%</code>&nbsp;表示取余运算。</li>
	<li>将&nbsp;<code>newNums</code>&nbsp;<strong>替换</strong> 数组&nbsp;<code>nums</code>&nbsp;。</li>
	<li>从步骤 1 开始&nbsp;<strong>重复</strong>&nbsp;整个过程。</li>
</ol>

<p>请你返回&nbsp;<code>nums</code>&nbsp;的三角和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2221.Find%20Triangular%20Sum%20of%20an%20Array/images/ex1drawio.png" style="width: 250px; height: 250px;" /></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5]
<b>输出：</b>8
<strong>解释：</strong>
上图展示了得到数组三角和的过程。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5]
<b>输出：</b>5
<b>解释：</b>
由于 nums 中只有一个元素，数组的三角和为这个元素自己。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以直接模拟题目描述的操作，对数组 $\textit{nums}$ 进行 $n - 1$ 轮操作，每轮操作都按照题目描述的规则更新数组 $\textit{nums}$。最后返回数组 $\textit{nums}$ 中剩下的唯一元素即可。

时间复杂度 $O(n^2)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def triangularSum(self, nums: List[int]) -> int:
        for k in range(len(nums) - 1, 0, -1):
            for i in range(k):
                nums[i] = (nums[i] + nums[i + 1]) % 10
        return nums[0]
```

#### Java

```java
class Solution {
    public int triangularSum(int[] nums) {
        for (int k = nums.length - 1; k > 0; --k) {
            for (int i = 0; i < k; ++i) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int triangularSum(vector<int>& nums) {
        for (int k = nums.size() - 1; k; --k) {
            for (int i = 0; i < k; ++i) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }
};
```

#### Go

```go
func triangularSum(nums []int) int {
	for k := len(nums) - 1; k > 0; k-- {
		for i := 0; i < k; i++ {
			nums[i] = (nums[i] + nums[i+1]) % 10
		}
	}
	return nums[0]
}
```

#### TypeScript

```ts
function triangularSum(nums: number[]): number {
    for (let k = nums.length - 1; k; --k) {
        for (let i = 0; i < k; ++i) {
            nums[i] = (nums[i] + nums[i + 1]) % 10;
        }
    }
    return nums[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
