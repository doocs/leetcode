---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3101.Count%20Alternating%20Subarrays/README.md
rating: 1404
source: 第 391 场周赛 Q3
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [3101. 交替子数组计数](https://leetcode.cn/problems/count-alternating-subarrays)

[English Version](/solution/3100-3199/3101.Count%20Alternating%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个<span data-keyword="binary-array">二进制数组 </span><code>nums</code> 。</p>

<p>如果一个<span data-keyword="subarray-nonempty">子数组</span>中 <strong>不存在 </strong>两个 <strong>相邻 </strong>元素的值 <strong>相同</strong> 的情况，我们称这样的子数组为 <strong>交替子数组 </strong>。</p>

<p>返回数组 <code>nums</code> 中交替子数组的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>
<!-- 解释示例1的交替子数组 -->

<p>以下子数组是交替子数组：<code>[0]</code> 、<code>[1]</code> 、<code>[1]</code> 、<code>[1]</code> 以及 <code>[0,1]</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,0,1,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>
<!-- 解释示例2的交替子数组 -->

<p>数组的每个子数组都是交替子数组。可以统计在内的子数组共有 10 个。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 不是 <code>0</code> 就是 <code>1</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举以每个位置结尾的子数组，计算满足条件的子数组的数量，累加所有位置的满足条件的子数组的数量即可。

具体地，我们定义变量 $s$ 表示以元素 $nums[i]$ 结尾的满足条件的子数组的数量，初始时我们将 $s$ 置为 $1$，表示以第一个元素结尾的满足条件的子数组的数量为 $1$。

接下来，我们从第二个元素开始遍历数组，对于每个位置 $i$，我们根据 $nums[i]$ 和 $nums[i-1]$ 的关系更新 $s$ 的值：

- 如果 $nums[i] \neq nums[i-1]$，则 $s$ 的值增加 $1$，即 $s = s + 1$；
- 如果 $nums[i] = nums[i-1]$，则 $s$ 的值重置为 $1$，即 $s = 1$。

然后，我们将 $s$ 的值累加到答案中，继续遍历数组的下一个位置，直到遍历完整个数组。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countAlternatingSubarrays(self, nums: List[int]) -> int:
        ans = s = 1
        for a, b in pairwise(nums):
            s = s + 1 if a != b else 1
            ans += s
        return ans
```

#### Java

```java
class Solution {
    public long countAlternatingSubarrays(int[] nums) {
        long ans = 1, s = 1;
        for (int i = 1; i < nums.length; ++i) {
            s = nums[i] != nums[i - 1] ? s + 1 : 1;
            ans += s;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countAlternatingSubarrays(vector<int>& nums) {
        long long ans = 1, s = 1;
        for (int i = 1; i < nums.size(); ++i) {
            s = nums[i] != nums[i - 1] ? s + 1 : 1;
            ans += s;
        }
        return ans;
    }
};
```

#### Go

```go
func countAlternatingSubarrays(nums []int) int64 {
	ans, s := int64(1), int64(1)
	for i, x := range nums[1:] {
		if x != nums[i] {
			s++
		} else {
			s = 1
		}
		ans += s
	}
	return ans
}
```

#### TypeScript

```ts
function countAlternatingSubarrays(nums: number[]): number {
    let [ans, s] = [1, 1];
    for (let i = 1; i < nums.length; ++i) {
        s = nums[i] !== nums[i - 1] ? s + 1 : 1;
        ans += s;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
