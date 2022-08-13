# [1546. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target](https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target)

[中文文档](/solution/1500-1599/1546.Maximum%20Number%20of%20Non-Overlapping%20Subarrays%20With%20Sum%20Equals%20Target/README.md)

## Description

<p>Given an array <code>nums</code> and an integer <code>target</code>, return <em>the maximum number of <strong>non-empty</strong> <strong>non-overlapping</strong> subarrays such that the sum of values in each subarray is equal to</em> <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1], target = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 non-overlapping subarrays [<strong>1,1</strong>,1,<strong>1,1</strong>] with sum equals to target(2).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,3,5,1,4,2,-9], target = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 3 subarrays with sum equal to 6.
([5,1], [4,2], [3,5,1,4,2,-9]) but only the first 2 are non-overlapping.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= target &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxNonOverlapping(self, nums: List[int], target: int) -> int:
        i, n = 0, len(nums)
        ans = 0
        while i < n:
            s = 0
            seen = {0}
            while i < n:
                s += nums[i]
                if s - target in seen:
                    ans += 1
                    break
                i += 1
                seen.add(s)
            i += 1
        return ans
```

### **Java**

```java
class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        int i = 0, n = nums.length;
        int ans = 0;
        while (i < n) {
            int s = 0;
            Set<Integer> seen = new HashSet<>();
            seen.add(0);
            while (i < n) {
                s += nums[i];
                if (seen.contains(s - target)) {
                    ++ans;
                    break;
                }
                ++i;
                seen.add(s);
            }
            ++i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxNonOverlapping(vector<int>& nums, int target) {
        int i = 0, n = nums.size();
        int ans = 0;
        while (i < n) {
            int s = 0;
            unordered_set<int> seen;
            seen.insert(0);
            while (i < n) {
                s += nums[i];
                if (seen.count(s - target)) {
                    ++ans;
                    break;
                }
                ++i;
                seen.insert(s);
            }
            ++i;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxNonOverlapping(nums []int, target int) int {
	i, n, ans := 0, len(nums), 0
	for i < n {
		s := 0
		seen := map[int]bool{0: true}
		for i < n {
			s += nums[i]
			if seen[s-target] {
				ans++
				break
			}
			seen[s] = true
			i++
		}
		i++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
