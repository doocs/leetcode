---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
---

<!-- problem:start -->

# [545. Boundary of Binary Tree 🔒](https://leetcode.com/problems/boundary-of-binary-tree)

[中文文档](/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/README.md)

## Description

<!-- description:start -->

<p>The <strong>boundary</strong> of a binary tree is the concatenation of the <strong>root</strong>, the <strong>left boundary</strong>, the <strong>leaves</strong> ordered from left-to-right, and the <strong>reverse order</strong> of the <strong>right boundary</strong>.</p>

<p>The <strong>left boundary</strong> is the set of nodes defined by the following:</p>

<ul>
	<li>The root node&#39;s left child is in the left boundary. If the root does not have a left child, then the left boundary is <strong>empty</strong>.</li>
	<li>If a node is in the left boundary and has a left child, then the left child is in the left boundary.</li>
	<li>If a node is in the left boundary, has <strong>no</strong> left child, but has a right child, then the right child is in the left boundary.</li>
	<li>The leftmost leaf is <strong>not</strong> in the left boundary.</li>
</ul>

<p>The <strong>right boundary</strong> is similar to the <strong>left boundary</strong>, except it is the right side of the root&#39;s right subtree. Again, the leaf is <strong>not</strong> part of the <strong>right boundary</strong>, and the <strong>right boundary</strong> is empty if the root does not have a right child.</p>

<p>The <strong>leaves</strong> are nodes that do not have any children. For this problem, the root is <strong>not</strong> a leaf.</p>

<p>Given the <code>root</code> of a binary tree, return <em>the values of its <strong>boundary</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/images/boundary1.jpg" style="width: 299px; height: 290px;" />
<pre>
<strong>Input:</strong> root = [1,null,2,3,4]
<strong>Output:</strong> [1,3,4,2]
<b>Explanation:</b>
- The left boundary is empty because the root does not have a left child.
- The right boundary follows the path starting from the root&#39;s right child 2 -&gt; 4.
  4 is a leaf, so the right boundary is [2].
- The leaves from left to right are [3,4].
Concatenating everything results in [1] + [] + [3,4] + [2] = [1,3,4,2].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0545.Boundary%20of%20Binary%20Tree/images/boundary2.jpg" style="width: 599px; height: 411px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,4,5,6,null,null,null,7,8,9,10]
<strong>Output:</strong> [1,2,4,7,8,9,10,6,3]
<b>Explanation:</b>
- The left boundary follows the path starting from the root&#39;s left child 2 -&gt; 4.
  4 is a leaf, so the left boundary is [2].
- The right boundary follows the path starting from the root&#39;s right child 3 -&gt; 6 -&gt; 10.
  10 is a leaf, so the right boundary is [3,6], and in reverse order is [6,3].
