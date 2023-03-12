# [剑指 Offer II 020. 回文子字符串的个数](https://leetcode.cn/problems/a7VOhD)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code> ，请计算这个字符串中有多少个回文子字符串。</p>

<p>具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abc"
<strong>输出：</strong>3
<strong>解释：</strong>三个回文子串: "a", "b", "c"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s =<strong> </strong>"aaa"
<strong>输出：</strong>6
<strong>解释：</strong>6个回文子串: "a", "a", "a", "aa", "aa", "aaa"</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 647 题相同：<a href="https://leetcode.cn/problems/palindromic-substrings/">https://leetcode.cn/problems/palindromic-substrings/</a>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：从中心向两侧扩展回文串**

我们可以枚举回文串的中间点，然后向左右两边扩展，统计回文串的数量。注意，回文串可能包含奇数个字符，也可能不包含。因此这两种情况都要考虑。

时间复杂度 $O(n^2)$，其中 $n$ 是字符串 `s` 的长度。

**方法二：Manacher 算法**

在 Manacher 算法的计算过程中，用 $p[i]-1$ 表示以第 $i$ 位为中心的最大回文长度，以第 $i$ 位为中心的回文串数量为 $\left \lceil \frac{p[i]-1}{2}  \right \rceil$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 `s` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSubstrings(self, s: str) -> int:
        def f(i, j):
            cnt = 0
            while i >= 0 and j < n:
                if s[i] != s[j]:
                    break
                cnt += 1
                i, j = i - 1, j + 1
            return cnt

        n = len(s)
        return sum(f(i, i) + f(i, i + 1) for i in range(n))
```

```python
class Solution:
    def countSubstrings(self, s: str) -> int:
        t = '^#' + '#'.join(s) + '#$'
        n = len(t)
        p = [0 for _ in range(n)]
        pos, maxRight = 0, 0
        ans = 0
        for i in range(1, n - 1):
            p[i] = min(maxRight - i, p[2 * pos - i]) if maxRight > i else 1
            while t[i - p[i]] == t[i + p[i]]:
                p[i] += 1
            if i + p[i] > maxRight:
                maxRight = i + p[i]
                pos = i
            ans += p[i] // 2
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private String s;

    public int countSubstrings(String s) {
        int ans = 0;
        this.s = s;
        for (int i = 0; i < s.length(); ++i) {
            ans += f(i, i);
            ans += f(i, i + 1);
        }
        return ans;
    }

    private int f(int i, int j) {
        int cnt = 0;
        for (; i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j); --i, ++j) {
            ++cnt;
        }
        return cnt;
    }
}
```

```java
class Solution {
    public int countSubstrings(String s) {
        StringBuilder sb = new StringBuilder("^#");
        for (char ch : s.toCharArray()) {
            sb.append(ch).append('#');
        }
        String t = sb.append('$').toString();
        int n = t.length();
        int[] p = new int[n];
        int pos = 0, maxRight = 0;
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            p[i] = maxRight > i ? Math.min(maxRight - i, p[2 * pos - i]) : 1;
            while (t.charAt(i - p[i]) == t.charAt(i + p[i])) {
                p[i]++;
            }
            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                pos = i;
            }
            ans += p[i] / 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSubstrings(string s) {
        int ans = 0;
        int n = s.size();
        auto f = [&](int i, int j) -> int {
            int cnt = 0;
            for (; i >= 0 && j < n && s[i] == s[j]; --i, ++j) {
                ++cnt;
            }
            return cnt;
        };
        for (int i = 0; i < n; ++i) {
            ans += f(i, i) + f(i, i + 1);
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubstrings(s string) (ans int) {
	n := len(s)
	f := func(i, j int) (cnt int) {
		for ; i >= 0 && j < n && s[i] == s[j]; i, j = i-1, j+1 {
			cnt++
		}
		return
	}
	for i := range s {
		ans += f(i, i)
		ans += f(i, i+1)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
