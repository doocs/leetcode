# [2815. Max Pair Sum in an Array](https://leetcode.com/problems/max-pair-sum-in-an-array)

[中文文档](/solution/2800-2899/2815.Max%20Pair%20Sum%20in%20an%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. You have to find the <strong>maximum</strong> sum of a pair of numbers from <code>nums</code> such that the maximum <strong>digit </strong>in both numbers are equal.</p>

<p>Return <em>the maximum sum or</em> <code>-1</code><em> if no such pair exists</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [51,71,17,24,42]
<strong>Output:</strong> 88
<strong>Explanation:</strong> 
For i = 1 and j = 2, nums[i] and nums[j] have equal maximum digits with a pair sum of 71 + 17 = 88. 
For i = 3 and j = 4, nums[i] and nums[j] have equal maximum digits with a pair sum of 24 + 42 = 66.
It can be shown that there are no other pairs with equal maximum digits, so the answer is 88.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> No pair exists in nums with equal maximum digits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

**Solution 1: Enumeration**

First, we initialize the answer variable $ans=-1$. Next, we directly enumerate all pairs $(nums[i], nums[j])$ where $i \lt j$, and calculate their sum $v=nums[i] + nums[j]$. If $v$ is greater than $ans$ and the largest digit of $nums[i]$ and $nums[j]$ are the same, then we update $ans$ with $v$.

The time complexity is $O(n^2 \times \log M)$, where $n$ is the length of the array and $M$ is the maximum value in the array.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxSum(self, nums: List[int]) -> int:
        ans = -1
        for i, x in enumerate(nums):
            for y in nums[i + 1 :]:
                v = x + y
                if ans < v and max(str(x)) == max(str(y)):
                    ans = v
        return ans
```

### **Java**

```java
class Solution {
    public int maxSum(int[] nums) {
        int ans = -1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int v = nums[i] + nums[j];
                if (ans < v && f(nums[i]) == f(nums[j])) {
                    ans = v;
                }
            }
        }
        return ans;
    }

    private int f(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = Math.max(y, x % 10);
        }
        return y;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSum(vector<int>& nums) {
        int ans = -1;
        int n = nums.size();
        auto f = [](int x) {
            int y = 0;
            for (; x; x /= 10) {
                y = max(y, x % 10);
            }
            return y;
        };
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int v = nums[i] + nums[j];
                if (ans < v && f(nums[i]) == f(nums[j])) {
                    ans = v;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSum(nums []int) int {
	ans := -1
	f := func(x int) int {
		y := 0
		for ; x > 0; x /= 10 {
			y = max(y, x%10)
		}
		return y
	}
	for i, x := range nums {
		for _, y := range nums[i+1:] {
			if v := x + y; ans < v && f(x) == f(y) {
				ans = v
			}
		}
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

### **TypeScript**

```ts
function maxSum(nums: number[]): number {
    const n = nums.length;
    let ans = -1;
    const f = (x: number): number => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = Math.max(y, x % 10);
        }
        return y;
    };
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            const v = nums[i] + nums[j];
            if (ans < v && f(nums[i]) === f(nums[j])) {
                ans = v;
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
