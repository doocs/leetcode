---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3880.Minimum%20Absolute%20Difference%20Between%20Two%20Values/README.md
rating: 1257
source: 第 179 场双周赛 Q1
tags:
    - 数组
    - 枚举
---

<!-- problem:start -->

# [3880. 两个值之间的最小绝对差值](https://leetcode.cn/problems/minimum-absolute-difference-between-two-values)

[English Version](/solution/3800-3899/3880.Minimum%20Absolute%20Difference%20Between%20Two%20Values/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个只包含 0、1 和 2 的整数数组 <code>nums</code>。</p>

<p>如果 <code>nums[i] == 1</code> 且 <code>nums[j] == 2</code>，则称下标对 <code>(i, j)</code> 为 <strong>有效</strong> 的。</p>

<p>请返回所有有效下标对中 <code>i</code> 和 <code>j</code> 之间的 <strong>最小</strong> 绝对差。如果不存在有效下标对，则返回 -1。</p>

<p>下标 <code>i</code> 和 <code>j</code> 之间的绝对差定义为 <code>abs(i - j)</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,0,0,2,0,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>有效下标对有：</p>

<ul>
	<li>(0, 3)，其绝对差为 <code>abs(0 - 3) = 3</code>。</li>
	<li>(5, 3)，其绝对差为 <code>abs(5 - 3) = 2</code>。</li>
</ul>

<p>因此，结果是 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,0,1,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>数组中不存在有效下标对，因此结果是 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们用一个长度为 $3$ 的数组 $\textit{last}$ 来记录数字 $0$, $1$ 和 $2$ 最后一次出现的下标。初始时 $\textit{last} = [-(n+1), -(n+1), -(n+1)]$。我们遍历数组 $\textit{nums}$，对于当前遍历到的数字 $x$，如果 $x$ 不等于 $0$，则更新答案 $\textit{ans} = \min(\textit{ans}, i - \textit{last}[3 - x])$，其中 $i$ 是当前遍历到的数字 $x$ 的下标。然后更新 $\textit{last}[x] = i$。

遍历结束后，如果 $\textit{ans}$ 大于数组 $\textit{nums}$ 的长度，则说明不存在有效下标对，返回 -1；否则返回 $\textit{ans}$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minAbsoluteDifference(self, nums: list[int]) -> int:
        n = len(nums)
        ans = n + 1
        last = [-inf] * 3
        for i, x in enumerate(nums):
            if x:
                ans = min(ans, i - last[3 - x])
                last[x] = i
        return -1 if ans > n else ans
```

#### Java

```java
class Solution {
    public int minAbsoluteDifference(int[] nums) {
        int n = nums.length;
        int ans = n + 1;
        int[] last = new int[3];
        Arrays.fill(last, -(n + 1));

        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            if (x != 0) {
                ans = Math.min(ans, i - last[3 - x]);
                last[x] = i;
            }
        }
        return ans > n ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums) {
        int n = nums.size();
        int ans = n + 1;
        vector<int> last(3, -(n + 1));

        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            if (x != 0) {
                ans = min(ans, i - last[3 - x]);
                last[x] = i;
            }
        }
        return ans > n ? -1 : ans;
    }
};
```

#### Go

```go
func minAbsoluteDifference(nums []int) int {
	n := len(nums)
	ans := n + 1

	last := []int{-ans, -ans, -ans}

	for i, x := range nums {
		if x != 0 {
			ans = min(ans, i-last[3-x])
			last[x] = i
		}
	}

	if ans > n {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minAbsoluteDifference(nums: number[]): number {
    const n = nums.length;
    let ans = n + 1;
    const last = Array(3).fill(-ans);

    for (let i = 0; i < n; ++i) {
        const x = nums[i];
        if (x) {
            ans = Math.min(ans, i - last[3 - x]);
            last[x] = i;
        }
    }

    return ans > n ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
