---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.08.Permutation%20II/README_EN.md
---

# [08.08. Permutation II](https://leetcode.cn/problems/permutation-ii-lcci)

[中文文档](/lcci/08.08.Permutation%20II/README.md)

## Description

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

## Solutions

### Solution 1: Sorting + Backtracking

We can first sort the string by characters, which allows us to put duplicate characters together and makes it easier for us to remove duplicates.

Then, we design a function $dfs(i)$, which means that we need to fill in the character at the $i$-th position. The specific implementation of the function is as follows:

-   If $i = n$, it means that we have finished filling in, add the current permutation to the answer array, and then return.
-   Otherwise, we enumerate the character $s[j]$ at the $i$-th position, where the range of $j$ is $[0, n - 1]$. We need to ensure that $s[j]$ has not been used and is different from the previously enumerated characters, so as to ensure that the current permutation is not repeated. If the conditions are met, we can fill in $s[j]$, and continue to recursively fill in the next position, that is, call $dfs(i + 1)$. After the recursive call ends, we need to mark $s[j]$ as unused for later enumeration.

In the main function, we first sort the string, then call $dfs(0)$, that is, start filling from the $0$-th position, and finally return the answer array.

The time complexity is $O(n \times n!)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$. $n!$ enumerations need to be performed, and each enumeration requires $O(n)$ time to determine whether it is repeated. In addition, we need a marker array to mark whether each position has been used, so the space complexity is $O(n)$.

<!-- tabs:start -->

```python
class Solution:
    def permutation(self, S: str) -> List[str]:
        def dfs(i: int):
            if i == n:
                ans.append("".join(t))
                return
            for j in range(n):
                if vis[j] or (j and cs[j] == cs[j - 1] and not vis[j - 1]):
                    continue
                t[i] = cs[j]
                vis[j] = True
                dfs(i + 1)
                vis[j] = False

        cs = sorted(S)
        n = len(cs)
        ans = []
        t = [None] * n
        vis = [False] * n
        dfs(0)
        return ans
```

```java
class Solution {
    private int n;
    private char[] cs;
    private List<String> ans = new ArrayList<>();
    private boolean[] vis;
    private StringBuilder t = new StringBuilder();

    public String[] permutation(String S) {
        cs = S.toCharArray();
        n = cs.length;
        Arrays.sort(cs);
        vis = new boolean[n];
        dfs(0);
        return ans.toArray(new String[0]);
    }

    private void dfs(int i) {
        if (i == n) {
            ans.add(t.toString());
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && !vis[j - 1] && cs[j] == cs[j - 1])) {
                continue;
            }
            vis[j] = true;
            t.append(cs[j]);
            dfs(i + 1);
            t.deleteCharAt(t.length() - 1);
            vis[j] = false;
        }
    }
}
```

```cpp
class Solution {
public:
    vector<string> permutation(string S) {
        vector<char> cs(S.begin(), S.end());
        sort(cs.begin(), cs.end());
        int n = cs.size();
        vector<string> ans;
        vector<bool> vis(n);
        string t;
        function<void(int)> dfs = [&](int i) {
            if (i == n) {
                ans.push_back(t);
                return;
            }
            for (int j = 0; j < n; ++j) {
                if (vis[j] || (j && !vis[j - 1] && cs[j] == cs[j - 1])) {
                    continue;
                }
                vis[j] = true;
                t.push_back(cs[j]);
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
	cs := []byte(S)
	sort.Slice(cs, func(i, j int) bool { return cs[i] < cs[j] })
	t := []byte{}
	n := len(cs)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans = append(ans, string(t))
			return
		}
		for j := 0; j < n; j++ {
			if vis[j] || (j > 0 && !vis[j-1] && cs[j] == cs[j-1]) {
				continue
			}
			vis[j] = true
			t = append(t, cs[j])
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
    const cs: string[] = S.split('').sort();
    const ans: string[] = [];
    const n = cs.length;
    const vis: boolean[] = Array(n).fill(false);
    const t: string[] = [];
    const dfs = (i: number) => {
        if (i === n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && !vis[j - 1] && cs[j] === cs[j - 1])) {
                continue;
            }
            vis[j] = true;
            t.push(cs[j]);
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
    const cs = S.split('').sort();
    const ans = [];
    const n = cs.length;
    const vis = Array(n).fill(false);
    const t = [];
    const dfs = i => {
        if (i === n) {
            ans.push(t.join(''));
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (vis[j] || (j > 0 && !vis[j - 1] && cs[j] === cs[j - 1])) {
                continue;
            }
            vis[j] = true;
            t.push(cs[j]);
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
    private var n: Int = 0
    private var cs: [Character] = []
    private var ans: [String] = []
    private var vis: [Bool] = []
    private var t: String = ""

    func permutation(_ S: String) -> [String] {
        cs = Array(S)
        n = cs.count
        cs.sort()
        vis = Array(repeating: false, count: n)
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        if i == n {
            ans.append(t)
            return
        }
        for j in 0..<n {
            if vis[j] || (j > 0 && !vis[j - 1] && cs[j] == cs[j - 1]) {
                continue
            }
            vis[j] = true
            t.append(cs[j])
            dfs(i + 1)
            t.removeLast()
            vis[j] = false
        }
    }
}
```

<!-- tabs:end -->

<!-- end -->
