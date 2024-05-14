---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0446.Arithmetic%20Slices%20II%20-%20Subsequence/README.md
tags:
    - 数组
    - 动态规划
---

# [446. 等差数列划分 II - 子序列](https://leetcode.cn/problems/arithmetic-slices-ii-subsequence)

[English Version](/solution/0400-0499/0446.Arithmetic%20Slices%20II%20-%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，返回 <code>nums</code> 中所有 <strong>等差子序列</strong> 的数目。</p>

<p>如果一个序列中 <strong>至少有三个元素</strong> ，并且任意两个相邻元素之差相同，则称该序列为等差序列。</p>

<ul>
	<li>例如，<code>[1, 3, 5, 7, 9]</code>、<code>[7, 7, 7, 7]</code> 和 <code>[3, -1, -5, -9]</code> 都是等差序列。</li>
	<li>再例如，<code>[1, 1, 2, 5, 7]</code> 不是等差序列。</li>
</ul>

<p>数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。</p>

<ul>
	<li>例如，<code>[2,5,10]</code> 是 <code>[1,2,1,<em><strong>2</strong></em>,4,1,<strong><em>5</em></strong>,<em><strong>10</strong></em>]</code> 的一个子序列。</li>
</ul>

<p>题目数据保证答案是一个 <strong>32-bit</strong> 整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4,6,8,10]
<strong>输出：</strong>7
<strong>解释：</strong>所有的等差子序列为：
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [7,7,7,7,7]
<strong>输出：</strong>16
<strong>解释：</strong>数组中的任意子序列都是等差子序列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1&nbsp; &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

### 方法一：动态规划 + 哈希表

我们定义 $f[i][d]$ 表示以 $nums[i]$ 为结尾，公差为 $d$ 的弱等差子序列（最少有两个元素）的个数。由于 $d$ 的范围很大，所以我们使用哈希表来统计。

接下来，我们枚举 $nums$ 中的所有元素对 $(nums[i], nums[j])$，其中 $j \lt i$。我们将其作为等差数列的最后两个元素，由此即可得到公差 $d = nums[i] - nums[j]$。由于公差相同，我们可以将 $nums[i]$ 加到以 $nums[j]$ 为结尾的弱等差子序列的末尾，此时以 $nums[i]$ 为结尾的等差子序列的数量为 $f[j][d]$，我们将其加入答案。然后，我们再将 $nums[i]$ 加到以 $nums[j]$ 为结尾的弱等差子序列的末尾，这对应着状态转移 $f[i][d] += f[j][d]$。同时，$(nums[i], nums[j])$ 这一对元素也可以当作一个弱等差子序列，因此有状态转移 $f[i][d] += f[j][d] + 1$。

枚举结束，返回答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        f = [defaultdict(int) for _ in nums]
        ans = 0
        for i, x in enumerate(nums):
            for j, y in enumerate(nums[:i]):
                d = x - y
                ans += f[j][d]
                f[i][d] += f[j][d] + 1
        return ans
```

```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] f = new Map[n];
        Arrays.setAll(f, k -> new HashMap<>());
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                Long d = 1L * nums[i] - nums[j];
                int cnt = f[j].getOrDefault(d, 0);
                ans += cnt;
                f[i].merge(d, cnt + 1, Integer::sum);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        unordered_map<long long, int> f[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long long d = 1LL * nums[i] - nums[j];
                int cnt = f[j][d];
                ans += cnt;
                f[i][d] += cnt + 1;
            }
        }
        return ans;
    }
};
```

```go
func numberOfArithmeticSlices(nums []int) (ans int) {
	f := make([]map[int]int, len(nums))
	for i := range f {
		f[i] = map[int]int{}
	}
	for i, x := range nums {
		for j, y := range nums[:i] {
			d := x - y
			cnt := f[j][d]
			ans += cnt
			f[i][d] += cnt + 1
		}
	}
	return
}
```

```ts
function numberOfArithmeticSlices(nums: number[]): number {
    const n = nums.length;
    const f: Map<number, number>[] = new Array(n).fill(0).map(() => new Map());
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            const d = nums[i] - nums[j];
            const cnt = f[j].get(d) || 0;
            ans += cnt;
            f[i].set(d, (f[i].get(d) || 0) + cnt + 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
