# [1209. 删除字符串中的所有相邻重复项 II](https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string-ii)

[English Version](/solution/1200-1299/1209.Remove%20All%20Adjacent%20Duplicates%20in%20String%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>，「<code>k</code> 倍重复项删除操作」将会从 <code>s</code>&nbsp;中选择&nbsp;<code>k</code>&nbsp;个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。</p>

<p>你需要对&nbsp;<code>s</code>&nbsp;重复进行无限次这样的删除操作，直到无法继续为止。</p>

<p>在执行完所有删除操作后，返回最终得到的字符串。</p>

<p>本题答案保证唯一。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;abcd&quot;, k = 2
<strong>输出：</strong>&quot;abcd&quot;
<strong>解释：</strong>没有要删除的内容。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;deeedbbcccbdaa&quot;, k = 3
<strong>输出：</strong>&quot;aa&quot;
<strong>解释： 
</strong>先删除 &quot;eee&quot; 和 &quot;ccc&quot;，得到 &quot;ddbbbdaa&quot;
再删除 &quot;bbb&quot;，得到 &quot;dddaa&quot;
最后删除 &quot;ddd&quot;，得到 &quot;aa&quot;</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;pbbcggttciiippooaais&quot;, k = 2
<strong>输出：</strong>&quot;ps&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>2 &lt;= k &lt;= 10^4</code></li>
	<li><code>s</code>&nbsp;中只含有小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈**

我们可以遍历字符串 $s$，维护一个栈，栈中存储的是字符和该字符出现的次数。当遍历到字符 $c$ 时，如果栈顶元素的字符和 $c$ 相同，则将栈顶元素的次数加一，否则将字符 $c$ 和次数 $1$ 入栈。当栈顶元素的次数等于 $k$ 时，将栈顶元素出栈。

遍历完字符串 $s$ 后，栈中存储的就是最终结果。我们可以将栈中的元素依次弹出，拼接成字符串即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        t = []
        i, n = 0, len(s)
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            cnt = j - i
            cnt %= k
            if t and t[-1][0] == s[i]:
                t[-1][1] = (t[-1][1] + cnt) % k
                if t[-1][1] == 0:
                    t.pop()
            elif cnt:
                t.append([s[i], cnt])
            i = j
        ans = [c * v for c, v in t]
        return "".join(ans)
```

```python
class Solution:
    def removeDuplicates(self, s: str, k: int) -> str:
        stk = []
        for c in s:
            if stk and stk[-1][0] == c:
                stk[-1][1] = (stk[-1][1] + 1) % k
                if stk[-1][1] == 0:
                    stk.pop()
            else:
                stk.append([c, 1])
        ans = [c * v for c, v in stk]
        return "".join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<int[]> stk = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            if (!stk.isEmpty() && stk.peek()[0] == j) {
                stk.peek()[1] = (stk.peek()[1] + 1) % k;
                if (stk.peek()[1] == 0) {
                    stk.pop();
                }
            } else {
                stk.push(new int[] {j, 1});
            }
        }
        StringBuilder ans = new StringBuilder();
        for (var e : stk) {
            char c = (char) (e[0] + 'a');
            for (int i = 0; i < e[1]; ++i) {
                ans.append(c);
            }
        }
        ans.reverse();
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string removeDuplicates(string s, int k) {
        vector<pair<char, int>> stk;
        for (char& c : s) {
            if (stk.size() && stk.back().first == c) {
                stk.back().second = (stk.back().second + 1) % k;
                if (stk.back().second == 0) {
                    stk.pop_back();
                }
            } else {
                stk.push_back({c, 1});
            }
        }
        string ans;
        for (auto [c, v] : stk) {
            ans += string(v, c);
        }
        return ans;
    }
};
```

### **Go**

```go
func removeDuplicates(s string, k int) string {
    stk := []pair{}
    for _, c := range s {
        if len(stk) > 0 && stk[len(stk)-1].c == c {
            stk[len(stk)-1].v = (stk[len(stk)-1].v + 1) % k
            if stk[len(stk)-1].v == 0 {
                stk = stk[:len(stk)-1]
            }
        } else {
            stk = append(stk, pair{c, 1})
        }
    }
    ans := []rune{}
    for _, e := range stk {
        for i := 0; i < e.v; i++ {
            ans = append(ans, e.c)
        }
    }
    return string(ans)
}

type pair struct {
    c rune
    v int
}
```

### **...**

```

```

<!-- tabs:end -->
