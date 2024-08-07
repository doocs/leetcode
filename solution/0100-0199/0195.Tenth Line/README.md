---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0195.Tenth%20Line/README.md
tags:
    - Shell
---

<!-- problem:start -->

# [195. 第十行](https://leetcode.cn/problems/tenth-line)

[English Version](/solution/0100-0199/0195.Tenth%20Line/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个文本文件&nbsp;<code>file.txt</code>，请只打印这个文件中的第十行。</p>

<p><strong>示例:</strong></p>

<p>假设&nbsp;<code>file.txt</code> 有如下内容：</p>

<pre>Line 1
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

<p>你的脚本应当显示第十行：</p>

<pre>Line 10
</pre>

<p><strong>说明:</strong><br>
1. 如果文件少于十行，你应当输出什么？<br>
2. 至少有三种不同的解法，请尝试尽可能多的方法来解题。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：sed

<!-- tabs:start -->

#### Shell

```bash
# Read from the file file.txt and output the tenth line to stdout.
sed -n 10p file.txt
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
