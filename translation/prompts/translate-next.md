# Prompt: dịch unit kế tiếp

Đọc và tuân thủ `translation/INSTRUCTIONS.md` cùng read set được context kích hoạt. Dịch đầy đủ unit pending/stale hợp lệ tiếp theo theo source map, thứ tự và dependencies; nếu đã được giao một unit cụ thể thì chỉ làm unit đó.

Trước khi ghi, xác minh snapshot/source hash, target mapping, owner, quyền ghi và bản dịch hiện có. Thực hiện `translation/workflows/translation.md` từ đọc nguyên source đến self-check, review và required checks. Neighbor chỉ để hiểu context; không copy content ngoài scope. Code/output/identifier theo policy, không tự modernize hoặc bổ sung giải thích.

Mỗi lần chỉ ghi target/report thuộc assignment. Các unit trong một task nhiều file vẫn phải có review và evidence riêng; không đánh tất cả done vì đã tạo file. Nếu cần sửa glossary, ghi proposal thay vì sửa trong worker phase.

Không xác minh được nguồn hoặc required check thì checkpoint và báo đúng trạng thái, không đoán hoặc tự giảm QA. Nếu context yêu cầu independent review, giao reviewer thực khi công cụ hỗ trợ; chưa có reviewer thì dừng ở translated/reviewing. Cùng agent review lần hai phải ghi đúng sequential-self-review.

Kết thúc bằng báo cáo ngắn: unit/source pin; target; phạm vi đã đọc/dịch/review; checks thực chạy; trạng thái; blocker; bước tiếp theo. Commit/push chỉ khi được uỷ quyền rõ, không tự làm từ prompt này.
