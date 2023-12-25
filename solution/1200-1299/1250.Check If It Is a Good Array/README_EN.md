# [1250. Check If It Is a Good Array](https://leetcode.com/problems/check-if-it-is-a-good-array)

[中文文档](/solution/1200-1299/1250.Check%20If%20It%20Is%20a%20Good%20Array/README.md)

## Description

<p>Given an array <code>nums</code> of&nbsp;positive integers. Your task is to select some subset of <code>nums</code>, multiply each element by an integer and add all these numbers.&nbsp;The array is said to be&nbsp;<strong>good&nbsp;</strong>if you can obtain a sum of&nbsp;<code>1</code>&nbsp;from the array by any possible subset and multiplicand.</p>

<p>Return&nbsp;<code>True</code>&nbsp;if the array is <strong>good&nbsp;</strong>otherwise&nbsp;return&nbsp;<code>False</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [12,5,7,23]
<strong>Output:</strong> true
<strong>Explanation:</strong> Pick numbers 5 and 7.
5*3 + 7*(-2) = 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [29,6,10]
<strong>Output:</strong> true
<strong>Explanation:</strong> Pick numbers 29, 6 and 10.
29*1 + 6*(-3) + 10*(-1) = 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,6]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^9</code></li>
</ul>

## Solutions

**Solution 1: Mathematics (Bézout's Identity)**

First, consider the situation where we select two numbers. If the selected numbers are $a$ and $b$, then according to the problem's requirements, we need to satisfy $a \times x + b \times y = 1$, where $x$ and $y$ are any integers.

According to Bézout's Identity, if $a$ and $b$ are coprime, then the above equation definitely has a solution. In fact, Bézout's Identity can also be extended to the case of multiple numbers. That is, if $a_1, a_2, \cdots, a_i$ are coprime, then $a_1 \times x_1 + a_2 \times x_2 + \cdots + a_i \times x_i = 1$ definitely has a solution, where $x_1, x_2, \cdots, x_i$ are any integers.

Therefore, we only need to determine whether there exist $i$ coprime numbers in the array `nums`. The necessary and sufficient condition for two numbers to be coprime is that their greatest common divisor is $1$. If there exist $i$ coprime numbers in the array `nums`, then the greatest common divisor of all numbers in the array `nums` is also $1$.

So we transform the problem into: determining whether the greatest common divisor of all numbers in the array `nums` is $1$. We can do this by traversing the array `nums` and finding the greatest common divisor of all numbers in the array `nums`.

The time complexity is $O(n + \log m)$, and the space complexity is $O(1)$. Where $n$ is the length of the array `nums`, and $m$ is the maximum value in the array `nums`.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isGoodArray(self, nums: List[int]) -> bool:
        return reduce(gcd, nums) == 1
```

### **Java**

```java
class Solution {
    public boolean isGoodArray(int[] nums) {
        int g = 0;
        for (int x : nums) {
            g = gcd(x, g);
        }
        return g == 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isGoodArray(vector<int>& nums) {
        int g = 0;
        for (int x : nums) {
            g = gcd(x, g);
        }
        return g == 1;
    }
};
```

### **Go**

```go
func isGoodArray(nums []int) bool {
	g := 0
	for _, x := range nums {
		g = gcd(x, g)
	}
	return g == 1
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
