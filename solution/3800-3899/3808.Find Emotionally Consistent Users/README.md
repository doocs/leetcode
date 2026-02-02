---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3808.Find%20Emotionally%20Consistent%20Users/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3808. 寻找情绪一致的用户](https://leetcode.cn/problems/find-emotionally-consistent-users)

[English Version](/solution/3800-3899/3808.Find%20Emotionally%20Consistent%20Users/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>reactions</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| user_id      | int     |
| content_id   | int     |
| reaction     | varchar |
+--------------+---------+
(user_id, content_id) 是这张表的主键（值互不相同）。
每一行代表用户对某条内容的反应。
</pre>

<p>根据以下要求编写一个解决方案，以识别 <strong>情绪一致的用户</strong>：</p>

<ul>
	<li>为每个用户统计他们发送的总反应次数。</li>
	<li>仅包含 <strong>至少对 </strong><code>5</code><strong> 个不同内容项</strong> 作出反应的用户。</li>
	<li>如果用户 <strong>至少</strong> <code>60%</code> 的反应属于 <strong>同一种类型</strong>，则认为其 <strong>情绪一致</strong>。</li>
</ul>

<p>返回结果表按&nbsp;<code>reaction_ratio</code> <strong>降序</strong>&nbsp;排序，然后按&nbsp;<code>user_id</code> <strong>升序</strong>&nbsp;排序。</p>

<p><strong>注意：</strong></p>

<ul>
	<li><code>reaction_ratio</code>&nbsp;应该舍入到&nbsp;<code>2</code>&nbsp;位小数</li>
</ul>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>reactions 表：</p>

<pre class="example-io">
+---------+------------+----------+
| user_id | content_id | reaction |
+---------+------------+----------+
| 1       | 101        | like     |
| 1       | 102        | like     |
| 1       | 103        | like     |
| 1       | 104        | wow      |
| 1       | 105        | like     |
| 2       | 201        | like     |
| 2       | 202        | wow      |
| 2       | 203        | sad      |
| 2       | 204        | like     |
| 2       | 205        | wow      |
| 3       | 301        | love     |
| 3       | 302        | love     |
| 3       | 303        | love     |
| 3       | 304        | love     |
| 3       | 305        | love     |
+---------+------------+----------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+-------------------+----------------+
| user_id | dominant_reaction | reaction_ratio |
+---------+-------------------+----------------+
| 3       | love              | 1.00           |
| 1       | like              | 0.80           |
+---------+-------------------+----------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>用户 1：</strong>

    <ul>
    	<li>总反应数&nbsp;= 5</li>
    	<li>'like' 出现了 4 次</li>
    	<li>reaction_ratio = 4 / 5 = 0.80</li>
    	<li>满足 60% 一致的要求</li>
    </ul>
    </li>
    <li><strong>用户 2：</strong>
    <ul>
    	<li>总反应数 = 5</li>
    	<li>出现最多的反应只出现了 2 次</li>
    	<li>reaction_ratio = 2 / 5 = 0.40</li>
    	<li>不满足一致的要求</li>
    </ul>
    </li>
    <li><strong>用户 3：</strong>
    <ul>
    	<li>总反应数 = 5</li>
    	<li>'love' 出现了 5 次</li>
    	<li>reaction_ratio = 5 / 5 = 1.00</li>
    	<li>满足一致的要求</li>
    </ul>
    </li>

</ul>

<p>结果表按 reaction_ratio 降序排序，然后按 user_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计 + 连接查询

我们首先统计每个用户对每种反应的计数，记录在临时表 $t$ 中。然后，我们在临时表 $t$ 的基础上，统计每个用户的最大反应计数和总反应计数，计算出反应比例，并筛选出满足条件的用户，记录在临时表 $s$ 中。最后，我们将临时表 $s$ 和 $t$ 进行连接查询，找出每个用户的主导反应，并按要求排序输出结果。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            user_id,
            reaction,
            COUNT(1) cnt
        FROM reactions
        GROUP BY 1, 2
    ),
    s AS (
        SELECT
            user_id,
            MAX(cnt) mx_cnt,
            ROUND(MAX(cnt) / SUM(cnt), 2) reaction_ratio
        FROM t
        GROUP BY 1
        HAVING reaction_ratio >= 0.60 AND SUM(cnt) >= 5
    )
SELECT user_id, reaction dominant_reaction, reaction_ratio
FROM
    s
    JOIN t USING (user_id)
WHERE cnt = mx_cnt
ORDER BY 3 DESC, 1;
```

#### Pandas

```python
import pandas as pd
from decimal import Decimal, ROUND_HALF_UP


def find_emotionally_consistent_users(reactions: pd.DataFrame) -> pd.DataFrame:
    t = reactions.groupby(["user_id", "reaction"]).size().reset_index(name="cnt")

    s = (
        t.groupby("user_id")
        .agg(mx_cnt=("cnt", "max"), total_cnt=("cnt", "sum"))
        .reset_index()
    )

    s["reaction_ratio"] = (
        s["mx_cnt"]
        .div(s["total_cnt"])
        .apply(
            lambda x: float(
                Decimal(str(x)).quantize(Decimal("0.00"), rounding=ROUND_HALF_UP)
            )
        )
    )

    s = s[(s["reaction_ratio"] >= 0.60) & (s["total_cnt"] >= 5)]

    merged = pd.merge(
        s[["user_id", "mx_cnt", "reaction_ratio"]],
        t,
        left_on=["user_id", "mx_cnt"],
        right_on=["user_id", "cnt"],
    )

    result = (
        merged[["user_id", "reaction", "reaction_ratio"]]
        .rename(columns={"reaction": "dominant_reaction"})
        .sort_values(by=["reaction_ratio", "user_id"], ascending=[False, True])
        .reset_index(drop=True)
    )

    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
