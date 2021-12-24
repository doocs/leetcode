# [1172. Dinner Plate Stacks](https://leetcode.com/problems/dinner-plate-stacks)

[中文文档](/solution/1100-1199/1172.Dinner%20Plate%20Stacks/README.md)

## Description

<p>You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks has the same&nbsp;maximum <code>capacity</code>.</p>

<p>Implement the <code>DinnerPlates</code> class:</p>

<ul>
	<li><code>DinnerPlates(int capacity)</code> Initializes the object with the maximum <code>capacity</code> of the stacks.</li>
	<li><code>void push(int val)</code>&nbsp;Pushes the given positive integer <code>val</code> into the leftmost stack with size less than <code>capacity</code>.</li>
	<li><code>int pop()</code>&nbsp;Returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns <code>-1</code> if all stacks are empty.</li>
	<li><code>int popAtStack(int index)</code>&nbsp;Returns the value at the top of the stack with the given <code>index</code> and removes it from that stack, and returns -1 if the stack with that&nbsp;given <code>index</code> is empty.</li>
</ul>

<p><strong>Example:</strong></p>

<pre>
<b>Input: </b>
[&quot;DinnerPlates&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;popAtStack&quot;,&quot;push&quot;,&quot;push&quot;,&quot;popAtStack&quot;,&quot;popAtStack&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;]
[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
<b>Output: </b>
[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]

<b>Explanation: </b>
DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
D.push(1);
D.push(2);
D.push(3);
D.push(4);
D.push(5);         // The stacks are now:  2 &nbsp;4
&nbsp;                                          1 &nbsp;3 &nbsp;5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 2.  The stacks are now:    &nbsp;4
            &nbsp;                                          1 &nbsp;3 &nbsp;5
                                                       ﹈ ﹈ ﹈
D.push(20);        // The stacks are now: 20  4
&nbsp;                                          1 &nbsp;3 &nbsp;5
                                           ﹈ ﹈ ﹈
D.push(21);        // The stacks are now: 20  4 21
&nbsp;                                          1 &nbsp;3 &nbsp;5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
             &nbsp;                                          1 &nbsp;3 &nbsp;5
                                                        ﹈ ﹈ ﹈
D.popAtStack(2);   // Returns 21.  The stacks are now:     4
             &nbsp;                                          1 &nbsp;3 &nbsp;5
                                                        ﹈ ﹈ ﹈ 
D.pop()            // Returns 5.  The stacks are now:      4
             &nbsp;                                          1 &nbsp;3 
                                                        ﹈ ﹈  
D.pop()            // Returns 4.  The stacks are now:   1 &nbsp;3 
                                                        ﹈ ﹈   
D.pop()            // Returns 3.  The stacks are now:   1 
                                                        ﹈   
D.pop()            // Returns 1.  There are no stacks.
D.pop()            // Returns -1.  There are still no stacks.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= capacity&nbsp;&lt;= 20000</code></li>
	<li><code>1 &lt;= val&nbsp;&lt;= 20000</code></li>
	<li><code>0 &lt;= index&nbsp;&lt;= 100000</code></li>
	<li>At most <code>200000</code>&nbsp;calls will be made to <code>push</code>, <code>pop</code>, and <code>popAtStack</code>.</li>
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
