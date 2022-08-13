# [2182. 构造限制重复的字符串](https://leetcode.cn/problems/construct-string-with-repeat-limit)

[English Version](/solution/2100-2199/2182.Construct%20String%20With%20Repeat%20Limit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>repeatLimit</code> ，用 <code>s</code> 中的字符构造一个新字符串 <code>repeatLimitedString</code> ，使任何字母 <strong>连续</strong> 出现的次数都不超过 <code>repeatLimit</code> 次。你不必使用 <code>s</code> 中的全部字符。</p>

<p>返回 <strong>字典序最大的</strong><em> </em><code>repeatLimitedString</code> 。</p>

<p>如果在字符串 <code>a</code> 和 <code>b</code> 不同的第一个位置，字符串 <code>a</code> 中的字母在字母表中出现时间比字符串 <code>b</code> 对应的字母晚，则认为字符串 <code>a</code> 比字符串 <code>b</code> <strong>字典序更大</strong> 。如果字符串中前 <code>min(a.length, b.length)</code> 个字符都相同，那么较长的字符串字典序更大。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "cczazcc", repeatLimit = 3
<strong>输出：</strong>"zzcccac"
<strong>解释：</strong>使用 s 中的所有字符来构造 repeatLimitedString "zzcccac"。
字母 'a' 连续出现至多 1 次。
字母 'c' 连续出现至多 3 次。
字母 'z' 连续出现至多 2 次。
因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
该字符串是字典序最大的 repeatLimitedString ，所以返回 "zzcccac" 。
注意，尽管 "zzcccca" 字典序更大，但字母 'c' 连续出现超过 3 次，所以它不是一个有效的 repeatLimitedString 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "aababab", repeatLimit = 2
<strong>输出：</strong>"bbabaa"
<strong>解释：</strong>
使用 s 中的一些字符来构造 repeatLimitedString "bbabaa"。 
字母 'a' 连续出现至多 2 次。 
字母 'b' 连续出现至多 2 次。 
因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。 
该字符串是字典序最大的 repeatLimitedString ，所以返回 "bbabaa" 。 
注意，尽管 "bbabaaa" 字典序更大，但字母 'a' 连续出现超过 2 次，所以它不是一个有效的 repeatLimitedString 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= repeatLimit &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 双指针**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def repeatLimitedString(self, s: str, repeatLimit: int) -> str:
        cnt = [0] * 26
        for c in s:
            cnt[ord(c) - ord('a')] += 1
        ans = []
        for i in range(25, -1, -1):
            j = i - 1
            while 1:
                for _ in range(min(repeatLimit, cnt[i])):
                    cnt[i] -= 1
                    ans.append(chr(ord('a') + i))
                if cnt[i] == 0:
                    break
                while j >= 0 and cnt[j] == 0:
                    j -= 1
                if j < 0:
                    break
                cnt[j] -= 1
                ans.append(chr(ord('a') + j))
        return ''.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 25; i >= 0; --i) {
            int j = i - 1;
            while (true) {
                for (int k = Math.min(repeatLimit, cnt[i]); k > 0; --k) {
                    cnt[i]--;
                    ans.append((char) ('a' + i));
                }
                if (cnt[i] == 0) {
                    break;
                }
                while (j >= 0 && cnt[j] == 0) {
                    --j;
                }
                if (j < 0) {
                    break;
                }
                cnt[j]--;
                ans.append((char) ('a' + j));
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string repeatLimitedString(string s, int repeatLimit) {
        vector<int> cnt(26);
        for (char& c : s) cnt[c - 'a']++;
        string ans;
        for (int i = 25; ~i; --i) {
            int j = i - 1;
            while (true) {
                for (int k = min(cnt[i], repeatLimit); k; --k) {
                    cnt[i]--;
                    ans.push_back('a' + i);
                }
                if (cnt[i] == 0) break;
                while (~j && cnt[j] == 0) --j;
                if (j < 0) break;
                cnt[j]--;
                ans.push_back('a' + j);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func repeatLimitedString(s string, repeatLimit int) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	var ans []byte
	for i := 25; i >= 0; i-- {
		j := i - 1
		for {
			for k := min(cnt[i], repeatLimit); k > 0; k-- {
				cnt[i]--
				ans = append(ans, 'a'+byte(i))
			}
			if cnt[i] == 0 {
				break
			}
			for j >= 0 && cnt[j] == 0 {
				j--
			}
			if j < 0 {
				break
			}
			cnt[j]--
			ans = append(ans, 'a'+byte(j))
		}
	}
	return string(ans)
}

func min(a, b int) int {
	if a < b {
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
