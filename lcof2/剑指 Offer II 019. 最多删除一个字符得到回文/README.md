# [剑指 Offer II 019. 最多删除一个字符得到回文](https://leetcode.cn/problems/RQku0D)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空字符串&nbsp;<code>s</code>，请判断如果&nbsp;<strong>最多 </strong>从字符串中删除一个字符能否得到一个回文字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;aba&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;abca&quot;
<strong>输出:</strong> true
<strong>解释:</strong> 可以删除 &quot;c&quot; 字符 或者 &quot;b&quot; 字符
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;abc&quot;
<strong>输出:</strong> false</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 680&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/valid-palindrome-ii/">https://leetcode.cn/problems/valid-palindrome-ii/</a></p>

## 解法

### 方法一：双指针

我们用两个指针 $i$ 和 $j$ 分别指向字符串 $s$ 的第一个字符和最后一个字符，然后向中间移动指针，每次判断 $s[i]$ 和 $s[j]$ 是否相等：

-   如果 $s[i] = s[j]$，则指针 $i$ 向后移动一位，指针 $j$ 向前移动一位；
-   否则，存在两种情况，即删除字符 $s[i]$ 或者删除字符 $s[j]$，然后判断删除之后的字符串是否是回文字符串。即判断子串 $s[i+1..j]$ 或者子串 $s[i..j-1]$ 是否是回文字符串。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def validPalindrome(self, s: str) -> bool:
        def check(i: int, j: int) -> bool:
            while i < j:
                if s[i] != s[j]:
                    return False
                i, j = i + 1, j - 1
            return True

        i, j = 0, len(s) - 1
        while i < j:
            if s[i] != s[j]:
                return check(i + 1, j) or check(i, j - 1)
            i, j = i + 1, j - 1
        return True
```

```java
class Solution {
    private String s;

    public boolean validPalindrome(String s) {
        this.s = s;
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return check(i + 1, j) || check(i, j - 1);
            }
        }
        return true;
    }

    private boolean check(int i, int j) {
        for (; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
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
    bool validPalindrome(string s) {
        auto check = [&](int i, int j) {
            for (; i < j; ++i, --j) {
                if (s[i] != s[j]) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            if (s[i] != s[j]) {
                return check(i + 1, j) || check(i, j - 1);
            }
        }
        return true;
    }
};
```

```go
func validPalindrome(s string) bool {
	check := func(i, j int) bool {
		for ; i < j; i, j = i+1, j-1 {
			if s[i] != s[j] {
				return false
			}
		}
		return true
	}
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		if s[i] != s[j] {
			return check(i+1, j) || check(i, j-1)
		}
	}
	return true
}
```

```ts
function validPalindrome(s: string): boolean {
    const check = (i: number, j: number): boolean => {
        for (; i < j; ++i, --j) {
            if (s[i] !== s[j]) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s[i] !== s[j]) {
            return check(i + 1, j) || check(i, j - 1);
        }
    }
    return true;
}
```

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var validPalindrome = function (s) {
    const check = (i, j) => {
        for (; i < j; ++i, --j) {
            if (s[i] !== s[j]) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s[i] !== s[j]) {
            return check(i + 1, j) || check(i, j - 1);
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- end -->
