# [127. Word Ladder](https://leetcode.com/problems/word-ladder)

[中文文档](/solution/0100-0199/0127.Word%20Ladder/README.md)

## Description

<p>A <strong>transformation sequence</strong> from word <code>beginWord</code> to word <code>endWord</code> using a dictionary <code>wordList</code> is a sequence of words <code>beginWord -&gt; s<sub>1</sub> -&gt; s<sub>2</sub> -&gt; ... -&gt; s<sub>k</sub></code> such that:</p>

<ul>
	<li>Every adjacent pair of words differs by a single letter.</li>
	<li>Every <code>s<sub>i</sub></code> for <code>1 &lt;= i &lt;= k</code> is in <code>wordList</code>. Note that <code>beginWord</code> does not need to be in <code>wordList</code>.</li>
	<li><code>s<sub>k</sub> == endWord</code></li>
</ul>

<p>Given two words, <code>beginWord</code> and <code>endWord</code>, and a dictionary <code>wordList</code>, return <em>the <strong>number of words</strong> in the <strong>shortest transformation sequence</strong> from</em> <code>beginWord</code> <em>to</em> <code>endWord</code><em>, or </em><code>0</code><em> if no such sequence exists.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]
<strong>Output:</strong> 5
<strong>Explanation:</strong> One shortest transformation sequence is &quot;hit&quot; -&gt; &quot;hot&quot; -&gt; &quot;dot&quot; -&gt; &quot;dog&quot; -&gt; cog&quot;, which is 5 words long.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The endWord &quot;cog&quot; is not in wordList, therefore there is no valid transformation sequence.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= beginWord.length &lt;= 10</code></li>
	<li><code>endWord.length == beginWord.length</code></li>
	<li><code>1 &lt;= wordList.length &lt;= 5000</code></li>
	<li><code>wordList[i].length == beginWord.length</code></li>
	<li><code>beginWord</code>, <code>endWord</code>, and <code>wordList[i]</code> consist of lowercase English letters.</li>
	<li><code>beginWord != endWord</code></li>
	<li>All the words in <code>wordList</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        words = set(wordList)
        q = deque([beginWord])
        ans = 1
        while q:
            ans += 1
            for _ in range(len(q)):
                s = q.popleft()
                s = list(s)
                for i in range(len(s)):
                    ch = s[i]
                    for j in range(26):
                        s[i] = chr(ord('a') + j)
                        t = ''.join(s)
                        if t not in words:
                            continue
                        if t == endWord:
                            return ans
                        q.append(t)
                        words.remove(t)
                    s[i] = ch
        return 0
```

```python
class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        def extend(m1, m2, q):
            for _ in range(len(q)):
                s = q.popleft()
                step = m1[s]
                s = list(s)
                for i in range(len(s)):
                    ch = s[i]
                    for j in range(26):
                        s[i] = chr(ord('a') + j)
                        t = ''.join(s)
                        if t in m1 or t not in words:
                            continue
                        if t in m2:
                            return step + 1 + m2[t]
                        m1[t] = step + 1
                        q.append(t)
                    s[i] = ch
            return -1

        words = set(wordList)
        if endWord not in words:
            return 0
        q1, q2 = deque([beginWord]), deque([endWord])
        m1, m2 = {beginWord: 0}, {endWord: 0}
        while q1 and q2:
            t = extend(m1, m2, q1) if len(q1) <= len(
                q2) else extend(m2, m1, q2)
            if t != -1:
                return t + 1
        return 0
```

### **Java**

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        int ans = 1;
        while (!q.isEmpty()) {
            ++ans;
            for (int i = q.size(); i > 0; --i) {
                String s = q.poll();
                char[] chars = s.toCharArray();
                for (int j = 0; j < chars.length; ++j) {
                    char ch = chars[j];
                    for (char k = 'a'; k <= 'z'; ++k) {
                        chars[j] = k;
                        String t = new String(chars);
                        if (!words.contains(t)) {
                            continue;
                        }
                        if (endWord.equals(t)) {
                            return ans;
                        }
                        q.offer(t);
                        words.remove(t);
                    }
                    chars[j] = ch;
                }
            }
        }
        return 0;
    }
}
```

