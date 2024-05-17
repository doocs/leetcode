---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2918.Minimum%20Equal%20Sum%20of%20Two%20Arrays%20After%20Replacing%20Zeros/README.md
rating: 1526
source: 第 369 场周赛 Q2
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [2918. 数组的最小相等和](https://leetcode.cn/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros)

[English Version](/solution/2900-2999/2918.Minimum%20Equal%20Sum%20of%20Two%20Arrays%20After%20Replacing%20Zeros/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个由正整数和 <code>0</code> 组成的数组 <code>nums1</code> 和 <code>nums2</code> 。</p>

<p>你必须将两个数组中的<strong> 所有</strong> <code>0</code> 替换为 <strong>严格</strong> 正整数，并且满足两个数组中所有元素的和 <strong>相等</strong> 。</p>

<p>返回 <strong>最小</strong> 相等和 ，如果无法使两数组相等，则返回 <code>-1</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [3,2,0,1,0], nums2 = [6,5,0]
<strong>输出：</strong>12
<strong>解释：</strong>可以按下述方式替换数组中的 0 ：
- 用 2 和 4 替换 nums1 中的两个 0 。得到 nums1 = [3,2,2,1,4] 。
- 用 1 替换 nums2 中的一个 0 。得到 nums2 = [6,5,1] 。
两个数组的元素和相等，都等于 12 。可以证明这是可以获得的最小相等和。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,0,2,0], nums2 = [1,4]
<strong>输出：</strong>-1
<strong>解释：</strong>无法使两个数组的和相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分情况讨论

我们记把数组中的 $0$ 视为 $1$，统计两个数组的和，分别记为 $s_1$ 和 $s_2$。不妨设 $s_1 \le s_2$。

-   如果 $s_1 = s_2$，那么答案就是 $s_1$。
-   如果 $s_1 \lt s_2$，那么 $nums1$ 中必须存在 $0$，才能使得两个数组的和相等，此时的答案就是 $s_2$。否则，说明无法使两个数组的和相等，返回 $-1$。

时间复杂度 $O(n + m)$，其中 $n$ 和 $m$ 分别是数组 $nums1$ 和 $nums2$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minSum(self, nums1: List[int], nums2: List[int]) -> int:
        s1 = sum(nums1) + nums1.count(0)
        s2 = sum(nums2) + nums2.count(0)
        if s1 > s2:
            return self.minSum(nums2, nums1)
        if s1 == s2:
            return s1
        return -1 if nums1.count(0) == 0 else s2
```

```java
class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long s1 = 0, s2 = 0;
        boolean hasZero = false;
        for (int x : nums1) {
            hasZero |= x == 0;
            s1 += Math.max(x, 1);
        }
        for (int x : nums2) {
            s2 += Math.max(x, 1);
        }
        if (s1 > s2) {
            return minSum(nums2, nums1);
        }
        if (s1 == s2) {
            return s1;
        }
        return hasZero ? s2 : -1;
    }
}
```

```cpp
class Solution {
public:
    long long minSum(vector<int>& nums1, vector<int>& nums2) {
        long long s1 = 0, s2 = 0;
        bool hasZero = false;
        for (int x : nums1) {
            hasZero |= x == 0;
            s1 += max(x, 1);
        }
        for (int x : nums2) {
            s2 += max(x, 1);
        }
        if (s1 > s2) {
            return minSum(nums2, nums1);
        }
        if (s1 == s2) {
            return s1;
        }
        return hasZero ? s2 : -1;
    }
};
```

```go
func minSum(nums1 []int, nums2 []int) int64 {
	s1, s2 := 0, 0
	hasZero := false
	for _, x := range nums1 {
		if x == 0 {
			hasZero = true
		}
		s1 += max(x, 1)
	}
	for _, x := range nums2 {
		s2 += max(x, 1)
	}
	if s1 > s2 {
		return minSum(nums2, nums1)
	}
	if s1 == s2 {
		return int64(s1)
	}
	if hasZero {
		return int64(s2)
	}
	return -1
}
```

```ts
function minSum(nums1: number[], nums2: number[]): number {
    let [s1, s2] = [0, 0];
    let hasZero = false;
    for (const x of nums1) {
        if (x === 0) {
            hasZero = true;
        }
        s1 += Math.max(x, 1);
    }
    for (const x of nums2) {
        s2 += Math.max(x, 1);
    }
    if (s1 > s2) {
        return minSum(nums2, nums1);
    }
    if (s1 === s2) {
        return s1;
    }
    return hasZero ? s2 : -1;
}
```

```cs
public class Solution {
    public long MinSum(int[] nums1, int[] nums2) {
        long s1 = 0, s2 = 0;
        bool hasZero = false;
        foreach (int x in nums1) {
            hasZero |= x == 0;
            s1 += Math.Max(x, 1);
        }
        foreach (int x in nums2) {
            s2 += Math.Max(x, 1);
        }
        if (s1 > s2) {
            return MinSum(nums2, nums1);
        }
        if (s1 == s2) {
            return s1;
        }
        return hasZero ? s2 : -1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
