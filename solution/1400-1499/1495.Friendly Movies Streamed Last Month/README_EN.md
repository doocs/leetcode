# [1495. Friendly Movies Streamed Last Month](https://leetcode.com/problems/friendly-movies-streamed-last-month)

[中文文档](/solution/1400-1499/1495.Friendly%20Movies%20Streamed%20Last%20Month/README.md)

## Description

<p>Table: <code>TVProgram</code></p>

<pre>

+---------------+---------+

| Column Name   | Type    |

+---------------+---------+

| program_date  | date    |

| content_id    | int     |

| channel       | varchar |

+---------------+---------+

(program_date, content_id) is the primary key for this table.

This table contains information of the programs on the TV.

content_id is the id of the program in some channel on the TV.</pre>

<p>&nbsp;</p>

<p>Table: <code>Content</code></p>

<pre>

+------------------+---------+

| Column Name      | Type    |

+------------------+---------+

| content_id       | varchar |

| title            | varchar |

| Kids_content     | enum    |

| content_type     | varchar |

+------------------+---------+

content_id is the primary key for this table.

Kids_content is an enum that takes one of the values (&#39;Y&#39;, &#39;N&#39;) where: 

&#39;Y&#39; means is content for kids otherwise &#39;N&#39; is not content for kids.

content_type&nbsp;is the category of the content as movies, series, etc.

</pre>

<p>&nbsp;</p>

<p>Write an SQL query to&nbsp;report the distinct titles of the kid-friendly movies streamed in June 2020.</p>

<p>Return the result table in any order.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>

<pre>

<code>TVProgram</code> table:

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



<code>Content</code> table:

+------------+----------------+---------------+---------------+

| content_id | title          | Kids_content  | content_type  |

+------------+----------------+---------------+---------------+

| 1          | Leetcode Movie | N             | Movies        |

| 2          | Alg. for Kids  | Y             | Series        |

| 3          | Database Sols  | N             | Series        |

| 4          | Aladdin        | Y             | Movies        |

| 5          | Cinderella     | Y             | Movies        |

+------------+----------------+---------------+---------------+



Result table:

+--------------+

| title        |

+--------------+

| Aladdin      |

+--------------+

&quot;Leetcode Movie&quot; is not a content for kids.

&quot;Alg. for Kids&quot; is not a movie.

&quot;Database Sols&quot; is not a movie

&quot;Alladin&quot; is a movie, content for kids and was streamed in June 2020.

&quot;Cinderella&quot; was not streamed in June 2020.

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
