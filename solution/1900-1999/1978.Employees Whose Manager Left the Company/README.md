# [1978. 上级经理已离职的公司员工](https://leetcode.cn/problems/employees-whose-manager-left-the-company)

[English Version](/solution/1900-1999/1978.Employees%20Whose%20Manager%20Left%20the%20Company/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Employees</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| employee_id | int      |
| name        | varchar  |
| manager_id  | int      |
| salary      | int      |
+-------------+----------+
employee_id 是这个表的主键。
这个表包含了员工，他们的薪水和上级经理的id。
有一些员工没有上级经理（其manager_id 是空值）。
</pre>

<p>&nbsp;</p>

<p>写一个查询语句，查询出，这些员工的id，他们的薪水严格少于<code>$30000</code>&nbsp;并且他们的上级经理已离职。当一个经理离开公司时，他们的信息需要从员工表中删除掉，但是表中的员工的<code>manager_id</code> &nbsp;这一列还是设置的离职经理的id&nbsp;。</p>

<p>返回的结果按照<code>employee_id&nbsp;</code>从小到大排序。</p>

<p>查询结果如下所示：</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
Employees table:
+-------------+-----------+------------+--------+
| employee_id | name      | manager_id | salary |
+-------------+-----------+------------+--------+
| 3           | Mila      | 9          | 60301  |
| 12          | Antonella | null       | 31000  |
| 13          | Emery     | null       | 67084  |
| 1           | Kalel     | 11         | 21241  |
| 9           | Mikaela   | null       | 50937  |
| 11          | Joziah    | 6          | 28485  |
+-------------+-----------+------------+--------+
<strong>输出：</strong>
+-------------+
| employee_id |
+-------------+
| 11          |
+-------------+

<strong>解释：</strong>
薪水少于30000美元的员工有1号(Kalel) and 11号 (Joziah)。
Kalel的上级经理是11号员工，他还在公司上班(他是Joziah)。
Joziah的上级经理是6号员工，他已经离职，因为员工表里面已经没有6号员工的信息了，它被删除了。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
