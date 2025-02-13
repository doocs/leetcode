---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3451.Find%20Invalid%20IP%20Addresses/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3451. Find Invalid IP Addresses](https://leetcode.cn/problems/find-invalid-ip-addresses)

[English Version](/solution/3400-3499/3451.Find%20Invalid%20IP%20Addresses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Table: <code> logs</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| log_id      | int     |
| ip          | varchar |
| status_code | int     |
+-------------+---------+
log_id is the unique key for this table.
Each row contains server access log information including IP address and HTTP status code.
</pre>

<p>Write a solution to find <strong>invalid IP addresses</strong>. An IPv4 address is invalid if it meets any of these conditions:</p>

<ul>
	<li>Contains numbers <strong>greater than</strong> <code>255</code> in any octet</li>
	<li>Has <strong>leading zeros</strong> in any octet (like <code>01.02.03.04</code>)</li>
	<li>Has <strong>less or more</strong> than <code>4</code> octets</li>
</ul>

<p>Return <em>the result table </em><em>ordered by</em> <code>invalid_count</code>,&nbsp;<code>ip</code>&nbsp;<em>in <strong>descending</strong> order respectively</em>.&nbsp;</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>logs table:</p>

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

<p><strong>Output:</strong></p>

<pre class="example-io">
+---------------+--------------+
| ip            | invalid_count|
+---------------+--------------+
| 256.1.2.3     | 2            |
| 192.168.001.1 | 2            |
| 192.168.1     | 1            |
+---------------+--------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>256.1.2.3&nbsp;is invalid because 256 &gt; 255</li>
	<li>192.168.001.1&nbsp;is invalid because of leading zeros</li>
	<li>192.168.1&nbsp;is invalid because it has only 3 octets</li>
</ul>

<p>The output table is ordered by invalid_count, ip in descending order respectively.</p>
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
