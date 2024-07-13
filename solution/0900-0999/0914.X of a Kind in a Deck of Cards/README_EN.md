---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0914.X%20of%20a%20Kind%20in%20a%20Deck%20of%20Cards/README_EN.md
tags:
    - Array
    - Hash Table
    - Math
    - Counting
    - Number Theory
---

<!-- problem:start -->

# [914. X of a Kind in a Deck of Cards](https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards)

[中文文档](/solution/0900-0999/0914.X%20of%20a%20Kind%20in%20a%20Deck%20of%20Cards/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greatest Common Divisor

First, we use an array or hash table `cnt` to count the occurrence of each number. Only when $X$ is a divisor of the greatest common divisor of all `cnt[i]`, can it satisfy the problem's requirement.

Therefore, we find the greatest common divisor $g$ of the occurrence of all numbers, and then check whether it is greater than or equal to $2$.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(n + \log M)$. Where $n$ and $M$ are the length of the array `deck` and the maximum value in the array `deck`, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasGroupsSizeX(self, deck: List[int]) -> bool:
        cnt = Counter(deck)
        return reduce(gcd, cnt.values()) >= 2
```

#### Java

```java
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : deck) {
            cnt.merge(x, 1, Integer::sum);
        }
        int g = cnt.get(deck[0]);
        for (int x : cnt.values()) {
            g = gcd(g, x);
        }
        return g >= 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasGroupsSizeX(vector<int>& deck) {
        unordered_map<int, int> cnt;
        for (int x : deck) {
            ++cnt[x];
        }
        int g = cnt[deck[0]];
        for (auto& [_, x] : cnt) {
            g = gcd(g, x);
        }
        return g >= 2;
    }
};
```

#### Go

```go
func hasGroupsSizeX(deck []int) bool {
	cnt := map[int]int{}
	for _, x := range deck {
		cnt[x]++
	}
	g := cnt[deck[0]]
	for _, x := range cnt {
		g = gcd(g, x)
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

#### TypeScript

```ts
function hasGroupsSizeX(deck: number[]): boolean {
    const cnt: Record<number, number> = {};
    for (const x of deck) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const gcd = (a: number, b: number): number => (b === 0 ? a : gcd(b, a % b));
    let g = cnt[deck[0]];
    for (const [_, x] of Object.entries(cnt)) {
        g = gcd(g, x);
    }
    return g >= 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
