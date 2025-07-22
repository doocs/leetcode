---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3555.Smallest%20Subarray%20to%20Sort%20in%20Every%20Sliding%20Window/README.md
tags:
    - 栈
    - 贪心
    - 数组
    - 双指针
    - 排序
    - 单调栈
---

<!-- problem:start -->

# [3555. 排序每个滑动窗口中最小的子数组 🔒](https://leetcode.cn/problems/smallest-subarray-to-sort-in-every-sliding-window)

[English Version](/solution/3500-3599/3555.Smallest%20Subarray%20to%20Sort%20in%20Every%20Sliding%20Window/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p>对于每个长度为 <code>k</code>&nbsp;的连续 <span data-keyword="subarray">子数组</span>，确定必须排序的连续段的最小长度，以便整个窗口成为 <strong>非递减</strong> 的；如果窗口已经排序，则其所需长度为零。</p>

<p>返回一个长度为 <code>n − k + 1</code>&nbsp;的数组，其中每个元素对应其窗口的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,3,2,4,5], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>[2,2,0]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>nums[0...2] = [1, 3, 2]</code>。排序&nbsp;<code>[3, 2]</code> 得到&nbsp;<code>[1, 2, 3]</code>，答案是 2。</li>
	<li><code>nums[1...3] = [3, 2, 4]</code>。排序&nbsp;<code>[3, 2]</code> 得到&nbsp;<code>[2, 3, 4]</code>，答案是 2。</li>
	<li><code>nums[2...4] = [2, 4, 5]</code>&nbsp;已经有序，所以答案是 0。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [5,4,3,2,1], k = 4</span></p>

<p><span class="example-io"><b>输出：</b>[4,4]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>nums[0...3] = [5, 4, 3, 2]</code>。整个子数组必须有序，所以答案是4。</li>
	<li><code>nums[1...4] = [4, 3, 2, 1]</code>。整个子数组必须有序，所以答案是4。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 维护左侧最大值和右侧最小值

我们可以枚举每个长度为 $k$ 的子数组，对于每个子数组 $nums[i...i + k - 1]$，我们需要找到最小的连续段，使得排序后整个子数组都是非递减的。

对于子数组 $nums[i...i + k - 1]$，我们可以从左到右遍历数组，维护一个最大值 $mx$，如果当前值小于 $mx$，说明当前值不在正确的位置上，我们更新右边界 $r$ 为当前位置。同理，我们可以从右到左遍历数组，维护一个最小值 $mi$，如果当前值大于 $mi$，说明当前值不在正确的位置上，我们更新左边界 $l$ 为当前位置。在初始化时，我们将 $l$ 和 $r$ 都初始化为 $-1$，如果 $l$ 和 $r$ 都没有被更新，说明数组已经有序，返回 $0$，否则返回 $r - l + 1$。

时间复杂度 $O(n \times k)$，其中 $n$ 是数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSubarraySort(self, nums: List[int], k: int) -> List[int]:
        def f(i: int, j: int) -> int:
            mi, mx = inf, -inf
            l = r = -1
            for k in range(i, j + 1):
                if mx > nums[k]:
                    r = k
                else:
                    mx = nums[k]
                p = j - k + i
                if mi < nums[p]:
                    l = p
                else:
                    mi = nums[p]
            return 0 if r == -1 else r - l + 1

        n = len(nums)
        return [f(i, i + k - 1) for i in range(n - k + 1)]
```

#### Java

```java
class Solution {
    private int[] nums;
    private final int inf = 1 << 30;

    public int[] minSubarraySort(int[] nums, int k) {
        this.nums = nums;
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; ++i) {
            ans[i] = f(i, i + k - 1);
        }
        return ans;
    }

    private int f(int i, int j) {
        int mi = inf, mx = -inf;
        int l = -1, r = -1;
        for (int k = i; k <= j; ++k) {
            if (nums[k] < mx) {
                r = k;
            } else {
                mx = nums[k];
            }
            int p = j - k + i;
            if (nums[p] > mi) {
                l = p;
            } else {
                mi = nums[p];
            }
        }
        return r == -1 ? 0 : r - l + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minSubarraySort(vector<int>& nums, int k) {
        const int inf = 1 << 30;
        int n = nums.size();
        auto f = [&](int i, int j) -> int {
            int mi = inf, mx = -inf;
            int l = -1, r = -1;
            for (int k = i; k <= j; ++k) {
                if (nums[k] < mx) {
                    r = k;
                } else {
                    mx = nums[k];
                }
                int p = j - k + i;
                if (nums[p] > mi) {
                    l = p;
                } else {
                    mi = nums[p];
                }
            }
            return r == -1 ? 0 : r - l + 1;
        };
        vector<int> ans;
        for (int i = 0; i < n - k + 1; ++i) {
            ans.push_back(f(i, i + k - 1));
        }
        return ans;
    }
};
```

#### Go

```go
func minSubarraySort(nums []int, k int) []int {
	const inf = 1 << 30
	n := len(nums)
	f := func(i, j int) int {
		mi := inf
		mx := -inf
		l, r := -1, -1
		for p := i; p <= j; p++ {
			if nums[p] < mx {
				r = p
			} else {
				mx = nums[p]
			}
			q := j - p + i
			if nums[q] > mi {
				l = q
			} else {
				mi = nums[q]
			}
		}
		if r == -1 {
			return 0
		}
		return r - l + 1
	}

	ans := make([]int, 0, n-k+1)
	for i := 0; i <= n-k; i++ {
		ans = append(ans, f(i, i+k-1))
	}
	return ans
}
```

#### TypeScript

```ts
function minSubarraySort(nums: number[], k: number): number[] {
    const inf = Infinity;
    const n = nums.length;
    const f = (i: number, j: number): number => {
        let mi = inf;
        let mx = -inf;
        let l = -1,
            r = -1;
        for (let p = i; p <= j; ++p) {
            if (nums[p] < mx) {
                r = p;
            } else {
                mx = nums[p];
            }
            const q = j - p + i;
            if (nums[q] > mi) {
                l = q;
            } else {
                mi = nums[q];
            }
        }
        return r === -1 ? 0 : r - l + 1;
    };

    const ans: number[] = [];
    for (let i = 0; i <= n - k; ++i) {
        ans.push(f(i, i + k - 1));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
