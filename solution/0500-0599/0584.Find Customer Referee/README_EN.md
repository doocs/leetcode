# [584. Find Customer Referee](https://leetcode.com/problems/find-customer-referee)

[中文文档](/solution/0500-0599/0584.Find%20Customer%20Referee/README.md)

## Description
None


## Solutions


<!-- tabs:start -->

### **SQL**

```
SELECT name
FROM customer
WHERE referee_id != 2
        OR referee_id is null
```

<!-- tabs:end -->
