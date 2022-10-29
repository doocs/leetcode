# [523. Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum)

[中文文档](/solution/0500-0599/0523.Continuous%20Subarray%20Sum/README.md)

## Description

<p>Given an integer array nums and an integer k, return <code>true</code> <em>if </em><code>nums</code><em> has a <strong>good subarray</strong> or </em><code>false</code><em> otherwise</em>.</p>

<p>A <strong>good subarray</strong> is a subarray where:</p>

<ul>
	<li>its length is <strong>at least two</strong>, and</li>
	<li>the sum of the elements of the subarray is a multiple of <code>k</code>.</li>
</ul>

<p><strong>Note</strong> that:</p>

<ul>
	<li>A <strong>subarray</strong> is a contiguous part of the array.</li>
	<li>An integer <code>x</code> is a multiple of <code>k</code> if there exists an integer <code>n</code> such that <code>x = n * k</code>. <code>0</code> is <strong>always</strong> a multiple of <code>k</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [23,<u>2,4</u>,6,7], k = 6
<strong>Output:</strong> true
<strong>Explanation:</strong> [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [<u>23,2,6,4,7</u>], k = 6
<strong>Output:</strong> true
<strong>Explanation:</strong> [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [23,2,6,4,7], k = 13
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= sum(nums[i]) &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= k &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        s = 0
        mp = {0: -1}
        for i, v in enumerate(nums):
            s += v
            r = s % k
            if r in mp and i - mp[r] >= 2:
                return True
            if r not in mp:
                mp[r] = i
        return False
```

### **Java**

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            int r = s % k;
            if (mp.containsKey(r) && i - mp.get(r) >= 2) {
                return true;
            }
            if (!mp.containsKey(r)) {
                mp.put(r, i);
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        mp[0] = -1;
        int s = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i];
            int r = s % k;
            if (mp.count(r) && i - mp[r] >= 2) return true;
            if (!mp.count(r)) mp[r] = i;
        }
        return false;
    }
};
```

### **Go**

```go
func checkSubarraySum(nums []int, k int) bool {
	mp := map[int]int{0: -1}
	s := 0
	for i, v := range nums {
		s += v
		r := s % k
		if j, ok := mp[r]; ok && i-j >= 2 {
			return true
		}
		if _, ok := mp[r]; !ok {
			mp[r] = i
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
