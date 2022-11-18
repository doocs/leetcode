# [2464. Minimum Subarrays in a Valid Split](https://leetcode.com/problems/minimum-subarrays-in-a-valid-split)

[中文文档](/solution/2400-2499/2464.Minimum%20Subarrays%20in%20a%20Valid%20Split/README.md)

## Description

<p>You are given an integer array <code>nums</code>.</p>

<p>Splitting of an integer array <code>nums</code> into <strong>subarrays</strong> is <strong>valid</strong> if:</p>

<ul>
	<li>the <em>greatest common divisor</em> of the first and last elements of each subarray is <strong>greater</strong> than <code>1</code>, and</li>
	<li>each element of <code>nums</code> belongs to exactly one subarray.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of subarrays in a <strong>valid</strong> subarray splitting of</em> <code>nums</code>. If a valid subarray splitting is not possible, return <code>-1</code>.</p>

<p><strong>Note</strong> that:</p>

<ul>
	<li>The <strong>greatest common divisor</strong> of two numbers is the largest positive integer that evenly divides both numbers.</li>
	<li>A <strong>subarray</strong> is a contiguous non-empty part of an array.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,6,3,4,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can create a valid split in the following way: [2,6] | [3,4,3].
- The starting element of the 1<sup>st</sup> subarray is 2 and the ending is 6. Their greatest common divisor is 2, which is greater than 1.
- The starting element of the 2<sup>nd</sup> subarray is 3 and the ending is 3. Their greatest common divisor is 3, which is greater than 1.
It can be proved that 2 is the minimum number of subarrays that we can obtain in a valid split.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can create a valid split in the following way: [3] | [5].
- The starting element of the 1<sup>st</sup> subarray is 3 and the ending is 3. Their greatest common divisor is 3, which is greater than 1.
- The starting element of the 2<sup>nd</sup> subarray is 5 and the ending is 5. Their greatest common divisor is 5, which is greater than 1.
It can be proved that 2 is the minimum number of subarrays that we can obtain in a valid split.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is impossible to create valid split.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def validSubarraySplit(self, nums: List[int]) -> int:
        @cache
        def dfs(i):
            if i >= n:
                return 0
            ans = inf
            for j in range(i, n):
                if gcd(nums[i], nums[j]) > 1:
                    ans = min(ans, 1 + dfs(j + 1))
            return ans

        n = len(nums)
        ans = dfs(0)
        dfs.cache_clear()
        return ans if ans < inf else -1
```

### **Java**

```java
class Solution {
    private int n;
    private int[] f;
    private int[] nums;
    private int inf = 0x3f3f3f3f;

    public int validSubarraySplit(int[] nums) {
        n = nums.length;
        f = new int[n];
        this.nums = nums;
        int ans = dfs(0);
        return ans < inf ? ans : -1;
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] > 0) {
            return f[i];
        }
        int ans = inf;
        for (int j = i; j < n; ++j) {
            if (gcd(nums[i], nums[j]) > 1) {
                ans = Math.min(ans, 1 + dfs(j + 1));
            }
        }
        f[i] = ans;
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int inf = 0x3f3f3f3f;
    int validSubarraySplit(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(n);
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) return 0;
            if (f[i]) return f[i];
            int ans = inf;
            for (int j = i; j < n; ++j) {
                if (__gcd(nums[i], nums[j]) > 1) {
                    ans = min(ans, 1 + dfs(j + 1));
                }
            }
            f[i] = ans;
            return ans;
        };
        int ans = dfs(0);
        return ans < inf ? ans : -1;
    }
};
```

### **Go**

```go
func validSubarraySplit(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	var dfs func(int) int
	const inf int = 0x3f3f3f3f
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		ans := inf
		for j := i; j < n; j++ {
			if gcd(nums[i], nums[j]) > 1 {
				ans = min(ans, 1+dfs(j+1))
			}
		}
		f[i] = ans
		return ans
	}
	ans := dfs(0)
	if ans < inf {
		return ans
	}
	return -1
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
