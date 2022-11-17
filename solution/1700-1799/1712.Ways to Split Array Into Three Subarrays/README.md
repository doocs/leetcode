# [1712. 将数组分成三个子数组的方案数](https://leetcode.cn/problems/ways-to-split-array-into-three-subarrays)

[English Version](/solution/1700-1799/1712.Ways%20to%20Split%20Array%20Into%20Three%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们称一个分割整数数组的方案是 <strong>好的</strong> ，当它满足：</p>

<ul>
	<li>数组被分成三个 <strong>非空</strong> 连续子数组，从左至右分别命名为 <code>left</code> ， <code>mid</code> ， <code>right</code> 。</li>
	<li><code>left</code> 中元素和小于等于 <code>mid</code> 中元素和，<code>mid</code> 中元素和小于等于 <code>right</code> 中元素和。</li>
</ul>

<p>给你一个 <strong>非负</strong> 整数数组 <code>nums</code> ，请你返回 <strong>好的</strong> 分割 <code>nums</code> 方案数目。由于答案可能会很大，请你将结果对 <code>10<sup>9 </sup>+ 7</code> 取余后返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1]
<b>输出：</b>1
<b>解释：</b>唯一一种好的分割方案是将 nums 分成 [1] [1] [1] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,2,2,5,0]
<b>输出：</b>3
<b>解释：</b>nums 总共有 3 种好的分割方案：
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,1]
<b>输出：</b>0
<b>解释：</b>没有好的分割方案。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 二分查找**

我们先预处理出数组 `nums` 的前缀和数组 $s$，其中 $s[i]$ 表述数组 `nums` 前 $i+1$ 个元素之和。

由于数组 `nums` 的元素都是非负整数，因此前缀和数组 $s$ 是一个单调递增数组。

我们在 $[0,..n-2)$ 的范围内枚举 `left` 子数组所能到达的下标 $i$，然后利用前缀和数组单调递增的特性，通过二分查找的方式找到 `mid` 子数组分割的合理范围，记为 $[j, k)$，累加方案数 $k-j$。

二分细节上，子数组分割必须满足 $s[j] \geq s[i]$，并且 $s[n - 1] - s[k] \geq s[k] - s[i]$。即 $s[j] \geq s[i]$，且 $s[k] \leq \frac{s[n - 1] + s[i]}{2}$。

最后，将方案数对 $10^9+7$ 取模后返回即可。

时间复杂度 $O(n\times \log n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def waysToSplit(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        s = list(accumulate(nums))
        ans, n = 0, len(nums)
        for i in range(n - 2):
            j = bisect_left(s, s[i] << 1, i + 1, n - 1)
            k = bisect_right(s, (s[-1] + s[i]) >> 1, j, n - 1)
            ans += k - j
        return ans % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] s = new int[n];
        s[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            s[i] = s[i - 1] + nums[i];
        }
        int ans = 0;
        for (int i = 0; i < n - 2; ++i) {
            int j = search(s, s[i] << 1, i + 1, n - 1);
            int k = search(s, ((s[n - 1] + s[i]) >> 1) + 1, j, n - 1);
            ans = (ans + k - j) % MOD;
        }
        return ans;
    }

    private int search(int[] s, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] >= x) {
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
    const int mod = 1e9 + 7;

    int waysToSplit(vector<int>& nums) {
        int n = nums.size();
        vector<int> s(n, nums[0]);
        for (int i = 1; i < n; ++i) s[i] = s[i - 1] + nums[i];
        int ans = 0;
        for (int i = 0; i < n - 2; ++i) {
            int j = lower_bound(s.begin() + i + 1, s.begin() + n - 1, s[i] << 1) - s.begin();
            int k = upper_bound(s.begin() + j, s.begin() + n - 1, (s[n - 1] + s[i]) >> 1) - s.begin();
            ans = (ans + k - j) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func waysToSplit(nums []int) (ans int) {
	const mod int = 1e9 + 7
	n := len(nums)
	s := make([]int, n)
	s[0] = nums[0]
	for i := 1; i < n; i++ {
		s[i] = s[i-1] + nums[i]
	}
	for i := 0; i < n-2; i++ {
		j := sort.Search(n-1, func(h int) bool { return h > i && s[h] >= (s[i]<<1) })
		k := sort.Search(n-1, func(h int) bool { return h >= j && s[h] > (s[n-1]+s[i])>>1 })
		ans = (ans + k - j) % mod
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var waysToSplit = function (nums) {
    const mod = 1e9 + 7;
    const n = nums.length;
    const s = new Array(n).fill(nums[0]);
    for (let i = 1; i < n; ++i) {
        s[i] = s[i - 1] + nums[i];
    }
    function search(s, x, left, right) {
        while (left < right) {
            const mid = (left + right) >> 1;
            if (s[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    let ans = 0;
    for (let i = 0; i < n - 2; ++i) {
        const j = search(s, s[i] << 1, i + 1, n - 1);
        const k = search(s, ((s[n - 1] + s[i]) >> 1) + 1, j, n - 1);
        ans = (ans + k - j) % mod;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
