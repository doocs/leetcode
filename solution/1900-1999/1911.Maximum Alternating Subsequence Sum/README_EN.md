# [1911. Maximum Alternating Subsequence Sum](https://leetcode.com/problems/maximum-alternating-subsequence-sum)

[中文文档](/solution/1900-1999/1911.Maximum%20Alternating%20Subsequence%20Sum/README.md)

## Description

<p>The <strong>alternating sum</strong> of a <strong>0-indexed</strong> array is defined as the <strong>sum</strong> of the elements at <strong>even</strong> indices <strong>minus</strong> the <strong>sum</strong> of the elements at <strong>odd</strong> indices.</p>

<ul>
    <li>For example, the alternating sum of <code>[4,2,5,3]</code> is <code>(4 + 5) - (2 + 3) = 4</code>.</li>
</ul>

<p>Given an array <code>nums</code>, return <em>the <strong>maximum alternating sum</strong> of any subsequence of </em><code>nums</code><em> (after <strong>reindexing</strong> the elements of the subsequence)</em>.</p>

<ul>

</ul>

<p>A <strong>subsequence</strong> of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements&#39; relative order. For example, <code>[2,7,4]</code> is a subsequence of <code>[4,<u>2</u>,3,<u>7</u>,2,1,<u>4</u>]</code> (the underlined elements), while <code>[2,4,2]</code> is not.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [<u>4</u>,<u>2</u>,<u>5</u>,3]

<strong>Output:</strong> 7

<strong>Explanation:</strong> It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [5,6,7,<u>8</u>]

<strong>Output:</strong> 8

<strong>Explanation:</strong> It is optimal to choose the subsequence [8] with alternating sum 8.

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> nums = [<u>6</u>,2,<u>1</u>,2,4,<u>5</u>]

<strong>Output:</strong> 10

<strong>Explanation:</strong> It is optimal to choose the subsequence [6,1,5] with alternating sum (6 + 5) - 1 = 10.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
    <li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        n = len(nums)
        f = [0] * (n + 1)
        g = [0] * (n + 1)
        for i, x in enumerate(nums, 1):
            f[i] = max(g[i - 1] - x, f[i - 1])
            g[i] = max(f[i - 1] + x, g[i - 1])
        return max(f[n], g[n])
```

```python
class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        f = g = 0
        for x in nums:
            f, g = max(g - x, f), max(f + x, g)
        return max(f, g)
```

### **Java**

```java
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] f = new long[n + 1];
        long[] g = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            f[i] = Math.max(g[i - 1] - nums[i - 1], f[i - 1]);
            g[i] = Math.max(f[i - 1] + nums[i - 1], g[i - 1]);
        }
        return Math.max(f[n], g[n]);
    }
}
```

```java
class Solution {
    public long maxAlternatingSum(int[] nums) {
        long f = 0, g = 0;
        for (int x : nums) {
            long ff = Math.max(g - x, f);
            long gg = Math.max(f + x, g);
            f = ff;
            g = gg;
        }
        return Math.max(f, g);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums) {
        int n = nums.size();
        vector<long long> f(n + 1), g(n + 1);
        for (int i = 1; i <= n; ++i) {
            f[i] = max(g[i - 1] - nums[i - 1], f[i - 1]);
            g[i] = max(f[i - 1] + nums[i - 1], g[i - 1]);
        }
        return max(f[n], g[n]);
    }
};
```

```cpp
class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums) {
        long long f = 0, g = 0;
        for (int& x : nums) {
            long ff = max(g - x, f), gg = max(f + x, g);
            f = ff, g = gg;
        }
        return max(f, g);
    }
};
```

### **Go**

```go
func maxAlternatingSum(nums []int) int64 {
	n := len(nums)
	f := make([]int, n+1)
	g := make([]int, n+1)
	for i, x := range nums {
		i++
		f[i] = max(g[i-1]-x, f[i-1])
		g[i] = max(f[i-1]+x, g[i-1])
	}
	return int64(max(f[n], g[n]))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxAlternatingSum(nums []int) int64 {
	var f, g int
	for _, x := range nums {
		f, g = max(g-x, f), max(f+x, g)
	}
	return int64(max(f, g))
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
