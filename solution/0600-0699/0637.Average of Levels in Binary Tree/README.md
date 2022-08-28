# [637. 二叉树的层平均值](https://leetcode.cn/problems/average-of-levels-in-binary-tree)

[English Version](/solution/0600-0699/0637.Average%20of%20Levels%20in%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空二叉树的根节点<meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;, 以数组的形式返回每一层节点的平均值。与实际答案相差&nbsp;<code>10<sup>-5</sup></code> 以内的答案可以被接受。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0637.Average%20of%20Levels%20in%20Binary%20Tree/images/avg1-tree.jpg" /></p>

<pre>
<strong>输入：</strong>root = [3,9,20,null,null,15,7]
<strong>输出：</strong>[3.00000,14.50000,11.00000]
<strong>解释：</strong>第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
因此返回 [3, 14.5, 11] 。
</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0637.Average%20of%20Levels%20in%20Binary%20Tree/images/avg2-tree.jpg" /></p>

<pre>
<strong>输入：</strong>root = [3,9,20,15,7]
<strong>输出：</strong>[3.00000,14.50000,11.00000]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li>树中节点数量在&nbsp;<code>[1, 10<sup>4</sup>]</code> 范围内</li>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= Node.val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

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
    def averageOfLevels(self, root: Optional[TreeNode]) -> List[float]:
        q = deque([root])
        ans = []
        while q:
            s, n = 0, len(q)
            for _ in range(n):
                root = q.popleft()
                s += root.val
                if root.left:
                    q.append(root.left)
                if root.right:
                    q.append(root.right)
            ans.append(s / n)
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
    def averageOfLevels(self, root: Optional[TreeNode]) -> List[float]:
        def dfs(root, i):
            if root is None:
                return
            if len(s) == i:
                s.append([root.val, 1])
            else:
                s[i][0] += root.val
                s[i][1] += 1
            dfs(root.left, i + 1)
            dfs(root.right, i + 1)

        s = []
        dfs(root, 0)
        return [a / b for a, b in s]
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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            long s = 0;
            for (int i = 0; i < n; ++i) {
                root = q.pollFirst();
                s += root.val;
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            }
            ans.add(s * 1.0 / n);
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
    private List<Long> s = new ArrayList<>();
    private List<Integer> cnt = new ArrayList<>();

    public List<Double> averageOfLevels(TreeNode root) {
        dfs(root, 0);
        List<Double> ans = new ArrayList<>();
        for (int i = 0; i < s.size(); ++i) {
            ans.add(s.get(i) * 1.0 / cnt.get(i));
        }
        return ans;
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        if (s.size() == i) {
            s.add((long) root.val);
            cnt.add(1);
        } else {
            s.set(i, s.get(i) + root.val);
            cnt.set(i, cnt.get(i) + 1);
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    }
}
```

### **JavaScript**

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var averageOfLevels = function (root) {
    let q = [root];
    let ans = [];
    while (q.length) {
        const n = q.length;
        let s = 0;
        for (let i = 0; i < n; ++i) {
            root = q.shift();
            s += root.val;
            if (root.left) {
                q.push(root.left);
            }
            if (root.right) {
                q.push(root.right);
            }
        }
        ans.push(s / n);
    }
    return ans;
};
```

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var averageOfLevels = function (root) {
    let s = [];
    let cnt = [];
    function dfs(root, i) {
        if (!root) {
            return;
        }
        if (s.length == i) {
            s.push(root.val);
            cnt.push(1);
        } else {
            s[i] += root.val;
            cnt[i]++;
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    }
    dfs(root, 0);
    let ans = [];
    for (let i = 0; i < s.length; ++i) {
        ans.push(s[i] / cnt[i]);
    }
    return ans;
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
func averageOfLevels(root *TreeNode) []float64 {
	q := []*TreeNode{root}
	ans := []float64{}
	for len(q) > 0 {
		n := len(q)
		s := 0
		for i := 0; i < n; i++ {
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
		ans = append(ans, float64(s)/float64(n))
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
func averageOfLevels(root *TreeNode) []float64 {
	s := []int{}
	cnt := []int{}
	var dfs func(root *TreeNode, i int)
	dfs = func(root *TreeNode, i int) {
		if root == nil {
			return
		}
		if len(s) == i {
			s = append(s, root.Val)
			cnt = append(cnt, 1)
		} else {
			s[i] += root.Val
			cnt[i]++
		}
		dfs(root.Left, i+1)
		dfs(root.Right, i+1)
	}
	dfs(root, 0)
	ans := []float64{}
	for i, t := range s {
		ans = append(ans, float64(t)/float64(cnt[i]))
	}
	return ans
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
    vector<double> averageOfLevels(TreeNode* root) {
        queue<TreeNode*> q {{root}};
        vector<double> ans;
        while (!q.empty()) {
            int n = q.size();
            long long s = 0;
            for (int i = 0; i < n; ++i) {
                root = q.front();
                q.pop();
                s += root->val;
                if (root->left) q.push(root->left);
                if (root->right) q.push(root->right);
            }
            ans.push_back(s * 1.0 / n);
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

using ll = long long;

class Solution {
public:
    vector<ll> s;
    vector<int> cnt;

    vector<double> averageOfLevels(TreeNode* root) {
        dfs(root, 0);
        vector<double> ans(s.size());
        for (int i = 0; i < s.size(); ++i) {
            ans[i] = (s[i] * 1.0 / cnt[i]);
        }
        return ans;
    }

    void dfs(TreeNode* root, int i) {
        if (!root) return;
        if (s.size() == i) {
            s.push_back(root->val);
            cnt.push_back(1);
        } else {
            s[i] += root->val;
            cnt[i]++;
        }
        dfs(root->left, i + 1);
        dfs(root->right, i + 1);
    }
};
```

### **Rust**

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
use std::collections::VecDeque;
impl Solution {
    pub fn average_of_levels(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<f64> {
        if root.is_none() {
            return Vec::new();
        }

        let mut q = VecDeque::new();
        q.push_back(Rc::clone(&root.unwrap()));
        let mut ans = Vec::new();
        while !q.is_empty() {
            let n = q.len();
            let mut sum = 0.0;
            for _ in 0..n {
                let node = q.pop_front().unwrap();
                sum += node.borrow().val as f64;
                if node.borrow().left.is_some() {
                    q.push_back(Rc::clone(node.borrow().left.as_ref().unwrap()));
                }
                if node.borrow().right.is_some() {
                    q.push_back(Rc::clone(node.borrow().right.as_ref().unwrap()));
                }
            }
            ans.push(sum / n as f64);
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
