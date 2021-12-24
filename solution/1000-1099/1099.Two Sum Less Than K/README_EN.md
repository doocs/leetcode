# [1099. Two Sum Less Than K](https://leetcode.com/problems/two-sum-less-than-k)

[中文文档](/solution/1000-1099/1099.Two%20Sum%20Less%20Than%20K/README.md)

## Description

<p>Given an array <code>nums</code> of integers and&nbsp;integer <code>k</code>, return the maximum <code>sum</code> such that there exists <code>i &lt; j</code> with <code>nums[i] + nums[j] = sum</code> and <code>sum &lt; k</code>. If no <code>i</code>, <code>j</code> exist satisfying this equation, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [34,23,1,24,75,33,54,8], k = 60
<strong>Output:</strong> 58
<strong>Explanation: </strong>We can use 34 and 24 to sum 58 which is less than 60.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,30], k = 15
<strong>Output:</strong> -1
<strong>Explanation: </strong>In this case it is not possible to get a pair sum less that 15.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 2000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        nums.sort()
        low, high = 0, len(nums) - 1
        res = -1
        while low < high:
            val = nums[low] + nums[high]
            if val < k:
                res = max(res, val)
                low += 1
            else:
                high -= 1
        return res
```

### **Java**

```java
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0, high = nums.length - 1;
        int res = -1;
        while (low < high) {
            int val = nums[low] + nums[high];
            if (val < k) {
                res = Math.max(res, val);
                ++low;
            } else {
                --high;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int low = 0, high = nums.size() - 1;
        int res = -1;
        while (low < high) {
            int val = nums[low] + nums[high];
            if (val < k) {
                res = max(res, val);
                ++low;
            } else {
                --high;
            }
        }
        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
