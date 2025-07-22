---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.07.Permutation%20I/README_EN.md
---

<!-- problem:start -->

# [08.07. Permutation I](https://leetcode.cn/problems/permutation-i-lcci)

[中文文档](/lcci/08.07.Permutation%20I/README.md)

## Description

<!-- description:start -->

<p>Write a method to compute all permutations of a string of unique characters.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: S = &quot;qwe&quot;

<strong> Output</strong>: [&quot;qwe&quot;, &quot;qew&quot;, &quot;wqe&quot;, &quot;weq&quot;, &quot;ewq&quot;, &quot;eqw&quot;]

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: S = &quot;ab&quot;

<strong> Output</strong>: [&quot;ab&quot;, &quot;ba&quot;]

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li>All characters are English letters.</li>
	<li><code>1 &lt;= S.length &lt;= 9</code></li>
</ol>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS (Backtracking)

We design a function $\textit{dfs}(i)$ to represent that the first $i$ positions have been filled, and now we need to fill the $(i+1)$-th position. Enumerate all possible characters, and if the character has not been used, fill in this character and continue to fill the next position until all positions are filled.

The time complexity is $O(n \times n!)$, where $n$ is the length of the string. There are $n!$ permutations in total, and each permutation takes $O(n)$ time to construct.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(i: int):
            if i >= n:
                ans.append("".join(t))
                return
            for j, c in enumerate(S):
                if not vis[j]:
                    vis[j] = True
                    t[i] = c
                    dfs(i + 1)
                    vis[j] = False

        ans = []
        n = len(S)
        vis = [False] * n
        t = list(S)
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private char[] s;
    private char[] t;
    private boolean[] vis;
    private List<String> ans = new ArrayList<>();

    public String[] permutation(String S) {
        s = S.toCharArray();
        int n = s.length;
        vis = new boolean[n];
        t = new char[n];
        dfs(0);
        return ans.toArray(new String[0]);
    }

    private void dfs(int i) {
        if (i >= s.length) {
            ans.add(new String(t));
            return;
        }
        for (int j = 0; j < s.length; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                t[i] = s[j];
                dfs(i + 1);
                vis[j] = false;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> permutation(string S) {
        int n = S.size();
        vector<bool> vis(n);
        string t = S;
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, int i) {
            if (i >= n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (!vis[j]) {
                    vis[j] = true;
                    t[i] = S[j];
                    dfs(i + 1);
                    vis[j] = false;
                }
            }
        };
        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func permutation(S string) (ans []string) {
	t := []byte(S)
	n := len(t)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i >= n {
			ans = append(ans, string(t))
			return
		}
		for j := range S {
			if !vis[j] {
				vis[j] = true
				t[i] = S[j]
				dfs(i + 1)
				vis[j] = false
			}
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function permutation(S: string): string[] {
    const n = S.length;
    const vis: boolean[] = Array(n).fill(false);
    const ans: string[] = [];
    const t: string[] = Array(n).fill('');
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j]) {
                continue;
            }
            vis[j] = true;
            t[i] = S[j];
            dfs(i + 1);
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {string} S
 * @return {string[]}
 */
var permutation = function (S) {
    const n = S.length;
    const vis = Array(n).fill(false);
    const ans = [];
    const t = Array(n).fill('');
    const dfs = i => {
        if (i >= n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j]) {
                continue;
            }
            vis[j] = true;
            t[i] = S[j];
            dfs(i + 1);
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
};
```

#### Swift

```swift
class Solution {
    func permutation(_ S: String) -> [String] {
        var ans: [String] = []
        let s = Array(S)
        var t = s
        var vis = Array(repeating: false, count: s.count)
        let n = s.count

        func dfs(_ i: Int) {
            if i >= n {
                ans.append(String(t))
                return
            }
            for j in 0..<n {
                if !vis[j] {
                    vis[j] = true
                    t[i] = s[j]
                    dfs(i + 1)
                    vis[j] = false
                }
            }
        }

        dfs(0)
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
