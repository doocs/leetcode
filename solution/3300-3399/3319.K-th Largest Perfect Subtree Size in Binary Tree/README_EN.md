---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/README_EN.md
rating: 1603
source: Weekly Contest 419 Q2
tags:
    - Tree
    - Depth-First Search
    - Binary Tree
    - Sorting
---

<!-- problem:start -->

# [3319. K-th Largest Perfect Subtree Size in Binary Tree](https://leetcode.com/problems/k-th-largest-perfect-subtree-size-in-binary-tree)

[中文文档](/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a <strong>binary tree</strong> and an integer <code>k</code>.</p>

<p>Return an integer denoting the size of the <code>k<sup>th</sup></code> <strong>largest<em> </em>perfect binary</strong><em> </em><span data-keyword="subtree">subtree</span>, or <code>-1</code> if it doesn&#39;t exist.</p>

<p>A <strong>perfect binary tree</strong> is a tree where all leaves are on the same level, and every parent has two children.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [5,3,6,5,2,5,7,1,8,null,null,6,8], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/images/tmpresl95rp-1.png" style="width: 400px; height: 173px;" /></p>

<p>The roots of the perfect binary subtrees are highlighted in black. Their sizes, in non-increasing order are <code>[3, 3, 1, 1, 1, 1, 1, 1]</code>.<br />
The <code>2<sup>nd</sup></code> largest size is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [1,2,3,4,5,6,7], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/images/tmp_s508x9e-1.png" style="width: 300px; height: 189px;" /></p>

<p>The sizes of the perfect binary subtrees in non-increasing order are <code>[7, 3, 3, 1, 1, 1, 1]</code>. The size of the largest perfect binary subtree is 7.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">root = [1,2,3,null,4], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3319.K-th%20Largest%20Perfect%20Subtree%20Size%20in%20Binary%20Tree/images/tmp74xnmpj4-1.png" style="width: 250px; height: 225px;" /></p>

<p>The sizes of the perfect binary subtrees in non-increasing order are <code>[1, 1]</code>. There are fewer than 3 perfect binary subtrees.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 2000]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 2000</code></li>
	<li><code>1 &lt;= k &lt;= 1024</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS + Sorting

We define a function $\textit{dfs}$ to calculate the size of the perfect binary subtree rooted at the current node, using an array $\textit{nums}$ to record the sizes of all perfect binary subtrees. If the subtree rooted at the current node is not a perfect binary subtree, it returns $-1$.

The execution process of the function $\textit{dfs}$ is as follows:

1. If the current node is null, return $0$;
2. Recursively calculate the sizes of the perfect binary subtrees of the left and right subtrees, denoted as $l$ and $r$ respectively;
3. If the sizes of the left and right subtrees are not equal, or if the sizes of the left and right subtrees are less than $0$, return $-1$;
4. Calculate the size of the perfect binary subtree rooted at the current node $\textit{cnt} = l + r + 1$, and add $\textit{cnt}$ to the array $\textit{nums}$;
5. Return $\textit{cnt}$.

We call the $\textit{dfs}$ function to calculate the sizes of all perfect binary subtrees. If the length of the array $\textit{nums}$ is less than $k$, return $-1$. Otherwise, sort the array $\textit{nums}$ in descending order and return the $k$-th largest perfect binary subtree size.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

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
    def kthLargestPerfectSubtree(self, root: Optional[TreeNode], k: int) -> int:
        def dfs(root: Optional[TreeNode]) -> int:
            if root is None:
                return 0
            l, r = dfs(root.left), dfs(root.right)
            if l < 0 or l != r:
                return -1
            cnt = l + r + 1
            nums.append(cnt)
            return cnt

        nums = []
        dfs(root)
        if len(nums) < k:
            return -1
        nums.sort(reverse=True)
        return nums[k - 1]
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
    private List<Integer> nums = new ArrayList<>();

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        dfs(root);
        if (nums.size() < k) {
            return -1;
        }
        nums.sort(Comparator.reverseOrder());
        return nums.get(k - 1);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        if (l < 0 || l != r) {
            return -1;
        }
        int cnt = l + r + 1;
        nums.add(cnt);
        return cnt;
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
    int kthLargestPerfectSubtree(TreeNode* root, int k) {
        vector<int> nums;
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> int {
            if (!root) {
                return 0;
            }
            int l = dfs(root->left);
            int r = dfs(root->right);
            if (l < 0 || l != r) {
                return -1;
            }
            int cnt = l + r + 1;
            nums.push_back(cnt);
            return cnt;
        };
        dfs(root);
        if (nums.size() < k) {
            return -1;
        }
        ranges::sort(nums, greater<int>());
        return nums[k - 1];
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
func kthLargestPerfectSubtree(root *TreeNode, k int) int {
	nums := []int{}
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if l < 0 || l != r {
			return -1
		}
		cnt := l + r + 1
		nums = append(nums, cnt)
		return cnt
	}
	dfs(root)
	if len(nums) < k {
		return -1
	}
	sort.Sort(sort.Reverse(sort.IntSlice(nums)))
	return nums[k-1]
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

function kthLargestPerfectSubtree(root: TreeNode | null, k: number): number {
    const nums: number[] = [];
    const dfs = (root: TreeNode | null): number => {
        if (!root) {
            return 0;
        }
        const l = dfs(root.left);
        const r = dfs(root.right);
        if (l < 0 || l !== r) {
            return -1;
        }
        const cnt = l + r + 1;
        nums.push(cnt);
        return cnt;
    };
    dfs(root);
    if (nums.length < k) {
        return -1;
    }
    return nums.sort((a, b) => b - a)[k - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
