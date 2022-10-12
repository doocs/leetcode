# [1673. Find the Most Competitive Subsequence](https://leetcode.com/problems/find-the-most-competitive-subsequence)

[中文文档](/solution/1600-1699/1673.Find%20the%20Most%20Competitive%20Subsequence/README.md)

## Description

<p>Given an integer array <code>nums</code> and a positive integer <code>k</code>, return <em>the most<strong> competitive</strong> subsequence of </em><code>nums</code> <em>of size </em><code>k</code>.</p>

<p>An array&#39;s subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.</p>

<p>We define that a subsequence <code>a</code> is more <strong>competitive</strong> than a subsequence <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, subsequence <code>a</code> has a number <strong>less</strong> than the corresponding number in <code>b</code>. For example, <code>[1,3,4]</code> is more competitive than <code>[1,3,5]</code> because the first position they differ is at the final number, and <code>4</code> is less than <code>5</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5,2,6], k = 2
<strong>Output:</strong> [2,6]
<strong>Explanation:</strong> Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,4,3,3,5,4,9,6], k = 4
<strong>Output:</strong> [2,3,3,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def mostCompetitive(self, nums: List[int], k: int) -> List[int]:
        stk = []
        n = len(nums)
        for i, v in enumerate(nums):
            while stk and stk[-1] > v and len(stk) + n - i > k:
                stk.pop()
            if len(stk) < k:
                stk.append(v)
        return stk
```

### **Java**

```java
class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = nums.length;
        for (int i = 0; i < nums.length; ++i) {
            while (!stk.isEmpty() && stk.peek() > nums[i] && stk.size() + n - i > k) {
                stk.pop();
            }
            if (stk.size() < k) {
                stk.push(nums[i]);
            }
        }
        int[] ans = new int[stk.size()];
        for (int i = ans.length - 1; i >= 0; --i) {
            ans[i] = stk.pop();
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> mostCompetitive(vector<int>& nums, int k) {
        vector<int> stk;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            while (stk.size() && stk.back() > nums[i] && stk.size() + n - i > k) {
                stk.pop_back();
            }
            if (stk.size() < k) {
                stk.push_back(nums[i]);
            }
        }
        return stk;
    }
};
```

### **Go**

```go
func mostCompetitive(nums []int, k int) []int {
	stk := []int{}
	n := len(nums)
	for i, v := range nums {
		for len(stk) > 0 && stk[len(stk)-1] > v && len(stk)+n-i > k {
			stk = stk[:len(stk)-1]
		}
		if len(stk) < k {
			stk = append(stk, v)
		}
	}
	return stk
}
```

### **...**

```

```

<!-- tabs:end -->
