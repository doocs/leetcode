# Progress — bảng tổng hợp từ report đã kiểm chứng

Scope/snapshot: chưa khởi tạo. Không dùng số file tồn tại thay cho số unit đã review.

| Unit | Source hash | Translation | Review | Boundary | Checks | State report |
| ---- | ----------- | ----------- | ------ | -------- | ------ | ------------ |

Chỉ coordinator cập nhật bảng. State report từng unit là bằng chứng chi tiết; bảng này không thay thế report. Khi hash không khớp hoặc nguồn đổi, ghi stale; không giữ dấu hoàn tất cũ một cách mặc định.

Tổng hợp riêng: expected units, translated, verified, blocked, stale và excluded có lý do. Không cộng partial vào verified. Commit/push/publication báo riêng, không gộp vào translation status.
