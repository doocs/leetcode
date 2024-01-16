# [2673. 使二叉树所有路径值相等的最小代价](https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree)

[English Version](/solution/2600-2699/2673.Make%20Costs%20of%20Paths%20Equal%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;表示一棵 <b>满二叉树</b>&nbsp;里面节点的数目，节点编号从 <code>1</code>&nbsp;到 <code>n</code>&nbsp;。根节点编号为 <code>1</code>&nbsp;，树中每个非叶子节点&nbsp;<code>i</code>&nbsp;都有两个孩子，分别是左孩子&nbsp;<code>2 * i</code>&nbsp;和右孩子&nbsp;<code>2 * i + 1</code>&nbsp;。</p>

<p>树中每个节点都有一个值，用下标从<b>&nbsp;0</b>&nbsp;开始、长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>cost</code>&nbsp;表示，其中&nbsp;<code>cost[i]</code>&nbsp;是第&nbsp;<code>i + 1</code>&nbsp;个节点的值。每次操作，你可以将树中&nbsp;<strong>任意</strong>&nbsp;节点的值&nbsp;<strong>增加</strong>&nbsp;<code>1</code>&nbsp;。你可以执行操作 <strong>任意</strong> 次。</p>

<p>你的目标是让根到每一个 <strong>叶子结点</strong>&nbsp;的路径值相等。请你返回 <strong>最少</strong>&nbsp;需要执行增加操作多少次。</p>

<p><b>注意：</b></p>

<ul>
	<li><strong>满二叉树</strong>&nbsp;指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个节点，且所有叶子节点距离根节点距离相同。</li>
	<li><strong>路径值</strong> 指的是路径上所有节点的值之和。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2673.Make%20Costs%20of%20Paths%20Equal%20in%20a%20Binary%20Tree/images/binaryytreeedrawio-4.png" /></p>

<pre>
<b>输入：</b>n = 7, cost = [1,5,2,2,3,3,1]
<b>输出：</b>6
<b>解释：</b>我们执行以下的增加操作：
- 将节点 4 的值增加一次。
- 将节点 3 的值增加三次。
- 将节点 7 的值增加两次。
从根到叶子的每一条路径值都为 9 。
总共增加次数为 1 + 3 + 2 = 6 。
这是最小的答案。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2673.Make%20Costs%20of%20Paths%20Equal%20in%20a%20Binary%20Tree/images/binaryytreee2drawio.png" style="width: 205px; height: 151px;" /></p>

<pre>
<b>输入：</b>n = 3, cost = [5,3,3]
<b>输出：</b>0
<b>解释：</b>两条路径已经有相等的路径值，所以不需要执行任何增加操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n + 1</code> 是&nbsp;<code>2</code>&nbsp;的幂</li>
	<li><code>cost.length == n</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def minIncrements(self, n: int, cost: List[int]) -> int:
        def dfs(i: int) -> int:
            if (i << 1) > n:
                return cost[i - 1]
            l, r = dfs(i << 1), dfs(i << 1 | 1)
            nonlocal ans
            ans += max(l, r) - min(l, r)
            return cost[i - 1] + max(l, r)

        ans = 0
        dfs(1)
        return ans
```

```java
class Solution {
    private int[] cost;
    private int n;
    private int ans;

    public int minIncrements(int n, int[] cost) {
        this.n = n;
        this.cost = cost;
        dfs(1);
        return ans;
    }

    private int dfs(int i) {
        if ((i << 1) > n) {
            return cost[i - 1];
        }
        int l = dfs(i << 1);
        int r = dfs(i << 1 | 1);
        ans += Math.max(l, r) - Math.min(l, r);
        return cost[i - 1] + Math.max(l, r);
    }
}
```

```cpp
class Solution {
public:
    int minIncrements(int n, vector<int>& cost) {
        int ans = 0;
        function<int(int)> dfs = [&](int i) -> int {
            if ((i << 1) > n) {
                return cost[i - 1];
            }
            int l = dfs(i << 1);
            int r = dfs(i << 1 | 1);
            ans += max(l, r) - min(l, r);
            return cost[i - 1] + max(l, r);
        };
        dfs(1);
        return ans;
    }
};
```

```go
func minIncrements(n int, cost []int) (ans int) {
	var dfs func(int) int
	dfs = func(i int) int {
		if (i << 1) > n {
			return cost[i-1]
		}
		l, r := dfs(i<<1), dfs(i<<1|1)
		ans += max(l, r) - min(l, r)
		return cost[i-1] + max(l, r)
	}
	dfs(1)
	return ans
}
```

```ts
function minIncrements(n: number, cost: number[]): number {
    let ans = 0;
    const dfs = (i: number): number => {
        if (i << 1 > n) {
            return cost[i - 1];
        }
        const [a, b] = [dfs(i << 1), dfs((i << 1) | 1)];
        ans += Math.max(a, b) - Math.min(a, b);
        return cost[i - 1] + Math.max(a, b);
    };
    dfs(1);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
