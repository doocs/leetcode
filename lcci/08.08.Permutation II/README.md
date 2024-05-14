---
comment: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/08.08.Permutation%20II/README.md
---

# [面试题 08.08. 有重复字符串的排列组合](https://leetcode.cn/problems/permutation-ii-lcci)

[English Version](/lcci/08.08.Permutation%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一：排序 + 回溯

我们可以先对字符串按照字符进行排序，这样就可以将重复的字符放在一起，方便我们进行去重。

然后，我们设计一个函数 $dfs(i)$，表示当前需要填写第 $i$ 个位置的字符。函数的具体实现如下：

-   如果 $i = n$，说明我们已经填写完毕，将当前排列加入答案数组中，然后返回。
-   否则，我们枚举第 $i$ 个位置的字符 $s[j]$，其中 $j$ 的范围是 $[0, n - 1]$。我们需要保证 $s[j]$ 没有被使用过，并且与前面枚举的字符不同，这样才能保证当前排列不重复。如果满足条件，我们就可以填写 $s[j]$，并继续递归地填写下一个位置，即调用 $dfs(i + 1)$。在递归调用结束后，我们需要将 $s[j]$ 标记为未使用，以便于进行后面的枚举。

在主函数中，我们首先对字符串进行排序，然后调用 $dfs(0)$，即从第 $0$ 个位置开始填写，最终返回答案数组即可。

时间复杂度 $O(n \times n!)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。需要进行 $n!$ 次枚举，每次枚举需要 $O(n)$ 的时间来判断是否重复。另外，我们需要一个标记数组来标记每个位置是否被使用过，因此空间复杂度为 $O(n)$。

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
