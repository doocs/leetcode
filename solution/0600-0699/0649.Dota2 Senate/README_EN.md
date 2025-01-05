---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0649.Dota2%20Senate/README_EN.md
tags:
    - Greedy
    - Queue
    - String
---

<!-- problem:start -->

# [649. Dota2 Senate](https://leetcode.com/problems/dota2-senate)

[中文文档](/solution/0600-0699/0649.Dota2%20Senate/README.md)

## Description

<!-- description:start -->

<p>In the world of Dota2, there are two parties: the Radiant and the Dire.</p>

<p>The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise <strong>one</strong> of the two rights:</p>

<ul>
	<li><strong>Ban one senator&#39;s right:</strong> A senator can make another senator lose all his rights in this and all the following rounds.</li>
	<li><strong>Announce the victory:</strong> If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.</li>
</ul>

<p>Given a string <code>senate</code> representing each senator&#39;s party belonging. The character <code>&#39;R&#39;</code> and <code>&#39;D&#39;</code> represent the Radiant party and the Dire party. Then if there are <code>n</code> senators, the size of the given string will be <code>n</code>.</p>

<p>The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.</p>

<p>Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be <code>&quot;Radiant&quot;</code> or <code>&quot;Dire&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> senate = &quot;RD&quot;
<strong>Output:</strong> &quot;Radiant&quot;
<strong>Explanation:</strong> 
The first senator comes from Radiant and he can just ban the next senator&#39;s right in round 1. 
And the second senator can&#39;t exercise any rights anymore since his right has been banned. 
And in round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> senate = &quot;RDD&quot;
<strong>Output:</strong> &quot;Dire&quot;
<strong>Explanation:</strong> 
The first senator comes from Radiant and he can just ban the next senator&#39;s right in round 1. 
And the second senator can&#39;t exercise any rights anymore since his right has been banned. 
And the third senator comes from Dire and he can ban the first senator&#39;s right in round 1. 
And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == senate.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>senate[i]</code> is either <code>&#39;R&#39;</code> or <code>&#39;D&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Queue + Simulation

We create two queues $qr$ and $qd$ to record the indices of the Radiant and Dire senators, respectively. Then we start the simulation, where in each round we dequeue one senator from each queue and perform different operations based on their factions:

-   If the Radiant senator's index is less than the Dire senator's index, the Radiant senator can permanently ban the voting rights of the Dire senator. We add $n$ to the Radiant senator's index and enqueue it back to the end of the queue, indicating that this senator will participate in the next round of voting.
-   If the Dire senator's index is less than the Radiant senator's index, the Dire senator can permanently ban the voting rights of the Radiant senator. We add $n$ to the Dire senator's index and enqueue it back to the end of the queue, indicating that this senator will participate in the next round of voting.

Finally, when there are only senators from one faction left in the queues, the senators from that faction win.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of senators.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def predictPartyVictory(self, senate: str) -> str:
        qr = deque()
        qd = deque()
        for i, c in enumerate(senate):
            if c == "R":
                qr.append(i)
            else:
                qd.append(i)
        n = len(senate)
        while qr and qd:
            if qr[0] < qd[0]:
                qr.append(qr[0] + n)
            else:
                qd.append(qd[0] + n)
            qr.popleft()
            qd.popleft()
        return "Radiant" if qr else "Dire"
```

#### Java

```java
class Solution {
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Deque<Integer> qr = new ArrayDeque<>();
        Deque<Integer> qd = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                qr.offer(i);
            } else {
                qd.offer(i);
            }
        }
        while (!qr.isEmpty() && !qd.isEmpty()) {
            if (qr.peek() < qd.peek()) {
                qr.offer(qr.peek() + n);
            } else {
                qd.offer(qd.peek() + n);
            }
            qr.poll();
            qd.poll();
        }
        return qr.isEmpty() ? "Dire" : "Radiant";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string predictPartyVictory(string senate) {
        int n = senate.size();
        queue<int> qr;
        queue<int> qd;
        for (int i = 0; i < n; ++i) {
            if (senate[i] == 'R') {
                qr.push(i);
            } else {
                qd.push(i);
            }
        }
        while (!qr.empty() && !qd.empty()) {
            int r = qr.front();
            int d = qd.front();
            qr.pop();
            qd.pop();
            if (r < d) {
                qr.push(r + n);
            } else {
                qd.push(d + n);
            }
        }
        return qr.empty() ? "Dire" : "Radiant";
    }
};
```

#### Go

```go
func predictPartyVictory(senate string) string {
	n := len(senate)
	qr := []int{}
	qd := []int{}
	for i, c := range senate {
		if c == 'R' {
			qr = append(qr, i)
		} else {
			qd = append(qd, i)
		}
	}
	for len(qr) > 0 && len(qd) > 0 {
		r, d := qr[0], qd[0]
		qr, qd = qr[1:], qd[1:]
		if r < d {
			qr = append(qr, r+n)
		} else {
			qd = append(qd, d+n)
		}
	}
	if len(qr) > 0 {
		return "Radiant"
	}
	return "Dire"
}
```

#### TypeScript

```ts
function predictPartyVictory(senate: string): string {
    const n = senate.length;
    const qr: number[] = [];
    const qd: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (senate[i] === 'R') {
            qr.push(i);
        } else {
            qd.push(i);
        }
    }
    while (qr.length > 0 && qd.length > 0) {
        const r = qr.shift()!;
        const d = qd.shift()!;
        if (r < d) {
            qr.push(r + n);
        } else {
            qd.push(d + n);
        }
    }
    return qr.length > 0 ? 'Radiant' : 'Dire';
}
```

#### Rust

```rust
impl Solution {
    pub fn predict_party_victory(senate: String) -> String {
        let mut qr = std::collections::VecDeque::new();
        let mut qd = std::collections::VecDeque::new();
        let n = senate.len();
        for i in 0..n {
            if let Some(char) = senate.chars().nth(i) {
                if char == 'R' {
                    qr.push_back(i);
                } else {
                    qd.push_back(i);
                }
            }
        }

        while !qr.is_empty() && !qd.is_empty() {
            let front_qr = qr.pop_front().unwrap();
            let front_qd = qd.pop_front().unwrap();
            if front_qr < front_qd {
                qr.push_back(front_qr + n);
            } else {
                qd.push_back(front_qd + n);
            }
        }
        if qr.is_empty() {
            return "Dire".to_string();
        }
        "Radiant".to_string()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
