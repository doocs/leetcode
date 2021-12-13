# [816. Ambiguous Coordinates](https://leetcode.com/problems/ambiguous-coordinates)

[中文文档](/solution/0800-0899/0816.Ambiguous%20Coordinates/README.md)

## Description

<p>We had some 2-dimensional coordinates, like <code>&quot;(1, 3)&quot;</code> or <code>&quot;(2, 0.5)&quot;</code>.&nbsp; Then, we removed&nbsp;all commas, decimal points, and spaces, and ended up with the string&nbsp;<code>S</code>.&nbsp; Return a list of strings representing&nbsp;all possibilities for what our original coordinates could have been.</p>

<p>Our original representation never had extraneous zeroes, so we never started with numbers like &quot;00&quot;, &quot;0.0&quot;, &quot;0.00&quot;, &quot;1.0&quot;, &quot;001&quot;, &quot;00.01&quot;, or any other number that can be represented with&nbsp;less digits.&nbsp; Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like &quot;.1&quot;.</p>

<p>The final answer list can be returned in any order.&nbsp; Also note that all coordinates in the final answer&nbsp;have exactly one space between them (occurring after the comma.)</p>

<pre>

<strong>Example 1:</strong>

<strong>Input:</strong> &quot;(123)&quot;

<strong>Output:</strong> [&quot;(1, 23)&quot;, &quot;(12, 3)&quot;, &quot;(1.2, 3)&quot;, &quot;(1, 2.3)&quot;]

</pre>

<pre>

<strong>Example 2:</strong>

<strong>Input:</strong> &quot;(00011)&quot;

<strong>Output:</strong> &nbsp;[&quot;(0.001, 1)&quot;, &quot;(0, 0.011)&quot;]

<strong>Explanation:</strong> 

0.0, 00, 0001 or 00.01 are not allowed.

</pre>

<pre>

<strong>Example 3:</strong>

<strong>Input:</strong> &quot;(0123)&quot;

<strong>Output:</strong> [&quot;(0, 123)&quot;, &quot;(0, 12.3)&quot;, &quot;(0, 1.23)&quot;, &quot;(0.1, 23)&quot;, &quot;(0.1, 2.3)&quot;, &quot;(0.12, 3)&quot;]

</pre>

<pre>

<strong>Example 4:</strong>

<strong>Input:</strong> &quot;(100)&quot;

<strong>Output:</strong> [(10, 0)]

<strong>Explanation:</strong> 

1.0 is not allowed.

</pre>

<p>&nbsp;</p>

<p><strong>Note: </strong></p>

<ul>
	<li><code>4 &lt;= S.length &lt;= 12</code>.</li>
	<li><code>S[0]</code> = &quot;(&quot;, <code>S[S.length - 1]</code> = &quot;)&quot;, and the other elements in <code>S</code> are digits.</li>
</ul>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def ambiguousCoordinates(self, s: str) -> List[str]:
        def convert(i, j):
            res = []
            for k in range(1, j - i + 1):
                left, right = s[i: i + k], s[i + k: j]
                valid = (left == '0' or not left.startswith(
                    '0')) and not right.endswith('0')
                if valid:
                    res.append(left + ('.' if k < j - i else '') + right)
            return res

        n = len(s)
        ans = []
        for i in range(2, n - 1):
            for x in convert(1, i):
                for y in convert(i, n - 1):
                    ans.append(f'({x}, {y})')
        return ans
```

### **Java**

```java
class Solution {
    public List<String> ambiguousCoordinates(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        for (int i = 2; i < n - 1; ++i) {
            for (String x : convert(s, 1, i)) {
                for (String y : convert(s, i, n - 1)) {
                    ans.add(String.format("(%s, %s)", x, y));
                }
            }
        }
        return ans;
    }

    private List<String> convert(String s, int i, int j) {
        List<String> res = new ArrayList<>();
        for (int k = 1; k <= j - i; ++k) {
            String left = s.substring(i, i + k);
            String right = s.substring(i + k, j);
            boolean valid = ("0".equals(left) || !left.startsWith("0")) && !right.endsWith("0");
            if (valid) {
                res.add(left + (k < j - i ? "." : "") + right);
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
