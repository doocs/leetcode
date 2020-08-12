# [574. 当选者](https://leetcode-cn.com/problems/winning-candidate)

[English Version](/solution/0500-0599/0574.Winning%20Candidate/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **SQL**

```
SELECT Name
FROM Candidate, 
    (SELECT CandidateId,
         count(*) AS total
    FROM Vote
    GROUP BY  CandidateId
    ORDER BY  total DESC limit 1) AS tmp
WHERE Candidate.id = tmp.CandidateId
```

<!-- tabs:end -->
