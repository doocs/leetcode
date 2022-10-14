# [914. X of a Kind in a Deck of Cards](https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards)

[中文文档](/solution/0900-0999/0914.X%20of%20a%20Kind%20in%20a%20Deck%20of%20Cards/README.md)

## Description

<p>You are given an integer array <code>deck</code> where <code>deck[i]</code> represents the number written on the <code>i<sup>th</sup></code> card.</p>

<p>Partition the cards into <strong>one or more groups</strong> such that:</p>

<ul>
	<li>Each group has <strong>exactly</strong> <code>x</code> cards where <code>x &gt; 1</code>, and</li>
	<li>All the cards in one group have the same integer written on them.</li>
</ul>

<p>Return <code>true</code><em> if such partition is possible, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> deck = [1,2,3,4,4,3,2,1]
<strong>Output:</strong> true
<strong>Explanation</strong>: Possible partition [1,1],[2,2],[3,3],[4,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

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
        int[] cnt = new int[10000];
        for (int v : deck) {
            ++cnt[v];
        }
        int g = -1;
        for (int v : cnt) {
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
        int cnt[10000] = {0};
        for (int& v : deck) ++cnt[v];
        int g = -1;
        for (int& v : cnt) {
            if (v) {
                g = g == -1 ? v : __gcd(g, v);
            }
        }
        return g >= 2;
    }
};
```

### **Go**

```go
func hasGroupsSizeX(deck []int) bool {
	cnt := make([]int, 10000)
	for _, v := range deck {
		cnt[v]++
	}
	g := -1
	for _, v := range cnt {
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

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
