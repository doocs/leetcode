---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.08.Permutation%20II/README.md
---

<!-- problem:start -->

# [面试题 08.08. 有重复字符串的排列组合](https://leetcode.cn/problems/permutation-ii-lcci)

[English Version](/lcci/08.08.Permutation%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。</p>
<p><strong>示例1:</strong></p>
<pre><strong> 输入</strong>：S = &quot;qqe&quot;
<strong> 输出</strong>：[&quot;eqq&quot;,&quot;qeq&quot;,&quot;qqe&quot;]
</pre>
<p><strong>示例2:</strong></p>
<pre><strong> 输入</strong>：S = &quot;ab&quot;
<strong> 输出</strong>：[&quot;ab&quot;, &quot;ba&quot;]
</pre>
<p><strong>提示:</strong></p>
<ol>
	<li>字符都是英文字母。</li>
	<li>字符串长度在[1, 9]之间。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 回溯

我们可以先对字符串按照字符进行排序，这样就可以将重复的字符放在一起，方便我们进行去重。

然后，我们设计一个函数 $\textit{dfs}(i)$，表示当前需要填写第 $i$ 个位置的字符。函数的具体实现如下：

- 如果 $i = n$，说明我们已经填写完毕，将当前排列加入答案数组中，然后返回。
- 否则，我们枚举第 $i$ 个位置的字符 $\textit{s}[j]$，其中 $j$ 的范围是 $[0, n - 1]$。我们需要保证 $\textit{s}[j]$ 没有被使用过，并且与前面枚举的字符不同，这样才能保证当前排列不重复。如果满足条件，我们就可以填写 $\textit{s}[j]$，并继续递归地填写下一个位置，即调用 $\textit{dfs}(i + 1)$。在递归调用结束后，我们需要将 $\textit{s}[j]$ 标记为未使用，以便于进行后面的枚举。

在主函数中，我们首先对字符串进行排序，然后调用 $\textit{dfs}(0)$，即从第 $0$ 个位置开始填写，最终返回答案数组即可。

时间复杂度 $O(n \times n!)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。需要进行 $n!$ 次枚举，每次枚举需要 $O(n)$ 的时间来判断是否重复。另外，我们需要一个标记数组来标记每个位置是否被使用过，因此空间复杂度为 $O(n)$。

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
