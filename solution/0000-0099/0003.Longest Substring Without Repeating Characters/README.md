---
comments: true
difficulty: 中等
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters)

[English Version](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串 <code>s</code> ，请你找出其中不含有重复字符的&nbsp;<strong>最长 <span data-keyword="substring-nonempty">子串</span></strong><strong>&nbsp;</strong>的长度。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>s = "abcabcbb"
<strong>输出: </strong>3 
<strong>解释:</strong> 因为无重复字符的最长子串是 <code>"abc"</code>，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = "bbbbb"
<strong>输出: </strong>1
<strong>解释: </strong>因为无重复字符的最长子串是 <code>"b"</code>，所以其长度为 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>s = "pwwkew"
<strong>输出: </strong>3
<strong>解释: </strong>因为无重复字符的最长子串是&nbsp;<code>"wke"</code>，所以其长度为 3。
&nbsp;    请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>"pwke"</code>&nbsp;是一个<em>子序列，</em>不是子串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;由英文字母、数字、符号和空格组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

<!-- thinking:start -->

> **思考**
>
> 若枚举所有子串再判断是否含有重复字符，时间复杂度至少为 $O(n^2)$。$n \le 10^5$，无法通过。
>
> 子串之间大量重叠：一旦区间 $[l, r]$ 已经不含重复字符，右端再纳入字符 $c$ 时，只需处理 $c$ 是否在窗口内再次出现，而不必重新扫描整段。若出现重复，合法解不可能再包含左侧多出来的那个 $c$，应当右移 $l$。用计数表维护 $\textit{cnt}[c]$，大于 $1$ 时收缩左端，直到 $c$ 只出现一次；此时当前窗口就是以 $r$ 结尾的最长无重复子串。

<!-- thinking:end -->

我们可以用两个指针 $l$ 和 $r$ 维护一个滑动窗口，使其始终满足窗口内没有重复字符，初始时 $l$ 和 $r$ 都指向字符串的第一个字符。用一个哈希表或者长度为 $128$ 的数组 $\textit{cnt}$ 来记录每个字符出现的次数，其中 $\textit{cnt}[c]$ 表示字符 $c$ 出现的次数。

接下来，我们依次移动右指针 $r$，每次移动时，将 $\textit{cnt}[s[r]]$ 的值加 $1$，然后判断当前窗口 $[l, r]$ 内 $\textit{cnt}[s[r]]$ 是否大于 $1$，如果大于 $1$，说明当前窗口内有重复字符，我们需要移动左指针 $l$，直到窗口内没有重复字符为止。然后，我们更新答案 $\textit{ans} = \max(\textit{ans}, r - l + 1)$。

最终，我们返回答案 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 表示字符集，这里 $\Sigma$ 的大小为 $128$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        cnt = Counter()
        ans = l = 0
        for r, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 1:
                cnt[s[l]] -= 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] cnt = new int[128];
        int ans = 0, n = s.length();
        for (int l = 0, r = 0; r < n; ++r) {
            char c = s.charAt(r);
            ++cnt[c];
            while (cnt[c] > 1) {
                --cnt[s.charAt(l++)];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int cnt[128]{};
        int ans = 0, n = s.size();
        for (int l = 0, r = 0; r < n; ++r) {
            ++cnt[s[r]];
            while (cnt[s[r]] > 1) {
                --cnt[s[l++]];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func lengthOfLongestSubstring(s string) (ans int) {
	cnt := [128]int{}
	l := 0
	for r, c := range s {
		cnt[c]++
		for cnt[c] > 1 {
			cnt[s[l]]--
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
```

#### TypeScript

```ts
function lengthOfLongestSubstring(s: string): number {
    let ans = 0;
    const cnt = new Map<string, number>();
    const n = s.length;
    for (let l = 0, r = 0; r < n; ++r) {
        cnt.set(s[r], (cnt.get(s[r]) || 0) + 1);
        while (cnt.get(s[r])! > 1) {
            cnt.set(s[l], cnt.get(s[l])! - 1);
            ++l;
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let mut cnt = [0; 128];
        let mut ans = 0;
        let mut l = 0;
        let chars: Vec<char> = s.chars().collect();
        let n = chars.len();
        for (r, &c) in chars.iter().enumerate() {
            cnt[c as usize] += 1;
            while cnt[c as usize] > 1 {
                cnt[chars[l] as usize] -= 1;
                l += 1;
            }
            ans = ans.max((r - l + 1) as i32);
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
    let ans = 0;
    const n = s.length;
    const cnt = new Map();
    for (let l = 0, r = 0; r < n; ++r) {
        cnt.set(s[r], (cnt.get(s[r]) || 0) + 1);
        while (cnt.get(s[r]) > 1) {
            cnt.set(s[l], cnt.get(s[l]) - 1);
            ++l;
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int LengthOfLongestSubstring(string s) {
        int n = s.Length;
        int ans = 0;
        var cnt = new int[128];
        for (int l = 0, r = 0; r < n; ++r) {
            ++cnt[s[r]];
            while (cnt[s[r]] > 1) {
                --cnt[s[l++]];
            }
            ans = Math.Max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    function lengthOfLongestSubstring($s) {
        $n = strlen($s);
        $ans = 0;
        $cnt = array_fill(0, 128, 0);
        $l = 0;
        for ($r = 0; $r < $n; ++$r) {
            $cnt[ord($s[$r])]++;
            while ($cnt[ord($s[$r])] > 1) {
                $cnt[ord($s[$l])]--;
                $l++;
            }
            $ans = max($ans, $r - $l + 1);
        }
        return $ans;
    }
}
```

#### Swift

```swift
class Solution {
    func lengthOfLongestSubstring(_ s: String) -> Int {
        let n = s.count
        var ans = 0
        var cnt = [Int](repeating: 0, count: 128)
        var l = 0
        let sArray = Array(s)
        for r in 0..<n {
            cnt[Int(sArray[r].asciiValue!)] += 1
            while cnt[Int(sArray[r].asciiValue!)] > 1 {
                cnt[Int(sArray[l].asciiValue!)] -= 1
                l += 1
            }
            ans = max(ans, r - l + 1)
        }
        return ans
    }
}
```

#### Kotlin

```kotlin
class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        val n = s.length
        var ans = 0
        val cnt = IntArray(128)
        var l = 0
        for (r in 0 until n) {
            cnt[s[r].toInt()]++
            while (cnt[s[r].toInt()] > 1) {
                cnt[s[l].toInt()]--
                l++
            }
            ans = Math.max(ans, r - l + 1)
        }
        return ans
    }
}
```

#### C

```c
int lengthOfLongestSubstring(char* s) {
    int freq[256] = {0};
    int l = 0, r = 0;
    int ans = 0;
    int len = strlen(s);

    for (r = 0; r < len; r++) {
        char c = s[r];
        freq[(unsigned char) c]++;

        while (freq[(unsigned char) c] > 1) {
            freq[(unsigned char) s[l]]--;
            l++;
        }

        if (ans < r - l + 1) {
            ans = r - l + 1;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
