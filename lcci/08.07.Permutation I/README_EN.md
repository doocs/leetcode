---
comment: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.07.Permutation%20I/README_EN.md
---

# [08.07. Permutation I](https://leetcode.cn/problems/permutation-i-lcci)

[中文文档](/lcci/08.07.Permutation%20I/README.md)

## Description

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

## Solutions

### Solution 1: DFS (Backtracking)

We design a function $dfs(i)$ to represent that the first $i$ positions have been filled, and now the $i+1$ position needs to be filled. We enumerate all possible characters, if this character has not been filled, we fill in this character, and then continue to fill the next position until all positions are filled.

The time complexity is $O(n \times n!)$, where $n$ is the length of the string. There are $n!$ permutations in total, and each permutation takes $O(n)$ time to construct.

<!-- tabs:start -->

```python
class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(i: int):
            if i == n:
                ans.append("".join(t))
                return
            for j, c in enumerate(S):
                if vis[j]:
                    continue
                vis[j] = True
                t.append(c)
                dfs(i + 1)
                t.pop()
                vis[j] = False

        n = len(S)
        vis = [False] * n
        ans = []
        t = []
        dfs(0)
        return ans
```

```java
class Solution {
    private char[] s;
    private boolean[] vis = new boolean['z' + 1];
    private List<String> ans = new ArrayList<>();
    private StringBuilder t = new StringBuilder();

    public String[] permutation(String S) {
        s = S.toCharArray();
        dfs(0);
        return ans.toArray(new String[0]);
    }

    private void dfs(int i) {
        if (i == s.length) {
            ans.add(t.toString());
            return;
        }
        for (char c : s) {
            if (vis[c]) {
                continue;
            }
            vis[c] = true;
            t.append(c);
            dfs(i + 1);
            t.deleteCharAt(t.length() - 1);
            vis[c] = false;
        }
    }
}
```

```cpp
class Solution {
public:
    vector<string> permutation(string S) {
        int n = S.size();
        vector<bool> vis(n);
        vector<string> ans;
        string t;
        function<void(int)> dfs = [&](int i) {
            if (i >= n) {
                ans.push_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (vis[j]) {
                    continue;
                }
                vis[j] = true;
                t.push_back(S[j]);
                dfs(i + 1);
                t.pop_back();
                vis[j] = false;
            }
        };
        dfs(0);
        return ans;
    }
};
```

```go
func permutation(S string) (ans []string) {
	t := []byte{}
	vis := make([]bool, len(S))
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(S) {
			ans = append(ans, string(t))
			return
		}
		for j := range S {
			if vis[j] {
				continue
			}
			vis[j] = true
			t = append(t, S[j])
			dfs(i + 1)
			t = t[:len(t)-1]
			vis[j] = false
		}
	}
	dfs(0)
	return
}
```

```ts
function permutation(S: string): string[] {
    const n = S.length;
    const vis: boolean[] = Array(n).fill(false);
    const ans: string[] = [];
    const t: string[] = [];
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
            t.push(S[j]);
            dfs(i + 1);
            t.pop();
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
}
```

```js
/**
 * @param {string} S
 * @return {string[]}
 */
var permutation = function (S) {
    const n = S.length;
    const vis = Array(n).fill(false);
    const ans = [];
    const t = [];
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
            t.push(S[j]);
            dfs(i + 1);
            t.pop();
            vis[j] = false;
        }
    };
    dfs(0);
    return ans;
};
```

```swift
class Solution {
    private var s: [Character] = []
    private var vis: [Bool] = Array(repeating: false, count: 128)
    private var ans: [String] = []
    private var t: String = ""

    func permutation(_ S: String) -> [String] {
        s = Array(S)
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        if i == s.count {
            ans.append(t)
            return
        }
        for c in s {
            let index = Int(c.asciiValue!)
            if vis[index] {
                continue
            }
            vis[index] = true
            t.append(c)
            dfs(i + 1)
            t.removeLast()
            vis[index] = false
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
