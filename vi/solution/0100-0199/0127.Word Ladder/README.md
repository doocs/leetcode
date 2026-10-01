---
comments: true
difficulty: Hard
tags:
    - Breadth-First Search
    - Hash Table
    - String
    - Bidirectional Search
---

<!-- problem:start -->

# [127. Word Ladder](https://leetcode.com/problems/word-ladder)

[中文文档](/solution/0100-0199/0127.Word%20Ladder/README.md)

## Mô tả

<!-- description:start -->

<p><strong>Chuỗi chuyển đổi</strong> từ từ <code>beginWord</code> đến từ <code>endWord</code> sử dụng từ điển <code>wordList</code> là một dãy từ <code>beginWord -&gt; s<sub>1</sub> -&gt; s<sub>2</sub> -&gt; ... -&gt; s<sub>k</sub></code> sao cho:</p>

<ul>
	<li>Mỗi cặp từ liền kề khác nhau đúng một chữ cái.</li>
	<li>Mọi <code>s<sub>i</sub></code> với <code>1 &lt;= i &lt;= k</code> đều thuộc <code>wordList</code>. Lưu ý rằng <code>beginWord</code> không nhất thiết phải thuộc <code>wordList</code>.</li>
	<li><code>s<sub>k</sub> == endWord</code></li>
</ul>

<p>Cho hai từ <code>beginWord</code> và <code>endWord</code>, cùng một từ điển <code>wordList</code>, hãy trả về <em><strong>số lượng từ</strong> trong <strong>chuỗi chuyển đổi ngắn nhất</strong> từ</em> <code>beginWord</code> <em>đến</em> <code>endWord</code><em>, hoặc </em><code>0</code><em> nếu không tồn tại chuỗi như vậy.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]
<strong>Đầu ra:</strong> 5
<strong>Giải thích:</strong> Một chuỗi chuyển đổi ngắn nhất là &quot;hit&quot; -&gt; &quot;hot&quot; -&gt; &quot;dot&quot; -&gt; &quot;dog&quot; -&gt; cog&quot;, có độ dài 5 từ.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;]
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> Từ endWord "cog" không có trong wordList, do đó không tồn tại chuỗi chuyển đổi hợp lệ.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= beginWord.length &lt;= 10</code></li>
	<li><code>endWord.length == beginWord.length</code></li>
	<li><code>1 &lt;= wordList.length &lt;= 5000</code></li>
	<li><code>wordList[i].length == beginWord.length</code></li>
	<li><code>beginWord</code>, <code>endWord</code> và <code>wordList[i]</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
	<li><code>beginWord != endWord</code></li>
	<li>Tất cả các từ trong <code>wordList</code> đều <strong>duy nhất</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: BFS

<!-- thinking:start -->

> **Tư duy**
>
> Tìm chuỗi chuyển đổi ngắn nhất; mỗi bước thay đổi một chữ cái và phải vẫn nằm trong danh sách từ. Với tối đa $5000$ từ có độ dài $10$, việc liệt kê các đường đi là bất khả thi. Đường đi ngắn nhất không trọng số là BFS: thử $26$ chữ cái ở mỗi vị trí, đưa các từ tìm được vào hàng đợi và xóa chúng khỏi tập để không truy cập chúng hai lần.

<!-- thinking:end -->

Mô hình bước tối thiểu bằng BFS. Bài toán này có thể được giải bằng BFS đơn giản, hoặc được tối ưu bằng BFS hai chiều để giảm không gian tìm kiếm và cải thiện hiệu năng.

BFS hai chiều là một phương pháp tối ưu phổ biến cho BFS, với các ý tưởng triển khai chính như sau:

1. Tạo hai hàng đợi, q1 và q2, lần lượt cho các hướng tìm kiếm "start -> end" và "end -> start".
2. Tạo hai hash map, m1 và m2, để ghi lại các nút đã truy cập và số lần mở rộng tương ứng của chúng.
3. Trong mỗi lần tìm kiếm, ưu tiên mở rộng hàng đợi có ít phần tử hơn. Nếu tìm thấy một nút đã được phía tìm kiếm kia truy cập trong quá trình mở rộng, điều đó có nghĩa là đã tìm thấy đường đi ngắn nhất.
4. Nếu một trong hai hàng đợi rỗng, điều đó có nghĩa là việc tìm kiếm theo hướng hiện tại không thể tiếp tục, cho thấy điểm bắt đầu và điểm kết thúc không liên thông, và không cần tiếp tục tìm kiếm.

