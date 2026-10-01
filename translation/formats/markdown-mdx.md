# Format adapter: Markdown / MDX

Áp cho Markdown và biến thể thực tế đã nhận diện; không coi mọi file `.md` có cùng parser.

Giữ heading level, thứ tự section, paragraph, nested list, blockquote, reference definition, footnote, emphasis, fence language/info string, directive/container, include và HTML/JSX. Dịch text hiển thị, không đổi ID, import/export, prop/key, expression hoặc variable interpolation.

Frontmatter phải theo allowlist trường prose của project. `title`/`description` thường là prose nhưng `slug`, `permalink`, `id`, taxonomy key có thể tham gia route: phải phân loại trước. Parse bằng parser tương thích với project; giá trị có `: `, ` #`, quote, backslash hoặc newline cần escape/serialization hợp lệ. Không dùng regex sửa hàng loạt YAML rồi coi parse đã pass.

MDX text quanh component có thể dịch; JavaScript expression, import và tên component giữ nguyên. Component prop string chỉ dịch khi được xác định là label hiển thị và được phép; không coi mọi quoted string là prose. Các shortcode hoặc directive như admonition phải giữ delimiter và nesting.

Heading được dịch có thể đổi anchor tự sinh. Chọn một chiến lược: giữ explicit ID hợp lệ hoặc map fragment sang anchor được renderer tạo thật; cập nhật link bị ảnh hưởng trong vùng cho phép. Không tạo duplicate ID. Nếu source có explicit anchor, mặc định bảo toàn.

Code fence: không để backtick trong ví dụ đóng fence bên ngoài. Không đếm mọi dòng chứa ba backtick để kết luận cân bằng; cần xét độ dài fence, loại fence, indentation và context. Table prose dịch nhưng giữ cell association; escape `|` khi cần theo parser. Không chuyển structured output thành table đẹp hơn.

Required checks: parser/frontmatter, structure comparison, protected spans, include/footnote/anchor resolution, target rendering nếu dạng extension có hành vi cần kiểm chứng. Công cụ lint được chọn theo dialect; một CommonMark parser không chứng minh MDX/AsciiDoc đúng.
