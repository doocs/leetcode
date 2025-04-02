---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1918.Kth%20Smallest%20Subarray%20Sum/README_EN.md
tags:
    - Array
    - Binary Search
    - Sliding Window
---

<!-- problem:start -->

# [1918. Kth Smallest Subarray Sum 🔒](https://leetcode.com/problems/kth-smallest-subarray-sum)

[中文文档](/solution/1900-1999/1918.Kth%20Smallest%20Subarray%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>, return <em>the </em><code>k<sup>th</sup></code> <em><strong>smallest subarray sum</strong>.</em></p>

<p>A <strong>subarray</strong> is defined as a <strong>non-empty</strong> contiguous sequence of elements in an array. A <strong>subarray sum</strong> is the sum of all elements in the subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,3], k = 4
<strong>Output:</strong> 3
<strong>Explanation: </strong>The subarrays of [2,1,3] are:
- [2] with sum 2
- [1] with sum 1
- [3] with sum 3
- [2,1] with sum 3
- [1,3] with sum 4
- [2,1,3] with sum 6 
Ordering the sums from smallest to largest gives 1, 2, 3, <u>3</u>, 4, 6. The 4th smallest is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,5,5], k = 7
<strong>Output:</strong> 10
<strong>Explanation: </strong>The subarrays of [3,3,5,5] are:
- [3] with sum 3
- [3] with sum 3
- [5] with sum 5
- [5] with sum 5
- [3,3] with sum 6
- [3,5] with sum 8
- [5,5] with sum 10
- [3,3,5], with sum 11
- [3,5,5] with sum 13
- [3,3,5,5] with sum 16
Ordering the sums from smallest to largest gives 3, 3, 5, 5, 6, 8, <u>10</u>, 11, 13, 16. The 7th smallest is 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= n * (n + 1) / 2</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search + Two Pointers

We observe that all elements in the array are positive integers. The larger the subarray sum $s$, the more subarrays there are with sums less than or equal to $s$. This monotonicity allows us to use binary search to solve the problem.

We perform binary search on the subarray sum, initializing the left and right boundaries as the minimum value in the array $\textit{nums}$ and the sum of all elements in the array, respectively. Each time, we calculate the number of subarrays with sums less than or equal to the current middle value. If the count is greater than or equal to $k$, it means the current middle value $s$ might be the $k$-th smallest subarray sum, so we shrink the right boundary. Otherwise, we increase the left boundary. After the binary search ends, the left boundary will be the $k$-th smallest subarray sum.

The problem reduces to calculating the number of subarrays in an array with sums less than or equal to $s$, which we can compute using a function $f(s)$.

The function $f(s)$ is calculated as follows:

-   Initialize two pointers $j$ and $i$, representing the left and right boundaries of the current window, with $j = i = 0$. Also, initialize the sum of elements in the window $t = 0$.
-   Use a variable $\textit{cnt}$ to record the number of subarrays with sums less than or equal to $s$, initially $\textit{cnt} = 0$.
-   Traverse the array $\textit{nums}$. For each element $\textit{nums}[i]$, add it to the window, i.e., $t = t + \textit{nums}[i]$. If $t > s$, move the left boundary of the window to the right until $t \leq s$, i.e., repeatedly execute $t -= \textit{nums}[j]$ and $j = j + 1$. Then update $\textit{cnt}$ as $\textit{cnt} = \textit{cnt} + i - j + 1$. Continue to the next element until the entire array is traversed.

Finally, return $cnt$ as the result of the function $f(s)$.

Time complexity is $O(n \times \log S)$, where $n$ is the length of the array $\textit{nums}$, and $S$ is the sum of all elements in the array $\textit{nums}$. Space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthSmallestSubarraySum(self, nums: List[int], k: int) -> int:
        def f(s):
            t = j = 0
            cnt = 0
            for i, x in enumerate(nums):
                t += x
                while t > s:
                    t -= nums[j]
                    j += 1
                cnt += i - j + 1
            return cnt >= k

        l, r = min(nums), sum(nums)
        return l + bisect_left(range(l, r + 1), True, key=f)
```

#### Java

```java
class Solution {
    public int kthSmallestSubarraySum(int[] nums, int k) {
        int l = 1 << 30, r = 0;
        for (int x : nums) {
            l = Math.min(l, x);
            r += x;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (f(nums, mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int f(int[] nums, int s) {
        int t = 0, j = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            t += nums[i];
            while (t > s) {
                t -= nums[j++];
            }
            cnt += i - j + 1;
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kthSmallestSubarraySum(vector<int>& nums, int k) {
        int l = 1 << 30, r = 0;
        for (int& x : nums) {
            l = min(l, x);
            r += x;
        }
        auto f = [&](int s) {
            int cnt = 0, t = 0;
            for (int i = 0, j = 0; i < nums.size(); ++i) {
                t += nums[i];
                while (t > s) {
                    t -= nums[j++];
                }
                cnt += i - j + 1;
            }
            return cnt;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (f(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

#### Go

```go
func kthSmallestSubarraySum(nums []int, k int) int {
	l, r := 1<<30, 0
	for _, x := range nums {
		l = min(l, x)
		r += x
	}
	f := func(s int) (cnt int) {
		t := 0
		for i, j := 0, 0; i < len(nums); i++ {
			t += nums[i]
			for t > s {
				t -= nums[j]
				j++
			}
			cnt += i - j + 1
		}
		return
	}
	for l < r {
		mid := (l + r) >> 1
		if f(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
```

#### Typescript

```ts
function kthSmallestSubarraySum(nums: number[], k: number): number {
    let l = Math.min(...nums);
    let r = nums.reduce((sum, x) => sum + x, 0);

    const f = (s: number): number => {
        let cnt = 0;
        let t = 0;
        let j = 0;

        for (let i = 0; i < nums.length; i++) {
            t += nums[i];
            while (t > s) {
                t -= nums[j];
                j++;
            }
            cnt += i - j + 1;
        }
        return cnt;
    };

    while (l < r) {
        const mid = (l + r) >> 1;
        if (f(mid) >= k) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

#### Rust

```rust
impl Solution {
    pub fn kth_smallest_subarray_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mut l = *nums.iter().min().unwrap();
        let mut r: i32 = nums.iter().sum();

        let f = |s: i32| -> i32 {
            let (mut cnt, mut t, mut j) = (0, 0, 0);

            for i in 0..nums.len() {
                t += nums[i];
                while t > s {
                    t -= nums[j];
                    j += 1;
                }
                cnt += (i - j + 1) as i32;
            }
            cnt
        };

        while l < r {
            let mid = (l + r) / 2;
            if f(mid) >= k {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
```

#### Scala

```scala
object Solution {
    def kthSmallestSubarraySum(nums: Array[Int], k: Int): Int = {
        var l = Int.MaxValue
        var r = 0

        for (x <- nums) {
            l = l.min(x)
            r += x
        }

        def f(s: Int): Int = {
            var cnt = 0
            var t = 0
            var j = 0

            for (i <- nums.indices) {
                t += nums(i)
                while (t > s) {
                    t -= nums(j)
                    j += 1
                }
                cnt += i - j + 1
            }
            cnt
        }

        while (l < r) {
            val mid = (l + r) / 2
            if (f(mid) >= k) r = mid
            else l = mid + 1
        }
        l
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
