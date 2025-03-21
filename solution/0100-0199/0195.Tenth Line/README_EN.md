---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0195.Tenth%20Line/README_EN.md
tags:
    - Shell
---

<!-- problem:start -->

# [195. Tenth Line](https://leetcode.com/problems/tenth-line)

[中文文档](/solution/0100-0199/0195.Tenth%20Line/README.md)

## Description

<!-- description:start -->

<p>Given a text file&nbsp;<code>file.txt</code>, print&nbsp;just the 10th line of the&nbsp;file.</p>

<p><strong class="example">Example:</strong></p>

<p>Assume that <code>file.txt</code> has the following content:</p>

<pre>

Line 1

Line 2

Line 3

Line 4

Line 5

Line 6

Line 7

Line 8

Line 9

Line 10

</pre>

<p>Your script should output the tenth line, which is:</p>

<pre>

Line 10

</pre>

<div class="spoilers"><b>Note:</b><br />

1. If the file contains less than 10 lines, what should you output?<br />

2. There&#39;s at least three different solutions. Try to explore all possibilities.</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: sed

<!-- tabs:start -->

#### Shell

```bash
# Read from the file file.txt and output the tenth line to stdout.
sed -n 10p file.txt
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
