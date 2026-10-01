# Prompt: thích nghi bộ rule với context khác

Khảo sát project và đọc `translation/INSTRUCTIONS.md` trước. Mục tiêu là dùng lại core, chỉ chỉnh context, glossary và project rule cần thiết; không tìm-thay toàn bộ tên công nghệ trong pack.

Xác định bốn trục độc lập: source kind, target format, technical domain và execution/release workflow. Chọn module đang có; nếu chưa phù hợp, tạo adapter/profile mới với phạm vi cụ thể và regression case. Không đưa PDF rule vào project chỉ dịch repo docs hoặc glossary ngành cũ vào tài liệu mới.

So sánh policy hiện hữu với source và yêu cầu người dùng. Giải quyết rõ quyền ghi, code comments, display strings, links/anchors, reviewer mode, source pin và release quyền; không tự đổi default thành permissive. Ghi quyết định thay đổi có căn cứ.

Nếu cần sửa core, nêu rule ID, lý do, compatibility impact, các project có thể ảnh hưởng và test case mới. Đánh version, giữ migration note; không làm yếu fidelity hoặc QA để hợp thức hoá một output có lỗi.

Bàn giao cấu hình đã kiểm chứng và danh sách khác biệt so với base. Những phần chưa xác minh phải để needs_setup/blocker; không giả thiết công cụ hoặc version từ project cũ.
