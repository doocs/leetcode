---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0320.Generalized%20Abbreviation/README.md
tags:
    - 位运算
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [320. 列举单词的全部缩写 🔒](https://leetcode.cn/problems/generalized-abbreviation)

[English Version](/solution/0300-0399/0320.Generalized%20Abbreviation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>单词的 <strong>广义缩写词</strong> 可以通过下述步骤构造：先取任意数量的 <strong>不重叠、不相邻</strong> 的<span data-keyword="substring-nonempty">子字符串</span>，再用它们各自的长度进行替换。</p>

<ul>
	<li>例如，<code>"abcde"</code> 可以缩写为：

    <ul>
    	<li><code>"a3e"</code>（<code>"bcd"</code> 变为 <code>"3"</code> ）</li>
    	<li><code>"1bcd1"</code>（<code>"a"</code> 和 <code>"e"</code> 都变为 <code>"1"</code>）<meta charset="UTF-8" /></li>
    	<li><code>"5"</code>&nbsp;(<code>"abcde"</code>&nbsp;变为&nbsp;<code>"5"</code>)</li>
    	<li><code>"abcde"</code>&nbsp;(没有子字符串被代替)</li>
    </ul>
    </li>
    <li>然而，这些缩写是 <strong>无效的</strong> ：
    <ul>
    	<li><code>"23"</code>（<code>"ab"</code> 变为 <code>"2"</code> ，<code>"cde"</code> 变为 <code>"3"</code> ）是无效的，因为被选择的字符串是相邻的</li>
    	<li><meta charset="UTF-8" /><code>"22de"</code>&nbsp;(<code>"ab"</code> 变为&nbsp;<code>"2"</code>&nbsp;，&nbsp;<code>"bc"</code>&nbsp;变为&nbsp;<code>"2"</code>) &nbsp;是无效的，因为被选择的字符串是重叠的</li>
    </ul>
    </li>

</ul>

<p>给你一个字符串&nbsp;<code>word</code> ，返回&nbsp;<em>一个由</em>&nbsp;<code>word</code> 的<em>所有可能 <strong>广义缩写词</strong> 组成的列表</em>&nbsp;。按 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "word"
<strong>输出：</strong>["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "a"
<strong>输出：</strong>["1","a"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 15</code></li>
	<li><code>word</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们设计一个函数 $dfs(i)$，表示对于字符串 $word[i:]$，返回其所有可能的缩写。

函数 $dfs(i)$ 的执行逻辑如下：

如果 $i \geq n$，说明已经处理完了字符串 $word$，直接返回一个空字符串组成的列表。

否则，我们可以选择保留 $word[i]$，然后对 $dfs(i + 1)$ 返回的列表中的每个字符串前面添加 $word[i]$，将得到的结果添加到答案中。

我们也可以选择删除 $word[i]$ 及其后面的若干个字符，假设我们删除了 $word[i..j)$，那么第 $j$ 个字符不删除，然后对 $dfs(j + 1)$ 返回的列表中的每个字符串前面添加 $j - i$，将得到的结果添加到答案中。

最后，我们在主函数中调用 $dfs(0)$ 即可。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $word$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generateAbbreviations(self, word: str) -> List[str]:
        def dfs(i: int) -> List[str]:
            if i >= n:
                return [""]
            ans = [word[i] + s for s in dfs(i + 1)]
            for j in range(i + 1, n + 1):
                for s in dfs(j + 1):
                    ans.append(str(j - i) + (word[j] if j < n else "") + s)
            return ans

        n = len(word)
        return dfs(0)
```

#### Java

```java
class Solution {
    private String word;
    private int n;

    public List<String> generateAbbreviations(String word) {
        this.word = word;
        n = word.length();
        return dfs(0);
    }

    private List<String> dfs(int i) {
        if (i >= n) {
            return List.of("");
        }
        List<String> ans = new ArrayList<>();
        for (String s : dfs(i + 1)) {
            ans.add(String.valueOf(word.charAt(i)) + s);
        }
        for (int j = i + 1; j <= n; ++j) {
            for (String s : dfs(j + 1)) {
                ans.add((j - i) + "" + (j < n ? String.valueOf(word.charAt(j)) : "") + s);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> generateAbbreviations(string word) {
        int n = word.size();
        function<vector<string>(int)> dfs = [&](int i) -> vector<string> {
            if (i >= n) {
                return {""};
            }
            vector<string> ans;
            for (auto& s : dfs(i + 1)) {
                string p(1, word[i]);
                ans.emplace_back(p + s);
            }
            for (int j = i + 1; j <= n; ++j) {
                for (auto& s : dfs(j + 1)) {
                    string p = j < n ? string(1, word[j]) : "";
                    ans.emplace_back(to_string(j - i) + p + s);
                }
            }
            return ans;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func generateAbbreviations(word string) []string {
	n := len(word)
	var dfs func(int) []string
	dfs = func(i int) []string {
		if i >= n {
			return []string{""}
		}
		ans := []string{}
		for _, s := range dfs(i + 1) {
			ans = append(ans, word[i:i+1]+s)
		}
		for j := i + 1; j <= n; j++ {
			for _, s := range dfs(j + 1) {
				p := ""
				if j < n {
					p = word[j : j+1]
				}
				ans = append(ans, strconv.Itoa(j-i)+p+s)
			}
		}
		return ans
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function generateAbbreviations(word: string): string[] {
    const n = word.length;
    const dfs = (i: number): string[] => {
        if (i >= n) {
            return [''];
        }
        const ans: string[] = [];
        for (const s of dfs(i + 1)) {
            ans.push(word[i] + s);
        }
        for (let j = i + 1; j <= n; ++j) {
            for (const s of dfs(j + 1)) {
                ans.push((j - i).toString() + (j < n ? word[j] : '') + s);
            }
        }
        return ans;
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：二进制枚举

由于字符串 $word$ 的长度不超过 $15$，因此我们可以使用二进制枚举的方法枚举所有的缩写。我们用一个长度为 $n$ 的二进制数 $i$ 表示一种缩写方式，其中 $0$ 表示保留对应的字符，而 $1$ 表示删除对应的字符。我们在 $[0, 2^n)$ 的范围内枚举所有 $i$，并将其转换成对应的缩写，添加到答案列表中即可。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $word$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generateAbbreviations(self, word: str) -> List[str]:
        n = len(word)
        ans = []
        for i in range(1 << n):
            cnt = 0
            s = []
            for j in range(n):
                if i >> j & 1:
                    cnt += 1
                else:
                    if cnt:
                        s.append(str(cnt))
                        cnt = 0
                    s.append(word[j])
            if cnt:
                s.append(str(cnt))
            ans.append("".join(s))
        return ans
```

#### Java

```java
class Solution {
    public List<String> generateAbbreviations(String word) {
        int n = word.length();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            StringBuilder s = new StringBuilder();
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    ++cnt;
                } else {
                    if (cnt > 0) {
                        s.append(cnt);
                        cnt = 0;
                    }
                    s.append(word.charAt(j));
                }
            }
            if (cnt > 0) {
                s.append(cnt);
            }
            ans.add(s.toString());
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> generateAbbreviations(string word) {
        int n = word.size();
        vector<string> ans;
        for (int i = 0; i < 1 << n; ++i) {
            string s;
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    ++cnt;
                } else {
                    if (cnt) {
                        s += to_string(cnt);
                        cnt = 0;
                    }
                    s.push_back(word[j]);
                }
            }
            if (cnt) {
                s += to_string(cnt);
            }
            ans.push_back(s);
        }
        return ans;
    }
};
```

#### Go

```go
func generateAbbreviations(word string) (ans []string) {
	n := len(word)
	for i := 0; i < 1<<n; i++ {
		s := &strings.Builder{}
		cnt := 0
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				cnt++
			} else {
				if cnt > 0 {
					s.WriteString(strconv.Itoa(cnt))
					cnt = 0
				}
				s.WriteByte(word[j])
			}
		}
		if cnt > 0 {
			s.WriteString(strconv.Itoa(cnt))
		}
		ans = append(ans, s.String())
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
