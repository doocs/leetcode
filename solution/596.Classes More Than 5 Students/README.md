## 超过5名学生的课
### 题目描述

有一个 `courses` 表 ，有: **student(学生)** 和 **class(课程)**。

请列出所有超过或等于5名学生的课。

例如,表:
```
+---------+------------+
| student | class      |
+---------+------------+
| A       | Math       |
| B       | English    |
| C       | Math       |
| D       | Biology    |
| E       | Math       |
| F       | Computer   |
| G       | Math       |
| H       | Math       |
| I       | Math       |
+---------+------------+
```

应该输出:
```
+---------+
| class   |
+---------+
| Math    |
+---------+
```

**Note:**
学生在每个课中不应被重复计算。

### 解法
注意学生可能被重复计算，需要 `distinct`。

```sql
# Write your MySQL query statement below
select class from courses group by class having count(distinct student) >= 5

```