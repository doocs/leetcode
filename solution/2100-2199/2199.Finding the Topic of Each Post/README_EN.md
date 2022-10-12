# [2199. Finding the Topic of Each Post](https://leetcode.com/problems/finding-the-topic-of-each-post)

[中文文档](/solution/2100-2199/2199.Finding%20the%20Topic%20of%20Each%20Post/README.md)

## Description

<p>Table: <code>Keywords</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| topic_id    | int     |
| word        | varchar |
+-------------+---------+
(topic_id, word) is the primary key for this table.
Each row of this table contains the id of a topic and a word that is used to express this topic.
There may be more than one word to express the same topic and one word may be used to express multiple topics.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Posts</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| post_id     | int     |
| content     | varchar |
+-------------+---------+
post_id is the primary key for this table.
Each row of this table contains the ID of a post and its content.
Content will consist only of English letters and spaces.
</pre>

<p>&nbsp;</p>

<p>Leetcode has collected some posts from its social media website and is interested in finding the topics of each post. Each topic can be expressed by one or more keywords. If a keyword of a certain topic exists in the content of a post (<strong>case insensitive</strong>) then the post has this topic.</p>

<p>Write an SQL query to find the topics of each post according to the following rules:</p>

<ul>
	<li>If the post does not have keywords from any topic, its topic should be <code>&quot;Ambiguous!&quot;</code>.</li>
	<li>If the post has at least one keyword of any topic, its topic should be a string of the IDs of its topics sorted in ascending order and separated by commas <code>&#39;,&#39;</code>. The string should not contain duplicate IDs.</li>
</ul>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Keywords table:
+----------+----------+
| topic_id | word     |
+----------+----------+
| 1        | handball |
| 1        | football |
| 3        | WAR      |
| 2        | Vaccine  |
+----------+----------+
Posts table:
+---------+------------------------------------------------------------------------+
| post_id | content                                                                |
+---------+------------------------------------------------------------------------+
| 1       | We call it soccer They call it football hahaha                         |
| 2       | Americans prefer basketball while Europeans love handball and football |
| 3       | stop the war and play handball                                         |
| 4       | warning I planted some flowers this morning and then got vaccinated    |
+---------+------------------------------------------------------------------------+
<strong>Output:</strong> 
+---------+------------+
| post_id | topic      |
+---------+------------+
| 1       | 1          |
| 2       | 1          |
| 3       | 1,3        |
| 4       | Ambiguous! |
+---------+------------+
<strong>Explanation:</strong> 
1: &quot;We call it soccer They call it football hahaha&quot;
&quot;football&quot; expresses topic 1. There is no other word that expresses any other topic.

2: &quot;Americans prefer basketball while Europeans love handball and football&quot;
&quot;handball&quot; expresses topic 1. &quot;football&quot; expresses topic 1. 
There is no other word that expresses any other topic.

3: &quot;stop the war and play handball&quot;
&quot;war&quot; expresses topic 3. &quot;handball&quot; expresses topic 1.
There is no other word that expresses any other topic.

4: &quot;warning I planted some flowers this morning and then got vaccinated&quot;
There is no word in this sentence that expresses any topic. Note that &quot;warning&quot; is different from &quot;war&quot; although they have a common prefix. 
This post is ambiguous.

Note that it is okay to have one word that expresses more than one topic.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
