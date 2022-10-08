# [1668. 最大重复子字符串](https://leetcode.cn/problems/maximum-repeating-substring)

[English Version](/solution/1600-1699/1668.Maximum%20Repeating%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>sequence</code> ，如果字符串 <code>word</code> 连续重复 <code>k</code> 次形成的字符串是 <code>sequence</code> 的一个子字符串，那么单词 <code>word</code> 的 <strong>重复值为 <code>k</code></strong><strong> </strong>。单词 <code>word</code> 的 <strong>最</strong><strong>大重复值</strong> 是单词 <code>word</code> 在 <code>sequence</code> 中最大的重复值。如果 <code>word</code> 不是 <code>sequence</code> 的子串，那么重复值 <code>k</code> 为 <code>0</code> 。</p>

<p>给你一个字符串 <code>sequence</code> 和 <code>word</code> ，请你返回 <strong>最大重复值 <code>k</code> </strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>sequence = "ababc", word = "ab"
<b>输出：</b>2
<strong>解释：</strong>"abab" 是 "<strong>abab</strong>c" 的子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>sequence = "ababc", word = "ba"
<b>输出：</b>1
<strong>解释：</strong>"ba" 是 "a<strong>ba</strong>bc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>sequence = "ababc", word = "ac"
<b>输出：</b>0
<strong>解释：</strong>"ac" 不是 "ababc" 的子字符串。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= sequence.length <= 100</code></li>
	<li><code>1 <= word.length <= 100</code></li>
	<li><code>sequence</code> 和 <code>word</code> 都只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力枚举**

直接从大到小枚举 `word` 的重复次数，判断 `word` 重复该次数后是否是 `sequence` 的子串即可。

时间复杂度为 $O(n^2)$，其中 $n$ 为 `sequence` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxRepeating(self, sequence: str, word: str) -> int:
        x = len(sequence) // len(word)
        for k in range(x, 0, -1):
            if word * k in sequence:
                return k
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxRepeating(String sequence, String word) {
        int x = sequence.length() / word.length();
        for (int k = x; k > 0; --k) {
            if (sequence.contains(word.repeat(k))) {
                return k;
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxRepeating(string sequence, string word) {
        int ans = 0;
        string t = word;
        int x = sequence.size() / word.size();
        for (int k = 1; k <= x; ++k) {
            if (sequence.find(t) != string::npos) {
                ans = k;
            }
            t += word;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxRepeating(sequence string, word string) int {
	x := len(sequence) / len(word)
	for k := x; k > 0; k-- {
		if strings.Contains(sequence, strings.Repeat(word, k)) {
			return k
		}
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
