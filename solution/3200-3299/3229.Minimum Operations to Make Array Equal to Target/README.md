---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3229.Minimum%20Operations%20to%20Make%20Array%20Equal%20to%20Target/README.md
rating: 2066
source: 第 407 场周赛 Q4
tags:
    - 栈
    - 贪心
    - 数组
    - 动态规划
    - 单调栈
---

<!-- problem:start -->

# [3229. 使数组等于目标数组所需的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-make-array-equal-to-target)

[English Version](/solution/3200-3299/3229.Minimum%20Operations%20to%20Make%20Array%20Equal%20to%20Target/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度相同的正整数数组 <code>nums</code> 和 <code>target</code>。</p>

<p>在一次操作中，你可以选择 <code>nums</code> 的任何子数组，并将该子数组内的每个元素的值增加或减少 1。</p>

<p>返回使 <code>nums</code> 数组变为 <code>target</code> 数组所需的 <strong>最少 </strong>操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,5,1,2], target = [4,6,2,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>执行以下操作可以使 <code>nums</code> 等于 <code>target</code>：<br />
- <code>nums[0..3]</code> 增加 1，<code>nums = [4,6,2,3]</code>。<br />
- <code>nums[3..3]</code> 增加 1，<code>nums = [4,6,2,4]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,3,2], target = [2,1,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>执行以下操作可以使 <code>nums</code> 等于 <code>target</code>：<br />
- <code>nums[0..0]</code> 增加 1，<code>nums = [2,3,2]</code>。<br />
- <code>nums[1..1]</code> 减少 1，<code>nums = [2,2,2]</code>。<br />
- <code>nums[1..1]</code> 减少 1，<code>nums = [2,1,2]</code>。<br />
- <code>nums[2..2]</code> 增加 1，<code>nums = [2,1,3]</code>。<br />
- <code>nums[2..2]</code> 增加 1，<code>nums = [2,1,4]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们可以先计算出 $\textit{nums}$ 和 $\textit{target}$ 两个数组的差值，然后对于一个差值数组，我们找出连续的差值符号相同的区间，然后对于每个区间，我们将第一个元素的绝对值加到结果中，然后对于后面的元素，如果差值的绝对值比前一个差值的绝对值大，那么我们将绝对值的差值加到结果中。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

相似题目：

-   [1526. 形成目标数组的子数组最少增加次数](https://github.com/doocs/leetcode/tree/main/solution/1500-1599/1526.Minimum%20Number%20of%20Increments%20on%20Subarrays%20to%20Form%20a%20Target%20Array/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperations(self, nums: List[int], target: List[int]) -> int:
        n = len(nums)
        f = abs(target[0] - nums[0])
        for i in range(1, n):
            x = target[i] - nums[i]
            y = target[i - 1] - nums[i - 1]
            if x * y > 0:
                d = abs(x) - abs(y)
                if d > 0:
                    f += d
            else:
                f += abs(x)
        return f
```

#### Java

```java
class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        long f = Math.abs(target[0] - nums[0]);
        for (int i = 1; i < nums.length; ++i) {
            long x = target[i] - nums[i];
            long y = target[i - 1] - nums[i - 1];
            if (x * y > 0) {
                long d = Math.abs(x) - Math.abs(y);
                if (d > 0) {
                    f += d;
                }
            } else {
                f += Math.abs(x);
            }
        }
        return f;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumOperations(vector<int>& nums, vector<int>& target) {
        using ll = long long;
        ll f = abs(target[0] - nums[0]);
        for (int i = 1; i < nums.size(); ++i) {
            long x = target[i] - nums[i];
            long y = target[i - 1] - nums[i - 1];
            if (x * y > 0) {
                ll d = abs(x) - abs(y);
                if (d > 0) {
                    f += d;
                }
            } else {
                f += abs(x);
            }
        }
        return f;
    }
};
```

#### Go

```go
func minimumOperations(nums []int, target []int) int64 {
	f := abs(target[0] - nums[0])
	for i := 1; i < len(target); i++ {
		x := target[i] - nums[i]
		y := target[i-1] - nums[i-1]
		if x*y > 0 {
			if d := abs(x) - abs(y); d > 0 {
				f += d
			}
		} else {
			f += abs(x)
		}
	}
	return int64(f)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minimumOperations(nums: number[], target: number[]): number {
    const n = nums.length;
    let f = Math.abs(target[0] - nums[0]);
    for (let i = 1; i < n; ++i) {
        const x = target[i] - nums[i];
        const y = target[i - 1] - nums[i - 1];
        if (x * y > 0) {
            const d = Math.abs(x) - Math.abs(y);
            if (d > 0) {
                f += d;
            }
        } else {
            f += Math.abs(x);
        }
    }
    return f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
