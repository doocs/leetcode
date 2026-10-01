---
comments: true
difficulty: Medium
tags:
    - Database
---

<!-- problem:start -->

# [180. Consecutive Numbers](https://leetcode.com/problems/consecutive-numbers)

[中文文档](/solution/0100-0199/0180.Consecutive%20Numbers/README.md)

## Mô tả

<!-- description:start -->

<p>Bảng: <code>Logs</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| num         | varchar |
+-------------+---------+
Trong SQL, id là khóa chính của bảng này.
id là một cột tự động tăng, bắt đầu từ 1.
</pre>

<p>&nbsp;</p>

<p>Tìm tất cả các số xuất hiện liên tiếp ít nhất ba lần.</p>

<p>Trả về bảng kết quả theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>Định dạng kết quả được minh họa trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Bảng Logs:
+----+-----+
| id | num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+
<strong>Đầu ra:</strong>
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
<strong>Giải thích:</strong> 1 là số duy nhất xuất hiện liên tiếp ít nhất ba lần.
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Hai phép JOIN

<!-- thinking:start -->

> **Tư duy**
>
> Tìm các số xuất hiện liên tiếp ít nhất ba lần. Tự JOIN bảng log hai lần, yêu cầu các $\textit{id}$ liền kề chênh nhau $1$ và $\textit{num}$ giống nhau. Như vậy ta cố định một cửa sổ có độ dài $3$; sau đó lấy các giá trị không trùng lặp.

<!-- thinking:end -->

Chúng ta có thể sử dụng hai phép JOIN để giải bài toán này.

Đầu tiên, chúng ta thực hiện một phép tự JOIN với điều kiện `l1.num = l2.num` và `l1.id = l2.id - 1`, nhờ đó tìm được tất cả các số xuất hiện liên tiếp ít nhất hai lần. Sau đó, chúng ta thực hiện thêm một phép tự JOIN với điều kiện `l2.num = l3.num` và `l2.id = l3.id - 1`, nhờ đó tìm được tất cả các số xuất hiện liên tiếp ít nhất ba lần. Cuối cùng, chúng ta chỉ cần chọn `l2.num` không trùng lặp.

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def consecutive_numbers(logs: pd.DataFrame) -> pd.DataFrame:
    all_the_same = lambda lst: lst.nunique() == 1
    logs["is_consecutive"] = (
        logs["num"].rolling(window=3, center=True, min_periods=3).apply(all_the_same)
    )
    return (
        logs.query("is_consecutive == 1.0")[["num"]]
        .drop_duplicates()
        .rename(columns={"num": "ConsecutiveNums"})
    )
```

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
SELECT DISTINCT l2.num AS ConsecutiveNums
FROM
    Logs AS l1
    JOIN Logs AS l2 ON l1.id = l2.id - 1 AND l1.num = l2.num
    JOIN Logs AS l3 ON l2.id = l3.id - 1 AND l2.num = l3.num;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Hàm cửa sổ

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 sử dụng hai phép JOIN. $\textit{LAG}/\textit{LEAD}$ lấy các $\textit{num}$ lân cận; việc ba giá trị bằng nhau cho biết một dãy có độ dài ba, với ít phép JOIN hơn.

<!-- thinking:end -->

Chúng ta có thể sử dụng các hàm cửa sổ `LAG` và `LEAD` để lấy `num` của hàng trước và hàng sau hàng hiện tại, rồi ghi chúng vào các trường $a$ và $b$, tương ứng. Cuối cùng, chúng ta chỉ cần lọc các hàng mà $a = num$ và $b = num$; đó là các số xuất hiện liên tiếp ít nhất ba lần. Lưu ý rằng chúng ta cần sử dụng từ khóa `DISTINCT` để loại bỏ các bản sao khỏi kết quả.

Chúng ta cũng có thể nhóm các số bằng cách sử dụng hàm `IF` để xác định `num` của hàng hiện tại có bằng `num` của hàng trước đó hay không. Nếu bằng nhau, chúng ta đặt giá trị thành $0$, ngược lại đặt thành $1$. Sau đó, chúng ta sử dụng hàm cửa sổ `SUM` để tính tổng tiền tố, đây là mã định danh nhóm. Cuối cùng, chúng ta chỉ cần nhóm theo mã định danh nhóm và lọc các số có số hàng trong mỗi nhóm lớn hơn hoặc bằng $3$. Tương tự, chúng ta cần sử dụng từ khóa `DISTINCT` để loại bỏ các bản sao khỏi kết quả.

<!-- tabs:start -->

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
WITH
    T AS (
        SELECT
            *,
            LAG(num) OVER () AS a,
            LEAD(num) OVER () AS b
        FROM Logs
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM T
WHERE a = num AND b = num;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 3

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 2 chỉ xét các cửa sổ có độ dài $3$, nên một dãy dài hơn sẽ được báo cáo thành nhiều phần. Một cờ thay đổi khi $\textit{num}$ thay đổi, kết hợp với tổng tiền tố, sẽ gán mã định danh nhóm cho mỗi dãy; $\textit{HAVING}\,\textit{COUNT}\ge 3$ giữ lại mọi dãy đủ dài.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
WITH
    T AS (
        SELECT
            *,
            IF(num = (LAG(num) OVER ()), 0, 1) AS st
        FROM Logs
    ),
    S AS (
        SELECT *, SUM(st) OVER (ORDER BY id) AS p
        FROM T
    )
SELECT DISTINCT num AS ConsecutiveNums
FROM S
GROUP BY p
HAVING COUNT(1) >= 3;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
