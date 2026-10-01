---
comments: true
difficulty: Easy
tags:
    - Database
---

<!-- problem:start -->

# [175. Combine Two Tables](https://leetcode.com/problems/combine-two-tables)

[中文文档](/solution/0100-0199/0175.Combine%20Two%20Tables/README.md)

## Mô tả

<!-- description:start -->

<p>Bảng: <code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| personId    | int     |
| lastName    | varchar |
| firstName   | varchar |
+-------------+---------+
personId là khóa chính (cột có các giá trị duy nhất) của bảng này.
Bảng này chứa thông tin về ID của một số người cùng họ và tên của họ.
</pre>

<p>&nbsp;</p>

<p>Bảng: <code>Address</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| addressId   | int     |
| personId    | int     |
| city        | varchar |
| state       | varchar |
+-------------+---------+
addressId là khóa chính (cột có các giá trị duy nhất) của bảng này.
Mỗi hàng của bảng này chứa thông tin về thành phố và bang của một người có ID = PersonId.
</pre>

<p>&nbsp;</p>

<p>Hãy viết lời giải để báo cáo tên, họ, thành phố và bang của mỗi người trong bảng <code>Person</code>. Nếu địa chỉ của <code>personId</code> không có trong bảng <code>Address</code>, hãy báo cáo <code>null</code> thay thế.</p>

<p>Trả về bảng kết quả theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>Định dạng kết quả được minh họa trong ví dụ sau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong>
Person table:
+----------+----------+-----------+
| personId | lastName | firstName |
+----------+----------+-----------+
| 1        | Wang     | Allen     |
| 2        | Alice    | Bob       |
+----------+----------+-----------+
Address table:
+-----------+----------+---------------+------------+
| addressId | personId | city          | state      |
+-----------+----------+---------------+------------+
| 1         | 2        | New York City | New York   |
| 2         | 3        | Leetcode      | California |
+-----------+----------+---------------+------------+
<strong>Đầu ra:</strong>
+-----------+----------+---------------+----------+
| firstName | lastName | city          | state    |
+-----------+----------+---------------+----------+
| Allen     | Wang     | Null          | Null     |
| Bob       | Alice    | New York City | New York |
+-----------+----------+---------------+----------+
<strong>Giải thích:</strong>
Không có địa chỉ nào trong bảng địa chỉ cho personId = 1, vì vậy chúng ta trả về null trong city và state của người đó.
addressId = 1 chứa thông tin về địa chỉ của personId = 2.
</pre>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: LEFT JOIN

<!-- thinking:start -->

> **Tư duy**
>
> Mỗi người tương ứng với một hàng; city và state phải là $\textit{NULL}$ khi thiếu địa chỉ. Một inner join sẽ loại bỏ những người không có địa chỉ. Một left join lấy $\textit{Person}$ làm bảng chính và nối theo $\textit{personId}$ sẽ giữ lại mọi người, đồng thời để các cột địa chỉ là null khi không có kết quả khớp.

<!-- thinking:end -->

Chúng ta có thể sử dụng left join để nối bảng `Person` với bảng `Address` theo điều kiện `Person.personId = Address.personId`, từ đó lấy được tên, họ, thành phố và bang của mỗi người. Nếu địa chỉ của một `personId` không có trong bảng `Address`, địa chỉ đó sẽ được báo cáo là `null`.

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def combine_two_tables(person: pd.DataFrame, address: pd.DataFrame) -> pd.DataFrame:
    return pd.merge(left=person, right=address, how="left", on="personId")[
        ["firstName", "lastName", "city", "state"]
    ]
```

#### MySQL

```sql
# Viết câu lệnh truy vấn MySQL của bạn bên dưới
SELECT firstName, lastName, city, state
FROM
    Person
    LEFT JOIN Address USING (personId);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
