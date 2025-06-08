---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1298.Maximum%20Candies%20You%20Can%20Get%20from%20Boxes/README.md
rating: 1824
source: 第 168 场周赛 Q4
tags:
    - 广度优先搜索
    - 图
    - 数组
---

<!-- problem:start -->

# [1298. 你能从盒子里获得的最大糖果数](https://leetcode.cn/problems/maximum-candies-you-can-get-from-boxes)

[English Version](/solution/1200-1299/1298.Maximum%20Candies%20You%20Can%20Get%20from%20Boxes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你&nbsp;<code>n</code>&nbsp;个盒子，每个盒子的格式为&nbsp;<code>[status, candies, keys, containedBoxes]</code>&nbsp;，其中：</p>

<ul>
	<li>状态字&nbsp;<code>status[i]</code>：整数，如果&nbsp;<code>box[i]</code>&nbsp;是开的，那么是 <strong>1&nbsp;</strong>，否则是 <strong>0&nbsp;</strong>。</li>
	<li>糖果数&nbsp;<code>candies[i]</code>: 整数，表示&nbsp;<code>box[i]</code> 中糖果的数目。</li>
	<li>钥匙&nbsp;<code>keys[i]</code>：数组，表示你打开&nbsp;<code>box[i]</code>&nbsp;后，可以得到一些盒子的钥匙，每个元素分别为该钥匙对应盒子的下标。</li>
	<li>内含的盒子&nbsp;<code>containedBoxes[i]</code>：整数，表示放在&nbsp;<code>box[i]</code>&nbsp;里的盒子所对应的下标。</li>
</ul>

<p>给你一个整数数组&nbsp;<code>initialBoxes</code>，包含你最初拥有的盒子。你可以拿走每个&nbsp;<strong>已打开盒子&nbsp;</strong>里的所有糖果，并且可以使用其中的钥匙去开启新的盒子，并且可以使用在其中发现的其他盒子。</p>

<p>请你按照上述规则，返回可以获得糖果的 <strong>最大数目&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
<strong>输出：</strong>16
<strong>解释：
</strong>一开始你有盒子 0 。你将获得它里面的 7 个糖果和盒子 1 和 2。
盒子 1 目前状态是关闭的，而且你还没有对应它的钥匙。所以你将会打开盒子 2 ，并得到里面的 4 个糖果和盒子 1 的钥匙。
在盒子 1 中，你会获得 5 个糖果和盒子 3 ，但是你没法获得盒子 3 的钥匙所以盒子 3 会保持关闭状态。
你总共可以获得的糖果数目 = 7 + 4 + 5 = 16 个。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
<strong>输出：</strong>6
<strong>解释：
</strong>你一开始拥有盒子 0 。打开它你可以找到盒子 1,2,3,4,5 和它们对应的钥匙。
打开这些盒子，你将获得所有盒子的糖果，所以总糖果数为 6 个。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>status = [1,1,1], candies = [100,1,100], keys = [[],[0,2],[]], containedBoxes = [[],[],[]], initialBoxes = [1]
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>status = [1], candies = [100], keys = [[]], containedBoxes = [[]], initialBoxes = []
<strong>输出：</strong>0
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>status = [1,1,1], candies = [2,3,2], keys = [[],[],[]], containedBoxes = [[],[],[]], initialBoxes = [2,1,0]
<strong>输出：</strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= status.length &lt;= 1000</code></li>
	<li><code>status.length == candies.length == keys.length == containedBoxes.length == n</code></li>
	<li><code>status[i]</code> 要么是&nbsp;<code>0</code>&nbsp;要么是&nbsp;<code>1</code> 。</li>
	<li><code>1 &lt;= candies[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= keys[i].length &lt;= status.length</code></li>
	<li><code>0 &lt;= keys[i][j] &lt; status.length</code></li>
	<li><code>keys[i]</code>&nbsp;中的值都是互不相同的。</li>
	<li><code>0 &lt;= containedBoxes[i].length &lt;= status.length</code></li>
	<li><code>0 &lt;= containedBoxes[i][j] &lt; status.length</code></li>
	<li><code>containedBoxes[i]</code>&nbsp;中的值都是互不相同的。</li>
	<li>每个盒子最多被一个盒子包含。</li>
	<li><code>0 &lt;= initialBoxes.length&nbsp;&lt;= status.length</code></li>
	<li><code>0 &lt;= initialBoxes[i] &lt; status.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS + 哈希集合

题目给定一批盒子，每个盒子可能有状态（开/关）、糖果、钥匙、以及其他盒子。我们的目标是通过初始给定的一些盒子，尽可能多地打开更多盒子，并收集其中的糖果。可以通过获得钥匙来解锁新盒子，通过盒子中嵌套的盒子来获取更多资源。

我们采用 BFS 的方式模拟整个探索过程。

我们用一个队列 $q$ 表示当前可以访问的、**已经开启** 的盒子；用两个集合 $\textit{has}$ 和 $\textit{took}$ 分别记录**我们拥有的所有盒子**和**已经处理过的盒子**，防止重复。

初始时，将所有 $\textit{initialBoxes}$ 添加到 $\textit{has}$ 中，如果初始盒子状态为开启，立即加入队列 $\textit{q}$ 并累计糖果；

然后进行 BFS，依次从 $\textit{q}$ 中取出盒子：

-   获取盒子中的钥匙 $\textit{keys[box]}$，将能解锁的盒子加入队列；
-   收集盒子中包含的其他盒子 $\textit{containedBoxes[box]}$，如果状态是开启的且未处理过，则立即处理；

每个盒子最多处理一次，糖果累计一次，最终返回总糖果数 $\textit{ans}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是盒子的总数。

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
