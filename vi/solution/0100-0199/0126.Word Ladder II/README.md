---
comments: true
difficulty: Hard
tags:
    - Breadth-First Search
    - Hash Table
    - String
    - Backtracking
    - Bidirectional Search
---

<!-- problem:start -->

# [126. Word Ladder II](https://leetcode.com/problems/word-ladder-ii)

[中文文档](/solution/0100-0199/0126.Word%20Ladder%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Một <strong>chuỗi biến đổi</strong> từ từ <code>beginWord</code> đến từ <code>endWord</code> sử dụng một từ điển <code>wordList</code> là một chuỗi các từ <code>beginWord -&gt; s<sub>1</sub> -&gt; s<sub>2</sub> -&gt; ... -&gt; s<sub>k</sub></code> sao cho:</p>

<ul>
	<li>Mỗi cặp từ liền kề khác nhau đúng một chữ cái.</li>
	<li>Mọi <code>s<sub>i</sub></code> với <code>1 &lt;= i &lt;= k</code> đều nằm trong <code>wordList</code>. Lưu ý rằng <code>beginWord</code> không cần nằm trong <code>wordList</code>.</li>
	<li><code>s<sub>k</sub> == endWord</code></li>
</ul>

<p>Cho hai từ <code>beginWord</code> và <code>endWord</code>, cùng một từ điển <code>wordList</code>, hãy trả về <em>tất cả <strong>các chuỗi biến đổi ngắn nhất</strong> từ</em> <code>beginWord</code> <em>đến</em> <code>endWord</code><em>, hoặc một danh sách rỗng nếu không tồn tại chuỗi nào như vậy. Mỗi chuỗi cần được trả về dưới dạng danh sách các từ </em><code>[beginWord, s<sub>1</sub>, s<sub>2</sub>, ..., s<sub>k</sub>]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]
<strong>Đầu ra:</strong> [[&quot;hit&quot;,&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;cog&quot;],[&quot;hit&quot;,&quot;hot&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]]
<strong>Giải thích:</strong>&nbsp;Có 2 chuỗi biến đổi ngắn nhất:
&quot;hit&quot; -&gt; &quot;hot&quot; -&gt; &quot;dot&quot; -&gt; &quot;dog&quot; -&gt; &quot;cog&quot;
&quot;hit&quot; -&gt; &quot;hot&quot; -&gt; &quot;lot&quot; -&gt; &quot;log&quot; -&gt; &quot;cog&quot;
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;]
<strong>Đầu ra:</strong> []
<strong>Giải thích:</strong>&nbsp;Từ endWord &quot;cog&quot; không nằm trong wordList, do đó không có chuỗi biến đổi hợp lệ nào.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= beginWord.length &lt;= 5</code></li>
	<li><code>endWord.length == beginWord.length</code></li>
	<li><code>1 &lt;= wordList.length &lt;= 500</code></li>
	<li><code>wordList[i].length == beginWord.length</code></li>
	<li><code>beginWord</code>, <code>endWord</code> và <code>wordList[i]</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
	<li><code>beginWord != endWord</code></li>
	<li>Tất cả các từ trong <code>wordList</code> là <strong>duy nhất</strong>.</li>
	<li><strong>Tổng</strong> của tất cả các chuỗi biến đổi ngắn nhất không vượt quá <code>10<sup>5</sup></code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta cần mọi phép biến đổi ngắn nhất, không chỉ độ dài. Các từ có độ dài tối đa là $5$ và danh sách có tối đa $500$ từ, vì vậy độ sâu BFS nhỏ, nhưng số đường đi có thể lớn.
>
> BFS một chiều mở rộng theo từng level và ghi lại các từ tiền nhiệm của mỗi từ. Chỉ lưu các cạnh duy trì khoảng cách hiện tại, nên mọi đường đi được ghi nhận đều ngắn nhất. Sau khi gặp từ đích, đi ngược qua các tập tiền nhiệm để liệt kê tất cả chúng.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLadders(
        self, beginWord: str, endWord: str, wordList: List[str]
    ) -> List[List[str]]:
        def dfs(path, cur):
            if cur == beginWord:
                ans.append(path[::-1])
                return
            for precursor in prev[cur]:
                path.append(precursor)
                dfs(path, precursor)
                path.pop()

        ans = []
        words = set(wordList)
        if endWord not in words:
            return ans
        words.discard(beginWord)
        dist = {beginWord: 0}
        prev = defaultdict(set)
        q = deque([beginWord])
        found = False
        step = 0
        while q and not found:
            step += 1
            for i in range(len(q), 0, -1):
                p = q.popleft()
                s = list(p)
                for i in range(len(s)):
                    ch = s[i]
                    for j in range(26):
                        s[i] = chr(ord('a') + j)
                        t = ''.join(s)
                        if dist.get(t, 0) == step:
                            prev[t].add(p)
                        if t not in words:
                            continue
                        prev[t].add(p)
                        words.discard(t)
                        q.append(t)
                        dist[t] = step
                        if endWord == t:
                            found = True
                    s[i] = ch
        if found:
            path = [endWord]
            dfs(path, endWord)
        return ans
