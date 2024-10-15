---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3013.Divide%20an%20Array%20Into%20Subarrays%20With%20Minimum%20Cost%20II/README.md
rating: 2540
source: 第 122 场双周赛 Q4
tags:
    - 数组
    - 哈希表
    - 滑动窗口
    - 堆（优先队列）
---

<!-- problem:start -->

# [3013. 将数组分成最小总代价的子数组 II](https://leetcode.cn/problems/divide-an-array-into-subarrays-with-minimum-cost-ii)

[English Version](/solution/3000-3099/3013.Divide%20an%20Array%20Into%20Subarrays%20With%20Minimum%20Cost%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和两个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code> 和&nbsp;<code>dist</code>&nbsp;。</p>

<p>一个数组的 <strong>代价</strong>&nbsp;是数组中的 <strong>第一个</strong>&nbsp;元素。比方说，<code>[1,2,3]</code>&nbsp;的代价为&nbsp;<code>1</code>&nbsp;，<code>[3,4,1]</code>&nbsp;的代价为&nbsp;<code>3</code>&nbsp;。</p>

<p>你需要将 <code>nums</code>&nbsp;分割成 <code>k</code>&nbsp;个 <strong>连续且互不相交</strong>&nbsp;的<span data-keyword="subarray">子数组</span>，满足 <strong>第二</strong>&nbsp;个子数组与第 <code>k</code>&nbsp;个子数组中第一个元素的下标距离 <strong>不超过</strong>&nbsp;<code>dist</code>&nbsp;。换句话说，如果你将&nbsp;<code>nums</code>&nbsp;分割成子数组&nbsp;<code>nums[0..(i<sub>1</sub> - 1)], nums[i<sub>1</sub>..(i<sub>2</sub> - 1)], ..., nums[i<sub>k-1</sub>..(n - 1)]</code>&nbsp;，那么它需要满足&nbsp;<code>i<sub>k-1</sub> - i<sub>1</sub> &lt;= dist</code>&nbsp;。</p>

<p>请你返回这些子数组的 <strong>最小</strong>&nbsp;总代价。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,3,2,6,4,2], k = 3, dist = 3
<b>输出：</b>5
<b>解释：</b>将数组分割成 3 个子数组的最优方案是：[1,3] ，[2,6,4] 和 [2] 。这是一个合法分割，因为 i<sub>k-1</sub> - i<sub>1</sub> 等于 5 - 2 = 3 ，等于 dist 。总代价为 nums[0] + nums[2] + nums[5] ，也就是 1 + 2 + 2 = 5 。
5 是分割成 3 个子数组的最小总代价。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,1,2,2,2,1], k = 4, dist = 3
<b>输出：</b>15
<b>解释：</b>将数组分割成 4 个子数组的最优方案是：[10] ，[1] ，[2] 和 [2,2,1] 。这是一个合法分割，因为 i<sub>k-1</sub> - i<sub>1</sub> 等于 3 - 1 = 2 ，小于 dist 。总代价为 nums[0] + nums[1] + nums[2] + nums[3] ，也就是 10 + 1 + 2 + 2 = 15 。
分割 [10] ，[1] ，[2,2,2] 和 [1] 不是一个合法分割，因为 i<sub>k-1</sub> 和 i<sub>1</sub> 的差为 5 - 1 = 4 ，大于 dist 。
15 是分割成 4 个子数组的最小总代价。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [10,8,18,9], k = 3, dist = 1
<b>输出：</b>36
<b>解释：</b>将数组分割成 4 个子数组的最优方案是：[10] ，[8] 和 [18,9] 。这是一个合法分割，因为 i<sub>k-1</sub> - i<sub>1</sub> 等于 2 - 1 = 1 ，等于 dist 。总代价为 nums[0] + nums[1] + nums[2] ，也就是 10 + 8 + 18 = 36 。
分割 [10] ，[8,18] 和 [9] 不是一个合法分割，因为 i<sub>k-1</sub> 和 i<sub>1</sub> 的差为 3 - 1 = 2 ，大于 dist 。
36 是分割成 3 个子数组的最小总代价。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>3 &lt;= k &lt;= n</code></li>
	<li><code>k - 2 &lt;= dist &lt;= n - 2</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：有序集合

