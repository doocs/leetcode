---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3597.Partition%20String/README_EN.md
---

<!-- problem:start -->

# [3597. Partition String](https://leetcode.com/problems/partition-string)

[中文文档](/solution/3500-3599/3597.Partition%20String/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>, partition it into <strong>unique segments</strong> according to the following procedure:</p>

<ul>
	<li>Start building a segment beginning at index 0.</li>
	<li>Continue extending the current segment character by character until the current segment has not been seen before.</li>
	<li>Once the segment is unique, add it to your list of segments, mark it as seen, and begin a new segment from the next index.</li>
	<li>Repeat until you reach the end of <code>s</code>.</li>
</ul>

<p>Return an array of strings <code>segments</code>, where <code>segments[i]</code> is the <code>i<sup>th</sup></code> segment created.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abbccccd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;a&quot;,&quot;b&quot;,&quot;bc&quot;,&quot;c&quot;,&quot;cc&quot;,&quot;d&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Index</th>
			<th style="border: 1px solid black;">Segment After Adding</th>
			<th style="border: 1px solid black;">Seen Segments</th>
			<th style="border: 1px solid black;">Current Segment Seen Before?</th>
			<th style="border: 1px solid black;">New Segment</th>
			<th style="border: 1px solid black;">Updated Seen Segments</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">&quot;b&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">&quot;b&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">&quot;b&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">&quot;bc&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">&quot;c&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">&quot;c&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">&quot;c&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">&quot;cc&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;, &quot;cc&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">&quot;d&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;, &quot;cc&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;, &quot;cc&quot;, &quot;d&quot;]</td>
		</tr>
	</tbody>
</table>

<p>Hence, the final output is <code>[&quot;a&quot;, &quot;b&quot;, &quot;bc&quot;, &quot;c&quot;, &quot;cc&quot;, &quot;d&quot;]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;a&quot;,&quot;aa&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Index</th>
			<th style="border: 1px solid black;">Segment After Adding</th>
			<th style="border: 1px solid black;">Seen Segments</th>
			<th style="border: 1px solid black;">Current Segment Seen Before?</th>
			<th style="border: 1px solid black;">New Segment</th>
			<th style="border: 1px solid black;">Updated Seen Segments</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">&quot;aa&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;]</td>
			<td style="border: 1px solid black;">No</td>
			<td style="border: 1px solid black;">&quot;&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;aa&quot;]</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;aa&quot;]</td>
			<td style="border: 1px solid black;">Yes</td>
			<td style="border: 1px solid black;">&quot;a&quot;</td>
			<td style="border: 1px solid black;">[&quot;a&quot;, &quot;aa&quot;]</td>
		</tr>
	</tbody>
</table>

<p>Hence, the final output is <code>[&quot;a&quot;, &quot;aa&quot;]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters. </li>
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
