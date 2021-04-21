# [288. Unique Word Abbreviation](https://leetcode.com/problems/unique-word-abbreviation)

[中文文档](/solution/0200-0299/0288.Unique%20Word%20Abbreviation/README.md)

## Description

<p>The <strong>abbreviation</strong> of a word is a concatenation of its first letter, the number of characters between the first and last letter, and its last letter. If a word has only two characters, then it is an <strong>abbreviation</strong> of itself.</p>

<p>For example:</p>

<ul>
	<li><code>dog --&gt; d1g</code> because there is one letter between the first letter <code>&#39;d&#39;</code> and the last letter <code>&#39;g&#39;</code>.</li>
	<li><code>internationalization --&gt; i18n</code> because there are 18 letters between the first letter <code>&#39;i&#39;</code> and the last letter <code>&#39;n&#39;</code>.</li>
	<li><code>it --&gt; it</code> because any word with only two characters is an <strong>abbreviation</strong> of itself.</li>
</ul>

<p>Implement the <code>ValidWordAbbr</code> class:</p>

<ul>
	<li><code>ValidWordAbbr(String[] dictionary)</code> Initializes the object with a <code>dictionary</code> of words.</li>
	<li><code>boolean isUnique(string word)</code> Returns <code>true</code> if <strong>either</strong> of the following conditions are met (otherwise returns <code>false</code>):
	<ul>
		<li>There is no word in <code>dictionary</code> whose <strong>abbreviation</strong> is equal to <code>word</code>&#39;s <strong>abbreviation</strong>.</li>
		<li>For any word in <code>dictionary</code> whose <strong>abbreviation</strong> is equal to <code>word</code>&#39;s <strong>abbreviation</strong>, that word and <code>word</code> are <strong>the same</strong>.</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;ValidWordAbbr&quot;, &quot;isUnique&quot;, &quot;isUnique&quot;, &quot;isUnique&quot;, &quot;isUnique&quot;]
[[[&quot;deer&quot;, &quot;door&quot;, &quot;cake&quot;, &quot;card&quot;]], [&quot;dear&quot;], [&quot;cart&quot;], [&quot;cane&quot;], [&quot;make&quot;]]
<strong>Output</strong>
[null, false, true, false, true]

<strong>Explanation</strong>
ValidWordAbbr validWordAbbr = new ValidWordAbbr([&quot;deer&quot;, &quot;door&quot;, &quot;cake&quot;, &quot;card&quot;]);
validWordAbbr.isUnique(&quot;dear&quot;); // return false, dictionary word &quot;deer&quot; and word &quot;dear&quot; have the same abbreviation
&nbsp;                               // &quot;d2r&quot; but are not the same.
validWordAbbr.isUnique(&quot;cart&quot;); // return true, no words in the dictionary have the abbreviation &quot;c2t&quot;.
validWordAbbr.isUnique(&quot;cane&quot;); // return false, dictionary word &quot;cake&quot; and word &quot;cane&quot; have the same abbreviation 
                                // &quot;c2e&quot; but are not the same.
validWordAbbr.isUnique(&quot;make&quot;); // return true, no words in the dictionary have the abbreviation &quot;m2e&quot;.
validWordAbbr.isUnique(&quot;cake&quot;); // return true, because &quot;cake&quot; is already in the dictionary and no other word in the dictionary has &quot;c2e&quot; abbreviation.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 20</code></li>
	<li><code>dictionary[i]</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= word.length &lt;= 20</code></li>
	<li><code>word</code> consists of lowercase English letters.</li>
	<li>At most <code>5000</code> calls will be made to <code>isUnique</code>.</li>
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
