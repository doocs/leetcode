# Workflow: nguồn đổi và dịch tiếp

Mục tiêu là giảm xung đột, giữ source nguyên vẹn và phát hiện bản dịch lỗi thời. Không hứa “không bao giờ conflict”: upstream có thể thêm file cùng tên, đổi layout, route hoặc semantics của include.

## Snapshot cũ và mới

Giữ source snapshot đã dùng cho mỗi unit. Đọc trạng thái Git/worktree trước mọi thao tác; fetch/merge/rebase hoặc đổi source snapshot cần tác vụ và quyền phù hợp. Không chạy `reset --hard`, xoá untracked file hoặc force-push để làm sạch môi trường dịch.

Sau khi có snapshot mới được xác nhận, diff source theo mapping và dependency. So sánh hash từng unit, không chỉ mốc HEAD toàn repo. Include/snippet/template đổi phải đánh giá các trang phụ thuộc, dù file prose của trang không đổi.

## Phân loại

| Thay đổi                           | Cách xử lý                                                                    |
| ---------------------------------- | ----------------------------------------------------------------------------- |
| Source mới                         | Thêm pending vào inventory theo scope/order                                   |
| Source đổi                         | Đánh stale; giữ bản đã dịch và report cũ để đối chiếu                         |
| Source đổi tên/di chuyển           | Xác minh mapping/rename, target route và incoming links; không tự xoá tạo lại |
| Source bị xoá                      | Đánh orphan để review; không tự xoá bản dịch                                  |
| Dependency hoặc policy đổi         | Review những unit bị ảnh hưởng; không giả định hash prose đủ                  |
| Không đổi source/context liên quan | Giữ evidence còn hiệu lực, không dịch lại vô ích                              |

## Update có kiểm soát

Dịch/review phần bị ảnh hưởng với đầy đủ context và source snapshot mới. Mapping coverage phải phân biệt phần giữ lại và phần cập nhật. Không copy nội dung version mới vào một unit vẫn mang nhãn version cũ.

Chỉ cập nhật `reviewed_source_hash` cho unit thực sự được review. Batch dịch một trang không được đánh toàn bộ repo là “đã sync đến HEAD”. Source gốc phải tiếp tục read-only trong phạm vi translation task.

Nếu có thay đổi local chưa biết của người dùng, dừng ghi vùng đó và nêu conflict cụ thể. Không tự giải bằng chọn bản agent mới nhất. Source map, glossary và route registry cập nhật bởi một coordinator; ghi migration decision và các unit stale còn lại.
