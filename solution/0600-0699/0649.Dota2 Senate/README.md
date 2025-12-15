---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0649.Dota2%20Senate/README.md
tags:
    - 贪心
    - 队列
    - 字符串
---

<!-- problem:start -->

# [649. Dota2 参议院](https://leetcode.cn/problems/dota2-senate)

[English Version](/solution/0600-0699/0649.Dota2%20Senate/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Dota2 的世界里有两个阵营：<code>Radiant</code>（天辉）和&nbsp;<code>Dire</code>（夜魇）</p>

<p>Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的 <strong>一 </strong>项：</p>

<ul>
	<li><strong>剥夺一名参议员的权利</strong>：一名参议员可以使另一名参议员在本轮及所有后续轮次中失去所有权利。</li>
	<li><strong>宣布胜利</strong>：如果参议员发现有权利投票的参议员都是 <strong>同一个阵营的</strong> ，他可以宣布胜利并决定在游戏中的有关变化。</li>
</ul>

<p>给你一个字符串&nbsp;<code>senate</code> 代表每个参议员的阵营。字母 <code>'R'</code> 和 <code>'D'</code>分别代表了&nbsp;<code>Radiant</code>（天辉）和&nbsp;<code>Dire</code>（夜魇）。然后，如果有 <code>n</code> 个参议员，给定字符串的大小将是&nbsp;<code>n</code>。</p>

<p>以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。</p>

<p>假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是&nbsp;<code>"Radiant"</code> 或 <code>"Dire"</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>senate = "RD"
<strong>输出：</strong>"Radiant"
<strong>解释：
</strong><code>第 1 轮时，第一个参议员来自 Radiant 阵营，他可以使用第一项权利让第二个参议员失去所有权利。
这一轮中，第二个参议员将会被跳过，因为他的权利被禁止了。
第 2 轮时，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人</code>。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>senate = "RDD"
<strong>输出：</strong>"Dire"
<strong>解释：</strong>
第 1 轮时，第一个<code>来自 Radiant 阵营的</code>参议员可以使用第一项权利禁止第二个参议员的权利。
<code>这一轮中，</code>第二个<code>来自 Dire 阵营的</code>参议员会将被跳过，因为他的权利被禁止了。
<code>这一轮中，</code>第三个<code>来自 Dire 阵营的</code>参议员可以使用他的第一项权利禁止第一个参议员的权利。
因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == senate.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>senate[i]</code> 为 <code>'R'</code> 或 <code>'D'</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：队列 + 模拟

我们创建两个队列 $qr$ 和 $qd$，分别记录天辉和夜魇阵营的参议员的下标。然后我们开始进行模拟，每一轮各从队首取出一位参议员，然后根据他的阵营进行不同的操作：

- 如果天辉阵营的参议员编号小于夜魇阵营的参议员编号，那么该天辉阵营的参议员就可以将夜魇阵营的参议员票权永久取消，我们将天辉阵营的参议员的下标加 $n$ 后重新放回队尾，表示该参议员会参与下一轮的投票。
- 如果夜魇阵营的参议员编号小于天辉阵营的参议员编号，那么该夜魇阵营的参议员就可以将天辉阵营的参议员票权永久取消，我们将夜魇阵营的参议员的下标加 $n$ 后重新放回队尾，表示该参议员会参与下一轮的投票。

最后当队列中只剩一种阵营的参议员时，该阵营的参议员获胜。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为参议员的数量。

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
