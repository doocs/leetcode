# [2009. Minimum Number of Operations to Make Array Continuous](https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous)

[中文文档](/solution/2000-2099/2009.Minimum%20Number%20of%20Operations%20to%20Make%20Array%20Continuous/README.md)

<!-- tags:Array,Hash Table,Binary Search,Sliding Window -->

<!-- difficulty:Hard -->

## Description

<p>You are given an integer array <code>nums</code>. In one operation, you can replace <strong>any</strong> element in <code>nums</code> with <strong>any</strong> integer.</p>

<p><code>nums</code> is considered <strong>continuous</strong> if both of the following conditions are fulfilled:</p>

<ul>
	<li>All elements in <code>nums</code> are <strong>unique</strong>.</li>
	<li>The difference between the <strong>maximum</strong> element and the <strong>minimum</strong> element in <code>nums</code> equals <code>nums.length - 1</code>.</li>
</ul>

<p>For example, <code>nums = [4, 2, 5, 3]</code> is <strong>continuous</strong>, but <code>nums = [1, 2, 3, 5, 6]</code> is <strong>not continuous</strong>.</p>

<p>Return <em>the <strong>minimum</strong> number of operations to make </em><code>nums</code><em> </em><strong><em>continuous</em></strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,2,5,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;nums is already continuous.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,5,6]
<strong>Output:</strong> 1
<strong>Explanation:</strong>&nbsp;One possible solution is to change the last element to 4.
The resulting array is [1,2,3,5,4], which is continuous.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,10,100,1000]
<strong>Output:</strong> 3
<strong>Explanation:</strong>&nbsp;One possible solution is to:
- Change the second element to 2.
- Change the third element to 3.
- Change the fourth element to 4.
The resulting array is [1,2,3,4], which is continuous.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Sorting + Deduplication + Binary Search

First, we sort the array and remove duplicates.

Then, we traverse the array, enumerating the current element $nums[i]$ as the minimum value of the consecutive array. We use binary search to find the first position $j$ that is greater than $nums[i] + n - 1$. Then, $j-i$ is the length of the consecutive array when the current element is the minimum value. We update the answer, i.e., $ans = \min(ans, n - (j - i))$.

Finally, we return $ans$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        ans = n = len(nums)
        nums = sorted(set(nums))
        for i, v in enumerate(nums):
            j = bisect_right(nums, v + n - 1)
            ans = min(ans, n - (j - i))
        return ans
```

```java
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int m = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[i - 1]) {
                nums[m++] = nums[i];
            }
        }
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int j = search(nums, nums[i] + n - 1, i, m);
            ans = Math.min(ans, n - (j - i));
        }
        return ans;
    }

    private int search(int[] nums, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int m = unique(nums.begin(), nums.end()) - nums.begin();
        int n = nums.size();
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int j = upper_bound(nums.begin() + i, nums.begin() + m, nums[i] + n - 1) - nums.begin();
            ans = min(ans, n - (j - i));
        }
        return ans;
    }
};
```

```go
func minOperations(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	m := 1
	for i := 1; i < n; i++ {
		if nums[i] != nums[i-1] {
			nums[m] = nums[i]
			m++
		}
	}
	ans := n
	for i := 0; i < m; i++ {
		j := sort.Search(m, func(k int) bool { return nums[k] > nums[i]+n-1 })
		ans = min(ans, n-(j-i))
	}
	return ans
}
```

```rust
use std::collections::BTreeSet;

impl Solution {
    #[allow(dead_code)]
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let nums = nums.into_iter().collect::<BTreeSet<i32>>();

        let m = nums.len();
        let nums = nums.into_iter().collect::<Vec<i32>>();

        let mut ans = n;

        for i in 0..m {
            let j = match nums.binary_search(&(nums[i] + (n as i32))) {
                Ok(idx) => idx,
                Err(idx) => idx,
            };
            ans = std::cmp::min(ans, n - (j - i));
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

### Solution 2: Sorting + Deduplication + Two Pointers

Similar to Solution 1, we first sort the array and remove duplicates.

Then, we traverse the array, enumerating the current element $nums[i]$ as the minimum value of the consecutive array. We use two pointers to find the first position $j$ that is greater than $nums[i] + n - 1$. Then, $j-i$ is the length of the consecutive array when the current element is the minimum value. We update the answer, i.e., $ans = \min(ans, n - (j - i))$.

Finally, we return $ans$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        nums = sorted(set(nums))
        ans, j = n, 0
        for i, v in enumerate(nums):
            while j < len(nums) and nums[j] - v <= n - 1:
                j += 1
            ans = min(ans, n - (j - i))
        return ans
```

```java
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int m = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[i - 1]) {
                nums[m++] = nums[i];
            }
        }
        int ans = n;
        for (int i = 0, j = 0; i < m; ++i) {
            while (j < m && nums[j] - nums[i] <= n - 1) {
                ++j;
            }
            ans = Math.min(ans, n - (j - i));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int m = unique(nums.begin(), nums.end()) - nums.begin();
        int n = nums.size();
        int ans = n;
        for (int i = 0, j = 0; i < m; ++i) {
            while (j < m && nums[j] - nums[i] <= n - 1) {
                ++j;
            }
            ans = min(ans, n - (j - i));
        }
        return ans;
    }
};
```

```go
func minOperations(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	m := 1
	for i := 1; i < n; i++ {
		if nums[i] != nums[i-1] {
			nums[m] = nums[i]
			m++
		}
	}
	ans := n
	for i, j := 0, 0; i < m; i++ {
		for j < m && nums[j]-nums[i] <= n-1 {
			j++
		}
		ans = min(ans, n-(j-i))
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
