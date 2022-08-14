# [1915. 最美子字符串的数目](https://leetcode.cn/problems/number-of-wonderful-substrings)

[English Version](/solution/1900-1999/1915.Number%20of%20Wonderful%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果某个字符串中 <strong>至多一个</strong> 字母出现 <strong>奇数</strong> 次，则称其为 <strong>最美</strong> 字符串。</p>

<ul>
	<li>例如，<code>"ccjjc"</code> 和 <code>"abab"</code> 都是最美字符串，但 <code>"ab"</code> 不是。</li>
</ul>

<p>给你一个字符串 <code>word</code> ，该字符串由前十个小写英文字母组成（<code>'a'</code> 到 <code>'j'</code>）。请你返回 <code>word</code> 中 <strong>最美非空子字符串</strong> 的数目<em>。</em>如果同样的子字符串在<em> </em><code>word</code> 中出现多次，那么应当对 <strong>每次出现</strong> 分别计数<em>。</em></p>

<p><strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "aba"
<strong>输出：</strong>4
<strong>解释：</strong>4 个最美子字符串如下所示：
- "<strong>a</strong>ba" -> "a"
- "a<strong>b</strong>a" -> "b"
- "ab<strong>a</strong>" -> "a"
- "<strong>aba</strong>" -> "aba"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "aabb"
<strong>输出：</strong>9
<strong>解释：</strong>9 个最美子字符串如下所示：
- "<strong>a</strong>abb" -> "a"
- "<strong>aa</strong>bb" -> "aa"
- "<strong>aab</strong>b" -> "aab"
- "<strong>aabb</strong>" -> "aabb"
- "a<strong>a</strong>bb" -> "a"
- "a<strong>abb</strong>" -> "abb"
- "aa<strong>b</strong>b" -> "b"
- "aa<strong>bb</strong>" -> "bb"
- "aab<strong>b</strong>" -> "b"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word = "he"
<strong>输出：</strong>2
<strong>解释：</strong>2 个最美子字符串如下所示：
- "<b>h</b>e" -> "h"
- "h<strong>e</strong>" -> "e"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word.length <= 10<sup>5</sup></code></li>
	<li><code>word</code> 由从 <code>'a'</code> 到 <code>'j'</code> 的小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

状态压缩 + 前缀和。

相似题目：[1371. 每个元音包含偶数次的最长子字符串](/solution/1300-1399/1371.Find%20the%20Longest%20Substring%20Containing%20Vowels%20in%20Even%20Counts/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def wonderfulSubstrings(self, word: str) -> int:
        counter = Counter({0: 1})
        state = 0
        ans = 0
        for c in word:
            state ^= 1 << (ord(c) - ord('a'))
            ans += counter[state]
            for i in range(10):
                ans += counter[state ^ (1 << i)]
            counter[state] += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long wonderfulSubstrings(String word) {
        int[] counter = new int[1 << 10];
        counter[0] = 1;
        int state = 0;
        long ans = 0;
        for (char c : word.toCharArray()) {
            state ^= (1 << (c - 'a'));
            ans += counter[state];
            for (int i = 0; i < 10; ++i) {
                ans += counter[state ^ (1 << i)];
            }
            ++counter[state];
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} word
 * @return {number}
 */
var wonderfulSubstrings = function (word) {
    let counter = new Array(1 << 10).fill(0);
    counter[0] = 1;
    let state = 0;
    let ans = 0;
    for (let c of word) {
        state ^= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        ans += counter[state];
        for (let i = 0; i < 10; ++i) {
            ans += counter[state ^ (1 << i)];
        }
        ++counter[state];
    }
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    long long wonderfulSubstrings(string word) {
        vector<int> counter(1024);
        counter[0] = 1;
        long long ans = 0;
        int state = 0;
        for (char c : word) {
            state ^= (1 << (c - 'a'));
            ans += counter[state];
            for (int i = 0; i < 10; ++i) ans += counter[state ^ (1 << i)];
            ++counter[state];
        }
        return ans;
    }
};
```

### **Go**

```go
func wonderfulSubstrings(word string) int64 {
	counter := make([]int, 1024)
	counter[0] = 1
	state := 0
	var ans int64
	for _, c := range word {
		state ^= (1 << (c - 'a'))
		ans += int64(counter[state])
		for i := 0; i < 10; i++ {
			ans += int64(counter[state^(1<<i)])
		}
		counter[state]++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
