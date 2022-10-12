# [2219. Maximum Sum Score of Array](https://leetcode.com/problems/maximum-sum-score-of-array)

[中文文档](/solution/2200-2299/2219.Maximum%20Sum%20Score%20of%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>.</p>

<p>The <strong>sum </strong><strong>score</strong> of <code>nums</code> at an index <code>i</code> where <code>0 &lt;= i &lt; n</code> is the <strong>maximum</strong> of:</p>

<ul>
	<li>The sum of the <strong>first</strong> <code>i + 1</code> elements of <code>nums</code>.</li>
	<li>The sum of the <strong>last</strong> <code>n - i</code> elements of <code>nums</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> <strong>sum </strong><strong>score</strong> of </em><code>nums</code><em> at any index.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,-2,5]
<strong>Output:</strong> 10
<strong>Explanation:</strong>
The sum score at index 0 is max(4, 4 + 3 + -2 + 5) = max(4, 10) = 10.
The sum score at index 1 is max(4 + 3, 3 + -2 + 5) = max(7, 6) = 7.
The sum score at index 2 is max(4 + 3 + -2, -2 + 5) = max(5, 3) = 5.
The sum score at index 3 is max(4 + 3 + -2 + 5, 5) = max(10, 5) = 10.
The maximum sum score of nums is 10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-3,-5]
<strong>Output:</strong> -3
<strong>Explanation:</strong>
The sum score at index 0 is max(-3, -3 + -5) = max(-3, -8) = -3.
The sum score at index 1 is max(-3 + -5, -5) = max(-8, -5) = -5.
The maximum sum score of nums is -3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumSumScore(self, nums: List[int]) -> int:
        s = [0] + list(accumulate(nums))
        return max(max(s[i + 1], s[-1] - s[i]) for i in range(len(nums)))
```

### **Java**

```java
class Solution {
    public long maximumSumScore(int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, Math.max(s[i + 1], s[n] - s[i]));
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maximumSumScore(nums: number[]): number {
    const n = nums.length;
    let s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = -Infinity;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, Math.max(s[i + 1], s[n] - s[i]));
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    long long maximumSumScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        long long ans = INT_MIN;
        for (int i = 0; i < n; ++i) ans = max(ans, max(s[i + 1], s[n] - s[i]));
        return ans;
    }
};
```

### **Go**

```go
func maximumSumScore(nums []int) int64 {
	n := len(nums)
	s := make([]int64, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + int64(v)
	}
	var ans int64 = math.MinInt64
	for i := 0; i < n; i++ {
		ans = max(ans, max(s[i+1], s[n]-s[i]))
	}
	return ans
}

func max(a, b int64) int64 {
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
var maximumSumScore = function (nums) {
    const n = nums.length;
    let s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = -Infinity;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, Math.max(s[i + 1], s[n] - s[i]));
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
