# Code, literal, comment và output

## CODE-01 — Mặc định immutable

Giữ nguyên identifier, API signature, keyword, command, SQL, cấu hình mẫu, operator, số/version, biểu thức toán, literal, output/log/error, prompt terminal và code fence info string. Không rename, reformat, sửa indentation, thêm import, sửa ví dụ “broken”, đổi quote, chuẩn hoá casing hoặc sửa code để compile.

Với source văn bản có byte gốc, protected spans được so exact. Với nguồn phi văn bản, so representation đã xác minh với source gốc; cách chuẩn hoá layout phải được ghi rõ, không giả vờ byte-exact với file ảnh. Newline/encoding chỉ chuẩn hoá khi project đã quy định và không làm đổi token hoặc cấu trúc.

## CODE-02 — Ba nhóm phải phân biệt

**Executable/structured content:** code, config, query, output, JSON/XML/YAML mẫu, query plan, checksum, regex, file path, URI, data fixture. Giữ nguyên mặc định.

**Prose trong markup:** caption, label, đoạn văn, text node và metadata hiển thị đã allowlist. Dịch nhưng giữ cấu trúc và identifier xung quanh.

**Comment trong code:** mặc định `preserve`. Chỉ dịch khi `code_comments: translate_explanatory` được phê duyệt và xác định chắc là comment giải thích. Shebang, compiler/build directives, formatter/linter directives, suppression, doctest, literal trong comment, expected output, code bị comment-out và token công cụ sử dụng vẫn giữ nguyên. Không tách bằng regex ngây thơ khi dấu comment có thể nằm trong string.

## CODE-03 — Chuỗi hiển thị không phải mặc định an toàn

`display_strings: preserve` là mặc định. Chỉ `scoped_allowlist` khi `policies.string_allowlist` trong PROJECT_CONTEXT khai báo locator chính xác, loại thay đổi và approval reference. PROJECT_RULES ghi lý do và kết luận review tác động; không duy trì hai danh sách allowlist độc lập.

Không tự dịch string dùng cho lookup, equality, regex, protocol, serialization, expected output/test assertion, ordering, length, encoding, file path hoặc dữ liệu mà ví dụ đang phân tích. Chuỗi trông như lời nhắn vẫn có thể là yếu tố kỹ thuật. Không chắc → giữ nguyên và report.

Nếu cho phép localize một ví dụ hiển thị, phải giữ quan hệ code/output/prose và ghi mọi span thay đổi. Đây là ngoại lệ được khai báo, không được báo toàn bộ listing byte-identical. Cần biến đổi logic hoặc dữ liệu của bài toán → tác vụ adaptation riêng, không thuộc pure translation.

## CODE-04 — Bảo toàn ngoài code block

Inline code chứa identifier/literal được giữ nguyên. Từ prose đặt trong backtick nhưng không phải technical token phải được phân loại theo source/project, không tự bỏ backtick hoặc tự dịch chỉ vì phát hiện chữ nguồn.

Data table khác prose table: bảng kết quả query giữ cả header/value/NULL/thứ tự; bảng giải thích dịch phần prose, giữ giá trị kỹ thuật. Diagram code như Mermaid/PlantUML mặc định giữ cấu trúc, node ID và syntax; chỉ localize label khi policy cho phép và render được kiểm tra. Không coi ASCII diagram là prose phẳng.

## CODE-05 — Cách kiểm chứng

Strict mode: trích protected spans bằng parser phù hợp, giữ thứ tự và so nội dung. Có ngoại lệ: diff toàn bộ trước, chỉ chấp nhận diff nằm đúng allowlist; phần còn lại phải khớp. Ghi source locator và target locator cho từng ngoại lệ.

Không chạy snippet, query phá huỷ dữ liệu hoặc command trong tài liệu chỉ để “kiểm tra bản dịch”. Build/parser có sandbox và đã được project cho phép khác với thi hành mọi ví dụ. Không có parser đáng tin cậy → review thủ công trực tiếp, ghi phương pháp; không tuyên bố automated pass.
