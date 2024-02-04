# [3026. Maximum Good Subarray Sum](https://leetcode.com/problems/maximum-good-subarray-sum)

[中文文档](/solution/3000-3099/3026.Maximum%20Good%20Subarray%20Sum/README.md)

## Description

<p>You are given an array <code>nums</code> of length <code>n</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>A <span data-keyword="subarray-nonempty">subarray</span> of <code>nums</code> is called <strong>good</strong> if the <strong>absolute difference</strong> between its first and last element is <strong>exactly</strong> <code>k</code>, in other words, the subarray <code>nums[i..j]</code> is good if <code>|nums[i] - nums[j]| == k</code>.</p>

<p>Return <em>the <strong>maximum</strong> sum of a <strong>good</strong> subarray of </em><code>nums</code>. <em>If there are no good subarrays</em><em>, return </em><code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6], k = 1
<strong>Output:</strong> 11
<strong>Explanation:</strong> The absolute difference between the first and last element<!-- notionvc: 2a6d66c9-0149-4294-b267-8be9fe252de9 --> must be 1 for a good subarray. All the good subarrays are: [1,2], [2,3], [3,4], [4,5], and [5,6]. The maximum subarray sum is 11 for the subarray [5,6].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,3,2,4,5], k = 3
<strong>Output:</strong> 11
<strong>Explanation:</strong> The absolute difference between the first and last element<!-- notionvc: 2a6d66c9-0149-4294-b267-8be9fe252de9 --> must be 3 for a good subarray. All the good subarrays are: [-1,3,2], and [2,4,5]. The maximum subarray sum is 11 for the subarray [2,4,5].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-2,-3,-4], k = 2
<strong>Output:</strong> -6
<strong>Explanation:</strong> The absolute difference between the first and last element<!-- notionvc: 2a6d66c9-0149-4294-b267-8be9fe252de9 --> must be 2 for a good subarray. All the good subarrays are: [-1,-2,-3], and [-2,-3,-4]. The maximum subarray sum is -6 for the subarray [-1,-2,-3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1

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
            if (i + 1 == n) break;
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
