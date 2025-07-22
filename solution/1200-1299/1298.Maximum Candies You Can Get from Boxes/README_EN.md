---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1298.Maximum%20Candies%20You%20Can%20Get%20from%20Boxes/README_EN.md
rating: 1824
source: Weekly Contest 168 Q4
tags:
    - Breadth-First Search
    - Graph
    - Array
---

<!-- problem:start -->

# [1298. Maximum Candies You Can Get from Boxes](https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes)

[中文文档](/solution/1200-1299/1298.Maximum%20Candies%20You%20Can%20Get%20from%20Boxes/README.md)

## Description

<!-- description:start -->

<p>You have <code>n</code> boxes labeled from <code>0</code> to <code>n - 1</code>. You are given four arrays: <code>status</code>, <code>candies</code>, <code>keys</code>, and <code>containedBoxes</code> where:</p>

<ul>
	<li><code>status[i]</code> is <code>1</code> if the <code>i<sup>th</sup></code> box is open and <code>0</code> if the <code>i<sup>th</sup></code> box is closed,</li>
	<li><code>candies[i]</code> is the number of candies in the <code>i<sup>th</sup></code> box,</li>
	<li><code>keys[i]</code> is a list of the labels of the boxes you can open after opening the <code>i<sup>th</sup></code> box.</li>
	<li><code>containedBoxes[i]</code> is a list of the boxes you found inside the <code>i<sup>th</sup></code> box.</li>
</ul>

<p>You are given an integer array <code>initialBoxes</code> that contains the labels of the boxes you initially have. You can take all the candies in <strong>any open box</strong> and you can use the keys in it to open new boxes and you also can use the boxes you find in it.</p>

