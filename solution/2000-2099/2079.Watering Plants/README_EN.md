---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2079.Watering%20Plants/README_EN.md
rating: 1320
source: Weekly Contest 268 Q2
tags:
    - Array
    - Simulation
---

# [2079. Watering Plants](https://leetcode.com/problems/watering-plants)

[中文文档](/solution/2000-2099/2079.Watering%20Plants/README.md)

## Description

<p>You want to water <code>n</code> plants in your garden with a watering can. The plants are arranged in a row and are labeled from <code>0</code> to <code>n - 1</code> from left to right where the <code>i<sup>th</sup></code> plant is located at <code>x = i</code>. There is a river at <code>x = -1</code> that you can refill your watering can at.</p>

<p>Each plant needs a specific amount of water. You will water the plants in the following way:</p>

<ul>
	<li>Water the plants in order from left to right.</li>
	<li>After watering the current plant, if you do not have enough water to <strong>completely</strong> water the next plant, return to the river to fully refill the watering can.</li>
	<li>You <strong>cannot</strong> refill the watering can early.</li>
</ul>

<p>You are initially at the river (i.e., <code>x = -1</code>). It takes <strong>one step</strong> to move <strong>one unit</strong> on the x-axis.</p>

<p>Given a <strong>0-indexed</strong> integer array <code>plants</code> of <code>n</code> integers, where <code>plants[i]</code> is the amount of water the <code>i<sup>th</sup></code> plant needs, and an integer <code>capacity</code> representing the watering can capacity, return <em>the <strong>number of steps</strong> needed to water all the plants</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> plants = [2,2,3,3], capacity = 5
<strong>Output:</strong> 14
<strong>Explanation:</strong> Start at the river with a full watering can:
- Walk to plant 0 (1 step) and water it. Watering can has 3 units of water.
- Walk to plant 1 (1 step) and water it. Watering can has 1 unit of water.
- Since you cannot completely water plant 2, walk back to the river to refill (2 steps).
- Walk to plant 2 (3 steps) and water it. Watering can has 2 units of water.
- Since you cannot completely water plant 3, walk back to the river to refill (3 steps).
- Walk to plant 3 (4 steps) and water it.
Steps needed = 1 + 1 + 2 + 3 + 3 + 4 = 14.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> plants = [1,1,1,4,2,3], capacity = 4
<strong>Output:</strong> 30
<strong>Explanation:</strong> Start at the river with a full watering can:
- Water plants 0, 1, and 2 (3 steps). Return to river (3 steps).
- Water plant 3 (4 steps). Return to river (4 steps).
- Water plant 4 (5 steps). Return to river (5 steps).
- Water plant 5 (6 steps).
Steps needed = 3 + 3 + 4 + 4 + 5 + 5 + 6 = 30.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> plants = [7,7,7,7,7,7,7], capacity = 8
<strong>Output:</strong> 49
<strong>Explanation:</strong> You have to refill before watering each plant.
Steps needed = 1 + 1 + 2 + 2 + 3 + 3 + 4 + 4 + 5 + 5 + 6 + 6 + 7 = 49.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == plants.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= plants[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>max(plants[i]) &lt;= capacity &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Simulation

We can simulate the process of watering the plants. We use a variable $\text{water}$ to represent the current amount of water in the watering can, initially $\text{water} = \text{capacity}$.

We traverse the plants. For each plant:

-   If the current amount of water in the watering can is enough to water this plant, we move forward one step, water this plant, and update $\text{water} = \text{water} - \text{plants}[i]$.
-   Otherwise, we need to return to the river to refill the watering can, walk back to the current position, and then move forward one step. The number of steps we need is $i \times 2 + 1$. Then we water this plant and update $\text{water} = \text{capacity} - \text{plants}[i]$.

Finally, return the total number of steps.

The time complexity is $O(n)$, where $n$ is the number of plants. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def wateringPlants(self, plants: List[int], capacity: int) -> int:
        ans, water = 0, capacity
        for i, p in enumerate(plants):
            if water >= p:
                water -= p
                ans += 1
            else:
                water = capacity - p
                ans += i * 2 + 1
        return ans
```

```java
class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int ans = 0, water = capacity;
        for (int i = 0; i < plants.length; ++i) {
            if (water >= plants[i]) {
                water -= plants[i];
                ans += 1;
            } else {
                water = capacity - plants[i];
                ans += i * 2 + 1;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int wateringPlants(vector<int>& plants, int capacity) {
        int ans = 0, water = capacity;
        for (int i = 0; i < plants.size(); ++i) {
            if (water >= plants[i]) {
                water -= plants[i];
                ans += 1;
            } else {
                water = capacity - plants[i];
                ans += i * 2 + 1;
            }
        }
        return ans;
    }
};
```

```go
func wateringPlants(plants []int, capacity int) (ans int) {
	water := capacity
	for i, p := range plants {
		if water >= p {
			water -= p
			ans++
		} else {
			water = capacity - p
			ans += i*2 + 1
		}
	}
	return
}
```

```ts
function wateringPlants(plants: number[], capacity: number): number {
    let [ans, water] = [0, capacity];
    for (let i = 0; i < plants.length; ++i) {
        if (water >= plants[i]) {
            water -= plants[i];
            ++ans;
        } else {
            water = capacity - plants[i];
            ans += i * 2 + 1;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn watering_plants(plants: Vec<i32>, capacity: i32) -> i32 {
        let mut ans = 0;
        let mut water = capacity;
        for (i, &p) in plants.iter().enumerate() {
            if water >= p {
                water -= p;
                ans += 1;
            } else {
                water = capacity - p;
                ans += (i as i32) * 2 + 1;
            }
        }
        ans
    }
}
```

```c
int wateringPlants(int* plants, int plantsSize, int capacity) {
    int ans = 0, water = capacity;
    for (int i = 0; i < plantsSize; ++i) {
        if (water >= plants[i]) {
            water -= plants[i];
            ans += 1;
        } else {
            water = capacity - plants[i];
            ans += i * 2 + 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
