# [1055. 形成字符串的最短路径](https://leetcode.cn/problems/shortest-way-to-form-string)

[English Version](/solution/1000-1099/1055.Shortest%20Way%20to%20Form%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对于任何字符串，我们可以通过删除其中一些字符（也可能不删除）来构造该字符串的 <strong>子序列</strong> 。(例如，<code>“ace”</code>&nbsp;是 <code>“abcde”</code> 的子序列，而 <code>“aec”</code> 不是)。</p>

<p>给定源字符串&nbsp;<code>source</code> 和目标字符串&nbsp;<code>target</code>，返回 <em>源字符串&nbsp;<code>source</code>&nbsp;中能通过串联形成目标字符串&nbsp;</em><code>target</code>&nbsp;<em>的 <strong>子序列</strong> 的最小数量&nbsp;</em>。如果无法通过串联源字符串中的子序列来构造目标字符串，则返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>source = "abc", target = "abcbc"
<strong>输出：</strong>2
<strong>解释：</strong>目标字符串 "abcbc" 可以由 "abc" 和 "bc" 形成，它们都是源字符串 "abc" 的子序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>source = "abc", target = "acdbc"
<strong>输出：</strong>-1
<strong>解释：</strong>由于目标字符串中包含字符 "d"，所以无法由源字符串的子序列构建目标字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>source = "xyz", target = "xzyxz"
<strong>输出：</strong>3
<strong>解释：</strong>目标字符串可以按如下方式构建： "xz" + "y" + "xz"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= source.length, target.length &lt;= 1000</code></li>
	<li><code>source</code> 和&nbsp;<code>target</code>&nbsp;仅包含英文小写字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们可以使用双指针的方法，用指针 $j$ 指向目标字符串 `target`，然后遍历源字符串 `source`，用指针 $i$ 指向源字符串 `source`，如果 $source[i] = target[j]$，则 $i$ 和 $j$ 同时向后移动一位，否则只移动 $i$ 指针。当指针 $i$ 和 $j$ 都到达字符串末尾时，如果没有找到相等的字符，则返回 $-1$，否则子序列数量加一，然后将指针 $i$ 置为 $0$，继续遍历。

遍历结束后，返回子序列数量即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为字符串 `source` 和 `target` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestWay(self, source: str, target: str) -> int:
        def f(i, j):
            while i < m and j < n:
                if source[i] == target[j]:
                    j += 1
                i += 1
            return j

        m, n = len(source), len(target)
        ans = j = 0
        while j < n:
            k = f(0, j)
            if k == j:
                return -1
            j = k
            ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shortestWay(String source, String target) {
        int m = source.length(), n = target.length();
        int ans = 0, j = 0;
        while (j < n) {
            int i = 0;
            boolean ok = false;
            while (i < m && j < n) {
                if (source.charAt(i) == target.charAt(j)) {
                    ok = true;
                    ++j;
                }
                ++i;
            }
            if (!ok) {
                return -1;
            }
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shortestWay(string source, string target) {
        int m = source.size(), n = target.size();
        int ans = 0, j = 0;
        while (j < n) {
            int i = 0;
            bool ok = false;
            while (i < m && j < n) {
                if (source[i] == target[j]) {
                    ok = true;
                    ++j;
                }
                ++i;
            }
            if (!ok) {
                return -1;
            }
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestWay(source string, target string) int {
	m, n := len(source), len(target)
	ans, j := 0, 0
	for j < n {
		ok := false
		for i := 0; i < m && j < n; i++ {
			if source[i] == target[j] {
				ok = true
				j++
			}
		}
		if !ok {
			return -1
		}
		ans++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
