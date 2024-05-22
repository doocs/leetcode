---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2225.Find%20Players%20With%20Zero%20or%20One%20Losses/README_EN.md
rating: 1316
source: Weekly Contest 287 Q2
tags:
    - Array
    - Hash Table
    - Counting
    - Sorting
---

<!-- problem:start -->

# [2225. Find Players With Zero or One Losses](https://leetcode.com/problems/find-players-with-zero-or-one-losses)

[中文文档](/solution/2200-2299/2225.Find%20Players%20With%20Zero%20or%20One%20Losses/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>matches</code> where <code>matches[i] = [winner<sub>i</sub>, loser<sub>i</sub>]</code> indicates that the player <code>winner<sub>i</sub></code> defeated player <code>loser<sub>i</sub></code> in a match.</p>

<p>Return <em>a list </em><code>answer</code><em> of size </em><code>2</code><em> where:</em></p>

<ul>
	<li><code>answer[0]</code> is a list of all players that have <strong>not</strong> lost any matches.</li>
	<li><code>answer[1]</code> is a list of all players that have lost exactly <strong>one</strong> match.</li>
</ul>

<p>The values in the two lists should be returned in <strong>increasing</strong> order.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>You should only consider the players that have played <strong>at least one</strong> match.</li>
	<li>The testcases will be generated such that <strong>no</strong> two matches will have the <strong>same</strong> outcome.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
<strong>Output:</strong> [[1,2,10],[4,5,7,8]]
<strong>Explanation:</strong>
Players 1, 2, and 10 have not lost any matches.
Players 4, 5, 7, and 8 each have lost one match.
Players 3, 6, and 9 each have lost two matches.
Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> matches = [[2,3],[1,3],[5,4],[6,4]]
<strong>Output:</strong> [[1,2,5,6],[]]
<strong>Explanation:</strong>
Players 1, 2, 5, and 6 have not lost any matches.
Players 3 and 4 each have lost two matches.
Thus, answer[0] = [1,2,5,6] and answer[1] = [].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= matches.length &lt;= 10<sup>5</sup></code></li>
	<li><code>matches[i].length == 2</code></li>
	<li><code>1 &lt;= winner<sub>i</sub>, loser<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>winner<sub>i</sub> != loser<sub>i</sub></code></li>
	<li>All <code>matches[i]</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Sorting

We use a hash table `cnt` to record the number of matches each player has lost.

Then we traverse the hash table, put the players who lost 0 matches into `ans[0]`, and put the players who lost 1 match into `ans[1]`.

Finally, we sort `ans[0]` and `ans[1]` in ascending order and return the result.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of matches.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findWinners(self, matches: List[List[int]]) -> List[List[int]]:
        cnt = Counter()
        for winner, loser in matches:
            if winner not in cnt:
                cnt[winner] = 0
            cnt[loser] += 1
        ans = [[], []]
        for x, v in sorted(cnt.items()):
            if v < 2:
                ans[v].append(x)
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var e : matches) {
            cnt.putIfAbsent(e[0], 0);
            cnt.merge(e[1], 1, Integer::sum);
        }
        List<List<Integer>> ans = List.of(new ArrayList<>(), new ArrayList<>());
        for (var e : cnt.entrySet()) {
            if (e.getValue() < 2) {
                ans.get(e.getValue()).add(e.getKey());
            }
        }
        Collections.sort(ans.get(0));
        Collections.sort(ans.get(1));
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> findWinners(vector<vector<int>>& matches) {
        map<int, int> cnt;
        for (auto& e : matches) {
            if (!cnt.contains(e[0])) {
                cnt[e[0]] = 0;
            }
            ++cnt[e[1]];
        }
        vector<vector<int>> ans(2);
        for (auto& [x, v] : cnt) {
            if (v < 2) {
                ans[v].push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findWinners(matches [][]int) [][]int {
	cnt := map[int]int{}
	for _, e := range matches {
		if _, ok := cnt[e[0]]; !ok {
			cnt[e[0]] = 0
		}
		cnt[e[1]]++
	}
	ans := make([][]int, 2)
	for x, v := range cnt {
		if v < 2 {
			ans[v] = append(ans[v], x)
		}
	}
	sort.Ints(ans[0])
	sort.Ints(ans[1])
	return ans
}
```

#### TypeScript

```ts
function findWinners(matches: number[][]): number[][] {
    const cnt: Map<number, number> = new Map();
    for (const [winner, loser] of matches) {
        if (!cnt.has(winner)) {
            cnt.set(winner, 0);
        }
        cnt.set(loser, (cnt.get(loser) || 0) + 1);
    }
    const ans: number[][] = [[], []];
    for (const [x, v] of cnt) {
        if (v < 2) {
            ans[v].push(x);
        }
    }
    ans[0].sort((a, b) => a - b);
    ans[1].sort((a, b) => a - b);
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matches
 * @return {number[][]}
 */
var findWinners = function (matches) {
    const cnt = new Map();
    for (const [winner, loser] of matches) {
        if (!cnt.has(winner)) {
            cnt.set(winner, 0);
        }
        cnt.set(loser, (cnt.get(loser) || 0) + 1);
    }
    const ans = [[], []];
    for (const [x, v] of cnt) {
        if (v < 2) {
            ans[v].push(x);
        }
    }
    ans[0].sort((a, b) => a - b);
    ans[1].sort((a, b) => a - b);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
