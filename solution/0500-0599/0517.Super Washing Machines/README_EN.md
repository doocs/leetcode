# [517. Super Washing Machines](https://leetcode.com/problems/super-washing-machines)

[中文文档](/solution/0500-0599/0517.Super%20Washing%20Machines/README.md)

## Description

<p>You have <b>n</b> super washing machines on a line. Initially, each washing machine has some dresses or is empty.

</p>

<p>For each <b>move</b>, you could choose <b>any m</b> (1 &le; m &le; n) washing machines, and pass <b>one dress</b> of each washing machine to one of its adjacent washing machines <b> at the same time </b>.  </p>

<p>Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the <b>minimum number of moves</b> to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.</p>

<p><b>Example1</b>

<pre>

<b>Input:</b> [1,0,5]



<b>Output:</b> 3



<b>Explanation:</b> 

1st move:    1     0 <-- 5    =>    1     1     4

2nd move:    1 <-- 1 <-- 4    =>    2     1     3    

3rd move:    2     1 <-- 3    =>    2     2     2   

</pre>

<p><b>Example2</b>

<pre>

<b>Input:</b> [0,3,0]



<b>Output:</b> 2



<b>Explanation:</b> 

1st move:    0 <-- 3     0    =>    1     2     0    

2nd move:    1     2 --> 0    =>    1     1     1     

</pre>

<p><b>Example3</b>

<pre>

<b>Input:</b> [0,2,0]



<b>Output:</b> -1



<b>Explanation:</b> 

It's impossible to make all the three washing machines have the same number of dresses. 

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>The range of n is [1, 10000].</li>

<li>The range of dresses number in a super washing machine is [0, 1e5].</li>

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
