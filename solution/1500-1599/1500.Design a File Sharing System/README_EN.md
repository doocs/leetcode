# [1500. Design a File Sharing System](https://leetcode.com/problems/design-a-file-sharing-system)

[中文文档](/solution/1500-1599/1500.Design%20a%20File%20Sharing%20System/README.md)

## Description

<p>We will use a file-sharing system to share a very large file which consists of <code>m</code> small <b>chunks</b> with IDs from <code>1</code> to <code>m</code>.</p>

<p>When users join the system, the system should assign <b>a unique</b> ID to them. The unique ID should be used <b>once</b> for each user, but when a user leaves the system, the ID can be <b>reused</b> again.</p>

<p>Users can request a certain chunk of the file, the system should return a list of IDs of all the users who own this chunk. If the user receives a non-empty list of IDs, they receive the requested chunk successfully.</p>

<p><br />
Implement the <code>FileSharing</code> class:</p>

<ul>
	<li><code>FileSharing(int m)</code> Initializes the object with a file of <code>m</code> chunks.</li>
	<li><code>int join(int[] ownedChunks)</code>: A new user joined the system owning some chunks of the file, the system should assign an id to the user which is the <b>smallest positive integer</b> not taken by any other user. Return the assigned id.</li>
	<li><code>void leave(int userID)</code>: The user with <code>userID</code> will leave the system, you cannot take file chunks from them anymore.</li>
	<li><code>int[] request(int userID, int chunkID)</code>: The user <code>userID</code> requested the file chunk with <code>chunkID</code>. Return a list of the IDs of all users that own this chunk sorted in ascending order.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example:</strong></p>

<pre>
<b>Input:</b>
[&quot;FileSharing&quot;,&quot;join&quot;,&quot;join&quot;,&quot;join&quot;,&quot;request&quot;,&quot;request&quot;,&quot;leave&quot;,&quot;request&quot;,&quot;leave&quot;,&quot;join&quot;]
[[4],[[1,2]],[[2,3]],[[4]],[1,3],[2,2],[1],[2,1],[2],[[]]]
<b>Output:</b>
[null,1,2,3,[2],[1,2],null,[],null,1]
<b>Explanation:</b>
FileSharing fileSharing = new FileSharing(4); // We use the system to share a file of 4 chunks.

fileSharing.join([1, 2]);    // A user who has chunks [1,2] joined the system, assign id = 1 to them and return 1.

fileSharing.join([2, 3]);    // A user who has chunks [2,3] joined the system, assign id = 2 to them and return 2.

fileSharing.join([4]);       // A user who has chunk [4] joined the system, assign id = 3 to them and return 3.

fileSharing.request(1, 3);   // The user with id = 1 requested the third file chunk, as only the user with id = 2 has the file, return [2] . Notice that user 1 now has chunks [1,2,3].

fileSharing.request(2, 2);   // The user with id = 2 requested the second file chunk, users with ids [1,2] have this chunk, thus we return [1,2].

fileSharing.leave(1);        // The user with id = 1 left the system, all the file chunks with them are no longer available for other users.

fileSharing.request(2, 1);   // The user with id = 2 requested the first file chunk, no one in the system has this chunk, we return empty list [].

fileSharing.leave(2);        // The user with id = 2 left the system.

fileSharing.join([]);        // A user who doesn&#39;t have any chunks joined the system, assign id = 1 to them and return 1. Notice that ids 1 and 2 are free and we can reuse them.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= ownedChunks.length &lt;= min(100, m)</code></li>
	<li><code>1 &lt;= ownedChunks[i] &lt;= m</code></li>
	<li>Values of <code>ownedChunks</code> are unique.</li>
	<li><code>1 &lt;= chunkID &lt;= m</code></li>
	<li><code>userID</code> is guaranteed to be a user in the system if you <strong>assign</strong> the IDs <strong>correctly</strong>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>join</code>, <code>leave</code> and <code>request</code>.</li>
	<li>Each call to <code>leave</code> will have a matching call for <code>join</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong></p>

<ul>
	<li>What happens if the system identifies the user by their IP address instead of their unique ID and users disconnect and connect from the system with the same IP?</li>
	<li>If the users in the system join and leave the system frequently without requesting any chunks, will your solution still be efficient?</li>
	<li>If all users join the system one time, request all files, and then leave, will your solution still be efficient?</li>
	<li>If the system will be used to share <code>n</code> files where the <code>ith</code> file consists of <code>m[i]</code>, what are the changes you have to make?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **...**

```

```

<!-- tabs:end -->
