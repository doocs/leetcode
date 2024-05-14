---
comment: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.22.Word%20Transformer/README.md
---

# [面试题 17.22. 单词转换](https://leetcode.cn/problems/word-transformer-lcci)

[English Version](/lcci/17.22.Word%20Transformer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。</p>

<p>编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong>
beginWord = &quot;hit&quot;,
endWord = &quot;cog&quot;,
wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]

<strong>输出:</strong>
[&quot;hit&quot;,&quot;hot&quot;,&quot;dot&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong>
beginWord = &quot;hit&quot;
endWord = &quot;cog&quot;
wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;]

<strong>输出: </strong>[]

<strong>解释:</strong>&nbsp;<em>endWord</em> &quot;cog&quot; 不在字典中，所以不存在符合要求的转换序列。</pre>

## 解法

### 方法一：DFS

我们定义一个答案数组 $\textit{ans}$，初始时只包含 $\textit{beginWord}$。然后我们定义一个数组 $\textit{vis}$，用来标记 $\textit{wordList}$ 中的单词是否被访问过。

接下来，我们设计一个函数 $\text{dfs}(s)$，表示从 $\textit{s}$ 出发，尝试将 $\textit{s}$ 转换为 $\textit{endWord}$，是否能够成功。如果能够成功，返回 $\text{True}$，否则返回 $\text{False}$。

函数 $\text{dfs}(s)$ 的具体实现如下：

1. 如果 $\textit{s}$ 等于 $\textit{endWord}$，说明转换成功，返回 $\text{True}$；
2. 否则，我们遍历 $\textit{wordList}$ 中的每个单词 $\textit{t}$，如果 $\textit{t}$ 没有被访问过且 $\textit{s}$ 和 $\textit{t}$ 之间只有一个字符不同，那么我们将 $\textit{t}$ 标记为已访问，并将 $\textit{t}$ 加入到 $\textit{ans}$ 中，然后递归调用 $\text{dfs}(t)$，如果返回 $\text{True}$，说明转换成功，我们返回 $\text{True}$，否则我们将 $\textit{t}$ 从 $\textit{ans}$ 中移除，继续遍历下一个单词；
3. 如果遍历完 $\textit{wordList}$ 中的所有单词都没有找到可以转换的单词，说明转换失败，我们返回 $\text{False}$。

最后，我们调用 $\text{dfs}(\textit{beginWord})$，如果返回 $\text{True}$，说明转换成功，我们返回 $\textit{ans}$，否则返回空数组。

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
