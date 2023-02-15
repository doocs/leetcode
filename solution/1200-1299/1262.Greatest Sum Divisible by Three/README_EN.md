# [1262. Greatest Sum Divisible by Three](https://leetcode.com/problems/greatest-sum-divisible-by-three)

[中文文档](/solution/1200-1299/1262.Greatest%20Sum%20Divisible%20by%20Three/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the <strong>maximum possible sum </strong>of elements of the array such that it is divisible by three</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,6,5,1,8]
<strong>Output:</strong> 18
<strong>Explanation:</strong> Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since 4 is not divisible by 3, do not pick any number.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,4]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSumDivThree(self, nums: List[int]) -> int:
        f = [0] * 3
        for x in nums:
            a, b, c = f[0] + x, f[1] + x, f[2] + x
            f[a % 3] = max(f[a % 3], a)
            f[b % 3] = max(f[b % 3], b)
            f[c % 3] = max(f[c % 3], c)
        return f[0]
```

### **Java**

```java
class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] f = new int[3];
        for (int x : nums) {
            int a = f[0] + x, b = f[1] + x, c = f[2] + x;
            f[a % 3] = Math.max(f[a % 3], a);
            f[b % 3] = Math.max(f[b % 3], b);
            f[c % 3] = Math.max(f[c % 3], c);
        }
        return f[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSumDivThree(vector<int>& nums) {
        int f[3]{};
        for (int x : nums) {
            int a = f[0] + x, b = f[1] + x, c = f[2] + x;
            f[a % 3] = max(f[a % 3], a);
            f[b % 3] = max(f[b % 3], b);
            f[c % 3] = max(f[c % 3], c);
        }
        return f[0];
    }
};
```

### **Go**

```go
func maxSumDivThree(nums []int) int {
	f := [3]int{}
	for _, x := range nums {
		a, b, c := f[0]+x, f[1]+x, f[2]+x
		f[a%3] = max(f[a%3], a)
		f[b%3] = max(f[b%3], b)
		f[c%3] = max(f[c%3], c)
	}
	return f[0]
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
