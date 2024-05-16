---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2762.Continuous%20Subarrays/README.md
rating: 1940
source: 第 352 场周赛 Q3
tags:
    - 队列
    - 数组
    - 有序集合
    - 滑动窗口
    - 单调队列
    - 堆（优先队列）
---

# [2762. 不间断子数组](https://leetcode.cn/problems/continuous-subarrays)

[English Version](/solution/2700-2799/2762.Continuous%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。<code>nums</code>&nbsp;的一个子数组如果满足以下条件，那么它是 <strong>不间断</strong> 的：</p>

<ul>
	<li><code>i</code>，<code>i + 1</code>&nbsp;，...，<code>j</code><sub> </sub>&nbsp;表示子数组中的下标。对于所有满足&nbsp;<code>i &lt;= i<sub>1</sub>, i<sub>2</sub> &lt;= j</code>&nbsp;的下标对，都有 <code>0 &lt;= |nums[i<sub>1</sub>] - nums[i<sub>2</sub>]| &lt;= 2</code>&nbsp;。</li>
</ul>

<p>请你返回 <strong>不间断</strong> 子数组的总数目。</p>

<p>子数组是一个数组中一段连续 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [5,4,2,4]
<strong>输出：</strong>8
<b>解释：</b>
大小为 1 的不间断子数组：[5], [4], [2], [4] 。
大小为 2 的不间断子数组：[5,4], [4,2], [2,4] 。
大小为 3 的不间断子数组：[4,2,4] 。
没有大小为 4 的不间断子数组。
不间断子数组的总数目为 4 + 3 + 1 = 8 。
除了这些以外，没有别的不间断子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3]
<b>输出：</b>6
<b>解释：</b>
大小为 1 的不间断子数组：[1], [2], [3] 。
大小为 2 的不间断子数组：[1,2], [2,3] 。
大小为 3 的不间断子数组：[1,2,3] 。
不间断子数组的总数目为 3 + 2 + 1 = 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：有序列表 + 双指针

我们可以用双指针 $i$ 和 $j$ 维护当前子数组的左右端点，用一个有序列表维护当前子数组的所有元素。

遍历数组 $nums$，对于当前遍历到的数字 $nums[i]$，我们将其加到有序列表中，如果此时有序列表中的最大值与最小值的差值大于 $2$，那么我们循环右移指针 $i$，不断将 $nums[i]$ 从有序列表中移出，直到列表为空或者有序列表元素的最大差值不大于 $2$。此时不间断的子数组数目为 $j - i + 1$，我们将其添加到答案中。

遍历结束，返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
from sortedcontainers import SortedList


class Solution:
    def continuousSubarrays(self, nums: List[int]) -> int:
        ans = i = 0
        sl = SortedList()
        for x in nums:
            sl.add(x)
            while sl[-1] - sl[0] > 2:
                sl.remove(nums[i])
                i += 1
            ans += len(sl)
        return ans
```

```java
class Solution {
    public long continuousSubarrays(int[] nums) {
        long ans = 0;
        int i = 0, n = nums.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int j = 0; j < n; ++j) {
            tm.merge(nums[j], 1, Integer::sum);
            while (tm.lastEntry().getKey() - tm.firstEntry().getKey() > 2) {
                tm.merge(nums[i], -1, Integer::sum);
                if (tm.get(nums[i]) == 0) {
                    tm.remove(nums[i]);
                }
                ++i;
            }
            ans += j - i + 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long continuousSubarrays(vector<int>& nums) {
        long long ans = 0;
        int i = 0, n = nums.size();
        multiset<int> s;
        for (int j = 0; j < n; ++j) {
            s.insert(nums[j]);
            while (*s.rbegin() - *s.begin() > 2) {
                s.erase(s.find(nums[i++]));
            }
            ans += j - i + 1;
        }
        return ans;
    }
};
```

```go
func continuousSubarrays(nums []int) (ans int64) {
	i := 0
	tm := treemap.NewWithIntComparator()
	for j, x := range nums {
		if v, ok := tm.Get(x); ok {
			tm.Put(x, v.(int)+1)
		} else {
			tm.Put(x, 1)
		}
		for {
			a, _ := tm.Min()
			b, _ := tm.Max()
			if b.(int)-a.(int) > 2 {
				if v, _ := tm.Get(nums[i]); v.(int) == 1 {
					tm.Remove(nums[i])
				} else {
					tm.Put(nums[i], v.(int)-1)
				}
				i++
			} else {
				break
			}
		}
		ans += int64(j - i + 1)
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
