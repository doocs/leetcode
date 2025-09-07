---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1733.Minimum%20Number%20of%20People%20to%20Teach/README.md
rating: 1983
source: 第 44 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [1733. 需要教语言的最少人数](https://leetcode.cn/problems/minimum-number-of-people-to-teach)

[English Version](/solution/1700-1799/1733.Minimum%20Number%20of%20People%20to%20Teach/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在一个由 <code>m</code> 个用户组成的社交网络里，我们获取到一些用户之间的好友关系。两个用户之间可以相互沟通的条件是他们都掌握同一门语言。</p>

<p>给你一个整数 <code>n</code> ，数组 <code>languages</code> 和数组 <code>friendships</code> ，它们的含义如下：</p>

<ul>
	<li>总共有 <code>n</code> 种语言，编号从 <code>1</code> 到 <code>n</code> 。</li>
	<li><code>languages[i]</code> 是第 <code>i</code> 位用户掌握的语言集合。</li>
	<li><code>friendships[i] = [u<sub>​​​​​​i</sub>​​​, v<sub>​​​​​​i</sub>]</code> 表示 <code>u<sup>​​​​​</sup><sub>​​​​​​i</sub></code>​​​​​ 和 <code>v<sub>i</sub></code> 为好友关系。</li>
</ul>

<p>你可以选择 <strong>一门</strong> 语言并教会一些用户，使得所有好友之间都可以相互沟通。请返回你 <strong>最少</strong> 需要教会多少名用户。</p>
请注意，好友关系没有传递性，也就是说如果 <code>x</code> 和 <code>y</code> 是好友，且 <code>y</code> 和 <code>z</code> 是好友， <code>x</code> 和 <code>z</code> 不一定是好友。

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
<b>输出：</b>1
<b>解释：</b>你可以选择教用户 1 第二门语言，也可以选择教用户 2 第一门语言。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
<b>输出：</b>2
<b>解释：</b>教用户 1 和用户 3 第三门语言，需要教 2 名用户。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 500</code></li>
	<li><code>languages.length == m</code></li>
	<li><code>1 <= m <= 500</code></li>
	<li><code>1 <= languages[i].length <= n</code></li>
	<li><code>1 <= languages[i][j] <= n</code></li>
	<li><code>1 <= u<sub>​​​​​​i</sub> < v<sub>​​​​​​i</sub> <= languages.length</code></li>
	<li><code>1 <= friendships.length <= 500</code></li>
	<li>所有的好友关系 <code>(u<sub>​​​​​i, </sub>v<sub>​​​​​​i</sub>)</code> 都是唯一的。</li>
	<li><code>languages[i]</code> 中包含的值互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟 + 统计

对于每个好友关系，如果两个人掌握的语言集合不相交，则需要教一门语言，使得两个人可以相互沟通，我们将这些人放入一个哈希集合 $s$ 中。

然后在这个集合 $s$ 中，统计每种语言掌握的人数，获取最大的人数，我们记为 $mx$，那么答案就是 $|s| - mx$。其中 $|s|$ 表示集合 $s$ 的大小。

时间复杂度 $O(m^2 \times k)$。其中 $m$ 为语言的数量，而 $k$ 为好友关系的数量。

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
