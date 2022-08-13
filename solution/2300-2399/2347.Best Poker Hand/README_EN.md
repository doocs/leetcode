# [2347. Best Poker Hand](https://leetcode.com/problems/best-poker-hand)

[中文文档](/solution/2300-2399/2347.Best%20Poker%20Hand/README.md)

## Description

<p>You are given an integer array <code>ranks</code> and a character array <code>suits</code>. You have <code>5</code> cards where the <code>i<sup>th</sup></code> card has a rank of <code>ranks[i]</code> and a suit of <code>suits[i]</code>.</p>

<p>The following are the types of <strong>poker hands</strong> you can make from best to worst:</p>

<ol>
	<li><code>&quot;Flush&quot;</code>: Five cards of the same suit.</li>
	<li><code>&quot;Three of a Kind&quot;</code>: Three cards of the same rank.</li>
	<li><code>&quot;Pair&quot;</code>: Two cards of the same rank.</li>
	<li><code>&quot;High Card&quot;</code>: Any single card.</li>
</ol>

<p>Return <em>a string representing the <strong>best</strong> type of <strong>poker hand</strong> you can make with the given cards.</em></p>

<p><strong>Note</strong> that the return values are <strong>case-sensitive</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> ranks = [13,2,3,1,9], suits = [&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;a&quot;]
<strong>Output:</strong> &quot;Flush&quot;
<strong>Explanation:</strong> The hand with all the cards consists of 5 cards with the same suit, so we have a &quot;Flush&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> ranks = [4,4,2,4,4], suits = [&quot;d&quot;,&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]
<strong>Output:</strong> &quot;Three of a Kind&quot;
<strong>Explanation:</strong> The hand with the first, second, and fourth card consists of 3 cards with the same rank, so we have a &quot;Three of a Kind&quot;.
Note that we could also make a &quot;Pair&quot; hand but &quot;Three of a Kind&quot; is a better hand.
Also note that other cards could be used to make the &quot;Three of a Kind&quot; hand.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> ranks = [10,10,2,12,9], suits = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;a&quot;,&quot;d&quot;]
<strong>Output:</strong> &quot;Pair&quot;
<strong>Explanation:</strong> The hand with the first and second card consists of 2 cards with the same rank, so we have a &quot;Pair&quot;.
Note that we cannot make a &quot;Flush&quot; or a &quot;Three of a Kind&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>ranks.length == suits.length == 5</code></li>
	<li><code>1 &lt;= ranks[i] &lt;= 13</code></li>
	<li><code>&#39;a&#39; &lt;= suits[i] &lt;= &#39;d&#39;</code></li>
	<li>No two cards have the same rank and suit.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def bestHand(self, ranks: List[int], suits: List[str]) -> str:
        if len(set(suits)) == 1:
            return 'Flush'
        cnt = Counter(ranks)
        if any(v >= 3 for v in cnt.values()):
            return 'Three of a Kind'
        if any(v == 2 for v in cnt.values()):
            return 'Pair'
        return 'High Card'
```

### **Java**

```java
class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        Set<Character> s = new HashSet<>();
        for (char c : suits) {
            s.add(c);
        }
        if (s.size() == 1) {
            return "Flush";
        }
        int[] cnt = new int[20];
        for (int v : ranks) {
            ++cnt[v];
            if (cnt[v] >= 3) {
                return "Three of a Kind";
            }
        }
        for (int v : cnt) {
            if (v == 2) {
                return "Pair";
            }
        }
        return "High Card";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string bestHand(vector<int>& ranks, vector<char>& suits) {
        unordered_set<char> s(suits.begin(), suits.end());
        if (s.size() == 1) return "Flush";
        vector<int> cnt(20);
        for (int v : ranks)
            if (++cnt[v] >= 3) return "Three of a Kind";
        for (int v : cnt)
            if (v == 2) return "Pair";
        return "High Card";
    }
};
```

### **Go**

```go
func bestHand(ranks []int, suits []byte) string {
	s := map[byte]bool{}
	for _, v := range suits {
		s[v] = true
	}
	if len(s) == 1 {
		return "Flush"
	}
	cnt := make([]int, 20)
	for _, v := range ranks {
		cnt[v]++
		if cnt[v] >= 3 {
			return "Three of a Kind"
		}
	}
	for _, v := range cnt {
		if v == 2 {
			return "Pair"
		}
	}
	return "High Card"
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
