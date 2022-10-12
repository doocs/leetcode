# [1311. Get Watched Videos by Your Friends](https://leetcode.com/problems/get-watched-videos-by-your-friends)

[中文文档](/solution/1300-1399/1311.Get%20Watched%20Videos%20by%20Your%20Friends/README.md)

## Description

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

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def watchedVideosByFriends(
        self,
        watchedVideos: List[List[str]],
        friends: List[List[int]],
        id: int,
        level: int,
    ) -> List[str]:
        n = len(friends)
        vis = [False] * n
        q = deque([id])
        vis[id] = True
        for _ in range(level):
            size = len(q)
            for _ in range(size):
                u = q.popleft()
                for v in friends[u]:
                    if not vis[v]:
                        q.append(v)
                        vis[v] = True
        freq = Counter()
        for _ in range(len(q)):
            u = q.pop()
            for w in watchedVideos[u]:
                freq[w] += 1
        videos = list(freq.items())
        videos.sort(key=lambda x: (x[1], x[0]))
        return [v[0] for v in videos]
```

### **Java**

```java
class Solution {
    public List<String> watchedVideosByFriends(
        List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new LinkedList<>();
        q.offerLast(id);
        vis[id] = true;
        while (level-- > 0) {
            for (int i = q.size(); i > 0; --i) {
                int u = q.pollFirst();
                for (int v : friends[u]) {
                    if (!vis[v]) {
                        q.offerLast(v);
                        vis[v] = true;
                    }
                }
            }
        }
        Map<String, Integer> freq = new HashMap<>();
        while (!q.isEmpty()) {
            for (String w : watchedVideos.get(q.pollFirst())) {
                freq.put(w, freq.getOrDefault(w, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> t = new ArrayList<>(freq.entrySet());
        t.sort((a, b) -> {
            if (a.getValue() > b.getValue()) {
                return 1;
            }
            if (a.getValue() < b.getValue()) {
                return -1;
            }
            return a.getKey().compareTo(b.getKey());
        });
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> e : t) {
            ans.add(e.getKey());
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
