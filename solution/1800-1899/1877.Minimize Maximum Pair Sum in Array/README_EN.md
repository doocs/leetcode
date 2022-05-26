# [1877. Minimize Maximum Pair Sum in Array](https://leetcode.com/problems/minimize-maximum-pair-sum-in-array)

[中文文档](/solution/1800-1899/1877.Minimize%20Maximum%20Pair%20Sum%20in%20Array/README.md)

## Description

<p>The <strong>pair sum</strong> of a pair <code>(a,b)</code> is equal to <code>a + b</code>. The <strong>maximum pair sum</strong> is the largest <strong>pair sum</strong> in a list of pairs.</p>

<ul>
    <li>For example, if we have pairs <code>(1,5)</code>, <code>(2,3)</code>, and <code>(4,4)</code>, the <strong>maximum pair sum</strong> would be <code>max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8</code>.</li>
</ul>

<p>Given an array <code>nums</code> of <strong>even</strong> length <code>n</code>, pair up the elements of <code>nums</code> into <code>n / 2</code> pairs such that:</p>

<ul>
    <li>Each element of <code>nums</code> is in <strong>exactly one</strong> pair, and</li>
    <li>The <strong>maximum pair sum </strong>is <strong>minimized</strong>.</li>
</ul>

<p>Return <em>the minimized <strong>maximum pair sum</strong> after optimally pairing up the elements</em>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [3,5,2,3]

<strong>Output:</strong> 7

<strong>Explanation:</strong> The elements can be paired up into pairs (3,3) and (5,2).

The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [3,5,4,2,4,6]

<strong>Output:</strong> 8

<strong>Explanation:</strong> The elements can be paired up into pairs (3,5), (4,4), and (6,2).

The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>n == nums.length</code></li>
    <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
    <li><code>n</code> is <strong>even</strong>.</li>
    <li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

Sort & Greedy.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minPairSum(self, nums: List[int]) -> int:
        nums.sort()
        res, n = 0, len(nums)
        for i in range(n >> 1):
            res = max(res, nums[i] + nums[n - i - 1])
        return res
```

### **Java**

```java
class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0, n = nums.length;
        for (int i = 0; i < (n >> 1); ++i) {
            res = Math.max(res, nums[i] + nums[n - i - 1]);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minPairSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int res = 0, n = nums.size();
        for (int i = 0; i < (n >> 1); ++i) {
            res = max(res, nums[i] + nums[n - i - 1]);
        }
        return res;
    }
};
```

### **Go**

```go
func minPairSum(nums []int) int {
	sort.Ints(nums)
	res, n := 0, len(nums)
	for i := 0; i < (n >> 1); i++ {
		res = max(res, nums[i]+nums[n-i-1])
	}
	return res
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
