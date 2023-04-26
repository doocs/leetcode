# [16.17. Contiguous Sequence](https://leetcode.cn/problems/contiguous-sequence-lcci)

[中文文档](/lcci/16.17.Contiguous%20Sequence/README.md)

## Description

<p>You are given an array of integers (both positive and negative). Find the contiguous sequence with the largest sum. Return the sum.</p>

<p><strong>Example: </strong></p>

<pre>



<strong>Input: </strong> [-2,1,-3,4,-1,2,1,-5,4]



<strong>Output: </strong> 6



<strong>Explanation: </strong> [4,-1,2,1] has the largest sum 6.



</pre>

<p><strong>Follow Up: </strong></p>

<p>If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * n
        dp[0] = nums[0]
        for i in range(1, n):
            dp[i] = max(dp[i - 1], 0) + nums[i]
        return max(dp)
```

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        ans = s = -inf
        for v in nums:
            s = max(s, 0) + v
            ans = max(ans, s)
        return ans
```

### **Java**

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < n; ++i) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int inf = Integer.MIN_VALUE;
        int ans = inf, s = inf;
        for (int v : nums) {
            s = Math.max(s, 0) + v;
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
    int maxSubArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n);
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < n; ++i) {
            dp[i] = max(dp[i - 1], 0) + nums[i];
            ans = max(ans, dp[i]);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int s = INT_MIN, ans = INT_MIN;
        for (int v : nums) {
            s = max(s, 0) + v;
            ans = max(ans, s);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSubArray(nums []int) int {
	n := len(nums)
	dp := make([]int, n)
	dp[0] = nums[0]
	ans := dp[0]
	for i := 1; i < n; i++ {
		dp[i] = max(dp[i-1], 0) + nums[i]
		ans = max(ans, dp[i])
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

```go
func maxSubArray(nums []int) int {
	inf := math.MinInt32
	ans, s := inf, inf
	for _, v := range nums {
		s = max(s, 0) + v
		ans = max(ans, s)
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

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    const n = nums.length;
    const dp = new Array(n).fill(0);
    dp[0] = nums[0];
    let ans = dp[0];
    for (let i = 1; i < n; ++i) {
        dp[i] = Math.max(dp[i - 1], 0) + nums[i];
        ans = Math.max(ans, dp[i]);
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    const inf = -Infinity;
    let s = inf;
    let ans = inf;
    for (const v of nums) {
        s = Math.max(s, 0) + v;
        ans = Math.max(ans, s);
    }
    return ans;
};
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function maxSubArray($nums) {
        $pre = 0;
        $max = $nums[0];
        for ($i = 0; $i < count($nums); $i++) {
            $pre = max($pre + $nums[$i], $nums[$i]);
            $max = max($pre, $max);
        }
        return $max;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
