---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1733.Minimum%20Number%20of%20People%20to%20Teach/README_EN.md
rating: 1983
source: Biweekly Contest 44 Q2
tags:
    - Greedy
    - Array
    - Hash Table
---

<!-- problem:start -->

# [1733. Minimum Number of People to Teach](https://leetcode.com/problems/minimum-number-of-people-to-teach)

[中文文档](/solution/1700-1799/1733.Minimum%20Number%20of%20People%20to%20Teach/README.md)

## Description

<!-- description:start -->

<p>On a social network consisting of <code>m</code> users and some friendships between users, two users can communicate with each other if they know a common language.</p>

<p>You are given an integer <code>n</code>, an array <code>languages</code>, and an array <code>friendships</code> where:</p>

<ul>
	<li>There are <code>n</code> languages numbered <code>1</code> through <code>n</code>,</li>
	<li><code>languages[i]</code> is the set of languages the <code>i<sup>​​​​​​th</sup></code>​​​​ user knows, and</li>
	<li><code>friendships[i] = [u<sub>​​​​​​i</sub>​​​, v<sub>​​​​​​i</sub>]</code> denotes a friendship between the users <code>u<sup>​​​​​</sup><sub>​​​​​​i</sub></code>​​​​​ and <code>v<sub>i</sub></code>.</li>
</ul>

<p>You can choose <strong>one</strong> language and teach it to some users so that all friends can communicate with each other. Return <i data-stringify-type="italic">the</i> <i><strong>minimum</strong> </i><i data-stringify-type="italic">number of users you need to teach.</i></p>
Note that friendships are not transitive, meaning if <code>x</code> is a friend of <code>y</code> and <code>y</code> is a friend of <code>z</code>, this doesn&#39;t guarantee that <code>x</code> is a friend of <code>z</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can either teach user 1 the second language or user 2 the first language.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Teach the third language to users 1 and 3, yielding two users to teach.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>languages.length == m</code></li>
	<li><code>1 &lt;= m &lt;= 500</code></li>
	<li><code>1 &lt;= languages[i].length &lt;= n</code></li>
	<li><code>1 &lt;= languages[i][j] &lt;= n</code></li>
	<li><code>1 &lt;= u<sub>​​​​​​i</sub> &lt; v<sub>​​​​​​i</sub> &lt;= languages.length</code></li>
	<li><code>1 &lt;= friendships.length &lt;= 500</code></li>
	<li>All tuples <code>(u<sub>​​​​​i, </sub>v<sub>​​​​​​i</sub>)</code> are unique</li>
	<li><code>languages[i]</code> contains only unique values</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation + Statistics

For each friendship, if the sets of languages known by the two people do not intersect, we need to teach one language so that they can communicate. We add these people to a hash set $s$.

Then, for each language, we count how many people in set $s$ know that language and find the maximum count, denoted as $mx$. The answer is $|s| - mx$, where $|s|$ is the size of set $s$.

The time complexity is $O(m^2 \times k)$. Here, $m$ is the number of languages, and $k$ is the number of friendships.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumTeachings(
        self, n: int, languages: List[List[int]], friendships: List[List[int]]
    ) -> int:
        def check(u: int, v: int) -> bool:
            for x in languages[u - 1]:
                for y in languages[v - 1]:
                    if x == y:
                        return True
            return False

        s = set()
        for u, v in friendships:
            if not check(u, v):
                s.add(u)
                s.add(v)
        cnt = Counter()
        for u in s:
            for l in languages[u - 1]:
                cnt[l] += 1
        return len(s) - max(cnt.values(), default=0)
```

#### Java

```java
class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer> s = new HashSet<>();
        for (var e : friendships) {
            int u = e[0], v = e[1];
            if (!check(u, v, languages)) {
                s.add(u);
                s.add(v);
            }
        }
        if (s.isEmpty()) {
            return 0;
        }
        int[] cnt = new int[n + 1];
        for (int u : s) {
            for (int l : languages[u - 1]) {
                ++cnt[l];
            }
        }
        int mx = 0;
        for (int v : cnt) {
            mx = Math.max(mx, v);
        }
        return s.size() - mx;
    }

    private boolean check(int u, int v, int[][] languages) {
        for (int x : languages[u - 1]) {
            for (int y : languages[v - 1]) {
                if (x == y) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumTeachings(int n, vector<vector<int>>& languages, vector<vector<int>>& friendships) {
        unordered_set<int> s;
        auto check = [&](int u, int v) {
            for (int x : languages[u - 1]) {
                for (int y : languages[v - 1]) {
                    if (x == y) {
                        return true;
                    }
                }
            }
            return false;
        };
        for (auto& e : friendships) {
            int u = e[0], v = e[1];
            if (!check(u, v)) {
                s.insert(u);
                s.insert(v);
            }
        }
        if (s.empty()) {
            return 0;
        }
        vector<int> cnt(n + 1);
        for (int u : s) {
            for (int& l : languages[u - 1]) {
                ++cnt[l];
            }
        }
        return s.size() - ranges::max(cnt);
    }
};
```

#### Go

```go
func minimumTeachings(n int, languages [][]int, friendships [][]int) int {
	check := func(u, v int) bool {
		for _, x := range languages[u-1] {
			for _, y := range languages[v-1] {
				if x == y {
					return true
				}
			}
		}
		return false
	}
	s := map[int]bool{}
	for _, e := range friendships {
		u, v := e[0], e[1]
		if !check(u, v) {
			s[u], s[v] = true, true
		}
	}
	if len(s) == 0 {
		return 0
	}
	cnt := make([]int, n+1)
	for u := range s {
		for _, l := range languages[u-1] {
			cnt[l]++
		}
	}
	return len(s) - slices.Max(cnt)
}
```

#### TypeScript

```ts
function minimumTeachings(n: number, languages: number[][], friendships: number[][]): number {
    function check(u: number, v: number): boolean {
        for (const x of languages[u - 1]) {
            for (const y of languages[v - 1]) {
                if (x === y) {
                    return true;
                }
            }
        }
        return false;
    }

    const s = new Set<number>();
    for (const [u, v] of friendships) {
        if (!check(u, v)) {
            s.add(u);
            s.add(v);
        }
    }

    const cnt = new Map<number, number>();
    for (const u of s) {
        for (const l of languages[u - 1]) {
            cnt.set(l, (cnt.get(l) || 0) + 1);
        }
    }

    return s.size - Math.max(0, ...cnt.values());
}
```

#### Rust

```rust
use std::collections::{HashSet, HashMap};

impl Solution {
    pub fn minimum_teachings(n: i32, languages: Vec<Vec<i32>>, friendships: Vec<Vec<i32>>) -> i32 {
        fn check(u: usize, v: usize, languages: &Vec<Vec<i32>>) -> bool {
            for &x in &languages[u - 1] {
                for &y in &languages[v - 1] {
                    if x == y {
                        return true;
                    }
                }
            }
            false
        }

        let mut s: HashSet<usize> = HashSet::new();
        for edge in friendships.iter() {
            let u = edge[0] as usize;
            let v = edge[1] as usize;
            if !check(u, v, &languages) {
                s.insert(u);
                s.insert(v);
            }
        }

        let mut cnt: HashMap<i32, i32> = HashMap::new();
        for &u in s.iter() {
            for &l in &languages[u - 1] {
                *cnt.entry(l).or_insert(0) += 1;
            }
        }

        let mx = cnt.values().cloned().max().unwrap_or(0);
        (s.len() as i32) - mx
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
