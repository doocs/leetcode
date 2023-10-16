# [1322. 广告效果](https://leetcode.cn/problems/ads-performance)

[English Version](/solution/1300-1399/1322.Ads%20Performance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Ads</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| ad_id         | int     |
| user_id       | int     |
| action        | enum    |
+---------------+---------+
(ad_id, user_id) 是该表的主键(具有唯一值的列的组合)
该表的每一行包含一条广告的 ID(ad_id)，用户的 ID(user_id) 和用户对广告采取的行为 (action)
action 列是一个枚举类型 ('Clicked', 'Viewed', 'Ignored') 。
</pre>

<p>&nbsp;</p>

<p>一家公司正在运营这些广告并想计算每条广告的效果。</p>

<p>广告效果用点击通过率（Click-Through Rate：CTR）来衡量，公式如下:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1322.Ads%20Performance/images/sql1.png" style="height: 55px; width: 600px;" /></p>

<p>编写解决方案找出每一条广告的&nbsp;<code>ctr</code>&nbsp;，<code>ctr</code>&nbsp;要 <strong>保留两位小数</strong> 。</p>

<p>返回结果需要按&nbsp;<code>ctr</code>&nbsp;<strong>降序</strong>、按&nbsp;<code>ad_id</code>&nbsp;<strong>升序&nbsp;</strong>进行排序。</p>

<p>返回结果示例如下：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Ads 表:
+-------+---------+---------+
| ad_id | user_id | action  |
+-------+---------+---------+
| 1     | 1       | Clicked |
| 2     | 2       | Clicked |
| 3     | 3       | Viewed  |
| 5     | 5       | Ignored |
| 1     | 7       | Ignored |
| 2     | 7       | Viewed  |
| 3     | 5       | Clicked |
| 1     | 4       | Viewed  |
| 2     | 11      | Viewed  |
| 1     | 2       | Clicked |
+-------+---------+---------+
<strong>输出：</strong>
+-------+-------+
| ad_id | ctr   |
+-------+-------+
| 1     | 66.67 |
| 3     | 50.00 |
| 2     | 33.33 |
| 5     | 0.00  |
+-------+-------+
<strong>解释：</strong>
对于 ad_id = 1, ctr = (2/(2+1)) * 100 = 66.67
对于 ad_id = 2, ctr = (1/(1+2)) * 100 = 33.33
对于 ad_id = 3, ctr = (1/(1+1)) * 100 = 50.00
对于 ad_id = 5, ctr = 0.00, 注意 ad_id = 5 没有被点击 (Clicked) 或查看 (Viewed) 过
注意我们不关心 action 为 Ingnored 的广告
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT ad_id,
    ROUND(
         IFNULL(
             SUM(action='Clicked') / SUM(action IN ('Clicked', 'Viewed')) * 100
             , 0)
         , 2)
        AS ctr
FROM Ads
GROUP BY 1
ORDER BY 2 DESC, 1;
```

<!-- tabs:end -->
