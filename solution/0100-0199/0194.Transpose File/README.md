---
comments: true
difficulty: 中等
tags:
    - Shell
---

<!-- problem:start -->

# [194. 转置文件](https://leetcode.cn/problems/transpose-file)

[English Version](/solution/0100-0199/0194.Transpose%20File/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个文件 <code>file.txt</code>，转置它的内容。</p>

<p>你可以假设每行列数相同，并且每个字段由 <code>' '</code> 分隔。</p>

<p> </p>

<p><strong>示例：</strong></p>

<p>假设 <code>file.txt</code> 文件内容如下：</p>

<pre>
name age
alice 21
ryan 30
</pre>

<p>应当输出：</p>

<pre>
name alice ryan
age 21 30
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：awk

<!-- thinking:start -->

> **思考**
>
> 把空格分列的文本转置，行变列。按行读入时，第 $i$ 个字段追加到第 $i$ 个结果串；读完后按字段下标输出每一行。$\textit{awk}$ 的 $\textit{NF}/\textit{NR}$ 正好提供列号与是否首行，用来决定要不要先加空格。

<!-- thinking:end -->

<!-- tabs:start -->

#### Shell

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

<!-- solution:end -->

<!-- problem:end -->
