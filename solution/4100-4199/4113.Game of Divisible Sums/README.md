---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4113. 整除和博弈](https://leetcode.cn/problems/game-of-divisible-sums)

[English Version](/solution/4100-4199/4113.Game%20of%20Divisible%20Sums/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 和 Bob 正在用一个整数数组 <code>nums</code> 玩游戏。Alice 先手。</p>

<p>每个回合，当前玩家可以取走并移除<strong>&nbsp;最左边的一个元素</strong>&nbsp;，或者在至少还剩两个元素时，取走并移除&nbsp;<strong>最左边的两个元素</strong>。</p>

<p>本回合取走的元素之&nbsp;<strong>和&nbsp;</strong>决定接下来由谁行动：</p>

<ul>
	<li>如果该总和能被 3 整除，则接下来由另一位玩家行动。</li>
	<li>否则，当前玩家继续行动。</li>
</ul>

<p>一位玩家的&nbsp;<strong>得分</strong>&nbsp;是其取走的所有元素的值之和。</p>

<p>两位玩家都采用最优策略，各自力求<strong>&nbsp;最大化&nbsp;</strong>自己的得分。</p>

<p>当所有元素都被取走时，游戏结束。</p>

<p>返回 Alice 的得分&nbsp;<strong>减去</strong> Bob 的得分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,6,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的回合序列如下：</p>

<ol>
	<li>Alice 取走最左边的两个元素。它们的总和为 <code>3 + 6 = 9</code>，能被 3 整除，因此接下来由 Bob 行动。此时 Alice 的得分为 9，剩余数组为 <code>[4]</code>。</li>
	<li>Bob 取走唯一剩余的元素。他的得分为 4，游戏结束。</li>
</ol>

<p>Alice 的得分减去 Bob 的得分为 <code>9 - 4 = 5</code>，因此答案为 5。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [6,1,2,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的回合序列如下：</p>

<ol>
	<li>Alice 取走最左边的两个元素。它们的总和为 <code>6 + 1 = 7</code>，不能被 3 整除，因此 Alice 继续行动。此时她的得分为 7，剩余数组为 <code>[2, 1, 2]</code>。</li>
	<li>Alice 取走最左边的一个元素。它的值为 2，不能被 3 整除，因此 Alice 继续行动。此时她的得分为 <code>7 + 2 = 9</code>，剩余数组为 <code>[1, 2]</code>。</li>
	<li>Alice 取走剩余的两个元素。此时她的得分为 <code>9 + 1 + 2 = 12</code>，游戏结束。</li>
</ol>

<p>Bob 没有取走任何元素，因此他的得分为 0。Alice 的得分减去 Bob 的得分为 <code>12 - 0 = 12</code>，因此答案为 12。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,4,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">-3</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的回合序列如下：</p>

<ol>
	<li>Alice 取走最左边的两个元素。它们的总和为 <code>3 + 3 = 6</code>，能被 3 整除，因此接下来由 Bob 行动。此时 Alice 的得分为 6，剩余数组为 <code>[4, 5]</code>。</li>
	<li>Bob 取走剩余的两个元素。他的得分为 <code>4 + 5 = 9</code>，游戏结束。</li>
</ol>

<p>Alice 的得分减去 Bob 的得分为 <code>6 - 9 = -3</code>，因此答案为 -3。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> $n$ 达到 $10^5$。每一步都可以取一个或两个元素，整局对局的分支是指数级的，无法通过。
>
> 后缀里的元素最终都会被取走，双方得分之和是定值。最大化自己的得分，就等于最大化自己与对手的分差。轮到谁行动，只取决于刚取走的和能否被 $3$ 整除。
>
> 因此状态只保留剩余后缀的起点 $i$。 $\textit{dfs}(i)$ 是当前行动者在该后缀上能取得的最大分差。和能被 $3$ 整除时对手接着行动，分差要减去后续的 $\textit{dfs}$；否则自己继续，分差要加上后续的 $\textit{dfs}$。
>
> 每个起点尝试取一个元素或取两个元素，并用记忆化保证每个下标只算一次。

<!-- thinking:end -->

设 $\textit{dfs}(i)$ 为轮到当前玩家时，从下标 $i$ 开始的后缀上，当前玩家得分减去对手得分的最大值。答案是 $\textit{dfs}(0)$。

后缀上的元素最终都会被取走，双方得分之和固定。最大化自己的得分，就等于最大化自己与对手的分差。对手在自己行动时也会最大化自己的分差，所以当前玩家要按接下来是谁行动，减去或加上对方算出的 $\textit{dfs}$。

若 $i$ 已经越过数组末尾，返回 $0$。

当前玩家可以取走 $\textit{nums}[i]$。记 $x=\textit{nums}[i]$。若 $x$ 能被 $3$ 整除，接下来由对手行动，这一步的分差是 $x-\textit{dfs}(i+1)$；否则当前玩家继续行动，分差是 $x+\textit{dfs}(i+1)$。

后面还有元素时，也可以取走 $\textit{nums}[i]$ 与 $\textit{nums}[i+1]$。记它们的和为 $s$。 $s$ 能被 $3$ 整除时，分差是 $s-\textit{dfs}(i+2)$；否则是 $s+\textit{dfs}(i+2)$。

两种取法取较大值，并用记忆化保存 $\textit{dfs}(i)$。每个下标只计算一次。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDifference(self, nums: list[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(nums):
                return 0
            x = nums[i]
            res = x - dfs(i + 1) if x % 3 == 0 else x + dfs(i + 1)
            if i + 1 < len(nums):
                s = x + nums[i + 1]
                t = s - dfs(i + 2) if s % 3 == 0 else s + dfs(i + 2)
                res = max(res, t)
            return res

        return dfs(0)
```

#### Java

```java
class Solution {
    private int[] nums;
    private Long[] memo;

    public long maxDifference(int[] nums) {
        this.nums = nums;
        this.memo = new Long[nums.length];
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= nums.length) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        int x = nums[i];
        long res = x % 3 == 0 ? x - dfs(i + 1) : x + dfs(i + 1);
        if (i + 1 < nums.length) {
            long s = x + nums[i + 1];
            long t = s % 3 == 0 ? s - dfs(i + 2) : s + dfs(i + 2);
            res = Math.max(res, t);
        }
        memo[i] = res;
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxDifference(vector<int>& nums) {
        int n = nums.size();
        vector<long long> memo(n, LLONG_MIN);
        auto dfs = [&](this auto&& dfs, int i) -> long long {
            if (i >= n) {
                return 0;
            }
            if (memo[i] != LLONG_MIN) {
                return memo[i];
            }
            int x = nums[i];
            long long res = x % 3 == 0 ? x - dfs(i + 1) : x + dfs(i + 1);
            if (i + 1 < n) {
                long long s = x + nums[i + 1];
                long long t = s % 3 == 0 ? s - dfs(i + 2) : s + dfs(i + 2);
                res = max(res, t);
            }
            memo[i] = res;
            return res;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func maxDifference(nums []int) int64 {
	n := len(nums)
	const INF int64 = 1 << 62
	memo := make([]int64, n)
	for i := range memo {
		memo[i] = -INF
	}
	var dfs func(i int) int64
	dfs = func(i int) int64 {
		if i >= n {
			return 0
		}
		if memo[i] != -INF {
			return memo[i]
		}
		x := nums[i]
		var res int64
		if x%3 == 0 {
			res = int64(x) - dfs(i+1)
		} else {
			res = int64(x) + dfs(i+1)
		}
		if i+1 < n {
			s := int64(x + nums[i+1])
			var t int64
			if s%3 == 0 {
				t = s - dfs(i+2)
			} else {
				t = s + dfs(i+2)
			}
			res = max(res, t)
		}
		memo[i] = res
		return res
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maxDifference(nums: number[]): number {
    const n = nums.length;
    const memo: number[] = new Array(n).fill(NaN);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (!Number.isNaN(memo[i])) {
            return memo[i];
        }
        const x = nums[i];
        let res = x % 3 === 0 ? x - dfs(i + 1) : x + dfs(i + 1);
        if (i + 1 < n) {
            const s = x + nums[i + 1];
            const t = s % 3 === 0 ? s - dfs(i + 2) : s + dfs(i + 2);
            res = Math.max(res, t);
        }
        memo[i] = res;
        return res;
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
