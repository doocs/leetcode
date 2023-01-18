# [2302. Count Subarrays With Score Less Than K](https://leetcode.com/problems/count-subarrays-with-score-less-than-k)

[中文文档](/solution/2300-2399/2302.Count%20Subarrays%20With%20Score%20Less%20Than%20K/README.md)

## Description

<p>The <strong>score</strong> of an array is defined as the <strong>product</strong> of its sum and its length.</p>

<ul>
	<li>For example, the score of <code>[1, 2, 3, 4, 5]</code> is <code>(1 + 2 + 3 + 4 + 5) * 5 = 75</code>.</li>
</ul>

<p>Given a positive integer array <code>nums</code> and an integer <code>k</code>, return <em>the <strong>number of non-empty subarrays</strong> of</em> <code>nums</code> <em>whose score is <strong>strictly less</strong> than</em> <code>k</code>.</p>

<p>A <strong>subarray</strong> is a contiguous sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,4,3,5], k = 10
<strong>Output:</strong> 6
<strong>Explanation:</strong>
The 6 subarrays having scores less than 10 are:
- [2] with score 2 * 1 = 2.
- [1] with score 1 * 1 = 1.
- [4] with score 4 * 1 = 4.
- [3] with score 3 * 1 = 3. 
- [5] with score 5 * 1 = 5.
- [2,1] with score (2 + 1) * 2 = 6.
Note that subarrays such as [1,4] and [4,3,5] are not considered because their scores are 10 and 36 respectively, while we need scores strictly less than 10.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1], k = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong>
Every subarray except [1,1,1] has a score less than 5.
[1,1,1] has a score (1 + 1 + 1) * 3 = 9, which is greater than 5.
Thus, there are 5 subarrays having scores less than 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>15</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        s = list(accumulate(nums, initial=0))
        ans = 0
        for i in range(1, len(s)):
            left, right = 0, i
            while left < right:
                mid = (left + right + 1) >> 1
                if (s[i] - s[i - mid]) * mid < k:
                    left = mid
                else:
                    right = mid - 1
            ans += left
        return ans
```

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        ans = s = j = 0
        for i, v in enumerate(nums):
            s += v
            while s * (i - j + 1) >= k:
                s -= nums[j]
                j += 1
            ans += i - j + 1
        return ans
```

### **Java**

```java
class Solution {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            int left = 0, right = i;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                if ((s[i] - s[i - mid]) * mid < k) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans += left;
        }
        return ans;
    }
}
```

```java
class Solution {
    public long countSubarrays(int[] nums, long k) {
        long ans = 0, s = 0;
        for (int i = 0, j = 0; i < nums.length; ++i) {
            s += nums[i];
            while (s * (i - j + 1) >= k) {
                s -= nums[j++];
            }
            ans += i - j + 1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, long long k) {
        int n = nums.size();
        long long s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long long ans = 0;
        for (int i = 1; i <= n; ++i) {
            int left = 0, right = i;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                if ((s[i] - s[i - mid]) * mid < k) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans += left;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    long long countSubarrays(vector<int>& nums, long long k) {
        long long ans = 0, s = 0;
        for (int i = 0, j = 0; i < nums.size(); ++i) {
            s += nums[i];
            while (s * (i - j + 1) >= k) {
                s -= nums[j++];
            }
            ans += i - j + 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubarrays(nums []int, k int64) (ans int64) {
	n := len(nums)
	s := make([]int64, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + int64(v)
	}
	for i := 1; i <= n; i++ {
		left, right := 0, i
		for left < right {
			mid := (left + right + 1) >> 1
			if (s[i]-s[i-mid])*int64(mid) < k {
				left = mid
			} else {
				right = mid - 1
			}
		}
		ans += int64(left)
	}
	return
}
```

```go
func countSubarrays(nums []int, k int64) (ans int64) {
	s, j := 0, 0
	for i, v := range nums {
		s += v
		for int64(s*(i-j+1)) >= k {
			s -= nums[j]
			j++
		}
		ans += int64(i - j + 1)
	}
	return
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
