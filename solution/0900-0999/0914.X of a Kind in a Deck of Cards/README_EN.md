# [914. X of a Kind in a Deck of Cards](https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards)

[中文文档](/solution/0900-0999/0914.X%20of%20a%20Kind%20in%20a%20Deck%20of%20Cards/README.md)

## Description

<p>In a deck of cards, each card has an integer written on it.</p>

<p>Return <code>true</code> if and only if you can choose <code>X &gt;= 2</code> such that it is possible to split the entire deck into 1 or more groups of cards, where:</p>

<ul>
	<li>Each group has exactly <code>X</code> cards.</li>
	<li>All the cards in each group have the same integer.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> deck = [1,2,3,4,4,3,2,1]
<strong>Output:</strong> true
<strong>Explanation</strong>: Possible partition [1,1],[2,2],[3,3],[4,4].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> deck = [1,1,1,2,2,2,3,3]
<strong>Output:</strong> false
<strong>Explanation</strong>: No possible partition.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= deck.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= deck[i] &lt; 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hasGroupsSizeX(self, deck: List[int]) -> bool:
        vals = Counter(deck).values()
        return reduce(gcd, vals) >= 2
```

### **Java**

```java
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] counter = new int[10000];
        for (int d : deck) {
            ++counter[d];
        }
        int g = -1;
        for (int v : counter) {
            if (v > 0) {
                g = g == -1 ? v : gcd(g, v);
            }
        }
        return g >= 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool hasGroupsSizeX(vector<int>& deck) {
        vector<int> counter(10000);
        for (int& d : deck) ++counter[d];
        int g = -1;
        for (int& v : counter)
            if (v > 0)
                g = g == -1 ? v : gcd(g, v);
        return g >= 2;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
```

### **Go**

```go
func hasGroupsSizeX(deck []int) bool {
	counter := make([]int, 10000)
	for _, d := range deck {
		counter[d]++
	}
	var gcd func(a, b int) int
	gcd = func(a, b int) int {
		if b == 0 {
			return a
		}
		return gcd(b, a%b)
	}
	g := -1
	for _, v := range counter {
		if v > 0 {
			if g == -1 {
				g = v
			} else {
				g = gcd(g, v)
			}
		}
	}
	return g >= 2
}
```

### **...**

```

```

<!-- tabs:end -->
