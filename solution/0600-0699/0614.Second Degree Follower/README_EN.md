# [614. Second Degree Follower](https://leetcode.com/problems/second-degree-follower)

[中文文档](/solution/0600-0699/0614.Second%20Degree%20Follower/README.md)

## Description

<p>In facebook, there is a <code>follow</code> table with two columns: <b>followee</b>, <b>follower</b>.</p>

<p>Please write a sql query to get the amount of each follower&rsquo;s follower if he/she has one.</p>

<p>For example:</p>

<pre>

+-------------+------------+

| followee    | follower   |

+-------------+------------+

|     A       |     B      |

|     B       |     C      |

|     B       |     D      |

|     D       |     E      |

+-------------+------------+

</pre>

should output:

<pre>

+-------------+------------+

| follower    | num        |

+-------------+------------+

|     B       |  2         |

|     D       |  1         |

+-------------+------------+

</pre>

<b>Explaination:</b><br />

Both B and D exist in the follower list, when as a followee, B&#39;s follower is C and D, and D&#39;s follower is E. A does not exist in follower list.

<p>&nbsp;</p>

<p>&nbsp;</p>

<b>Note:</b><br />

Followee would not follow himself/herself in all cases.<br />

Please display the result in follower&#39;s alphabet order.

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
