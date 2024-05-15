---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1153.String%20Transforms%20Into%20Another%20String/README.md
rating: 1949
tags:
    - 哈希表
    - 字符串
---

# [1153. 字符串转化 🔒](https://leetcode.cn/problems/string-transforms-into-another-string)

[English Version](/solution/1100-1199/1153.String%20Transforms%20Into%20Another%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出两个长度相同的字符串&nbsp;<code>str1</code>&nbsp;和 <code>str2</code>。请你帮忙判断字符串 <code>str1</code> 能不能在 <strong>零次</strong>&nbsp;或 <strong>多次</strong>&nbsp;<em>转化</em>&nbsp;后变成字符串 <code>str2</code>。</p>

<p>每一次转化时，你可以将 <code>str1</code> 中出现的&nbsp;<strong>所有</strong>&nbsp;相同字母变成其他&nbsp;<strong>任何</strong>&nbsp;小写英文字母。</p>

<p>只有在字符串 <code>str1</code>&nbsp;能够通过上述方式顺利转化为字符串 <code>str2</code>&nbsp;时才能返回 <code>true</code>&nbsp;。​​</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>str1 = "aabcc", str2 = "ccdee"
<strong>输出：</strong>true
<strong>解释：</strong>将 'c' 变成 'e'，然后把 'b' 变成 'd'，接着再把 'a' 变成 'c'。注意，转化的顺序也很重要。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>str1 = "leetcode", str2 = "codeleet"
<strong>输出：</strong>false
<strong>解释：</strong>我们没有办法能够把 str1 转化为 str2。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= str1.length == str2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>str1</code>&nbsp;和 <code>str2</code> 中都只会出现小写英文字母</li>
</ul>

## 解法

### 方法一：哈希表

我们可以先判断 `str1` 和 `str2` 是否相等，若相等，直接返回 `true`。

然后我们统计 `str2` 中每个字母出现的次数，若出现的次数等于 $26$，说明 `str2` 包含了所有的小写字母，那么无论 `str1` 如何转换，都无法得到 `str2`，直接返回 `false`。

否则，我们用数组或哈希表 `d` 记录 `str1` 中每个字母转换后的字母。遍历字符串 `str1` 和 `str2`，若 `str1` 中的某个字母已经转换过，那么其转换后的字母必须与 `str2` 中对应位置的字母相同，否则返回 `false`。

遍历结束后，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 `str1` 的长度，而 $C$ 为字符集大小，本题中 $C = 26$。

<!-- tabs:start -->

```python
class Solution:
    def canConvert(self, str1: str, str2: str) -> bool:
        if str1 == str2:
            return True
        if len(set(str2)) == 26:
            return False
        d = {}
        for a, b in zip(str1, str2):
            if a not in d:
                d[a] = b
            elif d[a] != b:
                return False
        return True
```

```java
class Solution {
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        int m = 0;
        int[] cnt = new int[26];
        int n = str1.length();
        for (int i = 0; i < n; ++i) {
            if (++cnt[str2.charAt(i) - 'a'] == 1) {
                ++m;
            }
        }
        if (m == 26) {
            return false;
        }
        int[] d = new int[26];
        for (int i = 0; i < n; ++i) {
            int a = str1.charAt(i) - 'a';
            int b = str2.charAt(i) - 'a';
            if (d[a] == 0) {
                d[a] = b + 1;
            } else if (d[a] != b + 1) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool canConvert(string str1, string str2) {
        if (str1 == str2) {
            return true;
        }
        int cnt[26]{};
        int m = 0;
        for (char& c : str2) {
            if (++cnt[c - 'a'] == 1) {
                ++m;
            }
        }
        if (m == 26) {
            return false;
        }
        int d[26]{};
        for (int i = 0; i < str1.size(); ++i) {
            int a = str1[i] - 'a';
            int b = str2[i] - 'a';
            if (d[a] == 0) {
                d[a] = b + 1;
            } else if (d[a] != b + 1) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func canConvert(str1 string, str2 string) bool {
	if str1 == str2 {
		return true
	}
	s := map[rune]bool{}
	for _, c := range str2 {
		s[c] = true
		if len(s) == 26 {
			return false
		}
	}
	d := [26]int{}
	for i, c := range str1 {
		a, b := int(c-'a'), int(str2[i]-'a')
		if d[a] == 0 {
			d[a] = b + 1
		} else if d[a] != b+1 {
			return false
		}
	}
	return true
}
```

```ts
function canConvert(str1: string, str2: string): boolean {
    if (str1 === str2) {
        return true;
    }
    if (new Set(str2).size === 26) {
        return false;
    }
    const d: Map<string, string> = new Map();
    for (const [i, c] of str1.split('').entries()) {
        if (!d.has(c)) {
            d.set(c, str2[i]);
        } else if (d.get(c) !== str2[i]) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- end -->
