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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def wateringPlants(self, plants: List[int], capacity: int) -> int:
        ans, cap = 0, capacity
        for i, x in enumerate(plants):
            if cap >= x:
                cap -= x
                ans += 1
            else:
                cap = capacity - x
                ans += i * 2 + 1
        return ans
```

### **Java**

```java
class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int ans = 0, cap = capacity;
        for (int i = 0; i < plants.length; ++i) {
            if (cap >= plants[i]) {
                cap -= plants[i];
                ++ans;
            } else {
                ans += (i * 2 + 1);
                cap = capacity - plants[i];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int wateringPlants(vector<int>& plants, int capacity) {
        int ans = 0, cap = capacity;
        for (int i = 0; i < plants.size(); ++i) {
            if (cap >= plants[i]) {
                cap -= plants[i];
                ++ans;
            } else {
                cap = capacity - plants[i];
                ans += i * 2 + 1;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func wateringPlants(plants []int, capacity int) int {
	ans, cap := 0, capacity
	for i, x := range plants {
		if cap >= x {
			cap -= x
			ans++
		} else {
			cap = capacity - x
			ans += i*2 + 1
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function wateringPlants(plants: number[], capacity: number): number {
    const n = plants.length;
    let ans = 0;
    let water = capacity;
    for (let i = 0; i < n; i++) {
        if (water < plants[i]) {
            ans += i * 2 + 1;
            water = capacity - plants[i];
        } else {
            ans++;
            water -= plants[i];
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn watering_plants(plants: Vec<i32>, capacity: i32) -> i32 {
        let n = plants.len();
        let mut ans = 0;
        let mut water = capacity;
        for i in 0..n {
            if water < plants[i] {
                ans += 2 * i + 1;
                water = capacity - plants[i];
            } else {
                ans += 1;
                water -= plants[i];
            }
        }
        ans as i32
    }
}
```

### **C**

```c
int wateringPlants(int *plants, int plantsSize, int capacity) {
    int ans = 0;
    int water = capacity;
    for (int i = 0; i < plantsSize; i++) {
        if (water < plants[i]) {
            ans += i * 2 + 1;
            water = capacity - plants[i];
        } else {
            ans++;
            water -= plants[i];
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
