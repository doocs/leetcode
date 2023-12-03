# [2954. Count the Number of Infection Sequences](https://leetcode.com/problems/count-the-number-of-infection-sequences)

[中文文档](/solution/2900-2999/2954.Count%20the%20Number%20of%20Infection%20Sequences/README.md)

## Description

<p>You are given an integer <code>n</code> and a <strong>0-indexed</strong><strong> </strong>integer array <code>sick</code> which is <strong>sorted</strong> in <strong>increasing</strong> order.</p>

<p>There are <code>n</code> children standing in a queue with positions <code>0</code> to <code>n - 1</code> assigned to them. The array <code>sick</code> contains the positions of the children who are infected with an infectious disease. An infected child at position <code>i</code> can spread the disease to either of its immediate neighboring children at positions <code>i - 1</code> and <code>i + 1</code> <strong>if</strong> they exist and are currently not infected. <strong>At most one</strong> child who was previously not infected can get infected with the disease in one second.</p>

<p>It can be shown that after a finite number of seconds, all the children in the queue will get infected with the disease. An <strong>infection sequence</strong> is the sequential order of positions in which <strong>all</strong> of the non-infected children get infected with the disease. Return <em>the total number of possible infection sequences</em>.</p>

<p>Since the answer may be large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note</strong> that an infection sequence <strong>does not</strong> contain positions of children who were already infected with the disease in the beginning.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, sick = [0,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Children at positions 1, 2, and 3 are not infected in the beginning. There are 4 possible infection sequences:
- The children at positions 1 and 3 can get infected since their positions are adjacent to the infected children 0 and 4. The child at position 1 gets infected first.
Now, the child at position 2 is adjacent to the child at position 1 who is infected and the child at position 3 is adjacent to the child at position 4 who is infected, hence either of them can get infected. The child at position 2 gets infected.
Finally, the child at position 3 gets infected because it is adjacent to children at positions 2 and 4 who are infected. The infection sequence is [1,2,3].
- The children at positions 1 and 3 can get infected because their positions are adjacent to the infected children 0 and 4. The child at position 1 gets infected first.
Now, the child at position 2 is adjacent to the child at position 1 who is infected and the child at position 3 is adjacent to the child at position 4 who is infected, hence either of them can get infected. The child at position 3 gets infected.
Finally, the child at position 2 gets infected because it is adjacent to children at positions 1 and 3 who are infected. The infection sequence is [1,3,2].
- The infection sequence is [3,1,2]. The order of infection of disease in the children can be seen as: [<u>0</u>,1,2,3,<u>4</u>] =&gt; [<u>0</u>,1,2,<u>3</u>,<u>4</u>] =&gt; [<u>0</u>,<u>1</u>,2,<u>3</u>,<u>4</u>] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>,<u>4</u>].
- The infection sequence is [3,2,1]. The order of infection of disease in the children can be seen as: [<u>0</u>,1,2,3,<u>4</u>] =&gt; [<u>0</u>,1,2,<u>3</u>,<u>4</u>] =&gt; [<u>0</u>,1,<u>2</u>,<u>3</u>,<u>4</u>] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>,<u>4</u>].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, sick = [1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Children at positions 0, 2, and 3 are not infected in the beginning. There are 3 possible infection sequences:
- The infection sequence is [0,2,3]. The order of infection of disease in the children can be seen as: [0,<u>1</u>,2,3] =&gt; [<u>0</u>,<u>1</u>,2,3] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,3] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>].
- The infection sequence is [2,0,3]. The order of infection of disease in the children can be seen as: [0,<u>1</u>,2,3] =&gt; [0,<u>1</u>,<u>2</u>,3] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,3] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>].
- The infection sequence is [2,3,0]. The order of infection of disease in the children can be seen as: [0,<u>1</u>,2,3] =&gt; [0,<u>1</u>,<u>2</u>,3] =&gt; [0,<u>1</u>,<u>2</u>,<u>3</u>] =&gt; [<u>0</u>,<u>1</u>,<u>2</u>,<u>3</u>].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sick.length &lt;= n - 1</code></li>
	<li><code>0 &lt;= sick[i] &lt;= n - 1</code></li>
	<li><code>sick</code> is sorted in increasing order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
