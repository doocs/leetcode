# [1128. Number of Equivalent Domino Pairs](https://leetcode.com/problems/number-of-equivalent-domino-pairs)

[中文文档](/solution/1100-1199/1128.Number%20of%20Equivalent%20Domino%20Pairs/README.md)

## Description

<p>Given a list of <code>dominoes</code>, <code>dominoes[i] = [a, b]</code> is <strong>equivalent to</strong> <code>dominoes[j] = [c, d]</code> if and only if either (<code>a == c</code> and <code>b == d</code>), or (<code>a == d</code> and <code>b == c</code>) - that is, one domino can be rotated to be equal to another domino.</p>

<p>Return <em>the number of pairs </em><code>(i, j)</code><em> for which </em><code>0 &lt;= i &lt; j &lt; dominoes.length</code><em>, and </em><code>dominoes[i]</code><em> is <strong>equivalent to</strong> </em><code>dominoes[j]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> dominoes = [[1,2],[2,1],[3,4],[5,6]]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dominoes.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>dominoes[i].length == 2</code></li>
	<li><code>1 &lt;= dominoes[i][j] &lt;= 9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numEquivDominoPairs(self, dominoes: List[List[int]]) -> int:
        counter = Counter()
        ans = 0
        for a, b in dominoes:
            v = a * 10 + b if a > b else b * 10 + a
            ans += counter[v]
            counter[v] += 1
        return ans
```

### **Java**

```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;
        int[] counter = new int[100];
        for (int[] d : dominoes) {
            int v = d[0] > d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            ans += counter[v];
            ++counter[v];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] counter = new int[100];
        for (int[] d : dominoes) {
            int v = d[0] > d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            ++counter[v];
        }
        int ans = 0;
        for (int c : counter) {
            ans += c * (c - 1) / 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numEquivDominoPairs(vector<vector<int>>& dominoes) {
        vector<int> counter(100);
        int ans = 0;
        for (auto& d : dominoes) {
            int v = d[0] > d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            ans += counter[v];
            ++counter[v];
        }
        return ans;
    }
};
```

### **Go**

```go
func numEquivDominoPairs(dominoes [][]int) int {
	counter := make([]int, 100)
	for _, d := range dominoes {
		if d[1] < d[0] {
			d[0], d[1] = d[1], d[0]
		}
		v := d[0]*10 + d[1]
		counter[v]++
	}
	ans := 0
	for _, c := range counter {
		ans += c * (c - 1) / 2
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
