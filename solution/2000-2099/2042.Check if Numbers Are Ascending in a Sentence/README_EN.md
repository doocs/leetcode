---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2042.Check%20if%20Numbers%20Are%20Ascending%20in%20a%20Sentence/README_EN.md
rating: 1257
source: Weekly Contest 263 Q1
tags:
    - String
---

<!-- problem:start -->

# [2042. Check if Numbers Are Ascending in a Sentence](https://leetcode.com/problems/check-if-numbers-are-ascending-in-a-sentence)

[中文文档](/solution/2000-2099/2042.Check%20if%20Numbers%20Are%20Ascending%20in%20a%20Sentence/README.md)

## Description

<!-- description:start -->

<p>A sentence is a list of <strong>tokens</strong> separated by a <strong>single</strong> space with no leading or trailing spaces. Every token is either a <strong>positive number</strong> consisting of digits <code>0-9</code> with no leading zeros, or a <strong>word</strong> consisting of lowercase English letters.</p>

<ul>
	<li>For example, <code>&quot;a puppy has 2 eyes 4 legs&quot;</code> is a sentence with seven tokens: <code>&quot;2&quot;</code> and <code>&quot;4&quot;</code> are numbers and the other tokens such as <code>&quot;puppy&quot;</code> are words.</li>
</ul>

<p>Given a string <code>s</code> representing a sentence, you need to check if <strong>all</strong> the numbers in <code>s</code> are <strong>strictly increasing</strong> from left to right (i.e., other than the last number, <strong>each</strong> number is <strong>strictly smaller</strong> than the number on its <strong>right</strong> in <code>s</code>).</p>

<p>Return <code>true</code><em> if so, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="example-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2042.Check%20if%20Numbers%20Are%20Ascending%20in%20a%20Sentence/images/example1.png" style="width: 637px; height: 48px;" />
<pre>
<strong>Input:</strong> s = &quot;1 box has 3 blue 4 red 6 green and 12 yellow marbles&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The numbers in s are: 1, 3, 4, 6, 12.
They are strictly increasing from left to right: 1 &lt; 3 &lt; 4 &lt; 6 &lt; 12.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;hello world 5 x 5&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The numbers in s are: <u><strong>5</strong></u>, <strong><u>5</u></strong>. They are not strictly increasing.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="example-3" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2042.Check%20if%20Numbers%20Are%20Ascending%20in%20a%20Sentence/images/example3.png" style="width: 794px; height: 48px;" />
<pre>
<strong>Input:</strong> s = &quot;sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The numbers in s are: 7, <u><strong>51</strong></u>, <u><strong>50</strong></u>, 60. They are not strictly increasing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 200</code></li>
	<li><code>s</code> consists of lowercase English letters, spaces, and digits from <code>0</code> to <code>9</code>, inclusive.</li>
	<li>The number of tokens in <code>s</code> is between <code>2</code> and <code>100</code>, inclusive.</li>
	<li>The tokens in <code>s</code> are separated by a single space.</li>
	<li>There are at least <strong>two</strong> numbers in <code>s</code>.</li>
	<li>Each number in <code>s</code> is a <strong>positive</strong> number <strong>less</strong> than <code>100</code>, with no leading zeros.</li>
	<li><code>s</code> contains no leading or trailing spaces.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can split the string $s$ into several words by spaces. Then, for each word, check if it is a number. If it is a number, convert it to an integer, compare it with the previous number. If it is not strictly increasing, return `false`. Otherwise, assign the current number to the previous number and continue the traversal.

If the traversal ends, it means that the numbers in the string are strictly increasing, so return `true`.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def areNumbersAscending(self, s: str) -> bool:
        pre = 0
        for t in s.split():
            if t[0].isdigit():
                if (cur := int(t)) <= pre:
                    return False
                pre = cur
        return True
```

```java
class Solution {
    public boolean areNumbersAscending(String s) {
        int pre = 0;
        for (var t : s.split(" ")) {
            if (t.charAt(0) <= '9') {
                int cur = Integer.parseInt(t);
                if (pre >= cur) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool areNumbersAscending(string s) {
        int pre = 0;
        istringstream is(s);
        string t;
        while (is >> t) {
            if (isdigit(t[0])) {
                int cur = stoi(t);
                if (pre >= cur) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }
};
```

```go
func areNumbersAscending(s string) bool {
	pre := 0
	for _, t := range strings.Split(s, " ") {
		if t[0] <= '9' {
			cur, _ := strconv.Atoi(t)
			if pre >= cur {
				return false
			}
			pre = cur
		}
	}
	return true
}
```

```ts
function areNumbersAscending(s: string): boolean {
    let pre = -1;
    for (const cur of s.split(' ')) {
        if (cur[0] <= '9') {
            const num = Number(cur);
            if (num <= pre) {
                return false;
            }
            pre = num;
        }
    }
    return true;
}
```

```rust
impl Solution {
    pub fn are_numbers_ascending(s: String) -> bool {
        let mut pre = -1;
        for cur in s.split(' ') {
            if cur.as_bytes()[0] <= b'9' {
                let num = cur.parse::<i32>().unwrap();
                if num <= pre {
                    return false;
                }
                pre = num;
            }
        }
        true
    }
}
```

```c
bool areNumbersAscending(char* s) {
    int pre = -1;
    int cur = 0;
    for (int i = 0; s[i]; i++) {
        if (isdigit(s[i])) {
            cur = cur * 10 + s[i] - '0';
        } else {
            if (cur != 0) {
                if (cur <= pre) {
                    return 0;
                }
                pre = cur;
                cur = 0;
            }
        }
    }
    if (cur != 0 && cur <= pre) {
        return 0;
    }
    return 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def areNumbersAscending(self, s: str) -> bool:
        pre = i = 0
        n = len(s)
        while i < n:
            if s[i].isdigit():
                cur = 0
                while i < n and s[i].isdigit():
                    cur = cur * 10 + int(s[i])
                    i += 1
                if pre >= cur:
                    return False
                pre = cur
            else:
                i += 1
        return True
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
