# [441. Arranging Coins](https://leetcode.com/problems/arranging-coins)

[中文文档](/solution/0400-0499/0441.Arranging%20Coins/README.md)

## Description

<p>You have a total of <i>n</i> coins that you want to form in a staircase shape, where every <i>k</i>-th row must have exactly <i>k</i> coins.</p>

<p>Given <i>n</i>, find the total number of <b>full</b> staircase rows that can be formed.</p>

<p><i>n</i> is a non-negative integer and fits within the range of a 32-bit signed integer.</p>

<p><b>Example 1:</b>

<pre>

n = 5



The coins can form the following rows:

¤

¤ ¤

¤ ¤



Because the 3rd row is incomplete, we return 2.

</pre>

</p>

<p><b>Example 2:</b>

<pre>

n = 8



The coins can form the following rows:

¤

¤ ¤

¤ ¤ ¤

¤ ¤



Because the 4th row is incomplete, we return 3.

</pre>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def arrangeCoins(self, n: int) -> int:
        return int(math.sqrt(2) * math.sqrt(n + 0.125) - 0.5)
```

### **Java**

```java
class Solution {
    public int arrangeCoins(int n) {
        return (int) (Math.sqrt(2) * Math.sqrt(n + 0.125) - 0.5);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
