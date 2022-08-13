# [2333. Minimum Sum of Squared Difference](https://leetcode.com/problems/minimum-sum-of-squared-difference)

[中文文档](/solution/2300-2399/2333.Minimum%20Sum%20of%20Squared%20Difference/README.md)

## Description

<p>You are given two positive <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, both of length <code>n</code>.</p>

<p>The <strong>sum of squared difference</strong> of arrays <code>nums1</code> and <code>nums2</code> is defined as the <strong>sum</strong> of <code>(nums1[i] - nums2[i])<sup>2</sup></code> for each <code>0 &lt;= i &lt; n</code>.</p>

<p>You are also given two positive integers <code>k1</code> and <code>k2</code>. You can modify any of the elements of <code>nums1</code> by <code>+1</code> or <code>-1</code> at most <code>k1</code> times. Similarly, you can modify any of the elements of <code>nums2</code> by <code>+1</code> or <code>-1</code> at most <code>k2</code> times.</p>

<p>Return <em>the minimum <strong>sum of squared difference</strong> after modifying array </em><code>nums1</code><em> at most </em><code>k1</code><em> times and modifying array </em><code>nums2</code><em> at most </em><code>k2</code><em> times</em>.</p>

<p><strong>Note</strong>: You are allowed to modify the array elements to become <strong>negative</strong> integers.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
<strong>Output:</strong> 579
<strong>Explanation:</strong> The elements in nums1 and nums2 cannot be modified because k1 = 0 and k2 = 0. 
The sum of square difference will be: (1 - 2)<sup>2 </sup>+ (2 - 10)<sup>2 </sup>+ (3 - 20)<sup>2 </sup>+ (4 - 19)<sup>2</sup>&nbsp;= 579.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
<strong>Output:</strong> 43
<strong>Explanation:</strong> One way to obtain the minimum sum of square difference is: 
- Increase nums1[0] once.
- Increase nums2[2] once.
The minimum of the sum of square difference will be: 
(2 - 5)<sup>2 </sup>+ (4 - 8)<sup>2 </sup>+ (10 - 7)<sup>2 </sup>+ (12 - 9)<sup>2</sup>&nbsp;= 43.
Note that, there are other ways to obtain the minimum of the sum of square difference, but there is no way to obtain a sum smaller than 43.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k1, k2 &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSumSquareDiff(
        self, nums1: List[int], nums2: List[int], k1: int, k2: int
    ) -> int:
        d = [abs(a - b) for a, b in zip(nums1, nums2)]
        k = k1 + k2
        if sum(d) <= k:
            return 0
        left, right = 0, max(d)
        while left < right:
            mid = (left + right) >> 1
            if sum(max(v - mid, 0) for v in d) <= k:
                right = mid
            else:
                left = mid + 1
        for i, v in enumerate(d):
            d[i] = min(left, v)
            k -= max(0, v - left)
        for i, v in enumerate(d):
            if k == 0:
                break
            if v == left:
                k -= 1
                d[i] -= 1
        return sum(v * v for v in d)
```

### **Java**

```java
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] d = new int[n];
        long s = 0;
        int mx = 0;
        int k = k1 + k2;
        for (int i = 0; i < n; ++i) {
            d[i] = Math.abs(nums1[i] - nums2[i]);
            s += d[i];
            mx = Math.max(mx ,d[i]);
        }
        if (s <= k) {
            return 0;
        }
        int left = 0, right = mx;
        while (left < right) {
            int mid = (left + right) >> 1;
            long t = 0;
            for (int v : d) {
                t += Math.max(v - mid, 0);
            }
            if (t <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            k -= Math.max(0, d[i] - left);
            d[i] = Math.min(d[i], left);
        }
        for (int i = 0; i < n && k > 0; ++i) {
            if (d[i] == left) {
                --k;
                --d[i];
            }
        }
        long ans = 0;
        for (int v : d) {
            ans += (long) v * v;
        }
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2, int k1, int k2) {
        int n = nums1.size();
        vector<int> d(n);
        ll s = 0;
        int mx = 0;
        int k = k1 + k2;
        for (int i = 0; i < n; ++i) {
            d[i] = abs(nums1[i] - nums2[i]);
            s += d[i];
            mx = max(mx, d[i]);
        }
        if (s <= k) return 0;
        int left = 0, right = mx;
        while (left < right) {
            int mid = (left + right) >> 1;
            ll t = 0;
            for (int v : d) t += max(v - mid, 0);
            if (t <= k)
                right = mid;
            else
                left = mid + 1;
        }
        for (int i = 0; i < n; ++i) {
            k -= max(0, d[i] - left);
            d[i] = min(d[i], left);
        }
        for (int i = 0; i < n && k; ++i) {
            if (d[i] == left) {
                --k;
                --d[i];
            }
        }
        ll ans = 0;
        for (int v : d) ans += 1ll * v * v;
        return ans;
    }
};
```

### **Go**

```go
func minSumSquareDiff(nums1 []int, nums2 []int, k1 int, k2 int) int64 {
	k := k1 + k2
	s, mx := 0, 0
	n := len(nums1)
	d := make([]int, n)
	for i, v := range nums1 {
		d[i] = abs(v - nums2[i])
		s += d[i]
		mx = max(mx, d[i])
	}
	if s <= k {
		return 0
	}
	left, right := 0, mx
	for left < right {
		mid := (left + right) >> 1
		t := 0
		for _, v := range d {
			t += max(v-mid, 0)
		}
		if t <= k {
			right = mid
		} else {
			left = mid + 1
		}
	}
	for i, v := range d {
		k -= max(v-left, 0)
		d[i] = min(v, left)
	}
	for i, v := range d {
		if k <= 0 {
			break
		}
		if v == left {
			d[i]--
			k--
		}
	}
	ans := 0
	for _, v := range d {
		ans += v * v
	}
	return int64(ans)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
