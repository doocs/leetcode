---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2010.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E4%BB%BB%E5%8A%A1%E8%B0%83%E5%BA%A6/README.md
---

<!-- problem:start -->

# [LCP 10. 二叉树任务调度](https://leetcode.cn/problems/er-cha-shu-ren-wu-diao-du)

## 题目描述

<!-- description:start -->

<p>任务调度优化是计算机性能优化的关键任务之一。在任务众多时，不同的调度策略可能会得到不同的总体执行时间，因此寻求一个最优的调度方案是非常有必要的。</p>

<p>通常任务之间是存在依赖关系的，即对于某个任务，你需要先<strong>完成</strong>他的前导任务（如果非空），才能开始执行该任务。<strong>我们保证任务的依赖关系是一棵二叉树，</strong>其中 <code>root</code> 为根任务，<code>root.left</code> 和 <code>root.right</code> 为他的两个前导任务（可能为空），<code>root.val</code> 为其自身的执行时间。</p>

<p>在一个 CPU 核执行某个任务时，我们可以在任何时刻暂停当前任务的执行，并保留当前执行进度。在下次继续执行该任务时，会从之前停留的进度开始继续执行。暂停的时间可以不是整数。</p>

<p>现在，系统有<strong>两个</strong> CPU 核，即我们可以同时执行两个任务，但是同一个任务不能同时在两个核上执行。给定这颗任务树，请求出所有任务执行完毕的最小时间。</p>

<p><strong>示例 1：</strong></p>

<blockquote>
<p><img alt="image.png" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2010.%20二叉树任务调度/images/3522fbf8ce4ebb20b79019124eb9870109fdfe97fe9da99f6c20c07ceb1c60b3-image.png" /></p>

<p>输入：root = [47, 74, 31]</p>

<p>输出：121</p>

<p>解释：根节点的左右节点可以并行执行31分钟，剩下的43+47分钟只能串行执行，因此总体执行时间是121分钟。</p>
</blockquote>

<p><strong>示例 2：</strong></p>

<blockquote>
<p><img alt="image.png" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2010.%20二叉树任务调度/images/13accf172ee4a660d241e25901595d55b759380b090890a17e6e7bd51a143e3f-image.png" /></p>

<p>输入：root = [15, 21, null, 24, null, 27, 26]</p>

<p>输出：87</p>
</blockquote>

<p><strong>示例 3：</strong></p>

<blockquote>
<p><img alt="image.png" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2010.%20二叉树任务调度/images/bef743a12591aafb9047dd95d335b8083dfa66e8fdedc63f50fd406b4a9d163a-image.png" /></p>

<p>输入：root = [1,3,2,null,null,4,4]</p>

<p>输出：7.5</p>
</blockquote>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= 节点数量 &lt;= 1000</code></li>
	<li><code>1 &lt;= 单节点执行时间 &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimalExecTime(self, root: TreeNode) -> float:
        def dfs(root: TreeNode) -> Tuple[int, int]:
            if not root:
                return 0, 0
            s1, t1 = dfs(root.left)
            s2, t2 = dfs(root.right)
            return s1 + s2 + root.val, max(t1, t2, (s1 + s2) / 2) + root.val

        return dfs(root)[1]
```

#### Java

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public double minimalExecTime(TreeNode root) {
        return dfs(root)[1];
    }

    private double[] dfs(TreeNode root) {
        if (root == null) {
            return new double[] {0, 0};
        }
        double[] left = dfs(root.left);
        double[] right = dfs(root.right);
        double s = left[0] + right[0] + root.val;
        double t = Math.max(Math.max(left[1], right[1]), (left[0] + right[0]) / 2) + root.val;
        return new double[] {s, t};
    }
}
```

#### C++

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    double minimalExecTime(TreeNode* root) {
        function<pair<double, double>(TreeNode*)> dfs = [&](TreeNode* root) -> pair<double, double> {
            if (!root) {
                return {0, 0};
            }
            auto [s1, t1] = dfs(root->left);
            auto [s2, t2] = dfs(root->right);
            double s = s1 + s2 + root->val;
            double t = max({t1, t2, (s1 + s2) / 2}) + root->val;
            return {s, t};
        };
        auto [_, t] = dfs(root);
        return t;
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minimalExecTime(root *TreeNode) float64 {
	var dfs func(*TreeNode) (float64, float64)
	dfs = func(root *TreeNode) (float64, float64) {
		if root == nil {
			return 0, 0
		}
		s1, t1 := dfs(root.Left)
		s2, t2 := dfs(root.Right)
		s := s1 + s2 + float64(root.Val)
		t := math.Max(math.Max(t1, t2), (s1+s2)/2) + float64(root.Val)
		return s, t
	}
	_, t := dfs(root)
	return t
}
```

#### TypeScript

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function minimalExecTime(root: TreeNode | null): number {
    const dfs = (root: TreeNode | null): [number, number] => {
        if (root === null) {
            return [0, 0];
        }
        const [s1, t1] = dfs(root.left);
        const [s2, t2] = dfs(root.right);
        const s = s1 + s2 + root.val;
        const t = Math.max(t1, t2, (s1 + s2) / 2) + root.val;
        return [s, t];
    };
    return dfs(root)[1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
