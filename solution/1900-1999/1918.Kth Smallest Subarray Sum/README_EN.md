# [1918. Kth Smallest Subarray Sum](https://leetcode.com/problems/kth-smallest-subarray-sum)

[中文文档](/solution/1900-1999/1918.Kth%20Smallest%20Subarray%20Sum/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
