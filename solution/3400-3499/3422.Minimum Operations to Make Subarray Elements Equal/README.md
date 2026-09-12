---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3422.Minimum%20Operations%20to%20Make%20Subarray%20Elements%20Equal/README.md
tags:
    - 数组
    - 哈希表
    - 数学
    - 滑动窗口
    - 堆（优先队列）
---

<!-- problem:start -->

# [3422. 将子数组元素变为相等所需的最小操作数 🔒](https://leetcode.cn/problems/minimum-operations-to-make-subarray-elements-equal)

[English Version](/solution/3400-3499/3422.Minimum%20Operations%20to%20Make%20Subarray%20Elements%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>。你可以进行任意次以下操作：</p>

<ul>
	<li>给&nbsp;<code>nums</code>&nbsp;的任何元素增加或减少 1。</li>
</ul>

<p>返回确保&nbsp;<strong>至少</strong> 有一个大小为 <code>k</code> 的&nbsp;<code>nums</code>&nbsp;中的 <span data-keyword="subarray">子数组</span> 的所有元素都相等的所需的 <strong>最小</strong> 操作数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,-3,2,1,-4,6], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>使用 4 次操作来给&nbsp;<code>nums[1]</code>&nbsp;增加 4。结果数组为&nbsp;<span class="example-io"><code>[4, 1, 2, 1, -4, 6]</code>。</span></li>
	<li><span class="example-io">使用 1 次操作来给&nbsp;<code>nums[2]</code>&nbsp;减少 1。结果数组为&nbsp;<code>[4, 1, 1, 1, -4, 6]</code>。</span></li>
	<li><span class="example-io">现在数组包含一个大小为&nbsp;<code>k = 3</code>&nbsp;的子数组&nbsp;<code>[1, 1, 1]</code>，所有元素都想等。因此，答案为 5。</span></li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [-2,-2,3,1,4], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>
	<p>大小为&nbsp;<code>k = 2</code>&nbsp;的子数组&nbsp;<code>[-2, -2]</code> 已经包含了所有相等的元素，所以不需要操作。因此答案为 0。</p>
	</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>2 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：有序集合

<!-- thinking:start -->

> **思考**
>
> 把一段数都变成同一个目标，最优目标是中位数，代价为各元素到中位数的距离和。$n\le 10^5$，窗口长 $k$，需要对每个长度为 $k$ 的窗口求该代价。
>
> 若每个窗口重新排序，时间为 $O(nk\log k)$。需要在滑窗时动态维护中位数两侧的有序集合与元素和。
>
> 用两个有序集合 $l$、$r$ 分存较小半与较大半，并保持 $|r|-|l|\in\{0,1\}$，则 $r$ 的最小值即中位数。两侧和 $s_1,s_2$ 可 $O(1)$ 算出距离和。窗口滑出时按所属集合删除。

<!-- thinking:end -->

根据题目描述，我们需要找到一个长度为 $k$ 的子数组，通过最少的操作使得子数组中的所有元素相等，即我们需要找到一个长度为 $k$ 的子数组，使得子数组中所有元素变成这 $k$ 个元素的中位数所需的操作次数最小。

我们可以使用两个有序集合 $l$ 和 $r$ 分别维护 $k$ 个元素的左右两部分，其中 $l$ 用于存储 $k$ 个元素中较小的一部分，$r$ 用于存储 $k$ 个元素中较大的一部分，并且 $l$ 的元素个数要么等于 $r$ 的元素个数，要么比 $r$ 的元素个数少一个，这样 $r$ 的最小值就是 $k$ 个元素中的中位数。

时间复杂度 $O(n \times \log k)$，空间复杂度 $O(k)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        l = SortedList()
        r = SortedList()
        s1 = s2 = 0
        ans = inf
        for i, x in enumerate(nums):
            l.add(x)
            s1 += x
            y = l.pop()
            s1 -= y
            r.add(y)
            s2 += y
            if len(r) - len(l) > 1:
                y = r.pop(0)
                s2 -= y
                l.add(y)
                s1 += y
            if i >= k - 1:
                ans = min(ans, s2 - r[0] * len(r) + r[0] * len(l) - s1)
                j = i - k + 1
                if nums[j] in r:
                    r.remove(nums[j])
                    s2 -= nums[j]
                else:
                    l.remove(nums[j])
                    s1 -= nums[j]
        return ans
