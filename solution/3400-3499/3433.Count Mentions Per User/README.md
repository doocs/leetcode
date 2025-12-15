---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3433.Count%20Mentions%20Per%20User/README.md
rating: 1745
source: 第 434 场周赛 Q2
tags:
    - 数组
    - 数学
    - 排序
    - 模拟
---

<!-- problem:start -->

# [3433. 统计用户被提及情况](https://leetcode.cn/problems/count-mentions-per-user)

[English Version](/solution/3400-3499/3433.Count%20Mentions%20Per%20User/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>numberOfUsers</code>&nbsp;表示用户总数，另有一个大小为 <code>n x 3</code> 的数组&nbsp;<code>events</code>&nbsp;。</p>

<p>每个&nbsp;<code inline="">events[i]</code>&nbsp;都属于下述两种类型之一：</p>

<ol>
	<li><strong>消息事件（Message Event）：</strong><code>["MESSAGE", "timestamp<sub>i</sub>", "mentions_string<sub>i</sub>"]</code>

    <ul>
    	<li>事件表示在&nbsp;<code>timestamp<sub>i</sub></code>&nbsp;时，一组用户被消息提及。</li>
    	<li><code>mentions_string<sub>i</sub></code>&nbsp;字符串包含下述标识符之一：
    	<ul>
    		<li><code>id&lt;number&gt;</code>：其中&nbsp;<code>&lt;number&gt;</code>&nbsp;是一个区间&nbsp;<code>[0,numberOfUsers - 1]</code>&nbsp;内的整数。可以用单个空格分隔&nbsp;<strong>多个</strong> id ，并且 id 可能重复。此外，这种形式可以提及离线用户。</li>
    		<li><code>ALL</code>：提及 <strong>所有</strong> 用户。</li>
    		<li><code>HERE</code>：提及所有 <strong>在线</strong> 用户。</li>
    	</ul>
    	</li>
    </ul>
    </li>
    <li><strong>离线事件（Offline Event）：</strong><code>["OFFLINE", "timestamp<sub>i</sub>", "id<sub>i</sub>"]</code>
    <ul>
    	<li>事件表示用户&nbsp;<code>id<sub>i</sub></code>&nbsp;在&nbsp;<code>timestamp<sub>i</sub></code>&nbsp;时变为离线状态 <strong>60 个单位时间</strong>。用户会在&nbsp;<code>timestamp<sub>i</sub> + 60</code>&nbsp;时自动再次上线。</li>
    </ul>
    </li>

</ol>

<p>返回数组&nbsp;<code>mentions</code>&nbsp;，其中&nbsp;<code>mentions[i]</code>&nbsp;表示 &nbsp;id 为 &nbsp;<code>i</code>&nbsp;的用户在所有&nbsp;<code>MESSAGE</code>&nbsp;事件中被提及的次数。</p>

<p>最初所有用户都处于在线状态，并且如果某个用户离线或者重新上线，其对应的状态变更将会在所有相同时间发生的消息事件之前进行处理和同步。</p>

<p><strong>注意 </strong>在单条消息中，同一个用户可能会被提及多次。每次提及都需要被 <strong>分别</strong> 统计。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]</span></p>

<p><span class="example-io"><b>输出：</b>[2,2]</span></p>

<p><b>解释：</b></p>

<p>最初，所有用户都在线。</p>

<p>时间戳 10 ，<code>id1</code>&nbsp;和&nbsp;<code>id0</code>&nbsp;被提及，<code>mentions = [1,1]</code></p>

<p>时间戳 11 ，<code>id0</code>&nbsp;<strong>离线</strong> 。</p>

<p>时间戳 71 ，<code>id0</code>&nbsp;再次 <strong>上线</strong>&nbsp;并且&nbsp;<code>"HERE"</code>&nbsp;被提及，<code>mentions = [2,2]</code></p>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","12","ALL"]]</span></p>

<p><span class="example-io"><b>输出：</b>[2,2]</span></p>

<p><b>解释：</b></p>

<p>最初，所有用户都在线。</p>

<p>时间戳 10 ，<code>id1</code>&nbsp;和&nbsp;<code>id0</code>&nbsp;被提及，<code>mentions = [1,1]</code></p>

<p>时间戳 11 ，<code>id0</code>&nbsp;<strong>离线</strong> 。</p>

<p>时间戳 12 ，<code>"ALL"</code>&nbsp;被提及。这种方式将会包括所有离线用户，所以&nbsp;<code>id0</code>&nbsp;和&nbsp;<code>id1</code>&nbsp;都被提及，<code>mentions = [2,2]</code></p>
</div>

<p><b>示例 3：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>numberOfUsers = 2, events = [["OFFLINE","10","0"],["MESSAGE","12","HERE"]]</span></p>

