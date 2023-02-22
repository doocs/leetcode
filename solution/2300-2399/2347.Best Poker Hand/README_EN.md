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
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ranks = [13,2,3,1,9], suits = [&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;a&quot;]
<strong>Output:</strong> &quot;Flush&quot;
<strong>Explanation:</strong> The hand with all the cards consists of 5 cards with the same suit, so we have a &quot;Flush&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ranks = [4,4,2,4,4], suits = [&quot;d&quot;,&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]
<strong>Output:</strong> &quot;Three of a Kind&quot;
<strong>Explanation:</strong> The hand with the first, second, and fourth card consists of 3 cards with the same rank, so we have a &quot;Three of a Kind&quot;.
Note that we could also make a &quot;Pair&quot; hand but &quot;Three of a Kind&quot; is a better hand.
Also note that other cards could be used to make the &quot;Three of a Kind&quot; hand.</pre>

<p><strong class="example">Example 3:</strong></p>

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
        # if len(set(suits)) == 1:
        if all(a == b for a, b in pairwise(suits)):
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
        boolean flush = true;
        for (int i = 1; i < 5 && flush; ++i) {
            flush = suits[i] == suits[i - 1];
        }
        if (flush) {
            return "Flush";
        }
        int[] cnt = new int[14];
        boolean pair = false;
        for (int x : ranks) {
            if (++cnt[x] == 3) {
                return "Three of a Kind";
            }
            pair = pair || cnt[x] == 2;
        }
        return pair ? "Pair" : "High Card";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string bestHand(vector<int>& ranks, vector<char>& suits) {
        bool flush = true;
        for (int i = 1; i < 5 && flush; ++i) {
            flush = suits[i] == suits[i - 1];
        }
        if (flush) {
            return "Flush";
        }
        int cnt[14]{};
        bool pair = false;
        for (int& x : ranks) {
            if (++cnt[x] == 3) {
                return "Three of a Kind";
            }
            pair |= cnt[x] == 2;
        }
        return pair ? "Pair" : "High Card";
    }
};
```

### **Go**

```go
func bestHand(ranks []int, suits []byte) string {
	flush := true
	for i := 1; i < 5 && flush; i++ {
		flush = suits[i] == suits[i-1]
	}
	if flush {
		return "Flush"
	}
	cnt := [14]int{}
	pair := false
	for _, x := range ranks {
		cnt[x]++
		if cnt[x] == 3 {
			return "Three of a Kind"
		}
		pair = pair || cnt[x] == 2
	}
	if pair {
		return "Pair"
	}
	return "High Card"
}
```

### **TypeScript**

```ts
function bestHand(ranks: number[], suits: string[]): string {
    if (suits.every(v => v === suits[0])) {
        return 'Flush';
    }
    const count = new Array(14).fill(0);
    let isPair = false;
    for (const v of ranks) {
        if (++count[v] === 3) {
            return 'Three of a Kind';
        }
        isPair = isPair || count[v] === 2;
    }
    if (isPair) {
        return 'Pair';
    }
    return 'High Card';
}
```

### **Rust**

```rust
impl Solution {
    pub fn best_hand(ranks: Vec<i32>, suits: Vec<char>) -> String {
        if suits.iter().all(|v| *v == suits[0]) {
            return "Flush".to_string();
        }
        let mut count = [0; 14];
        let mut is_pair = false;
        for &v in ranks.iter() {
            let i = v as usize;
            count[i] += 1;
            if count[i] == 3 {
                return "Three of a Kind".to_string();
            }
            is_pair = is_pair || count[i] == 2;
        }
        (if is_pair { "Pair" } else { "High Card" }).to_string()
    }
}
```

### **C**

```c
char *bestHand(int *ranks, int ranksSize, char *suits, int suitsSize) {
    bool isFlush = true;
    for (int i = 1; i < suitsSize; i++) {
        if (suits[0] != suits[i]) {
            isFlush = false;
            break;
        }
    }
    if (isFlush) {
        return "Flush";
    }
    int count[14] = {0};
    bool isPair = false;
    for (int i = 0; i < ranksSize; i++) {
        if (++count[ranks[i]] == 3) {
            return "Three of a Kind";
        }
        isPair = isPair || count[ranks[i]] == 2;
    }
    if (isPair) {
        return "Pair";
    }
    return "High Card";
}
```

### **...**

```

```

<!-- tabs:end -->
