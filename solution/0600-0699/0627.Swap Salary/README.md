# [627. 变更性别](https://leetcode-cn.com/problems/swap-salary)

[English Version](/solution/0600-0699/0627.Swap%20Salary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个&nbsp;<code>salary</code>&nbsp;表，如下所示，有 m = 男性 和 f = 女性 的值。交换所有的 f 和 m 值（例如，将所有 f 值更改为 m，反之亦然）。要求只使用一个更新（Update）语句，并且没有中间的临时表。</p>

<p>注意，您必只能写一个 Update 语句，请不要编写任何 Select 语句。</p>

<p><strong>例如：</strong></p>

<pre>| id | name | sex | salary |
|----|------|-----|--------|
| 1  | A    | m   | 2500   |
| 2  | B    | f   | 1500   |
| 3  | C    | m   | 5500   |
| 4  | D    | f   | 500    |
</pre>

<p>运行你所编写的更新语句之后，将会得到以下表:</p>

<pre>| id | name | sex | salary |
|----|------|-----|--------|
| 1  | A    | f   | 2500   |
| 2  | B    | m   | 1500   |
| 3  | C    | f   | 5500   |
| 4  | D    | m   | 500    |
</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
UPDATE salary
SET
    sex = CASE sex
        WHEN 'm' THEN 'f'
        ELSE 'm'
    END;
```

<!-- tabs:end -->
