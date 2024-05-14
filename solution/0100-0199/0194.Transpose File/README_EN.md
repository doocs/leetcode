---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0194.Transpose%20File/README_EN.md
tags:
    - Shell
---

# [194. Transpose File](https://leetcode.com/problems/transpose-file)

[中文文档](/solution/0100-0199/0194.Transpose%20File/README.md)

## Description

<p>Given a text file <code>file.txt</code>, transpose its content.</p>

<p>You may assume that each row has the same number of columns, and each field is separated by the <code>&#39; &#39;</code> character.</p>

<p><strong class="example">Example:</strong></p>

<p>If <code>file.txt</code> has the following content:</p>

<pre>
name age
alice 21
ryan 30
</pre>

<p>Output the following:</p>

<pre>
name alice ryan
age 21 30
</pre>

## Solutions

### Solution 1: awk

<!-- tabs:start -->

```bash
# Read from the file file.txt and print its transposed content to stdout.
awk '
{
  for (i=1; i<=NF; i++) {
    if(NR == 1) {
      res[i] = re$i
    } else {
      res[i] = res[i]" "$i
    }
  }
}END {
  for (i=1;i<=NF;i++) {
    print res[i]
  }
}
' file.txt
```

<!-- tabs:end -->

<!-- end -->
