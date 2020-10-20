# [597. Friend Requests I: Overall Acceptance Rate](https://leetcode.com/problems/friend-requests-i-overall-acceptance-rate)

[中文文档](/solution/0500-0599/0597.Friend%20Requests%20I%20Overall%20Acceptance%20Rate/README.md)

## Description

None

## Solutions

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
