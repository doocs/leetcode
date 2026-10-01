# Prompt: khởi tạo context cho project mới

Bạn đang cấu hình bộ dịch có sẵn, chưa được phép thay đổi nội dung nguồn hoặc publish.

Đọc `AGENTS.md`/instruction hiện hữu nếu có, rồi `translation/INSTRUCTIONS.md`, sáu core rule, `translation/PROJECT_CONTEXT.yaml`, `translation/PROJECT_RULES.md` và `translation/GLOSSARY.md`. Không nạp toàn bộ example/audit làm instruction của project.

Khảo sát nguồn thật và ghi: loại nguồn; ngôn ngữ; edition/version; snapshot kiểm chứng được; source scope; cấu trúc target; file được bảo vệ; nav/TOC; includes/assets; tool/parser/build đang có. Đọc một unit đại diện đủ để xác định vấn đề format/domain, nhưng không dùng sample để tuyên bố đã review corpus.

Điền context và chỉ kích hoạt source adapter, format/domain, workflow cần thiết. Chốt mapping và policy comment/string/link/image; mặc định bảo toàn code, không Git write và một worker. Không sao chép Java/PDF/VuePress/30-worker/build command từ example khi source thực khác. Các path mẫu phải được thay bằng path đã xác minh.

Kiểm tra rule cũ có xung đột với core mới không. Ghi quyết định giữ/sửa/ngưng dùng và lý do trong PROJECT_RULES; không để hai canonical instruction đối lập cùng hiệu lực. Chỉ xoá/di chuyển rule cũ nếu người dùng cho phép; khi chưa có quyền, ghi conflict và dừng phần chịu ảnh hưởng. Không tự mở rộng quyền ghi để vượt instruction hiện hữu.

Tạo source map và thứ tự unit, scope exclusions có lý do, glossary baseline đúng domain, phương pháp kiểm tra thực hiện được và thư mục state/report riêng không bị publish. Quyền ghi phải phân theo vai trò và có authorization reference. Với reviewer report-only, allowlist chỉ chứa vùng report được giao, không chứa target dịch. Những vai trò không cần ghi có thể để allowlist rỗng.

Nếu thông tin có thể đọc từ repo/nguồn, tự kiểm chứng thay vì hỏi lại. Nếu thiếu nguồn/quyền/scope không thể suy ra an toàn, để `needs_setup`, ghi blocker cụ thể và vẫn hoàn thành phần cấu hình không bị chặn. Chỉ chuyển `ready` khi các điều kiện bootstrap trong INSTRUCTIONS đạt.

Bàn giao: file cấu hình đã tạo/sửa; read set hiệu lực; scope/unit count theo inventory; các quyết định policy; blocker còn lại; unit đầu tiên có thể dịch. Không dịch toàn project trong tác vụ bootstrap trừ khi người dùng đồng thời giao việc đó. Không tự commit/push/deploy.
