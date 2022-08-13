# [2333. 最小差值平方和](https://leetcode.cn/problems/minimum-sum-of-squared-difference)

[English Version](/solution/2300-2399/2333.Minimum%20Sum%20of%20Squared%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;，长度为&nbsp;<code>n</code>&nbsp;。</p>

<p>数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;的 <strong>差值平方和</strong>&nbsp;定义为所有满足&nbsp;<code>0 &lt;= i &lt; n</code>&nbsp;的&nbsp;<code>(nums1[i] - nums2[i])<sup>2</sup></code>&nbsp;之和。</p>

<p>同时给你两个正整数&nbsp;<code>k1</code> 和&nbsp;<code>k2</code>&nbsp;。你可以将&nbsp;<code>nums1</code>&nbsp;中的任意元素&nbsp;<code>+1</code> 或者&nbsp;<code>-1</code>&nbsp;至多&nbsp;<code>k1</code>&nbsp;次。类似的，你可以将&nbsp;<code>nums2</code>&nbsp;中的任意元素&nbsp;<code>+1</code> 或者&nbsp;<code>-1</code>&nbsp;至多&nbsp;<code>k2</code>&nbsp;次。</p>

<p>请你返回修改数组<em>&nbsp;</em><code>nums1</code><em>&nbsp;</em>至多<em>&nbsp;</em><code>k1</code>&nbsp;次且修改数组<em>&nbsp;</em><code>nums2</code>&nbsp;至多 <code>k2</code><em>&nbsp;</em>次后的最小&nbsp;<strong>差值平方和</strong>&nbsp;。</p>

<p><strong>注意：</strong>你可以将数组中的元素变成&nbsp;<strong>负</strong>&nbsp;整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
<b>输出：</b>579
<b>解释：</b>nums1 和 nums2 中的元素不能修改，因为 k1 = 0 和 k2 = 0 。
差值平方和为：(1 - 2)<sup>2 </sup>+ (2 - 10)<sup>2 </sup>+ (3 - 20)<sup>2 </sup>+ (4 - 19)<sup>2</sup>&nbsp;= 579 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
<b>输出：</b>43
<b>解释：</b>一种得到最小差值平方和的方式为：
- 将 nums1[0] 增加一次。
- 将 nums2[2] 增加一次。
最小差值平方和为：
(2 - 5)<sup>2 </sup>+ (4 - 8)<sup>2 </sup>+ (10 - 7)<sup>2 </sup>+ (12 - 9)<sup>2</sup>&nbsp;= 43 。
注意，也有其他方式可以得到最小差值平方和，但没有得到比 43 更小答案的方案。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k1, k2 &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
