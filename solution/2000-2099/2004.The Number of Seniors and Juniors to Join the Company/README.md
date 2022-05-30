# [2004. 职员招聘人数](https://leetcode.cn/problems/the-number-of-seniors-and-juniors-to-join-the-company)

[English Version](/solution/2000-2099/2004.The%20Number%20of%20Seniors%20and%20Juniors%20to%20Join%20the%20Company/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Candidates</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| employee_id | int  |
| experience  | enum |
| salary      | int  |
+-------------+------+
employee_id是此表的主键列。
经验是包含一个值（“高级”、“初级”）的枚举类型。
此表的每一行都显示候选人的id、月薪和经验。</pre>

<p>&nbsp;</p>

<p>一家公司想雇佣新员工。公司的工资预算是 <code>70000</code> 美元。公司的招聘标准是：</p>

<ol>
	<li>雇佣最多的高级员工。</li>
	<li>在雇佣最多的高级员工后，使用剩余预算雇佣最多的初级员工。</li>
</ol>

<p>编写一个SQL查询，查找根据上述标准雇佣的高级员工和初级员工的数量。<br />
按 <strong>任意顺序</strong> 返回结果表。<br />
查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Candidates table:
+-------------+------------+--------+
| employee_id | experience | salary |
+-------------+------------+--------+
| 1           | Junior     | 10000  |
| 9           | Junior     | 10000  |
| 2           | Senior     | 20000  |
| 11          | Senior     | 20000  |
| 13          | Senior     | 50000  |
| 4           | Junior     | 40000  |
+-------------+------------+--------+
<strong>输出:</strong> 
+------------+---------------------+
| experience | accepted_candidates |
+------------+---------------------+
| Senior     | 2                   |
| Junior     | 2                   |
+------------+---------------------+
<strong>说明：
我们可以雇佣2名ID为（2,11）的高级员工。由于预算是7万美元，他们的工资总额是4万美元，我们还有3万美元，但他们不足以雇佣ID为13的高级员工。
我们可以雇佣2名ID为（1,9）的初级员工。由于剩下的预算是3万美元，他们的工资总额是2万美元，我们还有1万美元，但他们不足以雇佣ID为4的初级员工。
</strong></pre>

<strong>示例 2：</strong>

<pre>
<strong>输入:</strong> 
Candidates table:
+-------------+------------+--------+
| employee_id | experience | salary |
+-------------+------------+--------+
| 1           | Junior     | 10000  |
| 9           | Junior     | 10000  |
| 2           | Senior     | 80000  |
| 11          | Senior     | 80000  |
| 13          | Senior     | 80000  |
| 4           | Junior     | 40000  |
+-------------+------------+--------+
<strong>输出:</strong> 
+------------+---------------------+
| experience | accepted_candidates |
+------------+---------------------+
| Senior     | 0                   |
| Junior     | 3                   |
+------------+---------------------+
<strong>解释：
</strong>我们不能用目前的预算雇佣任何高级员工，因为我们需要至少80000美元来雇佣一名高级员工。
我们可以用剩下的预算雇佣三名初级员工。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
