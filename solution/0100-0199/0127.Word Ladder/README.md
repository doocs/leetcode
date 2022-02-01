# [127. 单词接龙](https://leetcode-cn.com/problems/word-ladder)

[English Version](/solution/0100-0199/0127.Word%20Ladder/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字典 <code>wordList</code> 中从单词 <code>beginWord</code><em> </em>和 <code>endWord</code> 的 <strong>转换序列 </strong>是一个按下述规格形成的序列：</p>

<ul>
	<li>序列中第一个单词是 <code>beginWord</code> 。</li>
	<li>序列中最后一个单词是 <code>endWord</code> 。</li>
	<li>每次转换只能改变一个字母。</li>
	<li>转换过程中的中间单词必须是字典 <code>wordList</code> 中的单词。</li>
</ul>

<p>给你两个单词<em> </em><code>beginWord</code><em> </em>和 <code>endWord</code> 和一个字典 <code>wordList</code> ，找到从 <code>beginWord</code> 到 <code>endWord</code> 的 <strong>最短转换序列</strong> 中的 <strong>单词数目</strong> 。如果不存在这样的转换序列，返回 0。</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
<strong>输出：</strong>5
<strong>解释：</strong>一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
<strong>输出：</strong>0
<strong>解释：</strong>endWord "cog" 不在字典中，所以无法进行转换。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= beginWord.length <= 10</code></li>
	<li><code>endWord.length == beginWord.length</code></li>
	<li><code>1 <= wordList.length <= 5000</code></li>
	<li><code>wordList[i].length == beginWord.length</code></li>
	<li><code>beginWord</code>、<code>endWord</code> 和 <code>wordList[i]</code> 由小写英文字母组成</li>
	<li><code>beginWord != endWord</code></li>
	<li><code>wordList</code> 中的所有字符串 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        words = set(wordList)
        q = deque([beginWord])
        ans = 1
        while q:
            n = len(q)
            for _ in range(n):
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
                            return ans + 1
                        q.append(t)
                        words.remove(t)
                    s[i] = ch
            ans += 1
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int ladderLength(
        String beginWord,
        String endWord,
        List<String> wordList
    ) {
        Set<String> words = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int ans = 1;
        while (!q.isEmpty()) {
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
                            return ans + 1;
                        }
                        q.offer(t);
                        words.remove(t);
                    }
                    chars[j] = ch;
                }
            }
            ++ans;
        }
        return 0;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        unordered_set<string> words(wordList.begin(), wordList.end());
        queue<string> q{{beginWord}};
        int ans = 1;
        while (!q.empty())
        {
            for (int i = q.size(); i > 0; --i)
            {
                string s = q.front();
                q.pop();
                for (int j = 0; j < s.size(); ++j)
                {
                    char ch = s[j];
                    for (char k = 'a'; k <= 'z'; ++k)
                    {
                        s[j] = k;
                        if (!words.count(s)) continue;
                        if (s == endWord) return ans + 1;
                        q.push(s);
                        words.erase(s);
                    }
                    s[j] = ch;
                }
            }
            ++ans;
        }
        return 0;
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
						return ans + 1
					}
					q = append(q, t)
					words[t] = false
				}
				chars[j] = ch
			}
		}
		ans++
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
