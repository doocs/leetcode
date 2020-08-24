# [288. Unique Word Abbreviation](https://leetcode.com/problems/unique-word-abbreviation)

[中文文档](/solution/0200-0299/0288.Unique%20Word%20Abbreviation/README.md)

## Description
<p>An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:</p>

<pre>
a) it                      --> it    (no abbreviation)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n

Additionally for any string s of size less than or equal to 2 their abbreviation is the same string s.
</pre>

<p>Find whether its abbreviation is unique in the dictionary. A word's abbreviation is called <em>unique</em> if any of the following conditions is met:</p>

<ul>
	<li>There is no word in <code>dictionary</code> such that their abbreviation is equal to the abbreviation of <code>word</code>.</li>
	<li>Else, for all words in <code>dictionary</code> such that their abbreviation is equal to the abbreviation of <code>word</code> those words are equal to <code>word</code>.</li>
</ul>

<p> </p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
["ValidWordAbbr","isUnique","isUnique","isUnique","isUnique"]
[[["deer","door","cake","card"]],["dear"],["cart"],["cane"],["make"]]
<strong>Output</strong>
[null,false,true,false,true]

<strong>Explanation</strong>
ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
validWordAbbr.isUnique("dear"); // return False
validWordAbbr.isUnique("cart"); // return True
validWordAbbr.isUnique("cane"); // return False
validWordAbbr.isUnique("make"); // return True
</pre>

<p> </p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>Each word will only consist of lowercase English characters.</li>
</ul>



## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->