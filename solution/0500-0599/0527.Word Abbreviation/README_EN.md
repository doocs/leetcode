# [527. Word Abbreviation](https://leetcode.com/problems/word-abbreviation)

[中文文档](/solution/0500-0599/0527.Word%20Abbreviation/README.md)

## Description

<p>Given an array of n distinct non-empty strings, you need to generate <b>minimal</b> possible abbreviations for every word following rules below.</p>

<ol>

<li>Begin with the first character and then the number of characters abbreviated, which followed by the last character.</li>

<li>If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.</li>

<li> If the abbreviation doesn't make the word shorter, then keep it as original.</li>

</ol>

<p><b>Example:</b><br />

<pre>

<b>Input:</b> ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]

<b>Output:</b> ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]

</pre>

</p>

<b>Note:</b>

<ol>

<li> Both n and the length of each word will not exceed 400.</li>

<li> The length of each word is greater than 1.</li>

<li> The words consist of lowercase English letters only.</li>

<li> The return answers should be <b>in the same order</b> as the original array.</li>

</ol>

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
