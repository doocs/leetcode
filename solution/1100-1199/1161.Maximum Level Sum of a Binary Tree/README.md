# [1161. 最大层内元素和](https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree)

[English Version](/solution/1100-1199/1161.Maximum%20Level%20Sum%20of%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二叉树的根节点&nbsp;<code>root</code>。设根节点位于二叉树的第 <code>1</code> 层，而根节点的子节点位于第 <code>2</code> 层，依此类推。</p>

<p>请返回层内元素之和 <strong>最大</strong> 的那几层（可能只有一层）的层号，并返回其中&nbsp;<strong>最小</strong> 的那个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1161.Maximum%20Level%20Sum%20of%20a%20Binary%20Tree/images/capture.jpeg" style="height: 175px; width: 200px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,7,0,7,-8,null,null]
<strong>输出：</strong>2
<strong>解释：</strong>
第 1 层各元素之和为 1，
第 2 层各元素之和为 7 + 0 = 7，
第 3 层各元素之和为 7 + -8 = -1，
所以我们返回第 2 层的层号，它的层内元素之和最大。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [989,null,10250,98693,-89388,null,null,null,-32127]
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数在<meta charset="UTF-8" />&nbsp;<code>[1, 10<sup>4</sup>]</code>范围内<meta charset="UTF-8" /></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

BFS 层次遍历，求每一层的节点和，找出节点和最大的层，若有多个层的节点和最大，则返回最小的层。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点数。

**方法二：DFS**

我们也可以使用 DFS 求解。我们用一个数组 $s$ 来存储每一层的节点和，数组的下标表示层数，数组的值表示节点和。我们使用 DFS 遍历二叉树，将每个节点的值加到对应层数的节点和上。最后，我们返回 $s$ 中的最大值对应的下标即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxLevelSum(self, root: Optional[TreeNode]) -> int:
        q = deque([root])
        mx = -inf
        i = 0
        while q:
            i += 1
            s = 0
            for _ in range(len(q)):
                node = q.popleft()
                s += node.val
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            if mx < s:
                mx = s
                ans = i
        return ans
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxLevelSum(self, root: Optional[TreeNode]) -> int:
        def dfs(node, i):
            if node is None:
                return
            if i == len(s):
                s.append(node.val)
            else:
                s[i] += node.val
            dfs(node.left, i + 1)
            dfs(node.right, i + 1)

        s = []
        dfs(root, 0)
        return s.index(max(s)) + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public int maxLevelSum(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int mx = Integer.MIN_VALUE;
        int i = 0;
        int ans = 0;
        while (!q.isEmpty()) {
            ++i;
            int s = 0;
            for (int n = q.size(); n > 0; --n) {
                TreeNode node = q.pollFirst();
                s += node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            if (mx < s) {
                mx = s;
                ans = i;
            }
        }
        return ans;
    }
}
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
    private List<Integer> s = new ArrayList<>();

    public int maxLevelSum(TreeNode root) {
        dfs(root, 0);
        int mx = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (mx < s.get(i)) {
                mx = s.get(i);
                ans = i + 1;
            }
        }
        return ans;
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        if (i == s.size()) {
            s.add(root.val);
        } else {
            s.set(i, s.get(i) + root.val);
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    }
}
```

### **C++**

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
    int maxLevelSum(TreeNode* root) {
        queue<TreeNode*> q {{root}};
        int mx = INT_MIN;
        int ans = 0;
        int i = 0;
        while (!q.empty()) {
            ++i;
            int s = 0;
            for (int n = q.size(); n; --n) {
                root = q.front();
                q.pop();
                s += root->val;
                if (root->left) q.push(root->left);
                if (root->right) q.push(root->right);
            }
            if (mx < s) mx = s, ans = i;
        }
        return ans;
    }
};
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
    int maxLevelSum(TreeNode* root) {
        vector<int> s;
        dfs(root, 0, s);
        int mx = INT_MIN;
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) if (mx < s[i]) mx = s[i], ans = i + 1;
        return ans;
    }

    void dfs(TreeNode* root, int i, vector<int>& s) {
        if (!root) return;
        if (s.size() == i) s.push_back(root->val);
        else s[i] += root->val;
        dfs(root->left, i + 1, s);
        dfs(root->right, i + 1, s);
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxLevelSum(root *TreeNode) int {
	q := []*TreeNode{root}
	mx := -0x3f3f3f3f
	i := 0
	ans := 0
	for len(q) > 0 {
		i++
		s := 0
		for n := len(q); n > 0; n-- {
			root = q[0]
			q = q[1:]
			s += root.Val
			if root.Left != nil {
				q = append(q, root.Left)
			}
			if root.Right != nil {
				q = append(q, root.Right)
			}
		}
		if mx < s {
			mx = s
			ans = i
		}
	}
	return ans
}
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
func maxLevelSum(root *TreeNode) int {
	s := []int{}
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, i int) {
		if root == nil {
			return
		}
		if len(s) == i {
			s = append(s, root.Val)
		} else {
			s[i] += root.Val
		}
		dfs(root.Left, i+1)
		dfs(root.Right, i+1)
	}
	dfs(root, 0)
	ans, mx := 0, -0x3f3f3f3f
	for i, v := range s {
		if mx < v {
			mx = v
			ans = i + 1
		}
	}
	return ans
}
```

### **TypeScript**

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

function maxLevelSum(root: TreeNode | null): number {
    const queue = [root];
    let res = 1;
    let max = -Infinity;
    let h = 1;
    while (queue.length !== 0) {
        const n = queue.length;
        let sum = 0;
        for (let i = 0; i < n; i++) {
            const { val, left, right } = queue.shift();
            sum += val;
            left && queue.push(left);
            right && queue.push(right);
        }
        if (sum > max) {
            max = sum;
            res = h;
        }
        h++;
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
