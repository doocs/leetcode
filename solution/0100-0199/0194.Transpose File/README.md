<!-- problem:start -->

---

comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0194.Transpose%20File/README.md

---

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

<!-- solution:end -->

<!-- problem:end -->
