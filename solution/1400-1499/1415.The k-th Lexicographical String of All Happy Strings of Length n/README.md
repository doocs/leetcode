---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1415.The%20k-th%20Lexicographical%20String%20of%20All%20Happy%20Strings%20of%20Length%20n/README.md
rating: 1575
source: 第 24 场双周赛 Q3
tags:
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [1415. 长度为 n 的开心字符串中字典序第 k 小的字符串](https://leetcode.cn/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n)

[English Version](/solution/1400-1499/1415.The%20k-th%20Lexicographical%20String%20of%20All%20Happy%20Strings%20of%20Length%20n/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个 「开心字符串」定义为：</p>

<ul>
	<li>仅包含小写字母&nbsp;<code>[&#39;a&#39;, &#39;b&#39;, &#39;c&#39;]</code>.</li>
	<li>对所有在&nbsp;<code>1</code>&nbsp;到&nbsp;<code>s.length - 1</code>&nbsp;之间的&nbsp;<code>i</code>&nbsp;，满足&nbsp;<code>s[i] != s[i + 1]</code>&nbsp;（字符串的下标从 1 开始）。</li>
</ul>

<p>比方说，字符串&nbsp;<strong>&quot;abc&quot;</strong>，<strong>&quot;ac&quot;，&quot;b&quot;</strong> 和&nbsp;<strong>&quot;abcbabcbcb&quot;</strong>&nbsp;都是开心字符串，但是&nbsp;<strong>&quot;aa&quot;</strong>，<strong>&quot;baa&quot;</strong>&nbsp;和&nbsp;<strong>&quot;ababbc&quot;</strong>&nbsp;都不是开心字符串。</p>

<p>给你两个整数 <code>n</code>&nbsp;和 <code>k</code>&nbsp;，你需要将长度为 <code>n</code>&nbsp;的所有开心字符串按字典序排序。</p>

<p>请你返回排序后的第 k 个开心字符串，如果长度为 <code>n</code>&nbsp;的开心字符串少于 <code>k</code>&nbsp;个，那么请你返回 <strong>空字符串</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 1, k = 3
<strong>输出：</strong>&quot;c&quot;
<strong>解释：</strong>列表 [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;] 包含了所有长度为 1 的开心字符串。按照字典序排序后第三个字符串为 &quot;c&quot; 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 1, k = 4
<strong>输出：</strong>&quot;&quot;
<strong>解释：</strong>长度为 1 的开心字符串只有 3 个。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 3, k = 9
<strong>输出：</strong>&quot;cab&quot;
<strong>解释：</strong>长度为 3 的开心字符串总共有 12 个 [&quot;aba&quot;, &quot;abc&quot;, &quot;aca&quot;, &quot;acb&quot;, &quot;bab&quot;, &quot;bac&quot;, &quot;bca&quot;, &quot;bcb&quot;, &quot;cab&quot;, &quot;cac&quot;, &quot;cba&quot;, &quot;cbc&quot;] 。第 9 个字符串为 &quot;cab&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 2, k = 7
<strong>输出：</strong>&quot;&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>n = 10, k = 100
<strong>输出：</strong>&quot;abacbabacb&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们用一个字符串 $\textit{s}$ 来记录当前的字符串，初始时为空字符串。然后，我们设计一个函数 $\text{dfs}$，用来生成所有长度为 $n$ 的开心字符串。

函数 $\text{dfs}$ 的具体实现如下：

1. 如果当前字符串的长度等于 $n$，则将当前字符串加入答案数组 $\textit{ans}$ 中，然后返回；
2. 如果答案数组的长度大于等于 $k$，则直接返回；
3. 否则，我们遍历字符集 $\{a, b, c\}$，对于每个字符 $c$，如果当前字符串为空，或者当前字符串的最后一个字符不等于 $c$，则将字符 $c$ 加入当前字符串，然后递归调用 $\text{dfs}$，递归结束后，将当前字符串的最后一个字符删除。

最后，我们判断答案数组的长度是否小于 $k$，如果是，则返回空字符串，否则返回答案数组的第 $k-1$ 个元素。

时间复杂度 $O(n \times 2^n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getHappyString(self, n: int, k: int) -> str:
        def dfs():
            if len(s) == n:
                ans.append("".join(s))
                return
            if len(ans) >= k:
                return
            for c in "abc":
                if not s or s[-1] != c:
                    s.append(c)
                    dfs()
                    s.pop()

        ans = []
        s = []
        dfs()
        return "" if len(ans) < k else ans[k - 1]
```

#### Java

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private StringBuilder s = new StringBuilder();
    private int n, k;

    public String getHappyString(int n, int k) {
        this.n = n;
        this.k = k;
        dfs();
        return ans.size() < k ? "" : ans.get(k - 1);
    }

    private void dfs() {
        if (s.length() == n) {
            ans.add(s.toString());
            return;
        }
        if (ans.size() >= k) {
            return;
        }
        for (char c : "abc".toCharArray()) {
            if (s.isEmpty() || s.charAt(s.length() - 1) != c) {
                s.append(c);
                dfs();
                s.deleteCharAt(s.length() - 1);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    string getHappyString(int n, int k) {
        vector<string> ans;
        string s = "";
        auto dfs = [&](this auto&& dfs) -> void {
            if (s.size() == n) {
                ans.emplace_back(s);
                return;
            }
            if (ans.size() >= k) {
                return;
            }
            for (char c = 'a'; c <= 'c'; ++c) {
                if (s.empty() || s.back() != c) {
                    s.push_back(c);
                    dfs();
                    s.pop_back();
                }
            }
        };
        dfs();
        return ans.size() < k ? "" : ans[k - 1];
    }
};
```

#### Go

```go
func getHappyString(n int, k int) string {
    ans := []string{}
    var s []byte

    var dfs func()
    dfs = func() {
        if len(s) == n {
            ans = append(ans, string(s))
            return
        }
        if len(ans) >= k {
            return
        }
        for c := byte('a'); c <= 'c'; c++ {
            if len(s) == 0 || s[len(s)-1] != c {
                s = append(s, c)
                dfs()
                s = s[:len(s)-1]
            }
        }
    }

    dfs()
    if len(ans) < k {
        return ""
    }
    return ans[k-1]
}
```

#### TypeScript

```ts
function getHappyString(n: number, k: number): string {
    const ans: string[] = [];
    const s: string[] = [];
    const dfs = () => {
        if (s.length === n) {
            ans.push(s.join(''));
            return;
        }
        if (ans.length >= k) {
            return;
        }
        for (const c of 'abc') {
            if (!s.length || s.at(-1)! !== c) {
                s.push(c);
                dfs();
                s.pop();
            }
        }
    };
    dfs();
    return ans[k - 1] ?? '';
}
```

#### Rust

```rust
impl Solution {
    pub fn get_happy_string(n: i32, k: i32) -> String {
        let mut ans = Vec::new();
        let mut s = String::new();
        let mut k = k;

        fn dfs(n: i32, s: &mut String, ans: &mut Vec<String>, k: &mut i32) {
            if s.len() == n as usize {
                ans.push(s.clone());
                return;
            }
            if ans.len() >= *k as usize {
                return;
            }
            for c in "abc".chars() {
                if s.is_empty() || s.chars().last() != Some(c) {
                    s.push(c);
                    dfs(n, s, ans, k);
                    s.pop();
                }
            }
        }

        dfs(n, &mut s, &mut ans, &mut k);
        if ans.len() < k as usize {
            "".to_string()
        } else {
            ans[(k - 1) as usize].clone()
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @param {number} k
 * @return {string}
 */
var getHappyString = function (n, k) {
    const ans = [];
    const s = [];
    const dfs = () => {
        if (s.length === n) {
            ans.push(s.join(''));
            return;
        }
        if (ans.length >= k) {
            return;
        }
        for (const c of 'abc') {
            if (!s.length || s.at(-1) !== c) {
                s.push(c);
                dfs();
                s.pop();
            }
        }
    };
    dfs();
    return ans[k - 1] ?? '';
};
```

#### C#

```cs
public class Solution {
    public string GetHappyString(int n, int k) {
        List<string> ans = new List<string>();
        StringBuilder s = new StringBuilder();

        void Dfs() {
            if (s.Length == n) {
                ans.Add(s.ToString());
                return;
            }
            if (ans.Count >= k) {
                return;
            }
            foreach (char c in "abc") {
                if (s.Length == 0 || s[s.Length - 1] != c) {
                    s.Append(c);
                    Dfs();
                    s.Length--;
                }
            }
        }

        Dfs();
        return ans.Count < k ? "" : ans[k - 1];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
