---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2410.Maximum%20Matching%20of%20Players%20With%20Trainers/README_EN.md
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

According to the problem description, each athlete should be matched with the trainer whose ability value is as close as possible. Therefore, we can sort the ability values of both athletes and trainers, and then use the two-pointer method for matching.

We use two pointers $i$ and $j$ to point to the arrays of athletes and trainers, respectively, both initially pointing to the start of the arrays. Then we traverse the ability values of the athletes one by one. If the current trainer's ability value is less than the current athlete's ability value, we move the trainer's pointer to the right by one position until we find a trainer whose ability value is greater than or equal to the current athlete's. If no such trainer is found, it means the current athlete cannot be matched with any trainer, and we return the current athlete's index. Otherwise, we can match the current athlete with the trainer, and then move both pointers to the right by one position. Continue this process until all athletes have been traversed.

If we traverse all athletes, it means all athletes can be matched with trainers, and we return the number of athletes.

The time complexity is $O(m \times \log m + n \times \log n)$, and the space complexity is $O(\max(\log m, \log n))$. Here, $m$ and $n$ are the numbers of athletes and trainers, respectively.

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

<!-- problem:end -->
