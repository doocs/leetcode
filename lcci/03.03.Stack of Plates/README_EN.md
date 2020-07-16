# [03.03. Stack of Plates](https://leetcode-cn.com/problems/stack-of-plates-lcci)

[中文文档](/lcci/03.03.Stack%20of%20Plates/README.md)

## Description
<p>Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure <code>SetOfStacks</code> that mimics this.&nbsp;<code>SetOfStacks</code> should be composed of several stacks and should create a new stack once the previous one exceeds capacity. <code>SetOfStacks.push()</code> and <code>SetOfStacks.pop()</code> should behave identically to a single stack (that is, <code>pop()</code> should return the same values as it would if there were just a single stack). Follow Up: Implement a function <code>popAt(int index)</code> which performs a pop operation on a specific sub-stack.</p>



<p>You should delete the sub-stack when it becomes empty. <code>pop</code>, <code>popAt</code> should return -1 when there&#39;s no element to pop.</p>



<p><strong>Example1:</strong></p>



<pre>

<strong> Input</strong>: 

[&quot;StackOfPlates&quot;, &quot;push&quot;, &quot;push&quot;, &quot;popAt&quot;, &quot;pop&quot;, &quot;pop&quot;]

[[1], [1], [2], [1], [], []]

<strong> Output</strong>: 

[null, null, null, 2, 1, -1]

<strong> Explanation</strong>: 

</pre>



<p><strong>Example2:</strong></p>



<pre>

<strong> Input</strong>: 

[&quot;StackOfPlates&quot;, &quot;push&quot;, &quot;push&quot;, &quot;push&quot;, &quot;popAt&quot;, &quot;popAt&quot;, &quot;popAt&quot;]

[[2], [1], [2], [3], [0], [0], [0]]

<strong> Output</strong>: 

[null, null, null, null, 2, 1, 3]

</pre>




## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