题目需要我们将数组 $\textit{nums}$ 分割成 $k$ 个连续且不相交的子数组，并且第二个子数组的第一个元素与第 $k$ 个子数组的第一个元素的下标距离不超过 $\textit{dist}$，这等价于让我们从 $\textit{nums}$ 下标为 $1$ 的元素开始，找到一个窗口大小为 $\textit{dist}+1$ 的子数组，求其中前 $k-1$ 的最小元素之和。我们将 $k$ 减 $1$，这样我们只需要求出 $k$ 个最小元素之和，再加上 $\textit{nums}[0]$ 即可。

我们可以使用两个有序集合 $\textit{l}$ 和 $\textit{r}$ 分别维护大小为 $\textit{dist} + 1$ 的窗口元素，其中 $\textit{l}$ 维护最小的 $k$ 个元素，而 $\textit{r}$ 维护窗口的剩余元素。我们维护一个变量 $\textit{s}$ 表示 $\textit{nums}[0]$ 与 $l$ 中元素之和。初始时，我们将前 $\textit{dist}+2$ 个元素之和累加到 $\textit{s}$ 中，并将下标为 $[1, \textit{dist} + 1]$ 的所有元素加入到 $\textit{l}$ 中。如果 $\textit{l}$ 的大小大于 $k$，我们循环将 $\textit{l}$ 中的最大元素移动到 $\textit{r}$ 中，直到 $\textit{l}$ 的大小等于 $k$，过程中更新 $\textit{s}$ 的值。

那么此时初始答案 $\textit{ans} = \textit{s}$。

接下来我们从 $\textit{dist}+2$ 开始遍历 $\textit{nums}$，对于每一个元素 $\textit{nums}[i]$，我们需要将 $\textit{nums}[i-\textit{dist}-1]$ 从 $\textit{l}$ 或 $\textit{r}$ 中移除，然后将 $\textit{nums}[i]$ 加入到 $\textit{l}$ 或 $\textit{r}$ 中。如果 $\textit{nums}[i]$ 小于 $\textit{l}$ 中的最大元素，我们将 $\textit{nums}[i]$ 加入到 $\textit{l}$ 中，否则加入到 $\textit{r}$ 中。如果此时 $\textit{l}$ 的大小小于 $k$，我们将 $\textit{r}$ 中的最小元素移动到 $\textit{l}$ 中，直到 $\textit{l}$ 的大小等于 $k$。如果此时 $\textit{l}$ 的大小大于 $k$，我们将 $\textit{l}$ 中的最大元素移动到 $\textit{r}$ 中，直到 $\textit{l}$ 的大小等于 $k$。过程中更新 $\textit{s}$ 的值，并更新 $\textit{ans} = \min(\textit{ans}, \textit{s})$。

最终答案即为 $\textit{ans}$。

时间复杂度 $O(n \times \log \textit{dist})$，空间复杂度 $O(\textit{dist})$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
from sortedcontainers import SortedList


class Solution:
    def minimumCost(self, nums: List[int], k: int, dist: int) -> int:
        def l2r():
            nonlocal s
            x = l.pop()
            s -= x
            r.add(x)

        def r2l():
            nonlocal s
            x = r.pop(0)
            l.add(x)
            s += x

        k -= 1
        s = sum(nums[: dist + 2])
        l = SortedList(nums[1 : dist + 2])
        r = SortedList()
        while len(l) > k:
            l2r()
        ans = s
        for i in range(dist + 2, len(nums)):
            x = nums[i - dist - 1]
            if x in l:
                l.remove(x)
                s -= x
            else:
                r.remove(x)
            y = nums[i]
            if y < l[-1]:
                l.add(y)
                s += y
            else:
                r.add(y)
            while len(l) < k:
                r2l()
            while len(l) > k:
                l2r()
            ans = min(ans, s)
        return ans
