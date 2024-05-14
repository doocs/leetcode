# [1060. 有序数组中的缺失元素 🔒](https://leetcode.cn/problems/missing-element-in-sorted-array)

[English Version](/solution/1000-1099/1060.Missing%20Element%20in%20Sorted%20Array/README_EN.md)

<!-- tags:数组,二分查找 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一个按 <strong>升序</strong> 排列的整数数组 <code>nums</code> ，其中每个数字都 <strong>互不相同</strong> 。</p>

<p>给你一个整数 <code>k</code> ，请你找出并返回从数组最左边开始的第 <code>k</code> 个缺失数字。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,7,9,10], k = 1
<strong>输出：</strong>5
<strong>解释：</strong>第一个缺失数字为 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,7,9,10], k = 3
<strong>输出：</strong>8
<strong>解释：</strong>缺失数字有 [5,6,8,...]，因此第三个缺失数字为 8 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4], k = 3
<strong>输出：</strong>6
<strong>解释：</strong>缺失数字有 [3,5,6,7,...]，因此第三个缺失数字为 6 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>7</sup></code></li>
	<li><code>nums</code> 按 <strong>升序</strong> 排列，其中所有元素 <strong>互不相同</strong> 。</li>
	<li><code>1 <= k <= 10<sup>8</sup></code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你可以设计一个对数时间复杂度（即，<code>O(log(n))</code>）的解决方案吗？</p>

## 解法

### 方法一：二分查找

我们设计一个函数 $missing(i)$，表示 $nums[i]$ 与 $nums[0]$ 之间缺失的元素个数。那么 $missing(i)$ 就等于 $nums[i] - nums[0] - i$。我们可以通过二分查找找到最小的 $i$，使得 $missing(i) \geq k$，那么 $nums[i - 1] + k - missing(i - 1)$ 就是第 $k$ 个缺失的元素。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def missingElement(self, nums: List[int], k: int) -> int:
        def missing(i: int) -> int:
            return nums[i] - nums[0] - i

        n = len(nums)
        if k > missing(n - 1):
            return nums[n - 1] + k - missing(n - 1)
        l, r = 0, n - 1
        while l < r:
            mid = (l + r) >> 1
            if missing(mid) >= k:
                r = mid
            else:
                l = mid + 1
        return nums[l - 1] + k - missing(l - 1)
```

```java
class Solution {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        if (k > missing(nums, n - 1)) {
            return nums[n - 1] + k - missing(nums, n - 1);
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (missing(nums, mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l - 1] + k - missing(nums, l - 1);
    }

    private int missing(int[] nums, int i) {
        return nums[i] - nums[0] - i;
    }
}
```

```cpp
class Solution {
public:
    int missingElement(vector<int>& nums, int k) {
        auto missing = [&](int i) {
            return nums[i] - nums[0] - i;
        };
        int n = nums.size();
        if (k > missing(n - 1)) {
            return nums[n - 1] + k - missing(n - 1);
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (missing(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l - 1] + k - missing(l - 1);
    }
};
```

```go
func missingElement(nums []int, k int) int {
	missing := func(i int) int {
		return nums[i] - nums[0] - i
	}
	n := len(nums)
	if k > missing(n-1) {
		return nums[n-1] + k - missing(n-1)
	}
	l, r := 0, n-1
	for l < r {
		mid := (l + r) >> 1
		if missing(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return nums[l-1] + k - missing(l-1)
}
```

<!-- tabs:end -->

<!-- end -->
