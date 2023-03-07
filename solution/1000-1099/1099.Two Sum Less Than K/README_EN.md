# [1099. Two Sum Less Than K](https://leetcode.com/problems/two-sum-less-than-k)

[中文文档](/solution/1000-1099/1099.Two%20Sum%20Less%20Than%20K/README.md)

## Description

<p>Given an array <code>nums</code> of integers and&nbsp;integer <code>k</code>, return the maximum <code>sum</code> such that there exists <code>i &lt; j</code> with <code>nums[i] + nums[j] = sum</code> and <code>sum &lt; k</code>. If no <code>i</code>, <code>j</code> exist satisfying this equation, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [34,23,1,24,75,33,54,8], k = 60
<strong>Output:</strong> 58
<strong>Explanation: </strong>We can use 34 and 24 to sum 58 which is less than 60.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,30], k = 15
<strong>Output:</strong> -1
<strong>Explanation: </strong>In this case it is not possible to get a pair sum less that 15.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 2000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = -1
        for i, x in enumerate(nums):
            j = bisect_left(nums, k - x, lo=i + 1) - 1
            if i < j:
                ans = max(ans, x + nums[j])
        return ans
```

```python
class Solution:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = -1
        i, j = 0, len(nums) - 1
        while i < j:
            if (t := nums[i] + nums[j]) < k:
                ans = max(ans, t)
                i += 1
            else:
                j -= 1
        return ans
```

### **Java**

```java
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = search(nums, k - nums[i], i + 1, n) - 1;
            if (i < j) {
                ans = Math.max(ans, nums[i] + nums[j]);
            }
        }
        return ans;
    }

    private int search(int[] nums, int x, int l, int r) {
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```java
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int t = nums[i] + nums[j];
            if (t < k) {
                ans = Math.max(ans, t);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = -1, n = nums.size();
        for (int i = 0; i < n; ++i) {
            int j = lower_bound(nums.begin() + i + 1, nums.end(), k - nums[i]) - nums.begin() - 1;
            if (i < j) {
                ans = max(ans, nums[i] + nums[j]);
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = -1;
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            int t = nums[i] + nums[j];
            if (t < k) {
                ans = max(ans, t);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func twoSumLessThanK(nums []int, k int) int {
	sort.Ints(nums)
	ans := -1
	for i, x := range nums {
		j := sort.SearchInts(nums[i+1:], k-x) + i
		if v := nums[i] + nums[j]; i < j && ans < v {
			ans = v
		}
	}
	return ans
}
```

```go
func twoSumLessThanK(nums []int, k int) int {
	sort.Ints(nums)
	ans := -1
	i, j := 0, len(nums)-1
	for i < j {
		if t := nums[i] + nums[j]; t < k {
			ans = max(ans, t)
			i++
		} else {
			j--
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

### **...**

```

```

<!-- tabs:end -->
