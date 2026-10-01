---
comments: true
difficulty: Easy
tags:
    - Shell
---

<!-- problem:start -->

# [195. Tenth Line](https://leetcode.com/problems/tenth-line)

[中文文档](/solution/0100-0199/0195.Tenth%20Line/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một tệp văn bản&nbsp;<code>file.txt</code>, chỉ in dòng thứ 10 của tệp.</p>

<p><strong class="example">Ví dụ:</strong></p>

<p>Giả sử <code>file.txt</code> có nội dung sau:</p>

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

<p>Tập lệnh của bạn phải xuất dòng thứ mười, đó là:</p>

<pre>
Line 10
</pre>

<div class="spoilers"><b>Lưu ý:</b><br />
1. Nếu tệp có ít hơn 10 dòng, bạn nên xuất gì?<br />
2. Có ít nhất ba lời giải khác nhau. Hãy thử tìm hiểu tất cả các khả năng.</div>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: sed

<!-- thinking:start -->

> **Tư duy**
>
> In dòng thứ mười hoặc không in gì nếu tệp ngắn hơn. $\textit{sed}\,-n\,10p$ chỉ in dòng $10$; tệp ngắn sẽ cho đầu ra rỗng, vì vậy không cần đếm dòng trước.

<!-- thinking:end -->

<!-- tabs:start -->

#### Shell

```bash
# Đọc từ tệp file.txt và xuất dòng thứ mười ra stdout.
sed -n 10p file.txt
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
