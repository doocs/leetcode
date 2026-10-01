# Format adapter: HTML

Dịch text node trong phạm vi content; giữ DOM hierarchy và association giữa label, bảng, figure/caption, link và footnote. `script`, `style`, inline event handler, data/config và template expression không phải prose.

Attribute chỉ được dịch khi có vai trò hiển thị/ngôn ngữ và nằm trong allowlist: ví dụ alt/title/aria-label. Không đổi `id`, `class`, `name`, `data-*`, binding hay URL action chỉ vì chứa ngôn ngữ nguồn. `lang` và locale metadata thay ở bản dịch được cho phép, không sửa HTML gốc.

Bảo toàn entity, code/pre whitespace, math và placeholder. Dịch text trong form minh hoạ không được gửi form hoặc thực hiện thao tác lên website thật. URL/anchor/asset mapping áp STRUCT-03; relative URL phải resolve theo base thực.

Parse và kiểm tra DOM; không dùng search/replace toàn HTML. Kiểm tra visible text và accessibility text cùng context để tránh aria-label còn nguyên nghĩa cũ. Nếu cần visual verification, render bản local/snapshot an toàn; không suy ra UI đúng từ HTML parse thành công.
