# [595. Big Countries](https://leetcode.com/problems/big-countries)

[中文文档](/solution/0500-0599/0595.Big%20Countries/README.md)

## Description

<p>There is a table <code>World</code></p>

<pre>

+-----------------+------------+------------+--------------+---------------+

| name            | continent  | area       | population   | gdp           |

+-----------------+------------+------------+--------------+---------------+

| Afghanistan     | Asia       | 652230     | 25500100     | 20343000      |

| Albania         | Europe     | 28748      | 2831741      | 12960000      |

| Algeria         | Africa     | 2381741    | 37100000     | 188681000     |

| Andorra         | Europe     | 468        | 78115        | 3712000       |

| Angola          | Africa     | 1246700    | 20609294     | 100990000     |

+-----------------+------------+------------+--------------+---------------+

</pre>

<p>A country is big if it has an area of bigger than 3 million square km or a population of more than 25 million.</p>

<p>Write a SQL solution to output big countries&#39; name, population and area.</p>

<p>For example, according to the above table, we should output:</p>

<pre>

+--------------+-------------+--------------+

| name         | population  | area         |

+--------------+-------------+--------------+

| Afghanistan  | 25500100    | 652230       |

| Algeria      | 37100000    | 2381741      |

+--------------+-------------+--------------+

</pre>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```
SELECT name,
         population,
         area
FROM World
WHERE area>3000000
        OR population>25000000
```

<!-- tabs:end -->
