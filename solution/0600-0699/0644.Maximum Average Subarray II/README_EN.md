---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0644.Maximum%20Average%20Subarray%20II/README_EN.md
tags:
    - Array
    - Binary Search
    - Prefix Sum
---

# [644. Maximum Average Subarray II 🔒](https://leetcode.com/problems/maximum-average-subarray-ii)

[中文文档](/solution/0600-0699/0644.Maximum%20Average%20Subarray%20II/README.md)

## Description

<p>You are given an integer array <code>nums</code> consisting of <code>n</code> elements, and an integer <code>k</code>.</p>

<p>Find a contiguous subarray whose <strong>length is greater than or equal to</strong> <code>k</code> that has the maximum average value and return <em>this value</em>. Any answer with a calculation error less than <code>10<sup>-5</sup></code> will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,12,-5,-6,50,3], k = 4
<strong>Output:</strong> 12.75000
<b>Explanation:
</b>- When the length is 4, averages are [0.5, 12.75, 10.5] and the maximum average is 12.75
- When the length is 5, averages are [10.4, 10.8] and the maximum average is 10.8
- When the length is 6, averages are [9.16667] and the maximum average is 9.16667
The maximum average is when we choose a subarray of length 4 (i.e., the sub array [12, -5, -6, 50]) which has the max average 12.75, so we return 12.75
Note that we do not consider the subarrays of length &lt; 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5], k = 1
<strong>Output:</strong> 5.00000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1: Binary Search

We note that if the average value of a subarray with length greater than or equal to $k$ is $v$, then the maximum average number must be greater than or equal to $v$, otherwise the maximum average number must be less than $v$. Therefore, we can use binary search to find the maximum average number.

What are the left and right boundaries of binary search? The left boundary $l$ must be the minimum value in the array, and the right boundary $r$ is the maximum value in the array. Next, we binary search the midpoint $mid$, and judge whether there exists a subarray with length greater than or equal to $k$ whose average value is greater than or equal to $mid$. If it exists, then we update the left boundary $l$ to $mid$, otherwise we update the right boundary $r$ to $mid$. When the difference between the left and right boundaries is less than a very small non-negative number, i.e., $r - l < \epsilon$, we can get the maximum average number, where $\epsilon$ represents a very small positive number, which can be $10^{-5}$.

The key to the problem is how to judge whether the average value of a subarray with length greater than or equal to $k$ is greater than or equal to $v$.

We assume that in the array $nums$, there is a subarray with length $j$, the elements are $a_1, a_2, \cdots, a_j$, and its average value is greater than or equal to $v$, i.e.,

$$
\frac{a_1 + a_2 + \cdots + a_j}{j} \geq v
$$

Then,

$$
a_1 + a_2 + \cdots + a_j \geq v \times j
$$

That is,

$$
(a_1 - v) + (a_2 - v) + \cdots + (a_j - v) \geq 0
$$

We can find that if we subtract $v$ from each element in the array $nums$, the original problem is transformed into a problem of whether the sum of the elements of a subarray with length greater than or equal to $k$ is greater than or equal to $0$. We can use a sliding window to solve this problem.

First, we calculate the sum $s$ of the differences between the first $k$ elements and $v$. If $s \geq 0$, it means that there exists a subarray with length greater than or equal to $k$ whose element sum is greater than or equal to $0$.

Otherwise, we continue to traverse the element $nums[j]$. Suppose the current sum of the differences between the first $j$ elements and $v$ is $s_j$. Then we can maintain the minimum value $mi$ of the sum of the differences between the prefix sum and $v$ in the range $[0,..j-k]$. If $s_j \geq mi$ exists, it means that there exists a subarray with length greater than or equal to $k$ whose element sum is greater than or equal to $0$, and we return $true$.

Otherwise, we continue to traverse the element $nums[j]$ until the entire array is traversed.

The time complexity is $O(n \times \log M)$, where $n$ and $M$ are the length of the array $nums$ and the difference between the maximum and minimum values in the array, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        def check(v: float) -> bool:
            s = sum(nums[:k]) - k * v
            if s >= 0:
                return True
            t = mi = 0
            for i in range(k, len(nums)):
                s += nums[i] - v
                t += nums[i - k] - v
                mi = min(mi, t)
                if s >= mi:
                    return True
            return False

        eps = 1e-5
        l, r = min(nums), max(nums)
        while r - l >= eps:
            mid = (l + r) / 2
            if check(mid):
                l = mid
            else:
                r = mid
        return l
```

```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double eps = 1e-5;
        double l = 1e10, r = -1e10;
        for (int x : nums) {
            l = Math.min(l, x);
            r = Math.max(r, x);
        }
        while (r - l >= eps) {
            double mid = (l + r) / 2;
            if (check(nums, k, mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private boolean check(int[] nums, int k, double v) {
        double s = 0;
        for (int i = 0; i < k; ++i) {
            s += nums[i] - v;
        }
        if (s >= 0) {
            return true;
        }
        double t = 0;
        double mi = 0;
        for (int i = k; i < nums.length; ++i) {
            s += nums[i] - v;
            t += nums[i - k] - v;
            mi = Math.min(mi, t);
            if (s >= mi) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    double findMaxAverage(vector<int>& nums, int k) {
        double eps = 1e-5;
        double l = *min_element(nums.begin(), nums.end());
        double r = *max_element(nums.begin(), nums.end());
        auto check = [&](double v) {
            double s = 0;
            for (int i = 0; i < k; ++i) {
                s += nums[i] - v;
            }
            if (s >= 0) {
                return true;
            }
            double t = 0;
            double mi = 0;
            for (int i = k; i < nums.size(); ++i) {
                s += nums[i] - v;
                t += nums[i - k] - v;
                mi = min(mi, t);
                if (s >= mi) {
                    return true;
                }
            }
            return false;
        };
        while (r - l >= eps) {
            double mid = (l + r) / 2;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }
};
```

```go
func findMaxAverage(nums []int, k int) float64 {
	eps := 1e-5
	l := float64(slices.Min(nums))
	r := float64(slices.Max(nums))
	check := func(v float64) bool {
		s := 0.0
		for _, x := range nums[:k] {
			s += float64(x) - v
		}
		if s >= 0 {
			return true
		}
		t := 0.0
		mi := 0.0
		for i := k; i < len(nums); i++ {
			s += float64(nums[i]) - v
			t += float64(nums[i-k]) - v
			mi = math.Min(mi, t)
			if s >= mi {
				return true
			}
		}
		return false
	}
	for r-l >= eps {
		mid := (l + r) / 2
		if check(mid) {
			l = mid
		} else {
			r = mid
		}
	}
	return l
}
```

```ts
function findMaxAverage(nums: number[], k: number): number {
    const eps = 1e-5;
    let l = Math.min(...nums);
    let r = Math.max(...nums);
    const check = (v: number): boolean => {
        let s = nums.slice(0, k).reduce((a, b) => a + b) - v * k;
        if (s >= 0) {
            return true;
        }
        let t = 0;
        let mi = 0;
        for (let i = k; i < nums.length; ++i) {
            s += nums[i] - v;
            t += nums[i - k] - v;
            mi = Math.min(mi, t);
            if (s >= mi) {
                return true;
            }
        }
        return false;
    };
    while (r - l >= eps) {
        const mid = (l + r) / 2;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- end -->
