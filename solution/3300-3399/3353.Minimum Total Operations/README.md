---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3353.Minimum%20Total%20Operations/README.md
tags:
    - 数组
---

<!-- problem:start -->

# [3353. 最小总操作数 🔒](https://leetcode.cn/problems/minimum-total-operations)

[English Version](/solution/3300-3399/3353.Minimum%20Total%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code><font face="monospace">nums</font></code>，你可以在这个数组上进行&nbsp;<em>任意</em>&nbsp;次操作。</p>

<p>在每次 <strong>操作</strong>&nbsp;中，你可以：</p>

<ul>
	<li>选择这个数组的一个 <strong>前缀</strong>。</li>
	<li>选择一个整数&nbsp;<code><font face="monospace">k</font></code>（可以为负）并且对选中前缀的每一个元素加&nbsp;<code><font face="monospace">k</font></code>。</li>
</ul>

<p>数组的 <strong>前缀</strong> 是一个子数组，从数组的开头开始并延伸到数组内的任何位置。</p>

<p>返回使&nbsp;<code>arr</code>&nbsp;中的所有元素都相等的 <strong>最小</strong>&nbsp;操作数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,4,2]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>操作 1</strong>：选择长度为 2 的前缀&nbsp;<code>[1, 4]</code>&nbsp;并且对其中的所有元素加&nbsp;-2。数组变为&nbsp;<code>[-1, 2, 2]</code>。</li>
	<li><strong>操作 2</strong>：选择长度为 1 的前缀&nbsp;<code>[-1]</code>&nbsp;并且对其中的所有元素加 3。数组变为&nbsp;<code>[2, 2, 2]</code>。</li>
	<li>因此，所需的最小操作数为 2。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [10,10,10]</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>所有元素已经相等，所以不需要操作。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们可以遍历数组，对于每个元素，如果它与前一个元素不相等，那么就需要进行一次操作，最后返回操作的次数即可。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        return sum(x != y for x, y in pairwise(nums))
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            ans += nums[i] != nums[i - 1] ? 1 : 0;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            ans += nums[i] != nums[i - 1];
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int) (ans int) {
	for i, x := range nums[1:] {
		if x != nums[i] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    let ans = 0;
    for (let i = 1; i < nums.length; ++i) {
        ans += nums[i] !== nums[i - 1] ? 1 : 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
