---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0193.Valid%20Phone%20Numbers/README_EN.md
tags:
    - Shell
---

<!-- problem:start -->

# [193. Valid Phone Numbers](https://leetcode.com/problems/valid-phone-numbers)

[中文文档](/solution/0100-0199/0193.Valid%20Phone%20Numbers/README.md)

## Description

<p>Given a text file <code>file.txt</code> that contains a list of phone numbers (one per line), write a one-liner bash script to print all valid phone numbers.</p>

<p>You may assume that a valid phone number must appear in one of the following two formats: (xxx) xxx-xxxx or xxx-xxx-xxxx. (x means a digit)</p>

<p>You may also assume each line in the text file must not contain leading or trailing white spaces.</p>

<p><strong class="example">Example:</strong></p>

<p>Assume that <code>file.txt</code> has the following content:</p>

<pre>
987-123-4567
123 456 7890
(123) 456-7890
</pre>

<p>Your script should output the following valid phone numbers:</p>

<pre>
987-123-4567
(123) 456-7890
</pre>

## Solutions

<!-- solution:start -->

### Solution 1: awk

<!-- tabs:start -->

```bash
# Read from the file file.txt and output all valid phone numbers to stdout.
awk '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/' file.txt
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
