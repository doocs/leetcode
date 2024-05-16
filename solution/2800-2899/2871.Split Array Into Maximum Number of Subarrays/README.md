---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2871.Split%20Array%20Into%20Maximum%20Number%20of%20Subarrays/README.md
rating: 1749
source: 第 114 场双周赛 Q3
tags:
    - 贪心
    - 位运算
    - 数组
---

# [2871. 将数组分割成最多数目的子数组](https://leetcode.cn/problems/split-array-into-maximum-number-of-subarrays)

[English Version](/solution/2800-2899/2871.Split%20Array%20Into%20Maximum%20Number%20of%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个只包含 <strong>非负</strong>&nbsp;整数的数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>我们定义满足 <code>l &lt;= r</code>&nbsp;的子数组&nbsp;<code>nums[l..r]</code>&nbsp;的分数为&nbsp;<code>nums[l] AND nums[l + 1] AND ... AND nums[r]</code>&nbsp;，其中&nbsp;<strong>AND</strong>&nbsp;是按位与运算。</p>

<p>请你将数组分割成一个或者更多子数组，满足：</p>

<ul>
	<li><strong>每个</strong> 元素都&nbsp;<strong>只</strong>&nbsp;属于一个子数组。</li>
	<li>子数组分数之和尽可能<strong>&nbsp;小</strong>&nbsp;。</li>
</ul>

<p>请你在满足以上要求的条件下，返回<strong>&nbsp;最多</strong>&nbsp;可以得到多少个子数组。</p>

<p>一个 <strong>子数组</strong>&nbsp;是一个数组中一段连续的元素。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,0,2,0,1,2]
<b>输出：</b>3
<strong>解释：</strong>我们可以将数组分割成以下子数组：
- [1,0] 。子数组分数为 1 AND 0 = 0 。
- [2,0] 。子数组分数为 2 AND 0 = 0 。
- [1,2] 。子数组分数为 1 AND 2 = 0 。
分数之和为 0 + 0 + 0 = 0 ，是我们可以得到的最小分数之和。
在分数之和为 0 的前提下，最多可以将数组分割成 3 个子数组。所以返回 3 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,7,1,3]
<b>输出：</b>1
<b>解释：</b>我们可以将数组分割成一个子数组：[5,7,1,3] ，分数为 1 ，这是可以得到的最小总分数。
在总分数为 1 的前提下，最多可以将数组分割成 1 个子数组。所以返回 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一：贪心 + 位运算

我们初始化一个变量 $score$，用来记录当前子数组的分数，初始时 $score = -1$。然后我们遍历数组，对于每个元素 $num$，我们将 $score$ 与 $num$ 进行按位与运算，然后将结果赋值给 $score$。如果 $score = 0$，说明当前子数组的分数为 $0$，我们就可以将当前子数组分割出来，然后将 $score$ 重置为 $-1$。最后我们返回分割出的子数组的个数。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maxSubarrays(self, nums: List[int]) -> int:
        score, ans = -1, 1
        for num in nums:
            score &= num
            if score == 0:
                score = -1
                ans += 1
        return 1 if ans == 1 else ans - 1
```

```java
class Solution {
    public int maxSubarrays(int[] nums) {
        int score = -1;
        int ans = 1;
        for (int num : nums) {
            score &= num;
            if (score == 0) {
                ans++;
                score = -1;
            }
        }
        return ans == 1 ? 1 : ans - 1;
    }
}
```

```cpp
class Solution {
public:
    int maxSubarrays(vector<int>& nums) {
        int score = -1, ans = 1;
        for (int num : nums) {
            score &= num;
            if (score == 0) {
                --score;
                ++ans;
            }
        }
        return ans == 1 ? 1 : ans - 1;
    }
};
```

```go
func maxSubarrays(nums []int) int {
	ans, score := 1, -1
	for _, num := range nums {
		score &= num
		if score == 0 {
			score--
			ans++
		}
	}
	if ans == 1 {
		return 1
	}
	return ans - 1
}
```

```ts
function maxSubarrays(nums: number[]): number {
    let [ans, score] = [1, -1];
    for (const num of nums) {
        score &= num;
        if (score === 0) {
            --score;
            ++ans;
        }
    }
    return ans == 1 ? 1 : ans - 1;
}
```

<!-- tabs:end -->

<!-- end -->
