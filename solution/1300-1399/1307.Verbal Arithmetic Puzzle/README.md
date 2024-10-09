---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1307.Verbal%20Arithmetic%20Puzzle/README.md
rating: 2250
source: 第 169 场周赛 Q4
tags:
    - 数组
    - 数学
    - 字符串
    - 回溯
---

<!-- problem:start -->

# [1307. 口算难题](https://leetcode.cn/problems/verbal-arithmetic-puzzle)

[English Version](/solution/1300-1399/1307.Verbal%20Arithmetic%20Puzzle/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个方程，左边用&nbsp;<code>words</code>&nbsp;表示，右边用&nbsp;<code>result</code> 表示。</p>

<p>你需要根据以下规则检查方程是否可解：</p>

<ul>
	<li>每个字符都会被解码成一位数字（0 - 9）。</li>
	<li>每对不同的字符必须映射到不同的数字。</li>
	<li>每个 <code>words[i]</code> 和 <code>result</code>&nbsp;都会被解码成一个没有前导零的数字。</li>
	<li>左侧数字之和（<code>words</code>）等于右侧数字（<code>result</code>）。&nbsp;</li>
</ul>

<p>如果方程可解，返回&nbsp;<code>True</code>，否则返回&nbsp;<code>False</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = [&quot;SEND&quot;,&quot;MORE&quot;], result = &quot;MONEY&quot;
<strong>输出：</strong>true
<strong>解释：</strong>映射 &#39;S&#39;-&gt; 9, &#39;E&#39;-&gt;5, &#39;N&#39;-&gt;6, &#39;D&#39;-&gt;7, &#39;M&#39;-&gt;1, &#39;O&#39;-&gt;0, &#39;R&#39;-&gt;8, &#39;Y&#39;-&gt;&#39;2&#39;
所以 &quot;SEND&quot; + &quot;MORE&quot; = &quot;MONEY&quot; ,  9567 + 1085 = 10652</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = [&quot;SIX&quot;,&quot;SEVEN&quot;,&quot;SEVEN&quot;], result = &quot;TWENTY&quot;
<strong>输出：</strong>true
<strong>解释：</strong>映射 &#39;S&#39;-&gt; 6, &#39;I&#39;-&gt;5, &#39;X&#39;-&gt;0, &#39;E&#39;-&gt;8, &#39;V&#39;-&gt;7, &#39;N&#39;-&gt;2, &#39;T&#39;-&gt;1, &#39;W&#39;-&gt;&#39;3&#39;, &#39;Y&#39;-&gt;4
所以 &quot;SIX&quot; + &quot;SEVEN&quot; + &quot;SEVEN&quot; = &quot;TWENTY&quot; ,  650 + 68782 + 68782 = 138214</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>words = [&quot;THIS&quot;,&quot;IS&quot;,&quot;TOO&quot;], result = &quot;FUNNY&quot;
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>words = [&quot;LEET&quot;,&quot;CODE&quot;], result = &quot;POINT&quot;
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= words.length &lt;= 5</code></li>
	<li><code>1 &lt;= words[i].length,&nbsp;results.length&nbsp;&lt;= 7</code></li>
	<li><code>words[i], result</code>&nbsp;只含有大写英文字母</li>
	<li>表达式中使用的不同字符数最大为&nbsp;10</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isAnyMapping(
        self, words, row, col, bal, letToDig, digToLet, totalRows, totalCols
    ):
        # If traversed all columns.
        if col == totalCols:
            return bal == 0

        # At the end of a particular column.
        if row == totalRows:
            return bal % 10 == 0 and self.isAnyMapping(
                words, 0, col + 1, bal // 10, letToDig, digToLet, totalRows, totalCols
            )

        w = words[row]

        # If the current string 'w' has no character in the ('col')th index.
        if col >= len(w):
            return self.isAnyMapping(
                words, row + 1, col, bal, letToDig, digToLet, totalRows, totalCols
            )

        # Take the current character in the variable letter.
        letter = w[len(w) - 1 - col]

        # Create a variable 'sign' to check whether we have to add it or subtract it.
        if row < totalRows - 1:
            sign = 1
        else:
            sign = -1

        # If we have a prior valid mapping, then use that mapping.
        # The second condition is for the leading zeros.
        if letter in letToDig and (
            letToDig[letter] != 0
            or (letToDig[letter] == 0 and len(w) == 1)
            or col != len(w) - 1
        ):

            return self.isAnyMapping(
                words,
                row + 1,
                col,
                bal + sign * letToDig[letter],
                letToDig,
                digToLet,
                totalRows,
                totalCols,
            )

        # Choose a new mapping.
        else:
            for i in range(10):
                # If 'i'th mapping is valid then select it.
                if digToLet[i] == "-" and (
                    i != 0 or (i == 0 and len(w) == 1) or col != len(w) - 1
                ):
                    digToLet[i] = letter
                    letToDig[letter] = i

                    # Call the function again with the new mapping.
                    if self.isAnyMapping(
                        words,
                        row + 1,
                        col,
                        bal + sign * letToDig[letter],
                        letToDig,
                        digToLet,
                        totalRows,
                        totalCols,
                    ):
                        return True

                    # Unselect the mapping.
                    digToLet[i] = "-"
                    if letter in letToDig:
                        del letToDig[letter]

        # If nothing is correct then just return false.
        return False

    def isSolvable(self, words, result):
        # Add the string 'result' in the list 'words'.
        words.append(result)

        # Initialize 'totalRows' with the size of the list.
        totalRows = len(words)

        # Find the longest string in the list and set 'totalCols' with the size of that string.
        totalCols = max(len(word) for word in words)

        # Create a HashMap for the letter to digit mapping.
        letToDig = {}

        # Create a list for the digit to letter mapping.
        digToLet = ["-"] * 10

        return self.isAnyMapping(
            words, 0, 0, 0, letToDig, digToLet, totalRows, totalCols
        )
```

#### Java

```java
class Solution {
    private boolean isAnyMapping(List<String> words, int row, int col, int bal,
        HashMap<Character, Integer> letToDig, char[] digToLet, int totalRows, int totalCols) {
        // If traversed all columns.
        if (col == totalCols) {
            return bal == 0;
        }

        // At the end of a particular column.
        if (row == totalRows) {
            return (bal % 10 == 0
                && isAnyMapping(
                    words, 0, col + 1, bal / 10, letToDig, digToLet, totalRows, totalCols));
        }

        String w = words.get(row);

        // If the current string 'w' has no character in the ('col')th index.
        if (col >= w.length()) {
            return isAnyMapping(words, row + 1, col, bal, letToDig, digToLet, totalRows, totalCols);
        }

        // Take the current character in the variable letter.
        char letter = w.charAt(w.length() - 1 - col);

        // Create a variable 'sign' to check whether we have to add it or subtract it.
        int sign = (row < totalRows - 1) ? 1 : -1;

        // If we have a prior valid mapping, then use that mapping.
        // The second condition is for the leading zeros.
        if (letToDig.containsKey(letter)
            && (letToDig.get(letter) != 0 || (letToDig.get(letter) == 0 && w.length() == 1)
                || col != w.length() - 1)) {

            return isAnyMapping(words, row + 1, col, bal + sign * letToDig.get(letter), letToDig,
                digToLet, totalRows, totalCols);

        } else {
            // Choose a new mapping.
            for (int i = 0; i < 10; i++) {
                // If 'i'th mapping is valid then select it.
                if (digToLet[i] == '-'
                    && (i != 0 || (i == 0 && w.length() == 1) || col != w.length() - 1)) {
                    digToLet[i] = letter;
                    letToDig.put(letter, i);

                    // Call the function again with the new mapping.
                    if (isAnyMapping(words, row + 1, col, bal + sign * letToDig.get(letter),
                            letToDig, digToLet, totalRows, totalCols)) {
                        return true;
                    }

                    // Unselect the mapping.
                    digToLet[i] = '-';
                    letToDig.remove(letter);
                }
            }
        }

        // If nothing is correct then just return false.
        return false;
    }

    public boolean isSolvable(String[] wordsArr, String result) {
        // Add the string 'result' in the list 'words'.
        List<String> words = new ArrayList<>();
        for (String word : wordsArr) {
            words.add(word);
        }
        words.add(result);

        int totalRows = words.size();

        // Find the longest string in the list and set 'totalCols' with the size of that string.
        int totalCols = 0;
        for (String word : words) {
            if (totalCols < word.length()) {
                totalCols = word.length();
            }
        }

        // Create a HashMap for the letter to digit mapping.
        HashMap<Character, Integer> letToDig = new HashMap<>();

        // Create a char array for the digit to letter mapping.
        char[] digToLet = new char[10];
        for (int i = 0; i < 10; i++) {
            digToLet[i] = '-';
        }

        return isAnyMapping(words, 0, 0, 0, letToDig, digToLet, totalRows, totalCols);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isAnyMapping(vector<string>& words, int row, int col, int bal, unordered_map<char, int>& letToDig,
        vector<char>& digToLet, int totalRows, int totalCols) {
        // If traversed all columns.
        if (col == totalCols) {
            return bal == 0;
        }

        // At the end of a particular column.
        if (row == totalRows) {
            return (bal % 10 == 0 && isAnyMapping(words, 0, col + 1, bal / 10, letToDig, digToLet, totalRows, totalCols));
        }

        string w = words[row];

        // If the current string 'W' has no character in the ('COL')th index.
        if (col >= w.length()) {
            return isAnyMapping(words, row + 1, col, bal, letToDig, digToLet, totalRows, totalCols);
        }

        // Take the current character in the variable letter.
        char letter = w[w.length() - 1 - col];

        // Create a variable 'SIGN' to check whether we have to add it or subtract it.
        int sign;

        if (row < totalRows - 1) {
            sign = 1;
        } else {
            sign = -1;
        }

        /*
            If we have a prior valid mapping, then use that mapping.
            The second condition is for the leading zeros.
        */
        if (letToDig.count(letter) && (letToDig[letter] != 0 || (letToDig[letter] == 0 && w.length() == 1) || col != w.length() - 1)) {

            return isAnyMapping(words, row + 1, col, bal + sign * letToDig[letter],
                letToDig, digToLet, totalRows, totalCols);

        }
        // Choose a new mapping.
        else {
            for (int i = 0; i < 10; i++) {

                // If 'i'th mapping is valid then select it.
                if (digToLet[i] == '-' && (i != 0 || (i == 0 && w.length() == 1) || col != w.length() - 1)) {
                    digToLet[i] = letter;
                    letToDig[letter] = i;

                    // Call the function again with the new mapping.
                    bool x = isAnyMapping(words, row + 1, col, bal + sign * letToDig[letter],
                        letToDig, digToLet, totalRows, totalCols);

                    if (x == true) {
                        return true;
                    }

                    // Unselect the mapping.
                    digToLet[i] = '-';
                    if (letToDig.find(letter) != letToDig.end()) {
                        letToDig.erase(letter);
                    }
                }
            }
        }

        // If nothing is correct then just return false.
        return false;
    }

    bool isSolvable(vector<string>& words, string result) {
        // Add the string 'RESULT' in the vector 'WORDS'.
        words.push_back(result);

        int totalRows;
        int totalCols;

        // Initialize 'TOTALROWS' with the size of the vector.
        totalRows = words.size();

        // Find the longest string in the vector and set 'TOTALCOLS' with the size of that string.
        totalCols = 0;

        for (int i = 0; i < words.size(); i++) {

            // If the current string is the longest then update 'TOTALCOLS' with its length.
            if (totalCols < words[i].size()) {
                totalCols = words[i].size();
            }
        }

        // Create a HashMap for the letter to digit mapping.
        unordered_map<char, int> letToDig;

        // Create a vector for the digit to letter mapping.
        vector<char> digToLet(10, '-');

        return isAnyMapping(words, 0, 0, 0, letToDig, digToLet, totalRows, totalCols);
    }
};
```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
