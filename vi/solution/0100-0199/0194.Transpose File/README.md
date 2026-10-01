---
comments: true
difficulty: Medium
tags:
    - Shell
---

<!-- problem:start -->

# [194. Transpose File](https://leetcode.com/problems/transpose-file)

[中文文档](/solution/0100-0199/0194.Transpose%20File/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một tệp văn bản <code>file.txt</code>, hãy chuyển vị nội dung của tệp.</p>

<p>Có thể giả sử rằng mỗi hàng có cùng số cột và mỗi trường được phân tách bằng ký tự <code>&#39; &#39;</code>.</p>

<p><strong class="example">Ví dụ:</strong></p>

<p>Nếu <code>file.txt</code> có nội dung sau:</p>

<pre>
name age
alice 21
ryan 30
</pre>

<p>Xuất kết quả sau:</p>

<pre>
name alice ryan
age 21 30
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: awk

<!-- thinking:start -->

> **Tư duy**
>
> Chuyển vị văn bản được phân tách bằng khoảng trắng. Khi đọc một hàng, nối trường $i$ vào chuỗi kết quả $i$; sau khi đọc xong tệp, in các chuỗi đó. Các giá trị $\textit{NF}/\textit{NR}$ của $\textit{awk}$ lần lượt cho biết chỉ số cột và liệu đây có phải là hàng đầu tiên hay không, vì vậy chúng ta biết khi nào cần chèn một dấu cách.

<!-- thinking:end -->

<!-- tabs:start -->

#### Shell

```bash
# Đọc tệp file.txt và in nội dung đã chuyển vị ra stdout.
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