<p>Return <em>the maximum number of candies you can get following the rules above</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
<strong>Output:</strong> 16
<strong>Explanation:</strong> You will be initially given box 0. You will find 7 candies in it and boxes 1 and 2.
Box 1 is closed and you do not have a key for it so you will open box 2. You will find 4 candies and a key to box 1 in box 2.
In box 1, you will find 5 candies and box 3 but you will not find a key to box 3 so box 3 will remain closed.
Total number of candies collected = 7 + 4 + 5 = 16 candy.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
<strong>Output:</strong> 6
<strong>Explanation:</strong> You have initially box 0. Opening it you can find boxes 1,2,3,4 and 5 and their keys.
The total number of candies will be 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == status.length == candies.length == keys.length == containedBoxes.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>status[i]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>1 &lt;= candies[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= keys[i].length &lt;= n</code></li>
	<li><code>0 &lt;= keys[i][j] &lt; n</code></li>
	<li>All values of <code>keys[i]</code> are <strong>unique</strong>.</li>
	<li><code>0 &lt;= containedBoxes[i].length &lt;= n</code></li>
	<li><code>0 &lt;= containedBoxes[i][j] &lt; n</code></li>
	<li>All values of <code>containedBoxes[i]</code> are unique.</li>
	<li>Each box is contained in one box at most.</li>
	<li><code>0 &lt;= initialBoxes.length &lt;= n</code></li>
	<li><code>0 &lt;= initialBoxes[i] &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS + Hash Set

The problem gives a set of boxes, each of which may have a state (open/closed), candies, keys, and other boxes inside. Our goal is to use the initially given boxes to open as many more boxes as possible and collect the candies inside. We can unlock new boxes by obtaining keys, and get more resources through boxes nested inside other boxes.

We use BFS to simulate the entire exploration process.

We use a queue $q$ to represent the currently accessible and **already opened** boxes; two sets, $\textit{has}$ and $\textit{took}$, are used to record **all boxes we own** and **boxes we have already processed**, to avoid duplicates.

Initially, add all $\textit{initialBoxes}$ to $\textit{has}$. If an initial box is open, immediately add it to the queue $\textit{q}$ and accumulate its candies.

Then perform BFS, taking boxes out of $\textit{q}$ one by one:

-   Obtain the keys in the box $\textit{keys[box]}$ and add any boxes that can be unlocked to the queue;
-   Collect other boxes contained in the box $\textit{containedBoxes[box]}$. If a contained box is open and has not been processed, process it immediately;

Each box is processed at most once, and candies are accumulated once. Finally, return the total number of candies $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the total number of boxes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCandies(
        self,
        status: List[int],
        candies: List[int],
        keys: List[List[int]],
        containedBoxes: List[List[int]],
        initialBoxes: List[int],
    ) -> int:
        q = deque()
        has, took = set(initialBoxes), set()
        ans = 0

        for box in initialBoxes:
            if status[box]:
                q.append(box)
                took.add(box)
                ans += candies[box]
        while q:
            box = q.popleft()
            for k in keys[box]:
                if not status[k]:
                    status[k] = 1
                    if k in has and k not in took:
                        q.append(k)
                        took.add(k)
                        ans += candies[k]

            for b in containedBoxes[box]:
                has.add(b)
                if status[b] and b not in took:
                    q.append(b)
                    took.add(b)
                    ans += candies[b]
        return ans
```

#### Java

```java
class Solution {
    public int maxCandies(
        int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> has = new HashSet<>();
        Set<Integer> took = new HashSet<>();
        int ans = 0;
        for (int box : initialBoxes) {
            has.add(box);
            if (status[box] == 1) {
                q.offer(box);
                took.add(box);
                ans += candies[box];
            }
        }
        while (!q.isEmpty()) {
            int box = q.poll();
            for (int k : keys[box]) {
                if (status[k] == 0) {
                    status[k] = 1;
                    if (has.contains(k) && !took.contains(k)) {
                        q.offer(k);
                        took.add(k);
                        ans += candies[k];
                    }
                }
            }
            for (int b : containedBoxes[box]) {
                has.add(b);
                if (status[b] == 1 && !took.contains(b)) {
                    q.offer(b);
                    took.add(b);
                    ans += candies[b];
                }
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
    int maxCandies(
        vector<int>& status,
        vector<int>& candies,
        vector<vector<int>>& keys,
        vector<vector<int>>& containedBoxes,
        vector<int>& initialBoxes) {
        queue<int> q;
        unordered_set<int> has, took;
        int ans = 0;

        for (int box : initialBoxes) {
            has.insert(box);
            if (status[box]) {
                q.push(box);
                took.insert(box);
                ans += candies[box];
            }
        }

        while (!q.empty()) {
            int box = q.front();
            q.pop();

            for (int k : keys[box]) {
                if (!status[k]) {
                    status[k] = 1;
                    if (has.count(k) && !took.count(k)) {
                        q.push(k);
                        took.insert(k);
                        ans += candies[k];
                    }
                }
            }

            for (int b : containedBoxes[box]) {
                has.insert(b);
                if (status[b] && !took.count(b)) {
                    q.push(b);
                    took.insert(b);
                    ans += candies[b];
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maxCandies(status []int, candies []int, keys [][]int, containedBoxes [][]int, initialBoxes []int) (ans int) {
	q := []int{}
	has := make(map[int]bool)
	took := make(map[int]bool)
	for _, box := range initialBoxes {
		has[box] = true
		if status[box] == 1 {
			q = append(q, box)
			took[box] = true
			ans += candies[box]
		}
	}
	for len(q) > 0 {
		box := q[0]
		q = q[1:]
		for _, k := range keys[box] {
			if status[k] == 0 {
				status[k] = 1
				if has[k] && !took[k] {
					q = append(q, k)
					took[k] = true
					ans += candies[k]
				}
			}
		}
		for _, b := range containedBoxes[box] {
			has[b] = true
			if status[b] == 1 && !took[b] {
				q = append(q, b)
				took[b] = true
				ans += candies[b]
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function maxCandies(
    status: number[],
    candies: number[],
    keys: number[][],
    containedBoxes: number[][],
    initialBoxes: number[],
): number {
    const q: number[] = [];
    const has: Set<number> = new Set();
    const took: Set<number> = new Set();
    let ans = 0;

    for (const box of initialBoxes) {
        has.add(box);
        if (status[box] === 1) {
            q.push(box);
            took.add(box);
            ans += candies[box];
        }
    }

    while (q.length > 0) {
        const box = q.pop()!;

        for (const k of keys[box]) {
            if (status[k] === 0) {
                status[k] = 1;
                if (has.has(k) && !took.has(k)) {
                    q.push(k);
                    took.add(k);
                    ans += candies[k];
                }
            }
        }

        for (const b of containedBoxes[box]) {
            has.add(b);
            if (status[b] === 1 && !took.has(b)) {
                q.push(b);
                took.add(b);
                ans += candies[b];
            }
        }
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn max_candies(
        mut status: Vec<i32>,
        candies: Vec<i32>,
        keys: Vec<Vec<i32>>,
        contained_boxes: Vec<Vec<i32>>,
        initial_boxes: Vec<i32>,
    ) -> i32 {
        let mut q: VecDeque<i32> = VecDeque::new();
        let mut has: HashSet<i32> = HashSet::new();
        let mut took: HashSet<i32> = HashSet::new();
        let mut ans = 0;

        for &box_ in &initial_boxes {
            has.insert(box_);
            if status[box_ as usize] == 1 {
                q.push_back(box_);
                took.insert(box_);
                ans += candies[box_ as usize];
            }
        }

        while let Some(box_) = q.pop_front() {
            for &k in &keys[box_ as usize] {
                if status[k as usize] == 0 {
                    status[k as usize] = 1;
                    if has.contains(&k) && !took.contains(&k) {
                        q.push_back(k);
                        took.insert(k);
                        ans += candies[k as usize];
                    }
                }
            }

            for &b in &contained_boxes[box_ as usize] {
                has.insert(b);
                if status[b as usize] == 1 && !took.contains(&b) {
                    q.push_back(b);
                    took.insert(b);
                    ans += candies[b as usize];
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