<p><span class="example-io"><b>输出：</b>[0,1]</span></p>

<p><b>解释：</b></p>

<p>最初，所有用户都在线。</p>

<p>时间戳 10 ，<code>id0</code>&nbsp;<strong>离线</strong>&nbsp;<b>。</b></p>

<p>时间戳 12 ，<code>"HERE"</code>&nbsp;被提及。由于&nbsp;<code>id0</code>&nbsp;仍处于离线状态，其将不会被提及，<code>mentions = [0,1]</code></p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= numberOfUsers &lt;= 100</code></li>
	<li><code>1 &lt;= events.length &lt;= 100</code></li>
	<li><code>events[i].length == 3</code></li>
	<li><code>events[i][0]</code>&nbsp;的值为&nbsp;<code>MESSAGE</code>&nbsp;或&nbsp;<code>OFFLINE</code>&nbsp;。</li>
	<li><code>1 &lt;= int(events[i][1]) &lt;= 10<sup>5</sup></code></li>
	<li>在任意 <code>"MESSAGE"</code>&nbsp;事件中，以&nbsp;<code>id&lt;number&gt;</code>&nbsp;形式提及的用户数目介于&nbsp;<code>1</code>&nbsp;和&nbsp;<code>100</code>&nbsp;之间。</li>
	<li><code>0 &lt;= &lt;number&gt; &lt;= numberOfUsers - 1</code></li>
	<li>题目保证 <code>OFFLINE</code>&nbsp;引用的用户 id 在事件发生时处于 <strong>在线</strong> 状态。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 模拟

我们将事件按照时间戳升序排序，如果时间戳相同，我们将 OFFLINE 事件排在 MESSAGE 事件之前。

然后我们模拟事件的发生过程，使用 `online_t` 数组记录每个用户下一次上线的时间，用一个变量 `lazy` 记录所有用户还需要被提及的次数。

遍历事件列表，根据事件类型进行处理：

- 如果是 ONLINE 事件，我们更新 `online_t` 数组；
- 如果是 ALL 事件，我们将 `lazy` 加一；
- 如果是 HERE 事件，我们遍历 `online_t` 数组，如果用户下一次上线的时间小于等于当前时间，我们将该用户的提及次数加一；
- 如果是 MESSAGE 事件，我们将提及的用户的提及次数加一。

最后，如果 `lazy` 大于 0，我们将所有用户的提及次数加上 `lazy`。

时间复杂度 $O(n + m \times \log m \log M + L)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是用户总数和事件总数，而 $M$ 和 $L$ 分别是时间戳的最大值以及所有提及的字符串的总长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countMentions(self, numberOfUsers: int, events: List[List[str]]) -> List[int]:
        events.sort(key=lambda e: (int(e[1]), e[0][2]))
        ans = [0] * numberOfUsers
        online_t = [0] * numberOfUsers
        lazy = 0
        for etype, ts, s in events:
            cur = int(ts)
            if etype[0] == "O":
                online_t[int(s)] = cur + 60
            elif s[0] == "A":
                lazy += 1
            elif s[0] == "H":
                for i, t in enumerate(online_t):
                    if t <= cur:
                        ans[i] += 1
            else:
                for a in s.split():
                    ans[int(a[2:])] += 1
        if lazy:
            for i in range(numberOfUsers):
                ans[i] += lazy
        return ans
