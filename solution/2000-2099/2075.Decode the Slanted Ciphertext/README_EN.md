# [2075. Decode the Slanted Ciphertext](https://leetcode.com/problems/decode-the-slanted-ciphertext)

[中文文档](/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/README.md)

## Description

<p>A string <code>originalText</code> is encoded using a <strong>slanted transposition cipher</strong> to a string <code>encodedText</code> with the help of a matrix having a <strong>fixed number of rows</strong> <code>rows</code>.</p>

<p><code>originalText</code> is placed first in a top-left to bottom-right manner.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/exa11.png" style="width: 300px; height: 185px;" />
<p>The blue cells are filled first, followed by the red cells, then the yellow cells, and so on, until we reach the end of <code>originalText</code>. The arrow indicates the order in which the cells are filled. All empty cells are filled with <code>&#39; &#39;</code>. The number of columns is chosen such that the rightmost column will <strong>not be empty</strong> after filling in <code>originalText</code>.</p>

<p><code>encodedText</code> is then formed by appending all characters of the matrix in a row-wise fashion.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/exa12.png" style="width: 300px; height: 200px;" />
<p>The characters in the blue cells are appended first to <code>encodedText</code>, then the red cells, and so on, and finally the yellow cells. The arrow indicates the order in which the cells are accessed.</p>

<p>For example, if <code>originalText = &quot;cipher&quot;</code> and <code>rows = 3</code>, then we encode it in the following manner:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/desc2.png" style="width: 281px; height: 211px;" />
<p>The blue arrows depict how <code>originalText</code> is placed in the matrix, and the red arrows denote the order in which <code>encodedText</code> is formed. In the above example, <code>encodedText = &quot;ch ie pr&quot;</code>.</p>

<p>Given the encoded string <code>encodedText</code> and number of rows <code>rows</code>, return <em>the original string</em> <code>originalText</code>.</p>

<p><strong>Note:</strong> <code>originalText</code> <strong>does not</strong> have any trailing spaces <code>&#39; &#39;</code>. The test cases are generated such that there is only one possible <code>originalText</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> encodedText = &quot;ch   ie   pr&quot;, rows = 3
<strong>Output:</strong> &quot;cipher&quot;
<strong>Explanation:</strong> This is the same example described in the problem description.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/exam1.png" style="width: 250px; height: 168px;" />
<pre>
<strong>Input:</strong> encodedText = &quot;iveo    eed   l te   olc&quot;, rows = 4
<strong>Output:</strong> &quot;i love leetcode&quot;
<strong>Explanation:</strong> The figure above denotes the matrix that was used to encode originalText. 
The blue arrows show how we can find originalText from encodedText.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2075.Decode%20the%20Slanted%20Ciphertext/images/eg2.png" style="width: 300px; height: 51px;" />
<pre>
<strong>Input:</strong> encodedText = &quot;coding&quot;, rows = 1
<strong>Output:</strong> &quot;coding&quot;
<strong>Explanation:</strong> Since there is only 1 row, both originalText and encodedText are the same.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= encodedText.length &lt;= 10<sup>6</sup></code></li>
	<li><code>encodedText</code> consists of lowercase English letters and <code>&#39; &#39;</code> only.</li>
	<li><code>encodedText</code> is a valid encoding of some <code>originalText</code> that <strong>does not</strong> have trailing spaces.</li>
	<li><code>1 &lt;= rows &lt;= 1000</code></li>
	<li>The testcases are generated such that there is <strong>only one</strong> possible <code>originalText</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def decodeCiphertext(self, encodedText: str, rows: int) -> str:
        ans = []
        cols = len(encodedText) // rows
        for j in range(cols):
            x, y = 0, j
            while x < rows and y < cols:
                ans.append(encodedText[x * cols + y])
                x, y = x + 1, y + 1
        return ''.join(ans).rstrip()
```

### **Java**

```java
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        StringBuilder ans = new StringBuilder();
        int cols = encodedText.length() / rows;
        for (int j = 0; j < cols; ++j) {
            for (int x = 0, y = j; x < rows && y < cols; ++x, ++y) {
                ans.append(encodedText.charAt(x * cols + y));
            }
        }
        while (ans.length() > 0 && ans.charAt(ans.length() - 1) == ' ') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.toString();
    }
}
```

### **TypeScript**

```ts
function decodeCiphertext(encodedText: string, rows: number): string {
    const cols = Math.ceil(encodedText.length / rows);
    let ans = [];
    for (let k = 0; k <= cols; k++) {
        for (let i = 0, j = k; i < rows && j < cols; i++, j++) {
            ans.push(encodedText.charAt(i * cols + j));
        }
    }
    return ans.join('').trimEnd();
}
```

### **C++**

```cpp
class Solution {
public:
    string decodeCiphertext(string encodedText, int rows) {
        string ans;
        int cols = encodedText.size() / rows;
        for (int j = 0; j < cols; ++j)
            for (int x = 0, y = j; x < rows && y < cols; ++x, ++y)
                ans += encodedText[x * cols + y];
        while (ans.back() == ' ') ans.pop_back();
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
