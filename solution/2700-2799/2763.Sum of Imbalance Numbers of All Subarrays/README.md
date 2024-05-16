---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2763.Sum%20of%20Imbalance%20Numbers%20of%20All%20Subarrays/README.md
rating: 2277
source: 第 352 场周赛 Q4
tags:
    - 数组
    - 哈希表
    - 有序集合
---

# [2763. 所有子数组中不平衡数字之和](https://leetcode.cn/problems/sum-of-imbalance-numbers-of-all-subarrays)

[English Version](/solution/2700-2799/2763.Sum%20of%20Imbalance%20Numbers%20of%20All%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组 <code>arr</code>&nbsp;的 <strong>不平衡数字</strong>&nbsp;定义为，在&nbsp;<code>sarr = sorted(arr)</code>&nbsp;数组中，满足以下条件的下标数目：</p>

<ul>
	<li><code>0 &lt;= i &lt; n - 1</code>&nbsp;，和</li>
	<li><code>sarr[i+1] - sarr[i] &gt; 1</code></li>
</ul>

<p>这里，<code>sorted(arr)</code>&nbsp;表示将数组 <code>arr</code>&nbsp;排序后得到的数组。</p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，请你返回它所有&nbsp;<strong>子数组</strong>&nbsp;的&nbsp;<strong>不平衡数字</strong>&nbsp;之和。</p>

<p>子数组指的是一个数组中连续一段 <strong>非空</strong>&nbsp;的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,3,1,4]
<b>输出：</b>3
<b>解释：</b>总共有 3 个子数组有非 0 不平衡数字：
- 子数组 [3, 1] ，不平衡数字为 1 。
- 子数组 [3, 1, 4] ，不平衡数字为 1 。
- 子数组 [1, 4] ，不平衡数字为 1 。
其他所有子数组的不平衡数字都是 0 ，所以所有子数组的不平衡数字之和为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,3,3,3,5]
<b>输出：</b>8
<b>解释：</b>总共有 7 个子数组有非 0 不平衡数字：
- 子数组 [1, 3] ，不平衡数字为 1 。
- 子数组 [1, 3, 3] ，不平衡数字为 1 。
- 子数组 [1, 3, 3, 3] ，不平衡数字为 1 。
- 子数组 [1, 3, 3, 3, 5] ，不平衡数字为 2 。
- 子数组 [3, 3, 3, 5] ，不平衡数字为 1 。
- 子数组 [3, 3, 5] ，不平衡数字为 1 。
- 子数组 [3, 5] ，不平衡数字为 1 。
其他所有子数组的不平衡数字都是 0 ，所以所有子数组的不平衡数字之和为 8 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
</ul>

## 解法

### 方法一：枚举 + 有序集合

我们可以先枚举子数组的左端点 $i$，对于每个 $i$，我们从小到大枚举子数组的右端点 $j$，并且用一个有序列表维护当前子数组中的所有元素，用一个变量 $cnt$ 维护当前子数组的不平衡数字。

对于每个数字 $nums[j]$，我们在有序列表中找到第一个大于等于 $nums[j]$ 的元素 $nums[k]$，以及最后一个小于 $nums[j]$ 的元素 $nums[h]$：

-   如果 $nums[k]$ 存在，并且 $nums[k]$ 与 $nums[j]$ 的差值大于 $1$，那么不平衡数字加 $1$；
-   如果 $nums[h]$ 存在，并且 $nums[j]$ 与 $nums[h]$ 的差值大于 $1$，那么不平衡数字加 $1$；
-   如果 $nums[k]$ 存在，并且 $nums[h]$ 存在，那么将元素 $nums[j]$ 插入 $nums[h]$ 和 $nums[k]$ 的中间，会使得平衡数字减 $1$。

然后，我们将当前子数组的平衡数字累加到答案中，继续遍历，直到遍历完所有子数组。

时间复杂度 $O(n^2 \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
from sortedcontainers import SortedList


class Solution:
    def sumImbalanceNumbers(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            sl = SortedList()
            cnt = 0
            for j in range(i, n):
                k = sl.bisect_left(nums[j])
                h = k - 1
                if h >= 0 and nums[j] - sl[h] > 1:
                    cnt += 1
                if k < len(sl) and sl[k] - nums[j] > 1:
                    cnt += 1
                if h >= 0 and k < len(sl) and sl[k] - sl[h] > 1:
                    cnt -= 1
                sl.add(nums[j])
                ans += cnt
        return ans
```

```java
class Solution {
    public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                Integer k = tm.ceilingKey(nums[j]);
                if (k != null && k - nums[j] > 1) {
                    ++cnt;
                }
                Integer h = tm.floorKey(nums[j]);
                if (h != null && nums[j] - h > 1) {
                    ++cnt;
                }
                if (h != null && k != null && k - h > 1) {
                    --cnt;
                }
                tm.merge(nums[j], 1, Integer::sum);
                ans += cnt;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int sumImbalanceNumbers(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            multiset<int> s;
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                auto it = s.lower_bound(nums[j]);
                if (it != s.end() && *it - nums[j] > 1) {
                    ++cnt;
                }
                if (it != s.begin() && nums[j] - *prev(it) > 1) {
                    ++cnt;
                }
                if (it != s.end() && it != s.begin() && *it - *prev(it) > 1) {
                    --cnt;
                }
                s.insert(nums[j]);
                ans += cnt;
            }
        }
        return ans;
    }
};
```

<!-- tabs:end -->

<!-- end -->
