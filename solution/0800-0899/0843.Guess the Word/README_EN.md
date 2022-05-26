# [843. Guess the Word](https://leetcode.com/problems/guess-the-word)

[中文文档](/solution/0800-0899/0843.Guess%20the%20Word/README.md)

## Description

<p>This is an <strong><em>interactive problem</em></strong>.</p>

<p>You are given an array of <strong>unique</strong> strings <code>wordlist</code> where <code>wordlist[i]</code> is <code>6</code> letters long, and one word in this list is chosen as <code>secret</code>.</p>

<p>You may call <code>Master.guess(word)</code> to guess a word. The guessed word should have type <code>string</code> and must be from the original list with <code>6</code> lowercase letters.</p>

<p>This function returns an <code>integer</code> type, representing the number of exact matches (value and position) of your guess to the <code>secret</code> word. Also, if your guess is not in the given wordlist, it will return <code>-1</code> instead.</p>

<p>For each test case, you have exactly <code>10</code> guesses to guess the word. At the end of any number of calls, if you have made <code>10</code> or fewer calls to <code>Master.guess</code> and at least one of these guesses was <code>secret</code>, then you pass the test case.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> secret = &quot;acckzz&quot;, wordlist = [&quot;acckzz&quot;,&quot;ccbazz&quot;,&quot;eiowzz&quot;,&quot;abcczz&quot;], numguesses = 10
<strong>Output:</strong> You guessed the secret word correctly.
<strong>Explanation:</strong>
master.guess(&quot;aaaaaa&quot;) returns -1, because &quot;aaaaaa&quot; is not in wordlist.
master.guess(&quot;acckzz&quot;) returns 6, because &quot;acckzz&quot; is secret and has all 6 matches.
master.guess(&quot;ccbazz&quot;) returns 3, because &quot;ccbazz&quot; has 3 matches.
master.guess(&quot;eiowzz&quot;) returns 2, because &quot;eiowzz&quot; has 2 matches.
master.guess(&quot;abcczz&quot;) returns 4, because &quot;abcczz&quot; has 4 matches.
We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> secret = &quot;hamada&quot;, wordlist = [&quot;hamada&quot;,&quot;khaled&quot;], numguesses = 10
<strong>Output:</strong> You guessed the secret word correctly.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= wordlist.length &lt;= 100</code></li>
	<li><code>wordlist[i].length == 6</code></li>
	<li><code>wordlist[i]</code> consist of lowercase English letters.</li>
	<li>All the strings of <code>wordlist</code> are <strong>unique</strong>.</li>
	<li><code>secret</code> exists in <code>wordlist</code>.</li>
	<li><code>numguesses == 10</code></li>
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
