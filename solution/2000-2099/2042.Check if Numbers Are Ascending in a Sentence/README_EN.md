# [2042. Check if Numbers Are Ascending in a Sentence](https://leetcode.com/problems/check-if-numbers-are-ascending-in-a-sentence)

[中文文档](/solution/2000-2099/2042.Check%20if%20Numbers%20Are%20Ascending%20in%20a%20Sentence/README.md)

## Description

<p>A sentence is a list of <strong>tokens</strong> separated by a <strong>single</strong> space with no leading or trailing spaces. Every token is either a <strong>positive number</strong> consisting of digits <code>0-9</code> with no leading zeros, or a <strong>word</strong> consisting of lowercase English letters.</p>

<ul>
	<li>For example, <code>&quot;a puppy has 2 eyes 4 legs&quot;</code> is a sentence with seven tokens: <code>&quot;2&quot;</code> and <code>&quot;4&quot;</code> are numbers and the other tokens such as <code>&quot;puppy&quot;</code> are words.</li>
</ul>

<p>Given a string <code>s</code> representing a sentence, you need to check if <strong>all</strong> the numbers in <code>s</code> are <strong>strictly increasing</strong> from left to right (i.e., other than the last number, <strong>each</strong> number is <strong>strictly smaller</strong> than the number on its <strong>right</strong> in <code>s</code>).</p>

<p>Return <code>true</code><em> if so, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="example-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2042.Check%20if%20Numbers%20Are%20Ascending%20in%20a%20Sentence/images/example1.png" style="width: 637px; height: 48px;" />
<pre>
<strong>Input:</strong> s = &quot;1 box has 3 blue 4 red 6 green and 12 yellow marbles&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The numbers in s are: 1, 3, 4, 6, 12.
They are strictly increasing from left to right: 1 &lt; 3 &lt; 4 &lt; 6 &lt; 12.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;hello world 5 x 5&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The numbers in s are: <u><strong>5</strong></u>, <strong><u>5</u></strong>. They are not strictly increasing.
</pre>

<p><strong>Example 3:</strong></p>
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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def areNumbersAscending(self, s: str) -> bool:
        curr = 0
        for t in s.split(' '):
            if t[0].isdigit():
                if curr >= int(t):
                    return False
                curr = int(t)
        return True
```

### **Java**

```java
class Solution {
    public boolean areNumbersAscending(String s) {
        int curr = 0;
        for (String t : s.split(" ")) {
            char c = t.charAt(0);
            if (Character.isDigit(c)) {
                int x = Integer.parseInt(t);
                if (curr >= x) {
                    return false;
                }
                curr = x;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function areNumbersAscending(s: string): boolean {
    let strs = s.split(' ');
    let prev = Number.MIN_SAFE_INTEGER;
    for (let str of strs) {
        let num = Number(str);
        if (!isNaN(num)) {
            if (num <= prev) return false;
            prev = num;
        }
    }
    return true;
}
```

### **C++**

```cpp
class Solution {
public:
    bool areNumbersAscending(string s) {
        int curr = 0;
        istringstream is(s);
        string t;
        while (is >> t) {
            if (isdigit(t[0])) {
                int x = stoi(t);
                if (curr >= x) return false;
                curr = x;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func areNumbersAscending(s string) bool {
	curr := 0
	for _, t := range strings.Split(s, " ") {
		if unicode.IsDigit(rune(t[0])) {
			x, _ := strconv.Atoi(t)
			if curr >= x {
				return false
			}
			curr = x
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
