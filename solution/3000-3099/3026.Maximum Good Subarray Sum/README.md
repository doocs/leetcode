# [3026. 最大好子数组和](https://leetcode.cn/problems/maximum-good-subarray-sum)

[English Version](/solution/3000-3099/3026.Maximum%20Good%20Subarray%20Sum/README_EN.md)

<!-- tags:数组,哈希表,前缀和 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p>如果&nbsp;<code>nums</code>&nbsp;的一个子数组中，第一个元素和最后一个元素 <strong>差的绝对值恰好</strong>&nbsp;为&nbsp;<code>k</code>&nbsp;，我们称这个子数组为&nbsp;<strong>好</strong>&nbsp;的。换句话说，如果子数组&nbsp;<code>nums[i..j]</code>&nbsp;满足&nbsp;<code>|nums[i] - nums[j]| == k</code>&nbsp;，那么它是一个好子数组。</p>

<p>请你返回&nbsp;<code>nums</code>&nbsp;中&nbsp;<strong>好</strong>&nbsp;子数组的&nbsp;<strong>最大</strong>&nbsp;和，如果没有好子数组，返回<em>&nbsp;</em><code>0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5,6], k = 1
<b>输出：</b>11
<b>解释：</b>好子数组中第一个元素和最后一个元素的差的绝对值必须为 1 。好子数组有 [1,2] ，[2,3] ，[3,4] ，[4,5] 和 [5,6] 。最大子数组和为 11 ，对应的子数组为 [5,6] 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [-1,3,2,4,5], k = 3
<b>输出：</b>11
<b>解释：</b>好子数组中第一个元素和最后一个元素的差的绝对值必须为 3 。好子数组有 [-1,3,2] 和 [2,4,5] 。最大子数组和为 11 ，对应的子数组为 [2,4,5] 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [-1,-2,-3,-4], k = 2
<b>输出：</b>-6
<b>解释：</b>好子数组中第一个元素和最后一个元素的差的绝对值必须为 2 。好子数组有 [-1,-2,-3] 和 [-2,-3,-4] 。最大子数组和为 -6 ，对应的子数组为 [-1,-2,-3] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：前缀和 + 哈希表

我们用一个哈希表 $p$ 记录 $nums[i]$ 的前缀数组 $nums[0..i-1]$ 的和 $s$，如果有多个相同的 $nums[i]$，我们只保留最小的 $s$。初始时，我们将 $p[nums[0]]$ 设为 $0$。另外，我们用一个变量 $s$ 记录当前的前缀和，初始时 $s = 0$。初始化答案 $ans$ 为 $-\infty$。

接下来，我们枚举 $nums[i]$，并且维护一个变量 $s$ 表示 $nums[0..i]$ 的和。如果 $nums[i] - k$ 在 $p$ 中，那么我们就找到了一个好子数组，将答案更新为 $ans = \max(ans, s - p[nums[i] - k])$。同理，如果 $nums[i] + k$ 在 $p$ 中，那么我们也找到了一个好子数组，将答案更新为 $ans = \max(ans, s - p[nums[i] + k])$。然后，如果 $i + 1 \lt n$ 并且 $nums[i + 1]$ 不在 $p$ 中，或者 $p[nums[i + 1]] \gt s$，我们就将 $p[nums[i + 1]]$ 设为 $s$。

最后，如果 $ans = -\infty$，那么我们返回 $0$，否则返回 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        ans = -inf
        p = {nums[0]: 0}
        s, n = 0, len(nums)
        for i, x in enumerate(nums):
            s += x
            if x - k in p:
                ans = max(ans, s - p[x - k])
            if x + k in p:
                ans = max(ans, s - p[x + k])
            if i + 1 < n and (nums[i + 1] not in p or p[nums[i + 1]] > s):
                p[nums[i + 1]] = s
        return 0 if ans == -inf else ans
```

```java
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Long> p = new HashMap<>();
        p.put(nums[0], 0L);
        long s = 0;
        int n = nums.length;
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (p.containsKey(nums[i] - k)) {
                ans = Math.max(ans, s - p.get(nums[i] - k));
            }
            if (p.containsKey(nums[i] + k)) {
                ans = Math.max(ans, s - p.get(nums[i] + k));
            }
            if (i + 1 < n && (!p.containsKey(nums[i + 1]) || p.get(nums[i + 1]) > s)) {
                p.put(nums[i + 1], s);
            }
        }
        return ans == Long.MIN_VALUE ? 0 : ans;
    }
}
```

```cpp
class Solution {
public:
    long long maximumSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, long long> p;
        p[nums[0]] = 0;
        long long s = 0;
        const int n = nums.size();
        long long ans = LONG_LONG_MIN;
        for (int i = 0;; ++i) {
            s += nums[i];
            auto it = p.find(nums[i] - k);
            if (it != p.end()) {
                ans = max(ans, s - it->second);
            }
            it = p.find(nums[i] + k);
            if (it != p.end()) {
                ans = max(ans, s - it->second);
            }
            if (i + 1 == n) {
                break;
            }
            it = p.find(nums[i + 1]);
            if (it == p.end() || it->second > s) {
                p[nums[i + 1]] = s;
            }
        }
        return ans == LONG_LONG_MIN ? 0 : ans;
    }
};
```

```go
func maximumSubarraySum(nums []int, k int) int64 {
	p := map[int]int64{nums[0]: 0}
	var s int64 = 0
	n := len(nums)
	var ans int64 = math.MinInt64
	for i, x := range nums {
		s += int64(x)
		if t, ok := p[nums[i]-k]; ok {
			ans = max(ans, s-t)
		}
		if t, ok := p[nums[i]+k]; ok {
			ans = max(ans, s-t)
		}
		if i+1 == n {
			break
		}
		if t, ok := p[nums[i+1]]; !ok || s < t {
			p[nums[i+1]] = s
		}
	}
	if ans == math.MinInt64 {
		return 0
	}
	return ans
}
```

```ts
function maximumSubarraySum(nums: number[], k: number): number {
    const p: Map<number, number> = new Map();
    p.set(nums[0], 0);
    let ans: number = -Infinity;
    let s: number = 0;
    const n: number = nums.length;
    for (let i = 0; i < n; ++i) {
        s += nums[i];
        if (p.has(nums[i] - k)) {
            ans = Math.max(ans, s - p.get(nums[i] - k)!);
        }
        if (p.has(nums[i] + k)) {
            ans = Math.max(ans, s - p.get(nums[i] + k)!);
        }
        if (i + 1 < n && (!p.has(nums[i + 1]) || p.get(nums[i + 1])! > s)) {
            p.set(nums[i + 1], s);
        }
    }
    return ans === -Infinity ? 0 : ans;
}
```

```cs
public class Solution {
    public long MaximumSubarraySum(int[] nums, int k) {
        Dictionary<int, long> p = new Dictionary<int, long>();
        p[nums[0]] = 0L;
        long s = 0;
        int n = nums.Length;
        long ans = long.MinValue;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (p.ContainsKey(nums[i] - k)) {
                ans = Math.Max(ans, s - p[nums[i] - k]);
            }
            if (p.ContainsKey(nums[i] + k)) {
                ans = Math.Max(ans, s - p[nums[i] + k]);
            }
            if (i + 1 < n && (!p.ContainsKey(nums[i + 1]) || p[nums[i + 1]] > s)) {
                p[nums[i + 1]] = s;
            }
        }
        return ans == long.MinValue ? 0 : ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
