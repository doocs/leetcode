# [809. 情感丰富的文字](https://leetcode.cn/problems/expressive-words)

[English Version](/solution/0800-0899/0809.Expressive%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有时候人们会用重复写一些字母来表示额外的感受，比如 <code>"hello" -&gt; "heeellooo"</code>, <code>"hi" -&gt; "hiii"</code>。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。</p>

<p>对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母&nbsp;<code>c</code>&nbsp;），然后往其中添加相同的字母&nbsp;<code>c</code>&nbsp;使其长度达到 3 或以上。</p>

<p>例如，以&nbsp;"hello" 为例，我们可以对字母组&nbsp;"o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于&nbsp;3。此外，我们可以进行另一种扩张 "ll" -&gt; "lllll" 以获得&nbsp;"helllllooo"。如果&nbsp;<code>s = "helllllooo"</code>，那么查询词&nbsp;"hello" 是可扩张的，因为可以对它执行这两种扩张操作使得&nbsp;<code>query = "hello" -&gt; "hellooo" -&gt;&nbsp;"helllllooo" = s</code>。</p>

<p>输入一组查询单词，输出其中可扩张的单词数量。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong> 
s = "heeellooo"
words = ["hello", "hi", "helo"]
<strong>输出：</strong>1
<strong>解释</strong>：
我们能通过扩张 "hello" 的 "e" 和 "o" 来得到 "heeellooo"。
我们不能通过扩张 "helo" 来得到 "heeellooo" 因为 "ll" 的长度小于 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">s</span></font> 和所有在&nbsp;<code>words</code>&nbsp;中的单词都只由小写字母组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：遍历计数 + 双指针**

我们可以遍历数组 `words`，对于数组中的每个单词 $t$，判断 $t$ 是否可以通过扩张得到 $s$，如果可以，那么答案加一。

因此，问题的关键在于判断单词 $t$ 是否可以通过扩张得到 $s$。这里我们通过一个 $check(s, t)$ 函数来判断。函数的具体实现逻辑如下：

首先判断 $s$ 和 $t$ 的长度关系，如果 $t$ 的长度大于 $s$，直接返回 `false`；否则，我们用双指针 $i$ 和 $j$ 分别指向 $s$ 和 $t$，初始时 $i$ 和 $j$ 的值均为 $0$。

如果 $i$ 和 $j$ 指向的字符不同，那么 $t$ 无法通过扩张得到 $s$，直接返回 `false`；否则，我们需要判断 $i$ 指向的字符的连续出现次数 $c_1$ 和 $j$ 指向的字符的连续出现次数 $c_2$ 的关系。如果 $c_1 \lt c_2$ 或者 $c_1 \lt 3$ 并且 $c_1 \neq c_2$，那么 $t$ 无法通过扩张得到 $s$，直接返回 `false`；否则，将 $i$ 和 $j$ 分别右移 $c_1$ 和 $c_2$ 次。继续判断。

如果 $i$ 和 $j$ 都到达了字符串的末尾，那么 $t$ 可以通过扩张得到 $s$，返回 `true`，否则返回 `false`。

时间复杂度 $O(n \times m + \sum_{i=0}^{m-1} w_i)$，其中 $n$ 和 $m$ 分别为字符串 $s$ 和数组 $words$ 的长度，而 $w_i$ 为数组 $words$ 中第 $i$ 个单词的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def expressiveWords(self, s: str, words: List[str]) -> int:
        def check(s, t):
            m, n = len(s), len(t)
            if n > m:
                return False
            i = j = 0
            while i < m and j < n:
                if s[i] != t[j]:
                    return False
                k = i
                while k < m and s[k] == s[i]:
                    k += 1
                c1 = k - i
                i, k = k, j
                while k < n and t[k] == t[j]:
                    k += 1
                c2 = k - j
                j = k
                if c1 < c2 or (c1 < 3 and c1 != c2):
                    return False
            return i == m and j == n

        return sum(check(s, t) for t in words)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int expressiveWords(String s, String[] words) {
        int ans = 0;
        for (String t : words) {
            if (check(s, t)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) {
            return false;
        }
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            int k = i;
            while (k < m && s.charAt(k) == s.charAt(i)) {
                ++k;
            }
            int c1 = k - i;
            i = k;
            k = j;
            while (k < n && t.charAt(k) == t.charAt(j)) {
                ++k;
            }
            int c2 = k - j;
            j = k;
            if (c1 < c2 || (c1 < 3 && c1 != c2)) {
                return false;
            }
        }
        return i == m && j == n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int expressiveWords(string s, vector<string>& words) {
        auto check = [](string&s, string& t) -> int {
            int m = s.size(), n = t.size();
            if (n > m) return 0;
            int i = 0, j = 0;
            while (i < m && j < n) {
                if (s[i] != t[j]) return 0;
                int k = i;
                while (k < m && s[k] == s[i]) ++k;
                int c1 = k - i;
                i = k, k = j;
                while (k < n && t[k] == t[j]) ++k;
                int c2 = k - j;
                j = k;
                if (c1 < c2 || (c1 < 3 && c1 != c2)) return 0;
            }
            return i == m && j == n;
        };

        int ans = 0;
        for (string& t : words) ans += check(s, t);
        return ans;
    }
};
```

### **Go**

```go
func expressiveWords(s string, words []string) (ans int) {
	check := func(s, t string) bool {
		m, n := len(s), len(t)
		if n > m {
			return false
		}
		i, j := 0, 0
		for i < m && j < n {
			if s[i] != t[j] {
				return false
			}
			k := i
			for k < m && s[k] == s[i] {
				k++
			}
			c1 := k - i
			i, k = k, j
			for k < n && t[k] == t[j] {
				k++
			}
			c2 := k - j
			j = k
			if c1 < c2 || (c1 != c2 && c1 < 3) {
				return false
			}
		}
		return i == m && j == n
	}
	for _, t := range words {
		if check(s, t) {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
