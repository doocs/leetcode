# [449. 序列化和反序列化二叉搜索树](https://leetcode.cn/problems/serialize-and-deserialize-bst)

[English Version](/solution/0400-0499/0449.Serialize%20and%20Deserialize%20BST/README_EN.md)

<!-- tags:树,深度优先搜索,广度优先搜索,设计,二叉搜索树,字符串,二叉树 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。</p>

<p>设计一个算法来序列化和反序列化<strong> 二叉搜索树</strong> 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。</p>

<p><strong>编码的字符串应尽可能紧凑。</strong></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>root = [2,1,3]
<strong>输出：</strong>[2,1,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数范围是 <code>[0, 10<sup>4</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>题目数据 <strong>保证</strong> 输入的树是一棵二叉搜索树。</li>
</ul>

## 解法

### 方法一：先序遍历

题目给定的是二叉搜索树，我们知道二叉搜索树的中序遍历是有序的，而通过“先序遍历”和“中序遍历”可以唯一确定一棵二叉树，所以我们可以通过先序遍历的结果和中序遍历的结果来唯一确定一棵二叉搜索树。

在 `serialize` 方法中，我们使用先序遍历的方式将二叉搜索树序列化为空格分隔的字符串，然后在 `deserialize` 方法中，我们将字符串按空格分割为数组，然后使用递归的方式来构建二叉搜索树。递归函数为 $dfs(mi, mx)$，表示当前节点的值必须在 $[mi, mx]$ 之间，如果当前节点的值不在 $[mi, mx]$ 之间，则说明这个节点不是当前递归树的节点，返回 `None`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉搜索树的节点数。

<!-- tabs:start -->

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

<!-- end -->
