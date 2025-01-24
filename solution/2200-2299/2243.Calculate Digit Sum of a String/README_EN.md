---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2243.Calculate%20Digit%20Sum%20of%20a%20String/README_EN.md
rating: 1301
source: Weekly Contest 289 Q1
tags:
    - String
    - Simulation
---

<!-- problem:start -->

# [2243. Calculate Digit Sum of a String](https://leetcode.com/problems/calculate-digit-sum-of-a-string)

[中文文档](/solution/2200-2299/2243.Calculate%20Digit%20Sum%20of%20a%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of digits and an integer <code>k</code>.</p>

<p>A <strong>round</strong> can be completed if the length of <code>s</code> is greater than <code>k</code>. In one round, do the following:</p>

<ol>
	<li><strong>Divide</strong> <code>s</code> into <strong>consecutive groups</strong> of size <code>k</code> such that the first <code>k</code> characters are in the first group, the next <code>k</code> characters are in the second group, and so on. <strong>Note</strong> that the size of the last group can be smaller than <code>k</code>.</li>
	<li><strong>Replace</strong> each group of <code>s</code> with a string representing the sum of all its digits. For example, <code>&quot;346&quot;</code> is replaced with <code>&quot;13&quot;</code> because <code>3 + 4 + 6 = 13</code>.</li>
	<li><strong>Merge</strong> consecutive groups together to form a new string. If the length of the string is greater than <code>k</code>, repeat from step <code>1</code>.</li>
</ol>

<p>Return <code>s</code> <em>after all rounds have been completed</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;11111222223&quot;, k = 3
<strong>Output:</strong> &quot;135&quot;
<strong>Explanation:</strong> 
- For the first round, we divide s into groups of size 3: &quot;111&quot;, &quot;112&quot;, &quot;222&quot;, and &quot;23&quot;.
  ​​​​​Then we calculate the digit sum of each group: 1 + 1 + 1 = 3, 1 + 1 + 2 = 4, 2 + 2 + 2 = 6, and 2 + 3 = 5. 
&nbsp; So, s becomes &quot;3&quot; + &quot;4&quot; + &quot;6&quot; + &quot;5&quot; = &quot;3465&quot; after the first round.
- For the second round, we divide s into &quot;346&quot; and &quot;5&quot;.
&nbsp; Then we calculate the digit sum of each group: 3 + 4 + 6 = 13, 5 = 5. 
&nbsp; So, s becomes &quot;13&quot; + &quot;5&quot; = &quot;135&quot; after second round. 
Now, s.length &lt;= k, so we return &quot;135&quot; as the answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00000000&quot;, k = 3
<strong>Output:</strong> &quot;000&quot;
<strong>Explanation:</strong> 
We divide s into &quot;000&quot;, &quot;000&quot;, and &quot;00&quot;.
Then we calculate the digit sum of each group: 0 + 0 + 0 = 0, 0 + 0 + 0 = 0, and 0 + 0 = 0. 
s becomes &quot;0&quot; + &quot;0&quot; + &quot;0&quot; = &quot;000&quot;, whose length is equal to k, so we return &quot;000&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>2 &lt;= k &lt;= 100</code></li>
	<li><code>s</code> consists of digits only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def digitSum(self, s: str, k: int) -> str:
        while len(s) > k:
            t = []
            n = len(s)
            for i in range(0, n, k):
                x = 0
                for j in range(i, min(i + k, n)):
                    x += int(s[j])
                t.append(str(x))
            s = "".join(t)
        return s
```

#### Java

```java
class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            int n = s.length();
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < n; i += k) {
                int x = 0;
                for (int j = i; j < Math.min(i + k, n); ++j) {
                    x += s.charAt(j) - '0';
                }
                t.append(x);
            }
            s = t.toString();
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string digitSum(string s, int k) {
        while (s.size() > k) {
            string t;
            int n = s.size();
            for (int i = 0; i < n; i += k) {
                int x = 0;
                for (int j = i; j < min(i + k, n); ++j) {
                    x += s[j] - '0';
                }
                t += to_string(x);
            }
            s = t;
        }
        return s;
    }
};
```

#### Go

```go
func digitSum(s string, k int) string {
	for len(s) > k {
		t := &strings.Builder{}
		n := len(s)
		for i := 0; i < n; i += k {
			x := 0
			for j := i; j < i+k && j < n; j++ {
				x += int(s[j] - '0')
			}
			t.WriteString(strconv.Itoa(x))
		}
		s = t.String()
	}
	return s
}
```

#### TypeScript

```ts
function digitSum(s: string, k: number): string {
    let ans = [];
    while (s.length > k) {
        for (let i = 0; i < s.length; i += k) {
            let cur = s.slice(i, i + k);
            ans.push(cur.split('').reduce((a, c) => a + parseInt(c), 0));
        }
        s = ans.join('');
        ans = [];
    }
    return s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
