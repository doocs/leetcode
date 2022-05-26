# [618. Students Report By Geography](https://leetcode.com/problems/students-report-by-geography)

[中文文档](/solution/0600-0699/0618.Students%20Report%20By%20Geography/README.md)

## Description

None

## Solutions

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
