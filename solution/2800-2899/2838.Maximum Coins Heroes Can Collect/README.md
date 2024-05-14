# [2838. 英雄可以获得的最大金币数 🔒](https://leetcode.cn/problems/maximum-coins-heroes-can-collect)

[English Version](/solution/2800-2899/2838.Maximum%20Coins%20Heroes%20Can%20Collect/README_EN.md)

<!-- tags:数组,双指针,二分查找,前缀和,排序 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>在一场战斗中，<code>n</code> 位英雄正在试图击败 <code>m</code> 个怪物。你将得到两个下标从 <strong>1</strong> 开始的<strong> 正整数 </strong>数组 <code><font face="monospace">heroes</font></code> 和 <code><font face="monospace">monsters</font></code>，长度分别为 <code>n</code> 和 <code>m</code>。数组 <code><font face="monospace">heroes</font>[i]</code> 代表第 <code>i</code> 位英雄的力量，而 <code><font face="monospace">monsters</font>[i]</code> 代表第 <code>i</code> 个怪物的力量。</p>

<p>如果 <code>monsters[j] &lt;= heroes[i]</code>，则第 <code>i</code> 位英雄可以击败第 <code>j</code> 个怪物。</p>

<p>你还将获得一个下标从 <strong>1</strong> 开始的&nbsp;<strong>正整数</strong> 数组 <code>coins</code>，长度为 <code>m</code> 。数组 <code>coins[i]</code> 表示每位英雄在击败第 <code>i</code> 个怪物后可以获得的金币数。</p>

<p>返回一个长度为 <code>n</code> 的数组 <code>ans</code>，其中 <code>ans[i]</code> 是第 <code>i</code> 位英雄从这场战斗中能收集到的 <strong>最大 </strong>金币数。</p>

<p><strong>注意</strong></p>

<ul>
	<li>击败怪物后，英雄的生命值不会减少。</li>
	<li>多位英雄可以击败同一个怪物，但每个怪物只能被同一位英雄击败一次。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>heroes = [1,4,2], monsters = [1,1,5,2,3], coins = [2,3,4,5,6]
<strong>输出：</strong>[5,16,10]
<strong>解释：</strong>对于每位英雄，我们列出了所有他可以击败的怪物的下标：
第 1 位英雄：[1,2]，因为这位英雄的力量为 1，且 monsters[1], monsters[2] &lt;= 1。因此这位英雄收集的金币为 coins[1] + coins[2] = 5 金币。
第 2 位英雄：[1,2,4,5]，因为这位英雄的力量为 4，且 monsters[1], monsters[2], monsters[4], monsters[5] &lt;= 4。因此这位英雄收集的金币为 coins[1] + coins[2] + coins[4] + coins[5] = 16 金币。
第 3 位英雄：[1,2,4]，因为这位英雄的力量为 2，且 monsters[1], monsters[2], monsters[4] &lt;= 2。因此这位英雄收集的金币为 coins[1] + coins[2] + coins[4] = 10 金币。
因此答案为 [5,16,10]。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>heroes = [5], monsters = [2,3,1,2], coins = [10,6,5,2]
<strong>输出：</strong>[23]
<strong>解释：</strong>这位英雄可以击败所有怪物，因为 monsters[i] &lt;= 5。所以他收集了所有的金币：coins[1] + coins[2] + coins[3] + coins[4] = 23，因此答案为 [23]。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>heroes = [4,4], monsters = [5,7,8], coins = [1,1,1]
<strong>输出：</strong>[0,0]
<strong>解释：</strong>在这个例子中，没有英雄可以击败怪物。因此答案为 [0,0] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == heroes.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m == monsters.length &lt;= 10<sup>5</sup></code></li>
	<li><code>coins.length == m</code></li>
	<li><code>1 &lt;= heroes[i], monsters[i], coins[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：排序 + 前缀和 + 二分查找

我们可以将怪物和金币按照怪物的战斗力从小到大排序，然后使用前缀和计算每个英雄打败前 $i$ 个怪物可以获得的金币总数。

接下来，对于每个英雄，我们可以使用二分查找找到他可以打败的最大的怪物，然后使用前缀和计算他可以获得的金币总数即可。

时间复杂度 $O((m + n) \times \log n)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别是怪物和英雄的数量。

<!-- tabs:start -->

```python
class Solution:
    def maximumCoins(
        self, heroes: List[int], monsters: List[int], coins: List[int]
    ) -> List[int]:
        m = len(monsters)
        idx = sorted(range(m), key=lambda i: monsters[i])
        s = list(accumulate((coins[i] for i in idx), initial=0))
        ans = []
        for h in heroes:
            i = bisect_right(idx, h, key=lambda i: monsters[i])
            ans.append(s[i])
        return ans
```

```java
class Solution {
    public long[] maximumCoins(int[] heroes, int[] monsters, int[] coins) {
        int m = monsters.length;
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }

        Arrays.sort(idx, Comparator.comparingInt(j -> monsters[j]));
        long[] s = new long[m + 1];
        for (int i = 0; i < m; ++i) {
            s[i + 1] = s[i] + coins[idx[i]];
        }
        int n = heroes.length;
        long[] ans = new long[n];
        for (int k = 0; k < n; ++k) {
            int i = search(monsters, idx, heroes[k]);
            ans[k] = s[i];
        }
        return ans;
    }

    private int search(int[] nums, Integer[] idx, int x) {
        int l = 0, r = idx.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[idx[mid]] > x) {
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
    vector<long long> maximumCoins(vector<int>& heroes, vector<int>& monsters, vector<int>& coins) {
        int m = monsters.size();
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return monsters[i] < monsters[j];
        });
        long long s[m + 1];
        s[0] = 0;
        for (int i = 1; i <= m; ++i) {
            s[i] = s[i - 1] + coins[idx[i - 1]];
        }
        vector<long long> ans;
        auto search = [&](int x) {
            int l = 0, r = m;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (monsters[idx[mid]] > x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        };
        for (int h : heroes) {
            ans.push_back(s[search(h)]);
        }
        return ans;
    }
};
```

```go
func maximumCoins(heroes []int, monsters []int, coins []int) (ans []int64) {
	m := len(monsters)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return monsters[idx[i]] < monsters[idx[j]] })
	s := make([]int64, m+1)
	for i, j := range idx {
		s[i+1] = s[i] + int64(coins[j])
	}
	for _, h := range heroes {
		i := sort.Search(m, func(i int) bool { return monsters[idx[i]] > h })
		ans = append(ans, s[i])
	}
	return
}
```

```ts
function maximumCoins(heroes: number[], monsters: number[], coins: number[]): number[] {
    const m = monsters.length;
    const idx: number[] = Array.from({ length: m }, (_, i) => i);
    idx.sort((i, j) => monsters[i] - monsters[j]);
    const s: number[] = Array(m + 1).fill(0);
    for (let i = 0; i < m; ++i) {
        s[i + 1] = s[i] + coins[idx[i]];
    }
    const search = (x: number): number => {
        let l = 0;
        let r = m;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (monsters[idx[mid]] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    return heroes.map(h => s[search(h)]);
}
```

<!-- tabs:end -->

<!-- end -->
