---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0131.Palindrome%20Partitioning/README_EN.md
tags:
    - String
    - Dynamic Programming
    - Backtracking
---

# [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning)

[中文文档](/solution/0100-0199/0131.Palindrome%20Partitioning/README.md)

## Description

<p>Given a string <code>s</code>, partition <code>s</code> such that every <span data-keyword="substring-nonempty">substring</span> of the partition is a <span data-keyword="palindrome-string"><strong>palindrome</strong></span>. Return <em>all possible palindrome partitioning of </em><code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "aab"
<strong>Output:</strong> [["a","a","b"],["aa","b"]]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "a"
<strong>Output:</strong> [["a"]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 16</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def partition(self, s: str) -> List[List[str]]:
        def dfs(i: int):
            if i == n:
                ans.append(t[:])
                return
            for j in range(i, n):
                if f[i][j]:
                    t.append(s[i : j + 1])
                    dfs(j + 1)
                    t.pop()

        n = len(s)
        f = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = s[i] == s[j] and f[i + 1][j - 1]
        ans = []
        t = []
        dfs(0)
        return ans
```

```java
class Solution {
    private int n;
    private String s;
    private boolean[][] f;
    private List<String> t = new ArrayList<>();
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = s.charAt(i) == s.charAt(j) && f[i + 1][j - 1];
            }
        }
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                t.add(s.substring(i, j + 1));
                dfs(j + 1);
                t.remove(t.size() - 1);
            }
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<string>> partition(string s) {
        int n = s.size();
        bool f[n][n];
        memset(f, true, sizeof(f));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = s[i] == s[j] && f[i + 1][j - 1];
            }
        }
        vector<vector<string>> ans;
        vector<string> t;
        function<void(int)> dfs = [&](int i) {
            if (i == n) {
                ans.push_back(t);
                return;
            }
            for (int j = i; j < n; ++j) {
                if (f[i][j]) {
                    t.push_back(s.substr(i, j - i + 1));
                    dfs(j + 1);
                    t.pop_back();
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

```go
func partition(s string) (ans [][]string) {
	n := len(s)
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, n)
		for j := range f[i] {
			f[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = s[i] == s[j] && f[i+1][j-1]
		}
	}
	t := []string{}
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans = append(ans, append([]string(nil), t...))
			return
		}
		for j := i; j < n; j++ {
			if f[i][j] {
				t = append(t, s[i:j+1])
				dfs(j + 1)
				t = t[:len(t)-1]
			}
		}
	}
	dfs(0)
	return
}
```

```ts
function partition(s: string): string[][] {
    const n = s.length;
    const f: boolean[][] = new Array(n).fill(0).map(() => new Array(n).fill(true));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = s[i] === s[j] && f[i + 1][j - 1];
        }
    }
    const ans: string[][] = [];
    const t: string[] = [];
    const dfs = (i: number) => {
        if (i === n) {
            ans.push(t.slice());
            return;
        }
        for (let j = i; j < n; ++j) {
            if (f[i][j]) {
                t.push(s.slice(i, j + 1));
                dfs(j + 1);
                t.pop();
            }
        }
    };
    dfs(0);
    return ans;
}
```

```cs
public class Solution {
    private int n;
    private string s;
    private bool[,] f;
    private IList<IList<string>> ans = new List<IList<string>>();
    private IList<string> t = new List<string>();

    public IList<IList<string>> Partition(string s) {
        n = s.Length;
        this.s = s;
        f = new bool[n, n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                f[i, j] = true;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i, j] = s[i] == s[j] && f[i + 1, j - 1];
            }
        }
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            ans.Add(new List<string>(t));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i, j]) {
                t.Add(s.Substring(i, j + 1 - i));
                dfs(j + 1);
                t.RemoveAt(t.Count - 1);
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
