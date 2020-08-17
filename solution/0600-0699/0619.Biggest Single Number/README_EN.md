# [619. Biggest Single Number](https://leetcode.com/problems/biggest-single-number)

[中文文档](/solution/0600-0699/0619.Biggest%20Single%20Number/README.md)

## Description
None


## Solutions


<!-- tabs:start -->

### **SQL**

```
SELECT (
		SELECT num
		FROM my_numbers
		GROUP BY num
		HAVING COUNT(*) = 1
		ORDER BY num DESC
		LIMIT 1
	) AS num
```

<!-- tabs:end -->
