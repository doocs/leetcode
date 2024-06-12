---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3180.Maximum%20Total%20Reward%20Using%20Operations%20I/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3180. 执行操作可获得的最大总奖励 I](https://leetcode.cn/problems/maximum-total-reward-using-operations-i)

[English Version](/solution/3100-3199/3180.Maximum%20Total%20Reward%20Using%20Operations%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>rewardValues</code>，长度为 <code>n</code>，代表奖励的值。</p>

<p>最初，你的总奖励 <code>x</code> 为 0，所有下标都是<strong> 未标记 </strong>的。你可以执行以下操作 <strong>任意次 </strong>：</p>

<ul>
	<li>从区间 <code>[0, n - 1]</code> 中选择一个 <strong>未标记 </strong>的下标 <code>i</code>。</li>
	<li>如果 <code>rewardValues[i]</code> <strong>大于</strong> 你当前的总奖励 <code>x</code>，则将 <code>rewardValues[i]</code> 加到 <code>x</code> 上（即 <code>x = x + rewardValues[i]</code>），并<strong> 标记</strong> 下标 <code>i</code>。</li>
</ul>

<p>以整数形式返回执行最优操作能够获得的<strong> 最大</strong><em> </em>总奖励。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">rewardValues = [1,1,3,3]</span></p>

<p><strong>输出：</strong><span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>依次标记下标 0 和 2，总奖励为 4，这是可获得的最大值。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">rewardValues = [1,6,4,3,2]</span></p>

<p><strong>输出：</strong><span class="example-io">11</span></p>

<p><strong>解释：</strong></p>

<p>依次标记下标 0、2 和 1。总奖励为 11，这是可获得的最大值。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rewardValues.length &lt;= 2000</code></li>
	<li><code>1 &lt;= rewardValues[i] &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 记忆化搜索 + 二分查找

我们可以对奖励值数组 `rewardValues` 进行排序，然后使用记忆化搜索的方法求解最大总奖励。

我们定义一个函数 $\text{dfs}(x)$，表示当前总奖励为 $x$ 时，能够获得的最大总奖励。那么答案为 $\text{dfs}(0)$。

函数 $\text{dfs}(x)$ 的执行过程如下：

1. 二分查找数组 `rewardValues` 中第一个大于 $x$ 的元素的下标 $i$；
2. 遍历数组 `rewardValues` 中从下标 $i$ 开始的元素，对于每个元素 $v$，计算 $v + \text{dfs}(x + v)$ 的最大值。
3. 将结果返回。

为了避免重复计算，我们使用记忆化数组 `f` 记录已经计算过的结果。

时间复杂度 $O(n \times (\log n + M))$，空间复杂度 $O(M)$。其中 $n$ 是数组 `rewardValues` 的长度，而 $M$ 是数组 `rewardValues` 中的最大值的两倍。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTotalReward(self, rewardValues: List[int]) -> int:
        @cache
        def dfs(x: int) -> int:
            i = bisect_right(rewardValues, x)
            ans = 0
            for v in rewardValues[i:]:
                ans = max(ans, v + dfs(x + v))
            return ans

        rewardValues.sort()
        return dfs(0)
```

#### Java

```java
class Solution {
    private int[] nums;
    private Integer[] f;

    public int maxTotalReward(int[] rewardValues) {
        nums = rewardValues;
        Arrays.sort(nums);
        int n = nums.length;
        f = new Integer[nums[n - 1] << 1];
        return dfs(0);
    }

    private int dfs(int x) {
        if (f[x] != null) {
            return f[x];
        }
        int i = Arrays.binarySearch(nums, x + 1);
        i = i < 0 ? -i - 1 : i;
        int ans = 0;
        for (; i < nums.length; ++i) {
            ans = Math.max(ans, nums[i] + dfs(x + nums[i]));
        }
        return f[x] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxTotalReward(vector<int>& rewardValues) {
        sort(rewardValues.begin(), rewardValues.end());
        int n = rewardValues.size();
        int f[rewardValues.back() << 1];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int x) {
            if (f[x] != -1) {
                return f[x];
            }
            auto it = upper_bound(rewardValues.begin(), rewardValues.end(), x);
            int ans = 0;
            for (; it != rewardValues.end(); ++it) {
                ans = max(ans, rewardValues[it - rewardValues.begin()] + dfs(x + *it));
            }
            return f[x] = ans;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func maxTotalReward(rewardValues []int) int {
	sort.Ints(rewardValues)
	n := len(rewardValues)
	f := make([]int, rewardValues[n-1]<<1)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(x int) int {
		if f[x] != -1 {
			return f[x]
		}
		i := sort.SearchInts(rewardValues, x+1)
		f[x] = 0
		for _, v := range rewardValues[i:] {
			f[x] = max(f[x], v+dfs(x+v))
		}
		return f[x]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maxTotalReward(rewardValues: number[]): number {
    rewardValues.sort((a, b) => a - b);
    const search = (x: number): number => {
        let [l, r] = [0, rewardValues.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (rewardValues[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const f: number[] = Array(rewardValues.at(-1)! << 1).fill(-1);
    const dfs = (x: number): number => {
        if (f[x] !== -1) {
            return f[x];
        }
        let ans = 0;
        for (let i = search(x); i < rewardValues.length; ++i) {
            ans = Math.max(ans, rewardValues[i] + dfs(x + rewardValues[i]));
        }
        return (f[x] = ans);
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
