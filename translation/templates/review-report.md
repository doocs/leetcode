# Review report — điền unit ID thật

- Source snapshot/locator/hash: chưa kiểm chứng.
- Target path/hash: chưa kiểm chứng.
- Read set/policy snapshot: chưa ghi.
- Reviewer và mode: chưa ghi.
- Coverage: chưa review; không mặc định full.
- Required checks: chưa chạy.
- Verdict: pending.

## Findings

Ghi từng finding: ID; rule ID; severity; source locator; target locator; evidence ngắn; expected fix; resolved/unresolved và evidence sau fix. Không dùng ví dụ PASS như kết quả thực tế.

## Coverage và checks

Ghi rõ source blocks đã đọc và target blocks đã đối chiếu. Với sampling ghi sample cụ thể và phần chưa review. Mỗi check có method/command, input snapshot, scope, kết quả và log/observation. Thiếu tool ghi not_run kèm lý do.

## Handoff

Ghi blocker, phần có thể dùng, phần không được publish, next source block và action. Nếu reviewer report-only, chuyển patch proposal cho owner; không tự sửa ngoài quyền.
