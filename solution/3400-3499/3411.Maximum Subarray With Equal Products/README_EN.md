---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3411.Maximum%20Subarray%20With%20Equal%20Products/README_EN.md
---

<!-- problem:start -->

# [3411. Maximum Subarray With Equal Products](https://leetcode.com/problems/maximum-subarray-with-equal-products)

[中文文档](/solution/3400-3499/3411.Maximum%20Subarray%20With%20Equal%20Products/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>An array <code>arr</code> is called <strong>product equivalent</strong> if <code>prod(arr) == lcm(arr) * gcd(arr)</code>, where:</p>

<ul>
	<li><code>prod(arr)</code> is the product of all elements of <code>arr</code>.</li>
	<li><code>gcd(arr)</code> is the GCD of all elements of <code>arr</code>.</li>
	<li><code>lcm(arr)</code> is the LCM of all elements of <code>arr</code>.</li>
</ul>

<p>Return the length of the <strong>longest</strong> <strong>product equivalent</strong> subarray of <code>nums</code>.</p>

<p>A <strong>subarray</strong> is a contiguous <b>non-empty</b> sequence of elements within an array.</p>

<p>The term <code>gcd(a, b)</code> denotes the <strong>greatest common divisor</strong> of <code>a</code> and <code>b</code>.</p>

<p>The term <code>lcm(a, b)</code> denotes the <strong>least common multiple</strong> of <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,2,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>The longest product equivalent subarray is <code>[1, 2, 1, 1, 1]</code>, where&nbsp;<code>prod([1, 2, 1, 1, 1]) = 2</code>,&nbsp;<code>gcd([1, 2, 1, 1, 1]) = 1</code>, and&nbsp;<code>lcm([1, 2, 1, 1, 1]) = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,4,5,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>The longest product equivalent subarray is <code>[3, 4, 5].</code></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,1,4,5,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxLength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        max_p = lcm(*nums) * max(nums)
        for i in range(n):
            p, g, l = 1, 0, 1
            for j in range(i, n):
                p *= nums[j]
                g = gcd(g, nums[j])
                l = lcm(l, nums[j])
                if p == g * l:
                    ans = max(ans, j - i + 1)
                if p > max_p:
                    break
        return ans
```

#### Java

```java
class Solution {
    public int maxLength(int[] nums) {
        int mx = 0, ml = 1;
        for (int x : nums) {
            mx = Math.max(mx, x);
            ml = lcm(ml, x);
        }
        int maxP = ml * mx;
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int p = 1, g = 0, l = 1;
            for (int j = i; j < n; ++j) {
                p *= nums[j];
                g = gcd(g, nums[j]);
                l = lcm(l, nums[j]);
                if (p == g * l) {
                    ans = Math.max(ans, j - i + 1);
                }
                if (p > maxP) {
                    break;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxLength(vector<int>& nums) {
        int mx = 0, ml = 1;
        for (int x : nums) {
            mx = max(mx, x);
            ml = lcm(ml, x);
        }

        long long maxP = (long long) ml * mx;
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            long long p = 1, g = 0, l = 1;
            for (int j = i; j < n; ++j) {
                p *= nums[j];
                g = gcd(g, nums[j]);
                l = lcm(l, nums[j]);

                if (p == g * l) {
                    ans = max(ans, j - i + 1);
                }
                if (p > maxP) {
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxLength(nums []int) int {
	mx, ml := 0, 1
	for _, x := range nums {
		mx = max(mx, x)
		ml = lcm(ml, x)
	}
	maxP := ml * mx
	n := len(nums)
	ans := 0
	for i := 0; i < n; i++ {
		p, g, l := 1, 0, 1
		for j := i; j < n; j++ {
			p *= nums[j]
			g = gcd(g, nums[j])
			l = lcm(l, nums[j])
			if p == g*l {
				ans = max(ans, j-i+1)
			}
			if p > maxP {
				break
			}
		}
	}
	return ans
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}

func lcm(a, b int) int {
	return a / gcd(a, b) * b
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
