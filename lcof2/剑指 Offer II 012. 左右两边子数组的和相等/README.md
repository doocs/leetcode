---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20012.%20%E5%B7%A6%E5%8F%B3%E4%B8%A4%E8%BE%B9%E5%AD%90%E6%95%B0%E7%BB%84%E7%9A%84%E5%92%8C%E7%9B%B8%E7%AD%89/README.md
---

<!-- problem:start -->

# [剑指 Offer II 012. 左右两边子数组的和相等](https://leetcode.cn/problems/tvdfij)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code> ，请计算数组的 <strong>中心下标 </strong>。</p>

<p>数组<strong> 中心下标</strong><strong> </strong>是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。</p>

<p>如果中心下标位于数组最左端，那么左侧数之和视为 <code>0</code> ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。</p>

<p>如果数组有多个中心下标，应该返回 <strong>最靠近左边</strong> 的那一个。如果数组不存在中心下标，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,7,3,6,5,6]
<strong>输出：</strong>3
<strong>解释：</strong>
中心下标是 3 。
左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1, 2, 3]
<strong>输出：</strong>-1
<strong>解释：</strong>
数组中不存在满足此条件的中心下标。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2, 1, -1]
<strong>输出：</strong>0
<strong>解释：</strong>
中心下标是 0 。
左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 724&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/find-pivot-index/">https://leetcode.cn/problems/find-pivot-index/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

我们定义变量 $left$ 表示数组 $nums$ 中下标 $i$ 左侧元素之和，变量 $right$ 表示数组 $nums$ 中下标 $i$ 右侧元素之和。初始时 $left = 0$, $right = \sum_{i = 0}^{n - 1} nums[i]$。

遍历数组 $nums$，对于当前遍历到的数字 $x$，我们更新 $right = right - x$，此时如果 $left=right$，说明当前下标 $i$ 就是中间位置，直接返回即可。否则，我们更新 $left = left + x$，继续遍历下一个数字。

遍历结束，如果没有找到中间位置，返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

相似题目：

-   [1991. 找到数组的中间位置](https://github.com/doocs/leetcode/blob/main/solution/1900-1999/1991.Find%20the%20Middle%20Index%20in%20Array/README.md)
-   [2574. 左右元素和的差值](https://github.com/doocs/leetcode/blob/main/solution/2500-2599/2574.Left%20and%20Right%20Sum%20Differences/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pivotIndex(self, nums: List[int]) -> int:
        left, right = 0, sum(nums)
        for i, x in enumerate(nums):
            right -= x
            if left == right:
                return i
            left += x
        return -1
```

#### Java

```java
class Solution {
    public int pivotIndex(int[] nums) {
        int left = 0, right = 0;
        for (int x : nums) {
            right += x;
        }
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            right -= nums[i];
            if (left == right) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int pivotIndex(vector<int>& nums) {
        int left = 0;
        int right = accumulate(nums.begin(), nums.end(), 0);
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            right -= nums[i];
            if (left == right) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
};
```

#### Go

```go
func pivotIndex(nums []int) int {
	left, right := 0, 0
	for _, x := range nums {
		right += x
	}
	for i, x := range nums {
		right -= x
		if left == right {
			return i
		}
		left += x
	}
	return -1
}
```

#### TypeScript

```ts
function pivotIndex(nums: number[]): number {
    let left = 0;
    let right = nums.reduce((a, b) => a + b, 0);
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        right -= nums[i];
        if (left === right) {
            return i;
        }
        left += nums[i];
    }
    return -1;
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function pivotIndex($nums) {
        $left = 0;
        $right = array_sum($nums);
        for ($i = 0; $i < count($nums); $i++) {
            $right -= $nums[$i];
            if ($left == $right) {
                return $i;
            }
            $left += $nums[$i];
        }
        return -1;
    }
}
```

#### C

```c
int pivotIndex(int* nums, int numsSize) {
    int left, right;
    left = 0;
    right = 0;

    for (int i = 0; i < numsSize; i++) {
        right += nums[i];
    }

    for (int i = 0; i < numsSize; i++) {
        right -= nums[i];
        if (right == left)
            return i;
        left += nums[i];
    }

    return -1;
}
```

#### Swift

```swift
class Solution {
    func pivotIndex(_ nums: [Int]) -> Int {
        var leftSum = 0
        var rightSum = nums.reduce(0, +)
        
        for i in 0..<nums.count {
            rightSum -= nums[i]
            if leftSum == rightSum {
                return i
            }
            leftSum += nums[i]
        }
        return -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
