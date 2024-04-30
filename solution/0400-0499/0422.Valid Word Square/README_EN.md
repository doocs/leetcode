# [422. Valid Word Square 🔒](https://leetcode.com/problems/valid-word-square)

[中文文档](/solution/0400-0499/0422.Valid%20Word%20Square/README.md)

<!-- tags:Array,Matrix -->

## Description

<p>Given an array of strings <code>words</code>, return <code>true</code> <em>if it forms a valid <strong>word square</strong></em>.</p>

<p>A sequence of strings forms a valid <strong>word square</strong> if the <code>k<sup>th</sup></code> row and column read the same string, where <code>0 &lt;= k &lt; max(numRows, numColumns)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0422.Valid%20Word%20Square/images/validsq1-grid.jpg" style="width: 333px; height: 333px;" />
<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;,&quot;bnrt&quot;,&quot;crmy&quot;,&quot;dtye&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong>
The 1<sup>st</sup> row and 1<sup>st</sup> column both read &quot;abcd&quot;.
The 2<sup>nd</sup> row and 2<sup>nd</sup> column both read &quot;bnrt&quot;.
The 3<sup>rd</sup> row and 3<sup>rd</sup> column both read &quot;crmy&quot;.
The 4<sup>th</sup> row and 4<sup>th</sup> column both read &quot;dtye&quot;.
Therefore, it is a valid word square.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0422.Valid%20Word%20Square/images/validsq2-grid.jpg" style="width: 333px; height: 333px;" />
<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;,&quot;bnrt&quot;,&quot;crm&quot;,&quot;dt&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong>
The 1<sup>st</sup> row and 1<sup>st</sup> column both read &quot;abcd&quot;.
The 2<sup>nd</sup> row and 2<sup>nd</sup> column both read &quot;bnrt&quot;.
The 3<sup>rd</sup> row and 3<sup>rd</sup> column both read &quot;crm&quot;.
The 4<sup>th</sup> row and 4<sup>th</sup> column both read &quot;dt&quot;.
Therefore, it is a valid word square.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0422.Valid%20Word%20Square/images/validsq3-grid.jpg" style="width: 333px; height: 333px;" />
<pre>
<strong>Input:</strong> words = [&quot;ball&quot;,&quot;area&quot;,&quot;read&quot;,&quot;lady&quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong>
The 3<sup>rd</sup> row reads &quot;read&quot; while the 3<sup>rd</sup> column reads &quot;lead&quot;.
Therefore, it is NOT a valid word square.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 500</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 500</code></li>
	<li><code>words[i]</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def validWordSquare(self, words: List[str]) -> bool:
        m = len(words)
        n = max(len(w) for w in words)
        if m != n:
            return False
        for j in range(n):
            if words[j] != "".join(w[j] for w in words if j < len(w)):
                return False
        return True
```

```java
class Solution {
    public boolean validWordSquare(List<String> words) {
        int m = words.size();
        for (int i = 0; i < m; ++i) {
            int n = words.get(i).length();
            for (int j = 0; j < n; ++j) {
                if (j >= m || i >= words.get(j).length()) {
                    return false;
                }
                if (words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool validWordSquare(vector<string>& words) {
        int m = words.size();
        for (int i = 0; i < m; ++i) {
            int n = words[i].size();
            for (int j = 0; j < n; ++j) {
                if (j >= m || i >= words[j].size() || words[i][j] != words[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
};
```

```go
func validWordSquare(words []string) bool {
	m := len(words)
	for i, w := range words {
		for j := range w {
			if j >= m || i >= len(words[j]) || w[j] != words[j][i] {
				return false
			}
		}
	}
	return true
}
```

```ts
function validWordSquare(words: string[]): boolean {
    const m = words.length;
    for (let i = 0; i < m; ++i) {
        const n = words[i].length;
        for (let j = 0; j < n; ++j) {
            if (j >= m || i >= words[j].length || words[i][j] !== words[j][i]) {
                return false;
            }
        }
    }
    return true;
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def validWordSquare(self, words: List[str]) -> bool:
        m = len(words)
        for i, w in enumerate(words):
            for j, c in enumerate(w):
                if j >= m or i >= len(words[j]) or c != words[j][i]:
                    return False
        return True
```

<!-- tabs:end -->

<!-- end -->
