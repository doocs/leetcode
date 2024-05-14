# [520. 检测大写字母](https://leetcode.cn/problems/detect-capital)

[English Version](/solution/0500-0599/0520.Detect%20Capital/README_EN.md)

<!-- tags:字符串 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>我们定义，在以下情况时，单词的大写用法是正确的：</p>

<ul>
	<li>全部字母都是大写，比如 <code>"USA"</code> 。</li>
	<li>单词中所有字母都不是大写，比如 <code>"leetcode"</code> 。</li>
	<li>如果单词不只含有一个字母，只有首字母大写，&nbsp;比如&nbsp;<code>"Google"</code> 。</li>
</ul>

<p>给你一个字符串 <code>word</code> 。如果大写用法正确，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "USA"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "FlaG"
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code> 由小写和大写英文字母组成</li>
</ul>

## 解法

### 方法一：统计大写字母的个数

我们可以统计字符串中大写字母的个数，然后根据大写字母的个数判断是否符合题目要求。

-   如果大写字母的个数为 0 或者等于字符串的长度，那么返回 `true`。
-   如果大写字母的个数为 1 并且第一个字母是大写字母，那么返回 `true`。
-   否则返回 `false`。

时间复杂度 $O(n)$，其中 $n$ 为字符串 `word` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def detectCapitalUse(self, word: str) -> bool:
        cnt = sum(c.isupper() for c in word)
        return cnt == 0 or cnt == len(word) or (cnt == 1 and word[0].isupper())
```

```java
class Solution {
    public boolean detectCapitalUse(String word) {
        int cnt = 0;
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                ++cnt;
            }
        }
        return cnt == 0 || cnt == word.length()
            || (cnt == 1 && Character.isUpperCase(word.charAt(0)));
    }
}
```

```cpp
class Solution {
public:
    bool detectCapitalUse(string word) {
        int cnt = count_if(word.begin(), word.end(), [](char c) { return isupper(c); });
        return cnt == 0 || cnt == word.length() || (cnt == 1 && isupper(word[0]));
    }
};
```

```go
func detectCapitalUse(word string) bool {
	cnt := 0
	for _, c := range word {
		if unicode.IsUpper(c) {
			cnt++
		}
	}
	return cnt == 0 || cnt == len(word) || (cnt == 1 && unicode.IsUpper(rune(word[0])))
}
```

```ts
function detectCapitalUse(word: string): boolean {
    const cnt = word.split('').reduce((acc, c) => acc + (c === c.toUpperCase() ? 1 : 0), 0);
    return cnt === 0 || cnt === word.length || (cnt === 1 && word[0] === word[0].toUpperCase());
}
```

<!-- tabs:end -->

<!-- end -->
