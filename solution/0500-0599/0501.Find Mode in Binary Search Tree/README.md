---
comments: true
difficulty: 简单
tags:
    - 树
    - 深度优先搜索
    - 二叉搜索树
    - 二叉树
---

<!-- problem:start -->

# [501. 二叉搜索树中的众数](https://leetcode.cn/problems/find-mode-in-binary-search-tree)

[English Version](/solution/0500-0599/0501.Find%20Mode%20in%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个含重复值的二叉搜索树（BST）的根节点 <code>root</code> ，找出并返回 BST 中的所有 <a href="https://baike.baidu.com/item/%E4%BC%97%E6%95%B0/44796" target="_blank">众数</a>（即，出现频率最高的元素）。</p>

<p>如果树中有不止一个众数，可以按 <strong>任意顺序</strong> 返回。</p>

<p>假定 BST 满足如下定义：</p>

<ul>
	<li>结点左子树中所含节点的值 <strong>小于等于</strong> 当前节点的值</li>
	<li>结点右子树中所含节点的值 <strong>大于等于</strong> 当前节点的值</li>
	<li>左子树和右子树都是二叉搜索树</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0501.Find%20Mode%20in%20Binary%20Search%20Tree/images/mode-tree.jpg" style="width: 142px; height: 222px;" />
<pre>
<strong>输入：</strong>root = [1,null,2,2]
<strong>输出：</strong>[2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [0]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 10<sup>4</sup>]</code> 内</li>
	<li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 众数即出现次数最多的值。若先遍历整棵树再哈希计数，时间和空间均为 $O(n)$，在 $n \le 10^4$ 时可以接受，但没有用到二叉搜索树的有序性。
>
> 中序遍历得到的是非降序列，相同值必然相邻。因此只需在中序过程中维护前驱、当前连续次数与历史最大次数：次数更大则重置答案，次数持平则追加。一遍中序即可在 $O(h)$ 额外空间内收集全部众数。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findMode(self, root: TreeNode) -> List[int]:
        def dfs(root):
            if root is None:
                return
            nonlocal mx, prev, ans, cnt
            dfs(root.left)
            cnt = cnt + 1 if prev == root.val else 1
            if cnt > mx:
                ans = [root.val]
                mx = cnt
            elif cnt == mx:
                ans.append(root.val)
            prev = root.val
            dfs(root.right)

        prev = None
        mx = cnt = 0
        ans = []
        dfs(root)
        return ans
```

#### Java

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
    private int mx;
    private int cnt;
    private TreeNode prev;
    private List<Integer> res;

    public int[] findMode(TreeNode root) {
        res = new ArrayList<>();
        dfs(root);
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        cnt = prev != null && prev.val == root.val ? cnt + 1 : 1;
        if (cnt > mx) {
            res = new ArrayList<>(Arrays.asList(root.val));
            mx = cnt;
        } else if (cnt == mx) {
            res.add(root.val);
        }
        prev = root;
        dfs(root.right);
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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode* prev;
    int mx, cnt;
    vector<int> ans;

    vector<int> findMode(TreeNode* root) {
        dfs(root);
        return ans;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->left);
        cnt = prev != nullptr && prev->val == root->val ? cnt + 1 : 1;
        if (cnt > mx) {
            ans.clear();
            ans.push_back(root->val);
            mx = cnt;
        } else if (cnt == mx)
            ans.push_back(root->val);
        prev = root;
        dfs(root->right);
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
func findMode(root *TreeNode) []int {
	mx, cnt := 0, 0
	var prev *TreeNode
	var ans []int
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if prev != nil && prev.Val == root.Val {
			cnt++
		} else {
			cnt = 1
		}
		if cnt > mx {
			ans = []int{root.Val}
			mx = cnt
		} else if cnt == mx {
			ans = append(ans, root.Val)
		}
		prev = root
		dfs(root.Right)
	}
	dfs(root)
	return ans
}
```

#### C#

```cs
public class Solution {
    private int mx;
    private int cnt;
    private TreeNode prev;
    private List<int> res;

    public int[] FindMode(TreeNode root) {
        res = new List<int>();
        Dfs(root);
        int[] ans = new int[res.Count];
        for (int i = 0; i < res.Count; ++i) {
            ans[i] = res[i];
        }
        return ans;
    }

    private void Dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Dfs(root.left);
        cnt = prev != null && prev.val == root.val ? cnt + 1 : 1;
        if (cnt > mx) {
            res = new List<int>(new int[] { root.val });
            mx = cnt;
        } else if (cnt == mx) {
            res.Add(root.val);
        }
        prev = root;
        Dfs(root.right);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈中序遍历

<!-- thinking:start -->

> **思考**
>
> 众数是出现次数最多的值。先遍历整棵树再哈希计数，在 $n \le 10^4$ 时时间够用，但没有用到二叉搜索树的有序性。
>
> 中序会先走进左孩子。一条长度为 $10^4$ 的链按结点个数递归，调用栈会溢出。
>
> 中序得到的是非降序列，相同值必然相邻。因此只需要前一个值、当前连续次数和历史最大次数，不必等子树返回。
>
> 用显式栈做中序遍历。进入结点时压入退出标记和左孩子；退出时按连续次数更新答案，再压入右孩子。次数更大就重置答案，次数持平就追加。

<!-- thinking:end -->

二叉搜索树的中序遍历是非降序列，相同的值必然相邻。我们用显式栈完成这次中序遍历，并维护前一个值、当前连续次数和历史最大次数。进入结点时压入退出标记和左孩子；退出时，若当前值与前一个值相同则连续次数加一，否则置为 $1$。连续次数超过历史最大次数时重置答案，相等时把当前值追加进答案，然后压入右孩子。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉搜索树的节点数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findMode(self, root: TreeNode) -> List[int]:
        prev = None
        mx = cnt = 0
        ans = []
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                stk.append((node.left, 0))
                continue
            cnt = cnt + 1 if prev == node.val else 1
            if cnt > mx:
                ans = [node.val]
                mx = cnt
            elif cnt == mx:
                ans.append(node.val)
            prev = node.val
            stk.append((node.right, 0))
        return ans
```

#### Java

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
    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int[] findMode(TreeNode root) {
        Integer prev = null;
        int mx = 0;
        int cnt = 0;
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            Deque<Frame> stk = new ArrayDeque<>();
            stk.push(new Frame(root, 0));
            while (!stk.isEmpty()) {
                Frame cur = stk.pop();
                TreeNode node = cur.node;
                if (cur.state == 0) {
                    stk.push(new Frame(node, 1));
                    if (node.left != null) {
                        stk.push(new Frame(node.left, 0));
                    }
                    continue;
                }
                cnt = prev != null && prev == node.val ? cnt + 1 : 1;
                if (cnt > mx) {
                    res = new ArrayList<>(Arrays.asList(node.val));
                    mx = cnt;
                } else if (cnt == mx) {
                    res.add(node.val);
                }
                prev = node.val;
                if (node.right != null) {
                    stk.push(new Frame(node.right, 0));
                }
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            ans[i] = res.get(i);
        }
        return ans;
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
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<int> findMode(TreeNode* root) {
        bool has = false;
        int prev = 0, mx = 0, cnt = 0;
        vector<int> ans;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (state == 0) {
                stk.emplace_back(node, 1);
                stk.emplace_back(node->left, 0);
                continue;
            }
            cnt = has && prev == node->val ? cnt + 1 : 1;
            if (cnt > mx) {
                ans.clear();
                ans.push_back(node->val);
                mx = cnt;
            } else if (cnt == mx) {
                ans.push_back(node->val);
            }
            prev = node->val;
            has = true;
            stk.emplace_back(node->right, 0);
        }
        return ans;
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
func findMode(root *TreeNode) []int {
	mx, cnt, prev := 0, 0, 0
	has := false
	var ans []int
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, state := cur.node, cur.state
		if node == nil {
			continue
		}
		if state == 0 {
			stk = append(stk, frame{node, 1}, frame{node.Left, 0})
			continue
		}
		if has && prev == node.Val {
			cnt++
		} else {
			cnt = 1
		}
		if cnt > mx {
			ans = []int{node.Val}
			mx = cnt
		} else if cnt == mx {
			ans = append(ans, node.Val)
		}
		prev = node.Val
		has = true
		stk = append(stk, frame{node.Right, 0})
	}
	return ans
}
```

#### C#

```cs
public class Solution {
    private class Frame {
        public TreeNode node;
        public int state;

        public Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int[] FindMode(TreeNode root) {
        int mx = 0;
        int cnt = 0;
        int prev = 0;
        bool has = false;
        List<int> res = new List<int>();
        if (root != null) {
            Stack<Frame> stk = new Stack<Frame>();
            stk.Push(new Frame(root, 0));
            while (stk.Count > 0) {
                Frame cur = stk.Pop();
                TreeNode node = cur.node;
                if (cur.state == 0) {
                    stk.Push(new Frame(node, 1));
                    if (node.left != null) {
                        stk.Push(new Frame(node.left, 0));
                    }
                    continue;
                }
                cnt = has && prev == node.val ? cnt + 1 : 1;
                if (cnt > mx) {
                    res = new List<int>(new int[] { node.val });
                    mx = cnt;
                } else if (cnt == mx) {
                    res.Add(node.val);
                }
                prev = node.val;
                has = true;
                if (node.right != null) {
                    stk.Push(new Frame(node.right, 0));
                }
            }
        }
        int[] ans = new int[res.Count];
        for (int i = 0; i < res.Count; ++i) {
            ans[i] = res[i];
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
