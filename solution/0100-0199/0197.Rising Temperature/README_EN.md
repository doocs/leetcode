# [197. Rising Temperature](https://leetcode.com/problems/rising-temperature)

[中文文档](/solution/0100-0199/0197.Rising%20Temperature/README.md)

## Description

<p>Given a <code>Weather</code> table, write a SQL query to find all dates&#39; Ids with higher temperature compared to its previous (yesterday&#39;s) dates.</p>

<pre>

+---------+------------------+------------------+

| Id(INT) | RecordDate(DATE) | Temperature(INT) |

+---------+------------------+------------------+

|       1 |       2015-01-01 |               10 |

|       2 |       2015-01-02 |               25 |

|       3 |       2015-01-03 |               20 |

|       4 |       2015-01-04 |               30 |

+---------+------------------+------------------+

</pre>

<p>For example, return the following Ids for the above <code>Weather</code> table:</p>

<pre>

+----+

| Id |

+----+

|  2 |

|  4 |

+----+

</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```
select w1.Id
from
    Weather w1,
    Weather w2
where
    DATEDIFF(w1.RecordDate, w2.RecordDate) = 1 and w1.Temperature > w2.Temperature
```

<!-- tabs:end -->
