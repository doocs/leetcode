# [3114. Latest Time You Can Obtain After Replacing Characters](https://leetcode.com/problems/latest-time-you-can-obtain-after-replacing-characters)

[中文文档](/solution/3100-3199/3114.Latest%20Time%20You%20Can%20Obtain%20After%20Replacing%20Characters/README.md)

<!-- tags: -->

## Description

<p>You are given a string <code>s</code> representing a 12-hour format time where some of the digits (possibly none) are replaced with a <code>&quot;?&quot;</code>.</p>

<p>12-hour times are formatted as <code>&quot;HH:MM&quot;</code>, where <code>HH</code> is between <code>00</code> and <code>11</code>, and <code>MM</code> is between <code>00</code> and <code>59</code>. The earliest 12-hour time is <code>00:00</code>, and the latest is <code>11:59</code>.</p>

<p>You have to replace <strong>all</strong> the <code>&quot;?&quot;</code> characters in <code>s</code> with digits such that the time we obtain by the resulting string is a <strong>valid</strong> 12-hour format time and is the <strong>latest</strong> possible.</p>

<p>Return <em>the resulting string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1?:?4&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;11:54&quot;</span></p>

<p><strong>Explanation:</strong> The latest 12-hour format time we can achieve by replacing <code>&quot;?&quot;</code> characters is <code>&quot;11:54&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0?:5?&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;09:59&quot;</span></p>

<p><strong>Explanation:</strong> The latest 12-hour format time we can achieve by replacing <code>&quot;?&quot;</code> characters is <code>&quot;09:59&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s.length == 5</code></li>
	<li><code>s[2]</code> is equal to the character <code>&quot;:&quot;</code>.</li>
	<li>All characters except <code>s[2]</code> are digits or <code>&quot;?&quot;</code> characters.</li>
	<li>The input is generated such that there is <strong>at least</strong> one time between <code>&quot;00:00&quot;</code> and <code>&quot;11:59&quot;</code> that you can obtain after replacing the <code>&quot;?&quot;</code> characters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
