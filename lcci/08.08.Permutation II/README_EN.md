---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.08.Permutation%20II/README_EN.md
---

<!-- problem:start -->

# [08.08. Permutation II](https://leetcode.cn/problems/permutation-ii-lcci)

[中文文档](/lcci/08.08.Permutation%20II/README.md)

## Description

<!-- description:start -->

<p>Write a method to compute all permutations of a string whose charac&shy; ters are not necessarily unique. The list of permutations should not have duplicates.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: S = &quot;qqe&quot;

<strong> Output</strong>: [&quot;eqq&quot;,&quot;qeq&quot;,&quot;qqe&quot;]

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

### Solution 1: Sorting + Backtracking

We can first sort the string by characters, so that duplicate characters are placed together, making it easier to remove duplicates.

Then, we design a function $\textit{dfs}(i)$, which represents the character that needs to be filled at the $i$-th position. The specific implementation of the function is as follows:

-   If $i = n$, it means we have filled all positions, add the current permutation to the answer array, and then return.
-   Otherwise, we enumerate the character $\textit{s}[j]$ for the $i$-th position, where the range of $j$ is $[0, n - 1]$. We need to ensure that $\textit{s}[j]$ has not been used and is different from the previously enumerated character to ensure that the current permutation is not duplicated. If the conditions are met, we can fill $\textit{s}[j]$ and continue to recursively fill the next position by calling $\textit{dfs}(i + 1)$. After the recursive call ends, we need to mark $\textit{s}[j]$ as unused to facilitate subsequent enumeration.

In the main function, we first sort the string, then call $\textit{dfs}(0)$ to start filling from the 0th position, and finally return the answer array.

The time complexity is $O(n \times n!)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$. We need to perform $n!$ enumerations, and each enumeration requires $O(n)$ time to check for duplicates. Additionally, we need a marker array to mark whether each position has been used, so the space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(i: int):
            if i >= n:
                ans.append("".join(t))
                return
            for j, c in enumerate(s):
                if not vis[j] and (j == 0 or s[j] != s[j - 1] or vis[j - 1]):
                    vis[j] = True
                    t[i] = c
                    dfs(i + 1)
                    vis[j] = False

        s = sorted(S)
        ans = []
        t = s[:]
        n = len(s)
        vis = [False] * n
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
        int n = S.length();
        s = S.toCharArray();
        Arrays.sort(s);
        t = new char[n];
        vis = new boolean[n];
        dfs(0);
        return ans.toArray(new String[0]);
    }

    private void dfs(int i) {
        if (i >= s.length) {
            ans.add(new String(t));
            return;
        }
        for (int j = 0; j < s.length; ++j) {
            if (!vis[j] && (j == 0 || s[j] != s[j - 1] || vis[j - 1])) {
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
        ranges::sort(S);
        string t = S;
        int n = t.size();
        vector<bool> vis(n);
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, int i) {
            if (i >= n) {
                ans.emplace_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (j == 0 || S[j] != S[j - 1] || vis[j - 1])) {
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
	s := []byte(S)
	sort.Slice(s, func(i, j int) bool { return s[i] < s[j] })
	t := slices.Clone(s)
	vis := make([]bool, len(s))
	var dfs func(int)
	dfs = func(i int) {
		if i >= len(s) {
			ans = append(ans, string(t))
			return
		}
		for j := range s {
			if !vis[j] && (j == 0 || s[j] != s[j-1] || vis[j-1]) {
				vis[j] = true
				t[i] = s[j]
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
    const s: string[] = S.split('').sort();
    const n = s.length;
    const t = Array(n).fill('');
    const vis: boolean[] = Array(n).fill(false);
    const ans: string[] = [];
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (j === 0 || s[j] !== s[j - 1] || vis[j - 1])) {
                vis[j] = true;
                t[i] = s[j];
                dfs(i + 1);
                vis[j] = false;
            }
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
    const s = S.split('').sort();
    const n = s.length;
    const t = Array(n).fill('');
    const vis = Array(n).fill(false);
    const ans = [];
    const dfs = i => {
        if (i >= n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (j === 0 || s[j] !== s[j - 1] || vis[j - 1])) {
                vis[j] = true;
                t[i] = s[j];
                dfs(i + 1);
                vis[j] = false;
            }
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
        var s: [Character] = Array(S).sorted()
        var t: [Character] = Array(repeating: " ", count: s.count)
        var vis: [Bool] = Array(repeating: false, count: s.count)
        let n = s.count

        func dfs(_ i: Int) {
            if i >= n {
                ans.append(String(t))
                return
            }
            for j in 0..<n {
                if !vis[j] && (j == 0 || s[j] != s[j - 1] || vis[j - 1]) {
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
