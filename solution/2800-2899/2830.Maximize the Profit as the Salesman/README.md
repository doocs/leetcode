---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2830.Maximize%20the%20Profit%20as%20the%20Salesman/README.md
rating: 1851
source: 第 359 场周赛 Q3
tags:
    - 数组
    - 哈希表
    - 二分查找
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [2830. 销售利润最大化](https://leetcode.cn/problems/maximize-the-profit-as-the-salesman)

[English Version](/solution/2800-2899/2830.Maximize%20the%20Profit%20as%20the%20Salesman/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> 表示数轴上的房屋数量，编号从 <code>0</code> 到 <code>n - 1</code> 。</p>

<p>另给你一个二维整数数组 <code>offers</code> ，其中 <code>offers[i] = [start<sub>i</sub>, end<sub>i</sub>, gold<sub>i</sub>]</code> 表示第 <code>i</code> 个买家想要以 <code>gold<sub>i</sub></code> 枚金币的价格购买从 <code>start<sub>i</sub></code> 到 <code>end<sub>i</sub></code> 的所有房屋。</p>

<p>作为一名销售，你需要有策略地选择并销售房屋使自己的收入最大化。</p>

<p>返回你可以赚取的金币的最大数目。</p>

<p><strong>注意</strong> 同一所房屋不能卖给不同的买家，并且允许保留一些房屋不进行出售。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 5, offers = [[0,0,1],[0,2,2],[1,3,2]]
<strong>输出：</strong>3
<strong>解释：</strong>
有 5 所房屋，编号从 0 到 4 ，共有 3 个购买要约。
将位于 [0,0] 范围内的房屋以 1 金币的价格出售给第 1 位买家，并将位于 [1,3] 范围内的房屋以 2 金币的价格出售给第 3 位买家。
可以证明我们最多只能获得 3 枚金币。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5, offers = [[0,0,1],[0,2,10],[1,3,2]]
<strong>输出：</strong>10
<strong>解释：</strong>有 5 所房屋，编号从 0 到 4 ，共有 3 个购买要约。
将位于 [0,2] 范围内的房屋以 10 金币的价格出售给第 2 位买家。
可以证明我们最多只能获得 10 枚金币。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= offers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>offers[i].length == 3</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= gold<sub>i</sub> &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 二分查找 + 动态规划

我们将所有的购买要约按照 $end$ 从小到大排序，然后使用动态规划求解。

定义 $f[i]$ 表示前 $i$ 个购买要约中，我们可以获得的最大金币数。答案即为 $f[n]$。

对于 $f[i]$，我们可以选择不卖出第 $i$ 个购买要约，此时 $f[i] = f[i - 1]$；也可以选择卖出第 $i$ 个购买要约，此时 $f[i] = f[j] + gold_i$，其中 $j$ 是满足 $end_j \leq start_i$ 的最大下标。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是购买要约的数量。

<!-- tabs:start -->

```python
class Solution:
    def maximizeTheProfit(self, n: int, offers: List[List[int]]) -> int:
        offers.sort(key=lambda x: x[1])
        f = [0] * (len(offers) + 1)
        g = [x[1] for x in offers]
        for i, (s, _, v) in enumerate(offers, 1):
            j = bisect_left(g, s)
            f[i] = max(f[i - 1], f[j] + v)
        return f[-1]
```

```java
class Solution {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        offers.sort((a, b) -> a.get(1) - b.get(1));
        n = offers.size();
        int[] f = new int[n + 1];
        int[] g = new int[n];
        for (int i = 0; i < n; ++i) {
            g[i] = offers.get(i).get(1);
        }
        for (int i = 1; i <= n; ++i) {
            var o = offers.get(i - 1);
            int j = search(g, o.get(0));
            f[i] = Math.max(f[i - 1], f[j] + o.get(2));
        }
        return f[n];
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int maximizeTheProfit(int n, vector<vector<int>>& offers) {
        sort(offers.begin(), offers.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[1] < b[1];
        });
        n = offers.size();
        vector<int> f(n + 1);
        vector<int> g;
        for (auto& o : offers) {
            g.push_back(o[1]);
        }
        for (int i = 1; i <= n; ++i) {
            auto o = offers[i - 1];
            int j = lower_bound(g.begin(), g.end(), o[0]) - g.begin();
            f[i] = max(f[i - 1], f[j] + o[2]);
        }
        return f[n];
    }
};
```

```go
func maximizeTheProfit(n int, offers [][]int) int {
	sort.Slice(offers, func(i, j int) bool { return offers[i][1] < offers[j][1] })
	n = len(offers)
	f := make([]int, n+1)
	g := []int{}
	for _, o := range offers {
		g = append(g, o[1])
	}
	for i := 1; i <= n; i++ {
		j := sort.SearchInts(g, offers[i-1][0])
		f[i] = max(f[i-1], f[j]+offers[i-1][2])
	}
	return f[n]
}
```

```ts
function maximizeTheProfit(n: number, offers: number[][]): number {
    offers.sort((a, b) => a[1] - b[1]);
    n = offers.length;
    const f: number[] = Array(n + 1).fill(0);
    const g = offers.map(x => x[1]);
    const search = (x: number) => {
        let l = 0;
        let r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (g[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i <= n; ++i) {
        const j = search(offers[i - 1][0]);
        f[i] = Math.max(f[i - 1], f[j] + offers[i - 1][2]);
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
