# [1880. 检查某单词是否等于两单词之和](https://leetcode-cn.com/problems/check-if-word-equals-summation-of-two-words)

[English Version](/solution/1800-1899/1880.Check%20if%20Word%20Equals%20Summation%20of%20Two%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字母的 <strong>字母值</strong> 取决于字母在字母表中的位置，<strong>从 0 开始</strong> 计数。即，<code>'a' -&gt; 0</code>、<code>'b' -&gt; 1</code>、<code>'c' -&gt; 2</code>，以此类推。</p>

<p>对某个由小写字母组成的字符串 <code>s</code> 而言，其 <strong>数值</strong> 就等于将 <code>s</code> 中每个字母的 <strong>字母值</strong> 按顺序 <strong>连接</strong> 并 <strong>转换</strong> 成对应整数。</p>

<ul>
	<li>例如，<code>s = "acb"</code> ，依次连接每个字母的字母值可以得到 <code>"021"</code> ，转换为整数得到 <code>21</code> 。</li>
</ul>

<p>给你三个字符串 <code>firstWord</code>、<code>secondWord</code> 和 <code>targetWord</code> ，每个字符串都由从 <code>'a'</code> 到 <code>'j'</code> （<strong>含 </strong><code>'a'</code> 和 <code>'j'</code><strong> </strong>）的小写英文字母组成。</p>

<p>如果 <code>firstWord</code><em> </em>和<em> </em><code>secondWord</code> 的 <strong>数值之和</strong> 等于<em> </em><code>targetWord</code><em> </em>的数值，返回 <code>true</code> ；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>firstWord = "acb", secondWord = "cba", targetWord = "cdb"
<strong>输出：</strong>true
<strong>解释：</strong>
firstWord 的数值为 "acb" -&gt; "021" -&gt; 21
secondWord 的数值为 "cba" -&gt; "210" -&gt; 210
targetWord 的数值为 "cdb" -&gt; "231" -&gt; 231
由于 21 + 210 == 231 ，返回 true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>firstWord = "aaa", secondWord = "a", targetWord = "aab"
<strong>输出：</strong>false
<strong>解释：</strong>
firstWord 的数值为 "aaa" -&gt; "000" -&gt; 0
secondWord 的数值为 "a" -&gt; "0" -&gt; 0
targetWord 的数值为 "aab" -&gt; "001" -&gt; 1
由于 0 + 0 != 1 ，返回 false</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>firstWord = "aaa", secondWord = "a", targetWord = "aaaa"
<strong>输出：</strong>true
<strong>解释：</strong>
firstWord 的数值为 "aaa" -&gt; "000" -&gt; 0
secondWord 的数值为 "a" -&gt; "0" -&gt; 0
targetWord 的数值为 "aaaa" -&gt; "0000" -&gt; 0
由于 0 + 0 == 0 ，返回 true
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= firstWord.length, </code><code>secondWord.length, </code><code>targetWord.length &lt;= 8</code></li>
	<li><code>firstWord</code>、<code>secondWord</code> 和 <code>targetWord</code> 仅由从 <code>'a'</code> 到 <code>'j'</code> （<strong>含 </strong><code>'a'</code> 和 <code>'j'</code><strong> </strong>）的小写英文字母组成<strong>。</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isSumEqual(self, firstWord: str, secondWord: str, targetWord: str) -> bool:
        def convert(word):
            res = 0
            for c in word:
                res *= 10
                res += (ord(c) - ord('a'))
            return res
        return convert(firstWord) + convert(secondWord) == convert(targetWord)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return convert(firstWord) + convert(secondWord) == convert(targetWord);
    }

    private int convert(String word) {
        int res = 0;
        for (char c : word.toCharArray()) {
            res *= 10;
            res += (c - 'a');
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isSumEqual(string firstWord, string secondWord, string targetWord) {
        return convert(firstWord) + convert(secondWord) == convert(targetWord);
    }
private:
    int convert(string word) {
        int res = 0;
        for (char c : word) {
            res *= 10;
            res += (c - 'a');
        }
        return res;
    }
};
```

### **JavaScript**

```js
/**
 * @param {string} firstWord
 * @param {string} secondWord
 * @param {string} targetWord
 * @return {boolean}
 */
 var isSumEqual = function(firstWord, secondWord, targetWord) {
    let carry = 0;
    let n1 = firstWord.length, n2 = secondWord.length;
    let n3 = targetWord.length;
    for (let i = 0; i < n3; i++) {
        let num1 = getNum(firstWord.charAt(n1 - 1 - i));
        let num2 = getNum(secondWord.charAt(n2 - 1 - i));
        let sum = carry + num1 + num2;
        if (getNum(targetWord.charAt(n3 - 1 - i)) != (sum % 10)) return false;
        carry = parseInt(sum / 10);
    }
    return true;
};

function getNum (char) {
    if (!char) return 0;
    return char.charCodeAt() - 'a'.charCodeAt();
}
```

### **...**

```

```

<!-- tabs:end -->
