---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0193.Valid%20Phone%20Numbers/README.md
tags:
    - Shell
---

<!-- problem:start -->

# [193. 有效电话号码](https://leetcode.cn/problems/valid-phone-numbers)

[English Version](/solution/0100-0199/0193.Valid%20Phone%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个包含电话号码列表（一行一个电话号码）的文本文件 <code>file.txt</code>，写一个单行 bash 脚本输出所有有效的电话号码。</p>

<p>你可以假设一个有效的电话号码必须满足以下两种格式： (xxx) xxx-xxxx 或 xxx-xxx-xxxx。（x 表示一个数字）</p>

<p>你也可以假设每行前后没有多余的空格字符。</p>

<p> </p>

<p><strong>示例：</strong></p>

<p>假设 <code>file.txt</code> 内容如下：</p>

<pre>
987-123-4567
123 456 7890
(123) 456-7890
</pre>

<p>你的脚本应当输出下列有效的电话号码：</p>

<pre>
987-123-4567
(123) 456-7890
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：awk

<!-- tabs:start -->

#### Shell

```bash
# Read from the file file.txt and output all valid phone numbers to stdout.
awk '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/' file.txt
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
