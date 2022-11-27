# [813. Largest Sum of Averages](https://leetcode.com/problems/largest-sum-of-averages)

[中文文档](/solution/0800-0899/0813.Largest%20Sum%20of%20Averages/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. You can partition the array into <strong>at most</strong> <code>k</code> non-empty adjacent subarrays. The <strong>score</strong> of a partition is the sum of the averages of each subarray.</p>

<p>Note that the partition must use every integer in <code>nums</code>, and that the score is not necessarily an integer.</p>

<p>Return <em>the maximum <strong>score</strong> you can achieve of all the possible partitions</em>. Answers within <code>10<sup>-6</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,1,2,3,9], k = 3
<strong>Output:</strong> 20.00000
<strong>Explanation:</strong> 
The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7], k = 4
<strong>Output:</strong> 20.50000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        @cache
        def dfs(i, k):
            if i == n:
                return 0
            if k == 1:
                return (s[-1] - s[i]) / (n - i)
            ans = 0
            for j in range(i, n):
                t = (s[j + 1] - s[i]) / (j - i + 1) + dfs(j + 1, k - 1)
                ans = max(ans, t)
            return ans

        n = len(nums)
        s = list(accumulate(nums, initial=0))
        return dfs(0, k)
```

### **Java**

```java
class Solution {
    private Double[][] f;
    private int[] s;
    private int n;

    public double largestSumOfAverages(int[] nums, int k) {
        n = nums.length;
        s = new int[n + 1];
        f = new Double[n + 1][k + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        return dfs(0, k);
    }

    private double dfs(int i, int k) {
        if (i == n) {
            return 0;
        }
        if (k == 1) {
            return (s[n] - s[i]) * 1.0 / (n - i);
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        double ans = 0;
        for (int j = i; j < n; ++j) {
            double t = (s[j + 1] - s[i]) * 1.0 / (j - i + 1) + dfs(j + 1, k - 1);
            ans = Math.max(ans, t);
        }
        return f[i][k] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        int s[n + 1];
        double f[n][k + 1];
        s[0] = 0;
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        function<double(int, int)> dfs = [&](int i, int k) -> double {
            if (i == n) return 0;
            if (k == 1) return (s[n] - s[i]) * 1.0 / (n - i);
            if (f[i][k]) return f[i][k];
            double ans = 0;
            for (int j = i; j < n; ++j) {
                double t = (s[j + 1] - s[i]) * 1.0 / (j - i + 1) + dfs(j + 1, k - 1);
                ans = max(ans, t);
            }
            return f[i][k] = ans;
        };
        return dfs(0, k);
    }
};
```

### **Go**

```go
func largestSumOfAverages(nums []int, k int) float64 {
	n := len(nums)
	s := make([]int, n+1)
	f := [110][110]float64{}
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	var dfs func(i, k int) float64
	dfs = func(i, k int) float64 {
		if i == n {
			return 0
		}
		if k == 1 {
			return float64(s[n]-s[i]) / float64(n-i)
		}
		if f[i][k] > 0 {
			return f[i][k]
		}
		var ans float64
		for j := i; j < n; j++ {
			t := float64(s[j+1]-s[i])/float64(j-i+1) + dfs(j+1, k-1)
			ans = math.Max(ans, t)
		}
		f[i][k] = ans
		return ans
	}
	return dfs(0, k)
}
```

### **...**

```

```

<!-- tabs:end -->
