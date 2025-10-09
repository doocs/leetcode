---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3701.Compute%20Alternating%20Sum/README.md
rating: 1228
source: 第 470 场周赛 Q1
---

<!-- problem:start -->

# [3701. 计算交替和](https://leetcode.cn/problems/compute-alternating-sum)

[English Version](/solution/3700-3799/3701.Compute%20Alternating%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p><strong>交替和&nbsp;</strong>定义为：将 <code>nums</code> 中偶数下标位置的元素&nbsp;<strong>相加&nbsp;</strong>，<strong>减去</strong> 奇数下标位置的元素。即：<code>nums[0] - nums[1] + nums[2] - nums[3]...</code></p>

<p>返回表示 <code>nums</code> 的交替和的整数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,3,5,7]</span></p>

<p><strong>输出：</strong> <span class="example-io">-4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>偶数下标位置的元素是 <code>nums[0] = 1</code> 和 <code>nums[2] = 5</code>，因为 0 和 2 是偶数。</li>
	<li>奇数下标位置的元素是 <code>nums[1] = 3</code> 和 <code>nums[3] = 7</code>，因为 1 和 3 是奇数。</li>
	<li>交替和为 <code>nums[0] - nums[1] + nums[2] - nums[3] = 1 - 3 + 5 - 7 = -4</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [100]</span></p>

<p><strong>输出：</strong> <span class="example-io">100</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>唯一的偶数下标位置的元素是 <code>nums[0] = 100</code>，因为 0 是偶数。</li>
	<li>没有奇数下标位置的元素。</li>
	<li>交替和为 <code>nums[0] = 100</code>。</li>
</ul>
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

我们可以直接遍历数组 $\textit{nums}$，对于每个下标 $i$，如果 $i$ 是偶数，则将 $\textit{nums}[i]$ 加到答案中，否则将 $\textit{nums}[i]$ 减去。

最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def alternatingSum(self, nums: List[int]) -> int:
        return sum(nums[0::2]) - sum(nums[1::2])
```

#### Java

```java
class Solution {
    public int alternatingSum(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            ans += (i % 2 == 0 ? nums[i] : -nums[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int alternatingSum(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            ans += (i % 2 == 0 ? nums[i] : -nums[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func alternatingSum(nums []int) (ans int) {
	for i, x := range nums {
		if i%2 == 0 {
			ans += x
		} else {
			ans -= x
		}
	}
	return
}
```

#### TypeScript

```ts
function alternatingSum(nums: number[]): number {
    let ans: number = 0;
    for (let i = 0; i < nums.length; ++i) {
        ans += i % 2 === 0 ? nums[i] : -nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
