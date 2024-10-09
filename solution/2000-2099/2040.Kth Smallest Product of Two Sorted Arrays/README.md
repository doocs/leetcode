---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2040.Kth%20Smallest%20Product%20of%20Two%20Sorted%20Arrays/README.md
rating: 2517
source: 第 63 场双周赛 Q4
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [2040. 两个有序数组的第 K 小乘积](https://leetcode.cn/problems/kth-smallest-product-of-two-sorted-arrays)

[English Version](/solution/2000-2099/2040.Kth%20Smallest%20Product%20of%20Two%20Sorted%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

给你两个 <strong>从小到大排好序</strong>&nbsp;且下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;以及一个整数&nbsp;<code>k</code>&nbsp;，请你返回第<em>&nbsp;</em><code>k</code>&nbsp;（从 <strong>1</strong>&nbsp;开始编号）小的&nbsp;<code>nums1[i] \* nums2[j]</code><em>&nbsp;</em>的乘积，其中<em>&nbsp;</em><code>0 &lt;= i &lt; nums1.length</code><em> </em>且<em> </em><code>0 &lt;= j &lt; nums2.length</code>&nbsp;。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [2,5], nums2 = [3,4], k = 2
<b>输出：</b>8
<b>解释：</b>第 2 小的乘积计算如下：
- nums1[0] * nums2[0] = 2 * 3 = 6
- nums1[0] * nums2[1] = 2 * 4 = 8
第 2 小的乘积为 8 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
<b>输出：</b>0
<strong>解释：</strong>第 6 小的乘积计算如下：
- nums1[0] * nums2[1] = (-4) * 4 = -16
- nums1[0] * nums2[0] = (-4) * 2 = -8
- nums1[1] * nums2[1] = (-2) * 4 = -8
- nums1[1] * nums2[0] = (-2) * 2 = -4
- nums1[2] * nums2[0] = 0 * 2 = 0
- nums1[2] * nums2[1] = 0 * 4 = 0
第 6 小的乘积为 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
<b>输出：</b>-6
<b>解释：</b>第 3 小的乘积计算如下：
- nums1[0] * nums2[4] = (-2) * 5 = -10
- nums1[0] * nums2[3] = (-2) * 4 = -8
- nums1[4] * nums2[0] = 2 * (-3) = -6
第 3 小的乘积为 -6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums1[i], nums2[j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums1.length * nums2.length</code></li>
	<li><code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;都是从小到大排好序的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们可以二分枚举乘积的值 $p$，定义二分的区间为 $[l, r]$，其中 $l = -\textit{max}(|\textit{nums1}[0]|, |\textit{nums1}[n - 1]|) \times \textit{max}(|\textit{nums2}[0]|, |\textit{nums2}[n - 1]|)$, $r = -l$。

对于每个 $p$，我们计算出乘积小于等于 $p$ 的乘积的个数，如果这个个数大于等于 $k$，那么说明第 $k$ 小的乘积一定小于等于 $p$，我们就可以将区间右端点缩小到 $p$，否则我们将区间左端点增大到 $p + 1$。

那么问题的关键就是如何计算乘积小于等于 $p$ 的乘积的个数。我们可以枚举 $\textit{nums1}$ 中的每个数 $x$，分类讨论：

-   如果 $x > 0$，那么 $x \times \textit{nums2}[i]$ 随着 $i$ 的增大是单调递增的，我们可以使用二分查找找到最小的 $i$，使得 $x \times \textit{nums2}[i] > p$，那么 $i$ 就是小于等于 $p$ 的乘积的个数，累加到个数 $\textit{cnt}$ 中；
-   如果 $x < 0$，那么 $x \times \textit{nums2}[i]$ 随着 $i$ 的增大是单调递减的，我们可以使用二分查找找到最小的 $i$，使得 $x \times \textit{nums2}[i] \leq p$，那么 $n - i$ 就是小于等于 $p$ 的乘积的个数，累加到个数 $\textit{cnt}$ 中；
-   如果 $x = 0$，那么 $x \times \textit{nums2}[i] = 0$，如果 $p \geq 0$，那么 $n$ 就是小于等于 $p$ 的乘积的个数，累加到个数 $\textit{cnt}$ 中。

这样我们就可以通过二分查找找到第 $k$ 小的乘积。

时间复杂度 $O(m \times \log n \times \log M)$，其中 $m$ 和 $n$ 分别为 $\textit{nums1}$ 和 $\textit{nums2}$ 的长度，而 $M$ 为 $\textit{nums1}$ 和 $\textit{nums2}$ 中的最大值的绝对值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthSmallestProduct(self, nums1: List[int], nums2: List[int], k: int) -> int:
        def count(p: int) -> int:
            cnt = 0
            n = len(nums2)
            for x in nums1:
                if x > 0:
                    cnt += bisect_right(nums2, p / x)
                elif x < 0:
                    cnt += n - bisect_left(nums2, p / x)
                else:
                    cnt += n * int(p >= 0)
            return cnt

        mx = max(abs(nums1[0]), abs(nums1[-1])) * max(abs(nums2[0]), abs(nums2[-1]))
        return bisect_left(range(-mx, mx + 1), k, key=count) - mx
```

#### Java

```java
class Solution {
    private int[] nums1;
    private int[] nums2;

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        int m = nums1.length;
        int n = nums2.length;
        int a = Math.max(Math.abs(nums1[0]), Math.abs(nums1[m - 1]));
        int b = Math.max(Math.abs(nums2[0]), Math.abs(nums2[n - 1]));
        long r = (long) a * b;
        long l = (long) -a * b;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (count(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private long count(long p) {
        long cnt = 0;
        int n = nums2.length;
        for (int x : nums1) {
            if (x > 0) {
                int l = 0, r = n;
                while (l < r) {
                    int mid = (l + r) >> 1;
                    if ((long) x * nums2[mid] > p) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                cnt += l;
            } else if (x < 0) {
                int l = 0, r = n;
                while (l < r) {
                    int mid = (l + r) >> 1;
                    if ((long) x * nums2[mid] <= p) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                cnt += n - l;
            } else if (p >= 0) {
                cnt += n;
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long kthSmallestProduct(vector<int>& nums1, vector<int>& nums2, long long k) {
        int m = nums1.size(), n = nums2.size();
        int a = max(abs(nums1[0]), abs(nums1[m - 1]));
        int b = max(abs(nums2[0]), abs(nums2[n - 1]));
        long long r = 1LL * a * b;
        long long l = -r;
        auto count = [&](long long p) {
            long long cnt = 0;
            for (int x : nums1) {
                if (x > 0) {
                    int l = 0, r = n;
                    while (l < r) {
                        int mid = (l + r) >> 1;
                        if (1LL * x * nums2[mid] > p) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    cnt += l;
                } else if (x < 0) {
                    int l = 0, r = n;
                    while (l < r) {
                        int mid = (l + r) >> 1;
                        if (1LL * x * nums2[mid] <= p) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    cnt += n - l;
                } else if (p >= 0) {
                    cnt += n;
                }
            }
            return cnt;
        };
        while (l < r) {
            long long mid = (l + r) >> 1;
            if (count(mid) >= k) {
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
func kthSmallestProduct(nums1 []int, nums2 []int, k int64) int64 {
	m := len(nums1)
	n := len(nums2)
	a := max(abs(nums1[0]), abs(nums1[m-1]))
	b := max(abs(nums2[0]), abs(nums2[n-1]))
	r := int64(a) * int64(b)
	l := -r

	count := func(p int64) int64 {
		var cnt int64
		for _, x := range nums1 {
			if x > 0 {
				l, r := 0, n
				for l < r {
					mid := (l + r) >> 1
					if int64(x)*int64(nums2[mid]) > p {
						r = mid
					} else {
						l = mid + 1
					}
				}
				cnt += int64(l)
			} else if x < 0 {
				l, r := 0, n
				for l < r {
					mid := (l + r) >> 1
					if int64(x)*int64(nums2[mid]) <= p {
						r = mid
					} else {
						l = mid + 1
					}
				}
				cnt += int64(n - l)
			} else if p >= 0 {
				cnt += int64(n)
			}
		}
		return cnt
	}

	for l < r {
		mid := (l + r) >> 1
		if count(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
