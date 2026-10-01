---
comments: true
difficulty: Easy
tags:
    - Database
---

<!-- problem:start -->

# [197. Rising Temperature](https://leetcode.com/problems/rising-temperature)

[中文文档](/solution/0100-0199/0197.Rising%20Temperature/README.md)

## Mô tả

<!-- description:start -->

<p>Bảng: <code>Weather</code></p>

<pre>
+---------------+---------+
| Tên cột       | Kiểu    |
+---------------+---------+
| id            | int     |
| recordDate    | date    |
| temperature   | int     |
+---------------+---------+
id là cột chứa các giá trị duy nhất trong bảng này.
Không có hai hàng nào có cùng recordDate.
Bảng này chứa thông tin về nhiệt độ vào một ngày nhất định.
</pre>

<p>&nbsp;</p>

<p>Hãy viết lời giải để tìm <code>id</code> của tất cả các ngày có nhiệt độ cao hơn so với ngày trước đó (ngày hôm qua).</p>

<p>Trả về bảng kết quả theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>Định dạng kết quả được minh họa trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Bảng Weather:
+----+------------+-------------+
| id | recordDate | temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+
<strong>Đầu ra:</strong>
+----+
| id |
+----+
| 2  |
| 4  |
+----+
<strong>Giải thích:</strong>
Vào ngày 2015-01-02, nhiệt độ cao hơn ngày trước đó (10 -&gt; 25).
Vào ngày 2015-01-04, nhiệt độ cao hơn ngày trước đó (20 -&gt; 30).
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Self-Join + Hàm DATEDIFF/SUBDATE

<!-- thinking:start -->

> **Tư duy**
>
> Các ngày có nhiệt độ cao hơn ngày trước đó trên lịch, không chỉ hàng ngay trước đó. Nối hai hàng trong bảng Weather, yêu cầu chênh lệch ngày bằng $1$, rồi so sánh nhiệt độ.

<!-- thinking:end -->

Chúng ta có thể sử dụng self-join để so sánh mỗi hàng trong bảng `Weather` với hàng trước đó. Nếu nhiệt độ cao hơn và chênh lệch ngày là một ngày, thì đó là kết quả cần tìm.

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def rising_temperature(weather: pd.DataFrame) -> pd.DataFrame:
    weather.sort_values(by="recordDate", inplace=True)
    return weather[
        (weather.temperature.diff() > 0) & (weather.recordDate.diff().dt.days == 1)
    ][["id"]]
```

#### MySQL

```sql
# Write your MySQL query statement below
SELECT w1.id
FROM
    Weather AS w1
    JOIN Weather AS w2
        ON DATEDIFF(w1.recordDate, w2.recordDate) = 1 AND w1.temperature > w2.temperature;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 sử dụng một hàm tính chênh lệch ngày. Nối theo $\textit{SUBDATE}(w1.\textit{recordDate},1)=w2.\textit{recordDate}$ sẽ căn chỉnh “ngày hôm qua” dưới dạng một phép so sánh bằng, cách mà một số query planner có thể xử lý dễ hơn.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT w1.id
FROM
    Weather AS w1
    JOIN Weather AS w2
        ON SUBDATE(w1.recordDate, 1) = w2.recordDate AND w1.temperature > w2.temperature;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
