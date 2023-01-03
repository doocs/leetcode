# [1802. Maximum Value at a Given Index in a Bounded Array](https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array)

[中文文档](/solution/1800-1899/1802.Maximum%20Value%20at%20a%20Given%20Index%20in%20a%20Bounded%20Array/README.md)

## Description

<p>You are given three positive integers:&nbsp;<code>n</code>, <code>index</code>, and <code>maxSum</code>. You want to construct an array <code>nums</code> (<strong>0-indexed</strong>)<strong> </strong>that satisfies the following conditions:</p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>nums[i]</code> is a <strong>positive</strong> integer where <code>0 &lt;= i &lt; n</code>.</li>
	<li><code>abs(nums[i] - nums[i+1]) &lt;= 1</code> where <code>0 &lt;= i &lt; n-1</code>.</li>
	<li>The sum of all the elements of <code>nums</code> does not exceed <code>maxSum</code>.</li>
	<li><code>nums[index]</code> is <strong>maximized</strong>.</li>
</ul>

<p>Return <code>nums[index]</code><em> of the constructed array</em>.</p>

<p>Note that <code>abs(x)</code> equals <code>x</code> if <code>x &gt;= 0</code>, and <code>-x</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4, index = 2,  maxSum = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> nums = [1,2,<u><strong>2</strong></u>,1] is one array that satisfies all the conditions.
There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6, index = 1,  maxSum = 10
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= maxSum &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= index &lt; n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxValue(self, n: int, index: int, maxSum: int) -> int:
        def sum(x, cnt):
            return (x + x - cnt + 1) * cnt // 2 if x >= cnt else (x + 1) * x // 2 + cnt - x

        left, right = 1, maxSum
        while left < right:
            mid = (left + right + 1) >> 1
            if sum(mid - 1, index) + sum(mid, n - index) <= maxSum:
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

```java
class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private long sum(long x, int cnt) {
        return x >= cnt ? (x + x - cnt + 1) * cnt / 2 : (x + 1) * x / 2 + cnt - x;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxValue(int n, int index, int maxSum) {
        auto sum = [](long x, int cnt) {
            return x >= cnt ? (x + x - cnt + 1) * cnt / 2 : (x + 1) * x / 2 + cnt - x;
        };
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func maxValue(n int, index int, maxSum int) int {
	sum := func(x, cnt int) int {
		if x >= cnt {
			return (x + x - cnt + 1) * cnt / 2
		}
		return (x+1)*x/2 + cnt - x
	}
	return sort.Search(maxSum, func(x int) bool {
		x++
		return sum(x-1, index)+sum(x, n-index) > maxSum
	})
}
```

### **...**

```

```

<!-- tabs:end -->
