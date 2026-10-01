# Thêm domain mà không nhân bản core

Tạo một file profile riêng, ví dụ `translation/domains/go.md`, rồi thêm đường dẫn vào context. Không chỉnh source adapter chỉ vì đổi ngôn ngữ lập trình.

Profile mới chỉ cần: scope/version context; glossary baseline theo concept; danh sách dễ nhầm khi semantic review; vùng code/literal/output đặc thù cần bảo vệ; trường hợp format riêng cần adapter khác. Dùng glossary project cho quyết định đã chốt và ngoại lệ có scope.

Không chép toàn bộ CORE/QA hoặc đưa command build chưa kiểm chứng vào profile. Không cho profile “sửa kiến thức source cho đúng”, “tóm tắt phần khó” hoặc tự đổi string/identifier. Nếu domain thực sự cần một policy mới, thiết kế thay đổi core có version và regression case, thay vì lách trong một prompt.

Khi dự án có nhiều domain, nạp profile liên quan đến scope được giao. Phân biệt term đồng âm theo context và PROJECT_RULES. Không cho agent phải đọc mọi glossary của toàn bộ công nghệ khi chỉ dịch một chương độc lập.