```python
while q1 and q2:
    if len(q1) <= len(q2):
        # Prioritize the queue with fewer elements for expansion
        extend(m1, m2, q1)
    else:
        extend(m2, m1, q2)


def extend(m1, m2, q):
    # New round of expansion
    for _ in range(len(q)):
        p = q.popleft()
        step = m1[p]
        for t in next(p):
            if t in m1:
                # Already visited before
                continue
            if t in m2:
                # The other direction has been searched, indicating that a shortest path has been found
                return step + 1 + m2[t]
            q.append(t)
            m1[t] = step + 1
```

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        unordered_set<string> words(wordList.begin(), wordList.end());
        queue<string> q{{beginWord}};
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

#### Go

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

#### C#

```cs
using System.Collections;

public class Solution {
    public int LadderLength(string beginWord, string endWord, IList<string> wordList) {
        var words = Enumerable.Repeat(beginWord, 1).Concat(wordList).Select((word, i) => new { Word = word, Index = i }).ToList();
        var endWordIndex = words.Find(w => w.Word == endWord)?.Index;
        if (endWordIndex == null) {
            return 0;
        }

        var paths = new List<int>[words.Count];
        for (var i = 0; i < paths.Length; ++i) {
            paths[i] = new List<int>();
        }
        for (var i = 0; i < beginWord.Length; ++i) {
            var hashMap = new Hashtable();
            foreach (var item in words) {
                var newWord = string.Format("{0}_{1}", item.Word.Substring(0, i), item.Word.Substring(i + 1));
                List<int> similars;
                if (!hashMap.ContainsKey(newWord)) {
                    similars = new List<int>();
                    hashMap.Add(newWord, similars);
                }
                else {
                    similars = (List<int>)hashMap[newWord];
                }
                foreach (var similar in similars) {
                    paths[similar].Add(item.Index);
                    paths[item.Index].Add(similar);
                }
                similars.Add(item.Index);
            }
        }

        var left = words.Count - 1;
        var lastRound = new List<int> { 0 };
        var visited = new bool[words.Count];
        visited[0] = true;
        for (var result = 2; left > 0; ++result) {
            var thisRound = new List<int>();
            foreach (var index in lastRound) {
                foreach (var next in paths[index]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        if (next == endWordIndex) return result;
                        thisRound.Add(next);
                    }
                }
            }
            if (thisRound.Count == 0) break;
            lastRound = thisRound;
        }

        return 0;
    }
}
```

#### TypeScript

```ts
function ladderLength(beginWord: string, endWord: string, wordList: string[]): number {
    if (!wordList.includes(endWord)) return 0;

    const replace = (s: string, i: number, ch: string) => s.slice(0, i) + ch + s.slice(i + 1);
    const { length } = beginWord;
    const words: Record<string, string[]> = {};
    const g: Record<string, string[]> = {};

    for (const w of [beginWord, ...wordList]) {
        const derivatives: string[] = [];

        for (let i = 0; i < length; i++) {
            const nextW = replace(w, i, '*');
            derivatives.push(nextW);

            g[nextW] ??= [];
            g[nextW].push(w);
        }

        words[w] = derivatives;
    }

    let ans = 0;
    let q = words[beginWord];
    const vis = new Set<string>([beginWord]);

    while (q.length) {
        const nextQ: string[] = [];
        ans++;

        for (const variant of q) {
            for (const w of g[variant]) {
                if (w === endWord) return ans + 1;

                if (vis.has(w)) continue;
                vis.add(w);

                nextQ.push(...words[w]);
            }
        }

        q = nextQ;
    }

    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: BFS hai chiều

<!-- thinking:start -->

> **Tư duy**
>
> Biên tìm kiếm của Lời giải 1 có thể mở rộng đáng kể trên một danh sách lớn. Mở rộng từ cả hai đầu và luôn mở rộng phía nhỏ hơn sẽ gặp nhau ở giữa tại độ dài ngắn nhất, đồng thời thông thường có không gian tìm kiếm nhỏ hơn.

<!-- thinking:end -->

Tìm kiếm từ cả hai đầu cho đến khi hai phía gặp nhau.

<!-- tabs:start -->

#### Python3

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
            t = extend(m1, m2, q1) if len(q1) <= len(q2) else extend(m2, m1, q2)
            if t != -1:
                return t + 1
        return 0
```

#### Java

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

#### C++

```cpp
class Solution {
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        unordered_set<string> words(wordList.begin(), wordList.end());
        if (!words.count(endWord)) return 0;
        queue<string> q1{{beginWord}};
        queue<string> q2{{endWord}};
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

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
