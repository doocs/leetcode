# [1495. 上月播放的儿童适宜电影](https://leetcode.cn/problems/friendly-movies-streamed-last-month)

[English Version](/solution/1400-1499/1495.Friendly%20Movies%20Streamed%20Last%20Month/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>TVProgram</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| program_date  | date    |
| content_id    | int     |
| channel       | varchar |
+---------------+---------+
(program_date, content_id) 是该表主键.
该表包含电视上的节目信息.
content_id 是电视一些频道上的节目的 id.</pre>

<p>&nbsp;</p>

<p>表: <code>Content</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| content_id       | varchar |
| title            | varchar |
| Kids_content     | enum    |
| content_type     | varchar |
+------------------+---------+
content_id 是该表主键.
Kids_content 是枚举类型, 取值为('Y', 'N'), 其中: 
'Y' 表示儿童适宜内容, 而'N'表示儿童不宜内容.
content_type&nbsp;表示内容的类型, 比如电影, 电视剧等.
</pre>

<p>&nbsp;</p>

<p>写一个 SQL 语句,&nbsp;&nbsp;报告在 2020 年 6 月份播放的儿童适宜电影的去重电影名.</p>

<p>返回的结果表单 <strong>没有顺序要求</strong> .</p>

<p>查询结果的格式如下例所示.</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
TVProgram</code> 表:
+--------------------+--------------+-------------+
| program_date       | content_id   | channel     |
+--------------------+--------------+-------------+
| 2020-06-10 08:00   | 1            | LC-Channel  |
| 2020-05-11 12:00   | 2            | LC-Channel  |
| 2020-05-12 12:00   | 3            | LC-Channel  |
| 2020-05-13 14:00   | 4            | Disney Ch   |
| 2020-06-18 14:00   | 4            | Disney Ch   |
| 2020-07-15 16:00   | 5            | Disney Ch   |
+--------------------+--------------+-------------+
<code>Content</code> 表:
+------------+----------------+---------------+---------------+
| content_id | title          | Kids_content  | content_type  |
+------------+----------------+---------------+---------------+
| 1          | Leetcode Movie | N             | Movies        |
| 2          | Alg. for Kids  | Y             | Series        |
| 3          | Database Sols  | N             | Series        |
| 4          | Aladdin        | Y             | Movies        |
| 5          | Cinderella     | Y             | Movies        |
+------------+----------------+---------------+---------------+
<code><strong>输出：</strong></code>
+--------------+
| title        |
+--------------+
| Aladdin      |
+--------------+
<code><strong>解释：</strong></code>
"Leetcode Movie" 是儿童不宜的电影.
"Alg. for Kids" 不是电影.
"Database Sols" 不是电影
"Alladin" 是电影, 儿童适宜, 并且在 2020 年 6 月份播放.
"Cinderella" 不在 2020 年 6 月份播放.</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT DISTINCT
    title
FROM
    Content
        INNER JOIN
    TVProgram ON Content.content_id = TVProgram.content_id
WHERE
    content_type = 'Movies'
        AND kids_content = 'Y'
        AND program_date BETWEEN '2020-06-01' AND '2020-06-30';
```

```sql
SELECT DISTINCT
    title
FROM
    Content
        INNER JOIN
    TVProgram ON Content.content_id = TVProgram.content_id
WHERE
    kids_content = 'Y'
        AND (MONTH(program_date) , YEAR(program_date)) = (6 , 2020);
```

<!-- tabs:end -->
