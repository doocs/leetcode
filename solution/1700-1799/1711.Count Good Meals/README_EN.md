# [1711. Count Good Meals](https://leetcode.com/problems/count-good-meals)

[中文文档](/solution/1700-1799/1711.Count%20Good%20Meals/README.md)

## Description

<p>A <strong>good meal</strong> is a meal that contains <strong>exactly two different food items</strong> with a sum of deliciousness equal to a power of two.</p>

<p>You can pick <strong>any</strong> two different foods to make a good meal.</p>

<p>Given an array of integers <code>deliciousness</code> where <code>deliciousness[i]</code> is the deliciousness of the <code>i<sup>​​​​​​th</sup>​​​​</code>​​​​ item of food, return <em>the number of different <strong>good meals</strong> you can make from this list modulo</em> <code>10<sup>9</sup> + 7</code>.</p>

<p>Note that items with different indices are considered different even if they have the same deliciousness value.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> deliciousness = [1,3,5,7,9]
<strong>Output:</strong> 4
<strong>Explanation: </strong>The good meals are (1,3), (1,7), (3,5) and, (7,9).
Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> deliciousness = [1,1,1,3,3,3,7]
<strong>Output:</strong> 15
<strong>Explanation: </strong>The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and (1,7) with 3 ways.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= deliciousness.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= deliciousness[i] &lt;= 2<sup>20</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 1000000007
        limit = max(deliciousness) * 2
        pairs = 0
        freq = defaultdict(int)
        for d in deliciousness:
            target = 1
            while target <= limit:
                pairs = (pairs + freq[target - d]) % mod
                target = target << 1
            freq[d] += 1
        return pairs
```

### **Java**

```java
class Solution {

    private static final int MOD = 1000000007;

    public int countPairs(int[] deliciousness) {
        int limit = Arrays.stream(deliciousness).max().getAsInt() * 2;
        int pairs = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int d : deliciousness) {
            for (int sum = 1; sum <= limit; sum <<= 1) {
                int count = freq.getOrDefault(sum - d, 0);
                pairs = (pairs + count) % MOD;
            }
            freq.merge(d, 1, Integer::sum);
        }
        return pairs;
    }
}
```

### **Go**

```go
const mod int = 1e9 + 7

func countPairs(deliciousness []int) int {
	limit := 0
	for _, d := range deliciousness {
		limit = max(limit, d)
	}
	limit *= 2
	pairs := 0
	freq := make(map[int]int)
	for _, d := range deliciousness {
		for sum := 1; sum <= limit; sum <<= 1 {
			pairs = (pairs + freq[sum-d]) % mod
		}
		freq[d]++
	}
	return pairs
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

### **...**

```

```

<!-- tabs:end -->
