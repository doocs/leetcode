# [2207. 字符串中最多数目的子字符串](https://leetcode.cn/problems/maximize-number-of-subsequences-in-a-string)

[English Version](/solution/2200-2299/2207.Maximize%20Number%20of%20Subsequences%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>text</code>&nbsp;和另一个下标从 <strong>0</strong>&nbsp;开始且长度为 <code>2</code>&nbsp;的字符串&nbsp;<code>pattern</code>&nbsp;，两者都只包含小写英文字母。</p>

<p>你可以在 <code>text</code>&nbsp;中任意位置插入 <strong>一个</strong> 字符，这个插入的字符必须是&nbsp;<code>pattern[0]</code>&nbsp;<b>或者</b>&nbsp;<code>pattern[1]</code>&nbsp;。注意，这个字符可以插入在 <code>text</code>&nbsp;开头或者结尾的位置。</p>

<p>请你返回插入一个字符后，<code>text</code>&nbsp;中最多包含多少个等于 <code>pattern</code>&nbsp;的 <strong>子序列</strong>&nbsp;。</p>

<p><strong>子序列</strong> 指的是将一个字符串删除若干个字符后（也可以不删除），剩余字符保持原本顺序得到的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>text = "abdcdbc", pattern = "ac"
<b>输出：</b>4
<strong>解释：</strong>
如果我们在 text[1] 和 text[2] 之间添加 pattern[0] = 'a' ，那么我们得到 "ab<em><strong>a</strong></em>dcdbc" 。那么 "ac" 作为子序列出现 4 次。
其他得到 4 个 "ac" 子序列的方案还有 "<em><strong>a</strong></em>abdcdbc" 和 "abd<em><strong>a</strong></em>cdbc" 。
但是，"abdc<em><strong>a</strong></em>dbc" ，"abd<em><strong>c</strong></em>cdbc" 和 "abdcdbc<em><strong>c</strong></em>" 这些字符串虽然是可行的插入方案，但是只出现了 3 次 "ac" 子序列，所以不是最优解。
可以证明插入一个字符后，无法得到超过 4 个 "ac" 子序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>text = "aabb", pattern = "ab"
<b>输出：</b>6
<strong>解释：</strong>
可以得到 6 个 "ab" 子序列的部分方案为 "<em><strong>a</strong></em>aabb" ，"aa<em><strong>a</strong></em>bb" 和 "aab<em><strong>b</strong></em>b" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10<sup>5</sup></code></li>
	<li><code>pattern.length == 2</code></li>
	<li><code>text</code> 和&nbsp;<code>pattern</code>&nbsp;都只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumSubsequenceCount(self, text: str, pattern: str) -> int:
        ans = 0
        cnt = Counter()
        for c in text:
            if c == pattern[1]:
                ans += cnt[pattern[0]]
            cnt[c] += 1
        ans += max(cnt[pattern[0]], cnt[pattern[1]])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        int[] cnt = new int[26];
        char a = pattern.charAt(0);
        char b = pattern.charAt(1);
        long ans = 0;
        for (char c : text.toCharArray()) {
            if (c == b) {
                ans += cnt[a - 'a'];
            }
            cnt[c - 'a']++;
        }
        ans += Math.max(cnt[a - 'a'], cnt[b - 'a']);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maximumSubsequenceCount(string text, string pattern) {
        long long ans = 0;
        char a = pattern[0], b = pattern[1];
        vector<int> cnt(26);
        for (char& c : text) {
            if (c == b) ans += cnt[a - 'a'];
            cnt[c - 'a']++;
        }
        ans += max(cnt[a - 'a'], cnt[b - 'a']);
        return ans;
    }
};
```

### **Go**

```go
func maximumSubsequenceCount(text string, pattern string) int64 {
	ans := 0
	cnt := make([]int, 26)
	a, b := pattern[0], pattern[1]
	for i := range text {
		c := text[i]
		if c == b {
			ans += cnt[a-'a']
		}
		cnt[c-'a']++
	}
	ans += max(cnt[a-'a'], cnt[b-'a'])
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
