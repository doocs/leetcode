# [1527. 患某种疾病的患者](https://leetcode.cn/problems/patients-with-a-condition)

[English Version](/solution/1500-1599/1527.Patients%20With%20a%20Condition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>患者信息表：&nbsp;<code>Patients</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| patient_id   | int     |
| patient_name | varchar |
| conditions   | varchar |
+--------------+---------+
patient_id （患者 ID）是该表的主键。
'conditions' （疾病）包含 0 个或以上的疾病代码，以空格分隔。
这个表包含医院中患者的信息。</pre>

<p>&nbsp;</p>

<p>写一条&nbsp;SQL 语句，查询患有 I 类糖尿病的患者&nbsp;ID （patient_id）、患者姓名（patient_name）以及其患有的所有疾病代码（conditions）。I 类糖尿病的代码总是包含前缀&nbsp;<code>DIAB1</code>&nbsp;。</p>

<p>按 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：
</strong>Patients表：</code>
+------------+--------------+--------------+
| patient_id | patient_name | conditions   |
+------------+--------------+--------------+
| 1          | Daniel      &nbsp;| YFEV COUGH   |
| 2    &nbsp;     | Alice        |            &nbsp; |
| 3    &nbsp;     | Bob         &nbsp;| DIAB100 MYOP&nbsp;|
| 4 &nbsp;        | George      &nbsp;| ACNE DIAB100&nbsp;|
| 5 &nbsp;        | Alain       &nbsp;| DIAB201     &nbsp;|
+------------+--------------+--------------+
<strong>输出：</strong>
+------------+--------------+--------------+
| patient_id | patient_name | conditions   |
+------------+--------------+--------------+
| 3    &nbsp;     | Bob         &nbsp;| DIAB100 MYOP&nbsp;|
| 4 &nbsp;        | George   &nbsp;   | ACNE DIAB100&nbsp;| 
+------------+--------------+--------------+
<strong>解释：</strong>Bob 和 George 都患有代码以 DIAB1 开头的疾病。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
    patient_id,
    patient_name,
    conditions
FROM
    patients
WHERE
    conditions LIKE 'DIAB1%'
    OR conditions LIKE '% DIAB1%';
```

<!-- tabs:end -->
