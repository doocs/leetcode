---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0038.Count%20and%20Say/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [38. Count and Say](https://leetcode.com/problems/count-and-say)

[中文文档](/solution/0000-0099/0038.Count%20and%20Say/README.md)

## Description

<!-- description:start -->

<p>The <strong>count-and-say</strong> sequence is a sequence of digit strings defined by the recursive formula:</p>

<ul>
	<li><code>countAndSay(1) = &quot;1&quot;</code></li>
	<li><code>countAndSay(n)</code> is the run-length encoding of <code>countAndSay(n - 1)</code>.</li>
</ul>

<p><a href="http://en.wikipedia.org/wiki/Run-length_encoding" target="_blank">Run-length encoding</a> (RLE) is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string <code>&quot;3322251&quot;</code> we replace <code>&quot;33&quot;</code> with <code>&quot;23&quot;</code>, replace <code>&quot;222&quot;</code> with <code>&quot;32&quot;</code>, replace <code>&quot;5&quot;</code> with <code>&quot;15&quot;</code> and replace <code>&quot;1&quot;</code> with <code>&quot;11&quot;</code>. Thus the compressed string becomes <code>&quot;23321511&quot;</code>.</p>

<p>Given a positive integer <code>n</code>, return <em>the </em><code>n<sup>th</sup></code><em> element of the <strong>count-and-say</strong> sequence</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1211&quot;</span></p>

<p><strong>Explanation:</strong></p>

<pre>
countAndSay(1) = &quot;1&quot;
countAndSay(2) = RLE of &quot;1&quot; = &quot;11&quot;
countAndSay(3) = RLE of &quot;11&quot; = &quot;21&quot;
countAndSay(4) = RLE of &quot;21&quot; = &quot;1211&quot;
</pre>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>This is the base case.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 30</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you solve it iteratively?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

The task requires outputting the appearance sequence of the $n$-th item, where the $n$-th item is the description of the $n-1$-th item in the sequence. Therefore, we iterate $n-1$ times. In each iteration, we use fast and slow pointers, denoted as j and i respectively, to record the current character's position and the position of the next character that is not equal to the current character. We then update the sequence of the previous item to be $j-i$ occurrences of the current character.

Time Complexity:

1. The outer loop runs `n - 1` times, iterating to generate the "Count and Say" sequence up to the nth term.
2. The inner while loop iterates through each character in the current string s and counts the consecutive occurrences of the same character.
3. The inner while loop runs in $O(m)$ time, where m is the length of the current string s.

Overall, the time complexity is $O(n \times m)$, where n is the input parameter representing the term to generate, and m is the maximum length of the string in the sequence.

Space Complexity: $O(m)$.

<!-- tabs:start -->

```python
class Solution:
    def countAndSay(self, n: int) -> str:
        s = '1'
        for _ in range(n - 1):
            i = 0
            t = []
            while i < len(s):
                j = i
                while j < len(s) and s[j] == s[i]:
                    j += 1
                t.append(str(j - i))
                t.append(str(s[i]))
                i = j
            s = ''.join(t)
        return s
```

```java
class Solution {
    public String countAndSay(int n) {
        String s = "1";
        while (--n > 0) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < s.length();) {
                int j = i;
                while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                    ++j;
                }
                t.append((j - i) + "");
                t.append(s.charAt(i));
                i = j;
            }
            s = t.toString();
        }
        return s;
    }
}
```

```cpp
class Solution {
public:
    string countAndSay(int n) {
        string s = "1";
        while (--n) {
            string t = "";
            for (int i = 0; i < s.size();) {
                int j = i;
                while (j < s.size() && s[j] == s[i]) ++j;
                t += to_string(j - i);
                t += s[i];
                i = j;
            }
            s = t;
        }
        return s;
    }
};
```

```go
func countAndSay(n int) string {
	s := "1"
	for k := 0; k < n-1; k++ {
		t := &strings.Builder{}
		i := 0
		for i < len(s) {
			j := i
			for j < len(s) && s[j] == s[i] {
				j++
			}
			t.WriteString(strconv.Itoa(j - i))
			t.WriteByte(s[i])
			i = j
		}
		s = t.String()
	}
	return s
}
```

```ts
function countAndSay(n: number): string {
    let s = '1';
    for (let i = 1; i < n; i++) {
        let t = '';
        let cur = s[0];
        let count = 1;
        for (let j = 1; j < s.length; j++) {
            if (s[j] !== cur) {
                t += `${count}${cur}`;
                cur = s[j];
                count = 0;
            }
            count++;
        }
        t += `${count}${cur}`;
        s = t;
    }
    return s;
}
```

```rust
use std::iter::once;

impl Solution {
    pub fn count_and_say(n: i32) -> String {
        (1..n)
            .fold(vec![1], |curr, _| {
                let mut next = vec![];
                let mut slow = 0;
                for fast in 0..=curr.len() {
                    if fast == curr.len() || curr[slow] != curr[fast] {
                        next.extend(once((fast - slow) as u8).chain(once(curr[slow])));
                        slow = fast;
                    }
                }
                next
            })
            .into_iter()
            .map(|digit| (digit + b'0') as char)
            .collect()
    }
}
```

```js
const countAndSay = function (n) {
    let s = '1';

    for (let i = 2; i <= n; i++) {
        let count = 1,
            str = '',
            len = s.length;

        for (let j = 0; j < len; j++) {
            if (j < len - 1 && s[j] === s[j + 1]) {
                count++;
            } else {
                str += `${count}${s[j]}`;
                count = 1;
            }
        }
        s = str;
    }
    return s;
};
```

```cs
using System.Text;
public class Solution {
    public string CountAndSay(int n) {
        var s = "1";
        while (n > 1)
        {
            var sb = new StringBuilder();
            var lastChar = '1';
            var count = 0;
            foreach (var ch in s)
            {
                if (count > 0 && lastChar == ch)
                {
                    ++count;
                }
                else
                {
                    if (count > 0)
                    {
                        sb.Append(count);
                        sb.Append(lastChar);
                    }
                    lastChar = ch;
                    count = 1;
                }
            }
            if (count > 0)
            {
                sb.Append(count);
                sb.Append(lastChar);
            }
            s = sb.ToString();
            --n;
        }
        return s;
    }
}
```

```php
class Solution {
    /**
     * @param integer $n
     * @return string
     */

    function countAndSay($n) {
        if ($n <= 0) {
            return '';
        }

        $result = '1';
        for ($i = 2; $i <= $n; $i++) {
            $count = 1;
            $say = '';
            for ($j = 1; $j < strlen($result); $j++) {
                if ($result[$j] == $result[$j - 1]) {
                    $count++;
                } else {
                    $say .= $count . $result[$j - 1];
                    $count = 1;
                }
            }
            $say .= $count . $result[strlen($result) - 1];
            $result = $say;
        }
        return $result;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
