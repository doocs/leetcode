# [1815. Maximum Number of Groups Getting Fresh Donuts](https://leetcode.com/problems/maximum-number-of-groups-getting-fresh-donuts)

[中文文档](/solution/1800-1899/1815.Maximum%20Number%20of%20Groups%20Getting%20Fresh%20Donuts/README.md)

## Description

<p>There is a donuts shop that bakes donuts in batches of <code>batchSize</code>. They have a rule where they must serve <strong>all</strong> of the donuts of a batch before serving any donuts of the next batch. You are given an integer <code>batchSize</code> and an integer array <code>groups</code>, where <code>groups[i]</code> denotes that there is a group of <code>groups[i]</code> customers that will visit the shop. Each customer will get exactly one donut.</p>

<p>When a group visits the shop, all customers of the group must be served before serving any of the following groups. A group will be happy if they all get fresh donuts. That is, the first customer of the group does not receive a donut that was left over from the previous group.</p>

<p>You can freely rearrange the ordering of the groups. Return <em>the <strong>maximum</strong> possible number of happy groups after rearranging the groups.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> batchSize = 3, groups = [1,2,3,4,5,6]
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can arrange the groups as [6,2,4,5,1,3]. Then the 1<sup>st</sup>, 2<sup>nd</sup>, 4<sup>th</sup>, and 6<sup>th</sup> groups will be happy.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> batchSize = 4, groups = [1,3,2,5,2,2,1,6]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= batchSize &lt;= 9</code></li>
	<li><code>1 &lt;= groups.length &lt;= 30</code></li>
	<li><code>1 &lt;= groups[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
