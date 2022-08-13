# [17.22. Word Transformer](https://leetcode.cn/problems/word-transformer-lcci)

[中文文档](/lcci/17.22.Word%20Transformer/README.md)

## Description

<p>Given two words of equal length that are in a dictionary, write a method to transform one word into another word by changing only one letter at a time. The new word you get in each step must be in the dictionary.</p>

<p>Write code to return a possible transforming sequence. If there are more that one sequence, any one is ok.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong>

beginWord = &quot;hit&quot;,

endWord = &quot;cog&quot;,

wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]



<strong>Output:</strong>

[&quot;hit&quot;,&quot;hot&quot;,&quot;dot&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong>

beginWord = &quot;hit&quot;

endWord = &quot;cog&quot;

wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;]



<strong>Output: </strong>[]



<strong>Explanation:</strong>&nbsp;<em>endWord</em> &quot;cog&quot; is not in the dictionary, so there&#39;s no possible transforming sequence.</pre>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLadders(
        self, beginWord: str, endWord: str, wordList: List[str]
    ) -> List[str]:
        def check(a, b):
            return sum(a[i] != b[i] for i in range(len(a))) == 1

        def dfs(begin, end, t):
            nonlocal ans
            if ans:
                return
            if begin == end:
                ans = t.copy()
                return
            for word in wordList:
                if word in visited or not check(begin, word):
                    continue
                visited.add(word)
                t.append(word)
                dfs(word, end, t)
                t.pop()

        ans = []
        visited = set()
        dfs(beginWord, endWord, [beginWord])
        return ans
```

### **Java**

```java
class Solution {
    private List<String> words;
    private List<String> ans;
    private Set<String> visited;

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        words = wordList;
        ans = new ArrayList<>();
        visited = new HashSet<>();
        List<String> t = new ArrayList<>();
        t.add(beginWord);
        dfs(beginWord, endWord, t);
        return ans;
    }

    private void dfs(String begin, String end, List<String> t) {
        if (!ans.isEmpty()) {
            return;
        }
        if (Objects.equals(begin, end)) {
            ans = new ArrayList<>(t);
            return;
        }
        for (String word : words) {
            if (visited.contains(word) || !check(begin, word)) {
                continue;
            }
            t.add(word);
            visited.add(word);
            dfs(word, end, t);
            t.remove(t.size() - 1);
        }
    }

    private boolean check(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> words;
    vector<string> ans;
    unordered_set<string> visited;

    vector<string> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        this->words = wordList;
        ans.resize(0);
        vector<string> t;
        t.push_back(beginWord);
        dfs(beginWord, endWord, t);
        return ans;
    }

    void dfs(string begin, string end, vector<string>& t) {
        if (!ans.empty()) return;
        if (begin == end) {
            ans = t;
            return;
        }
        for (auto word : words) {
            if (visited.count(word) || !check(begin, word)) continue;
            visited.insert(word);
            t.push_back(word);
            dfs(word, end, t);
            t.pop_back();
        }
    }

    bool check(string a, string b) {
        if (a.size() != b.size()) return false;
        int cnt = 0;
        for (int i = 0; i < a.size(); ++i)
            if (a[i] != b[i]) ++cnt;
        return cnt == 1;
    }
};
```

### **Go**

```go
func findLadders(beginWord string, endWord string, wordList []string) []string {
	var ans []string
	visited := make(map[string]bool)

	check := func(a, b string) bool {
		if len(a) != len(b) {
			return false
		}
		cnt := 0
		for i := 0; i < len(a); i++ {
			if a[i] != b[i] {
				cnt++
			}
		}
		return cnt == 1
	}

	var dfs func(begin, end string, t []string)
	dfs = func(begin, end string, t []string) {
		if len(ans) > 0 {
			return
		}
		if begin == end {
			ans = make([]string, len(t))
			copy(ans, t)
			return
		}
		for _, word := range wordList {
			if visited[word] || !check(begin, word) {
				continue
			}
			t = append(t, word)
			visited[word] = true
			dfs(word, end, t)
			t = t[:len(t)-1]
		}
	}

	var t []string
	t = append(t, beginWord)
	dfs(beginWord, endWord, t)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
