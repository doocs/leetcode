---
comments: true
difficulty: Easy
tags:
    - Shell
---

<!-- problem:start -->

# [193. Valid Phone Numbers](https://leetcode.com/problems/valid-phone-numbers)

[中文文档](/solution/0100-0199/0193.Valid%20Phone%20Numbers/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một tệp văn bản <code>file.txt</code> chứa danh sách các số điện thoại, mỗi số trên một dòng, hãy viết một tập lệnh bash một dòng để in ra tất cả các số điện thoại hợp lệ.</p>

<p>Bạn có thể giả sử rằng một số điện thoại hợp lệ phải xuất hiện ở một trong hai định dạng sau: (xxx) xxx-xxxx hoặc xxx-xxx-xxxx. (x nghĩa là một chữ số)</p>

<p>Bạn cũng có thể giả sử mỗi dòng trong tệp văn bản không được chứa khoảng trắng ở đầu hoặc cuối.</p>

<p><strong class="example">Ví dụ:</strong></p>

<p>Giả sử <code>file.txt</code> có nội dung sau:</p>

<pre>
987-123-4567
123 456 7890
(123) 456-7890
</pre>

<p>Tập lệnh của bạn nên in ra các số điện thoại hợp lệ sau:</p>

<pre>
987-123-4567
(123) 456-7890
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: awk

<!-- thinking:start -->

> **Tư duy**
>
> Các số hợp lệ chỉ có thể là $xxx-xxx-xxxx$ và $(xxx)\,xxx-xxxx$. Khớp toàn bộ dòng. Một regex $\textit{awk}$ được neo ở cả hai đầu: ba chữ số và một dấu gạch ngang, hoặc một bộ ba chữ số trong ngoặc và một dấu cách, sau đó là ba chữ số, một dấu gạch ngang và bốn chữ số.

<!-- thinking:end -->

<!-- tabs:start -->

#### Shell

```bash
# Đọc từ tệp file.txt và xuất tất cả các số điện thoại hợp lệ ra stdout.
awk '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/' file.txt
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
