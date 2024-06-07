---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0449.Serialize%20and%20Deserialize%20BST/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Design
    - Binary Search Tree
    - String
    - Binary Tree
---

<!-- problem:start -->

# [449. Serialize and Deserialize BST](https://leetcode.com/problems/serialize-and-deserialize-bst)

[中文文档](/solution/0400-0499/0449.Serialize%20and%20Deserialize%20BST/README.md)

## Description

<!-- description:start -->

<p>Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.</p>

<p>Design an algorithm to serialize and deserialize a <b>binary search tree</b>. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.</p>

<p><b>The encoded string should be as compact as possible.</b></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> root = [2,1,3]
<strong>Output:</strong> [2,1,3]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>The input tree is <strong>guaranteed</strong> to be a binary search tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Codec:
    def serialize(self, root: Optional[TreeNode]) -> str:
        """Encodes a tree to a single string."""

        def dfs(root: Optional[TreeNode]):
            if root is None:
                return
            nums.append(root.val)
            dfs(root.left)
            dfs(root.right)

        nums = []
        dfs(root)
        return " ".join(map(str, nums))

    def deserialize(self, data: str) -> Optional[TreeNode]:
        """Decodes your encoded data to tree."""

        def dfs(mi: int, mx: int) -> Optional[TreeNode]:
            nonlocal i
            if i == len(nums) or not mi <= nums[i] <= mx:
                return None
            x = nums[i]
            root = TreeNode(x)
            i += 1
            root.left = dfs(mi, x)
            root.right = dfs(x, mx)
            return root

        nums = list(map(int, data.split()))
        i = 0
        return dfs(-inf, inf)


# Your Codec object will be instantiated and called as such:
# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# tree = ser.serialize(root)
# ans = deser.deserialize(tree)
# return ans
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
public class Codec {
    private int i;
    private List<String> nums;
    private final int inf = 1 << 30;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        nums = new ArrayList<>();
        dfs(root);
        return String.join(" ", nums);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data)) {
            return null;
        }
        i = 0;
        nums = Arrays.asList(data.split(" "));
        return dfs(-inf, inf);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        nums.add(String.valueOf(root.val));
        dfs(root.left);
        dfs(root.right);
    }

    private TreeNode dfs(int mi, int mx) {
        if (i == nums.size()) {
            return null;
        }
        int x = Integer.parseInt(nums.get(i));
        if (x < mi || x > mx) {
            return null;
        }
        TreeNode root = new TreeNode(x);
        ++i;
        root.left = dfs(mi, x);
        root.right = dfs(x, mx);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
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
class Codec {
public:
    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        if (!root) {
            return "";
        }
        string data = "";
        function<void(TreeNode*)> dfs = [&](TreeNode* root) {
            if (!root) {
                return;
            }
            data += to_string(root->val) + " ";
            dfs(root->left);
            dfs(root->right);
        };
        dfs(root);
        data.pop_back();
        return data;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if (data.empty()) {
            return nullptr;
        }
        vector<int> nums = split(data, ' ');
        int i = 0;
        function<TreeNode*(int, int)> dfs = [&](int mi, int mx) -> TreeNode* {
            if (i == nums.size() || nums[i] < mi || nums[i] > mx) {
                return nullptr;
            }
            int x = nums[i++];
            TreeNode* root = new TreeNode(x);
            root->left = dfs(mi, x);
            root->right = dfs(x, mx);
            return root;
        };
        return dfs(INT_MIN, INT_MAX);
    }

    vector<int> split(const string& s, char delim) {
        vector<int> tokens;
        stringstream ss(s);
        string token;
        while (getline(ss, token, delim)) {
            tokens.push_back(stoi(token));
        }
        return tokens;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec* ser = new Codec();
// Codec* deser = new Codec();
// string tree = ser->serialize(root);
// TreeNode* ans = deser->deserialize(tree);
// return ans;
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

type Codec struct {
}

func Constructor() Codec {
	return Codec{}
}

// Serializes a tree to a single string.
func (this *Codec) serialize(root *TreeNode) string {
	if root == nil {
		return ""
	}
	data := &strings.Builder{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		data.WriteString(strconv.Itoa(root.Val))
		data.WriteByte(' ')
		dfs(root.Left)
		dfs(root.Right)
	}
	dfs(root)
	return data.String()[0 : data.Len()-1]
}

// Deserializes your encoded data to tree.
func (this *Codec) deserialize(data string) *TreeNode {
	if data == "" {
		return nil
	}
	vals := strings.Split(data, " ")
	i := 0
	var dfs func(int, int) *TreeNode
	dfs = func(mi, mx int) *TreeNode {
		if i == len(vals) {
			return nil
		}
		x, _ := strconv.Atoi(vals[i])
		if x < mi || x > mx {
			return nil
		}
		i++
		root := &TreeNode{Val: x}
		root.Left = dfs(mi, x)
		root.Right = dfs(x, mx)
		return root
	}
	return dfs(math.MinInt64, math.MaxInt64)
}

/**
 * Your Codec object will be instantiated and called as such:
 * ser := Constructor()
 * deser := Constructor()
 * tree := ser.serialize(root)
 * ans := deser.deserialize(tree)
 * return ans
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
