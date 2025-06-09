---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3579.Minimum%20Steps%20to%20Convert%20String%20with%20Operations/README_EN.md
---

<!-- problem:start -->

# [3579. Minimum Steps to Convert String with Operations](https://leetcode.com/problems/minimum-steps-to-convert-string-with-operations)

[中文文档](/solution/3500-3599/3579.Minimum%20Steps%20to%20Convert%20String%20with%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given two strings, <code>word1</code> and <code>word2</code>, of equal length. You need to transform <code>word1</code> into <code>word2</code>.</p>

<p>For this, divide <code>word1</code> into one or more <strong>contiguous <span data-keyword="substring-nonempty">substrings</span></strong>. For each substring <code>substr</code> you can perform the following operations:</p>

<ol>
	<li>
	<p><strong>Replace:</strong> Replace the character at any one index of <code>substr</code> with another lowercase English letter.</p>
	</li>
	<li>
	<p><strong>Swap:</strong> Swap any two characters in <code>substr</code>.</p>
	</li>
	<li>
	<p><strong>Reverse Substring:</strong> Reverse <code>substr</code>.</p>
	</li>
</ol>

<p>Each of these counts as <strong>one</strong> operation and each character of each substring can be used in each type of operation at most once (i.e. no single index may be involved in more than one replace, one swap, or one reverse).</p>

<p>Return the <strong>minimum number of operations</strong> required to transform <code>word1</code> into <code>word2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;abcdf&quot;, word2 = &quot;dacbe&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Divide <code>word1</code> into <code>&quot;ab&quot;</code>, <code>&quot;c&quot;</code>, and <code>&quot;df&quot;</code>. The operations are:</p>

<ul>
	<li>For the substring <code>&quot;ab&quot;</code>,

    <ul>
    	<li>Perform operation of type 3 on <code>&quot;ab&quot; -&gt; &quot;ba&quot;</code>.</li>
    	<li>Perform operation of type 1 on <code>&quot;ba&quot; -&gt; &quot;da&quot;</code>.</li>
    </ul>
    </li>
    <li>For the substring <code>&quot;c&quot;</code> do no operations.</li>
    <li>For the substring <code>&quot;df&quot;</code>,
    <ul>
    	<li>Perform operation of type 1 on <code>&quot;df&quot; -&gt; &quot;bf&quot;</code>.</li>
    	<li>Perform operation of type 1 on <code>&quot;bf&quot; -&gt; &quot;be&quot;</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;abceded&quot;, word2 = &quot;baecfef&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Divide <code>word1</code> into <code>&quot;ab&quot;</code>, <code>&quot;ce&quot;</code>, and <code>&quot;ded&quot;</code>. The operations are:</p>

<ul>
	<li>For the substring <code>&quot;ab&quot;</code>,

    <ul>
    	<li>Perform operation of type 2 on <code>&quot;ab&quot; -&gt; &quot;ba&quot;</code>.</li>
    </ul>
    </li>
    <li>For the substring <code>&quot;ce&quot;</code>,
    <ul>
    	<li>Perform operation of type 2 on <code>&quot;ce&quot; -&gt; &quot;ec&quot;</code>.</li>
    </ul>
    </li>
    <li>For the substring <code>&quot;ded&quot;</code>,
    <ul>
    	<li>Perform operation of type 1 on <code>&quot;ded&quot; -&gt; &quot;fed&quot;</code>.</li>
    	<li>Perform operation of type 1 on <code>&quot;fed&quot; -&gt; &quot;fef&quot;</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;abcdef&quot;, word2 = &quot;fedabc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Divide <code>word1</code> into <code>&quot;abcdef&quot;</code>. The operations are:</p>

<ul>
	<li>For the substring <code>&quot;abcdef&quot;</code>,

    <ul>
    	<li>Perform operation of type 3 on <code>&quot;abcdef&quot; -&gt; &quot;fedcba&quot;</code>.</li>
    	<li>Perform operation of type 2 on <code>&quot;fedcba&quot; -&gt; &quot;fedabc&quot;</code>.</li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length == word2.length &lt;= 100</code></li>
	<li><code>word1</code> and <code>word2</code> consist only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
