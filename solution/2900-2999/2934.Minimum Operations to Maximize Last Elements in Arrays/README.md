---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2934.Minimum%20Operations%20to%20Maximize%20Last%20Elements%20in%20Arrays/README.md
rating: 1802
source: 第 371 场周赛 Q3
tags:
    - 数组
    - 枚举
---

<!-- problem:start -->

# [2934. 最大化数组末位元素的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-maximize-last-elements-in-arrays)

[English Version](/solution/2900-2999/2934.Minimum%20Operations%20to%20Maximize%20Last%20Elements%20in%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong> 开始的整数数组 <code>nums1</code> 和 <code>nums2</code> ，这两个数组的长度都是 <code>n</code> 。</p>

<p>你可以执行一系列<strong> 操作（可能不执行）</strong>。</p>

<p>在每次操作中，你可以选择一个在范围 <code>[0, n - 1]</code> 内的下标 <code>i</code> ，并交换 <code>nums1[i]</code> 和 <code>nums2[i]</code> 的值。</p>

<p>你的任务是找到满足以下条件所需的 <strong>最小</strong> 操作次数：</p>

<ul>
	<li><code>nums1[n - 1]</code> 等于 <code>nums1</code> 中所有元素的 <strong>最大值</strong> ，即 <code>nums1[n - 1] = max(nums1[0], nums1[1], ..., nums1[n - 1])</code> 。</li>
	<li><code>nums2[n - 1]</code> 等于 <code>nums2</code> 中所有元素的 <strong>最大值</strong> ，即 <code>nums2[n - 1] = max(nums2[0], nums2[1], ..., nums2[n - 1])</code> 。</li>
</ul>

<p>以整数形式，表示并返回满足上述 <strong>全部</strong> 条件所需的 <strong>最小</strong> 操作次数，如果无法同时满足两个条件，则返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,7]，nums2 = [4,5,3]
<strong>输出：</strong>1
<strong>解释：</strong>在这个示例中，可以选择下标 i = 2 执行一次操作。
交换 nums1[2] 和 nums2[2] 的值，nums1 变为 [1,2,3] ，nums2 变为 [4,5,7] 。
同时满足两个条件。
可以证明，需要执行的最小操作次数为 1 。
因此，答案是 1 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,3,4,5,9]，nums2 = [8,8,4,4,4]
<strong>输出：</strong>2
<strong>解释：</strong>在这个示例中，可以执行以下操作：
首先，选择下标 i = 4 执行操作。
交换 nums1[4] 和 nums2[4] 的值，nums1 变为 [2,3,4,5,4] ，nums2 变为 [8,8,4,4,9] 。
然后，选择下标 i = 3 执行操作。
交换 nums1[3] 和 nums2[3] 的值，nums1 变为 [2,3,4,4,4] ，nums2 变为 [8,8,4,5,9] 。
同时满足两个条件。 
可以证明，需要执行的最小操作次数为 2 。 
因此，答案是 2 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,5,4]，nums2 = [2,5,3]
<strong>输出：</strong>-1
<strong>解释：</strong>在这个示例中，无法同时满足两个条件。
因此，答案是 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length == nums2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums1[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分情况讨论 + 贪心

我们可以分成两种情况讨论：

1. 不交换 $nums1[n - 1]$ 和 $nums2[n - 1]$ 的值
1. 交换 $nums1[n - 1]$ 和 $nums2[n - 1]$ 的值

对于每一种情况，我们记数组 $nums1$ 和 $nums2$ 的最后一个值分别为 $x$ 和 $y$。然后遍历数组 $nums1$ 和 $nums2$ 的前 $n - 1$ 个值，用一个变量 $cnt$ 记录交换次数。如果 $nums1[i] \leq x$ 且 $nums2[i] \leq y$，则不需要交换，否则如果 $nums1[i] \leq y$ 且 $nums2[i] \leq x$，则需要交换，否则无法同时满足两个条件，返回 $-1$。最后返回 $cnt$ 即可。

我们记两种情况的交换次数分别为 $a$ 和 $b$，如果 $a + b = -2$，则无法同时满足两个条件，返回 $-1$，否则返回 $\min(a, b + 1)$。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        def f(x: int, y: int) -> int:
            cnt = 0
            for a, b in zip(nums1[:-1], nums2[:-1]):
                if a <= x and b <= y:
                    continue
                if not (a <= y and b <= x):
                    return -1
                cnt += 1
            return cnt

        a, b = f(nums1[-1], nums2[-1]), f(nums2[-1], nums1[-1])
        return -1 if a + b == -2 else min(a, b + 1)
```

#### Java

```java
class Solution {
    private int n;

    public int minOperations(int[] nums1, int[] nums2) {
        n = nums1.length;
        int a = f(nums1, nums2, nums1[n - 1], nums2[n - 1]);
        int b = f(nums1, nums2, nums2[n - 1], nums1[n - 1]);
        return a + b == -2 ? -1 : Math.min(a, b + 1);
    }

    private int f(int[] nums1, int[] nums2, int x, int y) {
        int cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (nums1[i] <= x && nums2[i] <= y) {
                continue;
            }
            if (!(nums1[i] <= y && nums2[i] <= x)) {
                return -1;
            }
            ++cnt;
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        auto f = [&](int x, int y) {
            int cnt = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (nums1[i] <= x && nums2[i] <= y) {
                    continue;
                }
                if (!(nums1[i] <= y && nums2[i] <= x)) {
                    return -1;
                }
                ++cnt;
            }
            return cnt;
        };
        int a = f(nums1.back(), nums2.back());
        int b = f(nums2.back(), nums1.back());
        return a + b == -2 ? -1 : min(a, b + 1);
    }
};
```

#### Go

```go
func minOperations(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f := func(x, y int) (cnt int) {
		for i, a := range nums1[:n-1] {
			b := nums2[i]
			if a <= x && b <= y {
				continue
			}
			if !(a <= y && b <= x) {
				return -1
			}
			cnt++
		}
		return
	}
	a, b := f(nums1[n-1], nums2[n-1]), f(nums2[n-1], nums1[n-1])
	if a+b == -2 {
		return -1
	}
	return min(a, b+1)
}
```

#### TypeScript

```ts
function minOperations(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const f = (x: number, y: number): number => {
        let cnt = 0;
        for (let i = 0; i < n - 1; ++i) {
            if (nums1[i] <= x && nums2[i] <= y) {
                continue;
            }
            if (!(nums1[i] <= y && nums2[i] <= x)) {
                return -1;
            }
            ++cnt;
        }
        return cnt;
    };
    const a = f(nums1.at(-1), nums2.at(-1));
    const b = f(nums2.at(-1), nums1.at(-1));
    return a + b === -2 ? -1 : Math.min(a, b + 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
