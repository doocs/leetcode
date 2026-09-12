---
comments: true
difficulty: 中等
tags:
    - 数组
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [2393. 严格递增的子数组个数 🔒](https://leetcode.cn/problems/count-strictly-increasing-subarrays)

[English Version](/solution/2300-2399/2393.Count%20Strictly%20Increasing%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个由&nbsp;<strong>正整数&nbsp;</strong>组成的数组 <code>nums</code> 。</p>

<p>返回&nbsp;<em><strong>严格递增&nbsp;</strong>顺序的 </em><code>nums</code><em>&nbsp;<strong>子数组&nbsp;</strong>的数目。</em></p>

<p data-group="1-1"><strong>子数组&nbsp;</strong>是数组的一部分，且是&nbsp;<strong>连续 </strong>的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,3,5,4,4,6]
<strong>输出:</strong> 10
<strong>解释:</strong> 严格递增的子数组如下:
- 长度为 1 的子数组: [1], [3], [5], [4], [4], [6]。
- 长度为 2 的子数组: [1,3], [3,5], [4,6]。
- 长度为 3 的子数组: [1,3,5]。
子数组的总数是 6 + 3 + 1 = 10。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,5]
<strong>输出:</strong> 15
<strong>解释:</strong> 每个子数组都严格递增。我们可以取 15 个子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

<!-- thinking:start -->

> **思考**
>
> 统计严格递增的连续子数组。以右端点分类后，长度由当前递增段决定，不必枚举左端。
>
> 维护以当前元素结尾的递增长度 $cnt$：比前一个大则加一，否则重置为 $1$，并累加 $cnt$。

<!-- thinking:end -->

我们可以枚举以每个元素结尾的严格递增子数组的个数，然后将它们累加起来即可。

我们用一个变量 $\textit{cnt}$ 来记录以当前元素结尾的严格递增子数组的个数，初始时 $\textit{cnt} = 1$。然后我们从第二个元素开始遍历数组，如果当前元素大于前一个元素，那么 $\textit{cnt}$ 就可以加 $1$，否则 $\textit{cnt}$ 重置为 $1$。此时，以当前元素结尾的严格递增子数组的个数就是 $\textit{cnt}$，我们将其累加到答案中即可。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubarrays(self, nums: List[int]) -> int:
        ans = cnt = 1
        for x, y in pairwise(nums):
            if x < y:
                cnt += 1
            else:
                cnt = 1
            ans += cnt
        return ans
```

#### Java

```java
class Solution {
    public long countSubarrays(int[] nums) {
        long ans = 1, cnt = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] < nums[i]) {
                ++cnt;
            } else {
                cnt = 1;
            }
            ans += cnt;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums) {
        long long ans = 1, cnt = 1;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                ++cnt;
            } else {
                cnt = 1;
            }
            ans += cnt;
        }
        return ans;
    }
};
```

#### Go

```go
func countSubarrays(nums []int) int64 {
	ans, cnt := 1, 1
	for i, x := range nums[1:] {
		if nums[i] < x {
			cnt++
		} else {
			cnt = 1
		}
		ans += cnt
	}
	return int64(ans)
}
```

#### TypeScript

```ts
function countSubarrays(nums: number[]): number {
    let [ans, cnt] = [1, 1];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            ++cnt;
        } else {
            cnt = 1;
        }
        ans += cnt;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
