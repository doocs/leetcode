# [2792. 计算足够大的节点数](https://leetcode.cn/problems/count-nodes-that-are-great-enough)

[English Version](/solution/2700-2799/2792.Count%20Nodes%20That%20Are%20Great%20Enough/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一棵二叉树的根节点 <code>root</code> 和一个整数 <code>k</code> 。如果一个节点满足以下条件，则称其为 <strong>足够大</strong>&nbsp;：</p>

<ul>
	<li>它的子树中 <strong>至少</strong> 有 <code>k</code> 个节点。</li>
	<li>它的值 <strong>大于</strong> 其子树中 <strong>至少</strong> <code>k</code> 个节点的值。</li>
</ul>

<p>返回足够大的节点数。</p>

<p>如果 <code>u == v</code> 或者 <code>v</code> 是 <code>u</code> 的祖先，则节点 <code>u</code> 在节点 <code>v</code> 的 <strong>子树</strong> 中。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>root = [7,6,5,4,3,2,1], k = 2
<b>输出：</b>3
<b>解释：</b>节点编号从 1 到 7。 
节点 1 的子树中的值：{1,2,3,4,5,6,7}。由于节点的值等于 7，有 6 个节点的值小于它的值，因此它是“足够大”的。 
节点 2 的子树中的值：{3,4,6}。由于节点的值等于 6，有 2 个节点的值小于它的值，因此它是“足够大”的。 
节点 3 的子树中的值：{1,2,5}。由于节点的值等于 5，有 2 个节点的值小于它的值，因此它是“足够大”的。 
其他节点不满足条件。 
参考下面的图片可以帮助你得到更好的理解。</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2792.Count%20Nodes%20That%20Are%20Great%20Enough/images/1.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 300px; height: 167px;" /></p>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>root = [1,2,3], k = 1
<b>输出：</b>0
<strong>解释：</strong>节点编号从 1 到 3。 
节点 1 的子树中的值：{1,2,3}。由于节点的值等于 1，没有节点的值小于它的值，因此它不是“足够大”的。 
节点 2 的子树中的值：{2}。由于节点的值等于 2，没有节点的值小于它的值，因此它不是“足够大”的。 
节点 3 的子树中的值：{3}。由于节点的值等于 3，没有节点的值小于它的值，因此它不是“足够大”的。 
参考下面的图片可以帮助你得到更好的理解。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2792.Count%20Nodes%20That%20Are%20Great%20Enough/images/2.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 123px; height: 101px;" /></p>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>root = [3,2,2], k = 2
<b>输出：</b>1
<strong>解释：</strong>节点编号从 1 到 3。 
节点 1 的子树中的值：{2,2,3}。
由于节点的值等于 3，有 2 个节点的值小于它的值，因此它是“足够大”的。 
节点 2 的子树中的值：{2}。由于节点的值等于 2，没有节点的值小于它的值，因此它不是“足够大”的。 
节点 3 的子树中的值：{2}。由于节点的值等于 2，没有节点的值小于它的值，因此它不是“足够大”的。 
参考下面的图片可以帮助你得到更好的理解。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2792.Count%20Nodes%20That%20Are%20Great%20Enough/images/3.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 123px; height: 101px;" /></p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数在范围&nbsp;<code>[1, 10<sup>4</sup>]</code> 内。<span style="display: none;">&nbsp;</span></li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
</ul>

## 解法

### 方法一：DFS + 大根堆

我们可以使用 DFS 后序遍历整棵树，对于每个节点，我们维护一个大根堆，堆中存储该节点的所有子树中最小的 k 个节点的值，如果当前节点的值大于堆顶元素，那么该节点就是一个「足够大」的节点，我们将答案加一。

时间复杂度 $O(n \times k \times \log k)$，空间复杂度 $(n \times k)$。其中 $n$ 是树中节点的个数。

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def countGreatEnoughNodes(self, root: Optional[TreeNode], k: int) -> int:
        def push(pq, x):
            heappush(pq, x)
            if len(pq) > k:
                heappop(pq)

        def dfs(root):
            if root is None:
                return []
            l, r = dfs(root.left), dfs(root.right)
            for x in r:
                push(l, x)
            if len(l) == k and -l[0] < root.val:
                nonlocal ans
                ans += 1
            push(l, -root.val)
            return l

        ans = 0
        dfs(root)
        return ans
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int ans;
    private int k;

    public int countGreatEnoughNodes(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }

    private PriorityQueue<Integer> dfs(TreeNode root) {
        if (root == null) {
            return new PriorityQueue<>(Comparator.reverseOrder());
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        for (int x : r) {
            l.offer(x);
            if (l.size() > k) {
                l.poll();
            }
        }
        if (l.size() == k && l.peek() < root.val) {
            ++ans;
        }
        l.offer(root.val);
        if (l.size() > k) {
            l.poll();
        }
        return l;
    }
}
```

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int countGreatEnoughNodes(TreeNode* root, int k) {
        int ans = 0;
        function<priority_queue<int>(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return priority_queue<int>();
            }
            auto left = dfs(root->left);
            auto right = dfs(root->right);
            while (right.size()) {
                left.push(right.top());
                right.pop();
                if (left.size() > k) {
                    left.pop();
                }
            }
            if (left.size() == k && left.top() < root->val) {
                ++ans;
            }
            left.push(root->val);
            if (left.size() > k) {
                left.pop();
            }
            return left;
        };
        dfs(root);
        return ans;
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func countGreatEnoughNodes(root *TreeNode, k int) (ans int) {
	var dfs func(*TreeNode) hp
	dfs = func(root *TreeNode) hp {
		if root == nil {
			return hp{}
		}
		l, r := dfs(root.Left), dfs(root.Right)
		for _, x := range r.IntSlice {
			l.push(x)
			if l.Len() > k {
				l.pop()
			}
		}
		if l.Len() == k && root.Val > l.IntSlice[0] {
			ans++
		}
		l.push(root.Val)
		if l.Len() > k {
			l.pop()
		}
		return l
	}
	dfs(root)
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

<!-- tabs:end -->

<!-- end -->
