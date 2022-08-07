# [1902. Depth of BST Given Insertion Order](https://leetcode.com/problems/depth-of-bst-given-insertion-order)

[中文文档](/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>order</code> of length <code>n</code>, a <strong>permutation</strong> of integers from <code>1</code> to <code>n</code> representing the <strong>order</strong> of insertion into a <strong>binary search tree</strong>.</p>

<p>A binary search tree is defined as follows:</p>

<ul>
    <li>The left subtree of a node contains only nodes with keys <strong>less than</strong> the node&#39;s key.</li>
    <li>The right subtree of a node contains only nodes with keys <strong>greater than</strong> the node&#39;s key.</li>
    <li>Both the left and right subtrees must also be binary search trees.</li>
</ul>

<p>The binary search tree is constructed as follows:</p>

<ul>
    <li><code>order[0]</code> will be the <strong>root</strong> of the binary search tree.</li>
    <li>All subsequent elements are inserted as the <strong>child</strong> of <strong>any</strong> existing node such that the binary search tree properties hold.</li>
</ul>

<p>Return <em>the <strong>depth</strong> of the binary search tree</em>.</p>

<p>A binary tree&#39;s <strong>depth</strong> is the number of <strong>nodes</strong> along the <strong>longest path</strong> from the root node down to the farthest leaf node.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/images/1.png" style="width: 624px; height: 154px;" />

<pre>

<strong>Input:</strong> order = [2,1,4,3]

<strong>Output:</strong> 3

<strong>Explanation: </strong>The binary search tree has a depth of 3 with path 2-&gt;3-&gt;4.

</pre>

<p><strong>Example 2:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/images/2.png" style="width: 624px; height: 146px;" />

<pre>

<strong>Input:</strong> order = [2,1,3,4]

<strong>Output:</strong> 3

<strong>Explanation: </strong>The binary search tree has a depth of 3 with path 2-&gt;3-&gt;4.

</pre>

<p><strong>Example 3:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/images/3.png" style="width: 624px; height: 225px;" />

<pre>

<strong>Input:</strong> order = [1,2,3,4]

<strong>Output:</strong> 4

<strong>Explanation: </strong>The binary search tree has a depth of 4 with path 1-&gt;2-&gt;3-&gt;4.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>n == order.length</code></li>
    <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
    <li><code>order</code> is a permutation of integers between <code>1</code> and <code>n</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedDict


class Solution:
    def maxDepthBST(self, order: List[int]) -> int:
        sd = SortedDict({0: 0, inf: 0, order[0]: 1})
        ans = 1
        for v in order[1:]:
            lower = sd.bisect_left(v) - 1
            higher = lower + 1
            depth = 1 + max(sd.values()[lower], sd.values()[higher])
            ans = max(ans, depth)
            sd[v] = depth
        return ans
```

### **Java**

```java
class Solution {
    public int maxDepthBST(int[] order) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(0, 0);
        tm.put(Integer.MAX_VALUE, 0);
        tm.put(order[0], 1);
        int ans = 1;
        for (int i = 1; i < order.length; ++i) {
            int v = order[i];
            Map.Entry<Integer, Integer> lower = tm.lowerEntry(v);
            Map.Entry<Integer, Integer> higher = tm.higherEntry(v);
            int depth = 1 + Math.max(lower.getValue(), higher.getValue());
            ans = Math.max(ans, depth);
            tm.put(v, depth);
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
