---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2105.Watering%20Plants%20II/README_EN.md
rating: 1507
tags:
    - Array
    - Two Pointers
    - Simulation
---

# [2105. Watering Plants II](https://leetcode.com/problems/watering-plants-ii)

[中文文档](/solution/2100-2199/2105.Watering%20Plants%20II/README.md)

## Description

<p>Alice and Bob want to water <code>n</code> plants in their garden. The plants are arranged in a row and are labeled from <code>0</code> to <code>n - 1</code> from left to right where the <code>i<sup>th</sup></code> plant is located at <code>x = i</code>.</p>

<p>Each plant needs a specific amount of water. Alice and Bob have a watering can each, <strong>initially full</strong>. They water the plants in the following way:</p>

<ul>
	<li>Alice waters the plants in order from <strong>left to right</strong>, starting from the <code>0<sup>th</sup></code> plant. Bob waters the plants in order from <strong>right to left</strong>, starting from the <code>(n - 1)<sup>th</sup></code> plant. They begin watering the plants <strong>simultaneously</strong>.</li>
	<li>It takes the same amount of time to water each plant regardless of how much water it needs.</li>
	<li>Alice/Bob <strong>must</strong> water the plant if they have enough in their can to <strong>fully</strong> water it. Otherwise, they <strong>first</strong> refill their can (instantaneously) then water the plant.</li>
	<li>In case both Alice and Bob reach the same plant, the one with <strong>more</strong> water currently in his/her watering can should water this plant. If they have the same amount of water, then Alice should water this plant.</li>
</ul>

<p>Given a <strong>0-indexed</strong> integer array <code>plants</code> of <code>n</code> integers, where <code>plants[i]</code> is the amount of water the <code>i<sup>th</sup></code> plant needs, and two integers <code>capacityA</code> and <code>capacityB</code> representing the capacities of Alice&#39;s and Bob&#39;s watering cans respectively, return <em>the <strong>number of times</strong> they have to refill to water all the plants</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> plants = [2,2,3,3], capacityA = 5, capacityB = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong>
- Initially, Alice and Bob have 5 units of water each in their watering cans.
- Alice waters plant 0, Bob waters plant 3.
- Alice and Bob now have 3 units and 2 units of water respectively.
- Alice has enough water for plant 1, so she waters it. Bob does not have enough water for plant 2, so he refills his can then waters it.
So, the total number of times they have to refill to water all the plants is 0 + 0 + 1 + 0 = 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> plants = [2,2,3,3], capacityA = 3, capacityB = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong>
- Initially, Alice and Bob have 3 units and 4 units of water in their watering cans respectively.
- Alice waters plant 0, Bob waters plant 3.
- Alice and Bob now have 1 unit of water each, and need to water plants 1 and 2 respectively.
- Since neither of them have enough water for their current plants, they refill their cans and then water the plants.
So, the total number of times they have to refill to water all the plants is 0 + 1 + 1 + 0 = 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> plants = [5], capacityA = 10, capacityB = 8
<strong>Output:</strong> 0
<strong>Explanation:</strong>
- There is only one plant.
- Alice&#39;s watering can has 10 units of water, whereas Bob&#39;s can has 8 units. Since Alice has more water in her can, she waters this plant.
So, the total number of times they have to refill is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == plants.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= plants[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>max(plants[i]) &lt;= capacityA, capacityB &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Two Pointers + Simulation

We use two variables $a$ and $b$ to represent the amount of water Alice and Bob have, initially $a = \text{capacityA}$, $b = \text{capacityB}$. Then we use two pointers $i$ and $j$ to point to the head and tail of the plant array, and simulate the process of Alice and Bob watering from both ends to the middle.

When $i < j$, we judge whether Alice and Bob have enough water to water the plants. If not, we refill the watering cans. Then we update the amount of water $a$ and $b$, and move the pointers $i$ and $j$. Finally, we need to judge whether $i$ and $j$ are equal. If they are equal, we need to judge whether $\max(a, b)$ is less than the amount of water the plant needs. If it is less, we need to refill the watering cans again.

The time complexity is $O(n)$, where $n$ is the length of the plant array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumRefill(self, plants: List[int], capacityA: int, capacityB: int) -> int:
        a, b = capacityA, capacityB
        ans = 0
        i, j = 0, len(plants) - 1
        while i < j:
            if a < plants[i]:
                ans += 1
                a = capacityA
            a -= plants[i]
            if b < plants[j]:
                ans += 1
                b = capacityB
            b -= plants[j]
            i, j = i + 1, j - 1
        ans += i == j and max(a, b) < plants[i]
        return ans
```

```java
class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int a = capacityA, b = capacityB;
        int ans = 0;
        int i = 0, j = plants.length - 1;
        for (; i < j; ++i, --j) {
            if (a < plants[i]) {
                ++ans;
                a = capacityA;
            }
            a -= plants[i];
            if (b < plants[j]) {
                ++ans;
                b = capacityB;
            }
            b -= plants[j];
        }
        ans += i == j && Math.max(a, b) < plants[i] ? 1 : 0;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumRefill(vector<int>& plants, int capacityA, int capacityB) {
        int a = capacityA, b = capacityB;
        int ans = 0;
        int i = 0, j = plants.size() - 1;
        for (; i < j; ++i, --j) {
            if (a < plants[i]) {
                ++ans;
                a = capacityA;
            }
            a -= plants[i];
            if (b < plants[j]) {
                ++ans;
                b = capacityB;
            }
            b -= plants[j];
        }
        ans += i == j && max(a, b) < plants[i];
        return ans;
    }
};
```

```go
func minimumRefill(plants []int, capacityA int, capacityB int) (ans int) {
	a, b := capacityA, capacityB
	i, j := 0, len(plants)-1
	for ; i < j; i, j = i+1, j-1 {
		if a < plants[i] {
			ans++
			a = capacityA
		}
		a -= plants[i]
		if b < plants[j] {
			ans++
			b = capacityB
		}
		b -= plants[j]
	}
	if i == j && max(a, b) < plants[i] {
		ans++
	}
	return
}
```

```ts
function minimumRefill(plants: number[], capacityA: number, capacityB: number): number {
    let [a, b] = [capacityA, capacityB];
    let ans = 0;
    let [i, j] = [0, plants.length - 1];
    for (; i < j; ++i, --j) {
        if (a < plants[i]) {
            ++ans;
            a = capacityA;
        }
        a -= plants[i];
        if (b < plants[j]) {
            ++ans;
            b = capacityB;
        }
        b -= plants[j];
    }
    ans += i === j && Math.max(a, b) < plants[i] ? 1 : 0;
    return ans;
}
```

```rust
impl Solution {
    pub fn minimum_refill(plants: Vec<i32>, capacity_a: i32, capacity_b: i32) -> i32 {
        let mut a = capacity_a;
        let mut b = capacity_b;
        let mut ans = 0;
        let mut i = 0;
        let mut j = plants.len() - 1;

        while i < j {
            if a < plants[i] {
                ans += 1;
                a = capacity_a;
            }
            a -= plants[i];

            if b < plants[j] {
                ans += 1;
                b = capacity_b;
            }
            b -= plants[j];

            i += 1;
            j -= 1;
        }

        if i == j && a.max(b) < plants[i] {
            ans += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
