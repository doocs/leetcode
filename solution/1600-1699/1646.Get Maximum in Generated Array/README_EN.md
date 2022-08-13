# [1646. Get Maximum in Generated Array](https://leetcode.com/problems/get-maximum-in-generated-array)

[中文文档](/solution/1600-1699/1646.Get%20Maximum%20in%20Generated%20Array/README.md)

## Description

<p>You are given an integer <code>n</code>. A <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n + 1</code> is generated in the following way:</p>

<ul>
	<li><code>nums[0] = 0</code></li>
	<li><code>nums[1] = 1</code></li>
	<li><code>nums[2 * i] = nums[i]</code> when <code>2 &lt;= 2 * i &lt;= n</code></li>
	<li><code>nums[2 * i + 1] = nums[i] + nums[i + 1]</code> when <code>2 &lt;= 2 * i + 1 &lt;= n</code></li>
</ul>

<p>Return<strong> </strong><em>the <strong>maximum</strong> integer in the array </em><code>nums</code>​​​.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> 3
<strong>Explanation:</strong> According to the given rules:
  nums[0] = 0
  nums[1] = 1
  nums[(1 * 2) = 2] = nums[1] = 1
  nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
  nums[(2 * 2) = 4] = nums[2] = 1
  nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
  nums[(3 * 2) = 6] = nums[3] = 2
  nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
Hence, nums = [0,1,1,2,1,3,2,3], and the maximum is max(0,1,1,2,1,3,2,3) = 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> According to the given rules, nums = [0,1,1]. The maximum is max(0,1,1) = 1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> According to the given rules, nums = [0,1,1,2]. The maximum is max(0,1,1,2) = 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getMaximumGenerated(self, n: int) -> int:
        if n == 0:
            return 0
        nums = [0] * (n + 1)
        nums[1] = 1
        for i in range(2, n + 1):
            nums[i] = nums[i >> 1] if i % 2 == 0 else nums[i >> 1] + nums[(i >> 1) + 1]
        return max(nums)
```

### **Java**

```java
class Solution {
    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        for (int i = 2; i < n + 1; ++i) {
            nums[i] = i % 2 == 0 ? nums[i >> 1] : nums[i >> 1] + nums[(i >> 1) + 1];
        }
        return Arrays.stream(nums).max().getAsInt();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int getMaximumGenerated(int n) {
        if (n == 0) return 0;
        vector<int> ans(n + 1, 0);
        ans[1] = 1;
        for (int i = 2; i < n + 1; ++i)
            ans[i] = i % 2 == 0 ? ans[i >> 1] : ans[i >> 1] + ans[(i >> 1) + 1];
        return *max_element(ans.begin(), ans.end());
    }
};
```

### **Go**

```go
func getMaximumGenerated(n int) int {
	if n == 0 {
		return 0
	}
	nums := make([]int, n+1)
	nums[1] = 1
	for i := 2; i <= n; i++ {
		if i%2 == 0 {
			nums[i] = nums[i>>1]
		} else {
			nums[i] = nums[i>>1] + nums[(i>>1)+1]
		}
	}
	var ans int
	for _, num := range nums {
		ans = max(ans, num)
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
