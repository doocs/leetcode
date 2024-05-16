---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2702.Minimum%20Operations%20to%20Make%20Numbers%20Non-positive/README_EN.md
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [2702. Minimum Operations to Make Numbers Non-positive 🔒](https://leetcode.com/problems/minimum-operations-to-make-numbers-non-positive)

[中文文档](/solution/2700-2799/2702.Minimum%20Operations%20to%20Make%20Numbers%20Non-positive/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and two integers <code>x</code> and <code>y</code>. In one operation, you must choose an index <code>i</code> such that <code>0 &lt;= i &lt; nums.length</code> and perform the following:</p>

<ul>
	<li>Decrement <code>nums[i]</code> by <code>x</code>.</li>
	<li>Decrement values by <code>y</code> at all indices except the <code>i<sup>th</sup></code> one.</li>
</ul>

<p>Return <em>the minimum number of operations to make all the integers in </em><code>nums</code> <em><strong>less than or equal to zero.</strong></em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,1,7,6], x = 4, y = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> You will need three operations. One of the optimal sequence of operations is:
Operation 1: Choose i = 3. Then, nums = [1,2,-1,3,4]. 
Operation 2: Choose i = 3. Then, nums = [-1,0,-3,-1,2].
Operation 3: Choose i = 4. Then, nums = [-3,-2,-5,-3,-2].
Now, all the numbers in nums are non-positive. Therefore, we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1], x = 2, y = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can perform the operation once on i = 1. Then, nums becomes [0,0,0]. All the positive numbers are removed, and therefore, we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= y &lt; x &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

We notice that if an operation count $t$ can make all numbers less than or equal to $0$, then for any $t' > t$, the operation count $t'$ can also make all numbers less than or equal to $0$. Therefore, we can use binary search to find the minimum operation count.

We define the left boundary of the binary search as $l=0$, and the right boundary as $r=\max(nums)$. Each time we perform a binary search, we find the middle value $mid=\lfloor\frac{l+r}{2}\rfloor$, and then determine whether there exists an operation method that does not exceed $mid$ and makes all numbers less than or equal to $0$. If it exists, we update the right boundary $r = mid$, otherwise, we update the left boundary

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], x: int, y: int) -> int:
        def check(t: int) -> bool:
            cnt = 0
            for v in nums:
                if v > t * y:
                    cnt += ceil((v - t * y) / (x - y))
            return cnt <= t

        l, r = 0, max(nums)
        while l < r:
            mid = (l + r) >> 1
            if check(mid):
                r = mid
            else:
                l = mid + 1
        return l
```

```java
class Solution {
    private int[] nums;
    private int x;
    private int y;

    public int minOperations(int[] nums, int x, int y) {
        this.nums = nums;
        this.x = x;
        this.y = y;
        int l = 0, r = 0;
        for (int v : nums) {
            r = Math.max(r, v);
        }
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int t) {
        long cnt = 0;
        for (int v : nums) {
            if (v > (long) t * y) {
                cnt += (v - (long) t * y + x - y - 1) / (x - y);
            }
        }
        return cnt <= t;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x, int y) {
        int l = 0, r = *max_element(nums.begin(), nums.end());
        auto check = [&](int t) {
            long long cnt = 0;
            for (int v : nums) {
                if (v > 1LL * t * y) {
                    cnt += (v - 1LL * t * y + x - y - 1) / (x - y);
                }
            }
            return cnt <= t;
        };
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

```go
func minOperations(nums []int, x int, y int) int {
	check := func(t int) bool {
		cnt := 0
		for _, v := range nums {
			if v > t*y {
				cnt += (v - t*y + x - y - 1) / (x - y)
			}
		}
		return cnt <= t
	}

	l, r := 0, slices.Max(nums)
	for l < r {
		mid := (l + r) >> 1
		if check(mid) {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l
}
```

```ts
function minOperations(nums: number[], x: number, y: number): number {
    let l = 0;
    let r = Math.max(...nums);
    const check = (t: number): boolean => {
        let cnt = 0;
        for (const v of nums) {
            if (v > t * y) {
                cnt += Math.ceil((v - t * y) / (x - y));
            }
        }
        return cnt <= t;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
