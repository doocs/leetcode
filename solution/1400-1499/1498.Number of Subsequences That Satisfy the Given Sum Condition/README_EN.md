# [1498. Number of Subsequences That Satisfy the Given Sum Condition](https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition)

[中文文档](/solution/1400-1499/1498.Number%20of%20Subsequences%20That%20Satisfy%20the%20Given%20Sum%20Condition/README.md)

## Description

<p>You are given an array of integers <code>nums</code> and an integer <code>target</code>.</p>

<p>Return <em>the number of <strong>non-empty</strong> subsequences of </em><code>nums</code><em> such that the sum of the minimum and maximum element on it is less or equal to </em><code>target</code>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5,6,7], target = 9
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 subsequences that satisfy the condition.
[3] -&gt; Min value + max value &lt;= target (3 + 3 &lt;= 9)
[3,5] -&gt; (3 + 5 &lt;= 9)
[3,5,6] -&gt; (3 + 6 &lt;= 9)
[3,6] -&gt; (3 + 6 &lt;= 9)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,6,8], target = 10
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,3,4,6,7], target = 12
<strong>Output:</strong> 61
<strong>Explanation:</strong> There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
Number of valid subsequences (63 - 2 = 61).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numSubseq(self, nums: List[int], target: int) -> int:
        mod = 10**9 + 7
        nums.sort()
        n = len(nums)
        f = [1] + [0] * n
        for i in range(1, n + 1):
            f[i] = f[i - 1] * 2 % mod
        ans = 0
        for i, x in enumerate(nums):
            if x * 2 > target:
                break
            j = bisect_right(nums, target - x, i + 1) - 1
            ans = (ans + f[j - i]) % mod
        return ans
```

### **Java**

```java
class Solution {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            f[i] = (f[i - 1] * 2) % mod;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] * 2L > target) {
                break;
            }
            int j = search(nums, target - nums[i], i + 1) - 1;
            ans = (ans + f[j - i]) % mod;
        }
        return ans;
    }

    private int search(int[] nums, int x, int left) {
        int right = nums.length;
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

### **C++**

```cpp
class Solution {
public:
    int numSubseq(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        const int mod = 1e9 + 7;
        int n = nums.size();
        int f[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            f[i] = (f[i - 1] * 2) % mod;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] * 2L > target) {
                break;
            }
            int j = upper_bound(nums.begin() + i + 1, nums.end(), target - nums[i]) - nums.begin() - 1;
            ans = (ans + f[j - i]) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func numSubseq(nums []int, target int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	f := make([]int, n+1)
	f[0] = 1
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		f[i] = f[i-1] * 2 % mod
	}
	for i, x := range nums {
		if x*2 > target {
			break
		}
		j := sort.SearchInts(nums[i+1:], target-x+1) + i
		ans = (ans + f[j-i]) % mod
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
