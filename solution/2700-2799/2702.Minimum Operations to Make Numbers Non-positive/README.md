---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2702.Minimum%20Operations%20to%20Make%20Numbers%20Non-positive/README.md
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [2702. 使数字变为非正数的最小操作次数 🔒](https://leetcode.cn/problems/minimum-operations-to-make-numbers-non-positive)

[English Version](/solution/2700-2799/2702.Minimum%20Operations%20to%20Make%20Numbers%20Non-positive/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 <strong>下标从0开始</strong> 的整数数组 <code>nums</code>，以及两个整数 <code>x</code> 和 <code>y</code>。在每一次操作中，你需要选择一个满足条件 <code>0 &lt;= i &lt; nums.length</code> 的下标 <code>i</code>&nbsp;，并执行以下操作：</p>

<ul>
	<li>将 <code>nums[i]</code> 减去 <code>x</code>。</li>
	<li>将除了下标为 <code>i</code> 的位置外，其他位置的值都减去 <code>y</code>。</li>
</ul>

<p>返回使得 <code>nums</code> 中的所有整数都 <strong>小于等于零&nbsp;</strong>所需的最小操作次数。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>nums = [3,4,1,7,6], x = 4, y = 2
<b>输出：</b>3
<b>解释：</b>你需要进行三次操作。其中一种最优操作序列如下：
操作 1: 选择 i = 3。 然后, nums = [1,2,-1,3,4]. 
操作 2: 选择 i = 3。 然后, nums = [-1,0,-3,-1,2].
操作 3: 选择 i = 4。 然后, nums = [-3,-2,-5,-3,-2].
现在，<code>nums</code> 中的所有数字都是非正数。因此，返回 3。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,1], x = 2, y = 1
<b>输出：</b>1
<b>解释：</b>我们可以在 <code>i = 1</code> 处执行一次操作，得到 <code>nums = [0,0,0]</code>。所有正数都被移除，因此返回 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= y &lt; x &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们注意到，如果一个操作次数 $t$ 能够使得所有的数都小于等于 $0$，那么对于任意 $t' > t$，操作次数 $t'$ 也能够使得所有的数都小于等于 $0$。因此我们可以使用二分查找的方法找到最小的操作次数。

我们定义二分查找的左边界 $l=0$，右边界 $r=\max(nums)$。每一次二分查找，我们找到中间值 $mid=\lfloor\frac{l+r}{2}\rfloor$，然后判断是否存在一种操作方法使得操作次数不超过 $mid$，使得所有的数都小于等于 $0$。如果存在，那么我们就更新右边界 $r = mid$，否则我们就更新左边界 $l = mid + 1$。最终当 $l=r$ 时，我们就找到了最小的操作次数，返回 $l$ 即可。

问题的关键在于如何判断是否存在一种操作方法使得操作次数不超过 $t$，使得所有的数都小于等于 $0$。我们可以使用贪心的方法来判断是否存在这样的操作方法。

我们遍历数组中的每一个数 $v$，如果 $v \leq t \times y$，那么我们不需要进行任何操作。否则，我们需要的操作次数为 $\lceil\frac{v - t \times y}{x - y}\rceil$。我们将所有的操作次数相加，如果小于等于 $t$，那么就说明存在一种操作方法使得操作次数不超过 $t$，使得所有的数都小于等于 $0$。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组的长度和数组中的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], x: int, y: int) -> int:
        def check(t: int) -> bool:
            cnt = 0
            for v in nums:
                if v > t * y:
                    cnt += ceil((v - t * y) / (x - y))
            return cnt <= t

        l, r = 0, max(nums)
        while l < r:
            mid = (l + r) >> 1
            if check(mid):
                r = mid
            else:
                l = mid + 1
        return l
```

#### Java

```java
class Solution {
    private int[] nums;
    private int x;
    private int y;

    public int minOperations(int[] nums, int x, int y) {
        this.nums = nums;
        this.x = x;
        this.y = y;
        int l = 0, r = 0;
        for (int v : nums) {
            r = Math.max(r, v);
        }
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int t) {
        long cnt = 0;
        for (int v : nums) {
            if (v > (long) t * y) {
                cnt += (v - (long) t * y + x - y - 1) / (x - y);
            }
        }
        return cnt <= t;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x, int y) {
        int l = 0, r = *max_element(nums.begin(), nums.end());
        auto check = [&](int t) {
            long long cnt = 0;
            for (int v : nums) {
                if (v > 1LL * t * y) {
                    cnt += (v - 1LL * t * y + x - y - 1) / (x - y);
                }
            }
            return cnt <= t;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
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
func minOperations(nums []int, x int, y int) int {
	check := func(t int) bool {
		cnt := 0
		for _, v := range nums {
			if v > t*y {
				cnt += (v - t*y + x - y - 1) / (x - y)
			}
		}
		return cnt <= t
	}

	l, r := 0, slices.Max(nums)
	for l < r {
		mid := (l + r) >> 1
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
```

#### TypeScript

```ts
function minOperations(nums: number[], x: number, y: number): number {
    let l = 0;
    let r = Math.max(...nums);
    const check = (t: number): boolean => {
        let cnt = 0;
        for (const v of nums) {
            if (v > t * y) {
                cnt += Math.ceil((v - t * y) / (x - y));
            }
        }
        return cnt <= t;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
