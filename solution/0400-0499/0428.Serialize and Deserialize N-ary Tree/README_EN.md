---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - String
---

<!-- problem:start -->

# [428. Serialize and Deserialize N-ary Tree 🔒](https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree)

[中文文档](/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/README.md)

## Description

<!-- description:start -->

<p>Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.</p>

<p>Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.</p>

<p>For example, you may serialize the following <code>3-ary</code> tree</p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/images/narytreeexample.png" style="width: 500px; max-width: 300px; height: 321px;" />
<p>&nbsp;</p>

<p>as <code>[1 [3[5 6] 2 4]]</code>. Note that this is just an example, you do not necessarily need to follow this format.</p>

<p>Or you can follow LeetCode&#39;s level order traversal serialization format, where each group of children is separated by the null value.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0428.Serialize%20and%20Deserialize%20N-ary%20Tree/images/sample_4_964.png" style="width: 500px; height: 454px;" />
<p>&nbsp;</p>

<p>For example, the above tree may be serialized as <code>[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]</code>.</p>

<p>You do not necessarily need to follow the above-suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>Output:</strong> [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1,null,3,2,4,null,5,6]
<strong>Output:</strong> [1,null,3,2,4,null,5,6]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>The height of the n-ary tree is less than or equal to <code>1000</code></li>
	<li>Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Use BFS
We start with the root of the tree and form a queue to store the nodes. We pop nodes from the queue to check the children of the node and push the children into the queue. This is done until the queue is empty (i.e., all nodes are traversed).

**Serialize:**
* We start with the root node and add it to a queue
* We keep on popping nodes from the queue, check its children, and add children into the queue. As we go through each node, we add its value into the serialized string. If a node has no children, we just add "n" to the string.
* This is repeated until the queue is empty and all nodes are traversed.

**Deserialize:**
* Start by creating the root node from the first element from the serialized string and add it into a queue.
* Next, go through each child of the node, create a node for each child and add it to the node's children.
* Each child node is also added to the queue to be checked for its children.
* This is repeated until the serialized string is completely traversed.

We first create a queue and push the root into the queue. While the queue is not empty, we pop a node from the queue and push the children of the current node into the queue. Here we also add the important information related to the node like its value and the count of children to the result array.

While deserializing, we start by popping nodes from the data and creating nodes for them. We keep popping as many nodes as the children count for the current node and add them to the node's children and the queue.

We repeat this process until all nodes are processed
<!-- tabs:start -->

#### Python3

```python
class Node(object):
    def __init__(self, val=0, children=0):
        self.val = val
        self.children = children

class Codec:
    def serialize(self, root):
        if root is None:
            return []

        queue = [root]
        result = [root.val]  # add root value

        while queue:
            node = queue.pop(0)
            if node is None:
                continue

            for child in node.children:
                queue.append(child)
            result.append(len(node.children))  # add count of children
            result.extend([child.val for child in node.children])  # add children values

        return result

    def deserialize(self, data):
        if not data:
            return None

        root = Node(data[0])  # get root from first index
        data = data[1:]  # remove root from data
        queue = [root]

        while queue:
            node = queue.pop(0)
            if node is None:
                continue

            for _ in range(data.pop(0)):  # check children count
                child = Node(data.pop(0))  # get child value
                node.children.append(child)
                queue.append(child)

        return root

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
