---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3175.Find%20The%20First%20Player%20to%20win%20K%20Games%20in%20a%20Row/README_EN.md
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [3175. Find The First Player to win K Games in a Row](https://leetcode.com/problems/find-the-first-player-to-win-k-games-in-a-row)

[中文文档](/solution/3100-3199/3175.Find%20The%20First%20Player%20to%20win%20K%20Games%20in%20a%20Row/README.md)

## Description

<!-- description:start -->

<p>A competition consists of <code>n</code> players numbered from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given an integer array <code>skills</code> of size <code>n</code> and a <strong>positive</strong> integer <code>k</code>, where <code>skills[i]</code> is the skill level of player <code>i</code>. All integers in <code>skills</code> are <strong>unique</strong>.</p>

<p>All players are standing in a queue in order from player <code>0</code> to player <code>n - 1</code>.</p>

<p>The competition process is as follows:</p>

<ul>
	<li>The first two players in the queue play a game, and the player with the <strong>higher</strong> skill level wins.</li>
	<li>After the game, the winner stays at the beginning of the queue, and the loser goes to the end of it.</li>
</ul>

<p>The winner of the competition is the <strong>first</strong> player who wins <code>k</code> games <strong>in a row</strong>.</p>

<p>Return the initial index of the <em>winning</em> player.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skills = [4,2,6,3,9], k = 2</span></p>

<p><strong>Output:</strong> 2</p>

<p><strong>Explanation:</strong></p>

<p>Initially, the queue of players is <code>[0,1,2,3,4]</code>. The following process happens:</p>

<ul>
	<li>Players 0 and 1 play a game, since the skill of player 0 is higher than that of player 1, player 0 wins. The resulting queue is <code>[0,2,3,4,1]</code>.</li>
	<li>Players 0 and 2 play a game, since the skill of player 2 is higher than that of player 0, player 2 wins. The resulting queue is <code>[2,3,4,1,0]</code>.</li>
	<li>Players 2 and 3 play a game, since the skill of player 2 is higher than that of player 3, player 2 wins. The resulting queue is <code>[2,4,1,0,3]</code>.</li>
</ul>

<p>Player 2 won <code>k = 2</code> games in a row, so the winner is player 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skills = [2,5,4], k = 3</span></p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<p>Initially, the queue of players is <code>[0,1,2]</code>. The following process happens:</p>

<ul>
	<li>Players 0 and 1 play a game, since the skill of player 1 is higher than that of player 0, player 1 wins. The resulting queue is <code>[1,2,0]</code>.</li>
	<li>Players 1 and 2 play a game, since the skill of player 1 is higher than that of player 2, player 1 wins. The resulting queue is <code>[1,0,2]</code>.</li>
	<li>Players 1 and 0 play a game, since the skill of player 1 is higher than that of player 0, player 1 wins. The resulting queue is <code>[1,2,0]</code>.</li>
</ul>

<p>Player 1 won <code>k = 3</code> games in a row, so the winner is player 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == skills.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= skills[i] &lt;= 10<sup>6</sup></code></li>
	<li>All integers in <code>skills</code> are unique.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Quick Thinking

We notice that each time the first two elements of the array are compared, regardless of the result, the next comparison will always be between the next element in the array and the current winner. Therefore, if we have looped $n-1$ times, the final winner must be the maximum element in the array. Otherwise, if an element has won consecutively $k$ times, then this element is the final winner.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

Similar problems:

-   [1535. Find the Winner of an Array Game](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1535.Find%20the%20Winner%20of%20an%20Array%20Game/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findWinningPlayer(self, skills: List[int], k: int) -> int:
        n = len(skills)
        k = min(k, n - 1)
        i = cnt = 0
        for j in range(1, n):
            if skills[i] < skills[j]:
                i = j
                cnt = 1
            else:
                cnt += 1
            if cnt == k:
                break
        return i
```

#### Java

```java
class Solution {
    public int findWinningPlayer(int[] skills, int k) {
        int n = skills.length;
        k = Math.min(k, n - 1);
        int i = 0, cnt = 0;
        for (int j = 1; j < n; ++j) {
            if (skills[i] < skills[j]) {
                i = j;
                cnt = 1;
            } else {
                ++cnt;
            }
            if (cnt == k) {
                break;
            }
        }
        return i;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findWinningPlayer(vector<int>& skills, int k) {
        int n = skills.size();
        k = min(k, n - 1);
        int i = 0, cnt = 0;
        for (int j = 1; j < n; ++j) {
            if (skills[i] < skills[j]) {
                i = j;
                cnt = 1;
            } else {
                ++cnt;
            }
            if (cnt == k) {
                break;
            }
        }
        return i;
    }
};
```

#### Go

```go
func findWinningPlayer(skills []int, k int) int {
	n := len(skills)
	k = min(k, n-1)
	i, cnt := 0, 0
	for j := 1; j < n; j++ {
		if skills[i] < skills[j] {
			i = j
			cnt = 1
		} else {
			cnt++
		}
		if cnt == k {
			break
		}
	}
	return i
}
```

#### TypeScript

```ts
function findWinningPlayer(skills: number[], k: number): number {
    const n = skills.length;
    k = Math.min(k, n - 1);
    let [i, cnt] = [0, 0];
    for (let j = 1; j < n; ++j) {
        if (skills[i] < skills[j]) {
            i = j;
            cnt = 1;
        } else {
            ++cnt;
        }
        if (cnt === k) {
            break;
        }
    }
    return i;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
