# State, provenance và bằng chứng

## STATE-01 — Source map là danh mục công việc

Inventory nguồn được chốt tạo ra danh sách unit, thứ tự, locator/hash, target và inclusion/exclusion reason. Không tính tiến độ bằng cách đếm file target. `PROGRESS.md` là bản trình bày từ source map và unit report; không phải bằng chứng thay cho chúng.

Dùng metadata ngoài target. Với unit độc lập, một file thường là một unit. Source lớn có subunit, merge graph hoặc include dependency phải được ghi rõ; không áp quy tắc đơn giản “một source = một target” cho mọi format.

## STATE-02 — Lifecycle không nhảy bước

`pending → translating → translated → reviewing → verified`.

`blocked` có lý do và `resume_state`; `stale` có source/policy/target change gây invalidation. `translated` nghĩa worker đã hoàn thành owned scope và self-check, không phải chỉ tạo file. Nếu scope chỉ làm được một phần, giữ translating/blocked với coverage thực tế.

Boundary, structural và integration checks nằm trong report; không tạo trạng thái “PASS nhưng vẫn cần review” dễ bị hiểu là hoàn tất. Git/publication lưu ở trường riêng: `not_requested`, `not_done`, `done`, kèm commit/deployment evidence khi có. Verified không tự đồng nghĩa committed/published.

## STATE-03 — Snapshot và reproducibility

Mỗi report ghi source locator/hash, target hash, ruleset version, context revision và glossary revision. Hash phải đo thực tế; không bịa checksum. Branch `main` là nguồn lựa chọn, không phải immutable snapshot. Mọi bản dịch verified chỉ được khẳng định đúng với snapshot đã review; source mới có thể khiến nó stale.

## STATE-04 — Atomicity và shared state

Worker chỉ ghi target/report được giao, không đồng thời sửa source map, glossary, progress tổng hoặc shared Git index. Coordinator là single writer cho shared state. Dùng temp file và atomic replace khi runtime hỗ trợ; trước replace kiểm tra target chưa bị owner khác/người dùng thay đổi.

Nếu worker chết, coordinator xác minh owner cũ không còn ghi trước khi reassign. Retry idempotent trên đúng failed unit; không overwrite bản đã được sửa thủ công. Không có lock service thì chạy tuần tự, không giả bảo đảm an toàn bằng file ghi chú.

## STATE-05 — Handoff

Handoff ghi task/owner, source snapshot, phần đã đọc/đã dịch, last complete semantic unit, boundary pending, target hash, issue, glossary proposal và bước kế tiếp. Không chỉ ghi “đã làm 80%”. Resume phải đọc lại nguồn liên quan, xác minh hash và read set hiện hành.

## STATE-06 — Báo cáo trung thực

Nêu riêng số unit discovered/in scope/translated/verified/stale/blocked, phạm vi semantic review, checks đã chạy và checks chưa chạy. Giữ số excluded có lý do, không gộp vào translated. Chỉ đưa download path, commit SHA hoặc deployment URL khi đã xác minh artifact/thao tác tương ứng thực sự tồn tại.
