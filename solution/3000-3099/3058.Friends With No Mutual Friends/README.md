---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3058.Friends%20With%20No%20Mutual%20Friends/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3058. 没有共同朋友的朋友 🔒](https://leetcode.cn/problems/friends-with-no-mutual-friends)

[English Version](/solution/3000-3099/3058.Friends%20With%20No%20Mutual%20Friends/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Friends</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id1    | int  |
| user_id2    | int  |
+-------------+------+
(user_id1, user_id2) 是这张表的主键（有不同值的列组合）。
每一行包含 user id1, user id2，两人都是朋友。
</pre>

<p>编写一个解决方案来找到彼此是朋友但 <strong>没有共同 </strong>朋友的 <strong>所有用户对</strong>。</p>

<p>以&nbsp;<code>user_id1,</code> <code>user_id2</code><em>&nbsp;<strong>升序</strong> </em>返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Friends 表：
+----------+----------+
| user_id1 | user_id2 | 
+----------+----------+
| 1        | 2        | 
| 2        | 3        | 
| 2        | 4        | 
| 1        | 5        | 
| 6        | 7        | 
| 3        | 4        | 
| 2        | 5        | 
| 8        | 9        | 
+----------+----------+
<strong>输出：</strong>
+----------+----------+
| user_id1 | user_id2 | 
+----------+----------+
| 6        | 7        | 
| 8        | 9        | 
+----------+----------+
<strong>解释：</strong> 
- 用户 1 和 2 是彼此的好友，但他们有一个用户 ID 为 5 的共同好友，因此结果不包含这一对。
- 用户 2 和 3 是朋友，他们有一个用户 ID 为 4 的共同好友，因此排除，类似地，对于具有用户 ID 为 3 的共同朋友的用户 2 和 4，也因此不包括在内。
- 用户 1 和 5 是彼此的好友，但他们有一个用户 ID 为 2 的共同好友，所以结果不包含这一对。
- 用户 6 和 7，与用户 8 和 9 一样，是彼此的好友，同时他们没有共同的好友，因此包含在结果中。
- 用户 3 和 4 是彼此的朋友，但他们有用户 ID 为 2 的共同好友，与用户 2 和 5 有用户 ID 为 1 的共同好友一样，因此被排除。
输出表以 user_id1 升序排列。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：子查询

我们先把所有的朋友关系都列出来，记录在 `T` 表中。然后再找出 没有共同朋友的朋友 🔒 对。

接下来，我们可以使用子查询来找出没有共同朋友的朋友 🔒 对，即这个朋友对不属于其他某个人的朋友。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT user_id1, user_id2 FROM Friends
        UNION ALL
        SELECT user_id2, user_id1 FROM Friends
    )
SELECT user_id1, user_id2
FROM Friends
WHERE
    (user_id1, user_id2) NOT IN (
        SELECT t1.user_id1, t2.user_id1
        FROM
            T AS t1
            JOIN T AS t2 ON t1.user_id2 = t2.user_id2
    )
ORDER BY 1, 2;
```

```python
import pandas as pd


def friends_with_no_mutual_friends(friends: pd.DataFrame) -> pd.DataFrame:
    cp = friends.copy()
    t = cp[["user_id1", "user_id2"]].copy()
    t = pd.concat(
        [
            t,
            cp[["user_id2", "user_id1"]].rename(
                columns={"user_id2": "user_id1", "user_id1": "user_id2"}
            ),
        ]
    )
    merged = t.merge(t, left_on="user_id2", right_on="user_id2")
    ans = cp[
        ~cp.apply(
            lambda x: (x["user_id1"], x["user_id2"])
            in zip(merged["user_id1_x"], merged["user_id1_y"]),
            axis=1,
        )
    ]
    return ans.sort_values(by=["user_id1", "user_id2"])
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
