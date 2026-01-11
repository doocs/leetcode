---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0297.Serialize%20and%20Deserialize%20Binary%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 设计
    - 字符串
    - 二叉树
---

<!-- problem:start -->

# [297. 二叉树的序列化与反序列化](https://leetcode.cn/problems/serialize-and-deserialize-binary-tree)

[English Version](/solution/0200-0299/0297.Serialize%20and%20Deserialize%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。</p>

<p>请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。</p>

<p><strong>提示: </strong>输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅&nbsp;<a href="https://leetcode.cn/help-center/3812581/">LeetCode 序列化二叉树的格式</a>。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0297.Serialize%20and%20Deserialize%20Binary%20Tree/images/serdeser.jpg" style="width: 442px; height: 324px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,null,null,4,5]
<strong>输出：</strong>[1,2,3,null,null,4,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[1]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>root = [1,2]
<strong>输出：</strong>[1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中结点数在范围 <code>[0, 10<sup>4</sup>]</code> 内</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：层序遍历

我们可以采用层序遍历的方式对二叉树进行序列化，即从根节点开始，依次将二叉树的节点按照从上到下、从左到右的顺序加入队列中，然后将队列中的节点依次出队。如果节点不为空，则将其值加入序列化字符串中，否则加入特殊字符 `#`。最后将序列化字符串返回即可。

反序列化时，我们将序列化字符串按照分隔符进行切分，得到一个字符串数组，然后依次将字符串数组中的元素加入队列中。队列中的元素即为二叉树的节点，我们从队列中依次取出元素，如果元素不为 `#`，则将其转换为整数后作为节点的值，然后将该节点加入队列中，否则将其置为 `null`。最后返回根节点即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Codec:
    def serialize(self, root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """
        if root is None:
            return ""
        q = deque([root])
        ans = []
        while q:
            node = q.popleft()
            if node:
                ans.append(str(node.val))
                q.append(node.left)
                q.append(node.right)
            else:
                ans.append("#")
        return ",".join(ans)

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """
        if not data:
            return None
        vals = data.split(",")
        root = TreeNode(int(vals[0]))
        q = deque([root])
        i = 1
        while q:
            node = q.popleft()
            if vals[i] != "#":
                node.left = TreeNode(int(vals[i]))
                q.append(node.left)
            i += 1
            if vals[i] != "#":
                node.right = TreeNode(int(vals[i]))
                q.append(node.right)
            i += 1
        return root


# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.deserialize(codec.serialize(root))
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

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<String> ans = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node != null) {
                ans.add(node.val + "");
                q.offer(node.left);
                q.offer(node.right);
            } else {
                ans.add("#");
            }
        }
        return String.join(",", ans);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] vals = data.split(",");
        int i = 0;
        TreeNode root = new TreeNode(Integer.valueOf(vals[i++]));
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (!"#".equals(vals[i])) {
                node.left = new TreeNode(Integer.valueOf(vals[i]));
                q.offer(node.left);
            }
            ++i;
            if (!"#".equals(vals[i])) {
                node.right = new TreeNode(Integer.valueOf(vals[i]));
                q.offer(node.right);
            }
            ++i;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
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
        queue<TreeNode*> q{{root}};
        string ans;
        while (!q.empty()) {
            auto node = q.front();
            q.pop();
            if (node) {
                ans += to_string(node->val) + " ";
                q.push(node->left);
                q.push(node->right);
            } else {
                ans += "# ";
            }
        }
        ans.pop_back();
        return ans;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if (data == "") {
            return nullptr;
        }
        stringstream ss(data);
        string t;
        ss >> t;
        TreeNode* root = new TreeNode(stoi(t));
        queue<TreeNode*> q{{root}};
        while (!q.empty()) {
            auto node = q.front();
            q.pop();
            ss >> t;
            if (t != "#") {
                node->left = new TreeNode(stoi(t));
                q.push(node->left);
            }
            ss >> t;
            if (t != "#") {
                node->right = new TreeNode(stoi(t));
                q.push(node->right);
            }
        }
        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.deserialize(codec.serialize(root));
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
	q := []*TreeNode{root}
	ans := []string{}
	for len(q) > 0 {
		node := q[0]
		q = q[1:]
		if node != nil {
			ans = append(ans, strconv.Itoa(node.Val))
			q = append(q, node.Left)
			q = append(q, node.Right)
		} else {
			ans = append(ans, "#")
		}
	}
	return strings.Join(ans, ",")
}

// Deserializes your encoded data to tree.
func (this *Codec) deserialize(data string) *TreeNode {
	if data == "" {
		return nil
	}
	vals := strings.Split(data, ",")
	v, _ := strconv.Atoi(vals[0])
	i := 1
	root := &TreeNode{Val: v}
	q := []*TreeNode{root}
	for len(q) > 0 {
		node := q[0]
		q = q[1:]
		if x, err := strconv.Atoi(vals[i]); err == nil {
			node.Left = &TreeNode{Val: x}
			q = append(q, node.Left)
		}
		i++
		if x, err := strconv.Atoi(vals[i]); err == nil {
			node.Right = &TreeNode{Val: x}
			q = append(q, node.Right)
		}
		i++
	}
	return root
}

/**
 * Your Codec object will be instantiated and called as such:
 * ser := Constructor();
 * deser := Constructor();
 * data := ser.serialize(root);
 * ans := deser.deserialize(data);
 */
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * Encodes a tree to a single string.
 *
 * @param {TreeNode} root
 * @return {string}
 */
var serialize = function (root) {
    if (root === null) {
        return null;
    }
    const ans = [];
    const q = [root];
    let index = 0;
    while (index < q.length) {
        const node = q[index++];
        if (node !== null) {
            ans.push(node.val.toString());
            q.push(node.left);
            q.push(node.right);
        } else {
            ans.push('#');
        }
    }
    return ans.join(',');
};

/**
 * Decodes your encoded data to tree.
 *
 * @param {string} data
 * @return {TreeNode}
 */
var deserialize = function (data) {
    if (data === null) {
        return null;
    }
    const vals = data.split(',');
    let i = 0;
    const root = new TreeNode(parseInt(vals[i++]));
    const q = [root];
    let index = 0;
    while (index < q.length) {
        const node = q[index++];
        if (vals[i] !== '#') {
            node.left = new TreeNode(+vals[i]);
            q.push(node.left);
        }
        i++;
        if (vals[i] !== '#') {
            node.right = new TreeNode(+vals[i]);
            q.push(node.right);
        }
        i++;
    }
    return root;
};

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
 */
```

#### C#

```cs
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public string serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<string> ans = new List<string>();
        Queue<TreeNode> q = new Queue<TreeNode>();
        q.Enqueue(root);
        while (q.Count > 0) {
            TreeNode node = q.Dequeue();
            if (node != null) {
                ans.Add(node.val.ToString());
                q.Enqueue(node.left);
                q.Enqueue(node.right);
            } else {
                ans.Add("#");
            }
        }
        return string.Join(",", ans);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(string data) {
        if (data == null) {
            return null;
        }
        string[] vals = data.Split(',');
        int i = 0;
        TreeNode root = new TreeNode(int.Parse(vals[i++]));
        Queue<TreeNode> q = new Queue<TreeNode>();
        q.Enqueue(root);
        while (q.Count > 0) {
            TreeNode node = q.Dequeue();
            if (vals[i] != "#") {
                node.left = new TreeNode(int.Parse(vals[i]));
                q.Enqueue(node.left);
            }
            i++;
            if (vals[i] != "#") {
                node.right = new TreeNode(int.Parse(vals[i]));
                q.Enqueue(node.right);
            }
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
