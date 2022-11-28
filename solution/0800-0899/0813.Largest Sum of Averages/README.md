# [813. 最大平均值和的分组](https://leetcode.cn/problems/largest-sum-of-averages)

[English Version](/solution/0800-0899/0813.Largest%20Sum%20of%20Averages/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。我们将给定的数组&nbsp;<code>nums</code>&nbsp;分成 <strong>最多</strong>&nbsp;<code>k</code>&nbsp;个相邻的非空子数组 。&nbsp;<strong>分数</strong> 由每个子数组内的平均值的总和构成。</p>

<p>注意我们必须使用 <code>nums</code> 数组中的每一个数进行分组，并且分数不一定需要是整数。</p>

<p>返回我们所能得到的最大 <strong>分数</strong> 是多少。答案误差在&nbsp;<code>10<sup>-6</sup></code>&nbsp;内被视为是正确的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [9,1,2,3,9], k = 3
<strong>输出:</strong> 20.00000
<strong>解释:</strong> 
nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20. 
我们也可以把 nums 分成[9, 1], [2], [3, 9]. 
这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4,5,6,7], k = 4
<strong>输出:</strong> 20.50000
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 记忆化搜索**

我们可以先预处理得到前缀和数组 $s$，方便快速得到子数组的和。

然后设计一个函数 $dfs(i, k)$，表示从数组下标 $i$ 开始，最多分成 $k$ 组的最大平均值和。答案为 $dfs(0, k)$。函数 $dfs(i, k)$ 的执行逻辑如下：

当 $i=n$ 时，表示已经遍历到数组末尾，此时返回 $0$。

当 $k=1$ 时，表示只剩下一组，此时返回从下标 $i$ 开始到数组末尾的平均值。

否则，我们在 $[i, ..n-1]$ 的范围内枚举分组的结束位置 $j$，计算从下标 $i$ 到下标 $j$ 的平均值，以及从下标 $j+1$ 开始，最多分成 $k-1$ 组的最大平均值和。取其中的最大值作为答案。

为了避免重复计算，我们可以用数组 $f$ 记忆化函数 $dfs(i, k)$ 的返回值。

时间复杂度 $O(n^2 \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 表示数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
