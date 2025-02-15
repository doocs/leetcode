---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.07.Permutation%20I/README.md
---

<!-- problem:start -->

# [面试题 08.07. 无重复字符串的排列组合](https://leetcode.cn/problems/permutation-i-lcci)

[English Version](/lcci/08.07.Permutation%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：S = "qwe"
<strong> 输出</strong>：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：S = "ab"
<strong> 输出</strong>：["ab", "ba"]
</pre>

<p> <strong>提示:</strong></p>

<ol>
<li>字符都是英文字母。</li>
<li>字符串长度在[1, 9]之间。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS（回溯）

我们设计一个函数 $\textit{dfs}(i)$ 表示已经填完了前 $i$ 个位置，现在需要填第 $i+1$ 个位置。枚举所有可能的字符，如果这个字符没有被填过，就填入这个字符，然后继续填下一个位置，直到填完所有的位置。

时间复杂度 $O(n \times n!)$，其中 $n$ 是字符串的长度。一共有 $n!$ 个排列，每个排列需要 $O(n)$ 的时间来构造。

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
