---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 字符串
---

<!-- problem:start -->

# [428. 序列化和反序列化 N 叉树 🔒](https://leetcode.cn/problems/serialize-and-deserialize-n-ary-tree)

[English Version](/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>序列化是指将一个数据结构转化为位序列的过程，因此可以将其存储在文件中或内存缓冲区中，以便稍后在相同或不同的计算机环境中恢复结构。</p>

<p>设计一个序列化和反序列化 N 叉树的算法。一个 N 叉树是指每个节点都有不超过 N 个孩子节点的有根树。序列化 / 反序列化算法的算法实现没有限制。你只需要保证 N 叉树可以被序列化为一个字符串并且该字符串可以被反序列化成原树结构即可。</p>

<p>例如，你需要序列化下面的 <code>3-叉</code> 树。</p>

<p>&nbsp;</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/images/1727093143-BPVnoI-image.png" style="height: 321px; width: 500px;" /></p>

<p>&nbsp;</p>

<p>为&nbsp;<code>[1 [3[5 6] 2 4]]</code>。你不需要以这种形式完成，你可以自己创造和实现不同的方法。</p>

<p>或者，您可以遵循 LeetCode 的层序遍历序列化格式，其中每组孩子节点由空值分隔。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/images/1727093169-WGFOps-image.png" style="height: 454px; width: 500px;" /></p>

<p>例如，上面的树可以序列化为 <code>[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]</code></p>

<p>你不一定要遵循以上建议的格式，有很多不同的格式，所以请发挥创造力，想出不同的方法来完成本题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>输出:</strong> [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [1,null,3,2,4,null,5,6]
<strong>输出:</strong> [1,null,3,2,4,null,5,6]
</pre>

<p><strong class="example">示例 3:</strong></p>

<pre>
<strong>输入:</strong> root = []
<strong>输出:</strong> []
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目的范围是 <code>[0,&nbsp;10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>N 叉树的高度小于等于 <code>1000</code></li>
	<li>不要使用类成员 / 全局变量 / 静态变量来存储状态。你的序列化和反序列化算法应是无状态的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：层序遍历

我们可以采用层序遍历对 N 叉树进行序列化。从根节点开始，将其值加入结果，再将根节点入队。每次弹出一个节点时，依次将其所有孩子的值加入结果并入队，然后加入特殊字符 `#` 表示该节点的孩子列表结束。最后用逗号拼接成字符串返回。

反序列化时，将序列化字符串按分隔符切分。先根据第一个值创建根节点并入队，然后对队列中的每个节点，不断取出后续元素作为孩子，直到遇到 `#`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是 N 叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
"""
# Definition for a Node.
class Node(object):
    def __init__(self, val: Optional[int] = None, children: Optional[List['Node']] = None):
        self.val = val
        self.children = children
"""


class Codec:
    def serialize(self, root: 'Node') -> str:
        if root is None:
            return ''
        ans = [str(root.val)]
        q = deque([root])
        while q:
            node = q.popleft()
            for child in node.children or []:
                ans.append(str(child.val))
                q.append(child)
            ans.append('#')
        return ','.join(ans)

    def deserialize(self, data: str) -> 'Node':
        if not data:
            return None
        vals = data.split(',')
        root = Node(int(vals[0]), [])
        q = deque([root])
        i = 1
        while q:
            node = q.popleft()
            while vals[i] != '#':
                child = Node(int(vals[i]), [])
                node.children.append(child)
                q.append(child)
                i += 1
            i += 1
        return root


# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.deserialize(codec.serialize(root))
```

#### Java

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Codec {
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        List<String> ans = new ArrayList<>();
        Deque<Node> q = new ArrayDeque<>();
        ans.add(String.valueOf(root.val));
        q.offer(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.children != null) {
                for (Node child : node.children) {
                    ans.add(String.valueOf(child.val));
                    q.offer(child);
                }
            }
            ans.add("#");
        }
        return String.join(",", ans);
    }

    public Node deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        String[] vals = data.split(",");
        Node root = new Node(Integer.parseInt(vals[0]), new ArrayList<>());
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty()) {
            Node node = q.poll();
            while (!"#".equals(vals[i])) {
                Node child = new Node(Integer.parseInt(vals[i++]), new ArrayList<>());
                node.children.add(child);
                q.offer(child);
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
/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Codec {
public:
    string serialize(Node* root) {
        if (!root) {
            return "";
        }
        queue<Node*> q{{root}};
        string ans = to_string(root->val);
        while (!q.empty()) {
            auto node = q.front();
            q.pop();
            for (auto child : node->children) {
                ans += "," + to_string(child->val);
                q.push(child);
            }
            ans += ",#";
        }
        return ans;
    }

    Node* deserialize(string data) {
        if (data.empty()) {
            return nullptr;
        }
        stringstream ss(data);
        string t;
        getline(ss, t, ',');
        Node* root = new Node(stoi(t), {});
        queue<Node*> q{{root}};
        while (!q.empty()) {
            auto node = q.front();
            q.pop();
            while (getline(ss, t, ',') && t != "#") {
                Node* child = new Node(stoi(t), {});
                node->children.push_back(child);
                q.push(child);
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
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

type Codec struct {
}

func Constructor() *Codec {
	return &Codec{}
}

func (this *Codec) serialize(root *Node) string {
	if root == nil {
		return ""
	}
	ans := []string{strconv.Itoa(root.Val)}
	q := []*Node{root}
	for len(q) > 0 {
		node := q[0]
		q = q[1:]
		for _, child := range node.Children {
			ans = append(ans, strconv.Itoa(child.Val))
			q = append(q, child)
		}
		ans = append(ans, "#")
	}
	return strings.Join(ans, ",")
}

func (this *Codec) deserialize(data string) *Node {
	if data == "" {
		return nil
	}
	vals := strings.Split(data, ",")
	v, _ := strconv.Atoi(vals[0])
	root := &Node{Val: v}
	q := []*Node{root}
	i := 1
	for len(q) > 0 {
		node := q[0]
		q = q[1:]
		for i < len(vals) && vals[i] != "#" {
			v, _ = strconv.Atoi(vals[i])
			child := &Node{Val: v}
			node.Children = append(node.Children, child)
			q = append(q, child)
			i++
		}
		i++
	}
	return root
}

/**
 * Your Codec object will be instantiated and called as such:
 * obj := Constructor();
 * data := obj.serialize(root);
 * ans := obj.deserialize(data);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
