---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1593.Split%20a%20String%20Into%20the%20Max%20Number%20of%20Unique%20Substrings/README.md
rating: 1739
source: 第 207 场周赛 Q2
tags:
    - 哈希表
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [1593. 拆分字符串使唯一子字符串的数目最大](https://leetcode.cn/problems/split-a-string-into-the-max-number-of-unique-substrings)

[English Version](/solution/1500-1599/1593.Split%20a%20String%20Into%20the%20Max%20Number%20of%20Unique%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，请你拆分该字符串，并返回拆分后唯一子字符串的最大数目。</p>

<p>字符串 <code>s</code> 拆分后可以得到若干 <strong>非空子字符串</strong> ，这些子字符串连接后应当能够还原为原字符串。但是拆分出来的每个子字符串都必须是 <strong>唯一的</strong> 。</p>

<p>注意：<strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;ababccc&quot;
<strong>输出：</strong>5
<strong>解释：</strong>一种最大拆分方法为 [&#39;a&#39;, &#39;b&#39;, &#39;ab&#39;, &#39;c&#39;, &#39;cc&#39;] 。像 [&#39;a&#39;, &#39;b&#39;, &#39;a&#39;, &#39;b&#39;, &#39;c&#39;, &#39;cc&#39;] 这样拆分不满足题目要求，因为其中的 &#39;a&#39; 和 &#39;b&#39; 都出现了不止一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aba&quot;
<strong>输出：</strong>2
<strong>解释：</strong>一种最大拆分方法为 [&#39;a&#39;, &#39;ba&#39;] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;aa&quot;
<strong>输出：</strong>1
<strong>解释：</strong>无法进一步拆分字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>
	<p><code>1 &lt;= s.length&nbsp;&lt;= 16</code></p>
	</li>
	<li>
	<p><code>s</code> 仅包含小写英文字母</p>
	</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：回溯 + 剪枝

我们定义一个哈希表 $\textit{st}$，用于存储当前已经拆分出的子字符串。然后我们使用深度优先搜索的方式，尝试将字符串 $\textit{s}$ 拆分成若干个唯一的子字符串。

具体地，我们设计一个函数 $\text{dfs}(i)$，表示我们正在考虑将 $\textit{s}[i:]$ 进行拆分。

在函数 $\text{dfs}(i)$ 中，我们首先判断如果当前已经拆分出的子字符串的数量加上剩余的字符数小于等于当前的答案，那么我们就没有必要继续拆分，直接返回。如果 $i \geq n$，那么说明我们已经完成了对整个字符串的拆分，我们更新答案为当前的子字符串数量和答案的较大值。否则，我们枚举当前子字符串的结束位置 $j$（不包括 $j$），并判断 $\textit{s}[i..j)$ 是否已经被拆分出来。如果没有被拆分出来，我们将其加入到哈希表 $\textit{st}$ 中，并继续递归地考虑拆分剩余的部分。在递归调用结束后，我们需要将 $\textit{s}[i..j)$ 从哈希表 $\textit{st}$ 中移除。

最后，我们返回答案。

时间复杂度 $O(n^2 \times 2^n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $\textit{s}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxUniqueSplit(self, s: str) -> int:
        def dfs(i: int):
            nonlocal ans
            if len(st) + len(s) - i <= ans:
                return
            if i >= len(s):
                ans = max(ans, len(st))
                return
            for j in range(i + 1, len(s) + 1):
                if s[i:j] not in st:
                    st.add(s[i:j])
                    dfs(j)
                    st.remove(s[i:j])

        ans = 0
        st = set()
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    private Set<String> st = new HashSet<>();
    private int ans;
    private String s;

    public int maxUniqueSplit(String s) {
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (st.size() + s.length() - i <= ans) {
            return;
        }
        if (i >= s.length()) {
            ans = Math.max(ans, st.size());
            return;
        }
        for (int j = i + 1; j <= s.length(); ++j) {
            String t = s.substring(i, j);
            if (st.add(t)) {
                dfs(j);
                st.remove(t);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxUniqueSplit(string s) {
        unordered_set<string> st;
        int n = s.size();
        int ans = 0;
        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (st.size() + n - i <= ans) {
                return;
            }
            if (i >= n) {
                ans = max(ans, (int) st.size());
                return;
            }
            for (int j = i + 1; j <= n; ++j) {
                string t = s.substr(i, j - i);
                if (!st.contains(t)) {
                    st.insert(t);
                    dfs(j);
                    st.erase(t);
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
func maxUniqueSplit(s string) (ans int) {
	st := map[string]bool{}
	n := len(s)
	var dfs func(int)
	dfs = func(i int) {
		if len(st)+n-i <= ans {
			return
		}
		if i >= n {
			ans = max(ans, len(st))
			return
		}
		for j := i + 1; j <= n; j++ {
			if t := s[i:j]; !st[t] {
				st[t] = true
				dfs(j)
				delete(st, t)
			}
		}
	}
	dfs(0)
	return
}
```

#### TypeScript

```ts
function maxUniqueSplit(s: string): number {
    const n = s.length;
    const st = new Set<string>();
    let ans = 0;
    const dfs = (i: number): void => {
        if (st.size + n - i <= ans) {
            return;
        }
        if (i >= n) {
            ans = Math.max(ans, st.size);
            return;
        }
        for (let j = i + 1; j <= n; ++j) {
            const t = s.slice(i, j);
            if (!st.has(t)) {
                st.add(t);
                dfs(j);
                st.delete(t);
            }
        }
    };
    dfs(0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
