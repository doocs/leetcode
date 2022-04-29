# [620. 有趣的电影](https://leetcode.cn/problems/not-boring-movies)

[English Version](/solution/0600-0699/0620.Not%20Boring%20Movies/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>某城市开了一家新的电影院，吸引了很多人过来看电影。该电影院特别注意用户体验，专门有个 LED显示板做电影推荐，上面公布着影评和相关电影描述。</p>

<p>作为该电影院的信息部主管，您需要编写一个 SQL查询，找出所有影片描述为<strong>非</strong>&nbsp;<code>boring</code>&nbsp;(不无聊)&nbsp;的并且<strong> id 为奇数&nbsp;</strong>的影片，结果请按等级 <code>rating</code> 排列。</p>

<p>&nbsp;</p>

<p>例如，下表 <code>cinema</code>:</p>

<pre>
+---------+-----------+--------------+-----------+
|   id    | movie     |  description |  rating   |
+---------+-----------+--------------+-----------+
|   1     | War       |   great 3D   |   8.9     |
|   2     | Science   |   fiction    |   8.5     |
|   3     | irish     |   boring     |   6.2     |
|   4     | Ice song  |   Fantacy    |   8.6     |
|   5     | House card|   Interesting|   9.1     |
+---------+-----------+--------------+-----------+
</pre>

<p>对于上面的例子，则正确的输出是为：</p>

<pre>
+---------+-----------+--------------+-----------+
|   id    | movie     |  description |  rating   |
+---------+-----------+--------------+-----------+
|   5     | House card|   Interesting|   9.1     |
|   1     | War       |   great 3D   |   8.9     |
+---------+-----------+--------------+-----------+
</pre>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT *
FROM cinema
WHERE description NOT LIKE '%boring%'
        AND mod(id, 2) = 1
ORDER BY rating desc;
```

<!-- tabs:end -->
