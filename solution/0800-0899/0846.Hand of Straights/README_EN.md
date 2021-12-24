# [846. Hand of Straights](https://leetcode.com/problems/hand-of-straights)

[中文文档](/solution/0800-0899/0846.Hand%20of%20Straights/README.md)

## Description

<p>Alice has a <code>hand</code> of cards, given as an array of integers.</p>

<p>Now she wants to rearrange the cards into groups so that each group is size <code>W</code>, and consists of <code>W</code> consecutive cards.</p>

<p>Return <code>true</code> if and only if she can.</p>

<p><strong>Note:</strong> This question is the same as&nbsp;1296:&nbsp;<a href="https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/">https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/</a></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> hand = [1,2,3,6,2,3,4,7,8], W = 3
<strong>Output:</strong> true
<strong>Explanation:</strong> Alice&#39;s hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> hand = [1,2,3,4,5], W = 4
<strong>Output:</strong> false
<strong>Explanation:</strong> Alice&#39;s hand can&#39;t be rearranged into groups of 4.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= hand.length &lt;= 10000</code></li>
	<li><code>0 &lt;= hand[i]&nbsp;&lt;= 10^9</code></li>
	<li><code>1 &lt;= W &lt;= hand.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for (int item : hand) {
            mp.put(item, mp.getOrDefault(item, 0) + 1);
        }

        while (mp.size() > 0) {
            int start = mp.firstKey();
            for (int i = start; i < start + groupSize; i++) {
                if (!mp.containsKey(i)) {
                    return false;
                }
                int time = mp.get(i);
                if (time == 1) {
                    mp.remove(i);
                } else {
                    mp.replace(i, time - 1);
                }
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
