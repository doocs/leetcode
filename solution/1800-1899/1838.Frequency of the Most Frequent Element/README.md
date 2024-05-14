---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1838.Frequency%20of%20the%20Most%20Frequent%20Element/README.md
rating: 1876
tags:
    - 贪心
    - 数组
    - 二分查找
    - 前缀和
    - 排序
    - 滑动窗口
---

# [1838. 最高频元素的频数](https://leetcode.cn/problems/frequency-of-the-most-frequent-element)

[English Version](/solution/1800-1899/1838.Frequency%20of%20the%20Most%20Frequent%20Element/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>元素的 <strong>频数</strong> 是该元素在一个数组中出现的次数。</p>

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。在一步操作中，你可以选择 <code>nums</code> 的一个下标，并将该下标对应元素的值增加 <code>1</code> 。</p>

<p>执行最多 <code>k</code> 次操作后，返回数组中最高频元素的 <strong>最大可能频数</strong> <em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4], k = 5
<strong>输出：</strong>3<strong>
解释：</strong>对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
4 是数组中最高频元素，频数是 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,4,8,13], k = 5
<strong>输出：</strong>2
<strong>解释：</strong>存在多种最优解决方案：
- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,9,6], k = 2
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>5</sup></code></li>
	<li><code>1 <= k <= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：排序 + 前缀和 + 二分查找

根据题目描述，我们可以得出三个结论：

1. 经过若干次操作后，数组中最高频元素一定是原数组中的某个元素。为什么呢？我们不妨假设操作的若干个元素分别为 $a_1, a_2, \cdots, a_m$，其中最大值为 $a_m$，这几个元素都变成了同一个值 $x$，其中 $x \geq a_m$，那么也一定可以将这些元素全部变成 $a_m$，这样操作次数不会增加。
1. 操作的若干个元素一定是排序后的数组的一段连续子数组。
1. 如果一个频数 $m$ 满足条件，那么所有 $m' \lt m$ 也满足条件。这启发我们可以考虑使用二分查找，找到最大的满足条件的频数。

因此，我们可以对数组 $nums$ 进行排序，然后计算排序后的数组的前缀和数组 $s$，其中 $s[i]$ 表示前 $i$ 个元素的和。

接下来，我们定义二分查找的左边界 $l=1$，右边界 $r=n$。每一次二分查找，我们取中间值 $m=(l+r+1)/2$，然后检查是否存在一个长度为 $m$ 的连续子数组，使得这个子数组中的元素都可以变成数组中的某个元素，且操作次数不超过 $k$。如果存在这样的子数组，那么我们就可以将左边界 $l$ 更新为 $m$，否则将右边界 $r$ 更新为 $m-1$。

最后返回左边界 $l$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        def check(m: int) -> bool:
            for i in range(m, n + 1):
                if nums[i - 1] * m - (s[i] - s[i - m]) <= k:
                    return True
            return False

        n = len(nums)
        nums.sort()
        s = list(accumulate(nums, initial=0))
        l, r = 1, n
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
```

```java
class Solution {
    private int[] nums;
    private long[] s;
    private int k;

    public int maxFrequency(int[] nums, int k) {
        this.k = k;
        this.nums = nums;
        Arrays.sort(nums);
        int n = nums.length;
        s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int l = 1, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int m) {
        for (int i = m; i <= nums.length; ++i) {
            if (1L * nums[i - 1] * m - (s[i] - s[i - m]) <= k) {
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
    int maxFrequency(vector<int>& nums, int k) {
        int n = nums.size();
        sort(nums.begin(), nums.end());
        long long s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int l = 1, r = n;
        auto check = [&](int m) {
            for (int i = m; i <= n; ++i) {
                if (1LL * nums[i - 1] * m - (s[i] - s[i - m]) <= k) {
                    return true;
                }
            }
            return false;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

```go
func maxFrequency(nums []int, k int) int {
	n := len(nums)
	sort.Ints(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	check := func(m int) bool {
		for i := m; i <= n; i++ {
			if nums[i-1]*m-(s[i]-s[i-m]) <= k {
				return true
			}
		}
		return false
	}
	l, r := 1, n
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
```

```ts
function maxFrequency(nums: number[], k: number): number {
    const n = nums.length;
    nums.sort((a, b) => a - b);
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        s[i] = s[i - 1] + nums[i - 1];
    }
    let [l, r] = [1, n];
    const check = (m: number): boolean => {
        for (let i = m; i <= n; ++i) {
            if (nums[i - 1] * m - (s[i] - s[i - m]) <= k) {
                return true;
            }
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

### 方法二：排序 + 双指针

我们也可以使用双指针来维护一个滑动窗口，窗口内中的元素都可以变成窗口中的最大值，窗口内元素的操作次数为 $s$，且 $s \leq k$。

初始时，我们将左指针 $j$ 指向数组的第一个元素，右指针 $i$ 也指向数组的第一个元素。接下来，我们每一次移动右指针 $i$，将窗口中的元素都变成 $nums[i]$，此时需要增加的操作次数为 $(nums[i] - nums[i - 1]) \times (i - j)$。如果这个操作次数超过了 $k$，那么我们就需要移动左指针 $j$，直到窗口内元素的操作次数不超过 $k$。然后，我们更新答案为窗口的长度的最大值。

时间复杂度 $O(n \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxFrequency(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = 1
        s = j = 0
        for i in range(1, len(nums)):
            s += (nums[i] - nums[i - 1]) * (i - j)
            while s > k:
                s -= nums[i] - nums[j]
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        long s = 0;
        for (int i = 1, j = 0; i < nums.length; ++i) {
            s += 1L * (nums[i] - nums[i - 1]) * (i - j);
            while (s > k) {
                s -= nums[i] - nums[j++];
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = 1;
        long long s = 0;
        for (int i = 1, j = 0; i < nums.size(); ++i) {
            s += 1LL * (nums[i] - nums[i - 1]) * (i - j);
            while (s > k) {
                s -= nums[i] - nums[j++];
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func maxFrequency(nums []int, k int) int {
	sort.Ints(nums)
	ans := 1
	s := 0
	for i, j := 1, 0; i < len(nums); i++ {
		s += (nums[i] - nums[i-1]) * (i - j)
		for ; s > k; j++ {
			s -= nums[i] - nums[j]
		}
		ans = max(ans, i-j+1)
	}
	return ans
}
```

```ts
function maxFrequency(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let [s, j] = [0, 0];
    for (let i = 1; i < nums.length; ++i) {
        s += (nums[i] - nums[i - 1]) * (i - j);
        while (s > k) {
            s -= nums[i] - nums[j++];
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