```

#### Java

```java
class Solution {
    private List<List<String>> ans;
    private Map<String, Set<String>> prev;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        ans = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return ans;
        }
        words.remove(beginWord);
        Map<String, Integer> dist = new HashMap<>();
        dist.put(beginWord, 0);
        prev = new HashMap<>();
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        boolean found = false;
        int step = 0;
        while (!q.isEmpty() && !found) {
            ++step;
            for (int i = q.size(); i > 0; --i) {
                String p = q.poll();
                char[] chars = p.toCharArray();
                for (int j = 0; j < chars.length; ++j) {
                    char ch = chars[j];
                    for (char k = 'a'; k <= 'z'; ++k) {
                        chars[j] = k;
                        String t = new String(chars);
                        if (dist.getOrDefault(t, 0) == step) {
                            prev.get(t).add(p);
                        }
                        if (!words.contains(t)) {
                            continue;
                        }
                        prev.computeIfAbsent(t, key -> new HashSet<>()).add(p);
                        words.remove(t);
                        q.offer(t);
                        dist.put(t, step);
                        if (endWord.equals(t)) {
                            found = true;
                        }
                    }
                    chars[j] = ch;
                }
            }
        }
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            dfs(path, beginWord, endWord);
        }
        return ans;
    }

    private void dfs(Deque<String> path, String beginWord, String cur) {
        if (cur.equals(beginWord)) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (String precursor : prev.get(cur)) {
            path.addFirst(precursor);
            dfs(path, beginWord, precursor);
            path.removeFirst();
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        vector<vector<string>> ans;
        unordered_set<string> words(wordList.begin(), wordList.end());
        if (!words.count(endWord)) {
            return ans;
        }
        words.erase(beginWord);
        unordered_map<string, int> dist{{beginWord, 0}};
        unordered_map<string, unordered_set<string>> prev;
        queue<string> q{{beginWord}};
        bool found = false;
        int step = 0;
        while (!q.empty() && !found) {
            ++step;
            for (int i = q.size(); i > 0; --i) {
                string p = q.front();
                q.pop();
                string t = p;
                for (int j = 0; j < t.size(); ++j) {
                    char ch = t[j];
                    for (char k = 'a'; k <= 'z'; ++k) {
                        t[j] = k;
                        if (dist.count(t) && dist[t] == step) {
                            prev[t].insert(p);
                        }
                        if (!words.count(t)) {
                            continue;
                        }
                        prev[t].insert(p);
                        words.erase(t);
                        q.push(t);
                        dist[t] = step;
                        if (t == endWord) {
                            found = true;
                        }
                    }
                    t[j] = ch;
                }
            }
        }
        function<void(vector<string>&, const string&)> dfs = [&](vector<string>& path, const string& cur) {
            if (cur == beginWord) {
                ans.emplace_back(path.rbegin(), path.rend());
                return;
            }
            for (const string& precursor : prev[cur]) {
                path.push_back(precursor);
                dfs(path, precursor);
                path.pop_back();
            }
        };
        if (found) {
            vector<string> path{endWord};
            dfs(path, endWord);
        }
        return ans;
    }
};
```

#### Go

```go
func findLadders(beginWord string, endWord string, wordList []string) [][]string {
	var ans [][]string
	words := make(map[string]bool)
	for _, word := range wordList {
		words[word] = true
	}
	if !words[endWord] {
		return ans
	}
	words[beginWord] = false
	dist := map[string]int{beginWord: 0}
	prev := map[string]map[string]bool{}
	q := []string{beginWord}
	found := false
	step := 0
	for len(q) > 0 && !found {
		step++
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			chars := []byte(p)
			for j := 0; j < len(chars); j++ {
				ch := chars[j]
				for k := 'a'; k <= 'z'; k++ {
					chars[j] = byte(k)
					t := string(chars)
					if v, ok := dist[t]; ok {
						if v == step {
							prev[t][p] = true
						}
					}
					if !words[t] {
						continue
					}
					if len(prev[t]) == 0 {
						prev[t] = make(map[string]bool)
					}
					prev[t][p] = true
					words[t] = false
					q = append(q, t)
					dist[t] = step
					if endWord == t {
						found = true
					}
				}
				chars[j] = ch
			}
		}
	}
	var dfs func(path []string, begin, cur string)
	dfs = func(path []string, begin, cur string) {
		if cur == beginWord {
			cp := make([]string, len(path))
			copy(cp, path)
			ans = append(ans, cp)
			return
		}
		for k := range prev[cur] {
			path = append([]string{k}, path...)
			dfs(path, beginWord, k)
			path = path[1:]
		}
	}
	if found {
		path := []string{endWord}
		dfs(path, beginWord, endWord)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
