# Prompt: điều phối dịch trong scope đã giao

Đọc `translation/INSTRUCTIONS.md`, context, rule, glossary và các module được bật. Xác nhận scope người dùng giao; bootstrap khi cần rồi thực thi, không chỉ trả kế hoạch.

Từ source map, tạo assignment có ownership và snapshot rõ cho các unit còn lại. Concurrency theo config và capability thực. Không có subagent thì làm tuần tự, không báo nhiều agent giả. Mọi worker nhận read set, raw source đúng scope, context cần thiết và report path riêng.

Điều phối translation → self-check → boundary nếu có → semantic review → integration checks. Một writer cho shared state và Git index. Không package khi worker còn đang sửa output. Kết quả worker phải được xác minh qua artifact/diff/hash/coverage và check evidence trước khi tiến độ thay đổi.

Retry phần fail; giữ unit đã verified còn hợp lệ. Với lỗi nguồn/tool ở một unit, ghi blocker và có thể tiếp tục unit độc lập khác trong scope, không dừng vô cớ toàn corpus cũng không che phần thiếu.

Nếu user yêu cầu từng file review–commit–push, kiểm tra đủ quyền và serialize Git sau verified từng unit. Nếu không có yêu cầu đó thì không commit/push. Main không tự động là đích.

Trước khi kết thúc session, ghi checkpoint thật và báo số unit pending/translated/verified/blocked/stale, checks còn thiếu và artifact thực có. Chỉ gọi toàn scope hoàn tất khi mọi điều kiện đạt. Không hứa tác vụ tiếp tục chạy sau khi session đã dừng.
