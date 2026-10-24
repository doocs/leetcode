---
comments: true
difficulty: Medium
rating: 1381
source: Biweekly Contest 87 Q2
tags:
    - Greedy
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [2410. Maximum Matching of Players With Trainers](https://leetcode.com/problems/maximum-matching-of-players-with-trainers)

[中文文档](/solution/2400-2499/2410.Maximum%20Matching%20of%20Players%20With%20Trainers/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>players</code>, where <code>players[i]</code> represents the <strong>ability</strong> of the <code>i<sup>th</sup></code> player. You are also given a <strong>0-indexed</strong> integer array <code>trainers</code>, where <code>trainers[j]</code> represents the <strong>training capacity </strong>of the <code>j<sup>th</sup></code> trainer.</p>

<p>The <code>i<sup>th</sup></code> player can <strong>match</strong> with the <code>j<sup>th</sup></code> trainer if the player&#39;s ability is <strong>less than or equal to</strong> the trainer&#39;s training capacity. Additionally, the <code>i<sup>th</sup></code> player can be matched with at most one trainer, and the <code>j<sup>th</sup></code> trainer can be matched with at most one player.</p>

<p>Return <em>the <strong>maximum</strong> number of matchings between </em><code>players</code><em> and </em><code>trainers</code><em> that satisfy these conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> players = [4,7,9], trainers = [8,2,5,8]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
One of the ways we can form two matchings is as follows:
- players[0] can be matched with trainers[0] since 4 &lt;= 8.
- players[1] can be matched with trainers[3] since 7 &lt;= 8.
It can be proven that 2 is the maximum number of matchings that can be formed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> players = [1,1,1], trainers = [10]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The trainer can be matched with any of the 3 players.
Each player can only be matched with one trainer, so the maximum answer is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= players.length, trainers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= players[i], trainers[j] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as <a href="https://leetcode.com/problems/assign-cookies/description/" target="_blank"> 445: Assign Cookies.</a></p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Two Pointers

<!-- thinking:start -->

> **Thinking**
>
> Trying every pair is too slow when $n,m \le 10^5$. A trainer who can match a weaker player can also match a stronger one, so the weakest remaining player should take the weakest trainer who is still good enough.
>
> Sort both arrays and handle players from weakest to strongest. The trainer pointer only moves forward: skip trainers who are too weak, then take the first one who can match. Sorting determines the running time.

<!-- thinking:end -->

Sort players and trainers by ability in non-decreasing order. Pointers $i$ and $j$ mark the player and the trainer under consideration.

Start from the weakest player. While the trainer's capacity is below the player's ability, move $j$ right until a trainer is strong enough or none remain. If the trainers run out, every later player is at least as strong, so matching stops and the answer is $i$, the number already matched. Otherwise this pair matches and both pointers move forward by one. If every player is matched, return the number of players.

The time complexity is $O(m \times \log m + n \times \log n)$, and the space complexity is $O(\max(\log m, \log n))$, where $m$ and $n$ are the numbers of players and trainers, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort()
        trainers.sort()
        j, n = 0, len(trainers)
        for i, p in enumerate(players):
            while j < n and trainers[j] < p:
                j += 1
            if j == n:
                return i
            j += 1
        return len(players)
```

#### Java

```java
class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int m = players.length, n = trainers.length;
        for (int i = 0, j = 0; i < m; ++i, ++j) {
            while (j < n && trainers[j] < players[i]) {
                ++j;
            }
            if (j == n) {
                return i;
            }
        }
        return m;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        ranges::sort(players);
        ranges::sort(trainers);
        int m = players.size(), n = trainers.size();
        for (int i = 0, j = 0; i < m; ++i, ++j) {
            while (j < n && trainers[j] < players[i]) {
                ++j;
            }
            if (j == n) {
                return i;
            }
        }
        return m;
    }
};
```

#### Go

```go
func matchPlayersAndTrainers(players []int, trainers []int) int {
	sort.Ints(players)
	sort.Ints(trainers)
	m, n := len(players), len(trainers)
	for i, j := 0, 0; i < m; i, j = i+1, j+1 {
		for j < n && trainers[j] < players[i] {
			j++
		}
		if j == n {
			return i
		}
	}
	return m
}
```

#### TypeScript

```ts
function matchPlayersAndTrainers(players: number[], trainers: number[]): number {
    players.sort((a, b) => a - b);
    trainers.sort((a, b) => a - b);
    const [m, n] = [players.length, trainers.length];
    for (let i = 0, j = 0; i < m; ++i, ++j) {
        while (j < n && trainers[j] < players[i]) {
            ++j;
        }
        if (j === n) {
            return i;
        }
    }
    return m;
}
```

#### Rust

```rust
impl Solution {
    pub fn match_players_and_trainers(mut players: Vec<i32>, mut trainers: Vec<i32>) -> i32 {
        players.sort();
        trainers.sort();
        let mut j = 0;
        let n = trainers.len();
        for (i, &p) in players.iter().enumerate() {
            while j < n && trainers[j] < p {
                j += 1;
            }
            if j == n {
                return i as i32;
            }
            j += 1;
        }
        players.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Greedy + Two Pointers (Iterate over Trainers)

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 still walks past trainers who are too weak for the current player, and returns early once no trainer remains. Moving the outer loop onto the trainers removes that inner scan and the early return.
>
> Sort both arrays and go through the trainers from weakest to strongest, keeping a pointer on the weakest unmatched player. A trainer too weak for that player is too weak for everyone left, and is skipped; a trainer strong enough produces one match and the pointer advances. The pointer's final value is the answer, and the complexity is unchanged.

<!-- thinking:end -->

Sort players and trainers by ability in non-decreasing order. Pointer $j$ marks the weakest unmatched player and starts at $0$.

Go through the trainers from weakest to strongest. If some player is still unmatched and that player's ability does not exceed the trainer's capacity, count one match and increment $j$. Otherwise this trainer cannot match any remaining player and is skipped. After the scan, $j$ is the maximum number of matches.

The time complexity is $O(m \times \log m + n \times \log n)$, and the space complexity is $O(\max(\log m, \log n))$, where $m$ and $n$ are the numbers of players and trainers, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort()
        trainers.sort()
        j = 0
        for t in trainers:
            if j < len(players) and players[j] <= t:
                j += 1
        return j
```

#### Java

```java
class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int j = 0, m = players.length;
        for (int t : trainers) {
            if (j < m && players[j] <= t) {
                ++j;
            }
        }
        return j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int matchPlayersAndTrainers(vector<int>& players, vector<int>& trainers) {
        ranges::sort(players);
        ranges::sort(trainers);
        int j = 0, m = players.size();
        for (int t : trainers) {
            if (j < m && players[j] <= t) {
                ++j;
            }
        }
        return j;
    }
};
```

#### Go

```go
func matchPlayersAndTrainers(players []int, trainers []int) int {
	sort.Ints(players)
	sort.Ints(trainers)
	j, m := 0, len(players)
	for _, t := range trainers {
		if j < m && players[j] <= t {
			j++
		}
	}
	return j
}
```

#### TypeScript

```ts
function matchPlayersAndTrainers(players: number[], trainers: number[]): number {
    players.sort((a, b) => a - b);
    trainers.sort((a, b) => a - b);
    let j = 0;
    const m = players.length;
    for (const t of trainers) {
        if (j < m && players[j] <= t) {
            ++j;
        }
    }
    return j;
}
```

#### Rust

```rust
impl Solution {
    pub fn match_players_and_trainers(mut players: Vec<i32>, mut trainers: Vec<i32>) -> i32 {
        players.sort();
        trainers.sort();
        let mut j = 0;
        for &t in &trainers {
            if j < players.len() && players[j] <= t {
                j += 1;
            }
        }
        j as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