```

#### Java

```java
class Solution {
    public long minOperations(int[] nums, int k) {
        TreeMap<Integer, Integer> l = new TreeMap<>();
        TreeMap<Integer, Integer> r = new TreeMap<>();
        long s1 = 0, s2 = 0;
        int sz1 = 0, sz2 = 0;
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            l.merge(nums[i], 1, Integer::sum);
            s1 += nums[i];
            ++sz1;
            int y = l.lastKey();
            if (l.merge(y, -1, Integer::sum) == 0) {
                l.remove(y);
            }
            s1 -= y;
            --sz1;
            r.merge(y, 1, Integer::sum);
            s2 += y;
            ++sz2;
            if (sz2 - sz1 > 1) {
                y = r.firstKey();
                if (r.merge(y, -1, Integer::sum) == 0) {
                    r.remove(y);
                }
                s2 -= y;
                --sz2;
                l.merge(y, 1, Integer::sum);
                s1 += y;
                ++sz1;
            }
            if (i >= k - 1) {
                ans = Math.min(ans, s2 - r.firstKey() * sz2 + r.firstKey() * sz1 - s1);
                int j = i - k + 1;
                if (r.containsKey(nums[j])) {
                    if (r.merge(nums[j], -1, Integer::sum) == 0) {
                        r.remove(nums[j]);
                    }
                    s2 -= nums[j];
                    --sz2;
                } else {
                    if (l.merge(nums[j], -1, Integer::sum) == 0) {
                        l.remove(nums[j]);
                    }
                    s1 -= nums[j];
                    --sz1;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minOperations(vector<int>& nums, int k) {
        multiset<int> l, r;
        long long s1 = 0, s2 = 0, ans = 1e18;
        for (int i = 0; i < nums.size(); ++i) {
            l.insert(nums[i]);
            s1 += nums[i];
            int y = *l.rbegin();
            l.erase(l.find(y));
            s1 -= y;
            r.insert(y);
            s2 += y;
            if (r.size() - l.size() > 1) {
                y = *r.begin();
                r.erase(r.find(y));
                s2 -= y;
                l.insert(y);
                s1 += y;
            }
            if (i >= k - 1) {
                long long x = *r.begin();
                ans = min(ans, s2 - x * (int) r.size() + x * (int) l.size() - s1);
                int j = i - k + 1;
                if (r.contains(nums[j])) {
                    r.erase(r.find(nums[j]));
                    s2 -= nums[j];
                } else {
                    l.erase(l.find(nums[j]));
                    s1 -= nums[j];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int, k int) int64 {
	l := redblacktree.New[int, int]()
	r := redblacktree.New[int, int]()
	merge := func(st *redblacktree.Tree[int, int], x, v int) {
		c, _ := st.Get(x)
		if c+v == 0 {
			st.Remove(x)
		} else {
			st.Put(x, c+v)
		}
	}
	var s1, s2, sz1, sz2 int
	ans := math.MaxInt64
	for i, x := range nums {
		merge(l, x, 1)
		s1 += x
		y := l.Right().Key
		merge(l, y, -1)
		s1 -= y
		merge(r, y, 1)
		s2 += y
		sz2++
		if sz2-sz1 > 1 {
			y = r.Left().Key
			merge(r, y, -1)
			s2 -= y
			sz2--
			merge(l, y, 1)
			s1 += y
			sz1++
		}
		if j := i - k + 1; j >= 0 {
			ans = min(ans, s2-r.Left().Key*sz2+r.Left().Key*sz1-s1)
			if _, ok := r.Get(nums[j]); ok {
				merge(r, nums[j], -1)
				s2 -= nums[j]
				sz2--
			} else {
				merge(l, nums[j], -1)
				s1 -= nums[j]
				sz1--
			}
		}
	}
	return int64(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
