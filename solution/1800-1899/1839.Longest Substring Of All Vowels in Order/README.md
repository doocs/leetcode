# [1839. 所有元音按顺序排布的最长子字符串](https://leetcode.cn/problems/longest-substring-of-all-vowels-in-order)

[English Version](/solution/1800-1899/1839.Longest%20Substring%20Of%20All%20Vowels%20in%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>当一个字符串满足如下条件时，我们称它是 <b>美丽的</b> ：</p>

<ul>
	<li>所有 5 个英文元音字母（<code>'a'</code> ，<code>'e'</code> ，<code>'i'</code> ，<code>'o'</code> ，<code>'u'</code>）都必须 <strong>至少</strong> 出现一次。</li>
	<li>这些元音字母的顺序都必须按照 <strong>字典序</strong> 升序排布（也就是说所有的 <code>'a'</code> 都在 <code>'e'</code> 前面，所有的 <code>'e'</code> 都在 <code>'i'</code> 前面，以此类推）</li>
</ul>

<p>比方说，字符串 <code>"aeiou"</code> 和 <code>"aaaaaaeiiiioou"</code> 都是 <strong>美丽的</strong> ，但是 <code>"uaeio"</code> ，<code>"aeoiu"</code> 和 <code>"aaaeeeooo"</code> <strong>不是美丽的</strong> 。</p>

<p>给你一个只包含英文元音字母的字符串 <code>word</code> ，请你返回 <code>word</code> 中 <strong>最长美丽子字符串的长度</strong> 。如果不存在这样的子字符串，请返回 <code>0</code> 。</p>

<p><strong>子字符串</strong> 是字符串中一个连续的字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>word = "aeiaaio<strong>aaaaeiiiiouuu</strong>ooaauuaeiu"
<b>输出：</b>13
<b>解释：</b>最长子字符串是 "aaaaeiiiiouuu" ，长度为 13 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>word = "aeeeiiiioooauuu<strong>aeiou</strong>"
<b>输出：</b>5
<b>解释：</b>最长子字符串是 "aeiou" ，长度为 5 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>word = "a"
<b>输出：</b>0
<b>解释：</b>没有美丽子字符串，所以返回 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word.length <= 5 * 10<sup>5</sup></code></li>
	<li><code>word</code> 只包含字符 <code>'a'</code>，<code>'e'</code>，<code>'i'</code>，<code>'o'</code> 和 <code>'u'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针 + 模拟**

我们可以先将字符串 `word` 做个转化，比如对于 `word="aaaeiouu"`，我们可以将其转化为数据项 `('a', 3)`, `('e', 1)`, `('i', 1)`, `('o', 1)`, `('u', 2)`，存放在数组 `arr` 中。其中每个数据项的第一个元素表示元音字母，第二个元素表示该元音字母连续出现的次数。这部分转化可以通过双指针来实现。

接下来，我们遍历数组 `arr`，每次取相邻的 $5$ 个数据项，判断这些数据项中的元音字母是否分别为 `'a'`, `'e'`, `'i'`, `'o'`, `'u'`，如果是，则计算这 $5$ 个数据项中元音字母的总次数，即为当前的美丽子字符串的长度，更新答案的最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `word` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestBeautifulSubstring(self, word: str) -> int:
        arr = []
        n = len(word)
        i = 0
        while i < n:
            j = i
            while j < n and word[j] == word[i]:
                j += 1
            arr.append((word[i], j - i))
            i = j
        ans = 0
        for i in range(len(arr) - 4):
            a, b, c, d, e = arr[i : i + 5]
            if a[0] + b[0] + c[0] + d[0] + e[0] == "aeiou":
                ans = max(ans, a[1] + b[1] + c[1] + d[1] + e[1])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestBeautifulSubstring(String word) {
        int n = word.length();
        List<Node> arr = new ArrayList<>();
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && word.charAt(j) == word.charAt(i)) {
                ++j;
            }
            arr.add(new Node(word.charAt(i), j - i));
            i = j;
        }
        int ans = 0;
        for (int i = 0; i < arr.size() - 4; ++i) {
            Node a = arr.get(i), b = arr.get(i + 1), c = arr.get(i + 2), d = arr.get(i + 3),
                 e = arr.get(i + 4);
            if (a.c == 'a' && b.c == 'e' && c.c == 'i' && d.c == 'o' && e.c == 'u') {
                ans = Math.max(ans, a.v + b.v + c.v + d.v + e.v);
            }
        }
        return ans;
    }
}

class Node {
    char c;
    int v;

    Node(char c, int v) {
        this.c = c;
        this.v = v;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestBeautifulSubstring(string word) {
        vector<pair<char, int>> arr;
        int n = word.size();
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && word[j] == word[i]) ++j;
            arr.push_back({word[i], j - i});
            i = j;
        }
        int ans = 0;
        for (int i = 0; i < (int) arr.size() - 4; ++i) {
            auto& [a, v1] = arr[i];
            auto& [b, v2] = arr[i + 1];
            auto& [c, v3] = arr[i + 2];
            auto& [d, v4] = arr[i + 3];
            auto& [e, v5] = arr[i + 4];
            if (a == 'a' && b == 'e' && c == 'i' && d == 'o' && e == 'u') {
                ans = max(ans, v1 + v2 + v3 + v4 + v5);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestBeautifulSubstring(word string) (ans int) {
	arr := []pair{}
	n := len(word)
	for i := 0; i < n; {
		j := i
		for j < n && word[j] == word[i] {
			j++
		}
		arr = append(arr, pair{word[i], j - i})
		i = j
	}
	for i := 0; i < len(arr)-4; i++ {
		a, b, c, d, e := arr[i], arr[i+1], arr[i+2], arr[i+3], arr[i+4]
		if a.c == 'a' && b.c == 'e' && c.c == 'i' && d.c == 'o' && e.c == 'u' {
			ans = max(ans, a.v+b.v+c.v+d.v+e.v)
		}
	}
	return
}

type pair struct {
	c byte
	v int
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
