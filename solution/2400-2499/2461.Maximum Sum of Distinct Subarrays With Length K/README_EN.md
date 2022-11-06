# [2461. Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k)

[中文文档](/solution/2400-2499/2461.Maximum%20Sum%20of%20Distinct%20Subarrays%20With%20Length%20K/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. Find the maximum subarray sum of all the subarrays of <code>nums</code> that meet the following conditions:</p>

<ul>
	<li>The length of the subarray is <code>k</code>, and</li>
	<li>All the elements of the subarray are <strong>distinct</strong>.</li>
</ul>

<p>Return <em>the maximum subarray sum of all the subarrays that meet the conditions</em><em>.</em> If no subarray meets the conditions, return <code>0</code>.</p>

<p><em>A <strong>subarray</strong> is a contiguous non-empty sequence of elements within an array.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,4,2,9,9,9], k = 3
<strong>Output:</strong> 15
<strong>Explanation:</strong> The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,4,4], k = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        n = len(nums)
        cnt = Counter(nums[:k])
        s = sum(nums[:k])
        ans = 0
        for i in range(k, n):
            if len(cnt) == k:
                ans = max(ans, s)
            cnt[nums[i]] += 1
            cnt[nums[i - k]] -= 1
            s += nums[i]
            s -= nums[i - k]
            if cnt[nums[i - k]] == 0:
                cnt.pop(nums[i - k])
        if len(cnt) == k:
            ans = max(ans, s)
        return ans
```

### **Java**

```java
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> cnt = new HashMap<>(k);
        long s = 0;
        for (int i = 0; i < k; ++i) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
            s += nums[i];
        }
        long ans = 0;
        for (int i = k; i < n; ++i) {
            if (cnt.size() == k) {
                ans = Math.max(ans, s);
            }
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
            cnt.put(nums[i - k], cnt.getOrDefault(nums[i - k], 0) - 1);
            if (cnt.get(nums[i - k]) == 0) {
                cnt.remove(nums[i - k]);
            }
            s += nums[i];
            s -= nums[i - k];
        }
        if (cnt.size() == k) {
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maximumSubarraySum(vector<int>& nums, int k) {
        int n = nums.size();
        unordered_map<int, int> cnt;
        long long s = 0, ans = 0;
        for (int i = 0; i < k; ++i) {
            cnt[nums[i]]++;
            s += nums[i];
        }
        for (int i = k; i < n; ++i) {
            if (cnt.size() == k) ans = max(ans, s);
            cnt[nums[i]]++;
            cnt[nums[i - k]]--;
            if (cnt[nums[i - k]] == 0) cnt.erase(nums[i - k]);
            s += nums[i];
            s -= nums[i - k];
        }
        if (cnt.size() == k) ans = max(ans, s);
        return ans;
    }
};
```

### **Go**

```go
func maximumSubarraySum(nums []int, k int) int64 {
	n := len(nums)
	cnt := map[int]int{}
	s, ans := 0, 0
	for i := 0; i < k; i++ {
		cnt[nums[i]]++
		s += nums[i]
	}
	for i := k; i < n; i++ {
		if len(cnt) == k {
			ans = max(ans, s)
		}
		cnt[nums[i]]++
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			delete(cnt, nums[i-k])
		}
		s += nums[i]
		s -= nums[i-k]
	}
	if len(cnt) == k {
		ans = max(ans, s)
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
