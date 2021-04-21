# [213. House Robber II](https://leetcode.com/problems/house-robber-ii)

[中文文档](/solution/0200-0299/0213.House%20Robber%20II/README.md)

## Description

<p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are <strong>arranged in a circle.</strong> That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and&nbsp;<b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>

<p>Given an integer array <code>nums</code> representing the amount of money of each house, return <em>the maximum amount of money you can rob tonight <strong>without alerting the police</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        def robRange(nums, start, end):
            if end - start == 0:
                return nums[start]
            pre, cur = 0, nums[start]
            for i in range(start + 1, end + 1):
                pre, cur = cur, max(pre + nums[i], cur)
            return cur
        n = len(nums)
        if n == 1:
            return nums[0]
        s1, s2 = robRange(nums, 0, n - 2), robRange(nums, 1, n - 1)
        return max(s1, s2)
```

### **Java**

```java
class Solution {
    public int rob(int[] nums) {
        int n;
        if ((n = nums.length) == 1) return nums[0];
        int s1 = robRange(nums, 0, n - 2);
        int s2 = robRange(nums, 1, n - 1);
        return Math.max(s1, s2);
    }

    private int robRange(int[] nums, int start, int end) {
        if (end - start == 0) return nums[start];
        int pre = 0;
        int cur = nums[start];
        for (int i = start + 1; i < end + 1; ++i) {
            int t = Math.max(pre + nums[i], cur);
            pre = cur;
            cur = t;
        }
        return cur;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];
        int s1 = robRange(nums, 0, n - 2);
        int s2 = robRange(nums, 1, n - 1);
        return max(s1, s2);
    }
private:
    int robRange(vector<int>& nums, int start, int end) {
        if (end - start == 0) return nums[start];
        int pre = 0;
        int cur = nums[start];
        for (int i = start + 1; i < end + 1; ++i) {
            int t = max(pre + nums[i], cur);
            pre = cur;
            cur = t;
        }
        return cur;
    }
};
```

### **Go**

```go
func rob(nums []int) int {
    n := len(nums)
    if n == 1 {
        return nums[0]
    }
    s1, s2 := robRange(nums, 0, n - 2), robRange(nums, 1, n - 1)
    return max(s1, s2)
}

func robRange(nums[]int, start int, end int) int {
    if end - start == 0 {
        return nums[start]
    }
    pre, cur := 0, nums[start]
    for i := start + 1; i < end + 1; i++ {
        pre, cur = cur, max(pre + nums[i], cur)
    }
    return cur
}

func max(a, b int) int {
    if (a > b) {
        return a
    }
    return b
}
```

### **...**

```

```

<!-- tabs:end -->
