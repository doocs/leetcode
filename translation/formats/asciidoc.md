# Format adapter: AsciiDoc

Áp cho AsciiDoc/Antora khi được project xác nhận. Không tự chuyển sang Markdown nếu nhiệm vụ chỉ là dịch.

Giữ section level, explicit ID, attribute name/reference `{name}`, macro, include/xref, callout number, listing delimiter, table syntax và conditional directive. Dịch prose, title và admonition body; giữ loại admonition. Thuộc tính chứa text hiển thị chỉ dịch theo allowlist, không đổi thuộc tính ảnh hưởng build/version/component/module.

Listing/source block, command/output và inline literal theo CODE. `include::` cần giữ target semantic và tag/line selection; snippet được giao owner riêng. Không expand include thủ công làm nội dung xuất hiện hai lần. Callout text có thể dịch nhưng số callout phải tiếp tục trỏ đúng code.

`xref:` trong Antora có component/version/module/page semantics; mapping theo resolver của project, không áp quy tắc đường dẫn Markdown. Kiểm tra attribute substitution, xref, attachment/image và nav locale bằng build/parser hiện hữu khi có.

Metadata nội bộ nằm ngoài content tree được publish. Phiên bản component và tên API không được hiện đại hoá. Tác vụ dịch không tự cho phép sửa playbook hoặc source upstream; integration có scope riêng.
