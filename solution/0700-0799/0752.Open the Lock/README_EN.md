# [752. Open the Lock](https://leetcode.com/problems/open-the-lock)

## Description
<p>

You have a lock in front of you with 4 circular wheels.  Each wheel has 10 slots: <code>'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'</code>.  The wheels can rotate freely and wrap around: for example we can turn <code>'9'</code> to be <code>'0'</code>, or <code>'0'</code> to be <code>'9'</code>.  Each move consists of turning one wheel one slot.

</p><p>

The lock initially starts at <code>'0000'</code>, a string representing the state of the 4 wheels.

</p><p>

You are given a list of <code>deadends</code> dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

</p><p>

Given a <code>target</code> representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

</p>



<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> deadends = ["0201","0101","0102","1212","2002"], target = "0202"

<b>Output:</b> 6

<b>Explanation:</b>

A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".

Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,

because the wheels of the lock become stuck after the display becomes the dead end "0102".

</pre>

</p>



<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> deadends = ["8888"], target = "0009"

<b>Output:</b> 1

<b>Explanation:</b>

We can turn the last wheel in reverse to move from "0000" -> "0009".

</pre>

</p>



<p><b>Example 3:</b><br />

<pre>

<b>Input:</b> deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"

<b>Output:</b> -1

<b>Explanation:</b>

We can't reach the target without getting stuck.

</pre>

</p>



<p><b>Example 4:</b><br />

<pre>

<b>Input:</b> deadends = ["0000"], target = "8888"

<b>Output:</b> -1

</pre>

</p>



<p><b>Note:</b><br>

<ol>

<li>The length of <code>deadends</code> will be in the range <code>[1, 500]</code>.</li>

<li><code>target</code> will not be in the list <code>deadends</code>.</li>

<li>Every string in <code>deadends</code> and the string <code>target</code> will be a string of 4 digits from the 10,000 possibilities <code>'0000'</code> to <code>'9999'</code>.</li>

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