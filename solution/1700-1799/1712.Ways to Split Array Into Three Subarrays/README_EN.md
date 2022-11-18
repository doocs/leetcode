# [1712. Ways to Split Array Into Three Subarrays](https://leetcode.com/problems/ways-to-split-array-into-three-subarrays)

[中文文档](/solution/1700-1799/1712.Ways%20to%20Split%20Array%20Into%20Three%20Subarrays/README.md)

## Description

<p>A split of an integer array is <strong>good</strong> if:</p>

<ul>
	<li>The array is split into three <strong>non-empty</strong> contiguous subarrays - named <code>left</code>, <code>mid</code>, <code>right</code> respectively from left to right.</li>
	<li>The sum of the elements in <code>left</code> is less than or equal to the sum of the elements in <code>mid</code>, and the sum of the elements in <code>mid</code> is less than or equal to the sum of the elements in <code>right</code>.</li>
</ul>

<p>Given <code>nums</code>, an array of <strong>non-negative</strong> integers, return <em>the number of <strong>good</strong> ways to split</em> <code>nums</code>. As the number may be too large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only good way to split nums is [1] [1] [1].</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,2,5,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are three good ways of splitting nums:
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no good way to split nums.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
