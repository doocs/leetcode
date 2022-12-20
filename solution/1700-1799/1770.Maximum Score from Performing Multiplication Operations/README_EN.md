# [1770. Maximum Score from Performing Multiplication Operations](https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations)

[中文文档](/solution/1700-1799/1770.Maximum%20Score%20from%20Performing%20Multiplication%20Operations/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums</code> and <code>multipliers</code><strong> </strong>of size <code>n</code> and <code>m</code> respectively, where <code>n &gt;= m</code>.</p>

<p>You begin with a score of <code>0</code>. You want to perform <strong>exactly</strong> <code>m</code> operations. On the <code>i<sup>th</sup></code> operation (<strong>0-indexed</strong>) you will:</p>

<ul>
    <li>Choose one integer <code>x</code> from <strong>either the start or the end </strong>of the array <code>nums</code>.</li>
    <li>Add <code>multipliers[i] * x</code> to your score.
    <ul>
        <li>Note that <code>multipliers[0]</code> corresponds to the first operation, <code>multipliers[1]</code> to the second operation, and so on.</li>
    </ul>
    </li>
    <li>Remove <code>x</code> from <code>nums</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> score after performing </em><code>m</code> <em>operations.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], multipliers = [3,2,1]
<strong>Output:</strong> 14
<strong>Explanation:</strong>&nbsp;An optimal solution is as follows:
- Choose from the end, [1,2,<strong><u>3</u></strong>], adding 3 * 3 = 9 to the score.
- Choose from the end, [1,<strong><u>2</u></strong>], adding 2 * 2 = 4 to the score.
- Choose from the end, [<strong><u>1</u></strong>], adding 1 * 1 = 1 to the score.
The total score is 9 + 4 + 1 = 14.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
<strong>Output:</strong> 102
<strong>Explanation: </strong>An optimal solution is as follows:
- Choose from the start, [<u><strong>-5</strong></u>,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
- Choose from the start, [<strong><u>-3</u></strong>,-3,-2,7,1], adding -3 * -5 = 15 to the score.
- Choose from the start, [<strong><u>-3</u></strong>,-2,7,1], adding -3 * 3 = -9 to the score.
- Choose from the end, [-2,7,<strong><u>1</u></strong>], adding 1 * 4 = 4 to the score.
- Choose from the end, [-2,<strong><u>7</u></strong>], adding 7 * 6 = 42 to the score. 
The total score is 50 + 15 - 9 + 4 + 42 = 102.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == multipliers.length</code></li>
	<li><code>1 &lt;= m &lt;= 300</code></li>
	<li><code>m &lt;= n &lt;= 10<sup>5</sup></code><code> </code></li>
	<li><code>-1000 &lt;= nums[i], multipliers[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumScore(self, nums: List[int], multipliers: List[int]) -> int:
        @cache
        def f(i, j, k):
            if k >= m or i >= n or j < 0:
                return 0
            a = f(i + 1, j, k + 1) + nums[i] * multipliers[k]
            b = f(i, j - 1, k + 1) + nums[j] * multipliers[k]
            return max(a, b)

        n = len(nums)
        m = len(multipliers)
        return f(0, n - 1, 0)
```

### **Java**

```java
class Solution {
    private Integer[][] f;
    private int[] multipliers;
    private int[] nums;
    private int n;
    private int m;

    public int maximumScore(int[] nums, int[] multipliers) {
        n = nums.length;
        m = multipliers.length;
        f = new Integer[m][m];
        this.nums = nums;
        this.multipliers = multipliers;
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m || j >= m || (i + j) >= m) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int k = i + j;
        int a = dfs(i + 1, j) + nums[i] * multipliers[k];
        int b = dfs(i, j + 1) + nums[n - 1 - j] * multipliers[k];
        f[i][j] = Math.max(a, b);
        return f[i][j];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumScore(vector<int>& nums, vector<int>& multipliers) {
        int n = nums.size(), m = multipliers.size();
        int f[m][m];
        memset(f, 0x3f, sizeof f);
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= m || j >= m || (i + j) >= m) return 0;
            if (f[i][j] != 0x3f3f3f3f) return f[i][j];
            int k = i + j;
            int a = dfs(i + 1, j) + nums[i] * multipliers[k];
            int b = dfs(i, j + 1) + nums[n - j - 1] * multipliers[k];
            return f[i][j] = max(a, b);
        };
        return dfs(0, 0);
    }
};
```

### **Go**

```go
func maximumScore(nums []int, multipliers []int) int {
	n, m := len(nums), len(multipliers)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = 1 << 30
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m || j >= m || i+j >= m {
			return 0
		}
		if f[i][j] != 1<<30 {
			return f[i][j]
		}
		k := i + j
		a := dfs(i+1, j) + nums[i]*multipliers[k]
		b := dfs(i, j+1) + nums[n-j-1]*multipliers[k]
		f[i][j] = max(a, b)
		return f[i][j]
	}
	return dfs(0, 0)
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
