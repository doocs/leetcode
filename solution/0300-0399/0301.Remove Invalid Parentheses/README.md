# [301. 删除无效的括号](https://leetcode.cn/problems/remove-invalid-parentheses)

[English Version](/solution/0300-0399/0301.Remove%20Invalid%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由若干括号和字母组成的字符串 <code>s</code> ，删除最小数量的无效括号，使得输入的字符串有效。</p>

<p>返回所有可能的结果。答案可以按 <strong>任意顺序</strong> 返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "()())()"
<strong>输出：</strong>["(())()","()()()"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "(a)())()"
<strong>输出：</strong>["(a())()","(a)()()"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = ")("
<strong>输出：</strong>[""]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 25</code></li>
	<li><code>s</code> 由小写英文字母以及括号 <code>'('</code> 和 <code>')'</code> 组成</li>
	<li><code>s</code> 中至多含 <code>20</code> 个括号</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS + 剪枝。

`ldel`, `rdel` 分别表示最少需要删除的 `(`, `)` 的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeInvalidParentheses(self, s: str) -> List[str]:
        def dfs(i, t, lcnt, rcnt, ldel, rdel):
            nonlocal tdel, ans
            if ldel * rdel < 0 or lcnt < rcnt or ldel + rdel > len(s) - i:
                return
            if ldel == 0 and rdel == 0:
                if len(s) - len(t) == tdel:
                    ans.add(t)
            if i == len(s):
                return
            if s[i] == '(':
                dfs(i + 1, t, lcnt, rcnt, ldel - 1, rdel)
                dfs(i + 1, t + '(', lcnt + 1, rcnt, ldel, rdel)
            elif s[i] == ')':
                dfs(i + 1, t, lcnt, rcnt, ldel, rdel - 1)
                dfs(i + 1, t + ')', lcnt, rcnt + 1, ldel, rdel)
            else:
                dfs(i + 1, t + s[i], lcnt, rcnt, ldel, rdel)

        ldel = rdel = 0
        for c in s:
            if c == '(':
                ldel += 1
            elif c == ')':
                if ldel == 0:
                    rdel += 1
                else:
                    ldel -= 1
        tdel = ldel + rdel
        ans = set()
        dfs(0, '', 0, 0, ldel, rdel)
        return list(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int tdel;
    private String s;
    private Set<String> ans;

    public List<String> removeInvalidParentheses(String s) {
        int ldel = 0, rdel = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++ldel;
            } else if (c == ')') {
                if (ldel == 0) {
                    ++rdel;
                } else {
                    --ldel;
                }
            }
        }
        tdel = ldel + rdel;
        this.s = s;
        ans = new HashSet<>();
        dfs(0, "", 0, 0, ldel, rdel);
        return new ArrayList<>(ans);
    }

    private void dfs(int i, String t, int lcnt, int rcnt, int ldel, int rdel) {
        if (ldel * rdel < 0 || lcnt < rcnt || ldel + rdel > s.length() - i) {
            return;
        }
        if (ldel == 0 && rdel == 0) {
            if (s.length() - t.length() == tdel) {
                ans.add(t);
            }
        }
        if (i == s.length()) {
            return;
        }
        char c = s.charAt(i);
        if (c == '(') {
            dfs(i + 1, t, lcnt, rcnt, ldel - 1, rdel);
            dfs(i + 1, t + String.valueOf(c), lcnt + 1, rcnt, ldel, rdel);
        } else if (c == ')') {
            dfs(i + 1, t, lcnt, rcnt, ldel, rdel - 1);
            dfs(i + 1, t + String.valueOf(c), lcnt, rcnt + 1, ldel, rdel);
        } else {
            dfs(i + 1, t + String.valueOf(c), lcnt, rcnt, ldel, rdel);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> removeInvalidParentheses(string s) {
        int ldel = 0, rdel = 0;
        for (char c : s) {
            if (c == '(')
                ++ldel;
            else if (c == ')') {
                if (ldel == 0)
                    ++rdel;
                else
                    --ldel;
            }
        }
        int tdel = ldel + rdel;
        unordered_set<string> ans;
        dfs(0, "", s, 0, 0, ldel, rdel, tdel, ans);
        vector<string> res;
        res.assign(ans.begin(), ans.end());
        return res;
    }

    void dfs(int i, string t, string s, int lcnt, int rcnt, int ldel, int rdel, int tdel, unordered_set<string>& ans) {
        if (ldel * rdel < 0 || lcnt < rcnt || ldel + rdel > s.size() - i) return;
        if (ldel == 0 && rdel == 0) {
            if (s.size() - t.size() == tdel) ans.insert(t);
        }
        if (i == s.size()) return;
        if (s[i] == '(') {
            dfs(i + 1, t, s, lcnt, rcnt, ldel - 1, rdel, tdel, ans);
            dfs(i + 1, t + s[i], s, lcnt + 1, rcnt, ldel, rdel, tdel, ans);
        } else if (s[i] == ')') {
            dfs(i + 1, t, s, lcnt, rcnt, ldel, rdel - 1, tdel, ans);
            dfs(i + 1, t + s[i], s, lcnt, rcnt + 1, ldel, rdel, tdel, ans);
        } else {
            dfs(i + 1, t + s[i], s, lcnt, rcnt, ldel, rdel, tdel, ans);
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
