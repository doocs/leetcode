# [1226. The Dining Philosophers](https://leetcode.com/problems/the-dining-philosophers)

[中文文档](/solution/1200-1299/1226.The%20Dining%20Philosophers/README.md)

## Description

<p>Five silent philosophers&nbsp;sit at a round table with bowls of spaghetti. Forks are placed between each pair of adjacent philosophers.</p>

<p>Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when they have both left and right forks. Each fork can be held by only one philosopher and so a philosopher can use the fork only if it is not being used by another philosopher. After an individual philosopher finishes eating, they need to put down both forks so that the forks become available to others. A philosopher can take the fork on their right or the one on their left as they become available, but cannot start eating before getting both forks.</p>

<p>Eating is not limited by the remaining amounts of spaghetti or stomach space; an infinite supply and an infinite demand are assumed.</p>

<p>Design a discipline of behaviour (a concurrent algorithm) such that no philosopher will starve;&nbsp;<i>i.e.</i>, each can forever continue to alternate between eating and thinking, assuming that no philosopher can know when others may want to eat or think.</p>

<p style="text-align: center"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1226.The%20Dining%20Philosophers/images/an_illustration_of_the_dining_philosophers_problem.png" style="width: 400px; height: 415px;" /></p>

<p style="text-align: center"><em>The problem statement and the image above are taken from <a href="https://en.wikipedia.org/wiki/Dining_philosophers_problem" target="_blank">wikipedia.org</a></em></p>

<p>&nbsp;</p>

<p>The philosophers&#39; ids are numbered from <strong>0</strong> to <strong>4</strong> in a <strong>clockwise</strong> order. Implement the function&nbsp;<code>void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork)</code> where:</p>

<ul>
	<li><code>philosopher</code>&nbsp;is the id of the philosopher who wants to eat.</li>
	<li><code>pickLeftFork</code>&nbsp;and&nbsp;<code>pickRightFork</code>&nbsp;are functions you can call to pick the corresponding forks of that philosopher.</li>
	<li><code>eat</code>&nbsp;is a function you can call to let the philosopher eat once he has picked&nbsp;both forks.</li>
	<li><code>putLeftFork</code>&nbsp;and&nbsp;<code>putRightFork</code>&nbsp;are functions you can call to put down the corresponding forks of that philosopher.</li>
	<li>The philosophers are assumed to be thinking as long as they are not asking to eat (the function is not being called with their number).</li>
</ul>

<p>Five threads, each representing a philosopher, will&nbsp;simultaneously use one object of your class to simulate the process. The function may be called for the same philosopher more than once, even before the last call ends.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [[4,2,1],[4,1,1],[0,1,1],[2,2,1],[2,1,1],[2,0,3],[2,1,2],[2,2,2],[4,0,3],[4,1,2],[0,2,1],[4,2,2],[3,2,1],[3,1,1],[0,0,3],[0,1,2],[0,2,2],[1,2,1],[1,1,1],[3,0,3],[3,1,2],[3,2,2],[1,0,3],[1,1,2],[1,2,2]]
<strong>Explanation:</strong>
n is the number of times each philosopher will call the function.
The output array describes the calls you made to the functions controlling the forks and the eat function, its format is:
output[i] = [a, b, c] (three integers)
- a is the id of a philosopher.
- b specifies the fork: {1 : left, 2 : right}.
- c specifies the operation: {1 : pick, 2 : put, 3 : eat}.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 60</code></li>
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
