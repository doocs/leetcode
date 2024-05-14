---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3098.Find%20the%20Sum%20of%20Subsequence%20Powers/README.md
rating: 2552
tags:
    - 数组
    - 动态规划
    - 排序
---

# [3098. 求出所有子序列的能量和](https://leetcode.cn/problems/find-the-sum-of-subsequence-powers)

[English Version](/solution/3000-3099/3098.Find%20the%20Sum%20of%20Subsequence%20Powers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>正</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。</p>

<p>一个子序列的 <strong>能量</strong>&nbsp;定义为子序列中&nbsp;<strong>任意</strong>&nbsp;两个元素的差值绝对值的 <strong>最小值</strong>&nbsp;。</p>

<p>请你返回 <code>nums</code>&nbsp;中长度 <strong>等于</strong>&nbsp;<code>k</code>&nbsp;的 <strong>所有</strong>&nbsp;子序列的 <strong>能量和</strong>&nbsp;。</p>

<p>由于答案可能会很大，将答案对&nbsp;<code>10<sup>9 </sup>+ 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4], k = 3</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code>&nbsp;中总共有 4 个长度为 3 的子序列：<code>[1,2,3]</code>&nbsp;，<code>[1,3,4]</code>&nbsp;，<code>[1,2,4]</code>&nbsp;和&nbsp;<code>[2,3,4]</code>&nbsp;。能量和为 <code>|2 - 3| + |3 - 4| + |2 - 1| + |3 - 4| = 4</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2,2], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code>&nbsp;中唯一一个长度为 2 的子序列是&nbsp;<code>[2,2]</code>&nbsp;。能量和为&nbsp;<code>|2 - 2| = 0</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [4,3,-1], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>10</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code>&nbsp;总共有 3 个长度为 2 的子序列：<code>[4,3]</code>&nbsp;，<code>[4,-1]</code>&nbsp;和&nbsp;<code>[3,-1]</code>&nbsp;。能量和为&nbsp;<code>|4 - 3| + |4 - (-1)| + |3 - (-1)| = 10</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 50</code></li>
	<li><code>-10<sup>8</sup> &lt;= nums[i] &lt;= 10<sup>8</sup> </code></li>
	<li><code>2 &lt;= k &lt;= n</code></li>
</ul>

## 解法

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i, j, k, mi)$，表示当前处理到第 $i$ 个元素，上一个选取的是第 $j$ 个元素，还需要选取 $k$ 个元素，当前的最小差值为 $mi$ 时，能量和的值。那么答案就是 $dfs(0, n, k, +\infty)$。

函数 $dfs(i, j, k, mi)$ 的执行过程如下：

-   如果 $i \geq n$，表示已经处理完了所有的元素，如果 $k = 0$，返回 $mi$，否则返回 $0$；
-   否则，我们可以选择不选取第 $i$ 个元素，可以获得的能量和为 $dfs(i + 1, j, k, mi)$；
-   也可以选择选取第 $i$ 个元素。如果 $j = n$，表示之前没有选取过元素，那么可以获得的能量和为 $dfs(i + 1, i, k - 1, mi)$；否则，可以获得的能量和为 $dfs(i + 1, i, k - 1, \min(mi, \text{nums}[i] - \text{nums}[j]))$。
-   我们累加上述结果，并对 $10^9 + 7$ 取模后返回。

为了避免重复计算，我们可以使用记忆化搜索的方法，将已经计算过的结果保存起来。

时间复杂度 $O(n^5)$，空间复杂度 $O(n^5)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def sumOfPowers(self, nums: List[int], k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int, mi: int) -> int:
            if i >= n:
                return mi if k == 0 else 0
            ans = dfs(i + 1, j, k, mi)
            if j == n:
                ans += dfs(i + 1, i, k - 1, mi)
            else:
                ans += dfs(i + 1, i, k - 1, min(mi, nums[i] - nums[j]))
            ans %= mod
            return ans

        mod = 10**9 + 7
        n = len(nums)
        nums.sort()
        return dfs(0, n, k, inf)
```

```java
class Solution {
    private Map<Long, Integer> f = new HashMap<>();
    private final int mod = (int) 1e9 + 7;
    private int[] nums;

    public int sumOfPowers(int[] nums, int k) {
        Arrays.sort(nums);
        this.nums = nums;
        return dfs(0, nums.length, k, Integer.MAX_VALUE);
    }

    private int dfs(int i, int j, int k, int mi) {
        if (i >= nums.length) {
            return k == 0 ? mi : 0;
        }
        long key = (1L * mi) << 18 | (i << 12) | (j << 6) | k;
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int ans = dfs(i + 1, j, k, mi);
        if (j == nums.length) {
            ans += dfs(i + 1, i, k - 1, mi);
        } else {
            ans += dfs(i + 1, i, k - 1, Math.min(mi, nums[i] - nums[j]));
        }
        ans %= mod;
        f.put(key, ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int sumOfPowers(vector<int>& nums, int k) {
        unordered_map<long long, int> f;
        const int mod = 1e9 + 7;
        int n = nums.size();
        sort(nums.begin(), nums.end());
        function<int(int, int, int, int)> dfs = [&](int i, int j, int k, int mi) {
            if (i >= n) {
                return k == 0 ? mi : 0;
            }
            long long key = (1LL * mi) << 18 | (i << 12) | (j << 6) | k;
            if (f.contains(key)) {
                return f[key];
            }
            long long ans = dfs(i + 1, j, k, mi);
            if (j == n) {
                ans += dfs(i + 1, i, k - 1, mi);
            } else {
                ans += dfs(i + 1, i, k - 1, min(mi, nums[i] - nums[j]));
            }
            ans %= mod;
            f[key] = ans;
            return f[key];
        };
        return dfs(0, n, k, INT_MAX);
    }
};
```

```go
func sumOfPowers(nums []int, k int) int {
	const mod int = 1e9 + 7
	sort.Ints(nums)
	n := len(nums)
	f := map[int]int{}
	var dfs func(i, j, k, mi int) int
	dfs = func(i, j, k, mi int) int {
		if i >= n {
			if k == 0 {
				return mi
			}
			return 0
		}
		key := mi<<18 | (i << 12) | (j << 6) | k
		if v, ok := f[key]; ok {
			return v
		}
		ans := dfs(i+1, j, k, mi)
		if j == n {
			ans += dfs(i+1, i, k-1, mi)
		} else {
			ans += dfs(i+1, i, k-1, min(mi, nums[i]-nums[j]))
		}
		ans %= mod
		f[key] = ans
		return ans
	}
	return dfs(0, n, k, math.MaxInt)
}
```

<!-- tabs:end -->

<!-- end -->
