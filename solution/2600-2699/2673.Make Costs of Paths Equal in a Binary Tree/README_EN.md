# [2673. Make Costs of Paths Equal in a Binary Tree](https://leetcode.com/problems/make-costs-of-paths-equal-in-a-binary-tree)

[中文文档](/solution/2600-2699/2673.Make%20Costs%20of%20Paths%20Equal%20in%20a%20Binary%20Tree/README.md)

<!-- tags:Greedy,Tree,Array,Dynamic Programming,Binary Tree -->

## Description

<p>You are given an integer <code>n</code> representing the number of nodes in a <strong>perfect binary tree</strong> consisting of nodes numbered from <code>1</code> to <code>n</code>. The root of the tree is node <code>1</code> and each node <code>i</code> in the tree has two children where the left child is the node <code>2 * i</code> and the right child is <code>2 * i + 1</code>.</p>

<p>Each node in the tree also has a <strong>cost</strong> represented by a given <strong>0-indexed</strong> integer array <code>cost</code> of size <code>n</code> where <code>cost[i]</code> is the cost of node <code>i + 1</code>. You are allowed to <strong>increment</strong> the cost of <strong>any</strong> node by <code>1</code> <strong>any</strong> number of times.</p>

<p>Return <em>the <strong>minimum</strong> number of increments you need to make the cost of paths from the root to each <strong>leaf</strong> node equal</em>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>A <strong>perfect binary tree </strong>is a tree where each node, except the leaf nodes, has exactly 2 children.</li>
	<li>The <strong>cost of a path</strong> is the sum of costs of nodes in the path.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2673.Make%20Costs%20of%20Paths%20Equal%20in%20a%20Binary%20Tree/images/binaryytreeedrawio-4.png" />
<pre>
<strong>Input:</strong> n = 7, cost = [1,5,2,2,3,3,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> We can do the following increments:
- Increase the cost of node 4 one time.
- Increase the cost of node 3 three times.
- Increase the cost of node 7 two times.
Each path from the root to a leaf will have a total cost of 9.
The total increments we did is 1 + 3 + 2 = 6.
It can be shown that this is the minimum answer we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2673.Make%20Costs%20of%20Paths%20Equal%20in%20a%20Binary%20Tree/images/binaryytreee2drawio.png" style="width: 205px; height: 151px;" />
<pre>
<strong>Input:</strong> n = 3, cost = [5,3,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The two paths already have equal total costs, so no increments are needed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n + 1</code> is a power of <code>2</code></li>
	<li><code>cost.length == n</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1: Greedy Algorithm

According to the problem description, we need to calculate the minimum number of increments to make the path values from the root node to each leaf node equal.

The path values from the root node to each leaf node being equal is actually equivalent to the path values from any node as the root of a subtree to each leaf node of that subtree being equal.

Why is that? We can prove it by contradiction. Suppose there is a node $x$, and the path values from it as the root of a subtree to some leaf nodes are not equal. Then there exists a situation where the path values from the root node to the leaf nodes are not equal, which contradicts the condition "the path values from the root node to each leaf node are equal". Therefore, the assumption is not valid, and the path values from any node as the root of a subtree to each leaf node of that subtree are equal.

We can start from the bottom of the tree and calculate the number of increments layer by layer. For each non-leaf node, we can calculate the path values of its left and right child nodes. The number of increments is the difference between the two path values, and then update the path values of the left and right child nodes to the larger one of the two.

Finally, return the total number of increments.

The time complexity is $O(n)$, where $n$ is the number of nodes. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minIncrements(self, n: int, cost: List[int]) -> int:
        ans = 0
        for i in range(n >> 1, 0, -1):
            l, r = i << 1, i << 1 | 1
            ans += abs(cost[l - 1] - cost[r - 1])
            cost[i - 1] += max(cost[l - 1], cost[r - 1])
        return ans
```

```java
class Solution {
    public int minIncrements(int n, int[] cost) {
        int ans = 0;
        for (int i = n >> 1; i > 0; --i) {
            int l = i << 1, r = i << 1 | 1;
            ans += Math.abs(cost[l - 1] - cost[r - 1]);
            cost[i - 1] += Math.max(cost[l - 1], cost[r - 1]);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minIncrements(int n, vector<int>& cost) {
        int ans = 0;
        for (int i = n >> 1; i > 0; --i) {
            int l = i << 1, r = i << 1 | 1;
            ans += abs(cost[l - 1] - cost[r - 1]);
            cost[i - 1] += max(cost[l - 1], cost[r - 1]);
        }
        return ans;
    }
};
```

```go
func minIncrements(n int, cost []int) (ans int) {
	for i := n >> 1; i > 0; i-- {
		l, r := i<<1, i<<1|1
		ans += abs(cost[l-1] - cost[r-1])
		cost[i-1] += max(cost[l-1], cost[r-1])
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function minIncrements(n: number, cost: number[]): number {
    let ans = 0;
    for (let i = n >> 1; i; --i) {
        const [l, r] = [i << 1, (i << 1) | 1];
        ans += Math.abs(cost[l - 1] - cost[r - 1]);
        cost[i - 1] += Math.max(cost[l - 1], cost[r - 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
