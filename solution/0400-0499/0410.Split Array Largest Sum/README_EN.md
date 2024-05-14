# [410. Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum)

[中文文档](/solution/0400-0499/0410.Split%20Array%20Largest%20Sum/README.md)

<!-- tags:Greedy,Array,Binary Search,Dynamic Programming,Prefix Sum -->

<!-- difficulty:Hard -->

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, split <code>nums</code> into <code>k</code> non-empty subarrays such that the largest sum of any subarray is <strong>minimized</strong>.</p>

<p>Return <em>the minimized largest sum of the split</em>.</p>

<p>A <strong>subarray</strong> is a contiguous part of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,2,5,10,8], k = 2
<strong>Output:</strong> 18
<strong>Explanation:</strong> There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], k = 2
<strong>Output:</strong> 9
<strong>Explanation:</strong> There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(50, nums.length)</code></li>
</ul>

## Solutions

### Solution 1: Binary Search

We notice that the larger the maximum sum of the subarrays, the fewer the number of subarrays. When there is a maximum sum of the subarrays that meets the condition, then a larger maximum sum of the subarrays will definitely meet the condition. This means that we can perform a binary search for the maximum sum of the subarrays to find the smallest value that meets the condition.

We define the left boundary of the binary search as $left = \max(nums)$, and the right boundary as $right = sum(nums)$. Then for each step of the binary search, we take the middle value $mid = \lfloor \frac{left + right}{2} \rfloor$, and then determine whether there is a way to split the array so that the maximum sum of the subarrays does not exceed $mid$. If there is, it means that $mid$ might be the smallest value that meets the condition, so we adjust the right boundary to $mid$. Otherwise, we adjust the left boundary to $mid + 1$.

How do we determine whether there is a way to split the array so that the maximum sum of the subarrays does not exceed $mid$? We can use a greedy method, traverse the array from left to right, and add the elements of the array to the subarray one by one. If the current sum of the subarray is greater than $mid$, then we add the current element to the next subarray. If we can split the array into no more than $k$ subarrays, and the maximum sum of each subarray does not exceed $mid$, then $mid$ is the smallest value that meets the condition. Otherwise, $mid$ does not meet the condition.

The time complexity is $O(n \times \log m)$, and the space complexity is $O(1)$. Here, $n$ and $m$ are the length of the array and the sum of all elements in the array, respectively.

<!-- tabs:start -->

```python
class Solution:
    def splitArray(self, nums: List[int], k: int) -> int:
        def check(mx):
            s, cnt = inf, 0
            for x in nums:
                s += x
                if s > mx:
                    s = x
                    cnt += 1
            return cnt <= k

        left, right = max(nums), sum(nums)
        return left + bisect_left(range(left, right + 1), True, key=check)
```

```java
class Solution {
    public int splitArray(int[] nums, int k) {
        int left = 0, right = 0;
        for (int x : nums) {
            left = Math.max(left, x);
            right += x;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(nums, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int mx, int k) {
        int s = 1 << 30, cnt = 0;
        for (int x : nums) {
            s += x;
            if (s > mx) {
                ++cnt;
                s = x;
            }
        }
        return cnt <= k;
    }
}
```

```cpp
class Solution {
public:
    int splitArray(vector<int>& nums, int k) {
        int left = 0, right = 0;
        for (int& x : nums) {
            left = max(left, x);
            right += x;
        }
        auto check = [&](int mx) {
            int s = 1 << 30, cnt = 0;
            for (int& x : nums) {
                s += x;
                if (s > mx) {
                    s = x;
                    ++cnt;
                }
            }
            return cnt <= k;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

```go
func splitArray(nums []int, k int) int {
	left, right := 0, 0
	for _, x := range nums {
		left = max(left, x)
		right += x
	}
	return left + sort.Search(right-left, func(mx int) bool {
		mx += left
		s, cnt := 1<<30, 0
		for _, x := range nums {
			s += x
			if s > mx {
				s = x
				cnt++
			}
		}
		return cnt <= k
	})
}
```

```ts
function splitArray(nums: number[], k: number): number {
    let left = 0;
    let right = 0;
    for (const x of nums) {
        left = Math.max(left, x);
        right += x;
    }
    const check = (mx: number) => {
        let s = 1 << 30;
        let cnt = 0;
        for (const x of nums) {
            s += x;
            if (s > mx) {
                s = x;
                ++cnt;
            }
        }
        return cnt <= k;
    };
    while (left < right) {
        const mid = (left + right) >> 1;
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

<!-- tabs:end -->

<!-- end -->
