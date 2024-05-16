---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1936.Add%20Minimum%20Number%20of%20Rungs/README_EN.md
rating: 1322
source: Weekly Contest 250 Q2
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [1936. Add Minimum Number of Rungs](https://leetcode.com/problems/add-minimum-number-of-rungs)

[中文文档](/solution/1900-1999/1936.Add%20Minimum%20Number%20of%20Rungs/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>strictly increasing</strong> integer array <code>rungs</code> that represents the <strong>height</strong> of rungs on a ladder. You are currently on the <strong>floor</strong> at height <code>0</code>, and you want to reach the last rung.</p>

<p>You are also given an integer <code>dist</code>. You can only climb to the next highest rung if the distance between where you are currently at (the floor or on a rung) and the next rung is <strong>at most</strong> <code>dist</code>. You are able to insert rungs at any positive <strong>integer</strong> height if a rung is not already there.</p>

<p>Return <em>the <strong>minimum</strong> number of rungs that must be added to the ladder in order for you to climb to the last rung.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rungs = [1,3,5,10], dist = 2
<strong>Output:</strong> 2
<strong>Explanation:
</strong>You currently cannot reach the last rung.
Add rungs at heights 7 and 8 to climb this ladder. 
The ladder will now have rungs at [1,3,5,<u>7</u>,<u>8</u>,10].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rungs = [3,6,8,10], dist = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong>
This ladder can be climbed without adding additional rungs.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> rungs = [3,4,6,7], dist = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong>
You currently cannot reach the first rung from the ground.
Add a rung at height 1 to climb this ladder.
The ladder will now have rungs at [<u>1</u>,3,4,6,7].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rungs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rungs[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= dist &lt;= 10<sup>9</sup></code></li>
	<li><code>rungs</code> is <strong>strictly increasing</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Simulation

According to the problem description, we know that every time we plan to climb a new rung, we need to ensure that the height difference between the new rung and the current position does not exceed `dist`. Otherwise, we need to greedily insert a new rung at a distance of $dist$ from the current position, climb a new rung, and the total number of rungs to be inserted is $\lfloor \frac{b - a - 1}{dist} \rfloor$, where $a$ and $b$ are the current position and the height of the new rung, respectively. The answer is the sum of all inserted rungs.

The time complexity is $O(n)$, where $n$ is the length of `rungs`. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def addRungs(self, rungs: List[int], dist: int) -> int:
        rungs = [0] + rungs
        return sum((b - a - 1) // dist for a, b in pairwise(rungs))
```

```java
class Solution {
    public int addRungs(int[] rungs, int dist) {
        int ans = 0, prev = 0;
        for (int x : rungs) {
            ans += (x - prev - 1) / dist;
            prev = x;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int addRungs(vector<int>& rungs, int dist) {
        int ans = 0, prev = 0;
        for (int& x : rungs) {
            ans += (x - prev - 1) / dist;
            prev = x;
        }
        return ans;
    }
};
```

```go
func addRungs(rungs []int, dist int) (ans int) {
	prev := 0
	for _, x := range rungs {
		ans += (x - prev - 1) / dist
		prev = x
	}
	return
}
```

```ts
function addRungs(rungs: number[], dist: number): number {
    let ans = 0;
    let prev = 0;
    for (const x of rungs) {
        ans += ((x - prev - 1) / dist) | 0;
        prev = x;
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn add_rungs(rungs: Vec<i32>, dist: i32) -> i32 {
        let mut ans = 0;
        let mut prev = 0;

        for &x in rungs.iter() {
            ans += (x - prev - 1) / dist;
            prev = x;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
