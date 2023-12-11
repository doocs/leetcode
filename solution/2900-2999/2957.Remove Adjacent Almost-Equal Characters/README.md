# [2957. 消除相邻近似相等字符](https://leetcode.cn/problems/remove-adjacent-almost-equal-characters)

[English Version](/solution/2900-2999/2957.Remove%20Adjacent%20Almost-Equal%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>word</code>&nbsp;。</p>

<p>一次操作中，你可以选择&nbsp;<code>word</code>&nbsp;中任意一个下标 <code>i</code>&nbsp;，将&nbsp;<code>word[i]</code> 修改成任意一个小写英文字母。</p>

<p>请你返回消除 <code>word</code>&nbsp;中所有相邻 <strong>近似相等</strong>&nbsp;字符的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>两个字符&nbsp;<code>a</code> 和&nbsp;<code>b</code>&nbsp;如果满足&nbsp;<code>a == b</code>&nbsp;或者&nbsp;<code>a</code> 和&nbsp;<code>b</code>&nbsp;在字母表中是相邻的，那么我们称它们是 <strong>近似相等</strong>&nbsp;字符。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>word = "aaaaa"
<b>输出：</b>2
<b>解释：</b>我们将 word 变为 "a<em><strong>c</strong></em>a<em><strong>c</strong></em>a" ，该字符串没有相邻近似相等字符。
消除 word 中所有相邻近似相等字符最少需要 2 次操作。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>word = "abddez"
<b>输出：</b>2
<b>解释：</b>我们将 word 变为 "<em><strong>y</strong></em>bd<em><strong>o</strong></em>ez" ，该字符串没有相邻近似相等字符。
消除 word 中所有相邻近似相等字符最少需要 2 次操作。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>word = "zyxyxyz"
<b>输出：</b>3
<b>解释：</b>我们将 word 变为 "z<em><strong>a</strong></em>x<em><strong>a</strong></em>x<em><strong>a</strong></em>z" ，该字符串没有相邻近似相等字符。
消除 word 中所有相邻近似相等字符最少需要 3 次操作
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们从下标 $1$ 开始遍历字符串 $word$，如果 $word[i]$ 和 $word[i - 1]$ 相邻近似相等，那么我们就贪心地将 $word[i]$ 替换成一个与 $word[i - 1]$ 和 $word[i + 1]$ 都不相等的字符（可以不执行替换操作，记录操作次数即可）。然后，我们跳过 $word[i + 1]$，继续遍历字符串 $word$。

最后，我们返回记录的操作次数。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $word$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeAlmostEqualCharacters(self, word: str) -> int:
        ans = 0
        i, n = 1, len(word)
        while i < n:
            if abs(ord(word[i]) - ord(word[i - 1])) < 2:
                ans += 1
                i += 2
            else:
                i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int removeAlmostEqualCharacters(String word) {
        int ans = 0, n = word.length();
        for (int i = 1; i < n; ++i) {
            if (Math.abs(word.charAt(i) - word.charAt(i - 1)) < 2) {
                ++ans;
                ++i;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int removeAlmostEqualCharacters(string word) {
        int ans = 0, n = word.size();
        for (int i = 1; i < n; ++i) {
            if (abs(word[i] - word[i - 1]) < 2) {
                ++ans;
                ++i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func removeAlmostEqualCharacters(word string) (ans int) {
	for i := 1; i < len(word); i++ {
		if abs(int(word[i])-int(word[i-1])) < 2 {
			ans++
			i++
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function removeAlmostEqualCharacters(word: string): number {
    let ans = 0;
    for (let i = 1; i < word.length; ++i) {
        if (Math.abs(word.charCodeAt(i) - word.charCodeAt(i - 1)) < 2) {
            ++ans;
            ++i;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
