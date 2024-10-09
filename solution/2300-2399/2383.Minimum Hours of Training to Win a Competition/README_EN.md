---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2383.Minimum%20Hours%20of%20Training%20to%20Win%20a%20Competition/README_EN.md
rating: 1413
source: Weekly Contest 307 Q1
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [2383. Minimum Hours of Training to Win a Competition](https://leetcode.com/problems/minimum-hours-of-training-to-win-a-competition)

[中文文档](/solution/2300-2399/2383.Minimum%20Hours%20of%20Training%20to%20Win%20a%20Competition/README.md)

## Description

<!-- description:start -->

<p>You are entering a competition, and are given two <strong>positive</strong> integers <code>initialEnergy</code> and <code>initialExperience</code> denoting your initial energy and initial experience respectively.</p>

<p>You are also given two <strong>0-indexed</strong> integer arrays <code>energy</code> and <code>experience</code>, both of length <code>n</code>.</p>

<p>You will face <code>n</code> opponents <strong>in order</strong>. The energy and experience of the <code>i<sup>th</sup></code> opponent is denoted by <code>energy[i]</code> and <code>experience[i]</code> respectively. When you face an opponent, you need to have both <strong>strictly</strong> greater experience and energy to defeat them and move to the next opponent if available.</p>

<p>Defeating the <code>i<sup>th</sup></code> opponent <strong>increases</strong> your experience by <code>experience[i]</code>, but <strong>decreases</strong> your energy by <code>energy[i]</code>.</p>

<p>Before starting the competition, you can train for some number of hours. After each hour of training, you can <strong>either</strong> choose to increase your initial experience by one, or increase your initial energy by one.</p>

<p>Return <em>the <strong>minimum</strong> number of training hours required to defeat all </em><code>n</code><em> opponents</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> initialEnergy = 5, initialExperience = 3, energy = [1,4,3,2], experience = [2,6,3,1]
<strong>Output:</strong> 8
<strong>Explanation:</strong> You can increase your energy to 11 after 6 hours of training, and your experience to 5 after 2 hours of training.
You face the opponents in the following order:
- You have more energy and experience than the 0<sup>th</sup> opponent so you win.
  Your energy becomes 11 - 1 = 10, and your experience becomes 5 + 2 = 7.
- You have more energy and experience than the 1<sup>st</sup> opponent so you win.
  Your energy becomes 10 - 4 = 6, and your experience becomes 7 + 6 = 13.
- You have more energy and experience than the 2<sup>nd</sup> opponent so you win.
  Your energy becomes 6 - 3 = 3, and your experience becomes 13 + 3 = 16.
- You have more energy and experience than the 3<sup>rd</sup> opponent so you win.
  Your energy becomes 3 - 2 = 1, and your experience becomes 16 + 1 = 17.
You did a total of 6 + 2 = 8 hours of training before the competition, so we return 8.
It can be proven that no smaller answer exists.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> initialEnergy = 2, initialExperience = 4, energy = [1], experience = [3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> You do not need any additional energy or experience to win the competition, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == energy.length == experience.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= initialEnergy, initialExperience, energy[i], experience[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Simulation

Let's denote the current energy as $x$ and the current experience as $y$.

Next, we traverse each opponent. For the $i$-th opponent, let their energy be $dx$ and their experience be $dy$.

-   If $x \leq dx$, then we need to train for $dx + 1 - x$ hours to increase our energy to $dx + 1$.
-   If $y \leq dy$, then we need to train for $dy + 1 - y$ hours to increase our experience to $dy + 1$.
-   Then, we subtract $dx$ from our energy and add $dy$ to our experience.

Finally, return the answer.

The time complexity is $O(n)$, where $n$ is the number of opponents. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minNumberOfHours(
        self, x: int, y: int, energy: List[int], experience: List[int]
    ) -> int:
        ans = 0
        for dx, dy in zip(energy, experience):
            if x <= dx:
                ans += dx + 1 - x
                x = dx + 1
            if y <= dy:
                ans += dy + 1 - y
                y = dy + 1
            x -= dx
            y += dy
        return ans
```

#### Java

```java
class Solution {
    public int minNumberOfHours(int x, int y, int[] energy, int[] experience) {
        int ans = 0;
        for (int i = 0; i < energy.length; ++i) {
            int dx = energy[i], dy = experience[i];
            if (x <= dx) {
                ans += dx + 1 - x;
                x = dx + 1;
            }
            if (y <= dy) {
                ans += dy + 1 - y;
                y = dy + 1;
            }
            x -= dx;
            y += dy;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minNumberOfHours(int x, int y, vector<int>& energy, vector<int>& experience) {
        int ans = 0;
        for (int i = 0; i < energy.size(); ++i) {
            int dx = energy[i], dy = experience[i];
            if (x <= dx) {
                ans += dx + 1 - x;
                x = dx + 1;
            }
            if (y <= dy) {
                ans += dy + 1 - y;
                y = dy + 1;
            }
            x -= dx;
            y += dy;
        }
        return ans;
    }
};
```

#### Go

```go
func minNumberOfHours(x int, y int, energy []int, experience []int) (ans int) {
	for i, dx := range energy {
		dy := experience[i]
		if x <= dx {
			ans += dx + 1 - x
			x = dx + 1
		}
		if y <= dy {
			ans += dy + 1 - y
			y = dy + 1
		}
		x -= dx
		y += dy
	}
	return
}
```

#### TypeScript

```ts
function minNumberOfHours(x: number, y: number, energy: number[], experience: number[]): number {
    let ans = 0;
    for (let i = 0; i < energy.length; ++i) {
        const [dx, dy] = [energy[i], experience[i]];
        if (x <= dx) {
            ans += dx + 1 - x;
            x = dx + 1;
        }
        if (y <= dy) {
            ans += dy + 1 - y;
            y = dy + 1;
        }
        x -= dx;
        y += dy;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_number_of_hours(
        mut x: i32,
        mut y: i32,
        energy: Vec<i32>,
        experience: Vec<i32>,
    ) -> i32 {
        let mut ans = 0;

        for (&dx, &dy) in energy.iter().zip(experience.iter()) {
            if x <= dx {
                ans += dx + 1 - x;
                x = dx + 1;
            }
            if y <= dy {
                ans += dy + 1 - y;
                y = dy + 1;
            }
            x -= dx;
            y += dy;
        }

        ans
    }
}
```

#### C

```c
int minNumberOfHours(int x, int y, int* energy, int energySize, int* experience, int experienceSize) {
    int ans = 0;
    for (int i = 0; i < energySize; ++i) {
        int dx = energy[i], dy = experience[i];
        if (x <= dx) {
            ans += dx + 1 - x;
            x = dx + 1;
        }
        if (y <= dy) {
            ans += dy + 1 - y;
            y = dy + 1;
        }
        x -= dx;
        y += dy;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
