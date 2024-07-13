---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0414.Third%20Maximum%20Number/README.md
tags:
    - 数组
    - 排序
---

<!-- problem:start -->

# [414. 第三大的数](https://leetcode.cn/problems/third-maximum-number)

[English Version](/solution/0400-0499/0414.Third%20Maximum%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个非空数组，返回此数组中 <strong>第三大的数</strong> 。如果不存在，则返回数组中最大的数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[3, 2, 1]
<strong>输出：</strong>1
<strong>解释：</strong>第三大的数是 1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[1, 2]
<strong>输出：</strong>2
<strong>解释：</strong>第三大的数不存在, 所以返回最大的数 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>[2, 2, 3, 1]
<strong>输出：</strong>1
<strong>解释：</strong>注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你能设计一个时间复杂度 <code>O(n)</code> 的解决方案吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们可以使用三个变量 $m_1$, $m_2$, $m_3$ 分别表示数组中的第一大、第二大和第三大的数。初始时，我们将这三个变量都赋值为负无穷大。

然后，我们遍历数组中的每个数，对于每个数，我们将其与 $m_1$, $m_2$, $m_3$ 进行比较，根据比较的结果更新这三个变量。具体地，我们遍历数组中的每个数，对于每个数：

-   如果这个数等于 $m_1$, $m_2$, $m_3$ 中的任何一个，我们跳过这个数；
-   如果这个数大于 $m_1$，我们将 $m_1$, $m_2$, $m_3$ 的值更新为 $m_2$, $m_3$, 这个数；
-   如果这个数大于 $m_2$，我们将 $m_2$, $m_3$ 的值更新为 $m_3$, 这个数；
-   如果这个数大于 $m_3$，我们将 $m_3$ 的值更新为这个数。

最后，如果 $m_3$ 的值没有被更新，说明数组中不存在第三大的数，那么我们返回 $m_1$，否则我们返回 $m_3$。

时间复杂度 $O(n)$，其中 $n$ 是数组 `nums` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def thirdMax(self, nums: List[int]) -> int:
        m1 = m2 = m3 = -inf
        for num in nums:
            if num in [m1, m2, m3]:
                continue
            if num > m1:
                m3, m2, m1 = m2, m1, num
            elif num > m2:
                m3, m2 = m2, num
            elif num > m3:
                m3 = num
        return m3 if m3 != -inf else m1
```

#### Java

```java
class Solution {
    public int thirdMax(int[] nums) {
        long m1 = Long.MIN_VALUE;
        long m2 = Long.MIN_VALUE;
        long m3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num == m1 || num == m2 || num == m3) {
                continue;
            }
            if (num > m1) {
                m3 = m2;
                m2 = m1;
                m1 = num;
            } else if (num > m2) {
                m3 = m2;
                m2 = num;
            } else if (num > m3) {
                m3 = num;
            }
        }
        return (int) (m3 != Long.MIN_VALUE ? m3 : m1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int thirdMax(vector<int>& nums) {
        long m1 = LONG_MIN, m2 = LONG_MIN, m3 = LONG_MIN;
        for (int num : nums) {
            if (num == m1 || num == m2 || num == m3) continue;
            if (num > m1) {
                m3 = m2;
                m2 = m1;
                m1 = num;
            } else if (num > m2) {
                m3 = m2;
                m2 = num;
            } else if (num > m3) {
                m3 = num;
            }
        }
        return (int) (m3 != LONG_MIN ? m3 : m1);
    }
};
```

#### Go

```go
func thirdMax(nums []int) int {
	m1, m2, m3 := math.MinInt64, math.MinInt64, math.MinInt64
	for _, num := range nums {
		if num == m1 || num == m2 || num == m3 {
			continue
		}
		if num > m1 {
			m3, m2, m1 = m2, m1, num
		} else if num > m2 {
			m3, m2 = m2, num
		} else if num > m3 {
			m3 = num
		}
	}
	if m3 != math.MinInt64 {
		return m3
	}
	return m1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
