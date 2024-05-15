---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.22.Word%20Transformer/README_EN.md
---

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

### Solution 1: DFS

We define an answer array `ans`, initially containing only `beginWord`. Then we define an array `vis` to mark whether the words in `wordList` have been visited.

Next, we design a function `dfs(s)`, which represents whether we can successfully convert `s` to `endWord` starting from `s`. If successful, return `True`, otherwise return `False`.

The specific implementation of the function `dfs(s)` is as follows:

1. If `s` equals `endWord`, the conversion is successful, return `True`;
2. Otherwise, we traverse each word `t` in `wordList`. If `t` has not been visited and there is only one different character between `s` and `t`, then we mark `t` as visited, add `t` to `ans`, and then recursively call `dfs(t)`. If `True` is returned, the conversion is successful, we return `True`, otherwise we remove `t` from `ans` and continue to traverse the next word;
3. If all the words in `wordList` have been traversed and no convertible word is found, the conversion fails, we return `False`.

Finally, we call `dfs(beginWord)`. If `True` is returned, the conversion is successful, we return `ans`, otherwise return an empty array.

<!-- tabs:start -->

```python
class Solution:
    def findLadders(
        self, beginWord: str, endWord: str, wordList: List[str]
    ) -> List[str]:
        def check(s: str, t: str) -> bool:
            return len(s) == len(t) and sum(a != b for a, b in zip(s, t)) == 1

        def dfs(s: str) -> bool:
            if s == endWord:
                return True
            for i, t in enumerate(wordList):
                if not vis[i] and check(s, t):
                    vis[i] = True
                    ans.append(t)
                    if dfs(t):
                        return True
                    ans.pop()
            return False

        ans = [beginWord]
        vis = [False] * len(wordList)
        return ans if dfs(beginWord) else []
```

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private List<String> wordList;
    private String endWord;
    private boolean[] vis;

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        this.wordList = wordList;
        this.endWord = endWord;
        ans.add(beginWord);
        vis = new boolean[wordList.size()];
        return dfs(beginWord) ? ans : List.of();
    }

    private boolean dfs(String s) {
        if (s.equals(endWord)) {
            return true;
        }
        for (int i = 0; i < wordList.size(); ++i) {
            String t = wordList.get(i);
            if (vis[i] || !check(s, t)) {
                continue;
            }
            vis[i] = true;
            ans.add(t);
            if (dfs(t)) {
                return true;
            }
            ans.remove(ans.size() - 1);
        }
        return false;
    }

    private boolean check(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
```

```cpp
class Solution {
public:
    vector<string> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        this->endWord = move(endWord);
        this->wordList = move(wordList);
        vis.resize(this->wordList.size(), false);
        ans.push_back(beginWord);
        if (dfs(beginWord)) {
            return ans;
        }
        return {};
    }

private:
    vector<string> ans;
    vector<bool> vis;
    string endWord;
    vector<string> wordList;

    bool check(string& s, string& t) {
        if (s.size() != t.size()) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < s.size(); ++i) {
            cnt += s[i] != t[i];
        }
        return cnt == 1;
    }

    bool dfs(string& s) {
        if (s == endWord) {
            return true;
        }
        for (int i = 0; i < wordList.size(); ++i) {
            string& t = wordList[i];
            if (!vis[i] && check(s, t)) {
                vis[i] = true;
                ans.push_back(t);
                if (dfs(t)) {
                    return true;
                }
                ans.pop_back();
            }
        }
        return false;
    }
};
```

```go
func findLadders(beginWord string, endWord string, wordList []string) []string {
	ans := []string{beginWord}
	vis := make([]bool, len(wordList))
	check := func(s, t string) bool {
		if len(s) != len(t) {
			return false
		}
		cnt := 0
		for i := range s {
			if s[i] != t[i] {
				cnt++
			}
		}
		return cnt == 1
	}
	var dfs func(s string) bool
	dfs = func(s string) bool {
		if s == endWord {
			return true
		}
		for i, t := range wordList {
			if !vis[i] && check(s, t) {
				vis[i] = true
				ans = append(ans, t)
				if dfs(t) {
					return true
				}
				ans = ans[:len(ans)-1]
			}
		}
		return false
	}
	if dfs(beginWord) {
		return ans
	}
	return []string{}
}
```

```ts
function findLadders(beginWord: string, endWord: string, wordList: string[]): string[] {
    const ans: string[] = [beginWord];
    const vis: boolean[] = Array(wordList.length).fill(false);
    const check = (s: string, t: string): boolean => {
        if (s.length !== t.length) {
            return false;
        }
        let cnt = 0;
        for (let i = 0; i < s.length; ++i) {
            if (s[i] !== t[i]) {
                ++cnt;
            }
        }
        return cnt === 1;
    };
    const dfs = (s: string): boolean => {
        if (s === endWord) {
            return true;
        }
        for (let i = 0; i < wordList.length; ++i) {
            const t: string = wordList[i];
            if (!vis[i] && check(s, t)) {
                vis[i] = true;
                ans.push(t);
                if (dfs(t)) {
                    return true;
                }
                ans.pop();
            }
        }
        return false;
    };
    return dfs(beginWord) ? ans : [];
}
```

<!-- tabs:end -->

<!-- end -->
