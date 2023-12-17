# [2968. 执行操作使频率分数最大](https://leetcode.cn/problems/apply-operations-to-maximize-frequency-score)

[English Version](/solution/2900-2999/2968.Apply%20Operations%20to%20Maximize%20Frequency%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你可以对数组执行 <strong>至多</strong>&nbsp;<code>k</code>&nbsp;次操作：</p>

<ul>
	<li>从数组中选择一个下标 <code>i</code>&nbsp;，将&nbsp;<code>nums[i]</code> <strong>增加</strong>&nbsp;或者&nbsp;<strong>减少</strong>&nbsp;<code>1</code>&nbsp;。</li>
</ul>

<p>最终数组的频率分数定义为数组中众数的 <strong>频率</strong>&nbsp;。</p>

<p>请你返回你可以得到的 <strong>最大</strong>&nbsp;频率分数。</p>

<p>众数指的是数组中出现次数最多的数。一个元素的频率指的是数组中这个元素的出现次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,6,4], k = 3
<b>输出：</b>3
<b>解释：</b>我们可以对数组执行以下操作：
- 选择 i = 0 ，将 nums[0] 增加 1 。得到数组 [2,2,6,4] 。
- 选择 i = 3 ，将 nums[3] 减少 1 ，得到数组 [2,2,6,3] 。
- 选择 i = 3 ，将 nums[3] 减少 1 ，得到数组 [2,2,6,2] 。
元素 2 是最终数组中的众数，出现了 3 次，所以频率分数为 3 。
3 是所有可行方案里的最大频率分数。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,4,2,4], k = 0
<b>输出：</b>3
<b>解释：</b>我们无法执行任何操作，所以得到的频率分数是原数组中众数的频率 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>14</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 前缀和 + 二分查找**

题目求的是在最多进行 $k$ 次操作的情况下，我们能得到的众数的最大频率。如果我们将数组 $nums$ 按照从小到大的顺序排列，那么最好就是将一段连续的数字都变成同一个数，这样可以使得操作次数较少，并且众数的频率较高。

因此，我们不妨先对数组 $nums$ 进行排序。

接下来，我们再分析，如果一个频率 $x$ 是可行的，那么对于任意 $y \le x$，频率 $y$ 也是可行的，这存在着单调性。因此，我们可以通过二分查找，找到最大的满足条件的频率。

我们二分枚举频率，定义二分查找的左边界 $l = 0$，右边界 $r = n$，其中 $n$ 是数组的长度。每次二分查找的过程中，我们取中间值 $mid = \lfloor \frac{l + r + 1}{2} \rfloor$，然后判断 $nums$ 中是否存在一个长度为 $mid$ 的连续子数组，使得这个子数组中的所有元素都变成这个子数组的中位数，且操作次数不超过 $k$。如果存在，那么我们就将左边界 $l$ 更新为 $mid$，否则我们就将右边界 $r$ 更新为 $mid - 1$。

为了判断是否存在这样的子数组，我们可以使用前缀和。我们首先定义两个指针 $i$ 和 $j$，初始时 $i = 0$, $j = i + mid$。那么 $nums[i]$ 到 $nums[j - 1]$ 这一段的元素都变成 $nums[(i + j) / 2]$，所需要的操作次数为 $left + right$，其中：

$$
\begin{aligned}
\text{left} &= \sum_{k = i}^{(i + j) / 2 - 1} (nums[(i + j) / 2] - nums[k]) \\
&= ((i + j) / 2 - i) \times nums[(i + j) / 2] - \sum_{k = i}^{(i + j) / 2 - 1} nums[k]
\end{aligned}
$$

$$
\begin{aligned}
\text{right} &= \sum_{k = (i + j) / 2 + 1}^{j} (nums[k] - nums[(i + j) / 2]) \\
&= \sum_{k = (i + j) / 2 + 1}^{j} nums[k] - (j - (i + j) / 2) \times nums[(i + j) / 2]
\end{aligned}
$$

我们可以通过前缀和数组 $s$ 来计算 $\sum_{k = i}^{j} nums[k]$，从而在 $O(1)$ 的时间内计算出 $left$ 和 $right$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxFrequencyScore(self, nums: List[int], k: int) -> int:
        nums.sort()
        s = list(accumulate(nums, initial=0))
        n = len(nums)
        l, r = 0, n
        while l < r:
            mid = (l + r + 1) >> 1
            ok = False
            for i in range(n - mid + 1):
                j = i + mid
                x = nums[(i + j) // 2]
                left = ((i + j) // 2 - i) * x - (s[(i + j) // 2] - s[i])
                right = (s[j] - s[(i + j) // 2]) - ((j - (i + j) // 2) * x)
                if left + right <= k:
                    ok = True
                    break
            if ok:
                l = mid
            else:
                r = mid - 1
        return l
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            boolean ok = false;

            for (int i = 0; i <= n - mid; i++) {
                int j = i + mid;
                int x = nums[(i + j) / 2];
                long left = ((i + j) / 2 - i) * (long) x - (s[(i + j) / 2] - s[i]);
                long right = (s[j] - s[(i + j) / 2]) - ((j - (i + j) / 2) * (long) x);
                if (left + right <= k) {
                    ok = true;
                    break;
                }
            }

            if (ok) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxFrequencyScore(vector<int>& nums, long long k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<long long> s(n + 1, 0);
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }

        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            bool ok = false;

            for (int i = 0; i <= n - mid; i++) {
                int j = i + mid;
                int x = nums[(i + j) / 2];
                long long left = ((i + j) / 2 - i) * (long long) x - (s[(i + j) / 2] - s[i]);
                long long right = (s[j] - s[(i + j) / 2]) - ((j - (i + j) / 2) * (long long) x);

                if (left + right <= k) {
                    ok = true;
                    break;
                }
            }

            if (ok) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
};
```

### **Go**

```go
func maxFrequencyScore(nums []int, k int64) int {
	sort.Ints(nums)
	n := len(nums)
	s := make([]int64, n+1)
	for i := 1; i <= n; i++ {
		s[i] = s[i-1] + int64(nums[i-1])
	}

	l, r := 0, n
	for l < r {
		mid := (l + r + 1) >> 1
		ok := false

		for i := 0; i <= n-mid; i++ {
			j := i + mid
			x := int64(nums[(i+j)/2])
			left := (int64((i+j)/2-i) * x) - (s[(i+j)/2] - s[i])
			right := (s[j] - s[(i+j)/2]) - (int64(j-(i+j)/2) * x)

			if left+right <= k {
				ok = true
				break
			}
		}

		if ok {
			l = mid
		} else {
			r = mid - 1
		}
	}

	return l
}
```

### **TypeScript**

```ts
function maxFrequencyScore(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + nums[i - 1];
    }

    let l: number = 0;
    let r: number = n;
    while (l < r) {
        const mid: number = (l + r + 1) >> 1;
        let ok: boolean = false;
        for (let i = 0; i <= n - mid; i++) {
            const j = i + mid;
            const x = nums[Math.floor((i + j) / 2)];
            const left = (Math.floor((i + j) / 2) - i) * x - (s[Math.floor((i + j) / 2)] - s[i]);
            const right = s[j] - s[Math.floor((i + j) / 2)] - (j - Math.floor((i + j) / 2)) * x;
            if (left + right <= k) {
                ok = true;
                break;
            }
        }
        if (ok) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    return l;
}
```

### **...**

```

```

<!-- tabs:end -->
