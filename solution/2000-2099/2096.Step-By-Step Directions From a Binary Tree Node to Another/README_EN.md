# [2096. Step-By-Step Directions From a Binary Tree Node to Another](https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another)

[中文文档](/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/README.md)

## Description

<p>You are given the <code>root</code> of a <strong>binary tree</strong> with <code>n</code> nodes. Each node is uniquely assigned a value from <code>1</code> to <code>n</code>. You are also given an integer <code>startValue</code> representing the value of the start node <code>s</code>, and a different integer <code>destValue</code> representing the value of the destination node <code>t</code>.</p>

<p>Find the <strong>shortest path</strong> starting from node <code>s</code> and ending at node <code>t</code>. Generate step-by-step directions of such path as a string consisting of only the <strong>uppercase</strong> letters <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, and <code>&#39;U&#39;</code>. Each letter indicates a specific direction:</p>

<ul>
	<li><code>&#39;L&#39;</code> means to go from a node to its <strong>left child</strong> node.</li>
	<li><code>&#39;R&#39;</code> means to go from a node to its <strong>right child</strong> node.</li>
	<li><code>&#39;U&#39;</code> means to go from a node to its <strong>parent</strong> node.</li>
</ul>

<p>Return <em>the step-by-step directions of the <strong>shortest path</strong> from node </em><code>s</code><em> to node</em> <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/images/eg1.png" style="width: 214px; height: 163px;" />
<pre>
<strong>Input:</strong> root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
<strong>Output:</strong> &quot;UURL&quot;
<strong>Explanation:</strong> The shortest path is: 3 &rarr; 1 &rarr; 5 &rarr; 2 &rarr; 6.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2096.Step-By-Step%20Directions%20From%20a%20Binary%20Tree%20Node%20to%20Another/images/eg2.png" style="width: 74px; height: 102px;" />
<pre>
<strong>Input:</strong> root = [2,1], startValue = 2, destValue = 1
<strong>Output:</strong> &quot;L&quot;
<strong>Explanation:</strong> The shortest path is: 2 &rarr; 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= n</code></li>
	<li>All the values in the tree are <strong>unique</strong>.</li>
	<li><code>1 &lt;= startValue, destValue &lt;= n</code></li>
	<li><code>startValue != destValue</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

```ts

```

### **...**

```

```

<!-- tabs:end -->
