# [608. Tree Node](https://leetcode.com/problems/tree-node)

[中文文档](/solution/0600-0699/0608.Tree%20Node/README.md)

## Description

<p>Given a table <code>tree</code>, <b>id</b> is identifier of the tree node and <b>p_id</b> is its parent node&#39;s <b>id</b>.</p>

<pre>

+----+------+

| id | p_id |

+----+------+

| 1  | null |

| 2  | 1    |

| 3  | 1    |

| 4  | 2    |

| 5  | 2    |

+----+------+

</pre>

Each node in the tree can be one of three types:

<ul>
	<li>Leaf: if the node is a leaf node.</li>
	<li>Root: if the node is the root of the tree.</li>
	<li>Inner: If the node is neither a leaf node nor a root node.</li>
</ul>

<p>&nbsp;</p>

Write a query to print the node id and the type of the node. Sort your output by the node id. The result for the above sample is:

<p>&nbsp;</p>

<pre>

+----+------+

| id | Type |

+----+------+

| 1  | Root |

| 2  | Inner|

| 3  | Leaf |

| 4  | Leaf |

| 5  | Leaf |

+----+------+

</pre>

<p>&nbsp;</p>

<p><b>Explanation</b></p>

<p>&nbsp;</p>

<ul>
	<li>Node &#39;1&#39; is root node, because its parent node is NULL and it has child node &#39;2&#39; and &#39;3&#39;.</li>
	<li>Node &#39;2&#39; is inner node, because it has parent node &#39;1&#39; and child node &#39;4&#39; and &#39;5&#39;.</li>
	<li>Node &#39;3&#39;, &#39;4&#39; and &#39;5&#39; is Leaf node, because they have parent node and they don&#39;t have child node.</li>
	<br />
	<li>And here is the image of the sample tree as below:
	<p>&nbsp;</p>
	<pre>

    		  1

    		/   \

                      2       3

                    /   \

                  4       5

</pre>
	<p><b>Note</b></p>
	<p>If there is only one node on the tree, you only need to output its root attributes.</p>
	</li>
</ul>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
