# [2225. Find Players With Zero or One Losses](https://leetcode.com/problems/find-players-with-zero-or-one-losses)

[中文文档](/solution/2200-2299/2225.Find%20Players%20With%20Zero%20or%20One%20Losses/README.md)

## Description

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
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
<strong>Output:</strong> [[1,2,10],[4,5,7,8]]
<strong>Explanation:</strong>
Players 1, 2, and 10 have not lost any matches.
Players 4, 5, 7, and 8 each have lost one match.
Players 3, 6, and 9 each have lost two matches.
Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
</pre>

<p><strong>Example 2:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findWinners(self, matches: List[List[int]]) -> List[List[int]]:
        cnt = Counter()
        for a, b in matches:
            if a not in cnt:
                cnt[a] = 0
            cnt[b] += 1
        ans = [[], []]
        for u, v in cnt.items():
            if v < 2:
                ans[v].append(u)
        ans[0].sort()
        ans[1].sort()
        return ans
```

### **Java**

```java
class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int[] m : matches) {
            int a = m[0], b = m[1];
            cnt.putIfAbsent(a, 0);
            cnt.put(b, cnt.getOrDefault(b, 0) + 1);
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int u = entry.getKey();
            int v = entry.getValue();
            if (v < 2) {
                ans.get(v).add(u);
            }
        }
        Collections.sort(ans.get(0));
        Collections.sort(ans.get(1));
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> findWinners(vector<vector<int>>& matches) {
        unordered_map<int, int> cnt;
        for (auto& m : matches) {
            int a = m[0], b = m[1];
            if (!cnt.count(a)) cnt[a] = 0;
            ++cnt[b];
        }
        vector<vector<int>> ans(2);
        for (auto& [u, v] : cnt) {
            if (v < 2) ans[v].push_back(u);
        }
        sort(ans[0].begin(), ans[0].end());
        sort(ans[1].begin(), ans[1].end());
        return ans;
    }
};
```

### **Go**

```go
func findWinners(matches [][]int) [][]int {
	cnt := map[int]int{}
	for _, m := range matches {
		a, b := m[0], m[1]
		if _, ok := cnt[a]; !ok {
			cnt[a] = 0
		}
		cnt[b]++
	}
	ans := make([][]int, 2)
	for u, v := range cnt {
		if v < 2 {
			ans[v] = append(ans[v], u)
		}
	}
	sort.Ints(ans[0])
	sort.Ints(ans[1])
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
