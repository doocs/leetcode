# [1298. Maximum Candies You Can Get from Boxes](https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes)

[中文文档](/solution/1200-1299/1298.Maximum%20Candies%20You%20Can%20Get%20from%20Boxes/README.md)

## Description

<p>Given <code>n</code> boxes, each box is given in the format <code>[status, candies, keys, containedBoxes]</code> where:</p>

<ul>
	<li><code>status[i]</code>: an integer which is <strong>1</strong> if&nbsp;<code>box[i]</code> is open and <strong>0</strong> if&nbsp;<code>box[i]</code> is closed.</li>
	<li><code>candies[i]</code>:&nbsp;an integer representing the number of candies in <code>box[i]</code>.</li>
	<li><code>keys[i]</code>: an array contains the indices of the boxes you can open with the key in <code>box[i]</code>.</li>
	<li><code>containedBoxes[i]</code>: an array contains the indices of the boxes found in <code>box[i]</code>.</li>
</ul>

<p>You will start with some boxes given in <code>initialBoxes</code> array. You can take all the candies in any open&nbsp;box and you can use the keys in it to open new boxes and you also can use the boxes you find in it.</p>

<p>Return <em>the maximum number of candies</em> you can get following the rules above.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]

<strong>Output:</strong> 16

<strong>Explanation:</strong> You will be initially given box 0. You will find 7 candies in it and boxes 1 and 2. Box 1 is closed and you don&#39;t have a key for it so you will open box 2. You will find 4 candies and a key to box 1 in box 2.

In box 1, you will find 5 candies and box 3 but you will not find a key to box 3 so box 3 will remain closed.

Total number of candies collected = 7 + 4 + 5 = 16 candy.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]

<strong>Output:</strong> 6

<strong>Explanation:</strong> You have initially box 0. Opening it you can find boxes 1,2,3,4 and 5 and their keys. The total number of candies will be 6.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> status = [1,1,1], candies = [100,1,100], keys = [[],[0,2],[]], containedBoxes = [[],[],[]], initialBoxes = [1]

<strong>Output:</strong> 1

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> status = [1], candies = [100], keys = [[]], containedBoxes = [[]], initialBoxes = []

<strong>Output:</strong> 0

</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> status = [1,1,1], candies = [2,3,2], keys = [[],[],[]], containedBoxes = [[],[],[]], initialBoxes = [2,1,0]

<strong>Output:</strong> 7

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= status.length &lt;= 1000</code></li>
	<li><code>status.length == candies.length == keys.length == containedBoxes.length == n</code></li>
	<li><code>status[i]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>1 &lt;= candies[i] &lt;= 1000</code></li>
	<li><code><font face="monospace">0 &lt;= keys[i].length &lt;= status.length</font></code></li>
	<li><code>0 &lt;= keys[i][j] &lt; status.length</code></li>
	<li>All values in <code>keys[i]</code> are unique.</li>
	<li><code><font face="monospace">0 &lt;= </font>containedBoxes<font face="monospace">[i].length &lt;= status.length</font></code></li>
	<li><code>0 &lt;= containedBoxes[i][j] &lt; status.length</code></li>
	<li>All values in <code>containedBoxes[i]</code> are unique.</li>
	<li>Each box is contained in one box at most.</li>
	<li><code>0 &lt;= initialBoxes.length&nbsp;&lt;= status.length</code></li>
	<li><code><font face="monospace">0 &lt;= initialBoxes[i] &lt; status.length</font></code></li>
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
