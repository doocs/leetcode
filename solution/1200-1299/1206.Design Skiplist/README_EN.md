# [1206. Design Skiplist](https://leetcode.com/problems/design-skiplist)

[中文文档](/solution/1200-1299/1206.Design%20Skiplist/README.md)

## Description

<p>Design a Skiplist without using any built-in libraries.</p>



<p><em>A Skiplist is a data structure that takes&nbsp;O(log(n)) time&nbsp;to <code>add</code>, <code>erase</code> and <code>search</code>. Comparing with treap and red-black tree which has the same function and performance, the code length of Skiplist can be&nbsp;comparatively short and the idea behind Skiplists are just simple linked lists.</em></p>



<p><em>For example:&nbsp;we have a Skiplist containing <code>[30,40,50,60,70,90]</code> and we want to add <code>80</code> and <code>45</code> into it. The&nbsp;Skiplist works this way:</em></p>



<p><img alt="" src="/solution/1200-1299/1206.Design Skiplist/images/1506_skiplist.gif" style="width: 960px; height: 332px;" /><br />

<small>Artyom Kalinin [CC BY-SA 3.0], via <a href="https://commons.wikimedia.org/wiki/File:Skip_list_add_element-en.gif" target="_blank" title="Artyom Kalinin [CC BY-SA 3.0 (https://creativecommons.org/licenses/by-sa/3.0)], via Wikimedia Commons">Wikimedia Commons</a></small></p>



<p><em>You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With the help of the top layers, <code>add</code>&nbsp;,&nbsp;<code>erase</code>&nbsp;and <code>search&nbsp;</code>can be faster than O(n).&nbsp;It can be proven&nbsp;that the average time complexity for each operation is O(log(n)) and space complexity is O(n).</em></p>



<p>To be specific, your design should include these functions:</p>



<ul>
	<li><code>bool search(int target)</code> : Return whether&nbsp;the <code>target</code> exists in the Skiplist&nbsp;or not.</li>
	<li><code>void add(int num)</code>:&nbsp;Insert a value into the SkipList.&nbsp;</li>
	<li><code>bool erase(int num)</code>: Remove a value in&nbsp;the Skiplist.&nbsp;If <code>num</code>&nbsp;does not exist in the Skiplist, do nothing and return false. If there exists multiple <code>num</code> values, removing&nbsp;any one of them is fine.</li>
</ul>



<p>See more about Skiplist :&nbsp;<a href="https://en.wikipedia.org/wiki/Skip_list" target="_blank">https://en.wikipedia.org/wiki/Skip_list</a></p>



<p>Note that duplicates may exist in the Skiplist, your code needs to handle this situation.</p>



<p>&nbsp;</p>



<p><b>Example:</b></p>



<pre>

Skiplist skiplist = new Skiplist();



skiplist.add(1);

skiplist.add(2);

skiplist.add(3);

skiplist.search(0);   // return false.

skiplist.add(4);

skiplist.search(1);   // return true.

skiplist.erase(0);    // return false, 0 is not in skiplist.

skiplist.erase(1);    // return true.

skiplist.search(1);   // return false, 1 has already been erased.</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>
	<li><code>0 &lt;= num, target&nbsp;&lt;= 20000</code></li>
	<li>At most <code>50000</code>&nbsp;calls will be made to <code>search</code>, <code>add</code>, and <code>erase</code>.</li>
</ul>

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
