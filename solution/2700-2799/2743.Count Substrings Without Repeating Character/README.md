# [2743. Count Substrings Without Repeating Character](https://leetcode.cn/problems/count-substrings-without-repeating-character)

[English Version](/solution/2700-2799/2743.Count%20Substrings%20Without%20Repeating%20Character/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a string <code>s</code> consisting only of lowercase English letters. We call a substring <b>special</b> if it contains no character which has occurred at least twice (in other words, it does not contain a repeating character). Your task is to count the number of <b>special</b> substrings. For example, in the string <code>&quot;pop&quot;</code>, the substring <code>&quot;po&quot;</code> is a <strong>special</strong> substring, however, <code>&quot;pop&quot;</code> is not <strong>special</strong> (since <code>&#39;p&#39;</code> has occurred twice).</p>

<p>Return <em>the number of <b>special</b> substrings.</em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string. For example, <code>&quot;abc&quot;</code> is a substring of <code>&quot;abcd&quot;</code>, but <code>&quot;acd&quot;</code> is not.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;
<strong>Output:</strong> 10
<strong>Explanation:</strong> Since each character occurs once, every substring is a special substring. We have 4 substrings of length one, 3 of length two, 2 of length three, and 1 substring of length four. So overall there are 4 + 3 + 2 + 1 = 10 special substrings.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ooo&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Any substring with a length of at least two contains a repeating character. So we have to count the number of substrings of length one, which is 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abab&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> Special substrings are as follows (sorted by their start positions):
Special substrings of length 1: &quot;a&quot;, &quot;b&quot;, &quot;a&quot;, &quot;b&quot;
Special substrings of length 2: &quot;ab&quot;, &quot;ba&quot;, &quot;ab&quot;
And it can be shown that there are no special substrings with a length of at least three. So the answer would be 4 + 3 = 7.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 双指针**

我们用两个指针 $j$ 和 $i$ 分别表示当前子串的左右边界，用一个长度为 $26$ 的数组 $cnt$ 统计当前子串中每个字符出现的次数。我们从左到右遍历字符串，每次遍历到位置 $i$ 时，将 $s[i]$ 出现的次数加一，然后判断 $s[i]$ 是否出现了至少两次，如果是，那么我们需要将 $s[j]$ 出现的次数减一，并将 $j$ 右移一位，直到 $s[i]$ 出现的次数不超过一次为止。这样一来，我们就得到以 $s[i]$ 结尾的最长特殊子串的长度，即 $i - j + 1$，那么以 $s[i]$ 结尾的特殊子串的数量就是 $i - j + 1$。最后我们将每个位置结尾的特殊子串的数量累加起来，即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串 $s$ 的长度；而 $C$ 是字符集的大小，这里字符集为小写英文字母，因此 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfSpecialSubstrings(self, s: str) -> int:
        cnt = Counter()
        ans = j = 0
        for i, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 1:
                cnt[s[j]] -= 1
                j += 1
            ans += i - j + 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfSpecialSubstrings(String s) {
        int n = s.length();
        int ans = 0;
        int[] cnt = new int[26];
        for (int i = 0, j = 0; i < n; ++i) {
            int k = s.charAt(i) - 'a';
            ++cnt[k];
            while (cnt[k] > 1) {
                --cnt[s.charAt(j++) - 'a'];
            }
            ans += i - j + 1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfSpecialSubstrings(string s) {
        int n = s.size();
        int cnt[26]{};
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            int k = s[i] - 'a';
            ++cnt[k];
            while (cnt[k] > 1) {
                --cnt[s[j++] - 'a'];
            }
            ans += i - j + 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfSpecialSubstrings(s string) (ans int) {
	j := 0
	cnt := [26]int{}
	for i, c := range s {
		k := c - 'a'
		cnt[k]++
		for cnt[k] > 1 {
			cnt[s[j]-'a']--
			j++
		}
		ans += i - j + 1
	}
	return
}
```

### **TypeScript**

```ts
function numberOfSpecialSubstrings(s: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const n = s.length;
    const cnt: number[] = Array(26).fill(0);
    let ans = 0;
    for (let i = 0, j = 0; i < n; ++i) {
        const k = idx(s[i]);
        ++cnt[k];
        while (cnt[k] > 1) {
            --cnt[idx(s[j++])];
        }
        ans += i - j + 1;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
