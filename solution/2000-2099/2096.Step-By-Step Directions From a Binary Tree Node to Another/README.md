# [2096. 从二叉树一个节点到另一个节点每一步的方向](https://leetcode.cn/problems/step-by-step-directions-from-a-binary-tree-node-to-another)

[English Version](/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵 <strong>二叉树</strong>&nbsp;的根节点&nbsp;<code>root</code>&nbsp;，这棵二叉树总共有&nbsp;<code>n</code>&nbsp;个节点。每个节点的值为&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;中的一个整数，且互不相同。给你一个整数&nbsp;<code>startValue</code>&nbsp;，表示起点节点 <code>s</code>&nbsp;的值，和另一个不同的整数&nbsp;<code>destValue</code>&nbsp;，表示终点节点&nbsp;<code>t</code>&nbsp;的值。</p>

<p>请找到从节点&nbsp;<code>s</code>&nbsp;到节点 <code>t</code>&nbsp;的 <strong>最短路径</strong>&nbsp;，并以字符串的形式返回每一步的方向。每一步用 <strong>大写</strong>&nbsp;字母&nbsp;<code>'L'</code>&nbsp;，<code>'R'</code>&nbsp;和&nbsp;<code>'U'</code>&nbsp;分别表示一种方向：</p>

<ul>
	<li><code>'L'</code>&nbsp;表示从一个节点前往它的 <strong>左孩子</strong>&nbsp;节点。</li>
	<li><code>'R'</code>&nbsp;表示从一个节点前往它的 <strong>右孩子</strong>&nbsp;节点。</li>
	<li><code>'U'</code>&nbsp;表示从一个节点前往它的 <strong>父</strong>&nbsp;节点。</li>
</ul>

<p>请你返回从 <code>s</code>&nbsp;到 <code>t</code>&nbsp;<strong>最短路径</strong>&nbsp;每一步的方向。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/images/eg1.png" style="width: 214px; height: 163px;"></p>

<pre><b>输入：</b>root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
<b>输出：</b>"UURL"
<b>解释：</b>最短路径为：3 → 1 → 5 → 2 → 6 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/images/eg2.png" style="width: 74px; height: 102px;"></p>

<pre><b>输入：</b>root = [2,1], startValue = 2, destValue = 1
<b>输出：</b>"L"
<b>解释：</b>最短路径为：2 → 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目为&nbsp;<code>n</code>&nbsp;。</li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= n</code></li>
	<li>树中所有节点的值 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>1 &lt;= startValue, destValue &lt;= n</code></li>
	<li><code>startValue != destValue</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先预处理父子节点的关系，然后 DFS 搜索即可。

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
    def getDirections(
        self, root: Optional[TreeNode], startValue: int, destValue: int
    ) -> str:
        edges = defaultdict(list)
        ans = None
        visited = set()

        def traverse(root):
            if not root:
                return
            if root.left:
                edges[root.val].append([root.left.val, 'L'])
                edges[root.left.val].append([root.val, 'U'])
            if root.right:
                edges[root.val].append([root.right.val, 'R'])
                edges[root.right.val].append([root.val, 'U'])
            traverse(root.left)
            traverse(root.right)

        def dfs(start, dest, t):
            nonlocal ans
            if start in visited:
                return
            if start == dest:
                if ans is None or len(ans) > len(t):
                    ans = ''.join(t)
                return
            visited.add(start)
            for d, k in edges[start]:
                t.append(k)
                dfs(d, dest, t)
                t.pop()

        traverse(root)
        dfs(startValue, destValue, [])
        return ans
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
    private Map<Integer, List<List<String>>> edges;
    private Set<Integer> visited;
    private String ans;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        edges = new HashMap<>();
        visited = new HashSet<>();
        ans = null;
        traverse(root);
        dfs(startValue, destValue, new ArrayList<>());
        return ans;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            edges.computeIfAbsent(root.val, k -> new ArrayList<>()).add(Arrays.asList(String.valueOf(root.left.val), "L"));
            edges.computeIfAbsent(root.left.val, k -> new ArrayList<>()).add(Arrays.asList(String.valueOf(root.val), "U"));
        }
        if (root.right != null) {
            edges.computeIfAbsent(root.val, k -> new ArrayList<>()).add(Arrays.asList(String.valueOf(root.right.val), "R"));
            edges.computeIfAbsent(root.right.val, k -> new ArrayList<>()).add(Arrays.asList(String.valueOf(root.val), "U"));
        }
        traverse(root.left);
        traverse(root.right);
    }

    private void dfs(int start, int dest, List<String> t) {
        if (visited.contains(start)) {
            return;
        }
        if (start == dest) {
            if (ans == null || ans.length() > t.size()) {
                ans = String.join("", t);
            }
            return;
        }
        visited.add(start);
        if (edges.containsKey(start)) {
            for (List<String> item : edges.get(start)) {
                t.add(item.get(1));
                dfs(Integer.parseInt(item.get(0)), dest, t);
                t.remove(t.size() - 1);
            }
        }
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
    unordered_map<int, vector<pair<int, char>>> edges;
    unordered_set<int> visited;
    string ans;

    string getDirections(TreeNode* root, int startValue, int destValue) {
        ans = "";
        traverse(root);
        string t = "";
        dfs(startValue, destValue, t);
        return ans;
    }

    void traverse(TreeNode* root) {
        if (!root) return;
        if (root->left) {
            edges[root->val].push_back({root->left->val, 'L'});
            edges[root->left->val].push_back({root->val, 'U'});
        }
        if (root->right) {
            edges[root->val].push_back({root->right->val, 'R'});
            edges[root->right->val].push_back({root->val, 'U'});
        }
        traverse(root->left);
        traverse(root->right);
    }

    void dfs(int start, int dest, string& t) {
        if (visited.count(start)) return;
        if (start == dest) {
            if (ans == "" || ans.size() > t.size()) ans = t;
            return;
        }
        visited.insert(start);
        if (edges.count(start)) {
            for (auto& item : edges[start]) {
                t += item.second;
                dfs(item.first, dest, t);
                t.pop_back();
            }
        }
    }
};
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
