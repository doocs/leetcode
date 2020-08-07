# [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view)

[中文文档](/solution/0100-0199/0199.Binary%20Tree%20Right%20Side%20View/README.md)

## Description
<p>Given a binary tree, imagine yourself standing on the <em>right</em> side of it, return the values of the nodes you can see ordered from top to bottom.</p>



<p><strong>Example:</strong></p>



<pre>

<strong>Input:</strong>&nbsp;[1,2,3,null,5,null,4]

<strong>Output:</strong>&nbsp;[1, 3, 4]

<strong>Explanation:

</strong>

   1            &lt;---

 /   \

2     3         &lt;---

 \     \

  5     4       &lt;---

</pre>


## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        robot(root, ans, 0);
        return ans;
    }

    private void robot(TreeNode root, List<Integer> ans, int level) {
        if (root == null) {
            return;
        }
        if (ans.size() <= level) {
            ans.add(root.val);
        }
        ans.set(level, root.val);
        robot(root.left, ans, level + 1);
        robot(root.right, ans, level + 1);
    }
}
```

### **...**
```

```

<!-- tabs:end -->
