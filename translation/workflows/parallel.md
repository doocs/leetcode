# Workflow: nhiều agent, không ghi đè nhau

Chỉ nạp khi `execution.mode: parallel` và môi trường thật sự có khả năng chạy nhiều worker. Nếu không, dùng tuần tự và ghi rõ mode thực tế; không báo số subagent không tồn tại.

## Coordinator là một writer cho state chung

Coordinator tạo inventory và assignment độc quyền trước khi dispatch. Mỗi task có unit ID, source pin/hash, target path, dependency, owner, attempt ID, policy/glossary snapshot và report path riêng. Giới hạn concurrency là số worker thực có thể vận hành với đủ context/QA, không tự mặc định 30.

`PROJECT_CONTEXT`, `PROJECT_RULES`, glossary, source-map và progress tổng hợp chỉ có coordinator sửa ở checkpoint; worker không append cùng một file log/report chung. Git index, commit và push được serialize bởi integrator, không dùng chung index cho nhiều worker.

## Phạm vi worker

Một target chỉ có một writer trong một thời điểm. Assignment không cấp quyền vượt allowlist. Worker chỉ sửa output và report của assignment hiện hành; mỗi retry có attempt ID mới. Trả về artifact/patch và evidence, không tự tích hợp unit khác.

Reviewer report-only có thể đọc snapshot ổn định. Reviewer sửa trực tiếp phải được chuyển ownership, worker cũ dừng ghi. Boundary reviewer cần quyền với toàn bộ interval bị tác động; khoá cặp chưa đủ nếu construct kéo dài ba unit.

## Tránh race khi thất bại và retry

Heartbeat/lease hoặc lock là capability của runtime, không được giả định có chỉ vì ghi trong Markdown. Khi runtime thiếu lock, coordinator phải điều phối tuần tự việc cấp/thu hồi quyền ghi. Owner mất liên lạc chưa đồng nghĩa đã dừng; không giao lại file cho đến khi xác minh hoặc cô lập attempt vào đường dẫn mới.

Kiểm tra source/target hash trước khi áp patch. Hash lệch thì reconcile, không last-write-wins. Chỉ retry unit fail và dependency/boundary bị ảnh hưởng; không dịch lại toàn corpus vì một lỗi.

## Nhận kết quả

Không tin trạng thái “done” của worker một cách máy móc. Coordinator xác minh artifact tồn tại, source/target đúng assignment, coverage/checks và hash. Sau đó mới tổng hợp progress. Term proposal được duyệt ở checkpoint; đổi glossary phải đánh version và đánh dấu unit liên quan cần review.

Không cho worker dịch và integrator package cùng các file chưa ổn định. Đóng batch trước khi release; read-only snapshot được dùng cho QA và ZIP.
