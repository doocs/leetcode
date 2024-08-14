---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1311.Get%20Watched%20Videos%20by%20Your%20Friends/README.md
rating: 1652
source: 第 170 场周赛 Q3
tags:
    - 广度优先搜索
    - 图
    - 数组
    - 哈希表
    - 排序
---

<!-- problem:start -->

# [1311. 获取你好友已观看的视频](https://leetcode.cn/problems/get-watched-videos-by-your-friends)

[English Version](/solution/1300-1399/1311.Get%20Watched%20Videos%20by%20Your%20Friends/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有&nbsp;<code>n</code> 个人，每个人都有一个&nbsp; <code>0</code>&nbsp;到&nbsp;<code>n-1</code>&nbsp;的唯一&nbsp;<em>id</em>&nbsp;。</p>

<p>给你数组 <code>watchedVideos</code>&nbsp; 和&nbsp;<code>friends</code>&nbsp;，其中&nbsp;<code>watchedVideos[i]</code>&nbsp; 和&nbsp;<code>friends[i]</code>&nbsp;分别表示&nbsp;<code>id = i</code>&nbsp;的人观看过的视频列表和他的好友列表。</p>

<p>Level&nbsp;<strong>1</strong>&nbsp;的视频包含所有你好友观看过的视频，level&nbsp;<strong>2</strong>&nbsp;的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 <strong>k</strong>&nbsp;的视频包含所有从你出发，最短距离为&nbsp;<strong>k</strong>&nbsp;的好友观看过的视频。</p>

<p>给定你的&nbsp;<code>id</code>&nbsp; 和一个&nbsp;<code>level</code>&nbsp;值，请你找出所有指定 <code>level</code> 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按字母顺序从小到大排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1311.Get%20Watched%20Videos%20by%20Your%20Friends/images/leetcode_friends_1.png" style="height: 179px; width: 129px;"></strong></p>

<pre><strong>输入：</strong>watchedVideos = [[&quot;A&quot;,&quot;B&quot;],[&quot;C&quot;],[&quot;B&quot;,&quot;C&quot;],[&quot;D&quot;]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
<strong>输出：</strong>[&quot;B&quot;,&quot;C&quot;] 
<strong>解释：</strong>
你的 id 为 0（绿色），你的朋友包括（黄色）：
id 为 1 -&gt; watchedVideos = [&quot;C&quot;]&nbsp;
id 为 2 -&gt; watchedVideos = [&quot;B&quot;,&quot;C&quot;]&nbsp;
你朋友观看过视频的频率为：
B -&gt; 1&nbsp;
C -&gt; 2
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1311.Get%20Watched%20Videos%20by%20Your%20Friends/images/leetcode_friends_2.png" style="height: 179px; width: 129px;"></strong></p>

<pre><strong>输入：</strong>watchedVideos = [[&quot;A&quot;,&quot;B&quot;],[&quot;C&quot;],[&quot;B&quot;,&quot;C&quot;],[&quot;D&quot;]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
<strong>输出：</strong>[&quot;D&quot;]
<strong>解释：</strong>
你的 id 为 0（绿色），你朋友的朋友只有一个人，他的 id 为 3（黄色）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == watchedVideos.length ==&nbsp;friends.length</code></li>
	<li><code>2 &lt;= n&nbsp;&lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;watchedVideos[i].length &lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;watchedVideos[i][j].length &lt;= 8</code></li>
	<li><code>0 &lt;= friends[i].length &lt; n</code></li>
	<li><code>0 &lt;= friends[i][j]&nbsp;&lt; n</code></li>
	<li><code>0 &lt;= id &lt; n</code></li>
	<li><code>1 &lt;= level &lt; n</code></li>
	<li>如果&nbsp;<code>friends[i]</code> 包含&nbsp;<code>j</code>&nbsp;，那么&nbsp;<code>friends[j]</code> 包含&nbsp;<code>i</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们可以使用广度优先搜索的方法，从 $\textit{id}$ 出发，找到所有距离为 $\textit{level}$ 的好友，然后统计这些好友观看的视频。

具体地，我们可以使用一个队列 $\textit{q}$ 来存储当前层的好友，初始时将 $\textit{id}$ 加入队列 $\textit{q}$ 中，用一个哈希表或者布尔数组 $\textit{vis}$ 来记录已经访问过的好友，然后进行 $\textit{level}$ 次循环，每次循环将队列中的所有好友出队，并将他们的好友加入队列，直到找到所有距禒为 $\textit{level}$ 的好友。

然后，我们使用一个哈希表 $\textit{cnt}$ 来统计这些好友观看的视频及其频率，最后将哈希表中的键值对按照频率升序排序，如果频率相同，则按照视频名称升序排序。最后返回排序后的视频名称列表。

时间复杂度 $O(n + m + v \times \log v)$，空间复杂度 $O(n + v)$。其中 $n$ 和 $m$ 分别是数组 $\textit{watchedVideos}$ 和 $\textit{friends}$ 的长度，而 $v$ 是所有好友观看的视频数量。

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
