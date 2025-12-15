---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2708.Maximum%20Strength%20of%20a%20Group/README.md
rating: 1502
source: 第 105 场双周赛 Q3
tags:
    - 贪心
    - 位运算
    - 数组
    - 动态规划
    - 回溯
    - 枚举
    - 排序
---

<!-- problem:start -->

# [2708. 一个小组的最大实力值](https://leetcode.cn/problems/maximum-strength-of-a-group)

[English Version](/solution/2700-2799/2708.Maximum%20Strength%20of%20a%20Group/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，它表示一个班级中所有学生在一次考试中的成绩。老师想选出一部分同学组成一个 <strong>非空</strong>&nbsp;小组，且这个小组的 <strong>实力值</strong>&nbsp;最大，如果这个小组里的学生下标为&nbsp;<code>i<sub>0</sub></code>, <code>i<sub>1</sub></code>, <code>i<sub>2</sub></code>, ... , <code>i<sub>k</sub></code>&nbsp;，那么这个小组的实力值定义为&nbsp;<code>nums[i<sub>0</sub>] * nums[i<sub>1</sub>] * nums[i<sub>2</sub>] * ... * nums[i<sub>k</sub>​]</code>&nbsp;。</p>

<p>请你返回老师创建的小组能得到的最大实力值为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [3,-1,-5,2,5,-9]
<strong>输出：</strong>1350
<b>解释：</b>一种构成最大实力值小组的方案是选择下标为 [0,2,3,4,5] 的学生。实力值为 3 * (-5) * 2 * 5 * (-9) = 1350 ，这是可以得到的最大实力值。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [-4,-5,-4]
<b>输出：</b>20
<b>解释：</b>选择下标为 [0, 1] 的学生。得到的实力值为 20 。我们没法得到更大的实力值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 13</code></li>
	<li><code>-9 &lt;= nums[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二进制枚举

题目实际上是求所有子集的乘积的最大值，由于数组长度不超过 $13$，我们可以考虑使用二进制枚举的方法。

我们在 $[1, 2^n)$ 的范围内枚举所有的子集，对于每个子集，我们计算其乘积，最后返回最大值即可。

时间复杂度 $O(2^n \times n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxStrength(self, nums: List[int]) -> int:
        ans = -inf
        for i in range(1, 1 << len(nums)):
            t = 1
            for j, x in enumerate(nums):
                if i >> j & 1:
                    t *= x
            ans = max(ans, t)
        return ans
```

#### Java

```java
class Solution {
    public long maxStrength(int[] nums) {
        long ans = (long) -1e14;
        int n = nums.length;
        for (int i = 1; i < 1 << n; ++i) {
            long t = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    t *= nums[j];
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxStrength(vector<int>& nums) {
        long long ans = -1e14;
        int n = nums.size();
        for (int i = 1; i < 1 << n; ++i) {
            long long t = 1;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    t *= nums[j];
                }
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

#### Go

```go
func maxStrength(nums []int) int64 {
	ans := int64(-1e14)
	for i := 1; i < 1<<len(nums); i++ {
		var t int64 = 1
		for j, x := range nums {
			if i>>j&1 == 1 {
				t *= int64(x)
			}
		}
		ans = max(ans, t)
	}
	return ans
}
```

#### TypeScript

```ts
function maxStrength(nums: number[]): number {
    let ans = -Infinity;
    const n = nums.length;
    for (let i = 1; i < 1 << n; ++i) {
        let t = 1;
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                t *= nums[j];
            }
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：排序 + 贪心

我们可以先对数组进行排序，然后根据数组的特点，我们可以得到以下结论：

- 如果数组中只有一个元素，那么最大实力值就是这个元素；
- 如果数组中有两个及以上的元素，且数组中 $nums[1] = nums[n - 1] = 0$，那么最大实力值就是 $0$；
- 否则，我们从小到大遍历数组，如果当前元素小于 $0$，且下一个元素也小于 $0$，那么我们将这两个元素相乘，累乘到答案中；否则，如果当前元素小于等于 $0$，我们直接跳过；如果当前元素大于 $0$，我们将这个元素累乘到答案中，最后返回答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxStrength(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        if n == 1:
            return nums[0]
        if nums[1] == nums[-1] == 0:
            return 0
        ans, i = 1, 0
        while i < n:
            if nums[i] < 0 and i + 1 < n and nums[i + 1] < 0:
                ans *= nums[i] * nums[i + 1]
                i += 2
            elif nums[i] <= 0:
                i += 1
            else:
                ans *= nums[i]
                i += 1
        return ans
```

#### Java

```java
class Solution {
    public long maxStrength(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (nums[1] == 0 && nums[n - 1] == 0) {
            return 0;
        }
        long ans = 1;
        int i = 0;
        while (i < n) {
            if (nums[i] < 0 && i + 1 < n && nums[i + 1] < 0) {
                ans *= nums[i] * nums[i + 1];
                i += 2;
            } else if (nums[i] <= 0) {
                i += 1;
            } else {
                ans *= nums[i];
                i += 1;
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
    long long maxStrength(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        if (n == 1) {
            return nums[0];
        }
        if (nums[1] == 0 && nums[n - 1] == 0) {
            return 0;
        }
        long long ans = 1;
        int i = 0;
        while (i < n) {
            if (nums[i] < 0 && i + 1 < n && nums[i + 1] < 0) {
                ans *= nums[i] * nums[i + 1];
                i += 2;
            } else if (nums[i] <= 0) {
                i += 1;
            } else {
                ans *= nums[i];
                i += 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxStrength(nums []int) int64 {
	sort.Ints(nums)
	n := len(nums)
	if n == 1 {
		return int64(nums[0])
	}
	if nums[1] == 0 && nums[n-1] == 0 {
		return 0
	}
	ans := int64(1)
	for i := 0; i < n; i++ {
		if nums[i] < 0 && i+1 < n && nums[i+1] < 0 {
			ans *= int64(nums[i] * nums[i+1])
			i++
		} else if nums[i] > 0 {
			ans *= int64(nums[i])
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxStrength(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    if (n === 1) {
        return nums[0];
    }
    if (nums[1] === 0 && nums[n - 1] === 0) {
        return 0;
    }
    let ans = 1;
    for (let i = 0; i < n; ++i) {
        if (nums[i] < 0 && i + 1 < n && nums[i + 1] < 0) {
            ans *= nums[i] * nums[i + 1];
            ++i;
        } else if (nums[i] > 0) {
            ans *= nums[i];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
