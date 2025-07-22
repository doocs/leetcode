---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3451.Find%20Invalid%20IP%20Addresses/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3451. 查找无效的 IP 地址](https://leetcode.cn/problems/find-invalid-ip-addresses)

[English Version](/solution/3400-3499/3451.Find%20Invalid%20IP%20Addresses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>logs</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| log_id      | int     |
| ip          | varchar |
| status_code | int     |
+-------------+---------+
log_id 是这张表的唯一主键。
每一行包含服务器访问日志信息，包括 IP 地址和 HTTP 状态码。
</pre>

<p>编写一个解决方案来查找 <strong>无效的 IP 地址</strong>。一个 IPv4 地址如果满足以下任何条件之一，则无效：</p>

<ul>
	<li>任何 8 位字节中包含大于 255 的数字</li>
	<li>任何 8 位字节中含有 <strong>前导零</strong>（如&nbsp;<code>01.02.03.04</code>）</li>
	<li><strong>少于或多于</strong>&nbsp;<code>4</code>&nbsp;个 8 位字节</li>
</ul>

<p>返回结果表分别以&nbsp;<code>invalid_count</code>，<code>ip</code>&nbsp;<strong>降序</strong>&nbsp;排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>logs 表：</p>

<pre class="example-io">
+--------+---------------+-------------+
| log_id | ip            | status_code | 
+--------+---------------+-------------+
| 1      | 192.168.1.1   | 200         | 
| 2      | 256.1.2.3     | 404         | 
| 3      | 192.168.001.1 | 200         | 
| 4      | 192.168.1.1   | 200         | 
| 5      | 192.168.1     | 500         | 
| 6      | 256.1.2.3     | 404         | 
| 7      | 192.168.001.1 | 200         | 
+--------+---------------+-------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------------+--------------+
| ip            | invalid_count|
+---------------+--------------+
| 256.1.2.3     | 2            |
| 192.168.001.1 | 2            |
| 192.168.1     | 1            |
+---------------+--------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li>256.1.2.3 是无效的，因为&nbsp;256 &gt; 255</li>
	<li>192.168.001.1 是无效的，因为有前导零</li>
	<li>192.168.1 是非法的，因为只有 3 个 8 位字节</li>
</ul>

<p>输出表分别以&nbsp;<code>invalid_count</code>，<code>ip</code>&nbsp;降序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以根据题意，判断 IP 地址是否不合法，判断的条件有：

1. IP 地址中的 `.` 的个数不等于 $3$；
2. IP 地址中的某个 octet 以 `0` 开头；
3. IP 地址中的某个 octet 大于 $255$。

然后我们将不合法的 IP 地址进行分组，并统计每个不合法的 IP 地址的个数 `invalid_count`，最后按照 `invalid_count` 和 `ip` 降序排序。

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    ip,
    COUNT(*) AS invalid_count
FROM logs
WHERE
    LENGTH(ip) - LENGTH(REPLACE(ip, '.', '')) != 3
    OR SUBSTRING_INDEX(ip, '.', 1) REGEXP '^0[0-9]'
    OR SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 2), '.', -1) REGEXP '^0[0-9]'
    OR SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 3), '.', -1) REGEXP '^0[0-9]'
    OR SUBSTRING_INDEX(ip, '.', -1) REGEXP '^0[0-9]'
    OR SUBSTRING_INDEX(ip, '.', 1) > 255
    OR SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 2), '.', -1) > 255
    OR SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 3), '.', -1) > 255
    OR SUBSTRING_INDEX(ip, '.', -1) > 255
GROUP BY 1
ORDER BY 2 DESC, 1 DESC;
```

#### Pandas

```python
import pandas as pd


def find_invalid_ips(logs: pd.DataFrame) -> pd.DataFrame:
    def is_valid_ip(ip: str) -> bool:
        octets = ip.split(".")
        if len(octets) != 4:
            return False
        for octet in octets:
            if not octet.isdigit():
                return False
            value = int(octet)
            if not 0 <= value <= 255 or octet != str(value):
                return False
        return True

    logs["is_valid"] = logs["ip"].apply(is_valid_ip)
    invalid_ips = logs[~logs["is_valid"]]
    invalid_count = invalid_ips["ip"].value_counts().reset_index()
    invalid_count.columns = ["ip", "invalid_count"]
    result = invalid_count.sort_values(
        by=["invalid_count", "ip"], ascending=[False, False]
    )
    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
