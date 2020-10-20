# [618. 学生地理信息报告](https://leetcode-cn.com/problems/students-report-by-geography)

[English Version](/solution/0600-0699/0618.Students%20Report%20By%20Geography/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

None

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT MAX(CASE
		WHEN continent = 'America' THEN name
	END) AS America, MAX(CASE
		WHEN continent = 'Asia' THEN name
	END) AS Asia
	, MAX(CASE
		WHEN continent = 'Europe' THEN name
	END) AS Europe
FROM (
	SELECT name, continent, row_number() OVER (PARTITION BY continent ORDER BY name) AS rk
	FROM student
) t
GROUP BY rk
```

<!-- tabs:end -->
