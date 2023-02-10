# [2335. Minimum Amount of Time to Fill Cups](https://leetcode.com/problems/minimum-amount-of-time-to-fill-cups)

[中文文档](/solution/2300-2399/2335.Minimum%20Amount%20of%20Time%20to%20Fill%20Cups/README.md)

## Description

<p>You have a water dispenser that can dispense cold, warm, and hot water. Every second, you can either fill up <code>2</code> cups with <strong>different</strong> types of water, or <code>1</code> cup of any type of water.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>amount</code> of length <code>3</code> where <code>amount[0]</code>, <code>amount[1]</code>, and <code>amount[2]</code> denote the number of cold, warm, and hot water cups you need to fill respectively. Return <em>the <strong>minimum</strong> number of seconds needed to fill up all the cups</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> amount = [1,4,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> One way to fill up the cups is:
Second 1: Fill up a cold cup and a warm cup.
Second 2: Fill up a warm cup and a hot cup.
Second 3: Fill up a warm cup and a hot cup.
Second 4: Fill up a warm cup.
It can be proven that 4 is the minimum number of seconds needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> amount = [5,4,4]
<strong>Output:</strong> 7
<strong>Explanation:</strong> One way to fill up the cups is:
Second 1: Fill up a cold cup, and a hot cup.
Second 2: Fill up a cold cup, and a warm cup.
Second 3: Fill up a cold cup, and a warm cup.
Second 4: Fill up a warm cup, and a hot cup.
Second 5: Fill up a cold cup, and a hot cup.
Second 6: Fill up a cold cup, and a warm cup.
Second 7: Fill up a hot cup.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> amount = [5,0,0]
<strong>Output:</strong> 5
<strong>Explanation:</strong> Every second, we fill up a cold cup.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>amount.length == 3</code></li>
	<li><code>0 &lt;= amount[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def fillCups(self, amount: List[int]) -> int:
        ans = 0
        while sum(amount):
            amount.sort()
            ans += 1
            amount[2] -= 1
            amount[1] = max(0, amount[1] - 1)
        return ans
```

```python
class Solution:
    def fillCups(self, amount: List[int]) -> int:
        amount.sort()
        if amount[0] + amount[1] <= amount[2]:
            return amount[2]
        return (sum(amount) + 1) // 2
```

### **Java**

```java
class Solution {
    public int fillCups(int[] amount) {
        int ans = 0;
        while (amount[0] + amount[1] + amount[2] > 0) {
            Arrays.sort(amount);
            ++ans;
            amount[2]--;
            amount[1] = Math.max(0, amount[1] - 1);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[0] + amount[1] <= amount[2]) {
            return amount[2];
        }
        return (amount[0] + amount[1] + amount[2] + 1) / 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int fillCups(vector<int>& amount) {
        int ans = 0;
        while (amount[0] + amount[1] + amount[2]) {
            sort(amount.begin(), amount.end());
            ++ans;
            amount[2]--;
            amount[1] = max(0, amount[1] - 1);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int fillCups(vector<int>& amount) {
        sort(amount.begin(), amount.end());
        if (amount[0] + amount[1] <= amount[2]) {
            return amount[2];
        }
        return (amount[0] + amount[1] + amount[2] + 1) / 2;
    }
};
```

### **Go**

```go
func fillCups(amount []int) int {
	ans := 0
	for amount[0]+amount[1]+amount[2] > 0 {
		sort.Ints(amount)
		ans++
		amount[2]--
		if amount[1] > 0 {
			amount[1]--
		}
	}
	return ans
}
```

```go
func fillCups(amount []int) int {
	sort.Ints(amount)
	if amount[0]+amount[1] <= amount[2] {
		return amount[2]
	}
	return (amount[0] + amount[1] + amount[2] + 1) / 2
}
```

### **TypeScript**

```ts
function fillCups(amount: number[]): number {
    amount.sort((a, b) => a - b);
    let [a, b, c] = amount;
    let diff = a + b - c;
    if (diff <= 0) return c;
    else return Math.floor((diff + 1) / 2) + c;
}
```

### **Rust**

```rust
impl Solution {
    pub fn fill_cups(mut amount: Vec<i32>) -> i32 {
        amount.sort();
        let dif = amount[0] + amount[1] - amount[2];
        if dif <= 0 {
            return amount[2];
        }
        (dif + 1) / 2 + amount[2]
    }
}

### **...**

```

```

<!-- tabs:end -->
```
