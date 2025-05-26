<h2><a href="https://leetcode.com/problems/lexicographically-smallest-string-after-a-swap">Lexicographically Smallest String After a Swap</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>Given a string <code>s</code> containing only digits, return the <span data-keyword="lexicographically-smaller-string">lexicographically smallest string</span> that can be obtained after swapping <strong>adjacent</strong> digits in <code>s</code> with the same <strong>parity</strong> at most <strong>once</strong>.</p>

<p>Digits have the same parity if both are odd or both are even. For example, 5 and 9, as well as 2 and 4, have the same parity, while 6 and 9 do not.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;45320&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;43520&quot;</span></p>

<p><strong>Explanation: </strong></p>

<p><code>s[1] == &#39;5&#39;</code> and <code>s[2] == &#39;3&#39;</code> both have the same parity, and swapping them results in the lexicographically smallest string.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;001&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;001&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no need to perform a swap because <code>s</code> is already the lexicographically smallest.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of digits.</li>
</ul>
