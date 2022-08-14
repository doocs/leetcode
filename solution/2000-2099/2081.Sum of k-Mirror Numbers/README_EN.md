# [2081. Sum of k-Mirror Numbers](https://leetcode.com/problems/sum-of-k-mirror-numbers)

[中文文档](/solution/2000-2099/2081.Sum%20of%20k-Mirror%20Numbers/README.md)

## Description

<p>A <strong>k-mirror number</strong> is a <strong>positive</strong> integer <strong>without leading zeros</strong> that reads the same both forward and backward in base-10 <strong>as well as</strong> in base-k.</p>

<ul>
	<li>For example, <code>9</code> is a 2-mirror number. The representation of <code>9</code> in base-10 and base-2 are <code>9</code> and <code>1001</code> respectively, which read the same both forward and backward.</li>
	<li>On the contrary, <code>4</code> is not a 2-mirror number. The representation of <code>4</code> in base-2 is <code>100</code>, which does not read the same both forward and backward.</li>
</ul>

<p>Given the base <code>k</code> and the number <code>n</code>, return <em>the <strong>sum</strong> of the</em> <code>n</code> <em><strong>smallest</strong> k-mirror numbers</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 2, n = 5
<strong>Output:</strong> 25
<strong>Explanation:
</strong>The 5 smallest 2-mirror numbers and their representations in base-2 are listed as follows:
  base-10    base-2
    1          1
    3          11
    5          101
    7          111
    9          1001
Their sum = 1 + 3 + 5 + 7 + 9 = 25. 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 3, n = 7
<strong>Output:</strong> 499
<strong>Explanation:
</strong>The 7 smallest 3-mirror numbers are and their representations in base-3 are listed as follows:
  base-10    base-3
    1          1
    2          2
    4          11
    8          22
    121        11111
    151        12121
    212        21212
Their sum = 1 + 2 + 4 + 8 + 121 + 151 + 212 = 499.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 7, n = 17
<strong>Output:</strong> 20379000
<strong>Explanation:</strong> The 17 smallest 7-mirror numbers are:
1, 2, 3, 4, 5, 6, 8, 121, 171, 242, 292, 16561, 65656, 2137312, 4602064, 6597956, 6958596
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 30</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
    public long kMirror(int k, int n) {
        long ans = 0;
        for (int l = 1; ; ++l) {
            int x = (int) Math.pow(10, (l - 1) / 2);
            int y = (int) Math.pow(10, (l + 1) / 2);
            for (int i = x; i < y; i++) {
                long v = i;
                for (int j = l % 2 == 0 ? i : i / 10; j > 0; j /= 10) {
                    v = v * 10 + j % 10;
                }
                String ss = Long.toString(v, k);
                if (check(ss.toCharArray())) {
                    ans += v;
                    if (--n == 0) {
                        return ans;
                    }
                }
            }
        }
    }


    private boolean check(char[] c) {
        for (int i = 0, j = c.length - 1; i < j; i++, j--) {
            if (c[i] != c[j]) {
                return false;
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
