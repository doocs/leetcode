---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0434.Number%20of%20Segments%20in%20a%20String/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [434. Number of Segments in a String](https://leetcode.com/problems/number-of-segments-in-a-string)

[中文文档](/solution/0400-0499/0434.Number%20of%20Segments%20in%20a%20String/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, return <em>the number of segments in the string</em>.</p>

<p>A <strong>segment</strong> is defined to be a contiguous sequence of <strong>non-space characters</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Hello, my name is John&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The five segments are [&quot;Hello,&quot;, &quot;my&quot;, &quot;name&quot;, &quot;is&quot;, &quot;John&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Hello&quot;
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 300</code></li>
	<li><code>s</code> consists of lowercase and uppercase English letters, digits, or one of the following characters <code>&quot;!@#$%^&amp;*()_+-=&#39;,.:&quot;</code>.</li>
	<li>The only space character in <code>s</code> is <code>&#39; &#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: String Splitting

We split the string $\textit{s}$ by spaces and then count the number of non-empty words.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $\textit{s}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSegments(self, s: str) -> int:
        return len(s.split())
```

#### Java

```java
class Solution {
    public int countSegments(String s) {
        int ans = 0;
        for (String t : s.split(" ")) {
            if (!"".equals(t)) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSegments(string s) {
        int ans = 0;
        istringstream ss(s);
        while (ss >> s) ++ans;
        return ans;
    }
};
```

#### Go

```go
func countSegments(s string) int {
	ans := 0
	for _, t := range strings.Split(s, " ") {
		if len(t) > 0 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSegments(s: string): number {
    return s.split(/\s+/).filter(Boolean).length;
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function countSegments($s) {
        $arr = explode(' ', $s);
        $cnt = 0;
        for ($i = 0; $i < count($arr); $i++) {
            if (strlen($arr[$i]) != 0) {
                $cnt++;
            }
        }
        return $cnt;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Simulation

We can also directly traverse each character $\text{s[i]}$ in the string. If $\text{s[i]}$ is not a space and $\text{s[i-1]}$ is a space or $i = 0$, then $\text{s[i]}$ marks the beginning of a new word, and we increment the answer by one.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{s}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSegments(self, s: str) -> int:
        ans = 0
        for i, c in enumerate(s):
            if c != ' ' and (i == 0 or s[i - 1] == ' '):
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countSegments(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSegments(string s) {
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] != ' ' && (i == 0 || s[i - 1] == ' ')) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSegments(s string) int {
	ans := 0
	for i, c := range s {
		if c != ' ' && (i == 0 || s[i-1] == ' ') {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSegments(s: string): number {
    let ans = 0;
    for (let i = 0; i < s.length; i++) {
        let c = s[i];
        if (c !== ' ' && (i === 0 || s[i - 1] === ' ')) {
            ans++;
        }
    }
    return ans;
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return Integer
     */
    function countSegments($s) {
        $ans = 0;
        $n = strlen($s);
        for ($i = 0; $i < $n; $i++) {
            $c = $s[$i];
            if ($c !== ' ' && ($i === 0 || $s[$i - 1] === ' ')) {
                $ans++;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
