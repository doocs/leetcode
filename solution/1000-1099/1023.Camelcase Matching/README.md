# [1023. 驼峰式匹配](https://leetcode.cn/problems/camelcase-matching)

[English Version](/solution/1000-1099/1023.Camelcase%20Matching/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果我们可以将<strong>小写字母</strong>插入模式串&nbsp;<code>pattern</code>&nbsp;得到待查询项&nbsp;<code>query</code>，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）</p>

<p>给定待查询列表&nbsp;<code>queries</code>，和模式串&nbsp;<code>pattern</code>，返回由布尔值组成的答案列表&nbsp;<code>answer</code>。只有在待查项&nbsp;<code>queries[i]</code> 与模式串&nbsp;<code>pattern</code> 匹配时，&nbsp;<code>answer[i]</code>&nbsp;才为 <code>true</code>，否则为 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
<strong>输出：</strong>[true,false,true,true,false]
<strong>示例：</strong>
"FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
"FootBall" 可以这样生成："F" + "oot" + "B" + "all".
"FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
<strong>输出：</strong>[true,false,true,false,false]
<strong>解释：</strong>
"FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
"FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
<strong>输出：</strong>[false,true,false,false,false]
<strong>解释： </strong>
"FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= queries.length &lt;= 100</code></li>
	<li><code>1 &lt;= queries[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= pattern.length &lt;= 100</code></li>
	<li>所有字符串都仅由大写和小写英文字母组成。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们可以遍历 `queries` 中的每个字符串，判断其是否与 `pattern` 匹配，若匹配则将 `true` 加入答案数组，否则加入 `false`。

接下来，我们实现一个 $check(s, t)$ 函数，用于判断字符串 $s$ 和 $t$ 是否匹配。

我们可以使用双指针 $i$ 和 $j$，分别指向两个字符串的首字符，然后遍历两个字符串。如果指针 $i$ 和 $j$ 指向的字符不同，并且 $s[i]$ 为小写字母，则指针 $i$ 循环向后移动一位。

如果指针 $i$ 已经到达字符串 $s$ 的末尾，或者指针 $i$ 和 $j$ 指向的字符不同，则返回 `false`。否则，指针 $i$ 和 $j$ 同时向后移动一位。当指针 $j$ 到达字符串 $t$ 的末尾时，我们需要判断字符串 $s$ 中剩余的字符是否都为小写字母，若是则返回 `true`，否则返回 `false`。

时间复杂度 $(n \times m)$，其中 $n$ 和 $m$ 分别为数组 `queries` 的长度和字符串 `pattern` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        def check(s, t):
            m, n = len(s), len(t)
            i = j = 0
            while j < n:
                while i < m and s[i] != t[j] and s[i].islower():
                    i += 1
                if i == m or s[i] != t[j]:
                    return False
                i, j = i + 1, j + 1
            while i < m and s[i].islower():
                i += 1
            return i == m

        return [check(q, pattern) for q in queries]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        for (var q : queries) {
            ans.add(check(q, pattern));
        }
        return ans;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        int i = 0, j = 0;
        for (; j < n; ++i, ++j) {
            while (i < m && s.charAt(i) != t.charAt(j) && Character.isLowerCase(s.charAt(i))) {
                ++i;
            }
            if (i == m || s.charAt(i) != t.charAt(j)) {
                return false;
            }
        }
        while (i < m && Character.isLowerCase(s.charAt(i))) {
            ++i;
        }
        return i == m;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<bool> camelMatch(vector<string>& queries, string pattern) {
        vector<bool> ans;
        auto check = [](string& s, string& t) {
            int m = s.size(), n = t.size();
            int i = 0, j = 0;
            for (; j < n; ++i, ++j) {
                while (i < m && s[i] != t[j] && islower(s[i])) {
                    ++i;
                }
                if (i == m || s[i] != t[j]) {
                    return false;
                }
            }
            while (i < m && islower(s[i])) {
                ++i;
            }
            return i == m;
        };
        for (auto& q : queries) {
            ans.push_back(check(q, pattern));
        }
        return ans;
    }
};
```

### **Go**

```go
func camelMatch(queries []string, pattern string) (ans []bool) {
	check := func(s, t string) bool {
		m, n := len(s), len(t)
		i, j := 0, 0
		for ; j < n; i, j = i+1, j+1 {
			for i < m && s[i] != t[j] && (s[i] >= 'a' && s[i] <= 'z') {
				i++
			}
			if i == m || s[i] != t[j] {
				return false
			}
		}
		for i < m && s[i] >= 'a' && s[i] <= 'z' {
			i++
		}
		return i == m
	}
	for _, q := range queries {
		ans = append(ans, check(q, pattern))
	}
	return
}
```

### **TypeScript**

```ts
function camelMatch(queries: string[], pattern: string): boolean[] {
    const check = (s: string, t: string) => {
        const m = s.length;
        const n = t.length;
        let i = 0;
        let j = 0;
        for (; j < n; ++i, ++j) {
            while (i < m && s[i] !== t[j] && s[i].codePointAt(0) >= 97) {
                ++i;
            }
            if (i === m || s[i] !== t[j]) {
                return false;
            }
        }
        while (i < m && s[i].codePointAt(0) >= 97) {
            ++i;
        }
        return i == m;
    };
    const ans: boolean[] = [];
    for (const q of queries) {
        ans.push(check(q, pattern));
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