```java
class Solution {
    private Set<String> words;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }
        Queue<String> q1 = new ArrayDeque<>();
        Queue<String> q2 = new ArrayDeque<>();
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        q1.offer(beginWord);
        q2.offer(endWord);
        m1.put(beginWord, 0);
        m2.put(endWord, 0);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1) : extend(m2, m1, q2);
            if (t != -1) {
                return t + 1;
            }
        }
        return 0;
    }

    private int extend(Map<String, Integer> m1, Map<String, Integer> m2, Queue<String> q) {
        for (int i = q.size(); i > 0; --i) {
            String s = q.poll();
            int step = m1.get(s);
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; ++j) {
                char ch = chars[j];
                for (char k = 'a'; k <= 'z'; ++k) {
                    chars[j] = k;
                    String t = new String(chars);
                    if (!words.contains(t) || m1.containsKey(t)) {
                        continue;
                    }
                    if (m2.containsKey(t)) {
                        return step + 1 + m2.get(t);
                    }
                    q.offer(t);
                    m1.put(t, step + 1);
                }
                chars[j] = ch;
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        unordered_set<string> words(wordList.begin(), wordList.end());
        queue<string> q {{beginWord}};
        int ans = 1;
        while (!q.empty()) {
            ++ans;
            for (int i = q.size(); i > 0; --i) {
                string s = q.front();
                q.pop();
                for (int j = 0; j < s.size(); ++j) {
                    char ch = s[j];
                    for (char k = 'a'; k <= 'z'; ++k) {
                        s[j] = k;
                        if (!words.count(s)) continue;
                        if (s == endWord) return ans;
                        q.push(s);
                        words.erase(s);
                    }
                    s[j] = ch;
                }
            }
        }
        return 0;
    }
};
```

```cpp
class Solution {
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        unordered_set<string> words(wordList.begin(), wordList.end());
        if (!words.count(endWord)) return 0;
        queue<string> q1 {{beginWord}};
        queue<string> q2 {{endWord}};
        unordered_map<string, int> m1;
        unordered_map<string, int> m2;
        m1[beginWord] = 0;
        m2[endWord] = 0;
        while (!q1.empty() && !q2.empty()) {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1, words) : extend(m2, m1, q2, words);
            if (t != -1) return t + 1;
        }
        return 0;
    }

    int extend(unordered_map<string, int>& m1, unordered_map<string, int>& m2, queue<string>& q, unordered_set<string>& words) {
        for (int i = q.size(); i > 0; --i) {
            string s = q.front();
            int step = m1[s];
            q.pop();
            for (int j = 0; j < s.size(); ++j) {
                char ch = s[j];
                for (char k = 'a'; k <= 'z'; ++k) {
                    s[j] = k;
                    if (!words.count(s) || m1.count(s)) continue;
                    if (m2.count(s)) return step + 1 + m2[s];
                    m1[s] = step + 1;
                    q.push(s);
                }
                s[j] = ch;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func ladderLength(beginWord string, endWord string, wordList []string) int {
	words := make(map[string]bool)
	for _, word := range wordList {
		words[word] = true
	}
	q := []string{beginWord}
	ans := 1
	for len(q) > 0 {
		ans++
		for i := len(q); i > 0; i-- {
			s := q[0]
			q = q[1:]
			chars := []byte(s)
			for j := 0; j < len(chars); j++ {
				ch := chars[j]
				for k := 'a'; k <= 'z'; k++ {
					chars[j] = byte(k)
					t := string(chars)
					if !words[t] {
						continue
					}
					if t == endWord {
						return ans
					}
					q = append(q, t)
					words[t] = false
				}
				chars[j] = ch
			}
		}
	}
	return 0
}
```

```go
func ladderLength(beginWord string, endWord string, wordList []string) int {
	words := make(map[string]bool)
	for _, word := range wordList {
		words[word] = true
	}
	if !words[endWord] {
		return 0
	}

	q1, q2 := []string{beginWord}, []string{endWord}
	m1, m2 := map[string]int{beginWord: 0}, map[string]int{endWord: 0}
	extend := func() int {
		for i := len(q1); i > 0; i-- {
			s := q1[0]
			step, _ := m1[s]
			q1 = q1[1:]
			chars := []byte(s)
			for j := 0; j < len(chars); j++ {
				ch := chars[j]
				for k := 'a'; k <= 'z'; k++ {
					chars[j] = byte(k)
					t := string(chars)
					if !words[t] {
						continue
					}
					if _, ok := m1[t]; ok {
						continue
					}
					if v, ok := m2[t]; ok {
						return step + 1 + v
					}
					q1 = append(q1, t)
					m1[t] = step + 1
				}
				chars[j] = ch
			}
		}
		return -1
	}
	for len(q1) > 0 && len(q2) > 0 {
		if len(q1) > len(q2) {
			m1, m2 = m2, m1
			q1, q2 = q2, q1
		}
		t := extend()
		if t != -1 {
			return t + 1
		}
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
