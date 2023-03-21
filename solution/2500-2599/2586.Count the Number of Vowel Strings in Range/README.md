# [2586. 统计范围内的元音字符串数](https://leetcode.cn/problems/count-the-number-of-vowel-strings-in-range)

[English Version](/solution/2500-2599/2586.Count%20the%20Number%20of%20Vowel%20Strings%20in%20Range/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串数组 <code>words</code> 和两个整数：<code>left</code> 和 <code>right</code> 。</p>

<p>如果字符串以元音字母开头并以元音字母结尾，那么该字符串就是一个 <strong>元音字符串</strong> ，其中元音字母是 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code>、<code>'u'</code> 。</p>

<p>返回<em> </em><code>words[i]</code> 是元音字符串的数目，其中<em> </em><code>i</code> 在闭区间 <code>[left, right]</code> 内。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["are","amy","u"], left = 0, right = 2
<strong>输出：</strong>2
<strong>解释：</strong>
- "are" 是一个元音字符串，因为它以 'a' 开头并以 'e' 结尾。
- "amy" 不是元音字符串，因为它没有以元音字母结尾。
- "u" 是一个元音字符串，因为它以 'u' 开头并以 'u' 结尾。
在上述范围中的元音字符串数目为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["hey","aeo","mu","ooo","artro"], left = 1, right = 4
<strong>输出：</strong>3
<strong>解释：</strong>
- "aeo" 是一个元音字符串，因为它以 'a' 开头并以 'o' 结尾。
- "mu" 不是元音字符串，因为它没有以元音字母开头。
- "ooo" 是一个元音字符串，因为它以 'o' 开头并以 'o' 结尾。
- "artro" 是一个元音字符串，因为它以 'a' 开头并以 'o' 结尾。
在上述范围中的元音字符串数目为 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
	<li><code>0 &lt;= left &lt;= right &lt; words.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们只需要遍历区间 $[left,.. right]$ 内的字符串，判断其是否以元音字母开头和结尾即可。若是，则答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(m)$，空间复杂度 $O(1)$。其中 $m = right - left + 1$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def vowelStrings(self, words: List[str], left: int, right: int) -> int:
        return sum(w[0] in 'aeiou' and w[-1] in 'aeiou' for w in words[left: right + 1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            var w = words[i];
            if (check(w.charAt(0)) && check(w.charAt(w.length() - 1))) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int vowelStrings(vector<string>& words, int left, int right) {
        auto check = [](char c) -> bool {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        };
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            auto w = words[i];
            ans += check(w[0]) && check(w[w.size() - 1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func vowelStrings(words []string, left int, right int) (ans int) {
	check := func(c byte) bool {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
	}
	for _, w := range words[left : right+1] {
		if check(w[0]) && check(w[len(w)-1]) {
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function vowelStrings(words: string[], left: number, right: number): number {
    let ans = 0;
    const check: string[] = ['a', 'e', 'i', 'o', 'u'];
    for (let i = left; i <= right; ++i) {
        var w = words[i];
        if (check.includes(w[0]) && check.includes(w[w.length - 1])) {
            ++ans;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
