# [03.06. Animal Shelter](https://leetcode-cn.com/problems/animal-shelter-lcci)

## Description
<p>An animal shelter, which holds only dogs and cats, operates on a strictly&quot;first in, first out&quot; basis. People must adopt either the&quot;oldest&quot; (based on arrival time) of all animals at the shelter, or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal they would like. Create the data structures to maintain this system and implement operations such as <code>enqueue</code>, <code>dequeueAny</code>, <code>dequeueDog</code>, and <code>dequeueCat</code>. You may use the built-in Linked list data structure.</p>

<p><code>enqueue</code> method has a <code>animal</code> parameter, <code>animal[0]</code> represents the number of the animal, <code>animal[1]</code> represents the type of the animal, 0 for cat and 1 for dog.</p>

<p><code>dequeue*</code> method returns <code>[animal number, animal type]</code>, if there&#39;s no animal that can be adopted, return <code>[-1, -1]</code>.</p>

<p><strong>Example1:</strong></p>

<pre>
<strong> Input</strong>: 
[&quot;AnimalShelf&quot;, &quot;enqueue&quot;, &quot;enqueue&quot;, &quot;dequeueCat&quot;, &quot;dequeueDog&quot;, &quot;dequeueAny&quot;]
[[], [[0, 0]], [[1, 0]], [], [], []]
<strong> Output</strong>: 
[null,null,null,[0,0],[-1,-1],[1,0]]
</pre>

<p><strong>Example2:</strong></p>

<pre>
<strong> Input</strong>: 
[&quot;AnimalShelf&quot;, &quot;enqueue&quot;, &quot;enqueue&quot;, &quot;enqueue&quot;, &quot;dequeueDog&quot;, &quot;dequeueCat&quot;, &quot;dequeueAny&quot;]
[[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]
<strong> Output</strong>: 
[null,null,null,null,[2,1],[0,0],[1,0]]
</pre>

<p><strong>Note:</strong></p>

<ol>
	<li>The number of animals in the shelter will not exceed 20000.</li>
</ol>



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
