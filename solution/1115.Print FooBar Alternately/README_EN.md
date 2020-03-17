# [1115. Print FooBar Alternately](https://leetcode.com/problems/print-foobar-alternately)

## Description
<p>Suppose you are given the following code:</p>

<pre>
class FooBar {
  public void foo() {
&nbsp; &nbsp; for (int i = 0; i &lt; n; i++) {
&nbsp; &nbsp; &nbsp; print(&quot;foo&quot;);
&nbsp;   }
  }

  public void bar() {
&nbsp; &nbsp; for (int i = 0; i &lt; n; i++) {
&nbsp; &nbsp; &nbsp; print(&quot;bar&quot;);
&nbsp; &nbsp; }
  }
}
</pre>

<p>The same instance of <code>FooBar</code> will be passed to two different threads. Thread A will call&nbsp;<code>foo()</code> while thread B will call&nbsp;<code>bar()</code>.&nbsp;Modify the given program to output &quot;foobar&quot; <em>n</em> times.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>
<b>Input:</b> n = 1
<b>Output:</b> &quot;foobar&quot;
<strong>Explanation:</strong> There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar(). &quot;foobar&quot; is being output 1 time.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<b>Input:</b> n = 2
<b>Output:</b> &quot;foobarfoobar&quot;
<strong>Explanation:</strong> &quot;foobar&quot; is being output 2 times.
</pre>



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

