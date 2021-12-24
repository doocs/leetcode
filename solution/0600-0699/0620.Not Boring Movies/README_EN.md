# [620. Not Boring Movies](https://leetcode.com/problems/not-boring-movies)

[中文文档](/solution/0600-0699/0620.Not%20Boring%20Movies/README.md)

## Description

X city opened a new cinema, many people would like to go to this cinema. The cinema also gives out a poster indicating the movies&rsquo; ratings and descriptions.

<p>Please write a SQL query to output movies with an odd numbered ID and a description that is not &#39;boring&#39;. Order the result by rating.</p>

<p>&nbsp;</p>

<p>For example, table <code>cinema</code>:</p>

<pre>

+---------+-----------+--------------+-----------+

|   id    | movie     |  description |  rating   |

+---------+-----------+--------------+-----------+

|   1     | War       |   great 3D   |   8.9     |

|   2     | Science   |   fiction    |   8.5     |

|   3     | irish     |   boring     |   6.2     |

|   4     | Ice song  |   Fantacy    |   8.6     |

|   5     | House card|   Interesting|   9.1     |

+---------+-----------+--------------+-----------+

</pre>

For the example above, the output should be:

<pre>

+---------+-----------+--------------+-----------+

|   id    | movie     |  description |  rating   |

+---------+-----------+--------------+-----------+

|   5     | House card|   Interesting|   9.1     |

|   1     | War       |   great 3D   |   8.9     |

+---------+-----------+--------------+-----------+

</pre>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```
SELECT *
FROM cinema
WHERE description NOT LIKE '%boring%'
        AND mod(id,2)=1
ORDER BY  rating desc;
```

<!-- tabs:end -->
