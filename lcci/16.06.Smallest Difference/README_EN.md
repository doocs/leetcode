# [16.06. Smallest Difference](https://leetcode-cn.com/problems/smallest-difference-lcci)

## Description
<p>Given two arrays of integers, compute the pair of values (one value in each array) with the smallest (non-negative) difference. Return the difference.</p>



<p><strong>Example: </strong></p>



<pre>

<strong>Input: </strong>{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}

<strong>Output: </strong> 3, the pair (11, 8)

</pre>



<p><strong>Note: </strong></p>



<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 100000</code></li>
	<li><code>-2147483648 &lt;= a[i], b[i] &lt;= 2147483647</code></li>
	<li>The result is in the range [-2147483648, 2147483647]</li>
</ul>




## Solutions


### Python3

```python
class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        a.sort()
        b.sort()
        i, j, res = 0, 0, 2147483647
        m, n = len(a), len(b)
        while i < m and j < n:
            if a[i] == b[j]: return 0
            res = min(res, abs(a[i] - b[j]))
            if a[i] > b[j]: j += 1
            else: i += 1
        return res
        
```

### Java

```java
class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int m = a.length, n = b.length;
        int i = 0, j = 0;
        long res = Long.MAX_VALUE;
        while (i < m && j < n) {
            if (a[i] == b[j]) return 0;
            res = Math.min(res, Math.abs((long) a[i] - (long) b[j]));
            if (a[i] > b[j]) ++j;
            else ++i;
        }
        return (int) res;
    }
}
```

### ...
```

```
