---
comment: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.07.Permutation%20I/README.md
---

# [面试题 08.07. 无重复字符串的排列组合](https://leetcode.cn/problems/permutation-i-lcci)

[English Version](/lcci/08.07.Permutation%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
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

## 解法

### 方法一：DFS（回溯）

我们设计一个函数 $dfs(i)$ 表示已经填完了前 $i$ 个位置，现在需要填第 $i+1$ 个位置。枚举所有可能的字符，如果这个字符没有被填过，就填入这个字符，然后继续填下一个位置，直到填完所有的位置。

时间复杂度 $O(n \times n!)$，其中 $n$ 是字符串的长度。一共有 $n!$ 个排列，每个排列需要 $O(n)$ 的时间来构造。

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
