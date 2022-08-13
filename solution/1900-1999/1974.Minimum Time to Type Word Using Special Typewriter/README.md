# [1974. 使用特殊打字机键入单词的最少时间](https://leetcode.cn/problems/minimum-time-to-type-word-using-special-typewriter)

[English Version](/solution/1900-1999/1974.Minimum%20Time%20to%20Type%20Word%20Using%20Special%20Typewriter/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个特殊打字机，它由一个 <strong>圆盘</strong> 和一个 <strong>指针</strong>&nbsp;组成， 圆盘上标有小写英文字母&nbsp;<code>'a'</code> 到&nbsp;<code>'z'</code>。<strong>只有</strong>&nbsp;当指针指向某个字母时，它才能被键入。指针 <strong>初始时</strong>&nbsp;指向字符 <code>'a'</code>&nbsp;。</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1974.Minimum%20Time%20to%20Type%20Word%20Using%20Special%20Typewriter/images/chart.jpg" style="width: 530px; height: 410px;" />
<p>每一秒钟，你可以执行以下操作之一：</p>

<ul>
	<li>将指针 <strong>顺时针</strong>&nbsp;或者 <b>逆时针</b>&nbsp;移动一个字符。</li>
	<li>键入指针 <strong>当前</strong>&nbsp;指向的字符。</li>
</ul>

<p>给你一个字符串&nbsp;<code>word</code>&nbsp;，请你返回键入&nbsp;<code>word</code>&nbsp;所表示单词的 <b>最少</b>&nbsp;秒数&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>word = "abc"
<b>输出：</b>5
<strong>解释：
</strong>单词按如下操作键入：
- 花 1 秒键入字符 'a' in 1 ，因为指针初始指向 'a' ，故不需移动指针。
- 花 1 秒将指针顺时针移到 'b' 。
- 花 1 秒键入字符 'b' 。
- 花 1 秒将指针顺时针移到 'c' 。
- 花 1 秒键入字符 'c' 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>word = "bza"
<b>输出：</b>7
<strong>解释：
</strong>单词按如下操作键入：
- 花 1 秒将指针顺时针移到 'b' 。
- 花 1 秒键入字符 'b' 。
- 花 2 秒将指针逆时针移到 'z' 。
- 花 1 秒键入字符 'z' 。
- 花 1 秒将指针顺时针移到 'a' 。
- 花 1 秒键入字符 'a' 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>word = "zjpc"
<b>输出：</b>34
<strong>解释：</strong>
单词按如下操作键入：
- 花 1 秒将指针逆时针移到 'z' 。
- 花 1 秒键入字符 'z' 。
- 花 10 秒将指针顺时针移到 'j' 。
- 花 1 秒键入字符 'j' 。
- 花 6 秒将指针顺时针移到 'p' 。
- 花 1 秒键入字符 'p' 。
- 花 13 秒将指针逆时针移到 'c' 。
- 花 1 秒键入字符 'c' 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minTimeToType(self, word: str) -> int:
        ans = prev = 0
        for c in word:
            curr = ord(c) - ord('a')
            t = abs(prev - curr)
            t = min(t, 26 - t)
            ans += t + 1
            prev = curr
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minTimeToType(String word) {
        int ans = 0;
        int prev = 0;
        for (char c : word.toCharArray()) {
            int curr = c - 'a';
            int t = Math.abs(prev - curr);
            t = Math.min(t, 26 - t);
            ans += t + 1;
            prev = curr;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minTimeToType(string word) {
        int ans = 0;
        int prev = 0;
        for (char& c : word) {
            int curr = c - 'a';
            int t = abs(prev - curr);
            t = min(t, 26 - t);
            ans += t + 1;
            prev = curr;
        }
        return ans;
    }
};
```

### **Go**

```go
func minTimeToType(word string) int {
	ans, prev := 0, 0
	for _, c := range word {
		curr := int(c - 'a')
		t := abs(prev - curr)
		t = min(t, 26-t)
		ans += t + 1
		prev = curr
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
