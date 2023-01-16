# [1815. 得到新鲜甜甜圈的最多组数](https://leetcode.cn/problems/maximum-number-of-groups-getting-fresh-donuts)

[English Version](/solution/1800-1899/1815.Maximum%20Number%20of%20Groups%20Getting%20Fresh%20Donuts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个甜甜圈商店，每批次都烤 <code>batchSize</code> 个甜甜圈。这个店铺有个规则，就是在烤一批新的甜甜圈时，之前 <strong>所有</strong> 甜甜圈都必须已经全部销售完毕。给你一个整数 <code>batchSize</code> 和一个整数数组 <code>groups</code> ，数组中的每个整数都代表一批前来购买甜甜圈的顾客，其中 <code>groups[i]</code> 表示这一批顾客的人数。每一位顾客都恰好只要一个甜甜圈。</p>

<p>当有一批顾客来到商店时，他们所有人都必须在下一批顾客来之前购买完甜甜圈。如果一批顾客中第一位顾客得到的甜甜圈不是上一组剩下的，那么这一组人都会很开心。</p>

<p>你可以随意安排每批顾客到来的顺序。请你返回在此前提下，<strong>最多</strong> 有多少组人会感到开心。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>batchSize = 3, groups = [1,2,3,4,5,6]
<b>输出：</b>4
<b>解释：</b>你可以将这些批次的顾客顺序安排为 [6,2,4,5,1,3] 。那么第 1，2，4，6 组都会感到开心。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>batchSize = 4, groups = [1,3,2,5,2,2,1,6]
<b>输出：</b>4
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= batchSize <= 9</code></li>
	<li><code>1 <= groups.length <= 30</code></li>
	<li><code>1 <= groups[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 状态压缩 + 记忆化搜索**

题目实际上要我们找到一种安排顺序，使得前缀和（这里指的是“人数”）与 $batchSize$ 取模后为 $0$ 的组数最多。因此，我们可以将所有顾客按组分成两类：

-   人数为 $batchSize$ 的整数倍的顾客，这些顾客不会对下一组顾客的甜甜圈产生影响，我们可以贪心地优先安排这些组的顾客，那么这些组的顾客都会感到开心，“初始答案”为这些组的数量；
-   人数不为 $batchSize$ 的整数倍的顾客，这些顾客的安排顺序会影响下一组顾客的甜甜圈。我们可以对这里每一组的人数 $v$ 模 $batchSize$，得到的这些余数构成一个集合，集合中的元素值范围是 $[1,2...,batchSize-1]$。数组 $groups$ 的长度最大为 $30$，因此，每个余数的数量最大不超过 $30$。我们可以用 $5$ 个二进制位来表示一个余数的数量，而 $batchSize$ 最大为 $9$，那么表示这些余数以及对应的数量总共需要的二进制位就是 $5\times (9-1)=40$。我们可以用一个 $64$ 位整数 $state$ 来表示。

接下来，我们设计一个函数 $dfs(state, mod)$，表示安排状态为 $state$，且当前前缀余数为 $mod$ 时，能使得多少组感到开心。那么我们在“初始答案”加上 $dfs(state, 0)$，即为最终答案。

函数 $dfs(state, mod)$ 的实现逻辑如下：

我们枚举 $1$ 到 $batchSize-1$ 的每一个余数 $i$，如果余数 $i$ 的数量不为 $0$，那么我们可以将余数 $i$ 的数量减去 $1$，将当前前缀余数加上 $i$ 并且对 $batchSize$ 取模，然后递归调用函数 $dfs$，求出子状态的最优解，取最大值即可。最后判断 $mod$ 是否为 $0$，如果为 $0$，我们在最大值上加 $1$ 后返回，否则直接返回最大值。

过程中，我们可以使用记忆化搜索来避免状态的重复计算。

时间复杂度不超过 $O(10^7)$，空间复杂度不超过 $O(10^6)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxHappyGroups(self, batchSize: int, groups: List[int]) -> int:
        @cache
        def dfs(state, mod):
            res = 0
            x = int(mod == 0)
            for i in range(1, batchSize):
                if state >> (i * 5) & 31:
                    t = dfs(state - (1 << (i * 5)), (mod + i) % batchSize)
                    res = max(res, t + x)
            return res

        state = ans = 0
        for v in groups:
            i = v % batchSize
            ans += i == 0
            if i:
                state += 1 << (i * 5)
        ans += dfs(state, 0)
        return ans
```

```python
class Solution:
    def maxHappyGroups(self, batchSize: int, groups: List[int]) -> int:
        @cache
        def dfs(state, x):
            if state == mask:
                return 0
            vis = [False] * batchSize
            res = 0
            for i, v in enumerate(g):
                if state >> i & 1 == 0 and not vis[v]:
                    vis[v] = True
                    y = (x + v) % batchSize
                    res = max(res, dfs(state | 1 << i, y))
            return res + (x == 0)

        g = [v % batchSize for v in groups if v % batchSize]
        mask = (1 << len(g)) - 1
        return len(groups) - len(g) + dfs(0, 0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Map<Long, Integer> f = new HashMap<>();
    private int size;

    public int maxHappyGroups(int batchSize, int[] groups) {
        size = batchSize;
        int ans = 0;
        long state = 0;
        for (int g : groups) {
            int i = g % size;
            if (i == 0) {
                ++ans;
            } else {
                state += 1l << (i * 5);
            }
        }
        ans += dfs(state, 0);
        return ans;
    }

    private int dfs(long state, int mod) {
        if (f.containsKey(state)) {
            return f.get(state);
        }
        int res = 0;
        for (int i = 1; i < size; ++i) {
            if ((state >> (i * 5) & 31) != 0) {
                int t = dfs(state - (1l << (i * 5)), (mod + i) % size);
                res = Math.max(res, t + (mod == 0 ? 1 : 0));
            }
        }
        f.put(state, res);
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxHappyGroups(int batchSize, vector<int>& groups) {
        using ll = long long;
        unordered_map<ll, int> f;
        ll state = 0;
        int ans = 0;
        for (auto& v : groups) {
            int i = v % batchSize;
            ans += i == 0;
            if (i) {
                state += 1ll << (i * 5);
            }
        }
        function<int(ll, int)> dfs = [&](ll state, int mod) {
            if (f.count(state)) {
                return f[state];
            }
            int res = 0;
            int x = mod == 0;
            for (int i = 1; i < batchSize; ++i) {
                if (state >> (i * 5) & 31) {
                    int t = dfs(state - (1ll << (i * 5)), (mod + i) % batchSize);
                    res = max(res, t + x);
                }
            }
            return f[state] = res;
        };
        ans += dfs(state, 0);
        return ans;
    }
};
```

### **Go**

```go
func maxHappyGroups(batchSize int, groups []int) (ans int) {
	state := 0
	for _, v := range groups {
		i := v % batchSize
		if i == 0 {
			ans++
		} else {
			state += 1 << (i * 5)
		}
	}
	f := map[int]int{}
	var dfs func(int, int) int
	dfs = func(state, mod int) int {
		if v, ok := f[state]; ok {
			return v
		}
		res := 0
		x := 0
		if mod == 0 {
			x = 1
		}
		for i := 1; i < batchSize; i++ {
			if state>>(i*5)&31 != 0 {
				t := dfs(state-1<<(i*5), (mod+i)%batchSize)
				res = max(res, t+x)
			}
		}
		f[state] = res
		return res
	}
	ans += dfs(state, 0)
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
