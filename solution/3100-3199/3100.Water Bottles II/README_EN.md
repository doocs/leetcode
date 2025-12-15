---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3100.Water%20Bottles%20II/README_EN.md
rating: 1366
source: Weekly Contest 391 Q2
tags:
    - Math
    - Simulation
---

<!-- problem:start -->

# [3100. Water Bottles II](https://leetcode.com/problems/water-bottles-ii)

[中文文档](/solution/3100-3199/3100.Water%20Bottles%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>numBottles</code> and <code>numExchange</code>.</p>

<p><code>numBottles</code> represents the number of full water bottles that you initially have. In one operation, you can perform one of the following operations:</p>

<ul>
	<li>Drink any number of full water bottles turning them into empty bottles.</li>
	<li>Exchange <code>numExchange</code> empty bottles with one full water bottle. Then, increase <code>numExchange</code> by one.</li>
</ul>

<p>Note that you cannot exchange multiple batches of empty bottles for the same value of <code>numExchange</code>. For example, if <code>numBottles == 3</code> and <code>numExchange == 1</code>, you cannot exchange <code>3</code> empty water bottles for <code>3</code> full bottles.</p>

<p>Return <em>the <strong>maximum</strong> number of water bottles you can drink</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3100.Water%20Bottles%20II/images/exampleone1.png" style="width: 948px; height: 482px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> numBottles = 13, numExchange = 6
<strong>Output:</strong> 15
<strong>Explanation:</strong> The table above shows the number of full water bottles, empty water bottles, the value of numExchange, and the number of bottles drunk.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3100.Water%20Bottles%20II/images/example231.png" style="width: 990px; height: 642px; padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> numBottles = 10, numExchange = 3
<strong>Output:</strong> 13
<strong>Explanation:</strong> The table above shows the number of full water bottles, empty water bottles, the value of numExchange, and the number of bottles drunk.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numBottles &lt;= 100 </code></li>
	<li><code>1 &lt;= numExchange &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can drink all the full water bottles at the beginning, so initially the amount of water we drink is $\textit{numBottles}$. Then, we repeatedly perform the following operations:

- If we currently have $\textit{numExchange}$ empty bottles, we can exchange them for one full bottle. After the exchange, the value of $\textit{numExchange}$ increases by $1$. Then, we drink this bottle, increasing the total amount of water drunk by $1$, and the number of empty bottles increases by $1$.
- If we do not have $\textit{numExchange}$ empty bottles, we cannot exchange for more water and should stop.

We repeat the above process until we can no longer exchange bottles. The total amount of water drunk is the answer.

The time complexity is $O(\sqrt{n})$, where $n$ is the initial number of full bottles. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxBottlesDrunk(self, numBottles: int, numExchange: int) -> int:
        ans = numBottles
        while numBottles >= numExchange:
            numBottles -= numExchange
            numExchange += 1
            ans += 1
            numBottles += 1
        return ans
```

#### Java

```java
class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= numExchange;
            ++numExchange;
            ++ans;
            ++numBottles;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxBottlesDrunk(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= numExchange;
            ++numExchange;
            ++ans;
            ++numBottles;
        }
        return ans;
    }
};
```

#### Go

```go
func maxBottlesDrunk(numBottles int, numExchange int) int {
	ans := numBottles
	for numBottles >= numExchange {
		numBottles -= numExchange
		numExchange++
		ans++
		numBottles++
	}
	return ans
}
```

#### TypeScript

```ts
function maxBottlesDrunk(numBottles: number, numExchange: number): number {
    let ans = numBottles;
    while (numBottles >= numExchange) {
        numBottles -= numExchange;
        ++numExchange;
        ++ans;
        ++numBottles;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_bottles_drunk(mut num_bottles: i32, mut num_exchange: i32) -> i32 {
        let mut ans = num_bottles;

        while num_bottles >= num_exchange {
            num_bottles -= num_exchange;
            num_exchange += 1;
            ans += 1;
            num_bottles += 1;
        }

        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int MaxBottlesDrunk(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= numExchange;
            ++numExchange;
            ++ans;
            ++numBottles;
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $numBottles
     * @param Integer $numExchange
     * @return Integer
     */
    function maxBottlesDrunk($numBottles, $numExchange) {
        $ans = $numBottles;
        while ($numBottles >= $numExchange) {
            $numBottles -= $numExchange;
            $numExchange++;
            $ans++;
            $numBottles++;
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
