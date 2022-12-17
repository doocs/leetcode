# [1766. 互质树](https://leetcode.cn/problems/tree-of-coprimes)

[English Version](/solution/1700-1799/1766.Tree%20of%20Coprimes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code> 个节点的树（也就是一个无环连通无向图），节点编号从 <code>0</code> 到 <code>n - 1</code> ，且恰好有 <code>n - 1</code> 条边，每个节点有一个值。树的 <strong>根节点</strong> 为 0 号点。</p>

<p>给你一个整数数组 <code>nums</code> 和一个二维数组 <code>edges</code> 来表示这棵树。<code>nums[i]</code> 表示第 <code>i</code> 个点的值，<code>edges[j] = [u<sub>j</sub>, v<sub>j</sub>]</code> 表示节点 <code>u<sub>j</sub></code> 和节点 <code>v<sub>j</sub></code> 在树中有一条边。</p>

<p>当 <code>gcd(x, y) == 1</code> ，我们称两个数 <code>x</code> 和 <code>y</code> 是 <strong>互质的</strong> ，其中 <code>gcd(x, y)</code> 是 <code>x</code> 和 <code>y</code> 的 <strong>最大公约数</strong> 。</p>

<p>从节点 <code>i</code> 到 <strong>根</strong> 最短路径上的点都是节点 <code>i</code> 的祖先节点。一个节点 <strong>不是</strong> 它自己的祖先节点。</p>

<p>请你返回一个大小为 <code>n</code> 的数组 <code>ans</code> ，其中<em> </em><code>ans[i]</code>是离节点 <code>i</code> 最近的祖先节点且满足<em> </em><code>nums[i]</code> 和<em> </em><code>nums[ans[i]]</code> 是 <strong>互质的</strong> ，如果不存在这样的祖先节点，<code>ans[i]</code> 为 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1766.Tree%20of%20Coprimes/images/untitled-diagram.png" style="width: 191px; height: 281px;" /></strong></p>

<pre>
<b>输入：</b>nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
<b>输出：</b>[-1,0,0,1]
<b>解释：</b>上图中，每个节点的值在括号中表示。
- 节点 0 没有互质祖先。
- 节点 1 只有一个祖先节点 0 。它们的值是互质的（gcd(2,3) == 1）。
- 节点 2 有两个祖先节点，分别是节点 1 和节点 0 。节点 1 的值与它的值不是互质的（gcd(3,3) == 3）但节点 0 的值是互质的(gcd(2,3) == 1)，所以节点 0 是最近的符合要求的祖先节点。
- 节点 3 有两个祖先节点，分别是节点 1 和节点 0 。它与节点 1 互质（gcd(3,2) == 1），所以节点 1 是离它最近的符合要求的祖先节点。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1766.Tree%20of%20Coprimes/images/untitled-diagram1.png" style="width: 441px; height: 291px;" /></p>

<pre>
<strong>输入：</strong>nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
<b>输出：</b>[-1,0,-1,0,0,0,-1]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>1 <= nums[i] <= 50</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[j].length == 2</code></li>
	<li><code>0 <= u<sub>j</sub>, v<sub>j</sub> < n</code></li>
	<li><code>u<sub>j</sub> != v<sub>j</sub></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理 + 枚举 + 栈 + 回溯**

由于题目中 $nums[i]$ 的取值范围为 $[1, 50]$，因此我们可以预处理出每个数的所有互质数，记录在数组 $f$ 中，其中 $f[i]$ 表示 $i$ 的所有互质数。

接下来我们可以使用回溯的方法，从根节点开始遍历整棵树，对于每个节点 $i$，我们可以通过 $f$ 数组得到 $nums[i]$ 的所有互质数。然后我们枚举 $nums[i]$ 的所有互质数，找到已经出现过的且深度最大的祖先节点 $t$，即为 $i$ 的最近的互质祖先节点。这里我们可以用一个长度为 $51$ 的栈数组 $stks$ 来获取每个出现过的值 $v$ 的节点以及其深度。每个栈 $stks[v]$ 的栈顶元素就是最近的深度最大的祖先节点。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getCoprimes(self, nums: List[int], edges: List[List[int]]) -> List[int]:
        def dfs(i, fa, depth):
            t = k = -1
            for v in f[nums[i]]:
                stk = stks[v]
                if stk and stk[-1][1] > k:
                    t, k = stk[-1]
            ans[i] = t
            for j in g[i]:
                if j != fa:
                    stks[nums[i]].append((i, depth))
                    dfs(j, i, depth + 1)
                    stks[nums[i]].pop()

        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        f = defaultdict(list)
        for i in range(1, 51):
            for j in range(1, 51):
                if gcd(i, j) == 1:
                    f[i].append(j)
        stks = defaultdict(list)
        ans = [-1] * len(nums)
        dfs(0, -1, 0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<Integer>[] g;
    private List<Integer>[] f;
    private Deque<int[]>[] stks;
    private int[] nums;
    private int[] ans;

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        f = new List[51];
        stks = new Deque[51];
        Arrays.setAll(f, k -> new ArrayList<>());
        Arrays.setAll(stks, k -> new ArrayDeque<>());
        for (int i = 1; i < 51; ++i) {
            for (int j = 1; j < 51; ++j) {
                if (gcd(i, j) == 1) {
                    f[i].add(j);
                }
            }
        }
        this.nums = nums;
        ans = new int[n];
        dfs(0, -1, 0);
        return ans;
    }

    private void dfs(int i, int fa, int depth) {
        int t = -1, k = -1;
        for (int v : f[nums[i]]) {
            var stk = stks[v];
            if (!stk.isEmpty() && stk.peek()[1] > k) {
                t = stk.peek()[0];
                k = stk.peek()[1];
            }
        }
        ans[i] = t;
        for (int j : g[i]) {
            if (j != fa) {
                stks[nums[i]].push(new int[] {i, depth});
                dfs(j, i, depth + 1);
                stks[nums[i]].pop();
            }
        }
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
    vector<int> getCoprimes(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        vector<vector<int>> g(n);
        vector<vector<int>> f(51);
        vector<stack<pair<int, int>>> stks(51);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].emplace_back(v);
            g[v].emplace_back(u);
        }
        for (int i = 1; i < 51; ++i) {
            for (int j = 1; j < 51; ++j) {
                if (__gcd(i, j) == 1) {
                    f[i].emplace_back(j);
                }
            }
        }
        vector<int> ans(n);
        function<void(int, int, int)> dfs = [&](int i, int fa, int depth) {
            int t = -1, k = -1;
            for (int v : f[nums[i]]) {
                auto& stk = stks[v];
                if (!stk.empty() && stk.top().second > k) {
                    t = stk.top().first;
                    k = stk.top().second;
                }
            }
            ans[i] = t;
            for (int j : g[i]) {
                if (j != fa) {
                    stks[nums[i]].push({i, depth});
                    dfs(j, i, depth + 1);
                    stks[nums[i]].pop();
                }
            }
        };
        dfs(0, -1, 0);
        return ans;
    }
};
```

### **Go**

```go
func getCoprimes(nums []int, edges [][]int) []int {
	n := len(nums)
	g := make([][]int, n)
	f := [51][]int{}
	type pair struct{ first, second int }
	stks := [51][]pair{}
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	for i := 1; i < 51; i++ {
		for j := 1; j < 51; j++ {
			if gcd(i, j) == 1 {
				f[i] = append(f[i], j)
			}
		}
	}
	ans := make([]int, n)
	var dfs func(i, fa, depth int)
	dfs = func(i, fa, depth int) {
		t, k := -1, -1
		for _, v := range f[nums[i]] {
			stk := stks[v]
			if len(stk) > 0 && stk[len(stk)-1].second > k {
				t, k = stk[len(stk)-1].first, stk[len(stk)-1].second
			}
		}
		ans[i] = t
		for _, j := range g[i] {
			if j != fa {
				stks[nums[i]] = append(stks[nums[i]], pair{i, depth})
				dfs(j, i, depth+1)
				stks[nums[i]] = stks[nums[i]][:len(stks[nums[i]])-1]
			}
		}
	}
	dfs(0, -1, 0)
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
