---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3035.Maximum%20Palindromes%20After%20Operations/README.md
rating: 1856
tags:
    - 贪心
    - 数组
    - 哈希表
    - 字符串
    - 计数
    - 排序
---

# [3035. 回文字符串的最大数量](https://leetcode.cn/problems/maximum-palindromes-after-operations)

[English Version](/solution/3000-3099/3035.Maximum%20Palindromes%20After%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串数组 <code>words</code> ，数组的长度为 <code>n</code> ，且包含下标从 <strong>0</strong> 开始的若干字符串。</p>

<p>你可以执行以下操作 <strong>任意 </strong>次数（<strong>包括零次</strong>）：</p>

<ul>
	<li>选择整数<code>i</code>、<code>j</code>、<code>x</code>和<code>y</code>，满足<code>0 &lt;= i, j &lt; n</code>，<code>0 &lt;= x &lt; words[i].length</code>，<code>0 &lt;= y &lt; words[j].length</code>，<strong>交换 </strong>字符 <code>words[i][x]</code> 和 <code>words[j][y]</code> 。</li>
</ul>

<p>返回一个整数，表示在执行一些操作后，<code>words</code> 中可以包含的<span data-keyword="palindrome-string">回文串</span>的 <strong>最大 </strong>数量。</p>

<p><strong>注意：</strong>在操作过程中，<code>i</code> 和 <code>j</code> 可以相等。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["abbb","ba","aa"]
<strong>输出：</strong>3
<strong>解释：</strong>在这个例子中，获得最多回文字符串的一种方式是：
选择 i = 0, j = 1, x = 0, y = 0，交换 words[0][0] 和 words[1][0] 。words 变成了 ["bbbb","aa","aa"] 。
words 中的所有字符串都是回文。
因此，可实现的回文字符串的最大数量是 3 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["abc","ab"]
<strong>输出：</strong>2
<strong>解释：</strong>在这个例子中，获得最多回文字符串的一种方式是： 
选择 i = 0, j = 1, x = 1, y = 0，交换 words[0][1] 和 words[1][0] 。words 变成了 ["aac","bb"] 。
选择 i = 0, j = 0, x = 1, y = 2，交换 words[0][1] 和 words[0][2] 。words 变成了 ["aca","bb"] 。
两个字符串都是回文 。
因此，可实现的回文字符串的最大数量是 2。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["cd","ef","a"]
<strong>输出：</strong>1
<strong>解释：</strong>在这个例子中，没有必要执行任何操作。
words 中有一个回文 "a" 。
可以证明，在执行任何次数操作后，无法得到更多回文。
因此，答案是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def maxPalindromesAfterOperations(self, words: List[str]) -> int:
        s = mask = 0
        for w in words:
            s += len(w)
            for c in w:
                mask ^= 1 << (ord(c) - ord("a"))
        s -= mask.bit_count()
        words.sort(key=len)
        ans = 0
        for w in words:
            s -= len(w) // 2 * 2
            if s < 0:
                break
            ans += 1
        return ans
```

```java
class Solution {
    public int maxPalindromesAfterOperations(String[] words) {
        int s = 0, mask = 0;
        for (var w : words) {
            s += w.length();
            for (var c : w.toCharArray()) {
                mask ^= 1 << (c - 'a');
            }
        }
        s -= Integer.bitCount(mask);
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int ans = 0;
        for (var w : words) {
            s -= w.length() / 2 * 2;
            if (s < 0) {
                break;
            }
            ++ans;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxPalindromesAfterOperations(vector<string>& words) {
        int s = 0, mask = 0;
        for (const auto& w : words) {
            s += w.length();
            for (char c : w) {
                mask ^= 1 << (c - 'a');
            }
        }
        s -= __builtin_popcount(mask);
        sort(words.begin(), words.end(), [](const string& a, const string& b) { return a.length() < b.length(); });
        int ans = 0;
        for (const auto& w : words) {
            s -= w.length() / 2 * 2;
            if (s < 0) {
                break;
            }
            ++ans;
        }
        return ans;
    }
};
```

```go
func maxPalindromesAfterOperations(words []string) (ans int) {
	var s, mask int
	for _, w := range words {
		s += len(w)
		for _, c := range w {
			mask ^= 1 << (c - 'a')
		}
	}
	s -= bits.OnesCount(uint(mask))
	sort.Slice(words, func(i, j int) bool {
		return len(words[i]) < len(words[j])
	})
	for _, w := range words {
		s -= len(w) / 2 * 2
		if s < 0 {
			break
		}
		ans++
	}
	return
}
```

```ts
function maxPalindromesAfterOperations(words: string[]): number {
    let s: number = 0;
    let mask: number = 0;
    for (const w of words) {
        s += w.length;
        for (const c of w) {
            mask ^= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        }
    }
    s -= (mask.toString(2).match(/1/g) || []).length;
    words.sort((a, b) => a.length - b.length);
    let ans: number = 0;
    for (const w of words) {
        s -= Math.floor(w.length / 2) * 2;
        if (s < 0) {
            break;
        }
        ans++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
