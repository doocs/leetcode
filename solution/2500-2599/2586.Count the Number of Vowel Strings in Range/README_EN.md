# [2586. Count the Number of Vowel Strings in Range](https://leetcode.com/problems/count-the-number-of-vowel-strings-in-range)

[中文文档](/solution/2500-2599/2586.Count%20the%20Number%20of%20Vowel%20Strings%20in%20Range/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of string <code>words</code> and two integers <code>left</code> and <code>right</code>.</p>

<p>A string is called a <strong>vowel string</strong> if it starts with a vowel character and ends with a vowel character where vowel characters are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>.</p>

<p>Return <em>the number of vowel strings </em><code>words[i]</code><em> where </em><code>i</code><em> belongs to the inclusive range </em><code>[left, right]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;are&quot;,&quot;amy&quot;,&quot;u&quot;], left = 0, right = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
- &quot;are&quot; is a vowel string because it starts with &#39;a&#39; and ends with &#39;e&#39;.
- &quot;amy&quot; is not a vowel string because it does not end with a vowel.
- &quot;u&quot; is a vowel string because it starts with &#39;u&#39; and ends with &#39;u&#39;.
The number of vowel strings in the mentioned range is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;hey&quot;,&quot;aeo&quot;,&quot;mu&quot;,&quot;ooo&quot;,&quot;artro&quot;], left = 1, right = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
- &quot;aeo&quot; is a vowel string because it starts with &#39;a&#39; and ends with &#39;o&#39;.
- &quot;mu&quot; is not a vowel string because it does not start with a vowel.
- &quot;ooo&quot; is a vowel string because it starts with &#39;o&#39; and ends with &#39;o&#39;.
- &quot;artro&quot; is a vowel string because it starts with &#39;a&#39; and ends with &#39;o&#39;.
The number of vowel strings in the mentioned range is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> consists of only lowercase English letters.</li>
	<li><code>0 &lt;= left &lt;= right &lt; words.length</code></li>
</ul>

## Solutions

### Solution 1: Simulation

We just need to traverse the string in the interval $[left,.. right]$, and check if it starts and ends with a vowel. If so, the answer plus one.

After the traversal, return the answer.

The time complexity is $O(m)$, and the space complexity is $O(1)$. Where $m = right - left + 1$.

<!-- tabs:start -->

```python
class Solution:
    def vowelStrings(self, words: List[str], left: int, right: int) -> int:
        return sum(
            w[0] in 'aeiou' and w[-1] in 'aeiou' for w in words[left : right + 1]
        )
```

```java
class Solution {
    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            var w = words[i];
            if (check(w.charAt(0)) && check(w.charAt(w.length() - 1))) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```

```cpp
class Solution {
public:
    int vowelStrings(vector<string>& words, int left, int right) {
        auto check = [](char c) -> bool {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        };
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            auto w = words[i];
            ans += check(w[0]) && check(w[w.size() - 1]);
        }
        return ans;
    }
};
```

```go
func vowelStrings(words []string, left int, right int) (ans int) {
	check := func(c byte) bool {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
	}
	for _, w := range words[left : right+1] {
		if check(w[0]) && check(w[len(w)-1]) {
			ans++
		}
	}
	return
}
```

```ts
function vowelStrings(words: string[], left: number, right: number): number {
    let ans = 0;
    const check: string[] = ['a', 'e', 'i', 'o', 'u'];
    for (let i = left; i <= right; ++i) {
        const w = words[i];
        if (check.includes(w[0]) && check.includes(w.at(-1))) {
            ++ans;
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn vowel_strings(words: Vec<String>, left: i32, right: i32) -> i32 {
        let check = |c: u8| -> bool {
            c == b'a' || c == b'e' || c == b'i' || c == b'o' || c == b'u'
        };

        let mut ans = 0;
        for i in left..=right {
            let w = words[i as usize].as_bytes();
            if check(w[0]) && check(w[w.len() - 1]) {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
