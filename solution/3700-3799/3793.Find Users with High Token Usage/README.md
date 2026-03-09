---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3793.Find%20Users%20with%20High%20Token%20Usage/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3793. 查找高词元使用量的用户](https://leetcode.cn/problems/find-users-with-high-token-usage)

[English Version](/solution/3700-3799/3793.Find%20Users%20with%20High%20Token%20Usage/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>prompts</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| prompt      | varchar |
| tokens      | int     |
+-------------+---------+
(user_id, prompt) 是这张表的主键（值互不相同）。
每一行表示一个用户提交给 AI 系统的提示词以及所消耗的词元数量。
</pre>

<p>根据下列要求编写一个解决方案来分析 <strong>AI 提示词的使用模式</strong>：</p>

<ul>
	<li>对每一个用户，计算他们提交的 <strong>提示词的总数</strong>。</li>
	<li>对每个用户，计算 <strong>每个提示词所使用的平均词元数</strong>（舍入到&nbsp;<code>2</code> 位小数）。</li>
	<li>仅包含&nbsp;<strong>至少提交了 <code>3</code> 个提示词</strong> 的用户。</li>
	<li>仅包含那些 <strong>至少提交过一个提示词</strong> 且其中的 <code>tokens</code> 数量 <strong>超过</strong> 自己平均词元使用量的用户。</li>
</ul>

<p>返回结果表按 <strong>平均词元数 降序</strong>&nbsp;排序，然后按<em>&nbsp;</em><code>user_id</code> <strong>升序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>prompts 表：</p>

<pre class="example-io">
+---------+--------------------------+--------+
| user_id | prompt                   | tokens |
+---------+--------------------------+--------+
| 1       | Write a blog outline     | 120    |
| 1       | Generate SQL query       | 80     |
| 1       | Summarize an article     | 200    |
| 2       | Create resume bullet     | 60     |
| 2       | Improve LinkedIn bio     | 70     |
| 3       | Explain neural networks  | 300    |
| 3       | Generate interview Q&amp;A   | 250    |
| 3       | Write cover letter       | 180    |
| 3       | Optimize Python code     | 220    |
+---------+--------------------------+--------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+---------------+------------+
| user_id | prompt_count  | avg_tokens |
+---------+---------------+------------+
| 3       | 4             | 237.5      |
| 1       | 3             | 133.33     |
+---------+---------------+------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>用户 1：</strong>

    <ul>
    	<li>总提示词数 = 3</li>
    	<li>平均词元数 = (120 + 80 + 200) / 3 = 133.33</li>
    	<li>有一个提示词为 200 个词元，这超过了平均值</li>
    	<li>包含在结果中</li>
    </ul>
    </li>
    <li><strong>用户 2</strong>:
    <ul>
    	<li>总提示词数&nbsp;= 2（少于所需的最小值）</li>
    	<li>从结果中排除</li>
    </ul>
    </li>
    <li><strong>用户 3</strong>:
    <ul>
    	<li>总提示词数 = 4</li>
    	<li>平均词元数 = (300 + 250 + 180 + 220) / 4 = 237.5</li>
    	<li>有包含 300 和 250 个词元的提示词，都大于平均数</li>
    	<li>包含在结果中</li>
    </ul>
    </li>

</ul>

<p>结果表按 avg_tokens 降序排序，然后按 user_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组统计

我们首先将数组按照 `user_id` 进行分组统计，计算每个用户的提示词数量 `prompt_count`、平均令牌数 `avg_tokens` 以及最大令牌数 `max_tokens`。然后筛选出满足条件的用户，即提示词数量不少于 3 且存在提示词的令牌数大于平均令牌数的用户。最后按照平均令牌数降序和用户 ID 升序排序输出结果。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    user_id,
    COUNT(1) AS prompt_count,
    ROUND(AVG(tokens), 2) AS avg_tokens
FROM prompts
GROUP BY user_id
HAVING prompt_count >= 3 AND MAX(tokens) > avg_tokens
ORDER BY avg_tokens DESC, user_id;
```

#### Pandas

```python
import pandas as pd


def find_users_with_high_tokens(prompts: pd.DataFrame) -> pd.DataFrame:
    df = prompts.groupby("user_id", as_index=False).agg(
        prompt_count=("user_id", "size"),
        avg_tokens=("tokens", "mean"),
        max_tokens=("tokens", "max"),
    )

    df["avg_tokens"] = df["avg_tokens"].round(2)

    df = df[(df["prompt_count"] >= 3) & (df["max_tokens"] > df["avg_tokens"])]

    df = (
        df.sort_values(["avg_tokens", "user_id"], ascending=[False, True])
        .loc[:, ["user_id", "prompt_count", "avg_tokens"]]
        .reset_index(drop=True)
    )

    return df
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
