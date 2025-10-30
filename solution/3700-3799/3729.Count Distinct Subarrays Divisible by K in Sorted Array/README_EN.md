---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3729.Count%20Distinct%20Subarrays%20Divisible%20by%20K%20in%20Sorted%20Array/README_EN.md
rating: 2248
source: Weekly Contest 473 Q4
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [3729. Count Distinct Subarrays Divisible by K in Sorted Array](https://leetcode.com/problems/count-distinct-subarrays-divisible-by-k-in-sorted-array)

[中文文档](/solution/3700-3799/3729.Count%20Distinct%20Subarrays%20Divisible%20by%20K%20in%20Sorted%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> <strong>sorted</strong> in <strong>non-descending</strong> order and a positive integer <code>k</code>.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> of <code>nums</code> is <strong>good</strong> if the sum of its elements is <strong>divisible</strong> by <code>k</code>.</p>

<p>Return an integer denoting the number of <strong>distinct</strong> <strong>good</strong> subarrays of <code>nums</code>.</p>

<p>Subarrays are <strong>distinct</strong> if their sequences of values are. For example, there are 3 <strong>distinct</strong> subarrays in <code>[1, 1, 1]</code>, namely <code>[1]</code>, <code>[1, 1]</code>, and <code>[1, 1, 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The good subarrays are <code>[1, 2]</code>, <code>[3]</code>, and <code>[1, 2, 3]</code>. For example, <code>[1, 2, 3]</code> is good because the sum of its elements is <code>1 + 2 + 3 = 6</code>, and <code>6 % k = 6 % 3 = 0</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,2,2,2,2], k = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The good subarrays are <code>[2, 2, 2]</code> and <code>[2, 2, 2, 2, 2, 2]</code>. For example, <code>[2, 2, 2]</code> is good because the sum of its elements is <code>2 + 2 + 2 = 6</code>, and <code>6 % k = 6 % 6 = 0</code>.</p>

<p>Note that <code>[2, 2, 2]</code> is counted only once.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> is sorted in non-descending order.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numGoodSubarrays(self, nums: List[int], k: int) -> int:
        cnt = Counter({0: 1})
        ans = s = 0
        for x in nums:
            s = (s + x) % k
            ans += cnt[s]
            cnt[s] += 1
        n = len(nums)
        i = 0
        while i < n:
            j = i + 1
            while j < n and nums[j] == nums[i]:
                j += 1
            m = j - i
            for h in range(1, m + 1):
                if (h * nums[i]) % k == 0:
                    ans -= m - h
            i = j
        return ans
```

#### Java

```java
class Solution {
    public long numGoodSubarrays(int[] nums, int k) {
        long ans = 0;
        int s = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        for (int x : nums) {
            s = (s + x) % k;
            ans += cnt.getOrDefault(s, 0);
            cnt.merge(s, 1, Integer::sum);
        }
        int n = nums.length;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) {
                ++j;
            }
            int m = j - i;
            for (int h = 1; h <= m; ++h) {
                if (1L * nums[i] * h % k == 0) {
                    ans -= (m - h);
                }
            }
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numGoodSubarrays(vector<int>& nums, int k) {
        long long ans = 0;
        int s = 0;
        unordered_map<int, int> cnt;
        cnt[0] = 1;
        for (int x : nums) {
            s = (s + x) % k;
            ans += cnt[s]++;
        }
        int n = nums.size();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) {
                ++j;
            }
            int m = j - i;
            for (int h = 1; h <= m; ++h) {
                if (1LL * nums[i] * h % k == 0) {
                    ans -= (m - h);
                }
            }
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func numGoodSubarrays(nums []int, k int) (ans int64) {
    s := 0
    cnt := map[int]int{0: 1}
    for _, x := range nums {
        s = (s + x) % k
        ans += int64(cnt[s])
        cnt[s]++
    }

    n := len(nums)
    for i := 0; i < n; {
        j := i + 1
        for j < n && nums[j] == nums[i] {
            j++
        }
        m := j - i
        for h := 1; h <= m; h++ {
            if int64(nums[i])*int64(h)%int64(k) == 0 {
                ans -= int64(m - h)
            }
        }
        i = j
    }
    return
}
```

#### TypeScript

```ts
function numGoodSubarrays(nums: number[], k: number): number {
    let ans = 0;
    let s = 0;
    const cnt = new Map<number, number>();
    cnt.set(0, 1);

    for (const x of nums) {
        s = (s + x) % k;
        ans += cnt.get(s) ?? 0;
        cnt.set(s, (cnt.get(s) ?? 0) + 1);
    }

    const n = nums.length;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && nums[j] === nums[i]) ++j;
        const m = j - i;
        for (let h = 1; h <= m; ++h) {
            if ((nums[i] * h) % k === 0) {
                ans -= m - h;
            }
        }
        i = j;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
