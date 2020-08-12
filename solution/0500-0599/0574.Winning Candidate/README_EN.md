# [574. Winning Candidate](https://leetcode.com/problems/winning-candidate)

[中文文档](/solution/0500-0599/0574.Winning%20Candidate/README.md)

## Description
None


## Solutions


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