```

#### Java

```java
class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        events.sort((a, b) -> {
            int x = Integer.parseInt(a.get(1));
            int y = Integer.parseInt(b.get(1));
            if (x == y) {
                return a.get(0).charAt(2) - b.get(0).charAt(2);
            }
            return x - y;
        });
        int[] ans = new int[numberOfUsers];
        int[] onlineT = new int[numberOfUsers];
        int lazy = 0;
        for (var e : events) {
            String etype = e.get(0);
            int cur = Integer.parseInt(e.get(1));
            String s = e.get(2);
            if (etype.charAt(0) == 'O') {
                onlineT[Integer.parseInt(s)] = cur + 60;
            } else if (s.charAt(0) == 'A') {
                ++lazy;
            } else if (s.charAt(0) == 'H') {
                for (int i = 0; i < numberOfUsers; ++i) {
                    if (onlineT[i] <= cur) {
                        ++ans[i];
                    }
                }
            } else {
                for (var a : s.split(" ")) {
                    ++ans[Integer.parseInt(a.substring(2))];
                }
            }
        }
        if (lazy > 0) {
            for (int i = 0; i < numberOfUsers; ++i) {
                ans[i] += lazy;
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
    vector<int> countMentions(int numberOfUsers, vector<vector<string>>& events) {
        ranges::sort(events, [](const vector<string>& a, const vector<string>& b) {
            int x = stoi(a[1]);
            int y = stoi(b[1]);
            if (x == y) {
                return a[0][2] < b[0][2];
            }
            return x < y;
        });

        vector<int> ans(numberOfUsers, 0);
        vector<int> onlineT(numberOfUsers, 0);
        int lazy = 0;

        for (const auto& e : events) {
            string etype = e[0];
            int cur = stoi(e[1]);
            string s = e[2];

            if (etype[0] == 'O') {
                onlineT[stoi(s)] = cur + 60;
            } else if (s[0] == 'A') {
                lazy++;
            } else if (s[0] == 'H') {
                for (int i = 0; i < numberOfUsers; ++i) {
                    if (onlineT[i] <= cur) {
                        ++ans[i];
                    }
                }
            } else {
                stringstream ss(s);
                string token;
                while (ss >> token) {
                    ans[stoi(token.substr(2))]++;
                }
            }
        }

        if (lazy > 0) {
            for (int i = 0; i < numberOfUsers; ++i) {
                ans[i] += lazy;
            }
        }

        return ans;
    }
};
```

#### Go

```go
func countMentions(numberOfUsers int, events [][]string) []int {
	sort.Slice(events, func(i, j int) bool {
		x, _ := strconv.Atoi(events[i][1])
		y, _ := strconv.Atoi(events[j][1])
		if x == y {
			return events[i][0][2] < events[j][0][2]
		}
		return x < y
	})

	ans := make([]int, numberOfUsers)
	onlineT := make([]int, numberOfUsers)
	lazy := 0

	for _, e := range events {
		etype := e[0]
		cur, _ := strconv.Atoi(e[1])
		s := e[2]

		if etype[0] == 'O' {
			userID, _ := strconv.Atoi(s)
			onlineT[userID] = cur + 60
		} else if s[0] == 'A' {
			lazy++
		} else if s[0] == 'H' {
			for i := 0; i < numberOfUsers; i++ {
				if onlineT[i] <= cur {
					ans[i]++
				}
			}
		} else {
			mentions := strings.Split(s, " ")
			for _, m := range mentions {
				userID, _ := strconv.Atoi(m[2:])
				ans[userID]++
			}
		}
	}

	if lazy > 0 {
		for i := 0; i < numberOfUsers; i++ {
			ans[i] += lazy
		}
	}

	return ans
}
```

#### TypeScript

```ts
function countMentions(numberOfUsers: number, events: string[][]): number[] {
    events.sort((a, b) => {
        const x = +a[1];
        const y = +b[1];
        if (x === y) {
            return a[0].charAt(2) < b[0].charAt(2) ? -1 : 1;
        }
        return x - y;
    });

    const ans: number[] = Array(numberOfUsers).fill(0);
    const onlineT: number[] = Array(numberOfUsers).fill(0);
    let lazy = 0;

    for (const [etype, ts, s] of events) {
        const cur = +ts;
        if (etype.charAt(0) === 'O') {
            const userID = +s;
            onlineT[userID] = cur + 60;
        } else if (s.charAt(0) === 'A') {
            lazy++;
        } else if (s.charAt(0) === 'H') {
            for (let i = 0; i < numberOfUsers; i++) {
                if (onlineT[i] <= cur) {
                    ans[i]++;
                }
            }
        } else {
            const mentions = s.split(' ');
            for (const m of mentions) {
                const userID = +m.slice(2);
                ans[userID]++;
            }
        }
    }

    if (lazy > 0) {
        for (let i = 0; i < numberOfUsers; i++) {
            ans[i] += lazy;
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_mentions(number_of_users: i32, mut events: Vec<Vec<String>>) -> Vec<i32> {
        let n = number_of_users as usize;

        events.sort_by(|a, b| {
            let x: i32 = a[1].parse().unwrap();
            let y: i32 = b[1].parse().unwrap();
            if x == y {
                a[0].as_bytes()[2].cmp(&b[0].as_bytes()[2])
            } else {
                x.cmp(&y)
            }
        });

        let mut ans = vec![0_i32; n];
        let mut online_t = vec![0_i32; n];
        let mut lazy = 0_i32;

        for e in events {
            let etype = &e[0];
            let cur: i32 = e[1].parse().unwrap();
            let s = &e[2];

            let c0 = etype.as_bytes()[0] as char;

            if c0 == 'O' {
                let uid: usize = s.parse().unwrap();
                online_t[uid] = cur + 60;

            } else if s.as_bytes()[0] as char == 'A' {
                lazy += 1;

            } else if s.as_bytes()[0] as char == 'H' {
                for i in 0..n {
                    if online_t[i] <= cur {
                        ans[i] += 1;
                    }
                }

            } else {
                for a in s.split(' ') {
                    let uid: usize = a[2..].parse().unwrap();
                    ans[uid] += 1;
                }
            }
        }

        if lazy > 0 {
            for i in 0..n {
                ans[i] += lazy;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
