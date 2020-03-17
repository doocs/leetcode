# [843. Guess the Word](https://leetcode.com/problems/guess-the-word)

## Description
<p>This problem is an&nbsp;<strong><em>interactive problem</em></strong>&nbsp;new to the LeetCode platform.</p>

<p>We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as <strong>secret</strong>.</p>

<p>You may call <code>master.guess(word)</code>&nbsp;to guess a word.&nbsp; The guessed word should have&nbsp;type <code>string</code>&nbsp;and must be from the original list&nbsp;with 6 lowercase letters.</p>

<p>This function returns an&nbsp;<code>integer</code>&nbsp;type, representing&nbsp;the number of exact matches (value and position) of your guess to the <strong>secret word</strong>.&nbsp; Also, if your guess is not in the given wordlist, it will return <code>-1</code> instead.</p>

<p>For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to <code>master.guess</code>&nbsp;and at least one of these guesses was the <strong>secret</strong>, you pass the testcase.</p>

<p>Besides the example test case below, there will be 5&nbsp;additional test cases, each with 100 words in the word list.&nbsp; The letters of each word in those testcases were chosen&nbsp;independently at random from <code>&#39;a&#39;</code> to <code>&#39;z&#39;</code>, such that every word in the given word lists is unique.</p>

<pre>
<strong>Example 1:</strong>
<strong>Input:</strong>&nbsp;secret = &quot;acckzz&quot;, wordlist = [&quot;acckzz&quot;,&quot;ccbazz&quot;,&quot;eiowzz&quot;,&quot;abcczz&quot;]

<strong>Explanation:</strong>

<code>master.guess(&quot;aaaaaa&quot;)</code> returns -1, because&nbsp;<code>&quot;aaaaaa&quot;</code>&nbsp;is not in wordlist.
<code>master.guess(&quot;acckzz&quot;) </code>returns 6, because&nbsp;<code>&quot;acckzz&quot;</code> is secret and has all 6&nbsp;matches.
<code>master.guess(&quot;ccbazz&quot;)</code> returns 3, because<code>&nbsp;&quot;ccbazz&quot;</code>&nbsp;has 3 matches.
<code>master.guess(&quot;eiowzz&quot;)</code> returns 2, because&nbsp;<code>&quot;eiowzz&quot;</code>&nbsp;has 2&nbsp;matches.
<code>master.guess(&quot;abcczz&quot;)</code> returns 4, because&nbsp;<code>&quot;abcczz&quot;</code> has 4 matches.

We made 5 calls to&nbsp;master.guess and one of them was the secret, so we pass the test case.
</pre>

<p><strong>Note:</strong>&nbsp; Any solutions that attempt to circumvent the judge&nbsp;will result in disqualification.</p>



## Solution
<!-- Type common method here -->


### Python3
<!-- Type special method here -->

```python

```

### Java
<!-- Type special method here -->

```java

```

### ...
```

```

