---
comments: true
difficulty: Medium
tags:
    - Shell
---

<!-- problem:start -->

# [192. Word Frequency](https://leetcode.com/problems/word-frequency)

[中文文档](/solution/0100-0199/0192.Word%20Frequency/README.md)

## Mô tả

<!-- description:start -->

<p>Viết một bash script để tính <span data-keyword="frequency-textfile">tần suất</span> của mỗi từ trong tệp văn bản <code>words.txt</code>.</p>

<p>Để đơn giản, bạn có thể giả sử:</p>

<ul>
	<li><code>words.txt</code> chỉ chứa các ký tự chữ thường và ký tự khoảng trắng <code>&#39; &#39;</code>.</li>
	<li>Mỗi từ chỉ được gồm các ký tự chữ thường.</li>
	<li>Các từ được phân tách bởi một hoặc nhiều ký tự khoảng trắng.</li>
</ul>

<p><strong class="example">Ví dụ:</strong></p>

<p>Giả sử <code>words.txt</code> có nội dung sau:</p>

<pre>
the day is sunny the the
the sunny is is
</pre>

<p>Script của bạn nên in ra kết quả sau, được sắp xếp theo tần suất giảm dần:</p>

<pre>
the 4
is 3
sunny 2
day 1
</pre>

<p><b>Lưu ý:</b></p>

<ul>
	<li>Không cần xử lý các trường hợp hòa, vì đảm bảo rằng số lần xuất hiện của mỗi từ là duy nhất.</li>
	<li>Bạn có thể viết nó trên một dòng bằng cách sử dụng <a href="http://tldp.org/HOWTO/Bash-Prog-Intro-HOWTO-4.html">Unix pipes</a> không?</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: awk

<!-- thinking:start -->

> **Tư duy**
>
> Tần suất của các từ, theo thứ tự phổ biến nhất trước. Thu gọn các khoảng trắng thành ký tự xuống dòng để mỗi từ nằm trên một dòng, sắp xếp, sau đó dùng $\textit{uniq}\,-c$. Sắp xếp các số đếm theo thứ tự số giảm dần, rồi để $\textit{awk}$ đổi “count word” thành “word count”.

<!-- thinking:end -->

<!-- tabs:start -->

#### Shell

```bash
# Đọc tệp words.txt và xuất danh sách tần suất từ ra stdout.
cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -nr | awk '{print $2, $1}'
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
