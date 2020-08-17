# [619. 只出现一次的最大数字](https://leetcode-cn.com/problems/biggest-single-number)

[English Version](/solution/0600-0699/0619.Biggest%20Single%20Number/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


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
