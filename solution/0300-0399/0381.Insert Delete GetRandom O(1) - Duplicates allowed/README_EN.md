# [381. Insert Delete GetRandom O(1) - Duplicates allowed](https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed)

[中文文档](/solution/0300-0399/0381.Insert%20Delete%20GetRandom%20O%281%29%20-%20Duplicates%20allowed/README.md)

## Description

<p>Implement the <code>RandomizedCollection</code> class:</p>

<ul>
	<li><code>RandomizedCollection()</code> Initializes the <code>RandomizedCollection</code> object.</li>
	<li><code>bool insert(int val)</code> Inserts an item <code>val</code> into the multiset if not present. Returns <code>true</code> if the item was not present, <code>false</code> otherwise.</li>
	<li><code>bool remove(int val)</code> Removes an item <code>val</code> from the multiset if present. Returns <code>true</code> if the item was present, <code>false</code> otherwise. Note that if <code>val</code> has multiple occurrences in the multiset, we only remove one of them.</li>
	<li><code>int getRandom()</code> Returns a random element from the current multiset of elements (it&#39;s guaranteed that at least one element exists when this method is called). The probability of each element being returned is <strong>linearly related</strong> to the number of same values the multiset contains.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;RandomizedCollection&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;getRandom&quot;, &quot;remove&quot;, &quot;getRandom&quot;]
[[], [1], [1], [2], [], [1], []]
<strong>Output</strong>
[null, true, false, true, 2, true, 1]

<strong>Explanation</strong>
RandomizedCollection randomizedCollection = new RandomizedCollection();
randomizedCollection.insert(1);   // return True. Inserts 1 to the collection. Returns true as the collection did not contain 1.
randomizedCollection.insert(1);   // return False. Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
randomizedCollection.insert(2);   // return True. Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
randomizedCollection.getRandom(); // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
randomizedCollection.remove(1);   // return True. Removes 1 from the collection, returns true. Collection now contains [1,2].
randomizedCollection.getRandom(); // getRandom should return 1 and 2 both equally likely.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= val &lt;= 2<sup>31</sup> - 1</code></li>
	<li>At most <code>10<sup>5</sup></code> calls will be made to <code>insert</code>, <code>remove</code>, and <code>getRandom</code>.</li>
	<li>There will be <strong>at least one</strong> element in the data structure when <code>getRandom</code> is called.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you implement the functions of the class with each function works in <strong>average</strong> <code>O(1)</code> time?

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
