# [2547. 拆分数组的最小代价](https://leetcode.cn/problems/minimum-cost-to-split-an-array)

[English Version](/solution/2500-2599/2547.Minimum%20Cost%20to%20Split%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p>将数组拆分成一些非空子数组。拆分的 <strong>代价</strong> 是每个子数组中的 <strong>重要性</strong> 之和。</p>

<p>令 <code>trimmed(subarray)</code> 作为子数组的一个特征，其中所有仅出现一次的数字将会被移除。</p>

<ul>
	<li>例如，<code>trimmed([3,1,2,4,3,4]) = [3,4,3,4]</code> 。</li>
</ul>

<p>子数组的 <strong>重要性</strong> 定义为 <code>k + trimmed(subarray).length</code> 。</p>

<ul>
	<li>例如，如果一个子数组是 <code>[1,2,3,3,3,4,4]</code> ，<code>trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4]</code> 。这个子数组的重要性就是 <code>k + 5</code> 。</li>
</ul>

<p>找出并返回拆分 <code>nums</code> 的所有可行方案中的最小代价。</p>

<p><strong>子数组</strong> 是数组的一个连续 <strong>非空</strong> 元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1,3,3], k = 2
<strong>输出：</strong>8
<strong>解释：</strong>将 nums 拆分成两个子数组：[1,2], [1,2,1,3,3]
[1,2] 的重要性是 2 + (0) = 2 。
[1,2,1,3,3] 的重要性是 2 + (2 + 2) = 6 。
拆分的代价是 2 + 6 = 8 ，可以证明这是所有可行的拆分方案中的最小代价。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1], k = 2
<strong>输出：</strong>6
<strong>解释：</strong>将 nums 拆分成两个子数组：[1,2], [1,2,1] 。
[1,2] 的重要性是 2 + (0) = 2 。
[1,2,1] 的重要性是 2 + (2) = 4 。
拆分的代价是 2 + 4 = 6 ，可以证明这是所有可行的拆分方案中的最小代价。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1], k = 5
<strong>输出：</strong>10
<strong>解释：</strong>将 nums 拆分成一个子数组：[1,2,1,2,1].
[1,2,1,2,1] 的重要性是 5 + (3 + 2) = 10 。
拆分的代价是 10 ，可以证明这是所有可行的拆分方案中的最小代价。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; nums.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCost(self, nums: List[int], k: int) -> int:
        @cache
        def dfs(i):
            if i >= n:
                return 0
            cnt = Counter()
            one = 0
            ans = inf
            for j in range(i, n):
                cnt[nums[j]] += 1
                if cnt[nums[j]] == 1:
                    one += 1
                elif cnt[nums[j]] == 2:
                    one -= 1
                ans = min(ans, k + j - i + 1 - one + dfs(j + 1))
            return ans

        n = len(nums)
        return dfs(0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Integer[] f;
    private int[] nums;
    private int n, k;

    public int minCost(int[] nums, int k) {
        n = nums.length;
        this.k = k;
        this.nums = nums;
        f = new Integer[n];
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] cnt = new int[n];
        int one = 0;
        int ans = 1 << 30;
        for (int j = i; j < n; ++j) {
            int x = ++cnt[nums[j]];
            if (x == 1) {
                ++one;
            } else if (x == 2) {
                --one;
            }
            ans = Math.min(ans, k + j - i + 1 - one + dfs(j + 1));
        }
        return f[i] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCost(vector<int>& nums, int k) {
        int n = nums.size();
        int f[n];
        memset(f, 0, sizeof f);
        function<int(int)> dfs = [&](int i) {
            if (i >= n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            int cnt[n];
            memset(cnt, 0, sizeof cnt);
            int one = 0;
            int ans = 1 << 30;
            for (int j = i; j < n; ++j) {
                int x = ++cnt[nums[j]];
                if (x == 1) {
                    ++one;
                } else if (x == 2) {
                    --one;
                }
                ans = min(ans, k + j - i + 1 - one + dfs(j + 1));
            }
            return f[i] = ans;
        };
        return dfs(0);
    }
};
```

### **Go**

```go
func minCost(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		ans, one := 1<<30, 0
		cnt := make([]int, n)
		for j := i; j < n; j++ {
			cnt[nums[j]]++
			x := cnt[nums[j]]
			if x == 1 {
				one++
			} else if x == 2 {
				one--
			}
			ans = min(ans, k+j-i+1-one+dfs(j+1))
		}
		f[i] = ans
		return ans
	}
	return dfs(0)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
