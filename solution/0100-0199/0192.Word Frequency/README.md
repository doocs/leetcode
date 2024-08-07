---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0192.Word%20Frequency/README.md
tags:
    - Shell
---

<!-- problem:start -->

# [192. 统计词频](https://leetcode.cn/problems/word-frequency)

[English Version](/solution/0100-0199/0192.Word%20Frequency/README_EN.md)

## 题目描述

<!-- description:start -->

<p>写一个 bash 脚本以统计一个文本文件&nbsp;<code>words.txt</code>&nbsp;中每个单词出现的<span data-keyword="frequency-textfile">频率</span>。</p>

<p>为了简单起见，你可以假设：</p>

<ul>
	<li><code>words.txt</code>只包括小写字母和&nbsp;<code>' '</code>&nbsp;。</li>
	<li>每个单词只由小写字母组成。</li>
	<li>单词间由一个或多个空格字符分隔。</li>
</ul>

<p><strong>示例:</strong></p>

<p>假设 <code>words.txt</code> 内容如下：</p>

<pre>
the day is sunny the the
the sunny is is
</pre>

<p>你的脚本应当输出（以词频降序排列）：</p>

<pre>
the 4
is 3
sunny 2
day 1
</pre>

<p><strong>说明:</strong></p>

<ul>
	<li>不要担心词频相同的单词的排序问题，每个单词出现的频率都是唯一的。</li>
	<li>你可以使用一行&nbsp;<a href="http://tldp.org/HOWTO/Bash-Prog-Intro-HOWTO-4.html">Unix pipes</a>&nbsp;实现吗？</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：awk

<!-- tabs:start -->

#### Shell

```bash
# Read from the file words.txt and output the word frequency list to stdout.
cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -nr | awk '{print $2, $1}'
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
