# [2199. 找到每篇文章的主题](https://leetcode.cn/problems/finding-the-topic-of-each-post)

[English Version](/solution/2100-2199/2199.Finding%20the%20Topic%20of%20Each%20Post/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Keywords</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| topic_id    | int     |
| word        | varchar |
+-------------+---------+
(topic_id, word) 是该表的主键。
该表的每一行都包含一个主题的 id 和一个用于表达该主题的词。
可以用多个词来表达同一个主题，也可以用一个词来表达多个主题。
</pre>

<p>&nbsp;</p>

<p>表: <code>Posts</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| post_id     | int     |
| content     | varchar |
+-------------+---------+
post_id 是该表的主键。
该表的每一行都包含一个帖子的 ID 及其内容。
内容仅由英文字母和空格组成。
</pre>

<p>&nbsp;</p>

<p>Leetcode 从其社交媒体网站上收集了一些帖子，并对每个帖子的主题感兴趣。每个主题可以由一个或多个关键字表示。如果某个主题的关键字存在于一个帖子的内容中 (不区分大小写)，那么这个帖子就有这个主题。</p>

<p>编写一个 SQL 查询，根据以下规则查找每篇文章的主题:</p>

<ul>
	<li>如果帖子没有来自任何主题的关键词，那么它的主题应该是&nbsp;<code>"Ambiguous!"</code>。</li>
	<li>如果该帖子至少有一个主题的关键字，其主题应该是其主题的 id 按升序排列并以逗号 '，' 分隔的字符串。字符串不应该包含重复的 id。</li>
</ul>

<p>以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Keywords 表:
+----------+----------+
| topic_id | word     |
+----------+----------+
| 1        | handball |
| 1        | football |
| 3        | WAR      |
| 2        | Vaccine  |
+----------+----------+
Posts 表:
+---------+------------------------------------------------------------------------+
| post_id | content                                                                |
+---------+------------------------------------------------------------------------+
| 1       | We call it soccer They call it football hahaha                         |
| 2       | Americans prefer basketball while Europeans love handball and football |
| 3       | stop the war and play handball                                         |
| 4       | warning I planted some flowers this morning and then got vaccinated    |
+---------+------------------------------------------------------------------------+
<strong>输出:</strong> 
+---------+------------+
| post_id | topic      |
+---------+------------+
| 1       | 1          |
| 2       | 1          |
| 3       | 1,3        |
| 4       | Ambiguous! |
+---------+------------+
<strong>解释:</strong> 
1: "We call it soccer They call it football hahaha"
"football" 表示主题 1。没有其他词能表示任何其他主题。

2: "Americans prefer basketball while Europeans love handball and football"
"handball" 表示主题 1。"football" 表示主题 1。
没有其他词能表示任何其他主题。

3: "stop the war and play handball"
"war" 表示主题 3。 "handball" 表示主题 1。
没有其他词能表示任何其他主题。

4: "warning I planted some flowers this morning and then got vaccinated"
这个句子里没有一个词能表示任何主题。注意 “warning” 和 “war” 不同，尽管它们有一个共同的前缀。
所以这篇文章 “Ambiguous!”
请注意，可以使用一个词来表达多个主题。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
