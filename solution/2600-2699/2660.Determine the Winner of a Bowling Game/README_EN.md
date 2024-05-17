---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2660.Determine%20the%20Winner%20of%20a%20Bowling%20Game/README_EN.md
rating: 1324
source: Weekly Contest 343 Q1
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [2660. Determine the Winner of a Bowling Game](https://leetcode.com/problems/determine-the-winner-of-a-bowling-game)

[中文文档](/solution/2600-2699/2660.Determine%20the%20Winner%20of%20a%20Bowling%20Game/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>0-indexed</strong> integer arrays <code><font face="monospace">player1</font></code> and <code>player2</code>, that represent the number of pins that player 1 and player 2 hit in a bowling game, respectively.</p>

<p>The bowling game consists of <code>n</code> turns, and the number of pins in each turn is exactly <code>10</code>.</p>

<p>Assume a player hit <code>x<sub>i</sub></code> pins in the <code>i<sup>th</sup></code> turn. The value of the <code>i<sup>th</sup></code> turn for the player is:</p>

<ul>
	<li><code>2x<sub>i</sub></code> if the player hit <code>10</code> pins in any of the previous two turns.</li>
	<li>Otherwise, It is <code>x<sub>i</sub></code>.</li>
</ul>

<p>The score of the player is the sum of the values of their <code>n</code> turns.</p>

<p>Return</p>

<ul>
	<li><code>1</code> <em>if the score of player 1 is more than the score of player 2,</em></li>
	<li><code>2</code> <em>if the score of player 2 is more than the score of player 1, and</em></li>
	<li><code>0</code> <em>in case of a draw.</em></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> player1 = [4,10,7,9], player2 = [6,5,2,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The score of player1 is 4 + 10 + 2*7 + 2*9 = 46.
The score of player2 is 6 + 5 + 2 + 3 = 16.
Score of player1 is more than the score of player2, so, player1 is the winner, and the answer is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> player1 = [3,5,7,6], player2 = [8,10,10,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The score of player1 is 3 + 5 + 7 + 6 = 21.
The score of player2 is 8 + 10 + 2*10 + 2*2 = 42.
Score of player2 is more than the score of player1, so, player2 is the winner, and the answer is 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> player1 = [2,3], player2 = [4,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The score of player1 is 2 + 3 = 5
The score of player2 is 4 + 1 = 5
The score of player1 equals to the score of player2, so, there is a draw, and the answer is 0.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == player1.length == player2.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= player1[i], player2[i] &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can define a function $f(arr)$ to calculate the scores of the two players, denoted as $a$ and $b$, respectively, and then return the answer based on the relationship between $a$ and $b$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isWinner(self, player1: List[int], player2: List[int]) -> int:
        def f(arr: List[int]) -> int:
            s = 0
            for i, x in enumerate(arr):
                k = 2 if (i and arr[i - 1] == 10) or (i > 1 and arr[i - 2] == 10) else 1
                s += k * x
            return s

        a, b = f(player1), f(player2)
        return 1 if a > b else (2 if b > a else 0)
```

#### Java

```java
class Solution {
    public int isWinner(int[] player1, int[] player2) {
        int a = f(player1), b = f(player2);
        return a > b ? 1 : b > a ? 2 : 0;
    }

    private int f(int[] arr) {
        int s = 0;
        for (int i = 0; i < arr.length; ++i) {
            int k = (i > 0 && arr[i - 1] == 10) || (i > 1 && arr[i - 2] == 10) ? 2 : 1;
            s += k * arr[i];
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int isWinner(vector<int>& player1, vector<int>& player2) {
        auto f = [](vector<int>& arr) {
            int s = 0;
            for (int i = 0, n = arr.size(); i < n; ++i) {
                int k = (i && arr[i - 1] == 10) || (i > 1 && arr[i - 2] == 10) ? 2 : 1;
                s += k * arr[i];
            }
            return s;
        };
        int a = f(player1), b = f(player2);
        return a > b ? 1 : (b > a ? 2 : 0);
    }
};
```

#### Go

```go
func isWinner(player1 []int, player2 []int) int {
	f := func(arr []int) int {
		s := 0
		for i, x := range arr {
			k := 1
			if (i > 0 && arr[i-1] == 10) || (i > 1 && arr[i-2] == 10) {
				k = 2
			}
			s += k * x
		}
		return s
	}
	a, b := f(player1), f(player2)
	if a > b {
		return 1
	}
	if b > a {
		return 2
	}
	return 0
}
```

#### TypeScript

```ts
function isWinner(player1: number[], player2: number[]): number {
    const f = (arr: number[]): number => {
        let s = 0;
        for (let i = 0; i < arr.length; ++i) {
            s += arr[i];
            if ((i && arr[i - 1] === 10) || (i > 1 && arr[i - 2] === 10)) {
                s += arr[i];
            }
        }
        return s;
    };
    const a = f(player1);
    const b = f(player2);
    return a > b ? 1 : a < b ? 2 : 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_winner(player1: Vec<i32>, player2: Vec<i32>) -> i32 {
        let f = |arr: &Vec<i32>| -> i32 {
            let mut s = 0;
            for i in 0..arr.len() {
                let mut k = 1;
                if (i > 0 && arr[i - 1] == 10) || (i > 1 && arr[i - 2] == 10) {
                    k = 2;
                }
                s += k * arr[i];
            }
            s
        };

        let a = f(&player1);
        let b = f(&player2);
        if a > b {
            1
        } else if a < b {
            2
        } else {
            0
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
