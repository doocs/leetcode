# [644. 子数组最大平均数 II 🔒](https://leetcode.cn/problems/maximum-average-subarray-ii)

[English Version](/solution/0600-0699/0644.Maximum%20Average%20Subarray%20II/README_EN.md)

<!-- tags:数组,二分查找,前缀和 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个包含 <code>n</code> 个整数的数组 <code>nums</code> ，和一个整数 <code>k</code> 。</p>

<p>请你找出<strong> 长度大于等于</strong> <code>k</code> 且含最大平均值的连续子数组。并输出这个最大平均值。任何计算误差小于 <code>10<sup>-5</sup></code> 的结果都将被视为正确答案。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,12,-5,-6,50,3], k = 4
<strong>输出：</strong>12.75000
<strong>解释：
</strong>- 当长度为 4 的时候，连续子数组平均值分别为 [0.5, 12.75, 10.5] ，其中最大平均值是 12.75 。
- 当长度为 5 的时候，连续子数组平均值分别为 [10.4, 10.8] ，其中最大平均值是 10.8 。
- 当长度为 6 的时候，连续子数组平均值分别为 [9.16667] ，其中最大平均值是 9.16667 。
当取长度为 4 的子数组（即，子数组 [12, -5, -6, 50]）的时候，可以得到最大的连续子数组平均值 12.75 ，所以返回 12.75 。
根据题目要求，无需考虑长度小于 4 的子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5], k = 1
<strong>输出：</strong>5.00000
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 <= k <= n <= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一：二分查找

我们注意到，如果一个长度大于等于 $k$ 的子数组的平均值为 $v$，那么最大平均数一定大于等于 $v$，否则最大平均数一定小于 $v$。因此，我们可以使用二分查找的方法找出最大平均数。

我们考虑二分查找的左右边界分别是什么？左边界 $l$ 一定是数组中的最小值，而右边界 $r$ 则是数组中的最大值。接下来，我们二分查找中点 $mid$，判断是否存在长度大于等于 $k$ 的子数组的平均值大于等于 $mid$。如果存在，那么我们就将左边界 $l$ 更新为 $mid$，否则我们就将右边界 $r$ 更新为 $mid$。当左边界和右边界的差小于一个极小的非负数，即 $r - l < \epsilon$ 时，我们就可以得到最大平均数，其中 $\epsilon$ 表示一个极小的正数，可以取 $10^{-5}$。

问题的关键在于如何判断一个长度大于等于 $k$ 的子数组的平均值是否大于等于 $v$。

我们假设在数组 $nums$ 中，存在一个长度为 $j$ 的子数组，元素分别为 $a_1, a_2, \cdots, a_j$，满足其平均值大于等于 $v$，即：

$$
\frac{a_1 + a_2 + \cdots + a_j}{j} \geq v
$$

那么：

$$
a_1 + a_2 + \cdots + a_j \geq v \times j
$$

即：

$$
(a_1 - v) + (a_2 - v) + \cdots + (a_j - v) \geq 0
$$

可以发现，如果我们将数组 $nums$ 中的每个元素都减去 $v$，那么原问题就转换成了一个求长度大于等于 $k$ 的子数组的元素和是否大于等于 $0$ 的问题。我们可以使用滑动窗口来解决这个问题。

我们先计算得到数组前 $k$ 个元素与 $v$ 的差值之和 $s$，如果 $s \geq 0$，那么就说明存在长度大于等于 $k$ 的子数组的元素和大于等于 $0$。

否则，我们继续往后遍历元素 $nums[j]$，假设当前前 $j$ 项元素与 $v$ 的差值之和为 $s_j$，那么我们可以维护在 $[0,..j-k]$ 范围内元素的前缀和与 $v$ 的差值之和的最小值 $mi$，如果存在 $s_j \geq mi$，那么就说明存在长度大于等于 $k$ 的子数组的元素和大于等于 $0$，返回 $true$。

否则，我们继续往后遍历元素 $nums[j]$，直到遍历完整个数组。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组 $nums$ 的长度以及数组中的最大值和最小值的差值。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        def check(v: float) -> bool:
            s = sum(nums[:k]) - k * v
            if s >= 0:
                return True
            t = mi = 0
            for i in range(k, len(nums)):
                s += nums[i] - v
                t += nums[i - k] - v
                mi = min(mi, t)
                if s >= mi:
                    return True
            return False

        eps = 1e-5
        l, r = min(nums), max(nums)
        while r - l >= eps:
            mid = (l + r) / 2
            if check(mid):
                l = mid
            else:
                r = mid
        return l
```

```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double eps = 1e-5;
        double l = 1e10, r = -1e10;
        for (int x : nums) {
            l = Math.min(l, x);
            r = Math.max(r, x);
        }
        while (r - l >= eps) {
            double mid = (l + r) / 2;
            if (check(nums, k, mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private boolean check(int[] nums, int k, double v) {
        double s = 0;
        for (int i = 0; i < k; ++i) {
            s += nums[i] - v;
        }
        if (s >= 0) {
            return true;
        }
        double t = 0;
        double mi = 0;
        for (int i = k; i < nums.length; ++i) {
            s += nums[i] - v;
            t += nums[i - k] - v;
            mi = Math.min(mi, t);
            if (s >= mi) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    double findMaxAverage(vector<int>& nums, int k) {
        double eps = 1e-5;
        double l = *min_element(nums.begin(), nums.end());
        double r = *max_element(nums.begin(), nums.end());
        auto check = [&](double v) {
            double s = 0;
            for (int i = 0; i < k; ++i) {
                s += nums[i] - v;
            }
            if (s >= 0) {
                return true;
            }
            double t = 0;
            double mi = 0;
            for (int i = k; i < nums.size(); ++i) {
                s += nums[i] - v;
                t += nums[i - k] - v;
                mi = min(mi, t);
                if (s >= mi) {
                    return true;
                }
            }
            return false;
        };
        while (r - l >= eps) {
            double mid = (l + r) / 2;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }
};
```

```go
func findMaxAverage(nums []int, k int) float64 {
	eps := 1e-5
	l := float64(slices.Min(nums))
	r := float64(slices.Max(nums))
	check := func(v float64) bool {
		s := 0.0
		for _, x := range nums[:k] {
			s += float64(x) - v
		}
		if s >= 0 {
			return true
		}
		t := 0.0
		mi := 0.0
		for i := k; i < len(nums); i++ {
			s += float64(nums[i]) - v
			t += float64(nums[i-k]) - v
			mi = math.Min(mi, t)
			if s >= mi {
				return true
			}
		}
		return false
	}
	for r-l >= eps {
		mid := (l + r) / 2
		if check(mid) {
			l = mid
		} else {
			r = mid
		}
	}
	return l
}
```

```ts
function findMaxAverage(nums: number[], k: number): number {
    const eps = 1e-5;
    let l = Math.min(...nums);
    let r = Math.max(...nums);
    const check = (v: number): boolean => {
        let s = nums.slice(0, k).reduce((a, b) => a + b) - v * k;
        if (s >= 0) {
            return true;
        }
        let t = 0;
        let mi = 0;
        for (let i = k; i < nums.length; ++i) {
            s += nums[i] - v;
            t += nums[i - k] - v;
            mi = Math.min(mi, t);
            if (s >= mi) {
                return true;
            }
        }
        return false;
    };
    while (r - l >= eps) {
        const mid = (l + r) / 2;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- end -->
