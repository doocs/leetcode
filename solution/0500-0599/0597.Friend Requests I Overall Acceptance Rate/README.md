# [597. 好友申请 I ：总体通过率](https://leetcode-cn.com/problems/friend-requests-i-overall-acceptance-rate)

[English Version](/solution/0500-0599/0597.Friend%20Requests%20I%20Overall%20Acceptance%20Rate/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
None


## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **SQL**

```
(SELECT ifnull(round(count(*) / 
    (SELECT count(*)
    FROM 
        (SELECT count(*)
        FROM friend_request
        GROUP BY  sender_id,send_to_id) request),2),0.0) AS accept_rate
        FROM 
            (SELECT count(*)
            FROM request_accepted
            GROUP BY  requester_id,accepter_id) accepter)
```

<!-- tabs:end -->
