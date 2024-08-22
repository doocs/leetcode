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

<p>Your task is to determine the <strong>health</strong> of the robots that survive the collisions, in the same <strong>order </strong>that the robots were given,<strong> </strong>i.e. final heath of robot 1 (if survived), final health of robot 2 (if survived), and so on. If there are no survivors, return an empty array.</p>

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

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def survivedRobotsHealths(
        self, positions: List[int], healths: List[int], directions: str
    ) -> List[int]:
        n = len(positions)
        indices = list(range(n))
        stack = []

        indices.sort(key=lambda i: positions[i])

        for currentIndex in indices:
            if directions[currentIndex] == "R":
                stack.append(currentIndex)
            else:
                while stack and healths[currentIndex] > 0:
                    topIndex = stack.pop()

                    if healths[topIndex] > healths[currentIndex]:
                        healths[topIndex] -= 1
                        healths[currentIndex] = 0
                        stack.append(topIndex)
                    elif healths[topIndex] < healths[currentIndex]:
                        healths[currentIndex] -= 1
                        healths[topIndex] = 0
                    else:
                        healths[currentIndex] = 0
                        healths[topIndex] = 0

        result = [health for health in healths if health > 0]
        return result

```

#### Java

```java
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (i, j) -> Integer.compare(positions[i], positions[j]));

        Stack<Integer> stack = new Stack<>();

        for (int currentIndex : indices) {
            if (directions.charAt(currentIndex) == 'R') {
                stack.push(currentIndex);
            } else {
                while (!stack.isEmpty() && healths[currentIndex] > 0) {
                    int topIndex = stack.pop();

                    if (healths[topIndex] > healths[currentIndex]) {
                        healths[topIndex] -= 1;
                        healths[currentIndex] = 0;
                        stack.push(topIndex);
                    } else if (healths[topIndex] < healths[currentIndex]) {
                        healths[currentIndex] -= 1;
                        healths[topIndex] = 0;
                    } else {
                        healths[currentIndex] = 0;
                        healths[topIndex] = 0;
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int health : healths) {
            if (health > 0) {
                result.add(health);
            }
        }

        return result;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string directions) {
        int n = positions.size();
        vector<int> indices(n);

        iota(indices.begin(), indices.end(), 0);
        stack<int> st;

        auto lambda = [&](int i, int j) { return positions[i] < positions[j]; };

        sort(begin(indices), end(indices), lambda);

        vector<int> result;
        for (int currentIndex : indices) {
            if (directions[currentIndex] == 'R') {
                st.push(currentIndex);
            } else {
                while (!st.empty() && healths[currentIndex] > 0) {
                    int topIndex = st.top();
                    st.pop();

                    if (healths[topIndex] > healths[currentIndex]) {
                        healths[topIndex] -= 1;
                        healths[currentIndex] = 0;
                        st.push(topIndex);
                    } else if (healths[topIndex] < healths[currentIndex]) {
                        healths[currentIndex] -= 1;
                        healths[topIndex] = 0;
                    } else {
                        healths[currentIndex] = 0;
                        healths[topIndex] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            if (healths[i] > 0) {
                result.push_back(healths[i]);
            }
        }
        return result;
    }
};
```

#### Go

```go
func survivedRobotsHealths(positions []int, healths []int, directions string) []int {
	n := len(positions)
	indices := make([]int, n)
	for i := range indices {
		indices[i] = i
	}

	sort.Slice(indices, func(i, j int) bool {
		return positions[indices[i]] < positions[indices[j]]
	})

	stack := []int{}

	for _, currentIndex := range indices {
		if directions[currentIndex] == 'R' {
			stack = append(stack, currentIndex)
		} else {
			for len(stack) > 0 && healths[currentIndex] > 0 {
				topIndex := stack[len(stack)-1]
				stack = stack[:len(stack)-1]

				if healths[topIndex] > healths[currentIndex] {
					healths[topIndex] -= 1
					healths[currentIndex] = 0
					stack = append(stack, topIndex)
				} else if healths[topIndex] < healths[currentIndex] {
					healths[currentIndex] -= 1
					healths[topIndex] = 0
				} else {
					healths[currentIndex] = 0
					healths[topIndex] = 0
				}
			}
		}
	}

	result := []int{}
	for _, health := range healths {
		if health > 0 {
			result = append(result, health)
		}
	}

	return result
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
