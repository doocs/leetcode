# Workflow: một translation unit

Thực hiện trong phạm vi task đã được giao; đây là workflow mặc định cho cả worker tuần tự và song song.

## 1. Preflight

Đọc read set trong `translation/INSTRUCTIONS.md`. Xác minh context ready, source snapshot, target mapping, ownership, source/target hash hiện tại và quyền ghi. Đọc bản dịch/report cũ nếu có để không xoá sửa của người dùng. Task thiếu source hoặc target mâu thuẫn mapping thì blocked phần đó.

Nếu tự chọn việc, lấy unit pending/stale hợp lệ tiếp theo từ inventory theo order/dependencies; coordinator xác nhận assignment trước khi ghi. Không chọn unit đang có owner sống. Không tạo task bằng phỏng đoán từ tên file.

## 2. Đọc và lập coverage

Đọc nguyên owned source, gồm code, note, table, caption, footnote và included content thuộc scope. Xác định các vùng translate/protect/structural-control, version, terminology và boundary. Lập mapping source block → target block đủ để phát hiện thiếu/thêm; không cần tạo một record cho từng từ.

Unit quá lớn thì chia lại có kiểm soát trước khi dịch. Neighbor/định nghĩa được đọc để hiểu, không tự nhập vào target. Source extraction chưa chắc chắn thì ghi locator cần kiểm chứng, không dựng lại bằng kiến thức.

## 3. Dịch

Dịch theo thứ tự source, giữ logic, cấu trúc và code policy. Không tóm tắt vì sắp hết context. Không thêm lời mở đầu/kết luận/giải thích. Giữ những chỗ tác giả cố ý sai để minh hoạ, chỉ report nghi vấn bên ngoài target.

Ghi vào file tạm thuộc vùng được phép rồi thay target có kiểm soát khi có thể. Nếu chỉ có công cụ sửa trực tiếp, dùng một writer và checkpoint; không giả rằng thao tác là atomic. Không công bố draft đang viết dở như trang đã verified.

## 4. Self-check và review

Đối chiếu source→target và target→source; so protected spans theo policy, kiểm tra condition/negation/modal/quantifier, terminology, structure và language residue. Chạy kiểm tra liên quan được context quy định, ghi command/method, phạm vi, exit status hoặc observation thực.

Hoàn tất phần dịch và self-check mới chuyển `translated`. Boundary tồn tại thì reconcile trước final semantic review. Reviewer full source-vs-target theo mode đã chọn; nếu cùng một agent thì report đúng `sequential-self-review`. Reviewer sửa cần ownership; report-only không tự sửa target.

## 5. Kết thúc unit

Có lỗi: fix nhỏ nhất có source evidence, chạy lại kiểm tra bị ảnh hưởng. Chưa đọc đủ/thiếu tool/thiếu source thì ghi blocked với `resume_state` và blocker; draft được giữ nhưng không verified.

Chỉ verified khi QA-05 đạt với hash hiện tại. Worker ghi report riêng; coordinator cập nhật progress từ report đã kiểm chứng. Source pin, scope dịch, coverage, checks, finding và next action phải truy vết được.

## 6. Lặp tiếp

Trong phạm vi user đã giao, tiếp tục unit kế tiếp theo cùng pipeline. Không dừng ở kế hoạch nếu có thể thực thi. Không tự mở rộng scope, tăng số agent hoặc publish. Hết khả năng thực thi trong session thì checkpoint chính xác, bàn giao phần đã làm và phần còn lại; không hứa chạy nền.

Commit từng file chỉ khi `git.commit` được bật và có uỷ quyền thật. Push cần uỷ quyền riêng. Các quyền này không được suy ra từ câu “dịch tiếp”.
