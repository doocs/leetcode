---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2682.Find%20the%20Losers%20of%20the%20Circular%20Game/README_EN.md
rating: 1382
source: Weekly Contest 345 Q1
tags:
    - Array
    - Hash Table
    - Simulation
---

<!-- problem:start -->

# [2682. Find the Losers of the Circular Game](https://leetcode.com/problems/find-the-losers-of-the-circular-game)

[中文文档](/solution/2600-2699/2682.Find%20the%20Losers%20of%20the%20Circular%20Game/README.md)

## Description

<p>There are <code>n</code> friends that are playing a game. The friends are sitting in a circle and are numbered from <code>1</code> to <code>n</code> in <strong>clockwise order</strong>. More formally, moving clockwise from the <code>i<sup>th</sup></code> friend brings you to the <code>(i+1)<sup>th</sup></code> friend for <code>1 &lt;= i &lt; n</code>, and moving clockwise from the <code>n<sup>th</sup></code> friend brings you to the <code>1<sup>st</sup></code> friend.</p>

<p>The rules of the game are as follows:</p>

<p><code>1<sup>st</sup></code> friend receives the ball.</p>

<ul>
	<li>After that, <code>1<sup>st</sup></code> friend passes it to the friend who is <code>k</code> steps away from them in the <strong>clockwise</strong> direction.</li>
	<li>After that, the friend who receives the ball should pass it to the friend who is <code>2 * k</code> steps away from them in the <strong>clockwise</strong> direction.</li>
	<li>After that, the friend who receives the ball should pass it to the friend who is <code>3 * k</code> steps away from them in the <strong>clockwise</strong> direction, and so on and so forth.</li>
</ul>

<p>In other words, on the <code>i<sup>th</sup></code> turn, the friend holding the ball should pass it to the friend who is <code>i * k</code> steps away from them in the <strong>clockwise</strong> direction.</p>

<p>The game is finished when some friend receives the ball for the second time.</p>

<p>The <strong>losers</strong> of the game are friends who did not receive the ball in the entire game.</p>

<p>Given the number of friends, <code>n</code>, and an integer <code>k</code>, return <em>the array answer, which contains the losers of the game in the <strong>ascending</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, k = 2
<strong>Output:</strong> [4,5]
<strong>Explanation:</strong> The game goes as follows:
1) Start at 1<sup>st</sup>&nbsp;friend and pass the ball to the friend who is 2 steps away from them - 3<sup>rd</sup>&nbsp;friend.
2) 3<sup>rd</sup>&nbsp;friend passes the ball to the friend who is 4 steps away from them - 2<sup>nd</sup>&nbsp;friend.
3) 2<sup>nd</sup>&nbsp;friend passes the ball to the friend who is 6 steps away from them  - 3<sup>rd</sup>&nbsp;friend.
4) The game ends as 3<sup>rd</sup>&nbsp;friend receives the ball for the second time.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, k = 4
<strong>Output:</strong> [2,3,4]
<strong>Explanation:</strong> The game goes as follows:
1) Start at the 1<sup>st</sup>&nbsp;friend and pass the ball to the friend who is 4 steps away from them - 1<sup>st</sup>&nbsp;friend.
2) The game ends as 1<sup>st</sup>&nbsp;friend receives the ball for the second time.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 50</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def circularGameLosers(self, n: int, k: int) -> List[int]:
        vis = [False] * n
        i, p = 0, 1
        while not vis[i]:
            vis[i] = True
            i = (i + p * k) % n
            p += 1
        return [i + 1 for i in range(n) if not vis[i]]
```

```java
class Solution {
    public int[] circularGameLosers(int n, int k) {
        boolean[] vis = new boolean[n];
        int cnt = 0;
        for (int i = 0, p = 1; !vis[i]; ++p) {
            vis[i] = true;
            ++cnt;
            i = (i + p * k) % n;
        }
        int[] ans = new int[n - cnt];
        for (int i = 0, j = 0; i < n; ++i) {
            if (!vis[i]) {
                ans[j++] = i + 1;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> circularGameLosers(int n, int k) {
        bool vis[n];
        memset(vis, false, sizeof(vis));
        for (int i = 0, p = 1; !vis[i]; ++p) {
            vis[i] = true;
            i = (i + p * k) % n;
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};
```

```go
func circularGameLosers(n int, k int) (ans []int) {
	vis := make([]bool, n)
	for i, p := 0, 1; !vis[i]; p++ {
		vis[i] = true
		i = (i + p*k) % n
	}
	for i, x := range vis {
		if !x {
			ans = append(ans, i+1)
		}
	}
	return
}
```

```ts
function circularGameLosers(n: number, k: number): number[] {
    const vis = new Array(n).fill(false);
    const ans: number[] = [];
    for (let i = 0, p = 1; !vis[i]; p++) {
        vis[i] = true;
        i = (i + p * k) % n;
    }
    for (let i = 0; i < vis.length; i++) {
        if (!vis[i]) {
            ans.push(i + 1);
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn circular_game_losers(n: i32, k: i32) -> Vec<i32> {
        let mut vis: Vec<bool> = vec![false; n as usize];

        let mut i = 0;
        let mut p = 1;
        while !vis[i] {
            vis[i] = true;
            i = (i + p * (k as usize)) % (n as usize);
            p += 1;
        }

        let mut ans = Vec::new();
        for i in 0..vis.len() {
            if !vis[i] {
                ans.push((i + 1) as i32);
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
