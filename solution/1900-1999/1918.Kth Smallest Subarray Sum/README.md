---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1918.Kth%20Smallest%20Subarray%20Sum/README.md
tags:
    - 数组
    - 二分查找
    - 滑动窗口
---

<!-- problem:start -->

# [1918. 第 K 小的子数组和 🔒](https://leetcode.cn/problems/kth-smallest-subarray-sum)

[English Version](/solution/1900-1999/1918.Kth%20Smallest%20Subarray%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 长度为&nbsp;<code>n</code>&nbsp;的整型数组&nbsp;<code>nums</code>&nbsp;和一个数值&nbsp;<code>k</code>&nbsp;，返回<strong> 第<em>&nbsp;</em><code>k</code>&nbsp;小的子数组和<i>。</i></strong></p>

<p><b>子数组</b> 是指数组中一个 <b>非空</b>&nbsp;且不间断的子序列。&nbsp; <b>子数组和</b> 则指子数组中所有元素的和。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,1,3], k = 4
<strong>输出:</strong> 3
<strong>解释: </strong>[2,1,3] 的子数组为：
- [2] 和为 2
- [1] 和为 1
- [3] 和为 3
- [2,1] 和为 3
- [1,3] 和为 4
- [2,1,3] 和为 6 
最小子数组和的升序排序为 1, 2, 3, <strong><em>3</em></strong>, 4, 6。 第 4 小的子数组和为 3 。
</pre>

<strong>示例 2：</strong>

<pre>
<strong>输入：</strong>nums = [3,3,5,5], k = 7
<strong>输出：</strong>10
<strong>解释：</strong>[3,3,5,5] 的子数组为：
- [3] 和为 3
- [3] 和为 3
- [5] 和为 5
- [5] 和为 5
- [3,3] 和为 6
- [3,5] 和为 8
- [5,5] 和为 10
- [3,3,5], 和为 11
- [3,5,5] 和为 13
- [3,3,5,5] 和为 16
最小子数组和的升序排序为 3, 3, 5, 5, 6, 8, <strong><em>10</em></strong>, 11, 13, 16。第 7 小的子数组和为 10 。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= n * (n + 1) / 2</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找 + 双指针

我们注意到，题目中数组元素均为正整数，子数组的和 $s$ 越大，那么数组中子数组和小于等于 $s$ 的个数就越多。这存在一个单调性，因此我们可以考虑使用使用二分查找的方法来求解。

我们二分枚举子数组的和，初始化左右边界分别为数组 $nums$ 中的最小值以及所有元素之和。每次我们计算数组中子数组和小于等于当前枚举值的个数，如果个数大于等于 $k$，则说明当前枚举值 $s$ 可能是第 $k$ 小的子数组和，我们缩小右边界，否则我们增大左边界。枚举结束后，左边界即为第 $k$ 小的子数组和。

问题转换为计算一个数组中，有多少个子数组的和小于等于 $s$，我们可以通过函数 $f(s)$ 来计算。

函数 $f(s)$ 的计算方法如下：

-   初始化双指针 $j$ 和 $i$，分别指向当前窗口的左右边界，初始时 $j = i = 0$。初始化窗口内元素的和 $t = 0$。
-   用变量 $cnt$ 记录子数组和小于等于 $s$ 的个数，初始时 $cnt = 0$。
-   遍历数组 $nums$，每次遍历到一个元素 $nums[i]$，我们将其加入窗口，即 $t = t + nums[i]$。如果此时 $t \gt s$，我们需要不断地将窗口的左边界右移，直到 $t \le s$ 为止，即不断地执行 $t -= nums[j]$，并且 $j = j + 1$。接下来我们更新 $cnt$，即 $cnt = cnt + i - j + 1$。继续遍历下一个元素，直到遍历完整个数组。

最后将 $cnt$ 作为函数 $f(s)$ 的返回值。

时间复杂度 $O(n \times \log S)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度，而 $S$ 为数组 $nums$ 中所有元素之和。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthSmallestSubarraySum(self, nums: List[int], k: int) -> int:
        def f(s):
            t = j = 0
            cnt = 0
            for i, x in enumerate(nums):
                t += x
                while t > s:
                    t -= nums[j]
                    j += 1
                cnt += i - j + 1
            return cnt >= k

        l, r = min(nums), sum(nums)
        return l + bisect_left(range(l, r + 1), True, key=f)
```

#### Java

```java
class Solution {
    public int kthSmallestSubarraySum(int[] nums, int k) {
        int l = 1 << 30, r = 0;
        for (int x : nums) {
            l = Math.min(l, x);
            r += x;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (f(nums, mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int f(int[] nums, int s) {
        int t = 0, j = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            t += nums[i];
            while (t > s) {
                t -= nums[j++];
            }
            cnt += i - j + 1;
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kthSmallestSubarraySum(vector<int>& nums, int k) {
        int l = 1 << 30, r = 0;
        for (int& x : nums) {
            l = min(l, x);
            r += x;
        }
        auto f = [&](int s) {
            int cnt = 0, t = 0;
            for (int i = 0, j = 0; i < nums.size(); ++i) {
                t += nums[i];
                while (t > s) {
                    t -= nums[j++];
                }
                cnt += i - j + 1;
            }
            return cnt;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (f(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func kthSmallestSubarraySum(nums []int, k int) int {
	l, r := 1<<30, 0
	for _, x := range nums {
		l = min(l, x)
		r += x
	}
	f := func(s int) (cnt int) {
		t := 0
		for i, j := 0, 0; i < len(nums); i++ {
			t += nums[i]
			for t > s {
				t -= nums[j]
				j++
			}
			cnt += i - j + 1
		}
		return
	}
	for l < r {
		mid := (l + r) >> 1
		if f(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
