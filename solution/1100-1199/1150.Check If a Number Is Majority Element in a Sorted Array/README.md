---
comments: true
difficulty: 简单
rating: 1249
source: 第 6 场双周赛 Q1
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [1150. 检查一个数是否在数组中占绝大多数 🔒](https://leetcode.cn/problems/check-if-a-number-is-majority-element-in-a-sorted-array)

[English Version](/solution/1100-1199/1150.Check%20If%20a%20Number%20Is%20Majority%20Element%20in%20a%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出一个按 <strong>非递减</strong> 顺序排列的数组 <code>nums</code>，和一个目标数值 <code>target</code>。假如数组 <code>nums</code> 中绝大多数元素的数值都等于 <code>target</code>，则返回 <code>True</code>，否则请返回 <code>False</code>。</p>

<p>所谓占绝大多数，是指在长度为 <code>N</code> 的数组中出现必须<strong> 超过 <code>N/2</code></strong> <strong>次</strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4,5,5,5,5,5,6,6], target = 5
<strong>输出：</strong>true
<strong>解释：</strong>
数字 5 出现了 5 次，而数组的长度为 9。
所以，5 在数组中占绝大多数，因为 5 次 > 9/2。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,100,101,101], target = 101
<strong>输出：</strong>false
<strong>解释：</strong>
数字 101 出现了 2 次，而数组的长度是 4。
所以，101 <strong>不是 </strong>数组占绝大多数的元素，因为 2 次 = 4/2。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 1000</code></li>
	<li><code>1 <= nums[i] <= 10^9</code></li>
	<li><code>1 <= target <= 10^9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

<!-- thinking:start -->

> **思考**
>
> 有序数组中 $target$ 的出现次数等于其左右边界之差。两次二分分别得第一个 $\ge target$ 与第一个 $>target$ 的下标，差大于 $n/2$ 则为多数元素。线性计数在 $n$ 较大时没有必要。

<!-- thinking:end -->

我们注意到，数组 $nums$ 中的元素是非递减的，也就是说，数组 $nums$ 中的元素单调递增。因此，我们可以使用二分查找的方法，找到数组 $nums$ 中第一个大于等于 $target$ 的元素的下标 $left$，以及第一个大于 $target$ 的元素的下标 $right$。如果 $right - left > \frac{n}{2}$，则说明数组 $nums$ 中的元素 $target$ 出现的次数超过了数组长度的一半，因此返回 $true$，否则返回 $false$。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        left = bisect_left(nums, target)
        right = bisect_right(nums, target)
        return right - left > len(nums) // 2
```

#### Java

```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int left = search(nums, target);
        int right = search(nums, target + 1);
        return right - left > nums.length / 2;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isMajorityElement(vector<int>& nums, int target) {
        auto left = lower_bound(nums.begin(), nums.end(), target);
        auto right = upper_bound(nums.begin(), nums.end(), target);
        return right - left > nums.size() / 2;
    }
};
```

#### Go

```go
func isMajorityElement(nums []int, target int) bool {
	left := sort.SearchInts(nums, target)
	right := sort.SearchInts(nums, target+1)
	return right-left > len(nums)/2
}
```

#### TypeScript

```ts
function isMajorityElement(nums: number[], target: number): boolean {
    const search = (x: number) => {
        let left = 0;
        let right = nums.length;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const left = search(target);
    const right = search(target + 1);
    return right - left > nums.length >> 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：二分查找（优化）

<!-- thinking:start -->

> **思考**
>
> 方法一用两次二分定位区间。若 $target$ 出现超过一半，则 $left+\lfloor n/2\rfloor$ 处仍应等于 $target$。一次 `bisect_left` 后再做一次下标检查即可，少一次二分。

<!-- thinking:end -->

方法一中，我们使用了两次二分查找，分别找到数组 $nums$ 中第一个大于等于 $target$ 的元素的下标 $left$，以及第一个大于 $target$ 的元素的下标 $right$。但是，我们可以使用一次二分查找，找到数组 $nums$ 中第一个大于等于 $target$ 的元素的下标 $left$，然后判断 $nums[left + \frac{n}{2}]$ 是否等于 $target$，如果相等，说明数组 $nums$ 中的元素 $target$ 出现的次数超过了数组长度的一半，因此返回 $true$，否则返回 $false$。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isMajorityElement(self, nums: List[int], target: int) -> bool:
        left = bisect_left(nums, target)
        right = left + len(nums) // 2
        return right < len(nums) and nums[right] == target
```

#### Java

```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int n = nums.length;
        int left = search(nums, target);
        int right = left + n / 2;
        return right < n && nums[right] == target;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isMajorityElement(vector<int>& nums, int target) {
        int n = nums.size();
        int left = lower_bound(nums.begin(), nums.end(), target) - nums.begin();
        int right = left + n / 2;
        return right < n && nums[right] == target;
    }
};
```

#### Go

```go
func isMajorityElement(nums []int, target int) bool {
	n := len(nums)
	left := sort.SearchInts(nums, target)
	right := left + n/2
	return right < n && nums[right] == target
}
```

#### TypeScript

```ts
function isMajorityElement(nums: number[], target: number): boolean {
    const search = (x: number) => {
        let left = 0;
        let right = n;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const n = nums.length;
    const left = search(target);
    const right = left + (n >> 1);
    return right < n && nums[right] === target;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
