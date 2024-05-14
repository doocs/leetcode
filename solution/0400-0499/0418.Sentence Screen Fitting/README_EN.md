# [418. Sentence Screen Fitting 🔒](https://leetcode.com/problems/sentence-screen-fitting)

[中文文档](/solution/0400-0499/0418.Sentence%20Screen%20Fitting/README.md)

<!-- tags:Array,String,Dynamic Programming -->

<!-- difficulty:Medium -->

## Description

<p>Given a&nbsp;<code>rows x cols</code> screen and a <code>sentence</code> represented as a list of strings, return <em>the number of&nbsp;times the given sentence can be fitted on the screen</em>.</p>

<p>The order of words in the sentence must remain unchanged, and a word cannot be split into two lines. A single space must separate two consecutive words in a line.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = [&quot;hello&quot;,&quot;world&quot;], rows = 2, cols = 8
<strong>Output:</strong> 1
<strong>Explanation:</strong>
hello---
world---
The character &#39;-&#39; signifies an empty space on the screen.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = [&quot;a&quot;, &quot;bcd&quot;, &quot;e&quot;], rows = 3, cols = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong>
a-bcd- 
e-a---
bcd-e-
The character &#39;-&#39; signifies an empty space on the screen.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence = [&quot;i&quot;,&quot;had&quot;,&quot;apple&quot;,&quot;pie&quot;], rows = 4, cols = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong>
i-had
apple
pie-i
had--
The character &#39;-&#39; signifies an empty space on the screen.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 100</code></li>
	<li><code>1 &lt;= sentence[i].length &lt;= 10</code></li>
	<li><code>sentence[i]</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= rows, cols &lt;= 2 * 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def wordsTyping(self, sentence: List[str], rows: int, cols: int) -> int:
        s = " ".join(sentence) + " "
        m = len(s)
        cur = 0
        for _ in range(rows):
            cur += cols
            if s[cur % m] == " ":
                cur += 1
            while cur and s[(cur - 1) % m] != " ":
                cur -= 1
        return cur // m
```

```java
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int m = s.length();
        int cur = 0;
        while (rows-- > 0) {
            cur += cols;
            if (s.charAt(cur % m) == ' ') {
                ++cur;
            } else {
                while (cur > 0 && s.charAt((cur - 1) % m) != ' ') {
                    --cur;
                }
            }
        }
        return cur / m;
    }
}
```

```cpp
class Solution {
public:
    int wordsTyping(vector<string>& sentence, int rows, int cols) {
        string s;
        for (auto& t : sentence) {
            s += t;
            s += " ";
        }
        int m = s.size();
        int cur = 0;
        while (rows--) {
            cur += cols;
            if (s[cur % m] == ' ') {
                ++cur;
            } else {
                while (cur && s[(cur - 1) % m] != ' ') {
                    --cur;
                }
            }
        }
        return cur / m;
    }
};
```

```go
func wordsTyping(sentence []string, rows int, cols int) int {
	s := strings.Join(sentence, " ") + " "
	m := len(s)
	cur := 0
	for i := 0; i < rows; i++ {
		cur += cols
		if s[cur%m] == ' ' {
			cur++
		} else {
			for cur > 0 && s[(cur-1)%m] != ' ' {
				cur--
			}
		}
	}
	return cur / m
}
```

```ts
function wordsTyping(sentence: string[], rows: number, cols: number): number {
    const s = sentence.join(' ') + ' ';
    let cur = 0;
    const m = s.length;
    for (let i = 0; i < rows; ++i) {
        cur += cols;
        if (s[cur % m] === ' ') {
            ++cur;
        } else {
            while (cur > 0 && s[(cur - 1) % m] !== ' ') {
                --cur;
            }
        }
    }
    return Math.floor(cur / m);
}
```

<!-- tabs:end -->

<!-- end -->
