# [1684. 统计一致字符串的数目](https://leetcode.cn/problems/count-the-number-of-consistent-strings)

[English Version](/solution/1600-1699/1684.Count%20the%20Number%20of%20Consistent%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由不同字符组成的字符串 <code>allowed</code> 和一个字符串数组 <code>words</code> 。如果一个字符串的每一个字符都在 <code>allowed</code> 中，就称这个字符串是 <strong>一致字符串 </strong>。</p>

<p>请你返回 <code>words</code> 数组中 <strong>一致字符串</strong> 的数目。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
<b>输出：</b>2
<b>解释：</b>字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
<b>输出：</b>7
<b>解释：</b>所有字符串都是一致的。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
<b>输出：</b>4
<b>解释：</b>字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 10<sup>4</sup></code></li>
	<li><code>1 <= allowed.length <=<sup> </sup>26</code></li>
	<li><code>1 <= words[i].length <= 10</code></li>
	<li><code>allowed</code> 中的字符 <strong>互不相同</strong> 。</li>
	<li><code>words[i]</code> 和 <code>allowed</code> 只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表或数组**

一种比较直接的思路是，用哈希表或数组 $s$ 记录 `allowed` 中的字符。然后遍历 `words` 数组，对于每个字符串 $w$，判断其是否由 `allowed` 中的字符组成。若是，答案加一。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为 `words` 数组的长度，而 $C$ 为 `allowed` 字符集的大小。本题中 $C \leq 26$。

**方法二：位运算**

我们也可以仅用一个整数来表示每个字符串中字符的出现情况。其中，整数的二进制表示中的每一位表示一个字符是否出现。

我们简单地定义一个函数 $f(w)$，这个函数可以将一个字符串 $w$ 转换为一个整数。整数的二进制表示中的每一位表示一个字符是否出现。例如，字符串 `ab` 可以转换为整数 $3$，即二进制表示为 $11$。字符串 `abd` 可以转换为整数 $11$，即二进制表示为 $1011$。

回到题目上，判断一个字符串 $w$ 是否由 `allowed` 中的字符组成，就可以转换为：判断 $f(allowed)$ 和 $f(w)$ 进行按位或运算后的结果是否等于 $f(allowed)$。若是，答案加一。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countConsistentStrings(self, allowed: str, words: List[str]) -> int:
        s = set(allowed)
        return sum(all(c in s for c in w) for w in words)
```

```python
class Solution:
    def countConsistentStrings(self, allowed: str, words: List[str]) -> int:
        def f(w):
            return reduce(or_, (1 << (ord(c) - ord('a')) for c in w))

        mask = f(allowed)
        return sum((mask | f(w)) == mask for w in words)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] s = new boolean[26];
        for (char c : allowed.toCharArray()) {
            s[c - 'a'] = true;
        }
        int ans = 0;
        for (String w : words) {
            if (check(w, s)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(String w, boolean[] s) {
        for (int i = 0; i < w.length(); ++i) {
            if (!s[w.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        int mask = f(allowed);
        int ans = 0;
        for (String w : words) {
            if ((mask | f(w)) == mask) {
                ++ans;
            }
        }
        return ans;
    }

    private int f(String w) {
        int mask = 0;
        for (int i = 0; i < w.length(); ++i) {
            mask |= 1 << (w.charAt(i) - 'a');
        }
        return mask;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countConsistentStrings(string allowed, vector<string>& words) {
        bitset<26> s;
        for (auto& c : allowed) s[c - 'a'] = 1;
        int ans = 0;
        auto check = [&](string& w) {
            for (auto& c : w) if (!s[c - 'a']) return false;
            return true;
        };
        for (auto& w : words) ans += check(w);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int countConsistentStrings(string allowed, vector<string>& words) {
        auto f = [](string& w) {
            int mask = 0;
            for (auto& c : w) mask |= 1 << (c - 'a');
            return mask;
        };
        int mask = f(allowed);
        int ans = 0;
        for (auto& w : words) ans += (mask | f(w)) == mask;
        return ans;
    }
};
```

### **Go**

```go
func countConsistentStrings(allowed string, words []string) (ans int) {
	s := [26]bool{}
	for _, c := range allowed {
		s[c-'a'] = true
	}
	check := func(w string) bool {
		for _, c := range w {
			if !s[c-'a'] {
				return false
			}
		}
		return true
	}
	for _, w := range words {
		if check(w) {
			ans++
		}
	}
	return ans
}
```

```go
func countConsistentStrings(allowed string, words []string) (ans int) {
	f := func(w string) (mask int) {
		for _, c := range w {
			mask |= 1 << (c - 'a')
		}
		return
	}

	mask := f(allowed)
	for _, w := range words {
		if (mask | f(w)) == mask {
			ans++
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
