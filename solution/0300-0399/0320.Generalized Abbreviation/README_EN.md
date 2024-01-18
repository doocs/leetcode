# [320. Generalized Abbreviation](https://leetcode.com/problems/generalized-abbreviation)

[中文文档](/solution/0300-0399/0320.Generalized%20Abbreviation/README.md)

## Description

<p>A word&#39;s <strong>generalized abbreviation</strong> can be constructed by taking any number of <strong>non-overlapping</strong> and <strong>non-adjacent</strong> <span data-keyword="substring-nonempty">substrings</span> and replacing them with their respective lengths.</p>

<ul>
	<li>For example, <code>&quot;abcde&quot;</code> can be abbreviated into:
    <ul>
    	<li><code>&quot;a3e&quot;</code> (<code>&quot;bcd&quot;</code> turned into <code>&quot;3&quot;</code>)</li>
    	<li><code>&quot;1bcd1&quot;</code> (<code>&quot;a&quot;</code> and <code>&quot;e&quot;</code> both turned into <code>&quot;1&quot;</code>)</li>
    	<li><code>&quot;5&quot;</code> (<code>&quot;abcde&quot;</code> turned into <code>&quot;5&quot;</code>)</li>
    	<li><code>&quot;abcde&quot;</code> (no substrings replaced)</li>
    </ul>
    </li>
    <li>However, these abbreviations are <strong>invalid</strong>:
    <ul>
    	<li><code>&quot;23&quot;</code> (<code>&quot;ab&quot;</code> turned into <code>&quot;2&quot;</code> and <code>&quot;cde&quot;</code> turned into <code>&quot;3&quot;</code>) is invalid as the substrings chosen are adjacent.</li>
    	<li><code>&quot;22de&quot;</code> (<code>&quot;ab&quot;</code> turned into <code>&quot;2&quot;</code> and <code>&quot;bc&quot;</code> turned into <code>&quot;2&quot;</code>) is invalid as the substring chosen overlap.</li>
    </ul>
    </li>
</ul>

<p>Given a string <code>word</code>, return <em>a list of all the possible <strong>generalized abbreviations</strong> of</em> <code>word</code>. Return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> word = "word"
<strong>Output:</strong> ["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> word = "a"
<strong>Output:</strong> ["1","a"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 15</code></li>
	<li><code>word</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: DFS

We design a function $dfs(i)$, which returns all possible abbreviations for the string $word[i:]$.

The execution logic of the function $dfs(i)$ is as follows:

If $i \geq n$, it means that the string $word$ has been processed, and we directly return a list composed of an empty string.

Otherwise, we can choose to keep $word[i]$, and then add $word[i]$ to the front of each string in the list returned by $dfs(i + 1)$, and add the obtained result to the answer.

We can also choose to delete $word[i]$ and some characters after it. Suppose we delete $word[i..j)$, then the $j$ th character is not deleted, and then add $j - i$ to the front of each string in the list returned by $dfs(j + 1)$, and add the obtained result to the answer.

Finally, we call $dfs(0)$ in the main function.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string $word$.

<!-- tabs:start -->

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

### Solution 2: Binary Enumeration

Since the length of the string $word$ does not exceed $15$, we can use the method of binary enumeration to enumerate all abbreviations. We use a binary number $i$ of length $n$ to represent an abbreviation, where $0$ represents keeping the corresponding character, and $1$ represents deleting the corresponding character. We enumerate all $i$ in the range of $[0, 2^n)$, convert it into the corresponding abbreviation, and add it to the answer list.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string $word$.

<!-- tabs:start -->

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

<!-- end -->
