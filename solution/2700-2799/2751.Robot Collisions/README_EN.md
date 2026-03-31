---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2751.Robot%20Collisions/README_EN.md
rating: 2091
source: Weekly Contest 351 Q4
tags:
    - Stack
    - Array
    - Sorting
    - Simulation
---

<!-- problem:start -->

# [2751. Robot Collisions](https://leetcode.com/problems/robot-collisions)

[中文文档](/solution/2700-2799/2751.Robot%20Collisions/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> <strong>1-indexed</strong> robots, each having a position on a line, health, and movement direction.</p>

<p>You are given <strong>0-indexed</strong> integer arrays <code>positions</code>, <code>healths</code>, and a string <code>directions</code> (<code>directions[i]</code> is either <strong>&#39;L&#39;</strong> for <strong>left</strong> or <strong>&#39;R&#39;</strong> for <strong>right</strong>). All integers in <code>positions</code> are <strong>unique</strong>.</p>

<p>All robots start moving on the line<strong> simultaneously</strong> at the <strong>same speed </strong>in their given directions. If two robots ever share the same position while moving, they will <strong>collide</strong>.</p>

<p>If two robots collide, the robot with <strong>lower health</strong> is <strong>removed</strong> from the line, and the health of the other robot <strong>decreases</strong> <strong>by one</strong>. The surviving robot continues in the <strong>same</strong> direction it was going. If both robots have the <strong>same</strong> health, they are both<strong> </strong>removed from the line.</p>

<p>Your task is to determine the <strong>health</strong> of the robots that survive the collisions, in the same <strong>order </strong>that the robots were given,<strong> </strong>i.e. final health of robot 1 (if survived), final health of robot 2 (if survived), and so on. If there are no survivors, return an empty array.</p>

<p>Return <em>an array containing the health of the remaining robots (in the order they were given in the input), after no further collisions can occur.</em></p>

<p><strong>Note:</strong> The positions may be unsorted.</p>

<div class="notranslate" style="all: initial;">&nbsp;</div>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img height="169" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2751.Robot%20Collisions/images/image-20230516011718-12.png" width="808" /></p>

<pre>
<strong>Input:</strong> positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = &quot;RRRRR&quot;
<strong>Output:</strong> [2,17,9,15,10]
<strong>Explanation:</strong> No collision occurs in this example, since all robots are moving in the same direction. So, the health of the robots in order from the first robot is returned, [2, 17, 9, 15, 10].
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img height="176" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2751.Robot%20Collisions/images/image-20230516004433-7.png" width="717" /></p>

<pre>
<strong>Input:</strong> positions = [3,5,2,6], healths = [10,10,15,12], directions = &quot;RLRL&quot;
<strong>Output:</strong> [14]
<strong>Explanation:</strong> There are 2 collisions in this example. Firstly, robot 1 and robot 2 will collide, and since both have the same health, they will be removed from the line. Next, robot 3 and robot 4 will collide and since robot 4&#39;s health is smaller, it gets removed, and robot 3&#39;s health becomes 15 - 1 = 14. Only robot 3 remains, so we return [14].
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><img height="172" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2751.Robot%20Collisions/images/image-20230516005114-9.png" width="732" /></p>

<pre>
<strong>Input:</strong> positions = [1,2,5,6], healths = [10,10,11,11], directions = &quot;RLRL&quot;
<strong>Output:</strong> []
<strong>Explanation:</strong> Robot 1 and robot 2 will collide and since both have the same health, they are both removed. Robot 3 and 4 will collide and since both have the same health, they are both removed. So, we return an empty array, [].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= positions.length == healths.length == directions.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= positions[i], healths[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>directions[i] == &#39;L&#39;</code> or <code>directions[i] == &#39;R&#39;</code></li>
	<li>All values in <code>positions</code> are distinct</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Approach 1: Stack Simulation

We first sort the robots by position in ascending order, storing the sorted robot indices in an array $\textit{idx}$. We then use a stack to simulate the collision process:

1. Traverse the robot indices $i$ in $\textit{idx}$ from left to right. If $directions[i]$ is moving right, push $i$ onto the stack.
2. If $directions[i]$ is moving left, it collides with the right-moving robot at the top of the stack, until the stack is empty or the current robot is removed.
    - If the top robot's health is greater than the current robot's, the current robot is removed and the top robot's health decreases by 1.
    - If the top robot's health is less than the current robot's, the top robot is removed, the current robot's health decreases by 1, and the current robot continues to collide with the new top robot.
    - If both have equal health, both are removed.

Finally, we return the health values of all robots with health greater than 0.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the number of robots.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def survivedRobotsHealths(self, positions, healths, directions):
        idx = sorted(range(len(positions)), key=lambda i: positions[i])
        stk = []

        for i in idx:
            if directions[i] == "R":
                stk.append(i)
                continue

            while stk and healths[i]:
                j = stk[-1]
                if healths[j] > healths[i]:
                    healths[j] -= 1
                    healths[i] = 0
                elif healths[j] < healths[i]:
                    healths[i] -= 1
                    healths[j] = 0
                    stk.pop()
                else:
                    healths[i] = healths[j] = 0
                    stk.pop()
                    break

        return [h for h in healths if h > 0]
```

#### Java

```java
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (a, b) -> positions[a] - positions[b]);

        Deque<Integer> stk = new ArrayDeque<>();

        for (int i : idx) {
            if (directions.charAt(i) == 'R') {
                stk.push(i);
                continue;
            }

            while (!stk.isEmpty() && healths[i] > 0) {
                int j = stk.peek();

                if (healths[j] > healths[i]) {
                    healths[j]--;
                    healths[i] = 0;
                } else if (healths[j] < healths[i]) {
                    healths[i]--;
                    healths[j] = 0;
                    stk.pop();
                } else {
                    healths[i] = healths[j] = 0;
                    stk.pop();
                    break;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) {
                ans.add(h);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string directions) {
        int n = positions.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);

        sort(idx.begin(), idx.end(), [&](int a, int b) {
            return positions[a] < positions[b];
        });

        vector<int> stk;

        for (int i : idx) {
            if (directions[i] == 'R') {
                stk.push_back(i);
                continue;
            }

            while (!stk.empty() && healths[i] > 0) {
                int j = stk.back();

                if (healths[j] > healths[i]) {
                    healths[j]--;
                    healths[i] = 0;
                } else if (healths[j] < healths[i]) {
                    healths[i]--;
                    healths[j] = 0;
                    stk.pop_back();
                } else {
                    healths[i] = healths[j] = 0;
                    stk.pop_back();
                    break;
                }
            }
        }

        vector<int> ans;
        for (int h : healths) {
            if (h > 0) {
                ans.push_back(h);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func survivedRobotsHealths(positions []int, healths []int, directions string) []int {
	n := len(positions)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}

	sort.Slice(idx, func(i, j int) bool {
		return positions[idx[i]] < positions[idx[j]]
	})

	stk := []int{}

	for _, i := range idx {
		if directions[i] == 'R' {
			stk = append(stk, i)
			continue
		}

		for len(stk) > 0 && healths[i] > 0 {
			j := stk[len(stk)-1]

			if healths[j] > healths[i] {
				healths[j]--
				healths[i] = 0
			} else if healths[j] < healths[i] {
				healths[i]--
				healths[j] = 0
				stk = stk[:len(stk)-1]
			} else {
				healths[i], healths[j] = 0, 0
				stk = stk[:len(stk)-1]
				break
			}
		}
	}

	ans := []int{}
	for _, h := range healths {
		if h > 0 {
			ans = append(ans, h)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function survivedRobotsHealths(
    positions: number[],
    healths: number[],
    directions: string,
): number[] {
    const n = positions.length;
    const idx = Array.from({ length: n }, (_, i) => i).sort((a, b) => positions[a] - positions[b]);

    const stk: number[] = [];

    for (const i of idx) {
        if (directions[i] === 'R') {
            stk.push(i);
            continue;
        }

        while (stk.length && healths[i] > 0) {
            const j = stk[stk.length - 1];

            if (healths[j] > healths[i]) {
                healths[j]--;
                healths[i] = 0;
            } else if (healths[j] < healths[i]) {
                healths[i]--;
                healths[j] = 0;
                stk.pop();
            } else {
                healths[i] = healths[j] = 0;
                stk.pop();
                break;
            }
        }
    }

    return healths.filter(h => h > 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn survived_robots_healths(
        positions: Vec<i32>,
        mut healths: Vec<i32>,
        directions: String
    ) -> Vec<i32> {
        let n = positions.len();
        let mut idx: Vec<usize> = (0..n).collect();

        idx.sort_by_key(|&i| positions[i]);

        let dirs = directions.as_bytes();
        let mut stk: Vec<usize> = Vec::new();

        for &i in &idx {
            if dirs[i] == b'R' {
                stk.push(i);
                continue;
            }

            while let Some(&j) = stk.last() {
                if healths[i] == 0 {
                    break;
                }

                if healths[j] > healths[i] {
                    healths[j] -= 1;
                    healths[i] = 0;
                    break;
                } else if healths[j] < healths[i] {
                    healths[i] -= 1;
                    healths[j] = 0;
                    stk.pop();
                } else {
                    healths[i] = 0;
                    healths[j] = 0;
                    stk.pop();
                    break;
                }
            }
        }

        healths.into_iter().filter(|&h| h > 0).collect()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} positions
 * @param {number[]} healths
 * @param {string} directions
 * @return {number[]}
 */
var survivedRobotsHealths = function (positions, healths, directions) {
    const n = positions.length;
    const idx = Array.from({ length: n }, (_, i) => i).sort((a, b) => positions[a] - positions[b]);

    const stk = [];

    for (const i of idx) {
        if (directions[i] === 'R') {
            stk.push(i);
            continue;
        }

        while (stk.length && healths[i] > 0) {
            const j = stk[stk.length - 1];

            if (healths[j] > healths[i]) {
                healths[j]--;
                healths[i] = 0;
            } else if (healths[j] < healths[i]) {
                healths[i]--;
                healths[j] = 0;
                stk.pop();
            } else {
                healths[i] = healths[j] = 0;
                stk.pop();
                break;
            }
        }
    }

    return healths.filter(h => h > 0);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
