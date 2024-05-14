---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1500.Design%20a%20File%20Sharing%20System/README.md
tags:
    - 设计
    - 哈希表
    - 数据流
    - 堆（优先队列）
---

# [1500. 设计文件分享系统 🔒](https://leetcode.cn/problems/design-a-file-sharing-system)

[English Version](/solution/1500-1599/1500.Design%20a%20File%20Sharing%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们需要使用一套文件分享系统来分享一个非常大的文件，该文件由&nbsp;<code>m</code> 个从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>m</code>&nbsp;编号的文件块组成。</p>

<p>当用户加入系统时，系统应为其注册<strong>一个独有</strong>的 ID。这个独有的 ID 应当被相应的用户使用<strong>一次</strong>，但是当用户离开系统时，其&nbsp;ID 应可以被（后续新注册的用户）<strong>再次使用</strong>。</p>

<p>用户可以请求文件中的某个指定的文件块，系统应当返回拥有这个文件块的所有用户的 ID。如果用户收到&nbsp;ID 的非空列表，就表示成功接收到请求的文件块。</p>

<p><br />
实现&nbsp;<code>FileSharing</code> 类：</p>

<ul>
	<li><code>FileSharing(int m)</code>&nbsp;初始化该对象，文件有&nbsp;<code>m</code> 个文件块。</li>
	<li><code>int join(int[] ownedChunks)</code>：一个新用户加入系统，并拥有文件的一些文件块。系统应当为该用户注册一个 ID，该 ID 应是未被其他用户占用的<strong>最小正整数</strong>。返回注册的 ID。</li>
	<li><code>void leave(int userID)</code>：ID 为&nbsp;<code>userID</code>&nbsp;的用户将离开系统，你不能再从该用户提取文件块了。</li>
	<li><code>int[] request(int userID, int chunkID)</code>：ID 为&nbsp;<code>userID</code>&nbsp;的用户请求编号为&nbsp;<code>chunkID</code>&nbsp;的文件块。返回拥有这个文件块的所有用户的 ID 所构成的列表或数组，按升序排列。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>
<strong>输入:</strong>
["FileSharing","join","join","join","request","request","leave","request","leave","join"]
[[4],[[1,2]],[[2,3]],[[4]],[1,3],[2,2],[1],[2,1],[2],[[]]]
<strong>输出:</strong>
[null,1,2,3,[2],[1,2],null,[],null,1]
<strong>解释:</strong>
FileSharing fileSharing = new FileSharing(4); // 我们用该系统分享由 4 个文件块组成的文件。

fileSharing.join([1, 2]);    // 一个拥有文件块 [1,2] 的用户加入系统，为其注册 id = 1 并返回 1。

fileSharing.join([2, 3]);    // 一个拥有文件块 [2,3] 的用户加入系统，为其注册 id = 2 并返回 2。

fileSharing.join([4]);       // 一个拥有文件块 [4] 的用户加入系统，为其注册 id = 3 并返回 3。

fileSharing.request(1, 3);   // id = 1 的用户请求第 3 个文件块，只有 id = 2 的用户拥有文件块，返回 [2] 。注意，现在用户 1 现拥有文件块 [1,2,3]。

fileSharing.request(2, 2);   // id = 2 的用户请求第 2 个文件块，id 为 [1,2] 的用户拥有该文件块，所以我们返回 [1,2] 。

fileSharing.leave(1);        // id = 1 的用户离开系统，其所拥有的所有文件块不再对其他用户可用。

fileSharing.request(2, 1);   // id = 2 的用户请求第 1 个文件块，系统中没有用户拥有该文件块，所以我们返回空列表 [] 。

fileSharing.leave(2);        // id = 2 的用户离开系统。

fileSharing.join([]);        // 一个不拥有任何文件块的用户加入系统，为其注册 id = 1 并返回 1 。注意，id 1 和 2 空闲，可以重新使用。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;=&nbsp;ownedChunks.length &lt;= min(100, m)</code></li>
	<li><code>1 &lt;= ownedChunks[i] &lt;= m</code></li>
	<li><code>ownedChunks</code> 的值是互不相同的。</li>
	<li><code>1 &lt;=&nbsp;chunkID &lt;= m</code></li>
	<li>当你<strong>正确地注册</strong>用户 ID 时，题目保证&nbsp;<code>userID</code>&nbsp;是系统中的一个已注册用户。</li>
	<li><code>join</code>、&nbsp;<code>leave</code>&nbsp;和&nbsp;<code>request</code>&nbsp;最多被调用&nbsp;<code>10<sup>4</sup></code>&nbsp;次。</li>
	<li>每次对&nbsp;<code>leave</code>&nbsp;的调用都有对应的对&nbsp;<code>join</code>&nbsp;的调用。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>当系统以用户的 IP 地址而不是独有 ID 来识别用户，且用户断开连接后以相同 IP 重新连接系统时，会发生什么？</li>
	<li>当用户频繁加入并退出系统，且该用户不请求任何文件块时，你的解决方案仍然保持高效吗？</li>
	<li>当所有用户同时加入系统，请求所有文件并离开时，你的解决方案仍然保持高效吗？</li>
	<li>如果系统用于分享&nbsp;<code>n</code>&nbsp;个文件，其中第 &nbsp;<code>i</code>&nbsp;个文件由&nbsp;<code>m[i]</code>&nbsp;组成，你需要如何修改？</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class FileSharing:
    def __init__(self, m: int):
        self.cur = 0
        self.chunks = m
        self.reused = []
        self.user_chunks = defaultdict(set)

    def join(self, ownedChunks: List[int]) -> int:
        if self.reused:
            userID = heappop(self.reused)
        else:
            self.cur += 1
            userID = self.cur
        self.user_chunks[userID] = set(ownedChunks)
        return userID

    def leave(self, userID: int) -> None:
        heappush(self.reused, userID)
        self.user_chunks.pop(userID)

    def request(self, userID: int, chunkID: int) -> List[int]:
        if chunkID < 1 or chunkID > self.chunks:
            return []
        res = []
        for k, v in self.user_chunks.items():
            if chunkID in v:
                res.append(k)
        if res:
            self.user_chunks[userID].add(chunkID)
        return sorted(res)


# Your FileSharing object will be instantiated and called as such:
# obj = FileSharing(m)
# param_1 = obj.join(ownedChunks)
# obj.leave(userID)
# param_3 = obj.request(userID,chunkID)
```

```java
class FileSharing {
    private int chunks;
    private int cur;
    private TreeSet<Integer> reused;
    private TreeMap<Integer, Set<Integer>> userChunks;

    public FileSharing(int m) {
        cur = 0;
        chunks = m;
        reused = new TreeSet<>();
        userChunks = new TreeMap<>();
    }

    public int join(List<Integer> ownedChunks) {
        int userID;
        if (reused.isEmpty()) {
            ++cur;
            userID = cur;
        } else {
            userID = reused.pollFirst();
        }
        userChunks.put(userID, new HashSet<>(ownedChunks));
        return userID;
    }

    public void leave(int userID) {
        reused.add(userID);
        userChunks.remove(userID);
    }

    public List<Integer> request(int userID, int chunkID) {
        if (chunkID < 1 || chunkID > chunks) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : userChunks.entrySet()) {
            if (entry.getValue().contains(chunkID)) {
                res.add(entry.getKey());
            }
        }
        if (!res.isEmpty()) {
            userChunks.computeIfAbsent(userID, k -> new HashSet<>()).add(chunkID);
        }
        return res;
    }
}

/**
 * Your FileSharing object will be instantiated and called as such:
 * FileSharing obj = new FileSharing(m);
 * int param_1 = obj.join(ownedChunks);
 * obj.leave(userID);
 * List<Integer> param_3 = obj.request(userID,chunkID);
 */
```

<!-- tabs:end -->

<!-- end -->
