# [2182. 构造限制重复的字符串](https://leetcode.cn/problems/construct-string-with-repeat-limit)

[English Version](/solution/2100-2199/2182.Construct%20String%20With%20Repeat%20Limit/README_EN.md)

<!-- tags:贪心,字符串,计数,堆（优先队列） -->

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

### 方法一：贪心

我们先用一个长度为 $26$ 的数组 $cnt$ 统计字符串 $s$ 中每个字符出现的次数，然后从大到小枚举字母表的第 $i$ 个字母，每次取出最多 $\min(cnt[i], repeatLimit)$ 个字母 $i$，如果取完后 $cnt[i]$ 还大于 $0$，则继续取字母表中第 $j$ 个字母，其中 $j$ 是最大的满足 $j < i$ 且 $cnt[j] > 0$ 的下标，直到取完所有字母。

时间复杂度 $O(n + |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串 $s$ 的长度，而 $\Sigma$ 是字符集，本题中 $|\Sigma| = 26$。

<!-- tabs:start -->

```python
class Solution:
    def repeatLimitedString(self, s: str, repeatLimit: int) -> str:
        cnt = [0] * 26
        for c in s:
            cnt[ord(c) - ord("a")] += 1
        ans = []
        j = 24
        for i in range(25, -1, -1):
            j = min(i - 1, j)
            while 1:
                x = min(repeatLimit, cnt[i])
                cnt[i] -= x
                ans.append(ascii_lowercase[i] * x)
                if cnt[i] == 0:
                    break
                while j >= 0 and cnt[j] == 0:
                    j -= 1
                if j < 0:
                    break
                cnt[j] -= 1
                ans.append(ascii_lowercase[j])
        return "".join(ans)
```

```java
class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 25, j = 24; i >= 0; --i) {
            j = Math.min(j, i - 1);
            while (true) {
                for (int k = Math.min(cnt[i], repeatLimit); k > 0; --k) {
                    ans.append((char) ('a' + i));
                    --cnt[i];
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
                ans.append((char) ('a' + j));
                --cnt[j];
            }
        }
        return ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string repeatLimitedString(string s, int repeatLimit) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        string ans;
        for (int i = 25, j = 24; ~i; --i) {
            j = min(j, i - 1);
            while (1) {
                for (int k = min(cnt[i], repeatLimit); k; --k) {
                    ans += 'a' + i;
                    --cnt[i];
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
                ans += 'a' + j;
                --cnt[j];
            }
        }
        return ans;
    }
};
```

```go
func repeatLimitedString(s string, repeatLimit int) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	var ans []byte
	for i, j := 25, 24; i >= 0; i-- {
		j = min(j, i-1)
		for {
			for k := min(cnt[i], repeatLimit); k > 0; k-- {
				ans = append(ans, byte(i+'a'))
				cnt[i]--
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
			ans = append(ans, byte(j+'a'))
			cnt[j]--
		}
	}
	return string(ans)
}
```

```ts
function repeatLimitedString(s: string, repeatLimit: number): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    const ans: string[] = [];
    for (let i = 25, j = 24; ~i; --i) {
        j = Math.min(j, i - 1);
        while (true) {
            for (let k = Math.min(cnt[i], repeatLimit); k; --k) {
                ans.push(String.fromCharCode(97 + i));
                --cnt[i];
            }
            if (!cnt[i]) {
                break;
            }
            while (j >= 0 && !cnt[j]) {
                --j;
            }
            if (j < 0) {
                break;
            }
            ans.push(String.fromCharCode(97 + j));
            --cnt[j];
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- end -->
