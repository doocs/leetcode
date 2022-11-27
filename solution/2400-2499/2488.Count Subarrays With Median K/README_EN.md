# [2488. Count Subarrays With Median K](https://leetcode.com/problems/count-subarrays-with-median-k)

[中文文档](/solution/2400-2499/2488.Count%20Subarrays%20With%20Median%20K/README.md)

## Description

<p>You are given an array <code>nums</code> of size <code>n</code> consisting of <strong>distinct </strong>integers from <code>1</code> to <code>n</code> and a positive integer <code>k</code>.</p>

<p>Return <em>the number of non-empty subarrays in </em><code>nums</code><em> that have a <strong>median</strong> equal to </em><code>k</code>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>The median of an array is the <strong>middle </strong>element after sorting the array in <strong>ascending </strong>order. If the array is of even length, the median is the <strong>left </strong>middle element.

    <ul>
    	<li>For example, the median of <code>[2,3,1,4]</code> is <code>2</code>, and the median of <code>[8,4,3,5,1]</code> is <code>4</code>.</li>
    </ul>
    </li>
    <li>A subarray is a contiguous part of an array.</li>

</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,4,5], k = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> The subarrays that have a median equal to 4 are: [4], [4,5] and [1,4,5].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1], k = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> [3] is the only subarray that has a median equal to 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= n</code></li>
	<li>The integers in <code>nums</code> are distinct.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        i = next(i for i, v in enumerate(nums) if v == k)
        n = len(nums)
        ans = 1
        d = defaultdict(int)
        mi = mx = 0
        for j in range(i + 1, n):
            if nums[j] < k:
                mi += 1
            else:
                mx += 1
            if 0 <= mx - mi <= 1:
                ans += 1
            d[mx - mi] += 1
        mi = mx = 0
        for j in range(i - 1, -1, -1):
            if nums[j] < k:
                mi += 1
            else:
                mx += 1
            if 0 <= mx - mi <= 1:
                ans += 1
            ans += d[mi - mx] + d[mi - mx + 1]
        return ans
```

### **Java**

```java
class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        for (int j = 0; j < n; ++j) {
            if (nums[j] == k) {
                i = j;
                break;
            }
        }
        int ans = 1;
        int[] d = new int[n << 1 | 1];
        int mi = 0, mx = 0;
        for (int j = i + 1; j < n; ++j) {
            if (nums[j] < k) {
                ++mi;
            } else {
                ++mx;
            }
            if (mx - mi >= 0 && mx - mi <= 1) {
                ++ans;
            }
            ++d[mx - mi + n];
        }
        mi = 0;
        mx = 0;
        for (int j = i - 1; j >= 0; --j) {
            if (nums[j] < k) {
                ++mi;
            } else {
                ++mx;
            }
            if (mx - mi >= 0 && mx - mi <= 1) {
                ++ans;
            }
            ans += d[mi - mx + n] + d[mi - mx + 1 + n];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        int i = 0;
        for (int j = 0; j < n; ++j) {
            if (nums[j] == k) {
                i = j;
                break;
            }
        }
        int ans = 1;
        int d[n << 1 | 1];
        memset(d, 0, sizeof d);
        int mi = 0, mx = 0;
        for (int j = i + 1; j < n; ++j) {
            if (nums[j] < k) ++mi;
            else ++mx;
            if (mx - mi >= 0 && mx - mi <= 1) ++ans;
            ++d[mx - mi + n];
        }
        mi = 0, mx = 0;
        for (int j = i - 1; ~j; --j) {
            if (nums[j] < k) ++mi;
            else ++mx;
            if (mx - mi >= 0 && mx - mi <= 1) ++ans;
            ans += d[mi - mx + n] + d[mi - mx + n + 1];
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubarrays(nums []int, k int) int {
	n := len(nums)
	var i int
	for j, v := range nums {
		if v == k {
			i = j
			break
		}
	}
	ans := 1
	d := make([]int, n<<1|1)
	mi, mx := 0, 0
	for j := i + 1; j < n; j++ {
		if nums[j] < k {
			mi++
		} else {
			mx++
		}
		if mx-mi >= 0 && mx-mi <= 1 {
			ans++
		}
		d[mx-mi+n]++
	}
	mi, mx = 0, 0
	for j := i - 1; j >= 0; j-- {
		if nums[j] < k {
			mi++
		} else {
			mx++
		}
		if mx-mi >= 0 && mx-mi <= 1 {
			ans++
		}
		ans += d[mi-mx+n] + d[mi-mx+n+1]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
