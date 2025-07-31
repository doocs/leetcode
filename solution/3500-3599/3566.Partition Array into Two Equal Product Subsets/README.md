---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3566.Partition%20Array%20into%20Two%20Equal%20Product%20Subsets/README.md
rating: 1459
source: 第 452 场周赛 Q1
tags:
    - 位运算
    - 递归
    - 数组
    - 枚举
---

<!-- problem:start -->

# [3566. 等积子集的划分方案](https://leetcode.cn/problems/partition-array-into-two-equal-product-subsets)

[English Version](/solution/3500-3599/3566.Partition%20Array%20into%20Two%20Equal%20Product%20Subsets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>，其中包含的正整数&nbsp;<strong>互不相同&nbsp;</strong>，另给你一个整数 <code>target</code>。</p>

<p>请判断是否可以将 <code>nums</code> 分成两个&nbsp;<strong>非空</strong>、<strong>互不相交&nbsp;</strong>的&nbsp;<strong>子集&nbsp;</strong>，并且每个元素必须 &nbsp;<strong>恰好 </strong>属于&nbsp;<strong>一个&nbsp;</strong>子集，使得这两个子集中元素的乘积都等于 <code>target</code>。</p>

<p>如果存在这样的划分，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p><strong>子集&nbsp;</strong>是数组中元素的一个选择集合。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,1,6,8,4], target = 24</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong>子集 <code>[3, 8]</code> 和 <code>[1, 6, 4]</code> 的乘积均为 24。因此，输出为 true 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,5,3,7], target = 15</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong>无法将 <code>nums</code> 划分为两个非空的互不相交子集，使得它们的乘积均为 15。因此，输出为 false。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 12</code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> 中的所有元素互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二进制枚举

我们可以使用二进制枚举的方式来检查所有可能的子集划分。对于每个子集划分，我们可以计算两个子集的乘积，并检查它们是否都等于目标值。

具体地，我们可以使用一个整数 $i$ 来表示子集划分的状态，其中 $i$ 的二进制位表示每个元素是否属于第一个子集。对于每个可能的 $i$，我们可以计算两个子集的乘积，并检查它们是否都等于目标值。

时间复杂度 $O(2^n \times n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkEqualPartitions(self, nums: List[int], target: int) -> bool:
        n = len(nums)
        for i in range(1 << n):
            x = y = 1
            for j in range(n):
                if i >> j & 1:
                    x *= nums[j]
                else:
                    y *= nums[j]
            if x == target and y == target:
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean checkEqualPartitions(int[] nums, long target) {
        int n = nums.length;
        for (int i = 0; i < 1 << n; ++i) {
            long x = 1, y = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    x *= nums[j];
                } else {
                    y *= nums[j];
                }
            }
            if (x == target && y == target) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkEqualPartitions(vector<int>& nums, long long target) {
        int n = nums.size();
        for (int i = 0; i < 1 << n; ++i) {
            long long x = 1, y = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    x *= nums[j];
                } else {
                    y *= nums[j];
                }
                if (x > target || y > target) {
                    break;
                }
            }
            if (x == target && y == target) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func checkEqualPartitions(nums []int, target int64) bool {
	n := len(nums)
	for i := 0; i < 1<<n; i++ {
		x, y := int64(1), int64(1)
		for j, v := range nums {
			if i>>j&1 == 1 {
				x *= int64(v)
			} else {
				y *= int64(v)
			}
			if x > target || y > target {
				break
			}
		}
		if x == target && y == target {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function checkEqualPartitions(nums: number[], target: number): boolean {
    const n = nums.length;
    for (let i = 0; i < 1 << n; ++i) {
        let [x, y] = [1, 1];
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 1) {
                x *= nums[j];
            } else {
                y *= nums[j];
            }
            if (x > target || y > target) {
                break;
            }
        }
        if (x === target && y === target) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
