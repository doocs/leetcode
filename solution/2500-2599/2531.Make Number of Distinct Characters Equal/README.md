# [2531. 使字符串总不同字符的数目相等](https://leetcode.cn/problems/make-number-of-distinct-characters-equal)

[English Version](/solution/2500-2599/2531.Make%20Number%20of%20Distinct%20Characters%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong> 开始的字符串 <code>word1</code> 和 <code>word2</code> 。</p>

<p>一次 <strong>移动</strong> 由以下两个步骤组成：</p>

<ul>
	<li>选中两个下标&nbsp;<code>i</code> 和 <code>j</code> ，分别满足 <code>0 &lt;= i &lt; word1.length</code> 和 <code>0 &lt;= j &lt; word2.length</code> ，</li>
	<li>交换 <code>word1[i]</code> 和 <code>word2[j]</code> 。</li>
</ul>

<p>如果可以通过 <strong>恰好一次</strong> 移动，使 <code>word1</code> 和 <code>word2</code> 中不同字符的数目相等，则返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>word1 = "ac", word2 = "b"
<strong>输出：</strong>false
<strong>解释：</strong>交换任何一组下标都会导致第一个字符串中有 2 个不同的字符，而在第二个字符串中只有 1 个不同字符。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>word1 = "abcc", word2 = "aab"
<strong>输出：</strong>true
<strong>解释：</strong>交换第一个字符串的下标 2 和第二个字符串的下标 0 。之后得到 word1 = "abac" 和 word2 = "cab" ，各有 3 个不同字符。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>word1 = "abcde", word2 = "fghij"
<strong>输出：</strong>true
<strong>解释：</strong>无论交换哪一组下标，两个字符串中都会有 5 个不同字符。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word1</code> 和 <code>word2</code> 仅由小写英文字母组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 枚举**

我们先用两个长度为 $26$ 的数组分别统计字符串 $word1$ 和 $word2$ 中每个字母的出现次数，记为 $cnt1$ 和 $cnt2$。

然后我们枚举 $cnt1$ 中的每个字母，接着枚举 $cnt2$ 中的每个字母，如果 $cnt1[i]$ 和 $cnt2[j]$ 都不为 $0$，那么我们就可以交换 $word1$ 中的第 $i$ 个字母和 $word2$ 中的第 $j$ 个字母。如果交换后 $word1$ 和 $word2$ 中不同字母的数目相等，那么就返回 `true`，否则，我们撤销此次交换，继续枚举。

如果枚举完所有的字母对，仍然没有找到一种交换方式，那么就返回 `false`。

时间复杂度 $O(n + C^3)$，空间复杂度 $O(C)$，其中 $n$ 是字符串的长度，而 $C$ 是字符集的大小。本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isItPossible(self, word1: str, word2: str) -> bool:
        cnt1 = [0] * 26
        cnt2 = [0] * 26
        for c in word1:
            cnt1[ord(c) - ord('a')] += 1
        for c in word2:
            cnt2[ord(c) - ord('a')] += 1
        for i, a in enumerate(cnt1):
            for j, b in enumerate(cnt2):
                if a and b:
                    cnt1[i], cnt2[j] = cnt1[i] - 1, cnt2[j] - 1
                    cnt1[j], cnt2[i] = cnt1[j] + 1, cnt2[i] + 1
                    if sum(v > 0 for v in cnt1) == sum(v > 0 for v in cnt2):
                        return True
                    cnt1[i], cnt2[j] = cnt1[i] + 1, cnt2[j] + 1
                    cnt1[j], cnt2[i] = cnt1[j] - 1, cnt2[i] - 1
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isItPossible(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < word1.length(); ++i) {
            ++cnt1[word1.charAt(i) - 'a'];
        }
        for (int i = 0; i < word2.length(); ++i) {
            ++cnt2[word2.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (cnt1[i] > 0 && cnt2[j] > 0) {
                    --cnt1[i];
                    --cnt2[j];
                    ++cnt1[j];
                    ++cnt2[i];
                    int d = 0;
                    for (int k = 0; k < 26; ++k) {
                        if (cnt1[k] > 0) {
                            ++d;
                        }
                        if (cnt2[k] > 0) {
                            --d;
                        }
                    }
                    if (d == 0) {
                        return true;
                    }
                    ++cnt1[i];
                    ++cnt2[j];
                    --cnt1[j];
                    --cnt2[i];
                }
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isItPossible(string word1, string word2) {
        int cnt1[26]{};
        int cnt2[26]{};
        for (char& c : word1) {
            ++cnt1[c - 'a'];
        }
        for (char& c : word2) {
            ++cnt2[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (cnt1[i] > 0 && cnt2[j] > 0) {
                    --cnt1[i];
                    --cnt2[j];
                    ++cnt1[j];
                    ++cnt2[i];
                    int d = 0;
                    for (int k = 0; k < 26; ++k) {
                        if (cnt1[k] > 0) {
                            ++d;
                        }
                        if (cnt2[k] > 0) {
                            --d;
                        }
                    }
                    if (d == 0) {
                        return true;
                    }
                    ++cnt1[i];
                    ++cnt2[j];
                    --cnt1[j];
                    --cnt2[i];
                }
            }
        }
        return false;
    }
};
```

### **Go**

```go
func isItPossible(word1 string, word2 string) bool {
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for _, c := range word1 {
		cnt1[c-'a']++
	}
	for _, c := range word2 {
		cnt2[c-'a']++
	}
	for i := range cnt1 {
		for j := range cnt2 {
			if cnt1[i] > 0 && cnt2[j] > 0 {
				cnt1[i], cnt2[j] = cnt1[i]-1, cnt2[j]-1
				cnt1[j], cnt2[i] = cnt1[j]+1, cnt2[i]+1
				d := 0
				for k, a := range cnt1 {
					if a > 0 {
						d++
					}
					if cnt2[k] > 0 {
						d--
					}
				}
				if d == 0 {
					return true
				}
				cnt1[i], cnt2[j] = cnt1[i]+1, cnt2[j]+1
				cnt1[j], cnt2[i] = cnt1[j]-1, cnt2[i]-1
			}
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
