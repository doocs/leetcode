# [325. Maximum Size Subarray Sum Equals k](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k)

[中文文档](/solution/0300-0399/0325.Maximum%20Size%20Subarray%20Sum%20Equals%20k/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the maximum length of a subarray that sums to</em> <code>k</code>. If there is not one, return <code>0</code> instead.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-1,5,-2,3], k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The subarray [1, -1, 5, -2] sums to 3 and is the longest.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,-1,2,1], k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The subarray [-1, 2] sums to 1 and is the longest.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSubArrayLen(self, nums: List[int], k: int) -> int:
        mp = {0: -1}
        s = ans = 0
        for i, v in enumerate(nums):
            s += v
            if s - k in mp:
                ans = max(ans, i - mp[s - k])
            if s not in mp:
                mp[s] = i
        return ans
```

### **Java**

```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int s = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            if (mp.containsKey(s - k)) {
                ans = Math.max(ans, i - mp.get(s - k));
            }
            if (!mp.containsKey(s)) {
                mp.put(s, i);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSubArrayLen(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        mp[0] = -1;
        int s = 0, ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i];
            if (mp.count(s - k)) ans = max(ans, i - mp[s - k]);
            if (!mp.count(s)) mp[s] = i;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSubArrayLen(nums []int, k int) int {
	mp := map[int]int{0: -1}
	s, ans := 0, 0
	for i, v := range nums {
		s += v
		if j, ok := mp[s-k]; ok {
			ans = max(ans, i-j)
		}
		if _, ok := mp[s]; !ok {
			mp[s] = i
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
