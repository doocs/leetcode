# [131. 分割回文串](https://leetcode.cn/problems/palindrome-partitioning)

[English Version](/solution/0100-0199/0131.Palindrome%20Partitioning/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>，请你将<em> </em><code>s</code><em> </em>分割成一些子串，使每个子串都是 <strong>回文串</strong> 。返回 <code>s</code> 所有可能的分割方案。</p>

<p><strong>回文串</strong> 是正着读和反着读都一样的字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aab"
<strong>输出：</strong>[["a","a","b"],["aa","b"]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a"
<strong>输出：</strong>[["a"]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 16</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

### 方法一：预处理 + DFS(回溯)

我们可以使用动态规划，预处理出字符串中的任意子串是否为回文串，即 $f[i][j]$ 表示子串 $s[i..j]$ 是否为回文串。

接下来，我们设计一个函数 $dfs(i)$，表示从字符串的第 $i$ 个字符开始，分割成若干回文串，当前分割方案为 $t$。

如果 $i=|s|$，说明已经分割完成，我们将 $t$ 放入答案数组中，然后返回。

否则，我们可以从 $i$ 开始，从小到大依次枚举结束位置 $j$，如果 $s[i..j]$ 是回文串，那么就把 $s[i..j]$ 加入到 $t$ 中，然后继续递归 $dfs(j+1)$，回溯的时候要弹出 $s[i..j]$。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(n^2)$。其中 $n$ 是字符串的长度。

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
