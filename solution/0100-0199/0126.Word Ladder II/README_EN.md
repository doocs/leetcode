# [126. Word Ladder II](https://leetcode.com/problems/word-ladder-ii)

[中文文档](/solution/0100-0199/0126.Word%20Ladder%20II/README.md)

## Description

<p>A <strong>transformation sequence</strong> from word <code>beginWord</code> to word <code>endWord</code> using a dictionary <code>wordList</code> is a sequence of words <code>beginWord -&gt; s<sub>1</sub> -&gt; s<sub>2</sub> -&gt; ... -&gt; s<sub>k</sub></code> such that:</p>

<ul>
	<li>Every adjacent pair of words differs by a single letter.</li>
	<li>Every <code>s<sub>i</sub></code> for <code>1 &lt;= i &lt;= k</code> is in <code>wordList</code>. Note that <code>beginWord</code> does not need to be in <code>wordList</code>.</li>
	<li><code>s<sub>k</sub> == endWord</code></li>
</ul>

<p>Given two words, <code>beginWord</code> and <code>endWord</code>, and a dictionary <code>wordList</code>, return <em>all the <strong>shortest transformation sequences</strong> from</em> <code>beginWord</code> <em>to</em> <code>endWord</code><em>, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words </em><code>[beginWord, s<sub>1</sub>, s<sub>2</sub>, ..., s<sub>k</sub>]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]
<strong>Output:</strong> [[&quot;hit&quot;,&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;cog&quot;],[&quot;hit&quot;,&quot;hot&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]]
<strong>Explanation:</strong>&nbsp;There are 2 shortest transformation sequences:
&quot;hit&quot; -&gt; &quot;hot&quot; -&gt; &quot;dot&quot; -&gt; &quot;dog&quot; -&gt; &quot;cog&quot;
&quot;hit&quot; -&gt; &quot;hot&quot; -&gt; &quot;lot&quot; -&gt; &quot;log&quot; -&gt; &quot;cog&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;]
<strong>Output:</strong> []
<strong>Explanation:</strong> The endWord &quot;cog&quot; is not in wordList, therefore there is no valid transformation sequence.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= beginWord.length &lt;= 5</code></li>
	<li><code>endWord.length == beginWord.length</code></li>
	<li><code>1 &lt;= wordList.length &lt;= 500</code></li>
	<li><code>wordList[i].length == beginWord.length</code></li>
	<li><code>beginWord</code>, <code>endWord</code>, and <code>wordList[i]</code> consist of lowercase English letters.</li>
	<li><code>beginWord != endWord</code></li>
	<li>All the words in <code>wordList</code> are <strong>unique</strong>.</li>
	<li>The <strong>sum</strong> of all shortest transformation sequences does not exceed <code>10<sup>5</sup></code>.</li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
