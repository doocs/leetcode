# [784. 字母大小写全排列](https://leetcode-cn.com/problems/letter-case-permutation)

[English Version](/solution/0700-0799/0784.Letter%20Case%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串<code>S</code>，通过将字符串<code>S</code>中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。</p>

<p>&nbsp;</p>

<pre><strong>示例：</strong>
<strong>输入：</strong>S = &quot;a1b2&quot;
<strong>输出：</strong>[&quot;a1b2&quot;, &quot;a1B2&quot;, &quot;A1b2&quot;, &quot;A1B2&quot;]

<strong>输入：</strong>S = &quot;3z4&quot;
<strong>输出：</strong>[&quot;3z4&quot;, &quot;3Z4&quot;]

<strong>输入：</strong>S = &quot;12345&quot;
<strong>输出：</strong>[&quot;12345&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>S</code>&nbsp;的长度不超过<code>12</code>。</li>
	<li><code>S</code>&nbsp;仅由数字和字母组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS 回溯法。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        def dfs(i, t):
            if i == len(t):
                ans.append(''.join(t))
                return
            dfs(i + 1, t)
            if t[i].isalpha():
                t[i] = t[i].upper() if t[i].islower() else t[i].lower()
                dfs(i + 1, t)

        ans = []
        t = list(s)
        dfs(0, t)
        return ans
```

```python
class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        def dfs(i, t):
            if i == len(s):
                ans.append(t)
                return
            if s[i].isalpha():
                dfs(i + 1, t + s[i].upper())
                dfs(i + 1, t + s[i].lower())
            else:
                dfs(i + 1, t + s[i])

        ans = []
        dfs(0, '')
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> letterCasePermutation(String S) {
        char[] cs = S.toCharArray();
        List<String> res = new ArrayList<>();
        dfs(cs, 0, res);
        return res;
    }

    private void dfs(char[] cs, int i, List<String> res) {
        if (i == cs.length) {
            res.add(String.valueOf(cs));
            return;
        }
        dfs(cs, i + 1, res);
        if (cs[i] >= 'A') {
            cs[i] ^= 32;
            dfs(cs, i + 1, res);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> ans;
    string s;

    vector<string> letterCasePermutation(string s) {
        this->s = s;
        string t = "";
        dfs(0, t);
        return ans;
    }

    void dfs(int i, string t) {
        if (i == s.size())
        {
            ans.push_back(t);
            return;
        }
        if (isalpha(s[i]))
        {
            char c1 = toupper(s[i]);
            char c2 = tolower(s[i]);
            dfs(i + 1, t + c1);
            dfs(i + 1, t + c2);
        }
        else
        {
            dfs(i + 1, t + s[i]);
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
