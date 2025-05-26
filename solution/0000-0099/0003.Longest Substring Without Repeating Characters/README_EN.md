---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README_EN.md
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters)

[中文文档](/solution/0000-0099/0003.Longest%20Substring%20Without%20Repeating%20Characters/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, find the length of the <strong>longest</strong> <span data-keyword="substring-nonempty"><strong>substring</strong></span> without duplicate characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabcbb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;abc&quot;, with the length of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbbbb&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The answer is &quot;b&quot;, with the length of 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;pwwkew&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The answer is &quot;wke&quot;, with the length of 3.
Notice that the answer must be a substring, &quot;pwke&quot; is a subsequence and not a substring.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of English letters, digits, symbols and spaces.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

We can use two pointers $l$ and $r$ to maintain a sliding window that always satisfies the condition of having no repeating characters within the window. Initially, both $l$ and $r$ point to the first character of the string. We use a hash table or an array of length $128$ called $\textit{cnt}$ to record the number of occurrences of each character, where $\textit{cnt}[c]$ represents the number of occurrences of character $c$.

Next, we move the right pointer $r$ one step at a time. Each time we move it, we increment the value of $\textit{cnt}[s[r]]$ by $1$, and then check if the value of $\textit{cnt}[s[r]]$ is greater than $1$ within the current window $[l, r]$. If it is greater than $1$, it means there are repeating characters within the current window, and we need to move the left pointer $l$ until there are no repeating characters within the window. Then, we update the answer $\textit{ans} = \max(\textit{ans}, r - l + 1)$.

Finally, we return the answer $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(|\Sigma|)$, where $\Sigma$ represents the character set, and the size of $\Sigma$ is $128$.

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
