# [450. Delete Node in a BST](https://leetcode.com/problems/delete-node-in-a-bst)

[中文文档](/solution/0400-0499/0450.Delete%20Node%20in%20a%20BST/README.md)

## Description
<p>Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.</p>



<p>Basically, the deletion can be divided into two stages:

<ol>

<li>Search for a node to remove.</li>

<li>If the node is found, delete the node.</li>

</ol>

</p>



<p><b>Note:</b> Time complexity should be O(height of tree).</p>



<p><b>Example:</b>

<pre>

root = [5,3,6,2,4,null,7]

key = 3



    5

   / \

  3   6

 / \   \

2   4   7



Given key to delete is 3. So we find the node with value 3 and delete it.



One valid answer is [5,4,6,2,null,null,7], shown in the following BST.



    5

   / \

  4   6

 /     \

2       7



Another valid answer is [5,2,6,null,4,null,7].



    5

   / \

  2   6

   \   \

    4   7

</pre>

</p>


## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->