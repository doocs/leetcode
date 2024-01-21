# [3016. Minimum Number of Pushes to Type Word II](https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii)

[中文文档](/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/README.md)

## Description

<p>You are given a string <code>word</code> containing lowercase English letters.</p>

<p>Telephone keypads have keys mapped with <strong>distinct</strong> collections of lowercase English letters, which can be used to form words by pushing them. For example, the key <code>2</code> is mapped with <code>[&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]</code>, we need to push the key one time to type <code>&quot;a&quot;</code>, two times to type <code>&quot;b&quot;</code>, and three times to type <code>&quot;c&quot;</code> <em>.</em></p>

<p>It is allowed to remap the keys numbered <code>2</code> to <code>9</code> to <strong>distinct</strong> collections of letters. The keys can be remapped to <strong>any</strong> amount of letters, but each letter <strong>must</strong> be mapped to <strong>exactly</strong> one key. You need to find the <strong>minimum</strong> number of times the keys will be pushed to type the string <code>word</code>.</p>

<p>Return <em>the <strong>minimum</strong> number of pushes needed to type </em><code>word</code> <em>after remapping the keys</em>.</p>

<p>An example mapping of letters to keys on a telephone keypad is given below. Note that <code>1</code>, <code>*</code>, <code>#</code>, and <code>0</code> do <strong>not</strong> map to any letters.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/images/keypaddesc.png" style="width: 329px; height: 313px;" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/images/keypadv1e1.png" style="width: 329px; height: 313px;" />
<pre>
<strong>Input:</strong> word = &quot;abcde&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The remapped keypad given in the image provides the minimum cost.
&quot;a&quot; -&gt; one push on key 2
&quot;b&quot; -&gt; one push on key 3
&quot;c&quot; -&gt; one push on key 4
&quot;d&quot; -&gt; one push on key 5
&quot;e&quot; -&gt; one push on key 6
Total cost is 1 + 1 + 1 + 1 + 1 = 5.
It can be shown that no other mapping can provide a lower cost.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/images/keypadv2e2.png" style="width: 329px; height: 313px;" />
<pre>
<strong>Input:</strong> word = &quot;xyzxyzxyzxyz&quot;
<strong>Output:</strong> 12
<strong>Explanation:</strong> The remapped keypad given in the image provides the minimum cost.
&quot;x&quot; -&gt; one push on key 2
&quot;y&quot; -&gt; one push on key 3
&quot;z&quot; -&gt; one push on key 4
Total cost is 1 * 4 + 1 * 4 + 1 * 4 = 12
It can be shown that no other mapping can provide a lower cost.
Note that the key 9 is not mapped to any letter: it is not necessary to map letters to every key, but to map all the letters.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3016.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20II/images/keypadv2.png" style="width: 329px; height: 313px;" />
<pre>
<strong>Input:</strong> word = &quot;aabbccddeeffgghhiiiiii&quot;
<strong>Output:</strong> 24
<strong>Explanation:</strong> The remapped keypad given in the image provides the minimum cost.
&quot;a&quot; -&gt; one push on key 2
&quot;b&quot; -&gt; one push on key 3
&quot;c&quot; -&gt; one push on key 4
&quot;d&quot; -&gt; one push on key 5
&quot;e&quot; -&gt; one push on key 6
&quot;f&quot; -&gt; one push on key 7
&quot;g&quot; -&gt; one push on key 8
&quot;h&quot; -&gt; two pushes on key 9
&quot;i&quot; -&gt; one push on key 9
Total cost is 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 2 * 2 + 6 * 1 = 24.
It can be shown that no other mapping can provide a lower cost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code> consists of lowercase English letters.</li>
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