- The leaves from left to right are [4,7,8,9,10].
Concatenating everything results in [1] + [2] + [4,7,8,9,10] + [6,3] = [1,2,4,7,8,9,10,6,3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

First, if the tree has only one node, we directly return a list with the value of that node.

Otherwise, we can use depth-first search (DFS) to find the left boundary, leaf nodes, and right boundary of the binary tree.

Specifically, we can use a recursive function $\textit{dfs}$ to find these three parts. In the $\textit{dfs}$ function, we need to pass in a list $\textit{nums}$, a node $\textit{root}$, and an integer $\textit{i}$, where $\textit{nums}$ is used to store the current part's node values, and $\textit{root}$ and $\textit{i}$ represent the current node and the type of the current part (left boundary, leaf nodes, or right boundary), respectively.

The function implementation is as follows:

-   If $\textit{root}$ is null, then directly return.
-   If $\textit{i} = 0$, we need to find the left boundary. If $\textit{root}$ is not a leaf node, we add the value of $\textit{root}$ to $\textit{nums}$. If $\textit{root}$ has a left child, we recursively call the $\textit{dfs}$ function, passing in $\textit{nums}$, the left child of $\textit{root}$, and $\textit{i}$. Otherwise, we recursively call the $\textit{dfs}$ function, passing in $\textit{nums}$, the right child of $\textit{root}$, and $\textit{i}$.
-   If $\textit{i} = 1$, we need to find the leaf nodes. If $\textit{root}$ is a leaf node, we add the value of $\textit{root}$ to $\textit{nums}$. Otherwise, we recursively call the $\textit{dfs}$ function, passing in $\textit{nums}$, the left child of $\textit{root}$ and $\textit{i}$, as well as $\textit{nums}$, the right child of $\textit{root}$ and $\textit{i}$.
-   If $\textit{i} = 2$, we need to find the right boundary. If $\textit{root}$ is not a leaf node, we add the value of $\textit{root}$ to $\textit{nums}$. If $\textit{root}$ has a right child, we recursively call the $\textit{dfs}$ function, passing in $\textit{nums}$, the right child of $\textit{root}$, and $\textit{i}$. Otherwise, we recursively call the $\textit{dfs}$ function, passing in $\textit{nums}$, the left child of $\textit{root}$, and $\textit{i}$.

We call the $\textit{dfs}$ function separately to find the left boundary, leaf nodes, and right boundary, and then concatenate these three parts to get the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the number of nodes in the binary tree.

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
    def boundaryOfBinaryTree(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(nums: List[int], root: Optional[TreeNode], i: int):
            if root is None:
                return
            if i == 0:
                if root.left != root.right:
                    nums.append(root.val)
                    if root.left:
                        dfs(nums, root.left, i)
                    else:
                        dfs(nums, root.right, i)
            elif i == 1:
                if root.left == root.right:
                    nums.append(root.val)
                else:
                    dfs(nums, root.left, i)
                    dfs(nums, root.right, i)
            else:
                if root.left != root.right:
                    nums.append(root.val)
                    if root.right:
                        dfs(nums, root.right, i)
                    else:
                        dfs(nums, root.left, i)

        ans = [root.val]
        if root.left == root.right:
            return ans
        left, leaves, right = [], [], []
        dfs(left, root.left, 0)
        dfs(leaves, root, 1)
        dfs(right, root.right, 2)
        ans += left + leaves + right[::-1]
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        ans.add(root.val);
        if (root.left == root.right) {
            return ans;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        dfs(left, root.left, 0);
        dfs(leaves, root, 1);
        dfs(right, root.right, 2);

        ans.addAll(left);
        ans.addAll(leaves);
        Collections.reverse(right);
        ans.addAll(right);
        return ans;
    }

    private void dfs(List<Integer> nums, TreeNode root, int i) {
        if (root == null) {
            return;
        }
        if (i == 0) {
            if (root.left != root.right) {
                nums.add(root.val);
                if (root.left != null) {
                    dfs(nums, root.left, i);
                } else {
                    dfs(nums, root.right, i);
                }
            }
        } else if (i == 1) {
            if (root.left == root.right) {
                nums.add(root.val);
            } else {
                dfs(nums, root.left, i);
                dfs(nums, root.right, i);
            }
        } else {
            if (root.left != root.right) {
                nums.add(root.val);
                if (root.right != null) {
                    dfs(nums, root.right, i);
                } else {
                    dfs(nums, root.left, i);
                }
            }
        }
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
    vector<int> boundaryOfBinaryTree(TreeNode* root) {
        auto dfs = [&](this auto&& dfs, vector<int>& nums, TreeNode* root, int i) -> void {
            if (!root) {
                return;
            }
            if (i == 0) {
                if (root->left != root->right) {
                    nums.push_back(root->val);
                    if (root->left) {
                        dfs(nums, root->left, i);
                    } else {
                        dfs(nums, root->right, i);
                    }
                }
            } else if (i == 1) {
                if (root->left == root->right) {
                    nums.push_back(root->val);
                } else {
                    dfs(nums, root->left, i);
                    dfs(nums, root->right, i);
                }
            } else {
                if (root->left != root->right) {
                    nums.push_back(root->val);
                    if (root->right) {
                        dfs(nums, root->right, i);
                    } else {
                        dfs(nums, root->left, i);
                    }
                }
            }
        };
        vector<int> ans = {root->val};
        if (root->left == root->right) {
            return ans;
        }
        vector<int> left, right, leaves;
        dfs(left, root->left, 0);
        dfs(leaves, root, 1);
        dfs(right, root->right, 2);
        ans.insert(ans.end(), left.begin(), left.end());
        ans.insert(ans.end(), leaves.begin(), leaves.end());
        ans.insert(ans.end(), right.rbegin(), right.rend());
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
func boundaryOfBinaryTree(root *TreeNode) []int {
	ans := []int{root.Val}
	if root.Left == root.Right {
		return ans
	}

	left, leaves, right := []int{}, []int{}, []int{}

	var dfs func(nums *[]int, root *TreeNode, i int)
	dfs = func(nums *[]int, root *TreeNode, i int) {
		if root == nil {
			return
		}
		if i == 0 {
			if root.Left != root.Right {
				*nums = append(*nums, root.Val)
				if root.Left != nil {
					dfs(nums, root.Left, i)
				} else {
					dfs(nums, root.Right, i)
				}
			}
		} else if i == 1 {
			if root.Left == root.Right {
				*nums = append(*nums, root.Val)
			} else {
				dfs(nums, root.Left, i)
				dfs(nums, root.Right, i)
			}
		} else {
			if root.Left != root.Right {
				*nums = append(*nums, root.Val)
				if root.Right != nil {
					dfs(nums, root.Right, i)
				} else {
					dfs(nums, root.Left, i)
				}
			}
		}
	}

	dfs(&left, root.Left, 0)
	dfs(&leaves, root, 1)
	dfs(&right, root.Right, 2)

	ans = append(ans, left...)
	ans = append(ans, leaves...)
	for i := len(right) - 1; i >= 0; i-- {
		ans = append(ans, right[i])
	}

	return ans
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

function boundaryOfBinaryTree(root: TreeNode | null): number[] {
    const ans: number[] = [root.val];
    if (root.left === root.right) {
        return ans;
    }

    const left: number[] = [];
    const leaves: number[] = [];
    const right: number[] = [];

    const dfs = function (nums: number[], root: TreeNode | null, i: number) {
        if (!root) {
            return;
        }
        if (i === 0) {
            if (root.left !== root.right) {
                nums.push(root.val);
                if (root.left) {
                    dfs(nums, root.left, i);
                } else {
                    dfs(nums, root.right, i);
                }
            }
        } else if (i === 1) {
            if (root.left === root.right) {
                nums.push(root.val);
            } else {
                dfs(nums, root.left, i);
                dfs(nums, root.right, i);
            }
        } else {
            if (root.left !== root.right) {
                nums.push(root.val);
                if (root.right) {
                    dfs(nums, root.right, i);
                } else {
                    dfs(nums, root.left, i);
                }
            }
        }
    };

    dfs(left, root.left, 0);
    dfs(leaves, root, 1);
    dfs(right, root.right, 2);

    return ans.concat(left).concat(leaves).concat(right.reverse());
}
```

#### JavaScript

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
var boundaryOfBinaryTree = function (root) {
    const ans = [root.val];
    if (root.left === root.right) {
        return ans;
    }

    const left = [];
    const leaves = [];
    const right = [];

    const dfs = function (nums, root, i) {
        if (!root) {
            return;
        }
        if (i === 0) {
            if (root.left !== root.right) {
                nums.push(root.val);
                if (root.left) {
                    dfs(nums, root.left, i);
                } else {
                    dfs(nums, root.right, i);
                }
            }
        } else if (i === 1) {
            if (root.left === root.right) {
                nums.push(root.val);
            } else {
                dfs(nums, root.left, i);
                dfs(nums, root.right, i);
            }
        } else {
            if (root.left !== root.right) {
                nums.push(root.val);
                if (root.right) {
                    dfs(nums, root.right, i);
                } else {
                    dfs(nums, root.left, i);
                }
            }
        }
    };

    dfs(left, root.left, 0);
    dfs(leaves, root, 1);
    dfs(right, root.right, 2);
    return ans.concat(left).concat(leaves).concat(right.reverse());
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
