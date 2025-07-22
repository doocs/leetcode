---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1311.Get%20Watched%20Videos%20by%20Your%20Friends/README_EN.md
rating: 1652
source: Weekly Contest 170 Q3
tags:
    - Breadth-First Search
    - Graph
    - Array
    - Hash Table
    - Sorting
---

<!-- problem:start -->

# [1311. Get Watched Videos by Your Friends](https://leetcode.com/problems/get-watched-videos-by-your-friends)

[中文文档](/solution/1300-1399/1311.Get%20Watched%20Videos%20by%20Your%20Friends/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> people, each person has a unique <em>id</em> between <code>0</code> and <code>n-1</code>. Given the arrays <code>watchedVideos</code> and <code>friends</code>, where <code>watchedVideos[i]</code> and <code>friends[i]</code> contain the list of watched videos and the list of friends respectively for the person with <code>id = i</code>.</p>

<p>Level <strong>1</strong> of videos are all watched videos by your&nbsp;friends, level <strong>2</strong> of videos are all watched videos by the friends of your&nbsp;friends and so on. In general, the level <code>k</code> of videos are all&nbsp;watched videos by people&nbsp;with the shortest path <strong>exactly</strong> equal&nbsp;to&nbsp;<code>k</code> with you. Given your&nbsp;<code>id</code> and the <code>level</code> of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.&nbsp;</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1311.Get%20Watched%20Videos%20by%20Your%20Friends/images/leetcode_friends_1.png" style="width: 144px; height: 200px;" /></strong></p>

<pre>
<strong>Input:</strong> watchedVideos = [[&quot;A&quot;,&quot;B&quot;],[&quot;C&quot;],[&quot;B&quot;,&quot;C&quot;],[&quot;D&quot;]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
<strong>Output:</strong> [&quot;B&quot;,&quot;C&quot;] 
<strong>Explanation:</strong> 
You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
Person with id = 1 -&gt; watchedVideos = [&quot;C&quot;]&nbsp;
Person with id = 2 -&gt; watchedVideos = [&quot;B&quot;,&quot;C&quot;]&nbsp;
The frequencies of watchedVideos by your friends are:&nbsp;
B -&gt; 1&nbsp;
C -&gt; 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1311.Get%20Watched%20Videos%20by%20Your%20Friends/images/leetcode_friends_2.png" style="width: 144px; height: 200px;" /></strong></p>

<pre>
<strong>Input:</strong> watchedVideos = [[&quot;A&quot;,&quot;B&quot;],[&quot;C&quot;],[&quot;B&quot;,&quot;C&quot;],[&quot;D&quot;]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
<strong>Output:</strong> [&quot;D&quot;]
<strong>Explanation:</strong> 
You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == watchedVideos.length ==&nbsp;friends.length</code></li>
	<li><code>2 &lt;= n&nbsp;&lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;watchedVideos[i].length &lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;watchedVideos[i][j].length &lt;= 8</code></li>
	<li><code>0 &lt;= friends[i].length &lt; n</code></li>
	<li><code>0 &lt;= friends[i][j]&nbsp;&lt; n</code></li>
	<li><code>0 &lt;= id &lt; n</code></li>
	<li><code>1 &lt;= level &lt; n</code></li>
	<li>if&nbsp;<code>friends[i]</code> contains <code>j</code>, then <code>friends[j]</code> contains <code>i</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

We can use the Breadth-First Search (BFS) method to start from $\textit{id}$ and find all friends at a distance of $\textit{level}$, then count the videos watched by these friends.

Specifically, we can use a queue $\textit{q}$ to store the friends at the current level. Initially, add $\textit{id}$ to the queue $\textit{q}$. Use a hash table or a boolean array $\textit{vis}$ to record the friends that have already been visited. Then, perform $\textit{level}$ iterations, in each iteration dequeue all friends from the queue and enqueue their friends until all friends at distance $\textit{level}$ are found.

Next, we use a hash table $\textit{cnt}$ to count the videos watched by these friends and their frequencies. Finally, sort the key-value pairs in the hash table in ascending order by frequency, and if frequencies are the same, sort by video name in ascending order. Return the sorted list of video names.

Time complexity is $O(n + m + v \times \log v)$, and space complexity is $O(n + v)$. Here, $n$ and $m$ are the lengths of the arrays $\textit{watchedVideos}$ and $\textit{friends}$, respectively, and $v$ is the total number of videos watched by all friends.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def watchedVideosByFriends(
        self,
        watchedVideos: List[List[str]],
        friends: List[List[int]],
        id: int,
        level: int,
    ) -> List[str]:
        q = deque([id])
        vis = {id}
        for _ in range(level):
            for _ in range(len(q)):
                i = q.popleft()
                for j in friends[i]:
                    if j not in vis:
                        vis.add(j)
                        q.append(j)
        cnt = Counter()
        for i in q:
            for v in watchedVideos[i]:
                cnt[v] += 1
        return sorted(cnt.keys(), key=lambda k: (cnt[k], k))
```

#### Java

```java
class Solution {
    public List<String> watchedVideosByFriends(
        List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(id);
        int n = friends.length;
        boolean[] vis = new boolean[n];
        vis[id] = true;
        while (level-- > 0) {
            for (int k = q.size(); k > 0; --k) {
                int i = q.poll();
                for (int j : friends[i]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
            }
        }
        Map<String, Integer> cnt = new HashMap<>();
        for (int i : q) {
            for (var v : watchedVideos.get(i)) {
                cnt.merge(v, 1, Integer::sum);
            }
        }
        List<String> ans = new ArrayList<>(cnt.keySet());
        ans.sort((a, b) -> {
            int x = cnt.get(a), y = cnt.get(b);
            return x == y ? a.compareTo(b) : Integer.compare(x, y);
        });
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> watchedVideosByFriends(vector<vector<string>>& watchedVideos, vector<vector<int>>& friends, int id, int level) {
        queue<int> q{{id}};
        int n = friends.size();
        vector<bool> vis(n);
        vis[id] = true;
        while (level--) {
            for (int k = q.size(); k; --k) {
                int i = q.front();
                q.pop();
                for (int j : friends[i]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
        }
        unordered_map<string, int> cnt;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (const auto& v : watchedVideos[i]) {
                cnt[v]++;
            }
        }
        vector<string> ans;
        for (const auto& [key, _] : cnt) {
            ans.push_back(key);
        }
        sort(ans.begin(), ans.end(), [&cnt](const string& a, const string& b) {
            return cnt[a] == cnt[b] ? a < b : cnt[a] < cnt[b];
        });
        return ans;
    }
};
```

#### Go

```go
func watchedVideosByFriends(watchedVideos [][]string, friends [][]int, id int, level int) []string {
	q := []int{id}
	n := len(friends)
	vis := make([]bool, n)
	vis[id] = true
	for level > 0 {
		level--
		nextQ := []int{}
		for _, i := range q {
			for _, j := range friends[i] {
				if !vis[j] {
					vis[j] = true
					nextQ = append(nextQ, j)
				}
			}
		}
		q = nextQ
	}
	cnt := make(map[string]int)
	for _, i := range q {
		for _, v := range watchedVideos[i] {
			cnt[v]++
		}
	}
	ans := []string{}
	for key := range cnt {
		ans = append(ans, key)
	}
	sort.Slice(ans, func(i, j int) bool {
		if cnt[ans[i]] == cnt[ans[j]] {
			return ans[i] < ans[j]
		}
		return cnt[ans[i]] < cnt[ans[j]]
	})
	return ans
}
```

#### TypeScript

```ts
function watchedVideosByFriends(
    watchedVideos: string[][],
    friends: number[][],
    id: number,
    level: number,
): string[] {
    let q: number[] = [id];
    const n: number = friends.length;
    const vis: boolean[] = Array(n).fill(false);
    vis[id] = true;
    while (level-- > 0) {
        const nq: number[] = [];
        for (const i of q) {
            for (const j of friends[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    nq.push(j);
                }
            }
        }
        q = nq;
    }
    const cnt: { [key: string]: number } = {};
    for (const i of q) {
        for (const v of watchedVideos[i]) {
            cnt[v] = (cnt[v] || 0) + 1;
        }
    }
    const ans: string[] = Object.keys(cnt);
    ans.sort((a, b) => {
        if (cnt[a] === cnt[b]) {
            return a.localeCompare(b);
        }
        return cnt[a] - cnt[b];
    });
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
