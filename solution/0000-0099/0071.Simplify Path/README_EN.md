# [71. Simplify Path](https://leetcode.com/problems/simplify-path)

## Description
<p>Given an <strong>absolute path</strong> for a file (Unix-style), simplify it. Or in other words, convert it to the <strong>canonical path</strong>.</p>

<p>In a UNIX-style file system, a period <code>.</code>&nbsp;refers to the current directory. Furthermore, a double period <code>..</code>&nbsp;moves the directory up a level. For more information, see:&nbsp;<a href="https://www.linuxnix.com/abslute-path-vs-relative-path-in-linuxunix/" target="_blank">Absolute path&nbsp;vs&nbsp;relative&nbsp;path&nbsp;in&nbsp;Linux/Unix</a></p>

<p>Note that the returned canonical path must always begin&nbsp;with a slash <code>/</code>, and there must be only a single slash <code>/</code>&nbsp;between two directory names.&nbsp;The last directory name (if it exists) <b>must not</b>&nbsp;end with a trailing <code>/</code>. Also, the canonical path must be the <strong>shortest</strong> string&nbsp;representing the absolute path.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: &quot;</strong><span id="example-input-1-1">/home/&quot;</span>
<strong>Output: &quot;</strong><span id="example-output-1">/home&quot;
<strong>Explanation:</strong> Note that there is no trailing slash after the last directory name.</span>
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: &quot;</strong><span id="example-input-1-1">/../&quot;</span>
<strong>Output: &quot;</strong><span id="example-output-1">/&quot;</span>
<strong>Explanation:</strong> Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input: &quot;</strong><span id="example-input-1-1">/home//foo/&quot;</span>
<strong>Output: &quot;</strong><span id="example-output-1">/home/foo&quot;</span>
<strong>Explanation: </strong>In the canonical path, multiple consecutive slashes are replaced by a single one.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input: &quot;</strong><span id="example-input-1-1">/a/./b/../../c/&quot;</span>
<strong>Output: &quot;</strong><span id="example-output-1">/c&quot;</span>
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input: &quot;</strong><span id="example-input-1-1">/a/../../b/../c//.//&quot;</span>
<strong>Output: &quot;</strong><span id="example-output-1">/c&quot;</span>
</pre>

<p><strong>Example 6:</strong></p>

<pre>
<strong>Input: &quot;</strong><span id="example-input-1-1">/a//b////c/d//././/..&quot;</span>
<strong>Output: &quot;</strong><span id="example-output-1">/a/b/c&quot;</span>
</pre>



## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
