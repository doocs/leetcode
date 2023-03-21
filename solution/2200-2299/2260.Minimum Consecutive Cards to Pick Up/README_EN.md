# [2260. Minimum Consecutive Cards to Pick Up](https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up)

[中文文档](/solution/2200-2299/2260.Minimum%20Consecutive%20Cards%20to%20Pick%20Up/README.md)

## Description

<p>You are given an integer array <code>cards</code> where <code>cards[i]</code> represents the <strong>value</strong> of the <code>i<sup>th</sup></code> card. A pair of cards are <strong>matching</strong> if the cards have the <strong>same</strong> value.</p>

<p>Return<em> the <strong>minimum</strong> number of <strong>consecutive</strong> cards you have to pick up to have a pair of <strong>matching</strong> cards among the picked cards.</em> If it is impossible to have matching cards, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cards = [3,4,2,3,4,7]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can pick up the cards [3,4,2,3] which contain a matching pair of cards with value 3. Note that picking up the cards [4,2,3,4] is also optimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

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
        last = {}
        ans = inf
        for i, x in enumerate(cards):
            if x in last:
                ans = min(ans, i - last[x] + 1)
            last[x] = i
        return -1 if ans == inf else ans
```

### **Java**

```java
class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> last = new HashMap<>();
        int n = cards.length;
        int ans = n + 1;
        for (int i = 0; i < n; ++i) {
            if (last.containsKey(cards[i])) {
                ans = Math.min(ans, i - last.get(cards[i]) + 1);
            }
            last.put(cards[i], i);
        }
        return ans > n ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumCardPickup(vector<int>& cards) {
        unordered_map<int, int> last;
        int n = cards.size();
        int ans = n + 1;
        for (int i = 0; i < n; ++i) {
            if (last.count(cards[i])) {
                ans = min(ans, i - last[cards[i]] + 1);
            }
            last[cards[i]] = i;
        }
        return ans > n ? -1 : ans;
    }
};
```

### **Go**

```go
func minimumCardPickup(cards []int) int {
	last := map[int]int{}
	n := len(cards)
	ans := n + 1
	for i, x := range cards {
		if j, ok := last[x]; ok && ans > i-j+1 {
			ans = i - j + 1
		}
		last[x] = i
	}
	if ans > n {
		return -1
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumCardPickup(cards: number[]): number {
    const n = cards.length;
    const last = new Map<number, number>();
    let ans = n + 1;
    for (let i = 0; i < n; ++i) {
        if (last.has(cards[i])) {
            ans = Math.min(ans, i - last.get(cards[i]) + 1);
        }
        last.set(cards[i], i);
    }
    return ans > n ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
