# [623. Add One Row to Tree](https://leetcode.com/problems/add-one-row-to-tree)

## Description
<p>Given the root of a binary tree, then value <code>v</code> and depth <code>d</code>, you need to add a row of nodes with value <code>v</code> at the given depth <code>d</code>. The root node is at depth 1. </p>



<p>The adding rule is: given a positive integer depth <code>d</code>, for each NOT null tree nodes <code>N</code> in depth <code>d-1</code>, create two tree nodes with value <code>v</code> as <code>N's</code> left subtree root and right subtree root. And <code>N's</code> <b>original left subtree</b> should be the left subtree of the new left subtree root, its <b>original right subtree</b> should be the right subtree of the new right subtree root. If depth <code>d</code> is 1 that means there is no depth d-1 at all, then create a tree node with value <b>v</b> as the new root of the whole original tree, and the original tree is the new root's left subtree.</p>



<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> 

A binary tree as following:

       4

     /   \

    2     6

   / \   / 

  3   1 5   



<b>v = 1</b>



<b>d = 2</b>



<b>Output:</b> 

       4

      / \

     1   1

    /     \

   2       6

  / \     / 

 3   1   5   



</pre>

</p>





<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> 

A binary tree as following:

      4

     /   

    2    

   / \   

  3   1    



<b>v = 1</b>



<b>d = 3</b>



<b>Output:</b> 

      4

     /   

    2

   / \    

  1   1

 /     \  

3       1

</pre>

</p>



<p><b>Note:</b><br>

<ol>

<li>The given d is in range [1, maximum depth of the given tree + 1].</li>

<li>The given binary tree has at least one tree node.</li>

</ol>

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