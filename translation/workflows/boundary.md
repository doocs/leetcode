# Workflow: continuity giữa các unit

Chỉ cần cho source liên tục bị chia giữa construct hoặc khi chunk của một file phải ghép lại. Hai trang docs độc lập không mặc định là sentence continuation.

## Phát hiện và sidecar

Ghi loại boundary: clean, sentence, paragraph, code, table, list, footnote, figure-caption, query-plan hoặc mixed. Ghi source locator, target locator, construct ID, neighbor IDs và phần wrapper local cần bỏ khi ghép. Không dùng tên file để đoán loại boundary.

Worker chỉ dịch phần source mình sở hữu. Context neighbor không được copy. Với câu tiếng Việt cần đổi trật tự khiến ranh giới fragment không thể chia tự nhiên, để integrator được giao cả construct xử lý sau; giữ coverage/source ownership map, không để hai worker tự dịch lại cả câu.

## Review và quyền ghi

Đọc nguyên văn hai phía cùng hình nguồn nếu cần. Khi construct chỉ qua hai part, có thể review hai wave: A `(1,2), (3,4), ...`; B `(2,3), (4,5), ...` sau khi A kết thúc. Mỗi boundary có report riêng; coordinator tổng hợp, không cùng append file chung.

Construct qua nhiều part phải có một reviewer/integrator được giao cả interval, hoặc lock mọi target liên quan. Hai wave cặp không tự giải quyết race cho interval dài hoặc cho reviewer đồng thời sửa glossary. Bản sửa cuối interval phải invalidate evidence cũ của các unit bị ảnh hưởng.

## Ghép theo cấu trúc

- Sentence/paragraph: nối thành logic source; không thêm dấu chấm hoặc paragraph break vì hết file. Chỉnh punctuation/capitalization dựa trên câu hoàn chỉnh.
- Code/query plan: ghép exact fragments theo source; loại wrapper fence local đã khai báo, không xoá code; giữ indentation và tree relation. Không tự thêm dấu ngoặc, SQL suffix hoặc node thiếu.
- Table: ghép đúng row/cell; loại header lặp do pagination khi xác minh, không bỏ header có ý nghĩa. Caption và footnote phải tiếp tục gắn đúng bảng.
- List/footnote: giữ numbering, nesting, reference-body mapping; không reset vì sang part.

Không xoá tất cả HTML comment bằng regex để “dọn metadata”; chỉ metadata do pack tạo và đã được xác định, không comment gốc/directive.

## Gate

Boundary cần review phải có evidence và hash của mọi target liên quan. Sau ghép, đọc construct hoàn chỉnh và kiểm tra không thiếu/lặp/đổi nghĩa; kiểm tra lại unit/neighbor bị sửa. `clean` là một kết luận có căn cứ, không phải mặc định cho boundary chưa đọc. Còn thiếu neighbor thì blocked hoặc partial scope được ghi rõ, không tự viết nối.
