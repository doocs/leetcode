# [2260. Minimum Consecutive Cards to Pick Up](https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up)

[中文文档](/solution/2200-2299/2260.Minimum%20Consecutive%20Cards%20to%20Pick%20Up/README.md)

## Description

<p>You are given an integer array <code>cards</code> where <code>cards[i]</code> represents the <strong>value</strong> of the <code>i<sup>th</sup></code> card. A pair of cards are <strong>matching</strong> if the cards have the <strong>same</strong> value.</p>

<p>Return<em> the <strong>minimum</strong> number of <strong>consecutive</strong> cards you have to pick up to have a pair of <strong>matching</strong> cards among the picked cards.</em> If it is impossible to have matching cards, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> cards = [3,4,2,3,4,7]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can pick up the cards [3,4,2,3] which contain a matching pair of cards with value 3. Note that picking up the cards [4,2,3,4] is also optimal.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> cards = [1,0,5,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no way to pick up a set of consecutive cards that contain a pair of matching cards.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= cards.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= cards[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumCardPickup(self, cards: List[int]) -> int:
        m = {}
        ans = 10**6
        for i, c in enumerate(cards):
            if c in m:
                ans = min(ans, i - m[c] + 1)
            m[c] = i
        return -1 if ans == 10**6 else ans
```

### **Java**

```java
class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> m = new HashMap<>();
        int ans = 1000000;
        for (int i = 0; i < cards.length; ++i) {
            int c = cards[i];
            if (m.containsKey(c)) {
                ans = Math.min(ans, i - m.get(c) + 1);
            }
            m.put(c, i);
        }
        return ans == 1000000 ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumCardPickup(vector<int>& cards) {
        unordered_map<int, int> m;
        int ans = 1e6;
        for (int i = 0; i < cards.size(); ++i) {
            int c = cards[i];
            if (m.count(c)) ans = min(ans, i - m[c] + 1);
            m[c] = i;
        }
        return ans == 1e6 ? -1 : ans;
    }
};
```

### **Go**

```go
func minimumCardPickup(cards []int) int {
	m := map[int]int{}
	ans := 1000000
	for i, c := range cards {
		if j, ok := m[c]; ok {
			ans = min(ans, i-j+1)
		}
		m[c] = i
	}
	if ans == 1000000 {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minimumCardPickup(cards: number[]): number {
    const n = cards.length;
    let hashMap = new Map<number, number>();
    const max = Number.MAX_SAFE_INTEGER;
    let ans = max;
    for (let i = 0; i < n; i++) {
        let num = cards[i];
        if (hashMap.has(num)) {
            ans = Math.min(i - hashMap.get(num) + 1, ans);
        }
        hashMap.set(num, i);
    }
    return ans == max ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
