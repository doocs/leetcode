# [1967. 作为子字符串出现在单词中的字符串数目](https://leetcode.cn/problems/number-of-strings-that-appear-as-substrings-in-word)

[English Version](/solution/1900-1999/1967.Number%20of%20Strings%20That%20Appear%20as%20Substrings%20in%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>patterns</code> 和一个字符串 <code>word</code> ，统计 <code>patterns</code> 中有多少个字符串是 <code>word</code> 的子字符串。返回字符串数目。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>patterns = ["a","abc","bc","d"], word = "abc"
<strong>输出：</strong>3
<strong>解释：</strong>
- "a" 是 "<em><strong>a</strong></em>bc" 的子字符串。
- "abc" 是 "<em><strong>abc</strong></em>" 的子字符串。
- "bc" 是 "a<em><strong>bc</strong></em>" 的子字符串。
- "d" 不是 "abc" 的子字符串。
patterns 中有 3 个字符串作为子字符串出现在 word 中。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>patterns = ["a","b","c"], word = "aaaaabbbbb"
<strong>输出：</strong>2
<strong>解释：</strong>
- "a" 是 "a<em><strong>a</strong></em>aaabbbbb" 的子字符串。
- "b" 是 "aaaaabbbb<em><strong>b</strong></em>" 的子字符串。
- "c" 不是 "aaaaabbbbb" 的字符串。
patterns 中有 2 个字符串作为子字符串出现在 word 中。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>patterns = ["a","a","a"], word = "ab"
<strong>输出：</strong>3
<strong>解释：</strong>patterns 中的每个字符串都作为子字符串出现在 word "<em><strong>a</strong></em>b" 中。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= patterns.length &lt;= 100</code></li>
	<li><code>1 &lt;= patterns[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>patterns[i]</code> 和 <code>word</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

遍历字符串数组 $patterns$ 中的每个字符串 $p$，判断其是否为 $word$ 的子字符串，如果是，答案加一。

遍历结束后，返回答案。

时间复杂度 $O(n \times m)$，空间复杂度 $O(1)$。其中 $n$ 和 $m$ 分别为 $patterns$ 和 $word$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numOfStrings(self, patterns: List[str], word: str) -> int:
        return sum(p in word for p in patterns)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for (String p : patterns) {
            if (word.contains(p)) {
                ++ans;
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
    int numOfStrings(vector<string>& patterns, string word) {
        int ans = 0;
        for (auto& p : patterns) {
            ans += word.find(p) != string::npos;
        }
        return ans;
    }
};
```

### **Go**

```go
func numOfStrings(patterns []string, word string) (ans int) {
	for _, p := range patterns {
		if strings.Contains(word, p) {
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function numOfStrings(patterns: string[], word: string): number {
    let ans = 0;
    for (const p of patterns) {
        if (word.includes(p)) {
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
