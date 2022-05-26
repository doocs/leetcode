# [1812. Determine Color of a Chessboard Square](https://leetcode.com/problems/determine-color-of-a-chessboard-square)

[中文文档](/solution/1800-1899/1812.Determine%20Color%20of%20a%20Chessboard%20Square/README.md)

## Description

<p>You are given <code>coordinates</code>, a string that represents the coordinates of a square of the chessboard. Below is a chessboard for your reference.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1812.Determine%20Color%20of%20a%20Chessboard%20Square/images/screenshot-2021-02-20-at-22159-pm.png" style="width: 400px; height: 396px;" /></p>

<p>Return <code>true</code><em> if the square is white, and </em><code>false</code><em> if the square is black</em>.</p>

<p>The coordinate will always represent a valid chessboard square. The coordinate will always have the letter first, and the number second.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> coordinates = &quot;a1&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> From the chessboard above, the square with coordinates &quot;a1&quot; is black, so return false.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> coordinates = &quot;h3&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> From the chessboard above, the square with coordinates &quot;h3&quot; is white, so return true.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> coordinates = &quot;c7&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>coordinates.length == 2</code></li>
	<li><code>&#39;a&#39; &lt;= coordinates[0] &lt;= &#39;h&#39;</code></li>
	<li><code>&#39;1&#39; &lt;= coordinates[1] &lt;= &#39;8&#39;</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def squareIsWhite(self, coordinates: str) -> bool:
        x = ord(coordinates[0]) - ord('a') + 1
        y = int(coordinates[1])
        return ((x + y) & 1) == 1
```

### **Java**

```java
class Solution {
    public boolean squareIsWhite(String coordinates) {
        int x = coordinates.charAt(0) - 'a' + 1;
        int y = coordinates.charAt(1) - '0';
        return ((x + y) & 1) == 1;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} coordinates
 * @return {boolean}
 */
var squareIsWhite = function (coordinates) {
    let x = coordinates.charAt(0).charCodeAt() - 'a'.charCodeAt() + 1;
    let y = Number(coordinates.charAt(1));
    return ((x + y) & 1) == 1;
};
```

### **...**

```

```

<!-- tabs:end -->
