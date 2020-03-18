# [01.03. String to URL](https://leetcode-cn.com/problems/string-to-url-lcci)

## Description
<p>Write a method to replace all spaces in a string with &#39;%20&#39;. You may assume that the string has sufficient space at the end to hold the additional characters,and that you are given the &quot;true&quot; length of the string. (Note: If implementing in Java,please use a character array so that you can perform this operation in place.)</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>&quot;Mr John Smith &quot;, 13

<strong>Output: </strong>&quot;Mr%20John%20Smith&quot;

<strong>Explanation: </strong>

The missing numbers are [5,6,8,...], hence the third missing number is 8.

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong>&quot;               &quot;, 5

<strong>Output: </strong>&quot;%20%20%20%20%20&quot;

</pre>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ol>

	<li><code>0 &lt;= S.length &lt;= 500000</code></li>

</ol>




## Solutions


### Python3

```python
class Solution:
    def replaceSpaces(self, S: str, length: int) -> str:
        S = S[:length] if length < len(S) else S
        return S.replace(' ', '%20')
```

### Java

```java

```

### ...
```

```
