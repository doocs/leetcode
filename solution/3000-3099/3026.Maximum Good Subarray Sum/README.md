# [3026. 最大好子数组和](https://leetcode.cn/problems/maximum-good-subarray-sum)

[English Version](/solution/3000-3099/3026.Maximum%20Good%20Subarray%20Sum/README_EN.md)

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

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        p = {}
        r = float('-inf')
        p[nums[0]] = 0
        s = 0
        n = len(nums)
        for i in range(n):
            s += nums[i]
            if nums[i] - k in p:
                r = max(r, s - p[nums[i] - k])
            if nums[i] + k in p:
                r = max(r, s - p[nums[i] + k])
            if i + 1 == n:
                break
            if nums[i + 1] not in p or p[nums[i + 1]] > s:
                p[nums[i + 1]] = s
        return r if r != float('-inf') else 0
```

```java
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer, Long> p = new HashMap<>();
        long r = Long.MIN_VALUE;
        p.put(nums[0], 0L);
        long s = 0;
        int n = nums.length;
        for (int i = 0;; ++i) {
            s += nums[i];
            if (p.containsKey(nums[i] - k)) {
                r = Math.max(r, s - p.get(nums[i] - k));
            }
            if (p.containsKey(nums[i] + k)) {
                r = Math.max(r, s - p.get(nums[i] + k));
            }
            if (i + 1 == n)
            break;
            if (!p.containsKey(nums[i + 1]) || p.get(nums[i + 1]) > s) {
                p.put(nums[i + 1], s);
            }
        }
        return r == Long.MIN_VALUE ? 0 : r;
    }
}
```

```cpp
class Solution {
public:
    long long maximumSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, long long> p;
        long long r = LONG_LONG_MIN;
        p[nums[0]] = 0;
        long long s = 0;
        const int n = nums.size();
        for (int i = 0;; ++i) {
            s += nums[i];
            auto t = p.find(nums[i] - k);
            if (t != p.end()) {
                r = max(r, s - t->second);
            }
            t = p.find(nums[i] + k);
            if (t != p.end()) {
                r = max(r, s - t->second);
            }
            if (i + 1 == n)
            break;
            t = p.find(nums[i + 1]);
            if (t == p.end() || t->second > s) {
                p[nums[i + 1]] = s;
            }
        }
        return r == LONG_LONG_MIN ? 0 : r;
    }
};
```

```go
func maximumSubarraySum(nums []int, k int) int64 {
    p := make(map[int]int64)
    var r int64 = math.MinInt64
    p[nums[0]] = 0
    var s int64 = 0
    n := len(nums)
    for i := 0; ; i++ {
        s += int64(nums[i])
        if t, ok := p[nums[i]-k]; ok {
            r = max(r, s-t)
        }
        if t, ok := p[nums[i]+k]; ok {
            r = max(r, s-t)
        }
        if i+1 == n {
            break
        }
        if t, ok := p[nums[i+1]]; !ok || t > s {
            p[nums[i+1]] = s
        }
    }
    if r == math.MinInt64 {
        return 0
    }
    return r
}
```

```ts
function maximumSubarraySum(nums: number[], k: number): number {
    const p: Map<number, number> = new Map();
    let r: number = Number.MIN_SAFE_INTEGER;
    p.set(nums[0], 0);
    let s: number = 0;
    const n: number = nums.length;
    for (let i = 0; ; ++i) {
        s += nums[i];
        let t: number | undefined = p.get(nums[i] - k);
        if (t !== undefined) {
            r = Math.max(r, s - t);
        }
        t = p.get(nums[i] + k);
        if (t !== undefined) {
            r = Math.max(r, s - t);
        }
        if (i + 1 === n) break;
        t = p.get(nums[i + 1]);
        if (t === undefined || t > s) {
            p.set(nums[i + 1], s);
        }
    }
    return r === Number.MIN_SAFE_INTEGER ? 0 : r;
}
```

<!-- tabs:end -->

<!-- end -->
