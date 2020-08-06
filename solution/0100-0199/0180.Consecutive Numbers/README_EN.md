# [180. Consecutive Numbers](https://leetcode.com/problems/consecutive-numbers)

[中文文档](/solution/0100-0199/0180.Consecutive%20Numbers/README.md)

## Description
<p>Write a SQL query to find all numbers that appear at least three times consecutively.</p>



<pre>

+----+-----+

| Id | Num |

+----+-----+

| 1  |  1  |

| 2  |  1  |

| 3  |  1  |

| 4  |  2  |

| 5  |  1  |

| 6  |  2  |

| 7  |  2  |

+----+-----+

</pre>



<p>For example, given the above <code>Logs</code> table, <code>1</code> is the only number that appears consecutively for at least three times.</p>



<pre>

+-----------------+

| ConsecutiveNums |

+-----------------+

| 1               |

+-----------------+

</pre>




## Solutions


<!-- tabs:start -->

### **SQL**

```
select distinct(Num) as ConsecutiveNums from Logs Curr where
    Num = (select Num from Logs where id = Curr.id - 1) and
    Num = (select Num from Logs where id = Curr.id - 2)
```

<!-- tabs:end -->
