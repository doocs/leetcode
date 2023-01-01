# [2464. 有效分割中的最少子数组数目](https://leetcode.cn/problems/minimum-subarrays-in-a-valid-split)

[English Version](/solution/2400-2499/2464.Minimum%20Subarrays%20in%20a%20Valid%20Split/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>nums</code>。</p>

<p>如果要将整数数组 <code>nums</code> 拆分为&nbsp;<strong>子数组&nbsp;</strong>后是&nbsp;<strong>有效的</strong>，则必须满足:</p>

<ul>
	<li>每个子数组的第一个和最后一个元素的最大公约数&nbsp;<strong>大于</strong> <code>1</code>，且</li>
	<li><code>nums</code> 的每个元素只属于一个子数组。</li>
</ul>

<p>返回 <code>nums</code>&nbsp;的&nbsp;<strong>有效&nbsp;</strong>子数组拆分中的&nbsp;<strong>最少&nbsp;</strong>子数组数目。如果不能进行有效的子数组拆分，则返回 <code>-1</code>。</p>

<p><b>注意</b>:</p>

<ul>
	<li>两个数的&nbsp;<strong>最大公约数&nbsp;</strong>是能整除两个数的最大正整数。</li>
	<li><strong>子数组&nbsp;</strong>是数组中连续的非空部分。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,6,3,4,3]
<strong>输出:</strong> 2
<strong>解释:</strong> 我们可以通过以下方式创建一个有效的分割: [2,6] | [3,4,3].
- 第一个子数组的起始元素是 2，结束元素是 6。它们的最大公约数是 2，大于 1。
- 第二个子数组的起始元素是 3，结束元素是 3。它们的最大公约数是 3，大于 1。
可以证明，2 是我们在有效分割中可以获得的最少子数组数。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [3,5]
<strong>输出:</strong> 2
<strong>解释:</strong> 我们可以通过以下方式创建一个有效的分割: [3] | [5].
- 第一个子数组的起始元素是 3，结束元素是 3。它们的最大公约数是 3，大于 1。
- 第二个子数组的起始元素是 5，结束元素是 5。它们的最大公约数是 5，大于 1。
可以证明，2 是我们在有效分割中可以获得的最少子数组数。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,1]
<strong>输出:</strong> -1
<strong>解释:</strong> 不可能创建有效的分割。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i)$ 表示从下标 $i$ 开始的最小分割数。对于下标 $i$，我们可以枚举所有的分割点 $j$，即 $i \leq j \lt n$，其中 $n$ 为数组长度。对于每个分割点 $j$，我们需要判断 $nums[i]$ 和 $nums[j]$ 的最大公约数是否大于 $1$，如果大于 $1$，则可以进行分割，此时分割数为 $1 + dfs(j + 1)$，否则分割数为 $+\infty$。最后我们取所有分割数的最小值即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
