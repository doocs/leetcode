---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3833.Count%20Dominant%20Indices/README.md
rating: 1171
source: 第 488 场周赛 Q1
tags:
    - 数组
    - 枚举
---

<!-- problem:start -->

# [3833. 统计主导元素下标数](https://leetcode.cn/problems/count-dominant-indices)

[English Version](/solution/3800-3899/3833.Count%20Dominant%20Indices/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>当下标&nbsp;<code>i</code> 满足以下条件时，该下标处的元素被称为&nbsp;<strong>主导元素</strong>：<code>nums[i] &gt; average(nums[i + 1], nums[i + 2], ..., nums[n - 1])</code></p>

<p>你的任务是统计数组中<strong>&nbsp;主导元素&nbsp;</strong>的下标数。</p>

<p><strong>平均值</strong>&nbsp;是指一组数的总和除以该组数的个数得到的值。</p>

<p><strong>注意</strong>：数组的<strong>&nbsp;最右边元素&nbsp;</strong>不算作<strong>&nbsp;主导元素</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,4,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在下标&nbsp;<code>i = 0</code> 处，值 5 是主导元素，因为 <code>5 &gt; average(4, 3) = 3.5</code>。</li>
	<li>在下标&nbsp;<code>i = 1</code> 处，值 4 是主导元素，相对于子数组 <code>[3]</code>。</li>
	<li>下标&nbsp;<code>i = 2</code> 不是主导元素，因为它右侧没有元素。因此答案是 2。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [4,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>在下标&nbsp;<code>i = 0</code> 处，值 4 是主导元素，相对于子数组 <code>[1, 2]</code>。</li>
	<li>在下标&nbsp;<code>i = 1</code> 处，值 1 不是主导元素。</li>
	<li>下标&nbsp;<code>i = 2</code> 不是主导元素，因为它右侧没有元素。因此答案是 1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code>​​​​​​​</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：逆序遍历

我们可以从后向前遍历数组，维护一个后缀和 $\text{suf}$，表示当前元素右侧所有元素的和。对于每个元素，判断其是否大于右侧元素的平均值 $\frac{\text{suf}}{n - i - 1}$，如果是，则将答案加一。最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\text{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def dominantIndices(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        suf = nums[-1]
        for i in range(n - 2, -1, -1):
            if nums[i] > suf / (n - i - 1):
                ans += 1
            suf += nums[i]
        return ans
```

#### Java

```java
class Solution {
    public int dominantIndices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int suf = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] * (n - i - 1) > suf) {
                ans++;
            }
            suf += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int dominantIndices(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        int suf = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] * (n - i - 1) > suf) {
                ans++;
            }
            suf += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func dominantIndices(nums []int) int {
	n := len(nums)
	ans := 0
	suf := nums[n-1]
	for i := n - 2; i >= 0; i-- {
		if nums[i]*(n-i-1) > suf {
			ans++
		}
		suf += nums[i]
	}
	return ans
}
```

#### TypeScript

```ts
function dominantIndices(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    let suf = nums[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] * (n - i - 1) > suf) {
            ans++;
        }
        suf += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
