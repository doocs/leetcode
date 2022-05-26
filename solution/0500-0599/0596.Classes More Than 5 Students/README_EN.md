# [596. Classes More Than 5 Students](https://leetcode.com/problems/classes-more-than-5-students)

[中文文档](/solution/0500-0599/0596.Classes%20More%20Than%205%20Students/README.md)

## Description

<p>There is a table <code>courses</code> with columns: <b>student</b> and <b>class</b></p>

<p>Please list out all classes which have more than or equal to 5 students.</p>

<p>For example, the table:</p>

<pre>

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

</pre>

<p>Should output:</p>

<pre>

+---------+

| class   |

+---------+

| Math    |

+---------+

</pre>

<p>&nbsp;</p>

<p><b>Note:</b><br />

The students should not be counted duplicate in each course.</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```
select
    c.class
from
    (select distinct courses.student, courses.class from courses) c
group by
    c.class
having
    count(c.class)>=5
```

<!-- tabs:end -->
