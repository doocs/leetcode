---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2211.Count%20Collisions%20on%20a%20Road/README_EN.md
rating: 1581
source: Weekly Contest 285 Q2
tags:
    - Stack
    - String
    - Simulation
---

<!-- problem:start -->

# [2211. Count Collisions on a Road](https://leetcode.com/problems/count-collisions-on-a-road)

[中文文档](/solution/2200-2299/2211.Count%20Collisions%20on%20a%20Road/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> cars on an infinitely long road. The cars are numbered from <code>0</code> to <code>n - 1</code> from left to right and each car is present at a <strong>unique</strong> point.</p>

<p>You are given a <strong>0-indexed</strong> string <code>directions</code> of length <code>n</code>. <code>directions[i]</code> can be either <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, or <code>&#39;S&#39;</code> denoting whether the <code>i<sup>th</sup></code> car is moving towards the <strong>left</strong>, towards the <strong>right</strong>, or <strong>staying</strong> at its current point respectively. Each moving car has the <strong>same speed</strong>.</p>

<p>The number of collisions can be calculated as follows:</p>

<ul>
	<li>When two cars moving in <strong>opposite</strong> directions collide with each other, the number of collisions increases by <code>2</code>.</li>
	<li>When a moving car collides with a stationary car, the number of collisions increases by <code>1</code>.</li>
</ul>

<p>After a collision, the cars involved can no longer move and will stay at the point where they collided. Other than that, cars cannot change their state or direction of motion.</p>

<p>Return <em>the <strong>total number of collisions</strong> that will happen on the road</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> directions = &quot;RLRSLL&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong>
The collisions that will happen on the road are:
- Cars 0 and 1 will collide with each other. Since they are moving in opposite directions, the number of collisions becomes 0 + 2 = 2.
- Cars 2 and 3 will collide with each other. Since car 3 is stationary, the number of collisions becomes 2 + 1 = 3.
- Cars 3 and 4 will collide with each other. Since car 3 is stationary, the number of collisions becomes 3 + 1 = 4.
- Cars 4 and 5 will collide with each other. After car 4 collides with car 3, it will stay at the point of collision and get hit by car 5. The number of collisions becomes 4 + 1 = 5.
Thus, the total number of collisions that will happen on the road is 5. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> directions = &quot;LLRR&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong>
No cars will collide with each other. Thus, the total number of collisions that will happen on the road is 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= directions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>directions[i]</code> is either <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, or <code>&#39;S&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

According to the problem description, when two cars moving in opposite directions collide, the collision count increases by $2$, meaning both cars stop, and the answer increases by $2$. When a moving car collides with a stationary car, the collision count increases by $1$, meaning one car stops, and the answer increases by $1$.

Obviously, the prefix $\textit{L}$ and the suffix $\textit{R}$ will not collide, so we only need to count the number of characters in the middle that are not $\textit{S}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$ or $O(1)$. Here, $n$ is the length of the string $\textit{directions}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countCollisions(self, directions: str) -> int:
        s = directions.lstrip("L").rstrip("R")
        return len(s) - s.count("S")
```

#### Java

```java
class Solution {
    public int countCollisions(String directions) {
        char[] s = directions.toCharArray();
        int n = s.length;
        int l = 0, r = n - 1;
        while (l < n && s[l] == 'L') {
            ++l;
        }
        while (r >= 0 && s[r] == 'R') {
            --r;
        }
        int ans = r - l + 1;
        for (int i = l; i <= r; ++i) {
            ans -= s[i] == 'S' ? 1 : 0;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countCollisions(string s) {
        int n = s.size();
        int l = 0, r = n - 1;
        while (l < n && s[l] == 'L') {
            ++l;
        }
        while (r >= 0 && s[r] == 'R') {
            --r;
        }
        return r - l + 1 - count(s.begin() + l, s.begin() + r + 1, 'S');
    }
};
```

#### Go

```go
func countCollisions(directions string) int {
	s := strings.TrimRight(strings.TrimLeft(directions, "L"), "R")
	return len(s) - strings.Count(s, "S")
}
```

#### TypeScript

```ts
function countCollisions(directions: string): number {
    const n = directions.length;
    let [l, r] = [0, n - 1];
    while (l < n && directions[l] == 'L') {
        ++l;
    }
    while (r >= 0 && directions[r] == 'R') {
        --r;
    }
    let ans = r - l + 1;
    for (let i = l; i <= r; ++i) {
        if (directions[i] === 'S') {
            --ans;
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {string} directions
 * @return {number}
 */
var countCollisions = function (directions) {
    const n = directions.length;
    let [l, r] = [0, n - 1];
    while (l < n && directions[l] == 'L') {
        ++l;
    }
    while (r >= 0 && directions[r] == 'R') {
        --r;
    }
    let ans = r - l + 1;
    for (let i = l; i <= r; ++i) {
        if (directions[i] === 'S') {
            --ans;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
