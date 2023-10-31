# [2735. Collecting Chocolates](https://leetcode.com/problems/collecting-chocolates)

[中文文档](/solution/2700-2799/2735.Collecting%20Chocolates/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of size <code>n</code> representing the cost of collecting different chocolates. The cost of collecting the chocolate at the index <code>i</code>&nbsp;is <code>nums[i]</code>. Each chocolate is of a different type, and initially, the chocolate at the index&nbsp;<code>i</code>&nbsp;is of <code>i<sup>th</sup></code> type.</p>

<p>In one operation, you can do the following with an incurred <strong>cost</strong> of <code>x</code>:</p>

<ul>
	<li>Simultaneously change the chocolate of <code>i<sup>th</sup></code> type to <code>((i + 1) mod n)<sup>th</sup></code> type for all chocolates.</li>
</ul>

<p>Return <em>the minimum cost to collect chocolates of all types, given that you can perform as many operations as you would like.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [20,1,15], x = 5
<strong>Output:</strong> 13
<strong>Explanation:</strong> Initially, the chocolate types are [0,1,2]. We will buy the 1<sup>st</sup>&nbsp;type of chocolate at a cost of 1.
Now, we will perform the operation at a cost of 5, and the types of chocolates will become [1,2,0]. We will buy the 2<sup>nd</sup><sup> </sup>type of chocolate at a cost of 1.
Now, we will again perform the operation at a cost of 5, and the chocolate types will become [2,0,1]. We will buy the 0<sup>th </sup>type of chocolate at a cost of 1. 
Thus, the total cost will become (1 + 5 + 1 + 5 + 1) = 13. We can prove that this is optimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], x = 4
<strong>Output:</strong> 6
<strong>Explanation:</strong> We will collect all three types of chocolates at their own price without performing any operations. Therefore, the total cost is 1 + 2 + 3 = 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCost(self, nums: List[int], x: int) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        for i, v in enumerate(nums):
            f[i][0] = v
            for j in range(1, n):
                f[i][j] = min(f[i][j - 1], nums[(i + j) % n])
        ans = inf
        for j in range(n):
            cost = sum(f[i][j] for i in range(n)) + x * j
            ans = min(ans, cost)
        return ans
```

### **Java**

```java
class Solution {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][0] = nums[i];
            for (int j = 1; j < n; ++j) {
                f[i][j] = Math.min(f[i][j - 1], nums[(i + j) % n]);
            }
        }
        long ans = 1L << 60;
        for (int j = 0; j < n; ++j) {
            long cost = 1L * j * x;
            for (int i = 0; i < n; ++i) {
                cost += f[i][j];
            }
            ans = Math.min(ans, cost);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minCost(vector<int>& nums, int x) {
        int n = nums.size();
        int f[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][0] = nums[i];
            for (int j = 1; j < n; ++j) {
                f[i][j] = min(f[i][j - 1], nums[(i + j) % n]);
            }
        }
        long long ans = 1LL << 60;
        for (int j = 0; j < n; ++j) {
            long long cost = 1LL * j * x;
            for (int i = 0; i < n; ++i) {
                cost += f[i][j];
            }
            ans = min(ans, cost);
        }
        return ans;
    }
};
```

### **Go**

```go
func minCost(nums []int, x int) int64 {
	n := len(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][0] = nums[i]
		for j := 1; j < n; j++ {
			f[i][j] = min(f[i][j-1], nums[(i+j)%n])
		}
	}
	ans := 1 << 60
	for j := 0; j < n; j++ {
		cost := x * j
		for i := range nums {
			cost += f[i][j]
		}
		ans = min(ans, cost)
	}
	return int64(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