```

#### Java

```java
class Solution {
    private final TreeMap<Integer, Integer> l = new TreeMap<>();
    private final TreeMap<Integer, Integer> r = new TreeMap<>();
    private long s;
    private int size;

    public long minimumCost(int[] nums, int k, int dist) {
        --k;
        s = nums[0];
        for (int i = 1; i < dist + 2; ++i) {
            s += nums[i];
            l.merge(nums[i], 1, Integer::sum);
        }
        size = dist + 1;
        while (size > k) {
            l2r();
        }
        long ans = s;
        for (int i = dist + 2; i < nums.length; ++i) {
            int x = nums[i - dist - 1];
            if (l.containsKey(x)) {
                if (l.merge(x, -1, Integer::sum) == 0) {
                    l.remove(x);
                }
                s -= x;
                --size;
            } else if (r.merge(x, -1, Integer::sum) == 0) {
                r.remove(x);
            }
            int y = nums[i];
            if (y < l.lastKey()) {
                l.merge(y, 1, Integer::sum);
                ++size;
                s += y;
            } else {
                r.merge(y, 1, Integer::sum);
            }
            while (size < k) {
                r2l();
            }
            while (size > k) {
                l2r();
            }
            ans = Math.min(ans, s);
        }
        return ans;
    }

    private void l2r() {
        int x = l.lastKey();
        s -= x;
        if (l.merge(x, -1, Integer::sum) == 0) {
            l.remove(x);
        }
        --size;
        r.merge(x, 1, Integer::sum);
    }

    private void r2l() {
        int x = r.firstKey();
        if (r.merge(x, -1, Integer::sum) == 0) {
            r.remove(x);
        }
        l.merge(x, 1, Integer::sum);
        s += x;
        ++size;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumCost(vector<int>& nums, int k, int dist) {
        --k;
        multiset<int> l(nums.begin() + 1, nums.begin() + dist + 2), r;
        long long s = accumulate(nums.begin(), nums.begin() + dist + 2, 0LL);
        while (l.size() > k) {
            int x = *l.rbegin();
            l.erase(l.find(x));
            s -= x;
            r.insert(x);
        }
        long long ans = s;
        for (int i = dist + 2; i < nums.size(); ++i) {
            int x = nums[i - dist - 1];
            auto it = l.find(x);
            if (it != l.end()) {
                l.erase(it);
                s -= x;
            } else {
                r.erase(r.find(x));
            }
            int y = nums[i];
            if (y < *l.rbegin()) {
                l.insert(y);
                s += y;
            } else {
                r.insert(y);
            }
            while (l.size() == k - 1) {
                int x = *r.begin();
                r.erase(r.find(x));
                l.insert(x);
                s += x;
            }
            while (l.size() == k + 1) {
                int x = *l.rbegin();
                l.erase(l.find(x));
                s -= x;
                r.insert(x);
            }
            ans = min(ans, s);
        }
        return ans;
    }
};
```

#### Go

```go
func minimumCost(nums []int, k int, dist int) int64 {
	k--
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

	s := nums[0]
	for _, x := range nums[1 : dist+2] {
		s += x
		merge(l, x, 1)
	}
	size := dist + 1

	l2r := func() {
		x := l.Right().Key
		merge(l, x, -1)
		s -= x
		size--
		merge(r, x, 1)
	}
	r2l := func() {
		x := r.Left().Key
		merge(r, x, -1)
		merge(l, x, 1)
		s += x
		size++
	}

	for size > k {
		l2r()
	}

	ans := s
	for i := dist + 2; i < len(nums); i++ {
		x := nums[i-dist-1]
		if _, ok := l.Get(x); ok {
			merge(l, x, -1)
			s -= x
			size--
		} else {
			merge(r, x, -1)
		}
		y := nums[i]
		if y < l.Right().Key {
			merge(l, y, 1)
			s += y
			size++
		} else {
			merge(r, y, 1)
		}
		for size < k {
			r2l()
		}
		for size > k {
			l2r()
		}
		ans = min(ans, s)

	}
	return int64(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
