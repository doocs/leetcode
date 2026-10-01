---
comments: true
difficulty: Medium
tags:
    - Database
---

<!-- problem:start -->

# [178. Rank Scores](https://leetcode.com/problems/rank-scores)

[中文文档](/solution/0100-0199/0178.Rank%20Scores/README.md)

## Mô tả

<!-- description:start -->

<p>Bảng: <code>Scores</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| score       | decimal |
+-------------+---------+
id là khóa chính (cột có các giá trị duy nhất) cho bảng này.
Mỗi hàng của bảng này chứa điểm số của một trò chơi. Điểm số là một giá trị số thực có hai chữ số thập phân.
</pre>

<p>&nbsp;</p>

<p>Viết lời giải để tìm thứ hạng của các điểm số. Thứ hạng phải được tính theo các quy tắc sau:</p>

<ul>
	<li>Các điểm số phải được xếp hạng từ cao xuống thấp.</li>
	<li>Nếu có sự hòa giữa hai điểm số, cả hai phải có cùng thứ hạng.</li>
	<li>Sau một lần hòa, số thứ hạng tiếp theo phải là giá trị số nguyên liên tiếp kế tiếp. Nói cách khác, giữa các thứ hạng không được có khoảng trống.</li>
</ul>

<p>Trả về bảng kết quả được sắp xếp theo <code>score</code> theo thứ tự giảm dần.</p>

<p>Định dạng kết quả được thể hiện trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Bảng Scores:
+----+-------+
| id | score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
<strong>Đầu ra:</strong>
+-------+------+
| score | rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Xếp hạng điểm số theo thứ tự giảm dần: các điểm bằng nhau cùng thứ hạng và thứ hạng tiếp theo là số liên tiếp. $\textit{RANK}$ để lại khoảng trống sau các điểm bằng nhau; $\textit{ROW\_NUMBER}$ tách chúng ra. $\textit{DENSE\_RANK}$ là kiểu xếp hạng chúng ta cần; sau đó sắp xếp theo score giảm dần.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def order_scores(scores: pd.DataFrame) -> pd.DataFrame:
    # Sử dụng phương thức rank để gán thứ hạng cho các điểm số theo thứ tự giảm dần mà không có khoảng trống
    scores["rank"] = scores["score"].rank(method="dense", ascending=False)

    # Xóa cột id và sắp xếp DataFrame theo score theo thứ tự giảm dần
    result_df = scores.drop("id", axis=1).sort_values(by="score", ascending=False)

    return result_df
```

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
SELECT
    score,
    DENSE_RANK() OVER (ORDER BY score DESC) AS 'rank'
FROM Scores;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 cần các hàm cửa sổ, nhưng các phiên bản MySQL cũ hơn không có chúng. Duyệt qua các điểm số theo thứ tự giảm dần bằng các biến lưu điểm số trước đó và thứ hạng hiện tại: chỉ tăng khi điểm số thay đổi. Đây là dense rank được tự cài đặt.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    Score,
    CONVERT(rk, SIGNED) `Rank`
FROM
    (
        SELECT
            Score,
            IF(@latest = Score, @rank, @rank := @rank + 1) rk,
            @latest := Score
        FROM
            Scores,
            (
                SELECT
                    @rank := 0,
                    @latest := NULL
            ) tmp
        ORDER BY
            Score DESC
    ) s;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
