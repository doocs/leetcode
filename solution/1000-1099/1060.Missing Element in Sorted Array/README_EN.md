# [1060. Missing Element in Sorted Array](https://leetcode.com/problems/missing-element-in-sorted-array)

[中文文档](/solution/1000-1099/1060.Missing%20Element%20in%20Sorted%20Array/README.md)

## Description

<p>Given an integer array <code>nums</code> which is sorted in <strong>ascending order</strong> and all of its elements are <strong>unique</strong> and given also an integer <code>k</code>, return the <code>k<sup>th</sup></code> missing number starting from the leftmost number of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,7,9,10], k = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> The first missing number is 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,7,9,10], k = 3
<strong>Output:</strong> 8
<strong>Explanation:</strong> The missing numbers are [5,6,8,...], hence the third missing number is 8.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4], k = 3
<strong>Output:</strong> 6
<strong>Explanation:</strong> The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>nums</code> is sorted in <strong>ascending order,</strong> and all the elements are <strong>unique</strong>.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>8</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Can you find a logarithmic time complexity (i.e., <code>O(log(n))</code>) solution?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def missingElement(self, nums: List[int], k: int) -> int:
        def missing(i: int) -> int:
            return nums[i] - nums[0] - i

        n = len(nums)
        if k > missing(n - 1):
            return nums[n - 1] + k - missing(n - 1)
        l, r = 0, n - 1
        while l < r:
            mid = (l + r) >> 1
            if missing(mid) >= k:
                r = mid
            else:
                l = mid + 1
        return nums[l - 1] + k - missing(l - 1)
```

### **Java**

```java
class Solution {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        if (k > missing(nums, n - 1)) {
            return nums[n - 1] + k - missing(nums, n - 1);
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (missing(nums, mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l - 1] + k - missing(nums, l - 1);
    }

    private int missing(int[] nums, int i) {
        return nums[i] - nums[0] - i;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int missingElement(vector<int>& nums, int k) {
        auto missing = [&](int i) {
            return nums[i] - nums[0] - i;
        };
        int n = nums.size();
        if (k > missing(n - 1)) {
            return nums[n - 1] + k - missing(n - 1);
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (missing(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l - 1] + k - missing(l - 1);
    }
};
```

### **Go**

```go
func missingElement(nums []int, k int) int {
	missing := func(i int) int {
		return nums[i] - nums[0] - i
	}
	n := len(nums)
	if k > missing(n-1) {
		return nums[n-1] + k - missing(n-1)
	}
	l, r := 0, n-1
	for l < r {
		mid := (l + r) >> 1
		if missing(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return nums[l-1] + k - missing(l-1)
}
```

### **...**

```

```

<!-- tabs:end -->
