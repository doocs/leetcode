# [2583. 二叉树中的第 K 大层和](https://leetcode.cn/problems/kth-largest-sum-in-a-binary-tree)

[English Version](/solution/2500-2599/2583.Kth%20Largest%20Sum%20in%20a%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树的根节点 <code>root</code> 和一个正整数 <code>k</code> 。</p>

<p>树中的 <strong>层和</strong> 是指 <strong>同一层</strong> 上节点值的总和。</p>

<p>返回树中第 <code>k</code> 大的层和（不一定不同）。如果树少于 <code>k</code> 层，则返回 <code>-1</code> 。</p>

<p><strong>注意</strong>，如果两个节点与根节点的距离相同，则认为它们在同一层。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2583.Kth%20Largest%20Sum%20in%20a%20Binary%20Tree/images/binaryytreeedrawio-2.png" style="width: 301px; height: 284px;" /></p>

<pre>
<strong>输入：</strong>root = [5,8,9,2,1,3,7,4,6], k = 2
<strong>输出：</strong>13
<strong>解释：</strong>树中每一层的层和分别是：
- Level 1: 5
- Level 2: 8 + 9 = 17
- Level 3: 2 + 1 + 3 + 7 = 13
- Level 4: 4 + 6 = 10
第 2 大的层和等于 13 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2583.Kth%20Largest%20Sum%20in%20a%20Binary%20Tree/images/treedrawio-3.png" style="width: 181px; height: 181px;" /></p>

<pre>
<strong>输入：</strong>root = [1,2,null,3], k = 1
<strong>输出：</strong>3
<strong>解释：</strong>最大的层和是 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数为 <code>n</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS + 排序**

我们可以使用 BFS 遍历二叉树，同时记录每一层的节点和，然后对节点和数组进行排序，最后返回第 $k$ 大的节点和即可。注意，如果二叉树的层数小于 $k$，则返回 $-1$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点数。

**方法二：DFS + 排序**

我们也可以使用 DFS 遍历二叉树，同时记录每一层的节点和，然后对节点和数组进行排序，最后返回第 $k$ 大的节点和即可。注意，如果二叉树的层数小于 $k$，则返回 $-1$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点数。

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
    def kthLargestLevelSum(self, root: Optional[TreeNode], k: int) -> int:
        arr = []
        q = deque([root])
        while q:
            t = 0
            for _ in range(len(q)):
                root = q.popleft()
                t += root.val
                if root.left:
                    q.append(root.left)
                if root.right:
                    q.append(root.right)
            arr.append(t)
        return -1 if len(arr) < k else nlargest(k, arr)[-1]
```

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def kthLargestLevelSum(self, root: Optional[TreeNode], k: int) -> int:
        def dfs(root, d):
            if root is None:
                return
            if len(arr) <= d:
                arr.append(0)
            arr[d] += root.val
            dfs(root.left, d + 1)
            dfs(root.right, d + 1)

        arr = []
        dfs(root, 0)
        return -1 if len(arr) < k else nlargest(k, arr)[-1]
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
    public long kthLargestLevelSum(TreeNode root, int k) {
        List<Long> arr = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            long t = 0;
            for (int n = q.size(); n > 0; --n) {
                root = q.pollFirst();
                t += root.val;
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            }
            arr.add(t);
        }
        if (arr.size() < k) {
            return -1;
        }
        Collections.sort(arr, Collections.reverseOrder());
        return arr.get(k - 1);
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
    private List<Long> arr = new ArrayList<>();

    public long kthLargestLevelSum(TreeNode root, int k) {
        dfs(root, 0);
        if (arr.size() < k) {
            return -1;
        }
        Collections.sort(arr, Collections.reverseOrder());
        return arr.get(k - 1);
    }

    private void dfs(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        if (arr.size() <= d) {
            arr.add(0L);
        }
        arr.set(d, arr.get(d) + root.val);
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
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
    long long kthLargestLevelSum(TreeNode* root, int k) {
        vector<long long> arr;
        queue<TreeNode*> q{{root}};
        while (!q.empty()) {
            long long t = 0;
            for (int n = q.size(); n; --n) {
                root = q.front();
                q.pop();
                t += root->val;
                if (root->left) {
                    q.push(root->left);
                }
                if (root->right) {
                    q.push(root->right);
                }
            }
            arr.push_back(t);
        }
        if (arr.size() < k) {
            return -1;
        }
        sort(arr.rbegin(), arr.rend());
        return arr[k - 1];
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
    long long kthLargestLevelSum(TreeNode* root, int k) {
        vector<long long> arr;
        function<void(TreeNode*, int)> dfs = [&](TreeNode* root, int d) {
            if (!root) {
                return;
            }
            if (arr.size() <= d) {
                arr.push_back(0);
            }
            arr[d] += root->val;
            dfs(root->left, d + 1);
            dfs(root->right, d + 1);
        };
        dfs(root, 0);
        if (arr.size() < k) {
            return -1;
        }
        sort(arr.rbegin(), arr.rend());
        return arr[k - 1];
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
func kthLargestLevelSum(root *TreeNode, k int) int64 {
	arr := []int{}
	q := []*TreeNode{root}
	for len(q) > 0 {
		t := 0
		for n := len(q); n > 0; n-- {
			root = q[0]
			q = q[1:]
			t += root.Val
			if root.Left != nil {
				q = append(q, root.Left)
			}
			if root.Right != nil {
				q = append(q, root.Right)
			}
		}
		arr = append(arr, t)
	}
	if n := len(arr); n >= k {
		sort.Ints(arr)
		return int64(arr[n-k])
	}
	return -1
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
func kthLargestLevelSum(root *TreeNode, k int) int64 {
	arr := []int{}
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, d int) {
		if root == nil {
			return
		}
		if len(arr) <= d {
			arr = append(arr, 0)
		}
		arr[d] += root.Val
		dfs(root.Left, d+1)
		dfs(root.Right, d+1)
	}

	dfs(root, 0)
	if n := len(arr); n >= k {
		sort.Ints(arr)
		return int64(arr[n-k])
	}
	return -1
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

function kthLargestLevelSum(root: TreeNode | null, k: number): number {
    const arr: number[] = [];
    const q = [root];
    while (q.length) {
        let t = 0;
        for (let n = q.length; n > 0; --n) {
            root = q.shift();
            t += root.val;
            if (root.left) {
                q.push(root.left);
            }
            if (root.right) {
                q.push(root.right);
            }
        }
        arr.push(t);
    }
    if (arr.length < k) {
        return -1;
    }
    arr.sort((a, b) => b - a);
    return arr[k - 1];
}
```

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

function kthLargestLevelSum(root: TreeNode | null, k: number): number {
    const dfs = (root: TreeNode, d: number) => {
        if (!root) {
            return;
        }
        if (arr.length <= d) {
            arr.push(0);
        }
        arr[d] += root.val;
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
    };
    const arr: number[] = [];
    dfs(root, 0);
    if (arr.length < k) {
        return -1;
    }
    arr.sort((a, b) => b - a);
    return arr[k - 1];
}
```

### **...**

```

```

<!-- tabs:end -->
