---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3433.Count%20Mentions%20Per%20User/README_EN.md
rating: 1745
source: Weekly Contest 434 Q2
tags:
    - Array
    - Math
    - Sorting
    - Simulation
---

<!-- problem:start -->

# [3433. Count Mentions Per User](https://leetcode.com/problems/count-mentions-per-user)

[中文文档](/solution/3400-3499/3433.Count%20Mentions%20Per%20User/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>numberOfUsers</code> representing the total number of users and an array <code>events</code> of size <code>n x 3</code>.</p>

<p>Each <code inline="">events[i]</code> can be either of the following two types:</p>

<ol>
	<li><strong>Message Event:</strong> <code>[&quot;MESSAGE&quot;, &quot;timestamp<sub>i</sub>&quot;, &quot;mentions_string<sub>i</sub>&quot;]</code>

    <ul>
    	<li>This event indicates that a set of users was mentioned in a message at <code>timestamp<sub>i</sub></code>.</li>
    	<li>The <code>mentions_string<sub>i</sub></code> string can contain one of the following tokens:
    	<ul>
    		<li><code>id&lt;number&gt;</code>: where <code>&lt;number&gt;</code> is an integer in range <code>[0,numberOfUsers - 1]</code>. There can be <strong>multiple</strong> ids separated by a single whitespace and may contain duplicates. This can mention even the offline users.</li>
    		<li><code>ALL</code>: mentions <strong>all</strong> users.</li>
    		<li><code>HERE</code>: mentions all <strong>online</strong> users.</li>
    	</ul>
    	</li>
    </ul>
    </li>
    <li><strong>Offline Event:</strong> <code>[&quot;OFFLINE&quot;, &quot;timestamp<sub>i</sub>&quot;, &quot;id<sub>i</sub>&quot;]</code>
    <ul>
    	<li>This event indicates that the user <code>id<sub>i</sub></code> had become offline at <code>timestamp<sub>i</sub></code> for <strong>60 time units</strong>. The user will automatically be online again at time <code>timestamp<sub>i</sub> + 60</code>.</li>
    </ul>
    </li>

</ol>

<p>Return an array <code>mentions</code> where <code>mentions[i]</code> represents the number of mentions the user with id <code>i</code> has across all <code>MESSAGE</code> events.</p>

<p>All users are initially online, and if a user goes offline or comes back online, their status change is processed <em>before</em> handling any message event that occurs at the same timestamp.</p>

<p><strong>Note </strong>that a user can be mentioned <strong>multiple</strong> times in a <strong>single</strong> message event, and each mention should be counted <strong>separately</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">numberOfUsers = 2, events = [[&quot;MESSAGE&quot;,&quot;10&quot;,&quot;id1 id0&quot;],[&quot;OFFLINE&quot;,&quot;11&quot;,&quot;0&quot;],[&quot;MESSAGE&quot;,&quot;71&quot;,&quot;HERE&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, all users are online.</p>

<p>At timestamp 10, <code>id1</code> and <code>id0</code> are mentioned. <code>mentions = [1,1]</code></p>

<p>At timestamp 11, <code>id0</code> goes <strong>offline.</strong></p>

<p>At timestamp 71, <code>id0</code> comes back <strong>online</strong> and <code>&quot;HERE&quot;</code> is mentioned. <code>mentions = [2,2]</code></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">numberOfUsers = 2, events = [[&quot;MESSAGE&quot;,&quot;10&quot;,&quot;id1 id0&quot;],[&quot;OFFLINE&quot;,&quot;11&quot;,&quot;0&quot;],[&quot;MESSAGE&quot;,&quot;12&quot;,&quot;ALL&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, all users are online.</p>

<p>At timestamp 10, <code>id1</code> and <code>id0</code> are mentioned. <code>mentions = [1,1]</code></p>

<p>At timestamp 11, <code>id0</code> goes <strong>offline.</strong></p>

<p>At timestamp 12, <code>&quot;ALL&quot;</code> is mentioned. This includes offline users, so both <code>id0</code> and <code>id1</code> are mentioned. <code>mentions = [2,2]</code></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">numberOfUsers = 2, events = [[&quot;OFFLINE&quot;,&quot;10&quot;,&quot;0&quot;],[&quot;MESSAGE&quot;,&quot;12&quot;,&quot;HERE&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, all users are online.</p>

<p>At timestamp 10, <code>id0</code> goes <strong>offline.</strong></p>

<p>At timestamp 12, <code>&quot;HERE&quot;</code> is mentioned. Because <code>id0</code> is still offline, they will not be mentioned. <code>mentions = [0,1]</code></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numberOfUsers &lt;= 100</code></li>
	<li><code>1 &lt;= events.length &lt;= 100</code></li>
	<li><code>events[i].length == 3</code></li>
	<li><code>events[i][0]</code> will be one of <code>MESSAGE</code> or <code>OFFLINE</code>.</li>
	<li><code>1 &lt;= int(events[i][1]) &lt;= 10<sup>5</sup></code></li>
	<li>The number of <code>id&lt;number&gt;</code> mentions in any <code>&quot;MESSAGE&quot;</code> event is between <code>1</code> and <code>100</code>.</li>
	<li><code>0 &lt;= &lt;number&gt; &lt;= numberOfUsers - 1</code></li>
	<li>It is <strong>guaranteed</strong> that the user id referenced in the <code>OFFLINE</code> event is <strong>online</strong> at the time the event occurs.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Simulation

We sort the events in ascending order of timestamps. If the timestamps are the same, we place OFFLINE events before MESSAGE events.

Then we simulate the occurrence of events, using the `online_t` array to record the next online time for each user and a variable `lazy` to record the number of mentions that need to be applied to all users.

We traverse the event list and handle each event based on its type:

-   If it is an ONLINE event, we update the `online_t` array.
-   If it is an ALL event, we increment `lazy` by one.
-   If it is a HERE event, we traverse the `online_t` array. If a user's next online time is less than or equal to the current time, we increment that user's mention count by one.
-   If it is a MESSAGE event, we increment the mention count of the mentioned user by one.

Finally, if `lazy` is greater than 0, we add `lazy` to the mention count of all users.

The time complexity is $O(n + m \times \log m \log M + L)$, and the space complexity is $O(n)$. Here, $n$ and $m$ are the total number of users and events, respectively, while $M$ and $L$ are the maximum value of the timestamps and the total length of all mentioned strings, respectively.

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
