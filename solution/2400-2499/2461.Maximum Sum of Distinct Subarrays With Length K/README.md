---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2461.Maximum%20Sum%20of%20Distinct%20Subarrays%20With%20Length%20K/README.md
rating: 1552
source: 第 318 场周赛 Q2
tags:
    - 数组
    - 哈希表
    - 滑动窗口
---

# [2461. 长度为 K 子数组中的最大和](https://leetcode.cn/problems/maximum-sum-of-distinct-subarrays-with-length-k)

[English Version](/solution/2400-2499/2461.Maximum%20Sum%20of%20Distinct%20Subarrays%20With%20Length%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。请你从 <code>nums</code> 中满足下述条件的全部子数组中找出最大子数组和：</p>

<ul>
	<li>子数组的长度是 <code>k</code>，且</li>
	<li>子数组中的所有元素 <strong>各不相同 。</strong></li>
</ul>

<p>返回满足题面要求的最大子数组和。如果不存在子数组满足这些条件，返回 <code>0</code> 。</p>

<p><strong>子数组</strong> 是数组中一段连续非空的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,5,4,2,9,9,9], k = 3
<strong>输出：</strong>15
<strong>解释：</strong>nums 中长度为 3 的子数组是：
- [1,5,4] 满足全部条件，和为 10 。
- [5,4,2] 满足全部条件，和为 11 。
- [4,2,9] 满足全部条件，和为 15 。
- [2,9,9] 不满足全部条件，因为元素 9 出现重复。
- [9,9,9] 不满足全部条件，因为元素 9 出现重复。
因为 15 是满足全部条件的所有子数组中的最大子数组和，所以返回 15 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [4,4,4], k = 3
<strong>输出：</strong>0
<strong>解释：</strong>nums 中长度为 3 的子数组是：
- [4,4,4] 不满足全部条件，因为元素 4 出现重复。
因为不存在满足全部条件的子数组，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：滑动窗口 + 哈希表

我们维护一个长度为 $k$ 的滑动窗口，用哈希表 $cnt$ 记录窗口中每个数字出现的次数，用变量 $s$ 记录窗口中所有数字的和。每次滑动窗口，如果窗口中的数字都不重复，那么更新答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        cnt = Counter(nums[:k])
        s = sum(nums[:k])
        ans = s if len(cnt) == k else 0
        for i in range(k, len(nums)):
            cnt[nums[i]] += 1
            cnt[nums[i - k]] -= 1
            if cnt[nums[i - k]] == 0:
                cnt.pop(nums[i - k])
            s += nums[i] - nums[i - k]
            if len(cnt) == k:
                ans = max(ans, s)
        return ans
```

```java
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<>(k);
        long s = 0;
        for (int i = 0; i < k; ++i) {
            cnt.merge(nums[i], 1, Integer::sum);
            s += nums[i];
        }
        long ans = cnt.size() == k ? s : 0;
        for (int i = k; i < n; ++i) {
            cnt.merge(nums[i], 1, Integer::sum);
            if (cnt.merge(nums[i - k], -1, Integer::sum) == 0) {
                cnt.remove(nums[i - k]);
            }
            s += nums[i] - nums[i - k];
            if (cnt.size() == k) {
                ans = Math.max(ans, s);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maximumSubarraySum(vector<int>& nums, int k) {
        using ll = long long;
        int n = nums.size();
        unordered_map<int, ll> cnt;
        ll s = 0;
        for (int i = 0; i < k; ++i) {
            ++cnt[nums[i]];
            s += nums[i];
        }
        ll ans = cnt.size() == k ? s : 0;
        for (int i = k; i < n; ++i) {
            ++cnt[nums[i]];
            if (--cnt[nums[i - k]] == 0) {
                cnt.erase(nums[i - k]);
            }
            s += nums[i] - nums[i - k];
            if (cnt.size() == k) {
                ans = max(ans, s);
            }
        }
        return ans;
    }
};
```

```go
func maximumSubarraySum(nums []int, k int) (ans int64) {
	n := len(nums)
	cnt := map[int]int64{}
	var s int64
	for _, x := range nums[:k] {
		cnt[x]++
		s += int64(x)
	}
	if len(cnt) == k {
		ans = s
	}
	for i := k; i < n; i++ {
		cnt[nums[i]]++
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			delete(cnt, nums[i-k])
		}
		s += int64(nums[i] - nums[i-k])
		if len(cnt) == k && ans < s {
			ans = s
		}
	}
	return
}
```

```ts
function maximumSubarraySum(nums: number[], k: number): number {
    const n = nums.length;
    const cnt: Map<number, number> = new Map();
    let s = 0;
    for (let i = 0; i < k; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
        s += nums[i];
    }
    let ans = cnt.size === k ? s : 0;
    for (let i = k; i < n; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
        cnt.set(nums[i - k], cnt.get(nums[i - k])! - 1);
        if (cnt.get(nums[i - k]) === 0) {
            cnt.delete(nums[i - k]);
        }
        s += nums[i] - nums[i - k];
        if (cnt.size === k) {
            ans = Math.max(ans, s);
        }
    }
    return ans;
}
```

```cs
public class Solution {
    public long MaximumSubarraySum(int[] nums, int k) {
        int n = nums.Length;
        Dictionary<int, int> cnt = new Dictionary<int, int>(k);
        long s = 0;

        for (int i = 0; i < k; ++i) {
            if (!cnt.ContainsKey(nums[i])) {
                cnt[nums[i]] = 1;
            }
            else {
                cnt[nums[i]]++;
            }
            s += nums[i];
        }

        long ans = cnt.Count == k ? s : 0;

        for (int i = k; i < n; ++i) {
            if (!cnt.ContainsKey(nums[i])) {
                cnt[nums[i]] = 1;
            }
            else {
                cnt[nums[i]]++;
            }
            if (--cnt[nums[i - k]] == 0) {
                cnt.Remove(nums[i - k]);
            }

            s += nums[i] - nums[i - k];

            if (cnt.Count == k) {
                ans = Math.Max(ans, s);
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
