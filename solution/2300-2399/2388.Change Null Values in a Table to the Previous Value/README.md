# [2388. 将表中的空值更改为前一个值](https://leetcode.cn/problems/change-null-values-in-a-table-to-the-previous-value)

[English Version](/solution/2300-2399/2388.Change%20Null%20Values%20in%20a%20Table%20to%20the%20Previous%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>CoffeeShop</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| drink       | varchar |
+-------------+---------+
id 是该表的主键。
该表中的每一行都显示了订单 id 和所点饮料的名称。一些饮料行为 null。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询，将 drink 的 <code>null</code>&nbsp;值替换为前面最近一行不为 <code>null</code>&nbsp;的 drink。保证表第一行的 drink 不为 <code>null</code>。</p>

<p>返回&nbsp;<strong>与输入顺序相同的&nbsp;</strong>结果表。</p>

<p>查询结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
CoffeeShop 表:
+----+------------------+
| id | drink            |
+----+------------------+
| 9  | Mezcal Margarita |
| 6  | null             |
| 7  | null             |
| 3  | Americano        |
| 1  | Daiquiri         |
| 2  | null             |
+----+------------------+
<strong>输出:</strong> 
+----+------------------+
| id | drink            |
+----+------------------+
| 9  | Mezcal Margarita |
| 6  | Mezcal Margarita |
| 7  | Mezcal Margarita |
| 3  | Americano        |
| 1  | Daiquiri         |
| 2  | Daiquiri         |
+----+------------------+
<strong>解释:</strong> 
对于 ID 6，之前不为空的值来自 ID 9。我们将 null 替换为 “Mezcal Margarita”。
对于 ID 7，之前不为空的值来自 ID 9。我们将 null 替换为 “Mezcal Margarita”。
对于 ID 2，之前不为空的值来自 ID 1。我们将 null 替换为 “Daiquiri”。
请注意，输出中的行与输入中的行相同。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
