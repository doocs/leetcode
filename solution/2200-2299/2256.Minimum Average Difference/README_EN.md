# [2256. Minimum Average Difference](https://leetcode.com/problems/minimum-average-difference)

[中文文档](/solution/2200-2299/2256.Minimum%20Average%20Difference/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>.</p>

<p>The <strong>average difference</strong> of the index <code>i</code> is the <strong>absolute</strong> <strong>difference</strong> between the average of the <strong>first</strong> <code>i + 1</code> elements of <code>nums</code> and the average of the <strong>last</strong> <code>n - i - 1</code> elements. Both averages should be <strong>rounded down</strong> to the nearest integer.</p>

<p>Return<em> the index with the <strong>minimum average difference</strong></em>. If there are multiple such indices, return the <strong>smallest</strong> one.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The <strong>absolute difference</strong> of two numbers is the absolute value of their difference.</li>
	<li>The <strong>average</strong> of <code>n</code> elements is the <strong>sum</strong> of the <code>n</code> elements divided (<strong>integer division</strong>) by <code>n</code>.</li>
	<li>The average of <code>0</code> elements is considered to be <code>0</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,5,3,9,5,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
- The average difference of index 0 is: |2 / 1 - (5 + 3 + 9 + 5 + 3) / 5| = |2 / 1 - 25 / 5| = |2 - 5| = 3.
- The average difference of index 1 is: |(2 + 5) / 2 - (3 + 9 + 5 + 3) / 4| = |7 / 2 - 20 / 4| = |3 - 5| = 2.
- The average difference of index 2 is: |(2 + 5 + 3) / 3 - (9 + 5 + 3) / 3| = |10 / 3 - 17 / 3| = |3 - 5| = 2.
- The average difference of index 3 is: |(2 + 5 + 3 + 9) / 4 - (5 + 3) / 2| = |19 / 4 - 8 / 2| = |4 - 4| = 0.
- The average difference of index 4 is: |(2 + 5 + 3 + 9 + 5) / 5 - 3 / 1| = |24 / 5 - 3 / 1| = |4 - 3| = 1.
- The average difference of index 5 is: |(2 + 5 + 3 + 9 + 5 + 3) / 6 - 0| = |27 / 6 - 0| = |4 - 0| = 4.
The average difference of index 3 is the minimum average difference so return 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The only index is 0 so return 0.
The average difference of index 0 is: |0 / 1 - 0| = |0 - 0| = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumAverageDifference(self, nums: List[int]) -> int:
        s = list(accumulate(nums))
        ans, n = 0, len(nums)
        mi = inf
        for i in range(n):
            a = s[i] // (i + 1)
            b = 0 if i == n - 1 else (s[-1] - s[i]) // (n - i - 1)
            t = abs(a - b)
            if mi > t:
                ans = i
                mi = t
        return ans
```

### **Java**

```java
class Solution {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long[] s = new long[n];
        s[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            s[i] = s[i - 1] + nums[i];
        }
        int ans = 0;
        long mi = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            long a = s[i] / (i + 1);
            long b = i == n - 1 ? 0 : (s[n - 1] - s[i]) / (n - i - 1);
            long t = Math.abs(a - b);
            if (mi > t) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
typedef long long ll;

class Solution {
public:
    int minimumAverageDifference(vector<int>& nums) {
        int n = nums.size();
        vector<ll> s(n);
        s[0] = nums[0];
        for (int i = 1; i < n; ++i) s[i] = s[i - 1] + nums[i];
        int ans = 0;
        ll mi = LONG_MAX;
        for (int i = 0; i < n; ++i) {
            ll a = s[i] / (i + 1);
            ll b = i == n - 1 ? 0 : (s[n - 1] - s[i]) / (n - i - 1);
            ll t = abs(a - b);
            if (mi > t) {
                ans = i;
                mi = t;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumAverageDifference(nums []int) int {
	n := len(nums)
	s := make([]int, n)
	s[0] = nums[0]
	for i := 1; i < n; i++ {
		s[i] = s[i-1] + nums[i]
	}
	ans := 0
	mi := math.MaxInt32
	for i := 0; i < n; i++ {
		a := s[i] / (i + 1)
		b := 0
		if i != n-1 {
			b = (s[n-1] - s[i]) / (n - i - 1)
		}
		t := abs(a - b)
		if mi > t {
			ans = i
			mi = t
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
